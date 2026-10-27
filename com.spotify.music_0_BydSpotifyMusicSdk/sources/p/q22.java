package p;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class q22 {
    public final Map a;
    public final Map b;
    public final Map c;

    public q22(HashMap map, HashMap map2, HashMap map3) {
        this.a = map;
        this.b = map2;
        this.c = map3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof q22)) {
            return false;
        }
        q22 q22Var = (q22) obj;
        return v03.b(this.a, q22Var.a) && v03.b(this.b, q22Var.b) && v03.b(this.c, q22Var.c);
    }

    public final int hashCode() {
        return this.c.hashCode() + ((this.b.hashCode() + (this.a.hashCode() * 31)) * 31);
    }

    public final String toString() {
        return "ReducedPoints(timestamps=" + this.a + ", durations=" + this.b + ", featureIds=" + this.c + ')';
    }
}
