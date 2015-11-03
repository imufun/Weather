package com.example.imran.dhakaweather.weather;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Imran on 10/21/2015.
 */
public class Current {

    private String Icon;
    private long mTime;
    private double mTeamperature;
    private double mHumidity;
    private double mPrecipChance;
    private String mSummary;

    private String timezone;

    public String getIcon() {
        return Icon;
    }

    public int getIconId() {

        return Forecast.getIconId(Icon);

    }

    public void setIcon(String icon) {
        Icon = icon;
    }

    public long getmTime() {
        return mTime;
    }

    public void setmTime(long mTime) {
        this.mTime = mTime;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getFormattedTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("h:m a");
        formatter.setTimeZone(TimeZone.getTimeZone(getTimezone()));

        Date dateTime = new Date(getmTime() * 1000);
        String timeString = formatter.format(dateTime);
        return timeString;


    }


    public int getmTeamperature() {
        return (int) Math.round(mTeamperature);
    }

    public void setmTeamperature(double mTeamperature) {
        this.mTeamperature = mTeamperature;
    }

    public double getmHumidity() {
        return mHumidity;
    }

    public void setmHumidity(double mHumidity) {
        this.mHumidity = mHumidity;
    }

    public int getmPrecipChance() {
        double precipPercentage = mPrecipChance * 100;
        return (int) Math.round(precipPercentage);
    }

    public void setmPrecipChance(double mPrecipChance) {
        this.mPrecipChance = mPrecipChance;
    }

    public String getmSummary() {
        return mSummary;
    }

    public void setmSummary(String mSummary) {
        this.mSummary = mSummary;
    }

}
