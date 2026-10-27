package T0;

/* JADX INFO: renamed from: T0.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0781g implements i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f8450a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f8451b;

    public C0781g(int i7, int i10) {
        this.f8450a = i7;
        this.f8451b = i10;
        if (i7 < 0 || i10 < 0) {
            throw new IllegalArgumentException(("Expected lengthBeforeCursor and lengthAfterCursor to be non-negative, were " + i7 + " and " + i10 + " respectively.").toString());
        }
    }

    @Override // T0.i
    public final void a(j jVar) {
        int i7 = jVar.f8455n;
        int i10 = this.f8451b;
        int iO = i7 + i10;
        int i11 = (i7 ^ iO) & (i10 ^ iO);
        A2.g gVar = (A2.g) jVar.f8457q;
        if (i11 < 0) {
            iO = gVar.o();
        }
        jVar.a(jVar.f8455n, Math.min(iO, gVar.o()));
        int i12 = jVar.f8454m;
        int i13 = this.f8450a;
        int i14 = i12 - i13;
        if (((i13 ^ i12) & (i12 ^ i14)) < 0) {
            i14 = 0;
        }
        jVar.a(Math.max(0, i14), jVar.f8454m);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0781g)) {
            return false;
        }
        C0781g c0781g = (C0781g) obj;
        return this.f8450a == c0781g.f8450a && this.f8451b == c0781g.f8451b;
    }

    public final int hashCode() {
        return (this.f8450a * 31) + this.f8451b;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("DeleteSurroundingTextCommand(lengthBeforeCursor=");
        sb.append(this.f8450a);
        sb.append(", lengthAfterCursor=");
        return android.hardware.bydauto.ac.tsimpl.a.j(sb, this.f8451b, ')');
    }
}
