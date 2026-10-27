package okhttp3.internal.http;

import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.connection.RealConnection;
import p.de2;
import p.pd2;

/* JADX INFO: loaded from: classes.dex */
public interface ExchangeCodec {

    public static final class Companion {
        static {
            new Companion();
        }

        private Companion() {
        }
    }

    void a();

    void b(Request request);

    void c();

    void cancel();

    long d(Response response);

    de2 e(Response response);

    pd2 f(Request request, long j);

    Response.Builder g(boolean z);

    RealConnection h();
}
