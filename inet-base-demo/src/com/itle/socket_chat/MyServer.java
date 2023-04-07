package com.itle.socket_chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * create by Luler on 2023/4/6 18:11
 *
 * @description
 */
public class MyServer {

    public static List<Socket> socketList = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(30000);
        while (true) {
            Socket s = serverSocket.accept();
            socketList.add(s);
            new Thread(new ServerThread(s)).start();
        }
    }

}
