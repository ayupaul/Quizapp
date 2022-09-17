package com.example.myquizapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView TotalQuestionsTextview;
    TextView questionTextView;
    Button AnsA,AnsB,AnsC,AnsD;
    Button SubmitBtn;
    int scores=0;
    int TotalQuestions=QuestionAnswer.question.length;
    int currentQuestionIndex=0;
    String selectedAnswer="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TotalQuestionsTextview=findViewById(R.id.total_question);
        questionTextView=findViewById(R.id.question);
        AnsA=findViewById(R.id.ans_A);
        AnsB=findViewById(R.id.ans_B);
        AnsC=findViewById(R.id.ans_C);
        AnsD=findViewById(R.id.ans_D);
        SubmitBtn=findViewById(R.id.Submit_Btn);
        AnsA.setOnClickListener(this);
        AnsB.setOnClickListener(this);
        AnsC.setOnClickListener(this);
        AnsD.setOnClickListener(this);
        SubmitBtn.setOnClickListener(this);
        TotalQuestionsTextview.setText("Total questions:"+TotalQuestions);
        loadNewQuestion();
    }

    @Override
    public void onClick(View v) {
        Button clickedButton=(Button) v;
        AnsA.setBackgroundColor(Color.WHITE);
        AnsB.setBackgroundColor(Color.WHITE);
        AnsC.setBackgroundColor(Color.WHITE);
        AnsD.setBackgroundColor(Color.WHITE);
        if(clickedButton.getId()==R.id.Submit_Btn){
            currentQuestionIndex++;
            loadNewQuestion();
            if(selectedAnswer.equals(QuestionAnswer.CorrectAnswers[currentQuestionIndex])){
                scores++;
            }
        }
        else{
            //choices button are clicked
            selectedAnswer= clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.MAGENTA);
        }
    }
    void loadNewQuestion(){
        if(currentQuestionIndex==TotalQuestions){
            finishQuiz();
            return;
        }
       questionTextView.setText(QuestionAnswer.question[currentQuestionIndex]);
       AnsA.setText(QuestionAnswer.choices[currentQuestionIndex][0]);
       AnsB.setText(QuestionAnswer.choices[currentQuestionIndex][1]);
       AnsC.setText(QuestionAnswer.choices[currentQuestionIndex][2]);
       AnsD.setText(QuestionAnswer.choices[currentQuestionIndex][3]);
    }
    void finishQuiz(){
        String passStatus="";
        if(scores>TotalQuestions*0.60){
            passStatus="Passed";
        }
        else{
            passStatus="Failed";
        }
        new AlertDialog.Builder(this)
            .setTitle(passStatus)
            .setMessage("Score is"+scores+"out of" +TotalQuestions)
            .setCancelable(false)
            .show();
    }
}