package com.example.android.miwok;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

    // Resource ID for the background color for this list of words
    private int ColorResourceId;

    public WordAdapter(Activity context, ArrayList<Word> words, int colorResourceId) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, words);
        ColorResourceId = colorResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the Word object located at this position in the list
        Word currentWord = getItem(position);

        // Find and get the Miwok TextView in the list_item.xml layout
        TextView miwokTextView = listItemView.findViewById(R.id.miwok_text_view);
        miwokTextView.setText(currentWord.getMiwokTranslation());

        // Find and get the Default TextView in the list_item.xml layout
        TextView defaultTextView = listItemView.findViewById(R.id.default_text_view);
        defaultTextView.setText(currentWord.getDefaultTranslation());

        // Find and get the ImageView in the list_item.xml layout
        ImageView imageView = listItemView.findViewById(R.id.word_image);
        imageView.setImageResource(currentWord.getImageResourceId());

        if (currentWord.hasImage()) {
            // If the current word has an image, display the image based on resource ID
            imageView.setImageResource(currentWord.getImageResourceId());

            // Make sure the view is visible
            imageView.setVisibility(View.VISIBLE);
        } else {
            // Otherwise hide the ImageView
            imageView.setVisibility(View.GONE);
        }

        // Set the theme color for the list item
        View textContainer = listItemView.findViewById(R.id.text_container);
        //Find the color that the resource ID maps to
        int color = ContextCompat.getColor(getContext(), ColorResourceId);
        //Set the background color of the text container View
        textContainer.setBackgroundColor(color);

        // Return the whole list item layout so that it can be shown in the ListView
        return listItemView;
    }

}
