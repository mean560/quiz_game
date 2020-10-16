package com.example.quizgame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizgame.modle.WordItem;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView mQuestionImageView;
    private Button[] mButtons = new Button[4];

    private  String mAnswerWord;
    private Random mRandom;
    private List<WordItem> mItemList;

    private int score;
    private int count=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //mItemList = new ArrayList<>(Arrays.asList(WordListActivity.items));

        mQuestionImageView = findViewById(R.id.question_image_view);
        mButtons[0] = findViewById(R.id.choie_1_button);
        mButtons[1] = findViewById(R.id.choie_2_button);
        mButtons[2] = findViewById(R.id.choie_3_button);
        mButtons[3] = findViewById(R.id.choie_4_button);


        mButtons[0].setOnClickListener(this);
        mButtons[1].setOnClickListener(this);
        mButtons[2].setOnClickListener(this);
        mButtons[3].setOnClickListener(this);

        mRandom  = new Random();

        newQuiz();
    }

    private void newQuiz() {
        mItemList = new ArrayList<>(Arrays.asList(WordListActivity.items));

        int answerIndex = mRandom.nextInt(mItemList.size()); //สุ่ม index ของคำศัพท์

        WordItem item = mItemList.get(answerIndex);//เข้าถึง WordItem ตาม inedex ที่สุ่มได้

        mQuestionImageView.setImageResource(item.imageResId); //แสดงรูปคำถาม

        mAnswerWord = item.word;

        int randomButton = mRandom.nextInt(4); //สุ่มตำแหน่งที่จะแสดงคำตอบ
        mButtons[randomButton].setText(item.word); //แสดงคำศัพท์ที่เป็นคำตอบ

        mItemList.remove(item); //ลบ Worditem mี่เป็นคำตอบออกจาก list

        Collections.shuffle(mItemList); //เอา list mี่เหลือมา shuffle

        for(int i=0; i<4; i++){
            if(i != randomButton){
                mButtons[i].setText(mItemList.get(i).word);
            }
        }
    }

    //int count=1;

    @Override
    public void onClick(View view) {
        Button b = findViewById(view.getId());
        String buttonText = b.getText().toString();
        if(buttonText.equals(mAnswerWord)){
           /* Toast.makeText(GameActivity.this, "Correct", Toast.LENGTH_SHORT).show();*/
            score++;
            /*Log.i("GameActivity", "คะแนนทั้งหมด:"+score);*/
        }/*else {
            Toast.makeText(GameActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
        }*/

        AlertDialog.Builder dialog = new AlertDialog.Builder(GameActivity.this);
        final TextView scoreTextView = findViewById(R.id.score_text_view);

        /*Log.i("GameActivityActivity", "count:"+count);*/

        if(count==5){
            dialog.setTitle("สรุปผล").setMessage("คุณได้ "+score+" คะแนน"+"\n"+"\n"+"ต้องการเล่นเกมใหม่หรือไม่").setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            }).setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    newQuiz(); count=1; score=0;
                    scoreTextView.setText("");
                }
            }).show();
        } else {
            newQuiz(); count++;
        }

        if(score>=1){
            scoreTextView.setText(score+" คะแนน");
        }
    }
}