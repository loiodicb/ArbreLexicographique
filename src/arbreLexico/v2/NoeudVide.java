package arbreLexico.v2;

public class NoeudVide extends NoeudAbstrait {

	public NoeudVide() {
		super(null);
	}

	@Override
	public boolean contient(String s) {
		return false;
	}

	@Override
	public boolean prefixe(String s) {
		return false;
	}

	@Override
	public int nbMots() {
		return 0;
	}

	@Override
	public NoeudAbstrait ajout(String s) throws ModificationImpossibleException {
		NoeudAbstrait n = new Marque(new NoeudVide());
		for (int i = s.length() - 1; i >= 0; i--)
			n = new Noeud(new NoeudVide(), n, s.charAt(i));
		return n;
	}

	@Override
	public NoeudAbstrait suppr(String s) throws ModificationImpossibleException {
		throw new ModificationImpossibleException("Suppression impossible : �l�ment absent.");
	}

	@Override
	public String toString(String s) {
		return "";
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
