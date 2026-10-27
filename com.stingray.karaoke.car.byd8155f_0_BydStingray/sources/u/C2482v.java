package u;

import java.util.Arrays;
import n7.AbstractC1996k;
import x3.AbstractC2673a;

/* JADX INFO: renamed from: u.v, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2482v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Object[] f22637a = new Object[16];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f22638b;

    public final void a(Object obj) {
        int i7 = this.f22638b + 1;
        Object[] objArr = this.f22637a;
        if (objArr.length < i7) {
            Object[] objArrCopyOf = Arrays.copyOf(objArr, Math.max(i7, (objArr.length * 3) / 2));
            A7.m.e(objArrCopyOf, "copyOf(this, newSize)");
            this.f22637a = objArrCopyOf;
        }
        Object[] objArr2 = this.f22637a;
        int i10 = this.f22638b;
        objArr2[i10] = obj;
        this.f22638b = i10 + 1;
    }

    public final int b(Object obj) {
        int i7 = 0;
        if (obj == null) {
            Object[] objArr = this.f22637a;
            int i10 = this.f22638b;
            while (i7 < i10) {
                if (objArr[i7] == null) {
                    return i7;
                }
                i7++;
            }
            return -1;
        }
        Object[] objArr2 = this.f22637a;
        int i11 = this.f22638b;
        while (i7 < i11) {
            if (obj.equals(objArr2[i7])) {
                return i7;
            }
            i7++;
        }
        return -1;
    }

    public final void c(C.j jVar) {
        int i7;
        int iB = b(jVar);
        if (iB >= 0) {
            if (iB < 0 || iB >= (i7 = this.f22638b)) {
                StringBuilder sbJ = M5.b.j("Index ", iB, " must be in 0..");
                sbJ.append(this.f22638b - 1);
                throw new IndexOutOfBoundsException(sbJ.toString());
            }
            Object[] objArr = this.f22637a;
            Object obj = objArr[iB];
            if (iB != i7 - 1) {
                AbstractC1996k.D(iB, iB + 1, i7, objArr, objArr);
            }
            int i10 = this.f22638b - 1;
            this.f22638b = i10;
            objArr[i10] = null;
        }
    }

    public final boolean equals(Object obj) {
        if (obj instanceof C2482v) {
            C2482v c2482v = (C2482v) obj;
            int i7 = c2482v.f22638b;
            int i10 = this.f22638b;
            if (i7 == i10) {
                Object[] objArr = this.f22637a;
                Object[] objArr2 = c2482v.f22637a;
                G7.d dVarB0 = AbstractC2673a.b0(0, i10);
                int i11 = dVarB0.l;
                int i12 = dVarB0.f2712m;
                if (i11 > i12) {
                    return true;
                }
                while (A7.m.a(objArr[i11], objArr2[i11])) {
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
        Object[] objArr = this.f22637a;
        int i7 = this.f22638b;
        int iHashCode = 0;
        for (int i10 = 0; i10 < i7; i10++) {
            Object obj = objArr[i10];
            iHashCode += (obj != null ? obj.hashCode() : 0) * 31;
        }
        return iHashCode;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append((CharSequence) "[");
        Object[] objArr = this.f22637a;
        int i7 = this.f22638b;
        int i10 = 0;
        while (true) {
            if (i10 >= i7) {
                sb.append((CharSequence) "]");
                break;
            }
            Object obj = objArr[i10];
            if (i10 == -1) {
                sb.append((CharSequence) "...");
                break;
            }
            if (i10 != 0) {
                sb.append((CharSequence) ", ");
            }
            sb.append((CharSequence) (obj == this ? "(this)" : String.valueOf(obj)));
            i10++;
        }
        String string = sb.toString();
        A7.m.e(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }
}
