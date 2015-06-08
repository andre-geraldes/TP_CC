
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author andregeraldes
 */
public class Game extends javax.swing.JFrame {
    public static String online;
    public static int uscore;
    public static int label;
    public static TreeMap<Date, Desafio> desafios;
    public static ArrayList<Utilizador> utilizadores;

    /**
     * Creates new form Game
     */
    public Game() {
        this.online = "";
        this.label = 1;
        this.desafios = new TreeMap<>();
        this.utilizadores = new ArrayList<>();
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        bemvindo = new javax.swing.JLabel();
        resultado = new javax.swing.JLabel();
        sair = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        inicio = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jogo = new javax.swing.JMenu();
        Criar_Desafio = new javax.swing.JMenuItem();
        listard = new javax.swing.JMenuItem();
        ranking = new javax.swing.JMenu();
        logout = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(300, 300));
        setSize(new java.awt.Dimension(300, 300));

        jPanel1.setMinimumSize(new java.awt.Dimension(753, 489));
        jPanel1.setSize(new java.awt.Dimension(753, 489));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });

        bemvindo.setFont(new java.awt.Font("Lucida Grande", 2, 24)); // NOI18N
        bemvindo.setText(" ");

        resultado.setFont(new java.awt.Font("Lucida Grande", 2, 24)); // NOI18N

        sair.setText("Sair");
        sair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sairMouseClicked(evt);
            }
        });
        sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addComponent(sair, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(bemvindo)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(bemvindo)
                .addGap(115, 115, 115)
                .addComponent(sair)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        inicio.setText("Inicio");

        jMenuItem2.setText("Login");
        jMenuItem2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuItem2MousePressed(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem2MouseClicked(evt);
            }
        });
        inicio.add(jMenuItem2);

        jMenuItem1.setText("Registar");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        inicio.add(jMenuItem1);

        jMenuBar1.add(inicio);

        jogo.setText("Jogo");
        jogo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jogoMouseClicked(evt);
            }
        });

        Criar_Desafio.setText("Criar");
        Criar_Desafio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Criar_DesafioMouseClicked(evt);
            }
        });
        Criar_Desafio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Criar_DesafioActionPerformed(evt);
            }
        });
        jogo.add(Criar_Desafio);

        listard.setText("Listar");
        listard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listardMouseClicked(evt);
            }
        });
        listard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listardActionPerformed(evt);
            }
        });
        jogo.add(listard);

        jMenuBar1.add(jogo);

        ranking.setText("Ranking");
        ranking.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rankingMouseClicked(evt);
            }
        });
        ranking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rankingActionPerformed(evt);
            }
        });
        jMenuBar1.add(ranking);

        logout.setText("Logout");
        logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoutMouseClicked(evt);
            }
        });
        jMenuBar1.add(logout);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        if(online.equals("")){
            new MenuRegistar().setVisible(true);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2MouseClicked

    private void jMenuItem2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem2MousePressed
        // TODO add your handling code here:
        if(online.equals("")){
            new MenuLogin().setVisible(true);
        }
    }//GEN-LAST:event_jMenuItem2MousePressed

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel1MouseClicked

    private void logoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseClicked
        // TODO add your handling code here:
        if(!this.online.equals("")){
            try (
                // Criar datagrama socket
                DatagramSocket socket = new DatagramSocket()) {
                byte[] buf = new byte[256];
                byte[] rec = new byte[256];
                InetAddress address = InetAddress.getLocalHost();

                // PDU de LOGOUT
                String pedido = "PDU[ver=0,seg=0,label=" + label++ + ",tipo=LOGOUT]Lista de campos[]"; 

                buf = pedido.getBytes();
                DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4445);
                socket.send(packet);

                // Receber resposta
                packet = new DatagramPacket(rec, rec.length);
                socket.receive(packet);

                // Apresentar resposta
                String received = new String(packet.getData(), 0, packet.getLength());

                String[] r;
                r = received.split(":\\[");
                r = r[1].split(("="));
                String tipo = r[0];
                if(tipo.equals("00")){               
                    JOptionPane.showMessageDialog(null, "Logout efectuado com sucesso");
                    this.dispose();
                }
                else {
                    JOptionPane.showMessageDialog(null, "Utilizador não logado");
                }
            } catch (SocketException ex) {
                Logger.getLogger(MenuLogin.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnknownHostException ex) {
                Logger.getLogger(MenuLogin.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(MenuLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.online = "";
            this.uscore = 0;
        }
    }//GEN-LAST:event_logoutMouseClicked

    private void jogoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jogoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jogoMouseClicked

    private void Criar_DesafioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Criar_DesafioActionPerformed
        // TODO add your handling code here:
        if(!(online.equals(""))){
            new MenuCriarDesafio().setVisible(true);
        }
    }//GEN-LAST:event_Criar_DesafioActionPerformed

    private void Criar_DesafioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Criar_DesafioMouseClicked
        // TODO add your handling code here:
        if(!(online.equals(""))){
            new MenuCriarDesafio().setVisible(true);
        }
    }//GEN-LAST:event_Criar_DesafioMouseClicked

    private void listardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listardActionPerformed
        // TODO add your handling code here:
        //get desafios
        if(!online.equals("")){
            try (
                // Criar datagrama socket
                DatagramSocket socket = new DatagramSocket()) {
                byte[] buf = new byte[256];
                byte[] rec = new byte[256];
                InetAddress address = InetAddress.getLocalHost();

                // PDU de LOGOUT
                String pedido = "PDU[ver=0,seg=0,label=" + label++ + ",tipo=LIST_CHALLENGE]Lista de campos[]"; 

                buf = pedido.getBytes();
                DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4445);
                socket.send(packet);

                // Receber resposta
                packet = new DatagramPacket(rec, rec.length);
                socket.receive(packet);

                // Apresentar resposta
                String received = new String(packet.getData(), 0, packet.getLength());
                
                
                String nomeD[] = received.split("07=\"");
                String aux;
                String auxV[];

                for(int i = 1; i < nomeD.length; i++) { 
                    
                    aux = nomeD[i];
                    auxV = aux.split("\"");
                    String nd = auxV[0];
                    
                    String horaD[] = aux.split("05=");
                    horaD = horaD[1].split(",");
                    String hd = horaD[0];

                    String diaD[] = aux.split("04=");
                    diaD = diaD[1].split(",");
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
                    
                    Desafio de = new Desafio(nd, dataD);
                    this.desafios.put(dataD, de);
                }
                
            } catch (SocketException ex) {
                Logger.getLogger(MenuLogin.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnknownHostException ex) {
                Logger.getLogger(MenuLogin.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(MenuLogin.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
            new MenuListarDesafios().setVisible(true);
        }
    }//GEN-LAST:event_listardActionPerformed

    private void listardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listardMouseClicked
        // TODO add your handling code here:
        if(!online.equals("")){
            
        }
    }//GEN-LAST:event_listardMouseClicked

    private void sairMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sairMouseClicked
        // TODO add your handling code here:
        try (
                // Criar datagrama socket
                DatagramSocket socket = new DatagramSocket()) {
                byte[] buf = new byte[256];
                byte[] rec = new byte[256];
                InetAddress address = InetAddress.getLocalHost();

                // PDU de LOGOUT
                String pedido = "PDU[ver=0,seg=0,label=" + label++ + ",tipo=LOGOUT]Lista de campos[]"; 

                buf = pedido.getBytes();
                DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4445);
                socket.send(packet);

                // Receber resposta
                packet = new DatagramPacket(rec, rec.length);
                socket.receive(packet);

                
                String received = new String(packet.getData(), 0, packet.getLength());
                
                JOptionPane.showMessageDialog(null, "Saida efectuada com sucesso");
                this.dispose();

            } catch (SocketException ex) {
                Logger.getLogger(MenuLogin.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnknownHostException ex) {
                Logger.getLogger(MenuLogin.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(MenuLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.online = "";
            this.uscore = 0;
        
    }//GEN-LAST:event_sairMouseClicked

    private void sairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sairActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sairActionPerformed

    private void rankingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rankingMouseClicked
        // TODO add your handling code here:
        if(!online.equals("")){
            try (
                // Criar datagrama socket
                DatagramSocket socket = new DatagramSocket()) {
                byte[] buf = new byte[256];
                byte[] rec = new byte[256];
                InetAddress address = InetAddress.getLocalHost();

                // PDU de LOGOUT
                String pedido = "PDU[ver=0,seg=0,label=" + label++ + ",tipo=LIST_RANKING]Lista de campos[]"; 

                buf = pedido.getBytes();
                DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4445);
                socket.send(packet);

                // Receber resposta
                packet = new DatagramPacket(rec, rec.length);
                socket.receive(packet);

                // Apresentar resposta
                String received = new String(packet.getData(), 0, packet.getLength());
                
                String nomeD[] = received.split("01=\"");
                String aux;
                String auxV[];

                for(int i = 1; i < nomeD.length; i++) { 
                    
                    aux = nomeD[i];
                    auxV = aux.split("\"");
                    String nd = auxV[0];
                    
                    String nick[] = aux.split("02=\"");
                    nick = nick[1].split("\"");
                    String nu = nick[0];

                    String pontos[] = aux.split("20=");
                    pontos = pontos[1].split(",");
                    String p = pontos[0];

                    Utilizador u = new Utilizador(Integer.parseInt(p), nd, nu);
                    //Guardar ordenado no arraylist
                    int size = this.utilizadores.size();
                    if(this.utilizadores.size() == 0) this.utilizadores.add(u);
                    for(int j = 0; j < size; j++){
                        if(this.utilizadores.get(j).getScore() >= u.getScore()){
                            this.utilizadores.add(j+1,u);
                        }
                        else this.utilizadores.add(u);
                    }
                }
                
            } catch (SocketException ex) {
                Logger.getLogger(MenuLogin.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnknownHostException ex) {
                Logger.getLogger(MenuLogin.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(MenuLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
            new MenuRanking().setVisible(true);
        }
    }//GEN-LAST:event_rankingMouseClicked

    private void rankingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rankingActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_rankingActionPerformed

    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Game().setVisible(true);
            }
        });
    }   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Criar_Desafio;
    public static javax.swing.JLabel bemvindo;
    private javax.swing.JMenu inicio;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenu jogo;
    private javax.swing.JMenuItem listard;
    private javax.swing.JMenu logout;
    private javax.swing.JMenu ranking;
    private javax.swing.JLabel resultado;
    private javax.swing.JButton sair;
    // End of variables declaration//GEN-END:variables
}
