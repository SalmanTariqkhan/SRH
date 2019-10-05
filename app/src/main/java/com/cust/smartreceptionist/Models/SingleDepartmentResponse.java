package com.cust.smartreceptionist.Models;


public class SingleDepartmentResponse extends DefaultResponse {
    private Department department;

    public SingleDepartmentResponse(boolean error, String message, Department department) {
        super(error, message);
        this.department = department;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartments(Department department) {
        this.department = department;
    }
}
