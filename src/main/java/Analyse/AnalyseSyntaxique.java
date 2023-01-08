package Analyse ;


import tla.*;

import java.util.List;
import java.util.Objects;


public class AnalyseSyntaxique {

    private int pos;
    private List<Token> tokens;
    private int niveauIndentation;

    /*
    effectue l'analyse syntaxique à partir de la liste de tokens
    et retourne le noeud racine de l'arbre syntaxique abstrait
     */
    public Noeud analyse(List<Token> tokens) throws Exception {
        this.pos = 0;
        this.tokens = tokens;
        this.niveauIndentation = 0;
        Noeud res = S();
        if (pos != tokens.size()) {
            System.out.println("L'analyse syntaxique s'est terminé avant l'examen de tous les tokens");
            throw new IncompleteParsingException();
        }
        return res;
    }

 /*   private Noeud Expr() throws UnexpectedTokenException {

        if (getTypeDeToken() == TypedeToken.intval ){
            lireToken();
            return I() ;

        }
        if (getTypeDeToken() == TypedeToken.chard) {
            lireToken() ;
            return C() ;
        }
        throw new UnexpectedTokenException("intval ou chard attendu");
    }*/
	/*

	Traite la dérivation du symbole non-terminal V

	V -> mPIVIP’ | sPIVIP’ | cPIVIVIVIP’ |  pPIVIP’ |  fPIVIVCP’|  tPIVIVCVIVIP’ | hPIVIVIP’

	 */
public Noeud S() throws UnexpectedTokenException {

    // S -> m(intval, intval)
    if (getTypeDeToken()== TypedeToken.m) {
        lireToken() ;

       if (lireToken().getTypeDeToken() != TypedeToken.parG) {
           throw new UnexpectedTokenException("( attendu");
       }

       Noeud n = new Noeud(TypeDeNoeud.m);
       Token t = lireToken() ;
       n.ajout(new Noeud(TypeDeNoeud.intval , t.getValeur()));

        if (lireToken().getTypeDeToken() != TypedeToken.virgule) {
            throw new UnexpectedTokenException(" , attendu");
        }
        Token t1 = lireToken() ;
        n.ajout(new Noeud(TypeDeNoeud.intval , t1.getValeur()));


        if (lireToken().getTypeDeToken() != TypedeToken.parD) {
            throw new UnexpectedTokenException(") attendu");
        }

        n.ajout(F());

        return n ;
    }

    // S -> h(intval , intval)
    if (getTypeDeToken()==TypedeToken.h) {

       lireToken() ;
        if (lireToken().getTypeDeToken() != TypedeToken.parG) {
            throw new UnexpectedTokenException("( attendu");
        }
        Noeud n = new Noeud(TypeDeNoeud.h);
        Token t = lireToken() ;
        n.ajout(new Noeud(TypeDeNoeud.intval , t.getValeur()));

        if (lireToken().getTypeDeToken() != TypedeToken.virgule) {
            throw new UnexpectedTokenException(", attendu");
        }
        Token t1 = lireToken() ;
        n.ajout(new Noeud(TypeDeNoeud.intval , t1.getValeur()));

        if (lireToken().getTypeDeToken() != TypedeToken.parD) {
            throw new UnexpectedTokenException(") attendu");
        }
        n.ajout(F());

        return n ;


    }

   // S ->  t(intval , intval , chard , intval , intval)
    if (getTypeDeToken()==TypedeToken.t) {

        lireToken() ;

        if (lireToken().getTypeDeToken() != TypedeToken.parG) {
            throw new UnexpectedTokenException("( attendu");
        }
        Noeud n1 = new Noeud(TypeDeNoeud.t);
        Token t = lireToken() ;
        n1.ajout(new Noeud(TypeDeNoeud.intval , t.getValeur()));


        if (lireToken().getTypeDeToken() != TypedeToken.virgule) {
            throw new UnexpectedTokenException(", attendu");
        }
        Token t1 = lireToken() ;
        n1.ajout(new Noeud(TypeDeNoeud.intval , t1.getValeur()));

        if (lireToken().getTypeDeToken() != TypedeToken.virgule) {
            throw new UnexpectedTokenException(" attendu");
        }

        Token t2 = lireToken() ;
        n1.ajout(new Noeud(TypeDeNoeud.chard , t2.getValeur()));

        if (lireToken().getTypeDeToken() != TypedeToken.virgule) {
            throw new UnexpectedTokenException(", attendu");
        }
        Token t3 = lireToken() ;
        n1.ajout(new Noeud(TypeDeNoeud.intval , t3.getValeur()));

        if (lireToken().getTypeDeToken() != TypedeToken.virgule) {
            throw new UnexpectedTokenException(", attendu");
        }

        Token t4 = lireToken() ;
        n1.ajout(new Noeud(TypeDeNoeud.intval, t4.getValeur()));

        if (lireToken().getTypeDeToken() != TypedeToken.parD) {
            throw new UnexpectedTokenException(") attendu");
        }
        n1.ajout(F());

    return n1 ;
    }

    // S -> p(intval , intval)
    if (getTypeDeToken()==TypedeToken.p) {
       lireToken() ;

        if (lireToken().getTypeDeToken() != TypedeToken.parG) {
            throw new UnexpectedTokenException("( attendu");
        }
        Noeud n2 = new Noeud(TypeDeNoeud.p);
        Token t = lireToken() ;
        n2.ajout(new Noeud(TypeDeNoeud.intval , t.getValeur()));

        if (lireToken().getTypeDeToken() != TypedeToken.virgule) {
            throw new UnexpectedTokenException(", attendu");
        }
        Token t1 = lireToken() ;
        n2.ajout(new Noeud(TypeDeNoeud.intval , t1.getValeur()));

        if (lireToken().getTypeDeToken() != TypedeToken.parD) {
            throw new UnexpectedTokenException(") attendu");
        }
        n2.ajout(F());

        return n2 ;


    }

    // S -> s(intval,intval)
    if (getTypeDeToken()==TypedeToken.s) {
        lireToken() ;

        if (lireToken().getTypeDeToken() != TypedeToken.parG) {
            throw new UnexpectedTokenException("( attendu");
        }
        Noeud n3 = new Noeud(TypeDeNoeud.s);
        Token t = lireToken() ;
        n3.ajout(new Noeud(TypeDeNoeud.intval , t.getValeur()));

        if (lireToken().getTypeDeToken() != TypedeToken.virgule) {
            throw new UnexpectedTokenException(", attendu");
        }
        Token t1 = lireToken() ;
        n3.ajout(new Noeud(TypeDeNoeud.intval , t1.getValeur()));

        if (lireToken().getTypeDeToken() != TypedeToken.parD) {
            throw new UnexpectedTokenException(") attendu");
        }
        n3.ajout(F());

        return n3 ;

    }

    // S -> fPIVIVCP’
    if (getTypeDeToken()==TypedeToken.f) {
         lireToken() ;

        if (lireToken().getTypeDeToken() != TypedeToken.parG) {
            throw new UnexpectedTokenException("( attendu");
        }
        Noeud n4 = new Noeud(TypeDeNoeud.f);
        Token t = lireToken() ;
        n4.ajout(new Noeud(TypeDeNoeud.intval , t.getValeur()));

        if (lireToken().getTypeDeToken() != TypedeToken.virgule) {
            throw new UnexpectedTokenException(", attendu");
        }
        Token t1 = lireToken() ;
        n4.ajout(new Noeud(TypeDeNoeud.intval , t1.getValeur()));

        if (lireToken().getTypeDeToken() != TypedeToken.virgule) {
            throw new UnexpectedTokenException(", attendu");
        }
        Token t2 = lireToken() ;
        n4.ajout(new Noeud(TypeDeNoeud.chard , t2.getValeur()));

        if (lireToken().getTypeDeToken() != TypedeToken.parD) {
            throw new UnexpectedTokenException(") attendu");
        }
        n4.ajout(F());

        return n4 ;
    }

    // S -> cPIVIVIVIP
    if (getTypeDeToken()==TypedeToken.c) {
        lireToken();

        if (lireToken().getTypeDeToken() != TypedeToken.parG) {
            throw new UnexpectedTokenException("( attendu");
        }
        Noeud n5 = new Noeud(TypeDeNoeud.c);
        Token t = lireToken();
        n5.ajout(new Noeud(TypeDeNoeud.intval, t.getValeur()));

        if (lireToken().getTypeDeToken() != TypedeToken.virgule) {
            throw new UnexpectedTokenException(", attendu");
        }
        Token t1 = lireToken();
        n5.ajout(new Noeud(TypeDeNoeud.intval, t1.getValeur()));

        if (lireToken().getTypeDeToken() != TypedeToken.virgule) {
            throw new UnexpectedTokenException(", attendu");
        }
        Token t2 = lireToken();
        n5.ajout(new Noeud(TypeDeNoeud.intval, t2.getValeur()));

        if (lireToken().getTypeDeToken() != TypedeToken.virgule) {
            throw new UnexpectedTokenException(", attendu");
        }
        Token t3 = lireToken();
        n5.ajout(new Noeud(TypeDeNoeud.intval, t3.getValeur()));


        if (lireToken().getTypeDeToken() != TypedeToken.parD) {
            throw new UnexpectedTokenException(") attendu");
        }
        n5.ajout(F());

        return n5;
    }
        if (getTypeDeToken()== TypedeToken.v) {
            lireToken() ;

            if (lireToken().getTypeDeToken() != TypedeToken.parG) {
                throw new UnexpectedTokenException("( attendu");
            }

            Noeud n6 = new Noeud(TypeDeNoeud.v);
            Token t = lireToken() ;
            n6.ajout(new Noeud(TypeDeNoeud.intval , t.getValeur()));

            if (lireToken().getTypeDeToken() != TypedeToken.virgule) {
                throw new UnexpectedTokenException(" , attendu");
            }
            Token t1 = lireToken() ;
            n6.ajout(new Noeud(TypeDeNoeud.intval , t1.getValeur()));


            if (lireToken().getTypeDeToken() != TypedeToken.parD) {
                throw new UnexpectedTokenException(") attendu");
            }

            n6.ajout(F());

            return n6 ;
        }


    throw new UnexpectedTokenException("attributs attendu");
}
  /*  public Noeud I() throws UnexpectedTokenException {
        if (getTypeDeToken() == TypedeToken.intval) {
            Token t = lireToken();

            return new Noeud (TypeDeNoeud.intval, t.getValeur()) ;
        }
        throw new UnexpectedTokenException("intval attendu");
    } */




 /*   public Noeud C() throws UnexpectedTokenException {
        if (getTypeDeToken() == TypedeToken.chard) {


            Token t = lireToken();

           return new Noeud (TypeDeNoeud.chard,t.getValeur());

        }
        throw new UnexpectedTokenException("caractère directionnel attendu");
    }*/

    public Noeud F() throws UnexpectedTokenException {

        if (finAtteinte()) return null  ;

        return S() ;
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
        if (pos >=tokens.size()) {
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
            return null ;
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
