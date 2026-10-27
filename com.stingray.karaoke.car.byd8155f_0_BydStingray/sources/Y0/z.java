package y0;

import E0.AbstractC0156f;
import F0.W0;
import S8.C0712i;
import q7.C2195i;
import q7.InterfaceC2189c;
import q7.InterfaceC2194h;
import r7.EnumC2303a;
import s7.AbstractC2408a;

/* JADX INFO: loaded from: classes.dex */
public final class z implements a1.b, InterfaceC2189c {
    public final C0712i l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C2722B f24108m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public C0712i f24109n;
    public EnumC2731i o = EnumC2731i.f24067m;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ C2722B f24110p;

    public z(C2722B c2722b, C0712i c0712i) {
        this.f24110p = c2722b;
        this.l = c0712i;
        this.f24108m = c2722b;
    }

    @Override // a1.b
    public final float K(long j3) {
        return this.f24108m.K(j3);
    }

    @Override // a1.b
    public final int O(float f10) {
        return this.f24108m.O(f10);
    }

    @Override // a1.b
    public final long Y(long j3) {
        return this.f24108m.Y(j3);
    }

    public final Object a(EnumC2731i enumC2731i, AbstractC2408a abstractC2408a) {
        C0712i c0712i = new C0712i(1, h6.e.k(abstractC2408a));
        c0712i.s();
        this.o = enumC2731i;
        this.f24109n = c0712i;
        Object objQ = c0712i.q();
        EnumC2303a enumC2303a = EnumC2303a.l;
        return objQ;
    }

    public final long b() {
        C2722B c2722b = this.f24110p;
        c2722b.getClass();
        long jY = c2722b.Y(AbstractC0156f.t(c2722b).f1466E.g());
        long j3 = c2722b.f24046H;
        return h6.j.f(Math.max(0.0f, m0.f.d(jY) - ((int) (j3 >> 32))) / 2.0f, Math.max(0.0f, m0.f.b(jY) - ((int) (j3 & 4294967295L))) / 2.0f);
    }

    @Override // a1.b
    public final float b0(long j3) {
        return this.f24108m.b0(j3);
    }

    public final W0 c() {
        C2722B c2722b = this.f24110p;
        c2722b.getClass();
        return AbstractC0156f.t(c2722b).f1466E;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object d(long r8, z7.InterfaceC2785n r10, s7.AbstractC2408a r11) throws java.lang.Throwable {
        /*
            r7 = this;
            boolean r0 = r11 instanceof y0.w
            if (r0 == 0) goto L13
            r0 = r11
            y0.w r0 = (y0.w) r0
            int r1 = r0.o
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.o = r1
            goto L18
        L13:
            y0.w r0 = new y0.w
            r0.<init>(r7, r11)
        L18:
            java.lang.Object r11 = r0.f24102m
            r7.a r1 = r7.EnumC2303a.l
            int r2 = r0.o
            r3 = 1
            if (r2 == 0) goto L33
            if (r2 != r3) goto L2b
            S8.k0 r7 = r0.l
            h8.AbstractC1386c.u(r11)     // Catch: java.lang.Throwable -> L29
            goto L69
        L29:
            r8 = move-exception
            goto L73
        L2b:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L33:
            h8.AbstractC1386c.u(r11)
            r4 = 0
            int r11 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
            if (r11 > 0) goto L4c
            S8.i r11 = r7.f24109n
            if (r11 == 0) goto L4c
            y0.j r2 = new y0.j
            r2.<init>(r8)
            m7.k r2 = h8.AbstractC1386c.f(r2)
            r11.resumeWith(r2)
        L4c:
            y0.B r11 = r7.f24110p
            S8.w r11 = r11.v0()
            y0.x r2 = new y0.x
            r4 = 0
            r2.<init>(r8, r7, r4)
            r8 = 3
            S8.k0 r8 = S8.AbstractC0727y.w(r11, r4, r4, r2, r8)
            r0.l = r8     // Catch: java.lang.Throwable -> L6f
            r0.o = r3     // Catch: java.lang.Throwable -> L6f
            java.lang.Object r11 = r10.invoke(r7, r0)     // Catch: java.lang.Throwable -> L6f
            if (r11 != r1) goto L68
            return r1
        L68:
            r7 = r8
        L69:
            y0.b r8 = y0.C2724b.l
            r7.g(r8)
            return r11
        L6f:
            r7 = move-exception
            r6 = r8
            r8 = r7
            r7 = r6
        L73:
            y0.b r9 = y0.C2724b.l
            r7.g(r9)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: y0.z.d(long, z7.n, s7.a):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object f(long r5, A.U0 r7, s7.AbstractC2408a r8) throws java.lang.Throwable {
        /*
            r4 = this;
            boolean r0 = r8 instanceof y0.y
            if (r0 == 0) goto L13
            r0 = r8
            y0.y r0 = (y0.y) r0
            int r1 = r0.f24107n
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f24107n = r1
            goto L18
        L13:
            y0.y r0 = new y0.y
            r0.<init>(r4, r8)
        L18:
            java.lang.Object r8 = r0.l
            r7.a r1 = r7.EnumC2303a.l
            int r2 = r0.f24107n
            r3 = 1
            if (r2 == 0) goto L2f
            if (r2 != r3) goto L27
            h8.AbstractC1386c.u(r8)     // Catch: y0.C2732j -> L3b
            goto L3c
        L27:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L2f:
            h8.AbstractC1386c.u(r8)
            r0.f24107n = r3     // Catch: y0.C2732j -> L3b
            java.lang.Object r8 = r4.d(r5, r7, r0)     // Catch: y0.C2732j -> L3b
            if (r8 != r1) goto L3c
            return r1
        L3b:
            r8 = 0
        L3c:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: y0.z.f(long, A.U0, s7.a):java.lang.Object");
    }

    @Override // q7.InterfaceC2189c
    public final InterfaceC2194h getContext() {
        return C2195i.l;
    }

    @Override // a1.b
    public final float getDensity() {
        return this.f24108m.getDensity();
    }

    @Override // a1.b
    public final long j0(float f10) {
        return this.f24108m.j0(f10);
    }

    @Override // a1.b
    public final float p() {
        return this.f24108m.p();
    }

    @Override // a1.b
    public final float p0(int i7) {
        return this.f24108m.p0(i7);
    }

    @Override // a1.b
    public final float r0(float f10) {
        return f10 / this.f24108m.getDensity();
    }

    @Override // q7.InterfaceC2189c
    public final void resumeWith(Object obj) {
        C2722B c2722b = this.f24110p;
        synchronized (c2722b.f24043E) {
            c2722b.f24043E.n(this);
        }
        this.l.resumeWith(obj);
    }

    @Override // a1.b
    public final long w(float f10) {
        return this.f24108m.w(f10);
    }

    @Override // a1.b
    public final long x(long j3) {
        return this.f24108m.x(j3);
    }

    @Override // a1.b
    public final float y(float f10) {
        return this.f24108m.getDensity() * f10;
    }
}
