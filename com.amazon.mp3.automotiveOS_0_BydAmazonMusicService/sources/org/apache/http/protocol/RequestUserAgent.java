package org.apache.http.protocol;

import com.amazonaws.http.HttpHeader;
import java.io.IOException;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.params.HttpParams;
import org.apache.http.util.Args;

/* JADX INFO: loaded from: classes7.dex */
public class RequestUserAgent implements HttpRequestInterceptor {
    private final String userAgent;

    public RequestUserAgent(String str) {
        this.userAgent = str;
    }

    public RequestUserAgent() {
        this(null);
    }

    @Override // org.apache.http.HttpRequestInterceptor
    public void process(HttpRequest httpRequest, HttpContext httpContext) throws HttpException, IOException {
        Args.notNull(httpRequest, "HTTP request");
        if (httpRequest.containsHeader(HttpHeader.USER_AGENT)) {
            return;
        }
        HttpParams params = httpRequest.getParams();
        String str = params != null ? (String) params.getParameter("http.useragent") : null;
        if (str == null) {
            str = this.userAgent;
        }
        if (str != null) {
            httpRequest.addHeader(HttpHeader.USER_AGENT, str);
        }
    }
}
