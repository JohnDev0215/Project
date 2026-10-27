package com.amazon.identity.auth.device;

import android.net.Uri;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public abstract class db {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final HttpURLConnection f282a;

    public db(HttpURLConnection httpURLConnection) {
        this.f282a = httpURLConnection;
    }

    public abstract byte[] getBody();

    public String getHeader(String str) {
        return this.f282a.getRequestProperty(str);
    }

    public Map<String, List<String>> getHeaders() {
        return this.f282a.getRequestProperties();
    }

    public String getHttpVerb() {
        return this.f282a.getRequestMethod();
    }

    public Uri getUri() {
        return Uri.parse(this.f282a.getURL().toString());
    }

    public HttpURLConnection getUrlConnection() {
        return this.f282a;
    }

    public void setHeader(String str, String str2) {
        this.f282a.setRequestProperty(str, str2);
    }
}
