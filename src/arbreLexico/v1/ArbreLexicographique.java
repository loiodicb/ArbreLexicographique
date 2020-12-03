package arbreLexico.v1;

public class ArbreLexicographique {
	private NoeudAbstrait entree;

	public ArbreLexicographique() {
		entree = null;
	}

	public boolean contient(String s) {
		if (entree == null)
			return false;
		return entree.contient(s);
	}

	public boolean prefixe(String s) {
		if (entree == null)
			return false;
		return entree.prefixe(s);
	}

	public int nbMots() {
		if (entree == null)
			return 0;
		return entree.nbMots();
	}

	public boolean ajout(String s) {
		if (entree == null) {
			entree = new Marque(null);
			if (!s.isEmpty()) {
				try {
					entree = entree.ajout(s);
					entree = entree.suppr("");
				} catch (ModificationImpossibleException e) {
					// normalement jamais
				}
			}
			return true;
		}
		try {
			entree = entree.ajout(s);
		} catch (ModificationImpossibleException e) {
			return false;
		}
		return true;
	}

	public boolean suppr(String s) {
		if (entree == null)
			return false;
		try {
			entree = entree.suppr(s);
		} catch (ModificationImpossibleException e) {
			return false;
		}
		return true;
	}

	public String toString() {
		if (entree == null)
			return "";
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
