package se.chalmers.TDA367.group13.model.entities.player;

import org.newdawn.slick.Animation;
import org.newdawn.slick.geom.Vector2f;

import se.chalmers.TDA367.group13.model.entities.IMoveableState;
import se.chalmers.TDA367.group13.util.Constants;
import se.chalmers.TDA367.group13.util.Direction;

public class PlayerWalking extends AbstractPlayerState implements IMoveableState {
	
	public PlayerWalking(){
		super();
		velocity = new Vector2f(0.25f,Constants.GRAVITY);
	}


}
