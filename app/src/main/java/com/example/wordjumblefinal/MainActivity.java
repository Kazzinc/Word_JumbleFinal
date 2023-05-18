package com.example.wordjumblefinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private Button start;
    private EditText Word,Clue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = (Button) findViewById(R.id.start);
        Word=findViewById(R.id.Word);
        Clue=findViewById(R.id.Clue);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGameActivity();

            }
        });
    }
    public void openGameActivity(){
        Intent intent=new Intent(MainActivity.this, GameActivity.class);
        intent.putExtra("Input", Word.getText().toString());
        intent.putExtra("Clue", Clue.getText().toString());
        startActivity(intent);
    }
}