package se.chalmers.TDA367.group13;

import javax.swing.text.html.parser.Entity;

import org.newdawn.slick.Graphics;

public class GameView {
	GameModel model; 
	
	public GameView(GameModel model){
		this.model = model;
	}

	public void render(Graphics g){
		renderLevel(g);
		renderEntities(g);
		renderEnemies(g);
		renderPlayer(g);
	}

	public void renderLevel(Graphics g){
		model.getLevel().render(g);
	}
	
	public void renderEnemies(Graphics g){
		for(Enemy e : model.getEnemies()){
			e.render(g);
		}
	}
	
	public void renderEntities(Graphics g){
		for(Entity e : model.getEntities()){
			e.render(g);
		}
	}
		
	public void renderPlayer(Graphics g){
		model.getPlayer().render(g);
	}
}
