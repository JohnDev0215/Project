package defpackage;

import java.lang.reflect.GenericDeclaration;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Map;

/* JADX INFO: compiled from: chromium-webapk8.dex-default-8 */
/* JADX INFO: loaded from: assets/webapk8.dex */
public final class S0 {
    private int[] a = AbstractC0197m.a;
    private Object[] b = AbstractC0197m.b;
    private int c;

    private final int b(int i, Object obj) {
        int i2 = this.c;
        if (i2 == 0) {
            return -1;
        }
        int iA = AbstractC0197m.a(this.a, i2, i);
        if (iA < 0 || C0186g0.a(obj, this.b[iA << 1])) {
            return iA;
        }
        int i3 = iA + 1;
        while (i3 < i2 && this.a[i3] == i) {
            if (C0186g0.a(obj, this.b[i3 << 1])) {
                return i3;
            }
            i3++;
        }
        for (int i4 = iA - 1; i4 >= 0 && this.a[i4] == i; i4--) {
            if (C0186g0.a(obj, this.b[i4 << 1])) {
                return i4;
            }
        }
        return ~i3;
    }

    private final int c() {
        int i = this.c;
        if (i == 0) {
            return -1;
        }
        int iA = AbstractC0197m.a(this.a, i, 0);
        if (iA < 0 || this.b[iA << 1] == null) {
            return iA;
        }
        int i2 = iA + 1;
        while (i2 < i && this.a[i2] == 0) {
            if (this.b[i2 << 1] == null) {
                return i2;
            }
            i2++;
        }
        for (int i3 = iA - 1; i3 >= 0 && this.a[i3] == 0; i3--) {
            if (this.b[i3 << 1] == null) {
                return i3;
            }
        }
        return ~i2;
    }

    public final Object a(Object obj) {
        int iC = obj == null ? c() : b(obj.hashCode(), obj);
        if (iC >= 0) {
            return this.b[(iC << 1) + 1];
        }
        return null;
    }

    public final Object d(int i) {
        boolean z = false;
        if (i >= 0 && i < this.c) {
            z = true;
        }
        if (z) {
            return this.b[i << 1];
        }
        throw new IllegalArgumentException(("Expected index to be within 0..size()-1, but was " + i).toString());
    }

    public final void e(Object obj, GenericDeclaration genericDeclaration) {
        int i = this.c;
        int iHashCode = obj.hashCode();
        int iB = b(iHashCode, obj);
        if (iB >= 0) {
            int i2 = (iB << 1) + 1;
            Object[] objArr = this.b;
            Object obj2 = objArr[i2];
            objArr[i2] = genericDeclaration;
            return;
        }
        int i3 = ~iB;
        int[] iArr = this.a;
        if (i >= iArr.length) {
            int i4 = 8;
            if (i >= 8) {
                i4 = (i >> 1) + i;
            } else if (i < 4) {
                i4 = 4;
            }
            int[] iArrCopyOf = Arrays.copyOf(iArr, i4);
            C0186g0.d(iArrCopyOf, "copyOf(this, newSize)");
            this.a = iArrCopyOf;
            Object[] objArrCopyOf = Arrays.copyOf(this.b, i4 << 1);
            C0186g0.d(objArrCopyOf, "copyOf(this, newSize)");
            this.b = objArrCopyOf;
            if (i != this.c) {
                throw new ConcurrentModificationException();
            }
        }
        if (i3 < i) {
            int[] iArr2 = this.a;
            int i5 = i3 + 1;
            C0186g0.e(iArr2, "<this>");
            System.arraycopy(iArr2, i3, iArr2, i5, i - i3);
            Object[] objArr2 = this.b;
            int i6 = i3 << 1;
            int i7 = this.c << 1;
            C0186g0.e(objArr2, "<this>");
            System.arraycopy(objArr2, i6, objArr2, i5 << 1, i7 - i6);
        }
        int i8 = this.c;
        if (i == i8) {
            int[] iArr3 = this.a;
            if (i3 < iArr3.length) {
                iArr3[i3] = iHashCode;
                Object[] objArr3 = this.b;
                int i9 = i3 << 1;
                objArr3[i9] = obj;
                objArr3[i9 + 1] = genericDeclaration;
                this.c = i8 + 1;
                return;
            }
        }
        throw new ConcurrentModificationException();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        try {
            if (obj instanceof S0) {
                int i = this.c;
                if (i != ((S0) obj).c) {
                    return false;
                }
                S0 s0 = (S0) obj;
                for (int i2 = 0; i2 < i; i2++) {
                    Object objD = d(i2);
                    Object objF = f(i2);
                    Object objA = s0.a(objD);
                    if (objF == null) {
                        if (objA == null) {
                            if (!((objD == null ? s0.c() : s0.b(objD.hashCode(), objD)) >= 0)) {
                            }
                        }
                        return false;
                    }
                    if (!C0186g0.a(objF, objA)) {
                        return false;
                    }
                }
                return true;
            }
            if (!(obj instanceof Map) || this.c != ((Map) obj).size()) {
                return false;
            }
            int i3 = this.c;
            for (int i4 = 0; i4 < i3; i4++) {
                Object objD2 = d(i4);
                Object objF2 = f(i4);
                Object obj2 = ((Map) obj).get(objD2);
                if (objF2 == null) {
                    if (obj2 != null || !((Map) obj).containsKey(objD2)) {
                        return false;
                    }
                } else if (!C0186g0.a(objF2, obj2)) {
                    return false;
                }
            }
            return true;
        } catch (ClassCastException | NullPointerException unused) {
        }
        return false;
    }

    public final Object f(int i) {
        boolean z = false;
        if (i >= 0 && i < this.c) {
            z = true;
        }
        if (z) {
            return this.b[(i << 1) + 1];
        }
        throw new IllegalArgumentException(("Expected index to be within 0..size()-1, but was " + i).toString());
    }

    public final int hashCode() {
        int[] iArr = this.a;
        Object[] objArr = this.b;
        int i = this.c;
        int i2 = 1;
        int i3 = 0;
        int iHashCode = 0;
        while (i3 < i) {
            Object obj = objArr[i2];
            iHashCode += (obj != null ? obj.hashCode() : 0) ^ iArr[i3];
            i3++;
            i2 += 2;
        }
        return iHashCode;
    }

    public final String toString() {
        int i = this.c;
        if (i <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(i * 28);
        sb.append('{');
        int i2 = this.c;
        for (int i3 = 0; i3 < i2; i3++) {
            if (i3 > 0) {
                sb.append(", ");
            }
            Object objD = d(i3);
            if (objD != sb) {
                sb.append(objD);
            } else {
                sb.append("(this Map)");
            }
            sb.append('=');
            Object objF = f(i3);
            if (objF != sb) {
                sb.append(objF);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        String string = sb.toString();
        C0186g0.d(string, "StringBuilder(capacity).…builderAction).toString()");
        return string;
    }
}
