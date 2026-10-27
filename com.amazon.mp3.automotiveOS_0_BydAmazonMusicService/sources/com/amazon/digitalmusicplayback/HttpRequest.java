package com.amazon.digitalmusicplayback;

import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public final class HttpRequest {
    final byte[] body;
    final HashMap<String, ArrayList<String>> headers;
    final String id;
    final HttpMethod method;
    final String url;

    public HttpRequest(String str, HttpMethod httpMethod, HashMap<String, ArrayList<String>> map, byte[] bArr, String str2) {
        this.url = str;
        this.method = httpMethod;
        this.headers = map;
        this.body = bArr;
        this.id = str2;
    }

    public String getUrl() {
        return this.url;
    }

    public HttpMethod getMethod() {
        return this.method;
    }

    public HashMap<String, ArrayList<String>> getHeaders() {
        return this.headers;
    }

    public byte[] getBody() {
        return this.body;
    }

    public String getId() {
        return this.id;
    }

    public String toString() {
        return "HttpRequest{url=" + this.url + ",method=" + this.method + ",headers=" + this.headers + ",body=" + this.body + ",id=" + this.id + "}";
    }
}
