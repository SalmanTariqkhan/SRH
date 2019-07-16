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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TimePicker;

import com.cust.smartreceptionist.R;
import com.cust.smartreceptionist.utilities.ConsultancyslotItem;
import com.cust.smartreceptionist.utilities.TimetableAdapter;
import com.cust.smartreceptionist.utilities.TimetableItem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class DoctorManageTimetableFragment extends Fragment implements
        DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
        ArrayList<TimetableItem> timetableItems;
        ImageButton bt_addconsultancyslot;
        String currentdate , starttime,endtime;
        RecyclerView rv_timetableslots;
        int firsttimepicker = 0,samedate =0, samedateindex;
        int hour,minutes;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        getActivity().setTitle("Manage Timetable");
        timetableItems = new ArrayList<TimetableItem>();
        return inflater.inflate(R.layout.doctor_managetimetable, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        bt_addconsultancyslot = view.findViewById(R.id.bt_addconsultancyslot);
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
        currentdate = new SimpleDateFormat("EE", Locale.getDefault()).format(new Date());
        currentdate = currentdate + " , " + year + " - " + month + " - " + dayOfMonth;
        if(!timetableItems.isEmpty()){
        for(int i = 0; i < timetableItems.size();i++)
        {
            if(timetableItems.get(i).getDate().equals(currentdate) )
            {
                System.out.println("yes");
                samedateindex = i;
                 samedate = 1;
            }
        }}
        Calendar c = Calendar.getInstance();
        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                DoctorManageTimetableFragment.this,c.get(Calendar.HOUR_OF_DAY),c.get(Calendar.MINUTE),DateFormat.is24HourFormat(getActivity()));
        timePickerDialog.show();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        if(firsttimepicker == 0) {
            hour = hourOfDay;
            minutes = minute;
            String timeSet = "";
            if (hour > 12) {
                hour -= 12;
                timeSet = "PM";
            } else if (hour == 0) {
                hour += 12;
                timeSet = "AM";
            } else if (hour == 12){
                timeSet = "PM";
            }else{
                timeSet = "AM";
            }

            String min = "";
            if (minutes < 10)
                min = "0" + minutes ;
            else
                min = String.valueOf(minutes);

            // Append in a StringBuilder
            starttime = new StringBuilder().append(hour).append(':')
                    .append(min ).append(" ").append(timeSet).toString();
            Calendar c = Calendar.getInstance();
            TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                    DoctorManageTimetableFragment.this,c.get(Calendar.HOUR_OF_DAY),c.get(Calendar.MINUTE),DateFormat.is24HourFormat(getActivity()));
            timePickerDialog.show();
            firsttimepicker = 1;

        } else
        {
            hour = hourOfDay;
            minutes = minute;
            String timeSet = "";
            if (hour > 12) {
                hour -= 12;
                timeSet = "PM";
            } else if (hour == 0) {
                hour += 12;
                timeSet = "AM";
            } else if (hour == 12){
                timeSet = "PM";
            }else{
                timeSet = "AM";
            }

            String min = "";
            if (minutes < 10)
                min = "0" + minutes ;
            else
                min = String.valueOf(minutes);

            // Append in a StringBuilder
            endtime = new StringBuilder().append(hour).append(':')
                    .append(min ).append(" ").append(timeSet).toString();
            if(samedate == 1)
            {   TimetableItem tempitem = timetableItems.get(samedateindex);
                tempitem.getConsultancyslotItems().add( new ConsultancyslotItem( starttime +" - "+ endtime));
                timetableItems.set(samedateindex ,tempitem );
                samedate = 0;
                firsttimepicker = 0;
                System.out.println(timetableItems.size() + "hello");
            }
            else if (samedate == 0)
            {   ArrayList<ConsultancyslotItem> consultancyslotItems = new ArrayList<ConsultancyslotItem>();
                consultancyslotItems.add(new ConsultancyslotItem(starttime +" - "+endtime));
                timetableItems.add( new TimetableItem(currentdate, consultancyslotItems));
                firsttimepicker = 0;

            }
            TimetableAdapter timetableAdapter = new TimetableAdapter(getActivity(), timetableItems);
            rv_timetableslots.setAdapter(timetableAdapter);
        }
    }
}


