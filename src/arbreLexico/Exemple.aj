package arbreLexico;

public aspect Exemple {
	
	declare parents : ArbreLexicographique implements Comparable<ArbreLexicographique>;

	private int ArbreLexicographique.bidon = 4;
	
	public int ArbreLexicographique.getBidon() {
		return bidon;
	}

	public void ArbreLexicographique.setBidon(int i) {
		bidon = i;
	}

	public int ArbreLexicographique.compareTo(ArbreLexicographique a) {
		return 0;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	// TODO Auto-generated method stub
	ArbreLexicographique a = new ArbreLexicographique();
	System.out.println(a.getBidon());
	a.setBidon(7);
	System.out.println(a.getBidon());
	
	}

}
