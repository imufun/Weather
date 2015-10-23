package com.example.imran.dhakaweather;

import android.content.Context;
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
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();
    private CurrentWeather mCurrentWeather;

    TextView mTimeLable, mhumidityLable, mlocation, mprecipLable, msummerLable, MtempretureLable;
    ImageView miconimageView;


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

        MtempretureLable = (TextView) findViewById(R.id.tempretureLable);
        mTimeLable = (TextView) findViewById(R.id.Timelable);
        mhumidityLable = (TextView) findViewById(R.id.humidityLable);
        miconimageView = (ImageView) findViewById(R.id.iconimageView);
        mprecipLable = (TextView) findViewById(R.id.precipLable);
        mlocation = (TextView) findViewById(R.id.location);
        msummerLable = (TextView) findViewById(R.id.summerLable);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        String apiKeys = "c73f4b46758d0409de27820e355e733b";
        double LATITUDE = 23.7805733;
        double LONGITUDE = 90.279237;
        String forcastUrl = "https://api.forecast.io/forecast/" + apiKeys + "/" + LATITUDE + "," + LONGITUDE;

        if (isNetworkAvilable()) {

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(forcastUrl).build();
            Call call = client.newCall(request);

            call.enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {


                }

                @Override
                public void onResponse(Response response) throws IOException {
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

    private void UpdateDisplay() {
        MtempretureLable.setText(mCurrentWeather.getmTeamperature() + "");
        mTimeLable.setText("At" + mCurrentWeather.getFormattedTime() + " It will be");
        mhumidityLable.setText(mCurrentWeather.getmHumidity() + "");
        mprecipLable.setText(mCurrentWeather.getmPrecipChance() + "");
        msummerLable.setText(mCurrentWeather.getmSummary());


    }

    private CurrentWeather getCurrentDetalis(String jsondata) throws JSONException {
        JSONObject forecase = new JSONObject(jsondata);
        String timezone = forecase.getString("timezone");
        JSONObject currently = forecase.getJSONObject("currently");
        Log.i(TAG, "FROM JSON" + timezone);


        CurrentWeather currentWeather = new CurrentWeather();
        currentWeather.setmHumidity(currently.getDouble("humidity"));
        currentWeather.setmTime(currently.getLong("time"));
        currentWeather.setIcon(currently.getString("icon"));
        currentWeather.setmPrecipChance(currently.getDouble("precipProbability"));
        currentWeather.setmSummary(currently.getString("summary"));
        currentWeather.setmTeamperature(currently.getDouble("temperature"));
        currentWeather.setTimezone(timezone);

        Log.d(TAG, currentWeather.getFormattedTime());

        return currentWeather;
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
