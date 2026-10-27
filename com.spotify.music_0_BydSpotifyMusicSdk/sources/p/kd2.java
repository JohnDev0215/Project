package p;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class kd2 {
    public int[] c;
    public Object[] e;
    public int k;

    public kd2() {
        this(0);
    }

    public final int a(Object obj) {
        int i = this.k * 2;
        Object[] objArr = this.e;
        if (obj == null) {
            for (int i2 = 1; i2 < i; i2 += 2) {
                if (objArr[i2] == null) {
                    return i2 >> 1;
                }
            }
            return -1;
        }
        for (int i3 = 1; i3 < i; i3 += 2) {
            if (v03.b(obj, objArr[i3])) {
                return i3 >> 1;
            }
        }
        return -1;
    }

    public final void b(int i) {
        int i2 = this.k;
        int[] iArr = this.c;
        if (iArr.length < i) {
            int[] iArrCopyOf = Arrays.copyOf(iArr, i);
            v03.i(iArrCopyOf, "copyOf(this, newSize)");
            this.c = iArrCopyOf;
            Object[] objArrCopyOf = Arrays.copyOf(this.e, i * 2);
            v03.i(objArrCopyOf, "copyOf(this, newSize)");
            this.e = objArrCopyOf;
        }
        if (this.k != i2) {
            throw new ConcurrentModificationException();
        }
    }

    public final int c(int i, Object obj) {
        int i2 = this.k;
        if (i2 == 0) {
            return -1;
        }
        int i3 = zu.i(i2, i, this.c);
        if (i3 < 0 || v03.b(obj, this.e[i3 << 1])) {
            return i3;
        }
        int i4 = i3 + 1;
        while (i4 < i2 && this.c[i4] == i) {
            if (v03.b(obj, this.e[i4 << 1])) {
                return i4;
            }
            i4++;
        }
        for (int i5 = i3 - 1; i5 >= 0 && this.c[i5] == i; i5--) {
            if (v03.b(obj, this.e[i5 << 1])) {
                return i5;
            }
        }
        return ~i4;
    }

    public final void clear() {
        if (this.k > 0) {
            this.c = zu.d;
            this.e = zu.e;
            this.k = 0;
        }
        if (this.k > 0) {
            throw new ConcurrentModificationException();
        }
    }

    public boolean containsKey(Object obj) {
        return d(obj) >= 0;
    }

    public boolean containsValue(Object obj) {
        return a(obj) >= 0;
    }

    public final int d(Object obj) {
        return obj == null ? e() : c(obj.hashCode(), obj);
    }

    public final int e() {
        int i = this.k;
        if (i == 0) {
            return -1;
        }
        int i2 = zu.i(i, 0, this.c);
        if (i2 < 0 || this.e[i2 << 1] == null) {
            return i2;
        }
        int i3 = i2 + 1;
        while (i3 < i && this.c[i3] == 0) {
            if (this.e[i3 << 1] == null) {
                return i3;
            }
            i3++;
        }
        for (int i4 = i2 - 1; i4 >= 0 && this.c[i4] == 0; i4--) {
            if (this.e[i4 << 1] == null) {
                return i4;
            }
        }
        return ~i3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        try {
            if (obj instanceof kd2) {
                int i = this.k;
                if (i != ((kd2) obj).k) {
                    return false;
                }
                kd2 kd2Var = (kd2) obj;
                for (int i2 = 0; i2 < i; i2++) {
                    Object objF = f(i2);
                    Object objI = i(i2);
                    Object obj2 = kd2Var.get(objF);
                    if (objI == null) {
                        if (obj2 != null || !kd2Var.containsKey(objF)) {
                            return false;
                        }
                    } else if (!v03.b(objI, obj2)) {
                        return false;
                    }
                }
                return true;
            }
            if (!(obj instanceof Map) || this.k != ((Map) obj).size()) {
                return false;
            }
            int i3 = this.k;
            for (int i4 = 0; i4 < i3; i4++) {
                Object objF2 = f(i4);
                Object objI2 = i(i4);
                Object obj3 = ((Map) obj).get(objF2);
                if (objI2 == null) {
                    if (obj3 != null || !((Map) obj).containsKey(objF2)) {
                        return false;
                    }
                } else if (!v03.b(objI2, obj3)) {
                    return false;
                }
            }
            return true;
        } catch (ClassCastException | NullPointerException unused) {
        }
        return false;
    }

    public final Object f(int i) {
        if (i < 0 || i >= this.k) {
            throw new IllegalArgumentException(u4.e("Expected index to be within 0..size()-1, but was ", i).toString());
        }
        return this.e[i << 1];
    }

    public final Object g(int i) {
        int i2;
        if (i < 0 || i >= (i2 = this.k)) {
            throw new IllegalArgumentException(u4.e("Expected index to be within 0..size()-1, but was ", i).toString());
        }
        Object[] objArr = this.e;
        int i3 = i << 1;
        Object obj = objArr[i3 + 1];
        if (i2 <= 1) {
            clear();
        } else {
            int i4 = i2 - 1;
            int[] iArr = this.c;
            if (iArr.length <= 8 || i2 >= iArr.length / 3) {
                if (i < i4) {
                    int i5 = i + 1;
                    rb.j0(i, i5, i2, iArr, iArr);
                    Object[] objArr2 = this.e;
                    rb.k0(i3, i5 << 1, i2 << 1, objArr2, objArr2);
                }
                Object[] objArr3 = this.e;
                int i6 = i4 << 1;
                objArr3[i6] = null;
                objArr3[i6 + 1] = null;
            } else {
                int i7 = i2 > 8 ? i2 + (i2 >> 1) : 8;
                int[] iArrCopyOf = Arrays.copyOf(iArr, i7);
                v03.i(iArrCopyOf, "copyOf(this, newSize)");
                this.c = iArrCopyOf;
                Object[] objArrCopyOf = Arrays.copyOf(this.e, i7 << 1);
                v03.i(objArrCopyOf, "copyOf(this, newSize)");
                this.e = objArrCopyOf;
                if (i2 != this.k) {
                    throw new ConcurrentModificationException();
                }
                if (i > 0) {
                    rb.j0(0, 0, i, iArr, this.c);
                    rb.k0(0, 0, i3, objArr, this.e);
                }
                if (i < i4) {
                    int i8 = i + 1;
                    rb.j0(i, i8, i2, iArr, this.c);
                    rb.k0(i3, i8 << 1, i2 << 1, objArr, this.e);
                }
            }
            if (i2 != this.k) {
                throw new ConcurrentModificationException();
            }
            this.k = i4;
        }
        return obj;
    }

    public Object get(Object obj) {
        int iD = d(obj);
        if (iD >= 0) {
            return this.e[(iD << 1) + 1];
        }
        return null;
    }

    public final Object getOrDefault(Object obj, Object obj2) {
        int iD = d(obj);
        return iD >= 0 ? this.e[(iD << 1) + 1] : obj2;
    }

    public final Object h(int i, Object obj) {
        if (i < 0 || i >= this.k) {
            throw new IllegalArgumentException(u4.e("Expected index to be within 0..size()-1, but was ", i).toString());
        }
        int i2 = (i << 1) + 1;
        Object[] objArr = this.e;
        Object obj2 = objArr[i2];
        objArr[i2] = obj;
        return obj2;
    }

    public final int hashCode() {
        int[] iArr = this.c;
        Object[] objArr = this.e;
        int i = this.k;
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

    public final Object i(int i) {
        if (i < 0 || i >= this.k) {
            throw new IllegalArgumentException(u4.e("Expected index to be within 0..size()-1, but was ", i).toString());
        }
        return this.e[(i << 1) + 1];
    }

    public final boolean isEmpty() {
        return this.k <= 0;
    }

    public final Object put(Object obj, Object obj2) {
        int i = this.k;
        int iHashCode = obj != null ? obj.hashCode() : 0;
        int iC = obj != null ? c(iHashCode, obj) : e();
        if (iC >= 0) {
            int i2 = (iC << 1) + 1;
            Object[] objArr = this.e;
            Object obj3 = objArr[i2];
            objArr[i2] = obj2;
            return obj3;
        }
        int i3 = ~iC;
        int[] iArr = this.c;
        if (i >= iArr.length) {
            int i4 = 8;
            if (i >= 8) {
                i4 = (i >> 1) + i;
            } else if (i < 4) {
                i4 = 4;
            }
            int[] iArrCopyOf = Arrays.copyOf(iArr, i4);
            v03.i(iArrCopyOf, "copyOf(this, newSize)");
            this.c = iArrCopyOf;
            Object[] objArrCopyOf = Arrays.copyOf(this.e, i4 << 1);
            v03.i(objArrCopyOf, "copyOf(this, newSize)");
            this.e = objArrCopyOf;
            if (i != this.k) {
                throw new ConcurrentModificationException();
            }
        }
        if (i3 < i) {
            int[] iArr2 = this.c;
            int i5 = i3 + 1;
            rb.j0(i5, i3, i, iArr2, iArr2);
            Object[] objArr2 = this.e;
            rb.k0(i5 << 1, i3 << 1, this.k << 1, objArr2, objArr2);
        }
        int i6 = this.k;
        if (i == i6) {
            int[] iArr3 = this.c;
            if (i3 < iArr3.length) {
                iArr3[i3] = iHashCode;
                Object[] objArr3 = this.e;
                int i7 = i3 << 1;
                objArr3[i7] = obj;
                objArr3[i7 + 1] = obj2;
                this.k = i6 + 1;
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
        int iD = d(obj);
        if (iD >= 0) {
            return g(iD);
        }
        return null;
    }

    public final Object replace(Object obj, Object obj2) {
        int iD = d(obj);
        if (iD >= 0) {
            return h(iD, obj2);
        }
        return null;
    }

    public final int size() {
        return this.k;
    }

    public final String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.k * 28);
        sb.append('{');
        int i = this.k;
        for (int i2 = 0; i2 < i; i2++) {
            if (i2 > 0) {
                sb.append(", ");
            }
            Object objF = f(i2);
            if (objF != sb) {
                sb.append(objF);
            } else {
                sb.append("(this Map)");
            }
            sb.append('=');
            Object objI = i(i2);
            if (objI != sb) {
                sb.append(objI);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        String string = sb.toString();
        v03.i(string, "StringBuilder(capacity).…builderAction).toString()");
        return string;
    }

    public kd2(int i) {
        this.c = i == 0 ? zu.d : new int[i];
        this.e = i == 0 ? zu.e : new Object[i << 1];
    }

    public final boolean remove(Object obj, Object obj2) {
        int iD = d(obj);
        if (iD < 0 || !v03.b(obj2, i(iD))) {
            return false;
        }
        g(iD);
        return true;
    }

    public final boolean replace(Object obj, Object obj2, Object obj3) {
        int iD = d(obj);
        if (iD < 0 || !v03.b(obj2, i(iD))) {
            return false;
        }
        h(iD, obj3);
        return true;
    }
}
