package se.chalmers.TDA367.group13;

import java.util.LinkedList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.tiled.TiledMap;

import se.chalmers.TDA367.group13.entities.Block;
import se.chalmers.TDA367.group13.entities.Boss_1;
import se.chalmers.TDA367.group13.entities.Enemy;
import se.chalmers.TDA367.group13.entities.Enemy.State;
import se.chalmers.TDA367.group13.entities.Enemy_1;
import se.chalmers.TDA367.group13.entities.Entity.Direction;
import se.chalmers.TDA367.group13.entities.Player;
import se.chalmers.TDA367.group13.entities.Projectile;

public class Level {

	private Music music;
	private TiledMap map;
	private LinkedList<Block> blocks;
	private LinkedList<Enemy> enemies;
	private Image smallBackground;
	private Image background;
	private Camera camera;
	private Boss_1 boss;
	private LinkedList<Projectile> projectiles;
	private int score = 0;

	public Level(Camera camera, TiledMap map, Image background, Music music)
			throws SlickException {
		this.camera = camera;
		this.map = map;
		this.background = background;
		this.music = music;
		smallBackground = background.getSubImage(0, 0, 1216, 768);
		boss = new Boss_1(300, 300);
		boss.resize(5);

		projectiles = new LinkedList<Projectile>();
		blocks = new LinkedList<Block>();
		enemies = new LinkedList<Enemy>();

		for (int x = 0; x < map.getWidth(); x++) {
			for (int y = 0; y < map.getHeight(); y++) {
				String block = map.getTileProperty(map.getTileId(x, y, 0), "blocked", "false");
				String enemy = map.getTileProperty(map.getTileId(x, y, 1), "enemy", "false");
				if (block.equals("true")) {
					blocks.add(new Block(x * map.getTileWidth(), y
							* map.getTileWidth(), map.getTileImage(x, y, 0)));
				}
				switch (enemy) {
				case "1":
					enemies.add(new Enemy_1(x * map.getTileWidth(), y
							* map.getTileWidth(), this));
					break;
				default:
					break;
				}

			}
		}

	}

	public void render(Graphics g) {
		g.drawImage(smallBackground, 0, 0);
		boss.render(g);

		for (Block b : blocks) {
			b.render(g);
		}
		for (Enemy e : enemies) {
			e.render(g);
		}
		
		for (Projectile projectile : projectiles) {
			g.drawImage(projectile.getImage(), projectile.getX(), projectile.getY());
		}
		
		g.setColor(Color.white);
		g.drawString("Score: " + score, 100, 100);
	}

	public Camera getCamera() {
		return camera;
	}

	public void updateBackground() {
		smallBackground = this.background.getSubImage((int) camera.getX(), 0,
				1216, 768);
	}

	public LinkedList<Block> getBlocks() {
		return blocks;
	}

	public LinkedList<Enemy> getEnemies() {
		return enemies;
	}

	public void moveBlocks(float f) {
		for (Block r : blocks) {
			r.setX(r.getX() + f);
		}
	}

	public void moveEnemies(float f) {
		for (Enemy e : enemies) {
			e.setX(e.getX() + f);
		}
	}

	public void setMusic(Music music) {
		this.music = music;
	}

	public void loopMusic() {
		music.loop();
	}

	public float getWidth() {
		return (map.getWidth() * map.getTileWidth());
	}

	public void updateEnemies(Player player) {
		LinkedList<Enemy> dead = new LinkedList<Enemy>();
		for (Enemy e : enemies) {
			if(e.getX() < Game.WIDTH) {
				
				if (e.isDestroyed()) {
					dead.add(e);
					score++;
					System.out.println("Your score:" + score);
				}

				Rectangle nextYPos;
				if (e.getDirection() == Direction.LEFT) {
					nextYPos = new Rectangle(e.getX()-e.getWidth(), e.getY(), e.getWidth(), e.getHeight());
				} else {
					nextYPos = new Rectangle(e.getMaxX(), e.getY(), e.getWidth(), e.getHeight());
				}
				
				nextYPos.setY(e.getNextY());
				if (isLegal(nextYPos)) {
					e.setDirection(((e.getDirection() == Direction.LEFT) ?  Direction.RIGHT: Direction.LEFT));
				}
				
				if (Math.abs((e.getCenterX() - player.getCenterX())) < 20 || isLegal(nextYPos)) {
					e.setState(State.STILL);
				} else {
					e.setState(State.WALKING);
				}
		
				if (e.getState() == State.WALKING) {
					Rectangle nextXPos = new Rectangle(e.getX(), e.getY(), e.getWidth(), e.getHeight());
					if (e.getDirection() == Direction.LEFT) {
						nextXPos.setX(e.getNextLeftX());
						if (isLegal(nextXPos)) {
							e.moveLeft();
						} else {
							e.setDirection(Direction.RIGHT);
						}
					} else if (e.getDirection() == Direction.RIGHT) {
						nextXPos.setX(e.getNextRightX());
						if (isLegal(nextXPos)) {
							e.moveRight();
						} else {
							e.setDirection(Direction.LEFT);
						} 
					}
					nextXPos.setX(e.getNextLeftX());
					if (!isLegal(nextXPos)) {
						e.setState(State.STILL);
					}
				}
				if (Math.abs((e.getCenterX() - player.getCenterX())) < 250) {
					if (e.getCenterX() < player.getCenterX()) {
						e.setDirection(Direction.RIGHT);
					} else {
						e.setDirection(Direction.LEFT);
					}
					e.getWeapon().pointAt(player.getCenterX(), player.getCenterY(), e.getDirection());
					e.getWeapon().fireWeapon(e.getDirection());
				} else {
					if (e.getDirection() == Direction.RIGHT) {
						e.getWeapon().setImage(e.getWeapon().getRightImage());
					} else if (e.getDirection() == Direction.LEFT) {
						e.getWeapon().setImage(e.getWeapon().getLeftImage());
					}
					e.getWeapon().getImage().setRotation(0);
				}

			}
		}
		LinkedList<Projectile> removed = new LinkedList<Projectile>();
		for (Projectile projectile : getProjectiles()) {
			if (projectile.intersects(player)) {
				player.loseHealth();
				removed.add(projectile);
			} else if (isLegal(projectile)) {
				projectile.update();
			} else {
				removed.add(projectile);
			}
		}
		getProjectiles().removeAll(removed);
		enemies.removeAll(dead);
	}


	public boolean isLegal(Rectangle hitbox) {
		for (Block b : blocks) {
			if (hitbox.intersects(b)) {
				return false;
			}
		}
		return true;
	}

	public LinkedList<Projectile> getProjectiles() {
		return projectiles;
	}
	
	public int getScore(){
		return score;
	}

}
