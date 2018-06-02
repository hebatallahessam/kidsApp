package com.example.android.kids;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int score=0;
    int totalSCore=8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    private void getAnswer(EditText v,String[] words){
        String answer=(v.getText()).toString();
        int length=words.length;
        for(int i=0;i<length;i++){
            if(answer.toLowerCase().indexOf(words[i])!=-1){
                score++;
            }
        }
    }
    public void getScore(){
        //what colors are apples?
        String appleColors[] ={"red","yellow","green"};
        getAnswer((EditText)findViewById(R.id.apples),appleColors);
        //what fruits have the same color?
        if(((CheckBox)findViewById(R.id.orange)).isChecked())
            score++;
        if(((CheckBox)findViewById(R.id.tange)).isChecked())
            score++;
        if(((CheckBox)findViewById(R.id.mango)).isChecked())
            score--;
        //Watermelon is ____ inside.
        String watermelonColor[]={"red"};
        getAnswer((EditText)findViewById(R.id.apples),watermelonColor);
        //what colors are bananas?
        if(((RadioButton)findViewById(R.id.yellowB)).isChecked())
            score++;
        else
            score--;
        //what is your favorite fruit?
        score++;
    }
    public void submit(View v){
        v.setVisibility(View.GONE);
        findViewById(R.id.result).setVisibility(View.VISIBLE);
        getScore();
        String scoreMsg;
        if (score<=0){
            scoreMsg="Try Again :( ! ";
            score=0;}
        else if (score<=(totalSCore/2))
            scoreMsg="You Can Do Better!";
        else
            scoreMsg=" Good Job ;)";
        String name=(((EditText)findViewById(R.id.name)).getText()).toString();
        String msg=" Hi "+name+" ,\n ";
        msg+="Your Score is "+score+" \n";
        msg+=scoreMsg;
        ((TextView)findViewById(R.id.result)).setText(msg);
        findViewById(R.id.restart).setVisibility(View.VISIBLE);
    }

    public void restart(View v){
        Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage( getBaseContext().getPackageName() );
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }
}

