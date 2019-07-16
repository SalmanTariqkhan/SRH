package com.cust.smartreceptionist.Models;

public class Timeslot {
    int appointmentslot_id;
    boolean status;
    String starttime;
    String startdate;

    public int getAppointmentslot_id() {
        return appointmentslot_id;
    }

    public void setAppointmentslot_id(int appointmentslot_id) {
        this.appointmentslot_id = appointmentslot_id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }
}
