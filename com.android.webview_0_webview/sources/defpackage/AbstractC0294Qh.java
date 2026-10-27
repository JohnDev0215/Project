package defpackage;

import java.util.NoSuchElementException;

/* JADX INFO: renamed from: Qh, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0294Qh extends AbstractC0896hi {
    public final int s;
    public int t;

    public AbstractC0294Qh(int i, int i2) {
        AbstractC0151Ih.b(i2, i);
        this.s = i;
        this.t = i2;
    }

    @Override // java.util.Iterator, java.util.ListIterator
    public final boolean hasNext() {
        return this.t < this.s;
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return this.t > 0;
    }

    @Override // java.util.Iterator, java.util.ListIterator
    public final Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int i = this.t;
        this.t = i + 1;
        return ((C0384Vh) this).u.get(i);
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return this.t;
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        if (!hasPrevious()) {
            throw new NoSuchElementException();
        }
        int i = this.t - 1;
        this.t = i;
        return ((C0384Vh) this).u.get(i);
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return this.t - 1;
    }
}
