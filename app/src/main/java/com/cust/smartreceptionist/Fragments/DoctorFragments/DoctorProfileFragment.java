package com.cust.smartreceptionist.Fragments.DoctorFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.cust.smartreceptionist.Models.Department;
import com.cust.smartreceptionist.Models.Doctor;
import com.cust.smartreceptionist.Models.SingleDepartmentResponse;
import com.cust.smartreceptionist.R;
import com.cust.smartreceptionist.Storage.SharedPrefManager;
import com.cust.smartreceptionist.api.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DoctorProfileFragment extends Fragment {
    EditText et_doctor_profile_name, et_doctor_profile_email, et_doctor_profile_pno, et_doctor_profile_dob, et_doctor_profile_department;
    Department department;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Doctor Profile");
        return inflater.inflate(R.layout.doctor_profile,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Doctor doctor = SharedPrefManager.getInstance(getActivity()).getDoctor();
        System.out.println(doctor.getDepartment_id());
        et_doctor_profile_name = view.findViewById(R.id.et_doctor_profile_name);
        et_doctor_profile_email = view.findViewById(R.id.et_doctor_profile_email);
        et_doctor_profile_pno = view.findViewById(R.id.et_doctor_profile_pno);
        et_doctor_profile_dob = view.findViewById(R.id.et_doctor_profile_dob);
        et_doctor_profile_department = view.findViewById(R.id.et_doctor_profile_department);

        Call<SingleDepartmentResponse> call = RetrofitClient.getInstance().getApi().getDepartment(doctor.getDepartment_id());
        call.enqueue(new Callback<SingleDepartmentResponse>() {
            @Override
            public void onResponse(Call<SingleDepartmentResponse> call, Response<SingleDepartmentResponse> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getActivity(), response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    SingleDepartmentResponse singleDepartmentResponse = response.body();
                    department = singleDepartmentResponse.getDepartment();
                    et_doctor_profile_department.setText(department.getDepartment_name());
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<SingleDepartmentResponse> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

        et_doctor_profile_dob.setText(doctor.getDOB());
        et_doctor_profile_name.setText(doctor.getFirstname() + " " + doctor.getLastname());
        et_doctor_profile_email.setText(doctor.getEmail());
        et_doctor_profile_pno.setText(doctor.getPhonenumber());
    }
}

