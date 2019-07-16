package com.cust.smartreceptionist.Fragments.DoctorFragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.Navigation;

import com.cust.smartreceptionist.Models.DefaultResponse;
import com.cust.smartreceptionist.Models.Department;
import com.cust.smartreceptionist.Models.DepartmentsResponse;
import com.cust.smartreceptionist.R;
import com.cust.smartreceptionist.api.RetrofitClient;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupDoctorFragment extends Fragment {
    EditText et_doctor_fname, et_doctor_lname, et_doctor_dob, et_doctor_pno, et_doctor_email, et_doctor_pass, et_doctor_cpass;
    private RadioGroup radioSexGroup;
    Spinner spin_department;
    int departmentid;
    private RadioButton radioSexButton;
    DatePickerDialog.OnDateSetListener date;
    final Calendar myCalendar = Calendar.getInstance();
    List<Department> departments;
    ProgressBar doctorsignuploading;
    Button bt_register;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Doctor Sign up");
        return inflater.inflate(R.layout.signup_doctor, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        spin_department = view.findViewById(R.id.spin_doctor_department_signup);
//        Call for Departments and implementation of doctor department spinner
        Call<DepartmentsResponse> call = RetrofitClient.getInstance().getApi().getDepartments();
        call.enqueue(new Callback<DepartmentsResponse>() {
            @Override
            public void onResponse(Call<DepartmentsResponse> call, Response<DepartmentsResponse> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getActivity(), response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                DepartmentsResponse data = response.body();
                departments = data.getDepartments();
                ArrayList<String> departmentnames = new ArrayList<>();
                for (Department department : departments) {
                    departmentnames.add(department.getDepartment_name());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                        android.R.layout.simple_list_item_1,
                        departmentnames);
                spin_department.setAdapter(adapter);
                spin_department.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        String departmentName = parent.getItemAtPosition(position).toString();
                        for (Department department : departments) {
                            if (department.getDepartment_name() == departmentName) {
                                departmentid = department.getId();
                            }
                        }
            }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });}


            @Override
            public void onFailure(Call<DepartmentsResponse> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

//        Datepicker implementation

        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };
        et_doctor_dob = view.findViewById(R.id.et_doctor_dob);
        et_doctor_dob.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        et_doctor_fname = view.findViewById(R.id.et_doctor_fname);
        et_doctor_lname = view.findViewById(R.id.et_doctor_lname);
        et_doctor_pno = view.findViewById(R.id.et_doctor_pno);
        et_doctor_email = view.findViewById(R.id.et_doctor_email);
        et_doctor_pass = view.findViewById(R.id.et_doctor_pass);
        et_doctor_cpass = view.findViewById(R.id.et_doctor_cpass);
        doctorsignuploading = view.findViewById(R.id.doctorsignuploading);
        radioSexGroup = view.findViewById(R.id.doctor_radioSex);
        int selectedId = radioSexGroup.getCheckedRadioButtonId();

        // find the radiobutton by returned id
        radioSexButton = view.findViewById(selectedId);


        bt_register = view.findViewById(R.id.bt_doctor_register);
        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doctorSignup();
                Navigation.findNavController(v).navigate(R.id.action_signupDoctorFragment_to_signinDoctorFragment);

            }
        });
        TextView regmessage = view.findViewById(R.id.doctor_regmessage);
        regmessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_signupDoctorFragment_to_signinDoctorFragment);

            }
        });
    }

    // Method for validation and register doctor
    private void doctorSignup() {
        doctorsignuploading.setVisibility(View.VISIBLE);
        bt_register.setVisibility(View.GONE);
       final String fname = et_doctor_fname.getText().toString().trim();
       final String lname = et_doctor_lname.getText().toString().trim();
       final String dob = et_doctor_dob.getText().toString().trim();
       final String pno = et_doctor_pno.getText().toString().trim();
       final String email = et_doctor_email.getText().toString().trim();
       final String pass = et_doctor_pass.getText().toString().trim();
       final String cpass = et_doctor_cpass.getText().toString().trim();
       final String gender = radioSexButton.getText().toString().trim();


        if (fname.isEmpty()) {
            et_doctor_fname.setError("Name required");
            et_doctor_fname.requestFocus();
            return;
        }

        if (pno.isEmpty()) {
            et_doctor_pno.setError("An active phone number  is required");
            et_doctor_pno.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            et_doctor_email.setError("Email is required");
            et_doctor_email.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            et_doctor_email.setError("Enter a valid email");
            et_doctor_email.requestFocus();
            return;
        }

        if (pass.isEmpty()) {
            et_doctor_pass.setError("Password required");
            et_doctor_pass.requestFocus();
            return;
        }

        if (pass.length() < 6) {
            et_doctor_pass.setError("Password should be atleast 6 character long");
            et_doctor_pass.requestFocus();
            return;
        }
        if (!pass.equals(cpass)) {
            et_doctor_cpass.setError("Password doesn't match");
            et_doctor_cpass.requestFocus();
            return;
        }
        Call<DefaultResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .registerDoctor(fname, lname,departmentid, email, pass, cpass, pno, dob, gender);
        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getActivity(), response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }


                DefaultResponse defaultResponse = response.body();
                Toast.makeText(getActivity(), defaultResponse.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void updateLabel() {
        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        et_doctor_dob.setText(sdf.format(myCalendar.getTime()));
    }

}
