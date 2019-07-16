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

public class EmailPhoneVerificationFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Verification");
        return inflater.inflate(R.layout.email_phoneno_verification,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Button bt_verify_submit = (Button)getView().findViewById(R.id.bt_verify_submit);
        bt_verify_submit.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_emailPhoneVerificationFragment_to_firstTimePatientFragment, null));
    }
}
