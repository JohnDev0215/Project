package androidx.viewpager2.widget;

import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: FakeDrag.java */
/* JADX INFO: loaded from: classes.dex */
final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ViewPager2 f2126a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final e f2127b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final RecyclerView f2128c;

    c(ViewPager2 viewPager2, e eVar, RecyclerView recyclerView) {
        this.f2126a = viewPager2;
        this.f2127b = eVar;
        this.f2128c = recyclerView;
    }

    boolean a() {
        return this.f2127b.i();
    }
}
