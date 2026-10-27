package com.amazon.identity.auth.device;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import javax.net.ssl.SSLSocketFactory;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class n6 extends SSLSocketFactory {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final SSLSocketFactory f458a;

    public n6(SSLSocketFactory sSLSocketFactory) {
        this.f458a = sSLSocketFactory;
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException {
        Socket socketCreateSocket = this.f458a.createSocket(socket, str, i, z);
        g6.a(socketCreateSocket);
        return socketCreateSocket;
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getDefaultCipherSuites() {
        return this.f458a.getDefaultCipherSuites();
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getSupportedCipherSuites() {
        return this.f458a.getSupportedCipherSuites();
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i) throws IOException {
        Socket socketCreateSocket = this.f458a.createSocket(str, i);
        g6.a(socketCreateSocket);
        return socketCreateSocket;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i) throws IOException {
        Socket socketCreateSocket = this.f458a.createSocket(inetAddress, i);
        g6.a(socketCreateSocket);
        return socketCreateSocket;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i, InetAddress inetAddress, int i2) throws IOException {
        Socket socketCreateSocket = this.f458a.createSocket(str, i, inetAddress, i2);
        g6.a(socketCreateSocket);
        return socketCreateSocket;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) throws IOException {
        Socket socketCreateSocket = this.f458a.createSocket(inetAddress, i, inetAddress2, i2);
        g6.a(socketCreateSocket);
        return socketCreateSocket;
    }
}
