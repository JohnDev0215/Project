package org.apache.http;

import org.apache.http.protocol.HttpContext;

/* JADX INFO: loaded from: classes7.dex */
public interface ConnectionReuseStrategy {
    boolean keepAlive(HttpResponse httpResponse, HttpContext httpContext);
}
