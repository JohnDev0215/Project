package org.apache.http.conn;

import org.apache.http.HttpInetConnection;
import org.apache.http.conn.routing.HttpRoute;

/* JADX INFO: loaded from: classes7.dex */
@Deprecated
public interface HttpRoutedConnection extends HttpInetConnection {
    @Override // org.apache.http.HttpInetConnection
    /* synthetic */ int getLocalPort();

    @Override // org.apache.http.HttpInetConnection
    /* synthetic */ int getRemotePort();

    HttpRoute getRoute();

    @Override // org.apache.http.HttpInetConnection, org.apache.http.HttpConnection
    /* synthetic */ int getSocketTimeout();

    @Override // org.apache.http.HttpInetConnection, org.apache.http.HttpConnection
    /* synthetic */ void setSocketTimeout(int i);
}
