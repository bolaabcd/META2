package com.example.myapplicationfalas2;

import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.provider.MediaStore;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.TextView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity{

    ArrayList<String> perguntas = new ArrayList<>();
    TextToSpeech falador;
    TextView entrada;
    int tempo;
    MediaPlayer somSaiu;
    TextView mensagens;
    Button voltar;
    Button falabotao;
    Button editar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final MediaPlayer somPerg = MediaPlayer.create(this, R.raw.notification);
        super.onCreate(savedInstanceState);
        totela1(somPerg);


        /*try{

        }
        catch (Exception e){
            entrada.setText("ERRO: "+e.toString());
        }*/

        /*
        setContentView(R.layout.configurator);
        Button voltar= findViewById(R.id.button13);
        setContentView(R.layout.activity_main);*/

        //Button voltar = paginaconfiguradora.findViewById(R.id.button13);
        //mensagens=paginaconfiguradora.findViewById(R.id.textView);



        /*
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
                    String pRest = strnPerg + "perguntas";
                    falador.speak(pRest,TextToSpeech.QUEUE_FLUSH,null);
                }
            }

            @Override
            public void onError(String utteranceId) {
                // There was an error.

            }

        });








*/
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {//sucodeuvaa
        if(keyCode == KeyEvent.KEYCODE_HEADSETHOOK||keyCode == 126||keyCode == 127){
            falar();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    public int getempo(){
        return 3600000*Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+60000*Calendar.getInstance().get(Calendar.MINUTE)+1000*Calendar.getInstance().get(Calendar.SECOND)+Calendar.getInstance().get(Calendar.MILLISECOND);
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

    public void totela2(final MediaPlayer sperg){
        setContentView(R.layout.configurator);
        voltar=findViewById(R.id.button13);
        voltar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                totela1(sperg);
            }
        });
    }
    public void totela1(final MediaPlayer sperg){
        setContentView(R.layout.activity_main);
        falabotao = findViewById(R.id.Fala);
        editar = findViewById(R.id.button10);
        entrada = findViewById(R.id.Entrada);
        falabotao.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String oque = entrada.getText().toString();
                if(!perguntas.contains(oque)){
                    sperg.start();
                    perguntas.add(oque);
                } else {
                    Toast.makeText(getApplicationContext(),"Essa mensagem já foi enviada", Toast.LENGTH_SHORT).show();

                }

            }
        });
        editar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                totela2(sperg);
            }
        });
    }



    @Override
    protected void onPause() {
        if(falador!=null){
            tempo=getempo();
            somSaiu=MediaPlayer.create(this,R.raw.saiu_app);
            somSaiu.start();
//            entrada.setText("VOCE SAIU DO APLICAtIVO NAO E MESMO???");
            //falador.stop();
            //falador.shutdown();
        }
        super.onPause();
    }
    @Override
    protected void onResume(){
        if (getempo()-tempo<200){
            somSaiu.stop();
            falar();
        }
        tempo=0;
        super.onResume();
    }

}
