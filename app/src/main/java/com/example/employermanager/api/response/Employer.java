package com.example.employermanager.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Employer {
    @SerializedName("id")
    @Expose
    private String Id;
    @SerializedName("employee_name")
    @Expose
    private String EmployeeName;
    @SerializedName("employee_salary")
    @Expose
    private String EmploySalary;
    @SerializedName("employee_age")
    @Expose
    private String EmployAge;
    @SerializedName("profile_image")
    @Expose
    private String ProfileImage;


    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getEmployeeName() {
        return EmployeeName;
    }

    public void setEmployeeName(String employeeName) {
        EmployeeName = employeeName;
    }

    public String getEmploySalary() {
        return EmploySalary;
    }

    public void setEmploySalary(String employSalary) {
        EmploySalary = employSalary;
    }

    public String getEmployAge() {
        return EmployAge;
    }

    public void setEmployAge(String employAge) {
        EmployAge = employAge;
    }

    public String getProfileImage() {
        return ProfileImage;
    }

    public void setProfileImage(String profileImage) {
        ProfileImage = profileImage;
    }
}
