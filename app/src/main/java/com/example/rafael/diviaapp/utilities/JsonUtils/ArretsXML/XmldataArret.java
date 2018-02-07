package com.example.rafael.diviaapp.utilities.JsonUtils.ArretsXML;

import com.example.rafael.diviaapp.utilities.JsonUtils.Erreur;

/**
 * Created by Rafael on 05/02/2018.
 */

class XmldataArret {
    private String expire;

    private Alss alss;

    private String heure;

    private Erreur erreur;

    private String date;

    public String getExpire ()
    {
        return expire;
    }

    public void setExpire (String expire)
    {
        this.expire = expire;
    }

    public Alss getAlss ()
    {
        return alss;
    }

    public void setAlss (Alss alss)
    {
        this.alss = alss;
    }

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

    @Override
    public String toString()
    {
        return "ClassPojo [expire = "+expire+", alss = "+alss+", heure = "+heure+", erreur = "+erreur+", date = "+date+"]";
    }
}
