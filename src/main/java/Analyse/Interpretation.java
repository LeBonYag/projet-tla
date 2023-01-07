package Analyse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Interpretation {

	// permet la lecture de chaîne au clavier
	private static BufferedReader stdinReader = new BufferedReader(new InputStreamReader(System.in));

	// stocke les variables lues au clavier durant l'interprétation
	private HashMap<String, Integer> variables;

	public Interpretation() {
		variables = new HashMap<>();
	}

	/*
	interprete le noeud n
	et appel récursif sur les noeuds enfants de n

	retourne
	  null si le noeud est une instruction (kPrint ou kInput)
	  la valeur de l'expression si le noeud est une expression

	 */
	public Integer interpreter(Noeud n) {

		switch(n.getTypeDeNoeud()) {
			case statement:
				interpreter(n.enfant(0));
				if (n.nombreEnfants()>1) {
					interpreter(n.enfant(1));
				}
				return null;
			case kInput: {
				/* demande la saisie au clavier d'un nombre et stocke sa valeur dans HashMap<String, Integer> variables
				la clé de stockage est un identifiant qui se trouve le fils de ce noeud kInput
				 */
				String name = n.enfant(0).getValeur();
				System.out.println("Veuillez entrer une valeur pour " +name + " :");
				try {
					String s = stdinReader.readLine();
					variables.put(name, Integer.valueOf(s));
				} catch (IOException e) {
					e.printStackTrace(System.out);
				}
				/* les instructions kInput et kPrint ne retourne pas de valeur */
				return null;
			}
			case kPrint:
				/* affiche le resultat de l'évaluation du fils de ce noeud kPrint */
				System.out.println(interpreter(n.enfant(0)));
				/* les instructions kInput et kPrint ne retourne pas de valeur */
				return null;
			case multiply:
				/* retourne le produit entre le 1e fils du noeud et le 2e */
				return interpreter(n.enfant(0)) * interpreter(n.enfant(1));
			case add:
				/* retourne la somme entre le 1e fils du noeud et le 2e */
				return interpreter(n.enfant(0)) + interpreter(n.enfant(1));
			case kPow: {
				/* retourne la puissance avec pour facteur le 1e fils du noeud et pour exposant le 2e fils */
				double d = Math.pow(interpreter(n.enfant(0)), interpreter(n.enfant(1)));
				return Double.valueOf(d).intValue();
			}
			case intVal:
				/* retourne la valeur d'entier litéral */
				return Integer.valueOf(n.getValeur());
			case ident:
				/* retourne la valeur d'une variable (désignée par son identifiant) */
				return variables.get(n.getValeur());
		}
		return null;
	}

}
