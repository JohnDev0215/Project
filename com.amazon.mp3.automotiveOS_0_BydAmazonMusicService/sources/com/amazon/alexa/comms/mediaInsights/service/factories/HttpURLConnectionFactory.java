package com.amazon.alexa.comms.mediaInsights.service.factories;

import com.google.firebase.perf.network.FirebasePerfUrlConnection;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import javax.net.ssl.HttpsURLConnection;
import lombok.NonNull;

/* JADX INFO: loaded from: classes2.dex */
public class HttpURLConnectionFactory {
    public HttpsURLConnection create(@NonNull String str) throws IOException {
        if (str == null) {
            throw new IllegalArgumentException("httpsEndpoint is null");
        }
        return (HttpsURLConnection) ((URLConnection) FirebasePerfUrlConnection.instrument(new URL(str).openConnection()));
    }
}
