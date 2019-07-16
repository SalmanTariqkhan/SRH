package com.cust.smartreceptionist.api;

import com.cust.smartreceptionist.Models.DefaultResponse;
import com.cust.smartreceptionist.Models.DepartmentDoctorsResponse;
import com.cust.smartreceptionist.Models.DepartmentsResponse;
import com.cust.smartreceptionist.Models.DoctorSigninResponse;
import com.cust.smartreceptionist.Models.PatientSigninResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIService {
    @GET("departments")
    Call<DepartmentsResponse> getDepartments();

    @GET("departments/doctors/{id}")
    Call<DepartmentDoctorsResponse> getDoctors(@Path("id") int department_id);

    @FormUrlEncoded
    @POST("patientregister")
    Call<DefaultResponse> registerPatient(
            @Field("firstname") String firstname,
            @Field("lastname")  String lastname,
            @Field("email") String email,
            @Field("password") String password,
            @Field("c_password") String c_password,
            @Field("phonenumber") String phonenumber,
            @Field("DOB") String DOB,
            @Field("gender") String gender);

    @FormUrlEncoded
    @POST("doctorregister")
    Call<DefaultResponse> registerDoctor(
            @Field("firstname") String firstname,
            @Field("lastname")  String lastname,
            @Field("department_id") int department_id,
            @Field("email") String email,
            @Field("password") String password,
            @Field("c_password") String c_password,
            @Field("phonenumber") String phonenumber,
            @Field("DOB") String DOB,
            @Field("gender") String gender);

    @FormUrlEncoded
    @POST("patientlogin")
    Call<PatientSigninResponse> loginPatient(
            @Field("email") String email,
            @Field("password") String password);

    @FormUrlEncoded
    @POST("doctorlogin")
    Call<DoctorSigninResponse> loginDoctor(
            @Field("email") String email,
            @Field("password") String password);
}
