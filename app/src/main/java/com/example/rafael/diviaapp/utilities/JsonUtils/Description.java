package com.example.rafael.diviaapp.utilities.JsonUtils;

/**
 * Created by Rafael on 16/01/2018.
 */

public class Description
{
    private String ligne_nom;

    private String ligne;

    private String couleur;

    private String vers;

    private String code;

    private String sens;

    private String arret;

    public String getLigne_nom ()
    {
        return ligne_nom;
    }

    public void setLigne_nom (String ligne_nom)
    {
        this.ligne_nom = ligne_nom;
    }

    public String getLigne ()
    {
        return ligne;
    }

    public void setLigne (String ligne)
    {
        this.ligne = ligne;
    }

    public String getCouleur ()
    {
        return couleur;
    }

    public void setCouleur (String couleur)
    {
        this.couleur = couleur;
    }

    public String getVers ()
    {
        return vers;
    }

    public void setVers (String vers)
    {
        this.vers = vers;
    }

    public String getCode ()
    {
        return code;
    }

    public void setCode (String code)
    {
        this.code = code;
    }

    public String getSens ()
    {
        return sens;
    }

    public void setSens (String sens)
    {
        this.sens = sens;
    }

    public String getArret ()
    {
        return arret;
    }

    public void setArret (String arret)
    {
        this.arret = arret;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [ligne_nom = "+ligne_nom+", ligne = "+ligne+", couleur = "+couleur+", vers = "+vers+", code = "+code+", sens = "+sens+", arret = "+arret+"]";
    }
}
