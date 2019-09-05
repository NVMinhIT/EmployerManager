package com.example.employermanager.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.employermanager.R;
import com.example.employermanager.api.IEmployer;
import com.example.employermanager.api.ServiceEmployer;
import com.example.employermanager.api.response.EmployBody;
import com.example.employermanager.api.response.ResponseEmployee;
import com.jaeger.library.StatusBarUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddEmployeeAcitivity extends AppCompatActivity implements View.OnClickListener {
    private Button btAdd;
    private Button btCancel;
    private TextView tName, tSalary, tAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee_acitivity);
        StatusBarUtil.setTranslucent(this, 50);
        initView();
    }

    private void initView() {
        btCancel = findViewById(R.id.btn_cancel);
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tName = findViewById(R.id.edt_name);
        tSalary = findViewById(R.id.edt_salary);
        tAge = findViewById(R.id.edt_age);
        btAdd = findViewById(R.id.btn_save);
        btAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        final String ten = tName.getText().toString();
        final String luong = tSalary.getText().toString();
        final String tuoi = tAge.getText().toString();
        if (ten.equals("")) {
            Toast.makeText(this, "Vui lòng nhập tên", Toast.LENGTH_SHORT).show();
        } else if (luong.equals("")) {
            Toast.makeText(this, "Vui lòng nhập lương", Toast.LENGTH_SHORT).show();
        } else if (tuoi.equals("")) {
            Toast.makeText(this, "Vui lòng nhập tuổi", Toast.LENGTH_SHORT).show();
        } else {
            IEmployer service = ServiceEmployer.createService(IEmployer.class);
            service.getAllEmployAfterCreate(new EmployBody(ten, luong, tuoi)).enqueue(new Callback<ResponseEmployee>() {
                @Override
                public void onResponse(Call<ResponseEmployee> call, Response<ResponseEmployee> response) {
                    ResponseEmployee responseEmployee = response.body();
                    //Log.d("hihi", "text" + responseEmployee.getId() + responseEmployee.getName());
                    Toast.makeText(AddEmployeeAcitivity.this, "Đã thêm nhân viên:" + responseEmployee.getName(), Toast.LENGTH_SHORT).show();
                    finish();
                }

                @Override
                public void onFailure(Call<ResponseEmployee> call, Throwable t) {

                }
            });
        }
    }

}
