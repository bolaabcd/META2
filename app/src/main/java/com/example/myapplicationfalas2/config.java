package com.example.myapplicationfalas2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Switch;

public class config extends AppCompatActivity {
    private SeekBar barrita1;
    private SeekBar barrita2;
    private SwitchCompat fone;
    private SwitchCompat perg;
    Boolean saiu = true;
    private SharedPreferences preferencia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        barrita1 = (SeekBar) findViewById(R.id.barraTom);
        fone = (SwitchCompat) findViewById(R.id.fone);
        perg = (SwitchCompat) findViewById(R.id.perguntas);
        barrita2 = (SeekBar) findViewById(R.id.barraVel);
        preferencia = getSharedPreferences("label", 0);
        fone.setChecked(preferencia.getBoolean("on_off_fone",true));
        perg.setChecked(preferencia.getBoolean("on_off_perg",true));
        barrita1.setProgress(preferencia.getInt("Progresso_tom", 50));
        barrita2.setProgress(preferencia.getInt("Progresso_Vel",50));
        ImageButton botaovolta = findViewById(R.id.setinha);
        botaovolta.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                volta();
                int progresso1 = barrita1.getProgress();
                SharedPreferences.Editor editar = preferencia.edit();
                editar.putInt("Progresso_tom",progresso1).commit();
                int progresso2 = barrita2.getProgress();
                editar.putBoolean("on_off_perg",perg.isChecked()).commit();
                editar.putBoolean("on_off_fone",fone.isChecked()).commit();
                editar.putInt("Progresso_Vel",progresso2).commit();
            }
        });
    }
    public void volta(){
        saiu = false;
        String tom = Float.toString(barrita1.getProgress()/50);
        String vel = Float.toString(barrita2.getProgress()/50);
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("perg", perg.isChecked());
        intent.putExtra("fone", fone.isChecked());
        intent.putExtra("tom",tom); //envia tom pra main
        intent.putExtra("vel",vel);
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
