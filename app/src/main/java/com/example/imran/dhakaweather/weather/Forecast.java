package com.example.imran.dhakaweather.weather;

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
}
