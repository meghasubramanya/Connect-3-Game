package com.example.connect3game;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int player = 0;   //0 for pink and 1 for green.
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2}; //initially 2 represents the gameState.

    int[][] winPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    Boolean gameActive = true;

    public void dropIn(View view) {
        ImageView counter = (ImageView) view;

        counter.getTag();
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameActive == true)           //to ensure that counter comes only if empty.
        {

            gameState[tappedCounter] = player;
            if(gameState[0]!=2 &&gameState[1]!=2 &&gameState[2]!=2 &&gameState[3]!=2 &&gameState[4]!=2 &&gameState[5]!=2 &&
                    gameState[6]!=2 &&gameState[7]!=2 &&gameState[8]!=2 )
            {
                Button btnPlayAgain=(Button) findViewById(R.id.btnPlayAgain);
                TextView tvWinner=(TextView) findViewById(R.id.tvWinner);
                btnPlayAgain.setVisibility(View.VISIBLE);
                tvWinner.setVisibility(View.VISIBLE);
                tvWinner.setText("There is no winner!");

            }
            counter.setTranslationY(-1500);

            //for alternate images to obtain.
            if (player == 0) {
                counter.setImageResource(R.drawable.pink);
                player = 1;
            } else {
                counter.setImageResource(R.drawable.green);
                player = 0;
            }
            counter.animate().translationYBy(1500).setDuration(300);


            for (int[] winPosition : winPositions) {
                if (gameState[winPosition[0]] == gameState[winPosition[1]] && gameState[winPosition[1]] == gameState[winPosition[2]]
                        && gameState[winPosition[0]] != 2) {

                    //someone has won.
                    gameActive = false;

                    String winner = "";
                    if (player == 1) {
                        winner = "Pink";               //since player is changed from 0 to 1 already
                    } else if(player==0) {
                        winner = "Green";
                    }

                Button btnPlayAgain = (Button) findViewById(R.id.btnPlayAgain);
                TextView tvWinner = (TextView) findViewById(R.id.tvWinner);

                tvWinner.setText(winner + " has won!");
                btnPlayAgain.setVisibility(View.VISIBLE);
                tvWinner.setVisibility(View.VISIBLE);

                }
            }
    }

    }
    public void playAgain(View view)
    {
        Button btnPlayAgain=(Button) findViewById(R.id.btnPlayAgain);
        TextView tvWinner=(TextView) findViewById(R.id.tvWinner);

        btnPlayAgain.setVisibility(View.INVISIBLE);
        tvWinner.setVisibility(View.INVISIBLE);

        //looping through grid.

        GridLayout gridLayout=(GridLayout) findViewById(R.id.gridLayout);

        for(int i=0; i<gridLayout.getChildCount(); i++) {

            ImageView counter = (ImageView) gridLayout.getChildAt(i);

            counter.setImageDrawable(null);

        }


        for(int i=0;i<gameState.length;i++)
        {
            gameState[i]=2;
        }
        player = 0;   //0 for pink and 1 for green.
         gameActive=true;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}