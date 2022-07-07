package br.com.gsn.calculadora.Historico;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import br.com.gsn.calculadora.DataBase.DatabaseHelper;
import br.com.gsn.calculadora.R;

public class ExcluirFragment extends Fragment {


    private DatabaseHelper databaseHelper;
    private TextView tvId;
    private TextView tvConta;
    private TextView tvResultado;
    public  Historico h;

    public ExcluirFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_excluir, container, false);

        tvConta = v.findViewById(R.id.textViewContaEx);
        tvResultado = v.findViewById(R.id.textViewResultadoEx);



        Bundle b = getArguments();
        int id_historico = b.getInt("id");
        databaseHelper = new DatabaseHelper(getActivity());
        h = databaseHelper.getByIdEndereco(id_historico);

        tvConta.setText(h.getConta());
        tvResultado.setText(h.getResultado());



        Button btnExcluir = v.findViewById(R.id.excluirHistorico);
        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Deseja excluir essa conta?");
                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        excluir(id_historico);
                    }
                });
                builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Não faz nada//
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });


        return v;
    }

    private void excluir(int id) {
        h = new Historico();
        h.setId(id);
        databaseHelper.deleteHistorico(h);
        Toast.makeText(getActivity(), "Conta Excluida", Toast.LENGTH_LONG).show();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.historico_fragment, new ListaHistoricoFragment()).commit();
    }
}