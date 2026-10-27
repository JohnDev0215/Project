package h.p.a;

import d.a.i;
import h.l;
import io.reactivex.exceptions.CompositeException;
import retrofit2.adapter.rxjava2.HttpException;

/* JADX INFO: compiled from: BodyObservable.java */
/* JADX INFO: loaded from: classes.dex */
public final class a<T> extends d.a.g<T> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final d.a.g<l<T>> f5856c;

    /* JADX INFO: renamed from: h.p.a.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: BodyObservable.java */
    public static class C0103a<R> implements i<l<R>> {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final i<? super R> f5857c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f5858d;

        public C0103a(i<? super R> iVar) {
            this.f5857c = iVar;
        }

        @Override // d.a.i
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onNext(l<R> lVar) {
            if (lVar.d()) {
                this.f5857c.onNext(lVar.a());
                return;
            }
            this.f5858d = true;
            HttpException httpException = new HttpException(lVar);
            try {
                this.f5857c.onError(httpException);
            } catch (Throwable th) {
                d.a.n.a.a(th);
                d.a.r.a.o(new CompositeException(httpException, th));
            }
        }

        @Override // d.a.i
        public void onComplete() {
            if (this.f5858d) {
                return;
            }
            this.f5857c.onComplete();
        }

        @Override // d.a.i
        public void onError(Throwable th) {
            if (!this.f5858d) {
                this.f5857c.onError(th);
                return;
            }
            AssertionError assertionError = new AssertionError("This should never happen! Report as a bug with the full stacktrace.");
            assertionError.initCause(th);
            d.a.r.a.o(assertionError);
        }

        @Override // d.a.i
        public void onSubscribe(d.a.m.b bVar) {
            this.f5857c.onSubscribe(bVar);
        }
    }

    public a(d.a.g<l<T>> gVar) {
        this.f5856c = gVar;
    }

    @Override // d.a.g
    public void t(i<? super T> iVar) {
        this.f5856c.a(new C0103a(iVar));
    }
}
