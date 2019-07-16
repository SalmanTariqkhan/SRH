package com.cust.smartreceptionist.Fragments.DoctorFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.navigation.Navigation;

import com.cust.smartreceptionist.Models.DoctorSigninResponse;
import com.cust.smartreceptionist.R;
import com.cust.smartreceptionist.api.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SigninDoctorFragment extends Fragment {
    EditText et_doctor_signin_email, et_doctor_signin_pass;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Doctor Sign In");
        return inflater.inflate(R.layout.signin_doctor, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        et_doctor_signin_email = view.findViewById(R.id.et_doctor_signin_email);
        et_doctor_signin_pass = view.findViewById(R.id.et_doctor_signin_pass);
        Button bt_doctor_signin = view.findViewById(R.id.bt_doctor_signin);
        bt_doctor_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doctorSignin();
                Navigation.findNavController(v).navigate(R.id.action_signinDoctorFragment_to_firstTimeDoctorFragment);

            }
        });
    }

    private void doctorSignin() {
        String email = et_doctor_signin_email.getText().toString().trim();
        String pass = et_doctor_signin_pass.getText().toString().trim();
        if (email.isEmpty()) {
            et_doctor_signin_email.setError("Email is required");
            et_doctor_signin_email.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            et_doctor_signin_email.setError("Enter a valid email");
            et_doctor_signin_email.requestFocus();
            return;
        }

        if (pass.isEmpty()) {
            et_doctor_signin_pass.setError("Password required");
            et_doctor_signin_pass.requestFocus();
            return;
        }
        Call<DoctorSigninResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .loginDoctor(email, pass);
        call.enqueue(new Callback<DoctorSigninResponse>() {
            @Override
            public void onResponse(Call<DoctorSigninResponse> call, Response<DoctorSigninResponse> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getActivity(), response.code(), Toast.LENGTH_LONG).show();
                    return;
                }


                DoctorSigninResponse doctorSigninResponse = response.body();
                Toast.makeText(getActivity(), doctorSigninResponse.getMessage(), Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<DoctorSigninResponse> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

    }
}
