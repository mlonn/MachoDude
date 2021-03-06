package se.chalmers.TDA367.group13.model.entities.weapon;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Vector2f;

import se.chalmers.TDA367.group13.model.entities.projectile.Enemy1Projectile;
import se.chalmers.TDA367.group13.model.entities.projectile.Projectile;
import se.chalmers.TDA367.group13.util.Direction;

public class Enemy_1_Weapon extends Weapon {


	public Enemy_1_Weapon(float x, float y) throws SlickException {
		super(x, y, new Image("res/Sprites/Enemies/Enemy_1/Enemy_1-Arm.png"), new Image("res/Sprites/Enemies/Enemy_1/Enemy_1-Arm.png").getFlippedCopy(true, false), new Image("res/Sprites/Enemies/Enemy_1/Enemy_1-Projectile.png"),"Enemy_1_Weapon", 1);
		shoulder = new Vector2f(11, 11);
		nuzzle = new Vector2f(17, 21);
		distanceToNuzzle = new Line(nuzzle, shoulder);
		nuzzleAngle = Math.atan2(distanceToNuzzle.getX1() - distanceToNuzzle.getX2(), distanceToNuzzle.getY1() - distanceToNuzzle.getY2());
		cooldown = 750;
		speed = 3;
		firingSound = new Sound("/res/Sound/Enemy_1/Shoot.wav");
	}
	
	@Override
	public Projectile fireWeapon(Direction direction) {
		if (direction == Direction.RIGHT) {
				firingSound.play();
				time = System.currentTimeMillis();

				return new Enemy1Projectile(getProjectileX(direction),getProjectileY(direction), getAngle(), direction);
		} else {
				firingSound.play();
				time = System.currentTimeMillis();
				return new Enemy1Projectile(getProjectileX(direction),getProjectileY(direction), getAngle(), direction);
		}
	}
	
}
