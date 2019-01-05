package com.wondertek.self.spring.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.*;

/**
 * @Author zbc
 * @Date 21:40-2019/1/5
 */
public class SocketServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(2000);

        System.out.println("服务器准备就绪");
        System.out.println("服务端信息：" + serverSocket.getInetAddress() + "P: " + serverSocket.getLocalPort());

        //等待客户端连接
       for (;;) {
           Socket client = serverSocket.accept();
           ClientHandler clientHandler = new ClientHandler(client);
           //启动线程
           clientHandler.start();
       }


    }

    private static class ClientHandler extends Thread {
        private Socket socket;
        private boolean flag = true;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            super.run();
            System.out.println("新客户端连接：" + socket.getInetAddress() + "P: " + socket.getPort());

            try {
                //得到打印流，用于数据输出，服务器回送数据
                PrintStream socketOutput = new PrintStream(socket.getOutputStream());

                //得到输入流，用于接受数据
                BufferedReader socketInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                do {
                    String line = socketInput.readLine();
                    if ("bye".equalsIgnoreCase(line)) {
                        flag = false;
                        socketOutput.println("bye");
                    } else {
                        System.out.println(line);
                        socketOutput.println("回送：" + line.length());
                    }
                } while (flag);
                socketInput.close();
                socketOutput.close();
            } catch (Exception e) {
                System.out.println("连接异常断开");
            }finally {
                try {
                    //连接关闭
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("客户端关闭：" + socket.getInetAddress() + "P: " + socket.getPort());
        }


    }
}
