package com.example.myapplicationfalas2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class AjudaActivity extends AppCompatActivity {

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
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
