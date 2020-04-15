package gameUI.mvc;

import java.awt.Color;
import java.awt.Graphics;


import gameUI.framework.GameView;
import main.Game;

public class View extends GameView{
	private Model model;
	
	public View(Model model) {
		this.model=model;
	}
	@Override
	protected void _paint(Graphics g) {
		// TODO Auto-generated method stub
		
		int frame_width = model.getGameUI().getFrame().getWidth();
		int frame_height = model.getGameUI().getFrame().getHeight();
		
		
		//g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, frame_width, frame_height);
		Game game = model.getGame();
		game.paint(g);
	}

}
