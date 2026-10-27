package com.amazonaws.http;

import java.io.IOException;

/* JADX INFO: loaded from: classes5.dex */
public interface HttpClient {
    HttpResponse execute(HttpRequest httpRequest) throws IOException;

    void shutdown();
}
