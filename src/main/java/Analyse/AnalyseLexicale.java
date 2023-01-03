package Analyse;

import java.util.ArrayList;
import java.util.List;

public class AnalyseLexicale {

    private static Integer TRANSITIONS[][] = {
            // espace m h t p f s c chard intval ( ) ,
            /* 0 */ { 0, 1, 6, 13, 24, 29, 36, 41, 0, 0, 0, 0, 0 },
            /* 1 */ { null, null, null, null, null, null, null, null, null, null, 2, null, null },
            /* 2 */ { null, null, null, null, null, null, null, null, null, 3, null, null, null },
            /* 3 */ { null, null, null, null, null, null, null, null, null, 3, null, null, 4 },
            /* 4 */ { null, null, null, null, null, null, null, null, null, 5, null, null, null },
            /* 5 */ { null, null, null, null, null, null, null, null, null, 5, null, 101, null },
            /* 6 */ { null, null, null, null, null, null, null, null, null, null, 7, null, null },
            /* 7 */ { null, null, null, null, null, null, null, null, null, 8, null, null, null },
            /* 8 */ { null, null, null, null, null, null, null, null, null, 8, null, null, 9 },
            /* 9 */ { null, null, null, null, null, null, null, null, null, 10, null, null, null },
            /* 10 */ { null, null, null, null, null, null, null, null, null, 10, null, null, 11 },
            /* 11 */ { null, null, null, null, null, null, null, null, null, 12, null, null, null },
            /* 12 */ { null, null, null, null, null, null, null, null, null, 12, null, 102, null },
            /* 13 */ { null, null, null, null, null, null, null, null, null, null, 14, null, null },
            /* 14 */ { null, null, null, null, null, null, null, null, null, 15, null, null, null },
            /* 15 */ { null, null, null, null, null, null, null, null, null, 15, null, null, 16 },
            /* 16 */ { null, null, null, null, null, null, null, null, null, 17, null, null, null },
            /* 17 */ { null, null, null, null, null, null, null, null, null, 17, null, null, 18 },
            /* 18 */ { null, null, null, null, null, null, null, null, 19, null, null, null, null },
            /* 19 */ { null, null, null, null, null, null, null, null, 19, null, null, null, 20 },
            /* 20 */ { null, null, null, null, null, null, null, null, null, 21, null, null, null },
            /* 21 */ { null, null, null, null, null, null, null, null, null, 21, null, null, 22 },
            /* 22 */ { null, null, null, null, null, null, null, null, null, 23, null, null, null },
            /* 23 */ { null, null, null, null, null, null, null, null, null, 23, null, 103, null },
            /* 24 */ { null, null, null, null, null, null, null, null, null, null, 25, null, null },
            /* 25 */ { null, null, null, null, null, null, null, null, null, 26, null, null, null },
            /* 26 */ { null, null, null, null, null, null, null, null, null, 26, null, null, 27 },
            /* 27 */ { null, null, null, null, null, null, null, null, null, 28, null, null, null },
            /* 28 */ { null, null, null, null, null, null, null, null, null, 28, null, 104, null },
            /* 29 */ { null, null, null, null, null, null, null, null, null, null, 30, null, null },
            /* 30 */ { null, null, null, null, null, null, null, null, null, 31, null, null, null },
            /* 31 */ { null, null, null, null, null, null, null, null, null, 31, null, null, 32 },
            /* 32 */ { null, null, null, null, null, null, null, null, null, 33, null, null, null },
            /* 33 */ { null, null, null, null, null, null, null, null, null, 33, null, null, 34 },
            /* 34 */ { null, null, null, null, null, null, null, null, 35, null, null, null, null },
            /* 35 */ { null, null, null, null, null, null, null, null, 35, null, null, 105, null },
            /* 36 */ { null, null, null, null, null, null, null, null, null, null, 37, null, null },
            /* 37 */ { null, null, null, null, null, null, null, null, null, 38, null, null, null },
            /* 38 */ { null, null, null, null, null, null, null, null, null, 38, null, null, 39 },
            /* 39 */ { null, null, null, null, null, null, null, null, null, 40, null, null, null },
            /* 40 */ { null, null, null, null, null, null, null, null, null, 40, null, 106, null },
            /* 41 */ { null, null, null, null, null, null, null, null, null, null, 42, null, null },
            /* 42 */ { null, null, null, null, null, null, null, null, null, 43, null, null, null },
            /* 43 */ { null, null, null, null, null, null, null, null, null, 43, null, null, 44 },
            /* 44 */ { null, null, null, null, null, null, null, null, null, 45, null, null, null },
            /* 45 */ { null, null, null, null, null, null, null, null, null, 45, null, null, 46 },
            /* 46 */ { null, null, null, null, null, null, null, null, null, 47, null, null, null },
            /* 47 */ { null, null, null, null, null, null, null, null, null, 47, null, null, 48 },
            /* 48 */ { null, null, null, null, null, null, null, null, null, 49, null, null, null },
            /* 49 */ { null, null, null, null, null, null, null, null, null, 49, null, 107, null },

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
            if (prochainEtat == null) {
                System.out.println("pas de transition depuis état " + etat + " avec symbole " + c);
                throw new LexicalErrorException("pas de transition depuis état " + etat + " avec symbole " + c);
            }
            if (prochainEtat >= 101) {
                // cas particulier lorsqu'un état d'acceptation est atteint
                switch (prochainEtat) {
                    case 101:
                        tokens.add(new Token(TypedeToken.m));
                        break;
                    case 102:
                        tokens.add(new Token(TypedeToken.m));
                        break;
                    case 103:
                        tokens.add(new Token(TypedeToken.m));
                        break;
                    case 104:
                        tokens.add(new Token(TypedeToken.m));
                        break;
                    case 105:
                        tokens.add(new Token(TypedeToken.m));
                        break;
                    case 106:
                        tokens.add(new Token(TypedeToken.m));
                        break;
                    case 107:
                        tokens.add(new Token(TypedeToken.m));
                        break;
                    case 1:
                        tokens.add(new Token(TypedeToken.m));
                        break;

                    case 3:
                        tokens.add(new Token(TypedeToken.intval));
                        retourArriere();
                        break;
                    case 5:
                        tokens.add(new Token(TypedeToken.intval));
                        retourArriere();
                        break;
                    case 8:
                        tokens.add(new Token(TypedeToken.intval));
                        retourArriere();
                        break;
                    case 10:
                        tokens.add(new Token(TypedeToken.intval));
                        retourArriere();
                        break;
                    case 12:
                        tokens.add(new Token(TypedeToken.intval));
                        retourArriere();
                        break;
                    case 15:
                        tokens.add(new Token(TypedeToken.intval));
                        retourArriere();
                        break;
                    case 17:
                        tokens.add(new Token(TypedeToken.intval));
                        retourArriere();
                        break;
                    case 20:
                        tokens.add(new Token(TypedeToken.intval));
                        retourArriere();
                        break;
                    case 22:
                        tokens.add(new Token(TypedeToken.intval));
                        retourArriere();
                        break;
                    case 25:
                        tokens.add(new Token(TypedeToken.intval));
                        retourArriere();
                        break;
                    case 27:
                        tokens.add(new Token(TypedeToken.intval));
                        retourArriere();
                        break;
                    case 30:
                        tokens.add(new Token(TypedeToken.intval));
                        retourArriere();
                        break;
                    case 32:
                        tokens.add(new Token(TypedeToken.intval));
                        retourArriere();
                        break;
                    case 35:
                        tokens.add(new Token(TypedeToken.intval));
                        retourArriere();
                        break;
                    case 37:
                        tokens.add(new Token(TypedeToken.intval));
                        retourArriere();
                        break;
                    case 42:
                        tokens.add(new Token(TypedeToken.intval));
                        retourArriere();
                        break;
                    case 44:
                        tokens.add(new Token(TypedeToken.intval));
                        retourArriere();
                        break;
                    case 46:
                        tokens.add(new Token(TypedeToken.intval));
                        retourArriere();
                        break;
                    case 48:
                        tokens.add(new Token(TypedeToken.intval));
                        retourArriere();
                        break;

                    case 18:
                        tokens.add(new Token(TypedeToken.chard));
                        break;
                    case 39:
                        tokens.add(new Token(TypedeToken.chard));
                        break;

                    case 2:
                        tokens.add(new Token(TypedeToken.ParG));
                        break;
                    case 7:
                        tokens.add(new Token(TypedeToken.ParG));
                        break;
                    case 14:
                        tokens.add(new Token(TypedeToken.ParG));
                        break;
                    case 24:
                        tokens.add(new Token(TypedeToken.ParG));
                        break;
                    case 29:
                        tokens.add(new Token(TypedeToken.ParG));
                        break;
                    case 34:
                        tokens.add(new Token(TypedeToken.ParG));
                        break;
                    case 41:
                        tokens.add(new Token(TypedeToken.ParG));
                        break;

                    case 4:
                        tokens.add(new Token(TypedeToken.virgule));
                        break;
                    case 9:
                        tokens.add(new Token(TypedeToken.virgule));
                        break;
                    case 11:
                        tokens.add(new Token(TypedeToken.virgule));
                        break;
                    case 16:
                        tokens.add(new Token(TypedeToken.virgule));
                        break;
                    case 19:
                        tokens.add(new Token(TypedeToken.virgule));
                        break;
                    case 21:
                        tokens.add(new Token(TypedeToken.virgule));
                        break;
                    case 26:
                        tokens.add(new Token(TypedeToken.virgule));
                        break;
                    case 31:
                        tokens.add(new Token(TypedeToken.virgule));
                        break;
                    case 36:
                        tokens.add(new Token(TypedeToken.virgule));
                        break;
                    case 38:
                        tokens.add(new Token(TypedeToken.virgule));
                        break;
                    case 43:
                        tokens.add(new Token(TypedeToken.virgule));
                        break;
                    case 45:
                        tokens.add(new Token(TypedeToken.virgule));
                        break;
                    case 47:
                        tokens.add(new Token(TypedeToken.virgule));
                        break;

                    case 6:
                        tokens.add(new Token(TypedeToken.h));
                        break;

                    case 13:
                        tokens.add(new Token(TypedeToken.t));
                        retourArriere();
                        break;

                    case 23:
                        tokens.add(new Token(TypedeToken.p));
                        break;

                    case 28:
                        tokens.add(new Token(TypedeToken.s));
                        break;

                    case 33:
                        tokens.add(new Token(TypedeToken.f));
                        break;
                }

                // un état d'acceptation ayant été atteint, retourne à l'état 0
                etat = ETAT_INITIAL;
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
            return 0;
        if (Character.isWhitespace(c))
            return 0;
        if (c == '(')
            return 1;
        if (c == ')')
            return 2;
        if (c == ',')
            return 3;
        if (c == 'm')
            return 4;
        if (c == 'h')
            return 5;
        if (c == 't')
            return 6;
        if (c == 'p')
            return 7;
        if (c == 's')
            return 8;
        if (c == 'f')
            return 9;
        if (Character.isDigit(c))
            return 10;
        if (Character.isLetter(c))
            return 11;
        System.out.println("Symbole inconnu : " + c);
        throw new IllegalCharacterException(c.toString());
    }
}