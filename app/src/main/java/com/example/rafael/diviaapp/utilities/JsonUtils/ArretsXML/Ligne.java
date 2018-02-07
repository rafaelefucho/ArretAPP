package com.example.rafael.diviaapp.utilities.JsonUtils.ArretsXML;

/**
 * Created by Rafael on 05/02/2018.
 */

public class Ligne
{
    private String couleur;

    private String vers;

    private String code;

    private String sens;

    private String nom;

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

    public String getNom ()
    {
        return nom;
    }

    public void setNom (String nom)
    {
        this.nom = nom;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [couleur = "+couleur+", vers = "+vers+", code = "+code+", sens = "+sens+", nom = "+nom+"]";
    }
}


