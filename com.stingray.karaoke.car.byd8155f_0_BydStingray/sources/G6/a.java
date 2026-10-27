package G6;

import h2.AbstractC1347d;
import n0.C1891t;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f2687a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f2688b;
    public final long c;
    public final long d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final long f2689e;

    public a(long j3, long j10, long j11, long j12, long j13) {
        this.f2687a = j3;
        this.f2688b = j10;
        this.c = j11;
        this.d = j12;
        this.f2689e = j13;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return C1891t.c(this.f2687a, aVar.f2687a) && C1891t.c(this.f2688b, aVar.f2688b) && C1891t.c(this.c, aVar.c) && C1891t.c(this.d, aVar.d) && C1891t.c(this.f2689e, aVar.f2689e);
    }

    public final int hashCode() {
        int i7 = C1891t.l;
        return Long.hashCode(this.f2689e) + AbstractC1347d.c(AbstractC1347d.c(AbstractC1347d.c(Long.hashCode(this.f2687a) * 31, 31, this.f2688b), 31, this.c), 31, this.d);
    }

    public final String toString() {
        String strI = C1891t.i(this.f2687a);
        String strI2 = C1891t.i(this.f2688b);
        String strI3 = C1891t.i(this.c);
        String strI4 = C1891t.i(this.d);
        String strI5 = C1891t.i(this.f2689e);
        StringBuilder sb = new StringBuilder("BackgroundPaletteColors(back=");
        sb.append(strI);
        sb.append(", top=");
        sb.append(strI2);
        sb.append(", bottom=");
        AbstractC1347d.r(sb, strI3, ", left=", strI4, ", right=");
        return android.hardware.bydauto.ac.tsimpl.a.l(sb, strI5, ")");
    }
}
