package com.cust.smartreceptionist.Fragments.PatientFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.navigation.Navigation;

import com.cust.smartreceptionist.R;


public class FirstTimePatientFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Welcome");
        return inflater.inflate(R.layout.firsttime_patient,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        TextView tv_firsttimessage = getView().findViewById(R.id.tv_firsttimemessage);
        tv_firsttimessage.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_firstTimePatientFragment_to_textAppointmentFragment, null));

    }
}
