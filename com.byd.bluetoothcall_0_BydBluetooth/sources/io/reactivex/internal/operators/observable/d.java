package io.reactivex.internal.operators.observable;

import d.a.h;
import d.a.i;
import d.a.m.f;

/* JADX INFO: compiled from: ObservableMap.java */
/* JADX INFO: loaded from: classes.dex */
public final class d<T, U> extends io.reactivex.internal.operators.observable.a<T, U> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final f<? super T, ? extends U> f4295c;

    /* JADX INFO: compiled from: ObservableMap.java */
    static final class a<T, U> extends io.reactivex.internal.observers.a<T, U> {
        final f<? super T, ? extends U> g;

        a(i<? super U> iVar, f<? super T, ? extends U> fVar) {
            super(iVar);
            this.g = fVar;
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        @Override // d.a.i
        public void onNext(T t) {
            if (this.f4270e) {
                return;
            }
            if (this.f != 0) {
                this.f4267b.onNext(null);
                return;
            }
            try {
                this.f4267b.onNext((Object) d.a.n.a.b.d(this.g.apply(t), "The mapper function returned a null value."));
            } catch (Throwable th) {
                c(th);
            }
        }

        @Override // d.a.n.b.e
        public U poll() throws Exception {
            T tPoll = this.f4269d.poll();
            if (tPoll != null) {
                return (U) d.a.n.a.b.d(this.g.apply(tPoll), "The mapper function returned a null value.");
            }
            return null;
        }

        @Override // d.a.n.b.b
        public int requestFusion(int i) {
            return d(i);
        }
    }

    public d(h<T> hVar, f<? super T, ? extends U> fVar) {
        super(hVar);
        this.f4295c = fVar;
    }

    @Override // d.a.e
    public void o(i<? super U> iVar) {
        this.f4292b.a(new a(iVar, this.f4295c));
    }
}
