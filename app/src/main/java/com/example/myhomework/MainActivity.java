package com.example.myhomework;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import static android.view.View.GONE;

public class MainActivity extends AppCompatActivity {


//    private Fragment_enter fragment_enter;
//    private Fragment_chek_in fragment_chek_in;
//
//    private FragmentManager manager;
//    private FragmentTransaction transaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
//        manager = getSupportFragmentManager();
//
//        fragment_enter = new Fragment_enter();
//        fragment_chek_in = new Fragment_chek_in();
    }

//    public void onClickFragment1 (View v){
//        transaction = manager.beginTransaction();
//
//        switch (v.getId()) {
//            case R.id.button5 :
//                transaction.add(R.id.conteiner,fragment_enter);
//                break;
//        }
//
//        transaction.commit();
//
//    }
//
//    public void onClickFragment2 (View v){
//        transaction = manager.beginTransaction();
//
//        switch (v.getId()) {
//            case R.id.button6 :
//                transaction.add(R.id.conteiner,fragment_chek_in);
//                break;
//        }
//
//        transaction.commit();
//
//    }
//
  public void Change (View v) {
        Fragment fragment = null;

        switch (v.getId()){
            case R.id.button5:
                fragment = new Fragment_enter();
                break;
            case R.id.button6:
                fragment = new Fragment_chek_in();
                break;
        }
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.conteiner,fragment);
        ft.commit();
  }
}