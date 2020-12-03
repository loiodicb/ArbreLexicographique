package arbreLexico.v2;

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

		return s.isEmpty() || frere.contient(s);
	}

	@Override
	public boolean prefixe(String s) {
		return s.isEmpty() || frere.prefixe(s);
	}

	@Override
	public int nbMots() {
		return 1 + frere.nbMots();
	}

	@Override
	public NoeudAbstrait ajout(String s) throws ModificationImpossibleException {
		if (s.isEmpty())
			throw new ModificationImpossibleException("Ajout impossible : �l�ment d�j� pr�sent");
		frere = frere.ajout(s);
		return this;
	}

	@Override
	public NoeudAbstrait suppr(String s)  throws ModificationImpossibleException {
		if (s.isEmpty())
			return frere;
		frere = frere.suppr(s);
		return this;
	}

	@Override
	public String toString(String s) {
		return s + "\n" + frere.toString(s);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
