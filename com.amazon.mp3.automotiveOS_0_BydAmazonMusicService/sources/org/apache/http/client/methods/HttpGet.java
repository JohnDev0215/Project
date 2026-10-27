package org.apache.http.client.methods;

import com.google.firebase.perf.FirebasePerformance;
import java.net.URI;

/* JADX INFO: loaded from: classes7.dex */
public class HttpGet extends HttpRequestBase {
    @Override // org.apache.http.client.methods.HttpRequestBase, org.apache.http.client.methods.HttpUriRequest
    public String getMethod() {
        return FirebasePerformance.HttpMethod.GET;
    }

    public HttpGet() {
    }

    public HttpGet(URI uri) {
        setURI(uri);
    }

    public HttpGet(String str) {
        setURI(URI.create(str));
    }
}
