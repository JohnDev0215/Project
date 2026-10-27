package d.a.p.e.b;

import io.reactivex.internal.disposables.DisposableHelper;

/* JADX INFO: compiled from: ObservableTakeWhile.java */
/* JADX INFO: loaded from: classes.dex */
public final class j<T> extends d.a.p.e.b.a<T, T> {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final d.a.o.g<? super T> f3207d;

    /* JADX INFO: compiled from: ObservableTakeWhile.java */
    public static final class a<T> implements d.a.i<T>, d.a.m.b {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final d.a.i<? super T> f3208c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final d.a.o.g<? super T> f3209d;
        public d.a.m.b s;
        public boolean x;

        public a(d.a.i<? super T> iVar, d.a.o.g<? super T> gVar) {
            this.f3208c = iVar;
            this.f3209d = gVar;
        }

        @Override // d.a.m.b
        public void dispose() {
            this.s.dispose();
        }

        @Override // d.a.m.b
        public boolean isDisposed() {
            return this.s.isDisposed();
        }

        @Override // d.a.i
        public void onComplete() {
            if (this.x) {
                return;
            }
            this.x = true;
            this.f3208c.onComplete();
        }

        @Override // d.a.i
        public void onError(Throwable th) {
            if (this.x) {
                d.a.r.a.o(th);
            } else {
                this.x = true;
                this.f3208c.onError(th);
            }
        }

        @Override // d.a.i
        public void onNext(T t) {
            if (this.x) {
                return;
            }
            try {
                if (this.f3209d.test(t)) {
                    this.f3208c.onNext(t);
                    return;
                }
                this.x = true;
                this.s.dispose();
                this.f3208c.onComplete();
            } catch (Throwable th) {
                d.a.n.a.a(th);
                this.s.dispose();
                onError(th);
            }
        }

        @Override // d.a.i
        public void onSubscribe(d.a.m.b bVar) {
            if (DisposableHelper.validate(this.s, bVar)) {
                this.s = bVar;
                this.f3208c.onSubscribe(this);
            }
        }
    }

    public j(d.a.h<T> hVar, d.a.o.g<? super T> gVar) {
        super(hVar);
        this.f3207d = gVar;
    }

    @Override // d.a.g
    public void t(d.a.i<? super T> iVar) {
        this.f3190c.a(new a(iVar, this.f3207d));
    }
}
