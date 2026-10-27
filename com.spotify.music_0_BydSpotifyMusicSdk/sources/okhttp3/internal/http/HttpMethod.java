package okhttp3.internal.http;

import com.spotify.cosmos.cosmos.Request;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class HttpMethod {
    public static final HttpMethod a = new HttpMethod();

    private HttpMethod() {
    }

    public static boolean a(String str) {
        v03.j(str, "method");
        return v03.b(str, Request.POST) || v03.b(str, "PATCH") || v03.b(str, Request.PUT) || v03.b(str, Request.DELETE) || v03.b(str, "MOVE");
    }

    public static final boolean b(String str) {
        v03.j(str, "method");
        return (v03.b(str, Request.GET) || v03.b(str, "HEAD")) ? false : true;
    }

    public static final boolean c(String str) {
        v03.j(str, "method");
        return v03.b(str, Request.POST) || v03.b(str, Request.PUT) || v03.b(str, "PATCH") || v03.b(str, "PROPPATCH") || v03.b(str, "REPORT");
    }
}
