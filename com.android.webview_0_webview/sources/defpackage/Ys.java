package defpackage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Ys implements Iterable {
    public final List s = new ArrayList();
    public int t;
    public int u;
    public boolean v;

    public static Object e(Ys ys, int i) {
        return ys.s.get(i);
    }

    public void clear() {
        this.u = 0;
        if (this.t == 0) {
            this.s.clear();
            return;
        }
        int size = this.s.size();
        this.v |= size != 0;
        for (int i = 0; i < size; i++) {
            this.s.set(i, null);
        }
    }

    public boolean f(Object obj) {
        if (obj == null || this.s.contains(obj)) {
            return false;
        }
        this.s.add(obj);
        this.u++;
        return true;
    }

    public boolean g(Object obj) {
        int iIndexOf;
        if (obj == null || (iIndexOf = this.s.indexOf(obj)) == -1) {
            return false;
        }
        if (this.t == 0) {
            this.s.remove(iIndexOf);
        } else {
            this.v = true;
            this.s.set(iIndexOf, null);
        }
        this.u--;
        return true;
    }

    public Xs h() {
        return new Ws(this, null);
    }

    @Override // java.lang.Iterable
    public Iterator iterator() {
        return new Ws(this, null);
    }
}
