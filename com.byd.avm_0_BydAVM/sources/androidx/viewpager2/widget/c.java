package androidx.viewpager2.widget;

import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: FakeDrag.java */
/* JADX INFO: loaded from: classes.dex */
final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ViewPager2 f1386a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final e f1387b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final RecyclerView f1388c;

    c(ViewPager2 viewPager2, e eVar, RecyclerView recyclerView) {
        this.f1386a = viewPager2;
        this.f1387b = eVar;
        this.f1388c = recyclerView;
    }

    boolean a() {
        return this.f1387b.i();
    }
}
