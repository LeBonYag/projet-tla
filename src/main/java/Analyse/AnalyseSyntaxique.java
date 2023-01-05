package Analyse;

import Analyse.Token;
import tla.*;

import java.util.List;

public class AnalyseSyntaxique   {

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
        Integer res = V();
        if (pos != tokens.size()) {
            System.out.println("L'analyse syntaxique s'est terminé avant l'examen de tous les tokens");
            throw new IncompleteParsingException();
        }
        return res;
    }

	/*

	Traite la dérivation du symbole non-terminal S

	S -> A S'

	 */

    private Integer V() throws UnexpectedTokenException {

        if (getTypeDeToken() == TypedeToken.m) {

            printToken("m");
            lireToken();
            niveauIndentation++;
            Integer m = M();
            niveauIndentation--;
            return S_prime(m);
        }

        if (getTypeDeToken() == TypedeToken.h) {

            printToken("h");
            lireToken();
            niveauIndentation++;
            Integer h = H();
            niveauIndentation--;
            return S_prime(h);
        }

        if (getTypeDeToken() == TypedeToken.t) {

            printToken("t");
            lireToken();
            niveauIndentation++;
            Integer t = T();
            niveauIndentation--;
            return S_prime(t);
        }

        if (getTypeDeToken() == TypedeToken.p) {

            printToken("p");
            lireToken();
            niveauIndentation++;
            Integer p = P();
            niveauIndentation--;
            return S_prime(p);
        }

        if (getTypeDeToken() == TypedeToken.s) {

            printToken("s");
            lireToken();
            niveauIndentation++;
            Integer s = S();
            niveauIndentation--;
            return S_prime(s);
        }

        if (getTypeDeToken() == TypedeToken.f) {

            printToken("f");
            lireToken();
            niveauIndentation++;
            Integer f = F();
            niveauIndentation--;
            return S_prime(f);
        }

        if (getTypeDeToken() == TypedeToken.c) {

            printToken("c");
            lireToken();
            niveauIndentation++;
            Integer c = C();
            niveauIndentation--;
            return S_prime(c);
        }
        throw new UnexpectedTokenException("error");
    }
    private Integer M () throws UnexpectedTokenException {

        // production M -> (intval ,intval)
        if (getTypeDeToken() == TypedeToken.parG) {
            lireToken() ;
            niveauIndentation++;
            Integer s = S() ;
            niveauIndentation--;

            if (getTypeDeToken() == TypedeToken.intval) {

                Token t = lireToken();
                niveauIndentation++;
                printToken(t.getValeur());
                niveauIndentation--;
                Integer i = Integer.valueOf(t.getValeur());
                return i  ;

                if (getTypeDeToken() == TypedeToken.virgule) {


                }

                if (getTypeDeToken() == TypedeToken.intval) {

                    Token t = lireToken();
                    niveauIndentation++;
                    printToken(t.getValeur());
                    niveauIndentation--;
                    Integer i = Integer.valueOf(t.getValeur());
                    return i  ;

            if (getTypeDeToken() == TypedeToken.parD) {
                lireToken();
                return V();
            }
        }
        throw new UnexpectedTokenException("error");
        }

    private Integer S () throws UnexpectedTokenException {

        //production S -> intVal A'
        if (getTypeDeToken() == TypedeToken.intval) {

            Token t = lireToken();
            niveauIndentation++;
            printToken(t.getValeur());
            niveauIndentation--;
            Integer i = Integer.valueOf(t.getValeur());
              return i  ;
        }
        throw new UnexpectedTokenException("error");
    }
    private Integer F_prime(Integer i) throws UnexpectedTokenException {

        return i ;
    }



    private Integer S_prime(Integer i) throws UnexpectedTokenException {

      return i ;
    }

    private Integer H() throws UnexpectedTokenException {
    return null ;
    }
    private Integer T() throws UnexpectedTokenException {
        return null ;
    } private Integer P() throws UnexpectedTokenException {
    return null ;
    } private Integer S() throws UnexpectedTokenException {
    return null ;
    } private Integer F() throws UnexpectedTokenException {
    return null ;
}
    private Integer C() throws UnexpectedTokenException {
        return null ;
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
