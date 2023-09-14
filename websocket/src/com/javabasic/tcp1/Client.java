package com.javabasic.tcp1;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket so =  new Socket("127.0.0.1",8888);

        OutputStream out =  so.getOutputStream();

        DataOutputStream dos = new DataOutputStream(out);

        dos.writeUTF("test something");
        dos.flush();
        dos.close();
        so.close();
    }



}
