package com.example.stanislavcavajda.memoryblitz.Fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.stanislavcavajda.memoryblitz.Helper.GameManager;
import com.example.stanislavcavajda.memoryblitz.R;

import info.hoang8f.android.segmented.SegmentedGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingsNew#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsNew extends Fragment {

    private SegmentedGroup numberOfCards;
    private SegmentedGroup cardsMatrix;
    private NumberPicker numberPicker;
    private Button saveBtn;
    private SoundPool mp;
    private LinearLayout mainLayout;

    public SettingsNew() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static SettingsNew newInstance(String param1, String param2) {
        SettingsNew fragment = new SettingsNew();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings_new, container, false);

        mainLayout = (LinearLayout) view.findViewById(R.id.main_layout);
        if (GameManager.getInstance().getDark()){
            mainLayout.setBackgroundColor(Color.parseColor("#000B2C"));
        } else {
            mainLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        Log.i("Number of cards index",GameManager.getInstance().getNumbersOfCardsIndex() + "");
        Log.i("Cards matrix",GameManager.getInstance().getMatrixIndex() + "");
        // numbers of cards initialization
        numberOfCards = (SegmentedGroup)view.findViewById(R.id.numberOfCards);

        numberOfCards.check(R.id.first);
        final int checkedNumber = numberOfCards.getCheckedRadioButtonId();

        numberOfCards.setTintColor(Color.parseColor("#ff6829"));
        if(GameManager.getInstance().getNumbersOfCardsIndex() == 0) {
            numberOfCards.check(R.id.first);
        } else if(GameManager.getInstance().getNumbersOfCardsIndex() == 1) {
            numberOfCards.check(R.id.second);
        } else if(GameManager.getInstance().getNumbersOfCardsIndex() == 2) {
            numberOfCards.check(R.id.third);
        }

        numberOfCards.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                GameManager.getInstance().setNumbersOfCardsIndex(numberOfCards.getCheckedRadioButtonId() - checkedNumber);
            }
        });


        // cards matrix initialization
        cardsMatrix = (SegmentedGroup)view.findViewById(R.id.cardsMatrix);
        cardsMatrix.setTintColor(Color.parseColor("#ff6829"));

        cardsMatrix.check(R.id.twoxtwo);
        final  int checkedMatrix = cardsMatrix.getCheckedRadioButtonId();
        if(GameManager.getInstance().getMatrixIndex() == 0) {
            cardsMatrix.check(R.id.twoxtwo);
        } else if(GameManager.getInstance().getMatrixIndex() == 1) {
            cardsMatrix.check(R.id.twoxthree);
        } else if(GameManager.getInstance().getMatrixIndex() == 2) {
            cardsMatrix.check(R.id.threexthree);
        }

        cardsMatrix.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                GameManager.getInstance().setMatrixIndex(cardsMatrix.getCheckedRadioButtonId() - checkedMatrix);
            }
        });

        numberPicker = (NumberPicker) view.findViewById(R.id.number_picker);
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(17);
        numberPicker.setValue(GameManager.getInstance().getSecondsToRemember());
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                GameManager.getInstance().setSecondsToRemember(numberPicker.getValue());
            }
        });

        saveBtn = (Button) view.findViewById(R.id.save);
        saveBtn.setOnTouchListener(new View.OnTouchListener() {
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
                        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putInt("numberOfCardsIndex",GameManager.getInstance().getNumbersOfCardsIndex());
                        editor.putInt("cardsMatrixIndex",GameManager.getInstance().getMatrixIndex());
                        editor.putInt("secondsToRemember",GameManager.getInstance().getSecondsToRemember());
                        editor.commit();
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
