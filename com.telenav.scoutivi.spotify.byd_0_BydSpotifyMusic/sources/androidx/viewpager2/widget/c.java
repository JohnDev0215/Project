package androidx.viewpager2.widget;

import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: FakeDrag.java */
/* JADX INFO: loaded from: classes.dex */
final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ViewPager2 f3341a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final e f3342b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final RecyclerView f3343c;

    c(ViewPager2 viewPager2, e eVar, RecyclerView recyclerView) {
        this.f3341a = viewPager2;
        this.f3342b = eVar;
        this.f3343c = recyclerView;
    }

    boolean a() {
        return this.f3342b.i();
    }
}
