package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        // Create an ArrayList of words objects
        ArrayList<Word> words = new ArrayList<Word>();

        // words.add("one");
        words.add(new Word(R.drawable.number_one, "one", "lutti"));
        words.add(new Word(R.drawable.number_two, "two", "otiiko"));
        words.add(new Word(R.drawable.number_three, "three", "tolookosu"));
        words.add(new Word(R.drawable.number_four, "four", "oyyisa"));
        words.add(new Word(R.drawable.number_five, "five", "massokka"));
        words.add(new Word(R.drawable.number_six, "six", "temmokka"));
        words.add(new Word(R.drawable.number_seven, "seven", "kenekaku"));
        words.add(new Word(R.drawable.number_eight, "eight", "kawinta"));
        words.add(new Word(R.drawable.number_nine, "nine", "wo'e"));
        words.add(new Word(R.drawable.number_ten, "ten", "na'aacha"));

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
        }
    }

