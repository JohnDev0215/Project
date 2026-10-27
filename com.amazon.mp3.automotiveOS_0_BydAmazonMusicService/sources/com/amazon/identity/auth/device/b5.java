package com.amazon.identity.auth.device;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class b5 extends a5 {
    public final HttpsURLConnection g;

    public b5(URL url) throws IOException {
        super(url);
        HttpURLConnection httpURLConnectionB = b();
        if (!(httpURLConnectionB instanceof HttpsURLConnection)) {
            throw new IllegalArgumentException("url must be https");
        }
        this.g = (HttpsURLConnection) httpURLConnectionB;
    }

    @Override // com.amazon.identity.auth.device.a5
    public void a(HttpURLConnection httpURLConnection) {
        super.a(httpURLConnection);
        if (httpURLConnection instanceof HttpsURLConnection) {
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) httpURLConnection;
            httpsURLConnection.setSSLSocketFactory(this.g.getSSLSocketFactory());
            httpsURLConnection.setHostnameVerifier(this.g.getHostnameVerifier());
        }
    }
}
