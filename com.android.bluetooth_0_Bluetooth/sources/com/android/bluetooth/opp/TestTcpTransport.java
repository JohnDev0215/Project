package com.android.bluetooth.opp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import javax.obex.ObexTransport;

/* JADX INFO: compiled from: TestActivity.java */
/* JADX INFO: loaded from: classes.dex */
class TestTcpTransport implements ObexTransport {
    Socket mSocket;

    TestTcpTransport(Socket s) {
        this.mSocket = null;
        this.mSocket = s;
    }

    public void close() throws IOException {
        this.mSocket.close();
    }

    public DataInputStream openDataInputStream() throws IOException {
        return new DataInputStream(openInputStream());
    }

    public DataOutputStream openDataOutputStream() throws IOException {
        return new DataOutputStream(openOutputStream());
    }

    public InputStream openInputStream() throws IOException {
        return this.mSocket.getInputStream();
    }

    public OutputStream openOutputStream() throws IOException {
        return this.mSocket.getOutputStream();
    }

    public void connect() throws IOException {
    }

    public void create() throws IOException {
    }

    public void disconnect() throws IOException {
    }

    public void listen() throws IOException {
    }

    public boolean isConnected() throws IOException {
        return this.mSocket.isConnected();
    }

    public int getMaxTransmitPacketSize() {
        return -1;
    }

    public int getMaxReceivePacketSize() {
        return -1;
    }

    public boolean isSrmSupported() {
        return false;
    }
}
