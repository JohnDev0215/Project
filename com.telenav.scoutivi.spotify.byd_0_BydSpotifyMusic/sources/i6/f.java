package i6;

import kotlin.jvm.internal.l;

/* JADX INFO: compiled from: HttpMethod.kt */
/* JADX INFO: loaded from: classes.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final f f7051a = new f();

    private f() {
    }

    public static final boolean a(String method) {
        l.e(method, "method");
        return (l.a(method, "GET") || l.a(method, "HEAD")) ? false : true;
    }

    public static final boolean d(String method) {
        l.e(method, "method");
        return l.a(method, "POST") || l.a(method, "PUT") || l.a(method, "PATCH") || l.a(method, "PROPPATCH") || l.a(method, "REPORT");
    }

    public final boolean b(String method) {
        l.e(method, "method");
        return !l.a(method, "PROPFIND");
    }

    public final boolean c(String method) {
        l.e(method, "method");
        return l.a(method, "PROPFIND");
    }
}
