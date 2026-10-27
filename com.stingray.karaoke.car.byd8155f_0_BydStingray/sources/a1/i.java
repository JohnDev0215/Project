package a1;

import x.AbstractC2641j;

/* JADX INFO: loaded from: classes.dex */
public final class i {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final i f11034e = new i(0, 0, 0, 0);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f11035a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f11036b;
    public final int c;
    public final int d;

    public i(int i7, int i10, int i11, int i12) {
        this.f11035a = i7;
        this.f11036b = i10;
        this.c = i11;
        this.d = i12;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof i)) {
            return false;
        }
        i iVar = (i) obj;
        return this.f11035a == iVar.f11035a && this.f11036b == iVar.f11036b && this.c == iVar.c && this.d == iVar.d;
    }

    public final int hashCode() {
        return Integer.hashCode(this.d) + AbstractC2641j.b(this.c, AbstractC2641j.b(this.f11036b, Integer.hashCode(this.f11035a) * 31, 31), 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("IntRect.fromLTRB(");
        sb.append(this.f11035a);
        sb.append(", ");
        sb.append(this.f11036b);
        sb.append(", ");
        sb.append(this.c);
        sb.append(", ");
        return android.hardware.bydauto.ac.tsimpl.a.j(sb, this.d, ')');
    }
}
