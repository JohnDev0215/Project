package org.apache.http.client.protocol;

import com.google.firebase.perf.FirebasePerformance;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.Args;

/* JADX INFO: loaded from: classes7.dex */
public class RequestDefaultHeaders implements HttpRequestInterceptor {
    private final Collection<? extends Header> defaultHeaders;

    public RequestDefaultHeaders(Collection<? extends Header> collection) {
        this.defaultHeaders = collection;
    }

    public RequestDefaultHeaders() {
        this(null);
    }

    @Override // org.apache.http.HttpRequestInterceptor
    public void process(HttpRequest httpRequest, HttpContext httpContext) throws HttpException, IOException {
        Args.notNull(httpRequest, "HTTP request");
        if (httpRequest.getRequestLine().getMethod().equalsIgnoreCase(FirebasePerformance.HttpMethod.CONNECT)) {
            return;
        }
        Collection<? extends Header> collection = (Collection) httpRequest.getParams().getParameter("http.default-headers");
        if (collection == null) {
            collection = this.defaultHeaders;
        }
        if (collection != null) {
            Iterator<? extends Header> it = collection.iterator();
            while (it.hasNext()) {
                httpRequest.addHeader(it.next());
            }
        }
    }
}
