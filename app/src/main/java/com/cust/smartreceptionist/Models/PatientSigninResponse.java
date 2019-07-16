package com.cust.smartreceptionist.Models;

public class PatientSigninResponse extends
        DefaultResponse {
    private Patient patient;
    private String token;

    public PatientSigninResponse(boolean error, String message, Patient patient, String token) {
        super(error, message);
        this.patient = patient;
        this.token = token;
    }

    public Patient getPatient() {
        return patient;
    }

    public String getToken() {
        return token;
    }
}
