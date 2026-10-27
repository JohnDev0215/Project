package org.apache.http.conn;

import javax.net.ssl.SSLSession;
import org.apache.http.HttpClientConnection;
import org.apache.http.HttpInetConnection;

/* JADX INFO: loaded from: classes7.dex */
public interface ManagedHttpClientConnection extends HttpClientConnection, HttpInetConnection {
    @Override // org.apache.http.HttpInetConnection
    /* synthetic */ int getLocalPort();

    @Override // org.apache.http.HttpInetConnection
    /* synthetic */ int getRemotePort();

    SSLSession getSSLSession();

    @Override // org.apache.http.HttpClientConnection, org.apache.http.HttpConnection
    /* synthetic */ int getSocketTimeout();

    @Override // org.apache.http.HttpClientConnection, org.apache.http.HttpConnection
    /* synthetic */ void setSocketTimeout(int i);
}
