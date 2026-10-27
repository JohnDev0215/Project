package org.apache.http.client;

import java.io.IOException;
import org.apache.http.protocol.HttpContext;

/* JADX INFO: loaded from: classes7.dex */
public interface HttpRequestRetryHandler {
    boolean retryRequest(IOException iOException, int i, HttpContext httpContext);
}
