package R0;

import A7.m;
import S0.C0697i;
import S0.C0698j;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Map;
import n7.AbstractC1996k;

/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int[] f7350a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Object[] f7351b;
    public int c;

    public final Object a(Object obj) {
        int iC = obj == null ? c() : b(obj.hashCode(), obj);
        if (iC >= 0) {
            return this.f7351b[(iC << 1) + 1];
        }
        return null;
    }

    public final int b(int i7, Object obj) {
        int i10 = this.c;
        if (i10 == 0) {
            return -1;
        }
        int iA = a.a(i10, i7, this.f7350a);
        if (iA < 0 || m.a(obj, this.f7351b[iA << 1])) {
            return iA;
        }
        int i11 = iA + 1;
        while (i11 < i10 && this.f7350a[i11] == i7) {
            if (m.a(obj, this.f7351b[i11 << 1])) {
                return i11;
            }
            i11++;
        }
        for (int i12 = iA - 1; i12 >= 0 && this.f7350a[i12] == i7; i12--) {
            if (m.a(obj, this.f7351b[i12 << 1])) {
                return i12;
            }
        }
        return ~i11;
    }

    public final int c() {
        int i7 = this.c;
        if (i7 == 0) {
            return -1;
        }
        int iA = a.a(i7, 0, this.f7350a);
        if (iA < 0 || this.f7351b[iA << 1] == null) {
            return iA;
        }
        int i10 = iA + 1;
        while (i10 < i7 && this.f7350a[i10] == 0) {
            if (this.f7351b[i10 << 1] == null) {
                return i10;
            }
            i10++;
        }
        for (int i11 = iA - 1; i11 >= 0 && this.f7350a[i11] == 0; i11--) {
            if (this.f7351b[i11 << 1] == null) {
                return i11;
            }
        }
        return ~i10;
    }

    public final Object d(C0698j c0698j, C0697i c0697i) {
        int iHashCode;
        int iB;
        int i7 = this.c;
        if (c0698j == null) {
            iB = c();
            iHashCode = 0;
        } else {
            iHashCode = c0698j.hashCode();
            iB = b(iHashCode, c0698j);
        }
        if (iB >= 0) {
            int i10 = (iB << 1) + 1;
            Object[] objArr = this.f7351b;
            Object obj = objArr[i10];
            objArr[i10] = c0697i;
            return obj;
        }
        int i11 = ~iB;
        int[] iArr = this.f7350a;
        if (i7 >= iArr.length) {
            int i12 = 8;
            if (i7 >= 8) {
                i12 = (i7 >> 1) + i7;
            } else if (i7 < 4) {
                i12 = 4;
            }
            int[] iArrCopyOf = Arrays.copyOf(iArr, i12);
            m.e(iArrCopyOf, "copyOf(this, newSize)");
            this.f7350a = iArrCopyOf;
            Object[] objArrCopyOf = Arrays.copyOf(this.f7351b, i12 << 1);
            m.e(objArrCopyOf, "copyOf(this, newSize)");
            this.f7351b = objArrCopyOf;
            if (i7 != this.c) {
                throw new ConcurrentModificationException();
            }
        }
        if (i11 < i7) {
            int[] iArr2 = this.f7350a;
            int i13 = i11 + 1;
            AbstractC1996k.C(i13, i11, i7, iArr2, iArr2);
            Object[] objArr2 = this.f7351b;
            AbstractC1996k.D(i13 << 1, i11 << 1, this.c << 1, objArr2, objArr2);
        }
        int i14 = this.c;
        if (i7 == i14) {
            int[] iArr3 = this.f7350a;
            if (i11 < iArr3.length) {
                iArr3[i11] = iHashCode;
                Object[] objArr3 = this.f7351b;
                int i15 = i11 << 1;
                objArr3[i15] = c0698j;
                objArr3[i15 + 1] = c0697i;
                this.c = i14 + 1;
                return null;
            }
        }
        throw new ConcurrentModificationException();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        try {
            if (obj instanceof c) {
                c cVar = (c) obj;
                int i7 = this.c;
                if (i7 != cVar.c) {
                    return false;
                }
                for (int i10 = 0; i10 < i7; i10++) {
                    Object[] objArr = this.f7351b;
                    int i11 = i10 << 1;
                    Object obj2 = objArr[i11];
                    Object obj3 = objArr[i11 + 1];
                    Object objA = cVar.a(obj2);
                    if (obj3 == null) {
                        if (objA == null) {
                            if ((obj2 == null ? cVar.c() : cVar.b(obj2.hashCode(), obj2)) >= 0) {
                            }
                        }
                        return false;
                    }
                    if (!obj3.equals(objA)) {
                        return false;
                    }
                }
                return true;
            }
            if (!(obj instanceof Map) || this.c != ((Map) obj).size()) {
                return false;
            }
            int i12 = this.c;
            for (int i13 = 0; i13 < i12; i13++) {
                Object[] objArr2 = this.f7351b;
                int i14 = i13 << 1;
                Object obj4 = objArr2[i14];
                Object obj5 = objArr2[i14 + 1];
                Object obj6 = ((Map) obj).get(obj4);
                if (obj5 == null) {
                    if (obj6 != null || !((Map) obj).containsKey(obj4)) {
                        return false;
                    }
                } else if (!obj5.equals(obj6)) {
                    return false;
                }
            }
            return true;
        } catch (ClassCastException | NullPointerException unused) {
        }
        return false;
    }

    public final int hashCode() {
        int[] iArr = this.f7350a;
        Object[] objArr = this.f7351b;
        int i7 = this.c;
        int i10 = 1;
        int i11 = 0;
        int iHashCode = 0;
        while (i11 < i7) {
            Object obj = objArr[i10];
            iHashCode += (obj != null ? obj.hashCode() : 0) ^ iArr[i11];
            i11++;
            i10 += 2;
        }
        return iHashCode;
    }

    public final String toString() {
        int i7 = this.c;
        if (i7 <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(i7 * 28);
        sb.append('{');
        int i10 = this.c;
        for (int i11 = 0; i11 < i10; i11++) {
            if (i11 > 0) {
                sb.append(", ");
            }
            int i12 = i11 << 1;
            Object obj = this.f7351b[i12];
            if (obj != this) {
                sb.append(obj);
            } else {
                sb.append("(this Map)");
            }
            sb.append('=');
            Object obj2 = this.f7351b[i12 + 1];
            if (obj2 != this) {
                sb.append(obj2);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }
}
