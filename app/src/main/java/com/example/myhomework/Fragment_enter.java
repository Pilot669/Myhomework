package com.example.myhomework;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Fragment_enter extends FragmentActivity {

    EditText etEmail, etPassword;
    TextView tvRegister;
    Button btnLogin;

    final String url_Login = "https://soldout-t.supplerus.com/auth/";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_enter, container, false);




//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_enter);

        etEmail = (EditText) findViewById(R.id.EmailAddress);
        etPassword = (EditText) findViewById(R.id.Password);
        btnLogin = (Button) findViewById(R.id.btn_login);
        tvRegister = (TextView) findViewById(R.id.tv_register);


        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Fragment_enter.this,
                        MainActivity.class);
                startActivity(i);
            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = etEmail.getText().toString();
                String Password = etPassword.getText().toString();

                new LoginUser().execute(Email, Password);
            }
        });
    }




    public class LoginUser extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            String Email = strings[0];
            String Password = strings[1];

            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody formBody = new FormBody.Builder()
                    .add("user_id", Email)
                    .add("user_password", Password)
                    .build();

            Request request = new Request.Builder()
                    .url(url_Login)
                    .post(formBody)
                    .build();

            Response response = null;
            try{
                response = okHttpClient.newCall(request).execute();
                if(response.isSuccessful()){
                    String result = response.body().string();
                    if(result.equalsIgnoreCase("login")){
                        Intent i = new Intent(Fragment_enter.this,
                                DashboardActivity.class);
                        startActivity(i);
                        finish();
                    }else{
                        showToast("Email or Password mismatched!");
                    }
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
                Toast.makeText(Fragment_enter.this,
                        Text, Toast.LENGTH_LONG).show();
            }
        });
    }

}