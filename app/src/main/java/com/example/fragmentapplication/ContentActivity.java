package com.example.fragmentapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class ContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_content);

        Intent intent = this.getIntent();
        String wordthis = intent.getStringExtra("wordthis");
        String wordmeaing = intent.getStringExtra("wordmeaing");
        String wordinstance = intent.getStringExtra("wordinstance");
        ContentFragment cf = (ContentFragment) this.getSupportFragmentManager()
                .findFragmentById(R.id.fm_content);
        cf.setContent(wordthis,wordmeaing,wordinstance);
    }
}
