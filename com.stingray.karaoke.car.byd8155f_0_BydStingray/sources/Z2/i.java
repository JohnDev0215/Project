package z2;

import E5.z;
import android.content.Context;
import android.os.Looper;
import android.util.Pair;
import android.view.Surface;
import d5.d0;
import e2.C1200h;
import e2.C1207o;
import e2.C1208p;
import e2.o0;
import e2.q0;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import h2.x;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import l8.C1789g;
import n2.C1913m;

/* JADX INFO: loaded from: classes.dex */
public final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList f24318a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final s9.d f24319b;
    public C1208p c;
    public long d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f24320e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public long f24321f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public long f24322g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public long f24323h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f24324i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f24325j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public long f24326k;
    public u l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Executor f24327m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ l f24328n;

    public i(l lVar, Context context) {
        this.f24328n = lVar;
        AbstractC1343A.E(context);
        this.f24318a = new ArrayList();
        this.f24319b = new s9.d();
        this.f24323h = -9223372036854775807L;
        this.l = u.f24377k;
        this.f24327m = l.o;
    }

    public final void a(boolean z6) {
        this.f24324i = false;
        this.f24323h = -9223372036854775807L;
        l lVar = this.f24328n;
        if (lVar.f24340n == 1) {
            lVar.f24339m++;
            C1789g c1789g = lVar.f24333f;
            if (z6) {
                o oVar = (o) c1789g.l;
                r rVar = oVar.f24342b;
                rVar.f24364m = 0L;
                rVar.f24366p = -1L;
                rVar.f24365n = -1L;
                oVar.f24345g = -9223372036854775807L;
                oVar.f24343e = -9223372036854775807L;
                oVar.d(1);
                oVar.f24346h = -9223372036854775807L;
            }
            s sVar = (s) c1789g.f18861m;
            z zVar = sVar.f24371f;
            zVar.f1841b = 0;
            zVar.c = 0;
            sVar.f24375j = -9223372036854775807L;
            A2.g gVar = sVar.f24370e;
            if (gVar.A() > 0) {
                AbstractC1346c.c(gVar.A() > 0);
                while (gVar.A() > 1) {
                    gVar.v();
                }
                Object objV = gVar.v();
                objV.getClass();
                gVar.b(0L, (Long) objV);
            }
            q0 q0Var = sVar.f24372g;
            A2.g gVar2 = sVar.d;
            if (q0Var != null) {
                gVar2.d();
            } else if (gVar2.A() > 0) {
                AbstractC1346c.c(gVar2.A() > 0);
                while (gVar2.A() > 1) {
                    gVar2.v();
                }
                Object objV2 = gVar2.v();
                objV2.getClass();
                sVar.f24372g = (q0) objV2;
            }
            x xVar = lVar.f24338k;
            AbstractC1346c.k(xVar);
            xVar.c(new p2.p(lVar, 4));
        }
        this.f24326k = -9223372036854775807L;
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0060, code lost:
    
        if (r8 >= r4) goto L23;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean b(long r18, boolean r20, long r21, long r23, B5.u r25) throws z2.v {
        /*
            r17 = this;
            r1 = r17
            r0 = r25
            z2.l r2 = r1.f24328n
            r3 = 0
            h2.AbstractC1346c.j(r3)
            long r4 = r1.f24321f
            long r4 = r18 - r4
            z2.o r6 = r2.f24331b     // Catch: n2.C1913m -> L6f
            long r13 = r1.d     // Catch: n2.C1913m -> L6f
            s9.d r15 = r1.f24319b     // Catch: n2.C1913m -> L6f
            r7 = r4
            r9 = r21
            r11 = r23
            r16 = r15
            r15 = r20
            int r6 = r6.a(r7, r9, r11, r13, r15, r16)     // Catch: n2.C1913m -> L6f
            r7 = 4
            if (r6 != r7) goto L25
            return r3
        L25:
            long r6 = r1.f24322g
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 >= 0) goto L3c
            if (r20 != 0) goto L3c
            java.lang.Object r1 = r0.o
            z2.e r1 = (z2.C2759e) r1
            java.lang.Object r2 = r0.f736n
            s2.k r2 = (s2.k) r2
            int r0 = r0.f735m
            r1.I0(r2, r0)
            r0 = 1
            return r0
        L3c:
            r4 = r21
            r6 = r23
            r1.f(r4, r6)
            boolean r0 = r1.f24325j
            if (r0 == 0) goto L6a
            long r4 = r1.f24326k
            r6 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L63
            int r0 = r2.f24339m
            if (r0 != 0) goto L62
            z2.s r0 = r2.c
            long r8 = r0.f24375j
            int r0 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
            if (r0 == 0) goto L62
            int r0 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
            if (r0 >= 0) goto L63
        L62:
            return r3
        L63:
            r17.e()
            r1.f24325j = r3
            r1.f24326k = r6
        L6a:
            r0 = 0
            h2.AbstractC1346c.k(r0)
            throw r0
        L6f:
            r0 = move-exception
            z2.v r2 = new z2.v
            e2.p r1 = r1.c
            h2.AbstractC1346c.k(r1)
            r2.<init>(r0, r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: z2.i.b(long, boolean, long, long, B5.u):boolean");
    }

    public final void c(C1208p c1208p) {
        l lVar = this.f24328n;
        AbstractC1346c.j(lVar.f24340n == 0);
        C1200h c1200h = c1208p.B;
        if (c1200h == null || !c1200h.d()) {
            c1200h = C1200h.f14107h;
        }
        if (c1200h.c != 7 || AbstractC1343A.f15195a < 34) {
        }
        Looper looperMyLooper = Looper.myLooper();
        AbstractC1346c.k(looperMyLooper);
        lVar.f24338k = lVar.f24334g.a(looperMyLooper, null);
        try {
            k kVar = lVar.d;
            d0 d0Var = d0.f13415p;
            kVar.a();
            Pair pair = lVar.l;
            if (pair == null) {
                throw null;
            }
            int i7 = ((h2.u) pair.second).f15243a;
            throw null;
        } catch (o0 e10) {
            throw new v(e10, c1208p);
        }
    }

    public final void d(boolean z6) {
        ((o) this.f24328n.f24333f.l).c(z6);
    }

    public final void e() {
        if (this.c == null) {
            return;
        }
        new ArrayList(this.f24318a);
        C1208p c1208p = this.c;
        c1208p.getClass();
        AbstractC1346c.k(null);
        C1200h c1200h = c1208p.B;
        if (c1200h == null || !c1200h.d()) {
            C1200h c1200h2 = C1200h.f14107h;
        }
        int i7 = c1208p.f14322u;
        AbstractC1346c.b("width must be positive, but is: " + i7, i7 > 0);
        int i10 = c1208p.f14323v;
        AbstractC1346c.b("height must be positive, but is: " + i10, i10 > 0);
        throw null;
    }

    public final void f(long j3, long j10) {
        try {
            l.a(this.f24328n, j3, j10);
        } catch (C1913m e10) {
            C1208p c1208p = this.c;
            if (c1208p == null) {
                c1208p = new C1208p(new C1207o());
            }
            throw new v(e10, c1208p);
        }
    }

    public final void g(int i7) {
        r rVar = ((o) this.f24328n.f24333f.l).f24342b;
        if (rVar.f24362j == i7) {
            return;
        }
        rVar.f24362j = i7;
        rVar.d(true);
    }

    public final void h(Surface surface, h2.u uVar) {
        l lVar = this.f24328n;
        Pair pair = lVar.l;
        if (pair != null && ((Surface) pair.first).equals(surface) && ((h2.u) lVar.l.second).equals(uVar)) {
            return;
        }
        lVar.l = Pair.create(surface, uVar);
        int i7 = uVar.f15243a;
    }

    public final void i(float f10) {
        ((o) this.f24328n.f24333f.l).h(f10);
    }

    public final void j(long j3, long j10, long j11, long j12) {
        if (this.f24320e == j10) {
            int i7 = (this.f24321f > j11 ? 1 : (this.f24321f == j11 ? 0 : -1));
        }
        this.d = j3;
        this.f24320e = j10;
        this.f24321f = j11;
        this.f24322g = j12;
    }

    public final void k(List list) {
        ArrayList arrayList = this.f24318a;
        if (arrayList.equals(list)) {
            return;
        }
        arrayList.clear();
        arrayList.addAll(list);
        arrayList.addAll(this.f24328n.f24332e);
        e();
    }
}
