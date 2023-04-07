package com.itle.chat_room;

import com.itle.socket_chat.MyServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * create by Luler on 2023/4/6 18:16
 *
 * @description
 */
public class ServerThread extends Thread {

    Socket socket = null;
    BufferedReader br = null;
    PrintStream ps = null;

    public ServerThread(Socket s){
        this.socket = s;
    }

    public void run() {
        try {

            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            ps = new PrintStream(socket.getOutputStream());
            String content = null;

            while ((content = br.readLine()) != null){
                //读到的行是以 CrazyProtocol.USER_ROUND 开头并以其结束，说明读到的是用户登录的用户名
                if (content.startsWith(CrazyProtocol.USER_ROUND)
                        && content.endsWith(CrazyProtocol.USER_ROUND)) {
                    String userName = getRealMsg(content);
                    if (Server.clients.map.containsKey(userName)){
                        System.out.println("重复");
                        ps.println(CrazyProtocol.NAME_REP);
                    }else {
                        System.out.println("成功");
                        ps.println(CrazyProtocol.LOGIN_SUCCESS);
                        Server.clients.put(userName, ps);
                    }
                } //私聊
                else if (content.startsWith(CrazyProtocol.PRIVATE_ROUND)
                        && content.endsWith(CrazyProtocol.PRIVATE_ROUND)) {
                    String userAndMsg = getRealMsg(content);
                    String user = userAndMsg.split(CrazyProtocol.SPLIT_SIGN)[0];
                    String msg = userAndMsg.split(CrazyProtocol.SPLIT_SIGN)[1];

                    Server.clients.map.get(user).println(Server.clients.getKeyByValue(ps) + "私聊你说：" + msg);
                } //公聊
                else {
                    String msg = getRealMsg(content);
                    for (PrintStream clientPs : Server.clients.valueSet()) {
                        clientPs.println(Server.clients.getKeyByValue(ps) + "说：" + msg);
                    }
                }
            }
         //捕获异常后，表明该 socket 对应的客户端出问题
         // 程序将其对应的数据流从 Map 中删除
        } catch (IOException e) {
            Server.clients.removeByValue(ps);
            System.out.println(Server.clients.map.size());
            try {
                if (br != null) br.close();
                if (ps != null) ps.close();
                if (socket != null) socket.close();
            }catch (IOException ex){
                ex.printStackTrace();
            }
        }
    }

    //将读到的内容去掉前后的协议字符，恢复成真实数据
    private String getRealMsg(String line){
        return line.substring(CrazyProtocol.PROTOCOL_LEN, line.length() - CrazyProtocol.PROTOCOL_LEN);
    }
}
