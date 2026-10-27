package H4;

import h2.AbstractC1347d;

/* JADX INFO: loaded from: classes.dex */
public final class v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final t f2821a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final t f2822b;
    public final e c;
    public final m d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f2823e;

    public v(s sVar, d dVar, l lVar, int i7) {
        int i10 = i7 & 1;
        c cVar = c.f2798e;
        t tVar = i10 != 0 ? cVar : sVar;
        e eVar = (i7 & 4) != 0 ? c.f2797b : dVar;
        m mVar = (i7 & 8) != 0 ? c.c : lVar;
        A7.m.f(tVar, "darkPixel");
        A7.m.f(eVar, "ball");
        A7.m.f(mVar, "frame");
        this.f2821a = tVar;
        this.f2822b = cVar;
        this.c = eVar;
        this.d = mVar;
        this.f2823e = true;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof v)) {
            return false;
        }
        v vVar = (v) obj;
        return A7.m.a(this.f2821a, vVar.f2821a) && A7.m.a(this.f2822b, vVar.f2822b) && A7.m.a(this.c, vVar.c) && A7.m.a(this.d, vVar.d) && this.f2823e == vVar.f2823e;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v2, types: [int] */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v4 */
    public final int hashCode() {
        int iHashCode = (this.d.hashCode() + ((this.c.hashCode() + ((this.f2822b.hashCode() + (this.f2821a.hashCode() * 31)) * 31)) * 31)) * 31;
        boolean z6 = this.f2823e;
        ?? r22 = z6;
        if (z6) {
            r22 = 1;
        }
        return iHashCode + r22;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("QrVectorShapes(darkPixel=");
        sb.append(this.f2821a);
        sb.append(", lightPixel=");
        sb.append(this.f2822b);
        sb.append(", ball=");
        sb.append(this.c);
        sb.append(", frame=");
        sb.append(this.d);
        sb.append(", centralSymmetry=");
        return AbstractC1347d.i(sb, this.f2823e, ')');
    }
}
