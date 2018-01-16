package com.example.rafael.diviaapp.utilities.JsonUtils;

/**
 * Created by Rafael on 16/01/2018.
 */

public class Passages
{
    private String nb;

    private Passage[] passage;

    public String getNb ()
    {
        return nb;
    }

    public void setNb (String nb)
    {
        this.nb = nb;
    }

    public Passage[] getPassage ()
    {
        return passage;
    }

    public void setPassage (Passage[] passage)
    {
        this.passage = passage;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [nb = "+nb+", passage = "+passage+"]";
    }
}
