package p;

import java.util.concurrent.atomic.AtomicReferenceArray;

/* JADX INFO: loaded from: classes.dex */
public final class er extends cb2 {
    public final kotlinx.coroutines.channels.a m;
    public final AtomicReferenceArray n;

    public er(long j, er erVar, kotlinx.coroutines.channels.a aVar, int i) {
        super(j, erVar, i);
        this.m = aVar;
        this.n = new AtomicReferenceArray(ho.b * 2);
    }

    @Override // p.cb2
    public final int f() {
        return ho.b;
    }

    /* JADX WARN: Code restructure failed: missing block: B:35:0x005b, code lost:
    
        m(r7, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x005e, code lost:
    
        if (r1 == false) goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0060, code lost:
    
        p.v03.h(r5);
        r7 = r5.e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0065, code lost:
    
        if (r7 == null) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0067, code lost:
    
        r7 = kotlinx.coroutines.internal.b.b(r7, r0, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x006b, code lost:
    
        if (r7 == null) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x006d, code lost:
    
        p.ux2.m(r8, r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0070, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:?, code lost:
    
        return;
     */
    @Override // p.cb2
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void g(int r7, p.y10 r8) {
        /*
            r6 = this;
            int r0 = p.ho.b
            if (r7 < r0) goto L6
            r1 = 1
            goto L7
        L6:
            r1 = 0
        L7:
            if (r1 == 0) goto La
            int r7 = r7 - r0
        La:
            java.util.concurrent.atomic.AtomicReferenceArray r0 = r6.n
            int r2 = r7 * 2
            java.lang.Object r0 = r0.get(r2)
        L12:
            java.lang.Object r2 = r6.k(r7)
            boolean r3 = r2 instanceof p.qv2
            r4 = 0
            kotlinx.coroutines.channels.a r5 = r6.m
            if (r3 != 0) goto L71
            boolean r3 = r2 instanceof p.rv2
            if (r3 == 0) goto L22
            goto L71
        L22:
            p.tz2 r3 = p.ho.j
            if (r2 == r3) goto L5b
            p.tz2 r3 = p.ho.k
            if (r2 != r3) goto L2b
            goto L5b
        L2b:
            p.tz2 r3 = p.ho.g
            if (r2 == r3) goto L12
            p.tz2 r3 = p.ho.f
            if (r2 != r3) goto L34
            goto L12
        L34:
            p.tz2 r7 = p.ho.i
            if (r2 == r7) goto L5a
            p.tz2 r7 = p.ho.d
            if (r2 != r7) goto L3d
            goto L5a
        L3d:
            p.tz2 r7 = p.ho.l
            if (r2 != r7) goto L42
            return
        L42:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r0 = "unexpected state: "
            r8.<init>(r0)
            r8.append(r2)
            java.lang.String r8 = r8.toString()
            java.lang.String r8 = r8.toString()
            r7.<init>(r8)
            throw r7
        L5a:
            return
        L5b:
            r6.m(r7, r4)
            if (r1 == 0) goto L70
            p.v03.h(r5)
            p.ry0 r7 = r5.e
            if (r7 == 0) goto L70
            kotlinx.coroutines.internal.UndeliveredElementException r7 = kotlinx.coroutines.internal.b.b(r7, r0, r4)
            if (r7 == 0) goto L70
            p.ux2.m(r8, r7)
        L70:
            return
        L71:
            if (r1 == 0) goto L76
            p.tz2 r3 = p.ho.j
            goto L78
        L76:
            p.tz2 r3 = p.ho.k
        L78:
            boolean r2 = r6.j(r2, r7, r3)
            if (r2 == 0) goto L12
            r6.m(r7, r4)
            r2 = r1 ^ 1
            r6.l(r7, r2)
            if (r1 == 0) goto L98
            p.v03.h(r5)
            p.ry0 r7 = r5.e
            if (r7 == 0) goto L98
            kotlinx.coroutines.internal.UndeliveredElementException r7 = kotlinx.coroutines.internal.b.b(r7, r0, r4)
            if (r7 == 0) goto L98
            p.ux2.m(r8, r7)
        L98:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p.er.g(int, p.y10):void");
    }

    public final boolean j(Object obj, int i, Object obj2) {
        AtomicReferenceArray atomicReferenceArray = this.n;
        int i2 = (i * 2) + 1;
        while (!atomicReferenceArray.compareAndSet(i2, obj, obj2)) {
            if (atomicReferenceArray.get(i2) != obj) {
                return false;
            }
        }
        return true;
    }

    public final Object k(int i) {
        return this.n.get((i * 2) + 1);
    }

    public final void l(int i, boolean z) {
        if (z) {
            kotlinx.coroutines.channels.a aVar = this.m;
            v03.h(aVar);
            aVar.K((this.k * ((long) ho.b)) + ((long) i));
        }
        h();
    }

    public final void m(int i, Object obj) {
        this.n.lazySet(i * 2, obj);
    }

    public final void n(int i, tz2 tz2Var) {
        this.n.set((i * 2) + 1, tz2Var);
    }
}
