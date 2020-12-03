package arbreLexico.v3;

public class NoeudVide extends NoeudAbstrait {
	private static NoeudVide instance = new NoeudVide();

	private NoeudVide() {
		super(null);
	}
	
	public static NoeudVide getInstance() {
		return instance;
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
		NoeudAbstrait n = new Marque(this);
		for (int i = s.length() - 1; i >= 0; i--)
			n = new Noeud(this, n, s.charAt(i));
		return n;
	}

	@Override
	public NoeudAbstrait suppr(String s) throws ModificationImpossibleException {
		throw new ModificationImpossibleException("Suppression impossible : élément absent.");
	}

	@Override
	public String toString(String s) {
		return "";
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
