package tk.zeryter.p2pchat.android.net;

import tk.zeryter.p2pchat.android.P2PChatMain;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

/**
 * Owen Holloway
 * ZerytSoft
 * Date: 13/11/13
 */

//TODO comment out entire file properly

public class Network implements Runnable {

    //Inner class for sending packets

    public static class send {

        public static void bytearray(byte[] data, String host, int port) {
            try {

                DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getByName(host), port);

                // Create a datagram socket, sendMessage the packet through it, close it.
                DatagramSocket dsocket = new DatagramSocket();
                dsocket.send(packet);
                dsocket.close();

                //System.out.println("Sent: " + packet.getAddress() + ":" + Crypt.utftostring(packet.getData()));

            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }

    //External calls to class instances

    private static Map<Integer, Network> networkMap = new HashMap<Integer, Network>();

    public static void startListening(Integer port, int PACKETSIZE) {

        networkMap.put(port, new Network());
        networkMap.get(port).port = port;
        networkMap.get(port).PACKETSIZE = PACKETSIZE;

        new Thread(networkMap.get(port)).start();

    }


    public static void setNetAction(int port, NetAction newNetAction) {
        networkMap.get(port).netAction = newNetAction;
    }

    public static void stopListening(Integer port) {
        networkMap.get(port).stop();
    }

    public static void removeNetAction(int port, NetAction netAction) {
        networkMap.get(port).netAction = null;
    }

    //Main body of class instance

    public int port;
    public int PACKETSIZE;

    NetAction netAction;

    boolean listening = true;

    public void run() {

        init();

        while (P2PChatMain.running && listening) {
            loop();
        }

        System.out.println("Stopped listening on port: " + port);

    }

    private DatagramSocket socket = null;

    private void init() {

        //Sets up the new listener for network.
        System.out.println("Starting up on port: " + port);
        try {
            socket = new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        System.out.println("Ready to recieve on " + port + " ....");
    }

    private void loop() {

        DatagramPacket packet = new DatagramPacket(new byte[PACKETSIZE], PACKETSIZE);

        // Receive a packet waits until a packet has been received
        try {
            socket.receive(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] packetData = packet.getData();

        //System.out.println("Received: " + packet.getAddress() + ":" + Crypt.utftostring(packetData));

        netAction.packetRecieved(packet);

    }

    public void stop() {
        listening = false;
    }
}
