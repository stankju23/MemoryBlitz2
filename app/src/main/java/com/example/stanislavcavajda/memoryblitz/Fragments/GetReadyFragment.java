package com.example.stanislavcavajda.memoryblitz.Fragments;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.stanislavcavajda.memoryblitz.Activities.MainGame;
import com.example.stanislavcavajda.memoryblitz.Helper.GameManager;
import com.example.stanislavcavajda.memoryblitz.R;


public class GetReadyFragment extends Fragment {

    private TextView getReadyText;
    private TextView secondsText;


    public GetReadyFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static GetReadyFragment newInstance(String param1, String param2) {
        GetReadyFragment fragment = new GetReadyFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_get_ready, container, false);
        getReadyText = (TextView) view.findViewById(R.id.getReadyText);
        getReadyText.setTypeface(getReadyText.getTypeface(), Typeface.ITALIC);
        secondsText = (TextView) view.findViewById(R.id.secondsText);
        secondsText.setTypeface(secondsText.getTypeface(), Typeface.ITALIC);
        ImageView circle = (ImageView)view.findViewById(R.id.circle);

        ConstraintLayout mainLayout = (ConstraintLayout) view.findViewById(R.id.get_ready_layout);
        if (GameManager.getInstance().getDark()){
            mainLayout.setBackgroundColor(Color.parseColor("#092f57"));
            getReadyText.setTextColor(Color.WHITE);
            secondsText.setTextColor(Color.WHITE);
            circle.setImageResource(R.drawable.circle_dark);

        } else {
            mainLayout.setBackgroundColor(Color.parseColor("#FFF600"));
            getReadyText.setTextColor(Color.BLACK);
            secondsText.setTextColor(Color.BLACK);
            circle.setImageResource(R.drawable.circle_element);
        }

        new CountDownTimer(5000,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                secondsText.animate().scaleXBy(.3f).setDuration(500);
                secondsText.animate().scaleYBy(.3f).setDuration(500);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        secondsText.animate().scaleXBy(-.3f).setDuration(500);
                        secondsText.animate().scaleYBy(-.3f).setDuration(500);
                    }
                },500);

                secondsText.setText(String.valueOf(millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(getActivity().getApplicationContext(),MainGame.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.abc_slide_in_bottom,R.anim.abc_slide_out_bottom);
            }
        }.start();


        return view;
    }

}
