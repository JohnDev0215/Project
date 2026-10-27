package com.google.android.projection.transport.net;

import android.util.Log;
import com.google.android.projection.protocol.Transport;
import com.google.android.projection.transport.StreamTransport;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

/* JADX INFO: loaded from: classes.dex */
public class TcpTransport extends StreamTransport {
    private static final String TAG = "AHU.TCP_CONNECTION";
    private final Socket mSocket;

    public TcpTransport(Socket socket) throws IOException {
        super(socket.getInputStream(), socket.getOutputStream());
        this.mSocket = socket;
    }

    public Socket getTcpSocket() {
        Socket socket = this.mSocket;
        if (socket != null) {
            return socket;
        }
        return null;
    }

    @Override // com.google.android.projection.transport.StreamTransport, com.google.android.projection.protocol.Transport
    public void stopTransport(Transport.StopReason reason) {
        super.stopTransport(reason);
        try {
            this.mSocket.close();
        } catch (IOException e) {
            Log.d("AHU.TCP_CONNECTION", "Can't close socket: " + e);
        }
    }

    @Override // com.google.android.projection.protocol.Transport, com.google.android.projection.protocol.ConnectionConfigurationCallbacks
    public int onRetrieveTcpReceiveBufferSize() throws SocketException {
        return this.mSocket.getReceiveBufferSize();
    }

    @Override // com.google.android.projection.protocol.Transport, com.google.android.projection.protocol.ConnectionConfigurationCallbacks
    public int onRetrieveTcpReadTimeout() throws SocketException {
        return this.mSocket.getSoTimeout();
    }

    @Override // com.google.android.projection.protocol.Transport, com.google.android.projection.protocol.ConnectionConfigurationCallbacks
    public int onRetrieveTcpSendBufferSize() throws SocketException {
        return this.mSocket.getSendBufferSize();
    }

    @Override // com.google.android.projection.protocol.Transport, com.google.android.projection.protocol.ConnectionConfigurationCallbacks
    public void onApplyTcpReadTimout(int readTimeout) throws SocketException {
        this.mSocket.setSoTimeout(readTimeout);
    }

    @Override // com.google.android.projection.protocol.Transport, com.google.android.projection.protocol.ConnectionConfigurationCallbacks
    public void onApplyTcpSendBufferSize(int sendBufferSize) throws SocketException {
        this.mSocket.setSendBufferSize(sendBufferSize);
    }

    @Override // com.google.android.projection.protocol.Transport, com.google.android.projection.protocol.ConnectionConfigurationCallbacks
    public void onApplyTcpReceiveBufferSize(int receiveBufferSize) throws SocketException {
        this.mSocket.setReceiveBufferSize(receiveBufferSize);
    }
}
