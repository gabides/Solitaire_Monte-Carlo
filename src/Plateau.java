import java.util.ArrayList;

public class Plateau {

	private Case[][] cases = new Case[7][7];
	//initialisation de la cas active a null
	private Case caseActive = null;
	

	//constructeur
	Plateau() {
		//on initialise toutes les cases du tableau //initialises all the cells on the board
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				if (i == j & i == 3) {
					this.cases[i][j] = new Case(0,i,j);
				} else if ((i > 1 & i < 5) | (j > 1 & j < 5)) {
					this.cases[i][j] = new Case(1,i,j);
				} else {
					this.cases[i][j] = new Case(3,i,j);
				}
			}
		}
	}
	
	Plateau(int[][] tableau){
		for(int i=0; i<7;i++){
			for(int j=0;j<7;j++){
				this.cases[i][j] = new Case(tableau[i][j],i,j);
			}
		}
	}
	
	//acquerir la valeur d'une case // gets the cell's value
	public int getVal(int i, int j) {
		return this.cases[i][j].getValue();
	}
	
	//changer la valeur de la case // changes the cell's value
	public void setVal(int i, int j, int val){
		this.cases[i][j].setValue(val);
	}

	//est ce que la case selectionnee 1 correspond a un debut de mouvement possible // does the chosen cell match at least one possible move
	public boolean isCase1Legal(int i, int j) {
		if (this.getVal(i, j) == 3 | this.getVal(i, j) == 0) {
			return false;
		} else {
			// cases en haut // upper cell
			if (i - 2 < 0) {
				if (((this.getVal(i + 2, j) != 0) | (this.getVal(i + 1, j) != 1))
						& ((this.getVal(i, j - 2) != 0) | (this.getVal(i, j - 1) != 1))
						& ((this.getVal(i, j + 2) != 0) | (this.getVal(i, j + 1) != 1))) {
					return false;
				} else {
					return true;
				}
			}
			// cases en bas // lower cell
			else if (i + 2 > 6) {
				if (((this.getVal(i - 2, j) != 0) | (this.getVal(i - 1, j) != 1))
						& ((this.getVal(i, j - 2) != 0) | (this.getVal(i, j - 1) != 1))
						& ((this.getVal(i, j + 2) != 0) | (this.getVal(i, j + 1) != 1))) {
					return false;
				} else {
					return true;
				}
				// case a gauche // left cell
			} else if (j - 2 < 0) {
				if (((this.getVal(i - 2, j) != 0) | (this.getVal(i - 1, j) != 1))
						& ((this.getVal(i + 2, j) != 0) | (this.getVal(i + 1, j) != 1))
						& ((this.getVal(i, j + 2) != 0) | (this.getVal(i, j + 1) != 1))) {
					return false;
				} else {
					return true;
				}
				// cases a droite // right cell
			} else if (j + 2 > 6) {
				if (((this.getVal(i - 2, j) != 0) | (this.getVal(i - 1, j) != 1))
						& ((this.getVal(i + 2, j) != 0) | (this.getVal(i + 1, j) != 1))
						& ((this.getVal(i, j - 2) != 0) | (this.getVal(i, j - 1) != 1))) {
					return false;
				} else {
					return true;
				}
			}
			// cases vers le centre // center cell
			else {
				if (((this.getVal(i - 2, j) != 0) | (this.getVal(i - 1, j) != 1))
						& ((this.getVal(i + 2, j) != 0) | (this.getVal(i + 1, j) != 1))
						& ((this.getVal(i, j - 2) != 0) | (this.getVal(i, j - 1) != 1))
						& ((this.getVal(i, j + 2) != 0) | (this.getVal(i, j + 1) != 1))) {
					return false;
				} else {
					return true;
				}
			}
		}
	}

	//en fonction de la premiere case est ce que la case 2 selectionnee permet un mouvement de pion // depending on 1st cell, does 2nd cell match any legal move
	public boolean isCase2Legal(int i1, int j1, int i2, int j2) {
		// case2 vide
		if (this.getVal(i2, j2) == 0) {
			// meme ligne et distance de 2 // same raw, distance 2
			if(i1 == i2 & Math.abs(j2 - j1) == 2){
				if(this.getVal(i2, (j1+j2)/2) == 1){
					return true;
				}
				else{
					return false;
				}
			}
			// meme colonne et distance de 2 // same column, distance 2
			else if(j1 == j2 & Math.abs(i2 - i1) == 2){
				if(this.getVal((i1+i2)/2, j2) == 1){
					return true;
				}
				else{
					return false;
				}
			}
			else{
				return false;
			}	
		} 
		else {
			return false;
		}
	}

	//afficher en console le tableau (plus utile) // shows the board in the console
	public String toString() {
		String resultat = "";
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				resultat += " " + this.getVal(i, j) + " ";
			}
			resultat += "\n";
		}
		return resultat;
	}

	//activer une case // activates a cell
	public void setCaseActive(int i, int j) {
		if(this.caseActive != null){
			this.cases[this.getCoordCaseActive()[0]][this.getCoordCaseActive()[1]].setValue(1);
			this.caseActive = this.cases[i][j];
			this.cases[i][j].setValue(2);
		}
		else{
			this.caseActive = this.cases[i][j];
			this.cases[i][j].setValue(2);
		}
		
	}
	
	//acquerir les coordonnees de la case active // gets the active cell's coodinates
	public int[] getCoordCaseActive(){
		int[] retour = new int[2];
		retour[0] = this.caseActive.getCoord()[0];
		retour[1] = this.caseActive.getCoord()[1];
		return retour;
		}	
	
	//faire bouger un pion // moves a pawn
	public void playMove(int i1, int j1, int i2, int j2) {
		if(this.isCase2Legal(i1, j1, i2, j2)){
			this.caseActive = null;
			this.cases[i1][j1].setValue(0);
			this.cases[i2][j2].setValue(1);
			this.cases[(i1 + i2) / 2][(j1 + j2) / 2].setValue(0);
		}
		
	}
		
	//jouer un coup //play a move
	public void jouerCoup(ArrayList<Integer> coup)
	{
		this.playMove(coup.get(0), coup.get(1), coup.get(2), coup.get(3));
	}
	
	
	// acquerir les coups possible dans cette configuration de plateau // get all possible moves in that position
	public ArrayList<ArrayList<Integer>> getCoup(){
		ArrayList<ArrayList<Integer>> liste = new ArrayList<ArrayList<Integer>>();
		
		//on regarde toutes les cases // watch all cells
		for(int i1=0; i1<7;i1++){
			for(int j1 = 0; j1 < 7; j1++){
				// si case legale // if legal 
				if(this.isCase1Legal(i1, j1)){
					//on regarde toutes les cases pour voir si une combinaison est possible avec la case precedente // chech each cell if possible combination with last cell
					for(int i2=0;i2<7;i2++){
						for(int j2=0;j2<7;j2++){
							//si oui dans ce cas on ajoute les deux cases car c'est une combinaison du plateau // if true then add both cells (one legal move = combination of 2 cells)
							if(this.isCase2Legal(i1, j1, i2, j2)){
								ArrayList<Integer> l = new ArrayList<Integer>();
								l.add(i1);
								l.add(j1);
								l.add(i2);
								l.add(j2);
								liste.add(l);
							}
						}
					}
				}
			}
		}
		return liste;
	}
	

	
	//jouer une partie aléatoire entière // plays a random full game 
	public ArrayList<ArrayList<Integer>> jouerPartieAleatoire(){
		ArrayList<ArrayList<Integer>> sequence = new ArrayList<ArrayList<Integer>>();
		int index;
		while(this.getCoup().size() > 0){                                              
			index = (int)(Math.random() * (this.getCoup().size()));
			sequence.add(this.getCoup().get(index));
			this.jouerCoup(this.getCoup().get(index));
		}
		return sequence;
	}
	
	//jouer une partie de montecarlo // plays a monte carlo game
	public int jouerPartieMonteCarloMoy(int nbpartiealeatoire){
		while(this.getCoup().size() > 0){
			this.jouerCoup(this.getCoupMonteCarloMoy(nbpartiealeatoire));
		}
		return this.getNbPions();
	}
	
	//utilise montecarlo pour obtenir le meilleur coup // use monte carlo to get the best move
	public ArrayList<Integer> getCoupMonteCarloMoy(int nbpartiealeatoire){
		int minValeur = Integer.MAX_VALUE ;
		int valeur;
		ArrayList<ArrayList<Integer>> coups = this.getCoup();
		int index=0;
		for(int i=0; i< coups.size(); i++){
			valeur = 0;
			Plateau monPlateau = this.copie();
			monPlateau.jouerCoup(coups.get(i));
			for(int j=0; j< nbpartiealeatoire;j++){
				valeur += 32-monPlateau.copie().jouerPartieAleatoire().size();
			}
			if(valeur < minValeur){
				minValeur = valeur;
				index = i;
			}
		}
		return coups.get(index);
	}
	
	public ArrayList<Integer> getCoupMonteCarloMin(int nbpartiealeatoire){
		int minValeur = Integer.MAX_VALUE ;
		int valeur=Integer.MAX_VALUE;
		ArrayList<ArrayList<Integer>> coups = this.getCoup();
		int index=0;
		for(int i=0; i< coups.size(); i++){
			Plateau monPlateau = this.copie();
			monPlateau.jouerCoup(coups.get(i));
			for(int j=0; j< nbpartiealeatoire;j++){
				valeur = 32-monPlateau.copie().jouerPartieAleatoire().size();
				if(valeur < minValeur){
					minValeur = valeur;
					index = i;
				}
			}
		}
		return coups.get(index);
	}
	
	public int jouerPartieMonteCarloMin(int nbpartiealeatoire){
		while(this.getCoup().size() > 0){
			this.jouerCoup(this.getCoupMonteCarloMin(nbpartiealeatoire));
		}
		return this.getNbPions();
	}
	

	//acquerir le nombre de pions // gets the number of pawns
	public int getNbPions(){
		int retour = 0;
		for(int i=0;i<7;i++){
			for(int j =0; j<7;j++){
				if(this.cases[i][j].getValue() == 1 | this.cases[i][j].getValue() == 2){
					retour = retour + 1;
				}
			}
		}
		return retour;
	}
	
	//operateur de clonage // board cloning operator
	public Plateau copie() {
		Plateau monPlateau = new Plateau();
		for(int i=0; i<7; i++){
			for(int j=0; j<7; j++){
				monPlateau.cases[i][j] = this.cases[i][j].copie();
			}
		}
		monPlateau.caseActive = null;
		// on renvoie le clone
		return monPlateau;
	}
	
	/*
	public ArrayList<ArrayList<Integer>> getBestSequence(){
		ArrayList<ArrayList<Integer>> sequence = new ArrayList<ArrayList<Integer>>();
		
	}
	*/
	
	
	
	/* fonctions moins utiles voir nom utilisés */ // other methods (unused or less efficient)
	
	//filtrer les coups qui ne rapprochent pas vers le centre // filter moves that don't get closer to the middle of the board
	public ArrayList<ArrayList<Integer>> getCoupRapprocheCentre(){
		ArrayList<ArrayList<Integer>> coups = this.getCoup();
		for(int i=0; i<coups.size();i++){
			if (coups.get(i).get(0) == coups.get(i).get(2) & 
					((coups.get(i).get(1) < coups.get(i).get(3) & (coups.get(i).get(1)+coups.get(i).get(3))/2 <=3) |
							(coups.get(i).get(1) > coups.get(i).get(3) & (coups.get(i).get(1)+coups.get(i).get(3))/2 >= 3))){
				
			}
			else if(coups.get(i).get(1) == coups.get(i).get(3) & 
					((coups.get(i).get(0) < coups.get(i).get(2) & (coups.get(i).get(0)+coups.get(i).get(2))/2 <=3) |
							(coups.get(i).get(0) > coups.get(i).get(2) & (coups.get(i).get(0)+coups.get(i).get(2))/2 >= 3))){
				
			}
			else{
				coups.remove(i);
			}
		}
		return coups;
	}
	
	//test de symetrie par rapport à la diagonale // diagonal symmetry test
	public boolean arePlateauSymetric(Plateau plateau2){
		for(int i=0; i<7; i++){
			for(int j =0; j<7; j++){
				if(this.getVal(i, j) != this.getVal(j, i)){
					return false;
				}
			}
		}
		return true;
	}
	
	// test symetrie par rapport à l'antidiagonale // antidiagonal symmetry test
	public boolean arePlateauAntiSymetric(Plateau plateau2){
		for(int i=0; i<7; i++){
			for(int j =0; j<7; j++){
				if(this.getVal(i, j) != this.getVal(6-j, 6-i)){
					return false;
				}
			}
		}
		return true;
	}
	
	//test symetrie par rapport à y // vertical symmetry test
	public boolean arePlateauSymetricY(Plateau plateau2){
		for(int i=0; i<7; i++){
			for(int j =0; j<7; j++){
				if(this.getVal(i, j) != this.getVal(i, 6-j)){
					return false;
				}
			}
		}
		return true;
	}
	
	//test symetrie par rapport à X // horizontal symmetry test
	public boolean arePlateauSymetricX(Plateau plateau2){
		for(int i=0; i<7; i++){
			for(int j =0; j<7; j++){
				if(this.getVal(i, j) != this.getVal(6-i, j)){
					return false;
				}
			}
		}
		return true;
	}
	
	//test symetrie par rappoet 
	public boolean existeSymetrie(Plateau plateau2){
		if(this.arePlateauSymetric(plateau2) == true |this.arePlateauAntiSymetric(plateau2) == true | this.arePlateauSymetricY(plateau2) == true | this.arePlateauSymetricX(plateau2) == true){
			return true;
		}
		else {
			return false;
		}
	}
	
	
	
	
	
	public ArrayList<ArrayList<Integer>> AIMonteCarlo(){
		
		//initialisation des variables //variables initi&lization
		ArrayList<ArrayList<Integer>> meilleurSequenceCoup = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> sequence = new ArrayList<ArrayList<Integer>>();
		//On itnitialise la meilleur sequence à des coups valant 0 // initialize sequence with moves (value = 0)
		for(int i=0; i<31;i++){
			meilleurSequenceCoup.add(null);
		}
		
		int meilleurScore = Integer.MAX_VALUE;
		int score = Integer.MAX_VALUE;
		int niveau= 0;
		while(this.getCoup().size() > 0){

			ArrayList<ArrayList<Integer>> coups = this.getCoup();
			
			//on boucle sur les coups possible // loop on possible moves
			for(int i=0; i< coups.size();i++){
				Plateau monPlateau2 = this.copie();
				monPlateau2.jouerCoup(coups.get(i));
				
				//on réalise plusieurs fois le même coup en enregistrant la séquence //make the same move several times and memorize the sequence 
				for(int j=0; j<100; j++){
					sequence = monPlateau2.copie().jouerPartieAleatoire();
					score = 32-sequence.size();
					
					// on test si le score est meilleur //test if score is higher
					if(score < meilleurScore){
						meilleurScore = score;
						
						//on actualise la séquence avec le bon coup à jouer // refresh sequence with the move to play
						meilleurSequenceCoup.set(niveau, coups.get(i));
							
						// on actualise les coups à partir de niveau+1 // refresh moves starting level+1
						for(int k=0;k<sequence.size();k++){
								meilleurSequenceCoup.set(niveau+k+1, sequence.get(k));
							}
						}
					sequence.clear();
					}
				
				}
			
			this.jouerCoup(meilleurSequenceCoup.get(niveau));
			coups.clear();
			niveau ++;
		}
		for(int i=0; i < meilleurSequenceCoup.size(); i++){
			if(meilleurSequenceCoup.get(i) == null){
				meilleurSequenceCoup.remove(i);
				i--;
			}
		}
		System.out.println(meilleurSequenceCoup);
		return meilleurSequenceCoup;
	}

	
	
	
}
