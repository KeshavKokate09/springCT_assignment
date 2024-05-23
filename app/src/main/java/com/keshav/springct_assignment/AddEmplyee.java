package com.keshav.springct_assignment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

public class AddEmplyee extends AppCompatActivity {

    private EditText name, age, address;
    private Button addEmpBtn;
    private DBHelper db;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_emplyee);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        address = findViewById(R.id.addres);
        addEmpBtn = findViewById(R.id.submit);
        addEmpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String empName = name.getText().toString();
                String empAge = age.getText().toString();
                String empAddress = address.getText().toString();

                if (empName.isEmpty() || empAge.isEmpty() || empAddress.isEmpty()) {
                    Toast.makeText(AddEmplyee.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                }else{
                    db= new DBHelper(AddEmplyee.this);
                    db.addEmployee(empName, empAge, empAddress);
                    Toast.makeText(AddEmplyee.this, "Employee added successfully", Toast.LENGTH_SHORT).show();
                    updateRecyclerView();
                }
            }
        });
    }

    private void updateRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView);
        CustomAdapter adapter = new CustomAdapter(this, db.getAllEmployees());
        recyclerView.setAdapter(adapter);
    }
}