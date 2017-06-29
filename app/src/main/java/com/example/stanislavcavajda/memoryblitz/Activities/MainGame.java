package com.example.stanislavcavajda.memoryblitz.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.stanislavcavajda.memoryblitz.BuildConfig;
import com.example.stanislavcavajda.memoryblitz.Helper.GameManager;
import com.example.stanislavcavajda.memoryblitz.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MainGame extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private ImageButton endGame;

    private List<ImageButton> gameButtons;
    private List<ImageButton> wantedButtons;

    private List<Integer> pictureNumbers;
    private List<Integer> drawedNumbers;
    private List<Integer> wantedNumbers;

    private TextView winnerText;
    private TextView retryText;
    private TextView highScoreText;

    private int clickedButton = 0;
    private int k = 0;
    private boolean allFind = false;
    private boolean retryGame = false;
    private int highScore = 0;
    private int obrazok = 0;

    private LinearLayout mainLayout;
    private LinearLayout memorise;
    private LinearLayout buttons;
    private LinearLayout cards;


    private CountDownTimer countDownTimer;

    private int timeRemaining;

    private InterstitialAd mInterstitialAd;

    private Handler handler;
    private Runnable runnable;
    private MediaPlayer mp ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (BuildConfig.FLAVOR.equals("lite")) {
            mInterstitialAd = new InterstitialAd(this);
            //mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712"); // test add
            mInterstitialAd.setAdUnitId("ca-app-pub-7144286645481402/8843518771");
            mInterstitialAd.loadAd(new AdRequest.Builder().build());
        }

        // hiding navigation bar and make activity fullscreen
        int currentApiVersion = Build.VERSION.SDK_INT;


        final int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

        // This work only for android 4.4+
        if (currentApiVersion >= Build.VERSION_CODES.KITKAT) {
            getWindow().getDecorView().setSystemUiVisibility(flags);
            final View decorView = getWindow().getDecorView();
            decorView
                    .setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {

                        @Override
                        public void onSystemUiVisibilityChange(int visibility) {
                            if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                                decorView.setSystemUiVisibility(flags);
                            }
                        }
                    });
        }
        setContentView(R.layout.activity_main_game);

        mainLayout = (LinearLayout)findViewById(R.id.main_game_layout);

        if (GameManager.getInstance().getDark()) {
            mainLayout.setBackgroundColor(Color.parseColor("#1D1313"));
        } else {
            mainLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }


        memorise = (LinearLayout)findViewById(R.id.memorise_layout);
        buttons = (LinearLayout)findViewById(R.id.buttons_layout);
        cards = (LinearLayout)findViewById(R.id.cards_layout);

        if (GameManager.getInstance().getDark()) {
            memorise.setBackground(getResources().getDrawable(R.drawable.settings_background_dark));
        }
        else
        {
            memorise.setBackground(getResources().getDrawable(R.drawable.settings_button));
        }

        if (GameManager.getInstance().getDark()) {
            buttons.setBackground(getResources().getDrawable(R.drawable.settings_background_dark));
        }
        else
        {
            buttons.setBackground(getResources().getDrawable(R.drawable.settings_button));
        }

        if (GameManager.getInstance().getDark()) {
            cards.setBackground(getResources().getDrawable(R.drawable.settings_background_dark));
        }
        else
        {
            cards.setBackground(getResources().getDrawable(R.drawable.settings_button));
        }

        winnerText = (TextView) findViewById(R.id.winner_text);
        winnerText.setTextColor(Color.parseColor("#9A9A9A"));
        winnerText.setText("MEMORISE");

        retryText = (TextView) findViewById(R.id.retry);
        retryText.setTextColor(Color.parseColor("#9A9A9A"));
        Typeface font = Typeface.createFromAsset(getAssets(), "digital.ttf");
        retryText.setTypeface(font);
        retryText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retryGame();
            }
        });
        retryText.setClickable(false);

        highScore = GameManager.getInstance().getActualScore();


        highScoreText = (TextView) findViewById(R.id.high_score_text);
        highScoreText.setTextColor(Color.parseColor("#9A9A9A"));
        highScoreText.setText(highScore + "");

        initializeGame();

        retryText.setText(GameManager.getInstance().getSecondsToRemember() + "");

        countDownTimer = new CountDownTimer((GameManager.getInstance().getSecondsToRemember() + 1) * 1000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

                winnerText.setText("MEMORISE");
                if (millisUntilFinished / 1000 < 10) {
                    retryText.setText("0:0" + millisUntilFinished / 1000);
                } else {
                    retryText.setText("0:" + millisUntilFinished / 1000);
                }
            }

            @Override
            public void onFinish() {
                hideAll();
                showWantedImages();
                retryText.setText("0:00");
                activeAllBtns();
            }
        }.start();

        endGame = (ImageButton) findViewById(R.id.end_game);
        endGame.setAlpha(0f);
        endGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound();
                if (GameManager.getInstance().getMatrixIndex() == 0) {
                    if (GameManager.getInstance().getTwoxtwoHighScore() < highScore) {
                        GameManager.getInstance().setTwoxtwoHighScore(highScore);
                    }
                }
                if (GameManager.getInstance().getMatrixIndex() == 1) {
                    if (GameManager.getInstance().getTwoxthreeHighScore() < highScore) {
                        GameManager.getInstance().setTwoxthreeHighScore(highScore);
                    }
                }
                if (GameManager.getInstance().getMatrixIndex() == 2) {
                    if (GameManager.getInstance().getThreexthreeHighScore() < highScore) {
                        GameManager.getInstance().setThreexthreeHighScore(highScore);
                    }
                }
                saveHighScore();
                GameManager.getInstance().setActualScore(0);
                startMenu();
            }
        });
        endGame.setClickable(false);
    }


    boolean setAllFind() {
        boolean nasiel = false;
        for (int k = 0; k < wantedNumbers.size(); k++) {
            if (wantedNumbers.size() != 0) {
                if (clickedButton == wantedNumbers.get(k)) {
                    nasiel = true;
                    wantedNumbers.remove(k);
                } else {
                    allFind = false;
                }
            }
        }
        if (wantedNumbers.size() == 0) {
            allFind = true;
        }

        if (allFind) {
            if (GameManager.getInstance().getMatrixIndex() == 0) {
                highScore++;
                GameManager.getInstance().setWinner(true);
                GameManager.getInstance().setActualScore(highScore);
                if (GameManager.getInstance().getTwoxtwoHighScore() < highScore) {
                    GameManager.getInstance().setTwoxtwoHighScore(highScore);
                }
                retryText.setClickable(false);
                winnerText.setText("Winner");
                highScoreText.setText(highScore + "");
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                        overridePendingTransition(R.anim.abc_slide_in_bottom, R.anim.abc_slide_out_bottom);
                    }
                }, 2000);

            }
            if (GameManager.getInstance().getMatrixIndex() == 1) {
                highScore++;
                GameManager.getInstance().setWinner(true);
                GameManager.getInstance().setActualScore(highScore);
                if (GameManager.getInstance().getTwoxthreeHighScore() < highScore) {
                    GameManager.getInstance().setTwoxthreeHighScore(highScore);
                }
                retryText.setClickable(false);
                winnerText.setText("Winner");
                highScoreText.setText(highScore + "");
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                        overridePendingTransition(R.anim.abc_slide_in_bottom, R.anim.abc_slide_out_bottom);
                    }
                }, 2000);
            }
            if (GameManager.getInstance().getMatrixIndex() == 2) {
                highScore++;
                GameManager.getInstance().setWinner(true);
                GameManager.getInstance().setActualScore(highScore);
                if (GameManager.getInstance().getThreexthreeHighScore() < highScore) {
                    GameManager.getInstance().setThreexthreeHighScore(highScore);
                }
                retryText.setClickable(false);
                winnerText.setText("Winner");
                highScoreText.setText(highScore + "");
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                        overridePendingTransition(R.anim.abc_slide_in_bottom, R.anim.abc_slide_out_bottom);
                    }
                }, 2000);
            }
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                    overridePendingTransition(R.anim.abc_slide_in_bottom, R.anim.abc_slide_out_bottom);
                }
            },2000);

        }
        return nasiel;
    }



    void startMenu() {
        this.finish();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.abc_slide_in_bottom,R.anim.abc_slide_out_bottom);
    }

    @Override
    public void onBackPressed() {

    }

    void initializeGame() {
        int cards = 0;
        if (GameManager.getInstance().getMatrixIndex() == 0) {
            cards = 4;
        } else if (GameManager.getInstance().getMatrixIndex() == 1) {
            cards = 6;
        } else if (GameManager.getInstance().getMatrixIndex() == 2) {
            cards = 9;
        }

        gameButtons = new ArrayList<>();
        wantedButtons = new ArrayList<>();
        pictureNumbers = new ArrayList<>();
        wantedNumbers = new ArrayList<>();
        drawedNumbers = new ArrayList<>();

        for (int i = 1; i < 10; i++) {
            pictureNumbers.add(i);
        }
        Collections.shuffle(pictureNumbers);


        for (int i = 1; i < 10; i++) {
            String imageBtn = "image" + i;
            int id = getResources().getIdentifier(imageBtn, "id", getApplicationContext().getPackageName());
            ImageButton image = (ImageButton) findViewById(id);
            gameButtons.add(image);
        }

        for (int i = 0; i < cards; i++) {
            String resource = GameManager.getInstance().getGraphicPackName() + pictureNumbers.get(i);
            int resId = getResources().getIdentifier(resource, "drawable", getApplicationContext().getPackageName());
            gameButtons.get(i).setImageResource(resId);
            drawedNumbers.add(pictureNumbers.get(i));
            if (GameManager.getInstance().getDark()) {
                gameButtons.get(i).setBackground(getResources().getDrawable(R.drawable.settings_background_dark));
            }
            else
            {
                gameButtons.get(i).setBackground(getResources().getDrawable(R.drawable.settings_button));
            }
        }

        for (int i = cards; i < gameButtons.size(); i++) {

            if (GameManager.getInstance().getDark()) {
                gameButtons.get(i).setBackground(getResources().getDrawable(R.drawable.settings_background_dark));
                int resId = getResources().getIdentifier("hidecard_dark", "drawable", getApplicationContext().getPackageName());
                gameButtons.get(i).setImageResource(resId);
            }
            else
            {
                int resId = getResources().getIdentifier("hidecard", "drawable", getApplicationContext().getPackageName());
                gameButtons.get(i).setImageResource(resId);
                gameButtons.get(i).setBackground(getResources().getDrawable(R.drawable.settings_button));
            }
        }

        for (int i = 1; i < 4; i++) {
            String imageBtn = "wanted_image" + i;
            int id = getResources().getIdentifier(imageBtn, "id", getApplicationContext().getPackageName());
            ImageButton image = (ImageButton) findViewById(id);
            wantedButtons.add(image);
        }
        for (int i = 0 ; i < wantedButtons.size(); i++) {
            if (GameManager.getInstance().getDark()) {
                wantedButtons.get(i).setBackground(getResources().getDrawable(R.drawable.settings_background_dark));
            }
            else
            {
                wantedButtons.get(i).setBackground(getResources().getDrawable(R.drawable.settings_button));
            }
        }

        for (int i = 0; i < drawedNumbers.size(); i++) {
            Log.i("cislo", drawedNumbers.get(i) + "");
        }
        unactiveAllBtns();
    }


    void showWantedImages() {
        ArrayList<Integer> helper = new ArrayList<>();
        for (int i = 0; i < drawedNumbers.size(); i++) {
            helper.add(drawedNumbers.get(i));
        }

        for (int i = 0; i < 4; i++) {
            Collections.shuffle(helper);
        }

        for (int i = 0; i < GameManager.getInstance().getNumbersOfCardsIndex() + 1; i++) {
            wantedNumbers.add(helper.get(i));
        }



        for (int i = 0; i < GameManager.getInstance().getNumbersOfCardsIndex() + 1; i++) {
            String resource = GameManager.getInstance().getGraphicPackName() + wantedNumbers.get(i);
            int resId = getResources().getIdentifier(resource, "drawable", getApplicationContext().getPackageName());
            wantedButtons.get(i).setImageResource(resId);
        }

        for (int i = 0 ; i < wantedButtons.size();i++) {
            if (GameManager.getInstance().getDark()) {
                wantedButtons.get(i).setBackground(getResources().getDrawable(R.drawable.settings_background_dark));
            }
            else
            {
                wantedButtons.get(i).setBackground(getResources().getDrawable(R.drawable.settings_button));
            }
        }

        activeAllBtns();
    }

    void hideAll() {
        winnerText.setText("Choose !");
        for (int i = 0; i < gameButtons.size(); i++) {
            if (GameManager.getInstance().getDark()) {
                int resId = getResources().getIdentifier("hidecard_dark", "drawable", getApplicationContext().getPackageName());
                gameButtons.get(i).setImageResource(resId);
            } else {
                int resId = getResources().getIdentifier("hidecard", "drawable", getApplicationContext().getPackageName());
                gameButtons.get(i).setImageResource(resId);
            }

            if (GameManager.getInstance().getDark()) {
                gameButtons.get(i).setBackground(getResources().getDrawable(R.drawable.settings_background_dark));
            }
            else
            {
                gameButtons.get(i).setBackground(getResources().getDrawable(R.drawable.settings_button));
            }
        }
    }

    public void setOnClick1(View v) {
        setOnClickListener(0);
    }

    public void setOnClick2(View v) {
        setOnClickListener(1);
    }

    public void setOnClick3(View v) {
        setOnClickListener(2);
    }

    public void setOnClick4(View v) {
        setOnClickListener(3);
    }

    public void setOnClick5(View v) {
        setOnClickListener(4);
    }

    public void setOnClick6(View v) {
        setOnClickListener(5);
    }

    public void setOnClick7(View v) {
        setOnClickListener(6);
    }

    public void setOnClick8(View v) {
        setOnClickListener(7);
    }

    public void setOnClick9(View v) {
        setOnClickListener(8);

    }

    void showEverything() {
        for (int i = 0; i < drawedNumbers.size(); i++) {
            String resource = GameManager.getInstance().getGraphicPackName() + pictureNumbers.get(i);
            int resId = getResources().getIdentifier(resource, "drawable", getApplicationContext().getPackageName());
            gameButtons.get(i).setImageResource(resId);
            if (GameManager.getInstance().getDark()) {
                gameButtons.get(i).setBackground(getResources().getDrawable(R.drawable.settings_background_dark));
            }
            else
            {
                gameButtons.get(i).setBackground(getResources().getDrawable(R.drawable.settings_button));
            }
        }
    }

    void hideAllWantedCards() {
        for (int i = 0; i < wantedButtons.size(); i++) {
            int id = getResources().getIdentifier("settings_button", "drawable", getPackageName());
            wantedButtons.get(i).setImageResource(id);
            if (GameManager.getInstance().getDark()) {
                wantedButtons.get(i).setBackground(getResources().getDrawable(R.drawable.settings_background_dark));
            }
            else
            {
                wantedButtons.get(i).setBackground(getResources().getDrawable(R.drawable.settings_button));
            }
        }
    }

    void retryGame() {
        playSound();
        GameManager.getInstance().setWinner(false);
        Intent intent = getIntent();
        finish();
        startActivity(intent);
        overridePendingTransition(R.anim.abc_slide_in_bottom,R.anim.abc_slide_out_bottom);
    }

    void setOnClickListener(final int index) {
        playSound();
        obrazok = index;
        clickedButton = 0;
        if (drawedNumbers.get(index) != null) {
            clickedButton = drawedNumbers.get(index);
            Log.i("Stlacene", clickedButton + "");
        }
        if (setAllFind()) {

            String resource = GameManager.getInstance().getGraphicPackName() + pictureNumbers.get(index);
            final int resId = getResources().getIdentifier(resource, "drawable", getApplicationContext().getPackageName());
            Context context = getApplicationContext();

            // Create the Animation objects.
            Animation outAnimation = AnimationUtils.loadAnimation(context, R.anim.fade_out);
            final Animation inAnimation = AnimationUtils.loadAnimation(context, R.anim.fade_in);

            outAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    gameButtons.get(obrazok).setImageResource(resId);

                    // Create the new Animation to apply to the ImageButton.
                    gameButtons.get(obrazok).startAnimation(inAnimation);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            if (GameManager.getInstance().getDark()) {
                gameButtons.get(index).setBackground(getResources().getDrawable(R.drawable.settings_background_dark));
            }
            else
            {
                gameButtons.get(index).setBackground(getResources().getDrawable(R.drawable.settings_button));
            }

            gameButtons.get(index).startAnimation(outAnimation);
            gameButtons.get(index).setClickable(false);
        } else {
            if (GameManager.getInstance().getMatrixIndex() == 0) {
                if (GameManager.getInstance().getTwoxtwoHighScore() < highScore) {
                    GameManager.getInstance().setTwoxtwoHighScore(highScore);
                }
            }
            if (GameManager.getInstance().getMatrixIndex() == 1) {
                if (GameManager.getInstance().getTwoxthreeHighScore() < highScore) {
                    GameManager.getInstance().setTwoxthreeHighScore(highScore);
                }
            }
            if (GameManager.getInstance().getMatrixIndex() == 2) {
                if (GameManager.getInstance().getThreexthreeHighScore() < highScore) {
                    GameManager.getInstance().setThreexthreeHighScore(highScore);
                }
            }
            saveHighScore();
            GameManager.getInstance().setActualScore(0);
            showEverything();
            winnerText.setText("Loose");
            retryText.setText("");
            retryText.setBackground(getResources().getDrawable(R.drawable.retry_btn));
            retryText.setClickable(true);
            endGame.setAlpha(1f);
            endGame.setClickable(true);
            highScoreText.setText(highScore + "");
            if (BuildConfig.FLAVOR.equals("lite")) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }

            }
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }



    void saveHighScore() {
        SharedPreferences sharedPref = getSharedPreferences("highscore",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("twoxtwo",GameManager.getInstance().getTwoxtwoHighScore());
        editor.putInt("twoxthree",GameManager.getInstance().getTwoxthreeHighScore());
        editor.putInt("threexthree",GameManager.getInstance().getThreexthreeHighScore());
        editor.commit();
    }

    void unactiveAllBtns(){
        for (int i = 0 ; i < gameButtons.size();i++){
            gameButtons.get(i).setClickable(false);
        }
    }

    void activeAllBtns(){
        for (int i = 0 ; i < drawedNumbers.size();i++){
            gameButtons.get(i).setClickable(true);
        }
    }

    void playSound() {
        MediaPlayer mp ;
        mp = MediaPlayer.create(this,R.raw.tap1);
        mp.start();

    }
}
