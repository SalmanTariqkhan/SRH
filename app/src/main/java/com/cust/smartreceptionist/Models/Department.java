package com.cust.smartreceptionist.Models;

public class Department {
    int department_id;
    String department_name;
    String description;

    public int getId() {
        return department_id;
    }

    public void setId(int id) {
        this.department_id = id;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
