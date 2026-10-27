package retrofit2;

import h.l;
import h.o;

/* JADX INFO: loaded from: classes.dex */
public class HttpException extends RuntimeException {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final transient l<?> f5958c;
    private final int code;
    private final String message;

    public HttpException(l<?> lVar) {
        super(a(lVar));
        this.code = lVar.b();
        this.message = lVar.e();
        this.f5958c = lVar;
    }

    public static String a(l<?> lVar) {
        o.b(lVar, "response == null");
        return "HTTP " + lVar.b() + " " + lVar.e();
    }

    public int code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }

    public l<?> response() {
        return this.f5958c;
    }
}
