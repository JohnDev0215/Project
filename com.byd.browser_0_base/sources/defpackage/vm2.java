package defpackage;

import java.util.ConcurrentModificationException;
import java.util.Map;

/* JADX INFO: compiled from: SimpleArrayMap.java */
/* JADX INFO: loaded from: classes3.dex */
public class vm2<K, V> {
    public static Object[] h;
    public static int i;
    public static Object[] j;
    public static int k;
    public int[] e = z7.g;
    public Object[] f = z7.h;
    public int g = 0;

    public static int b(int i2, int i3, int[] iArr) {
        int i4 = i2 - 1;
        int i5 = 0;
        while (i5 <= i4) {
            int i6 = (i5 + i4) >>> 1;
            try {
                int i7 = iArr[i6];
                if (i7 < i3) {
                    i5 = i6 + 1;
                } else {
                    if (i7 <= i3) {
                        return i6;
                    }
                    i4 = i6 - 1;
                }
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw new ConcurrentModificationException();
            }
        }
        return ~i5;
    }

    public static void c(int[] iArr, Object[] objArr, int i2) {
        if (iArr.length == 8) {
            synchronized (n8.class) {
                if (k < 10) {
                    objArr[0] = j;
                    objArr[1] = iArr;
                    for (int i3 = (i2 << 1) - 1; i3 >= 2; i3--) {
                        objArr[i3] = null;
                    }
                    j = objArr;
                    k++;
                }
            }
            return;
        }
        if (iArr.length == 4) {
            synchronized (n8.class) {
                if (i < 10) {
                    objArr[0] = h;
                    objArr[1] = iArr;
                    for (int i4 = (i2 << 1) - 1; i4 >= 2; i4--) {
                        objArr[i4] = null;
                    }
                    h = objArr;
                    i++;
                }
            }
        }
    }

    public final void a(int i2) {
        if (i2 == 8) {
            synchronized (n8.class) {
                Object[] objArr = j;
                if (objArr != null) {
                    this.f = objArr;
                    j = (Object[]) objArr[0];
                    this.e = (int[]) objArr[1];
                    objArr[1] = null;
                    objArr[0] = null;
                    k--;
                    return;
                }
            }
        } else if (i2 == 4) {
            synchronized (n8.class) {
                Object[] objArr2 = h;
                if (objArr2 != null) {
                    this.f = objArr2;
                    h = (Object[]) objArr2[0];
                    this.e = (int[]) objArr2[1];
                    objArr2[1] = null;
                    objArr2[0] = null;
                    i--;
                    return;
                }
            }
        }
        this.e = new int[i2];
        this.f = new Object[i2 << 1];
    }

    public final void clear() {
        int i2 = this.g;
        if (i2 > 0) {
            int[] iArr = this.e;
            Object[] objArr = this.f;
            this.e = z7.g;
            this.f = z7.h;
            this.g = 0;
            c(iArr, objArr, i2);
        }
        if (this.g > 0) {
            throw new ConcurrentModificationException();
        }
    }

    public final boolean containsKey(Object obj) {
        return e(obj) >= 0;
    }

    public final boolean containsValue(Object obj) {
        return g(obj) >= 0;
    }

    final int d(int i2, Object obj) {
        int i3 = this.g;
        if (i3 == 0) {
            return -1;
        }
        int iB = b(i3, i2, this.e);
        if (iB < 0 || obj.equals(this.f[iB << 1])) {
            return iB;
        }
        int i4 = iB + 1;
        while (i4 < i3 && this.e[i4] == i2) {
            if (obj.equals(this.f[i4 << 1])) {
                return i4;
            }
            i4++;
        }
        for (int i5 = iB - 1; i5 >= 0 && this.e[i5] == i2; i5--) {
            if (obj.equals(this.f[i5 << 1])) {
                return i5;
            }
        }
        return ~i4;
    }

    public final int e(Object obj) {
        return obj == null ? f() : d(obj.hashCode(), obj);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof vm2) {
            vm2 vm2Var = (vm2) obj;
            if (this.g != vm2Var.g) {
                return false;
            }
            for (int i2 = 0; i2 < this.g; i2++) {
                try {
                    Object[] objArr = this.f;
                    int i3 = i2 << 1;
                    Object obj2 = objArr[i3];
                    Object obj3 = objArr[i3 + 1];
                    Object obj4 = vm2Var.get(obj2);
                    if (obj3 == null) {
                        if (obj4 != null || !vm2Var.containsKey(obj2)) {
                            return false;
                        }
                    } else if (!obj3.equals(obj4)) {
                        return false;
                    }
                } catch (ClassCastException | NullPointerException unused) {
                    return false;
                }
            }
            return true;
        }
        if (obj instanceof Map) {
            Map map = (Map) obj;
            if (this.g != map.size()) {
                return false;
            }
            for (int i4 = 0; i4 < this.g; i4++) {
                try {
                    Object[] objArr2 = this.f;
                    int i5 = i4 << 1;
                    Object obj5 = objArr2[i5];
                    Object obj6 = objArr2[i5 + 1];
                    Object obj7 = map.get(obj5);
                    if (obj6 == null) {
                        if (obj7 != null || !map.containsKey(obj5)) {
                            return false;
                        }
                    } else if (!obj6.equals(obj7)) {
                        return false;
                    }
                } catch (ClassCastException | NullPointerException unused2) {
                }
            }
            return true;
        }
        return false;
    }

    public final int f() {
        int i2 = this.g;
        if (i2 == 0) {
            return -1;
        }
        int iB = b(i2, 0, this.e);
        if (iB < 0 || this.f[iB << 1] == null) {
            return iB;
        }
        int i3 = iB + 1;
        while (i3 < i2 && this.e[i3] == 0) {
            if (this.f[i3 << 1] == null) {
                return i3;
            }
            i3++;
        }
        for (int i4 = iB - 1; i4 >= 0 && this.e[i4] == 0; i4--) {
            if (this.f[i4 << 1] == null) {
                return i4;
            }
        }
        return ~i3;
    }

    final int g(Object obj) {
        int i2 = this.g * 2;
        Object[] objArr = this.f;
        if (obj == null) {
            for (int i3 = 1; i3 < i2; i3 += 2) {
                if (objArr[i3] == null) {
                    return i3 >> 1;
                }
            }
            return -1;
        }
        for (int i4 = 1; i4 < i2; i4 += 2) {
            if (obj.equals(objArr[i4])) {
                return i4 >> 1;
            }
        }
        return -1;
    }

    public final V get(Object obj) {
        int iE = e(obj);
        if (iE >= 0) {
            return (V) this.f[(iE << 1) + 1];
        }
        return null;
    }

    public final V h(int i2) {
        Object[] objArr = this.f;
        int i3 = i2 << 1;
        V v = (V) objArr[i3 + 1];
        int i4 = this.g;
        int i5 = 0;
        if (i4 <= 1) {
            c(this.e, objArr, i4);
            this.e = z7.g;
            this.f = z7.h;
        } else {
            int i6 = i4 - 1;
            int[] iArr = this.e;
            if (iArr.length <= 8 || i4 >= iArr.length / 3) {
                if (i2 < i6) {
                    int i7 = i2 + 1;
                    int i8 = i6 - i2;
                    System.arraycopy(iArr, i7, iArr, i2, i8);
                    Object[] objArr2 = this.f;
                    System.arraycopy(objArr2, i7 << 1, objArr2, i3, i8 << 1);
                }
                Object[] objArr3 = this.f;
                int i9 = i6 << 1;
                objArr3[i9] = null;
                objArr3[i9 + 1] = null;
            } else {
                a(i4 > 8 ? i4 + (i4 >> 1) : 8);
                if (i4 != this.g) {
                    throw new ConcurrentModificationException();
                }
                if (i2 > 0) {
                    System.arraycopy(iArr, 0, this.e, 0, i2);
                    System.arraycopy(objArr, 0, this.f, 0, i3);
                }
                if (i2 < i6) {
                    int i10 = i2 + 1;
                    int i11 = i6 - i2;
                    System.arraycopy(iArr, i10, this.e, i2, i11);
                    System.arraycopy(objArr, i10 << 1, this.f, i3, i11 << 1);
                }
            }
            i5 = i6;
        }
        if (i4 != this.g) {
            throw new ConcurrentModificationException();
        }
        this.g = i5;
        return v;
    }

    public final int hashCode() {
        int[] iArr = this.e;
        Object[] objArr = this.f;
        int i2 = this.g;
        int i3 = 1;
        int i4 = 0;
        int iHashCode = 0;
        while (i4 < i2) {
            Object obj = objArr[i3];
            iHashCode += (obj == null ? 0 : obj.hashCode()) ^ iArr[i4];
            i4++;
            i3 += 2;
        }
        return iHashCode;
    }

    public final boolean isEmpty() {
        return this.g <= 0;
    }

    public final V put(K k2, V v) {
        int i2;
        int iD;
        int i3 = this.g;
        if (k2 == null) {
            iD = f();
            i2 = 0;
        } else {
            int iHashCode = k2.hashCode();
            i2 = iHashCode;
            iD = d(iHashCode, k2);
        }
        if (iD >= 0) {
            int i4 = (iD << 1) + 1;
            Object[] objArr = this.f;
            V v2 = (V) objArr[i4];
            objArr[i4] = v;
            return v2;
        }
        int i5 = ~iD;
        int[] iArr = this.e;
        if (i3 >= iArr.length) {
            int i6 = 8;
            if (i3 >= 8) {
                i6 = (i3 >> 1) + i3;
            } else if (i3 < 4) {
                i6 = 4;
            }
            Object[] objArr2 = this.f;
            a(i6);
            if (i3 != this.g) {
                throw new ConcurrentModificationException();
            }
            int[] iArr2 = this.e;
            if (iArr2.length > 0) {
                System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
                System.arraycopy(objArr2, 0, this.f, 0, objArr2.length);
            }
            c(iArr, objArr2, i3);
        }
        if (i5 < i3) {
            int[] iArr3 = this.e;
            int i7 = i5 + 1;
            System.arraycopy(iArr3, i5, iArr3, i7, i3 - i5);
            Object[] objArr3 = this.f;
            System.arraycopy(objArr3, i5 << 1, objArr3, i7 << 1, (this.g - i5) << 1);
        }
        int i8 = this.g;
        if (i3 == i8) {
            int[] iArr4 = this.e;
            if (i5 < iArr4.length) {
                iArr4[i5] = i2;
                Object[] objArr4 = this.f;
                int i9 = i5 << 1;
                objArr4[i9] = k2;
                objArr4[i9 + 1] = v;
                this.g = i8 + 1;
                return null;
            }
        }
        throw new ConcurrentModificationException();
    }

    public final V remove(Object obj) {
        int iE = e(obj);
        if (iE >= 0) {
            return h(iE);
        }
        return null;
    }

    public final int size() {
        return this.g;
    }

    public final String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.g * 28);
        sb.append('{');
        for (int i2 = 0; i2 < this.g; i2++) {
            if (i2 > 0) {
                sb.append(", ");
            }
            int i3 = i2 << 1;
            Object obj = this.f[i3];
            if (obj != this) {
                sb.append(obj);
            } else {
                sb.append("(this Map)");
            }
            sb.append('=');
            Object obj2 = this.f[i3 + 1];
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
