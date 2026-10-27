package org.apache.http.conn.scheme;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.Args;

/* JADX INFO: loaded from: classes7.dex */
@Deprecated
public class PlainSocketFactory implements SchemeSocketFactory {
    private final HostNameResolver nameResolver = null;

    @Override // org.apache.http.conn.scheme.SchemeSocketFactory
    public final boolean isSecure(Socket socket) {
        return false;
    }

    public static PlainSocketFactory getSocketFactory() {
        return new PlainSocketFactory();
    }

    @Override // org.apache.http.conn.scheme.SchemeSocketFactory
    public Socket createSocket(HttpParams httpParams) {
        return new Socket();
    }

    public Socket createSocket() {
        return new Socket();
    }

    @Override // org.apache.http.conn.scheme.SchemeSocketFactory
    public Socket connectSocket(Socket socket, InetSocketAddress inetSocketAddress, InetSocketAddress inetSocketAddress2, HttpParams httpParams) throws IOException {
        Args.notNull(inetSocketAddress, "Remote address");
        Args.notNull(httpParams, "HTTP parameters");
        if (socket == null) {
            socket = createSocket();
        }
        if (inetSocketAddress2 != null) {
            socket.setReuseAddress(HttpConnectionParams.getSoReuseaddr(httpParams));
            socket.bind(inetSocketAddress2);
        }
        int connectionTimeout = HttpConnectionParams.getConnectionTimeout(httpParams);
        try {
            socket.setSoTimeout(HttpConnectionParams.getSoTimeout(httpParams));
            socket.connect(inetSocketAddress, connectionTimeout);
            return socket;
        } catch (SocketTimeoutException unused) {
            throw new ConnectTimeoutException("Connect to " + inetSocketAddress + " timed out");
        }
    }
}
