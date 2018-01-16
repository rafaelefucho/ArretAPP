package com.example.rafael.diviaapp.utilities.JsonUtils;

/**
 * Created by Rafael on 16/01/2018.
 */
public class Passage
{
    private String id;

    private String duree;

    private String destination;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getDuree ()
    {
        return duree;
    }

    public void setDuree (String duree)
    {
        this.duree = duree;
    }

    public String getDestination ()
    {
        return destination;
    }

    public void setDestination (String destination)
    {
        this.destination = destination;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", duree = "+duree+", destination = "+destination+"]";
    }
}
