import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Vue extends JPanel implements MouseListener {

	private Plateau plateau;
	JPanel board;
	Image imageCaseVide;
	Image imageCasePleine;
	Image imageCaseActive;
	Image imageCaseMeilleurCoupPleine;
	Image imageCaseMeilleurCoupVide;
	
	Vue(Plateau plateau) {
		this.plateau = plateau;
		this.board = new JPanel();
		
		//on met un fond blanc pour le plateau //white background on the board
		this.addMouseListener(this);
		
		//chargement des trois images : imageCasePleine, imageCaseVide, imageCaseActive //loads the 3 images
		try {
			this.imageCaseVide = ImageIO.read(new File(
			//		"/Users/gabrieldittrick/ECLIPSEWORK/CSWORK/Solitaire_Monte_Carlo/src/imageCaseVide.png"));
					"src/imageCaseVide.png")); // relative path

					
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			this.imageCasePleine = ImageIO.read(new File(
					"src/imageCasePleine.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			this.imageCaseActive = ImageIO.read(new File(
					"src/imageCaseActive.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			this.imageCaseActive = ImageIO.read(new File(
					"src/imageCaseMeilleurCoupPleine.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			this.imageCaseActive = ImageIO.read(new File(
					"src/imageCaseMeilleurCoupVide.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	//methode paintComponent : on affiche l'etat de l'instance plateau
	public void paintComponent(Graphics g) {
		int i1 = coordMeilleurCoup().get(0);
		int j1 = coordMeilleurCoup().get(1);
		int i2 = coordMeilleurCoup().get(2);
		int j2 = coordMeilleurCoup().get(3);
		
		
		
		System.out.println(plateau.getCoup().toString());
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {

				switch (this.plateau.getVal(i,j)) {
				case 0:
					if (i==i2 & j == j2){
						g.drawImage(this.imageCaseMeilleurCoupVide,j*this.getWidth()/7,i*this.getHeight()/7,this.getWidth()/7-5,this.getHeight()/7-5,this);
					}
					else {
						g.drawImage(this.imageCaseVide,j*this.getWidth()/7,i*this.getHeight()/7,this.getWidth()/7-5,this.getHeight()/7-5,this);

					}
					break;
				case 1:
					if (i==i1 & j == j1){
						g.drawImage(this.imageCaseMeilleurCoupPleine,j*this.getWidth()/7,i*this.getHeight()/7,this.getWidth()/7-5,this.getHeight()/7-5,this);
					}
					else {
						g.drawImage(this.imageCasePleine,j*this.getWidth()/7,i*this.getHeight()/7,this.getWidth()/7-5,this.getHeight()/7-5,this);

					}
					break;
					
				case 2:
					g.drawImage(this.imageCaseActive,j*this.getWidth()/7,i*this.getHeight()/7,this.getWidth()/7-5,this.getHeight()/7-5,this);
					break;
				case 3:
					break;

				}
			}
		}
		
	}

	private ArrayList<Integer> coordMeilleurCoup() {
		ArrayList<Integer> coord;
		coord=plateau.getCoupMonteCarloMoy(100);//

		return coord;
	}

	public void mouseClicked(MouseEvent e) {
	}
	
	public void mousePressed(MouseEvent e) {
				int i = (int)(7*e.getY()/this.getHeight());
				int j = (int)(7*e.getX()/this.getWidth());
				
				switch(this.plateau.getVal(i, j)){
				case 0:
					if(this.plateau.getCoordCaseActive() != null){
						if(this.plateau.isCase2Legal(this.plateau.getCoordCaseActive()[0], this.plateau.getCoordCaseActive()[1], i, j)){
							this.plateau.playMove(this.plateau.getCoordCaseActive()[0], this.plateau.getCoordCaseActive()[1], i, j);
						}
					}
					break;
				case 1:
					if(this.plateau.isCase1Legal(i, j) == true){
						this.plateau.setCaseActive(i, j);
					}
					break;
				case 2:
					this.plateau.setCaseActive(i, j);
					break;
				case 3:
					break;
				}
				this.repaint();
			}

	public void mouseReleased(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}
}
