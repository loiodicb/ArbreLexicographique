package arbreLexico;

public class Noeud extends NoeudAbstrait {
	private char valeur;
	private NoeudAbstrait fils;

	public Noeud(NoeudAbstrait frere, NoeudAbstrait fils, char valeur) {
		super(frere);
		if (fils == null || fils instanceof NoeudVide)
			throw new IllegalArgumentException("Fils null ou vide interdit");
		this.fils = fils;
		this.valeur = valeur;
	}

	@Override
	public boolean contient(String s) {
		if (s.isEmpty())
			return false;
		char c = s.charAt(0);
		if (c < valeur)
			return false;
		if (c == valeur)
			return fils.contient(s.substring(1));
		// (c > valeur)
		return frere.contient(s);
	}

	@Override
	public boolean prefixe(String s) {
		if (s.isEmpty())
			return true;
		char c = s.charAt(0);
		if (c < valeur)
			return false;
		if (c == valeur)
			return fils.prefixe(s.substring(1));
		// (c > valeur)
		return frere.prefixe(s);
	}

	@Override
	public int nbMots() {
		return fils.nbMots() + frere.nbMots();
	}

	@Override
	public NoeudAbstrait ajout(String s) throws ModificationImpossibleException {
		if (s.isEmpty())
			return new Marque(this);
		char c = s.charAt(0);
		if (c < valeur) {
			NoeudAbstrait n = NoeudVide.getInstance().ajout(s);
			n.frere = this;
			return n;
		}
		if (c == valeur) {
			fils = fils.ajout(s.substring(1));
			return this;
		}
		// (c > valeur)
		frere = frere.ajout(s);
		return this;
	}

	@Override
	public NoeudAbstrait suppr(String s) throws ModificationImpossibleException {
		if (s.isEmpty())
			throw new ModificationImpossibleException("Suppression impossible : �l�ment absent.");		
		char c = s.charAt(0);
		if (c < valeur)
			throw new ModificationImpossibleException("Suppression impossible : �l�ment absent.");		
		if (c == valeur) {
			fils = fils.suppr(s.substring(1));
			if (fils instanceof NoeudVide)
				return frere;
			else
				return this;
		}
		// (c > valeur)
		frere = frere.suppr(s);
		return this;
	}

	@Override
	public String toString(String s) {
		return fils.toString(s + valeur) + frere.toString(s);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
