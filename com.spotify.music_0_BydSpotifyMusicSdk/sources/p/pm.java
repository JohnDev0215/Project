package p;

import com.spotify.connectivity.httpwebgate.WebgateAuthorizer;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.http.RealInterceptorChain;

/* JADX INFO: loaded from: classes.dex */
public final class pm implements Interceptor {
    public final String b;

    public pm(String str) {
        this.b = str;
    }

    @Override // okhttp3.Interceptor
    public final Response intercept(Interceptor.Chain chain) {
        RealInterceptorChain realInterceptorChain = (RealInterceptorChain) chain;
        Request.Builder builderB = realInterceptorChain.e.b();
        builderB.a(WebgateAuthorizer.AUTHORIZATION_HEADER, WebgateAuthorizer.AUTHORIZATION_PREFIX + this.b);
        return realInterceptorChain.a(builderB.b());
    }
}
