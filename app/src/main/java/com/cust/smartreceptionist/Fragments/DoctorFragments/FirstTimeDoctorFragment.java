package com.cust.smartreceptionist.Fragments.DoctorFragments;

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


public class FirstTimeDoctorFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Welcome");
        return inflater.inflate(R.layout.firsttime_doctor,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        TextView tv_doctor_firsttimessage = (TextView)view.findViewById(R.id.tv_doctor_firsttimemessage);
        tv_doctor_firsttimessage.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_firstTimeDoctorFragment_to_doctorMenuFragment, null));

    }
}

