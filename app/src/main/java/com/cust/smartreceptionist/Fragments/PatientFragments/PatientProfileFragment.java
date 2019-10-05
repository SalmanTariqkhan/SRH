package com.cust.smartreceptionist.Fragments.PatientFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.cust.smartreceptionist.Models.Patient;
import com.cust.smartreceptionist.R;
import com.cust.smartreceptionist.Storage.SharedPrefManager;


public class PatientProfileFragment extends Fragment {
    EditText et_patient_profile_name, et_patient_profile_email, et_patient_profile_pno, et_patient_profile_dob;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Welcome");
        return inflater.inflate(R.layout.patient_profile,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Patient patient = SharedPrefManager.getInstance(getActivity()).getPatient();
        et_patient_profile_name = view.findViewById(R.id.et_patient_profile_name);
        et_patient_profile_email = view.findViewById(R.id.et_patient_profile_email);
        et_patient_profile_pno = view.findViewById(R.id.et_patient_profile_pno);
        et_patient_profile_dob = view.findViewById(R.id.et_patient_profile_dob);
        et_patient_profile_dob.setText(patient.getDOB());
        et_patient_profile_name.setText(patient.getFirstname() + "" + patient.getLastname());
        et_patient_profile_email.setText(patient.getEmail());
        et_patient_profile_pno.setText(patient.getPhonenumber());

    }
}

