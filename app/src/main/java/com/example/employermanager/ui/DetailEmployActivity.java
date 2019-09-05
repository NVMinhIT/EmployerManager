package com.example.employermanager.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.employermanager.R;
import com.example.employermanager.api.IEmployer;
import com.example.employermanager.api.ServiceEmployer;
import com.example.employermanager.api.response.DeleteResult;
import com.jaeger.library.StatusBarUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailEmployActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "DetailEmployActivity";
    private static final int REQUEST_CODE_EXAMPLE = 123;
    private String idmoi;
    private TextView tvId, tvName, tvSalary, tvAge;
    private ImageButton imageButtonBack;
    private Button btnDelete, btUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_employ);
        StatusBarUtil.setTranslucent(this, 50);
        initView();
    }

    private void initView() {
        imageButtonBack = findViewById(R.id.imb_back_detail);
        tvName = findViewById(R.id.txt_name_detail);
        tvSalary = findViewById(R.id.txt_salary_detail);
        tvId = findViewById(R.id.txt_id_detail);
        tvAge = findViewById(R.id.txt_age_detail);
        btnDelete = findViewById(R.id.btn_delete);
        btUpdate = findViewById(R.id.btn_update);
        btnDelete.setOnClickListener(this);
        btUpdate.setOnClickListener(this);
        imageButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Intent intent = getIntent();

        idmoi = intent.getStringExtra("Id");
        tvId.setText(idmoi);
        String name = intent.getStringExtra("Name");
        tvName.setText(name);
        String salary = intent.getStringExtra("Salary");
        tvSalary.setText(salary);
        String age = intent.getStringExtra("Age");
        tvAge.setText(age);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_delete:
                final AlertDialog.Builder dialogxoa = new AlertDialog.Builder(this);
                dialogxoa.setMessage("Bạn có muốn xóa nhân viên này không?");
                dialogxoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        IEmployer service = ServiceEmployer.createService(IEmployer.class);
                        service.getAllEmployAfterDelete(tvId.getText().toString()).enqueue(new Callback<DeleteResult>() {
                            @Override
                            public void onResponse(Call<DeleteResult> call, Response<DeleteResult> response) {
                                DeleteResult deleteResult = response.body();
                                Log.d("hihi", "result" + "sucess: " + deleteResult.getSuccess());
                                Toast.makeText(DetailEmployActivity.this, "Đã xóa : " + tvId.getText().toString(), Toast.LENGTH_SHORT).show();
                                finish();
                            }

                            @Override
                            public void onFailure(Call<DeleteResult> call, Throwable t) {
                            }
                        });
                    }
                });
                dialogxoa.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                dialogxoa.show();
                break;
            case R.id.btn_update:
                Intent intent = new Intent(DetailEmployActivity.this, UpdateEmployeeActivity.class);
                intent.putExtra("NAME", tvName.getText().toString());
                intent.putExtra("SALARY", tvSalary.getText().toString());
                intent.putExtra("AGE", tvAge.getText().toString());
                intent.putExtra("ID", tvId.getText().toString());
                startActivityForResult(intent, REQUEST_CODE_EXAMPLE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE_EXAMPLE) {
            if (resultCode == UpdateEmployeeActivity.RESULT_OK) {
                final String ten, luong, tuoi;
                if (data != null) {
                    ten = data.getStringExtra(UpdateEmployeeActivity.EXTRA_DATA_NAME);
                    tvName.setText(ten);
//                    luong = data.getStringExtra(UpdateEmployeeActivity.EXTRA_DATA_SALARY);
//                    tvSalary.setText(luong);
//                    tuoi = data.getStringExtra(UpdateEmployeeActivity.EXTRA_DATA_AGE);
//                    tvAge.setText(tuoi);

                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
