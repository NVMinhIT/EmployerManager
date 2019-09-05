package com.example.employermanager.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EmployBody {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("salary")
    @Expose
    private String salary;
    @SerializedName("age")
    @Expose
    private String age;

    public EmployBody() {

    }

    public EmployBody(String name, String salary, String age) {
        this.name = name;
        this.salary = salary;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
