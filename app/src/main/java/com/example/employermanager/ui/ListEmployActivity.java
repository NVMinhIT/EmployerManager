package com.example.employermanager.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.employermanager.R;
import com.example.employermanager.api.IEmployer;
import com.example.employermanager.api.ServiceEmployer;
import com.example.employermanager.api.response.Employer;
import com.example.employermanager.api.response.IOnClickListeners;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListEmployActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "ListEmployActivity";
    RecyclerView recyclerView;
    EmployerAdapter employerAdapter;
    List<Employer> employerList;
    FloatingActionButton floatingActionButton;
    private EditText edtSearch;
    private IOnClickListeners<Employer> mCallback;
    private FloatingActionButton floatingActionButtonadd;
    private Boolean isFirst = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_employ);
        StatusBarUtil.setTranslucent(this, 50);
        initView();

    }

    private void initView() {

        employerList = new ArrayList<>();
        recyclerView = findViewById(R.id.rv_list_employer);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(this);
        employerAdapter = new EmployerAdapter(this, employerList);
        recyclerView.setAdapter(employerAdapter);
        showListEmploy();
        edtSearch = findViewById(R.id.edt_search);
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (edtSearch.getText().toString().equals("")) {
                    showListEmploy();
                    employerAdapter.setListEmployer(employerList);
                } else {
                    search(edtSearch.getText().toString());
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void search(String name) {
        ArrayList<Employer> arr = new ArrayList<>();
        for (int i = 0; i < employerList.size(); i++) {
            if (employerList.get(i).getEmployeeName().contains(name)) {
                arr.add(employerList.get(i));
            }
        }
        employerAdapter.setListEmployer(arr);

    }

    private void showListEmploy() {
        IEmployer service = ServiceEmployer.createService(IEmployer.class);
        service.getResultEmployer().enqueue(new Callback<List<Employer>>() {
            @Override
            public void onResponse(Call<List<Employer>> call, Response<List<Employer>> response) {
                List<Employer> list = response.body();
                Log.d("count: ", "" + list.size());
                employerAdapter.setmListener(mCallback);
                employerAdapter.setEmployeelList(list);
            }

            @Override
            public void onFailure(Call<List<Employer>> call, Throwable t) {
                Toast.makeText(ListEmployActivity.this, "Not response", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(ListEmployActivity.this, AddEmployeeAcitivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!isFirst) {
            showListEmploy();
        } else {
            isFirst = false;
        }
    }
}