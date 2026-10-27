package io.reactivex.internal.operators.observable;

import e.a.h;
import e.a.i;
import e.a.m.f;

/* JADX INFO: compiled from: ObservableMap.java */
/* JADX INFO: loaded from: classes.dex */
public final class d<T, U> extends io.reactivex.internal.operators.observable.a<T, U> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final f<? super T, ? extends U> f5012c;

    /* JADX INFO: compiled from: ObservableMap.java */
    static final class a<T, U> extends io.reactivex.internal.observers.a<T, U> {

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final f<? super T, ? extends U> f5013g;

        a(i<? super U> iVar, f<? super T, ? extends U> fVar) {
            super(iVar);
            this.f5013g = fVar;
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        @Override // e.a.i
        public void onNext(T t) {
            if (this.f4994e) {
                return;
            }
            if (this.f4995f != 0) {
                this.f4991b.onNext(null);
                return;
            }
            try {
                this.f4991b.onNext((Object) e.a.n.a.b.c(this.f5013g.apply(t), "The mapper function returned a null value."));
            } catch (Throwable th) {
                c(th);
            }
        }

        @Override // e.a.n.b.e
        public U poll() throws Exception {
            T tPoll = this.f4993d.poll();
            if (tPoll != null) {
                return (U) e.a.n.a.b.c(this.f5013g.apply(tPoll), "The mapper function returned a null value.");
            }
            return null;
        }

        @Override // e.a.n.b.b
        public int requestFusion(int i) {
            return d(i);
        }
    }

    public d(h<T> hVar, f<? super T, ? extends U> fVar) {
        super(hVar);
        this.f5012c = fVar;
    }

    @Override // e.a.e
    public void m(i<? super U> iVar) {
        this.f5009b.a(new a(iVar, this.f5012c));
    }
}
