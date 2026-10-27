package y0;

import E0.AbstractC0156f;
import E0.n0;
import S8.AbstractC0727y;
import S8.C0712i;
import S8.EnumC0726x;
import S8.k0;
import T.Q;
import java.util.ArrayList;
import q0.C2162a;
import q7.C2196j;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: y0.B, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2722B extends g0.n implements a1.b, n0 {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public Object[] f24040A;
    public InterfaceC2785n B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public k0 f24041C;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public C2730h f24045G;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public Object f24047y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public Object f24048z;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public C2730h f24042D = v.f24101a;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public final V.d f24043E = new V.d(new z[16]);

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public final V.d f24044F = new V.d(new z[16]);

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public long f24046H = 0;

    public C2722B(Object obj, Object obj2, Object[] objArr, InterfaceC2785n interfaceC2785n) {
        this.f24047y = obj;
        this.f24048z = obj2;
        this.f24040A = objArr;
        this.B = interfaceC2785n;
    }

    @Override // g0.n
    public final void A0() {
        J0();
    }

    public final Object H0(InterfaceC2785n interfaceC2785n, InterfaceC2189c interfaceC2189c) {
        C0712i c0712i = new C0712i(1, h6.e.k(interfaceC2189c));
        c0712i.s();
        z zVar = new z(this, c0712i);
        synchronized (this.f24043E) {
            this.f24043E.b(zVar);
            new C2196j(h6.e.k(h6.e.g(zVar, zVar, interfaceC2785n)), EnumC2303a.l).resumeWith(m7.q.f19267a);
        }
        c0712i.u(new C2162a(zVar, 11));
        return c0712i.q();
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x003c A[Catch: all -> 0x003a, TryCatch #1 {all -> 0x003a, blocks: (B:6:0x000d, B:13:0x001b, B:15:0x0021, B:16:0x0024, B:18:0x002c, B:20:0x0030, B:21:0x0035, B:26:0x003c, B:28:0x0042, B:29:0x0045, B:31:0x004d, B:33:0x0051), top: B:45:0x000d }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void I0(y0.C2730h r7, y0.EnumC2731i r8) {
        /*
            r6 = this;
            V.d r0 = r6.f24043E
            monitor-enter(r0)
            V.d r1 = r6.f24044F     // Catch: java.lang.Throwable -> L66
            V.d r2 = r6.f24043E     // Catch: java.lang.Throwable -> L66
            int r3 = r1.f9279n     // Catch: java.lang.Throwable -> L66
            r1.c(r3, r2)     // Catch: java.lang.Throwable -> L66
            monitor-exit(r0)
            int r0 = r8.ordinal()     // Catch: java.lang.Throwable -> L3a
            r1 = 0
            if (r0 == 0) goto L3c
            r2 = 1
            if (r0 == r2) goto L1b
            r2 = 2
            if (r0 == r2) goto L3c
            goto L5a
        L1b:
            V.d r0 = r6.f24044F     // Catch: java.lang.Throwable -> L3a
            int r3 = r0.f9279n     // Catch: java.lang.Throwable -> L3a
            if (r3 <= 0) goto L5a
            int r3 = r3 - r2
            java.lang.Object[] r0 = r0.l     // Catch: java.lang.Throwable -> L3a
        L24:
            r2 = r0[r3]     // Catch: java.lang.Throwable -> L3a
            y0.z r2 = (y0.z) r2     // Catch: java.lang.Throwable -> L3a
            y0.i r4 = r2.o     // Catch: java.lang.Throwable -> L3a
            if (r8 != r4) goto L35
            S8.i r4 = r2.f24109n     // Catch: java.lang.Throwable -> L3a
            if (r4 == 0) goto L35
            r2.f24109n = r1     // Catch: java.lang.Throwable -> L3a
            r4.resumeWith(r7)     // Catch: java.lang.Throwable -> L3a
        L35:
            int r3 = r3 + (-1)
            if (r3 >= 0) goto L24
            goto L5a
        L3a:
            r7 = move-exception
            goto L60
        L3c:
            V.d r0 = r6.f24044F     // Catch: java.lang.Throwable -> L3a
            int r2 = r0.f9279n     // Catch: java.lang.Throwable -> L3a
            if (r2 <= 0) goto L5a
            java.lang.Object[] r0 = r0.l     // Catch: java.lang.Throwable -> L3a
            r3 = 0
        L45:
            r4 = r0[r3]     // Catch: java.lang.Throwable -> L3a
            y0.z r4 = (y0.z) r4     // Catch: java.lang.Throwable -> L3a
            y0.i r5 = r4.o     // Catch: java.lang.Throwable -> L3a
            if (r8 != r5) goto L56
            S8.i r5 = r4.f24109n     // Catch: java.lang.Throwable -> L3a
            if (r5 == 0) goto L56
            r4.f24109n = r1     // Catch: java.lang.Throwable -> L3a
            r5.resumeWith(r7)     // Catch: java.lang.Throwable -> L3a
        L56:
            int r3 = r3 + 1
            if (r3 < r2) goto L45
        L5a:
            V.d r6 = r6.f24044F
            r6.g()
            return
        L60:
            V.d r6 = r6.f24044F
            r6.g()
            throw r7
        L66:
            r6 = move-exception
            monitor-exit(r0)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: y0.C2722B.I0(y0.h, y0.i):void");
    }

    public final void J0() {
        k0 k0Var = this.f24041C;
        if (k0Var != null) {
            k0Var.x(new Q("Pointer input was reset", 4));
            this.f24041C = null;
        }
    }

    /* JADX WARN: Type inference failed for: r5v1, types: [java.lang.Object, java.util.List] */
    @Override // E0.n0
    public final void R(C2730h c2730h, EnumC2731i enumC2731i, long j3) {
        this.f24046H = j3;
        if (enumC2731i == EnumC2731i.l) {
            this.f24042D = c2730h;
        }
        if (this.f24041C == null) {
            this.f24041C = AbstractC0727y.w(v0(), null, EnumC0726x.o, new C2721A(this, null), 1);
        }
        I0(c2730h, enumC2731i);
        ?? r52 = c2730h.f24065a;
        int size = r52.size();
        int i7 = 0;
        while (true) {
            if (i7 >= size) {
                c2730h = null;
                break;
            } else if (!AbstractC2737o.c((q) r52.get(i7))) {
                break;
            } else {
                i7++;
            }
        }
        this.f24045G = c2730h;
    }

    @Override // E0.n0
    public final void a0() {
        J0();
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.Object, java.util.List] */
    @Override // E0.n0
    public final void e0() {
        C2730h c2730h = this.f24045G;
        if (c2730h == null) {
            return;
        }
        ?? r12 = c2730h.f24065a;
        int size = r12.size();
        for (int i7 = 0; i7 < size; i7++) {
            if (((q) r12.get(i7)).d) {
                ArrayList arrayList = new ArrayList(r12.size());
                int size2 = r12.size();
                for (int i10 = 0; i10 < size2; i10++) {
                    q qVar = (q) r12.get(i10);
                    long j3 = qVar.f24077a;
                    boolean z6 = qVar.d;
                    long j10 = qVar.f24078b;
                    long j11 = qVar.c;
                    arrayList.add(new q(j3, j10, j11, false, qVar.f24079e, j10, j11, z6, z6, 1, 0L));
                }
                C2730h c2730h2 = new C2730h(arrayList, null);
                this.f24042D = c2730h2;
                I0(c2730h2, EnumC2731i.l);
                I0(c2730h2, EnumC2731i.f24067m);
                I0(c2730h2, EnumC2731i.f24068n);
                this.f24045G = null;
                return;
            }
        }
    }

    @Override // a1.b
    public final float getDensity() {
        return AbstractC0156f.t(this).f1464C.getDensity();
    }

    @Override // E0.n0
    public final void k() {
        J0();
    }

    @Override // a1.b
    public final float p() {
        return AbstractC0156f.t(this).f1464C.p();
    }
}
