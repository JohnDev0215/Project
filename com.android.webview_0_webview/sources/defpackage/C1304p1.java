package defpackage;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: renamed from: p1, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1304p1 implements Iterator {
    public final int s;
    public int t;
    public int u;
    public boolean v = false;
    public final /* synthetic */ AbstractC1578u1 w;

    public C1304p1(AbstractC1578u1 abstractC1578u1, int i) {
        this.w = abstractC1578u1;
        this.s = i;
        this.t = abstractC1578u1.d();
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.u < this.t;
    }

    @Override // java.util.Iterator
    public Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Object objB = this.w.b(this.u, this.s);
        this.u++;
        this.v = true;
        return objB;
    }

    @Override // java.util.Iterator
    public void remove() {
        if (!this.v) {
            throw new IllegalStateException();
        }
        int i = this.u - 1;
        this.u = i;
        this.t--;
        this.v = false;
        this.w.h(i);
    }
}
