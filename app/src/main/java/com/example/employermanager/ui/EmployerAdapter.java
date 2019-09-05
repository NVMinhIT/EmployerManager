package com.example.employermanager.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.employermanager.R;
import com.example.employermanager.api.response.Employer;
import com.example.employermanager.api.response.IOnClickListeners;

import java.util.ArrayList;
import java.util.List;

public class EmployerAdapter extends RecyclerView.Adapter<EmployerAdapter.MyViewHolder> {
    private Context mContext;
    private List<Employer> employerList;
    private IOnClickListeners<Employer> mListener;

    public EmployerAdapter(Context mContext, List<Employer> employerList) {
        this.mContext = mContext;
        this.employerList = employerList;
    }

    public void setmListener(IOnClickListeners<Employer> mListener) {
        this.mListener = mListener;
    }

    public void setListEmployer(List<Employer> employerList) {
        this.employerList = employerList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EmployerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_employer_recycleview, parent, false);
        return new MyViewHolder(view);

    }
    @Override
    public void onBindViewHolder(@NonNull EmployerAdapter.MyViewHolder holder, int position) {

        final Employer employer = employerList.get(position);
        holder.tvId.setText(employer.getId());
        holder.tvName.setText(employer.getEmployeeName());
        holder.tvSalary.setText(employer.getEmploySalary());
        holder.tvAge.setText(employer.getEmployAge());
        //holder.tvImageProfile.setText(employer.getProfileImage());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailEmployActivity.class);
                intent.putExtra("Id", employer.getId());
                intent.putExtra("Name", employer.getEmployeeName());
                intent.putExtra("Salary", employer.getEmploySalary());
                intent.putExtra("Age", employer.getEmployAge());

                mContext.startActivity(intent);
            }
        });
    }
    public void setEmployeelList(List<Employer> employers) {
        if (employerList == null) {
            employerList = new ArrayList<>();
            employerList.addAll(employers);
            notifyDataSetChanged();
        } else {
            employerList.clear();
            employerList.addAll(employers);
            notifyDataSetChanged();
        }
    }
    @Override
    public int getItemCount() {
        return employerList.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvId, tvName, tvSalary, tvAge;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.txt_id_employee);
            tvName = itemView.findViewById(R.id.txt_name_employee);
            tvSalary = itemView.findViewById(R.id.txt_salary_employee);
            tvAge = itemView.findViewById(R.id.txt_age_employee);
        }
    }
}
