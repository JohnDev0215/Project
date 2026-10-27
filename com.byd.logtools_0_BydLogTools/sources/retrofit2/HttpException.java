package retrofit2;

import java.util.Objects;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public class HttpException extends RuntimeException {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final transient r<?> f5182b;
    private final int code;
    private final String message;

    public HttpException(r<?> rVar) {
        super(a(rVar));
        this.code = rVar.b();
        this.message = rVar.e();
        this.f5182b = rVar;
    }

    private static String a(r<?> rVar) {
        Objects.requireNonNull(rVar, "response == null");
        return "HTTP " + rVar.b() + " " + rVar.e();
    }

    public int code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }

    @Nullable
    public r<?> response() {
        return this.f5182b;
    }
}
