package com.example.my_quiz;

import static com.example.my_quiz.music.sound;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    MediaPlayer md;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            ListView listView = findViewById(R.id.listview);
            String string[] = {
                    "General Knowledge","Sports","History","Art","Computer","Maths","Science & Nature"
            };
            ImageView imageView =findViewById(R.id.music);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (sound == 1)
                    { sound =0;
                       md.stop();
                        Toast.makeText(MainActivity.this,"Sound OFF",Toast.LENGTH_LONG).show();
                    }
                   else {
                       Toast.makeText(MainActivity.this,"Sound ON",Toast.LENGTH_LONG).show();
                       sound =1;
                       md.start();
                    }
                }
            });
            ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,string);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(MainActivity.this,MainActivity2.class);

                    if (position == 0 )
                  {
                      intent.putExtra("id","9");
                      startActivity(intent);
                  } else if (position == 1) {
                        intent.putExtra("id","21");
                        startActivity(intent);

                  } else if (position == 2) {
                        intent.putExtra("id","23");
                        startActivity(intent);

                  } else if (position ==3) {
                        intent.putExtra("id","25");
                        startActivity(intent);

                  } else if (position == 4) {
                        intent.putExtra("id","9");
                        startActivity(intent);

                  } else if (position ==5) {
                        intent.putExtra("id","18");
                        startActivity(intent);

                    } else if (position==6){
                        intent.putExtra("id","19");
                        startActivity(intent);
                    }else {
                        intent.putExtra("id","17");
                        startActivity(intent);
                    }
                }
            });
            return insets;
        });

    }
    @Override
    protected void onStart() {
        if (sound ==1) {
            super.onStart();
            md = MediaPlayer.create(MainActivity.this, R.raw.bg);
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