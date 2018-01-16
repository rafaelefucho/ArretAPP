package com.example.rafael.diviaapp.utilities.JsonUtils;

/**
 * Created by Rafael on 16/01/2018.
 */

public class Erreur
{
    private String code;

    public String getCode ()
    {
        return code;
    }

    public void setCode (String code)
    {
        this.code = code;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [code = "+code+"]";
    }
}
