package com.example.rafael.diviaapp.utilities;

/**
 * Created by Rafael on 10/01/2018.
 */

public class LignesTransport {

    private String ligneCode;
    private String ligneNom;
    private String ligneSens;
    private String ligneVers;
    private String ligneColor;

    @Override
    public String toString() {
        return "Code='" + ligneCode + '\'' +
                "Nom='" + ligneNom + '\'' +
                "Sens='" + ligneSens + '\'' +
                "Vers='" + ligneVers + '\'' +
                "Color='" + ligneColor + '\''
                ;
    }

    public LignesTransport(String ligneCode, String ligneNom, String ligneSens, String ligneVers, String ligneColor) {
        this.ligneCode = ligneCode;
        this.ligneNom = ligneNom;
        this.ligneSens = ligneSens;
        this.ligneVers = ligneVers;
        this.ligneColor = ligneColor;
    }

    public String getLigneCode() {

        return ligneCode;
    }

    public void setLigneCode(String ligneCode) {
        this.ligneCode = ligneCode;
    }

    public String getLigneNom() {
        return ligneNom;
    }

    public void setLigneNom(String ligneNom) {
        this.ligneNom = ligneNom;
    }

    public String getLigneSens() {
        return ligneSens;
    }

    public void setLigneSens(String ligneSens) {
        this.ligneSens = ligneSens;
    }

    public String getLigneVers() {
        return ligneVers;
    }

    public void setLigneVers(String ligneVers) {
        this.ligneVers = ligneVers;
    }

    public String getLigneColor() {
        return ligneColor;
    }

    public void setLigneColor(String ligneColor) {
        this.ligneColor = ligneColor;
    }
}
