package K3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import n7.v;

/* JADX INFO: loaded from: classes.dex */
public final class o implements Iterable, B7.a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final o f3577m = new o(v.l);
    public final Map l;

    public o(Map map) {
        this.l = map;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof o) {
            if (A7.m.a(this.l, ((o) obj).l)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.l.hashCode();
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        Map map = this.l;
        ArrayList arrayList = new ArrayList(map.size());
        for (Map.Entry entry : map.entrySet()) {
            String str = (String) entry.getKey();
            if (entry.getValue() != null) {
                throw new ClassCastException();
            }
            arrayList.add(new m7.i(str, null));
        }
        return arrayList.iterator();
    }

    public final String toString() {
        return "Parameters(entries=" + this.l + ')';
    }
}
