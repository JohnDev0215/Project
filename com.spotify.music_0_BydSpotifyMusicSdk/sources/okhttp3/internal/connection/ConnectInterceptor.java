package okhttp3.internal.connection;

import com.spotify.cosmos.cosmos.Request;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.internal.http.RealInterceptorChain;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class ConnectInterceptor implements Interceptor {
    public static final ConnectInterceptor b = new ConnectInterceptor();

    private ConnectInterceptor() {
    }

    @Override // okhttp3.Interceptor
    public final Response intercept(Interceptor.Chain chain) throws IOException {
        RealInterceptorChain realInterceptorChain = (RealInterceptorChain) chain;
        RealCall realCall = realInterceptorChain.a;
        realCall.getClass();
        synchronized (realCall) {
            try {
                if (!realCall.w) {
                    throw new IllegalStateException("released".toString());
                }
                if (!(!realCall.v)) {
                    throw new IllegalStateException("Check failed.".toString());
                }
                if (!(!realCall.u)) {
                    throw new IllegalStateException("Check failed.".toString());
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        ExchangeFinder exchangeFinder = realCall.q;
        v03.h(exchangeFinder);
        OkHttpClient okHttpClient = realCall.c;
        v03.j(okHttpClient, "client");
        try {
            Exchange exchange = new Exchange(realCall, realCall.m, exchangeFinder, exchangeFinder.a(realInterceptorChain.f, realInterceptorChain.g, realInterceptorChain.h, okHttpClient.J, okHttpClient.n, !v03.b(realInterceptorChain.e.b, Request.GET)).j(okHttpClient, realInterceptorChain));
            realCall.t = exchange;
            realCall.y = exchange;
            synchronized (realCall) {
                realCall.u = true;
                realCall.v = true;
            }
            if (realCall.x) {
                throw new IOException("Canceled");
            }
            return RealInterceptorChain.b(realInterceptorChain, 0, exchange, null, 61).a(realInterceptorChain.e);
        } catch (IOException e) {
            exchangeFinder.c(e);
            throw new RouteException(e);
        } catch (RouteException e2) {
            exchangeFinder.c(e2.c());
            throw e2;
        }
    }
}
