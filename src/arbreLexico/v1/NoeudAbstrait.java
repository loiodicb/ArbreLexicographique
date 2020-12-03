package arbreLexico.v1;

public abstract class NoeudAbstrait {

	protected NoeudAbstrait frere;

	public NoeudAbstrait(NoeudAbstrait frere) {
		this.frere = frere;
	}

	public abstract boolean contient(String s);

	public abstract boolean prefixe(String s);

	public abstract int nbMots();

	public abstract NoeudAbstrait ajout(String s) throws ModificationImpossibleException;

	public abstract NoeudAbstrait suppr(String s) throws ModificationImpossibleException;

	public abstract String toString(String s);
	
	public static void main(String[] args) {
//		NoeudAbstrait arbre = new Marque(null);
//		arbre = arbre.ajout("covid");
//		System.out.println(">>>" + arbre.toString("") + "<<<");
//		arbre = arbre.suppr("");
//		arbre = arbre.ajout("masque");
//		arbre = arbre.ajout("max");
//		arbre = arbre.ajout("coureur");
//		arbre = arbre.ajout("cire");
//		arbre = arbre.ajout("meilleur");
//		arbre = arbre.ajout("meilleur");
//		arbre = arbre.ajout("meilleur");
//		arbre = arbre.ajout("meilleur");
//		System.out.println(">>>" + arbre.toString("") + "<<<");
//		System.out.println(arbre.nbMots());
//		System.out.println(arbre.contient("miel"));
//		System.out.println(arbre.contient("max"));
//		System.out.println(arbre.prefixe("mot"));
//		System.out.println(arbre.prefixe("max"));
//		System.out.println(arbre.prefixe("ma"));
//		arbre = arbre.suppr("meilleur");
//		arbre = arbre.suppr("meilleur");
//		arbre = arbre.suppr("pire");
//		System.out.println(">>>" + arbre.toString("") + "<<<");
	}

}
