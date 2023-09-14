package com.javabasic.web;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);

        while(true){
            Socket socket = serverSocket.accept();

            System.out.println(socket.getRemoteSocketAddress() + "上线了");

            new ServerReadThread(socket).start();
        }

    }
}
