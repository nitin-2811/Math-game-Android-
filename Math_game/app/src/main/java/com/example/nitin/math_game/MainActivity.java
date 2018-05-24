package com.example.nitin.math_game;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView quesTextView;
    TextView timerTextView,correctTextView,testTextView;

    Button Option1,Option2,Option3,Option4,playAgain;
    int a,b;
    CountDownTimer counter;
    int noOfQuestion,ansCorrect;
    public void timer(View view) {
        counter=(new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000));
            }

            @Override
            public void onFinish() {
                Option1.setClickable(false);
                Option2.setClickable(false);
                Option3.setClickable(false);
                Option4.setClickable(false);
                playAgain.setVisibility(1);
                correctTextView.setText(Integer.toString(ansCorrect)+"/"+Integer.toString(noOfQuestion));

            }
        }).start();
    }
    public void playAgainFunction(View view)
    {
        Option1.setClickable(false);
        Option2.setClickable(false);
        Option3.setClickable(false);
        Option4.setClickable(false);
        playAgain.setVisibility(0);
        ansCorrect=0;
        noOfQuestion=0;

    }
    public void checkAnswer(View view)
    {
        Button button=(Button)view;
        String c;
        c = String.valueOf(button.getText());
        if(String.valueOf(a+b).equals(c)) {
                testTextView.setText("Correct!!");
                ansCorrect++;
        }
        else{
            testTextView.setText("Wrong!");
        }
            noOfQuestion++;
        correctTextView.setText(Integer.toString(ansCorrect)+"/"+Integer.toString(noOfQuestion));
        updatequestion(quesTextView);
    }

    public void updatequestion(View view)
    {
        int c,rightPos;
        String question;
        Random random=new Random();
        ArrayList<Integer> ans=new ArrayList<Integer>();
        a=random.nextInt(21);
        b=random.nextInt(21);
        rightPos=random.nextInt(4);
        question=Integer.toString(a)+"+"+Integer.toString(b);
        quesTextView.setText(question);
        for(int i=0;i<4;i++) {
            if(i==rightPos)
                ans.add(a+b);
            else{
                c=random.nextInt(41);
                while(a+b==c) {
                    c=random.nextInt(41);
                }
                ans.add(c);
            }
        }
        Option1.setText(String.valueOf(ans.get(0)));
        Option2.setText(String.valueOf(ans.get(1)));
        Option3.setText(String.valueOf(ans.get(2)));
        Option4.setText(String.valueOf(ans.get(3)));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        quesTextView=findViewById(R.id.quesTextView);
        timerTextView=findViewById(R.id.timerTextView);
        correctTextView=findViewById(R.id.correctTextView);
        testTextView=findViewById(R.id.testTextView);
        Option1=findViewById(R.id.Option1);
        Option2=findViewById(R.id.Option2);
        Option3=findViewById(R.id.Option3);
        Option4=findViewById(R.id.Option4);
        playAgain=findViewById(R.id.playAgain);
        noOfQuestion=0;
        ansCorrect=0;
        updatequestion(quesTextView);

        timer(quesTextView);

    }
}
