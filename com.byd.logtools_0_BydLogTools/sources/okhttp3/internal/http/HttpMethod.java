package okhttp3.internal.http;

import kotlin.jvm.internal.i;

/* JADX INFO: compiled from: HttpMethod.kt */
/* JADX INFO: loaded from: classes.dex */
public final class HttpMethod {
    public static final HttpMethod INSTANCE = new HttpMethod();

    private HttpMethod() {
    }

    public static final boolean permitsRequestBody(String str) {
        i.c(str, "method");
        return (i.a(str, "GET") || i.a(str, "HEAD")) ? false : true;
    }

    public static final boolean requiresRequestBody(String str) {
        i.c(str, "method");
        return i.a(str, "POST") || i.a(str, "PUT") || i.a(str, "PATCH") || i.a(str, "PROPPATCH") || i.a(str, "REPORT");
    }

    public final boolean invalidatesCache(String str) {
        i.c(str, "method");
        return i.a(str, "POST") || i.a(str, "PATCH") || i.a(str, "PUT") || i.a(str, "DELETE") || i.a(str, "MOVE");
    }

    public final boolean redirectsToGet(String str) {
        i.c(str, "method");
        return !i.a(str, "PROPFIND");
    }

    public final boolean redirectsWithBody(String str) {
        i.c(str, "method");
        return i.a(str, "PROPFIND");
    }
}
