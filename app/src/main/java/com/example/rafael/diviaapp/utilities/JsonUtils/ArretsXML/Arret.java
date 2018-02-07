package com.example.rafael.diviaapp.utilities.JsonUtils.ArretsXML;

/**
 * Created by Rafael on 05/02/2018.
 */

public class Arret
{
    private String code;

    private String nom;

    public String getCode ()
    {
        return code;
    }

    public void setCode (String code)
    {
        this.code = code;
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
        return "ClassPojo [code = "+code+", nom = "+nom+"]";
    }
}
