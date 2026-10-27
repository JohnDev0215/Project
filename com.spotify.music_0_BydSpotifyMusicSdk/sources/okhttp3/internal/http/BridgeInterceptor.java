package okhttp3.internal.http;

import okhttp3.CookieJar;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.Util;
import p.f11;
import p.ng2;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class BridgeInterceptor implements Interceptor {
    public final CookieJar b;

    public BridgeInterceptor(CookieJar cookieJar) {
        v03.j(cookieJar, "cookieJar");
        this.b = cookieJar;
    }

    @Override // okhttp3.Interceptor
    public final Response intercept(Interceptor.Chain chain) {
        ResponseBody responseBody;
        RealInterceptorChain realInterceptorChain = (RealInterceptorChain) chain;
        Request request = realInterceptorChain.e;
        Request.Builder builderB = request.b();
        RequestBody requestBody = request.d;
        if (requestBody != null) {
            MediaType mediaTypeB = requestBody.b();
            if (mediaTypeB != null) {
                builderB.d("Content-Type", mediaTypeB.a);
            }
            long jA = requestBody.a();
            if (jA != -1) {
                builderB.d("Content-Length", String.valueOf(jA));
                builderB.c.g("Transfer-Encoding");
            } else {
                builderB.d("Transfer-Encoding", "chunked");
                builderB.c.g("Content-Length");
            }
        }
        Headers headers = request.c;
        String strA = headers.a("Host");
        boolean z = false;
        HttpUrl httpUrl = request.a;
        if (strA == null) {
            builderB.d("Host", Util.x(httpUrl, false));
        }
        if (headers.a("Connection") == null) {
            builderB.d("Connection", "Keep-Alive");
        }
        if (headers.a("Accept-Encoding") == null && headers.a("Range") == null) {
            builderB.d("Accept-Encoding", "gzip");
            z = true;
        }
        CookieJar cookieJar = this.b;
        cookieJar.a(httpUrl).getClass();
        if (headers.a("User-Agent") == null) {
            builderB.d("User-Agent", "okhttp/4.12.0");
        }
        Response responseA = realInterceptorChain.a(builderB.b());
        Headers headers2 = responseA.n;
        HttpHeaders.d(cookieJar, httpUrl, headers2);
        Response.Builder builderQ = responseA.q();
        builderQ.a = request;
        if (z && ng2.b0("gzip", Response.c(responseA, "Content-Encoding"), true) && HttpHeaders.a(responseA) && (responseBody = responseA.o) != null) {
            f11 f11Var = new f11(responseBody.i());
            Headers.Builder builderC = headers2.c();
            builderC.g("Content-Encoding");
            builderC.g("Content-Length");
            builderQ.c(builderC.e());
            builderQ.g = new RealResponseBody(Response.c(responseA, "Content-Type"), -1L, v03.d(f11Var));
        }
        return builderQ.a();
    }
}
