package main;
import java.awt.Graphics;

import components.Plateau;

public class Game {

	private Plateau m_plateau;
	
	public Game() {
		m_plateau = new Plateau();
	}
	
	public void paint(Graphics g){
		m_plateau.paint(g);
	}

	public void init() {
		// TODO Auto-generated method stub
			m_plateau.init(); //utiliser pour recommencer une partie, on regenere le plateau
	}
	
	public Plateau getPlateau() {
		return m_plateau;
	}
}
