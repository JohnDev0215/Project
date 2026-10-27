package org.apache.http.conn.params;

import java.net.InetAddress;
import org.apache.http.HttpHost;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.params.HttpParams;
import org.apache.http.util.Args;

/* JADX INFO: loaded from: classes7.dex */
@Deprecated
public class ConnRouteParams {
    public static final HttpHost NO_HOST;
    public static final HttpRoute NO_ROUTE;

    static {
        HttpHost httpHost = new HttpHost("127.0.0.255", 0, "no-host");
        NO_HOST = httpHost;
        NO_ROUTE = new HttpRoute(httpHost);
    }

    private ConnRouteParams() {
    }

    public static HttpHost getDefaultProxy(HttpParams httpParams) {
        Args.notNull(httpParams, "Parameters");
        HttpHost httpHost = (HttpHost) httpParams.getParameter("http.route.default-proxy");
        if (httpHost == null || !NO_HOST.equals(httpHost)) {
            return httpHost;
        }
        return null;
    }

    public static HttpRoute getForcedRoute(HttpParams httpParams) {
        Args.notNull(httpParams, "Parameters");
        HttpRoute httpRoute = (HttpRoute) httpParams.getParameter("http.route.forced-route");
        if (httpRoute == null || !NO_ROUTE.equals(httpRoute)) {
            return httpRoute;
        }
        return null;
    }

    public static InetAddress getLocalAddress(HttpParams httpParams) {
        Args.notNull(httpParams, "Parameters");
        return (InetAddress) httpParams.getParameter("http.route.local-address");
    }
}
