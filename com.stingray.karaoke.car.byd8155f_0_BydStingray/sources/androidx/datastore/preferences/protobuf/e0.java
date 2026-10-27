package androidx.datastore.preferences.protobuf;

import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class e0 implements Map.Entry, Comparable {
    public final Comparable l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Object f11837m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C0938a0 f11838n;

    public e0(C0938a0 c0938a0, Comparable comparable, Object obj) {
        this.f11838n = c0938a0;
        this.l = comparable;
        this.f11837m = obj;
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return this.l.compareTo(((e0) obj).l);
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
            Object obj2 = this.f11837m;
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
        return this.f11837m;
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        Comparable comparable = this.l;
        int iHashCode = comparable == null ? 0 : comparable.hashCode();
        Object obj = this.f11837m;
        return iHashCode ^ (obj != null ? obj.hashCode() : 0);
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        this.f11838n.c();
        Object obj2 = this.f11837m;
        this.f11837m = obj;
        return obj2;
    }

    public final String toString() {
        return this.l + "=" + this.f11837m;
    }
}
