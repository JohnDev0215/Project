package n;

import java.util.ConcurrentModificationException;
import java.util.Map;

/* JADX INFO: compiled from: SimpleArrayMap.java */
/* JADX INFO: loaded from: classes.dex */
public class g<K, V> {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    static Object[] f8479i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    static int f8480j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    static Object[] f8481k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    static int f8482l;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    int[] f8483e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    Object[] f8484g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    int f8485h;

    public g() {
        this.f8483e = c.f8448a;
        this.f8484g = c.f8450c;
        this.f8485h = 0;
    }

    private void c(int i7) {
        if (i7 == 8) {
            synchronized (g.class) {
                Object[] objArr = f8481k;
                if (objArr != null) {
                    this.f8484g = objArr;
                    f8481k = (Object[]) objArr[0];
                    this.f8483e = (int[]) objArr[1];
                    objArr[1] = null;
                    objArr[0] = null;
                    f8482l--;
                    return;
                }
            }
        } else if (i7 == 4) {
            synchronized (g.class) {
                Object[] objArr2 = f8479i;
                if (objArr2 != null) {
                    this.f8484g = objArr2;
                    f8479i = (Object[]) objArr2[0];
                    this.f8483e = (int[]) objArr2[1];
                    objArr2[1] = null;
                    objArr2[0] = null;
                    f8480j--;
                    return;
                }
            }
        }
        this.f8483e = new int[i7];
        this.f8484g = new Object[i7 << 1];
    }

    private static int d(int[] iArr, int i7, int i8) {
        try {
            return c.a(iArr, i7, i8);
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new ConcurrentModificationException();
        }
    }

    private static void f(int[] iArr, Object[] objArr, int i7) {
        if (iArr.length == 8) {
            synchronized (g.class) {
                if (f8482l < 10) {
                    objArr[0] = f8481k;
                    objArr[1] = iArr;
                    for (int i8 = (i7 << 1) - 1; i8 >= 2; i8--) {
                        objArr[i8] = null;
                    }
                    f8481k = objArr;
                    f8482l++;
                }
            }
            return;
        }
        if (iArr.length == 4) {
            synchronized (g.class) {
                if (f8480j < 10) {
                    objArr[0] = f8479i;
                    objArr[1] = iArr;
                    for (int i9 = (i7 << 1) - 1; i9 >= 2; i9--) {
                        objArr[i9] = null;
                    }
                    f8479i = objArr;
                    f8480j++;
                }
            }
        }
    }

    public void clear() {
        int i7 = this.f8485h;
        if (i7 > 0) {
            int[] iArr = this.f8483e;
            Object[] objArr = this.f8484g;
            this.f8483e = c.f8448a;
            this.f8484g = c.f8450c;
            this.f8485h = 0;
            f(iArr, objArr, i7);
        }
        if (this.f8485h > 0) {
            throw new ConcurrentModificationException();
        }
    }

    public boolean containsKey(Object obj) {
        return h(obj) >= 0;
    }

    public boolean containsValue(Object obj) {
        return j(obj) >= 0;
    }

    public void e(int i7) {
        int i8 = this.f8485h;
        int[] iArr = this.f8483e;
        if (iArr.length < i7) {
            Object[] objArr = this.f8484g;
            c(i7);
            if (this.f8485h > 0) {
                System.arraycopy(iArr, 0, this.f8483e, 0, i8);
                System.arraycopy(objArr, 0, this.f8484g, 0, i8 << 1);
            }
            f(iArr, objArr, i8);
        }
        if (this.f8485h != i8) {
            throw new ConcurrentModificationException();
        }
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
            for (int i7 = 0; i7 < this.f8485h; i7++) {
                try {
                    K k7 = k(i7);
                    V vO = o(i7);
                    Object obj2 = gVar.get(k7);
                    if (vO == null) {
                        if (obj2 != null || !gVar.containsKey(k7)) {
                            return false;
                        }
                    } else if (!vO.equals(obj2)) {
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
            for (int i8 = 0; i8 < this.f8485h; i8++) {
                try {
                    K k8 = k(i8);
                    V vO2 = o(i8);
                    Object obj3 = map.get(k8);
                    if (vO2 == null) {
                        if (obj3 != null || !map.containsKey(k8)) {
                            return false;
                        }
                    } else if (!vO2.equals(obj3)) {
                        return false;
                    }
                } catch (ClassCastException | NullPointerException unused2) {
                }
            }
            return true;
        }
        return false;
    }

    int g(Object obj, int i7) {
        int i8 = this.f8485h;
        if (i8 == 0) {
            return -1;
        }
        int iD = d(this.f8483e, i8, i7);
        if (iD < 0 || obj.equals(this.f8484g[iD << 1])) {
            return iD;
        }
        int i9 = iD + 1;
        while (i9 < i8 && this.f8483e[i9] == i7) {
            if (obj.equals(this.f8484g[i9 << 1])) {
                return i9;
            }
            i9++;
        }
        for (int i10 = iD - 1; i10 >= 0 && this.f8483e[i10] == i7; i10--) {
            if (obj.equals(this.f8484g[i10 << 1])) {
                return i10;
            }
        }
        return ~i9;
    }

    public V get(Object obj) {
        return getOrDefault(obj, null);
    }

    public V getOrDefault(Object obj, V v6) {
        int iH = h(obj);
        return iH >= 0 ? (V) this.f8484g[(iH << 1) + 1] : v6;
    }

    public int h(Object obj) {
        return obj == null ? i() : g(obj, obj.hashCode());
    }

    public int hashCode() {
        int[] iArr = this.f8483e;
        Object[] objArr = this.f8484g;
        int i7 = this.f8485h;
        int i8 = 1;
        int i9 = 0;
        int iHashCode = 0;
        while (i9 < i7) {
            Object obj = objArr[i8];
            iHashCode += (obj == null ? 0 : obj.hashCode()) ^ iArr[i9];
            i9++;
            i8 += 2;
        }
        return iHashCode;
    }

    int i() {
        int i7 = this.f8485h;
        if (i7 == 0) {
            return -1;
        }
        int iD = d(this.f8483e, i7, 0);
        if (iD < 0 || this.f8484g[iD << 1] == null) {
            return iD;
        }
        int i8 = iD + 1;
        while (i8 < i7 && this.f8483e[i8] == 0) {
            if (this.f8484g[i8 << 1] == null) {
                return i8;
            }
            i8++;
        }
        for (int i9 = iD - 1; i9 >= 0 && this.f8483e[i9] == 0; i9--) {
            if (this.f8484g[i9 << 1] == null) {
                return i9;
            }
        }
        return ~i8;
    }

    public boolean isEmpty() {
        return this.f8485h <= 0;
    }

    int j(Object obj) {
        int i7 = this.f8485h * 2;
        Object[] objArr = this.f8484g;
        if (obj == null) {
            for (int i8 = 1; i8 < i7; i8 += 2) {
                if (objArr[i8] == null) {
                    return i8 >> 1;
                }
            }
            return -1;
        }
        for (int i9 = 1; i9 < i7; i9 += 2) {
            if (obj.equals(objArr[i9])) {
                return i9 >> 1;
            }
        }
        return -1;
    }

    public K k(int i7) {
        return (K) this.f8484g[i7 << 1];
    }

    public void l(g<? extends K, ? extends V> gVar) {
        int i7 = gVar.f8485h;
        e(this.f8485h + i7);
        if (this.f8485h != 0) {
            for (int i8 = 0; i8 < i7; i8++) {
                put(gVar.k(i8), gVar.o(i8));
            }
        } else if (i7 > 0) {
            System.arraycopy(gVar.f8483e, 0, this.f8483e, 0, i7);
            System.arraycopy(gVar.f8484g, 0, this.f8484g, 0, i7 << 1);
            this.f8485h = i7;
        }
    }

    public V m(int i7) {
        Object[] objArr = this.f8484g;
        int i8 = i7 << 1;
        V v6 = (V) objArr[i8 + 1];
        int i9 = this.f8485h;
        int i10 = 0;
        if (i9 <= 1) {
            f(this.f8483e, objArr, i9);
            this.f8483e = c.f8448a;
            this.f8484g = c.f8450c;
        } else {
            int i11 = i9 - 1;
            int[] iArr = this.f8483e;
            if (iArr.length <= 8 || i9 >= iArr.length / 3) {
                if (i7 < i11) {
                    int i12 = i7 + 1;
                    int i13 = i11 - i7;
                    System.arraycopy(iArr, i12, iArr, i7, i13);
                    Object[] objArr2 = this.f8484g;
                    System.arraycopy(objArr2, i12 << 1, objArr2, i8, i13 << 1);
                }
                Object[] objArr3 = this.f8484g;
                int i14 = i11 << 1;
                objArr3[i14] = null;
                objArr3[i14 + 1] = null;
            } else {
                c(i9 > 8 ? i9 + (i9 >> 1) : 8);
                if (i9 != this.f8485h) {
                    throw new ConcurrentModificationException();
                }
                if (i7 > 0) {
                    System.arraycopy(iArr, 0, this.f8483e, 0, i7);
                    System.arraycopy(objArr, 0, this.f8484g, 0, i8);
                }
                if (i7 < i11) {
                    int i15 = i7 + 1;
                    int i16 = i11 - i7;
                    System.arraycopy(iArr, i15, this.f8483e, i7, i16);
                    System.arraycopy(objArr, i15 << 1, this.f8484g, i8, i16 << 1);
                }
            }
            i10 = i11;
        }
        if (i9 != this.f8485h) {
            throw new ConcurrentModificationException();
        }
        this.f8485h = i10;
        return v6;
    }

    public V n(int i7, V v6) {
        int i8 = (i7 << 1) + 1;
        Object[] objArr = this.f8484g;
        V v7 = (V) objArr[i8];
        objArr[i8] = v6;
        return v7;
    }

    public V o(int i7) {
        return (V) this.f8484g[(i7 << 1) + 1];
    }

    public V put(K k7, V v6) {
        int i7;
        int iG;
        int i8 = this.f8485h;
        if (k7 == null) {
            iG = i();
            i7 = 0;
        } else {
            int iHashCode = k7.hashCode();
            i7 = iHashCode;
            iG = g(k7, iHashCode);
        }
        if (iG >= 0) {
            int i9 = (iG << 1) + 1;
            Object[] objArr = this.f8484g;
            V v7 = (V) objArr[i9];
            objArr[i9] = v6;
            return v7;
        }
        int i10 = ~iG;
        int[] iArr = this.f8483e;
        if (i8 >= iArr.length) {
            int i11 = 4;
            if (i8 >= 8) {
                i11 = (i8 >> 1) + i8;
            } else if (i8 >= 4) {
                i11 = 8;
            }
            Object[] objArr2 = this.f8484g;
            c(i11);
            if (i8 != this.f8485h) {
                throw new ConcurrentModificationException();
            }
            int[] iArr2 = this.f8483e;
            if (iArr2.length > 0) {
                System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
                System.arraycopy(objArr2, 0, this.f8484g, 0, objArr2.length);
            }
            f(iArr, objArr2, i8);
        }
        if (i10 < i8) {
            int[] iArr3 = this.f8483e;
            int i12 = i10 + 1;
            System.arraycopy(iArr3, i10, iArr3, i12, i8 - i10);
            Object[] objArr3 = this.f8484g;
            System.arraycopy(objArr3, i10 << 1, objArr3, i12 << 1, (this.f8485h - i10) << 1);
        }
        int i13 = this.f8485h;
        if (i8 == i13) {
            int[] iArr4 = this.f8483e;
            if (i10 < iArr4.length) {
                iArr4[i10] = i7;
                Object[] objArr4 = this.f8484g;
                int i14 = i10 << 1;
                objArr4[i14] = k7;
                objArr4[i14 + 1] = v6;
                this.f8485h = i13 + 1;
                return null;
            }
        }
        throw new ConcurrentModificationException();
    }

    public V putIfAbsent(K k7, V v6) {
        V v7 = get(k7);
        return v7 == null ? put(k7, v6) : v7;
    }

    public V remove(Object obj) {
        int iH = h(obj);
        if (iH >= 0) {
            return m(iH);
        }
        return null;
    }

    public V replace(K k7, V v6) {
        int iH = h(k7);
        if (iH >= 0) {
            return n(iH, v6);
        }
        return null;
    }

    public int size() {
        return this.f8485h;
    }

    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f8485h * 28);
        sb.append('{');
        for (int i7 = 0; i7 < this.f8485h; i7++) {
            if (i7 > 0) {
                sb.append(", ");
            }
            K k7 = k(i7);
            if (k7 != this) {
                sb.append(k7);
            } else {
                sb.append("(this Map)");
            }
            sb.append('=');
            V vO = o(i7);
            if (vO != this) {
                sb.append(vO);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public boolean remove(Object obj, Object obj2) {
        int iH = h(obj);
        if (iH < 0) {
            return false;
        }
        V vO = o(iH);
        if (obj2 != vO && (obj2 == null || !obj2.equals(vO))) {
            return false;
        }
        m(iH);
        return true;
    }

    public boolean replace(K k7, V v6, V v7) {
        int iH = h(k7);
        if (iH < 0) {
            return false;
        }
        V vO = o(iH);
        if (vO != v6 && (v6 == null || !v6.equals(vO))) {
            return false;
        }
        n(iH, v7);
        return true;
    }

    public g(int i7) {
        if (i7 == 0) {
            this.f8483e = c.f8448a;
            this.f8484g = c.f8450c;
        } else {
            c(i7);
        }
        this.f8485h = 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public g(g<K, V> gVar) {
        this();
        if (gVar != 0) {
            l(gVar);
        }
    }
}
