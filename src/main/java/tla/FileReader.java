package tla;

import Analyse.*;

import java.io.*;
import java.util.*;

public class FileReader {

    public static String readLevel(File file) throws Exception {
        String INT_Carreau = "";

        Reader reader = new java.io.FileReader(file);
        BufferedReader br = new BufferedReader(reader);


        // m # v " " s *
        String line;
        Character tableau[][] = new Character[14][20];
        while ((line = br.readLine()) != null){
            List<Token> tokens = new AnalyseLexicale().analyse(line);
            Noeud res = new AnalyseSyntaxique().analyse(tokens);
            if(String.valueOf(res).contains("m")){
                line = line.replace("m(", "");
                line = line.replace(")","");
                String[] xy = line.split(",");
                tableau[Integer.parseInt(xy[1])][Integer.parseInt(xy[0])] = '#';
            }else if(String.valueOf(res).contains("v")){
                line = line.replace("v(", "");
                line = line.replace(")","");
                String[] xy = line.split(",");
                tableau[Integer.parseInt(xy[1])][Integer.parseInt(xy[0])] = ' ';
            }else if(String.valueOf(res).contains("s")){
                line = line.replace("s(", "");
                line = line.replace(")","");
                String[] xy = line.split(",");
                tableau[Integer.parseInt(xy[1])][Integer.parseInt(xy[0])] = '*';
            }
        }
        String level = "";
        for (int i = 0; i <= 13; i++) {
            for (int j = 0; j <= 19; j++) {
                level += tableau[i][j];
            }
        }
        br.close();

        return level;
    }

    public static ArrayList<Chrono> readChrono(File file) throws Exception {
        String INT_Carreau = "";

        Reader reader = new java.io.FileReader(file);
        BufferedReader br = new BufferedReader(reader);

        String line;
        ArrayList<Chrono> Test= new ArrayList<Chrono>();
        while ((line = br.readLine()) != null){

            List<Token> tokens = new AnalyseLexicale().analyse(line);
            Noeud res = new AnalyseSyntaxique().analyse(tokens);
            if(String.valueOf(res).contains("h")){
                line = line.replace("h(","");
                line = line.replace(")","");
                String[] xy = line.split(",");
                Test.add(new Chrono(Integer.parseInt(xy[0]), Integer.parseInt(xy[1]),Integer.parseInt(xy[2])));
            }
        }
        br.close();
        return Test;
    }


    public static ArrayList<Trappe> readTrappe(File file) throws Exception {
        String INT_Carreau = "";

        Reader reader = new java.io.FileReader(file);
        BufferedReader br = new BufferedReader(reader);

        String line;
        ArrayList<Trappe> Test= new ArrayList<Trappe>();
        while ((line = br.readLine()) != null){
            List<Token> tokens = new AnalyseLexicale().analyse(line);
            Noeud res = new AnalyseSyntaxique().analyse(tokens);
            if(String.valueOf(res).contains("t")){
                line = line.replace("t(","");
                line = line.replace(")","");
                String[] xy = line.split(",");
                Direction direction = null;

                if(xy[2].startsWith("no")){
                    direction = Direction.HAUT;
                }else if(xy[2].startsWith("ba")){
                    direction = Direction.BAS;
                }else if(xy[2].startsWith("ga")){
                    direction = Direction.GAUCHE;
                }else if(xy[2].startsWith("dr")){
                    direction = Direction.DROITE;
                }else {
                    break;
                }
                Test.add(new Trappe(Integer.parseInt(xy[0]), Integer.parseInt(xy[1]),direction,Integer.parseInt(xy[3]),Integer.parseInt(xy[4])));
            }
        }
        br.close();
        return Test;
    }


    public static ArrayList<Fantome> readFantome(File file) throws Exception {
        String INT_Carreau = "";

        Reader reader = new java.io.FileReader(file);
        BufferedReader br = new BufferedReader(reader);

        String line;
        ArrayList<Fantome> Test = new ArrayList<Fantome>();
        while ((line = br.readLine()) != null) {
            List<Token> tokens = new AnalyseLexicale().analyse(line);
            Noeud res = new AnalyseSyntaxique().analyse(tokens);
            if (String.valueOf(res).contains("f")) {
                line = line.replace("f(", "");
                line = line.replace(")", "");
                String[] xy = line.split(",");
                ArrayList<Direction> parc = new ArrayList<Direction>();
                int i;
                for (i = 0; i < xy[2].length(); i++) {
                    if (Integer.parseInt(String.valueOf(xy[2].charAt(i))) == 1) {
                        parc.add(Direction.HAUT);
                    } else if (Integer.parseInt(String.valueOf(xy[2].charAt(i))) == 2) {
                        parc.add(Direction.BAS);
                    } else if (Integer.parseInt(String.valueOf(xy[2].charAt(i))) == 3) {
                        parc.add(Direction.DROITE);
                    } else if (Integer.parseInt(String.valueOf(xy[2].charAt(i))) == 4) {
                        parc.add(Direction.GAUCHE);
                    }
                }
                Test.add(new Fantome(Integer.parseInt(xy[0]), Integer.parseInt(xy[1]), parc));
            }
        }
        return Test;
    }

    public static ArrayList<Commutateur> readCommu(File file) throws Exception {
        String INT_Carreau = "";

        Reader reader = new java.io.FileReader(file);
        BufferedReader br = new BufferedReader(reader);

        String line;
        ArrayList<Commutateur> Test= new ArrayList<Commutateur>();
        while ((line = br.readLine()) != null){

            List<Token> tokens = new AnalyseLexicale().analyse(line);
            Noeud res = new AnalyseSyntaxique().analyse(tokens);
            if(String.valueOf(res).contains("c")){
                line = line.replace("c(","");
                line = line.replace(")","");
                String[] xy = line.split(",");
                Test.add(new Commutateur(Integer.parseInt(xy[0]), Integer.parseInt(xy[1])));
            }
        }
        return Test;
    }

}
