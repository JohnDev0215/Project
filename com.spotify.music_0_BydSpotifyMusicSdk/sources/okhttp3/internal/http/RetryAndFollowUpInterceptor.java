package okhttp3.internal.http;

import com.spotify.connectivity.httpwebgate.WebgateAuthorizer;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.SocketTimeoutException;
import java.security.cert.CertificateException;
import java.util.Iterator;
import java.util.List;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocketFactory;
import kotlin.a;
import kotlin.collections.EmptyList;
import kotlin.text.Regex;
import okhttp3.Address;
import okhttp3.CertificatePinner;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.Route;
import okhttp3.internal.Util;
import okhttp3.internal.connection.Exchange;
import okhttp3.internal.connection.ExchangeFinder;
import okhttp3.internal.connection.RealCall;
import okhttp3.internal.connection.RealConnection;
import okhttp3.internal.connection.RealConnectionPool;
import okhttp3.internal.connection.RouteException;
import okhttp3.internal.connection.RouteSelector;
import okhttp3.internal.http2.ConnectionShutdownException;
import p.ou;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class RetryAndFollowUpInterceptor implements Interceptor {
    public final OkHttpClient b;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(int i) {
            this();
        }
    }

    static {
        new Companion(0);
    }

    public RetryAndFollowUpInterceptor(OkHttpClient okHttpClient) {
        v03.j(okHttpClient, "client");
        this.b = okHttpClient;
    }

    public static int c(Response response, int i) {
        String strC = Response.c(response, "Retry-After");
        if (strC == null) {
            return i;
        }
        if (!new Regex("\\d+").a(strC)) {
            return Integer.MAX_VALUE;
        }
        Integer numValueOf = Integer.valueOf(strC);
        v03.i(numValueOf, "valueOf(header)");
        return numValueOf.intValue();
    }

    public final Request a(Response response, Exchange exchange) throws ProtocolException {
        String strC;
        RealConnection realConnection;
        Route route = (exchange == null || (realConnection = exchange.g) == null) ? null : realConnection.b;
        int i = response.l;
        String str = response.c.b;
        if (i != 307 && i != 308) {
            if (i == 401) {
                return this.b.o.s(route, response);
            }
            if (i == 421) {
                if (exchange == null || !(!v03.b(exchange.c.b.i.d, exchange.g.b.a.i.d))) {
                    return null;
                }
                RealConnection realConnection2 = exchange.g;
                synchronized (realConnection2) {
                    realConnection2.k = true;
                }
                return response.c;
            }
            if (i == 503) {
                Response response2 = response.r;
                if ((response2 == null || response2.l != 503) && c(response, Integer.MAX_VALUE) == 0) {
                    return response.c;
                }
                return null;
            }
            if (i == 407) {
                v03.h(route);
                if (route.b.type() == Proxy.Type.HTTP) {
                    return this.b.w.s(route, response);
                }
                throw new ProtocolException("Received HTTP_PROXY_AUTH (407) code while not using proxy");
            }
            if (i == 408) {
                if (!this.b.n) {
                    return null;
                }
                Response response3 = response.r;
                if ((response3 == null || response3.l != 408) && c(response, 0) <= 0) {
                    return response.c;
                }
                return null;
            }
            switch (i) {
                case 300:
                case 301:
                case 302:
                case 303:
                    break;
                default:
                    return null;
            }
        }
        OkHttpClient okHttpClient = this.b;
        if (!okHttpClient.f93p || (strC = Response.c(response, "Location")) == null) {
            return null;
        }
        Request request = response.c;
        HttpUrl httpUrl = request.a;
        httpUrl.getClass();
        HttpUrl.Builder builderF = httpUrl.f(strC);
        HttpUrl httpUrlA = builderF != null ? builderF.a() : null;
        if (httpUrlA == null) {
            return null;
        }
        if (!v03.b(httpUrlA.a, request.a.a) && !okHttpClient.q) {
            return null;
        }
        Request.Builder builderB = request.b();
        if (HttpMethod.b(str)) {
            HttpMethod.a.getClass();
            boolean zB = v03.b(str, "PROPFIND");
            int i2 = response.l;
            boolean z = zB || i2 == 308 || i2 == 307;
            if (!(true ^ v03.b(str, "PROPFIND")) || i2 == 308 || i2 == 307) {
                builderB.f(str, z ? request.d : null);
            } else {
                builderB.f(com.spotify.cosmos.cosmos.Request.GET, null);
            }
            if (!z) {
                builderB.c.g("Transfer-Encoding");
                builderB.c.g("Content-Length");
                builderB.c.g("Content-Type");
            }
        }
        if (!Util.a(request.a, httpUrlA)) {
            builderB.c.g(WebgateAuthorizer.AUTHORIZATION_HEADER);
        }
        builderB.a = httpUrlA;
        return builderB.b();
    }

    public final boolean b(IOException iOException, RealCall realCall, Request request, boolean z) {
        RouteSelector routeSelector;
        RealConnection realConnection;
        if (!this.b.n) {
            return false;
        }
        if ((z && (iOException instanceof FileNotFoundException)) || (iOException instanceof ProtocolException) || (!(iOException instanceof InterruptedIOException) ? !(((iOException instanceof SSLHandshakeException) && (iOException.getCause() instanceof CertificateException)) || (iOException instanceof SSLPeerUnverifiedException)) : (iOException instanceof SocketTimeoutException) && !z)) {
            return false;
        }
        ExchangeFinder exchangeFinder = realCall.q;
        v03.h(exchangeFinder);
        int i = exchangeFinder.g;
        if (i != 0 || exchangeFinder.h != 0 || exchangeFinder.i != 0) {
            if (exchangeFinder.j == null) {
                Route route = null;
                if (i <= 1 && exchangeFinder.h <= 1 && exchangeFinder.i <= 0 && (realConnection = exchangeFinder.c.r) != null) {
                    synchronized (realConnection) {
                        if (realConnection.l == 0 && Util.a(realConnection.b.a.i, exchangeFinder.b.i)) {
                            route = realConnection.b;
                        }
                    }
                }
                if (route != null) {
                    exchangeFinder.j = route;
                } else {
                    RouteSelector.Selection selection = exchangeFinder.e;
                    if ((selection == null || !selection.a()) && (routeSelector = exchangeFinder.f) != null && !routeSelector.a()) {
                    }
                }
            }
            return true;
        }
        return false;
    }

    @Override // okhttp3.Interceptor
    public final Response intercept(Interceptor.Chain chain) throws Throwable {
        List list;
        int i;
        Response responseA;
        SSLSocketFactory sSLSocketFactory;
        HostnameVerifier hostnameVerifier;
        CertificatePinner certificatePinner;
        RealInterceptorChain realInterceptorChain = (RealInterceptorChain) chain;
        Request request = realInterceptorChain.e;
        RealCall realCall = realInterceptorChain.a;
        boolean z = true;
        List listJ0 = EmptyList.c;
        Response response = null;
        int i2 = 0;
        Request requestA = request;
        boolean z2 = true;
        while (true) {
            realCall.getClass();
            v03.j(requestA, "request");
            if (realCall.t != null) {
                throw new IllegalStateException("Check failed.".toString());
            }
            synchronized (realCall) {
                try {
                    if (!(realCall.v ^ z)) {
                        throw new IllegalStateException("cannot make a new request because the previous response is still open: please call response.close()".toString());
                    }
                    if (!(realCall.u ^ z)) {
                        throw new IllegalStateException("Check failed.".toString());
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            if (z2) {
                RealConnectionPool realConnectionPool = realCall.l;
                HttpUrl httpUrl = requestA.a;
                boolean z3 = httpUrl.j;
                OkHttpClient okHttpClient = realCall.c;
                if (z3) {
                    SSLSocketFactory sSLSocketFactory2 = okHttpClient.y;
                    if (sSLSocketFactory2 == null) {
                        throw new IllegalStateException("CLEARTEXT-only client");
                    }
                    HostnameVerifier hostnameVerifier2 = okHttpClient.C;
                    certificatePinner = okHttpClient.D;
                    sSLSocketFactory = sSLSocketFactory2;
                    hostnameVerifier = hostnameVerifier2;
                } else {
                    sSLSocketFactory = null;
                    hostnameVerifier = null;
                    certificatePinner = null;
                }
                list = listJ0;
                i = i2;
                realCall.q = new ExchangeFinder(realConnectionPool, new Address(httpUrl.d, httpUrl.e, okHttpClient.t, okHttpClient.x, sSLSocketFactory, hostnameVerifier, certificatePinner, okHttpClient.w, okHttpClient.u, okHttpClient.B, okHttpClient.A, okHttpClient.v), realCall, realCall.m);
            } else {
                list = listJ0;
                i = i2;
            }
            try {
            } catch (Throwable th2) {
                th = th2;
            }
            if (realCall.x) {
                throw new IOException("Canceled");
            }
            try {
                try {
                    responseA = realInterceptorChain.a(requestA);
                } catch (IOException e) {
                    if (!b(e, realCall, requestA, !(e instanceof ConnectionShutdownException))) {
                        List list2 = list;
                        v03.j(list2, "suppressed");
                        Iterator it = list2.iterator();
                        while (it.hasNext()) {
                            a.a(e, (Exception) it.next());
                        }
                        throw e;
                    }
                    listJ0 = ou.j0(list, e);
                    realCall.f(true);
                    z = true;
                    i2 = i;
                    z2 = false;
                } catch (RouteException e2) {
                    List list3 = list;
                    if (!b(e2.c(), realCall, requestA, false)) {
                        IOException iOExceptionB = e2.b();
                        v03.j(iOExceptionB, "<this>");
                        v03.j(list3, "suppressed");
                        Iterator it2 = list3.iterator();
                        while (it2.hasNext()) {
                            a.a(iOExceptionB, (Exception) it2.next());
                        }
                        throw iOExceptionB;
                    }
                    listJ0 = ou.j0(list3, e2.b());
                    realCall.f(true);
                    z = true;
                    z2 = false;
                    i2 = i;
                }
            } catch (Throwable th3) {
                th = th3;
            }
            if (response != null) {
                Response.Builder builderQ = responseA.q();
                Response.Builder builderQ2 = response.q();
                builderQ2.g = null;
                Response responseA2 = builderQ2.a();
                if (responseA2.o != null) {
                    throw new IllegalArgumentException("priorResponse.body != null".toString());
                }
                builderQ.j = responseA2;
                responseA = builderQ.a();
                realCall.f(true);
                throw th;
            }
            response = responseA;
            Exchange exchange = realCall.t;
            try {
                requestA = a(response, exchange);
                if (requestA == null) {
                    if (exchange != null && exchange.e) {
                        if (!(!realCall.s)) {
                            throw new IllegalStateException("Check failed.".toString());
                        }
                        realCall.s = true;
                        realCall.n.j();
                    }
                    realCall.f(false);
                    return response;
                }
                ResponseBody responseBody = response.o;
                if (responseBody != null) {
                    Util.c(responseBody);
                }
                i2 = i + 1;
                if (i2 > 20) {
                    throw new ProtocolException("Too many follow-up requests: " + i2);
                }
                realCall.f(true);
                listJ0 = list;
                z2 = true;
                z = true;
            } catch (Throwable th4) {
                th = th4;
            }
        }
    }
}
