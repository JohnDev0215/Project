package defpackage;

import java.util.Iterator;

/* JADX INFO: renamed from: wm, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1727wm implements Iterator {
    public Iterator s;
    public final /* synthetic */ C1782xm t;

    public C1727wm(C1782xm c1782xm) {
        this.t = c1782xm;
        this.s = this.t.s.iterator();
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.s.hasNext();
    }

    @Override // java.util.Iterator
    public Object next() {
        return (String) this.s.next();
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
