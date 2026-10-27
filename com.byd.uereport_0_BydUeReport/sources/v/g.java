package v;

import java.util.ConcurrentModificationException;
import java.util.Map;

/* JADX INFO: compiled from: SimpleArrayMap.java */
/* JADX INFO: loaded from: classes.dex */
public class g<K, V> {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static Object[] f8342d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static int f8343e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static Object[] f8344f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static int f8345g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int[] f8346h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Object[] f8347i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f8348j;

    public g() {
        this.f8346h = c.f8311a;
        this.f8347i = c.f8313c;
        this.f8348j = 0;
    }

    public static int b(int[] iArr, int i8, int i9) {
        try {
            return c.a(iArr, i8, i9);
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new ConcurrentModificationException();
        }
    }

    public static void d(int[] iArr, Object[] objArr, int i8) {
        if (iArr.length == 8) {
            synchronized (g.class) {
                if (f8345g < 10) {
                    objArr[0] = f8344f;
                    objArr[1] = iArr;
                    for (int i9 = (i8 << 1) - 1; i9 >= 2; i9--) {
                        objArr[i9] = null;
                    }
                    f8344f = objArr;
                    f8345g++;
                }
            }
            return;
        }
        if (iArr.length == 4) {
            synchronized (g.class) {
                if (f8343e < 10) {
                    objArr[0] = f8342d;
                    objArr[1] = iArr;
                    for (int i10 = (i8 << 1) - 1; i10 >= 2; i10--) {
                        objArr[i10] = null;
                    }
                    f8342d = objArr;
                    f8343e++;
                }
            }
        }
    }

    public final void a(int i8) {
        if (i8 == 8) {
            synchronized (g.class) {
                Object[] objArr = f8344f;
                if (objArr != null) {
                    this.f8347i = objArr;
                    f8344f = (Object[]) objArr[0];
                    this.f8346h = (int[]) objArr[1];
                    objArr[1] = null;
                    objArr[0] = null;
                    f8345g--;
                    return;
                }
            }
        } else if (i8 == 4) {
            synchronized (g.class) {
                Object[] objArr2 = f8342d;
                if (objArr2 != null) {
                    this.f8347i = objArr2;
                    f8342d = (Object[]) objArr2[0];
                    this.f8346h = (int[]) objArr2[1];
                    objArr2[1] = null;
                    objArr2[0] = null;
                    f8343e--;
                    return;
                }
            }
        }
        this.f8346h = new int[i8];
        this.f8347i = new Object[i8 << 1];
    }

    public void c(int i8) {
        int i9 = this.f8348j;
        int[] iArr = this.f8346h;
        if (iArr.length < i8) {
            Object[] objArr = this.f8347i;
            a(i8);
            if (this.f8348j > 0) {
                System.arraycopy(iArr, 0, this.f8346h, 0, i9);
                System.arraycopy(objArr, 0, this.f8347i, 0, i9 << 1);
            }
            d(iArr, objArr, i9);
        }
        if (this.f8348j != i9) {
            throw new ConcurrentModificationException();
        }
    }

    public void clear() {
        int i8 = this.f8348j;
        if (i8 > 0) {
            int[] iArr = this.f8346h;
            Object[] objArr = this.f8347i;
            this.f8346h = c.f8311a;
            this.f8347i = c.f8313c;
            this.f8348j = 0;
            d(iArr, objArr, i8);
        }
        if (this.f8348j > 0) {
            throw new ConcurrentModificationException();
        }
    }

    public boolean containsKey(Object obj) {
        return f(obj) >= 0;
    }

    public boolean containsValue(Object obj) {
        return h(obj) >= 0;
    }

    public int e(Object obj, int i8) {
        int i9 = this.f8348j;
        if (i9 == 0) {
            return -1;
        }
        int iB = b(this.f8346h, i9, i8);
        if (iB < 0 || obj.equals(this.f8347i[iB << 1])) {
            return iB;
        }
        int i10 = iB + 1;
        while (i10 < i9 && this.f8346h[i10] == i8) {
            if (obj.equals(this.f8347i[i10 << 1])) {
                return i10;
            }
            i10++;
        }
        for (int i11 = iB - 1; i11 >= 0 && this.f8346h[i11] == i8; i11--) {
            if (obj.equals(this.f8347i[i11 << 1])) {
                return i11;
            }
        }
        return ~i10;
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
            for (int i8 = 0; i8 < this.f8348j; i8++) {
                try {
                    K kI = i(i8);
                    V vM = m(i8);
                    Object obj2 = gVar.get(kI);
                    if (vM == null) {
                        if (obj2 != null || !gVar.containsKey(kI)) {
                            return false;
                        }
                    } else if (!vM.equals(obj2)) {
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
            for (int i9 = 0; i9 < this.f8348j; i9++) {
                try {
                    K kI2 = i(i9);
                    V vM2 = m(i9);
                    Object obj3 = map.get(kI2);
                    if (vM2 == null) {
                        if (obj3 != null || !map.containsKey(kI2)) {
                            return false;
                        }
                    } else if (!vM2.equals(obj3)) {
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
        int i8 = this.f8348j;
        if (i8 == 0) {
            return -1;
        }
        int iB = b(this.f8346h, i8, 0);
        if (iB < 0 || this.f8347i[iB << 1] == null) {
            return iB;
        }
        int i9 = iB + 1;
        while (i9 < i8 && this.f8346h[i9] == 0) {
            if (this.f8347i[i9 << 1] == null) {
                return i9;
            }
            i9++;
        }
        for (int i10 = iB - 1; i10 >= 0 && this.f8346h[i10] == 0; i10--) {
            if (this.f8347i[i10 << 1] == null) {
                return i10;
            }
        }
        return ~i9;
    }

    public V get(Object obj) {
        return getOrDefault(obj, null);
    }

    public V getOrDefault(Object obj, V v7) {
        int iF = f(obj);
        return iF >= 0 ? (V) this.f8347i[(iF << 1) + 1] : v7;
    }

    public int h(Object obj) {
        int i8 = this.f8348j * 2;
        Object[] objArr = this.f8347i;
        if (obj == null) {
            for (int i9 = 1; i9 < i8; i9 += 2) {
                if (objArr[i9] == null) {
                    return i9 >> 1;
                }
            }
            return -1;
        }
        for (int i10 = 1; i10 < i8; i10 += 2) {
            if (obj.equals(objArr[i10])) {
                return i10 >> 1;
            }
        }
        return -1;
    }

    public int hashCode() {
        int[] iArr = this.f8346h;
        Object[] objArr = this.f8347i;
        int i8 = this.f8348j;
        int i9 = 1;
        int i10 = 0;
        int iHashCode = 0;
        while (i10 < i8) {
            Object obj = objArr[i9];
            iHashCode += (obj == null ? 0 : obj.hashCode()) ^ iArr[i10];
            i10++;
            i9 += 2;
        }
        return iHashCode;
    }

    public K i(int i8) {
        return (K) this.f8347i[i8 << 1];
    }

    public boolean isEmpty() {
        return this.f8348j <= 0;
    }

    public void j(g<? extends K, ? extends V> gVar) {
        int i8 = gVar.f8348j;
        c(this.f8348j + i8);
        if (this.f8348j != 0) {
            for (int i9 = 0; i9 < i8; i9++) {
                put(gVar.i(i9), gVar.m(i9));
            }
        } else if (i8 > 0) {
            System.arraycopy(gVar.f8346h, 0, this.f8346h, 0, i8);
            System.arraycopy(gVar.f8347i, 0, this.f8347i, 0, i8 << 1);
            this.f8348j = i8;
        }
    }

    public V k(int i8) {
        Object[] objArr = this.f8347i;
        int i9 = i8 << 1;
        V v7 = (V) objArr[i9 + 1];
        int i10 = this.f8348j;
        int i11 = 0;
        if (i10 <= 1) {
            d(this.f8346h, objArr, i10);
            this.f8346h = c.f8311a;
            this.f8347i = c.f8313c;
        } else {
            int i12 = i10 - 1;
            int[] iArr = this.f8346h;
            if (iArr.length <= 8 || i10 >= iArr.length / 3) {
                if (i8 < i12) {
                    int i13 = i8 + 1;
                    int i14 = i12 - i8;
                    System.arraycopy(iArr, i13, iArr, i8, i14);
                    Object[] objArr2 = this.f8347i;
                    System.arraycopy(objArr2, i13 << 1, objArr2, i9, i14 << 1);
                }
                Object[] objArr3 = this.f8347i;
                int i15 = i12 << 1;
                objArr3[i15] = null;
                objArr3[i15 + 1] = null;
            } else {
                a(i10 > 8 ? i10 + (i10 >> 1) : 8);
                if (i10 != this.f8348j) {
                    throw new ConcurrentModificationException();
                }
                if (i8 > 0) {
                    System.arraycopy(iArr, 0, this.f8346h, 0, i8);
                    System.arraycopy(objArr, 0, this.f8347i, 0, i9);
                }
                if (i8 < i12) {
                    int i16 = i8 + 1;
                    int i17 = i12 - i8;
                    System.arraycopy(iArr, i16, this.f8346h, i8, i17);
                    System.arraycopy(objArr, i16 << 1, this.f8347i, i9, i17 << 1);
                }
            }
            i11 = i12;
        }
        if (i10 != this.f8348j) {
            throw new ConcurrentModificationException();
        }
        this.f8348j = i11;
        return v7;
    }

    public V l(int i8, V v7) {
        int i9 = (i8 << 1) + 1;
        Object[] objArr = this.f8347i;
        V v8 = (V) objArr[i9];
        objArr[i9] = v7;
        return v8;
    }

    public V m(int i8) {
        return (V) this.f8347i[(i8 << 1) + 1];
    }

    public V put(K k8, V v7) {
        int i8;
        int iE;
        int i9 = this.f8348j;
        if (k8 == null) {
            iE = g();
            i8 = 0;
        } else {
            int iHashCode = k8.hashCode();
            i8 = iHashCode;
            iE = e(k8, iHashCode);
        }
        if (iE >= 0) {
            int i10 = (iE << 1) + 1;
            Object[] objArr = this.f8347i;
            V v8 = (V) objArr[i10];
            objArr[i10] = v7;
            return v8;
        }
        int i11 = ~iE;
        int[] iArr = this.f8346h;
        if (i9 >= iArr.length) {
            int i12 = 4;
            if (i9 >= 8) {
                i12 = (i9 >> 1) + i9;
            } else if (i9 >= 4) {
                i12 = 8;
            }
            Object[] objArr2 = this.f8347i;
            a(i12);
            if (i9 != this.f8348j) {
                throw new ConcurrentModificationException();
            }
            int[] iArr2 = this.f8346h;
            if (iArr2.length > 0) {
                System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
                System.arraycopy(objArr2, 0, this.f8347i, 0, objArr2.length);
            }
            d(iArr, objArr2, i9);
        }
        if (i11 < i9) {
            int[] iArr3 = this.f8346h;
            int i13 = i11 + 1;
            System.arraycopy(iArr3, i11, iArr3, i13, i9 - i11);
            Object[] objArr3 = this.f8347i;
            System.arraycopy(objArr3, i11 << 1, objArr3, i13 << 1, (this.f8348j - i11) << 1);
        }
        int i14 = this.f8348j;
        if (i9 == i14) {
            int[] iArr4 = this.f8346h;
            if (i11 < iArr4.length) {
                iArr4[i11] = i8;
                Object[] objArr4 = this.f8347i;
                int i15 = i11 << 1;
                objArr4[i15] = k8;
                objArr4[i15 + 1] = v7;
                this.f8348j = i14 + 1;
                return null;
            }
        }
        throw new ConcurrentModificationException();
    }

    public V putIfAbsent(K k8, V v7) {
        V v8 = get(k8);
        return v8 == null ? put(k8, v7) : v8;
    }

    public V remove(Object obj) {
        int iF = f(obj);
        if (iF >= 0) {
            return k(iF);
        }
        return null;
    }

    public V replace(K k8, V v7) {
        int iF = f(k8);
        if (iF >= 0) {
            return l(iF, v7);
        }
        return null;
    }

    public int size() {
        return this.f8348j;
    }

    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f8348j * 28);
        sb.append('{');
        for (int i8 = 0; i8 < this.f8348j; i8++) {
            if (i8 > 0) {
                sb.append(", ");
            }
            K kI = i(i8);
            if (kI != this) {
                sb.append(kI);
            } else {
                sb.append("(this Map)");
            }
            sb.append('=');
            V vM = m(i8);
            if (vM != this) {
                sb.append(vM);
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
        V vM = m(iF);
        if (obj2 != vM && (obj2 == null || !obj2.equals(vM))) {
            return false;
        }
        k(iF);
        return true;
    }

    public boolean replace(K k8, V v7, V v8) {
        int iF = f(k8);
        if (iF < 0) {
            return false;
        }
        V vM = m(iF);
        if (vM != v7 && (v7 == null || !v7.equals(vM))) {
            return false;
        }
        l(iF, v8);
        return true;
    }

    public g(int i8) {
        if (i8 == 0) {
            this.f8346h = c.f8311a;
            this.f8347i = c.f8313c;
        } else {
            a(i8);
        }
        this.f8348j = 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public g(g<K, V> gVar) {
        this();
        if (gVar != 0) {
            j(gVar);
        }
    }
}
