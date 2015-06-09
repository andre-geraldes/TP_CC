
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author andregeraldes
 */
public class Desafio {
    private String nome;
    private Date data;
    private HashMap<Integer,Pergunta> perguntasDesafio;
    private ArrayList<String> jogadores;

    public Desafio(String nome, Date data) {
        this.nome = nome;
        this.data = data;
        this.perguntasDesafio = new HashMap<>(10);
        this.jogadores = new ArrayList<>();
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getData() {
        return this.data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public HashMap<Integer,Pergunta> getPerguntasDesafio() {
        return this.perguntasDesafio;
    }

    public void setPerguntasDesafio(HashMap<Integer,Pergunta> perguntasDesafio) {
        this.perguntasDesafio = perguntasDesafio;
    }

    public ArrayList<String> getJogadores() {
        return this.jogadores;
    }

    public void setJogadores(ArrayList<String> jogadores) {
        this.jogadores = jogadores;
    }
    
    
}
