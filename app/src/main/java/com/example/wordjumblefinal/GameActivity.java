package com.example.wordjumblefinal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    String[] wordReqd;
    String Empty = "";
    String theClue;
    int count = 0;
    ImageView Life1, Life2, Life3;

    TextView Guess,ClueGiven,Length;
    ArrayList<String> alphabets;
    private Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16, Reset, Check, ClueBtn;
    int pos = 0;
    int life = 3;
    int score = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2);

        Intent receiverIntent = getIntent();
        String theInput = (receiverIntent.getStringExtra("Input")).toUpperCase();
        theClue = receiverIntent.getStringExtra("Clue");
        System.out.println(theClue);
        wordReqd = theInput.split("");
        alphabets = new ArrayList<>();
        for (int i = 1; i <= 16; i++) {
            alphabets.add("0");
        }
        String English = "ABCDEFGHIJKLMNOPQRSTUVWXYZ ";




        for (String alphabet : wordReqd) {
            Empty += "_";
            count += 1;
            int flag = 1;
            while (flag == 1) {
                Random random = new Random();
                int val = random.nextInt(16);
                if (alphabets.get(val) == "0") {
                    alphabets.set(val, alphabet);
                    flag = 0;
                }

            }
        }
        for (int j = 1; j <= 16; j++) {
            Random random = new Random();
            int c = random.nextInt(26);
            if (alphabets.get(j - 1) == "0") {
                alphabets.set(j - 1, English.substring(c, c + 1));
            }
        }


        Guess = (TextView) findViewById(R.id.Guess);
        Guess.setText(Empty);
        Assign(alphabets);

        ClueBtn = (Button) findViewById(R.id.ClueBtn);
        ClueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog cluedialog=new Dialog(GameActivity.this);
                cluedialog.setContentView(R.layout.custom_dialogclue);
                Button Okay=cluedialog.findViewById(R.id.Okay);
                Okay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cluedialog.dismiss();
                    }
                });
                TextView ClueGiven = cluedialog.findViewById(R.id.ClueGiven);
                ClueGiven.setText(theClue);

                TextView Length = cluedialog.findViewById(R.id.Length);
                Length.setText("( " + String.valueOf(count) + " lettered word)");

                cluedialog.show();

            }
        });

        Reset = (Button) findViewById(R.id.Reset);
        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Empty = "";
                for (String alphabet : wordReqd) {
                    Empty += "_";
                }
                pos = 0;
                Guess.setText(Empty);
            }
        });


        Check = (Button) findViewById(R.id.Check);
        Check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count==pos) {

                    if (Empty.equals(theInput) || score == 10) {
                        if (Empty.equals(theInput)) {
                            Empty = "";
                            pos = 0;
                            final Dialog dialog = new Dialog(GameActivity.this);
                            dialog.setContentView(R.layout.custom_dialog);

                            TextView Score = dialog.findViewById(R.id.Score);
                            Score.setText("You won. Score: " + String.valueOf(score));
                            Button Home = dialog.findViewById(R.id.Home);
                            Button PlayAgain = dialog.findViewById(R.id.PlayAgain);
                            Home.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(GameActivity.this, MainActivity.class);
                                    dialog.dismiss();
                                    startActivity(intent);
                                }
                            });
                            PlayAgain.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                    if (life == 3) {
                                        score = 30;
                                        Empty = "";
                                        for (String alphabet : wordReqd) {
                                            Empty += "_";
                                        }
                                        pos = 0;

                                        life = 3;
                                        Guess.setText(Empty);
                                        Collections.shuffle(alphabets);
                                        Assign(alphabets);
                                    }
                                    if (life == 2) {
                                        Life1.setImageResource(R.drawable.yellowheart);
                                        score = 30;
                                        Empty = "";
                                        for (String alphabet : wordReqd) {
                                            Empty += "_";
                                        }
                                        pos = 0;

                                        life = 3;
                                        Guess.setText(Empty);
                                        Collections.shuffle(alphabets);
                                        Assign(alphabets);
                                    }
                                    if (life == 1) {
                                        Life1.setImageResource(R.drawable.yellowheart);
                                        Life2.setImageResource(R.drawable.yellowheart);
                                        score = 30;
                                        Empty = "";
                                        for (String alphabet : wordReqd) {
                                            Empty += "_";
                                        }
                                        pos = 0;

                                        life = 3;
                                        Guess.setText(Empty);
                                        Collections.shuffle(alphabets);
                                        Assign(alphabets);
                                    }

                                }
                            });
                            dialog.show();
                        } else {
                            Empty = "";
                            pos = 0;
                            final Dialog dialog = new Dialog(GameActivity.this);
                            dialog.setContentView(R.layout.custom_dialog);

                            Life3 = findViewById(R.id.Life3);
                            Life3.setImageResource(R.drawable.greyheart);
                            TextView Score = dialog.findViewById(R.id.Score);
                            Score.setText("You lost. Score: 0");
                            Button Home = dialog.findViewById(R.id.Home);
                            Button PlayAgain = dialog.findViewById(R.id.PlayAgain);
                            Home.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(GameActivity.this, MainActivity.class);
                                    dialog.dismiss();
                                    startActivity(intent);
                                }
                            });
                            PlayAgain.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                    life = 3;
                                    Life1.setImageResource(R.drawable.yellowheart);
                                    Life2.setImageResource(R.drawable.yellowheart);
                                    Life3.setImageResource(R.drawable.yellowheart);
                                    score = 30;
                                    Empty = "";
                                    for (String alphabet : wordReqd) {
                                        Empty += "_";
                                    }
                                    pos = 0;
                                    Guess.setText(Empty);
                                    Collections.shuffle(alphabets);
                                    Assign(alphabets);
                                }
                            });
                            dialog.show();
                        }
                    } else if (!(Empty.equals(theInput))) {
                        Empty = "";
                        pos = 0;
                        for (String alphabet : wordReqd) {
                            Empty += "_";
                        }
                        Guess.setText(Empty);

                        Toast.makeText(GameActivity.this, "Incorrect Guess", Toast.LENGTH_SHORT).show();
                        life = life - 1;
                        if (life == 2) {
                            Life1 = findViewById(R.id.Life1);
                            Life1.setImageResource(R.drawable.greyheart);
                            Collections.shuffle(alphabets);
                            Assign(alphabets);
                        } else if (life == 1) {
                            Life2 = findViewById(R.id.Life2);
                            Life2.setImageResource(R.drawable.greyheart);
                            Collections.shuffle(alphabets);
                            Assign(alphabets);
                        }
                        score = score - 10;
                        final Dialog dialog = new Dialog(GameActivity.this);
                        dialog.setContentView(R.layout.custom_dialog);

                        TextView Score = dialog.findViewById(R.id.Score);
                        Score.setText(String.valueOf(score));
                        Button Home = dialog.findViewById(R.id.Home);
                        Button PlayAgain = dialog.findViewById(R.id.PlayAgain);
                        Home.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(GameActivity.this, MainActivity.class);
                                dialog.dismiss();
                                startActivity(intent);
                            }
                        });
                        PlayAgain.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                                score = 30;
                                life = 3;
                                Life1.setImageResource(R.drawable.yellowheart);
                                Life2.setImageResource(R.drawable.yellowheart);
                                Life3.setImageResource(R.drawable.yellowheart);
                                Empty = "";
                                for (String alphabet : wordReqd) {
                                    Empty += "_";
                                }
                                pos = 0;
                                Guess.setText(Empty);
                                Collections.shuffle(alphabets);
                                Assign(alphabets);
                            }
                        });
                    }
                }
                else{
                    Toast.makeText(GameActivity.this, "Word Limit Not Reached", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void Assign(ArrayList<String> alphabets) {
        pos = 0;
        b1 = (Button) findViewById(R.id.b1);
        b1.setText(alphabets.get(0));
        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (pos< count){
                Empty = Empty.substring(0, pos) + alphabets.get(0) + Empty.substring(pos + 1);
                Guess.setText(Empty);
                pos++;
                }
                else{
                    Toast.makeText(GameActivity.this, "Word Limit Reached", Toast.LENGTH_SHORT).show();
                }
            }
        });
        b2 = (Button) findViewById(R.id.b2);
        b2.setText(alphabets.get(1));
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pos< count) {
                    Empty = Empty.substring(0, pos) + alphabets.get(1) + Empty.substring(pos + 1);
                    Guess.setText(Empty);
                    pos++;
                }
                else{
                    Toast.makeText(GameActivity.this, "Word Limit Reached", Toast.LENGTH_SHORT).show();
                }
            }
        });

        b3 = (Button) findViewById(R.id.b3);
        b3.setText(alphabets.get(2));
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pos< count){
                Empty = Empty.substring(0, pos) + alphabets.get(2) + Empty.substring(pos + 1);
                Guess.setText(Empty);
                pos++;}
                else{
                    Toast.makeText(GameActivity.this, "Word Limit Reached", Toast.LENGTH_SHORT).show();
                }
            }
        });
        b4 = (Button) findViewById(R.id.b4);
        b4.setText(alphabets.get(3));
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pos< count){
                Empty = Empty.substring(0, pos) + alphabets.get(3) + Empty.substring(pos + 1);
                Guess.setText(Empty);
                pos++;}
                else{
                    Toast.makeText(GameActivity.this, "Word Limit Reached", Toast.LENGTH_SHORT).show();
                }
            }
        });
        b5 = (Button) findViewById(R.id.b5);
        b5.setText(alphabets.get(4));
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pos< count){
                Empty = Empty.substring(0, pos) + alphabets.get(4) + Empty.substring(pos + 1);
                Guess.setText(Empty);
                pos++;}
                else{
                    Toast.makeText(GameActivity.this, "Word Limit Reached", Toast.LENGTH_SHORT).show();
                }
            }
        });
        b6 = (Button) findViewById(R.id.b6);
        b6.setText(alphabets.get(5));
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pos< count){
                Empty = Empty.substring(0, pos) + alphabets.get(5) + Empty.substring(pos + 1);
                Guess.setText(Empty);

                pos++;}
                else{
                    Toast.makeText(GameActivity.this, "Word Limit Reached", Toast.LENGTH_SHORT).show();
                }
            }
        });
        b7 = (Button) findViewById(R.id.b7);
        b7.setText(alphabets.get(6));
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pos< count){
                Empty = Empty.substring(0, pos) + alphabets.get(6) + Empty.substring(pos + 1);
                Guess.setText(Empty);
                pos++;}
                else{
                    Toast.makeText(GameActivity.this, "Word Limit Reached", Toast.LENGTH_SHORT).show();
                }
            }
        });
        b8 = (Button) findViewById(R.id.b8);
        b8.setText(alphabets.get(7));
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pos< count){
                Empty = Empty.substring(0, pos) + alphabets.get(7) + Empty.substring(pos + 1);
                Guess.setText(Empty);
                pos++;}
                else{
                    Toast.makeText(GameActivity.this, "Word Limit Reached", Toast.LENGTH_SHORT).show();
                }
            }
        });
        b9 = (Button) findViewById(R.id.b9);
        b9.setText(alphabets.get(8));
        b9.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (pos< count){
                Empty = Empty.substring(0, pos) + alphabets.get(8) + Empty.substring(pos + 1);
                Guess.setText(Empty);
                pos++;}
                else{
                    Toast.makeText(GameActivity.this, "Word Limit Reached", Toast.LENGTH_SHORT).show();
                }
            }
        });
        b10 = (Button) findViewById(R.id.b10);
        b10.setText(alphabets.get(9));
        b10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pos< count){
                Empty = Empty.substring(0, pos) + alphabets.get(9) + Empty.substring(pos + 1);
                Guess.setText(Empty);
                pos++;}
                else{
                    Toast.makeText(GameActivity.this, "Word Limit Reached", Toast.LENGTH_SHORT).show();
                }
            }
        });
        b11 = (Button) findViewById(R.id.b11);
        b11.setText(alphabets.get(10));
        b11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pos< count){
                Empty = Empty.substring(0, pos) + alphabets.get(10) + Empty.substring(pos + 1);
                Guess.setText(Empty);
                pos++;}
                else{
                    Toast.makeText(GameActivity.this, "Word Limit Reached", Toast.LENGTH_SHORT).show();
                }
            }
        });
        b12 = (Button) findViewById(R.id.b12);
        b12.setText(alphabets.get(11));
        b12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pos< count){
                Empty = Empty.substring(0, pos) + alphabets.get(11) + Empty.substring(pos + 1);
                Guess.setText(Empty);
                pos++;}
                else{
                    Toast.makeText(GameActivity.this, "Word Limit Reached", Toast.LENGTH_SHORT).show();
                }
            }
        });
        b13 = (Button) findViewById(R.id.b13);
        b13.setText(alphabets.get(12));
        b13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pos< count){
                Empty = Empty.substring(0, pos) + alphabets.get(12) + Empty.substring(pos + 1);
                Guess.setText(Empty);
                pos++;}
                else{
                    Toast.makeText(GameActivity.this, "Word Limit Reached", Toast.LENGTH_SHORT).show();
                }
            }
        });
        b14 = (Button) findViewById(R.id.b14);
        b14.setText(alphabets.get(13));
        b14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pos< count){
                Empty = Empty.substring(0, pos) + alphabets.get(13) + Empty.substring(pos + 1);
                Guess.setText(Empty);
                pos++;}
                else{
                    Toast.makeText(GameActivity.this, "Word Limit Reached", Toast.LENGTH_SHORT).show();
                }
            }
        });
        b15 = (Button) findViewById(R.id.b15);
        b15.setText(alphabets.get(14));
        b15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pos< count) {
                    Empty = Empty.substring(0, pos) + alphabets.get(14) + Empty.substring(pos + 1);
                    Guess.setText(Empty);
                    pos++;
                }
                else{
                    Toast.makeText(GameActivity.this, "Word Limit Reached", Toast.LENGTH_SHORT).show();
                }
            }
        });
        b16 = (Button) findViewById(R.id.b16);
        b16.setText(alphabets.get(15));
        b16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pos < count) {
                    Empty = Empty.substring(0, pos) + alphabets.get(15) + Empty.substring(pos + 1);
                    Guess.setText(Empty);
                    pos++;
                }
                else{
                    Toast.makeText(GameActivity.this, "Word Limit Reached", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

