package com.javabasic.chat;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ClientReadThread extends  Thread{
    private Socket so;
    public ClientReadThread(Socket so){
        this.so = so;
    }

    @Override
    public void run() {
        try {
            InputStream ips = so.getInputStream();
            DataInputStream dis = new DataInputStream(ips);
            while (true) {
                try {
                    String msg = dis.readUTF();
                    System.out.println(msg);
                } catch (Exception e) {
                    dis.close();
                    so.close();
                    break;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
