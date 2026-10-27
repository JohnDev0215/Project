package d5;

import java.io.Serializable;

/* JADX INFO: renamed from: d5.v, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1099v extends b0 implements Serializable {
    public final C1.b l;

    public C1099v(C1.b bVar) {
        this.l = bVar;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return this.l.compare(obj, obj2);
    }

    @Override // java.util.Comparator
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof C1099v) {
            return this.l.equals(((C1099v) obj).l);
        }
        return false;
    }

    public final int hashCode() {
        return this.l.hashCode();
    }

    public final String toString() {
        return this.l.toString();
    }
}
