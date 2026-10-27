package D;

import h2.AbstractC1347d;

/* JADX INFO: renamed from: D.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0124f implements InterfaceC0123e, InterfaceC0125g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f1073a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f1074b;

    public C0124f(float f10) {
        this.f1073a = f10;
        this.f1074b = f10;
    }

    @Override // D.InterfaceC0123e, D.InterfaceC0125g
    public final float a() {
        return this.f1074b;
    }

    @Override // D.InterfaceC0123e
    public final void b(a1.b bVar, int i7, int[] iArr, a1.k kVar, int[] iArr2) {
        int i10;
        int iMin;
        if (iArr.length == 0) {
            return;
        }
        int iO = bVar.O(this.f1073a);
        boolean z6 = kVar == a1.k.f11038m;
        C0119a c0119a = AbstractC0127i.f1076a;
        if (z6) {
            i10 = 0;
            iMin = 0;
            for (int length = iArr.length - 1; -1 < length; length--) {
                int i11 = iArr[length];
                int iMin2 = Math.min(i10, i7 - i11);
                iArr2[length] = iMin2;
                iMin = Math.min(iO, (i7 - iMin2) - i11);
                i10 = iArr2[length] + i11 + iMin;
            }
        } else {
            int length2 = iArr.length;
            int i12 = 0;
            i10 = 0;
            iMin = 0;
            int i13 = 0;
            while (i12 < length2) {
                int i14 = iArr[i12];
                int iMin3 = Math.min(i10, i7 - i14);
                iArr2[i13] = iMin3;
                int iMin4 = Math.min(iO, (i7 - iMin3) - i14);
                int i15 = iArr2[i13] + i14 + iMin4;
                i12++;
                i13++;
                iMin = iMin4;
                i10 = i15;
            }
        }
        if (i10 - iMin < i7) {
            int iRound = Math.round((1 + (kVar != a1.k.l ? (-1.0f) * (-1) : -1.0f)) * ((i7 - r4) / 2.0f));
            int length3 = iArr2.length;
            for (int i16 = 0; i16 < length3; i16++) {
                iArr2[i16] = iArr2[i16] + iRound;
            }
        }
    }

    @Override // D.InterfaceC0125g
    public final void c(a1.b bVar, int i7, int[] iArr, int[] iArr2) {
        b(bVar, i7, iArr, a1.k.l, iArr2);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0124f)) {
            return false;
        }
        if (!a1.e.a(this.f1073a, ((C0124f) obj).f1073a)) {
            return false;
        }
        Object obj2 = C0126h.f1075m;
        return obj2.equals(obj2);
    }

    public final int hashCode() {
        return C0126h.f1075m.hashCode() + AbstractC1347d.d(Float.hashCode(this.f1073a) * 31, 31, true);
    }

    public final String toString() {
        StringBuilder sbL = A7.k.l("", "Arrangement#spacedAligned(");
        sbL.append((Object) a1.e.b(this.f1073a));
        sbL.append(", ");
        sbL.append(C0126h.f1075m);
        sbL.append(')');
        return sbL.toString();
    }
}
