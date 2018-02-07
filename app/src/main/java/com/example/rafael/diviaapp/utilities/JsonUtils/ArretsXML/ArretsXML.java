package com.example.rafael.diviaapp.utilities.JsonUtils.ArretsXML;

/**
 * Created by Rafael on 05/02/2018.
 */

public class ArretsXML
{
    private XmldataArret xmldata;

    public XmldataArret getXmldata ()
    {
        return xmldata;
    }

    public void setXmldata (XmldataArret xmldata)
    {
        this.xmldata = xmldata;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [xmldata = "+xmldata+"]";
    }
}
