package br.com.gsn.calculadora.Historico;

public class Historico {


    private int id;
    private String conta;
    private String Resultado;


    public String getResultado() {
        return Resultado;
    }

    public void setResultado(String resultado) {
        Resultado = resultado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }



}
