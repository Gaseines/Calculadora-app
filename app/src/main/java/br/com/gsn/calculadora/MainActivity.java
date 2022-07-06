package br.com.gsn.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import br.com.gsn.calculadora.DataBase.DatabaseHelper;
import br.com.gsn.calculadora.Historico.Historico;
import br.com.gsn.calculadora.Historico.HistoricoActivity;
import br.com.gsn.calculadora.Historico.ListaHistoricoFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button numeroZero,numeroUm,numeroDois,numeroTres,numeroQuatro,numeroCinco,numeroSeis,numeroSete,
            numeroOito,numeroNove,virgula,soma,subtração,multiplicação,divisão,igual,limpar,mod;


    private ImageView historico;

    private TextView txtConta,txtResultado;
    private ImageView apagar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        IniciarComponentes();
        getSupportActionBar().hide();


        numeroZero.setOnClickListener(this);
        numeroUm.setOnClickListener(this);
        numeroDois.setOnClickListener(this);
        numeroTres.setOnClickListener(this);
        numeroQuatro.setOnClickListener(this);
        numeroCinco.setOnClickListener(this);
        numeroSeis.setOnClickListener(this);
        numeroSete.setOnClickListener(this);
        numeroOito.setOnClickListener(this);
        numeroNove.setOnClickListener(this);
        virgula.setOnClickListener(this);
        soma.setOnClickListener(this);
        subtração.setOnClickListener(this);
        divisão.setOnClickListener(this);
        multiplicação.setOnClickListener(this);
        mod.setOnClickListener(this);


        limpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtConta.setText("");
                txtResultado.setText("");
            }
        });

        historico = findViewById(R.id.historico);
        historico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hist = new Intent(MainActivity.this, HistoricoActivity.class );
                startActivity(hist);
            }
        });

        apagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView conta = findViewById(R.id.text_conta);
                String string = conta.getText().toString();

                if (!string.isEmpty()){

                    byte var0 = 0;
                    int var1 = string.length()-1;
                    String txtConta = string.substring(var0,var1);
                    conta.setText(txtConta);

                }
                txtResultado.setText("");
            }
        });

        igual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Expression expressao = new ExpressionBuilder(txtConta.getText().toString()).build();
                    double resultado = expressao.evaluate();
                    long longResultado = (long) resultado;

                    if (resultado == (double)longResultado){
                        txtResultado.setText((CharSequence) String.valueOf(longResultado));
                    }else {
                        txtResultado.setText((CharSequence) String.valueOf(resultado));
                    }

                }catch (Exception h){

                }

                adicionar();

            }


        });




    }

    private void IniciarComponentes(){
        numeroZero = findViewById(R.id.bt_0);
        numeroUm = findViewById(R.id.bt_1);
        numeroDois = findViewById(R.id.bt_2);
        numeroTres = findViewById(R.id.bt_3);
        numeroQuatro = findViewById(R.id.bt_4);
        numeroCinco = findViewById(R.id.bt_5);
        numeroSeis = findViewById(R.id.bt_6);
        numeroSete = findViewById(R.id.bt_7);
        numeroOito = findViewById(R.id.bt_8);
        numeroNove = findViewById(R.id.bt_9);
        virgula = findViewById(R.id.bt_virgula);
        soma = findViewById(R.id.bt_soma);
        subtração = findViewById(R.id.bt_subtracao);
        multiplicação = findViewById(R.id.bt_multiplicaçao);
        divisão = findViewById(R.id.bt_divisao);
        igual = findViewById(R.id.bt_igual);
        limpar = findViewById(R.id.bt_limpar);
        mod = findViewById(R.id.bt_mod);
        txtConta = findViewById(R.id.text_conta);
        txtResultado = findViewById(R.id.text_resultado);
        apagar = findViewById(R.id.bt_apagar);
    }

    public void AdicionarUmaExpressao(String string,boolean limpar_dados){

     if(txtResultado.getText().equals("")){
         txtConta.setText(" ");
     }
     if (limpar_dados){
         txtResultado.setText(" ");
         txtConta.append(string);
     }else{
         txtConta.append(txtResultado.getText());
         txtConta.append(string);
         txtResultado.setText(" ");
     }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_0:
                AdicionarUmaExpressao("0", true);
                break;

            case R.id.bt_1:
                AdicionarUmaExpressao("1", true);
                break;

            case R.id.bt_2:
                AdicionarUmaExpressao("2", true);
                break;

            case R.id.bt_3:
                AdicionarUmaExpressao("3", true);
                break;

            case R.id.bt_4:
                AdicionarUmaExpressao("4", true);
                break;

            case R.id.bt_5:
                AdicionarUmaExpressao("5", true);
                break;

            case R.id.bt_6:
                AdicionarUmaExpressao("6", true);
                break;

            case R.id.bt_7:
                AdicionarUmaExpressao("7", true);
                break;

            case R.id.bt_8:
                AdicionarUmaExpressao("8", true);
                break;

            case R.id.bt_9:
                AdicionarUmaExpressao("9", true);
                break;

            case R.id.bt_virgula:
                AdicionarUmaExpressao(".", true);
                break;

            case R.id.bt_soma:
                AdicionarUmaExpressao("+", false);
                break;

            case R.id.bt_subtracao:
                AdicionarUmaExpressao("-", false);
                break;

            case R.id.bt_multiplicaçao:
                AdicionarUmaExpressao("*", false);
                break;

            case R.id.bt_divisao:
                AdicionarUmaExpressao("/", false);
                break;

            case R.id.bt_mod:
                AdicionarUmaExpressao("%", false);
                break;
        }

    }


    private void adicionar() {
        if (txtConta.getText().toString().equals("Digite uma conta")){
            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
        }else {
            DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);
            Historico h = new Historico();
            h.setConta(txtConta.getText().toString());
            h.setResultado(txtResultado.getText().toString());
            databaseHelper.createHistorico(h);
        }

    }

}


