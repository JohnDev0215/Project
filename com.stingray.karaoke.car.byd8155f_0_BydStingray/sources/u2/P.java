package u2;

import B2.C0073m;
import android.net.Uri;
import h2.AbstractC1346c;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.Collections;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class P implements x2.l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Uri f22693a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final k2.C f22694b;
    public final h3.y c;
    public final T d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final B2.P f22695e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public volatile boolean f22697g;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public long f22699i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public k2.m f22700j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public B2.L f22701k;
    public boolean l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ T f22702m;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final B2.v f22696f = new B2.v();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f22698h = true;

    public P(T t10, Uri uri, k2.h hVar, h3.y yVar, T t11, B2.P p10) {
        this.f22702m = t10;
        this.f22693a = uri;
        this.f22694b = new k2.C(hVar);
        this.c = yVar;
        this.d = t11;
        this.f22695e = p10;
        C2516t.f22900a.getAndIncrement();
        this.f22700j = c(0L);
    }

    @Override // x2.l
    public final void a() {
        k2.h c2515s;
        B2.r rVar;
        int i7;
        int iC = 0;
        while (iC == 0 && !this.f22697g) {
            try {
                long j3 = this.f22696f.f600a;
                k2.m mVarC = c(j3);
                this.f22700j = mVarC;
                long jG = this.f22694b.g(mVarC);
                if (this.f22697g) {
                    if (iC != 1 && this.c.q() != -1) {
                        this.f22696f.f600a = this.c.q();
                    }
                    k2.C c = this.f22694b;
                    if (c != null) {
                        try {
                            c.close();
                            return;
                        } catch (IOException unused) {
                            return;
                        }
                    }
                    return;
                }
                if (jG != -1) {
                    jG += j3;
                    T t10 = this.f22702m;
                    t10.f22709C.post(new N(t10, 2));
                }
                long j10 = jG;
                this.f22702m.f22711E = O2.b.a(this.f22694b.l.f());
                k2.C c10 = this.f22694b;
                O2.b bVar = this.f22702m.f22711E;
                if (bVar == null || (i7 = bVar.f5066q) == -1) {
                    c2515s = c10;
                } else {
                    c2515s = new C2515s(c10, i7, this);
                    T t11 = this.f22702m;
                    t11.getClass();
                    B2.L lA = t11.A(new S(0, true));
                    this.f22701k = lA;
                    lA.d(T.f22707c0);
                }
                long jQ = j3;
                this.c.D(c2515s, this.f22693a, this.f22694b.l.f(), j3, j10, this.d);
                if (this.f22702m.f22711E != null && (rVar = (B2.r) this.c.f15500n) != null && (rVar instanceof U2.d)) {
                    ((U2.d) rVar).f8984r = true;
                }
                if (this.f22698h) {
                    h3.y yVar = this.c;
                    long j11 = this.f22699i;
                    B2.r rVar2 = (B2.r) yVar.f15500n;
                    rVar2.getClass();
                    rVar2.e(jQ, j11);
                    this.f22698h = false;
                }
                while (true) {
                    long j12 = jQ;
                    while (iC == 0 && !this.f22697g) {
                        try {
                            this.f22695e.a();
                            h3.y yVar2 = this.c;
                            B2.v vVar = this.f22696f;
                            B2.r rVar3 = (B2.r) yVar2.f15500n;
                            rVar3.getClass();
                            C0073m c0073m = (C0073m) yVar2.o;
                            c0073m.getClass();
                            iC = rVar3.c(c0073m, vVar);
                            jQ = this.c.q();
                            if (jQ > this.f22702m.f22741u + j12) {
                                B2.P p10 = this.f22695e;
                                synchronized (p10) {
                                    p10.l = false;
                                }
                                T t12 = this.f22702m;
                                t12.f22709C.post(t12.B);
                            }
                        } catch (InterruptedException unused2) {
                            throw new InterruptedIOException();
                        }
                    }
                    break;
                }
                if (iC == 1) {
                    iC = 0;
                } else if (this.c.q() != -1) {
                    this.f22696f.f600a = this.c.q();
                }
                k2.C c11 = this.f22694b;
                if (c11 != null) {
                    try {
                        c11.close();
                    } catch (IOException unused3) {
                    }
                }
            } catch (Throwable th) {
                if (iC != 1 && this.c.q() != -1) {
                    this.f22696f.f600a = this.c.q();
                }
                k2.C c12 = this.f22694b;
                if (c12 != null) {
                    try {
                        c12.close();
                    } catch (IOException unused4) {
                    }
                }
                throw th;
            }
        }
    }

    @Override // x2.l
    public final void b() {
        this.f22697g = true;
    }

    public final k2.m c(long j3) {
        Collections.emptyMap();
        String str = this.f22702m.f22740t;
        Map map = T.f22706b0;
        Uri uri = this.f22693a;
        AbstractC1346c.l(uri, "The uri must be set.");
        return new k2.m(uri, 0L, 1, null, map, j3, -1L, str, 6);
    }
}
