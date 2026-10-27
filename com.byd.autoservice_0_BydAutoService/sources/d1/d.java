package d1;

import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager2.widget.ViewPager2;
import java.util.Locale;

/* JADX INFO: compiled from: PageTransformerAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public final class d extends ViewPager2.i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final LinearLayoutManager f2684a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ViewPager2.k f2685b;

    public d(LinearLayoutManager linearLayoutManager) {
        this.f2684a = linearLayoutManager;
    }

    @Override // androidx.viewpager2.widget.ViewPager2.i
    public void a(int i3) {
    }

    @Override // androidx.viewpager2.widget.ViewPager2.i
    public void b(int i3, float f3, int i4) {
        if (this.f2685b == null) {
            return;
        }
        float f4 = -f3;
        for (int i5 = 0; i5 < this.f2684a.J(); i5++) {
            View viewI = this.f2684a.I(i5);
            if (viewI == null) {
                throw new IllegalStateException(String.format(Locale.US, "LayoutManager returned a null child at pos %d/%d while transforming pages", Integer.valueOf(i5), Integer.valueOf(this.f2684a.J())));
            }
            this.f2685b.a(viewI, (this.f2684a.h0(viewI) - i3) + f4);
        }
    }

    @Override // androidx.viewpager2.widget.ViewPager2.i
    public void c(int i3) {
    }

    public ViewPager2.k d() {
        return this.f2685b;
    }

    public void e(ViewPager2.k kVar) {
        this.f2685b = kVar;
    }
}
