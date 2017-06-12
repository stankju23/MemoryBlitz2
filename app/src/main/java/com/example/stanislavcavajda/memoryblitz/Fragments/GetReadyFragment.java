package com.example.stanislavcavajda.memoryblitz.Fragments;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.stanislavcavajda.memoryblitz.Activities.MainGame;
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

        new CountDownTimer(5000,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                secondsText.setText(String.valueOf(millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(getContext(), MainGame.class);
                startActivity(intent);
            }
        }.start();


        return view;
    }

}
