package com.example.stanislavcavajda.memoryblitz.Activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.stanislavcavajda.memoryblitz.Fragments.MainMenu;
import com.example.stanislavcavajda.memoryblitz.Helper.GameManager;
import com.example.stanislavcavajda.memoryblitz.R;
import com.google.android.gms.ads.MobileAds;


public class MainActivity extends AppCompatActivity{

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MobileAds.initialize(this,"ca-app-pub-7144286645481402~7366785574");

        int currentApiVersion = Build.VERSION.SDK_INT;

        final int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

        // This work only for android 4.4+
        if (currentApiVersion >= Build.VERSION_CODES.KITKAT) {
            getWindow().getDecorView().setSystemUiVisibility(flags);
            final View decorView = getWindow().getDecorView();
            decorView
                    .setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {

                        @Override
                        public void onSystemUiVisibilityChange(int visibility) {
                            if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                                decorView.setSystemUiVisibility(flags);
                            }
                        }
                    });
        }
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
