package org.apache.commons.net.ftp;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import javax.net.ServerSocketFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;

/* JADX INFO: loaded from: classes3.dex */
public class FTPSServerSocketFactory extends ServerSocketFactory {
    private final SSLContext sslContext;

    public FTPSServerSocketFactory(SSLContext sslContext) {
        this.sslContext = sslContext;
    }

    @Override // javax.net.ServerSocketFactory
    public ServerSocket createServerSocket() throws IOException {
        return init(getServerSocketFactory().createServerSocket());
    }

    @Override // javax.net.ServerSocketFactory
    public ServerSocket createServerSocket(int port) throws IOException {
        return init(getServerSocketFactory().createServerSocket(port));
    }

    @Override // javax.net.ServerSocketFactory
    public ServerSocket createServerSocket(int port, int backlog) throws IOException {
        return init(getServerSocketFactory().createServerSocket(port, backlog));
    }

    @Override // javax.net.ServerSocketFactory
    public ServerSocket createServerSocket(int port, int backlog, InetAddress ifAddress) throws IOException {
        return init(getServerSocketFactory().createServerSocket(port, backlog, ifAddress));
    }

    private SSLServerSocketFactory getServerSocketFactory() {
        return this.sslContext.getServerSocketFactory();
    }

    public ServerSocket init(ServerSocket socket) {
        ((SSLServerSocket) socket).setUseClientMode(true);
        return socket;
    }
}
