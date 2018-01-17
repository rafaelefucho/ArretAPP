package com.example.rafael.diviaapp.utilities.JsonUtils;

/**
 * Created by Rafael on 17/01/2018.
 */

public class Horaire
{
    private String id;

    private Passages passages;

    private Description description;

    private Messages messages;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public Passages getPassages ()
    {
        return passages;
    }

    public void setPassages (Passages passages)
    {
        this.passages = passages;
    }

    public Description getDescription ()
    {
        return description;
    }

    public void setDescription (Description description)
    {
        this.description = description;
    }

    public Messages getMessages ()
    {
        return messages;
    }

    public void setMessages (Messages messages)
    {
        this.messages = messages;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", passages = "+passages+", description = "+description+", messages = "+messages+"]";
    }
}


