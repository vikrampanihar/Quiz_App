package com.example.my_quiz;
import static com.example.my_quiz.Adapter.Application_adapter.count;

import static com.example.my_quiz.music.sound;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.my_quiz.Adapter.Application_adapter;
import com.example.my_quiz.model.Application_Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    private MediaPlayer md;
   List<Application_Model> list2;
   RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            String name = getIntent().getStringExtra("id");
            list2=new ArrayList<>();
            recyclerView = findViewById(R.id.recyclerview);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.show();
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            JsonObjectRequest obj = new JsonObjectRequest(Request.Method.GET, "https://opentdb.com/api.php?amount=10&category=" + name + "&type=multiple", null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    List<String> list = new ArrayList<>();

                    try {
                        JSONArray jsonArray = response.getJSONArray("results");
                        for (int i =0;i<jsonArray.length();i++)
                        {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                               list.add(jsonObject.getString("correct_answer"));
                               String q = jsonObject.getString("question");

                               String right = jsonObject.getString("correct_answer");

                            JSONArray incorrectAnswersArray = jsonObject.getJSONArray("incorrect_answers");
                            for (int ab =0;ab<incorrectAnswersArray.length();ab++)
                            {
                               list.add(incorrectAnswersArray.getString(ab));
                            }
                            Collections.shuffle(list);
                            list2.add(new Application_Model(0,list.get(0),list.get(1),list.get(2),list.get(3),q,right));
                             list.clear();
                        }
                        Application_adapter adapter = new Application_adapter(getApplicationContext(),list2);
                        recyclerView.setAdapter(adapter);
                           progressDialog.dismiss();
                    } catch (JSONException e) {
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();

                }
            });
            requestQueue.add(obj);
            Button sumbit = findViewById(R.id.submit);
            sumbit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(MainActivity2.this,MainActivity3.class);
                    startActivity(intent);

                }
            });
            return insets;
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        count =0;
    }
    @Override
    protected void onStart() {
        super.onStart();
        if (sound ==1) {
            md = MediaPlayer.create(MainActivity2.this, R.raw.backgroundsound);
            md.isLooping();
            md.start();
        }
    }
    @Override
    protected void onStop()
    {
        super.onStop();
        md.stop();
    }
}