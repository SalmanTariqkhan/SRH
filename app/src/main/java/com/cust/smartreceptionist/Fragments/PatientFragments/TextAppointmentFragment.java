package com.cust.smartreceptionist.Fragments.PatientFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.navigation.Navigation;

import com.cust.smartreceptionist.Models.Department;
import com.cust.smartreceptionist.Models.DepartmentDoctorsResponse;
import com.cust.smartreceptionist.Models.DepartmentsResponse;
import com.cust.smartreceptionist.Models.Doctor;
import com.cust.smartreceptionist.R;
import com.cust.smartreceptionist.api.APIService;
import com.cust.smartreceptionist.api.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TextAppointmentFragment extends Fragment {
    Spinner spin_department, spin_doctor;
    List<Department> departments;
    APIService APIService;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Appointment");

        return inflater.inflate(R.layout.text_appointment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        spin_department = view.findViewById(R.id.spin_department);
        spin_doctor = view.findViewById(R.id.spin_doctor);
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
                List<String> departmentnames = new ArrayList<>();
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
                        int departmentid = 0;
                        for (Department department : departments) {
                            if (department.getDepartment_name() == departmentName) {
                                departmentid = department.getId();
                            }
                        }
                        Call<DepartmentDoctorsResponse> call = RetrofitClient.getInstance().getApi().getDoctors(departmentid);
                        call.enqueue(new Callback<DepartmentDoctorsResponse>() {
                            @Override
                            public void onResponse(Call<DepartmentDoctorsResponse> call, Response<DepartmentDoctorsResponse> response) {
                                if (!response.isSuccessful()) {
                                    Toast.makeText(getActivity(), response.code(), Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                DepartmentDoctorsResponse departmentDoctorsResponse = response.body();
                                List<Doctor> doctors = departmentDoctorsResponse.getDoctors();
                                List<String> doctorsname = new ArrayList<>();
                                for (Doctor doctor : doctors) {
                                    doctorsname.add("Dr. " + doctor.getFirstname() + doctor.getLastname());
                                }
                                ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                                        android.R.layout.simple_list_item_1,
                                        doctorsname);
                                spin_doctor.setAdapter(adapter);
                            }

                            @Override
                            public void onFailure(Call<DepartmentDoctorsResponse> call, Throwable t) {
                                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onFailure(Call<DepartmentsResponse> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        Button bt_appointmentschedule = view.findViewById(R.id.bt_appointmentschedule);
        bt_appointmentschedule.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_textAppointmentFragment_to_textAppointmentTimeslotsFragment, null));
    }
}

