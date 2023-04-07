package com.itle.chat_room;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * create by Luler on 2023/4/7 15:46
 *
 * @description
 */
public class Client {
    private static final int SERVER_PORT = 30000;
    private Socket socket;
    private PrintStream ps;
    private BufferedReader brServer;
    private BufferedReader keyIn;

    //初始化，连接到服务器
    private void init() {
        try {
            keyIn = new BufferedReader(new InputStreamReader(System.in));
            socket = new Socket("127.0.0.1", SERVER_PORT);
            ps = new PrintStream(socket.getOutputStream());
            brServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String tip = "";
            while (true) {
                String userName = JOptionPane.showInputDialog(tip + "输入用户名");
                ps.println(CrazyProtocol.USER_ROUND + userName + CrazyProtocol.USER_ROUND);
                String result = brServer.readLine();
                if (result.equals(CrazyProtocol.NAME_REP)){
                    tip = "用户名重复！请重试";
                    continue;
                }
                if (result.equals(CrazyProtocol.LOGIN_SUCCESS)){
                    break;
                }
            }
        }catch (Exception e){
            System.out.println("找不到远程服务器");
            closeRs();
            System.exit(1);
        }
        //
        new ClientThread(brServer).start();
    }

    //读取和发送服务器内容
    private void readAndSend() {
        try {
            String line = null;
            while ((line = keyIn.readLine()) != null){
                //私聊以 '//私聊对象:内容' 的格式发送
                if (line.indexOf(":") > 0 && line.startsWith("//")) {
                    line = line.substring(2);
                    ps.println(CrazyProtocol.PRIVATE_ROUND + line.split(":")[0] +
                            CrazyProtocol.SPLIT_SIGN + line.split(":")[1] +
                            CrazyProtocol.PRIVATE_ROUND);
                }else {
                    ps.println(CrazyProtocol.MSG_ROUND + line +
                            CrazyProtocol.MSG_ROUND);
                }
            }
        } catch (IOException e) {
            System.out.println("网络通信异常！");
            closeRs();
            System.exit(1);
        }
    }

    private void closeRs() {
        try {
            if (keyIn != null) ps.close();
            if (brServer != null) ps.close();
            if (ps != null) ps.close();
            if (socket != null) keyIn.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.init();
        client.readAndSend();
    }

}
