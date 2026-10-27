package m7;

import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public final class o implements Serializable {
    public final Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Object f19264m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Object f19265n;

    public o(Object obj, Object obj2, Object obj3) {
        this.l = obj;
        this.f19264m = obj2;
        this.f19265n = obj3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof o)) {
            return false;
        }
        o oVar = (o) obj;
        return A7.m.a(this.l, oVar.l) && A7.m.a(this.f19264m, oVar.f19264m) && A7.m.a(this.f19265n, oVar.f19265n);
    }

    public final int hashCode() {
        Object obj = this.l;
        int iHashCode = (obj == null ? 0 : obj.hashCode()) * 31;
        Object obj2 = this.f19264m;
        int iHashCode2 = (iHashCode + (obj2 == null ? 0 : obj2.hashCode())) * 31;
        Object obj3 = this.f19265n;
        return iHashCode2 + (obj3 != null ? obj3.hashCode() : 0);
    }

    public final String toString() {
        return "(" + this.l + ", " + this.f19264m + ", " + this.f19265n + ')';
    }
}
