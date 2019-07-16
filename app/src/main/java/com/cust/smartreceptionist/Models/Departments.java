package com.cust.smartreceptionist.Models;

import java.util.ArrayList;

public class Departments {
    private ArrayList<Department> data;
    public Departments(){}

    public ArrayList<Department> getDepartments() {
        return data;
    }

    public void setDepartments(ArrayList<Department> data) {
        this.data = data;
    }
}
