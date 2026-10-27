package X8;

import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public final class l {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final /* synthetic */ AtomicReferenceFieldUpdater f10568e = AtomicReferenceFieldUpdater.newUpdater(l.class, Object.class, "_next$volatile");

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final /* synthetic */ AtomicLongFieldUpdater f10569f = AtomicLongFieldUpdater.newUpdater(l.class, "_state$volatile");

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final B2.q f10570g = new B2.q("REMOVE_FROZEN", 3);
    private volatile /* synthetic */ Object _next$volatile;
    private volatile /* synthetic */ long _state$volatile;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f10571a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f10572b;
    public final int c;
    public final /* synthetic */ AtomicReferenceArray d;

    public l(int i7, boolean z6) {
        this.f10571a = i7;
        this.f10572b = z6;
        int i10 = i7 - 1;
        this.c = i10;
        this.d = new AtomicReferenceArray(i7);
        if (i10 > 1073741823) {
            throw new IllegalStateException("Check failed.");
        }
        if ((i7 & i10) != 0) {
            throw new IllegalStateException("Check failed.");
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0050, code lost:
    
        return 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int a(java.lang.Runnable r14) {
        /*
            r13 = this;
        L0:
            java.util.concurrent.atomic.AtomicLongFieldUpdater r6 = X8.l.f10569f
            long r2 = r6.get(r13)
            r0 = 3458764513820540928(0x3000000000000000, double:1.727233711018889E-77)
            long r0 = r0 & r2
            r7 = 0
            int r0 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1))
            r1 = 1
            if (r0 == 0) goto L19
            r13 = 2305843009213693952(0x2000000000000000, double:1.4916681462400413E-154)
            long r13 = r13 & r2
            int r13 = (r13 > r7 ? 1 : (r13 == r7 ? 0 : -1))
            if (r13 == 0) goto L18
            r1 = 2
        L18:
            return r1
        L19:
            r4 = 1073741823(0x3fffffff, double:5.304989472E-315)
            long r4 = r4 & r2
            int r0 = (int) r4
            r4 = 1152921503533105152(0xfffffffc0000000, double:1.2882296003504729E-231)
            long r4 = r4 & r2
            r9 = 30
            long r4 = r4 >> r9
            int r10 = (int) r4
            int r4 = r10 + 2
            int r11 = r13.c
            r4 = r4 & r11
            r5 = r0 & r11
            if (r4 != r5) goto L32
            return r1
        L32:
            java.util.concurrent.atomic.AtomicReferenceArray r12 = r13.d
            boolean r4 = r13.f10572b
            r5 = 1073741823(0x3fffffff, float:1.9999999)
            if (r4 != 0) goto L51
            r4 = r10 & r11
            java.lang.Object r4 = r12.get(r4)
            if (r4 == 0) goto L51
            r2 = 1024(0x400, float:1.435E-42)
            int r3 = r13.f10571a
            if (r3 < r2) goto L50
            int r10 = r10 - r0
            r0 = r10 & r5
            int r2 = r3 >> 1
            if (r0 <= r2) goto L0
        L50:
            return r1
        L51:
            int r0 = r10 + 1
            r0 = r0 & r5
            r4 = -1152921503533105153(0xf00000003fffffff, double:-3.1050369248997324E231)
            long r4 = r4 & r2
            long r0 = (long) r0
            long r0 = r0 << r9
            long r4 = r4 | r0
            r0 = r6
            r1 = r13
            boolean r0 = r0.compareAndSet(r1, r2, r4)
            if (r0 == 0) goto L0
            r0 = r10 & r11
            r12.set(r0, r14)
        L6a:
            long r0 = r6.get(r13)
            r2 = 1152921504606846976(0x1000000000000000, double:1.2882297539194267E-231)
            long r0 = r0 & r2
            int r0 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1))
            if (r0 == 0) goto L93
            X8.l r13 = r13.c()
            java.util.concurrent.atomic.AtomicReferenceArray r0 = r13.d
            int r1 = r13.c
            r1 = r1 & r10
            java.lang.Object r2 = r0.get(r1)
            boolean r3 = r2 instanceof X8.k
            if (r3 == 0) goto L90
            X8.k r2 = (X8.k) r2
            int r2 = r2.f10567a
            if (r2 != r10) goto L90
            r0.set(r1, r14)
            goto L91
        L90:
            r13 = 0
        L91:
            if (r13 != 0) goto L6a
        L93:
            r13 = 0
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: X8.l.a(java.lang.Runnable):int");
    }

    public final boolean b() {
        AtomicLongFieldUpdater atomicLongFieldUpdater;
        long j3;
        do {
            atomicLongFieldUpdater = f10569f;
            j3 = atomicLongFieldUpdater.get(this);
            if ((j3 & 2305843009213693952L) != 0) {
                return true;
            }
            if ((1152921504606846976L & j3) != 0) {
                return false;
            }
        } while (!atomicLongFieldUpdater.compareAndSet(this, j3, 2305843009213693952L | j3));
        return true;
    }

    public final l c() {
        AtomicLongFieldUpdater atomicLongFieldUpdater;
        long j3;
        while (true) {
            atomicLongFieldUpdater = f10569f;
            j3 = atomicLongFieldUpdater.get(this);
            if ((j3 & 1152921504606846976L) != 0) {
                break;
            }
            long j10 = j3 | 1152921504606846976L;
            if (atomicLongFieldUpdater.compareAndSet(this, j3, j10)) {
                j3 = j10;
                break;
            }
        }
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f10568e;
            l lVar = (l) atomicReferenceFieldUpdater.get(this);
            if (lVar != null) {
                return lVar;
            }
            l lVar2 = new l(this.f10571a * 2, this.f10572b);
            int i7 = (int) (1073741823 & j3);
            int i10 = (int) ((1152921503533105152L & j3) >> 30);
            while (true) {
                int i11 = this.c;
                int i12 = i7 & i11;
                if (i12 == (i11 & i10)) {
                    break;
                }
                Object kVar = this.d.get(i12);
                if (kVar == null) {
                    kVar = new k(i7);
                }
                lVar2.d.set(lVar2.c & i7, kVar);
                i7++;
            }
            atomicLongFieldUpdater.set(lVar2, (-1152921504606846977L) & j3);
            while (!atomicReferenceFieldUpdater.compareAndSet(this, null, lVar2) && atomicReferenceFieldUpdater.get(this) == null) {
            }
        }
    }

    public final Object d() {
        while (true) {
            AtomicLongFieldUpdater atomicLongFieldUpdater = f10569f;
            long j3 = atomicLongFieldUpdater.get(this);
            if ((j3 & 1152921504606846976L) != 0) {
                return f10570g;
            }
            int i7 = (int) (j3 & 1073741823);
            int i10 = this.c;
            int i11 = i7 & i10;
            if ((((int) ((1152921503533105152L & j3) >> 30)) & i10) == i11) {
                return null;
            }
            AtomicReferenceArray atomicReferenceArray = this.d;
            Object obj = atomicReferenceArray.get(i11);
            boolean z6 = this.f10572b;
            if (obj == null) {
                if (z6) {
                    return null;
                }
            } else {
                if (obj instanceof k) {
                    return null;
                }
                long j10 = (i7 + 1) & 1073741823;
                if (atomicLongFieldUpdater.compareAndSet(this, j3, (j3 & (-1073741824)) | j10)) {
                    atomicReferenceArray.set(i11, null);
                    return obj;
                }
                if (z6) {
                    l lVarC = this;
                    while (true) {
                        AtomicLongFieldUpdater atomicLongFieldUpdater2 = f10569f;
                        long j11 = atomicLongFieldUpdater2.get(lVarC);
                        int i12 = (int) (j11 & 1073741823);
                        if ((j11 & 1152921504606846976L) != 0) {
                            lVarC = lVarC.c();
                        } else {
                            if (atomicLongFieldUpdater2.compareAndSet(lVarC, j11, (j11 & (-1073741824)) | j10)) {
                                lVarC.d.set(lVarC.c & i12, null);
                                lVarC = null;
                            } else {
                                continue;
                            }
                        }
                        if (lVarC == null) {
                            return obj;
                        }
                    }
                }
            }
        }
    }
}
