package T0;

/* JADX INFO: loaded from: classes.dex */
public final class h implements i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f8452a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f8453b;

    public h(int i7, int i10) {
        this.f8452a = i7;
        this.f8453b = i10;
        if (i7 < 0 || i10 < 0) {
            throw new IllegalArgumentException(("Expected lengthBeforeCursor and lengthAfterCursor to be non-negative, were " + i7 + " and " + i10 + " respectively.").toString());
        }
    }

    @Override // T0.i
    public final void a(j jVar) {
        int i7 = 0;
        int i10 = 0;
        int i11 = 0;
        while (true) {
            if (i10 < this.f8452a) {
                int i12 = i11 + 1;
                int i13 = jVar.f8454m;
                if (i13 <= i12) {
                    i11 = i13;
                    break;
                } else {
                    i11 = (Character.isHighSurrogate(jVar.b((i13 - i12) + (-1))) && Character.isLowSurrogate(jVar.b(jVar.f8454m - i12))) ? i11 + 2 : i12;
                    i10++;
                }
            } else {
                break;
            }
        }
        int iO = 0;
        while (true) {
            if (i7 >= this.f8453b) {
                break;
            }
            int i14 = iO + 1;
            int i15 = jVar.f8455n + i14;
            A2.g gVar = (A2.g) jVar.f8457q;
            if (i15 >= gVar.o()) {
                iO = gVar.o() - jVar.f8455n;
                break;
            } else {
                iO = (Character.isHighSurrogate(jVar.b((jVar.f8455n + i14) + (-1))) && Character.isLowSurrogate(jVar.b(jVar.f8455n + i14))) ? iO + 2 : i14;
                i7++;
            }
        }
        int i16 = jVar.f8455n;
        jVar.a(i16, iO + i16);
        int i17 = jVar.f8454m;
        jVar.a(i17 - i11, i17);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof h)) {
            return false;
        }
        h hVar = (h) obj;
        return this.f8452a == hVar.f8452a && this.f8453b == hVar.f8453b;
    }

    public final int hashCode() {
        return (this.f8452a * 31) + this.f8453b;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("DeleteSurroundingTextInCodePointsCommand(lengthBeforeCursor=");
        sb.append(this.f8452a);
        sb.append(", lengthAfterCursor=");
        return android.hardware.bydauto.ac.tsimpl.a.j(sb, this.f8453b, ')');
    }
}
