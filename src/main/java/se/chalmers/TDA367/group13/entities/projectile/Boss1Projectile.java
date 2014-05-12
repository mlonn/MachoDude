package se.chalmers.TDA367.group13.entities.projectile;

import org.newdawn.slick.Image;

import se.chalmers.TDA367.group13.util.Direction;

public class Boss1Projectile extends Projectile {

	public Boss1Projectile(float x, float y, Image image, float angle,
			float speed, Direction direction) {
		super(x, y, image, angle, speed, direction);
		damage = 3;
	}

}