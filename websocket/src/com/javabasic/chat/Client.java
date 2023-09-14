package com.javabasic.chat;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket so = new Socket("127.0.0.1",8888);

        ClientReadThread crt = new ClientReadThread(so);
        crt.start();

        OutputStream ops = so.getOutputStream();

        DataOutputStream dos = new DataOutputStream(ops);

        Scanner san = new Scanner(System.in);

        while (true){
            System.out.println("请输入要发送的消息，输入 exit 退出");

            String msg = san.nextLine();

            if(msg.equals("exit")){
                dos.close();
                so.close();
                break;
            }

            dos.writeUTF(msg);
            dos.flush();
        }
    }
}
