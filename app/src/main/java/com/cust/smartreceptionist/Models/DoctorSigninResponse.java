package com.cust.smartreceptionist.Models;

public class DoctorSigninResponse extends
        DefaultResponse {
    String token;
    private Doctor doctors;

    public DoctorSigninResponse(boolean error, String message, Doctor doctors, String token) {

        super(error, message);
        this.doctors = doctors;
        this.token = token;
    }

    public Doctor getDoctors() {
        return doctors;
    }

    public String getToken() {
        return token;
    }
}
