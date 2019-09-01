package com.example.myapplicationfalas2;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity{

    ArrayList<String> perguntas = new ArrayList<>();
    TextToSpeech falador;
    TextView entrada;
    int tempo;
    MediaPlayer somSaiu;
    TextView mensagens;
    Button voltar;
    ImageButton falabotao;
    ImageButton deslikebotao;
    ImageButton likebotao;
    Button editar;
    CharSequence textentrada;
    TextView pag2;//MENSAGENS PAGINA 2
    Button somapag2;//botão + Página 2

    int paginatual;//SALVA EM QUAL PÁGINA ESTÁ!
    ArrayList<String> palavras;//SALVA AS PALAVRAS DO MOMENTO!

    public ArrayList<String> getpage(int palporpag,int numerodapag,ArrayList<String>palavras){//Retorna página especificada
        return null;
    }
    public void updatepagef1(ArrayList<String> palavrasdapag){//Atualiza palavras da página na tela 1

    }
    public void updatepagef2(int numerodalista){//Atualiza as palavras da página na tela 2
        pag2.setText("OILA");
        ArrayList<String> botoestexto=getsavedwords(1,true);
        int n=0;
        while(n<botoestexto.size()){
            pag2.setText(botoestexto.get(n));
            n+=1;
        }
    }
    public int getnumpag(int palporpag,int numpalavras){//Retorna o número de páginas
        return (int) Math.floor(palporpag/numpalavras);
    }
    public ArrayList<String> move(boolean baixocima, ArrayList<String> lista, int numerodobotao){//move um botão da segunda página
        return null;
    }
    public ArrayList<String> getsavedwords(int numerodalista/*Pra fazer listas de matérias diferentes por exemplo*/, boolean palavrasfrases){
        String[] lines = getsavedstring(palavrasfrases).split(System.getProperty("line.separator"));
        int linha=0;
        int ultimalinha=lines.length;
        ArrayList<String> resultado= new ArrayList<String>();
        int listatual=1;
    while(true){
        if (linha==ultimalinha)break;
        else if(palavrasfrases&&lines[linha]!="") resultado.add(lines[linha]);
        else if((!palavrasfrases)&&lines[linha]!=""&&listatual!=numerodalista)numerodalista=+1;
        else if(!(palavrasfrases)&&lines[linha]!=""&&listatual==numerodalista)resultado.add(lines[linha]);
        else break;
        linha+=1;
        }
    return resultado;
    }
    public String getsavedstring(boolean palavrasfrases){
        try{
            FileOutputStream escrever = openFileOutput("palavras.txt", Context.MODE_PRIVATE);
            escrever.write("mensagem a escrever COMPLETA\nAOAOAOIAOIOAIOIA\noioioioioioi\nobamanaracasa\nuiawuadduwa\nOLHASO\nFUNCIONOU????\nnaofacoideiaainda!!!".getBytes());
            escrever.close();
            FileInputStream ler;
            if (palavrasfrases) ler = openFileInput("palavras.txt");
            else ler = openFileInput("frases.txt");
            int pare=0;
            String conteudo="";
            while (true){
                pare=ler.read();
                if(pare!=-1) conteudo= conteudo + (char) pare;
                else break;
            }
            //pag2.setText(conteudo);
            return conteudo;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public ArrayList<String> changeplace(int primeiro, int segundo, ArrayList<String> lista){//trocar posição de duas palavras
        return null;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{FileInputStream ler = openFileInput("palavras.txt");}
        catch (FileNotFoundException ex){
            File file= new File(this.getFilesDir(),"palavras.txt");
        }
        catch (Exception e){
            e.printStackTrace();
        }

        final MediaPlayer somPerg = MediaPlayer.create(this, R.raw.notification);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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



/* ESCREVE-ERROS (pode ser muito inutil), mas serve como padrao pra escrever arquivos
        try{
            File file= new File(this.getFilesDir(),"nomedoarquivo");
            FileOutputStream escrever = openFileOutput("nomedoarquivo",Context.MODE_PRIVATE);
            escrever.write("mensagem a escrever".getBytes());
            escrever.close();
            FileInputStream ler = openFileInput("mansagem a escrever.txt");
            int pare=0;
            String conteudo="";
            while (pare!=-1){
                pare=ler.read();
                conteudo= conteudo + (char) pare;
            }
            entrada.setText(conteudo);
        } catch (Exception e){
            e.printStackTrace();
        }
*/


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
        pag2=findViewById(R.id.textView);
        somapag2=findViewById(R.id.button39);
        somapag2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                updatepagef2(0);
            }
        });
        voltar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                totela1(sperg);
                entrada.setText(textentrada);
            }
        });
    }
    public void totela1(final MediaPlayer sperg){
        setContentView(R.layout.activity_main);
        falabotao = findViewById(R.id.Fala);
        likebotao = findViewById(R.id.like);
        deslikebotao = findViewById(R.id.deslike);
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
        likebotao.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                falador.speak("entendi",TextToSpeech.QUEUE_FLUSH,null);
            }
        });
        deslikebotao.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                falador.speak("não entendi",TextToSpeech.QUEUE_FLUSH,null);
            }
        });

        editar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                textentrada=entrada.getText();
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
