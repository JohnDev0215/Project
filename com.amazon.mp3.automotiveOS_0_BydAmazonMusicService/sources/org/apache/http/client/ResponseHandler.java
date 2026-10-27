package org.apache.http.client;

import java.io.IOException;
import org.apache.http.HttpResponse;

/* JADX INFO: loaded from: classes7.dex */
public interface ResponseHandler<T> {
    T handleResponse(HttpResponse httpResponse) throws IOException;
}
