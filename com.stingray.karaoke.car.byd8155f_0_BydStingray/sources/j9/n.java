package j9;

import c9.C;
import c9.D;
import c9.G;
import g1.AbstractC1274a;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import r9.C2322l;
import r9.H;
import r9.J;

/* JADX INFO: loaded from: classes.dex */
public final class n implements h9.c {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final List f17561g = d9.b.l("connection", "host", "keep-alive", "proxy-connection", "te", "transfer-encoding", "encoding", "upgrade", ":method", ":path", ":scheme", ":authority");

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final List f17562h = d9.b.l("connection", "host", "keep-alive", "proxy-connection", "te", "transfer-encoding", "encoding", "upgrade");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final g9.m f17563a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final F.u f17564b;
    public final m c;
    public volatile u d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final D f17565e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public volatile boolean f17566f;

    public n(C c, g9.m mVar, F.u uVar, m mVar2) {
        A7.m.f(c, "client");
        A7.m.f(mVar, "connection");
        A7.m.f(mVar2, "http2Connection");
        this.f17563a = mVar;
        this.f17564b = uVar;
        this.c = mVar2;
        D d = D.H2_PRIOR_KNOWLEDGE;
        this.f17565e = c.f12470D.contains(d) ? d : D.HTTP_2;
    }

    @Override // h9.c
    public final H a(H0.b bVar, long j3) {
        A7.m.f(bVar, "request");
        u uVar = this.d;
        A7.m.c(uVar);
        return uVar.f();
    }

    @Override // h9.c
    public final void b(H0.b bVar) throws IOException {
        int i7;
        u uVar;
        A7.m.f(bVar, "request");
        if (this.d != null) {
            return;
        }
        boolean z6 = true;
        boolean z10 = ((AbstractC1274a) bVar.f2767p) != null;
        c9.u uVar2 = (c9.u) bVar.o;
        ArrayList arrayList = new ArrayList(uVar2.size() + 4);
        arrayList.add(new b(b.f17503f, (String) bVar.f2766n));
        C2322l c2322l = b.f17504g;
        c9.w wVar = (c9.w) bVar.f2765m;
        A7.m.f(wVar, "url");
        String strB = wVar.b();
        String strD = wVar.d();
        if (strD != null) {
            strB = strB + '?' + strD;
        }
        arrayList.add(new b(c2322l, strB));
        String strD2 = ((c9.u) bVar.o).d("Host");
        if (strD2 != null) {
            arrayList.add(new b(b.f17506i, strD2));
        }
        arrayList.add(new b(b.f17505h, wVar.f12619a));
        int size = uVar2.size();
        for (int i10 = 0; i10 < size; i10++) {
            String strL = uVar2.l(i10);
            Locale locale = Locale.US;
            A7.m.e(locale, "US");
            String lowerCase = strL.toLowerCase(locale);
            A7.m.e(lowerCase, "this as java.lang.String).toLowerCase(locale)");
            if (!f17561g.contains(lowerCase) || (lowerCase.equals("te") && A7.m.a(uVar2.s(i10), "trailers"))) {
                arrayList.add(new b(lowerCase, uVar2.s(i10)));
            }
        }
        m mVar = this.c;
        mVar.getClass();
        boolean z11 = !z10;
        synchronized (mVar.f17545H) {
            synchronized (mVar) {
                try {
                    if (mVar.f17550p > 1073741823) {
                        mVar.j(8);
                    }
                    if (mVar.f17551q) {
                        throw new a();
                    }
                    i7 = mVar.f17550p;
                    mVar.f17550p = i7 + 2;
                    uVar = new u(i7, mVar, z11, false, null);
                    if (z10 && mVar.f17542E < mVar.f17543F && uVar.f17582e < uVar.f17583f) {
                        z6 = false;
                    }
                    if (uVar.h()) {
                        mVar.f17548m.put(Integer.valueOf(i7), uVar);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            mVar.f17545H.k(z11, i7, arrayList);
        }
        if (z6) {
            mVar.f17545H.flush();
        }
        this.d = uVar;
        if (this.f17566f) {
            u uVar3 = this.d;
            A7.m.c(uVar3);
            uVar3.e(9);
            throw new IOException("Canceled");
        }
        u uVar4 = this.d;
        A7.m.c(uVar4);
        t tVar = uVar4.f17588k;
        long j3 = this.f17564b.d;
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        tVar.g(j3, timeUnit);
        u uVar5 = this.d;
        A7.m.c(uVar5);
        uVar5.l.g(this.f17564b.f1960e, timeUnit);
    }

    @Override // h9.c
    public final void c() {
        u uVar = this.d;
        A7.m.c(uVar);
        uVar.f().close();
    }

    @Override // h9.c
    public final void cancel() {
        this.f17566f = true;
        u uVar = this.d;
        if (uVar != null) {
            uVar.e(9);
        }
    }

    @Override // h9.c
    public final void d() {
        this.c.flush();
    }

    @Override // h9.c
    public final long e(c9.H h10) {
        if (h9.d.a(h10)) {
            return d9.b.k(h10);
        }
        return 0L;
    }

    @Override // h9.c
    public final J f(c9.H h10) {
        u uVar = this.d;
        A7.m.c(uVar);
        return uVar.f17586i;
    }

    @Override // h9.c
    public final G g(boolean z6) throws IOException {
        c9.u uVar;
        u uVar2 = this.d;
        if (uVar2 == null) {
            throw new IOException("stream wasn't created");
        }
        synchronized (uVar2) {
            uVar2.f17588k.i();
            while (uVar2.f17584g.isEmpty() && uVar2.f17589m == 0) {
                try {
                    uVar2.k();
                } catch (Throwable th) {
                    uVar2.f17588k.l();
                    throw th;
                }
            }
            uVar2.f17588k.l();
            if (uVar2.f17584g.isEmpty()) {
                IOException iOException = uVar2.f17590n;
                if (iOException != null) {
                    throw iOException;
                }
                int i7 = uVar2.f17589m;
                A7.k.n(i7);
                throw new z(i7);
            }
            Object objRemoveFirst = uVar2.f17584g.removeFirst();
            A7.m.e(objRemoveFirst, "headersQueue.removeFirst()");
            uVar = (c9.u) objRemoveFirst;
        }
        D d = this.f17565e;
        A7.m.f(d, "protocol");
        ArrayList arrayList = new ArrayList(20);
        int size = uVar.size();
        B5.u uVarR = null;
        for (int i10 = 0; i10 < size; i10++) {
            String strL = uVar.l(i10);
            String strS = uVar.s(i10);
            if (A7.m.a(strL, ":status")) {
                uVarR = h6.m.r("HTTP/1.1 " + strS);
            } else if (!f17562h.contains(strL)) {
                A7.m.f(strL, "name");
                A7.m.f(strS, "value");
                arrayList.add(strL);
                arrayList.add(Q8.h.g1(strS).toString());
            }
        }
        if (uVarR == null) {
            throw new ProtocolException("Expected ':status' header not present");
        }
        G g7 = new G();
        g7.f12505b = d;
        g7.c = uVarR.f735m;
        g7.d = (String) uVarR.o;
        g7.c(new c9.u((String[]) arrayList.toArray(new String[0])));
        if (z6 && g7.c == 100) {
            return null;
        }
        return g7;
    }

    @Override // h9.c
    public final g9.m h() {
        return this.f17563a;
    }
}
