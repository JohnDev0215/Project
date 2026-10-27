package okhttp3;

import com.spotify.connectivity.httpwebgate.WebgateAuthorizer;
import java.io.IOException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.ProxySelector;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import kotlin.text.b;
import okhttp3.Call;
import okhttp3.EventListener;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.internal.Util;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.connection.Exchange;
import okhttp3.internal.connection.RealCall;
import okhttp3.internal.connection.RealConnection$newWebSocketStreams$1;
import okhttp3.internal.connection.RouteDatabase;
import okhttp3.internal.platform.Platform;
import okhttp3.internal.proxy.NullProxySelector;
import okhttp3.internal.tls.CertificateChainCleaner;
import okhttp3.internal.tls.OkHostnameVerifier;
import okhttp3.internal.ws.RealWebSocket;
import okhttp3.internal.ws.WebSocketExtensions;
import p.af2;
import p.f80;
import p.i51;
import p.mg2;
import p.ng2;
import p.nu;
import p.ou;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public class OkHttpClient implements Cloneable, Call.Factory, WebSocket.Factory {
    public static final Companion M = new Companion(0);
    public static final List N = Util.l(Protocol.HTTP_2, Protocol.HTTP_1_1);
    public static final List O = Util.l(ConnectionSpec.e, ConnectionSpec.f);
    public final List A;
    public final List B;
    public final HostnameVerifier C;
    public final CertificatePinner D;
    public final CertificateChainCleaner E;
    public final int F;
    public final int G;
    public final int H;
    public final int I;
    public final int J;
    public final long K;
    public final RouteDatabase L;
    public final Dispatcher c;
    public final ConnectionPool e;
    public final List k;
    public final List l;
    public final EventListener.Factory m;
    public final boolean n;
    public final Authenticator o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final boolean f93p;
    public final boolean q;
    public final CookieJar r;
    public final Cache s;
    public final Dns t;
    public final Proxy u;
    public final ProxySelector v;
    public final Authenticator w;
    public final SocketFactory x;
    public final SSLSocketFactory y;
    public final X509TrustManager z;

    public static final class Builder {
        public int A;
        public int B;
        public long C;
        public RouteDatabase D;
        public Dispatcher a = new Dispatcher();
        public ConnectionPool b = new ConnectionPool();
        public final ArrayList c = new ArrayList();
        public final ArrayList d = new ArrayList();
        public EventListener.Factory e;
        public boolean f;
        public Authenticator g;
        public boolean h;
        public boolean i;
        public CookieJar j;
        public Cache k;
        public Dns l;
        public Proxy m;
        public ProxySelector n;
        public Authenticator o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public SocketFactory f94p;
        public SSLSocketFactory q;
        public X509TrustManager r;
        public List s;
        public List t;
        public HostnameVerifier u;
        public CertificatePinner v;
        public CertificateChainCleaner w;
        public int x;
        public int y;
        public int z;

        public Builder() {
            EventListener$Companion$NONE$1 eventListener$Companion$NONE$1 = EventListener.a;
            byte[] bArr = Util.a;
            v03.j(eventListener$Companion$NONE$1, "<this>");
            this.e = new af2(14, eventListener$Companion$NONE$1);
            this.f = true;
            Authenticator authenticator = Authenticator.a;
            this.g = authenticator;
            this.h = true;
            this.i = true;
            this.j = CookieJar.a;
            this.l = Dns.a;
            this.o = authenticator;
            SocketFactory socketFactory = SocketFactory.getDefault();
            v03.i(socketFactory, "getDefault()");
            this.f94p = socketFactory;
            OkHttpClient.M.getClass();
            this.s = OkHttpClient.O;
            this.t = OkHttpClient.N;
            this.u = OkHostnameVerifier.a;
            this.v = CertificatePinner.d;
            this.y = WebgateAuthorizer.COSMOS_TIMEOUT_MS;
            this.z = WebgateAuthorizer.COSMOS_TIMEOUT_MS;
            this.A = WebgateAuthorizer.COSMOS_TIMEOUT_MS;
            this.C = 1024L;
        }

        public final void a(List list) {
            v03.j(list, "protocols");
            ArrayList arrayListO0 = ou.o0(list);
            Protocol protocol = Protocol.H2_PRIOR_KNOWLEDGE;
            if (!arrayListO0.contains(protocol) && !arrayListO0.contains(Protocol.HTTP_1_1)) {
                throw new IllegalArgumentException(("protocols must contain h2_prior_knowledge or http/1.1: " + arrayListO0).toString());
            }
            if (arrayListO0.contains(protocol) && arrayListO0.size() > 1) {
                throw new IllegalArgumentException(("protocols containing h2_prior_knowledge cannot use other protocols: " + arrayListO0).toString());
            }
            if (!(!arrayListO0.contains(Protocol.HTTP_1_0))) {
                throw new IllegalArgumentException(("protocols must not contain http/1.0: " + arrayListO0).toString());
            }
            if (!(true ^ arrayListO0.contains(null))) {
                throw new IllegalArgumentException("protocols must not contain null".toString());
            }
            arrayListO0.remove(Protocol.SPDY_3);
            if (!v03.b(arrayListO0, this.t)) {
                this.D = null;
            }
            List listUnmodifiableList = Collections.unmodifiableList(arrayListO0);
            v03.i(listUnmodifiableList, "unmodifiableList(protocolsCopy)");
            this.t = listUnmodifiableList;
        }
    }

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(int i) {
            this();
        }
    }

    public OkHttpClient(Builder builder) throws NoSuchAlgorithmException, KeyStoreException {
        ProxySelector proxySelector;
        this.c = builder.a;
        this.e = builder.b;
        this.k = Util.y(builder.c);
        this.l = Util.y(builder.d);
        this.m = builder.e;
        this.n = builder.f;
        this.o = builder.g;
        this.f93p = builder.h;
        this.q = builder.i;
        this.r = builder.j;
        this.s = builder.k;
        this.t = builder.l;
        Proxy proxy = builder.m;
        this.u = proxy;
        if (proxy != null) {
            proxySelector = NullProxySelector.a;
        } else {
            proxySelector = builder.n;
            proxySelector = proxySelector == null ? ProxySelector.getDefault() : proxySelector;
            if (proxySelector == null) {
                proxySelector = NullProxySelector.a;
            }
        }
        this.v = proxySelector;
        this.w = builder.o;
        this.x = builder.f94p;
        List list = builder.s;
        this.A = list;
        this.B = builder.t;
        this.C = builder.u;
        this.F = builder.x;
        this.G = builder.y;
        this.H = builder.z;
        this.I = builder.A;
        this.J = builder.B;
        this.K = builder.C;
        RouteDatabase routeDatabase = builder.D;
        this.L = routeDatabase == null ? new RouteDatabase() : routeDatabase;
        if ((list instanceof Collection) && list.isEmpty()) {
            this.y = null;
            this.E = null;
            this.z = null;
            this.D = CertificatePinner.d;
        } else {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                if (((ConnectionSpec) it.next()).a) {
                    SSLSocketFactory sSLSocketFactory = builder.q;
                    if (sSLSocketFactory != null) {
                        this.y = sSLSocketFactory;
                        CertificateChainCleaner certificateChainCleaner = builder.w;
                        v03.h(certificateChainCleaner);
                        this.E = certificateChainCleaner;
                        X509TrustManager x509TrustManager = builder.r;
                        v03.h(x509TrustManager);
                        this.z = x509TrustManager;
                        CertificatePinner certificatePinner = builder.v;
                        this.D = v03.b(certificatePinner.b, certificateChainCleaner) ? certificatePinner : new CertificatePinner(certificatePinner.a, certificateChainCleaner);
                    } else {
                        Platform.a.getClass();
                        X509TrustManager x509TrustManagerM = Platform.b.m();
                        this.z = x509TrustManagerM;
                        Platform platform = Platform.b;
                        v03.h(x509TrustManagerM);
                        this.y = platform.l(x509TrustManagerM);
                        CertificateChainCleaner.a.getClass();
                        CertificateChainCleaner certificateChainCleanerB = Platform.b.b(x509TrustManagerM);
                        this.E = certificateChainCleanerB;
                        CertificatePinner certificatePinner2 = builder.v;
                        v03.h(certificateChainCleanerB);
                        this.D = v03.b(certificatePinner2.b, certificateChainCleanerB) ? certificatePinner2 : new CertificatePinner(certificatePinner2.a, certificateChainCleanerB);
                    }
                }
            }
            this.y = null;
            this.E = null;
            this.z = null;
            this.D = CertificatePinner.d;
        }
        List list2 = this.k;
        v03.g(list2, "null cannot be cast to non-null type kotlin.collections.List<okhttp3.Interceptor?>");
        if (!(!list2.contains(null))) {
            throw new IllegalStateException(("Null interceptor: " + list2).toString());
        }
        List list3 = this.l;
        v03.g(list3, "null cannot be cast to non-null type kotlin.collections.List<okhttp3.Interceptor?>");
        if (!(!list3.contains(null))) {
            throw new IllegalStateException(("Null network interceptor: " + list3).toString());
        }
        List list4 = this.A;
        boolean z = list4 instanceof Collection;
        X509TrustManager x509TrustManager2 = this.z;
        CertificateChainCleaner certificateChainCleaner2 = this.E;
        SSLSocketFactory sSLSocketFactory2 = this.y;
        if (!z || !list4.isEmpty()) {
            Iterator it2 = list4.iterator();
            while (it2.hasNext()) {
                if (((ConnectionSpec) it2.next()).a) {
                    if (sSLSocketFactory2 == null) {
                        throw new IllegalStateException("sslSocketFactory == null".toString());
                    }
                    if (certificateChainCleaner2 == null) {
                        throw new IllegalStateException("certificateChainCleaner == null".toString());
                    }
                    if (x509TrustManager2 == null) {
                        throw new IllegalStateException("x509TrustManager == null".toString());
                    }
                    return;
                }
            }
        }
        if (sSLSocketFactory2 != null) {
            throw new IllegalStateException("Check failed.".toString());
        }
        if (certificateChainCleaner2 != null) {
            throw new IllegalStateException("Check failed.".toString());
        }
        if (x509TrustManager2 != null) {
            throw new IllegalStateException("Check failed.".toString());
        }
        if (!v03.b(this.D, CertificatePinner.d)) {
            throw new IllegalStateException("Check failed.".toString());
        }
    }

    @Override // okhttp3.WebSocket.Factory
    public final RealWebSocket a(Request request, f80 f80Var) {
        final RealWebSocket realWebSocket = new RealWebSocket(TaskRunner.i, request, f80Var, new Random(), this.J, this.K);
        Request request2 = realWebSocket.a;
        if (request2.c.a("Sec-WebSocket-Extensions") != null) {
            realWebSocket.j(new ProtocolException("Request header not permitted: 'Sec-WebSocket-Extensions'"), null);
        } else {
            Builder builderE = e();
            EventListener$Companion$NONE$1 eventListener$Companion$NONE$1 = EventListener.a;
            v03.j(eventListener$Companion$NONE$1, "eventListener");
            builderE.e = new af2(14, eventListener$Companion$NONE$1);
            builderE.a(RealWebSocket.x);
            OkHttpClient okHttpClient = new OkHttpClient(builderE);
            Request.Builder builderB = request2.b();
            builderB.d("Upgrade", "websocket");
            builderB.d("Connection", "Upgrade");
            builderB.d("Sec-WebSocket-Key", realWebSocket.g);
            builderB.d("Sec-WebSocket-Version", "13");
            builderB.d("Sec-WebSocket-Extensions", "permessage-deflate");
            final Request requestB = builderB.b();
            RealCall realCall = new RealCall(okHttpClient, requestB, true);
            realWebSocket.h = realCall;
            realCall.J(new Callback() { // from class: okhttp3.internal.ws.RealWebSocket$connect$1
                @Override // okhttp3.Callback
                public final void onFailure(Call call, IOException iOException) {
                    v03.j(call, "call");
                    realWebSocket.j(iOException, null);
                }

                /* JADX WARN: Multi-variable type inference failed */
                /* JADX WARN: Type inference failed for: r15v0, types: [boolean] */
                /* JADX WARN: Type inference failed for: r18v0 */
                /* JADX WARN: Type inference failed for: r18v1 */
                /* JADX WARN: Type inference failed for: r18v10 */
                /* JADX WARN: Type inference failed for: r18v11 */
                /* JADX WARN: Type inference failed for: r18v12 */
                /* JADX WARN: Type inference failed for: r18v13 */
                /* JADX WARN: Type inference failed for: r18v14 */
                /* JADX WARN: Type inference failed for: r18v15 */
                /* JADX WARN: Type inference failed for: r18v16 */
                /* JADX WARN: Type inference failed for: r18v17 */
                /* JADX WARN: Type inference failed for: r18v18 */
                /* JADX WARN: Type inference failed for: r18v19 */
                /* JADX WARN: Type inference failed for: r18v2 */
                /* JADX WARN: Type inference failed for: r18v20 */
                /* JADX WARN: Type inference failed for: r18v21 */
                /* JADX WARN: Type inference failed for: r18v22 */
                /* JADX WARN: Type inference failed for: r18v23 */
                /* JADX WARN: Type inference failed for: r18v24 */
                /* JADX WARN: Type inference failed for: r18v25 */
                /* JADX WARN: Type inference failed for: r18v26 */
                /* JADX WARN: Type inference failed for: r18v27 */
                /* JADX WARN: Type inference failed for: r18v28 */
                /* JADX WARN: Type inference failed for: r18v29 */
                /* JADX WARN: Type inference failed for: r18v3 */
                /* JADX WARN: Type inference failed for: r18v30 */
                /* JADX WARN: Type inference failed for: r18v4 */
                /* JADX WARN: Type inference failed for: r18v5 */
                /* JADX WARN: Type inference failed for: r18v6 */
                /* JADX WARN: Type inference failed for: r18v7 */
                /* JADX WARN: Type inference failed for: r18v8 */
                /* JADX WARN: Type inference failed for: r18v9 */
                /* JADX WARN: Type inference failed for: r5v0 */
                /* JADX WARN: Type inference failed for: r5v1, types: [boolean] */
                /* JADX WARN: Type inference failed for: r5v15 */
                /* JADX WARN: Type inference failed for: r5v16 */
                /* JADX WARN: Type inference failed for: r5v18 */
                /* JADX WARN: Type inference failed for: r5v4, types: [boolean, int] */
                /* JADX WARN: Type inference failed for: r5v5 */
                @Override // okhttp3.Callback
                public final void onResponse(Call call, Response response) {
                    String strB;
                    Exchange exchange = response.u;
                    ?? r5 = 1;
                    try {
                        realWebSocket.i(response, exchange);
                        RealConnection$newWebSocketStreams$1 realConnection$newWebSocketStreams$1C = exchange.c();
                        WebSocketExtensions.Companion companion = WebSocketExtensions.g;
                        Headers headers = response.n;
                        companion.getClass();
                        v03.j(headers, "responseHeaders");
                        int size = headers.size();
                        int i = 0;
                        int i2 = 0;
                        boolean z = false;
                        boolean z2 = false;
                        boolean z3 = false;
                        ?? r18 = 0;
                        Integer numY = null;
                        Integer numY2 = null;
                        while (i2 < size) {
                            if (ng2.b0(headers.b(i2), "Sec-WebSocket-Extensions", r5)) {
                                String strD = headers.d(i2);
                                int i3 = i;
                                while (i3 < strD.length()) {
                                    int iG = Util.g(strD, ',', i3, i, 4);
                                    char c = ';';
                                    int iF = Util.f(strD, ';', i3, iG);
                                    String strB2 = Util.B(strD, i3, iF);
                                    int i4 = iF + r5;
                                    if (ng2.b0(strB2, "permessage-deflate", r5)) {
                                        if (z) {
                                            r18 = r5;
                                        }
                                        i3 = i4;
                                        while (i3 < iG) {
                                            int iF2 = Util.f(strD, c, i3, iG);
                                            int iF3 = Util.f(strD, '=', i3, iF2);
                                            String strB3 = Util.B(strD, i3, iF3);
                                            if (iF3 < iF2) {
                                                strB = Util.B(strD, iF3 + 1, iF2);
                                                if (strB.length() >= 2 && b.A0(strB, "\"")) {
                                                    if (ng2.a0(strB, "\"", false)) {
                                                        strB = strB.substring(1, strB.length() - 1);
                                                        v03.i(strB, "this as java.lang.String…ing(startIndex, endIndex)");
                                                    }
                                                }
                                            } else {
                                                strB = null;
                                            }
                                            i3 = iF2 + 1;
                                            ?? r182 = r18;
                                            if (ng2.b0(strB3, "client_max_window_bits", true)) {
                                                if (numY != null) {
                                                    r182 = 1;
                                                }
                                                numY = strB != null ? mg2.Y(strB) : null;
                                                if (numY == null) {
                                                    c = ';';
                                                    r18 = 1;
                                                } else {
                                                    r18 = r182;
                                                    c = ';';
                                                }
                                            } else {
                                                if (ng2.b0(strB3, "client_no_context_takeover", true)) {
                                                    if (z2) {
                                                        r18 = 1;
                                                    }
                                                    if (strB != null) {
                                                        r18 = 1;
                                                    }
                                                    z2 = true;
                                                    r18 = r18;
                                                } else {
                                                    r182 = r18;
                                                    if (ng2.b0(strB3, "server_max_window_bits", true)) {
                                                        if (numY2 != null) {
                                                            r182 = 1;
                                                        }
                                                        numY2 = strB != null ? mg2.Y(strB) : null;
                                                        if (numY2 != null) {
                                                            r18 = r182;
                                                        }
                                                    } else if (ng2.b0(strB3, "server_no_context_takeover", true)) {
                                                        if (z3) {
                                                            r18 = 1;
                                                        }
                                                        if (strB != null) {
                                                            r18 = 1;
                                                        }
                                                        z3 = true;
                                                        r18 = r18;
                                                    }
                                                    c = ';';
                                                    r18 = 1;
                                                }
                                                c = ';';
                                            }
                                        }
                                        r5 = 1;
                                        i = 0;
                                        z = true;
                                    } else {
                                        i3 = i4;
                                        r5 = 1;
                                        i = 0;
                                        r18 = 1;
                                    }
                                }
                            }
                            i2++;
                            i = i;
                            r5 = 1;
                            r18 = r18;
                        }
                        realWebSocket.e = new WebSocketExtensions(z, numY, z2, numY2, z3, r18);
                        if (r18 != 0 || numY != null || (numY2 != null && !new i51(8, 15, 1).a(numY2.intValue()))) {
                            RealWebSocket realWebSocket2 = realWebSocket;
                            synchronized (realWebSocket2) {
                                realWebSocket2.f102p.clear();
                                realWebSocket2.b(1010, "unexpected Sec-WebSocket-Extensions in response header");
                            }
                        }
                        try {
                            realWebSocket.k(Util.g + " WebSocket " + requestB.a.g(), realConnection$newWebSocketStreams$1C);
                            RealWebSocket realWebSocket3 = realWebSocket;
                            realWebSocket3.b.f(realWebSocket3, response);
                            realWebSocket.l();
                        } catch (Exception e) {
                            realWebSocket.j(e, null);
                        }
                    } catch (IOException e2) {
                        realWebSocket.j(e2, response);
                        Util.c(response);
                        if (exchange != null) {
                            exchange.a(true, true, null);
                        }
                    }
                }
            });
        }
        return realWebSocket;
    }

    public final Object clone() {
        return super.clone();
    }

    @Override // okhttp3.Call.Factory
    public final RealCall d(Request request) {
        v03.j(request, "request");
        return new RealCall(this, request, false);
    }

    public final Builder e() {
        Builder builder = new Builder();
        builder.a = this.c;
        builder.b = this.e;
        nu.X(this.k, builder.c);
        nu.X(this.l, builder.d);
        builder.e = this.m;
        builder.f = this.n;
        builder.g = this.o;
        builder.h = this.f93p;
        builder.i = this.q;
        builder.j = this.r;
        builder.k = this.s;
        builder.l = this.t;
        builder.m = this.u;
        builder.n = this.v;
        builder.o = this.w;
        builder.f94p = this.x;
        builder.q = this.y;
        builder.r = this.z;
        builder.s = this.A;
        builder.t = this.B;
        builder.u = this.C;
        builder.v = this.D;
        builder.w = this.E;
        builder.x = this.F;
        builder.y = this.G;
        builder.z = this.H;
        builder.A = this.I;
        builder.B = this.J;
        builder.C = this.K;
        builder.D = this.L;
        return builder;
    }

    public OkHttpClient() {
        this(new Builder());
    }
}
