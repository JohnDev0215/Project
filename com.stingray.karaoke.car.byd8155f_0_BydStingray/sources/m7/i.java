package m7;

import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public final class i implements Serializable {
    public final Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Object f19259m;

    public i(Object obj, Object obj2) {
        this.l = obj;
        this.f19259m = obj2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof i)) {
            return false;
        }
        i iVar = (i) obj;
        return A7.m.a(this.l, iVar.l) && A7.m.a(this.f19259m, iVar.f19259m);
    }

    public final int hashCode() {
        Object obj = this.l;
        int iHashCode = (obj == null ? 0 : obj.hashCode()) * 31;
        Object obj2 = this.f19259m;
        return iHashCode + (obj2 != null ? obj2.hashCode() : 0);
    }

    public final String toString() {
        return "(" + this.l + ", " + this.f19259m + ')';
    }
}
