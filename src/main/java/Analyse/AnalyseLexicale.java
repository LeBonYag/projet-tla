package Analyse;

import java.util.ArrayList;
import java.util.List;

public class AnalyseLexicale {

    private static Integer TRANSITIONS[][] = {
                  // espace      m      h      t      p      f      s      c    chard  intval      (      )      ,
            /*0*/  {      0 ,    1 ,    6 ,   13 ,   24 ,   29 ,   36 ,   41 ,      0 ,     0 ,    0 ,    0 ,    0 },
            /*1*/  {     50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,     50 ,    50 ,    2 ,   50 ,   50 },
            /*2*/  {     50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,     50 ,     3 ,   50 ,   50 ,   50 },
            /*3*/  {     50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,     50 ,     3 ,   50 ,   50 ,    4 },
            /*4*/  {     50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,     50 ,     5 ,   50 ,   50 ,   50 },
            /*5*/  {     50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,     50 ,     5 ,   50 ,  101 ,   50 },
            /*6*/  {     50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,     50 ,    50 ,    7 ,   50 ,   50 },
            /*7*/  {     50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,     50 ,     8 ,   50 ,   50 ,   50 },
            /*8*/  {     50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,     50 ,     8 ,   50 ,   50 ,    9 },
            /*9*/  {     50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,     50 ,    10 ,   50 ,   50 ,   50 },
            /*10*/ {     50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,     50 ,    10 ,   50 ,   50 ,   11 },
            /*11*/ {     50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,     50 ,    12 ,   50 ,   50 ,   50 },
            /*12*/ {     50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,     50 ,    12 ,   50 ,  102  ,  50 },
            /*13*/ {     50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,     50 ,    50 ,   14 ,   50 ,   50 },
            /*14*/ {     50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,     50 ,    15 ,   50 ,   50 ,   50 },
            /*15*/ {     50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,     50 ,    15 ,   50 ,   50 ,   16 },
            /*16*/ {     50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,     50 ,    17 ,   50 ,   50 ,   50 },
            /*17*/ {     50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,     50 ,    17 ,   50 ,   50 ,   18 },
            /*18*/ {     50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,     19 ,    50 ,   50 ,   50 ,   50 },
            /*19*/ {     50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,     19 ,    50 ,   50 ,   50 ,   20 },
            /*20*/ {     50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,     50 ,    21 ,   50 ,   50 ,   50 },
            /*21*/ {     50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,     50 ,    21 ,   50 ,   50 ,   22 },
            /*22*/ {     50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,     50 ,    23 ,   50 ,   50 ,   50 },
            /*23*/ {     50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,     50 ,    23 ,   50 ,  103 ,   50 },
            /*24*/ {     50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,     50 ,    50 ,   25 ,   50 ,   50 },
            /*25*/ {     50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,     50 ,    26 ,   50 ,   50 ,   50 },
            /*26*/ {     50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,     50 ,    26 ,   50 ,   50 ,   27 },
            /*27*/ {     50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,     50 ,    28 ,   50 ,   50 ,   50 },
            /*28*/ {     50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,     50 ,    28 ,   50 ,  104 ,   50 },
            /*29*/ {     50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,     50 ,    50 ,   30 ,   50 ,   50 },
            /*30*/ {     50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,     50 ,    31 ,   50 ,   50 ,   50 },
            /*31*/ {     50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,     50 ,    31 ,   50 ,   50 ,   32 },
            /*32*/ {     50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,     50 ,    33 ,   50 ,   50 ,   50 },
            /*33*/ {     50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,     50 ,    33 ,   50 ,   50 ,   34 },
            /*34*/ {     50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,     35 ,    50 ,   50 ,   50 ,   50 },
            /*35*/ {     50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,     35 ,    50 ,   50 ,  105 ,   50 },
            /*36*/ {     50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,     50 ,    50 ,   37 ,   50 ,   50 },
            /*37*/ {     50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,     50 ,    38 ,   50 ,   50 ,   50 },
            /*38*/ {     50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,     50 ,    38 ,   50 ,   50 ,   39 },
            /*39*/ {     50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,     50 ,    40 ,   50 ,   50 ,   50 },
            /*40*/ {     50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,     50 ,    40 ,   50 ,  106 ,   50 },
            /*41*/ {     50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,     50 ,    50 ,   42 ,   50 ,   50 },
            /*42*/ {     50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,     50 ,    43 ,   50 ,   50 ,   50 },
            /*43*/ {     50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,     50 ,    43 ,   50 ,   50 ,   44 },
            /*44*/ {     50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,     50 ,    45 ,   50 ,   50 ,   50 },
            /*45*/ {     50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,     50 ,    45 ,   50 ,   50 ,   46 },
            /*46*/ {     50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,     50 ,    47 ,   50 ,   50 ,   50 },
            /*47*/ {     50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,     50 ,    47 ,   50 ,   50 ,   48 },
            /*48*/ {     50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,     50 ,    49 ,   50 ,   50 ,   50 },
            /*49*/ {     50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,   50 ,     50 ,    49 ,   50 ,  107 ,   50 },


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
            if (prochainEtat == 50) {
                System.out.println("pas de transition depuis état " + etat + " avec symbole " + c);
                throw new LexicalErrorException("pas de transition depuis état " + etat + " avec symbole " + c);
            }
            if (prochainEtat >= 100) {
                // cas particulier lorsqu'un état d'acceptation est atteint
                switch (prochainEtat) {
                    case 101:
                        tokens.add(new Token(TypedeToken.m));
                        break;
                    case 102:
                        tokens.add(new Token(TypedeToken.h));
                        break;
                    case 103:
                        tokens.add(new Token(TypedeToken.t));
                        break;
                    case 104:
                        tokens.add(new Token(TypedeToken.p));
                        break;
                    case 105:
                        tokens.add(new Token(TypedeToken.f));
                        break;
                    case 106:
                        tokens.add(new Token(TypedeToken.s));
                        break;
                    case 107:
                        tokens.add(new Token(TypedeToken.c));
                        break;
                    case 50 :
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
        if (Character.isLetter(c))
            return 8;
        if (Character.isDigit(c))
            return 9;
        if (c == '(')
            return 10;
        if (c == ')')
            return 11;
        if (c == ',')
            return 12;
        System.out.println("Symbole inconnu : " + c);
        throw new IllegalCharacterException(c.toString());
    }
}