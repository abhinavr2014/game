package com.reddyproject.abhinav.game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
//0--yellow,,,1--red
    int activeplayer = 0;
    int[] gamestate = {2,2,2,2,2,2,2,2,2};
    boolean gameactive = true;
    int[][] winningpositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public void dropIn(View view) {
        ImageView counter = (ImageView) view;
        System.out.println(counter.getTag().toString());
        int tappedcounter = Integer.parseInt(counter.getTag().toString());
        if (gamestate[tappedcounter] == 2 && gameactive) {

            gamestate[tappedcounter] = activeplayer;

            counter.setTranslationY(-1000f);
            if (activeplayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activeplayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activeplayer = 0;

            }
            counter.animate().translationYBy(1000f).rotation(3600).setDuration(300);


            for(int[]winningposition :winningpositions) {

                if(gamestate[winningposition[0]] == gamestate[winningposition[1]]&&gamestate[winningposition[1]] == gamestate[winningposition[2]] &&gamestate[winningposition[0]]!=2){
                    System.out.println(gamestate[winningposition[0]]);
                    gameactive = false;
                    String winner1 = "Red";
                    if(gamestate[winningposition[0]]==0){
                        winner1 = "yellow";
                    }

                    TextView winner = (TextView) findViewById(R.id.winner);
                    winner.setText(winner1 + " "+"HAS WON");
                    LinearLayout layout =(LinearLayout)findViewById(R.id.playagainlayout);

                    layout.setVisibility(View.VISIBLE);
                }
                else{
                    boolean gameover = true;
                    for(int counterstate :gamestate) {
                        if (counterstate == 2) gameover = false;

                    }
                    if (gameover){
                        TextView winner = (TextView) findViewById(R.id.winner);
                        winner.setText("ITS A DRAW");
                        LinearLayout layout =(LinearLayout)findViewById(R.id.playagainlayout);

                        layout.setVisibility(View.VISIBLE);

                    }
                }
            }

        }
    }
    public void playagain(View view){
        gameactive = true;

        LinearLayout layout =(LinearLayout)findViewById(R.id.playagainlayout);

        layout.setVisibility(View.INVISIBLE);

        activeplayer = 0;
        int[] gamestate = {2,2,2,2,2,2,2,2,2};

        for(int i =0;i<=gamestate.length;i++){
            gamestate[i]=2;
        }
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridlayout);
        for(int i =0;i<gridLayout.getChildCount();i++){
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);

        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
}
