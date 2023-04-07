package com.itle.socket;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * create by Luler on 2023/4/6 16:16
 *
 * @description
 */
public class ServerMain {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(30000);

        while (true){
            Socket accept = serverSocket.accept();
            PrintStream ps = new PrintStream(accept.getOutputStream());
            ps.println("hello!");
            ps.close();
            serverSocket.close();
        }
    }
}
