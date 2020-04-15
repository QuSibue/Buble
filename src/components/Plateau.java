package components;

import java.awt.Color;
import java.awt.Graphics;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;




public class Plateau {
	public static final int LIGNES = 11;
	public static final int COLONNES = 10;
	
	private int m_score = 0;
	
	private Case m_cases[][];
	private ArrayList<Case> m_cases_marked = new ArrayList<>();
	//ajouter un tableau de cases marquées
	
	public Plateau() {
		m_cases = new Case[COLONNES][LIGNES];
		this.init();
		
	}
	
	public void init() {
		this.setScore(0);
		this.initCases(); //va créer les cases du plateau
		this.initBilles(); // va créer les billes du plateau
	}
	
	public void initCases() {
		for(int i=0;i < LIGNES;i++) {
			for(int j=0;j< COLONNES;j++) {
				Case case_courante = new Case(j, i, this, null);
				m_cases[j][i]=case_courante;
			}
		}
	}
	
	public void initBilles() {
		for(int i=0;i < LIGNES;i++) {
			for(int j=0;j< COLONNES;j++) {
				Random rand = new Random(); 
				int value = rand.nextInt(3); //entier entre 0 et 2
				
				if(value == 0) {
					Bille bille = new Bille(Couleur.BLEU,this.m_cases[j][i]);
					m_cases[j][i].setM_bille(bille);
				}
				else if (value == 1) {
					Bille bille = new Bille(Couleur.ROUGE,this.m_cases[j][i]);
					m_cases[j][i].setM_bille(bille);
				}
				
				else {
					Bille bille = new Bille(Couleur.VERT,this.m_cases[j][i]);
					m_cases[j][i].setM_bille(bille);
				}
				
			}
		}
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		int x_case;
		int y_case;
		
		for(int i=0;i < LIGNES;i++) {
			for(int j=0;j< COLONNES;j++) {
				x_case = j * Case.TAILLE_CASE; //pour faire la matrice comme il faut, sinon les dessins ce chevauchent
				y_case = i * Case.TAILLE_CASE;
				m_cases[j][i].paint(g,x_case,y_case);
				
			}
		}
	}
	
	public Case[][] getCases() {
		return m_cases;
	}
	
	public Case getCase(int x, int y) {
		return m_cases[x][y];
	}
	
	public ArrayList<Case> get_casesmarked(){
		return m_cases_marked;
	}
	
	public int marquerBilles(Bille bille_courante,Couleur couleur) {
		int nbMarquees = marquerBillesWorker(bille_courante, couleur,0);
		System.out.println(nbMarquees);
		
		
		if(nbMarquees <= 1) {
			String SoundFile = "./DATA/S03.wav";
		    AudioStream audioStream = loadSound(SoundFile); 
		    AudioPlayer.player.start(audioStream);
		    
			bille_courante.setSelected();
			m_cases_marked.clear();//on supprime la seul bille marquée car inutile
		}
		else if (nbMarquees > 10) {
			  // open the sound file as a Java input stream
		    String SoundFile = "./DATA/S06.wav";
		    AudioStream audioStream = loadSound(SoundFile); 
		    // play the audio clip with the audioplayer class
		    AudioPlayer.player.start(audioStream);
		}
		
		else {
			String SoundFile = "./DATA/S02.wav";
		    AudioStream audioStream = loadSound(SoundFile); 
		    AudioPlayer.player.start(audioStream);
		}

		return nbMarquees;
	}
	
	
	public int marquerBillesWorker(Bille bille_courante,Couleur couleur,int nbmarquees) { //méthode recursive qui va marquer les bille de la couleur passée en parametre et qui son adjacentes les unes des autres
		
		int nbmarked = 0;
		int nbmarkedLeft = 0;
		int nbmarkedRight = 0;
		int nbmarkedUp = 0;
		int nbmarkedDown = 0;
		
		if(bille_courante != null && bille_courante.getCouleur() == couleur && !bille_courante.getSelected()) { //on verifie qu'on essaye pas de marque une case vide
			bille_courante.setSelected();
			Case caseBille = bille_courante.getCase();
			this.m_cases_marked.add(caseBille); //on ajoute au tableau de cases marquée la case de la bille courante
			nbmarked = 1; //on note que la case courant est marquée, donc on augmente l'entier associé
			
			if(caseBille.getX() != 0) {
				nbmarkedLeft = marquerBillesWorker(this.getCase(caseBille.getX()-1, caseBille.getY()).getM_bille(), couleur,0); //on marque la bille a gauche de la case courante
			}
			if(caseBille.getX() != COLONNES-1) {
				nbmarkedRight = marquerBillesWorker(this.getCase(caseBille.getX()+1, caseBille.getY()).getM_bille(), couleur,0); //si on est pas tout a droite on marque la case a notre droite
			}
			if(caseBille.getY() != 0) { //si on est pas sur la prmeiere ligne, on essaye de regarder la case au dessus
				nbmarkedUp = marquerBillesWorker(this.getCase(caseBille.getX(), caseBille.getY()-1).getM_bille(), couleur,0);
			}
			if(caseBille.getY() != LIGNES-1) { //si on est pas sur la derniere ligne, on regarde la case en dessous
				nbmarkedDown = marquerBillesWorker(this.getCase(caseBille.getX(), caseBille.getY()+1).getM_bille(), couleur,0);
			}
			
			
		}
		
		return nbmarked + nbmarkedLeft + nbmarkedRight + nbmarkedUp + nbmarkedDown;
		
	}
	
	public void supprimerBilles() {
		for(int i = 0;i<this.m_cases_marked.size();i++) { //on parcourt le tableau de cases marquées
			m_cases_marked.get(i).setM_bille(null); //on supprime la bille de la case courante
		}
		
		m_cases_marked.clear(); // un efois le parcours du tableau de cases marquées, on le vide car on a supprimé toute les billes
		
	}
	
	public void changedSelection() {
		for(int i = 0;i<this.m_cases_marked.size();i++) { //on parcourt le tableau de cases marquées
			m_cases_marked.get(i).getM_bille().setSelected(); // on remet la bille en mode non select
		}
		m_cases_marked.clear();
	}

	public void decalerBilles() {
		// TODO Auto-generated method stub
		
		int nbChange;
		Case case_courante;
		
		do {
			nbChange = 0;
			for(int i=LIGNES-1;i >= 0 ;i--) { //on parcours le plateau du début jusqua la fin
				for(int j=COLONNES-1;j >= 0;j--) {
					case_courante = m_cases[j][i]; //on recupere notre case courante
					if(case_courante.getX() != 0 && case_courante.getM_bille() == null && m_cases[j-1][i].getM_bille() != null) { //si notre case courante n'est pas une case de la premier colonne et qu'elle est vide mais pas sa voisine de gauche
						case_courante.setM_bille(m_cases[j-1][i].getM_bille()); //on affecte a notre case courante la bille de la case voisine
						case_courante.getM_bille().setCase(case_courante); //on modifie les coordonnées de la bille pour notre case
						m_cases[j-1][i].setM_bille(null); //on las suprime de la case voisine
						nbChange +=1; // on indique qu'un changement à eu lieu
					}
				}
			}
		}while(nbChange != 0);
		
	}

	public void descendreBilles() {
		// TODO Auto-generated method stub
		int nbChange;
		Case case_courante;
		
		do {
			nbChange = 0;
			for(int i=LIGNES-1;i >= 0 ;i--) { //on parcours le plateau du début jusqua la fin
				for(int j=COLONNES-1;j >= 0;j--) {
					case_courante = m_cases[j][i]; //on recupere notre case courante
					if(case_courante.getY() != 0 && case_courante.getM_bille() == null && m_cases[j][i-1].getM_bille() != null) { //si notre case courante n'est pas une case de la premier ligne et qu'elle est vide mais pas sa voisine du dessus
						case_courante.setM_bille(m_cases[j][i-1].getM_bille()); //on affecte a notre case courante la bille de la case voisine
						case_courante.getM_bille().setCase(case_courante); //on modifie les coordonnées de la bille pour notre case
						m_cases[j][i-1].setM_bille(null); //on las suprime de la case voisine
						nbChange +=1; // on indique qu'un changement à eu lieu
					}
				}
			}
		}while(nbChange != 0);
		
		String SoundFile = "./DATA/S01.wav";
	    AudioStream audioStream = loadSound(SoundFile); 
	    AudioPlayer.player.start(audioStream);
	}
	
	public AudioStream loadSound(String SoundFile) {
		InputStream in = null;
		try {
			in = new FileInputStream(SoundFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    // create an audiostream from the inputstream
	    AudioStream audioStream = null;
		try {
			audioStream = new AudioStream(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return audioStream;
	}
	
	
	public int getScore() {
		return m_score;
	}

	public void setScore(int i) {
		// TODO Auto-generated method stub
		m_score = i;
	}
}
