package com.itle.chat_room;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * create by Luler on 2023/4/6 18:11
 *
 * @description 先启动 Server，再启动多个 Client，在Client中发送消息
 *  可私聊可公聊，私聊已 ‘//昵称:消息内容’ 的格式发送
 */
public class Server {

    public static final int SERVER_PORT = 30000;
    public static CrazyMap<String, PrintStream> clients = new CrazyMap<>();

    public void init() {
        try {
            ServerSocket ss = new ServerSocket(SERVER_PORT);
            while (true){
                Socket socket = ss.accept();
                new Thread(new ServerThread(socket)).start();
            }
        } catch (IOException e) {
            System.out.println("服务器启动失败");
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.init();
    }

}
