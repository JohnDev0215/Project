package defpackage;

import java.util.ListIterator;

/* JADX INFO: renamed from: vm, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1672vm implements ListIterator {
    public ListIterator s;
    public final /* synthetic */ int t;
    public final /* synthetic */ C1782xm u;

    public C1672vm(C1782xm c1782xm, int i) {
        this.u = c1782xm;
        this.t = i;
        this.s = this.u.s.listIterator(this.t);
    }

    @Override // java.util.ListIterator
    public void add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public boolean hasNext() {
        return this.s.hasNext();
    }

    @Override // java.util.ListIterator
    public boolean hasPrevious() {
        return this.s.hasPrevious();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public Object next() {
        return (String) this.s.next();
    }

    @Override // java.util.ListIterator
    public int nextIndex() {
        return this.s.nextIndex();
    }

    @Override // java.util.ListIterator
    public Object previous() {
        return (String) this.s.previous();
    }

    @Override // java.util.ListIterator
    public int previousIndex() {
        return this.s.previousIndex();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.ListIterator
    public void set(Object obj) {
        throw new UnsupportedOperationException();
    }
}
