package com.spotify.core.http;

import p.u4;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class HttpResponse {
    private final String headers;
    private final int status;
    private final String url;

    public HttpResponse(int i, String str, String str2) {
        v03.j(str, "url");
        v03.j(str2, "headers");
        this.status = i;
        this.url = str;
        this.headers = str2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!v03.b(HttpResponse.class, obj != null ? obj.getClass() : null)) {
            return false;
        }
        v03.g(obj, "null cannot be cast to non-null type com.spotify.core.http.HttpResponse");
        HttpResponse httpResponse = (HttpResponse) obj;
        if (this.status != httpResponse.status) {
            return false;
        }
        String str = this.url;
        if (str == null ? httpResponse.url != null : !str.equals(httpResponse.url)) {
            return false;
        }
        String str2 = this.headers;
        String str3 = httpResponse.headers;
        return str2 == null ? str3 == null : str2.equals(str3);
    }

    public final String getHeaders() {
        return this.headers;
    }

    public final int getStatus() {
        return this.status;
    }

    public final String getUrl() {
        return this.url;
    }

    public int hashCode() {
        int i = this.status * 31;
        String str = this.url;
        int iHashCode = (i + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.headers;
        return iHashCode + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("HttpResponse{status=");
        sb.append(this.status);
        sb.append(", url='");
        sb.append(this.url);
        sb.append("', headers='");
        return u4.m(sb, this.headers, "'}");
    }
}
