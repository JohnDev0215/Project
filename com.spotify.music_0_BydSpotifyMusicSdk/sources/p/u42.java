package p;

import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.ResponseBody$Companion$asResponseBody$1;

/* JADX INFO: loaded from: classes.dex */
public final class u42<T> {
    public final Response a;
    public final Object b;
    public final ResponseBody c;

    public u42(Response response, Object obj, ResponseBody$Companion$asResponseBody$1 responseBody$Companion$asResponseBody$1) {
        this.a = response;
        this.b = obj;
        this.c = responseBody$Companion$asResponseBody$1;
    }

    public final String toString() {
        return this.a.toString();
    }
}
