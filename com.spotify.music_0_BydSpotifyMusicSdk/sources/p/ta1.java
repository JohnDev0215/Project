package p;

import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class ta1 implements Map.Entry {
    public ta1 c;
    public ta1 e;
    public ta1 k;
    public ta1 l;
    public ta1 m;
    public final Object n;
    public Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f188p;

    public ta1() {
        this.n = null;
        this.m = this;
        this.l = this;
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        Object obj2 = this.n;
        if (obj2 == null) {
            if (entry.getKey() != null) {
                return false;
            }
        } else if (!obj2.equals(entry.getKey())) {
            return false;
        }
        Object obj3 = this.o;
        if (obj3 == null) {
            if (entry.getValue() != null) {
                return false;
            }
        } else if (!obj3.equals(entry.getValue())) {
            return false;
        }
        return true;
    }

    @Override // java.util.Map.Entry
    public final Object getKey() {
        return this.n;
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        return this.o;
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        Object obj = this.n;
        int iHashCode = obj == null ? 0 : obj.hashCode();
        Object obj2 = this.o;
        return (obj2 != null ? obj2.hashCode() : 0) ^ iHashCode;
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        Object obj2 = this.o;
        this.o = obj;
        return obj2;
    }

    public final String toString() {
        return this.n + "=" + this.o;
    }

    public ta1(ta1 ta1Var, Object obj, ta1 ta1Var2, ta1 ta1Var3) {
        this.c = ta1Var;
        this.n = obj;
        this.f188p = 1;
        this.l = ta1Var2;
        this.m = ta1Var3;
        ta1Var3.l = this;
        ta1Var2.m = this;
    }
}
