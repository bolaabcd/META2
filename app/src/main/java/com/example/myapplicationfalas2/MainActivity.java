
package com.example.myapplicationfalas2;

import android.content.BroadcastReceiver;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.PopupMenu;

import androidx.constraintlayout.widget.ConstraintLayout;

import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.text.InputType;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
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
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;


import static java.lang.Thread.sleep;


class recebedor extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(MainActivity.getAppContexto(),"WOWOWOOWOWOWOWOOWOW", Toast.LENGTH_LONG);
        }
        }

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{
    static Context contexto;

    ArrayList<String> perguntas = new ArrayList<>();
    TextToSpeech falador;
    EditText entrada;
    int tempo;
    MediaPlayer somSaiu;
    TextView mensagens;
    AppCompatImageButton voltar;
    AppCompatImageButton falabotao;
    AppCompatImageButton deslikebotao;
    AppCompatImageButton likebotao;
    AppCompatButton editar;
    CharSequence textentrada;
    TextView pag2;//MENSAGENS PAGINA 2
    AppCompatButton somapag2;//botão + Página 2
    AppCompatButton trespontos;
    Boolean saiu = true;
    Boolean palavrasoufrases=true;


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
    //Linearlayouts da página 2:
    LinearLayout l1pp2;
    LinearLayout l2pp2;
    LinearLayout l3pp2;
    LinearLayout l4pp2;
    LinearLayout l5pp2;
    LinearLayout l6pp2;
    LinearLayout l7pp2;
    LinearLayout l8pp2;
    LinearLayout l9pp2;
    LinearLayout l10pp2;
    LinearLayout l11pp2;
    LinearLayout l12pp2;
    LinearLayout l13pp2;
    LinearLayout l14pp2;
    LinearLayout l15pp2;
    //Boteõs de apagar da página 2:
    AppCompatImageButton p1pp2;
    AppCompatImageButton p2pp2;
    AppCompatImageButton p3pp2;
    AppCompatImageButton p4pp2;
    AppCompatImageButton p5pp2;
    AppCompatImageButton p6pp2;
    AppCompatImageButton p7pp2;
    AppCompatImageButton p8pp2;
    AppCompatImageButton p9pp2;
    AppCompatImageButton p10pp2;
    AppCompatImageButton p11pp2;
    AppCompatImageButton p12pp2;
    AppCompatImageButton p13pp2;
    AppCompatImageButton p14pp2;
    AppCompatImageButton p15pp2;
    //Botões de ir e voltar página 2:
   AppCompatButton vaipag2;
   AppCompatButton voltapag2;

    //Botões de palavras da pagina 1:
   AppCompatButton b1pp1;
   AppCompatButton b2pp1;
   AppCompatButton b3pp1;
   AppCompatButton b4pp1;
    AppCompatButton b5pp1;
    AppCompatButton b6pp1;
    AppCompatButton b7pp1;
    AppCompatButton b8pp1;
    AppCompatButton b9pp1;
    AppCompatButton b10pp1;
    AppCompatButton b11pp1;
    AppCompatButton b12pp1;
    AppCompatButton b13pp1;
    AppCompatButton b14pp1;
    AppCompatButton b15pp1;

    //Botões de ir e voltar da tela 1:
   AppCompatButton vaipag1;
   AppCompatButton voltapag1;

   // Botões de pergunta
    AppCompatImageButton perg1;
    AppCompatImageButton perg2;
    AppCompatImageButton perg3;
    AppCompatImageButton perg4;

   //final SharedPreferences mPrefs=getSharedPreferences("labela", 0);
   //final SharedPreferences.Editor mEditor= mPrefs.edit();
   SharedPreferences mPrefs;
   SharedPreferences.Editor mEditor;

   int maxpaltela2;
   int maxpaltela1;


    public static Context getAppContexto() {
        return contexto;
    }

    public float getaltura(){
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.y / getResources().getDisplayMetrics().density;
    }
    public float getlargura(){
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x / getResources().getDisplayMetrics().density;

    }
    public void SalvaPerguntas(final int i){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final String key = "ps" + Integer.toString(i);
        builder.setTitle("Digite sua pergunta:");
        final EditText input = new EditText(this);
        input.setText(mPrefs.getString(key,""),TextView.BufferType.EDITABLE);
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
        builder.setView(input);
        builder.setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(input.getText().toString().length()==0){
                    Toast.makeText(getApplicationContext(),"Digite algo para salvar!", Toast.LENGTH_SHORT).show();
                } else {
                    mEditor.putString(key,input.getText().toString()).commit();
                    dialog.cancel();
                }
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }

        });

        builder.show();
    }
    public ArrayList<String> getpage(int palporpag,int numerodapag,ArrayList<String>palavras){//Retorna página especificada
        return null;
    }
    public void updatepagef1(int numerodalista/*Pra fazer listas de matérias diferentes por exemplo*/,int paginadepalavras){//Atualiza palavras da página na tela 1
        try{
            ArrayList<String> botoestexto=getpage(getsavedwords(numerodalista,true),maxpaltela1,paginadepalavras);
            if (maxpaltela1==15) {
                if (botoestexto.size() > 0) b1pp1.setText(botoestexto.get(0));
                else b1pp1.setText("");
                if (botoestexto.size() > 1) b2pp1.setText(botoestexto.get(1));
                else b2pp1.setText("");
                if (botoestexto.size() > 2) b3pp1.setText(botoestexto.get(2));
                else b3pp1.setText("");
                if (botoestexto.size() > 3) b4pp1.setText(botoestexto.get(3));
                else b4pp1.setText("");
                if (botoestexto.size() > 4) b5pp1.setText(botoestexto.get(4));
                else b5pp1.setText("");
                if (botoestexto.size() > 5) b6pp1.setText(botoestexto.get(5));
                else b6pp1.setText("");
                if (botoestexto.size() > 6) b7pp1.setText(botoestexto.get(6));
                else b7pp1.setText("");
                if (botoestexto.size() > 7) b8pp1.setText(botoestexto.get(7));
                else b8pp1.setText("");
                if (botoestexto.size() > 8) b9pp1.setText(botoestexto.get(8));
                else b9pp1.setText("");
                if (botoestexto.size() > 9) b10pp1.setText(botoestexto.get(9));
                else b10pp1.setText("");
                if (botoestexto.size() > 10) b11pp1.setText(botoestexto.get(10));
                else b11pp1.setText("");
                if (botoestexto.size() > 11) b12pp1.setText(botoestexto.get(11));
                else b12pp1.setText("");
                if (botoestexto.size() > 12) b13pp1.setText(botoestexto.get(12));
                else b13pp1.setText("");
                if (botoestexto.size() > 13) b14pp1.setText(botoestexto.get(13));
                else b14pp1.setText("");
                if (botoestexto.size() > 14) b15pp1.setText(botoestexto.get(14));
                else b15pp1.setText("");
            }else if(maxpaltela1==14){
                if (botoestexto.size() > 0) b1pp1.setText(botoestexto.get(0));
                else b1pp1.setText("");
                if (botoestexto.size() > 1) b2pp1.setText(botoestexto.get(1));
                else b2pp1.setText("");
                if (botoestexto.size() > 2) b3pp1.setText(botoestexto.get(2));
                else b3pp1.setText("");
                if (botoestexto.size() > 3) b4pp1.setText(botoestexto.get(3));
                else b4pp1.setText("");
                if (botoestexto.size() > 4) b5pp1.setText(botoestexto.get(4));
                else b5pp1.setText("");
                if (botoestexto.size() > 5) b6pp1.setText(botoestexto.get(5));
                else b6pp1.setText("");
                if (botoestexto.size() > 6) b7pp1.setText(botoestexto.get(6));
                else b7pp1.setText("");
                if (botoestexto.size() > 7) b8pp1.setText(botoestexto.get(7));
                else b8pp1.setText("");
                if (botoestexto.size() > 8) b9pp1.setText(botoestexto.get(8));
                else b9pp1.setText("");
                if (botoestexto.size() > 9) b10pp1.setText(botoestexto.get(9));
                else b10pp1.setText("");
                if (botoestexto.size() > 10) b11pp1.setText(botoestexto.get(10));
                else b11pp1.setText("");
                if (botoestexto.size() > 11) b12pp1.setText(botoestexto.get(11));
                else b12pp1.setText("");
                if (botoestexto.size() > 12) b13pp1.setText(botoestexto.get(12));
                else b13pp1.setText("");
                if (botoestexto.size() > 13) b14pp1.setText(botoestexto.get(13));
                else b14pp1.setText("");
            }else if(maxpaltela1==13){
                if (botoestexto.size() > 0) b1pp1.setText(botoestexto.get(0));
                else b1pp1.setText("");
                if (botoestexto.size() > 1) b2pp1.setText(botoestexto.get(1));
                else b2pp1.setText("");
                if (botoestexto.size() > 2) b3pp1.setText(botoestexto.get(2));
                else b3pp1.setText("");
                if (botoestexto.size() > 3) b4pp1.setText(botoestexto.get(3));
                else b4pp1.setText("");
                if (botoestexto.size() > 4) b5pp1.setText(botoestexto.get(4));
                else b5pp1.setText("");
                if (botoestexto.size() > 5) b6pp1.setText(botoestexto.get(5));
                else b6pp1.setText("");
                if (botoestexto.size() > 6) b7pp1.setText(botoestexto.get(6));
                else b7pp1.setText("");
                if (botoestexto.size() > 7) b8pp1.setText(botoestexto.get(7));
                else b8pp1.setText("");
                if (botoestexto.size() > 8) b9pp1.setText(botoestexto.get(8));
                else b9pp1.setText("");
                if (botoestexto.size() > 9) b10pp1.setText(botoestexto.get(9));
                else b10pp1.setText("");
                if (botoestexto.size() > 10) b11pp1.setText(botoestexto.get(10));
                else b11pp1.setText("");
                if (botoestexto.size() > 11) b12pp1.setText(botoestexto.get(11));
                else b12pp1.setText("");
                if (botoestexto.size() > 12) b13pp1.setText(botoestexto.get(12));
                else b13pp1.setText("");
            }else if(maxpaltela1==12){
                if (botoestexto.size() > 0) b1pp1.setText(botoestexto.get(0));
                else b1pp1.setText("");
                if (botoestexto.size() > 1) b2pp1.setText(botoestexto.get(1));
                else b2pp1.setText("");
                if (botoestexto.size() > 2) b3pp1.setText(botoestexto.get(2));
                else b3pp1.setText("");
                if (botoestexto.size() > 3) b4pp1.setText(botoestexto.get(3));
                else b4pp1.setText("");
                if (botoestexto.size() > 4) b5pp1.setText(botoestexto.get(4));
                else b5pp1.setText("");
                if (botoestexto.size() > 5) b6pp1.setText(botoestexto.get(5));
                else b6pp1.setText("");
                if (botoestexto.size() > 6) b7pp1.setText(botoestexto.get(6));
                else b7pp1.setText("");
                if (botoestexto.size() > 7) b8pp1.setText(botoestexto.get(7));
                else b8pp1.setText("");
                if (botoestexto.size() > 8) b9pp1.setText(botoestexto.get(8));
                else b9pp1.setText("");
                if (botoestexto.size() > 9) b10pp1.setText(botoestexto.get(9));
                else b10pp1.setText("");
                if (botoestexto.size() > 10) b11pp1.setText(botoestexto.get(10));
                else b11pp1.setText("");
                if (botoestexto.size() > 11) b12pp1.setText(botoestexto.get(11));
                else b12pp1.setText("");
            }else if(maxpaltela1==11){
                if (botoestexto.size() > 0) b1pp1.setText(botoestexto.get(0));
                else b1pp1.setText("");
                if (botoestexto.size() > 1) b2pp1.setText(botoestexto.get(1));
                else b2pp1.setText("");
                if (botoestexto.size() > 2) b3pp1.setText(botoestexto.get(2));
                else b3pp1.setText("");
                if (botoestexto.size() > 3) b4pp1.setText(botoestexto.get(3));
                else b4pp1.setText("");
                if (botoestexto.size() > 4) b5pp1.setText(botoestexto.get(4));
                else b5pp1.setText("");
                if (botoestexto.size() > 5) b6pp1.setText(botoestexto.get(5));
                else b6pp1.setText("");
                if (botoestexto.size() > 6) b7pp1.setText(botoestexto.get(6));
                else b7pp1.setText("");
                if (botoestexto.size() > 7) b8pp1.setText(botoestexto.get(7));
                else b8pp1.setText("");
                if (botoestexto.size() > 8) b9pp1.setText(botoestexto.get(8));
                else b9pp1.setText("");
                if (botoestexto.size() > 9) b10pp1.setText(botoestexto.get(9));
                else b10pp1.setText("");
                if (botoestexto.size() > 10) b11pp1.setText(botoestexto.get(10));
                else b11pp1.setText("");
            }else if(maxpaltela1==10){
                if (botoestexto.size() > 0) b1pp1.setText(botoestexto.get(0));
                else b1pp1.setText("");
                if (botoestexto.size() > 1) b2pp1.setText(botoestexto.get(1));
                else b2pp1.setText("");
                if (botoestexto.size() > 2) b3pp1.setText(botoestexto.get(2));
                else b3pp1.setText("");
                if (botoestexto.size() > 3) b4pp1.setText(botoestexto.get(3));
                else b4pp1.setText("");
                if (botoestexto.size() > 4) b5pp1.setText(botoestexto.get(4));
                else b5pp1.setText("");
                if (botoestexto.size() > 5) b6pp1.setText(botoestexto.get(5));
                else b6pp1.setText("");
                if (botoestexto.size() > 6) b7pp1.setText(botoestexto.get(6));
                else b7pp1.setText("");
                if (botoestexto.size() > 7) b8pp1.setText(botoestexto.get(7));
                else b8pp1.setText("");
                if (botoestexto.size() > 8) b9pp1.setText(botoestexto.get(8));
                else b9pp1.setText("");
                if (botoestexto.size() > 9) b10pp1.setText(botoestexto.get(9));
                else b10pp1.setText("");
            }else if(maxpaltela1==9){
                if (botoestexto.size() > 0) b1pp1.setText(botoestexto.get(0));
                else b1pp1.setText("");
                if (botoestexto.size() > 1) b2pp1.setText(botoestexto.get(1));
                else b2pp1.setText("");
                if (botoestexto.size() > 2) b3pp1.setText(botoestexto.get(2));
                else b3pp1.setText("");
                if (botoestexto.size() > 3) b4pp1.setText(botoestexto.get(3));
                else b4pp1.setText("");
                if (botoestexto.size() > 4) b5pp1.setText(botoestexto.get(4));
                else b5pp1.setText("");
                if (botoestexto.size() > 5) b6pp1.setText(botoestexto.get(5));
                else b6pp1.setText("");
                if (botoestexto.size() > 6) b7pp1.setText(botoestexto.get(6));
                else b7pp1.setText("");
                if (botoestexto.size() > 7) b8pp1.setText(botoestexto.get(7));
                else b8pp1.setText("");
                if (botoestexto.size() > 8) b9pp1.setText(botoestexto.get(8));
                else b9pp1.setText("");
            }else if(maxpaltela1==8){
                if (botoestexto.size() > 0) b1pp1.setText(botoestexto.get(0));
                else b1pp1.setText("");
                if (botoestexto.size() > 1) b2pp1.setText(botoestexto.get(1));
                else b2pp1.setText("");
                if (botoestexto.size() > 2) b3pp1.setText(botoestexto.get(2));
                else b3pp1.setText("");
                if (botoestexto.size() > 3) b4pp1.setText(botoestexto.get(3));
                else b4pp1.setText("");
                if (botoestexto.size() > 4) b5pp1.setText(botoestexto.get(4));
                else b5pp1.setText("");
                if (botoestexto.size() > 5) b6pp1.setText(botoestexto.get(5));
                else b6pp1.setText("");
                if (botoestexto.size() > 6) b7pp1.setText(botoestexto.get(6));
                else b7pp1.setText("");
                if (botoestexto.size() > 7) b8pp1.setText(botoestexto.get(7));
                else b8pp1.setText("");
            }else if(maxpaltela1==7){
                if (botoestexto.size() > 0) b1pp1.setText(botoestexto.get(0));
                else b1pp1.setText("");
                if (botoestexto.size() > 1) b2pp1.setText(botoestexto.get(1));
                else b2pp1.setText("");
                if (botoestexto.size() > 2) b3pp1.setText(botoestexto.get(2));
                else b3pp1.setText("");
                if (botoestexto.size() > 3) b4pp1.setText(botoestexto.get(3));
                else b4pp1.setText("");
                if (botoestexto.size() > 4) b5pp1.setText(botoestexto.get(4));
                else b5pp1.setText("");
                if (botoestexto.size() > 5) b6pp1.setText(botoestexto.get(5));
                else b6pp1.setText("");
                if (botoestexto.size() > 6) b7pp1.setText(botoestexto.get(6));
                else b7pp1.setText("");
            }else if(maxpaltela1==6){
                if (botoestexto.size() > 0) b1pp1.setText(botoestexto.get(0));
                else b1pp1.setText("");
                if (botoestexto.size() > 1) b2pp1.setText(botoestexto.get(1));
                else b2pp1.setText("");
                if (botoestexto.size() > 2) b3pp1.setText(botoestexto.get(2));
                else b3pp1.setText("");
                if (botoestexto.size() > 3) b4pp1.setText(botoestexto.get(3));
                else b4pp1.setText("");
                if (botoestexto.size() > 4) b5pp1.setText(botoestexto.get(4));
                else b5pp1.setText("");
                if (botoestexto.size() > 5) b6pp1.setText(botoestexto.get(5));
                else b6pp1.setText("");
            }else if(maxpaltela1==5){
                if (botoestexto.size() > 0) b1pp1.setText(botoestexto.get(0));
                else b1pp1.setText("");
                if (botoestexto.size() > 1) b2pp1.setText(botoestexto.get(1));
                else b2pp1.setText("");
                if (botoestexto.size() > 2) b3pp1.setText(botoestexto.get(2));
                else b3pp1.setText("");
                if (botoestexto.size() > 3) b4pp1.setText(botoestexto.get(3));
                else b4pp1.setText("");
                if (botoestexto.size() > 4) b5pp1.setText(botoestexto.get(4));
                else b5pp1.setText("");
                //entrada.setText(botoestexto.get(1)+"\n"+botoestexto.get(2)+"\n"+botoestexto.get(3)+"\n"+botoestexto.get(4));
            }else if(maxpaltela1==4){
                if (botoestexto.size() > 0) b1pp1.setText(botoestexto.get(0));
                else b1pp1.setText("");
                if (botoestexto.size() > 1) b2pp1.setText(botoestexto.get(1));
                else b2pp1.setText("");
                if (botoestexto.size() > 2) b3pp1.setText(botoestexto.get(2));
                else b3pp1.setText("");
                if (botoestexto.size() > 3) b4pp1.setText(botoestexto.get(3));
                else b4pp1.setText("");
            }else if(maxpaltela1==3){
                if (botoestexto.size() > 0) b1pp1.setText(botoestexto.get(0));
                else b1pp1.setText("");
                if (botoestexto.size() > 1) b2pp1.setText(botoestexto.get(1));
                else b2pp1.setText("");
                if (botoestexto.size() > 2) b3pp1.setText(botoestexto.get(2));
                else b3pp1.setText("");
            }else if(maxpaltela1==2){
                if (botoestexto.size() > 0) b1pp1.setText(botoestexto.get(0));
                else b1pp1.setText("");
                if (botoestexto.size() > 1) b2pp1.setText(botoestexto.get(1));
                else b2pp1.setText("");
            }else if(maxpaltela1==1){
                if (botoestexto.size() > 0) b1pp1.setText(botoestexto.get(0));
                else b1pp1.setText("");
            }

            //b1pp1.setText("MaiúÚSculho");
        }
        catch (Exception e){
            entrada.setText("erronoupdatepagef1"+e.toString());
        }
    }
    public void updatepagef2(int numerodalista/*Pra fazer listas de matérias diferentes por exemplo*/,int paginadepalavras){//Atualiza as palavras da página na tela 2
        //pag2.setText("OILA");
        ArrayList<String> botoestexto=getpage(getsavedwords(numerodalista,true),maxpaltela2,paginadepalavras);
        if (maxpaltela2==15){
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
            else b15pp2.setText("");}
        else if(maxpaltela2==14){
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
        }else if(maxpaltela2==13){
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
        }else if(maxpaltela2==12){
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
        }else if(maxpaltela2==11){
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
        }else if(maxpaltela2==10){
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
        }else if(maxpaltela2==9){
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
        }else if(maxpaltela2==8){
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
        }else if(maxpaltela2==7){
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
        }else if(maxpaltela2==6){
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
        }else if(maxpaltela2==5){
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
        }else if(maxpaltela2==4){
            if (botoestexto.size()>0)b1pp2.setText(botoestexto.get(0));
            else b1pp2.setText("");
            if (botoestexto.size()>1)b2pp2.setText(botoestexto.get(1));
            else b2pp2.setText("");
            if (botoestexto.size()>2)b3pp2.setText(botoestexto.get(2));
            else b3pp2.setText("");
            if (botoestexto.size()>3)b4pp2.setText(botoestexto.get(3));
            else b4pp2.setText("");
        }else if(maxpaltela2==3){
            if (botoestexto.size()>0)b1pp2.setText(botoestexto.get(0));
            else b1pp2.setText("");
            if (botoestexto.size()>1)b2pp2.setText(botoestexto.get(1));
            else b2pp2.setText("");
            if (botoestexto.size()>2)b3pp2.setText(botoestexto.get(2));
            else b3pp2.setText("");
        }else if(maxpaltela2==2){
            if (botoestexto.size()>0)b1pp2.setText(botoestexto.get(0));
            else b1pp2.setText("");
            if (botoestexto.size()>1)b2pp2.setText(botoestexto.get(1));
            else b2pp2.setText("");
        }else if(maxpaltela2==1){
            if (botoestexto.size()>0)b1pp2.setText(botoestexto.get(0));
            else b1pp2.setText("");
        }
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
                    while (true){
                        if (input.getText().toString().charAt(0)==" ".charAt(0)){
                            input.setText(input.getText().toString().substring(1));
                        } else break;
                    }

                }
                if (hasespaco(input.getText().toString())){
                    while (true){
                        if(input.getText().toString().charAt(input.getText().length()-1)==" ".charAt(0)){
                            input.setText(input.getText().toString().substring(0,input.getText().length()-1));
                        } else break;
                    }
                }
                if (hasespaco(input.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Frases não são salvas por aqui!", Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                }
                else if (input.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Digite algo para salvar!!", Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                } else {
                    if (getsavedstring(true)!=null&&!(getsavedstring(true).equals(""))) savestring(getsavedstring(true) + "\n" + input.getText().toString(), true);//ISSO AQUI TA ERRaDO!!!
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
    public void tirapalavraesalva(boolean palavrasfrases,int aremover){
        ArrayList<String> palavras= getsavedwords(1,palavrasfrases);
        if(palavras.size()>=1&&palavras.size()>aremover){
            palavras.remove(aremover);
            String saida="";
            if(0<palavras.size()-1)
            for (int i=0;i<palavras.size()-1;i++){
                saida=saida+palavras.get(i)+"\n";
            }
            if(palavras.size()!=0)saida=saida+palavras.get(palavras.size()-1);

            savestring(saida,palavrasfrases);
            //pag2.setText("a"+saida+"b");
        }//else savestring("",palavrasfrases);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            maxpaltela1=4;
            maxpaltela2=15;
            super.onCreate(savedInstanceState);
            this.contexto = getApplicationContext();
            /*teste=new TextToSpeech(MainActivity.this, new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    teste.setSpeechRate(0);
                }
            });
            MediaPlayer vazio = MediaPlayer.create(this,R.raw.notification);
            vazio.setVolume(0,0);*/
            //MediaButtonReceiver();
            //android.intent.action.MEDIA_BUTTON;



        try{FileInputStream ler = openFileInput("palavras.txt");}
        catch (FileNotFoundException ex){
            File file= new File(this.getFilesDir(),"palavras.txt");
        }
        catch (Exception e){
            e.printStackTrace();
        }


        final MediaPlayer somPerg = MediaPlayer.create(this, R.raw.notification);
        //super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        totela1(somPerg);

        mPrefs = getSharedPreferences("labela", 0);
        mEditor = mPrefs.edit();


        String csvList = mPrefs.getString("perguntitas", "");
        String[] items = csvList.split("¨");
        if(csvList.length() > 0){
            for (int i = 0; i < items.length; i++) {
                perguntas.add(items[i]);
            }
        }




        }catch(Exception e) {
            entrada.setText("Erro no onCreate"+e.toString());
            Toast.makeText(getApplicationContext(),e.toString(), Toast.LENGTH_LONG).show();
            //while(true){
            //Toast.makeText(getApplicationContext(),e.toString(), Toast.LENGTH_LONG).show();
            //try{sleep(1000);}catch (Exception err){err.printStackTrace();}
            //}
        }
        //entrada.setText("X= "+getlargura()+" Y="+getaltura());






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
                try{
                if (i != TextToSpeech.ERROR) {
                    Locale portugues = new Locale("PT", "BR");
                    Bundle extras = getIntent().getExtras();

                    SharedPreferences.Editor mEditor = mPrefs.edit();
                    Boolean perg = extras.getBoolean("perg",mPrefs.getBoolean("pergt",true));
                    Boolean fone = extras.getBoolean("fone",mPrefs.getBoolean("foni",true));
                    String tom = extras.getString("tom",mPrefs.getString("tag", "1"));
                    if (Float.parseFloat(tom) < 0.1) tom = "0.1f";
                    String vel = extras.getString("vel",mPrefs.getString("tag2", "1"));
                    if (Float.parseFloat(vel) < 0.1) vel = "0.1f";
                    mEditor.putBoolean("pergt", perg).commit();
                    mEditor.putBoolean("foni", fone).commit();
                    mEditor.putString("tag2", vel).commit();
                    mEditor.putString("tag", tom).commit();

                    String stringTom = mPrefs.getString("tag", "1");
                    String stringVel = mPrefs.getString("tag2", "1");
                    falador.setLanguage(portugues);
                    falador.setSpeechRate(Float.parseFloat(stringVel));
                    falador.setPitch(Float.parseFloat(stringTom));
                }
            }catch(Exception e){//Ignorando o erro abertamente aqui...
                //entrada.setText("Erro no OnInit"+e.toString());
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
                if(mPrefs.getBoolean("pergt", true)) {
                    if (perguntas.size() > 0) {
                        try {
                            sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        String strnPerg = new Integer(perguntas.size()).toString();
                        String pRest = strnPerg + "perguntas";
                        falador.speak(pRest, TextToSpeech.QUEUE_FLUSH, null);
                    }
                }
                }

            @Override
            public void onError(String utteranceId) {
                // There was an error.

            }

        });







        BroadcastReceiver teste = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Toast.makeText(getApplicationContext(),"WOWOWOOWOWOWOWOOWOW", Toast.LENGTH_LONG);
                entrada.setText("HHMMMM");
            }
        };
        getApplicationContext().registerReceiver(teste,new IntentFilter("android.intent.action.MEDIA_BUTTON" ));
        //Intent mediaButtonIntent = new Intent(Intent.ACTION_MEDIA_BUTTON, null, getApplicationContext(), MediaButtonReceiver.class);


    }
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.cfg:
                trocapagina();
                Intent intent = new Intent(this, config.class);
                startActivity(intent);
                return true;
            case R.id.help:
                trocapagina();
                Intent intent2 = new Intent(this, AjudaActivity.class);
                startActivity(intent2);
                return true;
            case R.id.sobre:
                trocapagina();
                Intent intent3 = new Intent(this, SobreActivity.class);
                startActivity(intent3);
                return true;
            default:
                return false;
        }
    }
    public void trocapagina(){
        saiu = false;
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString("escrito",entrada.getText().toString()).commit();

        StringBuilder csvList = new StringBuilder();
        for(String s : perguntas){
            csvList.append(s);
            csvList.append("¨");
        }

        mEditor.putString("perguntitas", csvList.toString()).commit();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {//sucodeuvaa
        if(keyCode == KeyEvent.KEYCODE_HEADSETHOOK||keyCode == 126||keyCode == 127){
            falar();//ONSTOP AQUI TAMBÉM!!!!!
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
        //Linearlayouts da tela 2:
        l1pp2=findViewById(R.id.lay1);
        l2pp2=findViewById(R.id.lay2);
        l3pp2=findViewById(R.id.lay3);
        l4pp2=findViewById(R.id.lay4);
        l5pp2=findViewById(R.id.lay5);
        l6pp2=findViewById(R.id.lay6);
        l7pp2=findViewById(R.id.lay7);
        l8pp2=findViewById(R.id.lay8);
        l9pp2=findViewById(R.id.lay9);
        l10pp2=findViewById(R.id.lay10);
        l11pp2=findViewById(R.id.lay11);
        l12pp2=findViewById(R.id.lay12);
        l13pp2=findViewById(R.id.lay13);
        l14pp2=findViewById(R.id.lay14);
        l15pp2=findViewById(R.id.lay15);
        //Botões de apagar da tela 2:
        p1pp2=findViewById(R.id.button22);
        p2pp2=findViewById(R.id.button25);
        p3pp2=findViewById(R.id.button19);
        p4pp2=findViewById(R.id.button15);
        p5pp2=findViewById(R.id.button28);
        p6pp2=findViewById(R.id.button31);
        p7pp2=findViewById(R.id.button34);
        p8pp2=findViewById(R.id.button37);
        p9pp2=findViewById(R.id.button61);
        p10pp2=findViewById(R.id.button58);
        p11pp2=findViewById(R.id.button55);
        p12pp2=findViewById(R.id.button52);
        p13pp2=findViewById(R.id.button49);
        p14pp2=findViewById(R.id.button46);
        p15pp2=findViewById(R.id.button43);


        //Botões de ir e voltar página 2:
        vaipag2=findViewById(R.id.button11);
        vaipag2.setRotation(180);
        voltapag2=findViewById(R.id.button12);





        somapag2=findViewById(R.id.button39);

        //Sem Solução:
        /*
        findViewById(R.id.constai).post(new Runnable() {
            @Override
            public void run() {
                pag2.setText(Float.toString(findViewById(R.id.constai).getMeasuredHeight()));
            }
        });
        try{
        float altconstr=new Float(pag2.getText().toString());
        pag2.setText("Editar palavras"+Float.toString(altconstr));
        }catch(Exception erroqorqkw){
            pag2.setText("Q"+erroqorqkw.toString());
        }*/
        float alturapadraotot=90;//50 da constraint e 40 do botão back
        maxpaltela2=(int)((getaltura()-130)/40)+1;//-1 do botão + pra dar exato se quiser...

        //Tirando botões excessivos
        if (maxpaltela2<1)l1pp2.setVisibility(View.GONE);
        if (maxpaltela2<2)l2pp2.setVisibility(View.GONE);
        if (maxpaltela2<3)l3pp2.setVisibility(View.GONE);
        if (maxpaltela2<4)l4pp2.setVisibility(View.GONE);
        if (maxpaltela2<5)l5pp2.setVisibility(View.GONE);
        if (maxpaltela2<6)l6pp2.setVisibility(View.GONE);
        if (maxpaltela2<7)l7pp2.setVisibility(View.GONE);
        if (maxpaltela2<8)l8pp2.setVisibility(View.GONE);
        if (maxpaltela2<9)l9pp2.setVisibility(View.GONE);
        if (maxpaltela2<10)l10pp2.setVisibility(View.GONE);
        if (maxpaltela2<11)l11pp2.setVisibility(View.GONE);
        if (maxpaltela2<12)l12pp2.setVisibility(View.GONE);
        if (maxpaltela2<13)l13pp2.setVisibility(View.GONE);
        if (maxpaltela2<14)l14pp2.setVisibility(View.GONE);
        if (maxpaltela2<15)l15pp2.setVisibility(View.GONE);


        //Setando Botões:
        p1pp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tirapalavraesalva(palavrasoufrases,0+(pagpaltel2-1)*maxpaltela2);
                if(getsavedwords(1,palavrasoufrases).size()!=0&&getsavedwords(1,palavrasoufrases).size()==(pagpaltel2-1)*maxpaltela2)pagpaltel2-=1;
                updatepagef2(1,pagpaltel2);
            }
        });
        p2pp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tirapalavraesalva(palavrasoufrases,1+(pagpaltel2-1)*maxpaltela2);
                if(getsavedwords(1,palavrasoufrases).size()!=0&&getsavedwords(1,palavrasoufrases).size()==(pagpaltel2-1)*maxpaltela2)pagpaltel2-=1;
                updatepagef2(1,pagpaltel2);
            }
        });
        p3pp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tirapalavraesalva(palavrasoufrases,2+(pagpaltel2-1)*maxpaltela2);
                if(getsavedwords(1,palavrasoufrases).size()!=0&&getsavedwords(1,palavrasoufrases).size()==pagpaltel2*maxpaltela2)pagpaltel2-=1;
                updatepagef2(1,pagpaltel2);
            }
        });
        p4pp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tirapalavraesalva(palavrasoufrases,3+(pagpaltel2-1)*maxpaltela2);
                if(getsavedwords(1,palavrasoufrases).size()==pagpaltel2*maxpaltela2)pagpaltel2-=1;
                updatepagef2(1,pagpaltel2);
            }
        });
        p5pp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tirapalavraesalva(palavrasoufrases,4+(pagpaltel2-1)*maxpaltela2);
                if(getsavedwords(1,palavrasoufrases).size()==pagpaltel2*maxpaltela2)pagpaltel2-=1;
                updatepagef2(1,pagpaltel2);
            }
        });
        p6pp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tirapalavraesalva(palavrasoufrases,5+(pagpaltel2-1)*maxpaltela2);
                if(getsavedwords(1,palavrasoufrases).size()==pagpaltel2*maxpaltela2)pagpaltel2-=1;
                updatepagef2(1,pagpaltel2);
            }
        });
        p7pp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tirapalavraesalva(palavrasoufrases,6+(pagpaltel2-1)*maxpaltela2);
                if(getsavedwords(1,palavrasoufrases).size()==pagpaltel2*maxpaltela2)pagpaltel2-=1;
                updatepagef2(1,pagpaltel2);
            }
        });
        p8pp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tirapalavraesalva(palavrasoufrases,7+(pagpaltel2-1)*maxpaltela2);
                if(getsavedwords(1,palavrasoufrases).size()==pagpaltel2*maxpaltela2)pagpaltel2-=1;
                updatepagef2(1,pagpaltel2);
            }
        });
        p9pp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tirapalavraesalva(palavrasoufrases,8+(pagpaltel2-1)*maxpaltela2);
                if(getsavedwords(1,palavrasoufrases).size()==pagpaltel2*maxpaltela2)pagpaltel2-=1;
                updatepagef2(1,pagpaltel2);
            }
        });
        p10pp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tirapalavraesalva(palavrasoufrases,9+(pagpaltel2-1)*maxpaltela2);
                if(getsavedwords(1,palavrasoufrases).size()==pagpaltel2*maxpaltela2)pagpaltel2-=1;
                updatepagef2(1,pagpaltel2);
            }
        });
        p11pp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tirapalavraesalva(palavrasoufrases,10+(pagpaltel2-1)*maxpaltela2);
                if(getsavedwords(1,palavrasoufrases).size()==pagpaltel2*maxpaltela2)pagpaltel2-=1;
                updatepagef2(1,pagpaltel2);
            }
        });
        p12pp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tirapalavraesalva(palavrasoufrases,11+(pagpaltel2-1)*maxpaltela2);
                if(getsavedwords(1,palavrasoufrases).size()==pagpaltel2*maxpaltela2)pagpaltel2-=1;
                updatepagef2(1,pagpaltel2);
            }
        });
        p13pp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tirapalavraesalva(palavrasoufrases,12+(pagpaltel2-1)*maxpaltela2);
                if(getsavedwords(1,palavrasoufrases).size()==pagpaltel2*maxpaltela2)pagpaltel2-=1;
                updatepagef2(1,pagpaltel2);
            }
        });
        p14pp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tirapalavraesalva(palavrasoufrases,13+(pagpaltel2-1)*maxpaltela2);
                if(getsavedwords(1,palavrasoufrases).size()==pagpaltel2*maxpaltela2)pagpaltel2-=1;
                updatepagef2(1,pagpaltel2);
            }
        });
        p15pp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tirapalavraesalva(palavrasoufrases,14+(pagpaltel2-1)*maxpaltela2);
                if(getsavedwords(1,palavrasoufrases).size()==pagpaltel2*maxpaltela2)pagpaltel2-=1;
                updatepagef2(1,pagpaltel2);
            }
        });
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
                //pag2.setText(Integer.toString(getnumpag(maxpaltela2,getsavedwords(1,true).size())));
                //pag2.setText(Integer.toString(pagpaltel2));
                if(getnumpag(maxpaltela2,getsavedwords(1,true).size())>pagpaltel2) {
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
        try{
        setContentView(R.layout.activity_main);
        falabotao = findViewById(R.id.Fala);
        likebotao = findViewById(R.id.like);
        deslikebotao = findViewById(R.id.deslike);
        editar = findViewById(R.id.button10);
        entrada = findViewById(R.id.Entrada);

        mPrefs=getSharedPreferences("labela", 0);

        entrada.setText(mPrefs.getString("escrito",""));


        //Botões das palavras tela 1:
        trespontos =findViewById(R.id.opcoes);
        b1pp1=findViewById(R.id.button);
        b2pp1=findViewById(R.id.button2);
        b3pp1=findViewById(R.id.button3);
        b4pp1=findViewById(R.id.button4);
        b5pp1=findViewById(R.id.button5);
        b6pp1=findViewById(R.id.button6);
        b7pp1=findViewById(R.id.button7);
        b8pp1=findViewById(R.id.button14);
        b9pp1=findViewById(R.id.button17);
        b10pp1=findViewById(R.id.button20);
        b11pp1=findViewById(R.id.button23);
        b12pp1=findViewById(R.id.button26);
        b13pp1=findViewById(R.id.button29);
        b14pp1=findViewById(R.id.button32);
        b15pp1=findViewById(R.id.button35);
        //Botões de ir e voltar tela 1:
        vaipag1=findViewById(R.id.button8);
        vaipag1.setRotation(180);
        voltapag1=findViewById(R.id.button9);




        maxpaltela1=(int)(getaltura()-170)/42-1;//40 de cada um dos 3 botões e 50 da toolbar
            //entrada.setText(Integer.toString(maxpaltela1));
            if (maxpaltela1<1)b1pp1.setVisibility(View.GONE);
            if (maxpaltela1<2)b2pp1.setVisibility(View.GONE);
            if (maxpaltela1<3)b3pp1.setVisibility(View.GONE);
            if (maxpaltela1<4)b4pp1.setVisibility(View.GONE);
            if (maxpaltela1<5)b5pp1.setVisibility(View.GONE);
            if (maxpaltela1<6)b6pp1.setVisibility(View.GONE);
            if (maxpaltela1<7)b7pp1.setVisibility(View.GONE);
            if (maxpaltela1<8)b8pp1.setVisibility(View.GONE);
            if (maxpaltela1<9)b9pp1.setVisibility(View.GONE);
            if (maxpaltela1<10)b10pp1.setVisibility(View.GONE);
            if (maxpaltela1<11)b11pp1.setVisibility(View.GONE);
            if (maxpaltela1<12)b12pp1.setVisibility(View.GONE);
            if (maxpaltela1<13)b13pp1.setVisibility(View.GONE);
            if (maxpaltela1<14)b14pp1.setVisibility(View.GONE);
            if (maxpaltela1<15)b15pp1.setVisibility(View.GONE);
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
            //Botões de pergunta
            perg1=findViewById(R.id.p1);
            perg2=findViewById(R.id.p2);
            perg3=findViewById(R.id.p3);
            perg4=findViewById(R.id.p4);

            perg1.setOnLongClickListener(new View.OnLongClickListener(){
                @Override
                public boolean onLongClick(View view){
                    SalvaPerguntas(1);
                    return true;
                }
            });
            perg1.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    entrada.setText(mPrefs.getString("ps1",""));
                }
            });

            perg2.setOnLongClickListener(new View.OnLongClickListener(){
                @Override
                public boolean onLongClick(View view){
                    SalvaPerguntas(2);
                    return true;
                }
            });
            perg2.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    entrada.setText(mPrefs.getString("ps2",""));
                }
            });

            perg3.setOnLongClickListener(new View.OnLongClickListener(){
                @Override
                public boolean onLongClick(View view){
                    SalvaPerguntas(3);
                    return true;
                }
            });
            perg3.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    entrada.setText(mPrefs.getString("ps3",""));
                }
            });

            perg4.setOnLongClickListener(new View.OnLongClickListener(){
                @Override
                public boolean onLongClick(View view){
                    SalvaPerguntas(4);
                    return true;
                }
            });
            perg4.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    entrada.setText(mPrefs.getString("ps4",""));
                }
            });



        //BOTOES DAS PALAVRAS TELA 1:
        b1pp1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(!b1pp1.getText().toString().equals("")){
                    int onde = entrada.getSelectionStart();
                    String sub1 = entrada.getText().toString().substring(0,onde);
                    String sub2 = entrada.getText().toString().substring(onde,entrada.getText().toString().length());
                    String palavrita = b1pp1.getText().toString();
                    if(sub1.length()>0) if(sub1.charAt(sub1.length()-1)!=' ') palavrita = ' ' + palavrita;
                    if(sub2.length()>0) if(sub2.charAt(0)!=' ') palavrita = palavrita + ' ';
                    entrada.setText(sub1+palavrita+sub2);
                    entrada.setSelection(onde + palavrita.length());
                }
            }
        });
        b2pp1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(!b2pp1.getText().toString().equals("")){
                    int onde = entrada.getSelectionStart();
                    String sub1 = entrada.getText().toString().substring(0,onde);
                    String sub2 = entrada.getText().toString().substring(onde,entrada.getText().toString().length());
                    String palavrita = b2pp1.getText().toString();
                    if(sub1.length()>0) if(sub1.charAt(sub1.length()-1)!=' ') palavrita = ' ' + palavrita;
                    if(sub2.length()>0) if(sub2.charAt(0)!=' ') palavrita = palavrita + ' ';
                    entrada.setText(sub1+palavrita+sub2);
                    entrada.setSelection(onde + palavrita.length());
                }
            }
        });
        b3pp1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(!b3pp1.getText().toString().equals("")){
                    int onde = entrada.getSelectionStart();
                    String sub1 = entrada.getText().toString().substring(0,onde);
                    String sub2 = entrada.getText().toString().substring(onde,entrada.getText().toString().length());
                    String palavrita = b3pp1.getText().toString();
                    if(sub1.length()>0) if(sub1.charAt(sub1.length()-1)!=' ') palavrita = ' ' + palavrita;
                    if(sub2.length()>0) if(sub2.charAt(0)!=' ') palavrita = palavrita + ' ';
                    entrada.setText(sub1+palavrita+sub2);
                    entrada.setSelection(onde + palavrita.length());
                }
            }
        });
        b4pp1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(!b4pp1.getText().toString().equals("")){
                    int onde = entrada.getSelectionStart();
                    String sub1 = entrada.getText().toString().substring(0,onde);
                    String sub2 = entrada.getText().toString().substring(onde,entrada.getText().toString().length());
                    String palavrita = b4pp1.getText().toString();
                    if(sub1.length()>0) if(sub1.charAt(sub1.length()-1)!=' ') palavrita = ' ' + palavrita;
                    if(sub2.length()>0) if(sub2.charAt(0)!=' ') palavrita = palavrita + ' ';
                    entrada.setText(sub1+palavrita+sub2);
                    entrada.setSelection(onde + palavrita.length());
                }
            }
        });

        b5pp1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(!b5pp1.getText().toString().equals("")){
                    int onde = entrada.getSelectionStart();
                    String sub1 = entrada.getText().toString().substring(0,onde);
                    String sub2 = entrada.getText().toString().substring(onde,entrada.getText().toString().length());
                    String palavrita = b5pp1.getText().toString();
                    if(sub1.length()>0) if(sub1.charAt(sub1.length()-1)!=' ') palavrita = ' ' + palavrita;
                    if(sub2.length()>0) if(sub2.charAt(0)!=' ') palavrita = palavrita + ' ';
                    entrada.setText(sub1+palavrita+sub2);
                    entrada.setSelection(onde + palavrita.length());
                }
            }
        });
        b6pp1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(!b6pp1.getText().toString().equals("")){
                    int onde = entrada.getSelectionStart();
                    String sub1 = entrada.getText().toString().substring(0,onde);
                    String sub2 = entrada.getText().toString().substring(onde,entrada.getText().toString().length());
                    String palavrita = b6pp1.getText().toString();
                    if(sub1.length()>0) if(sub1.charAt(sub1.length()-1)!=' ') palavrita = ' ' + palavrita;
                    if(sub2.length()>0) if(sub2.charAt(0)!=' ') palavrita = palavrita + ' ';
                    entrada.setText(sub1+palavrita+sub2);
                    entrada.setSelection(onde + palavrita.length());
                }
            }
        });
        b7pp1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(!b7pp1.getText().toString().equals("")){
                    int onde = entrada.getSelectionStart();
                    String sub1 = entrada.getText().toString().substring(0,onde);
                    String sub2 = entrada.getText().toString().substring(onde,entrada.getText().toString().length());
                    String palavrita = b7pp1.getText().toString();
                    if(sub1.length()>0) if(sub1.charAt(sub1.length()-1)!=' ') palavrita = ' ' + palavrita;
                    if(sub2.length()>0) if(sub2.charAt(0)!=' ') palavrita = palavrita + ' ';
                    entrada.setText(sub1+palavrita+sub2);
                    entrada.setSelection(onde + palavrita.length());
                }
            }
        });
            b8pp1.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    if(!b8pp1.getText().toString().equals("")){
                        int onde = entrada.getSelectionStart();
                        String sub1 = entrada.getText().toString().substring(0,onde);
                        String sub2 = entrada.getText().toString().substring(onde,entrada.getText().toString().length());
                        String palavrita = b8pp1.getText().toString();
                        if(sub1.length()>0) if(sub1.charAt(sub1.length()-1)!=' ') palavrita = ' ' + palavrita;
                        if(sub2.length()>0) if(sub2.charAt(0)!=' ') palavrita = palavrita + ' ';
                        entrada.setText(sub1+palavrita+sub2);
                        entrada.setSelection(onde + palavrita.length());
                    }
                }
            });
            b9pp1.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    if(!b9pp1.getText().toString().equals("")){
                        int onde = entrada.getSelectionStart();
                        String sub1 = entrada.getText().toString().substring(0,onde);
                        String sub2 = entrada.getText().toString().substring(onde,entrada.getText().toString().length());
                        String palavrita = b9pp1.getText().toString();
                        if(sub1.length()>0) if(sub1.charAt(sub1.length()-1)!=' ') palavrita = ' ' + palavrita;
                        if(sub2.length()>0) if(sub2.charAt(0)!=' ') palavrita = palavrita + ' ';
                        entrada.setText(sub1+palavrita+sub2);
                        entrada.setSelection(onde + palavrita.length());
                    }
                }
            });
            b10pp1.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    if(!b10pp1.getText().toString().equals("")){
                        int onde = entrada.getSelectionStart();
                        String sub1 = entrada.getText().toString().substring(0,onde);
                        String sub2 = entrada.getText().toString().substring(onde,entrada.getText().toString().length());
                        String palavrita = b10pp1.getText().toString();
                        if(sub1.length()>0) if(sub1.charAt(sub1.length()-1)!=' ') palavrita = ' ' + palavrita;
                        if(sub2.length()>0) if(sub2.charAt(0)!=' ') palavrita = palavrita + ' ';
                        entrada.setText(sub1+palavrita+sub2);
                        entrada.setSelection(onde + palavrita.length());
                    }
                }
            });
            b11pp1.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    if(!b11pp1.getText().toString().equals("")){
                        int onde = entrada.getSelectionStart();
                        String sub1 = entrada.getText().toString().substring(0,onde);
                        String sub2 = entrada.getText().toString().substring(onde,entrada.getText().toString().length());
                        String palavrita = b11pp1.getText().toString();
                        if(sub1.length()>0) if(sub1.charAt(sub1.length()-1)!=' ') palavrita = ' ' + palavrita;
                        if(sub2.length()>0) if(sub2.charAt(0)!=' ') palavrita = palavrita + ' ';
                        entrada.setText(sub1+palavrita+sub2);
                        entrada.setSelection(onde + palavrita.length());
                    }
                }
            });
            b12pp1.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    if(!b12pp1.getText().toString().equals("")){
                        int onde = entrada.getSelectionStart();
                        String sub1 = entrada.getText().toString().substring(0,onde);
                        String sub2 = entrada.getText().toString().substring(onde,entrada.getText().toString().length());
                        String palavrita = b12pp1.getText().toString();
                        if(sub1.length()>0) if(sub1.charAt(sub1.length()-1)!=' ') palavrita = ' ' + palavrita;
                        if(sub2.length()>0) if(sub2.charAt(0)!=' ') palavrita = palavrita + ' ';
                        entrada.setText(sub1+palavrita+sub2);
                        entrada.setSelection(onde + palavrita.length());
                    }
                }
            });
            b13pp1.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    if(!b13pp1.getText().toString().equals("")){
                        int onde = entrada.getSelectionStart();
                        String sub1 = entrada.getText().toString().substring(0,onde);
                        String sub2 = entrada.getText().toString().substring(onde,entrada.getText().toString().length());
                        String palavrita = b13pp1.getText().toString();
                        if(sub1.length()>0) if(sub1.charAt(sub1.length()-1)!=' ') palavrita = ' ' + palavrita;
                        if(sub2.length()>0) if(sub2.charAt(0)!=' ') palavrita = palavrita + ' ';
                        entrada.setText(sub1+palavrita+sub2);
                        entrada.setSelection(onde + palavrita.length());
                    }
                }
            });
            b14pp1.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    if(!b14pp1.getText().toString().equals("")){
                        int onde = entrada.getSelectionStart();
                        String sub1 = entrada.getText().toString().substring(0,onde);
                        String sub2 = entrada.getText().toString().substring(onde,entrada.getText().toString().length());
                        String palavrita = b14pp1.getText().toString();
                        if(sub1.length()>0) if(sub1.charAt(sub1.length()-1)!=' ') palavrita = ' ' + palavrita;
                        if(sub2.length()>0) if(sub2.charAt(0)!=' ') palavrita = palavrita + ' ';
                        entrada.setText(sub1+palavrita+sub2);
                        entrada.setSelection(onde + palavrita.length());
                    }
                }
            });
            b15pp1.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    if(!b15pp1.getText().toString().equals("")){
                        int onde = entrada.getSelectionStart();
                        String sub1 = entrada.getText().toString().substring(0,onde);
                        String sub2 = entrada.getText().toString().substring(onde,entrada.getText().toString().length());
                        String palavrita = b15pp1.getText().toString();
                        if(sub1.length()>0) if(sub1.charAt(sub1.length()-1)!=' ') palavrita = ' ' + palavrita;
                        if(sub2.length()>0) if(sub2.charAt(0)!=' ') palavrita = palavrita + ' ';
                        entrada.setText(sub1+palavrita+sub2);
                        entrada.setSelection(onde + palavrita.length());
                    }
                }
            });
        //setando botões
        falabotao.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String oque = entrada.getText().toString();
                if(oque.equals("")){
                    Toast.makeText(getApplicationContext(),"Digite algo para enviar uma mensagem!", Toast.LENGTH_SHORT).show();
                }
                else if(!mPrefs.getBoolean("foni",true)){
                    falador.speak(oque,TextToSpeech.QUEUE_FLUSH,null);
                    entrada.setText("");
                }
                else if(!perguntas.contains(oque)){
                    sperg.start();
                    perguntas.add(oque);
                    entrada.setText("");
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
                //pag2.setText(Integer.toString(getnumpag(maxpaltela2,getsavedwords(1,true).size())));
                //pag2.setText(Integer.toString(pagpaltel2));
                if(getnumpag(maxpaltela1,getsavedwords(1,true).size())>pagpaltel1) {
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
        }catch (Exception e){
            entrada.setText("erro no totela1"+e.toString());
        }
    }


    @Override
    protected void onPause() {
        if(falador!=null){
            tempo=getempo();
            if(saiu){
                somSaiu=MediaPlayer.create(this,R.raw.saiu_app);
                somSaiu.start();
                mEditor.putString("escrito","").commit();
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

    @Override
    protected void onDestroy(){
        super.onDestroy();
        SharedPreferences.Editor excluidor = getSharedPreferences("labela", 0).edit();
        excluidor.putString("escrito","").commit();
        excluidor.putString("perguntitas","").commit();
        perguntas.clear();
    }

}
