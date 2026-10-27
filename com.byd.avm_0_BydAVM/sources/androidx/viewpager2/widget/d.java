package androidx.viewpager2.widget;

import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager2.widget.ViewPager2;
import java.util.Locale;

/* JADX INFO: compiled from: PageTransformerAdapter.java */
/* JADX INFO: loaded from: classes.dex */
final class d extends ViewPager2.i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final LinearLayoutManager f1389a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private ViewPager2.k f1390b;

    d(LinearLayoutManager linearLayoutManager) {
        this.f1389a = linearLayoutManager;
    }

    @Override // androidx.viewpager2.widget.ViewPager2.i
    public void a(int i) {
    }

    @Override // androidx.viewpager2.widget.ViewPager2.i
    public void b(int i, float f, int i2) {
        if (this.f1390b == null) {
            return;
        }
        float f2 = -f;
        for (int i3 = 0; i3 < this.f1389a.J(); i3++) {
            View viewI = this.f1389a.I(i3);
            if (viewI == null) {
                throw new IllegalStateException(String.format(Locale.US, "LayoutManager returned a null child at pos %d/%d while transforming pages", Integer.valueOf(i3), Integer.valueOf(this.f1389a.J())));
            }
            this.f1390b.a(viewI, (this.f1389a.h0(viewI) - i) + f2);
        }
    }

    @Override // androidx.viewpager2.widget.ViewPager2.i
    public void c(int i) {
    }

    ViewPager2.k d() {
        return this.f1390b;
    }

    void e(ViewPager2.k kVar) {
        this.f1390b = kVar;
    }
}
