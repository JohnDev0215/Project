package A;

import E0.AbstractC0156f;
import r7.EnumC2303a;
import s7.AbstractC2410c;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class S0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public K0 f100a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public y.r0 f101b;
    public r c;
    public EnumC0017h0 d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f102e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public x0.d f103f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f104g = 1;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public InterfaceC0033p0 f105h = androidx.compose.foundation.gestures.a.f11449a;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final P0 f106i = new P0(this);

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final F f107j = new F(this, 2);

    public S0(K0 k02, y.r0 r0Var, r rVar, EnumC0017h0 enumC0017h0, boolean z6, x0.d dVar) {
        this.f100a = k02;
        this.f101b = r0Var;
        this.c = rVar;
        this.d = enumC0017h0;
        this.f102e = z6;
        this.f103f = dVar;
    }

    public static final long a(S0 s02, InterfaceC0033p0 interfaceC0033p0, long j3, int i7) {
        long j10;
        x0.g gVar = s02.f103f.f23678a;
        x0.g gVar2 = null;
        x0.g gVar3 = (gVar == null || !gVar.f14695x) ? null : (x0.g) AbstractC0156f.k(gVar);
        long jN = 0;
        long jN2 = gVar3 != null ? gVar3.n(j3, i7) : 0L;
        long jG = m0.c.g(j3, jN2);
        long jD = s02.d(s02.g(interfaceC0033p0.a(s02.f(s02.d(m0.c.a(jG, 0.0f, s02.d == EnumC0017h0.f181m ? 1 : 2))))));
        long jG2 = m0.c.g(jG, jD);
        x0.g gVar4 = s02.f103f.f23678a;
        if (gVar4 != null && gVar4.f14695x) {
            gVar2 = (x0.g) AbstractC0156f.k(gVar4);
        }
        x0.g gVar5 = gVar2;
        if (gVar5 != null) {
            j10 = jD;
            jN = gVar5.N(i7, jD, jG2);
        } else {
            j10 = jD;
        }
        return m0.c.h(m0.c.h(jN2, j10), jN);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object b(long r12, s7.AbstractC2410c r14) {
        /*
            r11 = this;
            boolean r0 = r14 instanceof A.M0
            if (r0 == 0) goto L13
            r0 = r14
            A.M0 r0 = (A.M0) r0
            int r1 = r0.o
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.o = r1
            goto L18
        L13:
            A.M0 r0 = new A.M0
            r0.<init>(r11, r14)
        L18:
            java.lang.Object r14 = r0.f72m
            r7.a r1 = r7.EnumC2303a.l
            int r2 = r0.o
            r3 = 1
            if (r2 == 0) goto L31
            if (r2 != r3) goto L29
            A7.y r11 = r0.l
            h8.AbstractC1386c.u(r14)
            goto L53
        L29:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L31:
            h8.AbstractC1386c.u(r14)
            A7.y r14 = new A7.y
            r14.<init>()
            r14.l = r12
            y.j0 r2 = y.j0.l
            A.O0 r10 = new A.O0
            r9 = 0
            r4 = r10
            r5 = r11
            r6 = r14
            r7 = r12
            r4.<init>(r5, r6, r7, r9)
            r0.l = r14
            r0.o = r3
            java.lang.Object r11 = r11.e(r2, r10, r0)
            if (r11 != r1) goto L52
            return r1
        L52:
            r11 = r14
        L53:
            long r11 = r11.l
            a1.o r13 = new a1.o
            r13.<init>(r11)
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: A.S0.b(long, s7.c):java.lang.Object");
    }

    public final float c(float f10) {
        return this.f102e ? f10 * (-1) : f10;
    }

    public final long d(long j3) {
        return this.f102e ? m0.c.i(-1.0f, j3) : j3;
    }

    public final Object e(y.j0 j0Var, InterfaceC2785n interfaceC2785n, AbstractC2410c abstractC2410c) {
        Object objE = this.f100a.e(j0Var, new R0(this, interfaceC2785n, null), abstractC2410c);
        return objE == EnumC2303a.l ? objE : m7.q.f19267a;
    }

    public final float f(long j3) {
        return this.d == EnumC0017h0.f181m ? m0.c.d(j3) : m0.c.e(j3);
    }

    public final long g(float f10) {
        if (f10 == 0.0f) {
            return 0L;
        }
        return this.d == EnumC0017h0.f181m ? h6.e.a(f10, 0.0f) : h6.e.a(0.0f, f10);
    }
}
