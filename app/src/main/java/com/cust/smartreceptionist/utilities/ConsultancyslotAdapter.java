package com.cust.smartreceptionist.utilities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cust.smartreceptionist.R;

import java.util.ArrayList;

public class ConsultancyslotAdapter extends RecyclerView.Adapter<ConsultancyslotAdapter.ConsultancyslotsViewHolder> {
    private ArrayList<ConsultancyslotItem> consultancyslotitems;
    private Context mContext;
    public static class ConsultancyslotsViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_consultancyslot;

        public ConsultancyslotsViewHolder(View itemView) {
            super(itemView);
            tv_consultancyslot = itemView.findViewById(R.id.tv_consultancyslot);
        }
    }

    public ConsultancyslotAdapter(Context mContext, ArrayList<ConsultancyslotItem> consultancyslotitems) {
        this.consultancyslotitems = consultancyslotitems;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ConsultancyslotsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.consultancyslotitem, viewGroup, false);
        ConsultancyslotsViewHolder consultancyslotsViewHolder = new ConsultancyslotsViewHolder(v);
        return consultancyslotsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ConsultancyslotsViewHolder consultancyslotsViewHolder, int i) {
    ConsultancyslotItem currentItem = consultancyslotitems.get(i);
    consultancyslotsViewHolder.tv_consultancyslot.setText(currentItem.getConsultancyslot());
    }

    @Override
    public int getItemCount() {
        return consultancyslotitems.size();
    }
}
