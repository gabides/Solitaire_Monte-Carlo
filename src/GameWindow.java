import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

public class GameWindow extends JFrame{
	
	Vue vue = new Vue(new Plateau());
	
	//constructeur de la fenetre // window constructor
	GameWindow(Vue vue) {
		this.vue = vue;
		this.setTitle("Solitaire");
		this.setPreferredSize(new Dimension(700, 700));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().add(this.vue);
		this.vue.setPreferredSize(new Dimension(this.getWidth(),this.getHeight()));
		this.pack();
		this.setVisible(true);
	}
	
	//paint component, quand on change quelque chose dans la fenetre (taille) on repaint le plateau
	// when something is changed within the window (size), repaint the board
	
	public void paintComponent(Graphics g){
		this.vue.repaint();
	}
	
	
}
