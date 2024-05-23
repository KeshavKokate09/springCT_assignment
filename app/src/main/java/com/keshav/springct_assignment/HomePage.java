package com.keshav.springct_assignment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HomePage extends AppCompatActivity {

    private Button addEmp;
    private RecyclerView recyclerView;
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        db = new DBHelper(this);

        addEmp = findViewById(R.id.addempBtn);
        recyclerView = findViewById(R.id.recyclerView);
        updateRecyclerView();
        addEmp.setOnClickListener(v -> {
            Intent intent = new Intent(HomePage.this, AddEmplyee.class);
            startActivity(intent);
        });
    }

    private void updateRecyclerView() {
        CustomAdapter adapter = new CustomAdapter(db.getAllEmployees());
        recyclerView.setAdapter(adapter);
    }
}