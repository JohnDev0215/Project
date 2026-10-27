package com.google.android.projection.protocol;

import java.net.SocketException;

/* JADX INFO: loaded from: classes.dex */
public interface ConnectionConfigurationCallbacks extends NativeCallback {
    void onApplyTcpReadTimout(int i) throws SocketException;

    void onApplyTcpReceiveBufferSize(int i) throws SocketException;

    void onApplyTcpSendBufferSize(int i) throws SocketException;

    int onRetrieveTcpReadTimeout() throws SocketException;

    int onRetrieveTcpReceiveBufferSize() throws SocketException;

    int onRetrieveTcpSendBufferSize() throws SocketException;
}
