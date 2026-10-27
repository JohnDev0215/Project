package androidx.lifecycle;

import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: MediatorLiveData.java */
/* JADX INFO: loaded from: classes.dex */
public class k<T> extends m<T> {
    private b.b.a.b.b<LiveData<?>, a<?>> k = new b.b.a.b.b<>();

    /* JADX INFO: compiled from: MediatorLiveData.java */
    private static class a<V> implements n<V> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final LiveData<V> f1509a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final n<? super V> f1510b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1511c = -1;

        a(LiveData<V> liveData, n<? super V> nVar) {
            this.f1509a = liveData;
            this.f1510b = nVar;
        }

        @Override // androidx.lifecycle.n
        public void a(V v) {
            if (this.f1511c != this.f1509a.d()) {
                this.f1511c = this.f1509a.d();
                this.f1510b.a(v);
            }
        }

        void b() {
            this.f1509a.g(this);
        }

        void c() {
            this.f1509a.j(this);
        }
    }

    @Override // androidx.lifecycle.LiveData
    protected void h() {
        Iterator<Map.Entry<LiveData<?>, a<?>>> it = this.k.iterator();
        while (it.hasNext()) {
            it.next().getValue().b();
        }
    }

    @Override // androidx.lifecycle.LiveData
    protected void i() {
        Iterator<Map.Entry<LiveData<?>, a<?>>> it = this.k.iterator();
        while (it.hasNext()) {
            it.next().getValue().c();
        }
    }

    public <S> void l(LiveData<S> liveData, n<? super S> nVar) {
        a<?> aVar = new a<>(liveData, nVar);
        a<?> aVarG = this.k.g(liveData, aVar);
        if (aVarG != null && aVarG.f1510b != nVar) {
            throw new IllegalArgumentException("This source was already added with the different observer");
        }
        if (aVarG == null && e()) {
            aVar.b();
        }
    }
}
