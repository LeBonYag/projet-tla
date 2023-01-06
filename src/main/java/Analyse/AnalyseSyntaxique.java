package Analyse ;


import tla.*;

import java.util.List;

import static Analyse.TypedeToken.m;


public class AnalyseSyntaxique {

    private int pos;
    private List<Token> tokens;
    private int niveauIndentation;

    /*
    effectue l'analyse syntaxique à partir de la liste de tokens
    et retourne le noeud racine de l'arbre syntaxique abstrait
     */
    public Integer analyse(List<Token> tokens) throws Exception {
        this.pos = 0;
        this.tokens = tokens;
        this.niveauIndentation = 0;
        Integer res = S();
        if (pos != tokens.size()) {
            System.out.println("L'analyse syntaxique s'est terminé avant l'examen de tous les tokens");
            throw new IncompleteParsingException();
        }
        return res;
    }

	/*

	Traite la dérivation du symbole non-terminal V

	V -> mPIVIP’ | sPIVIP’ | cPIVIVIVIP’ |  pPIVIP’ |  fPIVIVCP’|  tPIVIVCVIVIP’ | cPIVIVIP’

	 */
public Integer S() throws UnexpectedTokenException {

    // S -> mPIVIP’
    if (getTypeDeToken()== TypedeToken.m) {
        lireToken() ;
        printToken("m");
        Integer m = P() ;

        Integer m1 = I(m) ;

        Integer m2 = V(m1);

        Integer m3 = I(m2) ;

        Integer m4 = V(m3) ;

        Integer m5 = I(m4) ;

        Integer m6 = P_prime(m5);

        CARREAUX.ArrayasList();
        return F(m6) ;
    }

    // S -> hPIVIVIP’
    if (getTypeDeToken()==TypedeToken.h) {
        Token t = lireToken() ;
        printToken("h");
        Integer h = P();
         I(h) ;
        return P_prime(h);
    }

   // S ->  tPIVIVCVIVIP’
    if (getTypeDeToken()==TypedeToken.t) {
        Token to = lireToken() ;
        printToken("t");

        Integer t = P();

        return P_prime(t);
    }

    // S -> pPIVIP’
    if (getTypeDeToken()==TypedeToken.p) {
        Token t = lireToken() ;
        printToken("p");
        Integer p = P();
        lireToken();


        return P_prime(p);
    }

    // S -> sPIVIP
    if (getTypeDeToken()==TypedeToken.s) {
        Token t = lireToken() ;
        printToken("s");

        Integer s = P();

        return P_prime(s);
    }

    // S -> fPIVIVCP’
    if (getTypeDeToken()==TypedeToken.f) {
        Token t = lireToken() ;
        printToken("f");

        Integer f = P();

        return P_prime(f);
    }

    // S -> cPIVIVIVIP
    if (getTypeDeToken()==TypedeToken.c) {
        Token t = lireToken() ;
        printToken("c");

        Integer c = P();

        return P_prime(c);

    }

    throw new UnexpectedTokenException("attributs attendu");
}
    public Integer I(Integer i) throws UnexpectedTokenException {
        if (getTypeDeToken() == TypedeToken.intval) {
            Token t = lireToken();

            printToken(String.valueOf(t.getValeur()));

            Integer ii = Integer.valueOf(t.getValeur());

            return V(i);
        }
        throw new UnexpectedTokenException(") attendu");
    }

    public Integer P() throws UnexpectedTokenException {
        if (getTypeDeToken() == TypedeToken.parG) {
            Token t = lireToken();
            printToken("(");

        }
        throw new UnexpectedTokenException("( attendu");
    }

    public Integer P_prime(Integer i ) throws UnexpectedTokenException {
        if (getTypeDeToken() == TypedeToken.parD) {
            Token t = lireToken();
            printToken(")");

        }
        throw new UnexpectedTokenException(") attendu");
    }

    public Integer C() throws UnexpectedTokenException {
        if (getTypeDeToken() == TypedeToken.chard) {

            // production A -> intVal A'

            Token t = lireToken();

            printToken(String.valueOf(t.getValeur()));

        }
        throw new UnexpectedTokenException("caractère directionnel attendu");
    }

    public Integer F(Integer i) throws UnexpectedTokenException {
        if (getTypeDeToken() == TypedeToken.parD) {


            Integer f = S() ;
        }
        if (finAtteinte()) {

            return i ;
        }
        throw new UnexpectedTokenException("relançer ou fin d'entrée attendu");
    }

    public Integer V(Integer i ) throws UnexpectedTokenException {
        if (getTypeDeToken() == TypedeToken.virgule) {
            Token t = lireToken();
            printToken(",");
        }
        throw new UnexpectedTokenException(", attendu");
    }
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
