package com.example.stanislavcavajda.memoryblitz.Fragments;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

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
        graphicPacksBtn = (ImageButton) view.findViewById(R.id.graphics_pack_btn);
        graphicPacksBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.setCustomAnimations(R.anim.enter_from_left,R.anim.exit_to_right,R.anim.enter_from_right,R.anim.exit_to_left);
                Graphic_packs graphic_packs = new Graphic_packs();
                ft.replace(R.id.fragment_container,graphic_packs);
                ft.addToBackStack("");
                ft.commit();
            }
        });

        settingsBtn = (ImageButton) view.findViewById(R.id.settings_btn);
        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left,R.anim.enter_from_left,R.anim.exit_to_right);
                SettingsNew settings = new SettingsNew();
                ft.replace(R.id.fragment_container,settings);
                ft.addToBackStack("");
                ft.commit();
            }
        });

        startGameBtn = (ImageButton)view.findViewById(R.id.play_game_btn);
        startGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                //ft.setCustomAnimations(R.anim.slide_in_up,R.anim.slide_out_up,R.anim.slide_out_up,R.anim.slide_in_up);
                ft.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                GetReadyFragment settings = new GetReadyFragment();
                ft.replace(R.id.fragment_container,settings);
                ft.addToBackStack("");
                ft.commit();
            }
        });

        highScoreBtn = (ImageButton)view.findViewById(R.id.highscore_btn);
        highScoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left,R.anim.enter_from_left,R.anim.exit_to_right);
                HighScoreFragment settings = new HighScoreFragment();
                ft.replace(R.id.fragment_container,settings);
                ft.addToBackStack("");
                ft.commit();
            }
        });

        aboutBtn = (ImageButton) view.findViewById(R.id.about_btn);
        aboutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.setCustomAnimations(R.anim.enter_from_left,R.anim.exit_to_right,R.anim.enter_from_right,R.anim.exit_to_left);
                AboutFragment settings = new AboutFragment();
                ft.replace(R.id.fragment_container,settings);
                ft.addToBackStack("");
                ft.commit();
            }
        });


        return view;
    }
}
