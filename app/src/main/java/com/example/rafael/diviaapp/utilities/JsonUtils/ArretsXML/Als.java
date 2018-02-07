package com.example.rafael.diviaapp.utilities.JsonUtils.ArretsXML;

/**
 * Created by Rafael on 05/02/2018.
 */

public class Als
{
    private String id;

    private String refs;

    private Ligne ligne;

    private Arret arret;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getRefs ()
    {
        return refs;
    }

    public void setRefs (String refs)
    {
        this.refs = refs;
    }

    public Ligne getLigne ()
    {
        return ligne;
    }

    public void setLigne (Ligne ligne)
    {
        this.ligne = ligne;
    }

    public Arret getArret ()
    {
        return arret;
    }

    public void setArret (Arret arret)
    {
        this.arret = arret;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", refs = "+refs+", ligne = "+ligne+", arret = "+arret+"]";
    }
}


