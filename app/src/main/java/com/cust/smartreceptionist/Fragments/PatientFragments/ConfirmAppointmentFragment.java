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

public class ConfirmAppointmentFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Appointment");
        return inflater.inflate(R.layout.confirm_appointment,container,false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
       Button bt_patientmenu = (Button) getView().findViewById(R.id.bt_patientmenu);
        bt_patientmenu.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_confirmAppointmentFragment_to_patientMenuFragment, null));

    }
}
