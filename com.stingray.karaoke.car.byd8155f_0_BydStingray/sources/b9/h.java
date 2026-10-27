package b9;

import B2.q;
import S8.C0711h;
import S8.InterfaceC0710g;
import S8.v0;
import X8.r;
import a9.C0933c;
import a9.InterfaceC0934d;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public class h {
    public static final /* synthetic */ AtomicReferenceFieldUpdater c = AtomicReferenceFieldUpdater.newUpdater(h.class, Object.class, "head$volatile");
    public static final /* synthetic */ AtomicLongFieldUpdater d = AtomicLongFieldUpdater.newUpdater(h.class, "deqIdx$volatile");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final /* synthetic */ AtomicReferenceFieldUpdater f12339e = AtomicReferenceFieldUpdater.newUpdater(h.class, Object.class, "tail$volatile");

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final /* synthetic */ AtomicLongFieldUpdater f12340f = AtomicLongFieldUpdater.newUpdater(h.class, "enqIdx$volatile");

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final /* synthetic */ AtomicIntegerFieldUpdater f12341g = AtomicIntegerFieldUpdater.newUpdater(h.class, "_availablePermits$volatile");
    private volatile /* synthetic */ int _availablePermits$volatile;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f12342a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C0711h f12343b;
    private volatile /* synthetic */ long deqIdx$volatile;
    private volatile /* synthetic */ long enqIdx$volatile;
    private volatile /* synthetic */ Object head$volatile;
    private volatile /* synthetic */ Object tail$volatile;

    public h(int i7, int i10) {
        this.f12342a = i7;
        if (i7 <= 0) {
            throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.e(i7, "Semaphore should have at least 1 permit, but had ").toString());
        }
        if (i10 < 0 || i10 > i7) {
            throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.e(i7, "The number of acquired permits should be in 0..").toString());
        }
        k kVar = new k(0L, null, 2);
        this.head$volatile = kVar;
        this.tail$volatile = kVar;
        this._availablePermits$volatile = i7 - i10;
        this.f12343b = new C0711h(this, 2);
    }

    public final boolean a(v0 v0Var) {
        Object objD;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f12339e;
        k kVar = (k) atomicReferenceFieldUpdater.get(this);
        long andIncrement = f12340f.getAndIncrement(this);
        f fVar = f.f12337t;
        long j3 = andIncrement / ((long) j.f12347f);
        loop0: while (true) {
            objD = X8.a.d(kVar, j3, fVar);
            if (!X8.a.g(objD)) {
                r rVarE = X8.a.e(objD);
                while (true) {
                    r rVar = (r) atomicReferenceFieldUpdater.get(this);
                    if (rVar.f10577n >= rVarE.f10577n) {
                        break loop0;
                    }
                    if (!rVarE.j()) {
                        break;
                    }
                    while (!atomicReferenceFieldUpdater.compareAndSet(this, rVar, rVarE)) {
                        if (atomicReferenceFieldUpdater.get(this) != rVar) {
                            if (rVarE.f()) {
                                rVarE.e();
                            }
                        }
                    }
                    if (rVar.f()) {
                        rVar.e();
                    }
                }
            } else {
                break;
            }
        }
        k kVar2 = (k) X8.a.e(objD);
        int i7 = (int) (andIncrement % ((long) j.f12347f));
        AtomicReferenceArray atomicReferenceArray = kVar2.f12348p;
        while (!atomicReferenceArray.compareAndSet(i7, null, v0Var)) {
            if (atomicReferenceArray.get(i7) != null) {
                q qVar = j.f12345b;
                q qVar2 = j.c;
                while (!atomicReferenceArray.compareAndSet(i7, qVar, qVar2)) {
                    if (atomicReferenceArray.get(i7) != qVar) {
                        return false;
                    }
                }
                boolean z6 = v0Var instanceof InterfaceC0710g;
                m7.q qVar3 = m7.q.f19267a;
                if (z6) {
                    ((InterfaceC0710g) v0Var).n(qVar3, this.f12343b);
                } else {
                    if (!(v0Var instanceof InterfaceC0934d)) {
                        throw new IllegalStateException(("unexpected: " + v0Var).toString());
                    }
                    ((C0933c) ((InterfaceC0934d) v0Var)).f11230p = qVar3;
                }
                return true;
            }
        }
        v0Var.b(kVar2, i7);
        return true;
    }

    public final void b() {
        int i7;
        Object objD;
        boolean z6;
        do {
            AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = f12341g;
            int andIncrement = atomicIntegerFieldUpdater.getAndIncrement(this);
            int i10 = this.f12342a;
            if (andIncrement >= i10) {
                do {
                    i7 = atomicIntegerFieldUpdater.get(this);
                    if (i7 <= i10) {
                        break;
                    }
                } while (!atomicIntegerFieldUpdater.compareAndSet(this, i7, i10));
                throw new IllegalStateException(("The number of released permits cannot be greater than " + i10).toString());
            }
            if (andIncrement >= 0) {
                return;
            }
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = c;
            k kVar = (k) atomicReferenceFieldUpdater.get(this);
            long andIncrement2 = d.getAndIncrement(this);
            long j3 = andIncrement2 / ((long) j.f12347f);
            g gVar = g.f12338t;
            while (true) {
                objD = X8.a.d(kVar, j3, gVar);
                if (X8.a.g(objD)) {
                    break;
                }
                r rVarE = X8.a.e(objD);
                while (true) {
                    r rVar = (r) atomicReferenceFieldUpdater.get(this);
                    if (rVar.f10577n >= rVarE.f10577n) {
                        break;
                    }
                    if (!rVarE.j()) {
                        break;
                    }
                    while (!atomicReferenceFieldUpdater.compareAndSet(this, rVar, rVarE)) {
                        if (atomicReferenceFieldUpdater.get(this) != rVar) {
                            if (rVarE.f()) {
                                rVarE.e();
                            }
                        }
                    }
                    if (rVar.f()) {
                        rVar.e();
                    }
                }
            }
            k kVar2 = (k) X8.a.e(objD);
            kVar2.b();
            z6 = false;
            if (kVar2.f10577n <= j3) {
                int i11 = (int) (andIncrement2 % ((long) j.f12347f));
                q qVar = j.f12345b;
                AtomicReferenceArray atomicReferenceArray = kVar2.f12348p;
                Object andSet = atomicReferenceArray.getAndSet(i11, qVar);
                if (andSet == null) {
                    int i12 = j.f12344a;
                    for (int i13 = 0; i13 < i12; i13++) {
                        if (atomicReferenceArray.get(i11) == j.c) {
                            z6 = true;
                            break;
                        }
                    }
                    q qVar2 = j.f12345b;
                    q qVar3 = j.d;
                    while (true) {
                        if (!atomicReferenceArray.compareAndSet(i11, qVar2, qVar3)) {
                            if (atomicReferenceArray.get(i11) != qVar2) {
                                break;
                            }
                        } else {
                            z6 = true;
                            break;
                        }
                    }
                    z6 = !z6;
                } else if (andSet != j.f12346e) {
                    boolean z10 = andSet instanceof InterfaceC0710g;
                    m7.q qVar4 = m7.q.f19267a;
                    if (!z10) {
                        if (!(andSet instanceof InterfaceC0934d)) {
                            throw new IllegalStateException(("unexpected: " + andSet).toString());
                        }
                        if (((C0933c) ((InterfaceC0934d) andSet)).g(this, qVar4) == 0) {
                            z6 = true;
                            break;
                            break;
                        }
                    } else {
                        InterfaceC0710g interfaceC0710g = (InterfaceC0710g) andSet;
                        q qVarI = interfaceC0710g.i(qVar4, this.f12343b);
                        if (qVarI != null) {
                            interfaceC0710g.r(qVarI);
                            z6 = true;
                            break;
                            break;
                        }
                    }
                }
            }
        } while (!z6);
    }
}
