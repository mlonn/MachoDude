package se.chalmers.TDA367.group13.entities;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class TestWeapon extends Weapon {
	
	TestWeapon(float x, float y) throws SlickException
	{
		super(x, y, new Image("res/Sprites/testArm.png"), "TestWeapon", 1);
	}
	
	public void fireWeapon(){
		System.out.println("Shots fired!");
	}

}