/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author andregeraldes
 */
public class Utilizador {
    private int score;
    private String nome;
    private String nickname;
    private String password;
    private boolean online;

    public Utilizador(int s, String n, String nick, String pw){
        this.score = s;
        this.nome = n;
        this.nickname = nick;
        this.password = pw;
        this.online = false;
    }

    public Utilizador(int score, String nome, String nickname) {
        this.score = score;
        this.nome = nome;
        this.nickname = nickname;
    }
    
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isOnline() {
        return this.online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }
    
}
