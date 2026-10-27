package h.p.a;

import d.a.i;
import h.l;
import io.reactivex.exceptions.CompositeException;

/* JADX INFO: compiled from: CallEnqueueObservable.java */
/* JADX INFO: loaded from: classes.dex */
public final class b<T> extends d.a.g<l<T>> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final h.b<T> f5859c;

    /* JADX INFO: compiled from: CallEnqueueObservable.java */
    public static final class a<T> implements d.a.m.b, h.d<T> {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final h.b<?> f5860c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final i<? super l<T>> f5861d;
        public boolean s = false;

        public a(h.b<?> bVar, i<? super l<T>> iVar) {
            this.f5860c = bVar;
            this.f5861d = iVar;
        }

        @Override // h.d
        public void a(h.b<T> bVar, Throwable th) {
            if (bVar.d()) {
                return;
            }
            try {
                this.f5861d.onError(th);
            } catch (Throwable th2) {
                d.a.n.a.a(th2);
                d.a.r.a.o(new CompositeException(th, th2));
            }
        }

        @Override // h.d
        public void b(h.b<T> bVar, l<T> lVar) {
            if (bVar.d()) {
                return;
            }
            try {
                this.f5861d.onNext(lVar);
                if (bVar.d()) {
                    return;
                }
                this.s = true;
                this.f5861d.onComplete();
            } catch (Throwable th) {
                if (this.s) {
                    d.a.r.a.o(th);
                    return;
                }
                if (bVar.d()) {
                    return;
                }
                try {
                    this.f5861d.onError(th);
                } catch (Throwable th2) {
                    d.a.n.a.a(th2);
                    d.a.r.a.o(new CompositeException(th, th2));
                }
            }
        }

        @Override // d.a.m.b
        public void dispose() {
            this.f5860c.cancel();
        }

        @Override // d.a.m.b
        public boolean isDisposed() {
            return this.f5860c.d();
        }
    }

    public b(h.b<T> bVar) {
        this.f5859c = bVar;
    }

    @Override // d.a.g
    public void t(i<? super l<T>> iVar) {
        h.b<T> bVarM1clone = this.f5859c.m1clone();
        a aVar = new a(bVarM1clone, iVar);
        iVar.onSubscribe(aVar);
        bVarM1clone.v(aVar);
    }
}
