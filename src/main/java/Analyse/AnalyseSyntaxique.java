package Analyse ;


import tla.*;

import java.util.List;



public class AnalyseSyntaxique {

    private int pos;
    private List<Token> tokens;
    private int niveauIndentation;

    /*
    effectue l'analyse syntaxique à partir de la liste de tokens
    et retourne le noeud racine de l'arbre syntaxique abstrait
     */
    public Noeud  analyse(List<Token> tokens) throws Exception {
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

    private Noeud Expr() throws UnexpectedTokenException {

        if (getTypeDeToken() == TypedeToken.intval ){
            return I() ;

        }
        if (getTypeDeToken() == TypedeToken.chard) {
            return C() ;
        }
        throw new UnexpectedTokenException("intval attendu");
    }
	/*

	Traite la dérivation du symbole non-terminal V

	V -> mPIVIP’ | sPIVIP’ | cPIVIVIVIP’ |  pPIVIP’ |  fPIVIVCP’|  tPIVIVCVIVIP’ | cPIVIVIP’

	 */
public Noeud S() throws UnexpectedTokenException {

    // S -> m(intval, intval)
    if (getTypeDeToken()== TypedeToken.m) {
        lireToken() ;

       if (lireToken().getTypeDeToken() != TypedeToken.parG) {
           throw new UnexpectedTokenException("( attendu");
       }
       Noeud n = new Noeud(TypeDeNoeud.m);
       n.ajout(Expr());

        if (lireToken().getTypeDeToken() != TypedeToken.virgule) {
            throw new UnexpectedTokenException(" , attendu");
        }
        n.ajout(Expr());

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
        n.ajout(Expr());

        if (lireToken().getTypeDeToken() != TypedeToken.virgule) {
            throw new UnexpectedTokenException(", attendu");
        }
        n.ajout(Expr());

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
        n1.ajout(Expr());

        if (lireToken().getTypeDeToken() != TypedeToken.virgule) {
            throw new UnexpectedTokenException(", attendu");
        }
        n1.ajout(Expr());

        if (lireToken().getTypeDeToken() != TypedeToken.virgule) {
            throw new UnexpectedTokenException(" attendu");
        }

        n1.ajout(Expr());

        if (lireToken().getTypeDeToken() != TypedeToken.virgule) {
            throw new UnexpectedTokenException(", attendu");
        }
        n1.ajout(Expr());

        if (lireToken().getTypeDeToken() != TypedeToken.virgule) {
            throw new UnexpectedTokenException(", attendu");
        }
        n1.ajout(Expr());

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
        n2.ajout(Expr());

        if (lireToken().getTypeDeToken() != TypedeToken.virgule) {
            throw new UnexpectedTokenException(", attendu");
        }
        n2.ajout(Expr());

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
        n3.ajout(Expr());

        if (lireToken().getTypeDeToken() != TypedeToken.virgule) {
            throw new UnexpectedTokenException(", attendu");
        }
        n3.ajout(Expr());

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
        n4.ajout(Expr());

        if (lireToken().getTypeDeToken() != TypedeToken.virgule) {
            throw new UnexpectedTokenException(", attendu");
        }
        n4.ajout(Expr());

        if (lireToken().getTypeDeToken() != TypedeToken.virgule) {
            throw new UnexpectedTokenException(", attendu");
        }
        n4.ajout(Expr());

        if (lireToken().getTypeDeToken() != TypedeToken.parD) {
            throw new UnexpectedTokenException(") attendu");
        }
        n4.ajout(F());

        return n4 ;
    }

    // S -> cPIVIVIVIP
    if (getTypeDeToken()==TypedeToken.c) {
        lireToken() ;

        if (lireToken().getTypeDeToken() != TypedeToken.parG) {
            throw new UnexpectedTokenException("( attendu");
        }
        Noeud n5 = new Noeud(TypeDeNoeud.c);
        n5.ajout(Expr());

        if (lireToken().getTypeDeToken() != TypedeToken.virgule) {
            throw new UnexpectedTokenException(", attendu");
        }
        n5.ajout(Expr());

        if (lireToken().getTypeDeToken() != TypedeToken.virgule) {
            throw new UnexpectedTokenException(", attendu");
        }
        n5.ajout(Expr());

        if (lireToken().getTypeDeToken() != TypedeToken.virgule) {
            throw new UnexpectedTokenException(", attendu");
        }
        n5.ajout(Expr());

        if (lireToken().getTypeDeToken() != TypedeToken.parD) {
            throw new UnexpectedTokenException(") attendu");
        }
        n5.ajout(F());

        return n5 ;

    }

    throw new UnexpectedTokenException("attributs attendu");
}
    public Noeud I() throws UnexpectedTokenException {
        if (getTypeDeToken() == TypedeToken.intval) {
            Token t = lireToken();

            return new Noeud (TypeDeNoeud.intval, t.getValeur()) ;
        }
        throw new UnexpectedTokenException("intval attendu");
    }

    public Noeud P() throws UnexpectedTokenException {
        if (getTypeDeToken() == TypedeToken.parG) {
            Token t = lireToken();
            printToken("(");

        }
        throw new UnexpectedTokenException("( attendu");
    }

    public Noeud P_prime(Integer i ) throws UnexpectedTokenException {
        if (getTypeDeToken() == TypedeToken.parD) {
            Token t = lireToken();
            printToken(")");

        }
        throw new UnexpectedTokenException(") attendu");
    }

    public Noeud C() throws UnexpectedTokenException {
        if (getTypeDeToken() == TypedeToken.chard) {


            Token t = lireToken();

           return new Noeud (TypeDeNoeud.chard,t.getValeur());

        }
        throw new UnexpectedTokenException("caractère directionnel attendu");
    }

    public Noeud F() throws UnexpectedTokenException {

        if (finAtteinte()) return null ;

       return S() ;
    }

    public Noeud V(Integer i ) throws UnexpectedTokenException {
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
