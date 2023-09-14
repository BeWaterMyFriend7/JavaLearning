package com.javabasic.tcp1;
/*
* 接收多个客户端的多条消息
* */

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMmMC {
    public static void main(String[] args) throws IOException {
        System.out.println("服务器成功启动");

        ServerSocket serverSocket = new ServerSocket(8888);

        while (true){
            Socket soc = serverSocket.accept();
            System.out.println("客户端" + soc.getRemoteSocketAddress() + "上线了");

            new ServerThread(soc).start();

        }

    }
}
