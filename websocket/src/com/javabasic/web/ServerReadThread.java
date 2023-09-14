package com.javabasic.web;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerReadThread extends Thread{

    private Socket socket;
    public ServerReadThread(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
//        super.run();
        try {
            OutputStream ops = socket.getOutputStream();
            PrintStream ps = new PrintStream(ops);
            ps.println("HTTP/1.1 200 OK");
            ps.println("Content-Type:text/html;charset=UTF-8");
            ps.println();
            ps.println("test something");
            socket.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
