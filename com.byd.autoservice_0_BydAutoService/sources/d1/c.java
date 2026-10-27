package d1;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

/* JADX INFO: compiled from: FakeDrag.java */
/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ViewPager2 f2681a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final e f2682b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final RecyclerView f2683c;

    public c(ViewPager2 viewPager2, e eVar, RecyclerView recyclerView) {
        this.f2681a = viewPager2;
        this.f2682b = eVar;
        this.f2683c = recyclerView;
    }

    public boolean a() {
        return this.f2682b.i();
    }
}
