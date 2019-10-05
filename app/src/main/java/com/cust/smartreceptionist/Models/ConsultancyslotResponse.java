package com.cust.smartreceptionist.Models;

import java.util.ArrayList;

public class ConsultancyslotResponse extends DefaultResponse {
    ArrayList<Timeslot> Consultancy_slots;

    public ConsultancyslotResponse(boolean error, String message, ArrayList<Timeslot> consultancy_slots) {
        super(error, message);
        Consultancy_slots = consultancy_slots;
    }

    public ArrayList<Timeslot> getConsultancy_slots() {
        return Consultancy_slots;
    }
}
