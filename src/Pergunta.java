/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author andregeraldes
 */
public class Pergunta {
    private String musica;
    private String imagem;
    private String pergunta;
    private String resposta1;
    private String resposta2;
    private String resposta3;
    private int certa;

    public Pergunta(String musica, String imagem, String pergunta, String resposta1, String resposta2, String resposta3, int certa) {
        this.musica = musica;
        this.imagem = imagem;
        this.pergunta = pergunta;
        this.resposta1 = resposta1;
        this.resposta2 = resposta2;
        this.resposta3 = resposta3;
        this.certa = certa;
    }

    public Pergunta() {
        this.musica = "";
        this.imagem = "";
        this.pergunta = "";
        this.resposta1 = "";
        this.resposta2 = "";
        this.resposta3 = "";
        this.certa = 0;
    }

    public String getMusica() {
        return this.musica;
    }

    public void setMusica(String musica) {
        this.musica = musica;
    }

    public String getImagem() {
        return this.imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getPergunta() {
        return this.pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public String getResposta1() {
        return this.resposta1;
    }

    public void setResposta1(String resposta1) {
        this.resposta1 = resposta1;
    }

    public String getResposta2() {
        return this.resposta2;
    }

    public void setResposta2(String resposta2) {
        this.resposta2 = resposta2;
    }

    public String getResposta3() {
        return this.resposta3;
    }

    public void setResposta3(String resposta3) {
        this.resposta3 = resposta3;
    }

    public int getCerta() {
        return this.certa;
    }

    public void setCerta(int certa) {
        this.certa = certa;
    } 
}
