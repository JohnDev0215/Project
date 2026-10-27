package androidx.viewpager2.widget;

import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager2.widget.ViewPager2;
import java.util.Locale;

/* JADX INFO: compiled from: PageTransformerAdapter.java */
/* JADX INFO: loaded from: classes.dex */
final class d extends ViewPager2.i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final LinearLayoutManager f2045a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private ViewPager2.k f2046b;

    d(LinearLayoutManager linearLayoutManager) {
        this.f2045a = linearLayoutManager;
    }

    @Override // androidx.viewpager2.widget.ViewPager2.i
    public void a(int i) {
    }

    @Override // androidx.viewpager2.widget.ViewPager2.i
    public void b(int i, float f2, int i2) {
        if (this.f2046b == null) {
            return;
        }
        float f3 = -f2;
        for (int i3 = 0; i3 < this.f2045a.J(); i3++) {
            View viewI = this.f2045a.I(i3);
            if (viewI == null) {
                throw new IllegalStateException(String.format(Locale.US, "LayoutManager returned a null child at pos %d/%d while transforming pages", Integer.valueOf(i3), Integer.valueOf(this.f2045a.J())));
            }
            this.f2046b.a(viewI, (this.f2045a.h0(viewI) - i) + f3);
        }
    }

    @Override // androidx.viewpager2.widget.ViewPager2.i
    public void c(int i) {
    }

    ViewPager2.k d() {
        return this.f2046b;
    }

    void e(ViewPager2.k kVar) {
        this.f2046b = kVar;
    }
}
