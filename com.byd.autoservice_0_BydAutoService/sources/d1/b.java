package d1;

import androidx.viewpager2.widget.ViewPager2;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: CompositeOnPageChangeCallback.java */
/* JADX INFO: loaded from: classes.dex */
public final class b extends ViewPager2.i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List<ViewPager2.i> f2680a;

    public b(int i3) {
        this.f2680a = new ArrayList(i3);
    }

    @Override // androidx.viewpager2.widget.ViewPager2.i
    public void a(int i3) {
        try {
            Iterator<ViewPager2.i> it = this.f2680a.iterator();
            while (it.hasNext()) {
                it.next().a(i3);
            }
        } catch (ConcurrentModificationException e3) {
            e(e3);
        }
    }

    @Override // androidx.viewpager2.widget.ViewPager2.i
    public void b(int i3, float f3, int i4) {
        try {
            Iterator<ViewPager2.i> it = this.f2680a.iterator();
            while (it.hasNext()) {
                it.next().b(i3, f3, i4);
            }
        } catch (ConcurrentModificationException e3) {
            e(e3);
        }
    }

    @Override // androidx.viewpager2.widget.ViewPager2.i
    public void c(int i3) {
        try {
            Iterator<ViewPager2.i> it = this.f2680a.iterator();
            while (it.hasNext()) {
                it.next().c(i3);
            }
        } catch (ConcurrentModificationException e3) {
            e(e3);
        }
    }

    public void d(ViewPager2.i iVar) {
        this.f2680a.add(iVar);
    }

    public final void e(ConcurrentModificationException concurrentModificationException) {
        throw new IllegalStateException("Adding and removing callbacks during dispatch to callbacks is not supported", concurrentModificationException);
    }
}
