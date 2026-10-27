package androidx.recyclerview.widget;

import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: AdapterListUpdateCallback.java */
/* JADX INFO: loaded from: classes.dex */
public final class b implements k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final RecyclerView.g f3018a;

    public b(RecyclerView.g gVar) {
        this.f3018a = gVar;
    }

    @Override // androidx.recyclerview.widget.k
    public void a(int i7, int i8) {
        this.f3018a.o(i7, i8);
    }

    @Override // androidx.recyclerview.widget.k
    public void b(int i7, int i8) {
        this.f3018a.n(i7, i8);
    }

    @Override // androidx.recyclerview.widget.k
    public void c(int i7, int i8) {
        this.f3018a.l(i7, i8);
    }

    @Override // androidx.recyclerview.widget.k
    public void d(int i7, int i8, Object obj) {
        this.f3018a.m(i7, i8, obj);
    }
}
