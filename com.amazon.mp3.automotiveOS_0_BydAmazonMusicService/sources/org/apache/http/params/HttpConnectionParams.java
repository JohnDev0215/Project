package org.apache.http.params;

import org.apache.http.util.Args;

/* JADX INFO: loaded from: classes7.dex */
@Deprecated
public final class HttpConnectionParams {
    private HttpConnectionParams() {
    }

    public static int getSoTimeout(HttpParams httpParams) {
        Args.notNull(httpParams, "HTTP parameters");
        return httpParams.getIntParameter("http.socket.timeout", 0);
    }

    public static boolean getSoReuseaddr(HttpParams httpParams) {
        Args.notNull(httpParams, "HTTP parameters");
        return httpParams.getBooleanParameter("http.socket.reuseaddr", false);
    }

    public static boolean getTcpNoDelay(HttpParams httpParams) {
        Args.notNull(httpParams, "HTTP parameters");
        return httpParams.getBooleanParameter("http.tcp.nodelay", true);
    }

    public static void setTcpNoDelay(HttpParams httpParams, boolean z) {
        Args.notNull(httpParams, "HTTP parameters");
        httpParams.setBooleanParameter("http.tcp.nodelay", z);
    }

    public static void setSocketBufferSize(HttpParams httpParams, int i) {
        Args.notNull(httpParams, "HTTP parameters");
        httpParams.setIntParameter("http.socket.buffer-size", i);
    }

    public static int getLinger(HttpParams httpParams) {
        Args.notNull(httpParams, "HTTP parameters");
        return httpParams.getIntParameter("http.socket.linger", -1);
    }

    public static int getConnectionTimeout(HttpParams httpParams) {
        Args.notNull(httpParams, "HTTP parameters");
        return httpParams.getIntParameter("http.connection.timeout", 0);
    }

    public static boolean isStaleCheckingEnabled(HttpParams httpParams) {
        Args.notNull(httpParams, "HTTP parameters");
        return httpParams.getBooleanParameter("http.connection.stalecheck", true);
    }
}
