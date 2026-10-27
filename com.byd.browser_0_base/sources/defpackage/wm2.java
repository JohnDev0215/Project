package defpackage;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Map;
import kotlin.jvm.internal.SourceDebugExtension;

/* JADX INFO: compiled from: SimpleArrayMap.kt */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nSimpleArrayMap.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SimpleArrayMap.kt\nandroidx/collection/SimpleArrayMap\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,763:1\n291#1,5:764\n291#1,5:769\n1#2:774\n*S KotlinDebug\n*F\n+ 1 SimpleArrayMap.kt\nandroidx/collection/SimpleArrayMap\n*L\n270#1:764,5\n285#1:769,5\n*E\n"})
public class wm2<K, V> {
    public int[] e;
    public Object[] f;
    public int g;

    public wm2() {
        this(0);
    }

    private final int c(int i, Object obj) {
        int i2 = this.g;
        if (i2 == 0) {
            return -1;
        }
        int iJ = a8.j(i2, i, this.e);
        if (iJ < 0 || i21.a(obj, this.f[iJ << 1])) {
            return iJ;
        }
        int i3 = iJ + 1;
        while (i3 < i2 && this.e[i3] == i) {
            if (i21.a(obj, this.f[i3 << 1])) {
                return i3;
            }
            i3++;
        }
        for (int i4 = iJ - 1; i4 >= 0 && this.e[i4] == i; i4--) {
            if (i21.a(obj, this.f[i4 << 1])) {
                return i4;
            }
        }
        return ~i3;
    }

    public final int a(V v) {
        int i = this.g * 2;
        Object[] objArr = this.f;
        if (v == null) {
            for (int i2 = 1; i2 < i; i2 += 2) {
                if (objArr[i2] == null) {
                    return i2 >> 1;
                }
            }
            return -1;
        }
        for (int i3 = 1; i3 < i; i3 += 2) {
            if (i21.a(v, objArr[i3])) {
                return i3 >> 1;
            }
        }
        return -1;
    }

    public final void b(int i) {
        int i2 = this.g;
        int[] iArr = this.e;
        if (iArr.length < i) {
            int[] iArrCopyOf = Arrays.copyOf(iArr, i);
            i21.d(iArrCopyOf, "copyOf(this, newSize)");
            this.e = iArrCopyOf;
            Object[] objArrCopyOf = Arrays.copyOf(this.f, i * 2);
            i21.d(objArrCopyOf, "copyOf(this, newSize)");
            this.f = objArrCopyOf;
        }
        if (this.g != i2) {
            throw new ConcurrentModificationException();
        }
    }

    public final void clear() {
        if (this.g > 0) {
            this.e = a8.k;
            this.f = a8.l;
            this.g = 0;
        }
        if (this.g > 0) {
            throw new ConcurrentModificationException();
        }
    }

    public boolean containsKey(K k) {
        return d(k) >= 0;
    }

    public boolean containsValue(V v) {
        return a(v) >= 0;
    }

    public final int d(K k) {
        return k == null ? e() : c(k.hashCode(), k);
    }

    public final int e() {
        int i = this.g;
        if (i == 0) {
            return -1;
        }
        int iJ = a8.j(i, 0, this.e);
        if (iJ < 0 || this.f[iJ << 1] == null) {
            return iJ;
        }
        int i2 = iJ + 1;
        while (i2 < i && this.e[i2] == 0) {
            if (this.f[i2 << 1] == null) {
                return i2;
            }
            i2++;
        }
        for (int i3 = iJ - 1; i3 >= 0 && this.e[i3] == 0; i3--) {
            if (this.f[i3 << 1] == null) {
                return i3;
            }
        }
        return ~i2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        try {
            if (obj instanceof wm2) {
                int i = this.g;
                if (i != ((wm2) obj).g) {
                    return false;
                }
                wm2 wm2Var = (wm2) obj;
                for (int i2 = 0; i2 < i; i2++) {
                    K kF = f(i2);
                    V vI = i(i2);
                    Object obj2 = wm2Var.get(kF);
                    if (vI == null) {
                        if (obj2 != null || !wm2Var.containsKey(kF)) {
                            return false;
                        }
                    } else if (!i21.a(vI, obj2)) {
                        return false;
                    }
                }
                return true;
            }
            if (!(obj instanceof Map) || this.g != ((Map) obj).size()) {
                return false;
            }
            int i3 = this.g;
            for (int i4 = 0; i4 < i3; i4++) {
                K kF2 = f(i4);
                V vI2 = i(i4);
                Object obj3 = ((Map) obj).get(kF2);
                if (vI2 == null) {
                    if (obj3 != null || !((Map) obj).containsKey(kF2)) {
                        return false;
                    }
                } else if (!i21.a(vI2, obj3)) {
                    return false;
                }
            }
            return true;
        } catch (ClassCastException | NullPointerException unused) {
        }
        return false;
    }

    public final K f(int i) {
        boolean z = false;
        if (i >= 0 && i < this.g) {
            z = true;
        }
        if (z) {
            return (K) this.f[i << 1];
        }
        throw new IllegalArgumentException(gq2.e("Expected index to be within 0..size()-1, but was ", i).toString());
    }

    public final V g(int i) {
        if (!(i >= 0 && i < this.g)) {
            throw new IllegalArgumentException(gq2.e("Expected index to be within 0..size()-1, but was ", i).toString());
        }
        Object[] objArr = this.f;
        int i2 = i << 1;
        V v = (V) objArr[i2 + 1];
        int i3 = this.g;
        if (i3 <= 1) {
            clear();
        } else {
            int i4 = i3 - 1;
            int[] iArr = this.e;
            if (iArr.length <= 8 || i3 >= iArr.length / 3) {
                if (i < i4) {
                    int i5 = i + 1;
                    int i6 = i4 + 1;
                    r8.p0(i, i5, i6, iArr, iArr);
                    Object[] objArr2 = this.f;
                    r8.q0(i2, i5 << 1, i6 << 1, objArr2, objArr2);
                }
                Object[] objArr3 = this.f;
                int i7 = i4 << 1;
                objArr3[i7] = null;
                objArr3[i7 + 1] = null;
            } else {
                int i8 = i3 > 8 ? i3 + (i3 >> 1) : 8;
                int[] iArrCopyOf = Arrays.copyOf(iArr, i8);
                i21.d(iArrCopyOf, "copyOf(this, newSize)");
                this.e = iArrCopyOf;
                Object[] objArrCopyOf = Arrays.copyOf(this.f, i8 << 1);
                i21.d(objArrCopyOf, "copyOf(this, newSize)");
                this.f = objArrCopyOf;
                if (i3 != this.g) {
                    throw new ConcurrentModificationException();
                }
                if (i > 0) {
                    r8.p0(0, 0, i, iArr, this.e);
                    r8.q0(0, 0, i2, objArr, this.f);
                }
                if (i < i4) {
                    int i9 = i + 1;
                    int i10 = i4 + 1;
                    r8.p0(i, i9, i10, iArr, this.e);
                    r8.q0(i2, i9 << 1, i10 << 1, objArr, this.f);
                }
            }
            if (i3 != this.g) {
                throw new ConcurrentModificationException();
            }
            this.g = i4;
        }
        return v;
    }

    public V get(K k) {
        int iD = d(k);
        if (iD >= 0) {
            return (V) this.f[(iD << 1) + 1];
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final V getOrDefault(Object obj, V v) {
        int iD = d(obj);
        return iD >= 0 ? (V) this.f[(iD << 1) + 1] : v;
    }

    public final V h(int i, V v) {
        boolean z = false;
        if (i >= 0 && i < this.g) {
            z = true;
        }
        if (!z) {
            throw new IllegalArgumentException(gq2.e("Expected index to be within 0..size()-1, but was ", i).toString());
        }
        int i2 = (i << 1) + 1;
        Object[] objArr = this.f;
        V v2 = (V) objArr[i2];
        objArr[i2] = v;
        return v2;
    }

    public final int hashCode() {
        int[] iArr = this.e;
        Object[] objArr = this.f;
        int i = this.g;
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

    public final V i(int i) {
        boolean z = false;
        if (i >= 0 && i < this.g) {
            z = true;
        }
        if (z) {
            return (V) this.f[(i << 1) + 1];
        }
        throw new IllegalArgumentException(gq2.e("Expected index to be within 0..size()-1, but was ", i).toString());
    }

    public final boolean isEmpty() {
        return this.g <= 0;
    }

    public final V put(K k, V v) {
        int i = this.g;
        int iHashCode = k != null ? k.hashCode() : 0;
        int iC = k != null ? c(iHashCode, k) : e();
        if (iC >= 0) {
            int i2 = (iC << 1) + 1;
            Object[] objArr = this.f;
            V v2 = (V) objArr[i2];
            objArr[i2] = v;
            return v2;
        }
        int i3 = ~iC;
        int[] iArr = this.e;
        if (i >= iArr.length) {
            int i4 = 8;
            if (i >= 8) {
                i4 = (i >> 1) + i;
            } else if (i < 4) {
                i4 = 4;
            }
            int[] iArrCopyOf = Arrays.copyOf(iArr, i4);
            i21.d(iArrCopyOf, "copyOf(this, newSize)");
            this.e = iArrCopyOf;
            Object[] objArrCopyOf = Arrays.copyOf(this.f, i4 << 1);
            i21.d(objArrCopyOf, "copyOf(this, newSize)");
            this.f = objArrCopyOf;
            if (i != this.g) {
                throw new ConcurrentModificationException();
            }
        }
        if (i3 < i) {
            int[] iArr2 = this.e;
            int i5 = i3 + 1;
            r8.p0(i5, i3, i, iArr2, iArr2);
            Object[] objArr2 = this.f;
            r8.q0(i5 << 1, i3 << 1, this.g << 1, objArr2, objArr2);
        }
        int i6 = this.g;
        if (i == i6) {
            int[] iArr3 = this.e;
            if (i3 < iArr3.length) {
                iArr3[i3] = iHashCode;
                Object[] objArr3 = this.f;
                int i7 = i3 << 1;
                objArr3[i7] = k;
                objArr3[i7 + 1] = v;
                this.g = i6 + 1;
                return null;
            }
        }
        throw new ConcurrentModificationException();
    }

    public final V putIfAbsent(K k, V v) {
        V v2 = get(k);
        return v2 == null ? put(k, v) : v2;
    }

    public V remove(K k) {
        int iD = d(k);
        if (iD >= 0) {
            return g(iD);
        }
        return null;
    }

    public final V replace(K k, V v) {
        int iD = d(k);
        if (iD >= 0) {
            return h(iD, v);
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
        int i = this.g;
        for (int i2 = 0; i2 < i; i2++) {
            if (i2 > 0) {
                sb.append(", ");
            }
            K kF = f(i2);
            if (kF != sb) {
                sb.append(kF);
            } else {
                sb.append("(this Map)");
            }
            sb.append('=');
            V vI = i(i2);
            if (vI != sb) {
                sb.append(vI);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        String string = sb.toString();
        i21.d(string, "StringBuilder(capacity).…builderAction).toString()");
        return string;
    }

    public wm2(int i) {
        this.e = i == 0 ? a8.k : new int[i];
        this.f = i == 0 ? a8.l : new Object[i << 1];
    }

    public final boolean remove(K k, V v) {
        int iD = d(k);
        if (iD < 0 || !i21.a(v, i(iD))) {
            return false;
        }
        g(iD);
        return true;
    }

    public final boolean replace(K k, V v, V v2) {
        int iD = d(k);
        if (iD < 0 || !i21.a(v, i(iD))) {
            return false;
        }
        h(iD, v2);
        return true;
    }
}
