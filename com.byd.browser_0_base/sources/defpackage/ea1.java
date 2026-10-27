package defpackage;

import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: compiled from: LockFreeTaskQueue.kt */
/* JADX INFO: loaded from: classes.dex */
public final class ea1<E> {
    private volatile /* synthetic */ Object _next = null;
    private volatile /* synthetic */ long _state = 0;
    public final int a;
    public final boolean b;
    public final int c;
    public final /* synthetic */ AtomicReferenceArray d;
    public static final a41 g = new a41(2, "REMOVE_FROZEN");
    public static final /* synthetic */ AtomicReferenceFieldUpdater e = AtomicReferenceFieldUpdater.newUpdater(ea1.class, Object.class, "_next");
    public static final /* synthetic */ AtomicLongFieldUpdater f = AtomicLongFieldUpdater.newUpdater(ea1.class, "_state");

    /* JADX INFO: compiled from: LockFreeTaskQueue.kt */
    public static final class a {
        public final int a;

        public a(int i) {
            this.a = i;
        }
    }

    public ea1(int i, boolean z) {
        this.a = i;
        this.b = z;
        int i2 = i - 1;
        this.c = i2;
        this.d = new AtomicReferenceArray(i);
        if (!(i2 <= 1073741823)) {
            throw new IllegalStateException("Check failed.".toString());
        }
        if (!((i & i2) == 0)) {
            throw new IllegalStateException("Check failed.".toString());
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x004e, code lost:
    
        return 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int a(E r15) {
        /*
            r14 = this;
        L0:
            long r2 = r14._state
            r0 = 3458764513820540928(0x3000000000000000, double:1.727233711018889E-77)
            long r0 = r0 & r2
            r6 = 0
            int r0 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
            r1 = 1
            if (r0 == 0) goto L15
            r14 = 2305843009213693952(0x2000000000000000, double:1.4916681462400413E-154)
            long r14 = r14 & r2
            int r14 = (r14 > r6 ? 1 : (r14 == r6 ? 0 : -1))
            if (r14 == 0) goto L14
            r1 = 2
        L14:
            return r1
        L15:
            r4 = 1073741823(0x3fffffff, double:5.304989472E-315)
            long r4 = r4 & r2
            r8 = 0
            long r4 = r4 >> r8
            int r0 = (int) r4
            r4 = 1152921503533105152(0xfffffffc0000000, double:1.2882296003504729E-231)
            long r4 = r4 & r2
            r9 = 30
            long r4 = r4 >> r9
            int r10 = (int) r4
            int r11 = r14.c
            int r4 = r10 + 2
            r4 = r4 & r11
            r5 = r0 & r11
            if (r4 != r5) goto L30
            return r1
        L30:
            boolean r4 = r14.b
            r5 = 1073741823(0x3fffffff, float:1.9999999)
            if (r4 != 0) goto L4f
            java.util.concurrent.atomic.AtomicReferenceArray r4 = r14.d
            r12 = r10 & r11
            java.lang.Object r4 = r4.get(r12)
            if (r4 == 0) goto L4f
            int r2 = r14.a
            r3 = 1024(0x400, float:1.435E-42)
            if (r2 < r3) goto L4e
            int r10 = r10 - r0
            r0 = r10 & r5
            int r2 = r2 >> 1
            if (r0 <= r2) goto L0
        L4e:
            return r1
        L4f:
            int r0 = r10 + 1
            r0 = r0 & r5
            java.util.concurrent.atomic.AtomicLongFieldUpdater r1 = defpackage.ea1.f
            r4 = -1152921503533105153(0xf00000003fffffff, double:-3.1050369248997324E231)
            long r4 = r4 & r2
            long r12 = (long) r0
            long r12 = r12 << r9
            long r4 = r4 | r12
            r0 = r1
            r1 = r14
            boolean r0 = r0.compareAndSet(r1, r2, r4)
            if (r0 == 0) goto L0
            java.util.concurrent.atomic.AtomicReferenceArray r0 = r14.d
            r1 = r10 & r11
            r0.set(r1, r15)
        L6c:
            long r0 = r14._state
            r2 = 1152921504606846976(0x1000000000000000, double:1.2882297539194267E-231)
            long r0 = r0 & r2
            int r0 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
            if (r0 != 0) goto L76
            goto L94
        L76:
            ea1 r14 = r14.e()
            java.util.concurrent.atomic.AtomicReferenceArray r0 = r14.d
            int r1 = r14.c
            r1 = r1 & r10
            java.lang.Object r2 = r0.get(r1)
            boolean r3 = r2 instanceof ea1.a
            if (r3 == 0) goto L91
            ea1$a r2 = (ea1.a) r2
            int r2 = r2.a
            if (r2 != r10) goto L91
            r0.set(r1, r15)
            goto L92
        L91:
            r14 = 0
        L92:
            if (r14 != 0) goto L6c
        L94:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ea1.a(java.lang.Object):int");
    }

    public final boolean b() {
        long j;
        do {
            j = this._state;
            if ((j & 2305843009213693952L) != 0) {
                return true;
            }
            if ((1152921504606846976L & j) != 0) {
                return false;
            }
        } while (!f.compareAndSet(this, j, j | 2305843009213693952L));
        return true;
    }

    public final int c() {
        long j = this._state;
        return 1073741823 & (((int) ((j & 1152921503533105152L) >> 30)) - ((int) ((1073741823 & j) >> 0)));
    }

    public final boolean d() {
        long j = this._state;
        return ((int) ((1073741823 & j) >> 0)) == ((int) ((j & 1152921503533105152L) >> 30));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final ea1<E> e() {
        long j;
        while (true) {
            j = this._state;
            if ((j & 1152921504606846976L) != 0) {
                break;
            }
            long j2 = j | 1152921504606846976L;
            if (f.compareAndSet(this, j, j2)) {
                j = j2;
                break;
            }
        }
        while (true) {
            ea1<E> ea1Var = (ea1) this._next;
            if (ea1Var != null) {
                return ea1Var;
            }
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = e;
            ea1 ea1Var2 = new ea1(this.a * 2, this.b);
            int i = (int) ((1073741823 & j) >> 0);
            int i2 = (int) ((1152921503533105152L & j) >> 30);
            while (true) {
                int i3 = this.c;
                int i4 = i & i3;
                if (i4 == (i3 & i2)) {
                    break;
                }
                Object aVar = this.d.get(i4);
                if (aVar == null) {
                    aVar = new a(i);
                }
                ea1Var2.d.set(ea1Var2.c & i, aVar);
                i++;
            }
            ea1Var2._state = (-1152921504606846977L) & j;
            while (!atomicReferenceFieldUpdater.compareAndSet(this, null, ea1Var2) && atomicReferenceFieldUpdater.get(this) == null) {
            }
        }
    }

    public final Object f() {
        while (true) {
            long j = this._state;
            if ((j & 1152921504606846976L) != 0) {
                return g;
            }
            int i = (int) ((j & 1073741823) >> 0);
            int i2 = this.c;
            int i3 = ((int) ((1152921503533105152L & j) >> 30)) & i2;
            int i4 = i2 & i;
            if (i3 == i4) {
                return null;
            }
            Object obj = this.d.get(i4);
            if (obj == null) {
                if (this.b) {
                    return null;
                }
            } else {
                if (obj instanceof a) {
                    return null;
                }
                long j2 = ((long) ((i + 1) & 1073741823)) << 0;
                if (f.compareAndSet(this, j, j2 | (j & (-1073741824)))) {
                    this.d.set(this.c & i, null);
                    return obj;
                }
                if (this.b) {
                    ea1<E> ea1VarE = this;
                    while (true) {
                        long j3 = ea1VarE._state;
                        int i5 = (int) ((j3 & 1073741823) >> 0);
                        if ((j3 & 1152921504606846976L) != 0) {
                            ea1VarE = ea1VarE.e();
                        } else {
                            if (f.compareAndSet(ea1VarE, j3, (j3 & (-1073741824)) | j2)) {
                                ea1VarE.d.set(ea1VarE.c & i5, null);
                                ea1VarE = null;
                            } else {
                                continue;
                            }
                        }
                        if (ea1VarE == null) {
                            return obj;
                        }
                    }
                }
            }
        }
    }
}
