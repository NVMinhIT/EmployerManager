package com.example.employermanager.ui;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.employermanager.R;
import com.example.employermanager.api.IEmployer;
import com.example.employermanager.api.ServiceEmployer;
import com.example.employermanager.api.response.EmployBody;
import com.jaeger.library.StatusBarUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class UpdateEmployeeActivity extends AppCompatActivity {
    public static final String EXTRA_DATA_NAME = UpdateEmployeeActivity.class.getSimpleName();
    public static final String EXTRA_DATA_SALARY = UpdateEmployeeActivity.class.getSimpleName();
    public static final String EXTRA_DATA_AGE = UpdateEmployeeActivity.class.getSimpleName();
    public static final String TAG = "UpdateEmployeeActivity";
    private EditText tname,tsalary,tage;
    private TextView tvId;
    private Button btSaveUpdate;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_employee);
        StatusBarUtil.setTranslucent(this, 50);
        initView();
    }
    private void initView() {
        tname = findViewById(R.id.edt_update_name);
        tsalary = findViewById(R.id.edt_update_salary);
        tage = findViewById(R.id.edt_age_update);
        tvId = findViewById(R.id.txt_update);
        Intent intent = getIntent();
        String name = intent.getStringExtra("NAME");
        tname.setText(name);
        String salary = intent.getStringExtra("SALARY");
        tsalary.setText(salary);
        String age = intent.getStringExtra("AGE");
        tage.setText(age);
        String id = intent.getStringExtra("ID");
        tvId.setText(id);
        btSaveUpdate = findViewById(R.id.btn_save_update);
        btSaveUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IEmployer service = ServiceEmployer.createService(IEmployer.class);
                final Call<EmployBody> call = service.getEmployUpdate(tvId.getText().toString(), new EmployBody(tname.getText().toString(),
                        tsalary.getText().toString(), tage.getText().toString()));
                progressDialog = new ProgressDialog(UpdateEmployeeActivity.this);
                progressDialog.setMessage("Updating");
                progressDialog.setCancelable(false);
                progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        call.cancel();
                    }
                });
                progressDialog.show();

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                            call.enqueue(new Callback<EmployBody>() {
                                @Override
                                public void onResponse(final Call<EmployBody> call, Response<EmployBody> response) {

                                    EmployBody employBody = response.body();
                                    //Log.d("haha", "reusult" + employBody.getSalary());
                                    Intent data = new Intent();
                                    data.putExtra(EXTRA_DATA_NAME, employBody.getName());
                                    //data.putExtra(EXTRA_DATA_SALARY, employBody.getSalary());
                                    //data.putExtra(EXTRA_DATA_AGE, tage.getText().toString());
                                    setResult(RESULT_OK, data);
                                    Toast.makeText(UpdateEmployeeActivity.this, "Update sucess: " +" "+employBody.getName(), Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                                @Override
                                public void onFailure(Call<EmployBody> call, Throwable t) {
                                }
                            });
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
                thread.start();
            }
        });
    }
}

