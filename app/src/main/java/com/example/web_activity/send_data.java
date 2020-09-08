package com.example.web_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class send_data extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final String get_data_url = "http://viditu.ddns.net/esp32_get_data_list1.php?Name=";
    private String Get_table,name;
    ArrayList<statistic> arrayStatus;
    statistic_adapter adapter;
    ListView lvdata;
    EditText Dev;
    ImageButton add_btn;
    Spinner select_table;
    SwipeRefreshLayout swipeRefresh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_data);
        swipeRefresh = findViewById(R.id.swipeRefresh);
        select_table = findViewById(R.id.switch_table);
        add_btn = findViewById(R.id.add);
        callAsynchronousTask();
        Get_data(get_data_url+Get_table);
        Select_table();
        swipeRefresh.setOnRefreshListener(  new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefresh.setRefreshing(true);
                Get_data(get_data_url+Get_table);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefresh.setRefreshing(false);
                        adapter.notifyDataSetChanged();
                    }
                },4000);
            }
        });
    }


    private void Select_table(){
        ArrayAdapter<CharSequence> switch_adapter = ArrayAdapter.createFromResource(this, R.array.planets_array, android.R.layout.simple_spinner_item);
        switch_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        select_table.setAdapter(switch_adapter);
        select_table.setOnItemSelectedListener(this);
    }
    public void callAsynchronousTask() {
        final Handler handler = new Handler();
        Timer timer = new Timer();
        TimerTask doAsynchronousTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        try {
                            Get_data(get_data_url+Get_table);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        };
        timer.schedule(doAsynchronousTask, 0, 10000); //execute in every 10 sec
    }
    private void Get_data(String url){
        lvdata = findViewById(R.id.list_data);
        arrayStatus = new ArrayList<>();
        adapter = new statistic_adapter(this, R.layout.cusstom_data_format, arrayStatus);
        lvdata.setAdapter(adapter);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i = 0; i < response.length(); i++){
                    try {
                        JSONObject object = response.getJSONObject(i);
                        arrayStatus.add(new statistic(
                                object.getString("Name"),
                                object.getString("Temp"),
                                object.getString("Water_level"),
                                object.getString("reading_time")
                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter.notifyDataSetChanged();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(send_data.this, "error", Toast.LENGTH_LONG).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Get_table = parent.getItemAtPosition(position).toString();
        Get_data(get_data_url+Get_table);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}