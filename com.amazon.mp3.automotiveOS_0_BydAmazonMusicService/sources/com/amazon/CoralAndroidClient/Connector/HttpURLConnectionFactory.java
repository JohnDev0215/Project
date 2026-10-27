package com.amazon.CoralAndroidClient.Connector;

import com.amazon.CoralAndroidClient.Exception.NativeException;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;

/* JADX INFO: loaded from: classes2.dex */
public interface HttpURLConnectionFactory {
    HttpURLConnection create(URL url, Proxy proxy) throws NativeException;
}
