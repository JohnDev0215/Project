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
    private final List<ViewPager2.i> f3340a;

    b(int i7) {
        this.f3340a = new ArrayList(i7);
    }

    private void f(ConcurrentModificationException concurrentModificationException) {
        throw new IllegalStateException("Adding and removing callbacks during dispatch to callbacks is not supported", concurrentModificationException);
    }

    @Override // androidx.viewpager2.widget.ViewPager2.i
    public void a(int i7) {
        try {
            Iterator<ViewPager2.i> it = this.f3340a.iterator();
            while (it.hasNext()) {
                it.next().a(i7);
            }
        } catch (ConcurrentModificationException e7) {
            f(e7);
        }
    }

    @Override // androidx.viewpager2.widget.ViewPager2.i
    public void b(int i7, float f7, int i8) {
        try {
            Iterator<ViewPager2.i> it = this.f3340a.iterator();
            while (it.hasNext()) {
                it.next().b(i7, f7, i8);
            }
        } catch (ConcurrentModificationException e7) {
            f(e7);
        }
    }

    @Override // androidx.viewpager2.widget.ViewPager2.i
    public void c(int i7) {
        try {
            Iterator<ViewPager2.i> it = this.f3340a.iterator();
            while (it.hasNext()) {
                it.next().c(i7);
            }
        } catch (ConcurrentModificationException e7) {
            f(e7);
        }
    }

    void d(ViewPager2.i iVar) {
        this.f3340a.add(iVar);
    }

    void e(ViewPager2.i iVar) {
        this.f3340a.remove(iVar);
    }
}
