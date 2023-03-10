package Analyse;

import tla.FileReader;

import java.util.List;

public class Test {
    public static void main(String[] args) {
    testAnalyseSyntaxique("c(1,2,3,4)");
    testAnalyseLexicale("v(18,17)");
    }
    private static void testAnalyseLexicale(String entree) {
        System.out.println("test analyse lexicale");
        try {
            List<Token> tokens = new AnalyseLexicale().analyse(entree);
            for (Token t : tokens) {
                System.out.println(t);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        System.out.println();
    }

    private static void testAnalyseSyntaxique(String entree) {
        System.out.println("test analyse syntaxique");
        try {
            List<Token> tokens = new AnalyseLexicale().analyse(entree);
            Noeud res = new AnalyseSyntaxique().analyse(tokens);
            System.out.println("La valeur de l'expression " + entree + " est " + res);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        System.out.println();
    }
}
