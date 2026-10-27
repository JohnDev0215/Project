package d.a.p.e.b;

/* JADX INFO: compiled from: ObservableMap.java */
/* JADX INFO: loaded from: classes.dex */
public final class g<T, U> extends d.a.p.e.b.a<T, U> {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final d.a.o.e<? super T, ? extends U> f3203d;

    /* JADX INFO: compiled from: ObservableMap.java */
    public static final class a<T, U> extends d.a.p.d.a<T, U> {
        public final d.a.o.e<? super T, ? extends U> E3;

        public a(d.a.i<? super U> iVar, d.a.o.e<? super T, ? extends U> eVar) {
            super(iVar);
            this.E3 = eVar;
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        @Override // d.a.i
        public void onNext(T t) {
            if (this.x) {
                return;
            }
            if (this.y != 0) {
                this.f3181c.onNext(null);
                return;
            }
            try {
                this.f3181c.onNext((Object) d.a.p.b.b.d(this.E3.apply(t), "The mapper function returned a null value."));
            } catch (Throwable th) {
                c(th);
            }
        }

        @Override // d.a.p.c.e
        public U poll() throws Exception {
            T tPoll = this.s.poll();
            if (tPoll != null) {
                return (U) d.a.p.b.b.d(this.E3.apply(tPoll), "The mapper function returned a null value.");
            }
            return null;
        }

        @Override // d.a.p.c.b
        public int requestFusion(int i2) {
            return d(i2);
        }
    }

    public g(d.a.h<T> hVar, d.a.o.e<? super T, ? extends U> eVar) {
        super(hVar);
        this.f3203d = eVar;
    }

    @Override // d.a.g
    public void t(d.a.i<? super U> iVar) {
        this.f3190c.a(new a(iVar, this.f3203d));
    }
}
