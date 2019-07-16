package com.cust.smartreceptionist.Fragments;

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

public class TitleFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Title");
        return inflater.inflate(R.layout.title,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        TextView tv_title = (TextView)getView().findViewById(R.id.tv_title);
        tv_title.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_titleFragment_to_chooseUserFragment, null));
    }
}
