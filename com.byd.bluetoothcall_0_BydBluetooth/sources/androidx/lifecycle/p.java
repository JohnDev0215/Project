package androidx.lifecycle;

/* JADX INFO: compiled from: Transformations.java */
/* JADX INFO: loaded from: classes.dex */
public class p {

    /* JADX INFO: Add missing generic type declarations: [X] */
    /* JADX INFO: compiled from: Transformations.java */
    static class a<X> implements n<X> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ k f1513a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ b.b.a.c.a f1514b;

        a(k kVar, b.b.a.c.a aVar) {
            this.f1513a = kVar;
            this.f1514b = aVar;
        }

        @Override // androidx.lifecycle.n
        public void a(X x) {
            this.f1513a.k(this.f1514b.apply(x));
        }
    }

    public static <X, Y> LiveData<Y> a(LiveData<X> liveData, b.b.a.c.a<X, Y> aVar) {
        k kVar = new k();
        kVar.l(liveData, new a(kVar, aVar));
        return kVar;
    }
}
