package com.example.android.miwok;

/**
 * Word represents a vocabulary word that the user wants to learn.
 * It contains a default translation and a Miwok translation for that word.
 */

public class Word {

    /**
     * Image for the word
     */
    private int ImageResourceId;

    /** Default translation for the word */
    private String DefaultTranslation;

    /** Miwok translation for the word */
    private String MiwokTranslation;

    /**
     * Create a new Word object.
     *
     * @param defaultTranslation    Word in familiar language (e.g. English)
     * @param miwokTranslation      Word in Miwok language
     *
     */

    public Word(String defaultTranslation, String miwokTranslation) {
        DefaultTranslation = defaultTranslation;
        MiwokTranslation = miwokTranslation;
    }

    /**
     * Create a new Word object.
     *
     * @param imageResourceId    Drawable Resource Id for image associated with the word
     * @param defaultTranslation Word in familiar language (e.g. English)
     * @param miwokTranslation   Word in Miwok language
     */
    public Word(int imageResourceId, String defaultTranslation, String miwokTranslation) {
        ImageResourceId = imageResourceId;
        DefaultTranslation = defaultTranslation;
        MiwokTranslation = miwokTranslation;
    }

    /**
     * Get the images associated with using the drawable Resource Id
     */
    public int getImageResourceId() {
        return ImageResourceId; }

    /** Get the Miwok translation of the word */
    public String getMiwokTranslation() {
        return MiwokTranslation; }

    /** Get the default translation of the word */
    public String getDefaultTranslation() {
        return DefaultTranslation; }
}
