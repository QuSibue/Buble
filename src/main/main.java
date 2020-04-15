package main;
import java.awt.Dimension;

import gameUI.framework.GameUI;
import gameUI.mvc.Controller;
import gameUI.mvc.Model;
import gameUI.mvc.View;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Model model = new Model();
		View view = new View(model);
		Controller controller = new Controller(model);
		GameUI game = new GameUI(model, view, controller, new Dimension(301,360)); //On creer le jeu global qui rassemble les 3 composantes MVC plus la fenetre
	}

}
