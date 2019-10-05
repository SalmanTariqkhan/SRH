package com.cust.smartreceptionist.Models;

public class Appointmentslot {
    int appointmentslot_id;
    int status;
    String start_time;
    String startdate;

    public Appointmentslot(int appointmentslot_id, int status, String start_time, String startdate) {
        this.appointmentslot_id = appointmentslot_id;
        this.status = status;
        this.start_time = start_time;
        this.startdate = startdate;
    }

    public int getAppointmentslot_id() {
        return appointmentslot_id;
    }

    public int getStatus() {
        return status;
    }

    public String getStart_time() {
        return start_time;
    }

    public String getStartdate() {
        return startdate;
    }
}
