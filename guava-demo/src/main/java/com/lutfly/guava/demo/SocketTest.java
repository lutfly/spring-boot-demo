package com.lutfly.guava.demo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class SocketTest {
    public static void main(String[] args) throws IOException {
        // 要连接的服务端IP地址和端口
        String host = "127.0.0.1";
        int port = 55533;
        // 与服务端建立连接
        Socket socket = new Socket(host, port);
        OutputStream outputStream = socket.getOutputStream();

        for (int i = 0; i < 10; i++) {
            String message = "hello"+i;
            byte[] sendBytes = message.getBytes("UTF-8");
            outputStream.write(sendBytes.length >>8);
            outputStream.write(sendBytes.length);
            //然后将消息再次发送出去
            outputStream.write(sendBytes);
            outputStream.flush();
            System.out.println("aaa");
            outputStream.close();
        }

        socket.close();
    }
}
