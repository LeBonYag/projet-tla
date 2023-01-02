package tla;

import java.util.Date;

public class Chrono {

    private int x;
    private int y;
    private int temps;

    public Chrono(int x, int y, int temps) {
        this.x = x;
        this.y = y;
        this.temps = temps;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getTemps() {
        return temps;
    }

    public void setTemps(int temps) {
        this.temps = temps;
    }
}

