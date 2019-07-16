package com.cust.smartreceptionist.Fragments.DoctorFragments;

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

import com.cust.smartreceptionist.R;

public class DoctorMenuFragment extends Fragment {
    Button bt_doctor_edit_profile,bt_doctor_appointment_history,bt_doctor_consultancy,bt_doctor_viewtimetable;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Doctor Menu");
        return inflater.inflate(R.layout.doctor_menu,container,false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
     bt_doctor_edit_profile = view.findViewById(R.id.bt_doctor_edit_profile);
     bt_doctor_appointment_history = view.findViewById(R.id.bt_doctor_appointment_history);
     bt_doctor_consultancy = view.findViewById(R.id.bt_consultancy);
     bt_doctor_viewtimetable = view.findViewById(R.id.bt_viewtimetable);
     bt_doctor_edit_profile.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_doctorMenuFragment_to_doctorProfileFragment, null));
     bt_doctor_viewtimetable.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_doctorMenuFragment_to_doctorManageTimetableFragment2 , null));


    }

}
