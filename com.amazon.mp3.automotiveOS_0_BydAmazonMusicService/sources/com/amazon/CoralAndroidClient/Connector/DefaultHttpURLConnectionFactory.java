package com.amazon.CoralAndroidClient.Connector;

import com.amazon.CoralAndroidClient.Exception.NativeException;
import com.google.firebase.perf.network.FirebasePerfUrlConnection;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;

/* JADX INFO: loaded from: classes2.dex */
public class DefaultHttpURLConnectionFactory implements HttpURLConnectionFactory {
    @Override // com.amazon.CoralAndroidClient.Connector.HttpURLConnectionFactory
    public HttpURLConnection create(URL url, Proxy proxy) throws NativeException {
        URLConnection uRLConnection;
        if (url == null) {
            throw new NativeException("url is null");
        }
        try {
            if (proxy != null) {
                uRLConnection = (URLConnection) FirebasePerfUrlConnection.instrument(url.openConnection(proxy));
            } else {
                uRLConnection = (URLConnection) FirebasePerfUrlConnection.instrument(url.openConnection());
            }
            if (!(uRLConnection instanceof HttpURLConnection)) {
                throw new NativeException(String.format("invalid endpoint %s, only HTTP and HTTPS is acceptable", url.toString()));
            }
            return (HttpURLConnection) uRLConnection;
        } catch (IOException e) {
            throw new NativeException(e);
        }
    }
}
