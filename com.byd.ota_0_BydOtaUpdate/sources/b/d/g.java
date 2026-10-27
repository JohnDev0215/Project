package b.d;

import java.util.ConcurrentModificationException;
import java.util.Map;

/* JADX INFO: compiled from: SimpleArrayMap.java */
/* JADX INFO: loaded from: classes.dex */
public class g<K, V> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static Object[] f1180c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static int f1181d;
    public static Object[] s;
    public static int x;
    public Object[] E3;
    public int F3;
    public int[] y;

    public g() {
        this.y = c.f1157a;
        this.E3 = c.f1159c;
        this.F3 = 0;
    }

    public static int b(int[] iArr, int i2, int i3) {
        try {
            return c.a(iArr, i2, i3);
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new ConcurrentModificationException();
        }
    }

    public static void d(int[] iArr, Object[] objArr, int i2) {
        if (iArr.length == 8) {
            synchronized (g.class) {
                if (x < 10) {
                    objArr[0] = s;
                    objArr[1] = iArr;
                    for (int i3 = (i2 << 1) - 1; i3 >= 2; i3--) {
                        objArr[i3] = null;
                    }
                    s = objArr;
                    x++;
                }
            }
            return;
        }
        if (iArr.length == 4) {
            synchronized (g.class) {
                if (f1181d < 10) {
                    objArr[0] = f1180c;
                    objArr[1] = iArr;
                    for (int i4 = (i2 << 1) - 1; i4 >= 2; i4--) {
                        objArr[i4] = null;
                    }
                    f1180c = objArr;
                    f1181d++;
                }
            }
        }
    }

    public final void a(int i2) {
        if (i2 == 8) {
            synchronized (g.class) {
                Object[] objArr = s;
                if (objArr != null) {
                    this.E3 = objArr;
                    s = (Object[]) objArr[0];
                    this.y = (int[]) objArr[1];
                    objArr[1] = null;
                    objArr[0] = null;
                    x--;
                    return;
                }
            }
        } else if (i2 == 4) {
            synchronized (g.class) {
                Object[] objArr2 = f1180c;
                if (objArr2 != null) {
                    this.E3 = objArr2;
                    f1180c = (Object[]) objArr2[0];
                    this.y = (int[]) objArr2[1];
                    objArr2[1] = null;
                    objArr2[0] = null;
                    f1181d--;
                    return;
                }
            }
        }
        this.y = new int[i2];
        this.E3 = new Object[i2 << 1];
    }

    public void c(int i2) {
        int i3 = this.F3;
        int[] iArr = this.y;
        if (iArr.length < i2) {
            Object[] objArr = this.E3;
            a(i2);
            if (this.F3 > 0) {
                System.arraycopy(iArr, 0, this.y, 0, i3);
                System.arraycopy(objArr, 0, this.E3, 0, i3 << 1);
            }
            d(iArr, objArr, i3);
        }
        if (this.F3 != i3) {
            throw new ConcurrentModificationException();
        }
    }

    public void clear() {
        int i2 = this.F3;
        if (i2 > 0) {
            int[] iArr = this.y;
            Object[] objArr = this.E3;
            this.y = c.f1157a;
            this.E3 = c.f1159c;
            this.F3 = 0;
            d(iArr, objArr, i2);
        }
        if (this.F3 > 0) {
            throw new ConcurrentModificationException();
        }
    }

    public boolean containsKey(Object obj) {
        return f(obj) >= 0;
    }

    public boolean containsValue(Object obj) {
        return h(obj) >= 0;
    }

    public int e(Object obj, int i2) {
        int i3 = this.F3;
        if (i3 == 0) {
            return -1;
        }
        int iB = b(this.y, i3, i2);
        if (iB < 0 || obj.equals(this.E3[iB << 1])) {
            return iB;
        }
        int i4 = iB + 1;
        while (i4 < i3 && this.y[i4] == i2) {
            if (obj.equals(this.E3[i4 << 1])) {
                return i4;
            }
            i4++;
        }
        for (int i5 = iB - 1; i5 >= 0 && this.y[i5] == i2; i5--) {
            if (obj.equals(this.E3[i5 << 1])) {
                return i5;
            }
        }
        return ~i4;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof g) {
            g gVar = (g) obj;
            if (size() != gVar.size()) {
                return false;
            }
            for (int i2 = 0; i2 < this.F3; i2++) {
                try {
                    K kI = i(i2);
                    V vL = l(i2);
                    Object obj2 = gVar.get(kI);
                    if (vL == null) {
                        if (obj2 != null || !gVar.containsKey(kI)) {
                            return false;
                        }
                    } else if (!vL.equals(obj2)) {
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
            if (size() != map.size()) {
                return false;
            }
            for (int i3 = 0; i3 < this.F3; i3++) {
                try {
                    K kI2 = i(i3);
                    V vL2 = l(i3);
                    Object obj3 = map.get(kI2);
                    if (vL2 == null) {
                        if (obj3 != null || !map.containsKey(kI2)) {
                            return false;
                        }
                    } else if (!vL2.equals(obj3)) {
                        return false;
                    }
                } catch (ClassCastException | NullPointerException unused2) {
                }
            }
            return true;
        }
        return false;
    }

    public int f(Object obj) {
        return obj == null ? g() : e(obj, obj.hashCode());
    }

    public int g() {
        int i2 = this.F3;
        if (i2 == 0) {
            return -1;
        }
        int iB = b(this.y, i2, 0);
        if (iB < 0 || this.E3[iB << 1] == null) {
            return iB;
        }
        int i3 = iB + 1;
        while (i3 < i2 && this.y[i3] == 0) {
            if (this.E3[i3 << 1] == null) {
                return i3;
            }
            i3++;
        }
        for (int i4 = iB - 1; i4 >= 0 && this.y[i4] == 0; i4--) {
            if (this.E3[i4 << 1] == null) {
                return i4;
            }
        }
        return ~i3;
    }

    public V get(Object obj) {
        return getOrDefault(obj, null);
    }

    public V getOrDefault(Object obj, V v) {
        int iF = f(obj);
        return iF >= 0 ? (V) this.E3[(iF << 1) + 1] : v;
    }

    public int h(Object obj) {
        int i2 = this.F3 * 2;
        Object[] objArr = this.E3;
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

    public int hashCode() {
        int[] iArr = this.y;
        Object[] objArr = this.E3;
        int i2 = this.F3;
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

    public K i(int i2) {
        return (K) this.E3[i2 << 1];
    }

    public boolean isEmpty() {
        return this.F3 <= 0;
    }

    public V j(int i2) {
        Object[] objArr = this.E3;
        int i3 = i2 << 1;
        V v = (V) objArr[i3 + 1];
        int i4 = this.F3;
        int i5 = 0;
        if (i4 <= 1) {
            d(this.y, objArr, i4);
            this.y = c.f1157a;
            this.E3 = c.f1159c;
        } else {
            int i6 = i4 - 1;
            int[] iArr = this.y;
            if (iArr.length <= 8 || i4 >= iArr.length / 3) {
                if (i2 < i6) {
                    int i7 = i2 + 1;
                    int i8 = i6 - i2;
                    System.arraycopy(iArr, i7, iArr, i2, i8);
                    Object[] objArr2 = this.E3;
                    System.arraycopy(objArr2, i7 << 1, objArr2, i3, i8 << 1);
                }
                Object[] objArr3 = this.E3;
                int i9 = i6 << 1;
                objArr3[i9] = null;
                objArr3[i9 + 1] = null;
            } else {
                a(i4 > 8 ? i4 + (i4 >> 1) : 8);
                if (i4 != this.F3) {
                    throw new ConcurrentModificationException();
                }
                if (i2 > 0) {
                    System.arraycopy(iArr, 0, this.y, 0, i2);
                    System.arraycopy(objArr, 0, this.E3, 0, i3);
                }
                if (i2 < i6) {
                    int i10 = i2 + 1;
                    int i11 = i6 - i2;
                    System.arraycopy(iArr, i10, this.y, i2, i11);
                    System.arraycopy(objArr, i10 << 1, this.E3, i3, i11 << 1);
                }
            }
            i5 = i6;
        }
        if (i4 != this.F3) {
            throw new ConcurrentModificationException();
        }
        this.F3 = i5;
        return v;
    }

    public V k(int i2, V v) {
        int i3 = (i2 << 1) + 1;
        Object[] objArr = this.E3;
        V v2 = (V) objArr[i3];
        objArr[i3] = v;
        return v2;
    }

    public V l(int i2) {
        return (V) this.E3[(i2 << 1) + 1];
    }

    public V put(K k, V v) {
        int i2;
        int iE;
        int i3 = this.F3;
        if (k == null) {
            iE = g();
            i2 = 0;
        } else {
            int iHashCode = k.hashCode();
            i2 = iHashCode;
            iE = e(k, iHashCode);
        }
        if (iE >= 0) {
            int i4 = (iE << 1) + 1;
            Object[] objArr = this.E3;
            V v2 = (V) objArr[i4];
            objArr[i4] = v;
            return v2;
        }
        int i5 = ~iE;
        int[] iArr = this.y;
        if (i3 >= iArr.length) {
            int i6 = 4;
            if (i3 >= 8) {
                i6 = (i3 >> 1) + i3;
            } else if (i3 >= 4) {
                i6 = 8;
            }
            Object[] objArr2 = this.E3;
            a(i6);
            if (i3 != this.F3) {
                throw new ConcurrentModificationException();
            }
            int[] iArr2 = this.y;
            if (iArr2.length > 0) {
                System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
                System.arraycopy(objArr2, 0, this.E3, 0, objArr2.length);
            }
            d(iArr, objArr2, i3);
        }
        if (i5 < i3) {
            int[] iArr3 = this.y;
            int i7 = i5 + 1;
            System.arraycopy(iArr3, i5, iArr3, i7, i3 - i5);
            Object[] objArr3 = this.E3;
            System.arraycopy(objArr3, i5 << 1, objArr3, i7 << 1, (this.F3 - i5) << 1);
        }
        int i8 = this.F3;
        if (i3 == i8) {
            int[] iArr4 = this.y;
            if (i5 < iArr4.length) {
                iArr4[i5] = i2;
                Object[] objArr4 = this.E3;
                int i9 = i5 << 1;
                objArr4[i9] = k;
                objArr4[i9 + 1] = v;
                this.F3 = i8 + 1;
                return null;
            }
        }
        throw new ConcurrentModificationException();
    }

    public V putIfAbsent(K k, V v) {
        V v2 = get(k);
        return v2 == null ? put(k, v) : v2;
    }

    public V remove(Object obj) {
        int iF = f(obj);
        if (iF >= 0) {
            return j(iF);
        }
        return null;
    }

    public V replace(K k, V v) {
        int iF = f(k);
        if (iF >= 0) {
            return k(iF, v);
        }
        return null;
    }

    public int size() {
        return this.F3;
    }

    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.F3 * 28);
        sb.append('{');
        for (int i2 = 0; i2 < this.F3; i2++) {
            if (i2 > 0) {
                sb.append(", ");
            }
            K kI = i(i2);
            if (kI != this) {
                sb.append(kI);
            } else {
                sb.append("(this Map)");
            }
            sb.append('=');
            V vL = l(i2);
            if (vL != this) {
                sb.append(vL);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public boolean remove(Object obj, Object obj2) {
        int iF = f(obj);
        if (iF < 0) {
            return false;
        }
        V vL = l(iF);
        if (obj2 != vL && (obj2 == null || !obj2.equals(vL))) {
            return false;
        }
        j(iF);
        return true;
    }

    public boolean replace(K k, V v, V v2) {
        int iF = f(k);
        if (iF < 0) {
            return false;
        }
        V vL = l(iF);
        if (vL != v && (v == null || !v.equals(vL))) {
            return false;
        }
        k(iF, v2);
        return true;
    }

    public g(int i2) {
        if (i2 == 0) {
            this.y = c.f1157a;
            this.E3 = c.f1159c;
        } else {
            a(i2);
        }
        this.F3 = 0;
    }
}
