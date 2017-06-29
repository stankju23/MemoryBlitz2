package com.example.stanislavcavajda.memoryblitz.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.stanislavcavajda.memoryblitz.Helper.GameManager;
import com.example.stanislavcavajda.memoryblitz.R;

import java.util.ArrayList;


public class Graphic_packs extends Fragment {

    private LinearLayout christmasPack;
    private LinearLayout familyPack;
    private LinearLayout westernPack;
    private LinearLayout spacePack;


    private ArrayList<LinearLayout> graphicPacks;

    public Graphic_packs() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Graphic_packs newInstance(String param1, String param2) {
        Graphic_packs fragment = new Graphic_packs();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_graphic_packs, container, false);
        graphicPacks = new ArrayList<>();

        christmasPack = (LinearLayout)view.findViewById(R.id.christmas_package);
        familyPack = (LinearLayout)view.findViewById(R.id.family_package);
        westernPack = (LinearLayout)view.findViewById(R.id.western_package);
        spacePack = (LinearLayout)view.findViewById(R.id.space_package);

        graphicPacks.add(christmasPack);
        graphicPacks.add(familyPack);
        graphicPacks.add(westernPack);
        graphicPacks.add(spacePack);

        graphicPacks.get(GameManager.getInstance().getPackIndex()).setBackgroundColor(Color.parseColor("#D3D3D3"));

        christmasPack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                graphicPacks.get(GameManager.getInstance().getPackIndex()).setBackgroundColor(Color.parseColor("#F4F4F4"));
                GameManager.getInstance().setPackIndex(0);
                graphicPacks.get(GameManager.getInstance().getPackIndex()).setBackgroundColor(Color.parseColor("#D3D3D3"));
                getFragmentManager().popBackStack();
                Log.i("vyber",GameManager.getInstance().getPackIndex() + "");
                saveData();
                playSound();

            }
        });

        familyPack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                graphicPacks.get(GameManager.getInstance().getPackIndex()).setBackgroundColor(Color.parseColor("#F4F4F4"));
                GameManager.getInstance().setPackIndex(1);
                graphicPacks.get(GameManager.getInstance().getPackIndex()).setBackgroundColor(Color.parseColor("#D3D3D3"));
                getFragmentManager().popBackStack();
                Log.i("vyber",GameManager.getInstance().getPackIndex() + "");
                saveData();
                playSound();

            }
        });

        westernPack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                graphicPacks.get(GameManager.getInstance().getPackIndex()).setBackgroundColor(Color.parseColor("#F4F4F4"));
                GameManager.getInstance().setPackIndex(2);
                graphicPacks.get(GameManager.getInstance().getPackIndex()).setBackgroundColor(Color.parseColor("#D3D3D3"));
                getFragmentManager().popBackStack();
                Log.i("vyber",GameManager.getInstance().getPackIndex() + "");
                saveData();
                playSound();
            }
        });

        spacePack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                graphicPacks.get(GameManager.getInstance().getPackIndex()).setBackgroundColor(Color.parseColor("#F4F4F4"));
                GameManager.getInstance().setPackIndex(3);
                graphicPacks.get(GameManager.getInstance().getPackIndex()).setBackgroundColor(Color.parseColor("#D3D3D3"));
                getFragmentManager().popBackStack();
                Log.i("vyber",GameManager.getInstance().getPackIndex() + "");
                saveData();
                playSound();
            }
        });


        return view;
    }

    void saveData(){
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("graphicPackIndex",GameManager.getInstance().getPackIndex());
        editor.commit();
    }

    void playSound() {
        MediaPlayer mp ;
        mp = MediaPlayer.create(getActivity().getApplicationContext(),R.raw.tap1);
        mp.start();
    }
}
