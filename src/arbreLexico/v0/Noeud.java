package arbreLexico.v0;

public class Noeud extends NoeudAbstrait {
	private char valeur;
	private NoeudAbstrait fils;

	public Noeud(NoeudAbstrait frere, NoeudAbstrait fils, char valeur) {
		super(frere);
		if (fils == null)
			throw new IllegalArgumentException("Fils null interdit");
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
		if (frere == null)
			return false;
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
		if (frere == null)
			return false;
		return frere.prefixe(s);
	}

	@Override
	public int nbMots() {
		return fils.nbMots() + (frere == null ? 0 : frere.nbMots());
	}

	@Override
	public NoeudAbstrait ajout(String s) {
		if (s.isEmpty())
			return new Marque(this);
		char c = s.charAt(0);
		if (c < valeur) {
			NoeudAbstrait n = new Marque(null);
			for (int i = s.length() - 1; i >= 0; i--)
				n = new Noeud(null, n, s.charAt(i));
			n.frere = this;
			return n;
		}
		if (c == valeur) {
			fils = fils.ajout(s.substring(1));
			return this;
		}
		// (c > valeur)
		if (frere == null) {
			NoeudAbstrait n = new Marque(null);
			for (int i = s.length() - 1; i >= 0; i--)
				n = new Noeud(null, n, s.charAt(i));
			frere = n;
			return this;
		}
		frere = frere.ajout(s);
		return this;
	}

	@Override
	public NoeudAbstrait suppr(String s) {
		if (s.isEmpty())
			return this;
		char c = s.charAt(0);
		if (c < valeur)
			return this;
		if (c == valeur) {
			fils = fils.suppr(s.substring(1));
			if (fils == null)
				return frere;
			else
				return this;
		}
		// (c > valeur)
		if (frere == null)
			return this;
		frere = frere.suppr(s);
		return this;
	}

	@Override
	public String toString(String s) {
		return fils.toString(s + valeur) + (frere != null ? frere.toString(s) : "");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
