package Analyse;

import java.util.ArrayList;
import java.util.List;

public class AnalyseLexicale {

    private static Integer TRANSITIONS[][] = {
            // espace       m      h     t      p      s     f   chard  intval     (      )      ,
            {        0 ,    1 ,    6 ,  13 ,   23 ,   28 ,  33 ,     0 ,     0,    0 ,    0 ,    0 },
            {     null , null , null, null , null , null, null ,  null ,  null,    2 , null , null },
            {     null , null , null, null , null , null, null ,  null ,     3,  null, null , null },
            {     null , null , null, null , null , null, null ,  null ,     3,  null, null ,    4 },
            {     null , null , null, null , null , null, null ,  null ,     5,  null, null , null },
            {     null , null , null, null , null , null, null ,  null ,     5,  null,  101 , null },
            {     null , null , null, null , null , null, null ,  null ,  null,     7, null , null },
            {     null , null , null, null , null , null, null ,  null ,     8,  null, null , null },
            {     null , null , null, null , null , null, null ,  null ,     8,  null, null ,    9 },
            {     null , null , null, null , null , null, null ,  null ,    10,  null, null , null },
            {     null , null , null, null , null , null, null ,  null ,    10,  null, null ,   11 },
            {     null , null , null, null , null , null, null ,  null ,    12,  null, null , null },
            {     null , null , null, null , null , null, null ,  null ,  null,  null,  102 , null },
            {     null , null , null, null , null , null, null ,  null ,  null,    14, null , null },
            {     null , null , null, null , null , null, null ,  null ,    15,  null, null , null },
            {     null , null , null, null , null , null, null ,  null ,    15,  null, null ,   16 },
            {     null , null , null, null , null , null, null ,  null ,    17,  null, null , null },
            {     null , null , null, null , null , null, null ,    18 ,  null,  null, null , null },
            {     null , null , null, null , null , null, null ,  null ,  null,  null, null ,   19 },
            {     null , null , null, null , null , null, null ,  null ,    20,  null, null , null },
            {     null , null , null, null , null , null, null ,  null ,    20,  null, null ,   21 },
            {     null , null , null, null , null , null, null ,  null ,    22,  null, null , null },
            {     null , null , null, null , null , null, null ,  null ,    22,  null,  103 , null },
            {     null , null , null, null , null , null, null ,  null ,  null,    24, null , null },
            {     null , null , null, null , null , null, null ,  null ,    25,  null, null , null },
            {     null , null , null, null , null , null, null ,  null ,    25,  null, null ,   26 },
            {     null , null , null, null , null , null, null ,  null ,    27,  null, null , null },
            {     null , null , null, null , null , null, null ,  null ,    27,  null,  104 , null },
            {     null , null , null, null , null , null, null ,  null ,  null,    29, null , null },
            {     null , null , null, null , null , null, null ,  null ,    30,  null, null , null },
            {     null , null , null, null , null , null, null ,  null ,    30,  null, null ,   31 },
            {     null , null , null, null , null , null, null ,  null ,    32,  null, null , null },
            {     null , null , null, null , null , null, null ,  null ,    32,  null,  105 , null },
            {     null , null , null, null , null , null, null ,  null ,  null,    34, null , null },
            {     null , null , null, null , null , null, null ,  null ,    35,  null, null , null },
            {     null , null , null, null , null , null, null ,  null ,    35,  null, null ,   36 },
            {     null , null , null, null , null , null, null ,  null ,    37,  null, null , null },
            {     null , null , null, null , null , null, null ,  null ,    37,  null, null ,   38 },
            {     null , null , null, null , null , null, null ,    39 ,  null,  null, null , null },
            {     null , null , null, null , null , null, null ,  null ,  null,  null,  106 , null },
            {     null , null , null, null , null , null, null ,  null ,  null,    41, null , null },
            {     null , null , null, null , null , null, null ,  null ,    42,  null, null , null },
            {     null , null , null, null , null , null, null ,  null ,    42,  null, null ,   43 },
            {     null , null , null, null , null , null, null ,  null ,    44,  null, null , null },
            {     null , null , null, null , null , null, null ,  null ,    44,  null, null ,   45 },
            {     null , null , null, null , null , null, null ,  null ,    46,  null, null , null },
            {     null , null , null, null , null , null, null ,  null ,    46,  null, null ,   47 },
            {     null , null , null, null , null , null, null ,  null ,    48,  null, null , null },
            {     null , null , null, null , null , null, null ,  null ,    48,  null,  107 , null },


    };

    private String entree;
    private int pos;

    private static final int ETAT_INITIAL = 0;

    /*
    effectue l'analyse lexicale et retourne une liste de Token
     */
    public List<Token> analyse(String entree) throws Exception {

        this.entree=entree;
        pos = 0;

        List<Token> tokens = new ArrayList<>();

		/* copie des symboles en entrée
		- permet de distinguer les mots-clés des identifiants
		- permet de conserver une copie des valeurs particulières des tokens de type ident et intval
		 */
        String buf = "";

        Integer etat = ETAT_INITIAL;

        Character c;
        do {
            c = lireCaractere();
            Integer e = TRANSITIONS[etat][indiceSymbole(c)];
            if (e == null) {
                System.out.println("pas de transition depuis état " + etat + " avec symbole " + c);
                throw new LexicalErrorException("pas de transition depuis état " + etat + " avec symbole " + c);
            }
            // cas particulier lorsqu'un état d'acceptation est atteint
            if (e >= 1) {
                if (e == 101||e == 102||e == 103||e == 104||e == 105||e == 106||e == 107) {
                    tokens.add(new Token(TypedeToken.ParD));
                } else if (e == 3||e == 5||e == 8 ||e == 10||e == 12||e == 15||e == 17||e == 20||e == 22||e == 25||e == 27||e == 30||e == 32||e == 35||e == 37||e == 42||e == 44||e == 46||e == 48  ) {
                    tokens.add(new Token(TypedeToken.intval,buf));
                } else if () {
                    tokens.add(new Token(TypedeToken.ParD));
                } else if () {
                    tokens.add(new Token(TypedeToken.ParD));
                } else if () {
                    tokens.add(new Token(TypedeToken.ParD));
                } else if () {
                    tokens.add(new Token(TypedeToken.ParD));
                    retourArriere();
                } else if () {
                    tokens.add(new Token(TypedeToken.ParD));

                }
                // un état d'acceptation ayant été atteint, retourne à l'état 0
                etat = 0;
                // reinitialise buf
                buf = "";
            } else {
                // enregistre le nouvel état
                etat = e;
                // ajoute le symbole qui vient d'être examiné à buf
                // sauf s'il s'agit un espace ou assimilé
                if (etat>0) buf = buf + c;
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
	Pour chaque symbole terminal acceptable en entrée de l'analyse syntaxique
	retourne un indice identifiant soit un symbole, soit une classe de symbole :
	 */
    private static int indiceSymbole(Character c) throws IllegalCharacterException {
        if (c == null) return 0;
        if (Character.isWhitespace(c)) return 0;
        if (c == '(') return 1;
        if (c == ')') return 2;
        if (c == ',') return 3;
        if (c == 'm') return 4;
        if (c == 'h') return 5;
        if (c == 't') return 6;
        if (c == 'p') return 7;
        if (c == 's') return 8;
        if (c == 'f') return 9;
        if (Character.isDigit(c)) return 10;
        if (Character.isLetter(c)) return 11;
        System.out.println("Symbole inconnu : " + c);
        throw new IllegalCharacterException(c.toString());
    }
}