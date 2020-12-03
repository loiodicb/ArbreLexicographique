package arbreLexico.v3;

public class ArbreLexicographique {
	private NoeudAbstrait entree;

	public ArbreLexicographique() {
		entree = NoeudVide.getInstance();
	}

	public boolean contient(String s) {
		return entree.contient(s);
	}

	public boolean prefixe(String s) {
		return entree.prefixe(s);
	}

	public int nbMots() {
		return entree.nbMots();
	}

	public boolean ajout(String s) {
		try {
			entree = entree.ajout(s);
		} catch (ModificationImpossibleException e) {
			return false;
		}
		return true;
	}

	public boolean suppr(String s) {
		try {
			entree = entree.suppr(s);
		} catch (ModificationImpossibleException e) {
			return false;
		}
		return true;
	}

	public String toString() {
		return entree.toString("");
	}

	public static void main(String[] args) {
		ArbreLexicographique arbre = new ArbreLexicographique();
		arbre.ajout("covid");
		System.out.println(">>>" + arbre.toString() + "<<<");
		arbre.ajout("masque");
		arbre.ajout("max");
		arbre.ajout("coureur");
		System.out.println(arbre.ajout("cire"));
		System.out.println(arbre.ajout("meilleur"));
		System.out.println(arbre.ajout("meilleur"));
		System.out.println(arbre.ajout("meilleur"));
		arbre.ajout("meilleur");
		System.out.println(">>>" + arbre.toString() + "<<<");
		System.out.println(arbre.nbMots());
		System.out.println(arbre.contient("miel"));
		System.out.println(arbre.contient("max"));
		System.out.println(arbre.prefixe("mot"));
		System.out.println(arbre.prefixe("max"));
		System.out.println(arbre.prefixe("ma"));
		System.out.println(arbre.suppr("meilleur"));
		System.out.println(arbre.suppr("meilleur"));
		System.out.println(arbre.suppr("pire"));
		System.out.println(">>>" + arbre.toString() + "<<<");

	}

}
