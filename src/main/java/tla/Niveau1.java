package tla;

import java.io.File;

class Niveau1 extends Niveau {


    private File file = new File("E:\\NaverSeriesApp\\projet-tla-master\\src\\main\\resources\\lvl1.txt");

    Niveau1() throws Exception {
        INIT_CARREAUX = new FileReader().readLevel(file);

        CHRONOS = new FileReader().readChrono(file);

        TRAPPES = new FileReader().readTrappe(file);

        fantomes = new FileReader().readFantome(file);

        commutateurs = new FileReader().readCommu(file);

    }

    void hookApresDeplacement(Plateau plateau) {
        plateau.carreaux[190].setEtat(commutateurs.get(0).getEtat() ? EtatCarreau.VIDE : EtatCarreau.PORTE_FERMEE);
    }
}