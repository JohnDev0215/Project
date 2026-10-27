package b.u.b;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

/* JADX INFO: compiled from: FakeDrag.java */
/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ViewPager2 f1576a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final e f1577b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final RecyclerView f1578c;

    public c(ViewPager2 viewPager2, e eVar, RecyclerView recyclerView) {
        this.f1576a = viewPager2;
        this.f1577b = eVar;
        this.f1578c = recyclerView;
    }

    public boolean a() {
        return this.f1577b.i();
    }
}
