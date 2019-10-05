package com.cust.smartreceptionist.Models;

public class TimeslotResponse extends DefaultResponse {
    Timeslot Consultancy_slots;

    public TimeslotResponse(boolean error, String message, Timeslot consultancy_slots) {
        super(error, message);
        Consultancy_slots = consultancy_slots;
    }

    public Timeslot getConsultancy_slots() {
        return Consultancy_slots;
    }
}
