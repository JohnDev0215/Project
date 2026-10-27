package org.apache.http;

import java.io.IOException;

/* JADX INFO: loaded from: classes7.dex */
public interface HttpClientConnection extends HttpConnection {
    void flush() throws IOException;

    @Override // org.apache.http.HttpConnection
    /* synthetic */ int getSocketTimeout();

    boolean isResponseAvailable(int i) throws IOException;

    void receiveResponseEntity(HttpResponse httpResponse) throws HttpException, IOException;

    HttpResponse receiveResponseHeader() throws HttpException, IOException;

    void sendRequestEntity(HttpEntityEnclosingRequest httpEntityEnclosingRequest) throws HttpException, IOException;

    void sendRequestHeader(HttpRequest httpRequest) throws HttpException, IOException;

    @Override // org.apache.http.HttpConnection
    /* synthetic */ void setSocketTimeout(int i);
}
