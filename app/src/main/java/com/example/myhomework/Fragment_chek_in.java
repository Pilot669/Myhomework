package com.example.myhomework;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//import com.google.android.gms.common.api.Response;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class Fragment_chek_in extends FragmentActivity {

    EditText etName, etEmail, etPassword;
    Button btnRegister;

    final String url_Register = "https://soldout-t.supplerus.com/auth/";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chek_in, container, false);

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_chek_in);

        etName = (EditText) findViewById(R.id.Reg_login_neam);
        etEmail = (EditText) findViewById(R.id.Reg_EmailAddress);
        etPassword = (EditText) findViewById(R.id.Reg_Password);
        btnRegister = (Button) findViewById(R.id.chek_in);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = etName.getText().toString();
                String Email = etEmail.getText().toString();
                String Password = etPassword.getText().toString();

                new RegisterUser().execute(Name, Email, Password);
            }
        });
    }



public class RegisterUser extends AsyncTask<String, Void, String>{

    @Override
    protected String doInBackground(String... strings) {
        String Name = strings[0];
        String Email = strings[1];
        String Password = strings[2];

        String finalURL = url_Register + "?user_name=" + Name +
                "&user_id=" + Email +
                "&user_password=" + Password;

        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(finalURL)
                    .get()
                    .build();
            Response response = null;

            try {
                response = okHttpClient.newCall(request).execute();
                if (response.isSuccessful()) {
                    String result = response.body().string();

                    if (result.equalsIgnoreCase("User registered successfully")) {
                        showToast("Register successful");
                        Intent i = new Intent(Fragment_chek_in.this,
                               MainActivity.class);
                        startActivity(i);
                        finish();
                    } else if (result.equalsIgnoreCase("User already exists")) {
                        showToast("User already exists");
                    } else {
                        showToast("oop! please try again");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}


    public void showToast(final String Text){
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(Fragment_chek_in.this,
                        Text, Toast.LENGTH_LONG).show();
            }
        });
    }
}