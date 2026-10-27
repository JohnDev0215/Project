package p;

import com.spotify.automotive.connectivity.ConnectionStateController$ConnectionState;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.http.RealInterceptorChain;

/* JADX INFO: loaded from: classes.dex */
public final class ep1 implements Interceptor {
    public final rx b;

    public ep1(rx rxVar) {
        this.b = rxVar;
    }

    @Override // okhttp3.Interceptor
    public final Response intercept(Interceptor.Chain chain) {
        RealInterceptorChain realInterceptorChain = (RealInterceptorChain) chain;
        ConnectionStateController$ConnectionState connectionStateController$ConnectionState = this.b.c;
        ConnectionStateController$ConnectionState connectionStateController$ConnectionState2 = ConnectionStateController$ConnectionState.e;
        Request requestB = realInterceptorChain.e;
        if (connectionStateController$ConnectionState == connectionStateController$ConnectionState2) {
            Request.Builder builderB = requestB.b();
            builderB.c(CacheControl.f91p);
            requestB = builderB.b();
        }
        return realInterceptorChain.a(requestB);
    }
}
