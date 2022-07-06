package br.com.gsn.calculadora.Historico;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import br.com.gsn.calculadora.R;

public class HistoricoFragment extends Fragment {



    public HistoricoFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_historico, container, false);

        if (savedInstanceState == null){
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.historico_fragment, new ListaHistoricoFragment()).commit();
        }


        // Inflate the layout for this fragment
        return v;
    }
}