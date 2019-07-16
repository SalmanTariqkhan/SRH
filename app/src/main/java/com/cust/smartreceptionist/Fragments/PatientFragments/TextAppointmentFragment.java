package com.cust.smartreceptionist.Fragments.PatientFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
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
import com.cust.smartreceptionist.Models.DepartmentDoctors;
import com.cust.smartreceptionist.Models.Departments;
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
        spin_department = (Spinner) view.findViewById(R.id.spin_department);
        spin_doctor = (Spinner) view.findViewById(R.id.spin_doctor);
        Call<Departments> call = RetrofitClient.getInstance().getApi().getDepartments();
        call.enqueue(new Callback<Departments>() {
            @Override
            public void onResponse(Call<Departments> call, Response<Departments> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getActivity(), response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Departments data = response.body();
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
                        Call<DepartmentDoctors> call = RetrofitClient.getInstance().getApi().getDoctors(departmentid);
                        call.enqueue(new Callback<DepartmentDoctors>() {
                            @Override
                            public void onResponse(Call<DepartmentDoctors> call, Response<DepartmentDoctors> response) {
                                if (!response.isSuccessful()) {
                                    Toast.makeText(getActivity(), response.code(), Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                DepartmentDoctors departmentDoctors = response.body();
                                List<Doctor> doctors = departmentDoctors.getData();
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
                            public void onFailure(Call<DepartmentDoctors> call, Throwable t) {
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
            public void onFailure(Call<Departments> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        Button bt_appointmentschedule = (Button) view.findViewById(R.id.bt_appointmentschedule);
        bt_appointmentschedule.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_textAppointmentFragment_to_textAppointmentTimeslotsFragment, null));
    }
}

