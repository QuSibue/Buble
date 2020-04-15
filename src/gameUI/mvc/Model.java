package gameUI.mvc;

import gameUI.framework.GameModel;
import main.Game;

public class Model extends GameModel{

	private Game m_game;
	
	public Model() {
		m_game = new Game();
	}
	
	public Game getGame() {
		return m_game;
	}
	
	@Override
	public void step(long now) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub
		
	}

}
