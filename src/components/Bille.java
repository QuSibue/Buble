package components;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bille {
	private final Couleur m_couleur;
	public static final int TAILLE_BILLE = 21;
	private BufferedImage[] m_sprites; //Normalement, chaque bille aura 2 formes, 1 ronde et l'autre rectangulaire quand selectionnée
	private Case m_case;
	private boolean m_selected;
	
	public Bille(Couleur couleur, Case caseB) {
		m_couleur = couleur;
		m_case = caseB;
		m_selected = false;
		m_sprites = new BufferedImage[2];
		
		if(m_couleur == Couleur.BLEU) { //mettre les bons sprites
			try {
				m_sprites[0] = ImageIO.read(new File("./DATA/blue_ball21.png"));
				m_sprites[1] = ImageIO.read(new File("./DATA/blue_square21.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (m_couleur == Couleur.ROUGE) {
			try {
				m_sprites[0] = ImageIO.read(new File("./DATA/red_ball21.png"));
				m_sprites[1] = ImageIO.read(new File("./DATA/red_square21.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			try {
				m_sprites[0] = ImageIO.read(new File("./DATA/green_ball21.png"));
				m_sprites[1] = ImageIO.read(new File("./DATA/green_square21.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void paint(Graphics g, int x, int y) {
		BufferedImage imageCourante;
		if(!m_selected) {
			imageCourante = m_sprites[0];
		}
		else {
			imageCourante = m_sprites[1];
		}
		g.drawImage(imageCourante, x, y, null);
	}
	
	public boolean getSelected() {
		return m_selected;
	}
	public void setSelected() {
		m_selected = !m_selected;
	}
	
	public Couleur getCouleur() {
		return this.m_couleur;
	}
	
	public Case getCase() {
		return this.m_case;
	}
	
	public void setCase(Case c) {
		this.m_case = c;
	}
}
