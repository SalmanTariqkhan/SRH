package com.example.smartreceptionist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.navigation.Navigation;

public class TextAppointmentFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.text_appointment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Button bt_appointmentschedule = (Button) getView().findViewById(R.id.bt_appointmentschedule);
        bt_appointmentschedule.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_textAppointmentFragment_to_textAppointmentTimeslotsFragment, null));
    }
}
