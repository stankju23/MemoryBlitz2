package com.example.stanislavcavajda.memoryblitz.Activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.example.stanislavcavajda.memoryblitz.Fragments.MainMenu;
import com.example.stanislavcavajda.memoryblitz.Helper.GameManager;
import com.example.stanislavcavajda.memoryblitz.R;


public class  MainActivity extends AppCompatActivity{

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // make navigation bar transparent
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        //make fullscreen activity
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //add main menu fragment on start screen
        MainMenu mainMenuFragment = new MainMenu();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container,mainMenuFragment);
        fragmentTransaction.commit();


        // loading saved data
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        GameManager.getInstance().setPackIndex(sharedPreferences.getInt("graphicPackIndex",0));
        GameManager.getInstance().setNumbersOfCardsIndex(sharedPreferences.getInt("numberOfCardsIndex",0));
        GameManager.getInstance().setMatrixIndex(sharedPreferences.getInt("cardsMatrixIndex",0));
        GameManager.getInstance().setSecondsToRemember(sharedPreferences.getInt("secondsToRemember",0));
    }

}
