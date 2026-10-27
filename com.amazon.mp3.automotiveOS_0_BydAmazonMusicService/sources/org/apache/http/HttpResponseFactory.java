package org.apache.http;

import org.apache.http.protocol.HttpContext;

/* JADX INFO: loaded from: classes7.dex */
public interface HttpResponseFactory {
    HttpResponse newHttpResponse(StatusLine statusLine, HttpContext httpContext);
}
