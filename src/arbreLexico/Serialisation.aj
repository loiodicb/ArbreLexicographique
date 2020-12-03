package arbreLexico;

import java.io.*;
import java.util.Scanner;

public aspect Serialisation {
	
	declare parents : ArbreLexicographique implements Serializable;

	public void ArbreLexicographique.sauve(String nomFichier) {
		Writer w;
		try {
			w = new BufferedWriter(new FileWriter(nomFichier));
			w.write(toString());
			w.flush();
			w.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void ArbreLexicographique.charge(String nomFichier){
	      try {
	      // Ouverture d'un flux en lecture depuis le fichier de nom fichier 
	       BufferedReader in = new BufferedReader(new FileReader(nomFichier));
	       Scanner s = new Scanner(nomFichier);
	       while(s.hasNextLine()){
	    	   String mots = s.nextLine();
	    	   this.ajout(mots);
	       }
	       in.close();
	      } catch (IOException e) {
	    	  e.printStackTrace();
	      }
	       
	}
	public static void main(String[] args) {
	// TODO Auto-generated method stub
	
	}
}
