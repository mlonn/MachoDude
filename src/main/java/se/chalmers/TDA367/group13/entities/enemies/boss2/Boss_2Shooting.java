package se.chalmers.TDA367.group13.entities.enemies.boss2;

import org.newdawn.slick.Animation;
import org.newdawn.slick.geom.Vector2f;


public class Boss_2Shooting extends  AbstractBoss_2State{

	public Boss_2Shooting(){
		velocity = new Vector2f(0,0);
		coolDown = 500;
	}
	
	public Boss_2Shooting(Animation a){
		super(a);
		velocity = new Vector2f(0,0);
		coolDown = 500;
	}
	
	
}
