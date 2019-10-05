package com.cust.smartreceptionist.Fragments.DoctorFragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

import com.cust.smartreceptionist.Models.ConsultancyslotResponse;
import com.cust.smartreceptionist.Models.Doctor;
import com.cust.smartreceptionist.Models.Timeslot;
import com.cust.smartreceptionist.Models.TimeslotResponse;
import com.cust.smartreceptionist.R;
import com.cust.smartreceptionist.Storage.SharedPrefManager;
import com.cust.smartreceptionist.api.RetrofitClient;
import com.cust.smartreceptionist.utilities.ConsultancyslotItem;
import com.cust.smartreceptionist.utilities.TimetableAdapter;
import com.cust.smartreceptionist.utilities.TimetableItem;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DoctorManageTimetableFragment extends Fragment implements
        DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
        ArrayList<TimetableItem> timetableItems;
        ImageButton bt_addconsultancyslot;
    ArrayList<Timeslot> timeslots;
        String currentdate , starttime,endtime;
        RecyclerView rv_timetableslots;
        int firsttimepicker = 0,samedate =0, samedateindex;
    //        int hour,minutes;
    Timeslot timeslot;
    Doctor doctor;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        getActivity().setTitle("Manage Timetable");
        return inflater.inflate(R.layout.doctor_managetimetable, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        doctor = SharedPrefManager.getInstance(getActivity()).getDoctor();
        bt_addconsultancyslot = view.findViewById(R.id.bt_addconsultancyslot);
        getConsultancyslotsresponse(doctor.getId());
        bt_addconsultancyslot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Calendar c = Calendar.getInstance();
            DatePickerDialog datePickerDialog = new DatePickerDialog( getActivity(),DoctorManageTimetableFragment.this,
                    c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.show();
            System.out.println(timetableItems.size());
            }
        });
        rv_timetableslots = view.findViewById(R.id.rv_timetableslots);
        rv_timetableslots.setHasFixedSize(true);
        rv_timetableslots.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));


    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//        currentdate = new SimpleDateFormat("EE", Locale.getDefault()).format(new Date());
        currentdate = year + "-" + month + "-" + dayOfMonth;
//        if(!timetableItems.isEmpty()){
//        for(int i = 0; i < timetableItems.size();i++)
//        {
//            if(timetableItems.get(i).getDate().equals(currentdate) )
//            {
//                System.out.println("yes");
//                samedateindex = i;
//                 samedate = 1;
//            }
//        }}
        Calendar c = Calendar.getInstance();
        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                DoctorManageTimetableFragment.this,c.get(Calendar.HOUR_OF_DAY),c.get(Calendar.MINUTE),DateFormat.is24HourFormat(getActivity()));
        timePickerDialog.show();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        if(firsttimepicker == 0) {
            starttime = hourOfDay + ":" + minute;
//            hour = hourOfDay;
//            minutes = minute;
//            String timeSet = "";
//            if (hour > 12) {
//                hour -= 12;
//                timeSet = "PM";
//            } else if (hour == 0) {
//                hour += 12;
//                timeSet = "AM";
//            } else if (hour == 12){
//                timeSet = "PM";
//            }else{
//                timeSet = "AM";
//            }
//
//            String min = "";
//            if (minutes < 10)
//                min = "0" + minutes ;
//            else
//                min = String.valueOf(minutes);
//
//            // Append in a StringBuilder
//            starttime = new StringBuilder().append(hour).append(':')
//                    .append(min ).append(" ").append(timeSet).toString();
            Calendar c = Calendar.getInstance();
            TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                    DoctorManageTimetableFragment.this,c.get(Calendar.HOUR_OF_DAY),c.get(Calendar.MINUTE),DateFormat.is24HourFormat(getActivity()));
            timePickerDialog.show();
            firsttimepicker = 1;

        } else
        {
//            hour = hourOfDay;
//            minutes = minute;
//            String timeSet = "";
//            if (hour > 12) {
//                hour -= 12;
//                timeSet = "PM";
//            } else if (hour == 0) {
//                hour += 12;
//                timeSet = "AM";
//            } else if (hour == 12){
//                timeSet = "PM";
//            }else{
//                timeSet = "AM";
//            }
//
//            String min = "";
//            if (minutes < 10)
//                min = "0" + minutes ;
//            else
//                min = String.valueOf(minutes);
//
//            // Append in a StringBuilder
//            endtime = new StringBuilder().append(hour).append(':')
//                    .append(min ).append(" ").append(timeSet).toString();
            endtime = hourOfDay + ":" + minute;
            firsttimepicker = 0;
//            if(samedate == 1)
//            {   TimetableItem tempitem = timetableItems.get(samedateindex);
//                tempitem.getConsultancyslotItems().add( new ConsultancyslotItem( starttime +" - "+ endtime));
//                timetableItems.set(samedateindex ,tempitem );
//                samedate = 0;
//                firsttimepicker = 0;
//                System.out.println(timetableItems.size() + "hello");
//            }
//            else if (samedate == 0)
//            {   ArrayList<ConsultancyslotItem> consultancyslotItems = new ArrayList<ConsultancyslotItem>();
//                consultancyslotItems.add(new ConsultancyslotItem(starttime +" - "+endtime));
//                timetableItems.add( new TimetableItem(currentdate, consultancyslotItems));
//                firsttimepicker = 0;
//
//            }

            addConsultancyslot(doctor.getId(), starttime, endtime, currentdate);
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    // this code will be executed after 2 seconds
                    getConsultancyslotsresponse(doctor.getId());
                }
            }, 2000);


        }
    }

    private void getConsultancyslotsresponse(final int doctor_id) {
        timetableItems = new ArrayList<TimetableItem>();
        timeslots = new ArrayList<Timeslot>();
        Call<ConsultancyslotResponse> call = RetrofitClient.getInstance().getApi().getConsultancyslots(doctor_id);
        call.enqueue(new Callback<ConsultancyslotResponse>() {
            @Override
            public void onResponse(Call<ConsultancyslotResponse> call, Response<ConsultancyslotResponse> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getActivity(), String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
                    return;
                }
                ConsultancyslotResponse consultancyslotResponse = response.body();

                timeslots = consultancyslotResponse.getConsultancy_slots();

//                                if(timeslots.size() == 1)
//                                {
//
//                                }
                for (int i = 0; i < timeslots.size(); i++) {
                    ArrayList<ConsultancyslotItem> consultancyslotItems = new ArrayList<ConsultancyslotItem>();
                    consultancyslotItems.add(new ConsultancyslotItem(timeslots.get(i).getStart_time() + "-" + timeslots.get(i).getEnd_time()));
                    timetableItems.add(new TimetableItem(timeslots.get(i).getStartdate(), consultancyslotItems));
                }
                TimetableAdapter timetableAdapter = new TimetableAdapter(getActivity(), timetableItems);
                timetableAdapter.setOnItemClickListener(new TimetableAdapter.OnItemClickListener() {

                    @Override
                    public void onDeleteClick(int position) {
                        Call<ResponseBody> responseBodyCall = RetrofitClient
                                .getInstance()
                                .getApi()
                                .deleteConsultancyslots(timeslots
                                        .get(position).getTimeslot_id());
                        responseBodyCall.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                if (!response.isSuccessful()) {
                                    Toast.makeText(getActivity(), String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                try {

                                    if (response.body().string().equals("204")) {
                                        getConsultancyslotsresponse(doctor_id);
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });
                rv_timetableslots.setAdapter(timetableAdapter);

            }

            @Override
            public void onFailure(Call<ConsultancyslotResponse> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    private void addConsultancyslot(int doctor_id, String starttime, String endtime, String date) {
        Call<TimeslotResponse> call = RetrofitClient.getInstance().getApi().addConsultancyslot(doctor_id, starttime, endtime, date);
        call.enqueue(new Callback<TimeslotResponse>() {
            @Override
            public void onResponse(Call<TimeslotResponse> call, Response<TimeslotResponse> response) {
//                    try {
                if (!response.isSuccessful()) {
                    Toast.makeText(getActivity(), String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
                    return;
                }

                TimeslotResponse timeslotResponse = response.body();

                getConsultancyslotsresponse(1);

//                    }catch (Exception e)
//                    {
//                        e.printStackTrace();
//                    }

            }

            @Override
            public void onFailure(Call<TimeslotResponse> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }
}


