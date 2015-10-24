package com.example.imran.dhakaweather.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.imran.dhakaweather.R;
import com.example.imran.dhakaweather.weather.Current;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();
    private Current mCurrentWeather;

    TextView mTimeLable, humidityValue, mlocation, mprecipLable, msummerLable, precipValue, tempretureLable;
    ImageView miconimageView, refresh;
    ProgressBar progressBar;


//
//    @Bind(R.id.Timelable)
//    TextView mTimeLable;
//    @Bind(R.id.humidityLable)
//    TextView mhumidityLable;
//    @Bind(R.id.iconimageView)
//    TextView miconimageView;
//    @Bind(R.id.location)
//    TextView mlocation;
//    @Bind(R.id.precipLable)
//    TextView mprecipLable;
//    @Bind(R.id.summerLable)
//    TextView msummerLable;
//    @Bind(R.id.tempretureLable)
    //  TextView MtempretureLable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);


        final double LATITUDE = 23.7805733;
        final double LONGITUDE = 90.279237;

        tempretureLable = (TextView) findViewById(R.id.tempretureLable);
        humidityValue = (TextView) findViewById(R.id.humidityValue);
        mTimeLable = (TextView) findViewById(R.id.Timelable);
        precipValue = (TextView) findViewById(R.id.precipValue);
        miconimageView = (ImageView) findViewById(R.id.iconimageView);
        //   mprecipLable = (TextView) findViewById(R.id.precipLable);
        mlocation = (TextView) findViewById(R.id.location);
        msummerLable = (TextView) findViewById(R.id.summerLable);
        refresh = (ImageView) findViewById(R.id.refreshImageView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);


        progressBar.setVisibility(View.INVISIBLE);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getforecast(LATITUDE, LONGITUDE);
            }
        });
    }

    private void getforecast(double LATITUDE, double LONGITUDE) {
        String apiKeys = "c73f4b46758d0409de27820e355e733b";

        String forcastUrl = "https://api.forecast.io/forecast/" + apiKeys + "/" + LATITUDE + "," + LONGITUDE;

        if (isNetworkAvilable()) {

            getToogleRefresh();


            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(forcastUrl).build();
            Call call = client.newCall(request);

            call.enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            getToogleRefresh();
                        }
                    });
                    alertDialogAboutError();
                }

                @Override
                public void onResponse(Response response) throws IOException {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            getToogleRefresh();
                        }
                    });
                    try {
                        String jsondata = response.body().string();
                        Log.v(TAG, jsondata);
                        if (response.isSuccessful()) {
                            mCurrentWeather = getCurrentDetalis(jsondata);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    UpdateDisplay();
                                }
                            });

                        } else {
                            alertDialogAboutError();
                        }
                    } catch (IOException e) {
                        Log.e(TAG, "Eecption caught : ", e);
                    } catch (JSONException e) {
                        Log.e(TAG, "Eecption caught : ", e);
                    }
                    Log.d(TAG, "APP Running");
                }
            });
        } else {
            Toast.makeText(this, R.string.network_error, Toast.LENGTH_LONG).show();
        }
    }

    private void getToogleRefresh() {
        if (progressBar.getVisibility() == View.INVISIBLE) {
            progressBar.setVisibility(View.VISIBLE);
            refresh.setVisibility(View.INVISIBLE);

        } else {
            progressBar.setVisibility(View.INVISIBLE);
            refresh.setVisibility(View.VISIBLE);

        }
    }

    private void UpdateDisplay() {
        humidityValue.setText(mCurrentWeather.getmHumidity() + "");
        mTimeLable.setText("At" + mCurrentWeather.getFormattedTime() + " ");
        precipValue.setText(mCurrentWeather.getmPrecipChance() + "%");
        //  mprecipLable.setText(mCurrentWeather.getmPrecipChance() + "%");
        msummerLable.setText(mCurrentWeather.getmSummary());

        Drawable drawable = getResources().getDrawable(mCurrentWeather.getIconId());
        miconimageView.setImageDrawable(drawable);
        tempretureLable.setText(mCurrentWeather.getmTeamperature() + "");


    }

    private Current getCurrentDetalis(String jsondata) throws JSONException {
        JSONObject forecase = new JSONObject(jsondata);
        String timezone = forecase.getString("timezone");
        JSONObject currently = forecase.getJSONObject("currently");
        Log.i(TAG, "FROM JSON" + timezone);


        Current current = new Current();
        current.setmHumidity(currently.getDouble("humidity"));
        current.setmTime(currently.getLong("time"));
        current.setIcon(currently.getString("icon"));
        current.setmPrecipChance(currently.getDouble("precipProbability"));
        current.setmSummary(currently.getString("summary"));
        current.setmTeamperature(currently.getDouble("temperature"));
        current.setTimezone(timezone);

        Log.d(TAG, current.getFormattedTime());

        return current;
    }

    private boolean isNetworkAvilable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }
        return isAvailable;
    }

    private void alertDialogAboutError() {

        AlertDialogFragment dialog = new AlertDialogFragment();
        dialog.show(getFragmentManager(), "error Dialog");

    }


}
