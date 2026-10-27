package org.apache.http.client.params;

import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.Args;

/* JADX INFO: loaded from: classes7.dex */
@Deprecated
public class HttpClientParams {
    private HttpClientParams() {
    }

    public static boolean isRedirecting(HttpParams httpParams) {
        Args.notNull(httpParams, "HTTP parameters");
        return httpParams.getBooleanParameter("http.protocol.handle-redirects", true);
    }

    public static boolean isAuthenticating(HttpParams httpParams) {
        Args.notNull(httpParams, "HTTP parameters");
        return httpParams.getBooleanParameter("http.protocol.handle-authentication", true);
    }

    public static long getConnectionManagerTimeout(HttpParams httpParams) {
        Args.notNull(httpParams, "HTTP parameters");
        Long l = (Long) httpParams.getParameter("http.conn-manager.timeout");
        if (l != null) {
            return l.longValue();
        }
        return HttpConnectionParams.getConnectionTimeout(httpParams);
    }
}
