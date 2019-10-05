package com.cust.smartreceptionist.api;

import com.cust.smartreceptionist.Models.AppointmentResponse;
import com.cust.smartreceptionist.Models.ConsultancyslotResponse;
import com.cust.smartreceptionist.Models.DefaultResponse;
import com.cust.smartreceptionist.Models.DepartmentDoctorsResponse;
import com.cust.smartreceptionist.Models.DepartmentsResponse;
import com.cust.smartreceptionist.Models.SingleDepartmentResponse;
import com.cust.smartreceptionist.Models.TimeslotResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIService {
    @GET("departments")
    Call<DepartmentsResponse> getDepartments();

    @GET("departments/{id}")
    Call<SingleDepartmentResponse> getDepartment(@Path("id") int department_id);

    @GET("departments/doctors/{id}")
    Call<DepartmentDoctorsResponse> getDoctors(@Path("id") int department_id);

    @GET("doctor/{id}/timeslots")
    Call<ConsultancyslotResponse> getConsultancyslots(@Path("id") int doctor_id);

    @GET("timeslot/delete/{id}")
    Call<ResponseBody> deleteConsultancyslots(@Path("id") int timeslot_id);

    @GET("doctor/freeslots/{id}")
    Call<AppointmentResponse> getFreeAppointmentSlots(@Path("id") int doctor_id);

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
    Call<ResponseBody> loginPatient(
            @Field("email") String email,
            @Field("password") String password);

    @FormUrlEncoded
    @POST("doctorlogin")
    Call<ResponseBody> loginDoctor(
            @Field("email") String email,
            @Field("password") String password);

    @FormUrlEncoded
    @POST("timeslot/create")
    Call<TimeslotResponse> addConsultancyslot(
            @Field("doctor_id") int doctor_id,
            @Field("start_time") String start_time,
            @Field("end_time") String end_time,
            @Field("date") String date);
}
