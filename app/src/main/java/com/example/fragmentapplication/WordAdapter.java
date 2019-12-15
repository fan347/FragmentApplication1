package com.example.fragmentapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class WordAdapter extends ArrayAdapter<Word> {
    private  int resourced;

    public WordAdapter(@NonNull Context context, int resource, @NonNull List<Word> objects ){
        super(context, resource, objects);
        this.resourced = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Word word = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourced,parent,false);
        TextView wordThis = (TextView) view.findViewById(R.id.list_view_word_item);
        wordThis.setText(word.getWordThis());
        return view;
    }
}
