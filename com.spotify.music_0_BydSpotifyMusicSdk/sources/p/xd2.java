package p;

import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class xd2 implements Map.Entry, Comparable {
    public final Comparable c;
    public Object e;
    public final /* synthetic */ ud2 k;

    public xd2(ud2 ud2Var, Comparable comparable, Object obj) {
        this.k = ud2Var;
        this.c = comparable;
        this.e = obj;
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return this.c.compareTo(((xd2) obj).c);
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        Object key = entry.getKey();
        Comparable comparable = this.c;
        if (comparable != null ? comparable.equals(key) : key == null) {
            Object obj2 = this.e;
            Object value = entry.getValue();
            if (obj2 == null) {
                if (value == null) {
                    return true;
                }
            } else if (obj2.equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.Map.Entry
    public final Object getKey() {
        return this.c;
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        return this.e;
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        Comparable comparable = this.c;
        int iHashCode = comparable == null ? 0 : comparable.hashCode();
        Object obj = this.e;
        return (obj != null ? obj.hashCode() : 0) ^ iHashCode;
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        int i = ud2.o;
        this.k.b();
        Object obj2 = this.e;
        this.e = obj;
        return obj2;
    }

    public final String toString() {
        return this.c + "=" + this.e;
    }
}
