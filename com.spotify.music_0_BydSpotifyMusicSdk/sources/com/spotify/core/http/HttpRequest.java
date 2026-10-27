package com.spotify.core.http;

import java.util.Arrays;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class HttpRequest {
    private final byte[] body;
    private final byte[] headers;
    private final String method;
    private final String url;

    public HttpRequest(String str, String str2, byte[] bArr, byte[] bArr2) {
        this.url = str;
        this.method = str2;
        this.headers = bArr;
        this.body = bArr2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!v03.b(HttpRequest.class, obj != null ? obj.getClass() : null)) {
            return false;
        }
        v03.g(obj, "null cannot be cast to non-null type com.spotify.core.http.HttpRequest");
        HttpRequest httpRequest = (HttpRequest) obj;
        String str = this.url;
        if (str == null ? httpRequest.url != null : !str.equals(httpRequest.url)) {
            return false;
        }
        String str2 = this.method;
        if (str2 == null ? httpRequest.method == null : str2.equals(httpRequest.method)) {
            return Arrays.equals(this.headers, httpRequest.headers) && Arrays.equals(this.body, httpRequest.body);
        }
        return false;
    }

    public final byte[] getBody() {
        return this.body;
    }

    public final byte[] getHeaders() {
        return this.headers;
    }

    public final String getMethod() {
        return this.method;
    }

    public final String getUrl() {
        return this.url;
    }

    public int hashCode() {
        String str = this.url;
        int iHashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.method;
        return Arrays.hashCode(this.body) + ((Arrays.hashCode(this.headers) + ((iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31)) * 31);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("HttpRequest{url='");
        sb.append(this.url);
        sb.append("', method='");
        sb.append(this.method);
        sb.append("', mHeaders=");
        String string = Arrays.toString(this.headers);
        v03.i(string, "toString(this)");
        sb.append(string);
        sb.append(", mBody=");
        String string2 = Arrays.toString(this.body);
        v03.i(string2, "toString(this)");
        sb.append(string2);
        sb.append('}');
        return sb.toString();
    }
}
