package arbreLexico.v1;

public class Marque extends NoeudAbstrait {

	public Marque(NoeudAbstrait frere) {
		super(frere);
	}

	@Override
	public boolean contient(String s) {
		// if (s.isEmpty())
		// return true;
		// if (frere == null)
		// return false;
		// return frere.contient(s);

		return s.isEmpty() || (frere != null && frere.contient(s));
	}

	@Override
	public boolean prefixe(String s) {
		return s.isEmpty() || (frere != null && frere.prefixe(s));
	}

	@Override
	public int nbMots() {
		return 1 + (frere == null ? 0 : frere.nbMots());
	}

	@Override
	public NoeudAbstrait ajout(String s) throws ModificationImpossibleException {
		if (s.isEmpty())
			throw new ModificationImpossibleException("Ajout impossible : élément déjà présent");
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
	public NoeudAbstrait suppr(String s)  throws ModificationImpossibleException {
		if (s.isEmpty())
			return frere;
		if (frere == null)
			throw new ModificationImpossibleException("Suppression impossible : élément absent.");
		frere = frere.suppr(s);
		return this;
	}

	@Override
	public String toString(String s) {
		return s + "\n" + (frere != null ? frere.toString(s) : "");
	}

	
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
