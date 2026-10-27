package retrofit2;

import java.util.Objects;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Protocol;
import retrofit2.OkHttpCall;
import spi.e;
import spi.im;
import spi.ke;
import spi.ln;
import spi.mn;

/* JADX INFO: loaded from: classes2.dex */
public final class Response<T> {
    private final T body;
    private final mn errorBody;
    private final ln rawResponse;

    private Response(ln lnVar, T t, mn mnVar) {
        this.rawResponse = lnVar;
        this.body = t;
        this.errorBody = mnVar;
    }

    public static <T> Response<T> error(int i, mn mnVar) {
        Objects.requireNonNull(mnVar, "body == null");
        if (i < 400) {
            throw new IllegalArgumentException(e.c("code < 400: ", i));
        }
        ln.a aVar = new ln.a();
        aVar.g = new OkHttpCall.NoContentResponseBody(mnVar.contentType(), mnVar.contentLength());
        aVar.c = i;
        Intrinsics.checkNotNullParameter("Response.error()", "message");
        aVar.d = "Response.error()";
        aVar.f(Protocol.HTTP_1_1);
        im.a aVar2 = new im.a();
        aVar2.g("http://localhost/");
        im request = aVar2.a();
        Intrinsics.checkNotNullParameter(request, "request");
        aVar.a = request;
        return error(mnVar, aVar.a());
    }

    public static <T> Response<T> error(mn mnVar, ln lnVar) {
        Objects.requireNonNull(mnVar, "body == null");
        Objects.requireNonNull(lnVar, "rawResponse == null");
        if (lnVar.C()) {
            throw new IllegalArgumentException("rawResponse should not be successful response");
        }
        return new Response<>(lnVar, null, mnVar);
    }

    public static <T> Response<T> success(int i, T t) {
        if (i < 200 || i >= 300) {
            throw new IllegalArgumentException(e.c("code < 200 or >= 300: ", i));
        }
        ln.a aVar = new ln.a();
        aVar.c = i;
        Intrinsics.checkNotNullParameter("Response.success()", "message");
        aVar.d = "Response.success()";
        aVar.f(Protocol.HTTP_1_1);
        im.a aVar2 = new im.a();
        aVar2.g("http://localhost/");
        im request = aVar2.a();
        Intrinsics.checkNotNullParameter(request, "request");
        aVar.a = request;
        return success(t, aVar.a());
    }

    public static <T> Response<T> success(T t, ln lnVar) {
        Objects.requireNonNull(lnVar, "rawResponse == null");
        if (lnVar.C()) {
            return new Response<>(lnVar, t, null);
        }
        throw new IllegalArgumentException("rawResponse must be successful response");
    }

    public T body() {
        return this.body;
    }

    public int code() {
        return this.rawResponse.e;
    }

    public mn errorBody() {
        return this.errorBody;
    }

    public ke headers() {
        return this.rawResponse.g;
    }

    public boolean isSuccessful() {
        return this.rawResponse.C();
    }

    public String message() {
        return this.rawResponse.d;
    }

    public ln raw() {
        return this.rawResponse;
    }

    public String toString() {
        return this.rawResponse.toString();
    }

    public static <T> Response<T> success(T t) {
        ln.a aVar = new ln.a();
        aVar.c = 200;
        Intrinsics.checkNotNullParameter("OK", "message");
        aVar.d = "OK";
        aVar.f(Protocol.HTTP_1_1);
        im.a aVar2 = new im.a();
        aVar2.g("http://localhost/");
        im request = aVar2.a();
        Intrinsics.checkNotNullParameter(request, "request");
        aVar.a = request;
        return success(t, aVar.a());
    }

    public static <T> Response<T> success(T t, ke keVar) {
        Objects.requireNonNull(keVar, "headers == null");
        ln.a aVar = new ln.a();
        aVar.c = 200;
        Intrinsics.checkNotNullParameter("OK", "message");
        aVar.d = "OK";
        aVar.f(Protocol.HTTP_1_1);
        aVar.d(keVar);
        im.a aVar2 = new im.a();
        aVar2.g("http://localhost/");
        im request = aVar2.a();
        Intrinsics.checkNotNullParameter(request, "request");
        aVar.a = request;
        return success(t, aVar.a());
    }
}
