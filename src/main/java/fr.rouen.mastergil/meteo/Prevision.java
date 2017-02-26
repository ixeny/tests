package fr.rouen.mastergil.meteo;


import java.util.Date;
/**
 * Created by rudy on 26/02/17.
 */
public class Prevision {
    private Date date;
    private double tempMin;
    private double tempMax;
    private double tempDay;
    private double tempNight;
    private String description;

    public Date getDate() {
        return date;
    }

    public Prevision setDate(Date date) {
        this.date = date;
        return this;
    }

    public double getTempMin() {
        return tempMin;
    }

    public Prevision setTempMin(double tempMin) {
        this.tempMin = tempMin;
        return this;
    }

    public double getTempMax() {
        return tempMax;
    }

    public Prevision setTempMax(double tempMax) {
        this.tempMax = tempMax;
        return this;
    }

    public double getTempDay() {
        return tempDay;
    }

    public Prevision setTempDay(double tempDay) {
        this.tempDay = tempDay;
        return this;
    }

    public double getTempNight() {
        return tempNight;
    }

    public Prevision setTempNight(double tempNight) {
        this.tempNight = tempNight;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Prevision setDescription(String description) {
        this.description = description;
        return this;
    }
}
