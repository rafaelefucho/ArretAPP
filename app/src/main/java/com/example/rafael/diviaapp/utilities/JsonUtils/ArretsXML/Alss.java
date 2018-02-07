package com.example.rafael.diviaapp.utilities.JsonUtils.ArretsXML;

/**
 * Created by Rafael on 05/02/2018.
 */

public class Alss
{
    private String nb;

    private Als[] als;

    public String getNb ()
    {
        return nb;
    }

    public void setNb (String nb)
    {
        this.nb = nb;
    }

    public Als[] getAls ()
    {
        return als;
    }

    public void setAls (Als[] als)
    {
        this.als = als;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [nb = "+nb+", als = "+als+"]";
    }
}
