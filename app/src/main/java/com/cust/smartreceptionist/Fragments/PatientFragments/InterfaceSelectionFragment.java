package com.cust.smartreceptionist.Fragments.PatientFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.navigation.Navigation;

import com.cust.smartreceptionist.R;

public class InterfaceSelectionFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Appointment");
        return inflater.inflate(R.layout.interface_selection, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        TextView tv_textinterface;
        ImageView iv_textinterface;
        tv_textinterface = (TextView) getView().findViewById(R.id.tv_textinterface);
        tv_textinterface.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_interfaceSelectionFragment_to_textAppointmentFragment, null));
        iv_textinterface = (ImageView) getView().findViewById(R.id.iv_textinterface);
        iv_textinterface.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_interfaceSelectionFragment_to_textAppointmentFragment, null));
    }
}
