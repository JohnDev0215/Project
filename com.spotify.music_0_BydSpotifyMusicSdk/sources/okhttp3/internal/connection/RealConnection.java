package okhttp3.internal.connection;

import com.spotify.playbacknative.AudioDriver;
import java.io.IOException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import kotlin.text.a;
import okhttp3.Address;
import okhttp3.CertificatePinner;
import okhttp3.Connection;
import okhttp3.ConnectionSpec;
import okhttp3.EventListener;
import okhttp3.Handshake;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Route;
import okhttp3.internal.Util;
import okhttp3.internal.concurrent.Task;
import okhttp3.internal.concurrent.TaskQueue;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.http.ExchangeCodec;
import okhttp3.internal.http.RealInterceptorChain;
import okhttp3.internal.http1.Http1ExchangeCodec;
import okhttp3.internal.http2.ErrorCode;
import okhttp3.internal.http2.Http2;
import okhttp3.internal.http2.Http2Connection;
import okhttp3.internal.http2.Http2ExchangeCodec;
import okhttp3.internal.http2.Http2Stream;
import okhttp3.internal.http2.Http2Writer;
import okhttp3.internal.http2.Settings;
import okhttp3.internal.platform.Platform;
import okhttp3.internal.tls.CertificateChainCleaner;
import okhttp3.internal.tls.OkHostnameVerifier;
import okio.ByteString;
import p.lu;
import p.ou;
import p.py0;
import p.qo;
import p.v02;
import p.v03;
import p.w02;

/* JADX INFO: loaded from: classes.dex */
public final class RealConnection extends Http2Connection.Listener implements Connection {
    public final Route b;
    public Socket c;
    public Socket d;
    public Handshake e;
    public Protocol f;
    public Http2Connection g;
    public w02 h;
    public v02 i;
    public boolean j;
    public boolean k;
    public int l;
    public int m;
    public int n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final ArrayList f99p;
    public long q;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(int i) {
            this();
        }
    }

    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[Proxy.Type.values().length];
            try {
                iArr[Proxy.Type.DIRECT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Proxy.Type.HTTP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            a = iArr;
        }
    }

    static {
        new Companion(0);
    }

    public RealConnection(RealConnectionPool realConnectionPool, Route route) {
        v03.j(realConnectionPool, "connectionPool");
        v03.j(route, "route");
        this.b = route;
        this.o = 1;
        this.f99p = new ArrayList();
        this.q = Long.MAX_VALUE;
    }

    public static void d(OkHttpClient okHttpClient, Route route, IOException iOException) {
        v03.j(okHttpClient, "client");
        v03.j(route, "failedRoute");
        v03.j(iOException, "failure");
        if (route.b.type() != Proxy.Type.DIRECT) {
            Address address = route.a;
            address.h.connectFailed(address.i.h(), route.b.address(), iOException);
        }
        RouteDatabase routeDatabase = okHttpClient.L;
        synchronized (routeDatabase) {
            routeDatabase.a.add(route);
        }
    }

    @Override // okhttp3.internal.http2.Http2Connection.Listener
    public final synchronized void a(Http2Connection http2Connection, Settings settings) {
        v03.j(http2Connection, "connection");
        v03.j(settings, "settings");
        this.o = (settings.a & 16) != 0 ? settings.b[4] : Integer.MAX_VALUE;
    }

    @Override // okhttp3.internal.http2.Http2Connection.Listener
    public final void b(Http2Stream http2Stream) {
        v03.j(http2Stream, "stream");
        http2Stream.c(ErrorCode.REFUSED_STREAM, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:51:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x011d  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0123  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0128  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x014c A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void c(int r16, int r17, int r18, int r19, boolean r20, okhttp3.internal.connection.RealCall r21, okhttp3.EventListener r22) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 358
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.connection.RealConnection.c(int, int, int, int, boolean, okhttp3.internal.connection.RealCall, okhttp3.EventListener):void");
    }

    public final void e(int i, int i2, RealCall realCall, EventListener eventListener) throws IOException {
        Socket socketCreateSocket;
        Route route = this.b;
        Proxy proxy = route.b;
        Address address = route.a;
        Proxy.Type type = proxy.type();
        int i3 = type == null ? -1 : WhenMappings.a[type.ordinal()];
        if (i3 == 1 || i3 == 2) {
            socketCreateSocket = address.b.createSocket();
            v03.h(socketCreateSocket);
        } else {
            socketCreateSocket = new Socket(proxy);
        }
        this.c = socketCreateSocket;
        InetSocketAddress inetSocketAddress = this.b.c;
        eventListener.getClass();
        v03.j(realCall, "call");
        v03.j(inetSocketAddress, "inetSocketAddress");
        socketCreateSocket.setSoTimeout(i2);
        try {
            Platform.a.getClass();
            Platform.b.e(socketCreateSocket, this.b.c, i);
            try {
                this.h = v03.d(v03.M(socketCreateSocket));
                this.i = v03.c(v03.J(socketCreateSocket));
            } catch (NullPointerException e) {
                if (v03.b(e.getMessage(), "throw with null exception")) {
                    throw new IOException(e);
                }
            }
        } catch (ConnectException e2) {
            ConnectException connectException = new ConnectException("Failed to connect to " + this.b.c);
            connectException.initCause(e2);
            throw connectException;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x0140, code lost:
    
        if (r11 != null) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0143, code lost:
    
        r6 = r17.c;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0145, code lost:
    
        if (r6 == null) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0147, code lost:
    
        okhttp3.internal.Util.d(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x014a, code lost:
    
        r6 = null;
        r17.c = null;
        r17.i = null;
        r17.h = null;
        r7 = okhttp3.EventListener.a;
        p.v03.j(r21, "call");
        p.v03.j(r4.c, "inetSocketAddress");
        p.v03.j(r4.b, "proxy");
        r10 = r10 + 1;
        r1 = r19;
        r8 = r12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:?, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void f(int r18, int r19, int r20, okhttp3.internal.connection.RealCall r21, okhttp3.EventListener r22) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 374
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.connection.RealConnection.f(int, int, int, okhttp3.internal.connection.RealCall, okhttp3.EventListener):void");
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void g(ConnectionSpecSelector connectionSpecSelector, int i, RealCall realCall, EventListener eventListener) throws Throwable {
        Protocol protocolA;
        Address address = this.b.a;
        if (address.c == null) {
            List list = address.j;
            Protocol protocol = Protocol.H2_PRIOR_KNOWLEDGE;
            if (!list.contains(protocol)) {
                this.d = this.c;
                this.f = Protocol.HTTP_1_1;
                return;
            } else {
                this.d = this.c;
                this.f = protocol;
                l(i);
                return;
            }
        }
        eventListener.getClass();
        v03.j(realCall, "call");
        final Address address2 = this.b.a;
        SSLSocketFactory sSLSocketFactory = address2.c;
        SSLSocket sSLSocket = null;
        String strF = null;
        try {
            v03.h(sSLSocketFactory);
            Socket socket = this.c;
            HttpUrl httpUrl = address2.i;
            Socket socketCreateSocket = sSLSocketFactory.createSocket(socket, httpUrl.d, httpUrl.e, true);
            v03.g(socketCreateSocket, "null cannot be cast to non-null type javax.net.ssl.SSLSocket");
            SSLSocket sSLSocket2 = (SSLSocket) socketCreateSocket;
            try {
                ConnectionSpec connectionSpecA = connectionSpecSelector.a(sSLSocket2);
                if (connectionSpecA.b) {
                    Platform.a.getClass();
                    Platform.b.d(sSLSocket2, address2.i.d, address2.j);
                }
                sSLSocket2.startHandshake();
                SSLSession session = sSLSocket2.getSession();
                Handshake.Companion companion = Handshake.e;
                v03.i(session, "sslSocketSession");
                companion.getClass();
                final Handshake handshakeA = Handshake.Companion.a(session);
                HostnameVerifier hostnameVerifier = address2.d;
                v03.h(hostnameVerifier);
                if (hostnameVerifier.verify(address2.i.d, session)) {
                    final CertificatePinner certificatePinner = address2.e;
                    v03.h(certificatePinner);
                    this.e = new Handshake(handshakeA.a, handshakeA.b, handshakeA.c, new py0() { // from class: okhttp3.internal.connection.RealConnection$connectTls$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        @Override // p.py0
                        public final Object invoke() {
                            CertificateChainCleaner certificateChainCleaner = certificatePinner.b;
                            v03.h(certificateChainCleaner);
                            return certificateChainCleaner.a(address2.i.d, handshakeA.a());
                        }
                    });
                    certificatePinner.b(address2.i.d, new py0() { // from class: okhttp3.internal.connection.RealConnection$connectTls$2
                        {
                            super(0);
                        }

                        @Override // p.py0
                        public final Object invoke() {
                            Handshake handshake = this.this$0.e;
                            v03.h(handshake);
                            List<Certificate> listA = handshake.a();
                            ArrayList arrayList = new ArrayList(lu.W(listA));
                            for (Certificate certificate : listA) {
                                v03.g(certificate, "null cannot be cast to non-null type java.security.cert.X509Certificate");
                                arrayList.add((X509Certificate) certificate);
                            }
                            return arrayList;
                        }
                    });
                    if (connectionSpecA.b) {
                        Platform.a.getClass();
                        strF = Platform.b.f(sSLSocket2);
                    }
                    this.d = sSLSocket2;
                    this.h = v03.d(v03.M(sSLSocket2));
                    this.i = v03.c(v03.J(sSLSocket2));
                    if (strF != null) {
                        Protocol.c.getClass();
                        protocolA = Protocol.Companion.a(strF);
                    } else {
                        protocolA = Protocol.HTTP_1_1;
                    }
                    this.f = protocolA;
                    Platform.a.getClass();
                    Platform.b.a(sSLSocket2);
                    if (this.f == Protocol.HTTP_2) {
                        l(i);
                        return;
                    }
                    return;
                }
                List listA = handshakeA.a();
                if (!(!listA.isEmpty())) {
                    throw new SSLPeerUnverifiedException("Hostname " + address2.i.d + " not verified (no certificates)");
                }
                Object obj = listA.get(0);
                v03.g(obj, "null cannot be cast to non-null type java.security.cert.X509Certificate");
                X509Certificate x509Certificate = (X509Certificate) obj;
                StringBuilder sb = new StringBuilder("\n              |Hostname ");
                sb.append(address2.i.d);
                sb.append(" not verified:\n              |    certificate: ");
                CertificatePinner.c.getClass();
                StringBuilder sb2 = new StringBuilder("sha256/");
                ByteString byteString = ByteString.k;
                byte[] encoded = x509Certificate.getPublicKey().getEncoded();
                v03.i(encoded, "publicKey.encoded");
                sb2.append(qo.s(encoded).c("SHA-256").a());
                sb.append(sb2.toString());
                sb.append("\n              |    DN: ");
                sb.append(x509Certificate.getSubjectDN().getName());
                sb.append("\n              |    subjectAltNames: ");
                OkHostnameVerifier.a.getClass();
                sb.append(ou.k0(OkHostnameVerifier.a(x509Certificate, 2), OkHostnameVerifier.a(x509Certificate, 7)));
                sb.append("\n              ");
                throw new SSLPeerUnverifiedException(a.X(sb.toString()));
            } catch (Throwable th) {
                th = th;
                sSLSocket = sSLSocket2;
                if (sSLSocket != null) {
                    Platform.a.getClass();
                    Platform.b.a(sSLSocket);
                }
                if (sSLSocket != null) {
                    Util.d(sSLSocket);
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:50:0x00b4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean h(okhttp3.Address r9, java.util.List r10) {
        /*
            r8 = this;
            java.lang.String r0 = "address"
            p.v03.j(r9, r0)
            byte[] r0 = okhttp3.internal.Util.a
            java.util.ArrayList r0 = r8.f99p
            int r0 = r0.size()
            int r1 = r8.o
            r2 = 0
            if (r0 >= r1) goto Lc6
            boolean r0 = r8.j
            if (r0 == 0) goto L18
            goto Lc6
        L18:
            okhttp3.Route r0 = r8.b
            okhttp3.Address r1 = r0.a
            boolean r1 = r1.a(r9)
            if (r1 != 0) goto L23
            return r2
        L23:
            okhttp3.HttpUrl r1 = r9.i
            java.lang.String r3 = r1.d
            okhttp3.Address r4 = r0.a
            okhttp3.HttpUrl r5 = r4.i
            java.lang.String r5 = r5.d
            boolean r3 = p.v03.b(r3, r5)
            r5 = 1
            if (r3 == 0) goto L35
            return r5
        L35:
            okhttp3.internal.http2.Http2Connection r3 = r8.g
            if (r3 != 0) goto L3a
            return r2
        L3a:
            if (r10 == 0) goto Lc6
            boolean r3 = r10.isEmpty()
            if (r3 == 0) goto L44
            goto Lc6
        L44:
            java.util.Iterator r10 = r10.iterator()
        L48:
            boolean r3 = r10.hasNext()
            if (r3 == 0) goto Lc6
            java.lang.Object r3 = r10.next()
            okhttp3.Route r3 = (okhttp3.Route) r3
            java.net.Proxy r6 = r3.b
            java.net.Proxy$Type r6 = r6.type()
            java.net.Proxy$Type r7 = java.net.Proxy.Type.DIRECT
            if (r6 != r7) goto L48
            java.net.Proxy r6 = r0.b
            java.net.Proxy$Type r6 = r6.type()
            if (r6 != r7) goto L48
            java.net.InetSocketAddress r3 = r3.c
            java.net.InetSocketAddress r6 = r0.c
            boolean r3 = p.v03.b(r6, r3)
            if (r3 == 0) goto L48
            okhttp3.internal.tls.OkHostnameVerifier r10 = okhttp3.internal.tls.OkHostnameVerifier.a
            javax.net.ssl.HostnameVerifier r0 = r9.d
            if (r0 == r10) goto L77
            return r2
        L77:
            byte[] r0 = okhttp3.internal.Util.a
            okhttp3.HttpUrl r0 = r4.i
            int r3 = r0.e
            int r4 = r1.e
            if (r4 == r3) goto L82
            goto Lc6
        L82:
            java.lang.String r0 = r0.d
            java.lang.String r1 = r1.d
            boolean r0 = p.v03.b(r1, r0)
            if (r0 == 0) goto L8d
            goto Lb4
        L8d:
            boolean r0 = r8.k
            if (r0 != 0) goto Lc6
            okhttp3.Handshake r0 = r8.e
            if (r0 == 0) goto Lc6
            java.util.List r0 = r0.a()
            boolean r3 = r0.isEmpty()
            r3 = r3 ^ r5
            if (r3 == 0) goto Lc6
            java.lang.Object r0 = r0.get(r2)
            java.lang.String r3 = "null cannot be cast to non-null type java.security.cert.X509Certificate"
            p.v03.g(r0, r3)
            java.security.cert.X509Certificate r0 = (java.security.cert.X509Certificate) r0
            r10.getClass()
            boolean r10 = okhttp3.internal.tls.OkHostnameVerifier.c(r1, r0)
            if (r10 == 0) goto Lc6
        Lb4:
            okhttp3.CertificatePinner r9 = r9.e     // Catch: javax.net.ssl.SSLPeerUnverifiedException -> Lc6
            p.v03.h(r9)     // Catch: javax.net.ssl.SSLPeerUnverifiedException -> Lc6
            okhttp3.Handshake r10 = r8.e     // Catch: javax.net.ssl.SSLPeerUnverifiedException -> Lc6
            p.v03.h(r10)     // Catch: javax.net.ssl.SSLPeerUnverifiedException -> Lc6
            java.util.List r10 = r10.a()     // Catch: javax.net.ssl.SSLPeerUnverifiedException -> Lc6
            r9.a(r1, r10)     // Catch: javax.net.ssl.SSLPeerUnverifiedException -> Lc6
            return r5
        Lc6:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.connection.RealConnection.h(okhttp3.Address, java.util.List):boolean");
    }

    public final boolean i(boolean z) {
        long j;
        byte[] bArr = Util.a;
        long jNanoTime = System.nanoTime();
        Socket socket = this.c;
        v03.h(socket);
        Socket socket2 = this.d;
        v03.h(socket2);
        w02 w02Var = this.h;
        v03.h(w02Var);
        if (socket.isClosed() || socket2.isClosed() || socket2.isInputShutdown() || socket2.isOutputShutdown()) {
            return false;
        }
        Http2Connection http2Connection = this.g;
        if (http2Connection != null) {
            synchronized (http2Connection) {
                if (http2Connection.o) {
                    return false;
                }
                if (http2Connection.x < http2Connection.w) {
                    if (jNanoTime >= http2Connection.y) {
                        return false;
                    }
                }
                return true;
            }
        }
        synchronized (this) {
            j = jNanoTime - this.q;
        }
        if (j < 10000000000L || !z) {
            return true;
        }
        try {
            int soTimeout = socket2.getSoTimeout();
            try {
                socket2.setSoTimeout(1);
                boolean z2 = !w02Var.Q();
                socket2.setSoTimeout(soTimeout);
                return z2;
            } catch (Throwable th) {
                socket2.setSoTimeout(soTimeout);
                throw th;
            }
        } catch (SocketTimeoutException unused) {
            return true;
        } catch (IOException unused2) {
            return false;
        }
    }

    public final ExchangeCodec j(OkHttpClient okHttpClient, RealInterceptorChain realInterceptorChain) throws SocketException {
        Socket socket = this.d;
        v03.h(socket);
        w02 w02Var = this.h;
        v03.h(w02Var);
        v02 v02Var = this.i;
        v03.h(v02Var);
        Http2Connection http2Connection = this.g;
        if (http2Connection != null) {
            return new Http2ExchangeCodec(okHttpClient, this, realInterceptorChain, http2Connection);
        }
        int i = realInterceptorChain.g;
        socket.setSoTimeout(i);
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        w02Var.c.e().g(i, timeUnit);
        v02Var.c.e().g(realInterceptorChain.h, timeUnit);
        return new Http1ExchangeCodec(okHttpClient, this, w02Var, v02Var);
    }

    public final synchronized void k() {
        this.j = true;
    }

    public final void l(int i) throws SocketException {
        String strConcat;
        Socket socket = this.d;
        v03.h(socket);
        w02 w02Var = this.h;
        v03.h(w02Var);
        v02 v02Var = this.i;
        v03.h(v02Var);
        socket.setSoTimeout(0);
        TaskRunner taskRunner = TaskRunner.i;
        Http2Connection.Builder builder = new Http2Connection.Builder(taskRunner);
        String str = this.b.a.i.d;
        v03.j(str, "peerName");
        builder.c = socket;
        if (builder.a) {
            strConcat = Util.g + ' ' + str;
        } else {
            strConcat = "MockWebServer ".concat(str);
        }
        v03.j(strConcat, "<set-?>");
        builder.d = strConcat;
        builder.e = w02Var;
        builder.f = v02Var;
        builder.g = this;
        builder.i = i;
        Http2Connection http2Connection = new Http2Connection(builder);
        this.g = http2Connection;
        Http2Connection.J.getClass();
        Settings settings = Http2Connection.K;
        this.o = (settings.a & 16) != 0 ? settings.b[4] : Integer.MAX_VALUE;
        Http2Writer http2Writer = http2Connection.G;
        synchronized (http2Writer) {
            try {
                if (http2Writer.m) {
                    throw new IOException("closed");
                }
                if (http2Writer.e) {
                    Logger logger = Http2Writer.o;
                    if (logger.isLoggable(Level.FINE)) {
                        logger.fine(Util.i(">> CONNECTION " + Http2.b.f(), new Object[0]));
                    }
                    http2Writer.c.a0(Http2.b);
                    http2Writer.c.flush();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        Http2Writer http2Writer2 = http2Connection.G;
        Settings settings2 = http2Connection.z;
        synchronized (http2Writer2) {
            try {
                v03.j(settings2, "settings");
                if (http2Writer2.m) {
                    throw new IOException("closed");
                }
                http2Writer2.i(0, Integer.bitCount(settings2.a) * 6, 4, 0);
                int i2 = 0;
                while (i2 < 10) {
                    if (((1 << i2) & settings2.a) != 0) {
                        http2Writer2.c.C(i2 != 4 ? i2 != 7 ? i2 : 4 : 3);
                        http2Writer2.c.I(settings2.b[i2]);
                    }
                    i2++;
                }
                http2Writer2.c.flush();
            } catch (Throwable th2) {
                throw th2;
            }
        }
        if (http2Connection.z.a() != 65535) {
            http2Connection.G.X(0, r0 - AudioDriver.SPOTIFY_MAX_VOLUME);
        }
        TaskQueue taskQueueF = taskRunner.f();
        final String str2 = http2Connection.l;
        final Http2Connection.ReaderRunnable readerRunnable = http2Connection.H;
        taskQueueF.c(new Task(str2) { // from class: okhttp3.internal.concurrent.TaskQueue$execute$1
            @Override // okhttp3.internal.concurrent.Task
            public final long a() {
                readerRunnable.invoke();
                return -1L;
            }
        }, 0L);
    }

    public final String toString() {
        Object obj;
        StringBuilder sb = new StringBuilder("Connection{");
        Route route = this.b;
        sb.append(route.a.i.d);
        sb.append(':');
        sb.append(route.a.i.e);
        sb.append(", proxy=");
        sb.append(route.b);
        sb.append(" hostAddress=");
        sb.append(route.c);
        sb.append(" cipherSuite=");
        Handshake handshake = this.e;
        if (handshake == null || (obj = handshake.b) == null) {
            obj = "none";
        }
        sb.append(obj);
        sb.append(" protocol=");
        sb.append(this.f);
        sb.append('}');
        return sb.toString();
    }
}
