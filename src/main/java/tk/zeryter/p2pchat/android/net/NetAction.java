package tk.zeryter.p2pchat.android.net;

import java.net.DatagramPacket;

/**
 * Owen Holloway
 * ZerytSoft
 * Date: 14/11/13
 */

public interface NetAction {

    public void packetRecieved(DatagramPacket packet);

}