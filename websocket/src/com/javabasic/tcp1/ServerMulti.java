package com.javabasic.tcp1;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMulti {
    public static void main(String[] args) throws IOException {
        System.out.println("————————服务器启动成功——————");

        ServerSocket serverSocket = new ServerSocket(8888);

        Socket so = serverSocket.accept();

        InputStream inp = so.getInputStream();

        DataInputStream dis = new DataInputStream(inp);

        while (true) {
            InetAddress add = so.getInetAddress();
            try {
                String msg = dis.readUTF();
                System.out.println(msg + " come from " + add );
            } catch (Exception e) {
                System.out.println("the client " + add + "is closed.....");
                dis.close();
                so.close();
                break;
            }
        }

    }
}
