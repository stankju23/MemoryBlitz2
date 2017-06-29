package com.example.stanislavcavajda.memoryblitz.Fragments;


import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.stanislavcavajda.memoryblitz.Helper.GameManager;
import com.example.stanislavcavajda.memoryblitz.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainMenu#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainMenu extends Fragment {

    private ImageButton graphicPacksBtn;
    private ImageButton settingsBtn;
    private ImageButton startGameBtn;
    private ImageButton highScoreBtn;
    private ImageButton aboutBtn;
    private ImageButton darkModeBtn;
    private ImageView logo;
    private MediaPlayer mp ;


    private FrameLayout background;

    public MainMenu() {
        // Required empty public constructor
    }

    public static MainMenu newInstance(String param1, String param2) {
        MainMenu fragment = new MainMenu();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_menu, container, false);

        background = (FrameLayout)view.findViewById(R.id.background);
        logo = (ImageView)view.findViewById(R.id.menu_logo);
        if (!GameManager.getInstance().getDark()) {
            logo.setBackground(getResources().getDrawable(R.drawable.logo));
        } else {
            logo.setBackground(getResources().getDrawable(R.drawable.logo_dark));
        }
        graphicPacksBtn = (ImageButton) view.findViewById(R.id.graphics_pack_btn);
        graphicPacksBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN : {
                        ImageButton view = (ImageButton ) v;
                        view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        FragmentManager fm = getFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        ft.setCustomAnimations(R.anim.enter_from_left,R.anim.exit_to_right,R.anim.enter_from_right,R.anim.exit_to_left);
                        Graphic_packs settings = new Graphic_packs();
                        ft.replace(R.id.fragment_container,settings);
                        ft.addToBackStack("");
                        ft.commit();
                    }
                    case MotionEvent.ACTION_CANCEL: {
                        ImageButton view = (ImageButton) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        playSound();
                        break;
                    }

                }
                return true;
            }
        });

        settingsBtn = (ImageButton) view.findViewById(R.id.settings_btn);
        settingsBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN : {
                        ImageButton view = (ImageButton ) v;
                        view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        FragmentManager fm = getFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        ft.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left,R.anim.enter_from_left,R.anim.exit_to_right);
                        SettingsNew settings = new SettingsNew();
                        ft.replace(R.id.fragment_container,settings);
                        ft.addToBackStack("");
                        ft.commit();
                    }
                    case MotionEvent.ACTION_CANCEL: {
                        ImageButton view = (ImageButton) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        playSound();
                        break;
                    }

                }
                return true;
            }
        });

        startGameBtn = (ImageButton)view.findViewById(R.id.play_game_btn);
        startGameBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN : {
                        ImageButton view = (ImageButton ) v;
                        view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        FragmentManager fm = getFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        ft.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left,R.anim.enter_from_left,R.anim.exit_to_right);
                        GetReadyFragment settings = new GetReadyFragment();
                        ft.replace(R.id.fragment_container,settings);
                        ft.addToBackStack("");
                        ft.commit();
                    }
                    case MotionEvent.ACTION_CANCEL: {
                        ImageButton view = (ImageButton) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        playSound();
                        break;
                    }

                }
                return true;
            }
        });

        highScoreBtn = (ImageButton)view.findViewById(R.id.highscore_btn);
        highScoreBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN : {
                        ImageButton view = (ImageButton ) v;
                        view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        FragmentManager fm = getFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        ft.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left,R.anim.enter_from_left,R.anim.exit_to_right);
                        HighScoreFragment settings = new HighScoreFragment();
                        ft.replace(R.id.fragment_container,settings);
                        ft.addToBackStack("");
                        ft.commit();
                    }
                    case MotionEvent.ACTION_CANCEL: {
                        ImageButton view = (ImageButton) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        playSound();
                        break;
                    }

                }
                return true;
            }
        });


        aboutBtn = (ImageButton) view.findViewById(R.id.about_btn);
        aboutBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN : {
                        ImageButton view = (ImageButton ) v;
                        view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        FragmentManager fm = getFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        ft.setCustomAnimations(R.anim.enter_from_left,R.anim.exit_to_right,R.anim.enter_from_right,R.anim.exit_to_left);
                        AboutFragment settings = new AboutFragment();
                        ft.replace(R.id.fragment_container,settings);
                        ft.addToBackStack("");
                        ft.commit();
                    }
                    case MotionEvent.ACTION_CANCEL: {
                        ImageButton view = (ImageButton) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        playSound();
                        break;
                    }

                }
                return true;
            }
        });

        darkModeBtn = (ImageButton)view.findViewById(R.id.dark_mode);
        darkModeBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN : {
                        ImageButton view = (ImageButton ) v;
                        view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        if (!GameManager.getInstance().getDark()){
                            startGameBtn.setBackground(getResources().getDrawable(R.drawable.start_game_dark_btn));
                            graphicPacksBtn.setBackground(getResources().getDrawable(R.drawable.graphic_pack_dark_btn));
                            settingsBtn.setBackground(getResources().getDrawable(R.drawable.settings_dark_btn));
                            highScoreBtn.setBackground(getResources().getDrawable(R.drawable.highscore_dark_btn));
                            aboutBtn.setBackground(getResources().getDrawable(R.drawable.about_dark_btn));
                            GameManager.getInstance().setDark();
                            background.setBackgroundColor(Color.parseColor("#000B2C"));
                            logo.setBackground(getResources().getDrawable(R.drawable.logo_dark));
                        } else {
                            startGameBtn.setBackground(getResources().getDrawable(R.drawable.start_game_btn));
                            graphicPacksBtn.setBackground(getResources().getDrawable(R.drawable.graphic_pack_btn));
                            settingsBtn.setBackground(getResources().getDrawable(R.drawable.settings_btn));
                            highScoreBtn.setBackground(getResources().getDrawable(R.drawable.highscore_btn));
                            aboutBtn.setBackground(getResources().getDrawable(R.drawable.about_btn));
                            GameManager.getInstance().setDark();
                            background.setBackground(getResources().getDrawable(R.drawable.main_menu_bg));
                            logo.setBackground(getResources().getDrawable(R.drawable.logo));
                        }
                    }
                    case MotionEvent.ACTION_CANCEL: {
                        ImageButton view = (ImageButton) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        playSound();
                        break;
                    }

                }
                return true;
            }
        });

        if (GameManager.getInstance().getDark()) {
            startGameBtn.setBackground(getResources().getDrawable(R.drawable.start_game_dark_btn));
            graphicPacksBtn.setBackground(getResources().getDrawable(R.drawable.graphic_pack_dark_btn));
            settingsBtn.setBackground(getResources().getDrawable(R.drawable.settings_dark_btn));
            highScoreBtn.setBackground(getResources().getDrawable(R.drawable.highscore_dark_btn));
            aboutBtn.setBackground(getResources().getDrawable(R.drawable.about_dark_btn));
            background.setBackgroundColor(Color.parseColor("#000B2C"));
        } else {
            startGameBtn.setBackground(getResources().getDrawable(R.drawable.start_game_btn));
            graphicPacksBtn.setBackground(getResources().getDrawable(R.drawable.graphic_pack_btn));
            settingsBtn.setBackground(getResources().getDrawable(R.drawable.settings_btn));
            highScoreBtn.setBackground(getResources().getDrawable(R.drawable.highscore_btn));
            aboutBtn.setBackground(getResources().getDrawable(R.drawable.about_btn));
            background.setBackground(getResources().getDrawable(R.drawable.main_menu_bg));
        }
        return view;
    }
    void playSound() {
        MediaPlayer mp ;
        mp = MediaPlayer.create(getActivity().getApplicationContext(),R.raw.tap1);
        mp.start();
    }
}
