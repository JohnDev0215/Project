package androidx.viewpager2.widget;

import androidx.viewpager2.widget.ViewPager2;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

/* JADX INFO: compiled from: CompositeOnPageChangeCallback.java */
/* JADX INFO: loaded from: classes.dex */
public final class a extends ViewPager2.g {
    public final ArrayList a = new ArrayList(3);

    @Override // androidx.viewpager2.widget.ViewPager2.g
    public final void a(int i) {
        try {
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                ((ViewPager2.g) it.next()).a(i);
            }
        } catch (ConcurrentModificationException e) {
            throw new IllegalStateException("Adding and removing callbacks during dispatch to callbacks is not supported", e);
        }
    }

    @Override // androidx.viewpager2.widget.ViewPager2.g
    public final void b(int i, int i2, float f) {
        try {
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                ((ViewPager2.g) it.next()).b(i, i2, f);
            }
        } catch (ConcurrentModificationException e) {
            throw new IllegalStateException("Adding and removing callbacks during dispatch to callbacks is not supported", e);
        }
    }

    @Override // androidx.viewpager2.widget.ViewPager2.g
    public final void c(int i) {
        try {
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                ((ViewPager2.g) it.next()).c(i);
            }
        } catch (ConcurrentModificationException e) {
            throw new IllegalStateException("Adding and removing callbacks during dispatch to callbacks is not supported", e);
        }
    }
}
