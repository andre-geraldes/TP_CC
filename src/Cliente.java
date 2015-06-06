/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import java.net.*;

import java.util.Scanner;

/**
 * 
 * @author andregeraldes
 */
public class Cliente {
    /**
     * @param args the command line arguments
     * @throws java.net.SocketException
     */
    public static void main(String[] args) throws SocketException, IOException {
        
        // enviar pedido
        try ( 
            // Criar datagrama socket
            DatagramSocket socket = new DatagramSocket()) {
            byte[] buf = new byte[256];
            InetAddress address = InetAddress.getLocalHost();
            
            String pedido = "PDU[ver=0,seg=0,label=6,tipo=LOGIN]Lista de Campos:[02=\"ZéZe\",03='JDU7736SG668']";
            
            //Criar pacote de envio
            buf = pedido.getBytes();
            DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4445);
            socket.send(packet);
            
           
            // Receber resposta
            packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);
            
            // Apresentar resposta
            String received = new String(packet.getData(), 0, packet.getLength());
            System.out.println("algo" + received);
        }
        
    }
    
}
