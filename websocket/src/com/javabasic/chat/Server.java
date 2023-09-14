package com.javabasic.chat;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;

public class Server {
    public static List<Socket> socketLists = new ArrayList<Socket>();
//    List

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);

        System.out.println("服务器启动成功！");

        while (true) {
            Socket so = serverSocket.accept();
            socketLists.add(so);
            System.out.println(so.getRemoteSocketAddress() + "上线了");
            ServerReadThread sth = new ServerReadThread(so);
            sth.start();
        }


    }
}
