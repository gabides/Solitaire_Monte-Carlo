import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class Launcher {

	
		

	public static void main(String[] args) throws CloneNotSupportedException {
		Scanner sc = new Scanner(System.in);
		System.out.println("****************************");
		System.out.println("*        SOLITAIRE         *");
		System.out.println("****************************");
		System.out.println("Menu" + "\n"+ "1- play" + "\n" + "2- Monte Carlo - mean" + "\n" + "3- Monte Carlo - minimum" +  "\n" + "4- Monte Carlo - save sequence");
//		System.out.println("Menu" + "\n"+ "1- jouer" + "\n" + "2- Monte Carlo base sur le moyenne" + "\n" + "3- Monte Carlo base sur le minimum" +  "\n" + "4- Monte Carlo avec sauvegarde de la sequence");

		System.out.println("\n");
		int choix = 0;
		do{
//			System.out.println("quel est votre choix ? ");
			System.out.println("what's your choice ? ");
			choix = sc.nextInt();
		}
		while(choix < 1 | choix > 4);
		
		
		//choix menu
		switch(choix){
		
		//jeu normal en solitaire // regular solitaire game with graphical interface
		case 1:
			Plateau plateau = new Plateau();
			Vue vue = new Vue(plateau);
			GameWindow gamewindow = new GameWindow(vue);
			
			
			while (plateau.getCoup().size() > 0) {
				
			}
			
			if(plateau.getNbPions() == 1){
				gamewindow.repaint();
				System.out.println("vous avez gagne !");
			}
			else{
				gamewindow.repaint();
				System.out.println("domage vous avez perdu, il n'y a plus de coupe possible et il reste " + plateau.getNbPions() + " pions");
			}
			break;
			
		case 2 :
			double valeur = 0;
			Plateau monPlateau = new Plateau();
			for(int i=0; i< 100; i++){
				valeur += monPlateau.copie().jouerPartieMonteCarloMoy(1000);
				System.out.println(valeur);
			}
			System.out.println("moyenne MonteCarlo base sur la moyenne : " + valeur / 100);
			break;
			
		case 3:
			double valeur2 = 0;
			Plateau monPlateau2 = new Plateau();
			for(int i=0; i< 100; i++){
				valeur2 += monPlateau2.copie().jouerPartieMonteCarloMin(1000);
			}
			System.out.println("moyenne MonteCarlo base sur le minimum : " + valeur2 / 100);
			break;
			
		case 4 :
			Plateau monPlateau3 = new Plateau();
			double valeur3=0;
			for(int i=0; i< 100; i++){
				valeur3 += 32- monPlateau3.copie().AIMonteCarlo().size();
			}
			
			System.out.println(valeur3/100);
			
		}
	}
}
