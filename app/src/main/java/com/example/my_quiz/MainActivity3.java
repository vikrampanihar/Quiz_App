package com.example.my_quiz;

import static com.example.my_quiz.Adapter.Application_adapter.count;
import static com.example.my_quiz.music.sound;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.my_quiz.Adapter.Application_adapter;

public class MainActivity3 extends AppCompatActivity {
    private  MediaPlayer md;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            TextView tv = findViewById(R.id.textView);
            String and =String.valueOf(count);
            tv.setText(and);

            return insets;
        });
    }
    @Override
    protected void onStart() {

        super.onStart();
        if (sound == 1) {
            md = MediaPlayer.create(MainActivity3.this, R.raw.bg);
            md.start();
            md.isLooping();
        }
    }
    @Override
    protected void onStop()
    {
        super.onStop();
        md.stop();
    }


}