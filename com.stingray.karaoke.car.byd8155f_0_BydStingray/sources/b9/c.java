package b9;

import B2.q;
import S8.AbstractC0727y;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public final class c extends h implements InterfaceC1014a {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final /* synthetic */ AtomicReferenceFieldUpdater f12335h = AtomicReferenceFieldUpdater.newUpdater(c.class, Object.class, "owner$volatile");
    private volatile /* synthetic */ Object owner$volatile;

    public c(boolean z6) {
        super(1, z6 ? 1 : 0);
        this.owner$volatile = z6 ? null : d.f12336a;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0022, code lost:
    
        r0.n(r1, r3.f12343b);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object c(java.lang.Object r4, s7.AbstractC2410c r5) {
        /*
            r3 = this;
            boolean r0 = r3.d(r4)
            m7.q r1 = m7.q.f19267a
            if (r0 == 0) goto L9
            goto L3b
        L9:
            q7.c r5 = h6.e.k(r5)
            S8.i r5 = S8.AbstractC0727y.p(r5)
            b9.b r0 = new b9.b     // Catch: java.lang.Throwable -> L3c
            r0.<init>(r3, r5, r4)     // Catch: java.lang.Throwable -> L3c
        L16:
            java.util.concurrent.atomic.AtomicIntegerFieldUpdater r4 = b9.h.f12341g     // Catch: java.lang.Throwable -> L3c
            int r4 = r4.getAndDecrement(r3)     // Catch: java.lang.Throwable -> L3c
            int r2 = r3.f12342a     // Catch: java.lang.Throwable -> L3c
            if (r4 > r2) goto L16
            if (r4 <= 0) goto L28
            S8.h r3 = r3.f12343b     // Catch: java.lang.Throwable -> L3c
            r0.n(r1, r3)     // Catch: java.lang.Throwable -> L3c
            goto L2e
        L28:
            boolean r4 = r3.a(r0)     // Catch: java.lang.Throwable -> L3c
            if (r4 == 0) goto L16
        L2e:
            java.lang.Object r3 = r5.q()
            r7.a r4 = r7.EnumC2303a.l
            if (r3 != r4) goto L37
            goto L38
        L37:
            r3 = r1
        L38:
            if (r3 != r4) goto L3b
            r1 = r3
        L3b:
            return r1
        L3c:
            r3 = move-exception
            r5.A()
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: b9.c.c(java.lang.Object, s7.c):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0020, code lost:
    
        r7 = 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean d(java.lang.Object r8) {
        /*
            r7 = this;
        L0:
            java.util.concurrent.atomic.AtomicIntegerFieldUpdater r0 = b9.h.f12341g
            int r1 = r0.get(r7)
            int r2 = r7.f12342a
            if (r1 <= r2) goto L17
        La:
            int r1 = r0.get(r7)
            if (r1 <= r2) goto L0
            boolean r1 = r0.compareAndSet(r7, r1, r2)
            if (r1 == 0) goto La
            goto L0
        L17:
            r2 = 1
            r3 = 0
            r4 = 2
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r5 = b9.c.f12335h
            if (r1 > 0) goto L42
            if (r8 != 0) goto L22
        L20:
            r7 = r2
            goto L4e
        L22:
            int r1 = r0.get(r7)
            int r1 = java.lang.Math.max(r1, r3)
            if (r1 != 0) goto L3a
            java.lang.Object r1 = r5.get(r7)
            B2.q r6 = b9.d.f12336a
            if (r1 == r6) goto L22
            if (r1 != r8) goto L38
            r0 = r2
            goto L3b
        L38:
            r0 = r4
            goto L3b
        L3a:
            r0 = r3
        L3b:
            if (r0 == r2) goto L40
            if (r0 == r4) goto L20
            goto L0
        L40:
            r7 = r4
            goto L4e
        L42:
            int r6 = r1 + (-1)
            boolean r0 = r0.compareAndSet(r7, r1, r6)
            if (r0 == 0) goto L0
            r5.set(r7, r8)
            r7 = r3
        L4e:
            if (r7 == 0) goto L75
            if (r7 == r2) goto L74
            if (r7 == r4) goto L5c
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "unexpected"
            r7.<init>(r8)
            throw r7
        L5c:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "This mutex is already locked by the specified owner: "
            r0.<init>(r1)
            r0.append(r8)
            java.lang.String r8 = r0.toString()
            java.lang.String r8 = r8.toString()
            r7.<init>(r8)
            throw r7
        L74:
            r2 = r3
        L75:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: b9.c.d(java.lang.Object):boolean");
    }

    public final void e(Object obj) {
        while (Math.max(h.f12341g.get(this), 0) == 0) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f12335h;
            Object obj2 = atomicReferenceFieldUpdater.get(this);
            q qVar = d.f12336a;
            if (obj2 != qVar) {
                if (obj2 == obj || obj == null) {
                    while (!atomicReferenceFieldUpdater.compareAndSet(this, obj2, qVar)) {
                        if (atomicReferenceFieldUpdater.get(this) != obj2) {
                            break;
                        }
                    }
                    b();
                    return;
                }
                throw new IllegalStateException(("This mutex is locked by " + obj2 + ", but " + obj + " is expected").toString());
            }
        }
        throw new IllegalStateException("This mutex is not locked");
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Mutex@");
        sb.append(AbstractC0727y.n(this));
        sb.append("[isLocked=");
        sb.append(Math.max(h.f12341g.get(this), 0) == 0);
        sb.append(",owner=");
        sb.append(f12335h.get(this));
        sb.append(']');
        return sb.toString();
    }
}
