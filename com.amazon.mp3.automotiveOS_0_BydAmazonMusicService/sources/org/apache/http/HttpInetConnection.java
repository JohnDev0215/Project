package org.apache.http;

import java.net.InetAddress;

/* JADX INFO: loaded from: classes7.dex */
public interface HttpInetConnection extends HttpConnection {
    int getLocalPort();

    InetAddress getRemoteAddress();

    int getRemotePort();

    @Override // org.apache.http.HttpConnection
    /* synthetic */ int getSocketTimeout();

    @Override // org.apache.http.HttpConnection
    /* synthetic */ void setSocketTimeout(int i);
}
