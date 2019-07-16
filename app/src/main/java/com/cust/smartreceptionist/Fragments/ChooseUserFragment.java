package com.cust.smartreceptionist.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.navigation.Navigation;

import com.cust.smartreceptionist.R;

public class ChooseUserFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Choose User");
        return inflater.inflate(R.layout.choose_user,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ImageView iv_patient = (ImageView)getView().findViewById(R.id.iv_patient);
        iv_patient.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_chooseUserFragment_to_signupPatientFragment, null));
        ImageView iv_doctor = (ImageView)getView().findViewById(R.id.iv_doctor);
        iv_doctor.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_chooseUserFragment_to_signupDoctorFragment, null));

    }
}
