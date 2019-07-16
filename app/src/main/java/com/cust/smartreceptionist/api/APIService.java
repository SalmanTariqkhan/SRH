package com.cust.smartreceptionist.api;

import com.cust.smartreceptionist.Models.DepartmentDoctors;
import com.cust.smartreceptionist.Models.Departments;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIService {
    @GET("departments")
    Call<Departments> getDepartments();

    @GET("departments/doctors/{id}")
    Call<DepartmentDoctors> getDoctors(@Path("id") int department_id);

    @FormUrlEncoded
    @POST("patientregister")
    Call<ResponseBody> registerPatient(
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
    Call<ResponseBody> registerDoctor(
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
    Call<ResponseBody> loginPatient(
            @Field("email") String email,
            @Field("password") String password);

    @FormUrlEncoded
    @POST("doctorlogin")
    Call<ResponseBody> loginDoctor(
            @Field("email") String email,
            @Field("password") String password);
}
