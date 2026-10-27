package org.apache.http.conn;

import java.io.InterruptedIOException;
import org.apache.http.HttpHost;

/* JADX INFO: loaded from: classes7.dex */
public class ConnectTimeoutException extends InterruptedIOException {
    private final HttpHost host;

    public ConnectTimeoutException() {
        this.host = null;
    }

    public ConnectTimeoutException(String str) {
        super(str);
        this.host = null;
    }
}
