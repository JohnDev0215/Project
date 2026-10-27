package okhttp3.internal.http;

import java.util.List;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.connection.Exchange;
import okhttp3.internal.connection.RealCall;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class RealInterceptorChain implements Interceptor.Chain {
    public final RealCall a;
    public final List b;
    public final int c;
    public final Exchange d;
    public final Request e;
    public final int f;
    public final int g;
    public final int h;
    public int i;

    public RealInterceptorChain(RealCall realCall, List list, int i, Exchange exchange, Request request, int i2, int i3, int i4) {
        v03.j(realCall, "call");
        v03.j(list, "interceptors");
        v03.j(request, "request");
        this.a = realCall;
        this.b = list;
        this.c = i;
        this.d = exchange;
        this.e = request;
        this.f = i2;
        this.g = i3;
        this.h = i4;
    }

    public static RealInterceptorChain b(RealInterceptorChain realInterceptorChain, int i, Exchange exchange, Request request, int i2) {
        if ((i2 & 1) != 0) {
            i = realInterceptorChain.c;
        }
        int i3 = i;
        if ((i2 & 2) != 0) {
            exchange = realInterceptorChain.d;
        }
        Exchange exchange2 = exchange;
        if ((i2 & 4) != 0) {
            request = realInterceptorChain.e;
        }
        Request request2 = request;
        int i4 = (i2 & 8) != 0 ? realInterceptorChain.f : 0;
        int i5 = (i2 & 16) != 0 ? realInterceptorChain.g : 0;
        int i6 = (i2 & 32) != 0 ? realInterceptorChain.h : 0;
        realInterceptorChain.getClass();
        v03.j(request2, "request");
        return new RealInterceptorChain(realInterceptorChain.a, realInterceptorChain.b, i3, exchange2, request2, i4, i5, i6);
    }

    @Override // okhttp3.Interceptor.Chain
    public final Response a(Request request) {
        v03.j(request, "request");
        List list = this.b;
        int size = list.size();
        int i = this.c;
        if (i >= size) {
            throw new IllegalStateException("Check failed.".toString());
        }
        this.i++;
        Exchange exchange = this.d;
        if (exchange != null) {
            if (!exchange.c.b(request.a)) {
                throw new IllegalStateException(("network interceptor " + list.get(i - 1) + " must retain the same host and port").toString());
            }
            if (this.i != 1) {
                throw new IllegalStateException(("network interceptor " + list.get(i - 1) + " must call proceed() exactly once").toString());
            }
        }
        int i2 = i + 1;
        RealInterceptorChain realInterceptorChainB = b(this, i2, null, request, 58);
        Interceptor interceptor = (Interceptor) list.get(i);
        Response responseIntercept = interceptor.intercept(realInterceptorChainB);
        if (responseIntercept == null) {
            throw new NullPointerException("interceptor " + interceptor + " returned null");
        }
        if (exchange != null && i2 < list.size() && realInterceptorChainB.i != 1) {
            throw new IllegalStateException(("network interceptor " + interceptor + " must call proceed() exactly once").toString());
        }
        if (responseIntercept.o != null) {
            return responseIntercept;
        }
        throw new IllegalStateException(("interceptor " + interceptor + " returned a response with no body").toString());
    }

    @Override // okhttp3.Interceptor.Chain
    public final Request c() {
        return this.e;
    }
}
