package com.example.myhomework;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Fragment_chek_in extends Fragment {

    Context c;
    EditText regLogin;
    EditText passwordText;
    EditText emailText;
    Button regloginBtn;
    String password;
    String rLogin;
    String email;
    String url = "https://soldout-t.supplerus.com/auth/";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chek_in, container, false);


        regLogin = (EditText) regLogin.findViewById(R.id.editTextText);
        passwordText = (EditText) passwordText.findViewById(R.id.editTextTextPassword);
        emailText = (EditText) emailText.findViewById(R.id.editTextTextEmailAddress);
        regloginBtn = (Button) regloginBtn.findViewById(R.id.button2);

        regloginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  _("Login button hit");

                rLogin = regLogin.getText() + "";
                password = passwordText.getText() + "";
                email = emailText.getText() +"";

                if ( email.length() == 0 || password.length() == 0) {
                    Toast.makeText(c, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                if ( email.length() > 0 && password.length() > 0) {
                    //Do networking
                    Networking n = new Networking();
                    n.execute(url, Networking.NETWORK_STATE_REGISTER);
                    Toast.makeText(c, "Register Done", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Fragment_chek_in.this, MainActivity.class);
                    startActivity(intent);
                }

            }
        });
    }


    //AsyncTask good for long running tasks
    public class Networking extends AsyncTask {

        public static final int NETWORK_STATE_REGISTER = 1;

        @Override
        protected Object doInBackground(Object[] params) {

            getJson((String) params[0], (Integer) params[1]);
            return null;
        }
    }

    private void getJson(String url, int state) {
        //Do a HTTP POST, more secure than GET
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost request = new HttpPost(url);
        List<NameValuePair> postParameters = new ArrayList<NameValuePair>();

        boolean valid = false;

        switch (state) {
            case Networking.NETWORK_STATE_REGISTER:
                //Building key value pairs to be accessed on web
                postParameters.add(new BasicNameValuePair("username", rLogin));
                postParameters.add(new BasicNameValuePair("email", emailText));
                postParameters.add(new BasicNameValuePair("password", password));

                valid = true;


                break;
            default:
                Toast.makeText(c, "Unknown state", Toast.LENGTH_SHORT).show();

        }

        if (valid == true) {
            //Reads everything that comes from server
            BufferedReader bufferedReader = null;
            StringBuffer stringBuffer = new StringBuffer("");
            try {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(postParameters);
                request.setEntity(entity);

                //Send off to server
                HttpResponse response = httpClient.execute(request);

                //Reads response and gets content
                bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                String line = "";
                String LineSeparator = System.getProperty("line.separator");
                //Read back server output
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuffer.append(line + LineSeparator);
                }

                bufferedReader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            decodeResultIntoJson(stringBuffer.toString());
        } else {
        }
    }

    private void decodeResultIntoJson(String response) {
        if (response.contains("error")) {
            try {
                JSONObject jo = new JSONObject(response);
                String error = jo.getString("error");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        try {
            JSONObject jo = new JSONObject(response);

            String success = jo.getString("success");
            String message = jo.getString("message");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}