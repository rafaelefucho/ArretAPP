package com.example.rafael.diviaapp.utilities;

/**
 * Created by Rafael on 10/01/2018.
 */

public class ArretsTransport {

    private String arretCode;
    private String arretNom;
    private String arretRefs;
    private String arretLineCode;
    private String arretLineSens;
    private String arretVersSens;

    public String getArretVersSens() {
        return arretVersSens;
    }

    public void setArretVersSens(String arretVersSens) {
        this.arretVersSens = arretVersSens;
    }

    @Override
    public String toString() {
        return "ArretsTransport{" +
                ", arretCode='" + arretCode + '\'' +
                ", arretNom='" + arretNom + '\'' +
                ", arretRefs='" + arretRefs + '\'' +
                ", arretLineCode='" + arretLineCode + '\'' +
                ", arretLineSens='" + arretLineSens + '\'' +
                '}';
    }

    public ArretsTransport(String arretCode, String arretNom, String arretRefs, String arretLineCode, String arretLineSens, String arretVersSens) {
        this.arretCode = arretCode;
        this.arretNom = arretNom;
        this.arretRefs = arretRefs;
        this.arretLineCode = arretLineCode;
        this.arretLineSens = arretLineSens;
        this.arretVersSens = arretVersSens;
    }

    public String getArretCode() {
        return arretCode;
    }

    public void setArretCode(String arretCode) {
        this.arretCode = arretCode;
    }

    public String getArretNom() {
        return arretNom;
    }

    public void setArretNom(String arretNom) {
        this.arretNom = arretNom;
    }

    public String getArretRefs() {
        return arretRefs;
    }

    public void setArretRefs(String arretRefs) {
        this.arretRefs = arretRefs;
    }

    public String getArretLineCode() {
        return arretLineCode;
    }

    public void setArretLineCode(String arretLineCode) {
        this.arretLineCode = arretLineCode;
    }

    public String getArretLineSens() {
        return arretLineSens;
    }

    public void setArretLineSens(String arretLineSens) {
        this.arretLineSens = arretLineSens;
    }
}
