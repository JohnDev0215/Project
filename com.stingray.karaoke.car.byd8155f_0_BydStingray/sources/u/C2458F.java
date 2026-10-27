package u;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Map;
import n7.AbstractC1996k;
import v.AbstractC2530a;

/* JADX INFO: renamed from: u.F, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C2458F {
    public int[] l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Object[] f22586m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f22587n;

    public C2458F(int i7) {
        this.l = i7 == 0 ? AbstractC2530a.f22938a : new int[i7];
        this.f22586m = i7 == 0 ? AbstractC2530a.c : new Object[i7 << 1];
    }

    public final int b(Object obj) {
        int i7 = this.f22587n * 2;
        Object[] objArr = this.f22586m;
        if (obj == null) {
            for (int i10 = 1; i10 < i7; i10 += 2) {
                if (objArr[i10] == null) {
                    return i10 >> 1;
                }
            }
            return -1;
        }
        for (int i11 = 1; i11 < i7; i11 += 2) {
            if (obj.equals(objArr[i11])) {
                return i11 >> 1;
            }
        }
        return -1;
    }

    public final void c(int i7) {
        int i10 = this.f22587n;
        int[] iArr = this.l;
        if (iArr.length < i7) {
            int[] iArrCopyOf = Arrays.copyOf(iArr, i7);
            A7.m.e(iArrCopyOf, "copyOf(this, newSize)");
            this.l = iArrCopyOf;
            Object[] objArrCopyOf = Arrays.copyOf(this.f22586m, i7 * 2);
            A7.m.e(objArrCopyOf, "copyOf(this, newSize)");
            this.f22586m = objArrCopyOf;
        }
        if (this.f22587n != i10) {
            throw new ConcurrentModificationException();
        }
    }

    public void clear() {
        if (this.f22587n > 0) {
            this.l = AbstractC2530a.f22938a;
            this.f22586m = AbstractC2530a.c;
            this.f22587n = 0;
        }
        if (this.f22587n > 0) {
            throw new ConcurrentModificationException();
        }
    }

    public boolean containsKey(Object obj) {
        return e(obj) >= 0;
    }

    public boolean containsValue(Object obj) {
        return b(obj) >= 0;
    }

    public final int d(int i7, Object obj) {
        int i10 = this.f22587n;
        if (i10 == 0) {
            return -1;
        }
        int iA = AbstractC2530a.a(i10, i7, this.l);
        if (iA < 0 || A7.m.a(obj, this.f22586m[iA << 1])) {
            return iA;
        }
        int i11 = iA + 1;
        while (i11 < i10 && this.l[i11] == i7) {
            if (A7.m.a(obj, this.f22586m[i11 << 1])) {
                return i11;
            }
            i11++;
        }
        for (int i12 = iA - 1; i12 >= 0 && this.l[i12] == i7; i12--) {
            if (A7.m.a(obj, this.f22586m[i12 << 1])) {
                return i12;
            }
        }
        return ~i11;
    }

    public final int e(Object obj) {
        return obj == null ? f() : d(obj.hashCode(), obj);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        try {
            if (obj instanceof C2458F) {
                int i7 = this.f22587n;
                if (i7 != ((C2458F) obj).f22587n) {
                    return false;
                }
                C2458F c2458f = (C2458F) obj;
                for (int i10 = 0; i10 < i7; i10++) {
                    Object objG = g(i10);
                    Object objK = k(i10);
                    Object obj2 = c2458f.get(objG);
                    if (objK == null) {
                        if (obj2 != null || !c2458f.containsKey(objG)) {
                            return false;
                        }
                    } else if (!objK.equals(obj2)) {
                        return false;
                    }
                }
                return true;
            }
            if (!(obj instanceof Map) || this.f22587n != ((Map) obj).size()) {
                return false;
            }
            int i11 = this.f22587n;
            for (int i12 = 0; i12 < i11; i12++) {
                Object objG2 = g(i12);
                Object objK2 = k(i12);
                Object obj3 = ((Map) obj).get(objG2);
                if (objK2 == null) {
                    if (obj3 != null || !((Map) obj).containsKey(objG2)) {
                        return false;
                    }
                } else if (!objK2.equals(obj3)) {
                    return false;
                }
            }
            return true;
        } catch (ClassCastException | NullPointerException unused) {
        }
        return false;
    }

    public final int f() {
        int i7 = this.f22587n;
        if (i7 == 0) {
            return -1;
        }
        int iA = AbstractC2530a.a(i7, 0, this.l);
        if (iA < 0 || this.f22586m[iA << 1] == null) {
            return iA;
        }
        int i10 = iA + 1;
        while (i10 < i7 && this.l[i10] == 0) {
            if (this.f22586m[i10 << 1] == null) {
                return i10;
            }
            i10++;
        }
        for (int i11 = iA - 1; i11 >= 0 && this.l[i11] == 0; i11--) {
            if (this.f22586m[i11 << 1] == null) {
                return i11;
            }
        }
        return ~i10;
    }

    public final Object g(int i7) {
        boolean z6 = false;
        if (i7 >= 0 && i7 < this.f22587n) {
            z6 = true;
        }
        if (z6) {
            return this.f22586m[i7 << 1];
        }
        AbstractC2530a.c("Expected index to be within 0..size()-1, but was " + i7);
        throw null;
    }

    public Object get(Object obj) {
        int iE = e(obj);
        if (iE >= 0) {
            return this.f22586m[(iE << 1) + 1];
        }
        return null;
    }

    public final Object getOrDefault(Object obj, Object obj2) {
        int iE = e(obj);
        return iE >= 0 ? this.f22586m[(iE << 1) + 1] : obj2;
    }

    public void h(C2465e c2465e) {
        int i7 = c2465e.f22587n;
        c(this.f22587n + i7);
        if (this.f22587n != 0) {
            for (int i10 = 0; i10 < i7; i10++) {
                put(c2465e.g(i10), c2465e.k(i10));
            }
        } else if (i7 > 0) {
            AbstractC1996k.C(0, 0, i7, c2465e.l, this.l);
            AbstractC1996k.D(0, 0, i7 << 1, c2465e.f22586m, this.f22586m);
            this.f22587n = i7;
        }
    }

    public int hashCode() {
        int[] iArr = this.l;
        Object[] objArr = this.f22586m;
        int i7 = this.f22587n;
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

    public Object i(int i7) {
        if (!(i7 >= 0 && i7 < this.f22587n)) {
            AbstractC2530a.c("Expected index to be within 0..size()-1, but was " + i7);
            throw null;
        }
        Object[] objArr = this.f22586m;
        int i10 = i7 << 1;
        Object obj = objArr[i10 + 1];
        int i11 = this.f22587n;
        if (i11 <= 1) {
            clear();
        } else {
            int i12 = i11 - 1;
            int[] iArr = this.l;
            if (iArr.length <= 8 || i11 >= iArr.length / 3) {
                if (i7 < i12) {
                    int i13 = i7 + 1;
                    AbstractC1996k.C(i7, i13, i11, iArr, iArr);
                    Object[] objArr2 = this.f22586m;
                    AbstractC1996k.D(i10, i13 << 1, i11 << 1, objArr2, objArr2);
                }
                Object[] objArr3 = this.f22586m;
                int i14 = i12 << 1;
                objArr3[i14] = null;
                objArr3[i14 + 1] = null;
            } else {
                int i15 = i11 > 8 ? i11 + (i11 >> 1) : 8;
                int[] iArrCopyOf = Arrays.copyOf(iArr, i15);
                A7.m.e(iArrCopyOf, "copyOf(this, newSize)");
                this.l = iArrCopyOf;
                Object[] objArrCopyOf = Arrays.copyOf(this.f22586m, i15 << 1);
                A7.m.e(objArrCopyOf, "copyOf(this, newSize)");
                this.f22586m = objArrCopyOf;
                if (i11 != this.f22587n) {
                    throw new ConcurrentModificationException();
                }
                if (i7 > 0) {
                    AbstractC1996k.C(0, 0, i7, iArr, this.l);
                    AbstractC1996k.D(0, 0, i10, objArr, this.f22586m);
                }
                if (i7 < i12) {
                    int i16 = i7 + 1;
                    AbstractC1996k.C(i7, i16, i11, iArr, this.l);
                    AbstractC1996k.D(i10, i16 << 1, i11 << 1, objArr, this.f22586m);
                }
            }
            if (i11 != this.f22587n) {
                throw new ConcurrentModificationException();
            }
            this.f22587n = i12;
        }
        return obj;
    }

    public final boolean isEmpty() {
        return this.f22587n <= 0;
    }

    public Object j(int i7, Object obj) {
        boolean z6 = false;
        if (i7 >= 0 && i7 < this.f22587n) {
            z6 = true;
        }
        if (!z6) {
            AbstractC2530a.c("Expected index to be within 0..size()-1, but was " + i7);
            throw null;
        }
        int i10 = (i7 << 1) + 1;
        Object[] objArr = this.f22586m;
        Object obj2 = objArr[i10];
        objArr[i10] = obj;
        return obj2;
    }

    public final Object k(int i7) {
        boolean z6 = false;
        if (i7 >= 0 && i7 < this.f22587n) {
            z6 = true;
        }
        if (z6) {
            return this.f22586m[(i7 << 1) + 1];
        }
        AbstractC2530a.c("Expected index to be within 0..size()-1, but was " + i7);
        throw null;
    }

    public Object put(Object obj, Object obj2) {
        int i7 = this.f22587n;
        int iHashCode = obj != null ? obj.hashCode() : 0;
        int iD = obj != null ? d(iHashCode, obj) : f();
        if (iD >= 0) {
            int i10 = (iD << 1) + 1;
            Object[] objArr = this.f22586m;
            Object obj3 = objArr[i10];
            objArr[i10] = obj2;
            return obj3;
        }
        int i11 = ~iD;
        int[] iArr = this.l;
        if (i7 >= iArr.length) {
            int i12 = 8;
            if (i7 >= 8) {
                i12 = (i7 >> 1) + i7;
            } else if (i7 < 4) {
                i12 = 4;
            }
            int[] iArrCopyOf = Arrays.copyOf(iArr, i12);
            A7.m.e(iArrCopyOf, "copyOf(this, newSize)");
            this.l = iArrCopyOf;
            Object[] objArrCopyOf = Arrays.copyOf(this.f22586m, i12 << 1);
            A7.m.e(objArrCopyOf, "copyOf(this, newSize)");
            this.f22586m = objArrCopyOf;
            if (i7 != this.f22587n) {
                throw new ConcurrentModificationException();
            }
        }
        if (i11 < i7) {
            int[] iArr2 = this.l;
            int i13 = i11 + 1;
            AbstractC1996k.C(i13, i11, i7, iArr2, iArr2);
            Object[] objArr2 = this.f22586m;
            AbstractC1996k.D(i13 << 1, i11 << 1, this.f22587n << 1, objArr2, objArr2);
        }
        int i14 = this.f22587n;
        if (i7 == i14) {
            int[] iArr3 = this.l;
            if (i11 < iArr3.length) {
                iArr3[i11] = iHashCode;
                Object[] objArr3 = this.f22586m;
                int i15 = i11 << 1;
                objArr3[i15] = obj;
                objArr3[i15 + 1] = obj2;
                this.f22587n = i14 + 1;
                return null;
            }
        }
        throw new ConcurrentModificationException();
    }

    public final Object putIfAbsent(Object obj, Object obj2) {
        Object obj3 = get(obj);
        return obj3 == null ? put(obj, obj2) : obj3;
    }

    public Object remove(Object obj) {
        int iE = e(obj);
        if (iE >= 0) {
            return i(iE);
        }
        return null;
    }

    public final Object replace(Object obj, Object obj2) {
        int iE = e(obj);
        if (iE >= 0) {
            return j(iE, obj2);
        }
        return null;
    }

    public final int size() {
        return this.f22587n;
    }

    public final String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f22587n * 28);
        sb.append('{');
        int i7 = this.f22587n;
        for (int i10 = 0; i10 < i7; i10++) {
            if (i10 > 0) {
                sb.append(", ");
            }
            Object objG = g(i10);
            if (objG != sb) {
                sb.append(objG);
            } else {
                sb.append("(this Map)");
            }
            sb.append('=');
            Object objK = k(i10);
            if (objK != sb) {
                sb.append(objK);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        String string = sb.toString();
        A7.m.e(string, "StringBuilder(capacity).…builderAction).toString()");
        return string;
    }

    public final boolean remove(Object obj, Object obj2) {
        int iE = e(obj);
        if (iE < 0 || !A7.m.a(obj2, k(iE))) {
            return false;
        }
        i(iE);
        return true;
    }

    public final boolean replace(Object obj, Object obj2, Object obj3) {
        int iE = e(obj);
        if (iE < 0 || !A7.m.a(obj2, k(iE))) {
            return false;
        }
        j(iE, obj3);
        return true;
    }
}
