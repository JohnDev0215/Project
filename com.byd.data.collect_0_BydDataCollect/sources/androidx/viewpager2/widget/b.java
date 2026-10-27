package androidx.viewpager2.widget;

import androidx.viewpager2.widget.ViewPager2;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: CompositeOnPageChangeCallback.java */
/* JADX INFO: loaded from: classes.dex */
final class b extends ViewPager2.i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List<ViewPager2.i> f2125a;

    b(int i2) {
        this.f2125a = new ArrayList(i2);
    }

    private void e(ConcurrentModificationException concurrentModificationException) {
        throw new IllegalStateException("Adding and removing callbacks during dispatch to callbacks is not supported", concurrentModificationException);
    }

    @Override // androidx.viewpager2.widget.ViewPager2.i
    public void a(int i2) {
        try {
            Iterator<ViewPager2.i> it = this.f2125a.iterator();
            while (it.hasNext()) {
                it.next().a(i2);
            }
        } catch (ConcurrentModificationException e2) {
            e(e2);
        }
    }

    @Override // androidx.viewpager2.widget.ViewPager2.i
    public void b(int i2, float f2, int i3) {
        try {
            Iterator<ViewPager2.i> it = this.f2125a.iterator();
            while (it.hasNext()) {
                it.next().b(i2, f2, i3);
            }
        } catch (ConcurrentModificationException e2) {
            e(e2);
        }
    }

    @Override // androidx.viewpager2.widget.ViewPager2.i
    public void c(int i2) {
        try {
            Iterator<ViewPager2.i> it = this.f2125a.iterator();
            while (it.hasNext()) {
                it.next().c(i2);
            }
        } catch (ConcurrentModificationException e2) {
            e(e2);
        }
    }

    void d(ViewPager2.i iVar) {
        this.f2125a.add(iVar);
    }
}
