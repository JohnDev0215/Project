package okhttp3.internal.http;

import okhttp3.HttpUrl;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class RequestLine {
    public static final RequestLine a = new RequestLine();

    private RequestLine() {
    }

    public static String a(HttpUrl httpUrl) {
        v03.j(httpUrl, "url");
        String strB = httpUrl.b();
        String strD = httpUrl.d();
        if (strD == null) {
            return strB;
        }
        return strB + '?' + strD;
    }
}
