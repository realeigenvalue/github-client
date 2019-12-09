package com.realeigenvalue.github_client.tasks;

import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Base64;
import android.view.View;

import com.realeigenvalue.github_client.R;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

/**
 * Async task to get the repository stats, with authentication
 */
public class GetRepositoryStats extends AsyncTask<Void, Void, Void> {
    private JSONObject json;
    private ArrayList<Integer> data;
    private String URL;
    private String credential;
    private View view;
    private boolean done;

    public GetRepositoryStats(ArrayList<Integer> data, String URL, String credential, View view) {
        this.data = data;
        if(this.data != null) {
            this.data.clear();
        }
        this.URL = URL;
        this.credential = credential;
        this.view = view;
        this.done = false;
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            URL url = new URL(this.URL);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            String authentication = "Basic " + (Base64.encodeToString(credential.getBytes(), Base64.NO_WRAP));
            httpURLConnection.setRequestProperty("Authorization", authentication);
            InputStream stream = httpURLConnection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder builder = new StringBuilder();
            int character;
            while((character = reader.read()) != -1) {
                builder.append((char)character);
            }
            json = new JSONObject(builder.toString());
            done = true;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        PieChart pieChart = (PieChart) this.view.findViewById(R.id.piechart);
        pieChart.clearChart();
        if(done == true) {
            try {
                String list_str = json.getString("all");
                list_str = list_str.replace("[", "");
                list_str = list_str.replace("]", "");
                String[] values = list_str.split(",");
                for (int i = 0; i < values.length; i++) {
                    this.data.add(Integer.parseInt(values[i]));
                }
                Random random = new Random();
                for (int i = 0; i < this.data.size(); i++) {
                    int red = random.nextInt(255);
                    int green = random.nextInt(255);
                    int blue = random.nextInt(255);
                    pieChart.addPieSlice(new PieModel("Week " + (i + 1), data.get(i), Color.rgb(red, green, blue)));
                }
                pieChart.startAnimation();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}