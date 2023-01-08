package Analyse;

import java.util.ArrayList;
import java.util.List;

public class AnalyseLexicale {

    private static Integer TRANSITIONS[][] = {
                  // espace      m      h      t      p      f      s      c      v   chard  intval      (      )      ,
            /*0*/  {      0 ,    1 ,    6 ,   13 ,   24 ,   29 ,   36 ,   41 ,   50,      0 ,     0 ,    0 ,    0 ,    0 },
            /*1*/  {     90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90,     90 ,    90 ,    2 ,   90 ,   90 },
            /*2*/  {     90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90,     90 ,     3 ,   90 ,   90 ,   90 },
            /*3*/  {     90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90,     90 ,     3 ,   90 ,   90 ,    4 },
            /*4*/  {     90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90,     90 ,     5 ,   90 ,   90 ,   90 },
            /*5*/  {     90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90,     90 ,     5 ,   90 ,  101 ,   90 },
            /*6*/  {     90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90,     90 ,    90 ,    7 ,   90 ,   90 },
            /*7*/  {     90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90,     90 ,     8 ,   90 ,   90 ,   90 },
            /*8*/  {     90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90,     90 ,     8 ,   90 ,   90 ,    9 },
            /*9*/  {     90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90,     90 ,    10 ,   90 ,   90 ,   90 },
            /*10*/ {     90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90,     90 ,    10 ,   90 ,   90 ,   11 },
            /*11*/ {     90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90,     90 ,    12 ,   90 ,   90 ,   90 },
            /*12*/ {     90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90,     90 ,    12 ,   90 ,  102  ,  90 },
            /*13*/ {     90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90,     90 ,    90 ,   14 ,   90 ,   90 },
            /*14*/ {     90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90,     90 ,    15 ,   90 ,   90 ,   90 },
            /*15*/ {     90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90,     90 ,    15 ,   90 ,   90 ,   16 },
            /*16*/ {     90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90,     90 ,    17 ,   90 ,   90 ,   90 },
            /*17*/ {     90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90,     90 ,    17 ,   90 ,   90 ,   18 },
            /*18*/ {     90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90,     19 ,    90 ,   90 ,   90 ,   90 },
            /*19*/ {     90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90,     19 ,    90 ,   90 ,   90 ,   20 },
            /*20*/ {     90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90,     90 ,    21 ,   90 ,   90 ,   90 },
            /*21*/ {     90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90,     90 ,    21 ,   90 ,   90 ,   22 },
            /*22*/ {     90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90,     90 ,    23 ,   90 ,   90 ,   90 },
            /*23*/ {     90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90,     90 ,    23 ,   90 ,  103 ,   90 },
            /*24*/ {     90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90,     90 ,    90 ,   25 ,   90 ,   90 },
            /*25*/ {     90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90,     90 ,    26 ,   90 ,   90 ,   90 },
            /*26*/ {     90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90,     90 ,    26 ,   90 ,   90 ,   27 },
            /*27*/ {     90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90,     90 ,    28 ,   90 ,   90 ,   90 },
            /*28*/ {     90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90,     90 ,    28 ,   90 ,  104 ,   90 },
            /*29*/ {     90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90,     90 ,    90 ,   30 ,   90 ,   90 },
            /*30*/ {     90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90,     90 ,    31 ,   90 ,   90 ,   90 },
            /*31*/ {     90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90,     90 ,    31 ,   90 ,   90 ,   32 },
            /*32*/ {     90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90,     90 ,    33 ,   90 ,   90 ,   90 },
            /*33*/ {     90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90,     90 ,    33 ,   90 ,   90 ,   34 },
            /*34*/ {     90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90,     90 ,    35 ,   90 ,   90 ,   90 },
            /*35*/ {     90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90,     90 ,    35 ,   90 ,  105 ,   90 },
            /*36*/ {     90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90,     90 ,    90 ,   37 ,   90 ,   90 },
            /*37*/ {     90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90,     90 ,    38 ,   90 ,   90 ,   90 },
            /*38*/ {     90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90,     90 ,    38 ,   90 ,   90 ,   39 },
            /*39*/ {     90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90,     90 ,    40 ,   90 ,   90 ,   90 },
            /*40*/ {     90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90,     90 ,    40 ,   90 ,  106 ,   90 },
            /*41*/ {     90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90,     90 ,    90 ,   42 ,   90 ,   90 },
            /*42*/ {     90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90,     90 ,    43 ,   90 ,   90 ,   90 },
            /*43*/ {     90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90,     90 ,    43 ,   90 ,   90 ,   44 },
            /*44*/ {     90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90,     90 ,    45 ,   90 ,   90 ,   90 },
            /*45*/ {     90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90,     90 ,    45 ,   90 ,   90 ,   46 },
            /*46*/ {     90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90,     90 ,    47 ,   90 ,   90 ,   90 },
            /*47*/ {     90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90,     90 ,    47 ,   90 ,   90 ,   48 },
            /*48*/ {     90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90,     90 ,    49 ,   90 ,   90 ,   90 },
            /*49*/ {     90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90,     90 ,    49 ,   90 ,  107 ,   90 },
            /*50*/ {     90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90,     90 ,    90 ,   51 ,   90 ,   90 },
            /*51*/ {     90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90,     90 ,    52 ,   90 ,   90 ,   90 },
            /*52*/ {     90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90,     90 ,    52 ,   90 ,   90 ,   53 },
            /*53*/ {     90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90,     90 ,    54 ,   90 ,   90 ,   90 },
            /*54*/ {     90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90 ,   90,     90 ,    54 ,   90 ,  108 ,   90 },
    };

    private String entree;
    private int pos;

    private static final int ETAT_INITIAL = 0;

    /*
     * effectue l'analyse lexicale et retourne une liste de Token
     */
    public List<Token> analyse(String entree) throws Exception {

        this.entree = entree;
        pos = 0;

        List<Token> tokens = new ArrayList<>();

        /*
         * copie des symboles en entrée
         * - permet de distinguer les mots-clés des identifiants
         * - permet de conserver une copie des valeurs particulières des tokens de type
         * ident et intval
         */
        String buf = "";

        Integer etat = ETAT_INITIAL;

        Character c;
        do {
            c = lireCaractere();
            Integer prochainEtat = TRANSITIONS[etat][indiceSymbole(c)];
            if (prochainEtat == 90) {
                System.out.println("pas de transition depuis état " + etat + " avec symbole " + c);
                throw new LexicalErrorException("pas de transition depuis état " + etat + " avec symbole " + c);
            }
            if (prochainEtat >= 100) {
                // cas particulier lorsqu'un état d'acceptation est atteint
                switch (prochainEtat) {
                    case 101:
                        tokens.add(new Token(TypedeToken.m));
                        tokens.add(new Token(TypedeToken.parG));
                        tokens.add(new Token(TypedeToken.intval));
                        tokens.add(new Token(TypedeToken.virgule));
                        tokens.add(new Token(TypedeToken.intval));
                        tokens.add(new Token(TypedeToken.parD));
                        break;
                    case 102:
                        tokens.add(new Token(TypedeToken.h));
                        tokens.add(new Token(TypedeToken.parG));
                        tokens.add(new Token(TypedeToken.intval));
                        tokens.add(new Token(TypedeToken.virgule));
                        tokens.add(new Token(TypedeToken.intval));
                        tokens.add(new Token(TypedeToken.virgule));
                        tokens.add(new Token(TypedeToken.intval));
                        tokens.add(new Token(TypedeToken.parD));
                        break;
                    case 103:
                        tokens.add(new Token(TypedeToken.t));
                        tokens.add(new Token(TypedeToken.parG));
                        tokens.add(new Token(TypedeToken.intval));
                        tokens.add(new Token(TypedeToken.virgule));
                        tokens.add(new Token(TypedeToken.intval));
                        tokens.add(new Token(TypedeToken.virgule));
                        tokens.add(new Token(TypedeToken.chard));
                        tokens.add(new Token(TypedeToken.virgule));
                        tokens.add(new Token(TypedeToken.intval));
                        tokens.add(new Token(TypedeToken.virgule));
                        tokens.add(new Token(TypedeToken.intval));
                        tokens.add(new Token(TypedeToken.parD));
                        break;
                    case 104:
                        tokens.add(new Token(TypedeToken.p));
                        tokens.add(new Token(TypedeToken.parG));
                        tokens.add(new Token(TypedeToken.intval));
                        tokens.add(new Token(TypedeToken.virgule));
                        tokens.add(new Token(TypedeToken.intval));
                        tokens.add(new Token(TypedeToken.parD));
                        break;
                    case 105:
                        tokens.add(new Token(TypedeToken.f));
                        tokens.add (new Token(TypedeToken.parG));
                        tokens.add (new Token(TypedeToken.intval));
                        tokens.add (new Token(TypedeToken.virgule));
                        tokens.add (new Token(TypedeToken.intval));
                        tokens.add (new Token(TypedeToken.virgule));
                        tokens.add (new Token(TypedeToken.intval));
                        tokens.add (new Token(TypedeToken.parD));
                        break;
                    case 106:
                        tokens.add(new Token(TypedeToken.s));
                        tokens.add(new Token(TypedeToken.parG));
                        tokens.add(new Token(TypedeToken.intval));
                        tokens.add(new Token(TypedeToken.virgule));
                        tokens.add(new Token(TypedeToken.intval));
                        tokens.add(new Token(TypedeToken.parD));
                        break;
                    case 107:
                        tokens.add(new Token(TypedeToken.c));
                        tokens.add(new Token(TypedeToken.parG));
                        tokens.add(new Token(TypedeToken.intval));
                        tokens.add(new Token(TypedeToken.virgule));
                        tokens.add(new Token(TypedeToken.intval));
                        tokens.add(new Token(TypedeToken.virgule));
                        tokens.add(new Token(TypedeToken.intval));
                        tokens.add(new Token(TypedeToken.virgule));
                        tokens.add(new Token(TypedeToken.intval));
                        tokens.add(new Token(TypedeToken.parD));
                        break;

                    case 108:
                        tokens.add(new Token(TypedeToken.v));
                        tokens.add(new Token(TypedeToken.parG));
                        tokens.add(new Token(TypedeToken.intval));
                        tokens.add(new Token(TypedeToken.virgule));
                        tokens.add(new Token(TypedeToken.intval));
                        tokens.add(new Token(TypedeToken.parD));
                    case 90 :
                     break;

                }

                // un état d'acceptation ayant été atteint, retourne à l'état 0
                etat = 0;
                // reinitialise buf
                buf = "";
            } else {
                // enregistre le nouvel état
                etat = prochainEtat;
                // ajoute le symbole qui vient d'être examiné à buf
                // sauf s'il s'agit un espace ou assimilé
                if (etat > 0)
                    buf = buf + c;
            }

        } while (c != null);

        return tokens;
    }

    private Character lireCaractere() {
        Character c;
        try {
            c = entree.charAt(pos);
            pos = pos + 1;
        } catch (StringIndexOutOfBoundsException ex) {
            c = null;
        }
        return c;
    }

    private void retourArriere() {
        pos = pos - 1;
    }

    /*
     * Pour chaque symbole terminal acceptable en entrée de l'analyse syntaxique
     * retourne un indice identifiant soit un symbole, soit une classe de symbole :
     */
    private static int indiceSymbole(Character c) throws IllegalCharacterException {
        if (c == null)
            return 0 ;
        if (Character.isWhitespace(c))
            return 0;
        if (c == 'm')
            return 1;
        if (c == 'h')
            return 2;
        if (c == 't')
            return 3;
        if (c == 'p')
            return 4;
        if (c == 'f')
            return 5;
        if (c == 's')
            return 6;
        if (c == 'c')
            return 7;
        if (c == 'v')
            return 8;
        if (Character.isLetter(c))
            return 9;
        if (Character.isDigit(c))
            return 10;
        if (c == '(')
            return 11;
        if (c == ')')
            return 12;
        if (c == ',')
            return 13;
        System.out.println("Symbole inconnu : " + c);
        throw new IllegalCharacterException(c.toString());
    }
}