package kotlinx.coroutines.internal;

import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: compiled from: LockFreeTaskQueue.kt */
/* JADX INFO: loaded from: classes.dex */
public final class r<E> {
    private volatile Object _next = null;
    private volatile long _state = 0;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f7673a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private AtomicReferenceArray f7674b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f7675c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final boolean f7676d;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final a f7672h = new a(null);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final c0 f7671g = new c0("REMOVE_FROZEN");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final AtomicReferenceFieldUpdater f7669e = AtomicReferenceFieldUpdater.newUpdater(r.class, Object.class, "_next");

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final AtomicLongFieldUpdater f7670f = AtomicLongFieldUpdater.newUpdater(r.class, "_state");

    /* JADX INFO: compiled from: LockFreeTaskQueue.kt */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(kotlin.jvm.internal.g gVar) {
            this();
        }

        public final int a(long j7) {
            return (j7 & 2305843009213693952L) != 0 ? 2 : 1;
        }

        public final long b(long j7, int i7) {
            return d(j7, 1073741823L) | (((long) i7) << 0);
        }

        public final long c(long j7, int i7) {
            return d(j7, 1152921503533105152L) | (((long) i7) << 30);
        }

        public final long d(long j7, long j8) {
            return j7 & (~j8);
        }
    }

    /* JADX INFO: compiled from: LockFreeTaskQueue.kt */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f7677a;

        public b(int i7) {
            this.f7677a = i7;
        }
    }

    public r(int i7, boolean z6) {
        this.f7675c = i7;
        this.f7676d = z6;
        int i8 = i7 - 1;
        this.f7673a = i8;
        this.f7674b = new AtomicReferenceArray(i7);
        if (!(i8 <= 1073741823)) {
            throw new IllegalStateException("Check failed.".toString());
        }
        if (!((i7 & i8) == 0)) {
            throw new IllegalStateException("Check failed.".toString());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final r<E> b(long j7) {
        r<E> rVar = new r<>(this.f7675c * 2, this.f7676d);
        int i7 = (int) ((1073741823 & j7) >> 0);
        int i8 = (int) ((1152921503533105152L & j7) >> 30);
        while (true) {
            int i9 = this.f7673a;
            if ((i7 & i9) == (i8 & i9)) {
                rVar._state = f7672h.d(j7, 1152921504606846976L);
                return rVar;
            }
            Object bVar = this.f7674b.get(i9 & i7);
            if (bVar == null) {
                bVar = new b(i7);
            }
            rVar.f7674b.set(rVar.f7673a & i7, bVar);
            i7++;
        }
    }

    private final r<E> c(long j7) {
        while (true) {
            r<E> rVar = (r) this._next;
            if (rVar != null) {
                return rVar;
            }
            kotlinx.coroutines.j.a(f7669e, this, null, b(j7));
        }
    }

    private final r<E> e(int i7, E e7) {
        Object obj = this.f7674b.get(this.f7673a & i7);
        if (!(obj instanceof b) || ((b) obj).f7677a != i7) {
            return null;
        }
        this.f7674b.set(i7 & this.f7673a, e7);
        return this;
    }

    private final long h() {
        long j7;
        long j8;
        do {
            j7 = this._state;
            if ((j7 & 1152921504606846976L) != 0) {
                return j7;
            }
            j8 = j7 | 1152921504606846976L;
        } while (!f7670f.compareAndSet(this, j7, j8));
        return j8;
    }

    private final r<E> k(int i7, int i8) {
        long j7;
        a aVar;
        int i9;
        do {
            j7 = this._state;
            aVar = f7672h;
            i9 = (int) ((1073741823 & j7) >> 0);
            if ((1152921504606846976L & j7) != 0) {
                return i();
            }
        } while (!f7670f.compareAndSet(this, j7, aVar.b(j7, i8)));
        this.f7674b.set(this.f7673a & i9, null);
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x004e, code lost:
    
        return 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int a(E r14) {
        /*
            r13 = this;
        L0:
            long r2 = r13._state
            r0 = 3458764513820540928(0x3000000000000000, double:1.727233711018889E-77)
            long r0 = r0 & r2
            r6 = 0
            int r0 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
            if (r0 == 0) goto L12
            kotlinx.coroutines.internal.r$a r14 = kotlinx.coroutines.internal.r.f7672h
            int r14 = r14.a(r2)
            return r14
        L12:
            kotlinx.coroutines.internal.r$a r0 = kotlinx.coroutines.internal.r.f7672h
            r4 = 1073741823(0x3fffffff, double:5.304989472E-315)
            long r4 = r4 & r2
            r8 = 0
            long r4 = r4 >> r8
            int r1 = (int) r4
            r4 = 1152921503533105152(0xfffffffc0000000, double:1.2882296003504729E-231)
            long r4 = r4 & r2
            r9 = 30
            long r4 = r4 >> r9
            int r9 = (int) r4
            int r10 = r13.f7673a
            int r4 = r9 + 2
            r4 = r4 & r10
            r5 = r1 & r10
            r11 = 1
            if (r4 != r5) goto L30
            return r11
        L30:
            boolean r4 = r13.f7676d
            r5 = 1073741823(0x3fffffff, float:1.9999999)
            if (r4 != 0) goto L4f
            java.util.concurrent.atomic.AtomicReferenceArray r4 = r13.f7674b
            r12 = r9 & r10
            java.lang.Object r4 = r4.get(r12)
            if (r4 == 0) goto L4f
            int r0 = r13.f7675c
            r2 = 1024(0x400, float:1.435E-42)
            if (r0 < r2) goto L4e
            int r9 = r9 - r1
            r1 = r9 & r5
            int r0 = r0 >> 1
            if (r1 <= r0) goto L0
        L4e:
            return r11
        L4f:
            int r1 = r9 + 1
            r1 = r1 & r5
            java.util.concurrent.atomic.AtomicLongFieldUpdater r4 = kotlinx.coroutines.internal.r.f7670f
            long r11 = r0.c(r2, r1)
            r0 = r4
            r1 = r13
            r4 = r11
            boolean r0 = r0.compareAndSet(r1, r2, r4)
            if (r0 == 0) goto L0
            java.util.concurrent.atomic.AtomicReferenceArray r0 = r13.f7674b
            r1 = r9 & r10
            r0.set(r1, r14)
            r0 = r13
        L69:
            long r1 = r0._state
            r3 = 1152921504606846976(0x1000000000000000, double:1.2882297539194267E-231)
            long r1 = r1 & r3
            int r1 = (r1 > r6 ? 1 : (r1 == r6 ? 0 : -1))
            if (r1 != 0) goto L73
            goto L7e
        L73:
            kotlinx.coroutines.internal.r r0 = r0.i()
            kotlinx.coroutines.internal.r r0 = r0.e(r9, r14)
            if (r0 == 0) goto L7e
            goto L69
        L7e:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.internal.r.a(java.lang.Object):int");
    }

    public final boolean d() {
        long j7;
        do {
            j7 = this._state;
            if ((j7 & 2305843009213693952L) != 0) {
                return true;
            }
            if ((1152921504606846976L & j7) != 0) {
                return false;
            }
        } while (!f7670f.compareAndSet(this, j7, j7 | 2305843009213693952L));
        return true;
    }

    public final int f() {
        long j7 = this._state;
        return (((int) ((j7 & 1152921503533105152L) >> 30)) - ((int) ((1073741823 & j7) >> 0))) & 1073741823;
    }

    public final boolean g() {
        long j7 = this._state;
        return ((int) ((1073741823 & j7) >> 0)) == ((int) ((j7 & 1152921503533105152L) >> 30));
    }

    public final r<E> i() {
        return c(h());
    }

    public final Object j() {
        while (true) {
            long j7 = this._state;
            if ((1152921504606846976L & j7) != 0) {
                return f7671g;
            }
            a aVar = f7672h;
            int i7 = (int) ((1073741823 & j7) >> 0);
            int i8 = (int) ((1152921503533105152L & j7) >> 30);
            int i9 = this.f7673a;
            if ((i8 & i9) == (i7 & i9)) {
                return null;
            }
            Object obj = this.f7674b.get(i9 & i7);
            if (obj == null) {
                if (this.f7676d) {
                    return null;
                }
            } else {
                if (obj instanceof b) {
                    return null;
                }
                int i10 = (i7 + 1) & 1073741823;
                if (f7670f.compareAndSet(this, j7, aVar.b(j7, i10))) {
                    this.f7674b.set(this.f7673a & i7, null);
                    return obj;
                }
                if (this.f7676d) {
                    r<E> rVarK = this;
                    do {
                        rVarK = rVarK.k(i7, i10);
                    } while (rVarK != null);
                    return obj;
                }
            }
        }
    }
}
