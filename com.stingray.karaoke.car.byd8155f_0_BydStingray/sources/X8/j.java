package X8;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ AtomicReferenceFieldUpdater f10566a = AtomicReferenceFieldUpdater.newUpdater(j.class, Object.class, "_cur$volatile");
    private volatile /* synthetic */ Object _cur$volatile = new l(8, false);

    public final boolean a(Runnable runnable) {
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f10566a;
            l lVar = (l) atomicReferenceFieldUpdater.get(this);
            int iA = lVar.a(runnable);
            if (iA == 0) {
                return true;
            }
            if (iA == 1) {
                l lVarC = lVar.c();
                while (!atomicReferenceFieldUpdater.compareAndSet(this, lVar, lVarC) && atomicReferenceFieldUpdater.get(this) == lVar) {
                }
            } else if (iA == 2) {
                return false;
            }
        }
    }

    public final void b() {
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f10566a;
            l lVar = (l) atomicReferenceFieldUpdater.get(this);
            if (lVar.b()) {
                return;
            }
            l lVarC = lVar.c();
            while (!atomicReferenceFieldUpdater.compareAndSet(this, lVar, lVarC) && atomicReferenceFieldUpdater.get(this) == lVar) {
            }
        }
    }

    public final int c() {
        l lVar = (l) f10566a.get(this);
        lVar.getClass();
        long j3 = l.f10569f.get(lVar);
        return 1073741823 & (((int) ((j3 & 1152921503533105152L) >> 30)) - ((int) (1073741823 & j3)));
    }

    public final Object d() {
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f10566a;
            l lVar = (l) atomicReferenceFieldUpdater.get(this);
            Object objD = lVar.d();
            if (objD != l.f10570g) {
                return objD;
            }
            l lVarC = lVar.c();
            while (!atomicReferenceFieldUpdater.compareAndSet(this, lVar, lVarC) && atomicReferenceFieldUpdater.get(this) == lVar) {
            }
        }
    }
}
