package p;

import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class b71 extends i71 implements Iterable {
    public final ArrayList c = new ArrayList();

    public final boolean equals(Object obj) {
        return obj == this || ((obj instanceof b71) && ((b71) obj).c.equals(this.c));
    }

    public final int hashCode() {
        return this.c.hashCode();
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return this.c.iterator();
    }
}
