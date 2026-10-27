package L8;

import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public abstract class d implements Iterable, B7.a {
    public a l;

    public final boolean isEmpty() {
        return this.l.d() == 0;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return this.l.iterator();
    }
}
