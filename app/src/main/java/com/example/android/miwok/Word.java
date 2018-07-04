package com.example.android.miwok;

/**
 * Word represents a vocabulary word that the user wants to learn.
 * It contains a default translation and a Miwok translation for that word.
 */

public class Word {

    // Audio resource ID for the word
    private int AudioResourceId;

    // Constant value that represents no image was provided for this word
    private static final int NO_IMAGE_PROVIDED = 0;

    // Image resource ID for the word
    private int ImageResourceId = NO_IMAGE_PROVIDED;

    // Default translation for the word
    private String DefaultTranslation;

    // Miwok translation for the word
    private String MiwokTranslation;

    /**
     * Create a new Word object.
     *
     * @param defaultTranslation Word in familiar language (e.g. English)
     * @param miwokTranslation   Word in Miwok language
     * @param audioResourceId    Audio resource Id for audio file associated with the Miwok word
     */

    public Word(String defaultTranslation, String miwokTranslation, int audioResourceId) {
        DefaultTranslation = defaultTranslation;
        MiwokTranslation = miwokTranslation;
        AudioResourceId = audioResourceId;
    }

    /**
     * Create a new Word object.
     *
     * @param imageResourceId    Drawable Resource Id for image associated with the word
     * @param defaultTranslation Word in familiar language (e.g. English)
     * @param miwokTranslation   Word in Miwok language
     * @param audioResourceId    Audio resource Id for audio file associated with the Miwok word
     */
    public Word(int imageResourceId, String defaultTranslation, String miwokTranslation, int audioResourceId) {
        ImageResourceId = imageResourceId;
        DefaultTranslation = defaultTranslation;
        MiwokTranslation = miwokTranslation;
        AudioResourceId = audioResourceId;
    }

    // Get the audio file associated with the Miwok word
    public int getAudioResourceId() {
        return AudioResourceId;
    }

    // Get the image associated with using the drawable Resource Id
    public int getImageResourceId() {
        return ImageResourceId;
    }

    // Returns whether or not there is an image for this word
    public boolean hasImage() {
        return ImageResourceId != NO_IMAGE_PROVIDED;
    }

    // Get the Miwok translation of the word
    public String getMiwokTranslation() {
        return MiwokTranslation;
    }

    // Get the default translation of the word
    public String getDefaultTranslation() {
        return DefaultTranslation;
    }
}
