package com.example.tictabtoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
//import android.widget.GridLayout;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //0:yellow, 1:red

    int[] gameState={2,2,2,2,2,2,2,2,2};
    int[][] winingPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    int activePlayer = 0;

    boolean gameActive=true;
    public void dropIn(View view){
        ImageView counter =(ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter]==2 && gameActive){

        gameState[tappedCounter]=activePlayer;

        counter.setTranslationY(-1500);
        counter.setImageResource(R.drawable.o);

        if(activePlayer==0){
            counter.setImageResource(R.drawable.o);
            activePlayer=1;
        }
        else {
            counter.setImageResource(R.drawable.x);
            activePlayer=0;
        }
        counter.animate().translationYBy(1500).rotation(3600).setDuration(300);
        for(int[] winingPosition:winingPositions) {
            if (gameState[winingPosition[0]] == gameState[winingPosition[1]] && gameState[winingPosition[1]] == gameState[winingPosition[2]] && gameState[winingPosition[0]] != 2) {
                gameActive=false;
                String winner = "";

                if (activePlayer == 1) {
                    winner = "O";
                } else {
                    winner = "X";
                }


                Button playAgain=(Button)findViewById(R.id.playAgainbutton);
                TextView winnerText=(TextView)findViewById(R.id.winnerText);
                winnerText.setText(winner+" Has Won!");
                playAgain.setVisibility(view.VISIBLE);
                winnerText.setVisibility(view.VISIBLE);

            }
        }
        }
    }
    public void playAgain(View view) {

        Button playAgainButton = (Button) findViewById(R.id.playAgainbutton);

        TextView winnerTextView = (TextView) findViewById(R.id.winnerText);

        playAgainButton.setVisibility(View.INVISIBLE);

        winnerTextView.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for(int i=0; i<gridLayout.getChildCount(); i++) {

            ImageView counter = (ImageView) gridLayout.getChildAt(i);

            counter.setImageDrawable(null);

        }

        for (int i=0; i<gameState.length; i++) {

            gameState[i] = 2;

        }

        activePlayer = 0;

        gameActive = true;

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}