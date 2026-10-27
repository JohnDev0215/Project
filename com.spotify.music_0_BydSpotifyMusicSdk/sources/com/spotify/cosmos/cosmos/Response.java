package com.spotify.cosmos.cosmos;

import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class Response {
    private final byte[] body;
    private final Map<String, String> headers;
    private final int status;
    private final String uri;

    public Response(int i, String str, Map<String, String> map, byte[] bArr) {
        this.status = i;
        this.uri = str;
        this.headers = map;
        this.body = bArr;
    }

    public byte[] getBody() {
        return this.body;
    }

    public String getBodyString() {
        byte[] body = getBody();
        return body != null ? new String(body, ResponseKt.DEFAULT_CHARSET) : new String();
    }

    public Map<String, String> getHeaders() {
        return this.headers;
    }

    public int getStatus() {
        return this.status;
    }

    public String getUri() {
        return this.uri;
    }

    public String toString() {
        return "Response{mStatus=" + getStatus() + ", mUri='" + getUri() + "', mHeaders=" + getHeaders() + ", mBody='" + getBodyString() + "'}";
    }
}
