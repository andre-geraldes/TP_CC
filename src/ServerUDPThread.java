/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author andregeraldes
 */
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.Date;
 
public class ServerUDPThread extends Thread {
 
    private HashMap<String,Utilizador> utilizadores;
    private HashMap<Integer,Pergunta> perguntas;
    private HashMap<String,Desafio> desafios;
    private String userOnline;
    protected DatagramSocket socket = null;
 
    public ServerUDPThread() throws IOException {
        this("Servidor novo");
        this.utilizadores = new HashMap<>();
        this.desafios = new HashMap<>();
        this.perguntas = new HashMap<>();
        Utilizador ut = new Utilizador(0, "admin", "admin", "admin");
        this.getUtilizadores().put("admin", ut);
    }

    public ServerUDPThread(String name) throws IOException {
        super(name);
        socket = new DatagramSocket(4445);
    }
 
    public void run() {
        boolean b = true;
        while (b) {
            try {
                byte[] buf = new byte[256];
 
                // receber resposta
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                
                // recebido
                String received = new String(packet.getData(), 0, packet.getLength());
                Date date = new Date(); 
                String data = "[" + date.getDate() + ":" + date.getMinutes() + ":" + date.getSeconds() + "]";
                System.out.println(data + "Pedido recebido -> " + received);
                
                // parse 
                String[] lb;
                lb = received.split("label=");
                lb = lb[1].split((","));
                int label = Integer.parseInt(lb[0]);
                
                String resposta = "";   
                String op = splitPDU(received);
                
                if(op.equals("LOGIN")){                   
                    String[] nick;
                    nick = received.split("02=\"");
                    nick = nick[1].split(("\""));
                    String n = nick[0];
                    
                    String[] password;
                    password = received.split("03='");
                    password = password[1].split("'");
                    String p = password[0];                  
                    
                    if(this.getUtilizadores().containsKey(n)){ 
                        if(this.getUtilizadores().get(n).getPassword().equals(p)){
                            if (!this.getUtilizadores().get(n).isOnline()){
                                resposta = "PDU[ver=0,seg=0,label=" + label + ",tipo=REPLY]Lista de Campos:[01=\"" + this.getUtilizadores().get(n).getNome() + "\",20=" + this.getUtilizadores().get(n).getScore() + "]";
                                this.getUtilizadores().get(n).setOnline(true);
                                this.setUserOnline(n);
                            }
                            else {
                                resposta = "PDU[ver=0,seg=0,label=" + label + ",tipo=REPLY]Lista de Campos:[255=\"Utilizador já online\"]"; 
                            }
                                
                        }
                        else {
                            resposta = "PDU[ver=0,seg=0,label=" + label + ",tipo=REPLY]Lista de Campos:[255=\"Password errada\"]";                       
                        }
                    }
                    else {
                        resposta = "PDU[ver=0,seg=0,label=" + label + ",tipo=REPLY]Lista de Campos:[255=\"Utilizador nao existe\"]";                       
                    }
                }
                else if(op.equals("REGISTER")){
                    String nick[] = received.split("02=\"");
                    nick = nick[1].split("\"");
                    String n = nick[0];
                    if(!this.getUtilizadores().containsKey(n)){ 
                        String user[] = received.split("01=\"");
                        user = user[1].split("\"");
                        String u = user[0];             
                
                        String pass[] = received.split("03='");
                        pass = pass[1].split("'");
                        String p = pass[0];
                    
                        Utilizador ut = new Utilizador(0,u,n,p);
                        this.getUtilizadores().put(n,ut);
                        resposta = "PDU[ver=0,seg=0,label=" + label + ",tipo=REPLY]Lista de Campos:[00=0]";
                    }
                    else resposta = "PDU[ver=0,seg=0,label=" + label + ",tipo=REPLY]Lista de Campos:[255=\"Utilizador já existe\"]";
                }
                else if(op.equals("LOGOUT")){
                    if(this.getUtilizadores().containsKey(userOnline) && this.getUtilizadores().get(userOnline).isOnline()){
                        resposta = "PDU[ver=0,seg=0,label=" + label + ",tipo=REPLY]Lista de Campos:[00=0]";                         
                        this.getUtilizadores().get(userOnline).setOnline(false);
                        this.setUserOnline("");
                    }
                    else {
                        resposta = "PDU[ver=0,seg=0,label=" + label + ",tipo=REPLY]Lista de Campos:[255=\"Erro de logout\"]";
                    }
                }
                else if(op.equals("MAKE_CHALLENGE")){
                    
                };       
 
                buf = resposta.getBytes();
                // Enviar resposta ao cliente
                InetAddress address = packet.getAddress();
                int port = packet.getPort(); 
                packet = new DatagramPacket(buf, buf.length, address, port);
                socket.send(packet);
                
                date = new Date();
                data = "[" + date.getDate() + ":" + date.getMinutes() + ":" + date.getSeconds() + "]";
                System.out.println(data + "Pedido enviado -> " + resposta);
        } catch (IOException e) {
            e.printStackTrace();
            }
        }
        socket.close();
    }

    public HashMap<String,Utilizador> getUtilizadores() {
        return this.utilizadores;
    }

    public void setUtilizadores(HashMap<String,Utilizador> utilizadores) {
        this.utilizadores = utilizadores;
    }   
    
    public HashMap<Integer, Pergunta> getPerguntas() {
        return perguntas;
    }

    public void setPerguntas(HashMap<Integer, Pergunta> perguntas) {
        this.perguntas = perguntas;
    }

    public HashMap<String, Desafio> getDesafios() {
        return desafios;
    }

    public void setDesafios(HashMap<String, Desafio> desafios) {
        this.desafios = desafios;
    }

    public String getUserOnline() {
        return userOnline;
    }

    public void setUserOnline(String userOnline) {
        this.userOnline = userOnline;
    }
  
    public String splitPDU(String pdu){
        String[] nova;
        nova = pdu.split("tipo=");
        nova = nova[1].split("]");
        
        return nova[0];
    }
    
    public void lerPerguntas(String filename){
        
    }
}
