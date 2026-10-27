package N0;

import x.AbstractC2641j;

/* JADX INFO: loaded from: classes.dex */
public final class s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final V0.c f4407a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f4408b;
    public final int c;

    public s(V0.c cVar, int i7, int i10) {
        this.f4407a = cVar;
        this.f4408b = i7;
        this.c = i10;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof s)) {
            return false;
        }
        s sVar = (s) obj;
        return this.f4407a.equals(sVar.f4407a) && this.f4408b == sVar.f4408b && this.c == sVar.c;
    }

    public final int hashCode() {
        return Integer.hashCode(this.c) + AbstractC2641j.b(this.f4408b, this.f4407a.hashCode() * 31, 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("ParagraphIntrinsicInfo(intrinsics=");
        sb.append(this.f4407a);
        sb.append(", startIndex=");
        sb.append(this.f4408b);
        sb.append(", endIndex=");
        return android.hardware.bydauto.ac.tsimpl.a.j(sb, this.c, ')');
    }
}
