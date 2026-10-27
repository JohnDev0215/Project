package defpackage;

import java.util.NoSuchElementException;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Ws implements Xs {
    public int s;
    public int t;
    public boolean u;
    public final /* synthetic */ Ys v;

    public Ws(Ys ys, Vs vs) {
        this.v = ys;
        ys.t++;
        this.s = ys.s.size();
    }

    public final void a() {
        if (this.u) {
            return;
        }
        this.u = true;
        Ys ys = this.v;
        int i = ys.t - 1;
        ys.t = i;
        if (i > 0 || !ys.v) {
            return;
        }
        ys.v = false;
        int size = ys.s.size();
        while (true) {
            size--;
            if (size < 0) {
                return;
            }
            if (ys.s.get(size) == null) {
                ys.s.remove(size);
            }
        }
    }

    public void b() {
        a();
        Ys ys = this.v;
        ys.t++;
        this.s = ys.s.size();
        this.u = false;
        this.t = 0;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        int i = this.t;
        while (i < this.s && Ys.e(this.v, i) == null) {
            i++;
        }
        if (i < this.s) {
            return true;
        }
        a();
        return false;
    }

    @Override // java.util.Iterator
    public Object next() {
        while (true) {
            int i = this.t;
            if (i >= this.s || Ys.e(this.v, i) != null) {
                break;
            }
            this.t++;
        }
        int i2 = this.t;
        if (i2 >= this.s) {
            a();
            throw new NoSuchElementException();
        }
        Ys ys = this.v;
        this.t = i2 + 1;
        return Ys.e(ys, i2);
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
