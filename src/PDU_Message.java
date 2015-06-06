/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author andregeraldes
 */
public class PDU_Message {
    private String pdu;
    private String tipo;
    private int label;
    
    public PDU_Message(){
        this.pdu = "";
        this.tipo = "";
        this.label = 0;
    }
    
    public PDU_Message(String p, String t, int l){
        this.pdu = p;
        this.tipo = t;
        this.label = l;
    }

    public String getPdu() {
        return pdu;
    }

    public void setPdu(String pdu) {
        this.pdu = pdu;
    }

    public int getLabel() {
        return label;
    }

    public void setLabel(int label) {
        this.label = label;
    }
       
    public String newLoginPDU(int label, String nome, String pass){
        this.pdu = "PDU[ver=0,seg=0,label=" + label + ",tipo=LOGIN]Lista de Campos:[02=\"" + nome + "\",03='" + pass + "']";
        this.tipo = "LOGIN";
        this.label = label;
        return this.pdu;
    }
    
    public String newRegisterPDU(int label, String nome, String nick, String pass){
        
        return this.pdu;
    }
}
