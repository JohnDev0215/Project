package androidx.viewpager2.widget;

import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager2.widget.ViewPager2;
import java.util.Locale;

/* JADX INFO: compiled from: PageTransformerAdapter.java */
/* JADX INFO: loaded from: classes.dex */
final class d extends ViewPager2.i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final LinearLayoutManager f2129a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private ViewPager2.k f2130b;

    d(LinearLayoutManager linearLayoutManager) {
        this.f2129a = linearLayoutManager;
    }

    @Override // androidx.viewpager2.widget.ViewPager2.i
    public void a(int i2) {
    }

    @Override // androidx.viewpager2.widget.ViewPager2.i
    public void b(int i2, float f2, int i3) {
        if (this.f2130b == null) {
            return;
        }
        float f3 = -f2;
        for (int i4 = 0; i4 < this.f2129a.J(); i4++) {
            View viewI = this.f2129a.I(i4);
            if (viewI == null) {
                throw new IllegalStateException(String.format(Locale.US, "LayoutManager returned a null child at pos %d/%d while transforming pages", Integer.valueOf(i4), Integer.valueOf(this.f2129a.J())));
            }
            this.f2130b.a(viewI, (this.f2129a.h0(viewI) - i2) + f3);
        }
    }

    @Override // androidx.viewpager2.widget.ViewPager2.i
    public void c(int i2) {
    }

    ViewPager2.k d() {
        return this.f2130b;
    }

    void e(ViewPager2.k kVar) {
        this.f2130b = kVar;
    }
}
