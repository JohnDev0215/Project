package T0;

import x3.AbstractC2673a;

/* JADX INFO: loaded from: classes.dex */
public final class x implements i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f8479a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f8480b;

    public x(int i7, int i10) {
        this.f8479a = i7;
        this.f8480b = i10;
    }

    @Override // T0.i
    public final void a(j jVar) {
        int iV = AbstractC2673a.v(this.f8479a, 0, ((A2.g) jVar.f8457q).o());
        int iV2 = AbstractC2673a.v(this.f8480b, 0, ((A2.g) jVar.f8457q).o());
        if (iV < iV2) {
            jVar.i(iV, iV2);
        } else {
            jVar.i(iV2, iV);
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof x)) {
            return false;
        }
        x xVar = (x) obj;
        return this.f8479a == xVar.f8479a && this.f8480b == xVar.f8480b;
    }

    public final int hashCode() {
        return (this.f8479a * 31) + this.f8480b;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("SetSelectionCommand(start=");
        sb.append(this.f8479a);
        sb.append(", end=");
        return android.hardware.bydauto.ac.tsimpl.a.j(sb, this.f8480b, ')');
    }
}
