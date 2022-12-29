package Analyse;

import java.util.List;

public class Test {
    public static void main(String[] args) {
testAnalyseLexicale("p(16,27)");
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
}
