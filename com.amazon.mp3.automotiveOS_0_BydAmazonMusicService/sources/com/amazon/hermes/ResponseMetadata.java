package com.amazon.hermes;

import com.android.volley.NetworkResponse;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class ResponseMetadata {
    private Map<String, String> headers;

    public ResponseMetadata(NetworkResponse networkResponse) {
        if (networkResponse != null) {
            this.headers = new HashMap(networkResponse.headers);
        } else {
            this.headers = new HashMap();
        }
    }

    public Map<String, String> getHeaders() {
        return this.headers;
    }
}
