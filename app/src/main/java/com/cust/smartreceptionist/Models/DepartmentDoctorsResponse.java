package com.cust.smartreceptionist.Models;

import java.util.ArrayList;

public class DepartmentDoctorsResponse extends
        DefaultResponse {
    private ArrayList<Doctor> doctors;

    public DepartmentDoctorsResponse(boolean error, String message, ArrayList<Doctor> doctors) {
        super(error, message);
        this.doctors = doctors;
    }

    public ArrayList<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(ArrayList<Doctor> doctors) {
        this.doctors = doctors;
    }
}
