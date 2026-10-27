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
    private final List<ViewPager2.i> f1465a;

    b(int i) {
        this.f1465a = new ArrayList(i);
    }

    private void e(ConcurrentModificationException concurrentModificationException) {
        throw new IllegalStateException("Adding and removing callbacks during dispatch to callbacks is not supported", concurrentModificationException);
    }

    @Override // androidx.viewpager2.widget.ViewPager2.i
    public void a(int i) {
        try {
            Iterator<ViewPager2.i> it = this.f1465a.iterator();
            while (it.hasNext()) {
                it.next().a(i);
            }
        } catch (ConcurrentModificationException e) {
            e(e);
        }
    }

    @Override // androidx.viewpager2.widget.ViewPager2.i
    public void b(int i, float f, int i2) {
        try {
            Iterator<ViewPager2.i> it = this.f1465a.iterator();
            while (it.hasNext()) {
                it.next().b(i, f, i2);
            }
        } catch (ConcurrentModificationException e) {
            e(e);
        }
    }

    @Override // androidx.viewpager2.widget.ViewPager2.i
    public void c(int i) {
        try {
            Iterator<ViewPager2.i> it = this.f1465a.iterator();
            while (it.hasNext()) {
                it.next().c(i);
            }
        } catch (ConcurrentModificationException e) {
            e(e);
        }
    }

    void d(ViewPager2.i iVar) {
        this.f1465a.add(iVar);
    }
}
