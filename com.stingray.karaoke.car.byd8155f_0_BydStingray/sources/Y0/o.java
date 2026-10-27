package Y0;

import a.AbstractC0904a;

/* JADX INFO: loaded from: classes.dex */
public final class o {
    public static final o c = new o(AbstractC0904a.I(0), AbstractC0904a.I(0));

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f10625a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f10626b;

    public o(long j3, long j10) {
        this.f10625a = j3;
        this.f10626b = j10;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof o)) {
            return false;
        }
        o oVar = (o) obj;
        return a1.m.a(this.f10625a, oVar.f10625a) && a1.m.a(this.f10626b, oVar.f10626b);
    }

    public final int hashCode() {
        a1.n[] nVarArr = a1.m.f11041b;
        return Long.hashCode(this.f10626b) + (Long.hashCode(this.f10625a) * 31);
    }

    public final String toString() {
        return "TextIndent(firstLine=" + ((Object) a1.m.d(this.f10625a)) + ", restLine=" + ((Object) a1.m.d(this.f10626b)) + ')';
    }
}
