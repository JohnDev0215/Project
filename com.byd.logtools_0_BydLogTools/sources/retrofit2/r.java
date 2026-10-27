package retrofit2;

import java.util.Objects;
import javax.annotation.Nullable;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* JADX INFO: compiled from: Response.java */
/* JADX INFO: loaded from: classes.dex */
public final class r<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Response f5318a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    @Nullable
    private final T f5319b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    @Nullable
    private final ResponseBody f5320c;

    private r(Response response, @Nullable T t, @Nullable ResponseBody responseBody) {
        this.f5318a = response;
        this.f5319b = t;
        this.f5320c = responseBody;
    }

    public static <T> r<T> c(ResponseBody responseBody, Response response) {
        Objects.requireNonNull(responseBody, "body == null");
        Objects.requireNonNull(response, "rawResponse == null");
        if (response.isSuccessful()) {
            throw new IllegalArgumentException("rawResponse should not be successful response");
        }
        return new r<>(response, null, responseBody);
    }

    public static <T> r<T> f(@Nullable T t, Response response) {
        Objects.requireNonNull(response, "rawResponse == null");
        if (response.isSuccessful()) {
            return new r<>(response, t, null);
        }
        throw new IllegalArgumentException("rawResponse must be successful response");
    }

    @Nullable
    public T a() {
        return this.f5319b;
    }

    public int b() {
        return this.f5318a.code();
    }

    public boolean d() {
        return this.f5318a.isSuccessful();
    }

    public String e() {
        return this.f5318a.message();
    }

    public String toString() {
        return this.f5318a.toString();
    }
}
