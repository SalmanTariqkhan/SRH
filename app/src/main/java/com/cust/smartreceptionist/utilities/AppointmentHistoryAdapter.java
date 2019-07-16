package com.cust.smartreceptionist.utilities;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cust.smartreceptionist.R;

import java.util.ArrayList;

public class AppointmentHistoryAdapter extends RecyclerView.Adapter<AppointmentHistoryAdapter.AppointmentHistoryViewHolder> {
    private ArrayList<AppointmentHistoryItem> appointmentHistoryItems;

    public static class AppointmentHistoryViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_itemtitle, tv1_value, tv2_value, tv3_value;

        public AppointmentHistoryViewHolder(View itemView) {
            super(itemView);
            tv_itemtitle = itemView.findViewById(R.id.tv_itemtitle);
            tv1_value = itemView.findViewById(R.id.tv1_value);
            tv2_value = itemView.findViewById(R.id.tv2_value);
            tv3_value = itemView.findViewById(R.id.tv3_value);
        }
    }

    public AppointmentHistoryAdapter(ArrayList<AppointmentHistoryItem> appointmentHistoryItems) {
        this.appointmentHistoryItems = appointmentHistoryItems;
    }

    @NonNull
    @Override
    public AppointmentHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.appointmenthistoryitem, viewGroup, false);
        AppointmentHistoryViewHolder appointmentHistoryViewHolder = new AppointmentHistoryViewHolder(v);
        return appointmentHistoryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentHistoryViewHolder appointmentHistoryViewHolder, int i) {
    AppointmentHistoryItem currentItem = appointmentHistoryItems.get(i);
    appointmentHistoryViewHolder.tv_itemtitle.setText(currentItem.getTv_itemtitle());
    appointmentHistoryViewHolder.tv1_value.setText(currentItem.getTv1_value());
    appointmentHistoryViewHolder.tv2_value.setText(currentItem.getTv2_value());
    appointmentHistoryViewHolder.tv3_value.setText(currentItem.getTv3_value());
    }

    @Override
    public int getItemCount() {
        return appointmentHistoryItems.size();
    }
}
