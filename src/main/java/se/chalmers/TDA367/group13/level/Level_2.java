package se.chalmers.TDA367.group13.level;

import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import se.chalmers.TDA367.group13.util.Camera;

public class Level_2 extends Level {

	public Level_2()
			throws SlickException {
		super(new Camera(), new TiledMap("res/Maps/test_map.tmx"), new Image("res/Backgrounds/Jungle_Test.gif"), new Music("res/Music/RoccoW_-_Something_Something_Knight.ogg"));
		level_number = 2;
	}

}