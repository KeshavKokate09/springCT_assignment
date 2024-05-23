package com.keshav.springct_assignment;

import android.content.Intent;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class logIn extends AppCompatActivity {

    private EditText username,password;
    private Button loginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_log_in);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginBtn = findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=username.getText().toString().trim();
                String pass=password.getText().toString().trim();

                if(user.isEmpty()  || pass.isEmpty()){
                    Toast.makeText(logIn.this,"Please fill all the fields",Toast.LENGTH_SHORT).show();
                }else{
                    postRequest(user,pass);
                }

            }
        });
    }

    private void postRequest(String user, String pass) {
        Retrofit retrofit = new  Retrofit.Builder()
                .baseUrl("https://reqres.in/api/login")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        RetroFitAPI retroFitAPI = retrofit.create(RetroFitAPI.class);
        User user1 = new User(user,pass);

        Call<User> call = retroFitAPI.createPost(user1);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user2 = response.body();
                Bundle b = new Bundle();
                b.putString("username",user2.getUserName());
                b.putString("password",user2.getPassword());
                startActivity(new Intent(logIn.this, HomePage.class).putExtras(b));
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(logIn.this,"error While post request"+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }
}