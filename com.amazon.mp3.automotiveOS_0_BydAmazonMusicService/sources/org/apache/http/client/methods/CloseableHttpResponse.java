package org.apache.http.client.methods;

import java.io.Closeable;
import org.apache.http.HttpResponse;

/* JADX INFO: loaded from: classes7.dex */
public interface CloseableHttpResponse extends HttpResponse, Closeable {
    @Override // org.apache.http.HttpResponse
    /* synthetic */ void setStatusCode(int i) throws IllegalStateException;
}
