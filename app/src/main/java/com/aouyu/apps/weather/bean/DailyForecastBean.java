package com.aouyu.apps.weather.bean;

import java.io.Serializable;

/**
 * Created by fangxiaotian on 2016/12/23.
 */

public class DailyForecastBean implements Serializable {

    /**
     * astro : {"mr":"03:09","ms":"17:06","sr":"05:28","ss":"18:29"}
     * cond : {"code_d":"100","code_n":"100","txt_d":"晴","txt_n":"晴"}
     * date : 2016-08-30
     * hum : 45
     * pcpn : 0.0
     * pop : 8
     * pres : 1005
     * tmp : {"max":"29","min":"22"}
     * vis : 10
     * wind : {"deg":"339","dir":"北风","sc":"4-5","spd":"24"}
     */

    private AstroBean astro;
    private CondBean cond;
    private String date;
    private String hum;
    private String pcpn;
    private String pop;
    private String pres;
    private TmpBean tmp;
    private String vis;
    private WindBean wind;

    public AstroBean getAstro() {
        return astro;
    }

    public void setAstro(AstroBean astro) {
        this.astro = astro;
    }

    public CondBean getCond() {
        return cond;
    }

    public void setCond(CondBean cond) {
        this.cond = cond;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHum() {
        return hum;
    }

    public void setHum(String hum) {
        this.hum = hum;
    }

    public String getPcpn() {
        return pcpn;
    }

    public void setPcpn(String pcpn) {
        this.pcpn = pcpn;
    }

    public String getPop() {
        return pop;
    }

    public void setPop(String pop) {
        this.pop = pop;
    }

    public String getPres() {
        return pres;
    }

    public void setPres(String pres) {
        this.pres = pres;
    }

    public TmpBean getTmp() {
        return tmp;
    }

    public void setTmp(TmpBean tmp) {
        this.tmp = tmp;
    }

    public String getVis() {
        return vis;
    }

    public void setVis(String vis) {
        this.vis = vis;
    }

    public WindBean getWind() {
        return wind;
    }

    public void setWind(WindBean wind) {
        this.wind = wind;
    }

    @Override
    public String toString() {
        return "DailyForecastBean{" +
                "astro=" + astro +
                ", cond=" + cond +
                ", date='" + date + '\'' +
                ", hum='" + hum + '\'' +
                ", pcpn='" + pcpn + '\'' +
                ", pop='" + pop + '\'' +
                ", pres='" + pres + '\'' +
                ", tmp=" + tmp +
                ", vis='" + vis + '\'' +
                ", wind=" + wind +
                '}';
    }
}
