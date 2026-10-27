package kotlin.collections.builders;

import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import p.f60;
import p.i51;
import p.i81;
import p.ie1;
import p.le1;
import p.me1;
import p.ne1;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class MapBuilder<K, V> implements Map<K, V>, Serializable, i81 {
    private le1 entriesView;
    private int[] hashArray;
    private int hashShift;
    private boolean isReadOnly;
    private K[] keysArray;
    private me1 keysView;
    private int length;
    private int maxProbeDistance;
    private int[] presenceArray;
    private int size;
    private V[] valuesArray;
    private ne1 valuesView;

    public MapBuilder() {
        this(8);
    }

    private final Object writeReplace() throws NotSerializableException {
        if (this.isReadOnly) {
            return new SerializedMap(this);
        }
        throw new NotSerializableException("The map cannot be serialized while it is being built.");
    }

    @Override // java.util.Map
    public final void clear() {
        int i;
        h();
        boolean z = true;
        i51 i51Var = new i51(0, this.length - 1, 1);
        int i2 = i51Var.e;
        int i3 = i51Var.k;
        if (i3 <= 0 ? i2 > 0 : i2 < 0) {
            z = false;
        }
        int i4 = z ? 0 : i2;
        while (z) {
            if (i4 != i2) {
                i = i4 + i3;
            } else {
                if (!z) {
                    throw new NoSuchElementException();
                }
                z = false;
                i = i4;
            }
            int[] iArr = this.presenceArray;
            int i5 = iArr[i4];
            if (i5 >= 0) {
                this.hashArray[i5] = 0;
                iArr[i4] = -1;
            }
            i4 = i;
        }
        f60.E(0, this.length, this.keysArray);
        V[] vArr = this.valuesArray;
        if (vArr != null) {
            f60.E(0, this.length, vArr);
        }
        this.size = 0;
        this.length = 0;
    }

    @Override // java.util.Map
    public final boolean containsKey(Object obj) {
        return l(obj) >= 0;
    }

    @Override // java.util.Map
    public final boolean containsValue(Object obj) {
        return m(obj) >= 0;
    }

    public final int e(Object obj) {
        h();
        while (true) {
            int iN = n(obj);
            int i = this.maxProbeDistance * 2;
            int length = this.hashArray.length / 2;
            if (i > length) {
                i = length;
            }
            int i2 = 0;
            while (true) {
                int[] iArr = this.hashArray;
                int i3 = iArr[iN];
                if (i3 <= 0) {
                    int i4 = this.length;
                    Object[] objArr = (K[]) this.keysArray;
                    if (i4 < objArr.length) {
                        int i5 = i4 + 1;
                        this.length = i5;
                        objArr[i4] = obj;
                        this.presenceArray[i4] = iN;
                        iArr[iN] = i5;
                        this.size++;
                        if (i2 > this.maxProbeDistance) {
                            this.maxProbeDistance = i2;
                        }
                        return i4;
                    }
                    k(1);
                } else {
                    if (v03.b(this.keysArray[i3 - 1], obj)) {
                        return -i3;
                    }
                    i2++;
                    if (i2 > i) {
                        p(this.hashArray.length * 2);
                        break;
                    }
                    iN = iN == 0 ? this.hashArray.length - 1 : iN - 1;
                }
            }
        }
    }

    @Override // java.util.Map
    public final Set entrySet() {
        le1 le1Var = this.entriesView;
        if (le1Var != null) {
            return le1Var;
        }
        le1 le1Var2 = new le1(this);
        this.entriesView = le1Var2;
        return le1Var2;
    }

    @Override // java.util.Map
    public final boolean equals(Object obj) {
        if (obj != this) {
            if (obj instanceof Map) {
                Map map = (Map) obj;
                if (this.size != map.size() || !i(map.entrySet())) {
                }
            }
            return false;
        }
        return true;
    }

    public final Object[] f() {
        V[] vArr = this.valuesArray;
        if (vArr != null) {
            return vArr;
        }
        int length = this.keysArray.length;
        if (length < 0) {
            throw new IllegalArgumentException("capacity must be non-negative.".toString());
        }
        V[] vArr2 = (V[]) new Object[length];
        this.valuesArray = vArr2;
        return vArr2;
    }

    public final void g() {
        h();
        this.isReadOnly = true;
    }

    @Override // java.util.Map
    public final Object get(Object obj) {
        int iL = l(obj);
        if (iL < 0) {
            return null;
        }
        V[] vArr = this.valuesArray;
        v03.h(vArr);
        return vArr[iL];
    }

    public final void h() {
        if (this.isReadOnly) {
            throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.Map
    public final int hashCode() {
        ie1 ie1Var = new ie1(this, 0);
        int i = 0;
        while (ie1Var.hasNext()) {
            int i2 = ie1Var.e;
            MapBuilder mapBuilder = ie1Var.c;
            if (i2 >= mapBuilder.length) {
                throw new NoSuchElementException();
            }
            ie1Var.e = i2 + 1;
            ie1Var.k = i2;
            K k = mapBuilder.keysArray[i2];
            int iHashCode = k != null ? k.hashCode() : 0;
            V[] vArr = mapBuilder.valuesArray;
            v03.h(vArr);
            V v = vArr[ie1Var.k];
            int iHashCode2 = v != null ? v.hashCode() : 0;
            ie1Var.a();
            i += iHashCode ^ iHashCode2;
        }
        return i;
    }

    public final boolean i(Collection collection) {
        v03.j(collection, "m");
        for (Object obj : collection) {
            if (obj != null) {
                try {
                    if (!j((Map.Entry) obj)) {
                    }
                } catch (ClassCastException unused) {
                }
            }
            return false;
        }
        return true;
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        return this.size == 0;
    }

    public final boolean j(Map.Entry entry) {
        v03.j(entry, "entry");
        int iL = l(entry.getKey());
        if (iL < 0) {
            return false;
        }
        V[] vArr = this.valuesArray;
        v03.h(vArr);
        return v03.b(vArr[iL], entry.getValue());
    }

    public final void k(int i) {
        V[] vArr;
        K[] kArr = this.keysArray;
        int length = kArr.length;
        int i2 = this.length;
        int i3 = length - i2;
        int i4 = i2 - this.size;
        if (i3 < i && i3 + i4 >= i && i4 >= kArr.length / 4) {
            p(this.hashArray.length);
            return;
        }
        int i5 = i2 + i;
        if (i5 < 0) {
            throw new OutOfMemoryError();
        }
        if (i5 > kArr.length) {
            int length2 = (kArr.length * 3) / 2;
            if (i5 <= length2) {
                i5 = length2;
            }
            K[] kArr2 = (K[]) Arrays.copyOf(kArr, i5);
            v03.i(kArr2, "copyOf(this, newSize)");
            this.keysArray = kArr2;
            V[] vArr2 = this.valuesArray;
            if (vArr2 != null) {
                vArr = (V[]) Arrays.copyOf(vArr2, i5);
                v03.i(vArr, "copyOf(this, newSize)");
            } else {
                vArr = null;
            }
            this.valuesArray = vArr;
            int[] iArrCopyOf = Arrays.copyOf(this.presenceArray, i5);
            v03.i(iArrCopyOf, "copyOf(this, newSize)");
            this.presenceArray = iArrCopyOf;
            if (i5 < 1) {
                i5 = 1;
            }
            int iHighestOneBit = Integer.highestOneBit(i5 * 3);
            if (iHighestOneBit > this.hashArray.length) {
                p(iHighestOneBit);
            }
        }
    }

    @Override // java.util.Map
    public final Set keySet() {
        me1 me1Var = this.keysView;
        if (me1Var != null) {
            return me1Var;
        }
        me1 me1Var2 = new me1(this);
        this.keysView = me1Var2;
        return me1Var2;
    }

    public final int l(Object obj) {
        int iN = n(obj);
        int i = this.maxProbeDistance;
        while (true) {
            int i2 = this.hashArray[iN];
            if (i2 == 0) {
                return -1;
            }
            if (i2 > 0) {
                int i3 = i2 - 1;
                if (v03.b(this.keysArray[i3], obj)) {
                    return i3;
                }
            }
            i--;
            if (i < 0) {
                return -1;
            }
            iN = iN == 0 ? this.hashArray.length - 1 : iN - 1;
        }
    }

    public final int m(Object obj) {
        int i = this.length;
        while (true) {
            i--;
            if (i < 0) {
                return -1;
            }
            if (this.presenceArray[i] >= 0) {
                V[] vArr = this.valuesArray;
                v03.h(vArr);
                if (v03.b(vArr[i], obj)) {
                    return i;
                }
            }
        }
    }

    public final int n(Object obj) {
        return ((obj != null ? obj.hashCode() : 0) * (-1640531527)) >>> this.hashShift;
    }

    public final boolean o() {
        return this.isReadOnly;
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x0065, code lost:
    
        r3[r0] = r7;
        r6.presenceArray[r2] = r0;
        r2 = r7;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void p(int r7) {
        /*
            r6 = this;
            int r0 = r6.length
            int r1 = r6.size
            r2 = 0
            if (r0 <= r1) goto L34
            V[] r0 = r6.valuesArray
            r1 = r2
            r3 = r1
        Lb:
            int r4 = r6.length
            if (r1 >= r4) goto L26
            int[] r4 = r6.presenceArray
            r4 = r4[r1]
            if (r4 < 0) goto L23
            K[] r4 = r6.keysArray
            r5 = r4[r1]
            r4[r3] = r5
            if (r0 == 0) goto L21
            r4 = r0[r1]
            r0[r3] = r4
        L21:
            int r3 = r3 + 1
        L23:
            int r1 = r1 + 1
            goto Lb
        L26:
            K[] r1 = r6.keysArray
            p.f60.E(r3, r4, r1)
            if (r0 == 0) goto L32
            int r1 = r6.length
            p.f60.E(r3, r1, r0)
        L32:
            r6.length = r3
        L34:
            int[] r0 = r6.hashArray
            int r1 = r0.length
            if (r7 == r1) goto L46
            int[] r0 = new int[r7]
            r6.hashArray = r0
            int r7 = java.lang.Integer.numberOfLeadingZeros(r7)
            int r7 = r7 + 1
            r6.hashShift = r7
            goto L4f
        L46:
            int r7 = r0.length
            java.lang.String r1 = "<this>"
            p.v03.j(r0, r1)
            java.util.Arrays.fill(r0, r2, r7, r2)
        L4f:
            int r7 = r6.length
            if (r2 >= r7) goto L83
            int r7 = r2 + 1
            K[] r0 = r6.keysArray
            r0 = r0[r2]
            int r0 = r6.n(r0)
            int r1 = r6.maxProbeDistance
        L5f:
            int[] r3 = r6.hashArray
            r4 = r3[r0]
            if (r4 != 0) goto L6d
            r3[r0] = r7
            int[] r1 = r6.presenceArray
            r1[r2] = r0
            r2 = r7
            goto L4f
        L6d:
            int r1 = r1 + (-1)
            if (r1 < 0) goto L7b
            int r4 = r0 + (-1)
            if (r0 != 0) goto L79
            int r0 = r3.length
            int r0 = r0 + (-1)
            goto L5f
        L79:
            r0 = r4
            goto L5f
        L7b:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "This cannot happen with fixed magic multiplier and grow-only hash array. Have object hashCodes changed?"
            r7.<init>(r0)
            throw r7
        L83:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.collections.builders.MapBuilder.p(int):void");
    }

    @Override // java.util.Map
    public final Object put(Object obj, Object obj2) {
        h();
        int iE = e(obj);
        Object[] objArrF = f();
        if (iE >= 0) {
            objArrF[iE] = obj2;
            return null;
        }
        int i = (-iE) - 1;
        Object obj3 = objArrF[i];
        objArrF[i] = obj2;
        return obj3;
    }

    @Override // java.util.Map
    public final void putAll(Map map) {
        v03.j(map, "from");
        h();
        Set<Map.Entry<K, V>> setEntrySet = map.entrySet();
        if (setEntrySet.isEmpty()) {
            return;
        }
        k(setEntrySet.size());
        for (Map.Entry<K, V> entry : setEntrySet) {
            int iE = e(entry.getKey());
            Object[] objArrF = f();
            if (iE >= 0) {
                objArrF[iE] = entry.getValue();
            } else {
                int i = (-iE) - 1;
                if (!v03.b(entry.getValue(), objArrF[i])) {
                    objArrF[i] = entry.getValue();
                }
            }
        }
    }

    public final boolean q(Map.Entry entry) {
        v03.j(entry, "entry");
        h();
        int iL = l(entry.getKey());
        if (iL < 0) {
            return false;
        }
        V[] vArr = this.valuesArray;
        v03.h(vArr);
        if (!v03.b(vArr[iL], entry.getValue())) {
            return false;
        }
        r(iL);
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0062 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:30:? A[LOOP:0: B:6:0x001e->B:30:?, LOOP_END, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void r(int r12) {
        /*
            r11 = this;
            K[] r0 = r11.keysArray
            java.lang.String r1 = "<this>"
            p.v03.j(r0, r1)
            r1 = 0
            r0[r12] = r1
            int[] r0 = r11.presenceArray
            r0 = r0[r12]
            int r1 = r11.maxProbeDistance
            int r1 = r1 * 2
            int[] r2 = r11.hashArray
            int r2 = r2.length
            int r2 = r2 / 2
            if (r1 <= r2) goto L1a
            r1 = r2
        L1a:
            r2 = 0
            r3 = r1
            r4 = r2
            r1 = r0
        L1e:
            int r5 = r0 + (-1)
            if (r0 != 0) goto L28
            int[] r0 = r11.hashArray
            int r0 = r0.length
            int r0 = r0 + (-1)
            goto L29
        L28:
            r0 = r5
        L29:
            int r4 = r4 + 1
            int r5 = r11.maxProbeDistance
            r6 = -1
            if (r4 <= r5) goto L35
            int[] r0 = r11.hashArray
            r0[r1] = r2
            goto L66
        L35:
            int[] r5 = r11.hashArray
            r7 = r5[r0]
            if (r7 != 0) goto L3e
            r5[r1] = r2
            goto L66
        L3e:
            if (r7 >= 0) goto L45
            r5[r1] = r6
        L42:
            r1 = r0
            r4 = r2
            goto L5f
        L45:
            K[] r5 = r11.keysArray
            int r8 = r7 + (-1)
            r5 = r5[r8]
            int r5 = r11.n(r5)
            int r5 = r5 - r0
            int[] r9 = r11.hashArray
            int r10 = r9.length
            int r10 = r10 + (-1)
            r5 = r5 & r10
            if (r5 < r4) goto L5f
            r9[r1] = r7
            int[] r4 = r11.presenceArray
            r4[r8] = r1
            goto L42
        L5f:
            int r3 = r3 + r6
            if (r3 >= 0) goto L1e
            int[] r0 = r11.hashArray
            r0[r1] = r6
        L66:
            int[] r0 = r11.presenceArray
            r0[r12] = r6
            int r12 = r11.size
            int r12 = r12 + r6
            r11.size = r12
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.collections.builders.MapBuilder.r(int):void");
    }

    @Override // java.util.Map
    public final Object remove(Object obj) {
        h();
        int iL = l(obj);
        if (iL < 0) {
            iL = -1;
        } else {
            r(iL);
        }
        if (iL < 0) {
            return null;
        }
        V[] vArr = this.valuesArray;
        v03.h(vArr);
        V v = vArr[iL];
        vArr[iL] = null;
        return v;
    }

    @Override // java.util.Map
    public final int size() {
        return this.size;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder((this.size * 3) + 2);
        sb.append("{");
        int i = 0;
        ie1 ie1Var = new ie1(this, 0);
        while (ie1Var.hasNext()) {
            if (i > 0) {
                sb.append(", ");
            }
            int i2 = ie1Var.e;
            MapBuilder mapBuilder = ie1Var.c;
            if (i2 >= mapBuilder.length) {
                throw new NoSuchElementException();
            }
            ie1Var.e = i2 + 1;
            ie1Var.k = i2;
            K k = mapBuilder.keysArray[i2];
            if (v03.b(k, mapBuilder)) {
                sb.append("(this Map)");
            } else {
                sb.append(k);
            }
            sb.append('=');
            V[] vArr = mapBuilder.valuesArray;
            v03.h(vArr);
            V v = vArr[ie1Var.k];
            if (v03.b(v, mapBuilder)) {
                sb.append("(this Map)");
            } else {
                sb.append(v);
            }
            ie1Var.a();
            i++;
        }
        sb.append("}");
        String string = sb.toString();
        v03.i(string, "sb.toString()");
        return string;
    }

    @Override // java.util.Map
    public final Collection values() {
        ne1 ne1Var = this.valuesView;
        if (ne1Var != null) {
            return ne1Var;
        }
        ne1 ne1Var2 = new ne1(this);
        this.valuesView = ne1Var2;
        return ne1Var2;
    }

    public MapBuilder(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("capacity must be non-negative.".toString());
        }
        K[] kArr = (K[]) new Object[i];
        int[] iArr = new int[i];
        int iHighestOneBit = Integer.highestOneBit((i < 1 ? 1 : i) * 3);
        this.keysArray = kArr;
        this.valuesArray = null;
        this.presenceArray = iArr;
        this.hashArray = new int[iHighestOneBit];
        this.maxProbeDistance = 2;
        this.length = 0;
        this.hashShift = Integer.numberOfLeadingZeros(iHighestOneBit) + 1;
    }
}
