package com.example.myhomework;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

        public void Change (View v){
            Fragment fragment = null;

            switch (v.getId()) {
                case R.id.button5:
                 fragment = new Fragment_enter();
                    break;
                case R.id.button6:
                 fragment = new Fragment_chek_in();
                    break;
            }
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.conteiner, fragment);
            ft.commit();

        }
        
    }




