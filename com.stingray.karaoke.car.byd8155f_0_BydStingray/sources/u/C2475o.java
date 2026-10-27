package u;

import java.util.Arrays;
import n7.AbstractC1996k;
import x3.AbstractC2673a;

/* JADX INFO: renamed from: u.o, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2475o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int[] f22613a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f22614b;

    public C2475o(int i7) {
        this.f22613a = i7 == 0 ? AbstractC2470j.f22603a : new int[i7];
    }

    public final void a(int i7) {
        b(this.f22614b + 1);
        int[] iArr = this.f22613a;
        int i10 = this.f22614b;
        iArr[i10] = i7;
        this.f22614b = i10 + 1;
    }

    public final void b(int i7) {
        int[] iArr = this.f22613a;
        if (iArr.length < i7) {
            int[] iArrCopyOf = Arrays.copyOf(iArr, Math.max(i7, (iArr.length * 3) / 2));
            A7.m.e(iArrCopyOf, "copyOf(this, newSize)");
            this.f22613a = iArrCopyOf;
        }
    }

    public final int c(int i7) {
        if (i7 >= 0 && i7 < this.f22614b) {
            return this.f22613a[i7];
        }
        StringBuilder sbJ = M5.b.j("Index ", i7, " must be in 0..");
        sbJ.append(this.f22614b - 1);
        throw new IndexOutOfBoundsException(sbJ.toString());
    }

    public final int d(int i7) {
        int i10;
        if (i7 < 0 || i7 >= (i10 = this.f22614b)) {
            StringBuilder sbJ = M5.b.j("Index ", i7, " must be in 0..");
            sbJ.append(this.f22614b - 1);
            throw new IndexOutOfBoundsException(sbJ.toString());
        }
        int[] iArr = this.f22613a;
        int i11 = iArr[i7];
        if (i7 != i10 - 1) {
            AbstractC1996k.C(i7, i7 + 1, i10, iArr, iArr);
        }
        this.f22614b--;
        return i11;
    }

    public final void e(int i7, int i10) {
        if (i7 < 0 || i7 >= this.f22614b) {
            StringBuilder sbJ = M5.b.j("set index ", i7, " must be between 0 .. ");
            sbJ.append(this.f22614b - 1);
            throw new IndexOutOfBoundsException(sbJ.toString());
        }
        int[] iArr = this.f22613a;
        int i11 = iArr[i7];
        iArr[i7] = i10;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof C2475o) {
            C2475o c2475o = (C2475o) obj;
            int i7 = c2475o.f22614b;
            int i10 = this.f22614b;
            if (i7 == i10) {
                int[] iArr = this.f22613a;
                int[] iArr2 = c2475o.f22613a;
                G7.d dVarB0 = AbstractC2673a.b0(0, i10);
                int i11 = dVarB0.l;
                int i12 = dVarB0.f2712m;
                if (i11 > i12) {
                    return true;
                }
                while (iArr[i11] == iArr2[i11]) {
                    if (i11 == i12) {
                        return true;
                    }
                    i11++;
                }
                return false;
            }
        }
        return false;
    }

    public final int hashCode() {
        int[] iArr = this.f22613a;
        int i7 = this.f22614b;
        int iHashCode = 0;
        for (int i10 = 0; i10 < i7; i10++) {
            iHashCode += Integer.hashCode(iArr[i10]) * 31;
        }
        return iHashCode;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append((CharSequence) "[");
        int[] iArr = this.f22613a;
        int i7 = this.f22614b;
        int i10 = 0;
        while (true) {
            if (i10 >= i7) {
                sb.append((CharSequence) "]");
                break;
            }
            int i11 = iArr[i10];
            if (i10 == -1) {
                sb.append((CharSequence) "...");
                break;
            }
            if (i10 != 0) {
                sb.append((CharSequence) ", ");
            }
            sb.append(i11);
            i10++;
        }
        String string = sb.toString();
        A7.m.e(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    public /* synthetic */ C2475o() {
        this(16);
    }
}
