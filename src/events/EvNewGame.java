package events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gameUI.framework.GameUI;
import main.Game;

public class EvNewGame implements ActionListener{

	public Game m_game;
	public GameUI m_ui;
	
	public EvNewGame(Game game, GameUI ui){
		m_game=game;
		m_ui = ui;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		m_game.init();
		m_ui.setScoreLabel(0);
	}

}
