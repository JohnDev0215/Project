package com.amazonaws.mobileconnectors.iot;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import javax.net.ssl.SSLSocketFactory;

/* JADX INFO: loaded from: classes5.dex */
public class AWSIotProxiedSocketFactory extends SSLSocketFactory {
    private final Proxy proxy;
    private final SSLSocketFactory socketFactory;

    public AWSIotProxiedSocketFactory(SSLSocketFactory sSLSocketFactory, String str, int i) {
        this.socketFactory = sSLSocketFactory;
        this.proxy = new Proxy(Proxy.Type.SOCKS, new InetSocketAddress(str, i));
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getDefaultCipherSuites() {
        return this.socketFactory.getDefaultCipherSuites();
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getSupportedCipherSuites() {
        return this.socketFactory.getSupportedCipherSuites();
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket() {
        return new AWSIotProxiedSSLSocket(this.socketFactory, new Socket(this.proxy));
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException {
        return this.socketFactory.createSocket(socket, str, i, z);
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i) throws IOException {
        return this.socketFactory.createSocket(str, i);
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i, InetAddress inetAddress, int i2) throws IOException {
        return this.socketFactory.createSocket(str, i, inetAddress, i2);
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i) throws IOException {
        return this.socketFactory.createSocket(inetAddress, i);
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) throws IOException {
        return this.socketFactory.createSocket(inetAddress, i, inetAddress2, i2);
    }
}
