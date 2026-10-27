package h.p.a;

import d.a.i;
import h.l;
import io.reactivex.exceptions.CompositeException;

/* JADX INFO: compiled from: CallExecuteObservable.java */
/* JADX INFO: loaded from: classes.dex */
public final class c<T> extends d.a.g<l<T>> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final h.b<T> f5862c;

    /* JADX INFO: compiled from: CallExecuteObservable.java */
    public static final class a implements d.a.m.b {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final h.b<?> f5863c;

        public a(h.b<?> bVar) {
            this.f5863c = bVar;
        }

        @Override // d.a.m.b
        public void dispose() {
            this.f5863c.cancel();
        }

        @Override // d.a.m.b
        public boolean isDisposed() {
            return this.f5863c.d();
        }
    }

    public c(h.b<T> bVar) {
        this.f5862c = bVar;
    }

    @Override // d.a.g
    public void t(i<? super l<T>> iVar) {
        boolean z;
        h.b<T> bVarM1clone = this.f5862c.m1clone();
        iVar.onSubscribe(new a(bVarM1clone));
        try {
            l<T> lVarC = bVarM1clone.c();
            if (!bVarM1clone.d()) {
                iVar.onNext(lVarC);
            }
            if (bVarM1clone.d()) {
                return;
            }
            try {
                iVar.onComplete();
            } catch (Throwable th) {
                th = th;
                z = true;
                d.a.n.a.a(th);
                if (z) {
                    d.a.r.a.o(th);
                    return;
                }
                if (bVarM1clone.d()) {
                    return;
                }
                try {
                    iVar.onError(th);
                } catch (Throwable th2) {
                    d.a.n.a.a(th2);
                    d.a.r.a.o(new CompositeException(th, th2));
                }
            }
        } catch (Throwable th3) {
            th = th3;
            z = false;
        }
    }
}
