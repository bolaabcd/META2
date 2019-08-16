package com.example.myapplicationfalas2;

import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.TextView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity{

    ArrayList<String> perguntas = new ArrayList<>();
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
                    Locale portugues = new Locale("PT", "BR");
                    falador.setLanguage(portugues);
                    falador.setSpeechRate(1f);
                }
            }
        });
        falador.setOnUtteranceProgressListener(new UtteranceProgressListener() {
            @Override
            public void onStart(String utteranceId) {
                // Speaking started.
            }

            @Override
            public void onDone(String utteranceId) {
                // Speaking stopped.
                if (perguntas.size() > 0){
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    String strnPerg = new Integer(perguntas.size()).toString();
                    String pRest = "Número de perguntas restantes" + strnPerg;
                    falador.speak(pRest,TextToSpeech.QUEUE_FLUSH,null);
                }
            }

            @Override
            public void onError(String utteranceId) {
                // There was an error.

            }

        });
        final MediaPlayer somPerg = MediaPlayer.create(this, R.raw.notification);
        falabotao.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                somPerg.start();
                String oque = entrada.getText().toString();
                perguntas.add(oque);
                System.out.println("gkmreskgmosr");

            }
        });
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {//sucodeuvaa
        if(keyCode == KeyEvent.KEYCODE_HEADSETHOOK||keyCode == 126||keyCode == 127){
            falar();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    public void falar(){
        if (perguntas.size() > 0) {
            String perg = perguntas.get(0);
            perguntas.remove(0);
            HashMap<String, String> map = new HashMap<String, String>();
            map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "ID");
            falador.speak(perg, TextToSpeech.QUEUE_FLUSH, map);

        }



    }
    @Override
    protected void onPause() {
        if(falador!=null){
            final MediaPlayer somSaiu = MediaPlayer.create(this,R.raw.saiu_app);
            somSaiu.start();
//            entrada.setText("VOCE SAIU DO APLICAtIVO NAO E MESMO???");
            //falador.stop();
            //falador.shutdown();
        }
        super.onPause();
    }
}
