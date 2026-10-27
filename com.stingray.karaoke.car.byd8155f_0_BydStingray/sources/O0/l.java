package O0;

import h2.AbstractC1347d;
import x.AbstractC2641j;

/* JADX INFO: loaded from: classes.dex */
public final class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f5019a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f5020b;
    public final boolean c;

    public l(int i7, int i10, boolean z6) {
        this.f5019a = i7;
        this.f5020b = i10;
        this.c = z6;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof l)) {
            return false;
        }
        l lVar = (l) obj;
        return this.f5019a == lVar.f5019a && this.f5020b == lVar.f5020b && this.c == lVar.c;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.c) + AbstractC2641j.b(this.f5020b, Integer.hashCode(this.f5019a) * 31, 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("BidiRun(start=");
        sb.append(this.f5019a);
        sb.append(", end=");
        sb.append(this.f5020b);
        sb.append(", isRtl=");
        return AbstractC1347d.i(sb, this.c, ')');
    }
}
