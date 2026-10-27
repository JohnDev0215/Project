package org.apache.http.protocol;

import org.apache.http.HttpConnection;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.util.Args;

/* JADX INFO: loaded from: classes7.dex */
public class HttpCoreContext implements HttpContext {
    private final HttpContext context;

    public static HttpCoreContext adapt(HttpContext httpContext) {
        Args.notNull(httpContext, "HTTP context");
        if (httpContext instanceof HttpCoreContext) {
            return (HttpCoreContext) httpContext;
        }
        return new HttpCoreContext(httpContext);
    }

    public HttpCoreContext(HttpContext httpContext) {
        this.context = httpContext;
    }

    public HttpCoreContext() {
        this.context = new BasicHttpContext();
    }

    @Override // org.apache.http.protocol.HttpContext
    public Object getAttribute(String str) {
        return this.context.getAttribute(str);
    }

    @Override // org.apache.http.protocol.HttpContext
    public void setAttribute(String str, Object obj) {
        this.context.setAttribute(str, obj);
    }

    public <T> T getAttribute(String str, Class<T> cls) {
        Args.notNull(cls, "Attribute class");
        Object attribute = getAttribute(str);
        if (attribute == null) {
            return null;
        }
        return cls.cast(attribute);
    }

    public HttpConnection getConnection() {
        return (HttpConnection) getAttribute("http.connection", HttpConnection.class);
    }

    public HttpRequest getRequest() {
        return (HttpRequest) getAttribute("http.request", HttpRequest.class);
    }

    public boolean isRequestSent() {
        Boolean bool = (Boolean) getAttribute("http.request_sent", Boolean.class);
        return bool != null && bool.booleanValue();
    }

    public HttpHost getTargetHost() {
        return (HttpHost) getAttribute("http.target_host", HttpHost.class);
    }
}
