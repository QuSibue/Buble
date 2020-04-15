package components;

import java.awt.Graphics;

public class Case {
	public static final int TAILLE_CASE = 30;
	
	
	
	private final int m_x;
	private final int m_y;
	private final Plateau m_plateau;
	
	private Bille m_bille;
	 
	
	public Case(int x, int y,Plateau plateau,Bille bille) {
		m_x = x;
		m_y = y;
		m_plateau = plateau;
		setM_bille(bille);
	}


	public int getX() {
		return this.m_x;
	}
	
	public int getY() {
		return this.m_y;
	}
	
	public Bille getM_bille() {
		return m_bille;
	}


	public void setM_bille(Bille m_bille) {
		this.m_bille = m_bille;
	}
	
	public void paint(Graphics g, int x, int y) {
		g.drawRect(x, y, Case.TAILLE_CASE, Case.TAILLE_CASE);
		if( this.getM_bille() != null) {
			int x_bille = x + (Case.TAILLE_CASE/2 - Bille.TAILLE_BILLE/2);
			int y_bille = y + (Case.TAILLE_CASE/2 - Bille.TAILLE_BILLE/2);
			this.getM_bille().paint(g,x_bille,y_bille);
		}
	}
}
