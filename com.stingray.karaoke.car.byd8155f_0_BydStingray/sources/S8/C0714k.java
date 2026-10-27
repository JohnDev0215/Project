package S8;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: renamed from: S8.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0714k extends Z {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ int f8119p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final C0712i f8120q;

    public /* synthetic */ C0714k(C0712i c0712i, int i7) {
        this.f8119p = i7;
        this.f8120q = c0712i;
    }

    @Override // S8.Z
    public final boolean k() {
        switch (this.f8119p) {
            case 0:
                return true;
            default:
                return false;
        }
    }

    @Override // S8.Z
    public final void l(Throwable th) throws IllegalAccessException, InvocationTargetException {
        switch (this.f8119p) {
            case 0:
                d0 d0VarJ = j();
                C0712i c0712i = this.f8120q;
                Throwable thP = c0712i.p(d0VarJ);
                if (c0712i.x()) {
                    X8.f fVar = (X8.f) c0712i.o;
                    while (true) {
                        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = X8.f.f10554s;
                        Object obj = atomicReferenceFieldUpdater.get(fVar);
                        B2.q qVar = X8.a.c;
                        if (A7.m.a(obj, qVar)) {
                            while (!atomicReferenceFieldUpdater.compareAndSet(fVar, qVar, thP)) {
                                if (atomicReferenceFieldUpdater.get(fVar) != qVar) {
                                }
                                break;
                            }
                        } else if (!(obj instanceof Throwable)) {
                            while (!atomicReferenceFieldUpdater.compareAndSet(fVar, obj, null)) {
                                if (atomicReferenceFieldUpdater.get(fVar) != obj) {
                                }
                            }
                        }
                    }
                }
                c0712i.l(thP);
                if (!c0712i.x()) {
                    c0712i.m();
                }
                break;
            default:
                this.f8120q.resumeWith(m7.q.f19267a);
                break;
        }
    }
}
