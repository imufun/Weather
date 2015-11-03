package com.example.imran.dhakaweather.weather;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Imran on 10/24/2015.
 */
public class Day {

    private long mTime;
    private String mSummary;
    private String mIcon;
    private double mTemperatureMax;
    private String mTimeZone;

    public long getmTime() {
        return mTime;
    }

    public void setmTime(long mTime) {
        this.mTime = mTime;
    }

    public String getmSummary() {
        return mSummary;
    }

    public void setmSummary(String mSummary) {
        this.mSummary = mSummary;
    }

    public String getmIcon() {
        return mIcon;
    }

    public void setmIcon(String mIcon) {
        this.mIcon = mIcon;
    }

    public int getmTemperatureMax() {
        return (int) Math.round(mTemperatureMax);
    }

    public void setmTemperatureMax(double mTemperatureMax) {
        this.mTemperatureMax = mTemperatureMax;
    }

    public String getmTimeZone() {
        return mTimeZone;
    }

    public void setmTimeZone(String mTimeZone) {
        this.mTimeZone = mTimeZone;
    }

    public int getIconId() {
        return Forecast.getIconId(mIcon);
    }

    public String getDayOfTheWeek() {
        SimpleDateFormat formater = new SimpleDateFormat("EEEE");
        formater.setTimeZone(TimeZone.getTimeZone(mTimeZone));
        Date dateTime = new Date(mTime * 1000);
        return formater.format(dateTime);
    }
}
