package com.example.android.miwok;

/**
 * Word represents a vocabulary word that the user wants to learn.
 * It contains a default translation and a Miwok translation for that word.
 */

public class Word {

    /** Default translation for the word */
    private String mDefaultTranslation;

    /** Miwok translation for the word */
    private String mMiwokTranslation;

    /**
     * Create a new Word object.
     *
     * @param defaultTranslation    Word in familiar language (e.g. English)
     * @param miwokTranslation      Word in Miwok language
     */


    public Word(String defaultTranslation, String miwokTranslation) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
    }

    /** Get the Miwok translation of the word */
    public String getMiwokTranslation(){
        return mMiwokTranslation;
    }

    /** Get the default translation of the word */
    public String getDefaultTranslation(){
        return mDefaultTranslation;
    }
}
