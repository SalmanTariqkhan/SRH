package com.cust.smartreceptionist.utilities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cust.smartreceptionist.R;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class TimetableAdapter extends RecyclerView.Adapter<TimetableAdapter.TimetableViewHolder> {
    private ArrayList<TimetableItem> timetableitems;
    private Context mContext;

    public static class TimetableViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_itemdate;
        public RecyclerView rv_consultancyslots;

        public TimetableViewHolder(View itemView) {
            super(itemView);
            tv_itemdate = itemView.findViewById(R.id.tv_itemDate);
            rv_consultancyslots = itemView.findViewById(R.id.rv_consultancyslots);
        }
    }

    public TimetableAdapter( Context mContext, ArrayList<TimetableItem> timetableitems) {
        this.timetableitems = timetableitems;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public TimetableViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.timetableitem, viewGroup, false);
        TimetableViewHolder timetableViewHolder = new TimetableViewHolder(v);
        return timetableViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TimetableViewHolder timetableViewHolder, int i) {
    TimetableItem currentItem = timetableitems.get(i);

    timetableViewHolder.tv_itemdate.setText(currentItem.getDate());
    ConsultancyslotAdapter consultancyslotAdapter = new ConsultancyslotAdapter(mContext,currentItem.getConsultancyslotItems());

    timetableViewHolder.rv_consultancyslots.setHasFixedSize(true);
    timetableViewHolder.rv_consultancyslots.setLayoutManager( new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
    timetableViewHolder.rv_consultancyslots.setAdapter(consultancyslotAdapter);
    }

    @Override
    public int getItemCount() {
        return timetableitems.size();
    }
}
