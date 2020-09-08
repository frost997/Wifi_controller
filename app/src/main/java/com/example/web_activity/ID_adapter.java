package com.example.web_activity;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ID_adapter extends RecyclerView.Adapter<ID_adapter.ExampleViewHolder> {


    private OnItemClickListener mlistener;
    public ArrayList<ID> IDList;
    public interface OnItemClickListener{
        void onItemClick(int position);
        void onDeleteClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        mlistener = listener;
    }
    public static class ExampleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        public TextView id;
        private Button btn_delete;
        public ExampleViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            id = itemView.findViewById(R.id.id);
            btn_delete = itemView.findViewById(R.id.delete_btn);
            btn_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition(); // gets item position
                        if (position != RecyclerView.NO_POSITION) { // Check if an item was deleted, but the user clicked it before the UI removed it
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition(); // gets item position
                        if (position != RecyclerView.NO_POSITION) { // Check if an item was deleted, but the user clicked it before the UI removed it
                        listener.onItemClick(position);
                    }
                    }
                }
            });
        }
        @Override
        public void onClick(View v) {

        }

        @Override
        public boolean onLongClick(View v) {
            return true;
        }
    }

    public ID_adapter(ArrayList<ID> exampleList) {
        IDList = exampleList;
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_id, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v,mlistener);
        return evh;
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        ID currentItem = IDList.get(position);
        holder.id.setText(currentItem.getDevice_ID());
    }

    @Override
    public int getItemCount() {
        return IDList.size();
    }
}