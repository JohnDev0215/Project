package androidx.viewpager2.widget;

import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: FakeDrag.java */
/* JADX INFO: loaded from: classes.dex */
final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ViewPager2 f1950a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final e f1951b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final RecyclerView f1952c;

    c(ViewPager2 viewPager2, e eVar, RecyclerView recyclerView) {
        this.f1950a = viewPager2;
        this.f1951b = eVar;
        this.f1952c = recyclerView;
    }

    boolean a() {
        return this.f1951b.i();
    }
}
