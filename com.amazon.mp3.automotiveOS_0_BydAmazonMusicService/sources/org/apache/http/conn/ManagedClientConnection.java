package org.apache.http.conn;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.http.HttpClientConnection;
import org.apache.http.HttpHost;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;

/* JADX INFO: loaded from: classes7.dex */
@Deprecated
public interface ManagedClientConnection extends HttpClientConnection, HttpRoutedConnection, ManagedHttpClientConnection, ConnectionReleaseTrigger {
    @Override // org.apache.http.conn.HttpRoutedConnection, org.apache.http.HttpInetConnection
    /* synthetic */ int getLocalPort();

    @Override // org.apache.http.conn.HttpRoutedConnection, org.apache.http.HttpInetConnection
    /* synthetic */ int getRemotePort();

    @Override // org.apache.http.conn.HttpRoutedConnection
    HttpRoute getRoute();

    @Override // org.apache.http.HttpClientConnection, org.apache.http.HttpConnection
    /* synthetic */ int getSocketTimeout();

    void layerProtocol(HttpContext httpContext, HttpParams httpParams) throws IOException;

    void markReusable();

    void open(HttpRoute httpRoute, HttpContext httpContext, HttpParams httpParams) throws IOException;

    void setIdleDuration(long j, TimeUnit timeUnit);

    @Override // org.apache.http.HttpClientConnection, org.apache.http.HttpConnection
    /* synthetic */ void setSocketTimeout(int i);

    void setState(Object obj);

    void tunnelProxy(HttpHost httpHost, boolean z, HttpParams httpParams) throws IOException;

    void tunnelTarget(boolean z, HttpParams httpParams) throws IOException;

    void unmarkReusable();
}
