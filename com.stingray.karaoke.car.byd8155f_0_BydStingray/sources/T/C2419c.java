package t;

import java.util.Map;

/* JADX INFO: renamed from: t.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2419c implements Map.Entry {
    public final Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Object f22419m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public C2419c f22420n;
    public C2419c o;

    public C2419c(Object obj, Object obj2) {
        this.l = obj;
        this.f22419m = obj2;
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C2419c)) {
            return false;
        }
        C2419c c2419c = (C2419c) obj;
        return this.l.equals(c2419c.l) && this.f22419m.equals(c2419c.f22419m);
    }

    @Override // java.util.Map.Entry
    public final Object getKey() {
        return this.l;
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        return this.f22419m;
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        return this.f22419m.hashCode() ^ this.l.hashCode();
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        throw new UnsupportedOperationException("An entry modification is not supported");
    }

    public final String toString() {
        return this.l + "=" + this.f22419m;
    }
}
