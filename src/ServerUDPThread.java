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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
 
public class ServerUDPThread extends Thread {
 
    private HashMap<String,Utilizador> utilizadores;
    private HashMap<Integer,Pergunta> perguntas;
    private HashMap<String,Desafio> desafios;
    private String userOnline;
    private String musicDir;
    private String imagDir;
    private int nrPerguntas;
    protected DatagramSocket socket = null;
 
    public ServerUDPThread() throws IOException {
        this("Servidor novo");
        this.utilizadores = new HashMap<>();
        this.desafios = new HashMap<>();
        this.perguntas = new HashMap<>();
        Utilizador ut = new Utilizador(0, "admin", "admin", "admin");
        this.getUtilizadores().put("admin", ut);
        this.lerPerguntas();
    }

    public ServerUDPThread(String name) throws IOException {
        super(name);
        socket = new DatagramSocket(4445);
        System.out.println("Servidor ligado na porta: " + socket.getLocalPort() + " e no Address: " + socket.getLocalSocketAddress() );
    }
 
    public void run() {
        boolean b = true;
        String online = "";
        String desafioActivo = null;
        boolean inicio = false;
        while (b) {
            try {
                byte[] buf = new byte[256];
 
                // receber resposta
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                
                // recebido
                String received = new String(packet.getData(), 0, packet.getLength());
                
                //Apresentar data no terminal do servidor
                Date date = new Date(); 
                String data = "[" + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds() + "]";
                System.out.println(data + "PDU recebido -> " + received);
                
                // parse do datagrama recebido
                String[] lb;
                lb = received.split("label=");
                lb = lb[1].split((","));
                int label = Integer.parseInt(lb[0]);
                
                String resposta = "";  
                //Parse para saber de que tipo é o pedido
                String op = splitPDU(received);
                
                if(op.equals("LOGIN")){                   
                    String[] nick;
                    nick = received.split("02=\"");
                    nick = nick[1].split(("\""));
                    String n = nick[0];
                    online = n;
                    
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
                        online = "";
                    }
                    else {
                        resposta = "PDU[ver=0,seg=0,label=" + label + ",tipo=REPLY]Lista de Campos:[255=\"Erro de logout\"]";
                    }
                }
                else if(op.equals("MAKE_CHALLENGE")){
                    //Criar desafio 07=nome 04=dia 05=hora
                    String nomeD[] = received.split("07=\"");
                    nomeD = nomeD[1].split("\"");
                    String nd = nomeD[0];
                    
                    String horaD[] = received.split("05=");
                    if(horaD[0] != received){
                        horaD = horaD[1].split(",");
                        String hd = horaD[0];
                        
                        String diaD[] = received.split("04=");
                        diaD = diaD[1].split("\\]");
                        String dd = diaD[0];
                        
                        DateFormat format = new SimpleDateFormat("yyMMdd", Locale.ENGLISH);
                        Date diD = format.parse(dd);
                        
                        format = new SimpleDateFormat("HHmmss", Locale.ENGLISH);
                        Date horD = format.parse(hd);      
                        
                        Date dataD = new Date();
                        dataD.setHours(horD.getHours());
                        dataD.setMinutes(horD.getMinutes());
                        dataD.setSeconds(horD.getSeconds());
                        dataD.setDate(diD.getDate());
                        dataD.setMonth(diD.getMonth());
                        dataD.setYear(diD.getYear());
                        
                        if(!this.desafios.containsKey(nd)){
                            Desafio desafio = new Desafio(nd, dataD);
                            this.desafios.put(nd, desafio);
                            resposta = "PDU[ver=0,seg=0,label=" + label + ",tipo=REPLY]Lista de Campos:[00=0]";
                        }
                        else resposta = "PDU[ver=0,seg=0,label=" + label + ",tipo=REPLY]Lista de Campos:[255=\"Nome de desafio já existente\"]";
                    }
                    else {
                        //criar em 5 min
                        Date d = new Date();
                        d.setMinutes(d.getMinutes() + 5);

                        if(!this.desafios.containsKey(nd)){
                            Desafio desafio = new Desafio(nd, d);
                            this.desafios.put(nd, desafio);
                            resposta = "PDU[ver=0,seg=0,label=" + label + ",tipo=REPLY]Lista de Campos:[00=0]";
                        }
                        else resposta = "PDU[ver=0,seg=0,label=" + label + ",tipo=REPLY]Lista de Campos:[255=\"Nome de desafio já existente\"]";
                    }
                    
                } else if(op.equals("LIST_CHALLENGE")){
                    resposta = "PDU[ver=0,seg=0,label=" + label + ",tipo=REPLY]Lista de Campos:[";
                    if(!this.desafios.isEmpty()){
                        for(Desafio desa : this.desafios.values()){
                            resposta += "07=\"" + desa.getNome() + "\",04="+String.format("%02d", desa.getData().getYear()-100) + String.format("%02d", desa.getData().getMonth()+1) + String.format("%02d", desa.getData().getDate()) +",05="+ String.format("%02d", desa.getData().getHours()) + String.format("%02d", desa.getData().getMinutes()) + String.format("%02d", desa.getData().getSeconds())+",";        
                        }
                        resposta += "]";
                    }
                    else resposta = "PDU[ver=0,seg=0,label=" + label + ",tipo=REPLY]Lista de Campos:[255=\"Não existem desafios\"]";
                } else if(op.equals("LIST_RANKING")){
                    resposta = "PDU[ver=0,seg=0,label=" + label + ",tipo=REPLY]Lista de Campos:[";
                    if(!this.utilizadores.isEmpty()){
                        for(Utilizador u : this.utilizadores.values()){
                            if(!u.getNome().equals("admin"))
                                resposta += "01=\"" + u.getNome() + "\",02=\""+u.getNickname()+"\",20="+u.getScore()+",";    
                        }
                        resposta += "]";
                    }
                    else resposta = "PDU[ver=0,seg=0,label=" + label + ",tipo=REPLY]Lista de Campos:[255=\"Não existem utilizadores\"]";
                } else if(op.equals("ACCEPT_CHALLENGE")){
                    String nomeD[] = received.split("07=\"");
                    nomeD = nomeD[1].split("\"");
                    String nd = nomeD[0];
                    desafioActivo = nd;
                    
                    if(!this.desafios.get(nd).getJogadores().contains(online)){
                        this.desafios.get(nd).getJogadores().add(online);
                        resposta = "PDU[ver=0,seg=0,label=" + label + ",tipo=REPLY]Lista de Campos:[00=0]";
                        inicio = true;
                    }
                    else resposta = "PDU[ver=0,seg=0,label=" + label + ",tipo=REPLY]Lista de Campos:[255=\"Utilizador já aceitou este desafio\"]";
   
                }  
 
                buf = resposta.getBytes();
                // Enviar resposta ao cliente
                InetAddress address = packet.getAddress();
                int port = packet.getPort(); 
                packet = new DatagramPacket(buf, buf.length, address, port);
                socket.send(packet);
                
                if(inicio) {
                    ArrayList<Integer> indices = this.randomDesafio();
                    HashMap<Integer,Pergunta> perg = new HashMap<>();
                    for(int h = 0; h < 10; h++){
                        perg.put(h, this.perguntas.get(indices.get(h)));
                    }
                    this.desafios.get(desafioActivo).setPerguntasDesafio(perg);
                    for(int h = 0; h < 10; h++){
                        String desafiostart = "PDU[ver=0,seg=0,label=" + label + ",tipo=REPLY]Lista de Campos:[07=\""+desafioActivo+"\",10="+h+",11=\""+this.desafios.get(desafioActivo).getPerguntasDesafio().get(h).getPergunta()+"\",12=1,13=\""+this.desafios.get(desafioActivo).getPerguntasDesafio().get(h).getResposta1()+"\",12=2,13=\""+this.desafios.get(desafioActivo).getPerguntasDesafio().get(h).getResposta2()+"\",12=3,13=\""+this.desafios.get(desafioActivo).getPerguntasDesafio().get(h).getResposta3()+"\",]";//Falta enviar imagem e campo 254
                        //buf = desafiostart.getBytes();
                    }
                }
                
                date = new Date();
                data = "[" + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds() + "]";
                System.out.println(data + "PDU enviado -> " + resposta + "\n");
        } catch (IOException e) {
            } catch (ParseException ex) {
                Logger.getLogger(ServerUDPThread.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public void lerPerguntas() throws FileNotFoundException, IOException{
        BufferedReader br = new BufferedReader(new FileReader("src/perguntas.txt"));
        int i = 0, j = 0, k = 0, in = 0;
        try {        
            String line = br.readLine();
        
        
            String[] dir = line.split("=");
            dir = dir[1].split("\n");
            this.musicDir = dir[0];
            line = br.readLine();
                
            dir = line.split("=");
            dir = dir[1].split("\n");
            this.imagDir = dir[0];
            line = br.readLine();
                
            dir = line.split("=");
            dir = dir[1].split("\n");
            this.nrPerguntas = Integer.parseInt(dir[0]);
            
            line = br.readLine();
            while (line != null) {
                String[] per = line.split(",");

                Pergunta p = new Pergunta();
                p.setMusica(per[0]);
                p.setImagem(per[1]);
                p.setPergunta(per[2]);
                p.setResposta1(per[3]);
                p.setResposta2(per[4]);
                p.setResposta3(per[5]);
                p.setCerta(Integer.parseInt(per[6]));
                this.perguntas.put(k++, p);
                line = br.readLine();             
            }          
        } finally {
            br.close();
        }
        this.randomDesafio();
    }
    
    // Gerar um arraylist com 10 indices de perguntas para um desafio
    public ArrayList<Integer> randomDesafio(){
        ArrayList<Integer> rand = new ArrayList<>(10);
        int i, ran;
        Random rando = new Random();
        for(i = 0; i < 10; i++){
            ran = rando.nextInt(this.nrPerguntas);
            if(rand.contains(ran)){
                while(rand.contains(ran)){
                    ran = rando.nextInt(this.nrPerguntas);
                }
                rand.add(ran);
            }
            else rand.add(ran);
        }

        return rand;
    }
}
