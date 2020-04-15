package gameUI.mvc;

import java.awt.Point;
import java.awt.event.MouseEvent;
import components.Bille;
import components.Case;
import components.Plateau;
import gameUI.framework.GameController;
import main.Game;


public class Controller extends GameController{

	private Model model;
	private int nbMarked=0;
	
	public Controller(Model model) {
		this.model=model;
	}
	
	@Override
	public void notifyVisible() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void step(long now) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		Game game = model.getGame();
		Plateau plat = game.getPlateau();
		
		int ligne_cliquee = (int) (e.getY()/Case.TAILLE_CASE);
		int colonne_cliquee = (int) (e.getX()/Case.TAILLE_CASE);
		
		
		Case case_courante = plat.getCase(colonne_cliquee, ligne_cliquee);
		
		if(case_courante.getM_bille() != null && !case_courante.getM_bille().getSelected()) {
			nbMarked = 0;
			if(plat.get_casesmarked().size()!=0 && !plat.get_casesmarked().contains(case_courante)) { //si on a deja une selection de billes et 
																									  //que celle qu'on selectionne n'est pas dans la 
																									  //selection c'est qu'on est dans un autre groupe de billes
				plat.changedSelection();
			}
			nbMarked = plat.marquerBilles(case_courante.getM_bille(),case_courante.getM_bille().getCouleur());//ajouter l'appel à la méthode recursive, qui renvoi le nombre de marquées pour augmenter le score
		}
		else if (case_courante.getM_bille() != null && plat.get_casesmarked().size()!=0 && case_courante.getM_bille().getSelected() ) {
			plat.supprimerBilles();
			plat.decalerBilles();//appeler la méthode qui décale et fait déscendre les billes restantes
			plat.descendreBilles();
			plat.setScore(plat.getScore()+10*nbMarked);
			nbMarked = 0;
			
			this.getGameUI().setScoreLabel(plat.getScore()); //on met a jour laffichage du score
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
