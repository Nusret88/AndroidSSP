package com.example.projektssp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameScreen extends AppCompatActivity {
GameMode gameMode = new GameMode();

    String playerChoice;
    String cpuChoice;

    int roundNum;

    TextView roundsNo, playerScore, cpuScore;
    Button rockButton, scissorButton, paperButton, resultButton;
    ImageView rockPlayer, scissorPlayer, paperPlayer, rockCPU, scissorCPU, paperCPU;

    // The game
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamescreen);

        engienSSP();

        resultButton = findViewById(R.id.resultButton);
        resultButton.setEnabled(false);
        resultButton.setVisibility(View.GONE);

        resultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeResultScreen();
            }
        });
}

// CPU randomly picks a hand
    public void handCPU () {
        Random hand = new Random();
        int cpuHand = hand.nextInt(3);

        switch (cpuHand) {
            case 0:
                rockCPU.setVisibility(View.VISIBLE);
                scissorCPU.setVisibility(View.GONE);
                paperCPU.setVisibility(View.GONE);
                cpuChoice = "Rock";
                break;

            case 1:
                rockCPU.setVisibility(View.GONE);
                scissorCPU.setVisibility(View.VISIBLE);
                paperCPU.setVisibility(View.GONE);
                cpuChoice = "Scissors";
                break;

            case 2:
                rockCPU.setVisibility(View.GONE);
                scissorCPU.setVisibility(View.GONE);
                paperCPU.setVisibility(View.VISIBLE);
                cpuChoice = "Paper";
                break;
        }
    }


    public void engienSSP() {
        rockButton = findViewById(R.id.rockButton);
        scissorButton = findViewById(R.id.scissorButton);
        paperButton = findViewById(R.id.paperButton);

        // Player Images
        rockPlayer = findViewById(R.id.rockPlayer);
        scissorPlayer = findViewById(R.id.scissorPlayer);
        paperPlayer = findViewById(R.id.paperPlayer);

        rockPlayer.setVisibility(View.GONE);
        scissorPlayer.setVisibility(View.GONE);
        paperPlayer.setVisibility(View.GONE);

        // CPU images
        rockCPU = findViewById(R.id.rockCPU);
        scissorCPU = findViewById(R.id.scissorCPU);
        paperCPU = findViewById(R.id.paperCPU);

        rockCPU.setVisibility(View.GONE);
        scissorCPU.setVisibility(View.GONE);
        paperCPU.setVisibility(View.GONE);

        // Everything that happens when pressing down on the Rock button
            rockButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    rockPlayer.setVisibility(View.VISIBLE);
                    scissorPlayer.setVisibility(View.GONE);
                    paperPlayer.setVisibility(View.GONE);
                    playerChoice = "Rock";
                    handCPU();
                    System.out.println("Player: " + playerChoice + " CPU: " + cpuChoice);
                    playerRock();
                    System.out.println("Player score: " + GameDataHolder.getPlayer() + " CPU score: " + GameDataHolder.getCpu());
                    gameStateCheck();
                    delayButtons();
                }
            });

        // Everything that happens when pressing down on the Scissors button
            scissorButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    rockPlayer.setVisibility(View.GONE);
                    scissorPlayer.setVisibility(View.VISIBLE);
                    paperPlayer.setVisibility(View.GONE);
                    playerChoice = "Scissors";
                    handCPU();
                    System.out.println("Player: " + playerChoice + " CPU: " + cpuChoice);
                    playerScissors();
                    System.out.println("Player score: " + GameDataHolder.getPlayer() + " CPU score: " + GameDataHolder.getCpu());
                    gameStateCheck();
                    delayButtons();
                }
            });

        // Everything that happens when pressing down on the Paper button
            paperButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    rockPlayer.setVisibility(View.GONE);
                    scissorPlayer.setVisibility(View.GONE);
                    paperPlayer.setVisibility(View.VISIBLE);
                    playerChoice = "Paper";
                    handCPU();
                    System.out.println("Player: " + playerChoice + " CPU: " + cpuChoice);
                    playerPaper();
                    System.out.println("Player score: " + GameDataHolder.getPlayer() + " CPU score: " + GameDataHolder.getCpu());
                    gameStateCheck();
                    delayButtons();
                }
            });

    }

    // Game logic when player picks rock
    public void playerRock() {
        roundsNo = findViewById(R.id.roundsNo);
        playerScore = findViewById(R.id.playerScore);
        cpuScore = findViewById(R.id.cpuScore);

        if ((playerChoice =="Rock") && (cpuChoice == "Rock")) {
            Toast.makeText(this,   "Draw", Toast.LENGTH_SHORT).show();
            GameDataHolder.rounds++;
            roundsNo.setText("Round " + GameDataHolder.getRounds());
        }

        if ((playerChoice =="Rock") && (cpuChoice == "Paper")) {
            Toast.makeText(this,   "You got eaten by paper", Toast.LENGTH_SHORT).show();
            GameDataHolder.cpu++;
            GameDataHolder.rounds++;
            roundsNo.setText("Round " + GameDataHolder.getRounds());
            cpuScore.setText(GameDataHolder.getCpu() + "");
        }

        if ((playerChoice =="Rock") && (cpuChoice == "Scissors")) {
            Toast.makeText(this,   "You crushed some scissors", Toast.LENGTH_SHORT).show();
            GameDataHolder.player++;
            GameDataHolder.rounds++;
            roundsNo.setText("Round " + GameDataHolder.getRounds());
            playerScore.setText(GameDataHolder.getPlayer() + "");
        }
    }
// game logic when player picks scissors
    public  void playerScissors() {
        roundsNo = findViewById(R.id.roundsNo);
        playerScore = findViewById(R.id.playerScore);
        cpuScore = findViewById(R.id.cpuScore);

        if ((playerChoice =="Scissors") && (cpuChoice == "Rock")) {
            Toast.makeText(this,   "You lost to Rock", Toast.LENGTH_SHORT).show();
            GameDataHolder.cpu++;
            GameDataHolder.rounds++;
            roundsNo.setText("Round " + GameDataHolder.getRounds());
            cpuScore.setText(GameDataHolder.getCpu() + "");
        }

        if ((playerChoice =="Scissors") && (cpuChoice == "Paper")) {
            Toast.makeText(this,   "You cut the paper", Toast.LENGTH_SHORT).show();
            GameDataHolder.player++;
            GameDataHolder.rounds++;
            roundsNo.setText("Round " + GameDataHolder.getRounds());
            playerScore.setText(GameDataHolder.getPlayer() + "");
        }

        if ((playerChoice =="Scissors") && (cpuChoice == "Scissors")) {
            Toast.makeText(this,   "Draw", Toast.LENGTH_SHORT).show();
            GameDataHolder.rounds++;
            roundsNo.setText("Round " + GameDataHolder.getRounds());
        }
    }
// game logic when player picks paper
    public void playerPaper() {
        roundsNo = findViewById(R.id.roundsNo);
        playerScore = findViewById(R.id.playerScore);
        cpuScore = findViewById(R.id.cpuScore);

        if ((playerChoice =="Paper") && (cpuChoice == "Rock")) {
            Toast.makeText(this,   "You enveloped paper", Toast.LENGTH_SHORT).show();
            GameDataHolder.player++;
            GameDataHolder.rounds++;
            roundsNo.setText("Round " + GameDataHolder.getRounds());
            playerScore.setText(GameDataHolder.getPlayer() + "");
        }

        if ((playerChoice =="Paper") && (cpuChoice == "Scissors")) {
            Toast.makeText(this,   "You got cut by scissors", Toast.LENGTH_SHORT).show();
            GameDataHolder.cpu++;
            GameDataHolder.rounds++;
            roundsNo.setText("Round " + GameDataHolder.getRounds());
            cpuScore.setText(GameDataHolder.getCpu() + "");
        }

        if ((playerChoice =="Paper") && (cpuChoice == "Paper")) {
            Toast.makeText(this,   "Draw", Toast.LENGTH_SHORT).show();
            GameDataHolder.rounds++;
            roundsNo.setText("Round " + GameDataHolder.getRounds());
        }
    }

    public void gameStateCheck() {
        roundsNo = findViewById(R.id.roundsNo);
        playerScore = findViewById(R.id.playerScore);
        cpuScore = findViewById(R.id.cpuScore);
        resultButton = findViewById(R.id.resultButton);

            int maxWins = GameMode.DataHolder.getBestOf();
            int maxRounds = GameMode.DataHolder.getRoundsOf();

        if (GameDataHolder.player == maxWins || GameDataHolder.cpu == maxWins || GameDataHolder.rounds == maxRounds) {
            startResult();

            /* If I want to use result button instead of instant transition to result screen
            rockPlayer.setVisibility(View.GONE);
            scissorPlayer.setVisibility(View.GONE);
            paperPlayer.setVisibility(View.GONE);

            rockCPU.setVisibility(View.GONE);
            scissorCPU.setVisibility(View.GONE);
            paperCPU.setVisibility(View.GONE);

            rockButton.setEnabled(false);
            scissorButton.setEnabled(false);
            paperButton.setEnabled(false);

            resultButton.setEnabled(true);
            resultButton.setVisibility(View.VISIBLE); */
        }
    }
    // Result screen for button
    public void changeResultScreen(){

            resultButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(GameScreen.this, ResultScreen.class));
                }
            });
    }

    // Delaying the unlock of buttons and hides the player and cpu's hans
    public  void delayButtons () {
        final Handler handler = new Handler();

        rockButton.setEnabled(false);
        scissorButton.setEnabled(false);
        paperButton.setEnabled(false);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                rockPlayer.setVisibility(View.GONE);
                scissorPlayer.setVisibility(View.GONE);
                paperPlayer.setVisibility(View.GONE);

                rockCPU.setVisibility(View.GONE);
                scissorCPU.setVisibility(View.GONE);
                paperCPU.setVisibility(View.GONE);

                rockButton.setEnabled(true);
                scissorButton.setEnabled(true);
                paperButton.setEnabled(true);
            }
        }, 2000);
    }

    // Resultscreen instantly
    public void startResult() {
        startActivity(new Intent(GameScreen.this, ResultScreen.class));
    }


    // DataHolder for all the important information that needs to be transferred to the ResultScreen
    public static class GameDataHolder {
        private static int player;
        private static int cpu;
        private static int rounds;

        public static int getPlayer() {return player;}
        public static void setPlayer(int player) {GameDataHolder.player = player;}

        public static int getCpu() {return cpu;}
        public static void setCpu(int cpu) {GameDataHolder.cpu = cpu;}

        public static int getRounds() {return rounds;}
        public static void setRounds(int rounds) {GameDataHolder.rounds = rounds;}
    }
}
