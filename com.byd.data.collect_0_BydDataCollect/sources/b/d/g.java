package b.d;

import java.util.ConcurrentModificationException;
import java.util.Map;

/* JADX INFO: compiled from: SimpleArrayMap.java */
/* JADX INFO: loaded from: classes.dex */
public class g<K, V> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static Object[] f2290b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static int f2291c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    static Object[] f2292d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    static int f2293e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    int[] f2294f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    Object[] f2295g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    int f2296h;

    public g() {
        this.f2294f = c.f2259a;
        this.f2295g = c.f2261c;
        this.f2296h = 0;
    }

    private void a(int i2) {
        if (i2 == 8) {
            synchronized (g.class) {
                Object[] objArr = f2292d;
                if (objArr != null) {
                    this.f2295g = objArr;
                    f2292d = (Object[]) objArr[0];
                    this.f2294f = (int[]) objArr[1];
                    objArr[1] = null;
                    objArr[0] = null;
                    f2293e--;
                    return;
                }
            }
        } else if (i2 == 4) {
            synchronized (g.class) {
                Object[] objArr2 = f2290b;
                if (objArr2 != null) {
                    this.f2295g = objArr2;
                    f2290b = (Object[]) objArr2[0];
                    this.f2294f = (int[]) objArr2[1];
                    objArr2[1] = null;
                    objArr2[0] = null;
                    f2291c--;
                    return;
                }
            }
        }
        this.f2294f = new int[i2];
        this.f2295g = new Object[i2 << 1];
    }

    private static int b(int[] iArr, int i2, int i3) {
        try {
            return c.a(iArr, i2, i3);
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new ConcurrentModificationException();
        }
    }

    private static void d(int[] iArr, Object[] objArr, int i2) {
        if (iArr.length == 8) {
            synchronized (g.class) {
                if (f2293e < 10) {
                    objArr[0] = f2292d;
                    objArr[1] = iArr;
                    for (int i3 = (i2 << 1) - 1; i3 >= 2; i3--) {
                        objArr[i3] = null;
                    }
                    f2292d = objArr;
                    f2293e++;
                }
            }
            return;
        }
        if (iArr.length == 4) {
            synchronized (g.class) {
                if (f2291c < 10) {
                    objArr[0] = f2290b;
                    objArr[1] = iArr;
                    for (int i4 = (i2 << 1) - 1; i4 >= 2; i4--) {
                        objArr[i4] = null;
                    }
                    f2290b = objArr;
                    f2291c++;
                }
            }
        }
    }

    public void c(int i2) {
        int i3 = this.f2296h;
        int[] iArr = this.f2294f;
        if (iArr.length < i2) {
            Object[] objArr = this.f2295g;
            a(i2);
            if (this.f2296h > 0) {
                System.arraycopy(iArr, 0, this.f2294f, 0, i3);
                System.arraycopy(objArr, 0, this.f2295g, 0, i3 << 1);
            }
            d(iArr, objArr, i3);
        }
        if (this.f2296h != i3) {
            throw new ConcurrentModificationException();
        }
    }

    public void clear() {
        int i2 = this.f2296h;
        if (i2 > 0) {
            int[] iArr = this.f2294f;
            Object[] objArr = this.f2295g;
            this.f2294f = c.f2259a;
            this.f2295g = c.f2261c;
            this.f2296h = 0;
            d(iArr, objArr, i2);
        }
        if (this.f2296h > 0) {
            throw new ConcurrentModificationException();
        }
    }

    public boolean containsKey(Object obj) {
        return f(obj) >= 0;
    }

    public boolean containsValue(Object obj) {
        return h(obj) >= 0;
    }

    int e(Object obj, int i2) {
        int i3 = this.f2296h;
        if (i3 == 0) {
            return -1;
        }
        int iB = b(this.f2294f, i3, i2);
        if (iB < 0 || obj.equals(this.f2295g[iB << 1])) {
            return iB;
        }
        int i4 = iB + 1;
        while (i4 < i3 && this.f2294f[i4] == i2) {
            if (obj.equals(this.f2295g[i4 << 1])) {
                return i4;
            }
            i4++;
        }
        for (int i5 = iB - 1; i5 >= 0 && this.f2294f[i5] == i2; i5--) {
            if (obj.equals(this.f2295g[i5 << 1])) {
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
            for (int i2 = 0; i2 < this.f2296h; i2++) {
                try {
                    K kI = i(i2);
                    V vM = m(i2);
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
            for (int i3 = 0; i3 < this.f2296h; i3++) {
                try {
                    K kI2 = i(i3);
                    V vM2 = m(i3);
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

    int g() {
        int i2 = this.f2296h;
        if (i2 == 0) {
            return -1;
        }
        int iB = b(this.f2294f, i2, 0);
        if (iB < 0 || this.f2295g[iB << 1] == null) {
            return iB;
        }
        int i3 = iB + 1;
        while (i3 < i2 && this.f2294f[i3] == 0) {
            if (this.f2295g[i3 << 1] == null) {
                return i3;
            }
            i3++;
        }
        for (int i4 = iB - 1; i4 >= 0 && this.f2294f[i4] == 0; i4--) {
            if (this.f2295g[i4 << 1] == null) {
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
        return iF >= 0 ? (V) this.f2295g[(iF << 1) + 1] : v;
    }

    int h(Object obj) {
        int i2 = this.f2296h * 2;
        Object[] objArr = this.f2295g;
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
        int[] iArr = this.f2294f;
        Object[] objArr = this.f2295g;
        int i2 = this.f2296h;
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
        return (K) this.f2295g[i2 << 1];
    }

    public boolean isEmpty() {
        return this.f2296h <= 0;
    }

    public void j(g<? extends K, ? extends V> gVar) {
        int i2 = gVar.f2296h;
        c(this.f2296h + i2);
        if (this.f2296h != 0) {
            for (int i3 = 0; i3 < i2; i3++) {
                put(gVar.i(i3), gVar.m(i3));
            }
        } else if (i2 > 0) {
            System.arraycopy(gVar.f2294f, 0, this.f2294f, 0, i2);
            System.arraycopy(gVar.f2295g, 0, this.f2295g, 0, i2 << 1);
            this.f2296h = i2;
        }
    }

    public V k(int i2) {
        Object[] objArr = this.f2295g;
        int i3 = i2 << 1;
        V v = (V) objArr[i3 + 1];
        int i4 = this.f2296h;
        int i5 = 0;
        if (i4 <= 1) {
            d(this.f2294f, objArr, i4);
            this.f2294f = c.f2259a;
            this.f2295g = c.f2261c;
        } else {
            int i6 = i4 - 1;
            int[] iArr = this.f2294f;
            if (iArr.length <= 8 || i4 >= iArr.length / 3) {
                if (i2 < i6) {
                    int i7 = i2 + 1;
                    int i8 = i6 - i2;
                    System.arraycopy(iArr, i7, iArr, i2, i8);
                    Object[] objArr2 = this.f2295g;
                    System.arraycopy(objArr2, i7 << 1, objArr2, i3, i8 << 1);
                }
                Object[] objArr3 = this.f2295g;
                int i9 = i6 << 1;
                objArr3[i9] = null;
                objArr3[i9 + 1] = null;
            } else {
                a(i4 > 8 ? i4 + (i4 >> 1) : 8);
                if (i4 != this.f2296h) {
                    throw new ConcurrentModificationException();
                }
                if (i2 > 0) {
                    System.arraycopy(iArr, 0, this.f2294f, 0, i2);
                    System.arraycopy(objArr, 0, this.f2295g, 0, i3);
                }
                if (i2 < i6) {
                    int i10 = i2 + 1;
                    int i11 = i6 - i2;
                    System.arraycopy(iArr, i10, this.f2294f, i2, i11);
                    System.arraycopy(objArr, i10 << 1, this.f2295g, i3, i11 << 1);
                }
            }
            i5 = i6;
        }
        if (i4 != this.f2296h) {
            throw new ConcurrentModificationException();
        }
        this.f2296h = i5;
        return v;
    }

    public V l(int i2, V v) {
        int i3 = (i2 << 1) + 1;
        Object[] objArr = this.f2295g;
        V v2 = (V) objArr[i3];
        objArr[i3] = v;
        return v2;
    }

    public V m(int i2) {
        return (V) this.f2295g[(i2 << 1) + 1];
    }

    public V put(K k, V v) {
        int i2;
        int iE;
        int i3 = this.f2296h;
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
            Object[] objArr = this.f2295g;
            V v2 = (V) objArr[i4];
            objArr[i4] = v;
            return v2;
        }
        int i5 = ~iE;
        int[] iArr = this.f2294f;
        if (i3 >= iArr.length) {
            int i6 = 4;
            if (i3 >= 8) {
                i6 = (i3 >> 1) + i3;
            } else if (i3 >= 4) {
                i6 = 8;
            }
            Object[] objArr2 = this.f2295g;
            a(i6);
            if (i3 != this.f2296h) {
                throw new ConcurrentModificationException();
            }
            int[] iArr2 = this.f2294f;
            if (iArr2.length > 0) {
                System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
                System.arraycopy(objArr2, 0, this.f2295g, 0, objArr2.length);
            }
            d(iArr, objArr2, i3);
        }
        if (i5 < i3) {
            int[] iArr3 = this.f2294f;
            int i7 = i5 + 1;
            System.arraycopy(iArr3, i5, iArr3, i7, i3 - i5);
            Object[] objArr3 = this.f2295g;
            System.arraycopy(objArr3, i5 << 1, objArr3, i7 << 1, (this.f2296h - i5) << 1);
        }
        int i8 = this.f2296h;
        if (i3 == i8) {
            int[] iArr4 = this.f2294f;
            if (i5 < iArr4.length) {
                iArr4[i5] = i2;
                Object[] objArr4 = this.f2295g;
                int i9 = i5 << 1;
                objArr4[i9] = k;
                objArr4[i9 + 1] = v;
                this.f2296h = i8 + 1;
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
            return k(iF);
        }
        return null;
    }

    public V replace(K k, V v) {
        int iF = f(k);
        if (iF >= 0) {
            return l(iF, v);
        }
        return null;
    }

    public int size() {
        return this.f2296h;
    }

    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f2296h * 28);
        sb.append('{');
        for (int i2 = 0; i2 < this.f2296h; i2++) {
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
            V vM = m(i2);
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

    public boolean replace(K k, V v, V v2) {
        int iF = f(k);
        if (iF < 0) {
            return false;
        }
        V vM = m(iF);
        if (vM != v && (v == null || !v.equals(vM))) {
            return false;
        }
        l(iF, v2);
        return true;
    }

    public g(int i2) {
        if (i2 == 0) {
            this.f2294f = c.f2259a;
            this.f2295g = c.f2261c;
        } else {
            a(i2);
        }
        this.f2296h = 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public g(g<K, V> gVar) {
        this();
        if (gVar != 0) {
            j(gVar);
        }
    }
}
