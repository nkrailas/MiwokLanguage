package com.example.android.miwok;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ColorsFragment extends Fragment {

    // Handles playback of all audio files
    private MediaPlayer mediaPlayer;

    // Handles audio focus when playing an audio file
    private AudioManager audioManager;

    // This listener gets triggered when audio focus changes
    private AudioManager.OnAudioFocusChangeListener audioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int audioFocusChange) {

            // If audio focus lost for a short amount of time or app continues playing at lower volume ...
            if (audioFocusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    audioFocusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                mediaPlayer.pause(); // Pause playback
                mediaPlayer.seekTo(0); // Reset player so word can be heard from the beginning (value is 0)

                // If audio focus is regained, then resume playback
            } else if (audioFocusChange == AudioManager.AUDIOFOCUS_GAIN) {
                mediaPlayer.start();

                // If audio focus is lost, then stop playback and clean up media resources
            } else if (audioFocusChange == AudioManager.AUDIOFOCUS_LOSS) {
                releaseMediaPlayer();
            }
        }
    };

    // This listener is gets triggered when Media Player has completed playing audio file
    private MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Audio file has finished playing, release media player resources
            releaseMediaPlayer();
        }
    };

    public ColorsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.word_list, container, false);

// Create and set up an Audio Manager to request audio focus
        audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

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
        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_colors);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_listyout file.
        ListView listView = rootView.findViewById(R.id.list);

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

                // Request audio focus in order to play audio file for a short amount of time
                int result = audioManager.requestAudioFocus(audioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, // Use the music stream
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);  // Request temporary audio focus

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) { // We have audio focus now

                    // Create and set up MediaPlayer for audio file associated with the current word
                    mediaPlayer = MediaPlayer.create(getActivity(), word.getAudioResourceId());
                    mediaPlayer.start();

                    // Set up listener on media Player so we can stop and release it when audio file finished playing
                    mediaPlayer.setOnCompletionListener(completionListener);

                }
            }
        });

        return rootView;
    }

    @Override
    public void onStop() {
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

            // Whether or not granted audio focus, abandon it.
            audioManager.abandonAudioFocus(audioFocusChangeListener);
        }
    }
}