package kotlinx.coroutines.sync;

import g5.l;
import g5.r;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.coroutines.jvm.internal.h;
import kotlin.jvm.internal.m;
import kotlinx.coroutines.i;
import kotlinx.coroutines.internal.a0;
import kotlinx.coroutines.internal.f;
import kotlinx.coroutines.internal.g;
import kotlinx.coroutines.internal.z;
import kotlinx.coroutines.j;
import kotlinx.coroutines.k;
import r5.l;

/* JADX INFO: compiled from: Semaphore.kt */
/* JADX INFO: loaded from: classes.dex */
final class c implements b {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final AtomicReferenceFieldUpdater f7811c = AtomicReferenceFieldUpdater.newUpdater(c.class, Object.class, "head");

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final AtomicLongFieldUpdater f7812d = AtomicLongFieldUpdater.newUpdater(c.class, "deqIdx");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final AtomicReferenceFieldUpdater f7813e = AtomicReferenceFieldUpdater.newUpdater(c.class, Object.class, "tail");

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final AtomicLongFieldUpdater f7814f = AtomicLongFieldUpdater.newUpdater(c.class, "enqIdx");

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    static final AtomicIntegerFieldUpdater f7815g = AtomicIntegerFieldUpdater.newUpdater(c.class, "_availablePermits");
    volatile int _availablePermits;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final l<Throwable, r> f7816a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f7817b;
    private volatile long deqIdx = 0;
    private volatile long enqIdx = 0;
    private volatile Object head;
    private volatile Object tail;

    /* JADX INFO: compiled from: Semaphore.kt */
    static final class a extends m implements l<Throwable, r> {
        a() {
            super(1);
        }

        @Override // r5.l
        public /* bridge */ /* synthetic */ r invoke(Throwable th) {
            invoke2(th);
            return r.f6563a;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(Throwable th) {
            c.this.a();
        }
    }

    public c(int i7, int i8) {
        this.f7817b = i7;
        if (!(i7 > 0)) {
            throw new IllegalArgumentException(("Semaphore should have at least 1 permit, but had " + i7).toString());
        }
        if (!(i8 >= 0 && i7 >= i8)) {
            throw new IllegalArgumentException(("The number of acquired permits should be in 0.." + i7).toString());
        }
        e eVar = new e(0L, null, 2);
        this.head = eVar;
        this.tail = eVar;
        this._availablePermits = i7 - i8;
        this.f7816a = new a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean e(i<? super r> iVar) {
        Object objA;
        boolean z6;
        e eVar = (e) this.tail;
        long andIncrement = f7814f.getAndIncrement(this);
        long j7 = andIncrement / ((long) d.f7824f);
        do {
            z zVar = eVar;
            while (true) {
                if (zVar.m() >= j7 && !zVar.g()) {
                    objA = a0.a(zVar);
                    break;
                }
                Object objE = zVar.e();
                if (objE == f.f7628a) {
                    objA = a0.a(f.f7628a);
                    break;
                }
                z zVarJ = (z) ((g) objE);
                if (zVarJ == null) {
                    zVarJ = d.j(zVar.m() + 1, (e) zVar);
                    if (zVar.k(zVarJ)) {
                        if (zVar.g()) {
                            zVar.j();
                        }
                    }
                }
                zVar = zVarJ;
            }
            if (a0.c(objA)) {
                break;
            }
            z zVarB = a0.b(objA);
            while (true) {
                z zVar2 = (z) this.tail;
                if (zVar2.m() >= zVarB.m()) {
                    break;
                }
                if (!zVarB.p()) {
                    z6 = false;
                    break;
                }
                if (j.a(f7813e, this, zVar2, zVarB)) {
                    if (zVar2.l()) {
                        zVar2.j();
                    }
                } else if (zVarB.l()) {
                    zVarB.j();
                }
            }
            z6 = true;
        } while (!z6);
        e eVar2 = (e) a0.b(objA);
        int i7 = (int) (andIncrement % ((long) d.f7824f));
        if (eVar2.f7825e.compareAndSet(i7, null, iVar)) {
            iVar.d(new kotlinx.coroutines.sync.a(eVar2, i7));
            return true;
        }
        if (!eVar2.f7825e.compareAndSet(i7, d.f7820b, d.f7821c)) {
            return false;
        }
        r rVar = r.f6563a;
        l.a aVar = g5.l.f6557e;
        iVar.resumeWith(g5.l.a(rVar));
        return true;
    }

    private final boolean f(i<? super r> iVar) {
        Object objR = iVar.r(r.f6563a, null, this.f7816a);
        if (objR == null) {
            return false;
        }
        iVar.v(objR);
        return true;
    }

    private final boolean g() {
        Object objA;
        int i7;
        boolean z6;
        e eVar = (e) this.head;
        long andIncrement = f7812d.getAndIncrement(this);
        long j7 = andIncrement / ((long) d.f7824f);
        do {
            z zVar = eVar;
            while (true) {
                if (zVar.m() >= j7 && !zVar.g()) {
                    objA = a0.a(zVar);
                    break;
                }
                Object objE = zVar.e();
                if (objE == f.f7628a) {
                    objA = a0.a(f.f7628a);
                    break;
                }
                z zVarJ = (z) ((g) objE);
                if (zVarJ == null) {
                    zVarJ = d.j(zVar.m() + 1, (e) zVar);
                    if (zVar.k(zVarJ)) {
                        if (zVar.g()) {
                            zVar.j();
                        }
                    }
                }
                zVar = zVarJ;
            }
            if (a0.c(objA)) {
                break;
            }
            z zVarB = a0.b(objA);
            while (true) {
                z zVar2 = (z) this.head;
                if (zVar2.m() >= zVarB.m()) {
                    break;
                }
                if (!zVarB.p()) {
                    z6 = false;
                    break;
                }
                if (j.a(f7811c, this, zVar2, zVarB)) {
                    if (zVar2.l()) {
                        zVar2.j();
                    }
                } else if (zVarB.l()) {
                    zVarB.j();
                }
            }
            z6 = true;
        } while (!z6);
        e eVar2 = (e) a0.b(objA);
        eVar2.b();
        if (eVar2.m() > j7) {
            return false;
        }
        int i8 = (int) (andIncrement % ((long) d.f7824f));
        Object andSet = eVar2.f7825e.getAndSet(i8, d.f7820b);
        if (andSet != null) {
            if (andSet == d.f7823e) {
                return false;
            }
            return f((i) andSet);
        }
        int i9 = d.f7819a;
        for (i7 = 0; i7 < i9; i7++) {
            if (eVar2.f7825e.get(i8) == d.f7821c) {
                return true;
            }
        }
        return !eVar2.f7825e.compareAndSet(i8, d.f7820b, d.f7822d);
    }

    @Override // kotlinx.coroutines.sync.b
    public void a() {
        while (true) {
            int i7 = this._availablePermits;
            if (!(i7 < this.f7817b)) {
                throw new IllegalStateException(("The number of released permits cannot be greater than " + this.f7817b).toString());
            }
            if (f7815g.compareAndSet(this, i7, i7 + 1) && (i7 >= 0 || g())) {
                return;
            }
        }
    }

    @Override // kotlinx.coroutines.sync.b
    public Object b(k5.d<? super r> dVar) throws Throwable {
        if (f7815g.getAndDecrement(this) > 0) {
            return r.f6563a;
        }
        Object objD = d(dVar);
        return objD == l5.d.d() ? objD : r.f6563a;
    }

    final /* synthetic */ Object d(k5.d<? super r> dVar) throws Throwable {
        k kVarB = kotlinx.coroutines.m.b(l5.c.c(dVar));
        while (true) {
            if (e(kVarB)) {
                break;
            }
            if (f7815g.getAndDecrement(this) > 0) {
                r rVar = r.f6563a;
                l.a aVar = g5.l.f6557e;
                kVarB.resumeWith(g5.l.a(rVar));
                break;
            }
        }
        Object objY = kVarB.y();
        if (objY == l5.d.d()) {
            h.c(dVar);
        }
        return objY;
    }
}
