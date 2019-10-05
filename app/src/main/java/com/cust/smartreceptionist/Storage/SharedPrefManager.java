package com.cust.smartreceptionist.Storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.cust.smartreceptionist.Models.Doctor;
import com.cust.smartreceptionist.Models.Patient;

public class SharedPrefManager {
    public static final String SHARED_PREF_NAME = "My_shared_preff";
    private static SharedPrefManager mInstance;
    private Context mContext;

    private SharedPrefManager(Context mContext) {
        this.mContext = mContext;
    }

    public static synchronized SharedPrefManager getInstance(Context mContext) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(mContext);
        }
        return mInstance;
    }

    public void savePatient(Patient patient) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt("Patient_id", patient.getId());
        editor.putString("Patient_firstname", patient.getFirstname());
        editor.putString("Patient_lastname", patient.getLastname());
        editor.putString("Patient_email", patient.getEmail());
        editor.putString("Patient_email_verified_at", patient.getEmail_verified_at());
        editor.putString("Patient_phonenumber", patient.getPhonenumber());
        editor.putString("Patient_phonenumber_verified_at", patient.getPhonenumber_verified_at());
        editor.putString("Patient_DOB", patient.getDOB());
        editor.putString("Patient_gender", patient.getGender());
        editor.putString("Patient_image", patient.getImage());
        editor.putString("Patient_created_at", patient.getCreated_at());
        editor.putString("Patient_updated_at", patient.getUpdated_at());
        editor.apply();
    }

    public void saveDoctor(Doctor doctor) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt("doctor_id", doctor.getId());
        editor.putString("doctor_firstname", doctor.getFirstname());
        editor.putString("doctor_lastname", doctor.getLastname());
        editor.putInt("doctor_department_id", doctor.getDepartment_id());
        editor.putString("doctor_email", doctor.getEmail());
        editor.putString("doctor_email_verified_at", doctor.getEmail_verified_at());
        editor.putString("doctor_accountstatus", doctor.getAccountstatus());
        editor.putString("doctor_phonenumber", doctor.getPhonenumber());
        editor.putString("doctor_phonenumber_verified_at", doctor.getPhonenumber_verified_at());
        editor.putString("doctor_DOB", doctor.getDOB());
        editor.putString("doctor_gender", doctor.getGender());
        editor.putString("doctor_image", doctor.getImage());
        editor.putString("doctor_created_at", doctor.getCreated_at());
        editor.putString("doctor_updated_at", doctor.getUpdated_at());
        editor.apply();
    }


    public boolean isPatientLoggedIn() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt("Patient_id", -1) != -1;
    }

    public boolean isDoctorLoggedIn() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt("doctor_id", -1) != -1;
    }

    public Patient getPatient() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new Patient(
                sharedPreferences.getInt("Patient_id", -1),
                sharedPreferences.getString("Patient_firstname", null),
                sharedPreferences.getString("Patient_lastname", null),
                sharedPreferences.getString("Patient_email", null),
                sharedPreferences.getString("Patient_email_verified_at", null),
                sharedPreferences.getString("Patient_phonenumber", null),
                sharedPreferences.getString("Patient_phonenumber_verified_at", null),
                sharedPreferences.getString("Patient_DOB", null),
                sharedPreferences.getString("Patient_gender", null),
                sharedPreferences.getString("Patient_image", null),
                sharedPreferences.getString("Patient_created_at", null),
                sharedPreferences.getString("Patient_updated_at", null));
    }

    public Doctor getDoctor() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new Doctor(
                sharedPreferences.getInt("doctor_id", -1),
                sharedPreferences.getString("doctor_firstname", null),
                sharedPreferences.getString("doctor_lastname", null),
                sharedPreferences.getInt("doctor_department_id", -1),
                sharedPreferences.getString("doctor_email", null),
                sharedPreferences.getString("doctor_email_verified_at", null),
                sharedPreferences.getString("doctor_accountstatus", null),
                sharedPreferences.getString("doctor_phonenumber", null),
                sharedPreferences.getString("doctor_phonenumber_verified_at", null),
                sharedPreferences.getString("doctor_DOB", null),
                sharedPreferences.getString("doctor_gender", null),
                sharedPreferences.getString("doctor_image", null),
                sharedPreferences.getString("doctor_created_at", null),
                sharedPreferences.getString("doctor_updated_at", null));
    }

    public void clear() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
    }
}
