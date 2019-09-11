package com.example.myapplicationfalas2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class AjudaActivity extends AppCompatActivity {
    Boolean saiu = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajuda);
        ImageButton botaovolta = findViewById(R.id.setinha);
        botaovolta.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                volta();
            }
        });
    }
    public void volta(){
        saiu = false;
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    protected void onPause() {
        if(saiu){
            MediaPlayer somSaiu;
            somSaiu = MediaPlayer.create(this, R.raw.saiu_app);
            somSaiu.start();
        }
        super.onPause();
    }
}
