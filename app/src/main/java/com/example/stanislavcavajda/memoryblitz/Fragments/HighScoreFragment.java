package com.example.stanislavcavajda.memoryblitz.Fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.stanislavcavajda.memoryblitz.Helper.GameManager;
import com.example.stanislavcavajda.memoryblitz.R;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HighScoreFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HighScoreFragment extends Fragment {

    private TextView twoxtwoScore;
    private TextView twoxthreeScore;
    private TextView threexthreeScore;

    private Button okBtn;

    public HighScoreFragment() {
        // Required empty public constructor
    }

    public static HighScoreFragment newInstance(String param1, String param2) {
        HighScoreFragment fragment = new HighScoreFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("highscore",MODE_PRIVATE);
        GameManager.getInstance().setTwoxtwoHighScore(sharedPreferences.getInt("twoxtwo",0));
        GameManager.getInstance().setTwoxthreeHighScore(sharedPreferences.getInt("twoxthree",0));
        GameManager.getInstance().setThreexthreeHighScore(sharedPreferences.getInt("threexthree",0));

        View view = inflater.inflate(R.layout.fragment_high_score, container, false);
        FrameLayout mainLayout = (FrameLayout) view.findViewById(R.id.high_score_layout);
        if (GameManager.getInstance().getDark()){
            mainLayout.setBackgroundColor(Color.parseColor("#000B2C"));
        } else {
            mainLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }

        twoxtwoScore = (TextView)view.findViewById(R.id.twoxtwo_score);
        twoxthreeScore = (TextView)view.findViewById(R.id.twoxthree_score);
        threexthreeScore = (TextView)view.findViewById(R.id.threexthree_score);

        twoxtwoScore.setText(GameManager.getInstance().getTwoxtwoHighScore() + "");
        twoxthreeScore.setText(GameManager.getInstance().getTwoxthreeHighScore() + "");
        threexthreeScore.setText(GameManager.getInstance().getThreexthreeHighScore() + "");

        okBtn = (Button) view.findViewById(R.id.highscore_ok_btn);
        okBtn.setOnTouchListener(new View.OnTouchListener() {
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
                        getFragmentManager().popBackStack();
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
