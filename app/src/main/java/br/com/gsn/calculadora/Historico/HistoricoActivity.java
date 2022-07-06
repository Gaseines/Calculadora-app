package br.com.gsn.calculadora.Historico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import br.com.gsn.calculadora.MainActivity;
import br.com.gsn.calculadora.R;

public class HistoricoActivity extends AppCompatActivity {

    private ImageView voltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);

        voltar = findViewById(R.id.voltar);
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent voltar = new Intent(HistoricoActivity.this, MainActivity.class);
                startActivity(voltar);
            }
        });

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_main, new HistoricoFragment()).commit();
        }

        getSupportActionBar().hide();

    }
}