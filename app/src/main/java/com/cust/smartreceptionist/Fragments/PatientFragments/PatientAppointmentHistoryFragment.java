package com.cust.smartreceptionist.Fragments.PatientFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cust.smartreceptionist.utilities.AppointmentHistoryAdapter;
import com.cust.smartreceptionist.utilities.AppointmentHistoryItem;
import com.cust.smartreceptionist.R;

import java.util.ArrayList;

public class PatientAppointmentHistoryFragment extends Fragment {
   private RecyclerView mRecyclerView;
   private RecyclerView.Adapter mAdapter;
   private RecyclerView.LayoutManager mLayoutManager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Appointment History");
        View view = inflater.inflate(R.layout.patient_appointment_history,container,false);
        ArrayList<AppointmentHistoryItem> appointmentHistoryItems = new ArrayList<>();
        appointmentHistoryItems.add( new AppointmentHistoryItem("Appointment 1","Dr. Salman","stak","Monday 1-july-2019"));
        appointmentHistoryItems.add( new AppointmentHistoryItem("Appointment 2","Dr. Shaheryar","stak","Tuesday 2-july-2019"));
        appointmentHistoryItems.add( new AppointmentHistoryItem("Appointment 3","Dr. Siddique","stak","Wednesday 3-july-2019"));
        appointmentHistoryItems.add( new AppointmentHistoryItem("Appointment 4","Dr. Ahmed","stak","Thursday 4-july-2019"));
        mRecyclerView = view.findViewById(R.id.rv_appointmenthistory);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new AppointmentHistoryAdapter(appointmentHistoryItems);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }
}
