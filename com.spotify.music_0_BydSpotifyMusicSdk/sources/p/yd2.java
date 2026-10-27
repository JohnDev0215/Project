package p;

import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class yd2 implements Iterator {
    public int c = -1;
    public boolean e;
    public Iterator k;
    public final /* synthetic */ ud2 l;

    public yd2(ud2 ud2Var) {
        this.l = ud2Var;
    }

    public final Iterator a() {
        if (this.k == null) {
            this.k = this.l.k.entrySet().iterator();
        }
        return this.k;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        int i = this.c + 1;
        ud2 ud2Var = this.l;
        if (i >= ud2Var.e.size()) {
            return !ud2Var.k.isEmpty() && a().hasNext();
        }
        return true;
    }

    @Override // java.util.Iterator
    public final Object next() {
        this.e = true;
        int i = this.c + 1;
        this.c = i;
        ud2 ud2Var = this.l;
        return i < ud2Var.e.size() ? (Map.Entry) ud2Var.e.get(this.c) : (Map.Entry) a().next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        if (!this.e) {
            throw new IllegalStateException("remove() was called before next()");
        }
        this.e = false;
        int i = ud2.o;
        ud2 ud2Var = this.l;
        ud2Var.b();
        if (this.c >= ud2Var.e.size()) {
            a().remove();
            return;
        }
        int i2 = this.c;
        this.c = i2 - 1;
        ud2Var.n(i2);
    }
}
