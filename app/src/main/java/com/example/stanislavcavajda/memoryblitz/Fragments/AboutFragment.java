package com.example.stanislavcavajda.memoryblitz.Fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.example.stanislavcavajda.memoryblitz.Helper.GameManager;
import com.example.stanislavcavajda.memoryblitz.R;

import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AboutFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AboutFragment extends Fragment {

    private Button authrButton;
    private boolean up = false;
    private ImageButton bactBtn;
    private  MediaPlayer mp;

    public AboutFragment() {
        // Required empty public constructor
    }


    public static AboutFragment newInstance(String param1, String param2) {
        AboutFragment fragment = new AboutFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        bactBtn = (ImageButton)view.findViewById(R.id.back_btn);
        bactBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        ImageButton view = (ImageButton) v;
                        view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        getFragmentManager().popBackStack();
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

        final RelativeLayout authorLayout = (RelativeLayout)view.findViewById(R.id.author_view);
        authrButton = (Button) view.findViewById(R.id.author);
        authrButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN : {
                        Button view = (Button ) v;
                        view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        if (!up) {
                            authorLayout.animate().translationYBy(-authorLayout.getHeight()).setDuration(1000);
                            up = true;
                        } else {
                            authorLayout.animate().translationYBy(+authorLayout.getHeight()).setDuration(1000);
                            up = false;
                        }
                    }
                    case MotionEvent.ACTION_CANCEL: {
                        Button view = (Button) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        playSound();
                        break;
                    }

                }
                return true;
            }
        });
        return view;
    }


    void playSound() {
        MediaPlayer mp ;
        mp = MediaPlayer.create(getActivity().getApplicationContext(),R.raw.tap1);
        mp.start();
    }
}
