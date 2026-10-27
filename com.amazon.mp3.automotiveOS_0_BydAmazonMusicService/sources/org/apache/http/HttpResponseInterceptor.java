package org.apache.http;

import java.io.IOException;
import org.apache.http.protocol.HttpContext;

/* JADX INFO: loaded from: classes7.dex */
public interface HttpResponseInterceptor {
    void process(HttpResponse httpResponse, HttpContext httpContext) throws HttpException, IOException;
}
