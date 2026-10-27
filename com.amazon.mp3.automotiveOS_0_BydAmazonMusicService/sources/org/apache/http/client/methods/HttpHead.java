package org.apache.http.client.methods;

import com.google.firebase.perf.FirebasePerformance;
import java.net.URI;

/* JADX INFO: loaded from: classes7.dex */
public class HttpHead extends HttpRequestBase {
    @Override // org.apache.http.client.methods.HttpRequestBase, org.apache.http.client.methods.HttpUriRequest
    public String getMethod() {
        return FirebasePerformance.HttpMethod.HEAD;
    }

    public HttpHead() {
    }

    public HttpHead(URI uri) {
        setURI(uri);
    }
}
