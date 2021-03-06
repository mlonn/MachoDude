package se.chalmers.TDA367.group13.model.entities.enemies;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.XMLPackedSheet;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Point;

import se.chalmers.TDA367.group13.model.entities.AbstractMoveableEntityState;
import se.chalmers.TDA367.group13.model.entities.IDestructable;
import se.chalmers.TDA367.group13.model.entities.MoveableEntity;
import se.chalmers.TDA367.group13.model.entities.weapon.Weapon;
import se.chalmers.TDA367.group13.util.Direction;


public abstract class Enemy extends MoveableEntity implements IDestructable {

	protected XMLPackedSheet enemySheet;
	protected Weapon weapon;
	protected int health, maxHealth;
	protected Circle aggroRange;
	private HealthBarEnemy healthbar;
	protected int scoreValue = 1;
	protected Animation stillLeft, stillRight, walkLeft, walkRight;
	protected Point rightShoulder, leftShoulder;
	protected Sound hurtSound, deathSound;
	protected AbstractEnemyState walking, still; 
	
	public Enemy(float x, float y, String sheet, String xml, int scale) throws SlickException {
		super(x, y - new Image(sheet).getWidth()*scale - 1, new Image(sheet));
		enemySheet = new XMLPackedSheet(sheet, xml);
		direction = Direction.LEFT;
		healthbar = new HealthBarEnemy();
	}
	
	@Override 
	public void render(Graphics g){
		healthbar.render(this,g);
		g.drawAnimation(state.getAnimation(direction), x, y);
		if (direction == Direction.LEFT){
			weapon.setCenterX(x + leftShoulder.getX());
			weapon.setCenterY(y + leftShoulder.getY());
		}
		else{
			weapon.setCenterX(x + rightShoulder.getX());
			weapon.setCenterY(y + rightShoulder.getY());
		}
		weapon.render(g, direction);
	}
		
	public void setDirection(Direction d) {
		direction = d;
	}
	
	public Direction getDirection() {
		return direction;
	}
	
	public void setState(AbstractMoveableEntityState s) {
		state = s;
	}

	public AbstractMoveableEntityState getStillState() {
		return still;
	}
	
	public AbstractMoveableEntityState getWalkingState() {
		return walking;
	}
	
	public AbstractMoveableEntityState getState() {
		return state;
	}

	
	public Weapon getWeapon() {
		return weapon;
	}



	public void loseHealth(){	
		health = health -1;
		hurtSound.play();
	}
	
	public boolean isDestroyed() {
		if(health <= 0){
		deathSound.play();
		return true;
		}
		return false;
	}
	
	public boolean isHurt(){
		return health < maxHealth;
	}
	
	public int getValue(){
		return scoreValue;
	}
	
	public Circle getAggroRange() {
		return aggroRange;
	}

	public void updateAggroRange() {
		aggroRange.setCenterX(getCenterX());
		aggroRange.setCenterY(getCenterY());
	}



}
