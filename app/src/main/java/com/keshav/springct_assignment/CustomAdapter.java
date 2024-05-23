package com.keshav.springct_assignment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>{

    ArrayList<Employee> emplist;

    public CustomAdapter(ArrayList<Employee> emplist) {
        this.emplist = emplist;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView name;
        private final TextView address;
        private final TextView age;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.cardView_empName);
            address = itemView.findViewById(R.id.cardView_empAddress);
            age = itemView.findViewById(R.id.cardView_empAge);
        }

        public TextView getName() {
            return name;
        }

        public TextView getAddress() {
            return address;
        }

        public TextView getAge() {
            return age;
        }
    }
    @NonNull
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.base_cardview_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder holder, int position) {
        Employee employee = emplist.get(position);
        holder.getName().setText(employee.getName());
        holder.getAddress().setText(employee.getAddress());
        holder.getAge().setText(employee.getAge());

    }

    @Override
    public int getItemCount() {
        return emplist.size();
    }


}
