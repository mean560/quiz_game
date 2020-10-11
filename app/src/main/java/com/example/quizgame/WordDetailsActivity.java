package com.example.quizgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quizgame.modle.WordItem;
import com.google.gson.Gson;

public class WordDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_details);

        Intent intent = getIntent();
        /*String word = intent.getStringExtra("word");
        int img = intent.getIntExtra("image", 0);*/
        String itemJson = intent.getStringExtra("item");
        WordItem item = new Gson().fromJson(itemJson, WordItem.class);

        ImageView iv = findViewById(R.id.image_view);
        TextView tv = findViewById(R.id.word_text_view);

        iv.setImageResource(item.imageResId);
        tv.setText(item.word);
    }
}
