package defpackage;

import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: compiled from: PropertyProvider.java */
/* JADX INFO: loaded from: classes.dex */
public class x62<T> {
    public final HashSet a;
    public final int b;

    public x62() {
        this(Integer.MIN_VALUE);
    }

    public final void b(T t) {
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ((b72) it.next()).a(this.b, t);
        }
    }

    public x62(int i) {
        this.a = new HashSet();
        this.b = i;
    }
}
