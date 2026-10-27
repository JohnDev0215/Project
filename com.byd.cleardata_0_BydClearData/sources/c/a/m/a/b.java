package c.a.m.a;

import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public enum b implements c.a.j.b {
    DISPOSED;

    public static boolean a(AtomicReference<c.a.j.b> atomicReference) {
        c.a.j.b andSet;
        c.a.j.b bVar = atomicReference.get();
        b bVar2 = DISPOSED;
        if (bVar == bVar2 || (andSet = atomicReference.getAndSet(bVar2)) == bVar2) {
            return false;
        }
        if (andSet == null) {
            return true;
        }
        andSet.e();
        return true;
    }

    public static boolean b(c.a.j.b bVar) {
        return bVar == DISPOSED;
    }

    public static void c() {
        c.a.n.a.l(new c.a.k.d("Disposable already set!"));
    }

    public static boolean d(AtomicReference<c.a.j.b> atomicReference, c.a.j.b bVar) {
        c.a.m.b.b.c(bVar, "d is null");
        if (atomicReference.compareAndSet(null, bVar)) {
            return true;
        }
        bVar.e();
        if (atomicReference.get() == DISPOSED) {
            return false;
        }
        c();
        return false;
    }

    public static boolean f(c.a.j.b bVar, c.a.j.b bVar2) {
        if (bVar2 == null) {
            c.a.n.a.l(new NullPointerException("next is null"));
            return false;
        }
        if (bVar == null) {
            return true;
        }
        bVar2.e();
        c();
        return false;
    }

    @Override // c.a.j.b
    public void e() {
    }

    @Override // c.a.j.b
    public boolean g() {
        return true;
    }
}
