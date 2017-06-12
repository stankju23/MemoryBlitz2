package com.example.stanislavcavajda.memoryblitz.Helper;

/**
 * Created by stanislavcavajda on 10.6.17.
 */

public class GameManager {

    private static final GameManager ourInstance = new GameManager();

    // graphic packs options
    private int packIndex;
    private String[] packs = {"christmas","family","western","space"};


    // settings options
    private int numbersOfCardsIndex;
    private int matrixIndex;
    private int secondsToRemember;


    // constructor
    private GameManager() {
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

}
