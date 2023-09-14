package com.javabasic.chat;

import java.io.*;
import java.net.Socket;
import java.net.SocketAddress;

public class ServerReadThread extends  Thread{
    private Socket so;
    ServerReadThread(Socket so){
        this.so = so;
    }

    @Override
    public void run() {
        try {
            SocketAddress add = so.getRemoteSocketAddress();
//            System.out.println(add + "上线了");
            InputStream ips = so.getInputStream();
            DataInputStream dis = new DataInputStream(ips);
            while (true) {
                try {
                    String msg = dis.readUTF();
                    System.out.println(msg + " come from " + add);
                    SendMsg2All(msg);
                } catch (Exception e) {
                    System.out.println(add + "下线了");
                    Server.socketLists.remove(so);
                    dis.close();
                    so.close();
                    break;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void SendMsg2All(String msg) throws IOException {
        for (Socket so : Server.socketLists) {
            OutputStream os = so.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);
            dos.writeUTF(msg);
            dos.flush();
        }
    }
}
