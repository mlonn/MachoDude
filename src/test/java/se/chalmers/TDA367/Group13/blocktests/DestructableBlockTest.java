package se.chalmers.TDA367.Group13.blocktests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import se.chalmers.TDA367.group13.entities.Entity;
import se.chalmers.TDA367.group13.entities.block.Block;
import se.chalmers.TDA367.group13.entities.block.DestructableBlock;

public class DestructableBlockTest {

	static DestructableBlock test;
	
	@BeforeClass
	public static void startUp() throws Exception{
		Display.create();
		test = new DestructableBlock(1, 1, new Image("/res/Sprites/testArm.png"), 2);
	}
	
	@Test
	public void testDestructableBlock() throws Exception {
		
		assertTrue(test instanceof Block && test instanceof Entity);
	}

	@Test
	public void testLoseHealth() {
		test.loseHealth();
	}

	@Test
	public void testIsDestroyed() {
		assertFalse(test.isDestroyed());
	}

	@Test
	public void testIsHurt() {
		assertTrue(test.isHurt());
	}

}
