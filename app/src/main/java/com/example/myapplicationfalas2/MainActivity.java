package com.example.myapplicationfalas2;

import android.content.res.Configuration;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.speech.tts.TextToSpeech;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.TextView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import org.w3c.dom.Text;

import java.util.Locale;

public class MainActivity extends AppCompatActivity{
    TextToSpeech falador;
    TextView entrada;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button falabotao = findViewById(R.id.Fala);
        entrada = findViewById(R.id.Entrada);
        falador = new TextToSpeech(MainActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i!=TextToSpeech.ERROR){
                    falador.setLanguage(Locale.getDefault());
                }
            }
        });

        falabotao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                falador.setLanguage(Locale.getDefault());
                String oque = entrada.getText().toString();
                falador.setSpeechRate(1f);
                falador.speak(oque,TextToSpeech.QUEUE_FLUSH,null);


            }
        });
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_HEADSETHOOK){
            entrada.setText("Botão de ouvir apertado!!!");
            falador.speak("Botao apertado!!",TextToSpeech.QUEUE_FLUSH,null);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    protected void onPause() {
        if(falador!=null){
            entrada.setText("VOCE SAIU DO APLICAtIVO NAO E MESMO???");
            //falador.stop();
            //falador.shutdown();
        }
        super.onPause();
    }
}
