package com.example.myhomework;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

//import com.google.android.gms.common.api.Response;



public class Fragment_chek_in extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_chek_in, container, false);
        View v = inflater.inflate(R.layout.fragment_chek_in, null);

        Button button = (Button) v.findViewById(R.id.chek_in);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }
}
