package org.apache.http.conn;

import java.io.IOException;
import java.net.Socket;
import org.apache.http.HttpClientConnection;
import org.apache.http.HttpHost;
import org.apache.http.HttpInetConnection;
import org.apache.http.params.HttpParams;

/* JADX INFO: loaded from: classes7.dex */
@Deprecated
public interface OperatedClientConnection extends HttpClientConnection, HttpInetConnection {
    @Override // org.apache.http.HttpInetConnection
    /* synthetic */ int getLocalPort();

    @Override // org.apache.http.HttpInetConnection
    /* synthetic */ int getRemotePort();

    Socket getSocket();

    @Override // org.apache.http.HttpClientConnection, org.apache.http.HttpConnection
    /* synthetic */ int getSocketTimeout();

    boolean isSecure();

    void openCompleted(boolean z, HttpParams httpParams) throws IOException;

    void opening(Socket socket, HttpHost httpHost) throws IOException;

    @Override // org.apache.http.HttpClientConnection, org.apache.http.HttpConnection
    /* synthetic */ void setSocketTimeout(int i);

    void update(Socket socket, HttpHost httpHost, boolean z, HttpParams httpParams) throws IOException;
}
