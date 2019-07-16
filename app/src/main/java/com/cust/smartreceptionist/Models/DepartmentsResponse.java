package com.cust.smartreceptionist.Models;

import java.util.ArrayList;


public class DepartmentsResponse extends DefaultResponse {
    private ArrayList<Department> department;

    public DepartmentsResponse(boolean error, String message, ArrayList<Department> department) {
        super(error, message);
        this.department = department;
    }

    public ArrayList<Department> getDepartments() {
        return department;
    }

    public void setDepartments(ArrayList<Department> data) {
        this.department = data;
    }
}
