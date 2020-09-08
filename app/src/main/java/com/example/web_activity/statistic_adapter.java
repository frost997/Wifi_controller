package com.example.web_activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class statistic_adapter extends BaseAdapter  {
    public statistic_adapter(Context context, int layout, List<statistic> datalist) {
        this.context = context;
        Layout = layout;
        this.datalist = datalist;

    }

    private Context context;
    private int Layout;
    private List<statistic> datalist;

    @Override
    public int getCount() {
        return datalist.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    private class ViewHolder{
        TextView id,water_level,gx,gy,gz,time,temp;
    }
    @SuppressLint("SetTextI18n")
    @Override
    public View getView (int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if ( view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(Layout,null);
            holder.id = view.findViewById(R.id.data_id);
            holder.temp = view.findViewById(R.id.temp);
            holder.water_level = view.findViewById(R.id.data_water_level);
            holder.time = view.findViewById(R.id.Time_stamp);
            view.setTag(holder);
        }
        else{
            holder = (ViewHolder) view.getTag();
        }
        statistic status = datalist.get(position);
        holder.id.setText("ID:" + status.getName());
        holder.temp.setText("Temp:" + status.getTemp());
        holder.water_level.setText("Water:" + status.getWater_level());
        holder.time.setText("Time:" + status.getTime_stamp());

    return view;
    }
}
