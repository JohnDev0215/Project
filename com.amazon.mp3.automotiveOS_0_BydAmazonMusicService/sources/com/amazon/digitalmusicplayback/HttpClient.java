package com.amazon.digitalmusicplayback;

/* JADX INFO: loaded from: classes2.dex */
public abstract class HttpClient {
    public abstract void cancel(String str);

    public abstract void sendRequest(HttpRequest httpRequest, HttpResponseHandlerWrapper httpResponseHandlerWrapper);

    public abstract void setTimeout(int i);

    public abstract void shutdown(int i);
}
