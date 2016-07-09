package com.example.travlr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private GoogleApiClient mGoogleApiClient;
   OkHttpClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        client = new OkHttpClient();

        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    String mockSearch= search("https://maps.googleapis.com/maps/api/place/radarsearch/json?location=48.859294,2.347589&radius=5000&type=cafe&keyword=vegetarian&key=AIzaSyDdfoXP4rLO-Wz4tzXAY0YTQmqpfW20Myg");
                    Log.d("MOCKSEARCH",mockSearch);

                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        };
        Thread t = new Thread(r);
        t.start();

    }
    String search(String url) throws IOException{
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();

    }




}
