package com.cust.smartreceptionist.Models;

public class Doctor {
    int id;
    String firstname;
    String lastname;
    int department_id;
    String email;
    String email_verified_at;
    String accountstatus;
    String phonenumber;
    String phonenumber_verified_at;
    String DOB;
    String gender;
    String image;
    String created_at;
    String updated_at;


    public Doctor(int id, String firstname, String lastname, int department_id, String email, String email_verified_at, String accountstatus, String phonenumber, String phonenumber_verified_at, String DOB, String gender, String image, String created_at, String updated_at) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.department_id = department_id;
        this.email = email;
        this.email_verified_at = email_verified_at;
        this.accountstatus = accountstatus;
        this.phonenumber = phonenumber;
        this.phonenumber_verified_at = phonenumber_verified_at;
        this.DOB = DOB;
        this.gender = gender;
        this.image = image;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public String getEmail() {
        return email;
    }

    public String getEmail_verified_at() {
        return email_verified_at;
    }

    public String getAccountstatus() {
        return accountstatus;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public String getPhonenumber_verified_at() {
        return phonenumber_verified_at;
    }

    public String getDOB() {
        return DOB;
    }

    public String getGender() {
        return gender;
    }

    public String getImage() {
        return image;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }
}
