package org.apache.http.params;

import org.apache.http.HttpVersion;
import org.apache.http.ProtocolVersion;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.Args;

/* JADX INFO: loaded from: classes7.dex */
@Deprecated
public final class HttpProtocolParams {
    private HttpProtocolParams() {
    }

    public static String getHttpElementCharset(HttpParams httpParams) {
        Args.notNull(httpParams, "HTTP parameters");
        String str = (String) httpParams.getParameter("http.protocol.element-charset");
        return str == null ? HTTP.DEF_PROTOCOL_CHARSET.name() : str;
    }

    public static void setContentCharset(HttpParams httpParams, String str) {
        Args.notNull(httpParams, "HTTP parameters");
        httpParams.setParameter("http.protocol.content-charset", str);
    }

    public static ProtocolVersion getVersion(HttpParams httpParams) {
        Args.notNull(httpParams, "HTTP parameters");
        Object parameter = httpParams.getParameter("http.protocol.version");
        if (parameter == null) {
            return HttpVersion.HTTP_1_1;
        }
        return (ProtocolVersion) parameter;
    }

    public static void setVersion(HttpParams httpParams, ProtocolVersion protocolVersion) {
        Args.notNull(httpParams, "HTTP parameters");
        httpParams.setParameter("http.protocol.version", protocolVersion);
    }

    public static void setUserAgent(HttpParams httpParams, String str) {
        Args.notNull(httpParams, "HTTP parameters");
        httpParams.setParameter("http.useragent", str);
    }
}
