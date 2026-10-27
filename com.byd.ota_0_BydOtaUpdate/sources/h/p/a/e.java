package h.p.a;

import d.a.i;
import h.l;
import io.reactivex.exceptions.CompositeException;

/* JADX INFO: compiled from: ResultObservable.java */
/* JADX INFO: loaded from: classes.dex */
public final class e<T> extends d.a.g<d<T>> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final d.a.g<l<T>> f5866c;

    /* JADX INFO: compiled from: ResultObservable.java */
    public static class a<R> implements i<l<R>> {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final i<? super d<R>> f5867c;

        public a(i<? super d<R>> iVar) {
            this.f5867c = iVar;
        }

        @Override // d.a.i
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onNext(l<R> lVar) {
            this.f5867c.onNext(d.b(lVar));
        }

        @Override // d.a.i
        public void onComplete() {
            this.f5867c.onComplete();
        }

        @Override // d.a.i
        public void onError(Throwable th) {
            try {
                this.f5867c.onNext(d.a(th));
                this.f5867c.onComplete();
            } catch (Throwable th2) {
                try {
                    this.f5867c.onError(th2);
                } catch (Throwable th3) {
                    d.a.n.a.a(th3);
                    d.a.r.a.o(new CompositeException(th2, th3));
                }
            }
        }

        @Override // d.a.i
        public void onSubscribe(d.a.m.b bVar) {
            this.f5867c.onSubscribe(bVar);
        }
    }

    public e(d.a.g<l<T>> gVar) {
        this.f5866c = gVar;
    }

    @Override // d.a.g
    public void t(i<? super d<T>> iVar) {
        this.f5866c.a(new a(iVar));
    }
}
