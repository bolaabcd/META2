
package com.example.myapplicationfalas2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.PopupMenu;

import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
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

import static java.lang.Boolean.TRUE;
import static java.lang.Thread.sleep;


public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{

    ArrayList<String> perguntas = new ArrayList<>();
    TextToSpeech falador;
    TextView entrada;
    int tempo;
    MediaPlayer somSaiu;
    TextView mensagens;
    AppCompatButton voltar;
    AppCompatImageButton falabotao;
    AppCompatImageButton deslikebotao;
    AppCompatImageButton likebotao;
    AppCompatButton editar;
    CharSequence textentrada;
    TextView pag2;//MENSAGENS PAGINA 2
    AppCompatButton somapag2;//botão + Página 2
    AppCompatButton trespontos;
    Boolean saiu = true;

    int telatual;//SALVA EM QUAL tela ESTÁ!
    ArrayList<String> palavras;//SALVA AS PALAVRAS DO MOMENTO!
    int pagpaltel2=1;//Página atual de palavras da tela 2
    int pagpaltel1=1;//Página atual de palavras da tela 1

    //Botões da página 2:
    AppCompatButton b1pp2;
    AppCompatButton b2pp2;
    AppCompatButton b3pp2;
   AppCompatButton b4pp2;
   AppCompatButton b5pp2;
   AppCompatButton b6pp2;
   AppCompatButton b7pp2;
   AppCompatButton b8pp2;
   AppCompatButton b9pp2;
   AppCompatButton b10pp2;
   AppCompatButton b11pp2;
   AppCompatButton b12pp2;
   AppCompatButton b13pp2;
   AppCompatButton b14pp2;
   AppCompatButton b15pp2;

    //Botões de ir e voltar página 2:
   AppCompatButton vaipag2;
   AppCompatButton voltapag2;

    //Botões de palavras da pagina 1:
   AppCompatButton b1pp1;
   AppCompatButton b2pp1;
   AppCompatButton b3pp1;
   AppCompatButton b4pp1;

    //Botões de ir e voltar da tela 1:
   AppCompatButton vaipag1;
   AppCompatButton voltapag1;


    public ArrayList<String> getpage(int palporpag,int numerodapag,ArrayList<String>palavras){//Retorna página especificada
        return null;
    }
    public void updatepagef1(int numerodalista/*Pra fazer listas de matérias diferentes por exemplo*/,int paginadepalavras){//Atualiza palavras da página na tela 1
        try{
            ArrayList<String> botoestexto=getpage(getsavedwords(numerodalista,true),4,paginadepalavras);

            if (botoestexto.size()>0)b1pp1.setText(botoestexto.get(0));
            else b1pp1.setText("");
            if (botoestexto.size()>1)b2pp1.setText(botoestexto.get(1));
            else b2pp1.setText("");
            if (botoestexto.size()>2)b3pp1.setText(botoestexto.get(2));
            else b3pp1.setText("");
            if (botoestexto.size()>3)b4pp1.setText(botoestexto.get(3));
            else b4pp1.setText("");
            //b1pp1.setText("MaiúÚSculho");
        }
        catch (Exception e){
            entrada.setText(e.toString());
        }
    }
    public void updatepagef2(int numerodalista/*Pra fazer listas de matérias diferentes por exemplo*/,int paginadepalavras){//Atualiza as palavras da página na tela 2
        //pag2.setText("OILA");
        ArrayList<String> botoestexto=getpage(getsavedwords(numerodalista,true),15,paginadepalavras);
        if (botoestexto.size()>0)b1pp2.setText(botoestexto.get(0));
        else b1pp2.setText("");
        if (botoestexto.size()>1)b2pp2.setText(botoestexto.get(1));
        else b2pp2.setText("");
        if (botoestexto.size()>2)b3pp2.setText(botoestexto.get(2));
        else b3pp2.setText("");
        if (botoestexto.size()>3)b4pp2.setText(botoestexto.get(3));
        else b4pp2.setText("");
        if (botoestexto.size()>4)b5pp2.setText(botoestexto.get(4));
        else b5pp2.setText("");
        if (botoestexto.size()>5)b6pp2.setText(botoestexto.get(5));
        else b6pp2.setText("");
        if (botoestexto.size()>6)b7pp2.setText(botoestexto.get(6));
        else b7pp2.setText("");
        if (botoestexto.size()>7)b8pp2.setText(botoestexto.get(7));
        else b8pp2.setText("");
        if (botoestexto.size()>8)b9pp2.setText(botoestexto.get(8));
        else b9pp2.setText("");
        if (botoestexto.size()>9)b10pp2.setText(botoestexto.get(9));
        else b10pp2.setText("");
        if (botoestexto.size()>10)b11pp2.setText(botoestexto.get(10));
        else b11pp2.setText("");
        if (botoestexto.size()>11)b12pp2.setText(botoestexto.get(11));
        else b12pp2.setText("");
        if (botoestexto.size()>12)b13pp2.setText(botoestexto.get(12));
        else b13pp2.setText("");
        if (botoestexto.size()>13)b14pp2.setText(botoestexto.get(13));
        else b14pp2.setText("");
        if (botoestexto.size()>14)b15pp2.setText(botoestexto.get(14));
        else b15pp2.setText("");
    }
    public ArrayList<String> getpage(ArrayList<String> palavras,int palporpag,int paginadesejada){
        String palavratual;
        int limite=palavras.size();
        int inicial=palporpag*(paginadesejada-1);
        int finali=palporpag*paginadesejada-1;
        int palavratualn=inicial;
        if(finali>limite-1)finali=limite-1;
        ArrayList<String> resposta= new ArrayList<String>();
        try{
        while(palavratualn<=finali){
            palavratual=palavras.get(palavratualn);
            palavratualn+=1;
            resposta.add(palavratual);
        }
        }
        catch (Exception e){
            pag2.setText(e.toString());
        }
        return resposta;
    }
    public int getnumpag(int palporpag,int numpalavras){//Retorna o número de páginas
        return (int) Math.ceil(((double)numpalavras)/((double)palporpag));
    }
    public ArrayList<String> move(boolean baixocima, ArrayList<String> lista, int numerodobotao){//move um botão da segunda página
        return null;
    }
    public ArrayList<String> getsavedwords(int numerodalista/*Pra fazer listas de matérias diferentes por exemplo*/, boolean palavrasfrases){
        int ultimalinha;
        String[] lines;
         if(getsavedstring(palavrasfrases)==null) {
             lines= new String[0];
             ultimalinha=0;
        }else{
            lines = getsavedstring(palavrasfrases).split(System.getProperty("line.separator"));
            ultimalinha=lines.length;
        }
        int linha=0;
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
            /*
            FileOutputStream escrever = openFileOutput("palavras.txt", Context.MODE_PRIVATE);
            escrever.write("mensagem\nAOAOAOIAOIOAIOIA\noioioioioioi\nobamanaracasa\nuiawuadduwa\nOLHASO\nFUNCIONOU????\nnaofacoideiaainda!!!\nmas\nlogo\nsaberei\nnão\né\nmesmo???\n1\n2\n3n\4n\5\n6\n7\n8\n9\n10\n11\n12\n13n\n14\n15\n16\n17\n18\n19\n20\n21\n22\n23\n24\n25\n26\n27\n28\n29\n30".getBytes());
            escrever.close();
            */
            FileInputStream ler;
            //File arq;
            byte[] bytes;
            int pare;
            int leng=1313131313;
            FileInputStream arq;
            if (palavrasfrases) {
                leng=0;
                arq=openFileInput("palavras.txt");
                while (true){
                    pare=arq.read();
                    if(pare==-1) break;
                    leng+=1;
                }
                bytes = new byte[leng];
                ler= openFileInput("palavras.txt");
            }
            else {
                leng=0;
                arq=openFileInput("frases.txt");
                while (true){
                    pare=arq.read();
                    if(pare==-1) break;
                    leng+=1;
                }
                bytes = new byte[leng];
                ler= openFileInput("frases.txt");
                /*arq = new File("frases.txt") ;
                bytes = new byte[(int) arq.length()];
                ler =  openFileInput("frases.txt");*/
            }
            //ler = new FileInputStream(arquivo);

            ler.read(bytes);
            //pag2.setText(Integer.toString(leng));
            String saida= new String(bytes);
            //pag2.setText(saida);
            return saida;
            /*
            int pare=0;

            String conteudo="";
            while (true){
                pare=ler.read();
                if(pare!=-1) conteudo= conteudo + (char) pare;
                else break;
            }
            //pag2.setText(conteudo);
            return conteudo;*/
        }
        catch (Exception e){
            //pag2.setText(e.toString());
            e.printStackTrace();
        }
        return null;
    }
    public ArrayList<String> changeplace(int primeiro, int segundo, ArrayList<String> lista){//trocar posição de duas palavras
        return null;
    }
    public boolean hasespaco(String entrada){
        int i;
        for(i=0;i<entrada.length();i++){
            if (entrada.charAt(i)==" ".charAt(0)||entrada.charAt(i)=="\n".charAt(0)) return true;
        }
        return false;
    }
    public String removeespaco(String entrada){
        int i;
        String resposta="";
        for(i=0;i<entrada.length();i++){
            if (!(entrada.charAt(i)==" ".charAt(0)||entrada.charAt(i)=="\n".charAt(0)))resposta+=entrada.charAt(i);
        }
        return resposta;
    }
    public void savestring(String s, boolean palavrasfrases){
        try {
            if (palavrasfrases) {
                FileOutputStream escrever = openFileOutput("palavras.txt", Context.MODE_PRIVATE);
                escrever.write(s.getBytes());
                escrever.close();
            }else{
                FileOutputStream escrever = openFileOutput("frases.txt", Context.MODE_PRIVATE);
                escrever.write(s.getBytes());
                escrever.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void ativarcaixadepalavras(){ //Retorna "cancelado" se o usuário cancelar
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Digite a palavra:");
        final EditText input = new EditText(this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
        builder.setView(input);

        builder.setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (hasespaco(input.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Frases não são salvas por aqui!", Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                } else if (input.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Digite algo para salvar!!", Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                } else {
                    if (getsavedstring(true)!=null) savestring(getsavedstring(true) + "\n" + input.getText().toString(), true);//ISSO AQUI TA ERRaDO!!!
                    else savestring(input.getText().toString(), true);
                    Toast.makeText(getApplicationContext(), "Palavra salva com sucesso!", Toast.LENGTH_SHORT).show();
                    updatepagef2(1, pagpaltel2);
                }
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                Toast.makeText(getApplicationContext(),"Operação cancelada com sucesso.", Toast.LENGTH_SHORT).show();
            }
        });

        builder.show();


        //else Toast.makeText(getApplicationContext(),"Palavra salva com sucesso!", Toast.LENGTH_SHORT).show();
    }
    public int[] getposultpal(String entrada){//retorna posição inicial e final da ultima palavra
        int[] resposta= new int[2];

        return resposta;
    }
    public String tirapalavra(String entrada){//ARRUNMAR
        String resultado="";
        if(entrada.equals(""))return entrada;
        boolean atencao=false;

        if(entrada.charAt(0)==" ".charAt(0)||entrada.charAt(0)=="\n".charAt(0))atencao=true;
        for(int i=0;i<entrada.length();i++){
            if((!atencao)&&(entrada.charAt(i)==" ".charAt(0)||entrada.charAt(i)=="\n".charAt(0)))break;
            else if(atencao&&(entrada.charAt(i)==" ".charAt(0)||entrada.charAt(i)=="\n".charAt(0)))continue;
            atencao=false;
            resultado+=entrada.charAt(i);
        }

        return resultado;
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
       AppCompatButton voltar= findViewById(R.id.button13);
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
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.cfg:
                saiu = false;
                Intent intent = new Intent(this, config.class);
                startActivity(intent);
                return true;
            case R.id.help:
                saiu = false;
                Intent intent2 = new Intent(this, AjudaActivity.class);
                startActivity(intent2);
                return true;
            case R.id.sobre:
                saiu = false;
                Intent intent3 = new Intent(this, SobreActivity.class);
                startActivity(intent3);
                return true;
            default:
                return false;
        }
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
        //Botões das palavras tela 2:
        b1pp2=findViewById(R.id.button21);
        b2pp2=findViewById(R.id.button24);
        b3pp2=findViewById(R.id.button18);
        b4pp2=findViewById(R.id.button16);
        b5pp2=findViewById(R.id.button27);
        b6pp2=findViewById(R.id.button30);
        b7pp2=findViewById(R.id.button33);
        b8pp2=findViewById(R.id.button36);
        b9pp2=findViewById(R.id.button60);
        b10pp2=findViewById(R.id.button57);
        b11pp2=findViewById(R.id.button54);
        b12pp2=findViewById(R.id.button51);
        b13pp2=findViewById(R.id.button48);
        b14pp2=findViewById(R.id.button45);
        b15pp2=findViewById(R.id.button42);


        //Botões de ir e voltar página 2:
        vaipag2=findViewById(R.id.button11);
        voltapag2=findViewById(R.id.button12);


        somapag2=findViewById(R.id.button39);

        //Setando Botões:
        somapag2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String ent;
                ativarcaixadepalavras();
                //ent=getnewWORDinput();
            }
        });

        voltar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                totela1(sperg);
                entrada.setText(textentrada);
            }
        });
        vaipag2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //pag2.setText(Integer.toString(getnumpag(15,getsavedwords(1,true).size())));
                //pag2.setText(Integer.toString(pagpaltel2));
                if(getnumpag(15,getsavedwords(1,true).size())>pagpaltel2) {
                    pagpaltel2 += 1;
                    updatepagef2(1, pagpaltel2);
                }

            }

        });
        voltapag2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (pagpaltel2!=1) {
                    pagpaltel2-=1;
                    updatepagef2(1,pagpaltel2);}
            }
        });
        updatepagef2(1,pagpaltel2);
    }
    public void totela1(final MediaPlayer sperg){
        setContentView(R.layout.activity_main);
        falabotao = findViewById(R.id.Fala);
        likebotao = findViewById(R.id.like);
        deslikebotao = findViewById(R.id.deslike);
        editar = findViewById(R.id.button10);
        entrada = findViewById(R.id.Entrada);


        //Botões das palavras tela 1:
        trespontos =findViewById(R.id.opcoes);
        b1pp1=findViewById(R.id.button);
        b2pp1=findViewById(R.id.button2);
        b3pp1=findViewById(R.id.button3);
        b4pp1=findViewById(R.id.button4);
        //Botões de ir e voltar tela 1:
        vaipag1=findViewById(R.id.button8);
        vaipag1.setRotation(180);
        voltapag1=findViewById(R.id.button9);
        //Botões de apagar:
        //apagatudo=findViewById(R.id.button56);
        //apagapalavra=findViewById(R.id.button76);


        //setando Botões da apagar:
        /*apagatudo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(!entrada.getText().toString().equals("")){
                    entrada.setText("");
                }
            }
        });*/

    /*apagapalavra.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(!entrada.getText().toString().equals("")){
                    entrada.setText(tirapalavra(entrada.getText().toString()));
                }
            }
        });*/
        //BOTOES DAS PALAVRAS TELA 1:
        b1pp1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(!b1pp1.getText().toString().equals("")){
                    entrada.setText(entrada.getText().toString()+b1pp1.getText().toString()+" ");
                }
            }
        });
        b2pp1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(!b2pp1.getText().toString().equals("")){
                    entrada.setText(entrada.getText().toString()+b2pp1.getText().toString()+" ");
                }
            }
        });
        b3pp1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(!b3pp1.getText().toString().equals("")){
                    entrada.setText(entrada.getText().toString()+b3pp1.getText().toString()+" ");
                }
            }
        });
        b4pp1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(!b4pp1.getText().toString().equals("")){
                    entrada.setText(entrada.getText().toString()+b4pp1.getText().toString()+" ");
                }
            }
        });
        /*
        b5pp1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(!b5pp1.getText().toString().equals("")){
                    entrada.setText(entrada.getText().toString()+" "+b5pp1.getText().toString());
                }
            }
        });
        b6pp1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(!b6pp1.getText().toString().equals("")){
                    entrada.setText(entrada.getText().toString()+" "+b6pp1.getText().toString());
                }
            }
        });
        b7pp1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(!b7pp1.getText().toString().equals("")){
                    entrada.setText(entrada.getText().toString()+" "+b7pp1.getText().toString());
                }
            }
        });*/
        //setando botões
        falabotao.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String oque = entrada.getText().toString();
                if(oque.equals(""))Toast.makeText(getApplicationContext(),"Digite algo para enviar uma mensagem!", Toast.LENGTH_SHORT).show();
                else if(!perguntas.contains(oque)){
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
//        trespontos.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
//                // nothing yet
//            }
//        });

        editar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                textentrada = entrada.getText();
                totela2(sperg);
            }
        });
        vaipag1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //pag2.setText(Integer.toString(getnumpag(15,getsavedwords(1,true).size())));
                //pag2.setText(Integer.toString(pagpaltel2));
                if(getnumpag(4,getsavedwords(1,true).size())>pagpaltel1) {
                    pagpaltel1 += 1;
                    updatepagef1(1, pagpaltel1);
                }

            }

        });
        voltapag1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (pagpaltel1!=1) {
                    pagpaltel1-=1;
                    updatepagef1(1,pagpaltel1);}
            }
        });
        updatepagef1(1,pagpaltel1);

        trespontos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(MainActivity.this, v);
                popup.setOnMenuItemClickListener(MainActivity.this);
                popup.inflate(R.menu.popup_menu);
                popup.show();
            }
        });
    }


    @Override
    protected void onPause() {
        if(falador!=null){
            tempo=getempo();
            if(saiu){
                somSaiu=MediaPlayer.create(this,R.raw.saiu_app);
                somSaiu.start();
            } else{
                saiu = false;
            }
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
        saiu = true;
        super.onResume();
    }

}
