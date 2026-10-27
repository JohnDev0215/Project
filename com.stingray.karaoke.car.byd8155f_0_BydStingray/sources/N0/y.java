package N0;

/* JADX INFO: loaded from: classes.dex */
public final class y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final x f4421a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final w f4422b;

    public y(x xVar, w wVar) {
        this.f4421a = xVar;
        this.f4422b = wVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof y)) {
            return false;
        }
        y yVar = (y) obj;
        return A7.m.a(this.f4422b, yVar.f4422b) && A7.m.a(this.f4421a, yVar.f4421a);
    }

    public final int hashCode() {
        x xVar = this.f4421a;
        int iHashCode = (xVar != null ? xVar.hashCode() : 0) * 31;
        w wVar = this.f4422b;
        return iHashCode + (wVar != null ? wVar.hashCode() : 0);
    }

    public final String toString() {
        return "PlatformTextStyle(spanStyle=" + this.f4421a + ", paragraphSyle=" + this.f4422b + ')';
    }

    public y() {
        this(null, new w());
    }
}
