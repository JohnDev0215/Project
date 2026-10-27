package P5;

import y0.C2738p;

/* JADX INFO: loaded from: classes.dex */
public final class z {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f5410a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final m0.c f5411b = null;

    public z(long j3) {
        this.f5410a = j3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof z)) {
            return false;
        }
        z zVar = (z) obj;
        return C2738p.a(this.f5410a, zVar.f5410a) && A7.m.a(this.f5411b, zVar.f5411b);
    }

    public final int hashCode() {
        int iHashCode = Long.hashCode(this.f5410a) * 31;
        m0.c cVar = this.f5411b;
        return iHashCode + (cVar == null ? 0 : Long.hashCode(cVar.f18880a));
    }

    public final String toString() {
        return "StartDrag(id=" + C2738p.b(this.f5410a) + ", offset=" + this.f5411b + ")";
    }
}
