package T0;

import x3.AbstractC2673a;

/* JADX INFO: loaded from: classes.dex */
public final class v implements i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f8475a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f8476b;

    public v(int i7, int i10) {
        this.f8475a = i7;
        this.f8476b = i10;
    }

    @Override // T0.i
    public final void a(j jVar) {
        if (jVar.o != -1) {
            jVar.o = -1;
            jVar.f8456p = -1;
        }
        A2.g gVar = (A2.g) jVar.f8457q;
        int iV = AbstractC2673a.v(this.f8475a, 0, gVar.o());
        int iV2 = AbstractC2673a.v(this.f8476b, 0, gVar.o());
        if (iV != iV2) {
            if (iV < iV2) {
                jVar.h(iV, iV2);
            } else {
                jVar.h(iV2, iV);
            }
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof v)) {
            return false;
        }
        v vVar = (v) obj;
        return this.f8475a == vVar.f8475a && this.f8476b == vVar.f8476b;
    }

    public final int hashCode() {
        return (this.f8475a * 31) + this.f8476b;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("SetComposingRegionCommand(start=");
        sb.append(this.f8475a);
        sb.append(", end=");
        return android.hardware.bydauto.ac.tsimpl.a.j(sb, this.f8476b, ')');
    }
}
