package com.keshav.springct_assignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>{

    ArrayList<Employee> emplist;
    Context context;

    public CustomAdapter(Context context,ArrayList<Employee> emplist) {
        this.emplist = emplist;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        TextView address;
        TextView age;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.cardView_empName);
            address = itemView.findViewById(R.id.cardView_empAddress);
            age = itemView.findViewById(R.id.cardView_empAge);

        }
    }
    @NonNull
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.base_cardview_layout,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder holder, int position) {
        Employee employee = emplist.get(position);
        holder.name.setText(employee.getName());
        holder.address.setText(employee.getAddress());
        holder.age.setText(employee.getAge());

    }

    @Override
    public int getItemCount() {
        return emplist.size();
    }
}
