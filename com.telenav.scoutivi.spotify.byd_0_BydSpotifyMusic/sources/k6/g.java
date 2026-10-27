package k6;

import c6.b0;
import c6.t;
import c6.x;
import c6.y;
import c6.z;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import p6.a0;

/* JADX INFO: compiled from: Http2ExchangeCodec.kt */
/* JADX INFO: loaded from: classes.dex */
public final class g implements i6.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private volatile i f7417a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final y f7418b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private volatile boolean f7419c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final h6.f f7420d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final i6.g f7421e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final f f7422f;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final a f7416i = new a(null);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static final List<String> f7414g = d6.b.s("connection", "host", "keep-alive", "proxy-connection", "te", "transfer-encoding", "encoding", "upgrade", ":method", ":path", ":scheme", ":authority");

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static final List<String> f7415h = d6.b.s("connection", "host", "keep-alive", "proxy-connection", "te", "transfer-encoding", "encoding", "upgrade");

    /* JADX INFO: compiled from: Http2ExchangeCodec.kt */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(kotlin.jvm.internal.g gVar) {
            this();
        }

        public final List<c> a(z request) {
            kotlin.jvm.internal.l.e(request, "request");
            t tVarE = request.e();
            ArrayList arrayList = new ArrayList(tVarE.size() + 4);
            arrayList.add(new c(c.f7281f, request.g()));
            arrayList.add(new c(c.f7282g, i6.i.f7064a.c(request.i())));
            String strD = request.d("Host");
            if (strD != null) {
                arrayList.add(new c(c.f7284i, strD));
            }
            arrayList.add(new c(c.f7283h, request.i().p()));
            int size = tVarE.size();
            for (int i7 = 0; i7 < size; i7++) {
                String strD2 = tVarE.d(i7);
                Locale locale = Locale.US;
                kotlin.jvm.internal.l.d(locale, "Locale.US");
                Objects.requireNonNull(strD2, "null cannot be cast to non-null type java.lang.String");
                String lowerCase = strD2.toLowerCase(locale);
                kotlin.jvm.internal.l.d(lowerCase, "(this as java.lang.String).toLowerCase(locale)");
                if (!g.f7414g.contains(lowerCase) || (kotlin.jvm.internal.l.a(lowerCase, "te") && kotlin.jvm.internal.l.a(tVarE.f(i7), "trailers"))) {
                    arrayList.add(new c(lowerCase, tVarE.f(i7)));
                }
            }
            return arrayList;
        }

        public final b0.a b(t headerBlock, y protocol) throws ProtocolException {
            kotlin.jvm.internal.l.e(headerBlock, "headerBlock");
            kotlin.jvm.internal.l.e(protocol, "protocol");
            t.a aVar = new t.a();
            int size = headerBlock.size();
            i6.k kVarA = null;
            for (int i7 = 0; i7 < size; i7++) {
                String strD = headerBlock.d(i7);
                String strF = headerBlock.f(i7);
                if (kotlin.jvm.internal.l.a(strD, ":status")) {
                    kVarA = i6.k.f7067d.a("HTTP/1.1 " + strF);
                } else if (!g.f7415h.contains(strD)) {
                    aVar.c(strD, strF);
                }
            }
            if (kVarA != null) {
                return new b0.a().p(protocol).g(kVarA.f7069b).m(kVarA.f7070c).k(aVar.d());
            }
            throw new ProtocolException("Expected ':status' header not present");
        }
    }

    public g(x client, h6.f connection, i6.g chain, f http2Connection) {
        kotlin.jvm.internal.l.e(client, "client");
        kotlin.jvm.internal.l.e(connection, "connection");
        kotlin.jvm.internal.l.e(chain, "chain");
        kotlin.jvm.internal.l.e(http2Connection, "http2Connection");
        this.f7420d = connection;
        this.f7421e = chain;
        this.f7422f = http2Connection;
        List<y> listW = client.w();
        y yVar = y.H2_PRIOR_KNOWLEDGE;
        this.f7418b = listW.contains(yVar) ? yVar : y.HTTP_2;
    }

    @Override // i6.d
    public void a(z request) throws IOException {
        kotlin.jvm.internal.l.e(request, "request");
        if (this.f7417a != null) {
            return;
        }
        this.f7417a = this.f7422f.w0(f7416i.a(request), request.a() != null);
        if (this.f7419c) {
            i iVar = this.f7417a;
            kotlin.jvm.internal.l.c(iVar);
            iVar.f(b.CANCEL);
            throw new IOException("Canceled");
        }
        i iVar2 = this.f7417a;
        kotlin.jvm.internal.l.c(iVar2);
        p6.b0 b0VarV = iVar2.v();
        long jH = this.f7421e.h();
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        b0VarV.g(jH, timeUnit);
        i iVar3 = this.f7417a;
        kotlin.jvm.internal.l.c(iVar3);
        iVar3.E().g(this.f7421e.j(), timeUnit);
    }

    @Override // i6.d
    public long b(b0 response) {
        kotlin.jvm.internal.l.e(response, "response");
        if (i6.e.b(response)) {
            return d6.b.r(response);
        }
        return 0L;
    }

    @Override // i6.d
    public p6.y c(z request, long j7) {
        kotlin.jvm.internal.l.e(request, "request");
        i iVar = this.f7417a;
        kotlin.jvm.internal.l.c(iVar);
        return iVar.n();
    }

    @Override // i6.d
    public void cancel() {
        this.f7419c = true;
        i iVar = this.f7417a;
        if (iVar != null) {
            iVar.f(b.CANCEL);
        }
    }

    @Override // i6.d
    public void d() {
        i iVar = this.f7417a;
        kotlin.jvm.internal.l.c(iVar);
        iVar.n().close();
    }

    @Override // i6.d
    public void e() {
        this.f7422f.flush();
    }

    @Override // i6.d
    public b0.a f(boolean z6) throws ProtocolException {
        i iVar = this.f7417a;
        kotlin.jvm.internal.l.c(iVar);
        b0.a aVarB = f7416i.b(iVar.C(), this.f7418b);
        if (z6 && aVarB.h() == 100) {
            return null;
        }
        return aVarB;
    }

    @Override // i6.d
    public a0 g(b0 response) {
        kotlin.jvm.internal.l.e(response, "response");
        i iVar = this.f7417a;
        kotlin.jvm.internal.l.c(iVar);
        return iVar.p();
    }

    @Override // i6.d
    public h6.f h() {
        return this.f7420d;
    }
}
