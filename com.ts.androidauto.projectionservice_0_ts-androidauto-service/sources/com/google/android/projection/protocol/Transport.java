package com.google.android.projection.protocol;

import java.io.IOException;
import java.net.SocketException;

/* JADX INFO: loaded from: classes.dex */
public abstract class Transport implements ConnectionConfigurationCallbacks {

    public enum StopReason {
        CONNECTION_TERMINATED,
        MD_BYEBYE,
        HU_BYEBYE,
        USER_SWITCH,
        PING_TIMEOUT,
        PING_RTT_TOO_HIGH,
        CONNECTION_DISCONNECTED,
        CONNECTION_ERROR
    }

    public abstract int read(byte[] bArr, int i, int i2) throws IOException;

    public abstract void stopTransport(StopReason stopReason);

    public abstract void write(byte[] bArr, int i, int i2) throws IOException;

    @Override // com.google.android.projection.protocol.ConnectionConfigurationCallbacks
    public int onRetrieveTcpReceiveBufferSize() throws SocketException {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override // com.google.android.projection.protocol.ConnectionConfigurationCallbacks
    public int onRetrieveTcpReadTimeout() throws SocketException {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override // com.google.android.projection.protocol.ConnectionConfigurationCallbacks
    public int onRetrieveTcpSendBufferSize() throws SocketException {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override // com.google.android.projection.protocol.ConnectionConfigurationCallbacks
    public void onApplyTcpReadTimout(int readTimeout) throws SocketException {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override // com.google.android.projection.protocol.ConnectionConfigurationCallbacks
    public void onApplyTcpSendBufferSize(int sendBufferSize) throws SocketException {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override // com.google.android.projection.protocol.ConnectionConfigurationCallbacks
    public void onApplyTcpReceiveBufferSize(int receiveBufferSize) throws SocketException {
        throw new UnsupportedOperationException("Not implemented");
    }
}
