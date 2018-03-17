public class Case {

	private int value;
	private int i;
	private int j;

	//constructeur de case // construct the cell 
	Case(int value, int i, int j) {
		this.value = value;
		this.i = i;
		this.j = j;
	}

	//acquerir la veleur de la case : 0,1,2 ou 3 // get the cell's value
	public int getValue() {
		return this.value;
	}

	//changer la valeur de la case 0,1,2 ou 3 // chaanges the cell's value
	public void setValue(int value) {
		this.value = value;
	}
	
	public int[] getCoord(){
		int[] resultat = new int[2];
		resultat[0] = this.i;
		resultat[1] = this.j;
		return resultat;
		
	}
	
	public Case copie(){
		Case retour = new Case(this.getValue(), this.getCoord()[0],this.getCoord()[1]);
		retour.i= this.i;
		retour.j = this.j;
		retour.value = this.value;
		return retour;
	}

}
