package androidx.recyclerview.widget;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
public final class b implements i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @NonNull
    private final RecyclerView.Adapter f1156a;

    public b(@NonNull RecyclerView.Adapter adapter) {
        this.f1156a = adapter;
    }

    @Override // androidx.recyclerview.widget.i
    public void a(int i, int i2) {
        this.f1156a.p(i, i2);
    }

    @Override // androidx.recyclerview.widget.i
    public void b(int i, int i2) {
        this.f1156a.o(i, i2);
    }

    @Override // androidx.recyclerview.widget.i
    public void c(int i, int i2) {
        this.f1156a.m(i, i2);
    }

    @Override // androidx.recyclerview.widget.i
    public void d(int i, int i2, Object obj) {
        this.f1156a.n(i, i2, obj);
    }
}
