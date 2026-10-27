package d5;

import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public final class l0 extends b0 implements Serializable {
    public final b0 l;

    public l0(b0 b0Var) {
        this.l = b0Var;
    }

    @Override // d5.b0
    public final b0 a() {
        return this.l;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return this.l.compare(obj2, obj);
    }

    @Override // java.util.Comparator
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof l0) {
            return this.l.equals(((l0) obj).l);
        }
        return false;
    }

    public final int hashCode() {
        return -this.l.hashCode();
    }

    public final String toString() {
        return this.l + ".reverse()";
    }
}
