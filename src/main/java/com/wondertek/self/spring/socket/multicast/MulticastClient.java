package com.wondertek.self.spring.socket.multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

/**
 * @Author zbc
 * @Date 21:44-2019/1/9
 */
public class MulticastClient {

    public static void main(String[] args) {
        try {
            InetAddress group = InetAddress.getByName("224.6.7.8");
            MulticastSocket socket = new MulticastSocket(8888);
            socket.joinGroup(group);

            byte[] bytes = new byte[256];
            while (true) {
                DatagramPacket packet = new DatagramPacket(bytes, bytes.length);
                socket.receive(packet);

                System.out.println(new String(packet.getData()));

            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
