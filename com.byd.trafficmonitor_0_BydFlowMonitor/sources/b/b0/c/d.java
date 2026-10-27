package b.b0.c;

import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager2.widget.ViewPager2;
import java.util.Locale;

/* JADX INFO: compiled from: PageTransformerAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public final class d extends ViewPager2.i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final LinearLayoutManager f1869a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ViewPager2.k f1870b;

    public d(LinearLayoutManager linearLayoutManager) {
        this.f1869a = linearLayoutManager;
    }

    @Override // androidx.viewpager2.widget.ViewPager2.i
    public void a(int i2) {
    }

    @Override // androidx.viewpager2.widget.ViewPager2.i
    public void b(int i2, float f2, int i3) {
        if (this.f1870b == null) {
            return;
        }
        float f3 = -f2;
        for (int i4 = 0; i4 < this.f1869a.J(); i4++) {
            View viewI = this.f1869a.I(i4);
            if (viewI == null) {
                throw new IllegalStateException(String.format(Locale.US, "LayoutManager returned a null child at pos %d/%d while transforming pages", Integer.valueOf(i4), Integer.valueOf(this.f1869a.J())));
            }
            this.f1870b.a(viewI, (this.f1869a.h0(viewI) - i2) + f3);
        }
    }

    @Override // androidx.viewpager2.widget.ViewPager2.i
    public void c(int i2) {
    }

    public ViewPager2.k d() {
        return this.f1870b;
    }

    public void e(ViewPager2.k kVar) {
        this.f1870b = kVar;
    }
}
