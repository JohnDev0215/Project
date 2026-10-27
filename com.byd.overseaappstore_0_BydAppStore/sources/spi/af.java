package spi;

import java.io.IOException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Protocol;
import okhttp3.internal.http2.ConnectionShutdownException;
import okhttp3.internal.http2.ErrorCode;
import okhttp3.internal.http2.StreamResetException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.cf;
import spi.ke;
import spi.ln;

/* JADX INFO: loaded from: classes2.dex */
public final class af implements z8 {
    public volatile cf a;
    public final Protocol b;
    public volatile boolean c;

    @NotNull
    public final wl d;
    public final bm e;
    public final re f;
    public static final a i = new a(null);
    public static final List<String> g = uu.k("connection", "host", "keep-alive", "proxy-connection", "te", "transfer-encoding", "encoding", "upgrade", ":method", ":path", ":scheme", ":authority");
    public static final List<String> h = uu.k("connection", "host", "keep-alive", "proxy-connection", "te", "transfer-encoding", "encoding", "upgrade");

    public static final class a {
        private a() {
        }

        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public af(@NotNull dk client, @NotNull wl connection, @NotNull bm chain, @NotNull re http2Connection) {
        Intrinsics.checkNotNullParameter(client, "client");
        Intrinsics.checkNotNullParameter(connection, "connection");
        Intrinsics.checkNotNullParameter(chain, "chain");
        Intrinsics.checkNotNullParameter(http2Connection, "http2Connection");
        this.d = connection;
        this.e = chain;
        this.f = http2Connection;
        List<Protocol> list = client.t;
        Protocol protocol = Protocol.H2_PRIOR_KNOWLEDGE;
        this.b = list.contains(protocol) ? protocol : Protocol.HTTP_2;
    }

    @Override // spi.z8
    public final void a() throws IOException {
        cf cfVar = this.a;
        Intrinsics.checkNotNull(cfVar);
        ((cf.b) cfVar.g()).close();
    }

    @Override // spi.z8
    @NotNull
    public final vr b(@NotNull ln response) {
        Intrinsics.checkNotNullParameter(response, "response");
        cf cfVar = this.a;
        Intrinsics.checkNotNull(cfVar);
        return cfVar.g;
    }

    @Override // spi.z8
    @Nullable
    public final ln.a c(boolean z) throws IOException {
        ke headerBlock;
        cf cfVar = this.a;
        Intrinsics.checkNotNull(cfVar);
        synchronized (cfVar) {
            cfVar.i.h();
            while (cfVar.e.isEmpty() && cfVar.k == null) {
                try {
                    cfVar.k();
                } catch (Throwable th) {
                    cfVar.i.l();
                    throw th;
                }
            }
            cfVar.i.l();
            if (!(!cfVar.e.isEmpty())) {
                IOException iOException = cfVar.l;
                if (iOException != null) {
                    throw iOException;
                }
                ErrorCode errorCode = cfVar.k;
                Intrinsics.checkNotNull(errorCode);
                throw new StreamResetException(errorCode);
            }
            ke keVarRemoveFirst = cfVar.e.removeFirst();
            Intrinsics.checkNotNullExpressionValue(keVarRemoveFirst, "headersQueue.removeFirst()");
            headerBlock = keVarRemoveFirst;
        }
        a aVar = i;
        Protocol protocol = this.b;
        Objects.requireNonNull(aVar);
        Intrinsics.checkNotNullParameter(headerBlock, "headerBlock");
        Intrinsics.checkNotNullParameter(protocol, "protocol");
        ke.a aVar2 = new ke.a();
        int length = headerBlock.a.length / 2;
        fs fsVarA = null;
        for (int i2 = 0; i2 < length; i2++) {
            String strB = headerBlock.b(i2);
            String strD = headerBlock.d(i2);
            if (Intrinsics.areEqual(strB, ":status")) {
                fsVarA = fs.d.a("HTTP/1.1 " + strD);
            } else if (!h.contains(strB)) {
                aVar2.c(strB, strD);
            }
        }
        if (fsVarA == null) {
            throw new ProtocolException("Expected ':status' header not present");
        }
        ln.a aVar3 = new ln.a();
        Intrinsics.checkNotNullParameter(protocol, "protocol");
        aVar3.b = protocol;
        aVar3.c = fsVarA.b;
        aVar3.e(fsVarA.c);
        aVar3.d(aVar2.d());
        if (z && aVar3.c == 100) {
            return null;
        }
        return aVar3;
    }

    @Override // spi.z8
    public final void cancel() {
        this.c = true;
        cf cfVar = this.a;
        if (cfVar != null) {
            cfVar.e(ErrorCode.CANCEL);
        }
    }

    @Override // spi.z8
    @NotNull
    public final wl d() {
        return this.d;
    }

    @Override // spi.z8
    public final void e() throws IOException {
        this.f.flush();
    }

    @Override // spi.z8
    @NotNull
    public final rp f(@NotNull im request, long j) {
        Intrinsics.checkNotNullParameter(request, "request");
        cf cfVar = this.a;
        Intrinsics.checkNotNull(cfVar);
        return cfVar.g();
    }

    @Override // spi.z8
    public final long g(@NotNull ln response) {
        Intrinsics.checkNotNullParameter(response, "response");
        if (ff.a(response)) {
            return uu.j(response);
        }
        return 0L;
    }

    @Override // spi.z8
    public final void h(@NotNull im request) throws IOException {
        int i2;
        cf cfVar;
        boolean z;
        Intrinsics.checkNotNullParameter(request, "request");
        if (this.a != null) {
            return;
        }
        boolean z2 = request.e != null;
        Objects.requireNonNull(i);
        Intrinsics.checkNotNullParameter(request, "request");
        ke keVar = request.d;
        ArrayList requestHeaders = new ArrayList((keVar.a.length / 2) + 4);
        requestHeaders.add(new ie(ie.f, request.c));
        requestHeaders.add(new ie(ie.g, om.a.a(request.b)));
        String strB = request.b("Host");
        if (strB != null) {
            requestHeaders.add(new ie(ie.i, strB));
        }
        requestHeaders.add(new ie(ie.h, request.b.b));
        int length = keVar.a.length / 2;
        for (int i3 = 0; i3 < length; i3++) {
            String strB2 = keVar.b(i3);
            Locale locale = Locale.US;
            Intrinsics.checkNotNullExpressionValue(locale, "Locale.US");
            Objects.requireNonNull(strB2, "null cannot be cast to non-null type java.lang.String");
            String lowerCase = strB2.toLowerCase(locale);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "(this as java.lang.String).toLowerCase(locale)");
            if (!g.contains(lowerCase) || (Intrinsics.areEqual(lowerCase, "te") && Intrinsics.areEqual(keVar.d(i3), "trailers"))) {
                requestHeaders.add(new ie(lowerCase, keVar.d(i3)));
            }
        }
        re reVar = this.f;
        Objects.requireNonNull(reVar);
        Intrinsics.checkNotNullParameter(requestHeaders, "requestHeaders");
        boolean z3 = !z2;
        synchronized (reVar.y) {
            synchronized (reVar) {
                if (reVar.f > 1073741823) {
                    reVar.F(ErrorCode.REFUSED_STREAM);
                }
                if (reVar.g) {
                    throw new ConnectionShutdownException();
                }
                i2 = reVar.f;
                reVar.f = i2 + 2;
                cfVar = new cf(i2, reVar, z3, false, null);
                z = !z2 || reVar.v >= reVar.w || cfVar.c >= cfVar.d;
                if (cfVar.i()) {
                    reVar.c.put(Integer.valueOf(i2), cfVar);
                }
                Unit unit = Unit.INSTANCE;
            }
            reVar.y.E(z3, i2, requestHeaders);
        }
        if (z) {
            reVar.y.flush();
        }
        this.a = cfVar;
        if (this.c) {
            cf cfVar2 = this.a;
            Intrinsics.checkNotNull(cfVar2);
            cfVar2.e(ErrorCode.CANCEL);
            throw new IOException("Canceled");
        }
        cf cfVar3 = this.a;
        Intrinsics.checkNotNull(cfVar3);
        cf.d dVar = cfVar3.i;
        long j = this.e.h;
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        dVar.g(j);
        cf cfVar4 = this.a;
        Intrinsics.checkNotNull(cfVar4);
        cfVar4.j.g(this.e.i);
    }
}
