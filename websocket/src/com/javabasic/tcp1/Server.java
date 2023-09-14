package com.javabasic.tcp1;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        System.out.println("-------------------");

        ServerSocket serverSocket = new  ServerSocket(8888);
        Socket so = serverSocket.accept();

        InputStream inp = so.getInputStream();

        DataInputStream is = new DataInputStream(inp);

        String str = is.readUTF();
        System.out.println(str);

        System.out.println("客户端地址： "+ so.getInetAddress());

        is.close();
        so.close();
    }
}
