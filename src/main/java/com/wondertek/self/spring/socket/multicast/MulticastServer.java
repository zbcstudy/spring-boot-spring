package com.wondertek.self.spring.socket.multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

/**
 * @Author zbc
 * @Date 21:39-2019/1/9
 */
public class MulticastServer {

    public static void main(String[] args) {
        try {
            InetAddress group = InetAddress.getByName("224.6.7.8");

            MulticastSocket socket = new MulticastSocket();

            for (int i = 0; i < 10; i++) {
                String data = "hello multicast";
                byte[] bytes = data.getBytes();

                socket.send(new DatagramPacket(bytes, bytes.length, group, 8888));
                TimeUnit.SECONDS.sleep(2);

            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
