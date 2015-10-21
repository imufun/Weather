package com.example.imran.dhakaweather;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Imran on 10/21/2015.
 */
public class CurrentWeather {

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

        //clear-day, clear-night, rain, snow, sleet, wind, fog, cloudy, partly-cloudy-day, or partly-cloudy-night
        int iconId = R.drawable.clear_day;
        if (Icon.equals("clear-day")) {
            iconId = R.drawable.clear_day;
        } else if (Icon.equals("clear-night")) {
            iconId = R.drawable.clear_night;
        } else if (Icon.equals("rain")) {
            iconId = R.drawable.rain;
        } else if (Icon.equals("sleet")) {
            iconId = R.drawable.sleet;
        } else if (Icon.equals("snow")) {
            iconId = R.drawable.snow;
        } else if (Icon.equals("fog")) {
            iconId = R.drawable.fog;
        } else if (Icon.equals("sunny")) {
            iconId = R.drawable.sunny;
        } else if (Icon.equals("cloudy")) {
            iconId = R.drawable.cloudy;
        } else if (Icon.equals("wind")) {
            iconId = R.drawable.wind;
        } else if (Icon.equals("degree")){
            iconId =R.drawable.degree;
        }else if (Icon.equals("partly-cloudy-day ")){
            iconId =R.drawable.partly_cloudy;
        }else if (Icon.equals("partly-cloudy-night")){
            iconId =R.drawable.cloudy_night;
        }
        return iconId;
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


    public double getmTeamperature() {
        return mTeamperature;
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

    public double getmPrecipChance() {
        return mPrecipChance;
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
