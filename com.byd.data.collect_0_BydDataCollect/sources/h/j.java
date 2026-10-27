package h;

import java.util.Objects;

/* JADX INFO: compiled from: HttpException.java */
/* JADX INFO: loaded from: classes.dex */
public class j extends RuntimeException {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final transient t<?> f5569b;
    private final int code;
    private final String message;

    public j(t<?> tVar) {
        super(a(tVar));
        this.code = tVar.b();
        this.message = tVar.e();
        this.f5569b = tVar;
    }

    private static String a(t<?> tVar) {
        Objects.requireNonNull(tVar, "response == null");
        return "HTTP " + tVar.b() + " " + tVar.e();
    }
}
