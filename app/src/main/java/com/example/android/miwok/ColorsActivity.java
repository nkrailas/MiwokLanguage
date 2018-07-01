package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    // Handles playback of all audio files
    private MediaPlayer mediaPlayer;

    // This listener is gets triggered when Media Player has completed playing audio file
    private MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Audio file has finished playing, release media player resources
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        // Create an ArrayList of words objects
        final ArrayList<Word> words = new ArrayList<Word>();

        // words.add("one");
        words.add(new Word(R.drawable.color_red, "red", "wetetti", R.raw.color_red));
        words.add(new Word(R.drawable.color_green, "green", "chokokki", R.raw.color_green));
        words.add(new Word(R.drawable.color_brown, "brown", "takaaki", R.raw.color_brown));
        words.add(new Word(R.drawable.color_gray, "gray", "topoppi", R.raw.color_gray));
        words.add(new Word(R.drawable.color_black, "black", "kululli", R.raw.color_black));
        words.add(new Word(R.drawable.color_white, "white", "kelelli", R.raw.color_white));
        words.add(new Word(R.drawable.color_dusty_yellow, "dusty yellow", "topiisə", R.raw.color_dusty_yellow));
        words.add(new Word(R.drawable.color_mustard_yellow, "mustard yellow", "chiwiitə", R.raw.color_mustard_yellow));

        // Create an {@link ArrayAdapter}, whose data source is a list of Strings. The
        // adapter knows how to create layouts for each item in the list, using the
        // simple_list_item_1.xml layout resource defined in the Android framework.
        // This list item layout contains a single {@link TextView}, which the adapter will set to
        // display a single word.
        WordAdapter adapter = new WordAdapter(this, words, R.color.category_colors);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_listyout file.
        ListView listView = findViewById(R.id.list);

        // Make the {@link ListView} use the {@link ArrayAdapter} we created above, so that the
        // {@link ListView} will display list items for each word in the list of words.
        // Do this by calling the setAdapter method on the {@link ListView} object and pass in
        // 1 argument, which is the {@link ArrayAdapter} with the variable name itemsAdapter.
        listView.setAdapter(adapter);

        // Set a click listener to play audio file associated with list item that is clicked
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Release media player if it exists because going to play a different audio file
                releaseMediaPlayer();

                // Get the Word object at the given position clicked on by user
                Word word = words.get(position);

                // Create and set up MediaPlayer for audio file associated with the current word
                mediaPlayer = MediaPlayer.create(ColorsActivity.this, word.getAudioResourceId());
                mediaPlayer.start();

                // Set up listener on media Player so we can stop and release it when audio file finished playing
                mediaPlayer.setOnCompletionListener(completionListener);

            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();

        // When the activity is stopped, release the media player resources because we won't be playing any more sounds.
        releaseMediaPlayer();
    }

    // Clean up the media player by releasing its resources
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.release();

            // Set the media player back to null. This is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer = null;
        }
    }
}