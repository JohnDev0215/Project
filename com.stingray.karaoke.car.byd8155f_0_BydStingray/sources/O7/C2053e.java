package o7;

import A7.m;
import Y.i;
import h8.AbstractC1386c;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/* JADX INFO: renamed from: o7.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2053e implements Map, Serializable, B7.e {

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static final C2053e f20548y;
    public Object[] l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Object[] f20549m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int[] f20550n;
    public int[] o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f20551p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f20552q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f20553r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f20554s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f20555t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public C2054f f20556u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public i f20557v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public C2054f f20558w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public boolean f20559x;

    static {
        C2053e c2053e = new C2053e(0);
        c2053e.f20559x = true;
        f20548y = c2053e;
    }

    public C2053e() {
        this(8);
    }

    public final int b(Object obj) {
        c();
        while (true) {
            int iL = l(obj);
            int i7 = this.f20551p * 2;
            int length = this.o.length / 2;
            if (i7 > length) {
                i7 = length;
            }
            int i10 = 0;
            while (true) {
                int[] iArr = this.o;
                int i11 = iArr[iL];
                if (i11 <= 0) {
                    int i12 = this.f20552q;
                    Object[] objArr = this.l;
                    if (i12 < objArr.length) {
                        int i13 = i12 + 1;
                        this.f20552q = i13;
                        objArr[i12] = obj;
                        this.f20550n[i12] = iL;
                        iArr[iL] = i13;
                        this.f20555t++;
                        this.f20554s++;
                        if (i10 > this.f20551p) {
                            this.f20551p = i10;
                        }
                        return i12;
                    }
                    h(1);
                } else {
                    if (m.a(this.l[i11 - 1], obj)) {
                        return -i11;
                    }
                    i10++;
                    if (i10 > i7) {
                        n(this.o.length * 2);
                        break;
                    }
                    iL = iL == 0 ? this.o.length - 1 : iL - 1;
                }
            }
        }
    }

    public final void c() {
        if (this.f20559x) {
            throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.Map
    public final void clear() {
        c();
        int i7 = this.f20552q - 1;
        if (i7 >= 0) {
            int i10 = 0;
            while (true) {
                int[] iArr = this.f20550n;
                int i11 = iArr[i10];
                if (i11 >= 0) {
                    this.o[i11] = 0;
                    iArr[i10] = -1;
                }
                if (i10 == i7) {
                    break;
                } else {
                    i10++;
                }
            }
        }
        AbstractC1386c.r(this.l, 0, this.f20552q);
        Object[] objArr = this.f20549m;
        if (objArr != null) {
            AbstractC1386c.r(objArr, 0, this.f20552q);
        }
        this.f20555t = 0;
        this.f20552q = 0;
        this.f20554s++;
    }

    @Override // java.util.Map
    public final boolean containsKey(Object obj) {
        return i(obj) >= 0;
    }

    @Override // java.util.Map
    public final boolean containsValue(Object obj) {
        return j(obj) >= 0;
    }

    public final void d(boolean z6) {
        int i7;
        Object[] objArr = this.f20549m;
        int i10 = 0;
        int i11 = 0;
        while (true) {
            i7 = this.f20552q;
            if (i10 >= i7) {
                break;
            }
            int[] iArr = this.f20550n;
            int i12 = iArr[i10];
            if (i12 >= 0) {
                Object[] objArr2 = this.l;
                objArr2[i11] = objArr2[i10];
                if (objArr != null) {
                    objArr[i11] = objArr[i10];
                }
                if (z6) {
                    iArr[i11] = i12;
                    this.o[i12] = i11 + 1;
                }
                i11++;
            }
            i10++;
        }
        AbstractC1386c.r(this.l, i11, i7);
        if (objArr != null) {
            AbstractC1386c.r(objArr, i11, this.f20552q);
        }
        this.f20552q = i11;
    }

    @Override // java.util.Map
    public final Set entrySet() {
        C2054f c2054f = this.f20558w;
        if (c2054f != null) {
            return c2054f;
        }
        C2054f c2054f2 = new C2054f(this, 0);
        this.f20558w = c2054f2;
        return c2054f2;
    }

    @Override // java.util.Map
    public final boolean equals(Object obj) {
        if (obj != this) {
            if (obj instanceof Map) {
                Map map = (Map) obj;
                if (this.f20555t != map.size() || !f(map.entrySet())) {
                }
            }
            return false;
        }
        return true;
    }

    public final boolean f(Collection collection) {
        m.f(collection, "m");
        for (Object obj : collection) {
            if (obj != null) {
                try {
                    if (!g((Map.Entry) obj)) {
                    }
                } catch (ClassCastException unused) {
                }
            }
            return false;
        }
        return true;
    }

    public final boolean g(Map.Entry entry) {
        m.f(entry, "entry");
        int i7 = i(entry.getKey());
        if (i7 < 0) {
            return false;
        }
        Object[] objArr = this.f20549m;
        m.c(objArr);
        return m.a(objArr[i7], entry.getValue());
    }

    @Override // java.util.Map
    public final Object get(Object obj) {
        int i7 = i(obj);
        if (i7 < 0) {
            return null;
        }
        Object[] objArr = this.f20549m;
        m.c(objArr);
        return objArr[i7];
    }

    public final void h(int i7) {
        Object[] objArrCopyOf;
        Object[] objArr = this.l;
        int length = objArr.length;
        int i10 = this.f20552q;
        int i11 = length - i10;
        int i12 = i10 - this.f20555t;
        if (i11 < i7 && i11 + i12 >= i7 && i12 >= objArr.length / 4) {
            d(true);
            return;
        }
        int i13 = i10 + i7;
        if (i13 < 0) {
            throw new OutOfMemoryError();
        }
        if (i13 > objArr.length) {
            int length2 = objArr.length;
            int i14 = length2 + (length2 >> 1);
            if (i14 - i13 < 0) {
                i14 = i13;
            }
            if (i14 - 2147483639 > 0) {
                i14 = i13 > 2147483639 ? Integer.MAX_VALUE : 2147483639;
            }
            Object[] objArrCopyOf2 = Arrays.copyOf(objArr, i14);
            m.e(objArrCopyOf2, "copyOf(...)");
            this.l = objArrCopyOf2;
            Object[] objArr2 = this.f20549m;
            if (objArr2 != null) {
                objArrCopyOf = Arrays.copyOf(objArr2, i14);
                m.e(objArrCopyOf, "copyOf(...)");
            } else {
                objArrCopyOf = null;
            }
            this.f20549m = objArrCopyOf;
            int[] iArrCopyOf = Arrays.copyOf(this.f20550n, i14);
            m.e(iArrCopyOf, "copyOf(...)");
            this.f20550n = iArrCopyOf;
            int iHighestOneBit = Integer.highestOneBit((i14 >= 1 ? i14 : 1) * 3);
            if (iHighestOneBit > this.o.length) {
                n(iHighestOneBit);
            }
        }
    }

    @Override // java.util.Map
    public final int hashCode() {
        C2051c c2051c = new C2051c(this, 0);
        int i7 = 0;
        while (c2051c.hasNext()) {
            int i10 = c2051c.l;
            C2053e c2053e = (C2053e) c2051c.o;
            if (i10 >= c2053e.f20552q) {
                throw new NoSuchElementException();
            }
            c2051c.l = i10 + 1;
            c2051c.f8960m = i10;
            Object obj = c2053e.l[i10];
            int iHashCode = obj != null ? obj.hashCode() : 0;
            Object[] objArr = c2053e.f20549m;
            m.c(objArr);
            Object obj2 = objArr[c2051c.f8960m];
            int iHashCode2 = obj2 != null ? obj2.hashCode() : 0;
            c2051c.d();
            i7 += iHashCode ^ iHashCode2;
        }
        return i7;
    }

    public final int i(Object obj) {
        int iL = l(obj);
        int i7 = this.f20551p;
        while (true) {
            int i10 = this.o[iL];
            if (i10 == 0) {
                return -1;
            }
            if (i10 > 0) {
                int i11 = i10 - 1;
                if (m.a(this.l[i11], obj)) {
                    return i11;
                }
            }
            i7--;
            if (i7 < 0) {
                return -1;
            }
            iL = iL == 0 ? this.o.length - 1 : iL - 1;
        }
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        return this.f20555t == 0;
    }

    public final int j(Object obj) {
        int i7 = this.f20552q;
        while (true) {
            i7--;
            if (i7 < 0) {
                return -1;
            }
            if (this.f20550n[i7] >= 0) {
                Object[] objArr = this.f20549m;
                m.c(objArr);
                if (m.a(objArr[i7], obj)) {
                    return i7;
                }
            }
        }
    }

    @Override // java.util.Map
    public final Set keySet() {
        C2054f c2054f = this.f20556u;
        if (c2054f != null) {
            return c2054f;
        }
        C2054f c2054f2 = new C2054f(this, 1);
        this.f20556u = c2054f2;
        return c2054f2;
    }

    public final int l(Object obj) {
        return ((obj != null ? obj.hashCode() : 0) * (-1640531527)) >>> this.f20553r;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0032, code lost:
    
        r3[r0] = r6;
        r5.f20550n[r2] = r0;
        r2 = r6;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void n(int r6) {
        /*
            r5 = this;
            int r0 = r5.f20554s
            int r0 = r0 + 1
            r5.f20554s = r0
            int r0 = r5.f20552q
            int r1 = r5.f20555t
            r2 = 0
            if (r0 <= r1) goto L10
            r5.d(r2)
        L10:
            int[] r0 = new int[r6]
            r5.o = r0
            int r6 = java.lang.Integer.numberOfLeadingZeros(r6)
            int r6 = r6 + 1
            r5.f20553r = r6
        L1c:
            int r6 = r5.f20552q
            if (r2 >= r6) goto L50
            int r6 = r2 + 1
            java.lang.Object[] r0 = r5.l
            r0 = r0[r2]
            int r0 = r5.l(r0)
            int r1 = r5.f20551p
        L2c:
            int[] r3 = r5.o
            r4 = r3[r0]
            if (r4 != 0) goto L3a
            r3[r0] = r6
            int[] r1 = r5.f20550n
            r1[r2] = r0
            r2 = r6
            goto L1c
        L3a:
            int r1 = r1 + (-1)
            if (r1 < 0) goto L48
            int r4 = r0 + (-1)
            if (r0 != 0) goto L46
            int r0 = r3.length
            int r0 = r0 + (-1)
            goto L2c
        L46:
            r0 = r4
            goto L2c
        L48:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "This cannot happen with fixed magic multiplier and grow-only hash array. Have object hashCodes changed?"
            r5.<init>(r6)
            throw r5
        L50:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: o7.C2053e.n(int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0068 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:33:? A[LOOP:0: B:9:0x0024->B:33:?, LOOP_END, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void o(int r12) {
        /*
            r11 = this;
            java.lang.Object[] r0 = r11.l
            java.lang.String r1 = "<this>"
            A7.m.f(r0, r1)
            r1 = 0
            r0[r12] = r1
            java.lang.Object[] r0 = r11.f20549m
            if (r0 == 0) goto L10
            r0[r12] = r1
        L10:
            int[] r0 = r11.f20550n
            r0 = r0[r12]
            int r1 = r11.f20551p
            int r1 = r1 * 2
            int[] r2 = r11.o
            int r2 = r2.length
            int r2 = r2 / 2
            if (r1 <= r2) goto L20
            r1 = r2
        L20:
            r2 = 0
            r3 = r1
            r4 = r2
            r1 = r0
        L24:
            int r5 = r0 + (-1)
            if (r0 != 0) goto L2e
            int[] r0 = r11.o
            int r0 = r0.length
            int r0 = r0 + (-1)
            goto L2f
        L2e:
            r0 = r5
        L2f:
            int r4 = r4 + 1
            int r5 = r11.f20551p
            r6 = -1
            if (r4 <= r5) goto L3b
            int[] r0 = r11.o
            r0[r1] = r2
            goto L6c
        L3b:
            int[] r5 = r11.o
            r7 = r5[r0]
            if (r7 != 0) goto L44
            r5[r1] = r2
            goto L6c
        L44:
            if (r7 >= 0) goto L4b
            r5[r1] = r6
        L48:
            r1 = r0
            r4 = r2
            goto L65
        L4b:
            java.lang.Object[] r5 = r11.l
            int r8 = r7 + (-1)
            r5 = r5[r8]
            int r5 = r11.l(r5)
            int r5 = r5 - r0
            int[] r9 = r11.o
            int r10 = r9.length
            int r10 = r10 + (-1)
            r5 = r5 & r10
            if (r5 < r4) goto L65
            r9[r1] = r7
            int[] r4 = r11.f20550n
            r4[r8] = r1
            goto L48
        L65:
            int r3 = r3 + r6
            if (r3 >= 0) goto L24
            int[] r0 = r11.o
            r0[r1] = r6
        L6c:
            int[] r0 = r11.f20550n
            r0[r12] = r6
            int r12 = r11.f20555t
            int r12 = r12 + r6
            r11.f20555t = r12
            int r12 = r11.f20554s
            int r12 = r12 + 1
            r11.f20554s = r12
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: o7.C2053e.o(int):void");
    }

    @Override // java.util.Map
    public final Object put(Object obj, Object obj2) {
        c();
        int iB = b(obj);
        Object[] objArr = this.f20549m;
        if (objArr == null) {
            int length = this.l.length;
            if (length < 0) {
                throw new IllegalArgumentException("capacity must be non-negative.");
            }
            objArr = new Object[length];
            this.f20549m = objArr;
        }
        if (iB >= 0) {
            objArr[iB] = obj2;
            return null;
        }
        int i7 = (-iB) - 1;
        Object obj3 = objArr[i7];
        objArr[i7] = obj2;
        return obj3;
    }

    @Override // java.util.Map
    public final void putAll(Map map) {
        m.f(map, "from");
        c();
        Set<Map.Entry> setEntrySet = map.entrySet();
        if (setEntrySet.isEmpty()) {
            return;
        }
        h(setEntrySet.size());
        for (Map.Entry entry : setEntrySet) {
            int iB = b(entry.getKey());
            Object[] objArr = this.f20549m;
            if (objArr == null) {
                int length = this.l.length;
                if (length < 0) {
                    throw new IllegalArgumentException("capacity must be non-negative.");
                }
                objArr = new Object[length];
                this.f20549m = objArr;
            }
            if (iB >= 0) {
                objArr[iB] = entry.getValue();
            } else {
                int i7 = (-iB) - 1;
                if (!m.a(entry.getValue(), objArr[i7])) {
                    objArr[i7] = entry.getValue();
                }
            }
        }
    }

    @Override // java.util.Map
    public final Object remove(Object obj) {
        c();
        int i7 = i(obj);
        if (i7 < 0) {
            return null;
        }
        Object[] objArr = this.f20549m;
        m.c(objArr);
        Object obj2 = objArr[i7];
        o(i7);
        return obj2;
    }

    @Override // java.util.Map
    public final int size() {
        return this.f20555t;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder((this.f20555t * 3) + 2);
        sb.append("{");
        C2051c c2051c = new C2051c(this, 0);
        int i7 = 0;
        while (c2051c.hasNext()) {
            if (i7 > 0) {
                sb.append(", ");
            }
            int i10 = c2051c.l;
            C2053e c2053e = (C2053e) c2051c.o;
            if (i10 >= c2053e.f20552q) {
                throw new NoSuchElementException();
            }
            c2051c.l = i10 + 1;
            c2051c.f8960m = i10;
            Object obj = c2053e.l[i10];
            if (obj == c2053e) {
                sb.append("(this Map)");
            } else {
                sb.append(obj);
            }
            sb.append('=');
            Object[] objArr = c2053e.f20549m;
            m.c(objArr);
            Object obj2 = objArr[c2051c.f8960m];
            if (obj2 == c2053e) {
                sb.append("(this Map)");
            } else {
                sb.append(obj2);
            }
            c2051c.d();
            i7++;
        }
        sb.append("}");
        String string = sb.toString();
        m.e(string, "toString(...)");
        return string;
    }

    @Override // java.util.Map
    public final Collection values() {
        i iVar = this.f20557v;
        if (iVar != null) {
            return iVar;
        }
        i iVar2 = new i(this);
        this.f20557v = iVar2;
        return iVar2;
    }

    public C2053e(int i7) {
        if (i7 < 0) {
            throw new IllegalArgumentException("capacity must be non-negative.");
        }
        Object[] objArr = new Object[i7];
        int[] iArr = new int[i7];
        int iHighestOneBit = Integer.highestOneBit((i7 < 1 ? 1 : i7) * 3);
        this.l = objArr;
        this.f20549m = null;
        this.f20550n = iArr;
        this.o = new int[iHighestOneBit];
        this.f20551p = 2;
        this.f20552q = 0;
        this.f20553r = Integer.numberOfLeadingZeros(iHighestOneBit) + 1;
    }
}
