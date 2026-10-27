package p;

import com.google.common.base.Optional;
import com.spotify.base.java.logging.Logger;
import com.spotify.connectivity.httpwebgate.WebgateAuthorizer;
import com.spotify.connectivity.httpwebgate.WebgateHelper;
import com.spotify.connectivity.httpwebgate.WebgateTokenProvider;
import okhttp3.Authenticator;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

/* JADX INFO: loaded from: classes.dex */
public final class bw2 implements Authenticator, Interceptor, zc1 {
    public final WebgateHelper b;
    public final WebgateTokenProvider c;
    public final Object d = new Object();

    public bw2(WebgateTokenProvider webgateTokenProvider, WebgateHelper webgateHelper) {
        this.c = webgateTokenProvider;
        this.b = webgateHelper;
    }

    @Override // p.zc1
    public final void a() {
        throw null;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x002e  */
    @Override // okhttp3.Interceptor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final okhttp3.Response intercept(okhttp3.Interceptor.Chain r5) {
        /*
            r4 = this;
            okhttp3.internal.http.RealInterceptorChain r5 = (okhttp3.internal.http.RealInterceptorChain) r5
            okhttp3.Request r0 = r5.e
            okhttp3.HttpUrl r1 = r0.a
            boolean r2 = r1.j
            com.spotify.connectivity.httpwebgate.WebgateHelper r3 = r4.b
            if (r2 != 0) goto Ld
            goto L17
        Ld:
            java.lang.String r2 = "api.spotify.com"
            java.lang.String r1 = r1.d
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L2e
        L17:
            okhttp3.HttpUrl r1 = r0.a
            boolean r2 = r1.j
            if (r2 != 0) goto L1e
            goto L28
        L1e:
            java.lang.String r2 = "api-partner.spotify.com"
            java.lang.String r1 = r1.d
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L2e
        L28:
            boolean r1 = r3.isWebgateRequest(r0)
            if (r1 == 0) goto L34
        L2e:
            boolean r1 = r3.hasNoAuthTag(r0)
            if (r1 == 0) goto L39
        L34:
            okhttp3.Response r5 = r5.a(r0)
            return r5
        L39:
            int r1 = r5.g
            int r1 = r1 / 2
            com.google.common.base.Optional r1 = r4.u(r1)
            boolean r2 = r1.d()
            if (r2 == 0) goto L6d
            okhttp3.Request$Builder r0 = r0.b()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Bearer "
            r2.<init>(r3)
            java.lang.Object r1 = r1.c()
            java.lang.String r1 = (java.lang.String) r1
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            java.lang.String r2 = "Authorization"
            r0.a(r2, r1)
            okhttp3.Request r0 = r0.b()
            okhttp3.Response r5 = r5.a(r0)
            return r5
        L6d:
            okhttp3.Response r5 = r5.a(r0)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: p.bw2.intercept(okhttp3.Interceptor$Chain):okhttp3.Response");
    }

    @Override // okhttp3.Authenticator
    public final Request s(Route route, Response response) {
        String strRequestAccessToken;
        Optional optionalU = u(5000);
        synchronized (this.d) {
            try {
                Optional optionalU2 = u(5000);
                if (!optionalU2.d() || optionalU2.equals(optionalU)) {
                    Logger.e("refreshing access token", new Object[0]);
                    this.c.reset();
                    try {
                        strRequestAccessToken = this.c.requestAccessToken(5000, true);
                    } catch (WebgateTokenProvider.WebgateTokenException unused) {
                        return null;
                    }
                } else {
                    Logger.e("access token was refresh in another thread", new Object[0]);
                    strRequestAccessToken = (String) optionalU2.c();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        Request.Builder builderB = response.c.b();
        builderB.c.g(WebgateAuthorizer.AUTHORIZATION_HEADER);
        builderB.a(WebgateAuthorizer.AUTHORIZATION_HEADER, WebgateAuthorizer.AUTHORIZATION_PREFIX + strRequestAccessToken);
        return builderB.b();
    }

    public final Optional u(int i) {
        Optional optionalA = Optional.a();
        try {
            return Optional.e(this.c.requestAccessToken(i));
        } catch (WebgateTokenProvider.WebgateTokenException e) {
            Logger.c(e, "Couldn't fetch the latest access token", new Object[0]);
            return optionalA;
        }
    }
}
