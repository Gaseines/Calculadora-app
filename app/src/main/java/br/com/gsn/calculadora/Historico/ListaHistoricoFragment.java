package br.com.gsn.calculadora.Historico;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import br.com.gsn.calculadora.DataBase.DatabaseHelper;
import br.com.gsn.calculadora.R;


public class ListaHistoricoFragment extends Fragment {

    private DatabaseHelper databaseHelper;



    public ListaHistoricoFragment() {

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_lista_historico, container, false);

        databaseHelper = new DatabaseHelper(getActivity());
        ListView lv = v.findViewById(R.id.list_view_historico);
        databaseHelper.getAllHistorico(getActivity(), lv);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tvId = view.findViewById(R.id.textViewId);
                Bundle b = new Bundle();
                b.putInt("id", Integer.parseInt(tvId.getText().toString()));

                ExcluirFragment excluir = new ExcluirFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                excluir.setArguments(b);
                ft.replace(R.id.historico_fragment, excluir).commit();
            }

        });


        return v;
    }
}