package Y0;

import h2.AbstractC1347d;
import n0.AbstractC1864N;
import n0.AbstractC1887p;
import n0.C1891t;

/* JADX INFO: loaded from: classes.dex */
public final class b implements m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AbstractC1864N f10606a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f10607b;

    public b(AbstractC1864N abstractC1864N, float f10) {
        this.f10606a = abstractC1864N;
        this.f10607b = f10;
    }

    @Override // Y0.m
    public final long a() {
        int i7 = C1891t.l;
        return C1891t.f19452k;
    }

    @Override // Y0.m
    public final AbstractC1887p b() {
        return this.f10606a;
    }

    @Override // Y0.m
    public final float c() {
        return this.f10607b;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return A7.m.a(this.f10606a, bVar.f10606a) && Float.compare(this.f10607b, bVar.f10607b) == 0;
    }

    public final int hashCode() {
        return Float.hashCode(this.f10607b) + (this.f10606a.hashCode() * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("BrushStyle(value=");
        sb.append(this.f10606a);
        sb.append(", alpha=");
        return AbstractC1347d.h(sb, this.f10607b, ')');
    }
}
