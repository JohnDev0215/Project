package spi;

import java.io.IOException;
import java.lang.ref.Reference;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketException;
import java.security.Principal;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__IndentKt;
import okhttp3.Protocol;
import okhttp3.internal.http2.ErrorCode;
import org.jetbrains.annotations.NotNull;
import spi.ge;
import spi.re;

/* JADX INFO: loaded from: classes2.dex */
public final class wl extends re.d implements b4 {
    public Socket b;
    public Socket c;
    public ge d;
    public Protocol e;
    public re f;
    public ul g;
    public tl h;
    public boolean i;
    public boolean j;
    public int k;
    public int l;
    public int m;
    public int n;

    @NotNull
    public final List<Reference<vl>> o;
    public long p;
    public final rn q;

    public static final class a {
        private a() {
        }

        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    static {
        new a(null);
    }

    public wl(@NotNull am connectionPool, @NotNull rn route) {
        Intrinsics.checkNotNullParameter(connectionPool, "connectionPool");
        Intrinsics.checkNotNullParameter(route, "route");
        this.q = route;
        this.n = 1;
        this.o = new ArrayList();
        this.p = Long.MAX_VALUE;
    }

    @Override // spi.re.d
    public final synchronized void a(@NotNull re connection, @NotNull jp settings) {
        Intrinsics.checkNotNullParameter(connection, "connection");
        Intrinsics.checkNotNullParameter(settings, "settings");
        this.n = (settings.a & 16) != 0 ? settings.b[4] : Integer.MAX_VALUE;
    }

    @Override // spi.re.d
    public final void b(@NotNull cf stream) throws IOException {
        Intrinsics.checkNotNullParameter(stream, "stream");
        stream.c(ErrorCode.REFUSED_STREAM, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:104:0x017b A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0110  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x013d  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0143  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0148  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void c(int r17, int r18, int r19, int r20, boolean r21, @org.jetbrains.annotations.NotNull spi.c3 r22, @org.jetbrains.annotations.NotNull spi.u8 r23) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 405
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.wl.c(int, int, int, int, boolean, spi.c3, spi.u8):void");
    }

    public final void d(@NotNull dk client, @NotNull rn failedRoute, @NotNull IOException failure) {
        Intrinsics.checkNotNullParameter(client, "client");
        Intrinsics.checkNotNullParameter(failedRoute, "failedRoute");
        Intrinsics.checkNotNullParameter(failure, "failure");
        if (failedRoute.b.type() != Proxy.Type.DIRECT) {
            t tVar = failedRoute.a;
            tVar.k.connectFailed(tVar.a.i(), failedRoute.b.address(), failure);
        }
        sn snVar = client.D;
        synchronized (snVar) {
            Intrinsics.checkNotNullParameter(failedRoute, "failedRoute");
            snVar.a.add(failedRoute);
        }
    }

    public final void e(int i, int i2, c3 call, u8 u8Var) throws IOException {
        Socket socket;
        int i3;
        rn rnVar = this.q;
        Proxy proxy = rnVar.b;
        t tVar = rnVar.a;
        Proxy.Type type = proxy.type();
        if (type != null && ((i3 = xl.a[type.ordinal()]) == 1 || i3 == 2)) {
            socket = tVar.e.createSocket();
            Intrinsics.checkNotNull(socket);
        } else {
            socket = new Socket(proxy);
        }
        this.b = socket;
        InetSocketAddress inetSocketAddress = this.q.c;
        Objects.requireNonNull(u8Var);
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(inetSocketAddress, "inetSocketAddress");
        Intrinsics.checkNotNullParameter(proxy, "proxy");
        socket.setSoTimeout(i2);
        try {
            Objects.requireNonNull(zk.c);
            zk.a.e(socket, this.q.c, i);
            try {
                this.g = (ul) b.c(b.m(socket));
                this.h = (tl) b.b(b.j(socket));
            } catch (NullPointerException e) {
                if (Intrinsics.areEqual(e.getMessage(), "throw with null exception")) {
                    throw new IOException(e);
                }
            }
        } catch (ConnectException e2) {
            StringBuilder sbK = e.k("Failed to connect to ");
            sbK.append(this.q.c);
            ConnectException connectException = new ConnectException(sbK.toString());
            connectException.initCause(e2);
            throw connectException;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x0170, code lost:
    
        if (r3 == null) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0172, code lost:
    
        r5 = r18.b;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0174, code lost:
    
        if (r5 == null) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0176, code lost:
    
        spi.uu.d(r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0179, code lost:
    
        r5 = null;
        r18.b = null;
        r18.h = null;
        r18.g = null;
        r6 = r18.q;
        r9 = r6.c;
        r6 = r6.b;
        kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r22, "call");
        kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, "inetSocketAddress");
        kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, "proxy");
        r8 = r8 + 1;
        r6 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:?, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void f(int r19, int r20, int r21, spi.c3 r22, spi.u8 r23) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 419
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.wl.f(int, int, int, spi.c3, spi.u8):void");
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
    public final void g(e4 e4Var, int i, c3 call, u8 u8Var) throws Throwable {
        t tVar = this.q.a;
        if (tVar.f == null) {
            List<Protocol> list = tVar.b;
            Protocol protocol = Protocol.H2_PRIOR_KNOWLEDGE;
            if (!list.contains(protocol)) {
                this.c = this.b;
                this.e = Protocol.HTTP_1_1;
                return;
            } else {
                this.c = this.b;
                this.e = protocol;
                m(i);
                return;
            }
        }
        Objects.requireNonNull(u8Var);
        Intrinsics.checkNotNullParameter(call, "call");
        t tVar2 = this.q.a;
        SSLSocketFactory sSLSocketFactory = tVar2.f;
        SSLSocket sSLSocket = null;
        String strF = null;
        try {
            Intrinsics.checkNotNull(sSLSocketFactory);
            Socket socket = this.b;
            hf hfVar = tVar2.a;
            Socket socketCreateSocket = sSLSocketFactory.createSocket(socket, hfVar.e, hfVar.f, true);
            if (socketCreateSocket == null) {
                throw new NullPointerException("null cannot be cast to non-null type javax.net.ssl.SSLSocket");
            }
            SSLSocket sSLSocket2 = (SSLSocket) socketCreateSocket;
            try {
                d4 d4VarA = e4Var.a(sSLSocket2);
                if (d4VarA.b) {
                    Objects.requireNonNull(zk.c);
                    zk.a.d(sSLSocket2, tVar2.a.e, tVar2.b);
                }
                sSLSocket2.startHandshake();
                SSLSession sslSocketSession = sSLSocket2.getSession();
                ge.a aVar = ge.e;
                Intrinsics.checkNotNullExpressionValue(sslSocketSession, "sslSocketSession");
                ge geVarA = aVar.a(sslSocketSession);
                HostnameVerifier hostnameVerifier = tVar2.g;
                Intrinsics.checkNotNull(hostnameVerifier);
                if (hostnameVerifier.verify(tVar2.a.e, sslSocketSession)) {
                    m3 m3Var = tVar2.h;
                    Intrinsics.checkNotNull(m3Var);
                    this.d = new ge(geVarA.b, geVarA.c, geVarA.d, new yl(m3Var, geVarA, tVar2));
                    m3Var.a(tVar2.a.e, new zl(this));
                    if (d4VarA.b) {
                        Objects.requireNonNull(zk.c);
                        strF = zk.a.f(sSLSocket2);
                    }
                    this.c = sSLSocket2;
                    this.g = (ul) b.c(b.m(sSLSocket2));
                    this.h = (tl) b.b(b.j(sSLSocket2));
                    this.e = strF != null ? Protocol.INSTANCE.a(strF) : Protocol.HTTP_1_1;
                    Objects.requireNonNull(zk.c);
                    zk.a.a(sSLSocket2);
                    Intrinsics.checkNotNullParameter(call, "call");
                    if (this.e == Protocol.HTTP_2) {
                        m(i);
                        return;
                    }
                    return;
                }
                List<Certificate> listB = geVarA.b();
                if (!(!listB.isEmpty())) {
                    throw new SSLPeerUnverifiedException("Hostname " + tVar2.a.e + " not verified (no certificates)");
                }
                Certificate certificate = listB.get(0);
                if (certificate == null) {
                    throw new NullPointerException("null cannot be cast to non-null type java.security.cert.X509Certificate");
                }
                X509Certificate certificate2 = (X509Certificate) certificate;
                StringBuilder sb = new StringBuilder();
                sb.append("\n              |Hostname ");
                sb.append(tVar2.a.e);
                sb.append(" not verified:\n              |    certificate: ");
                sb.append(m3.d.a(certificate2));
                sb.append("\n              |    DN: ");
                Principal subjectDN = certificate2.getSubjectDN();
                Intrinsics.checkNotNullExpressionValue(subjectDN, "cert.subjectDN");
                sb.append(subjectDN.getName());
                sb.append("\n              |    subjectAltNames: ");
                ck ckVar = ck.a;
                Objects.requireNonNull(ckVar);
                Intrinsics.checkNotNullParameter(certificate2, "certificate");
                sb.append(CollectionsKt.plus((Collection) ckVar.b(certificate2, 7), (Iterable) ckVar.b(certificate2, 2)));
                sb.append("\n              ");
                throw new SSLPeerUnverifiedException(StringsKt__IndentKt.trimMargin$default(sb.toString(), null, 1, null));
            } catch (Throwable th) {
                th = th;
                sSLSocket = sSLSocket2;
                if (sSLSocket != null) {
                    Objects.requireNonNull(zk.c);
                    zk.a.a(sSLSocket);
                }
                if (sSLSocket != null) {
                    uu.d(sSLSocket);
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00d9 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00da A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.util.ArrayList, java.util.List<java.lang.ref.Reference<spi.vl>>] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean h(@org.jetbrains.annotations.NotNull spi.t r7, @org.jetbrains.annotations.Nullable java.util.List<spi.rn> r8) {
        /*
            Method dump skipped, instruction units count: 259
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.wl.h(spi.t, java.util.List):boolean");
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0046, code lost:
    
        if (r0 >= r2.q) goto L22;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean i(boolean r10) {
        /*
            r9 = this;
            byte[] r0 = spi.uu.a
            long r0 = java.lang.System.nanoTime()
            java.net.Socket r2 = r9.b
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
            java.net.Socket r3 = r9.c
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
            spi.ul r4 = r9.g
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4)
            boolean r2 = r2.isClosed()
            r5 = 0
            if (r2 != 0) goto L86
            boolean r2 = r3.isClosed()
            if (r2 != 0) goto L86
            boolean r2 = r3.isInputShutdown()
            if (r2 != 0) goto L86
            boolean r2 = r3.isOutputShutdown()
            if (r2 == 0) goto L2f
            goto L86
        L2f:
            spi.re r2 = r9.f
            r6 = 1
            if (r2 == 0) goto L50
            monitor-enter(r2)
            boolean r10 = r2.g     // Catch: java.lang.Throwable -> L4d
            if (r10 == 0) goto L3a
            goto L48
        L3a:
            long r3 = r2.p     // Catch: java.lang.Throwable -> L4d
            long r7 = r2.o     // Catch: java.lang.Throwable -> L4d
            int r10 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
            if (r10 >= 0) goto L4a
            long r3 = r2.q     // Catch: java.lang.Throwable -> L4d
            int r10 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r10 < 0) goto L4a
        L48:
            monitor-exit(r2)
            goto L4c
        L4a:
            monitor-exit(r2)
            r5 = r6
        L4c:
            return r5
        L4d:
            r10 = move-exception
            monitor-exit(r2)
            throw r10
        L50:
            monitor-enter(r9)
            long r7 = r9.p     // Catch: java.lang.Throwable -> L83
            long r0 = r0 - r7
            monitor-exit(r9)
            r7 = 10000000000(0x2540be400, double:4.9406564584E-314)
            int r0 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1))
            if (r0 < 0) goto L82
            if (r10 == 0) goto L82
            java.lang.String r10 = "$this$isHealthy"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r10)
            java.lang.String r10 = "source"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r10)
            int r10 = r3.getSoTimeout()     // Catch: java.net.SocketTimeoutException -> L80 java.io.IOException -> L81
            r3.setSoTimeout(r6)     // Catch: java.lang.Throwable -> L7b
            boolean r0 = r4.h()     // Catch: java.lang.Throwable -> L7b
            r0 = r0 ^ r6
            r3.setSoTimeout(r10)     // Catch: java.net.SocketTimeoutException -> L80 java.io.IOException -> L81
            r5 = r0
            goto L81
        L7b:
            r0 = move-exception
            r3.setSoTimeout(r10)     // Catch: java.net.SocketTimeoutException -> L80 java.io.IOException -> L81
            throw r0     // Catch: java.net.SocketTimeoutException -> L80 java.io.IOException -> L81
        L80:
            r5 = r6
        L81:
            return r5
        L82:
            return r6
        L83:
            r10 = move-exception
            monitor-exit(r9)
            throw r10
        L86:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.wl.i(boolean):boolean");
    }

    public final boolean j() {
        return this.f != null;
    }

    @NotNull
    public final z8 k(@NotNull dk client, @NotNull bm chain) throws SocketException {
        Intrinsics.checkNotNullParameter(client, "client");
        Intrinsics.checkNotNullParameter(chain, "chain");
        Socket socket = this.c;
        Intrinsics.checkNotNull(socket);
        ul ulVar = this.g;
        Intrinsics.checkNotNull(ulVar);
        tl tlVar = this.h;
        Intrinsics.checkNotNull(tlVar);
        re reVar = this.f;
        if (reVar != null) {
            return new af(client, this, chain, reVar);
        }
        socket.setSoTimeout(chain.h);
        dt dtVarTimeout = ulVar.timeout();
        long j = chain.h;
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        dtVarTimeout.g(j);
        tlVar.timeout().g(chain.i);
        return new pe(client, this, ulVar, tlVar);
    }

    public final synchronized void l() {
        this.i = true;
    }

    public final void m(int i) throws IOException {
        StringBuilder sbK;
        Socket socket = this.c;
        Intrinsics.checkNotNull(socket);
        ul source = this.g;
        Intrinsics.checkNotNull(source);
        tl sink = this.h;
        Intrinsics.checkNotNull(sink);
        socket.setSoTimeout(0);
        ws taskRunner = ws.h;
        re.b bVar = new re.b(true, taskRunner);
        String peerName = this.q.a.a.e;
        Intrinsics.checkNotNullParameter(socket, "socket");
        Intrinsics.checkNotNullParameter(peerName, "peerName");
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(sink, "sink");
        bVar.a = socket;
        if (bVar.h) {
            sbK = new StringBuilder();
            sbK.append(uu.g);
            sbK.append(' ');
        } else {
            sbK = e.k("MockWebServer ");
        }
        sbK.append(peerName);
        bVar.b = sbK.toString();
        bVar.c = source;
        bVar.d = sink;
        Intrinsics.checkNotNullParameter(this, "listener");
        bVar.e = this;
        bVar.g = i;
        re reVar = new re(bVar);
        this.f = reVar;
        Objects.requireNonNull(re.C);
        jp jpVar = re.B;
        this.n = (jpVar.a & 16) != 0 ? jpVar.b[4] : Integer.MAX_VALUE;
        Intrinsics.checkNotNullParameter(taskRunner, "taskRunner");
        df dfVar = reVar.y;
        synchronized (dfVar) {
            if (dfVar.c) {
                throw new IOException("closed");
            }
            if (dfVar.f) {
                Logger logger = df.g;
                if (logger.isLoggable(Level.FINE)) {
                    logger.fine(uu.h(">> CONNECTION " + qe.a.hex(), new Object[0]));
                }
                dfVar.e.u(qe.a);
                dfVar.e.flush();
            }
        }
        df dfVar2 = reVar.y;
        jp settings = reVar.r;
        synchronized (dfVar2) {
            Intrinsics.checkNotNullParameter(settings, "settings");
            if (dfVar2.c) {
                throw new IOException("closed");
            }
            dfVar2.C(0, Integer.bitCount(settings.a) * 6, 4, 0);
            int i2 = 0;
            while (i2 < 10) {
                if (((1 << i2) & settings.a) != 0) {
                    dfVar2.e.c(i2 != 4 ? i2 != 7 ? i2 : 4 : 3);
                    dfVar2.e.d(settings.b[i2]);
                }
                i2++;
            }
            dfVar2.e.flush();
        }
        if (reVar.r.a() != 65535) {
            reVar.y.H(0, r0 - 65535);
        }
        vs vsVarF = taskRunner.f();
        String str = reVar.d;
        vsVarF.c(new us(reVar.z, str, true, str, true), 0L);
    }

    @NotNull
    public final String toString() {
        Object obj;
        StringBuilder sbK = e.k("Connection{");
        sbK.append(this.q.a.a.e);
        sbK.append(':');
        sbK.append(this.q.a.a.f);
        sbK.append(',');
        sbK.append(" proxy=");
        sbK.append(this.q.b);
        sbK.append(" hostAddress=");
        sbK.append(this.q.c);
        sbK.append(" cipherSuite=");
        ge geVar = this.d;
        if (geVar == null || (obj = geVar.c) == null) {
            obj = "none";
        }
        sbK.append(obj);
        sbK.append(" protocol=");
        sbK.append(this.e);
        sbK.append('}');
        return sbK.toString();
    }
}
