package androidx.viewpager2.widget;

import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager2.widget.ViewPager2;
import java.util.Locale;

/* JADX INFO: compiled from: PageTransformerAdapter.java */
/* JADX INFO: loaded from: classes.dex */
final class d extends ViewPager2.i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final LinearLayoutManager f1571a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private ViewPager2.k f1572b;

    d(LinearLayoutManager linearLayoutManager) {
        this.f1571a = linearLayoutManager;
    }

    @Override // androidx.viewpager2.widget.ViewPager2.i
    public void a(int i) {
    }

    @Override // androidx.viewpager2.widget.ViewPager2.i
    public void b(int i, float f, int i2) {
        if (this.f1572b == null) {
            return;
        }
        float f2 = -f;
        for (int i3 = 0; i3 < this.f1571a.T(); i3++) {
            View viewS = this.f1571a.S(i3);
            if (viewS == null) {
                throw new IllegalStateException(String.format(Locale.US, "LayoutManager returned a null child at pos %d/%d while transforming pages", Integer.valueOf(i3), Integer.valueOf(this.f1571a.T())));
            }
            this.f1572b.a(viewS, (this.f1571a.n0(viewS) - i) + f2);
        }
    }

    @Override // androidx.viewpager2.widget.ViewPager2.i
    public void c(int i) {
    }

    ViewPager2.k d() {
        return this.f1572b;
    }

    void e(ViewPager2.k kVar) {
        this.f1572b = kVar;
    }
}
