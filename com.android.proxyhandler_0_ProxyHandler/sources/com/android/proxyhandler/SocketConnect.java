package com.android.proxyhandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/* JADX INFO: loaded from: classes.dex */
public class SocketConnect extends Thread {
    private InputStream from;
    private OutputStream to;

    public SocketConnect(Socket socket, Socket socket2) throws IOException {
        this.from = socket.getInputStream();
        this.to = socket2.getOutputStream();
        start();
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        byte[] bArr = new byte[512];
        while (true) {
            try {
                int i = this.from.read(bArr);
                if (i >= 0) {
                    this.to.write(bArr, 0, i);
                } else {
                    this.from.close();
                    this.to.close();
                    return;
                }
            } catch (IOException unused) {
                return;
            }
        }
    }

    public static void connect(Socket socket, Socket socket2) {
        try {
            SocketConnect socketConnect = new SocketConnect(socket, socket2);
            SocketConnect socketConnect2 = new SocketConnect(socket2, socket);
            try {
                socketConnect.join();
            } catch (InterruptedException unused) {
            }
            try {
                socketConnect2.join();
            } catch (InterruptedException unused2) {
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
