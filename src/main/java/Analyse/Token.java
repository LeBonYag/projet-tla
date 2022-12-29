package Analyse;

public class Token {

    private TypedeToken typeDeToken;
    private String valeur;

    public Token(TypedeToken typeDeToken, String value) {
        this.typeDeToken=typeDeToken;
        this.valeur=value;
    }

    public Token(TypedeToken typeDeToken) {
        this.typeDeToken=typeDeToken;
    }

    public TypedeToken getTypeDeToken() {
        return typeDeToken;
    }

    public String getValeur() {
        return valeur;
    }

    public String toString() {
        String res = typeDeToken.toString();
        if (valeur != null) res = res + "(" + valeur + ")";
        return res;
    }
}
