package defpackage;

import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager2.widget.ViewPager2;
import java.util.Locale;

/* JADX INFO: compiled from: PageTransformerAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public final class pf extends ViewPager2.i {
    public final LinearLayoutManager a;
    public ViewPager2.k b;

    public pf(LinearLayoutManager linearLayoutManager) {
        this.a = linearLayoutManager;
    }

    @Override // androidx.viewpager2.widget.ViewPager2.i
    public void a(int i) {
    }

    @Override // androidx.viewpager2.widget.ViewPager2.i
    public void b(int i, float f, int i2) {
        if (this.b == null) {
            return;
        }
        float f2 = -f;
        for (int i3 = 0; i3 < this.a.J(); i3++) {
            View viewI = this.a.I(i3);
            if (viewI == null) {
                throw new IllegalStateException(String.format(Locale.US, "LayoutManager returned a null child at pos %d/%d while transforming pages", Integer.valueOf(i3), Integer.valueOf(this.a.J())));
            }
            this.b.a(viewI, (this.a.h0(viewI) - i) + f2);
        }
    }

    @Override // androidx.viewpager2.widget.ViewPager2.i
    public void c(int i) {
    }

    public ViewPager2.k d() {
        return this.b;
    }

    public void e(ViewPager2.k kVar) {
        this.b = kVar;
    }
}
