package com.example.stanislavcavajda.memoryblitz.Fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;

import com.example.stanislavcavajda.memoryblitz.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment {

    private Button twoXtwoButton;
    private Button twoXthreeButton;
    private Button threeXthreeButton;
    private NumberPicker numberPicker;
    private Button cards1;
    private Button cards2;
    private Button cards3;
    private Button saveButton;

    public SettingsFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        twoXtwoButton = (Button) view.findViewById(R.id.two_x_two_btn);
        twoXthreeButton = (Button) view.findViewById(R.id.two_x_three_btn);
        threeXthreeButton = (Button) view.findViewById(R.id.three_x_three_btn);
        cards1 = (Button) view.findViewById(R.id.cards_number1);
        cards2 = (Button) view.findViewById(R.id.cards_number2);
        cards3 = (Button) view.findViewById(R.id.cards_number3);
        saveButton = (Button) view.findViewById(R.id.save);

        twoXtwoButton.setTextColor(getResources().getColor(R.color.buttonSettingsColor));
        twoXthreeButton.setTextColor(getResources().getColor(R.color.buttonSettingsColor));
        threeXthreeButton.setTextColor(getResources().getColor(R.color.buttonSettingsColor));

        cards1.setTextColor(getResources().getColor(R.color.buttonSettingsColor));
        cards2.setTextColor(getResources().getColor(R.color.buttonSettingsColor));
        cards3.setTextColor(getResources().getColor(R.color.buttonSettingsColor));

        twoXtwoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                twoXtwoButton.setBackgroundColor(getResources().getColor(R.color.buttonSettingsColor));
                twoXtwoButton.setTextColor(Color.parseColor("#FFFFFF"));

                twoXthreeButton.setTextColor(getResources().getColor(R.color.buttonSettingsColor));
                threeXthreeButton.setTextColor(getResources().getColor(R.color.buttonSettingsColor));

                twoXthreeButton.setBackground(getResources().getDrawable(R.drawable.settings_button));
                threeXthreeButton.setBackground(getResources().getDrawable(R.drawable.settings_button));
            }
        });

        twoXthreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                twoXthreeButton.setBackgroundColor(getResources().getColor(R.color.buttonSettingsColor));
                twoXthreeButton.setTextColor(Color.parseColor("#FFFFFF"));

                twoXtwoButton.setTextColor(getResources().getColor(R.color.buttonSettingsColor));
                threeXthreeButton.setTextColor(getResources().getColor(R.color.buttonSettingsColor));

                twoXtwoButton.setBackground(getResources().getDrawable(R.drawable.settings_button));
                threeXthreeButton.setBackground(getResources().getDrawable(R.drawable.settings_button));
            }
        });

        threeXthreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                threeXthreeButton.setBackgroundColor(getResources().getColor(R.color.buttonSettingsColor));
                threeXthreeButton.setTextColor(Color.parseColor("#FFFFFF"));

                twoXtwoButton.setTextColor(getResources().getColor(R.color.buttonSettingsColor));
                twoXthreeButton.setTextColor(getResources().getColor(R.color.buttonSettingsColor));

                twoXtwoButton.setBackground(getResources().getDrawable(R.drawable.settings_button));
                twoXthreeButton.setBackground(getResources().getDrawable(R.drawable.settings_button));
            }
        });


        cards1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cards1.setBackgroundColor(getResources().getColor(R.color.buttonSettingsColor));
                cards1.setTextColor(Color.parseColor("#FFFFFF"));

                cards2.setTextColor(getResources().getColor(R.color.buttonSettingsColor));
                cards3.setTextColor(getResources().getColor(R.color.buttonSettingsColor));

                cards2.setBackground(getResources().getDrawable(R.drawable.settings_button));
                cards3.setBackground(getResources().getDrawable(R.drawable.settings_button));
            }
        });

        cards2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cards2.setBackgroundColor(getResources().getColor(R.color.buttonSettingsColor));
                cards2.setTextColor(Color.parseColor("#FFFFFF"));

                cards1.setTextColor(getResources().getColor(R.color.buttonSettingsColor));
                cards3.setTextColor(getResources().getColor(R.color.buttonSettingsColor));

                cards1.setBackground(getResources().getDrawable(R.drawable.settings_button));
                cards3.setBackground(getResources().getDrawable(R.drawable.settings_button));
            }
        });

        cards3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cards3.setBackgroundColor(getResources().getColor(R.color.buttonSettingsColor));
                cards3.setTextColor(Color.parseColor("#FFFFFF"));

                cards1.setTextColor(getResources().getColor(R.color.buttonSettingsColor));
                cards2.setTextColor(getResources().getColor(R.color.buttonSettingsColor));

                cards1.setBackground(getResources().getDrawable(R.drawable.settings_button));
                cards2.setBackground(getResources().getDrawable(R.drawable.settings_button));
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

        numberPicker = (NumberPicker)view.findViewById(R.id.number_picker);
        numberPicker.setMinValue(2);
        numberPicker.setMaxValue(17);





        return view;
    }

}
