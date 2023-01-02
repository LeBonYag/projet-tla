package Analyse;

import java.util.List;

public class AnalyseSyntaxique {


    private int pos;
    private List<Token> tokens;
    private int niveauIndentation;

    /*
    effectue l'analyse syntaxique à partir de la liste de tokens
    et retourne le noeud racine de l'arbre syntaxique abstrait
     */
    public String analyse(List<Token> tokens) throws Exception {
        this.pos = 0;
        this.tokens = tokens;
        this.niveauIndentation = 0;
        String res = V();
        if (pos != tokens.size()) {
            System.out.println("L'analyse syntaxique s'est terminé avant l'examen de tous les tokens");
            throw new IncompleteParsingException();
        }
        return res;
    }

	/*

	Traite la dérivation du symbole non-terminal V

	V -> T | M | S | C |  P | F | T | H

	 */

    private String V() throws UnexpectedTokenException {

        if (getTypeDeToken() == TypedeToken.m ) {

            // production S ->
            Token t = lireToken();
            printToken("m");
            niveauIndentation++;
            String m = M();
            niveauIndentation--;
            return V();
        }
        throw new UnexpectedTokenException("lettre d'attributs attendu");

          /*  if (getTypeDeToken() == TypedeToken.c ) {
                Token t = lireToken();
                printToken("c");
                niveauIndentation++;
                String c = C();

           }  throw new UnexpectedTokenException("lettre d'attributs attendu"); */

        if (getTypeDeToken() == TypedeToken.p) {
            Token t = lireToken();
            printToken("p");
            niveauIndentation++;
            String p = P();

        }  throw new UnexpectedTokenException("lettre d'attributs attendu");

        if (getTypeDeToken() == TypedeToken.f ) {
            Token t = lireToken();
            printToken("f");
            niveauIndentation++;
            String f = F();

        }  throw new UnexpectedTokenException("lettre d'attributs attendu");

        if (getTypeDeToken() == TypedeToken.t ) {
            Token t = lireToken();
            printToken("t");
            niveauIndentation++;
            String trap = T();

        }  throw new UnexpectedTokenException("lettre d'attributs attendu");

        if (getTypeDeToken() == TypedeToken.h ) {
            Token t = lireToken();
            printToken("h");
            niveauIndentation++;
            String h =  H();

        }  throw new UnexpectedTokenException("lettre d'attributs attendu");
    }

	/*

	Traite la dérivation du symbole non-terminal S'

	S' -> ) V | epsilon

	 */

    private String M(String ) throws UnexpectedTokenException {

        if (getTypeDeToken() == TypedeToken.ParG ) {

            // production S' -> + S

            Token t = lireToken();
            printToken("(");
            niveauIndentation++;
            String s = S();

        }

        if (getTypeDeToken() == TypedeToken.ParD ||
                finAtteinte()) {

            // production S' -> epsilon

            return null ;
        }

        throw new UnexpectedTokenException("+ ou ) attendu");
    }

	/*

	Traite la dérivation du symbole non-terminal A

	A -> ( S ) A' | intVal A'

	 */

    private String S(String s) throws UnexpectedTokenException {

        if (getTypeDeToken() == TypedeToken.intval) {

            // production A -> ( S ) A'

            Token t = lireToken() ;
            niveauIndentation++;
            String s = C();
            niveauIndentation--;

            if (getTypeDeToken() == TypedeToken.rightPar) {
                lireToken();
                return A_prime(s);
            }
            throw new UnexpectedTokenException(") attendu");
        }

        if (getTypeDeToken() == TypedeToken.intVal) {

            // production A -> intVal A'

            Token t = lireToken();
            niveauIndentation++;
            printToken(t.getValeur());
            niveauIndentation--;
            Integer i = Integer.valueOf(t.getValeur());
            return A_prime(i);
        }

        throw new UnexpectedTokenException("intVal ou ( attendu");
    }

	/*

	Traite la dérivation du symbole non-terminal A'

	A' -> * A | epsilon

	 */

    private String C() throws UnexpectedTokenException {
        if (getTypeDeToken() == TypedeToken.virgule) {

            // production A' -> * A

            Token t = lireToken();
            printToken(",");
            niveauIndentation++;
            Integer a = A();
            niveauIndentation--;
            return i * a;
        }

        if (getTypeDeToken() == TypedeToken.add ||
                getTypeDeToken() == TypedeToken.rightPar ||
                finAtteinte()) {

            // production A' -> epsilon

            return i;
        }
        throw new UnexpectedTokenException("*, +, ) ou fin d'entrée attendu");

    }
    private String P() throws UnexpectedTokenException {return null ;}
    private String D() throws UnexpectedTokenException {return null ;}
    private String T() throws UnexpectedTokenException {return null ;}
    private String H() throws UnexpectedTokenException {return null ;}
    private String F() throws UnexpectedTokenException {return null ;}


	/*

	méthodes utilitaires

	 */

    private boolean finAtteinte() {
        return pos >= tokens.size();
    }

    /*
     * Retourne la classe du prochain token à lire
     * SANS AVANCER au token suivant
     */
    private TypedeToken getTypeDeToken() {
        if (pos >= tokens.size()) {
            return null;
        } else {
            return tokens.get(pos).getTypeDeToken();
        }
    }

    /*
     * Retourne le prochain token à lire
     * ET AVANCE au token suivant
     */
    private Token lireToken() {
        if (pos >= tokens.size()) {
            return null;
        } else {
            Token t = tokens.get(pos);
            pos++;
            return t;
        }
    }

    /*
     * Affiche le token t avec un certain niveau d'identation
     */
    private void printToken(String s) {
        for(int i=0;i<niveauIndentation;i++) {
            System.out.print("      ");
        }
        System.out.println(s);
    }


}
