package se.chalmers.TDA367.group13.controller;

import java.util.LinkedList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.state.StateBasedGame;

import se.chalmers.TDA367.group13.util.Stats;
import se.chalmers.TDA367.group13.util.Util;
import se.chalmers.TDA367.group13.view.MenuView;
import se.chalmers.TDA367.group13.view.MenuItem;
import se.chalmers.TDA367.group13.view.StatsView;

public class StatsState extends AbstractMachoDudeState {

	public static final int ID = 42;
	private Image itemImage;
	private MenuView menu;
	private Point mouse;
	private StatsView view;

	@Override 
	public void enter(GameContainer container, StateBasedGame game){
		view = new StatsView();
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		super.init(container, game);
		initMenu();

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		super.render(container, game, g);
		menu.render(g);
		view.render(g);

	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		super.update(container, game, delta);
		mouse = new Point(input.getMouseX(), input.getMouseY());
		boolean isMousePressed = input.isMousePressed(Input.MOUSE_LEFT_BUTTON);

		for (MenuItem item : menu.getItems()) {

			if (item.contains(mouse)) {
				menu.setSelected(item);
			}

			if (item.contains(mouse) && isMousePressed) {
				if (menu.getSelected().getID() > 0) {
					game.enterState(item.getID());
				} else {
					Stats.getInstance().reset();
				}
			}

			if (input.isKeyPressed(Input.KEY_DOWN)) {
				menu.down();
			}

			if (input.isKeyPressed(Input.KEY_UP)) {
				menu.up();
			}

			if (input.isKeyPressed(Input.KEY_ENTER)
					&& item.equals(menu.getSelected())) {
				if (menu.getSelected().getID() > 0) {
					game.enterState(menu.getSelected().getID());
				} else {
					Stats.getInstance().reset();
				}

			}
		}
	}

	@Override
	public int getID() {
		return ID;
	}

	public void initMenu() {
		try {
			itemImage = new Image("res/GUI/menuItem.png");
			int middleX = gc.getWidth() / 2 - itemImage.getWidth() / 2;

			MenuItem mainButton = new MenuItem(middleX, gc.getHeight() - 300,
					itemImage, "MAIN MENU", GameStateController.getMenuState()
							.getID());
			MenuItem resetButton = new MenuItem(middleX, gc.getHeight() - 200,
					itemImage, "RESET STATS");

			LinkedList<MenuItem> items = new LinkedList<MenuItem>();
			items.add(mainButton);
			items.add(resetButton);
			menu = new MenuView(items);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}
