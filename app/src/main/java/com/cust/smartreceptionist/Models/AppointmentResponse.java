package com.cust.smartreceptionist.Models;

import java.util.ArrayList;

public class AppointmentResponse extends
        DefaultResponse {
    private ArrayList<Appointmentslot> Appointment_slots;

    public AppointmentResponse(boolean error, String message, ArrayList<Appointmentslot> appointmentslot_slots) {
        super(error, message);
        this.Appointment_slots = appointmentslot_slots;
    }

    public ArrayList<Appointmentslot> getAppointment_slots() {
        return Appointment_slots;
    }
}
