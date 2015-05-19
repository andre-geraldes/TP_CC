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
 
public class ServerUDPThread extends Thread {
 
    protected DatagramSocket socket = null;
    protected BufferedReader in = null;
    protected boolean moreQuotes = true;
 
    public ServerUDPThread() throws IOException {
    this("QuoteServerThread");
    }
 
    public ServerUDPThread(String name) throws IOException {
        super(name);
        socket = new DatagramSocket(4445);
    }
 
    public void run() {
        boolean login = true;
        while (login) {
            try {
                byte[] buf = new byte[256];
 
                // receive request
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
 
                // figure out response
                String dString = null;
                if (in == null)
                    dString = new Date().toString();
                else
                    dString = "teste";
 
                buf = dString.getBytes();
 
                // send the response to the client at "address" and "port"
                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                packet = new DatagramPacket(buf, buf.length, address, port);
                socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
            }
        }
        socket.close();
    }
}
