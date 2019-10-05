package com.cust.smartreceptionist.Models;

public class Timeslot {
    String start_time;
    String end_time;
    String startdate;
    int timeslot_id;

    public Timeslot(String start_time, String end_time, String startdate, int timeslot_id) {
        this.start_time = start_time;
        this.end_time = end_time;
        this.startdate = startdate;
        this.timeslot_id = timeslot_id;
    }

    public String getStart_time() {
        return start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public String getStartdate() {
        return startdate;
    }

    public int getTimeslot_id() {
        return timeslot_id;
    }
}
