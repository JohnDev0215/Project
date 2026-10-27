package j5;

import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class n extends o implements Iterable {
    public final ArrayList l = new ArrayList();

    public final boolean equals(Object obj) {
        return obj == this || ((obj instanceof n) && ((n) obj).l.equals(this.l));
    }

    public final int hashCode() {
        return this.l.hashCode();
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return this.l.iterator();
    }
}
