package com.cust.smartreceptionist.Fragments.PatientFragments;

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
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.Navigation;

import com.cust.smartreceptionist.R;
import com.cust.smartreceptionist.api.RetrofitClient;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SigninPatientFragment extends Fragment {
    private EditText et_patient_signin_email,et_patient_signin_pass;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Patient Sign In");
        return inflater.inflate(R.layout.signin_patient,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        TextView signinmessage = (TextView)getView().findViewById(R.id.signinmessage);
        signinmessage.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_signinPatientFragment_to_signupPatientFragment, null));
        et_patient_signin_email = view.findViewById(R.id.et_patient_signin_email);
        et_patient_signin_pass = view.findViewById(R.id.et_patient_signin_pass);
        Button bt_patient_signin = view.findViewById(R.id.bt_Signin);
        bt_patient_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                patientSignin();
                Navigation.findNavController(v).navigate(R.id.action_signinPatientFragment_to_patientMenuFragment);

            }
        });
    }
    private void patientSignin()
    {
        String email = et_patient_signin_email.getText().toString().trim();
        String pass = et_patient_signin_pass.getText().toString().trim();
        if (email.isEmpty()) {
            et_patient_signin_email.setError("Email is required");
            et_patient_signin_email.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            et_patient_signin_email.setError("Enter a valid email");
            et_patient_signin_email.requestFocus();
            return;
        }

        if (pass.isEmpty()) {
            et_patient_signin_pass.setError("Password required");
            et_patient_signin_pass.requestFocus();
            return;
        }
        Call<ResponseBody> call = RetrofitClient
                .getInstance()
                .getApi()
                .loginPatient( email, pass);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getActivity(), response.code(), Toast.LENGTH_LONG).show();
                    return;
                }
                try {

                    String result = response.body().string();
                    Toast.makeText(getActivity(), result, Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

    }
}

