package com.android.bluetooth.opp;

import android.util.Log;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.obex.Authenticator;
import javax.obex.ServerRequestHandler;
import javax.obex.ServerSession;

/* JADX INFO: compiled from: TestActivity.java */
/* JADX INFO: loaded from: classes.dex */
class TestTcpSessionNotifier {
    private static final String TAG = "TestTcpSessionNotifier";
    Socket mConn = null;
    ServerSocket mServer;

    TestTcpSessionNotifier(int port) throws IOException {
        this.mServer = null;
        this.mServer = new ServerSocket(port);
    }

    public ServerSession acceptAndOpen(ServerRequestHandler handler, Authenticator auth) throws IOException {
        try {
            this.mConn = this.mServer.accept();
        } catch (Exception e) {
            Log.v(TAG, "ex");
        }
        TestTcpTransport tt = new TestTcpTransport(this.mConn);
        return new ServerSession(tt, handler, auth);
    }

    public ServerSession acceptAndOpen(ServerRequestHandler handler) throws IOException {
        return acceptAndOpen(handler, null);
    }
}
