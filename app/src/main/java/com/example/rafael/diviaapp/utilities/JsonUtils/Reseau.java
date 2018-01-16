package com.example.rafael.diviaapp.utilities.JsonUtils;

/**
 * Created by Rafael on 16/01/2018.
 */

public class Reseau
{
    private String texte;

    private String titre;

    private String bloquant;

    public String getTexte ()
    {
        return texte;
    }

    public void setTexte (String texte)
    {
        this.texte = texte;
    }

    public String getTitre ()
    {
        return titre;
    }

    public void setTitre (String titre)
    {
        this.titre = titre;
    }

    public String getBloquant ()
    {
        return bloquant;
    }

    public void setBloquant (String bloquant)
    {
        this.bloquant = bloquant;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [texte = "+texte+", titre = "+titre+", bloquant = "+bloquant+"]";
    }
}


