package y4;

import q4.g;
import q4.i;

/* JADX INFO: compiled from: SingleMap.java */
/* JADX INFO: loaded from: classes.dex */
public final class d<T, R> extends q4.e<R> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final i<? extends T> f9976a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final v4.e<? super T, ? extends R> f9977b;

    /* JADX INFO: compiled from: SingleMap.java */
    static final class a<T, R> implements g<T> {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final g<? super R> f9978e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final v4.e<? super T, ? extends R> f9979g;

        a(g<? super R> gVar, v4.e<? super T, ? extends R> eVar) {
            this.f9978e = gVar;
            this.f9979g = eVar;
        }

        @Override // q4.g
        public void a(Throwable th) {
            this.f9978e.a(th);
        }

        @Override // q4.g
        public void b(T t6) {
            try {
                this.f9978e.b(x4.b.c(this.f9979g.a(t6), "The mapper function returned a null value."));
            } catch (Throwable th) {
                u4.b.b(th);
                a(th);
            }
        }

        @Override // q4.g
        public void c(t4.b bVar) {
            this.f9978e.c(bVar);
        }
    }

    public d(i<? extends T> iVar, v4.e<? super T, ? extends R> eVar) {
        this.f9976a = iVar;
        this.f9977b = eVar;
    }

    @Override // q4.e
    protected void g(g<? super R> gVar) {
        this.f9976a.a(new a(gVar, this.f9977b));
    }
}
