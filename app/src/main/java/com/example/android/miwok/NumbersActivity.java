package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

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
        words.add(new Word(R.drawable.number_one, "one", "lutti", R.raw.number_one));
        words.add(new Word(R.drawable.number_two, "two", "otiiko", R.raw.number_two));
        words.add(new Word(R.drawable.number_three, "three", "tolookosu", R.raw.number_three));
        words.add(new Word(R.drawable.number_four, "four", "oyyisa", R.raw.number_four));
        words.add(new Word(R.drawable.number_five, "five", "massokka", R.raw.number_five));
        words.add(new Word(R.drawable.number_six, "six", "temmokka", R.raw.number_six));
        words.add(new Word(R.drawable.number_seven, "seven", "kenekaku", R.raw.number_seven));
        words.add(new Word(R.drawable.number_eight, "eight", "kawinta", R.raw.number_eight));
        words.add(new Word(R.drawable.number_nine, "nine", "wo'e", R.raw.number_nine));
        words.add(new Word(R.drawable.number_ten, "ten", "na'aacha", R.raw.number_ten));

        // Create an {@link ArrayAdapter}, whose data source is a list of Strings. The
        // adapter knows how to create layouts for each item in the list, using the
        // simple_list_item_1.xml layout resource defined in the Android framework.
        // This list item layout contains a single {@link TextView}, which the adapter will set to
        // display a single word.
        WordAdapter adapter = new WordAdapter(this, words, R.color.category_numbers);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list layout file.
        ListView listView = findViewById(R.id.list);

        // Make the {@link ListView} use the {@link ArrayAdapter} we created above, so that the
        // {@link ListView} will display list items for each word in the list of words.
        // Do this by calling the setAdapter method on the {@link ListView} object and pass in
        // 1 argument, which is the {@link ArrayAdapter} with the variable name itemsAdapter.
        listView.setAdapter(adapter);

        // Set a click listener to play audio file associated with list item that is clicked
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                // Release media player if it exists because going to play a different audio file
                releaseMediaPlayer();

                // Get the Word object at the given position clicked on by user
                Word word = words.get(position);

                // Create and set up MediaPlayer for audio file associated with the current word
                mediaPlayer = MediaPlayer.create(NumbersActivity.this, word.getAudioResourceId());
                mediaPlayer.start(); // no need to call prepare() because create() already does it

                // Set up listener on media Player so we can stop and release it when audio file finished playing
                mediaPlayer.setOnCompletionListener(completionListener);
            }

        });
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