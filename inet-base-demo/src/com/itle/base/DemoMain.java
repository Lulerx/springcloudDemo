package com.itle.base;

import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.net.UnknownHostException;

/**
 * create by Luler on 2023/4/6 15:34
 *
 * @description
 */
public class DemoMain {
    public static void main(String[] args) throws Exception {
        DemoMain main = new DemoMain();
        main.test02();
    }

    public void test01() throws Exception {
        //根据主机名获取 InetAddress 实例
        InetAddress ip = InetAddress.getByName("www.baidu.com");
        System.out.println("baidu 是否可达：" + ip.isReachable(2000));

        System.out.println(ip.getHostAddress());

        //根据原始 IP 获取 InetAddress 实例
        InetAddress local = InetAddress.getByAddress(new byte[]{127,0,0,1});
        System.out.println("本机是否可达：" + local.isReachable(5000));
        //全限定域名
        System.out.println(local.getCanonicalHostName());
    }


    public void test02() throws Exception {
        //将 application/x-www-urlencoded MIME 字符 转换 成普通字符
        String keyWord = URLDecoder.decode("%E7%96%AF%E7%8B%82java", "utf-8");
        System.out.println(keyWord);

        //将普通字符转成 application/x-www-urlencoded MIME 字符
        String urlStr = URLEncoder.encode("疯狂java", "GBK");
        System.out.println(urlStr);
    }

}
