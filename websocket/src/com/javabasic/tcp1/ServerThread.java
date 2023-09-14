package com.javabasic.tcp1;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.SocketAddress;

public class ServerThread extends Thread{
    private  Socket so;

    ServerThread(Socket so){
        this.so = so;
    }

    @Override
    public void run() {

        try {
            InputStream inp = so.getInputStream();
            DataInputStream dis = new DataInputStream(inp);
            while(true){
                SocketAddress add  = so.getRemoteSocketAddress();
                try {
                    String msg = dis.readUTF();
                    System.out.println(msg + " come from  " + add);
                } catch (Exception e) {
                    System.out.println("客户端" + add + "下线了");
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
