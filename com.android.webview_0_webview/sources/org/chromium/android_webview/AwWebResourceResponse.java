package org.chromium.android_webview;

import java.io.InputStream;
import java.util.Map;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class AwWebResourceResponse {
    public String a;
    public String b;
    public InputStream c;
    public int d;
    public String e;
    public Map f;
    public String[] g;
    public String[] h;

    public AwWebResourceResponse(String str, String str2, InputStream inputStream) {
        this.a = str;
        this.b = null;
        this.c = inputStream;
    }

    private String[] getResponseHeaderNames() {
        a();
        return this.g;
    }

    private String[] getResponseHeaderValues() {
        a();
        return this.h;
    }

    public final void a() {
        Map map = this.f;
        if (map == null || this.g != null) {
            return;
        }
        this.g = new String[map.size()];
        this.h = new String[this.f.size()];
        int i = 0;
        for (Map.Entry entry : this.f.entrySet()) {
            this.g[i] = (String) entry.getKey();
            this.h[i] = (String) entry.getValue();
            i++;
        }
    }

    public String getCharset() {
        return this.b;
    }

    public InputStream getData() {
        return this.c;
    }

    public String getMimeType() {
        return this.a;
    }

    public String getReasonPhrase() {
        return this.e;
    }

    public int getStatusCode() {
        return this.d;
    }

    public AwWebResourceResponse(String str, String str2, InputStream inputStream, int i, String str3, Map map) {
        this.a = str;
        this.b = str2;
        this.c = inputStream;
        this.d = i;
        this.e = str3;
        this.f = map;
    }
}
