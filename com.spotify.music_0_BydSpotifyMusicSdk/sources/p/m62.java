package p;

import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class m62 implements Map.Entry {
    public final Object c;
    public final Object e;
    public m62 k;
    public m62 l;

    public m62(Object obj, Object obj2) {
        this.c = obj;
        this.e = obj2;
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof m62)) {
            return false;
        }
        m62 m62Var = (m62) obj;
        return this.c.equals(m62Var.c) && this.e.equals(m62Var.e);
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
        return this.c.hashCode() ^ this.e.hashCode();
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        throw new UnsupportedOperationException("An entry modification is not supported");
    }

    public final String toString() {
        return this.c + "=" + this.e;
    }
}
