package com.example.smartreceptionist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.navigation.Navigation;

public class SignupPatientFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.signup_patient,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
       Button bt_register = (Button)getView().findViewById(R.id.bt_register);
        bt_register.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_signupPatientFragment_to_emailPhoneVerificationFragment, null));

    }
}
