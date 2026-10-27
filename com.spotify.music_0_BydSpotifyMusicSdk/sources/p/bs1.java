package p;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import kotlin.Pair;

/* JADX INFO: loaded from: classes.dex */
public final class bs1 implements Iterable, g81 {
    public static final bs1 e = new bs1(kotlin.collections.c.h0());
    public final Map c;

    public bs1(Map map) {
        this.c = map;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof bs1) {
            if (v03.b(this.c, ((bs1) obj).c)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.c.hashCode();
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        Map map = this.c;
        ArrayList arrayList = new ArrayList(map.size());
        for (Map.Entry entry : map.entrySet()) {
            String str = (String) entry.getKey();
            u4.z(entry.getValue());
            arrayList.add(new Pair(str, null));
        }
        return arrayList.iterator();
    }

    public final String toString() {
        return "Parameters(entries=" + this.c + ')';
    }
}
