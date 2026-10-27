package K3;

/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final L3.h f3524a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final L3.f f3525b;
    public final M3.e c;
    public final L3.d d;

    public d(L3.h hVar, L3.f fVar, M3.e eVar, L3.d dVar) {
        this.f3524a = hVar;
        this.f3525b = fVar;
        this.c = eVar;
        this.d = dVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof d) {
            d dVar = (d) obj;
            dVar.getClass();
            if (A7.m.a(null, null) && A7.m.a(this.f3524a, dVar.f3524a) && this.f3525b == dVar.f3525b && A7.m.a(null, null) && A7.m.a(null, null) && A7.m.a(null, null) && A7.m.a(null, null) && A7.m.a(this.c, dVar.c) && this.d == dVar.d && A7.m.a(null, null) && A7.m.a(null, null)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        L3.h hVar = this.f3524a;
        int iHashCode = (hVar != null ? hVar.hashCode() : 0) * 31;
        L3.f fVar = this.f3525b;
        int iHashCode2 = (iHashCode + (fVar != null ? fVar.hashCode() : 0)) * 28629151;
        M3.e eVar = this.c;
        int iHashCode3 = (iHashCode2 + (eVar != null ? eVar.hashCode() : 0)) * 31;
        L3.d dVar = this.d;
        return (iHashCode3 + (dVar != null ? dVar.hashCode() : 0)) * 887503681;
    }
}
