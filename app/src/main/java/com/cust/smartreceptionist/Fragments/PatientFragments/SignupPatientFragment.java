package com.cust.smartreceptionist.Fragments.PatientFragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.Navigation;

import com.cust.smartreceptionist.Models.DefaultResponse;
import com.cust.smartreceptionist.R;
import com.cust.smartreceptionist.api.RetrofitClient;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupPatientFragment extends Fragment {
    EditText et_fname, et_lname, et_dob, et_pno, et_email, et_pass, et_cpass;
    private RadioGroup radioSexGroup;
    private RadioButton radioSexButton;
    DatePickerDialog.OnDateSetListener date;
    final Calendar myCalendar = Calendar.getInstance();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Patient Sign up");
        return inflater.inflate(R.layout.signup_patient, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {



        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };
        et_dob = view.findViewById(R.id.et_dob);
        et_dob.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(getActivity(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        et_fname = view.findViewById(R.id.et_fname);
        et_lname = view.findViewById(R.id.et_lname);
        et_pno = view.findViewById(R.id.et_pno);
        et_email = view.findViewById(R.id.et_email);
        et_pass = view.findViewById(R.id.et_pass);
        et_cpass = view.findViewById(R.id.et_cpass);
        radioSexGroup = view.findViewById(R.id.radioSex);
        int selectedId = radioSexGroup.getCheckedRadioButtonId();

        // find the radiobutton by returned id
        radioSexButton = view.findViewById(selectedId);


        Button bt_register = view.findViewById(R.id.bt_register);
        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userSignup();
                Navigation.findNavController(v).navigate(R.id.action_signupPatientFragment_to_emailPhoneVerificationFragment);

            }
        });
        TextView regmessage = view.findViewById(R.id.regmessage);
        regmessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_signupPatientFragment_to_signinPatientFragment);

            }
        });
    }

    private void userSignup() {
        String fname = et_fname.getText().toString().trim();
        String lname = et_lname.getText().toString().trim();
        String dob = et_dob.getText().toString().trim();
        String pno = et_pno.getText().toString().trim();
        String email = et_email.getText().toString().trim();
        String pass = et_pass.getText().toString().trim();
        String cpass = et_cpass.getText().toString().trim();
        String gender = radioSexButton.getText().toString().trim();


        if (fname.isEmpty()) {
            et_fname.setError("Name required");
            et_fname.requestFocus();
            return;
        }

        if (pno.isEmpty()) {
            et_pno.setError("An active phone number  is required");
            et_pno.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            et_email.setError("Email is required");
            et_email.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            et_email.setError("Enter a valid email");
            et_email.requestFocus();
            return;
        }

        if (pass.isEmpty()) {
            et_pass.setError("Password required");
            et_pass.requestFocus();
            return;
        }

        if (pass.length() < 6) {
            et_pass.setError("Password should be atleast 6 character long");
            et_pass.requestFocus();
            return;
        }
        if (!pass.equals(cpass)) {
            et_cpass.setError("Password doesn't match");
            et_cpass.requestFocus();
            return;
        }
        Call<DefaultResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .registerPatient(fname, lname, email, pass, cpass, pno, dob, gender);
        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getActivity(), response.code(), Toast.LENGTH_LONG).show();
                    return;
                }


                DefaultResponse defaultResponse = response.body();
                Toast.makeText(getActivity(), defaultResponse.getMessage(), Toast.LENGTH_LONG).show();
            }


            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

    }

    private void updateLabel() {
        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        et_dob.setText(sdf.format(myCalendar.getTime()));
    }

}
