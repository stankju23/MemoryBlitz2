package com.example.stanislavcavajda.memoryblitz.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.example.stanislavcavajda.memoryblitz.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AboutFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AboutFragment extends Fragment {

    private Button authrButton;
    private boolean up = false;
    private ImageButton bactBtn;


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
        bactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
        final RelativeLayout authorLayout = (RelativeLayout)view.findViewById(R.id.author_view);
        authrButton = (Button) view.findViewById(R.id.author);
        authrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!up) {
                    authorLayout.animate().translationYBy(-authorLayout.getHeight()).setDuration(1000);
                    up = true;
                } else {
                    authorLayout.animate().translationYBy(+authorLayout.getHeight()).setDuration(1000);
                    up = false;
                }
            }
        });

        return view;
    }

}
