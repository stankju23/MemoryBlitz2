package com.example.stanislavcavajda.memoryblitz.Helper;

import android.media.MediaPlayer;

/**
 * Created by stanislavcavajda on 10.6.17.
 */

public class GameManager {

    private static final GameManager ourInstance = new GameManager();

    // graphic packs options
    private int packIndex;
    private String[] packs = {"christmas_","family_","western_","space_"};


    // settings options
    private int numbersOfCardsIndex;
    private int matrixIndex;
    private int secondsToRemember;

    private int twoxtwoHighScore;
    private int twoxthreeHighScore;
    private int threexthreeHighScore;


    private boolean winner;
    private int actualScore;

    private boolean dark = false;

    // constructor
    private GameManager() {
    }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    public int getActualScore() {
        return actualScore;
    }

    public void setActualScore(int actualScore) {
        this.actualScore = actualScore;
    }


    public static GameManager getInstance() {
        return ourInstance;
    }

    public int getPackIndex() {
        return packIndex;
    }

    public void setPackIndex(int index) {
        this.packIndex = index;
    }

    public int getSecondsToRemember() {
        return secondsToRemember;
    }

    public void setSecondsToRemember(int secondsToRemember) {
        this.secondsToRemember = secondsToRemember;
    }
    public int getNumbersOfCardsIndex() {
        return numbersOfCardsIndex;
    }

    public void setNumbersOfCardsIndex(int numbersOfCardsIndex) {
        this.numbersOfCardsIndex = numbersOfCardsIndex;
    }

    public int getMatrixIndex() {
        return matrixIndex;
    }

    public void setMatrixIndex(int matrixIndex) {
        this.matrixIndex = matrixIndex;
    }
    public String getGraphicPackName(){
        return packs[packIndex];
    }
    public int getTwoxtwoHighScore() {
        return twoxtwoHighScore;
    }

    public void setTwoxtwoHighScore(int twoxtwoHighScore) {
        this.twoxtwoHighScore = twoxtwoHighScore;
    }

    public int getTwoxthreeHighScore() {
        return twoxthreeHighScore;
    }

    public void setTwoxthreeHighScore(int twoxthreeHighScore) {
        this.twoxthreeHighScore = twoxthreeHighScore;
    }

    public int getThreexthreeHighScore() {
        return threexthreeHighScore;
    }

    public void setThreexthreeHighScore(int threexthreeHighScore) {
        this.threexthreeHighScore = threexthreeHighScore;
    }


    public boolean getDark(){
        return dark;
    }

    public void setDark(){
        if (!dark) {
            dark = true;
        } else {
            dark = false;
        }

    }
}
