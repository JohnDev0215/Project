package spi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: JsonArray.java */
/* JADX INFO: loaded from: classes2.dex */
public final class g5 extends i5 implements Iterable<i5> {
    public final List<i5> a;

    public g5() {
        this.a = new ArrayList();
    }

    public final boolean equals(Object obj) {
        return obj == this || ((obj instanceof g5) && ((g5) obj).a.equals(this.a));
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.ArrayList, java.util.List<spi.i5>] */
    @Override // java.lang.Iterable
    public final Iterator<i5> iterator() {
        return this.a.iterator();
    }

    public g5(int i) {
        this.a = new ArrayList(i);
    }
}
