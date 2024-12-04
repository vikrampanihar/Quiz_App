package com.example.my_quiz.Adapter;


import static com.example.my_quiz.music.sound;

import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_quiz.R;
import com.example.my_quiz.model.Application_Model;

import java.util.ArrayList;
import java.util.List;

public class Application_adapter extends RecyclerView.Adapter<Application_adapter.viewholder> {
    List<Application_Model> list = new ArrayList<>();
    public Application_adapter(Context c, List<Application_Model> list) {
        this.c = c;
        this.list = list;
    }

   public static int count;
   
    Context c;
    private MediaPlayer mediaPlayer;

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(c).inflate(R.layout.my_layout,parent,false);
    return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        Application_Model model = list.get(position);
        holder.opt1.setText(model.getOp1());
        holder.opt2.setText(model.getOp2());
        holder.opt3.setText(model.getOp3());
        holder.opt4.setText(model.getOp4());
        holder.tv.setText("Q:-"+model.getTitle());
        String ans = model.getAns();

        holder.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                RadioButton i = holder.itemView.findViewById(checkedId);
                if (i.getText() == ans)
                {
                      count++;
                    i.setBackgroundColor(Color.GREEN);
                    if (sound == 1) {
                        mediaPlayer = MediaPlayer.create(c, R.raw.right);
                        mediaPlayer.start();
                    }
}
                else {
                    i.setBackgroundColor(Color.RED);
                    if (sound ==1) {
                        mediaPlayer = MediaPlayer.create(c, R.raw.wrong);
                        mediaPlayer.start();
                    }
                    holder.ans.setText("Ans:-"+ans);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{

                    RadioButton opt1,opt2,opt3,opt4;
                    RadioGroup radioGroup;
                    TextView tv,ans;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            opt1 = itemView.findViewById(R.id.op1);
            opt2 = itemView.findViewById(R.id.op2);
            opt3 = itemView.findViewById(R.id.op3);
            opt4 = itemView.findViewById(R.id.op4);
            ans = itemView.findViewById(R.id.anstv);
            tv = itemView.findViewById(R.id.question);
            radioGroup = itemView.findViewById(R.id.radiogroup);

        }
    }



}
