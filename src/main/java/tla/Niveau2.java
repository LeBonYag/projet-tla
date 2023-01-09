package tla;

import java.io.File;

class Niveau2 extends Niveau {


    private File file = new File("E:\\NaverSeriesApp\\projet-tla-master\\src\\main\\resources\\lvl2.txt");

    Niveau2() throws Exception {
        INIT_CARREAUX = new FileReader().readLevel(file);

        CHRONOS = new FileReader().readChrono(file);

        TRAPPES = new FileReader().readTrappe(file);

        fantomes = new FileReader().readFantome(file);

        commutateurs = new FileReader().readCommu(file);

    }

    void hookApresDeplacement(Plateau plateau) {
        plateau.carreaux[10 + 0 * Plateau.LARGEUR_PLATEAU]
                .setEtat(commutateurs.get(0).getEtat() ? EtatCarreau.VIDE : EtatCarreau.PORTE_FERMEE);
        plateau.carreaux[2 + 2 * Plateau.LARGEUR_PLATEAU]
                .setEtat(commutateurs.get(1).getEtat() ? EtatCarreau.VIDE : EtatCarreau.PORTE_FERMEE);
        plateau.carreaux[1 + 2 * Plateau.LARGEUR_PLATEAU]
                .setEtat(commutateurs.get(2).getEtat() ? EtatCarreau.VIDE : EtatCarreau.PORTE_FERMEE);
    }
}