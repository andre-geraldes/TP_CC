/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import java.net.*;

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
        
        // Criar datagrama socket
        DatagramSocket socket = new DatagramSocket();
 
        // send request
        byte[] buf = new byte[256];
        InetAddress address = InetAddress.getLocalHost();
        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4445);
        socket.send(packet);
     
        // get response
        packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);
 
        // display response
        String received = new String(packet.getData(), 0, packet.getLength());
        System.out.println("No cliente: " + received);
     
        socket.close();
        
        
    }
    
}
