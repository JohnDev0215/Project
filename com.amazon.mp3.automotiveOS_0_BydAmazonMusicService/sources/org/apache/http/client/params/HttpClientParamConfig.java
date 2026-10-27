package org.apache.http.client.params;

import java.net.InetAddress;
import java.util.Collection;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.params.HttpParams;

/* JADX INFO: loaded from: classes7.dex */
@Deprecated
public final class HttpClientParamConfig {
    private HttpClientParamConfig() {
    }

    public static RequestConfig getRequestConfig(HttpParams httpParams) {
        return getRequestConfig(httpParams, RequestConfig.DEFAULT);
    }

    public static RequestConfig getRequestConfig(HttpParams httpParams, RequestConfig requestConfig) {
        RequestConfig.Builder relativeRedirectsAllowed = RequestConfig.copy(requestConfig).setSocketTimeout(httpParams.getIntParameter("http.socket.timeout", requestConfig.getSocketTimeout())).setStaleConnectionCheckEnabled(httpParams.getBooleanParameter("http.connection.stalecheck", requestConfig.isStaleConnectionCheckEnabled())).setConnectTimeout(httpParams.getIntParameter("http.connection.timeout", requestConfig.getConnectTimeout())).setExpectContinueEnabled(httpParams.getBooleanParameter("http.protocol.expect-continue", requestConfig.isExpectContinueEnabled())).setAuthenticationEnabled(httpParams.getBooleanParameter("http.protocol.handle-authentication", requestConfig.isAuthenticationEnabled())).setCircularRedirectsAllowed(httpParams.getBooleanParameter("http.protocol.allow-circular-redirects", requestConfig.isCircularRedirectsAllowed())).setConnectionRequestTimeout((int) httpParams.getLongParameter("http.conn-manager.timeout", requestConfig.getConnectionRequestTimeout())).setMaxRedirects(httpParams.getIntParameter("http.protocol.max-redirects", requestConfig.getMaxRedirects())).setRedirectsEnabled(httpParams.getBooleanParameter("http.protocol.handle-redirects", requestConfig.isRedirectsEnabled())).setRelativeRedirectsAllowed(!httpParams.getBooleanParameter("http.protocol.reject-relative-redirect", !requestConfig.isRelativeRedirectsAllowed()));
        HttpHost httpHost = (HttpHost) httpParams.getParameter("http.route.default-proxy");
        if (httpHost != null) {
            relativeRedirectsAllowed.setProxy(httpHost);
        }
        InetAddress inetAddress = (InetAddress) httpParams.getParameter("http.route.local-address");
        if (inetAddress != null) {
            relativeRedirectsAllowed.setLocalAddress(inetAddress);
        }
        Collection<String> collection = (Collection) httpParams.getParameter("http.auth.target-scheme-pref");
        if (collection != null) {
            relativeRedirectsAllowed.setTargetPreferredAuthSchemes(collection);
        }
        Collection<String> collection2 = (Collection) httpParams.getParameter("http.auth.proxy-scheme-pref");
        if (collection2 != null) {
            relativeRedirectsAllowed.setProxyPreferredAuthSchemes(collection2);
        }
        String str = (String) httpParams.getParameter("http.protocol.cookie-policy");
        if (str != null) {
            relativeRedirectsAllowed.setCookieSpec(str);
        }
        return relativeRedirectsAllowed.build();
    }
}
