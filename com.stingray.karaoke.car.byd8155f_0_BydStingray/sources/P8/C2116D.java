package p8;

import java.util.Map;

/* JADX INFO: renamed from: p8.D, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2116D implements Comparable, Map.Entry {
    public final Comparable l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Object f20948m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ z f20949n;

    public C2116D(z zVar, Comparable comparable, Object obj) {
        this.f20949n = zVar;
        this.l = comparable;
        this.f20948m = obj;
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return this.l.compareTo(((C2116D) obj).l);
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
        Comparable comparable = this.l;
        if (comparable == null ? key == null : comparable.equals(key)) {
            Object obj2 = this.f20948m;
            Object value = entry.getValue();
            if (obj2 == null ? value == null : obj2.equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.Map.Entry
    public final Object getKey() {
        return this.l;
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        return this.f20948m;
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        Comparable comparable = this.l;
        int iHashCode = comparable == null ? 0 : comparable.hashCode();
        Object obj = this.f20948m;
        return iHashCode ^ (obj != null ? obj.hashCode() : 0);
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        this.f20949n.c();
        Object obj2 = this.f20948m;
        this.f20948m = obj;
        return obj2;
    }

    public final String toString() {
        String strValueOf = String.valueOf(this.l);
        String strValueOf2 = String.valueOf(this.f20948m);
        StringBuilder sb = new StringBuilder(strValueOf2.length() + strValueOf.length() + 1);
        sb.append(strValueOf);
        sb.append("=");
        sb.append(strValueOf2);
        return sb.toString();
    }
}
