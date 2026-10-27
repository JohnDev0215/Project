package kotlin;

import java.io.Serializable;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class Pair<A, B> implements Serializable {
    private final A first;
    private final B second;

    /* JADX WARN: Multi-variable type inference failed */
    public Pair(Object obj, Object obj2) {
        this.first = obj;
        this.second = obj2;
    }

    public final Object a() {
        return this.first;
    }

    public final Object b() {
        return this.second;
    }

    public final Object c() {
        return this.first;
    }

    public final Object d() {
        return this.second;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Pair)) {
            return false;
        }
        Pair pair = (Pair) obj;
        return v03.b(this.first, pair.first) && v03.b(this.second, pair.second);
    }

    public final int hashCode() {
        A a = this.first;
        int iHashCode = (a == null ? 0 : a.hashCode()) * 31;
        B b = this.second;
        return iHashCode + (b != null ? b.hashCode() : 0);
    }

    public final String toString() {
        return "(" + this.first + ", " + this.second + ')';
    }
}
