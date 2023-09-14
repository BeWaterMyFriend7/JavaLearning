package com.javabasic.tcp1;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientMulti {
    public static void main(String[] args) throws IOException {
        Socket clientSocket = new Socket("127.0.0.1",8888);

        OutputStream out = clientSocket.getOutputStream();
        DataOutputStream dos = new DataOutputStream(out);

        Scanner scn = new Scanner(System.in);
        while (true) {
            System.out.println("请输入要发送的信息, 输入 exit  退出程序");
            String msg = scn.nextLine();

            if(msg.equals("exit")){
                System.out.println("客户端退出成功");
                dos.close();
                clientSocket.close();
                break;
            }

            dos.writeUTF(msg);
            dos.flush();
        }

    }
}
