package com.example.rafael.diviaapp.utilities.JsonUtils;

/**
 * Created by Rafael on 16/01/2018.
 */

public class Horaires
{
    private String nb;

    private Horaire horaire;

    public String getNb ()
    {
        return nb;
    }

    public void setNb (String nb)
    {
        this.nb = nb;
    }

    public Horaire getHoraire ()
    {
        return horaire;
    }

    public void setHoraire (Horaire horaire)
    {
        this.horaire = horaire;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [nb = "+nb+", horaire = "+horaire+"]";
    }
}

