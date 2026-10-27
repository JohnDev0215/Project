package org.apache.http.impl.client;

import org.apache.http.HttpException;
import org.apache.http.HttpResponse;

/* JADX INFO: loaded from: classes7.dex */
@Deprecated
public class TunnelRefusedException extends HttpException {
    private final HttpResponse response;

    public TunnelRefusedException(String str, HttpResponse httpResponse) {
        super(str);
        this.response = httpResponse;
    }

    public HttpResponse getResponse() {
        return this.response;
    }
}
