package p;

import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public final class gc1 {
    public static final AtomicReferenceFieldUpdater e = AtomicReferenceFieldUpdater.newUpdater(gc1.class, Object.class, "_next");
    public static final AtomicLongFieldUpdater f = AtomicLongFieldUpdater.newUpdater(gc1.class, "_state");
    public static final tz2 g = new tz2("REMOVE_FROZEN", 3);
    private volatile Object _next;
    private volatile long _state;
    public final int a;
    public final boolean b;
    public final int c;
    public final AtomicReferenceArray d;

    public gc1(int i, boolean z) {
        this.a = i;
        this.b = z;
        int i2 = i - 1;
        this.c = i2;
        this.d = new AtomicReferenceArray(i);
        if (i2 > 1073741823) {
            throw new IllegalStateException("Check failed.".toString());
        }
        if ((i & i2) != 0) {
            throw new IllegalStateException("Check failed.".toString());
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0053, code lost:
    
        return 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int a(java.lang.Object r16) {
        /*
            r15 = this;
            r6 = r15
            r7 = r16
        L3:
            java.util.concurrent.atomic.AtomicLongFieldUpdater r8 = p.gc1.f
            long r2 = r8.get(r15)
            r0 = 3458764513820540928(0x3000000000000000, double:1.727233711018889E-77)
            long r0 = r0 & r2
            r9 = 0
            int r0 = (r0 > r9 ? 1 : (r0 == r9 ? 0 : -1))
            r1 = 1
            if (r0 == 0) goto L1c
            r4 = 2305843009213693952(0x2000000000000000, double:1.4916681462400413E-154)
            long r2 = r2 & r4
            int r0 = (r2 > r9 ? 1 : (r2 == r9 ? 0 : -1))
            if (r0 == 0) goto L1b
            r1 = 2
        L1b:
            return r1
        L1c:
            r4 = 1073741823(0x3fffffff, double:5.304989472E-315)
            long r4 = r4 & r2
            int r0 = (int) r4
            r4 = 1152921503533105152(0xfffffffc0000000, double:1.2882296003504729E-231)
            long r4 = r4 & r2
            r11 = 30
            long r4 = r4 >> r11
            int r12 = (int) r4
            int r4 = r12 + 2
            int r13 = r6.c
            r4 = r4 & r13
            r5 = r0 & r13
            if (r4 != r5) goto L35
            return r1
        L35:
            boolean r4 = r6.b
            r5 = 1073741823(0x3fffffff, float:1.9999999)
            java.util.concurrent.atomic.AtomicReferenceArray r14 = r6.d
            if (r4 != 0) goto L54
            r4 = r12 & r13
            java.lang.Object r4 = r14.get(r4)
            if (r4 == 0) goto L54
            r2 = 1024(0x400, float:1.435E-42)
            int r3 = r6.a
            if (r3 < r2) goto L53
            int r12 = r12 - r0
            r0 = r12 & r5
            int r2 = r3 >> 1
            if (r0 <= r2) goto L3
        L53:
            return r1
        L54:
            int r0 = r12 + 1
            r0 = r0 & r5
            java.util.concurrent.atomic.AtomicLongFieldUpdater r1 = p.gc1.f
            r4 = -1152921503533105153(0xf00000003fffffff, double:-3.1050369248997324E231)
            long r4 = r4 & r2
            long r9 = (long) r0
            long r9 = r9 << r11
            long r4 = r4 | r9
            r0 = r1
            r1 = r15
            boolean r0 = r0.compareAndSet(r1, r2, r4)
            if (r0 == 0) goto L3
            r0 = r12 & r13
            r14.set(r0, r7)
            r0 = r6
        L70:
            long r1 = r8.get(r0)
            r3 = 1152921504606846976(0x1000000000000000, double:1.2882297539194267E-231)
            long r1 = r1 & r3
            r3 = 0
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 == 0) goto L9b
            p.gc1 r0 = r0.c()
            java.util.concurrent.atomic.AtomicReferenceArray r1 = r0.d
            int r2 = r0.c
            r2 = r2 & r12
            java.lang.Object r5 = r1.get(r2)
            boolean r9 = r5 instanceof p.fc1
            if (r9 == 0) goto L98
            p.fc1 r5 = (p.fc1) r5
            int r5 = r5.a
            if (r5 != r12) goto L98
            r1.set(r2, r7)
            goto L99
        L98:
            r0 = 0
        L99:
            if (r0 != 0) goto L70
        L9b:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p.gc1.a(java.lang.Object):int");
    }

    public final boolean b() {
        AtomicLongFieldUpdater atomicLongFieldUpdater;
        long j;
        do {
            atomicLongFieldUpdater = f;
            j = atomicLongFieldUpdater.get(this);
            if ((j & 2305843009213693952L) != 0) {
                return true;
            }
            if ((1152921504606846976L & j) != 0) {
                return false;
            }
        } while (!atomicLongFieldUpdater.compareAndSet(this, j, 2305843009213693952L | j));
        return true;
    }

    public final gc1 c() {
        AtomicLongFieldUpdater atomicLongFieldUpdater;
        long j;
        while (true) {
            atomicLongFieldUpdater = f;
            j = atomicLongFieldUpdater.get(this);
            if ((j & 1152921504606846976L) != 0) {
                break;
            }
            long j2 = j | 1152921504606846976L;
            if (atomicLongFieldUpdater.compareAndSet(this, j, j2)) {
                j = j2;
                break;
            }
        }
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = e;
            gc1 gc1Var = (gc1) atomicReferenceFieldUpdater.get(this);
            if (gc1Var != null) {
                return gc1Var;
            }
            gc1 gc1Var2 = new gc1(this.a * 2, this.b);
            int i = (int) (1073741823 & j);
            int i2 = (int) ((1152921503533105152L & j) >> 30);
            while (true) {
                int i3 = this.c;
                int i4 = i & i3;
                if (i4 == (i3 & i2)) {
                    break;
                }
                Object fc1Var = this.d.get(i4);
                if (fc1Var == null) {
                    fc1Var = new fc1(i);
                }
                gc1Var2.d.set(gc1Var2.c & i, fc1Var);
                i++;
            }
            atomicLongFieldUpdater.set(gc1Var2, (-1152921504606846977L) & j);
            while (!atomicReferenceFieldUpdater.compareAndSet(this, null, gc1Var2) && atomicReferenceFieldUpdater.get(this) == null) {
            }
        }
    }

    public final Object d() {
        while (true) {
            AtomicLongFieldUpdater atomicLongFieldUpdater = f;
            long j = atomicLongFieldUpdater.get(this);
            if ((j & 1152921504606846976L) != 0) {
                return g;
            }
            int i = (int) (j & 1073741823);
            int i2 = this.c;
            int i3 = i & i2;
            if ((((int) ((1152921503533105152L & j) >> 30)) & i2) == i3) {
                return null;
            }
            AtomicReferenceArray atomicReferenceArray = this.d;
            Object obj = atomicReferenceArray.get(i3);
            boolean z = this.b;
            if (obj == null) {
                if (z) {
                    return null;
                }
            } else {
                if (obj instanceof fc1) {
                    return null;
                }
                long j2 = (i + 1) & 1073741823;
                if (atomicLongFieldUpdater.compareAndSet(this, j, (j & (-1073741824)) | j2)) {
                    atomicReferenceArray.set(i3, null);
                    return obj;
                }
                if (z) {
                    gc1 gc1VarC = this;
                    while (true) {
                        AtomicLongFieldUpdater atomicLongFieldUpdater2 = f;
                        long j3 = atomicLongFieldUpdater2.get(gc1VarC);
                        int i4 = (int) (j3 & 1073741823);
                        if ((j3 & 1152921504606846976L) != 0) {
                            gc1VarC = gc1VarC.c();
                        } else {
                            if (atomicLongFieldUpdater2.compareAndSet(gc1VarC, j3, (j3 & (-1073741824)) | j2)) {
                                gc1VarC.d.set(gc1VarC.c & i4, null);
                                gc1VarC = null;
                            } else {
                                continue;
                            }
                        }
                        if (gc1VarC == null) {
                            return obj;
                        }
                    }
                }
            }
        }
    }
}
