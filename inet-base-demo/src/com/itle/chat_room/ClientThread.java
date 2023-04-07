package com.itle.chat_room;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * create by Luler on 2023/4/7 16:03
 *
 * @description
 */
public class ClientThread extends Thread {
    BufferedReader br = null;

    public ClientThread(BufferedReader br) {
        this.br = br;
    }

    public void run(){
        try {
            String line = null;
            while ((line = br.readLine()) != null){
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (br != null) br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
