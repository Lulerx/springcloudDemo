package com.itle.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

/**
 * create by Luler on 2023/4/6 16:19
 *
 * @description
 */
public class ClientMain {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 30000);
        Socket s = new Socket();
//        s.connect(new InetAddress("127.0.0.1", 30000),10000);

        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String line = br.readLine();
        System.out.println("来自服务器的数据：" + line);
        br.close();
        socket.close();
    }
}
