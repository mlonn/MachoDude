package se.chalmers.TDA367.group13.entities.player;

import org.newdawn.slick.Animation;
import org.newdawn.slick.geom.Vector2f;

import se.chalmers.TDA367.group13.entities.IState;
import se.chalmers.TDA367.group13.util.Constants;
import se.chalmers.TDA367.group13.util.Direction;

public class PlayerStill extends AbstractPlayerState implements IState {
	private Vector2f velocity;
	
	public PlayerStill(){
		super();
		velocity = new Vector2f(0,Constants.gravity);
	}

	@Override
	public Vector2f getVelocity() {
		return velocity;
	}


}