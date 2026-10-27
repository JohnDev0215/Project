package androidx.viewpager2.widget;

import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager2.widget.ViewPager2;
import java.util.Locale;

/* JADX INFO: compiled from: PageTransformerAdapter.java */
/* JADX INFO: loaded from: classes.dex */
final class d extends ViewPager2.i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final LinearLayoutManager f3344a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private ViewPager2.k f3345b;

    d(LinearLayoutManager linearLayoutManager) {
        this.f3344a = linearLayoutManager;
    }

    @Override // androidx.viewpager2.widget.ViewPager2.i
    public void a(int i7) {
    }

    @Override // androidx.viewpager2.widget.ViewPager2.i
    public void b(int i7, float f7, int i8) {
        if (this.f3345b == null) {
            return;
        }
        float f8 = -f7;
        for (int i9 = 0; i9 < this.f3344a.J(); i9++) {
            View viewI = this.f3344a.I(i9);
            if (viewI == null) {
                throw new IllegalStateException(String.format(Locale.US, "LayoutManager returned a null child at pos %d/%d while transforming pages", Integer.valueOf(i9), Integer.valueOf(this.f3344a.J())));
            }
            this.f3345b.a(viewI, (this.f3344a.h0(viewI) - i7) + f8);
        }
    }

    @Override // androidx.viewpager2.widget.ViewPager2.i
    public void c(int i7) {
    }

    ViewPager2.k d() {
        return this.f3345b;
    }

    void e(ViewPager2.k kVar) {
        this.f3345b = kVar;
    }
}
