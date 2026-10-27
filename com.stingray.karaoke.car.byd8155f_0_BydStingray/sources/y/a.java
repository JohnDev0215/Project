package Y;

import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class a implements Map.Entry, B7.a {
    public final Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Object f10584m;

    public a(Object obj, Object obj2) {
        this.l = obj;
        this.f10584m = obj2;
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        Map.Entry entry = obj instanceof Map.Entry ? (Map.Entry) obj : null;
        return entry != null && A7.m.a(entry.getKey(), this.l) && A7.m.a(entry.getValue(), getValue());
    }

    @Override // java.util.Map.Entry
    public final Object getKey() {
        return this.l;
    }

    @Override // java.util.Map.Entry
    public Object getValue() {
        return this.f10584m;
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        Object obj = this.l;
        int iHashCode = obj != null ? obj.hashCode() : 0;
        Object value = getValue();
        return iHashCode ^ (value != null ? value.hashCode() : 0);
    }

    @Override // java.util.Map.Entry
    public Object setValue(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.l);
        sb.append('=');
        sb.append(getValue());
        return sb.toString();
    }
}
