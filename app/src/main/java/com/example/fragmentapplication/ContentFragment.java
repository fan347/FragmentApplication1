package com.example.fragmentapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

public class ContentFragment extends Fragment {
    TextView textViewWordThis;
    TextView textViewWordMeaing;
    TextView textViewWordInstance;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_content,container,false);
        textViewWordThis = (TextView) view.findViewById(R.id.content_wordThis);
        textViewWordMeaing = (TextView) view.findViewById(R.id.content_wordMeaing);
        textViewWordInstance = (TextView) view.findViewById(R.id.content_wordInstance);
        return view;
    }

    public void setContent(String wordthis,String wordMeaing,String wordInstance) {
        textViewWordThis.setText(wordthis);
        textViewWordMeaing.setText(wordMeaing);
        textViewWordInstance.setText(wordInstance);
    }

}
