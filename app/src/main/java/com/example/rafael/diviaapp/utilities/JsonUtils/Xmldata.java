package com.example.rafael.diviaapp.utilities.JsonUtils;

/**
 * Created by Rafael on 16/01/2018.
 */

public class Xmldata
{
    private String heure;

    private Erreur erreur;

    private String date;

    private Horaires horaires;

    private Reseau reseau;

    public String getHeure ()
    {
        return heure;
    }

    public void setHeure (String heure)
    {
        this.heure = heure;
    }

    public Erreur getErreur ()
    {
        return erreur;
    }

    public void setErreur (Erreur erreur)
    {
        this.erreur = erreur;
    }

    public String getDate ()
    {
        return date;
    }

    public void setDate (String date)
    {
        this.date = date;
    }

    public Horaires getHoraires ()
    {
        return horaires;
    }

    public void setHoraires (Horaires horaires)
    {
        this.horaires = horaires;
    }

    public Reseau getReseau ()
    {
        return reseau;
    }

    public void setReseau (Reseau reseau)
    {
        this.reseau = reseau;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [heure = "+heure+", erreur = "+erreur+", date = "+date+", horaires = "+horaires+", reseau = "+reseau+"]";
    }
}
