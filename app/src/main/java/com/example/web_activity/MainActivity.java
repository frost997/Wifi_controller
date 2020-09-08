package com.example.web_activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    TextView speed,ID_current;
    Button get;
    EditText id,key;
    Switch mode_select;
    Button data_list_btn,btn_foward,btn_backward,btn_left,btn_right,btn_set_speed,btn_set_turn_speed,btn_foward_left,btn_foward_right,btn_reverse_left,btn_reverse_right,btn_stop,btn_save,btn_delete;
    ImageButton btn_add;
    String movement = "";
    String mode = "";
    RecyclerView device_list;
    private String Get_ID,Del_ID;
    private ID Get_Item,Del_Item;
    private SeekBar seekBar;
    private ProgressBar progressBar;
    private String ID ;
    private ArrayList<ID> devices;
    private ID_adapter IDAdapter;
    /*   private static final String speed_url = "http://viditu.ddns.net/esp32_phone_speed_data.php";
    private static final String turn_url = "http://viditu.ddns.net/esp32_phone_turn_data.php";
    private static final String move_url = "http://viditu.ddns.net/esp32_phone_movement_data.php";
    private static final String mode_url = "http://viditu.ddns.net/esp32_phone_mode.php";
    private static final String add_url = "http://viditu.ddns.net/esp32_phone_data.php";*/
    private static final String speed_url = "http://viditu.ddns.net/esp32_phone_speed_data.php";
    private static final String turn_url = "http://viditu.ddns.net/esp32_phone_turn_data.php";
    private static final String move_url = "http://viditu.ddns.net/esp32_phone_movement_data.php";
    private static final String mode_url = "http://viditu.ddns.net/esp32_phone_mode.php";
    private static final String add_url = "http://viditu.ddns.net/esp32_phone_data.php";
    private static final String del_url = "http://viditu.ddns.net/esp32_delete.php";
    @SuppressLint("SourceLockedOrientationActivity")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);
        loadData();
        buildRecyclerView();
        initial();
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ID = id.getText().toString().trim();
                insertItem(ID);
                saveData();
            }
        });
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Add_item(add_url);*/
                Add_item(add_url);
            }
        });

        data_list_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), send_data.class);
                startActivityForResult(myIntent, 0);
            }
        });

        mode_select.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mode = "1";
                    On_Update_mode(mode_url);
                }
                else{
                    mode = "0";
                    On_Update_mode(mode_url);
                }
            }
        });
        
        btn_set_turn_speed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                On_Turn(turn_url);
            }
        });
        btn_set_speed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                On_Speed(speed_url);
            }
        });
        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movement ="0";
                On_Move(move_url);
            }
        });
        btn_foward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            movement = "1";
                On_Move(move_url);
            }
        }); //tien
        btn_backward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             movement = "2";
                On_Move(move_url);
            }
        }); //lui
        btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             movement = "3";
                On_Move(move_url);
            }
        }); // trai
        btn_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movement = "4";
                On_Move(move_url);
            }
        }); // phai
        btn_foward_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             movement = "5";
                On_Move(move_url);
            }
        }); //tientrai
        btn_foward_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             movement = "6";
                On_Move(move_url);
            }
        }); //tienphai
        btn_reverse_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             movement = "7";
                On_Move(move_url);
            }
        }); //luitrai
        btn_reverse_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             movement = "8";
                On_Move(move_url);
            }
        }); //luiphai

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressBar.setProgress(progress);
                speed.setText("" + progress );
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(devices);
        editor.putString("task list", json);
        editor.apply();
    }
    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type type = new TypeToken<ArrayList<ID>>() {}.getType();
        devices = gson.fromJson(json, type);
        if (devices == null) {
            devices = new ArrayList<>();
        }
    }
    private void Delete_device(int position){
        devices.remove(position);
        IDAdapter.notifyItemRemoved(position);
        On_delete_confirm(Del_ID);
        saveData();
    }
    private void buildRecyclerView() {
        device_list = findViewById(R.id.recyclerview);
        ID_current= findViewById(R.id.device_running);
        device_list.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        IDAdapter = new ID_adapter(devices);
        device_list.setLayoutManager(mLayoutManager);
        device_list.setAdapter(IDAdapter);
        IDAdapter.setOnItemClickListener(new ID_adapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Get_Item = devices.get(position);
                Get_ID = Get_Item.getDevice_ID();
                ID_current.setText(Get_ID);
            }
            @Override
            public void onDeleteClick(final int position) {
                Del_Item = devices.get(position);
                Del_ID = Del_Item.getDevice_ID();
                AlertDialog.Builder Del_dialog = new AlertDialog.Builder(MainActivity.this);
                Del_dialog.setMessage("the data will be permanently delete from the database, are you sure you want to continue");
                Del_dialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        Delete_device(position);
                        On_Del(del_url);
                    }
                });
                Del_dialog.setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                    }
                });
                Del_dialog.show();
            }
        });

    }
    private void On_delete_confirm(String ID){

    }
    private void Add_item(String url){
        RequestQueue AddQueue = Volley.newRequestQueue(this);
        StringRequest AddRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("api_key", "tPmAT5Ab3j7F9");
                params.put("id", id.getText().toString().trim());
                params.put("Speed", "");
                params.put("Mode", "");
                params.put("Movement", "");
                return params;
            }
        };
        AddQueue.add(AddRequest);


    }
    private void On_Move(String url) {

        RequestQueue MoveQueue = Volley.newRequestQueue(this);
            StringRequest MoveRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                }
            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    })
            {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<>();
                    params.put("api_key", "tPmAT5Ab3j7F9");
                    params.put("id", Get_ID);
                    params.put("Movement", String.valueOf(movement).trim());
                    return params;
                }
            };
        MoveQueue.add(MoveRequest);
    }

    private void On_Update_mode(String url) {

        RequestQueue modeQueue = Volley.newRequestQueue(this);
        StringRequest modeRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("api_key", "tPmAT5Ab3j7F9");
                params.put("id", Get_ID);
                params.put("Mode", String.valueOf(mode).trim());
                Log.d("DDD",mode);
                return params;
            }
        };
        modeQueue.add(modeRequest);
    }
    private void On_Del(String url) {

        RequestQueue DeleteQueue = Volley.newRequestQueue(this);
        StringRequest DeleteRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("api_key", "tPmAT5Ab3j7F9");
                params.put("id", Del_ID);
                Log.d("DDD",Del_ID);
                return params;
            }
        };
        DeleteQueue.add(DeleteRequest);
    }
    private void On_Turn(String url) {
        RequestQueue turnQueue = Volley.newRequestQueue(this);
        StringRequest turnRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("api_key", "tPmAT5Ab3j7F9");
                params.put("id", Get_ID);
                params.put("Set_turn_speed", speed.getText().toString().trim());
                return params;
            }
        };
        turnQueue.add(turnRequest);
    }

    private void On_Speed(String url) {
        RequestQueue speedQueue = Volley.newRequestQueue(this);
        StringRequest speedRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("api_key", "tPmAT5Ab3j7F9");
                params.put("id", Get_ID);
                params.put("Set_speed", speed.getText().toString().trim());
                return params;
            }
        };
        speedQueue.add(speedRequest);
    }
    private void initial(){
        btn_save =findViewById(R.id.save_btn);
        device_list = findViewById(R.id.recyclerview);
        btn_add = findViewById(R.id.add_btn);
        id = findViewById(R.id.ID_command);
        seekBar = findViewById(R.id.seekBar);
        speed = findViewById(R.id.speed);
        progressBar = findViewById(R.id.progressBar);
        btn_foward = findViewById(R.id.forward_btn);
        btn_backward = findViewById(R.id.reverse_btn);
        btn_left = findViewById(R.id.left_btn);
        btn_right = findViewById(R.id.right_btn);
        btn_foward_right = findViewById(R.id.foward_right_btn);
        btn_foward_left = findViewById(R.id.foward_left_btn);
        btn_reverse_right = findViewById(R.id.reverse_right_btn);
        btn_reverse_left = findViewById(R.id.reverse_left_btn);
        data_list_btn = findViewById(R.id.btn_data_list);
        btn_set_speed = findViewById(R.id.set_speed);
        btn_set_turn_speed = findViewById(R.id.set_turn_speed);
        data_list_btn = findViewById(R.id.btn_data_list);
        mode_select = findViewById(R.id.mode_switch);
        btn_stop = findViewById(R.id.stop_btn);
    }
    private void insertItem(String id) {
        devices.add(new ID(id));
        IDAdapter.notifyItemInserted(devices.size());
    }

}