package com.example.imran.dhakaweather.weather;

import com.example.imran.dhakaweather.R;

import java.util.Date;

/**
 * Created by Imran on 10/24/2015.
 */
public class Forecast {

    private Current mcurrent;
    private Hour[] mHourForecast;
    private Date[] mDailyForecast;

    public Current getMcurrent() {
        return mcurrent;
    }

    public void setMcurrent(Current mcurrent) {
        this.mcurrent = mcurrent;
    }

    public Hour[] getmHourForecast() {
        return mHourForecast;
    }

    public void setmHourForecast(Hour[] mHourForecast) {
        this.mHourForecast = mHourForecast;
    }

    public Date[] getmDailyForecastgetmDailyForecast() {
        return mDailyForecast;
    }

    public void setmDailyForecast(Date[] mDailyForecast) {
        this.mDailyForecast = mDailyForecast;
    }

    public void setmDailyForecast(Day[] dailyForecastday) {
    }

    public static int getIconId(String IconString){

        //clear-day, clear-night, rain, snow, sleet, wind, fog, cloudy, partly-cloudy-day, or partly-cloudy-night
        int iconId = R.drawable.clear_day;
        if (IconString.equals("clear-day")) {
            iconId = R.drawable.clear_day;
        } else if (IconString.equals("clear-night")) {
            iconId = R.drawable.clear_night;
        } else if (IconString.equals("rain")) {
            iconId = R.drawable.rain;
        } else if (IconString.equals("sleet")) {
            iconId = R.drawable.sleet;
        } else if (IconString.equals("snow")) {
            iconId = R.drawable.snow;
        } else if (IconString.equals("fog")) {
            iconId = R.drawable.fog;
        } else if (IconString.equals("sunny")) {
            iconId = R.drawable.sunny;
        } else if (IconString.equals("cloudy")) {
            iconId = R.drawable.cloudy;
        } else if (IconString.equals("wind")) {
            iconId = R.drawable.wind;
        } else if (IconString.equals("degree")) {
            iconId = R.drawable.degree;
        } else if (IconString.equals("partly-cloudy-day ")) {
            iconId = R.drawable.partly_cloudy;
        } else if (IconString.equals("partly-cloudy-night")) {
            iconId = R.drawable.cloudy_night;
        }
        return iconId;
    }
}
