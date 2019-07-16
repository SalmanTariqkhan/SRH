package com.cust.smartreceptionist.Fragments.PatientFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.navigation.Navigation;

import com.cust.smartreceptionist.R;

public class PatientMenuFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Patient Menu");
        return inflater.inflate(R.layout.patient_menu,container,false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Button bt_appointment_make = (Button) getView().findViewById(R.id.bt_appointment_make);
        bt_appointment_make.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_patientMenuFragment_to_interfaceSelectionFragment, null));
        Button bt_appointment_history = (Button) getView().findViewById(R.id.bt_appointment_history);
        bt_appointment_history.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_patientMenuFragment_to_patientAppointmentHistoryFragment, null));
        Button bt_patient_edit_profile = (Button) getView().findViewById(R.id.bt_patientmenu_edit_profile);
        bt_patient_edit_profile.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_patientMenuFragment_to_patientProfileFragment, null));

    }
}
