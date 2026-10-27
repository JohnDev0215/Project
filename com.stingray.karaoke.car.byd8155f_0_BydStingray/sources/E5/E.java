package E5;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class E extends AbstractMap implements Serializable {

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final y f1761t = new y(0);

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public B f1766r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public B f1767s;
    public int o = 0;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f1764p = 0;
    public final Comparator l = f1761t;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final D f1763n = new D();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public D[] f1762m = new D[16];

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f1765q = 12;

    public final D b(Object obj, boolean z6) {
        D d;
        int i7;
        D d10;
        D d11;
        D d12;
        D d13;
        D d14;
        D d15;
        D[] dArr = this.f1762m;
        int iHashCode = obj.hashCode();
        int i10 = iHashCode ^ ((iHashCode >>> 20) ^ (iHashCode >>> 12));
        int i11 = ((i10 >>> 7) ^ i10) ^ (i10 >>> 4);
        int length = i11 & (dArr.length - 1);
        D d16 = dArr[length];
        y yVar = f1761t;
        D d17 = null;
        Comparator comparator = this.l;
        if (d16 != null) {
            Comparable comparable = comparator == yVar ? (Comparable) obj : null;
            while (true) {
                Object obj2 = d16.f1757q;
                int iCompareTo = comparable != null ? comparable.compareTo(obj2) : comparator.compare(obj, obj2);
                if (iCompareTo == 0) {
                    return d16;
                }
                D d18 = iCompareTo < 0 ? d16.f1754m : d16.f1755n;
                if (d18 == null) {
                    i7 = iCompareTo;
                    d = d16;
                    break;
                }
                d16 = d18;
            }
        } else {
            d = d16;
            i7 = 0;
        }
        if (!z6) {
            return null;
        }
        D d19 = this.f1763n;
        if (d != null) {
            D d20 = new D(d, obj, i11, d19, d19.f1756p);
            if (i7 < 0) {
                d.f1754m = d20;
            } else {
                d.f1755n = d20;
            }
            c(d, true);
            d10 = d20;
        } else {
            if (comparator == yVar && !(obj instanceof Comparable)) {
                throw new ClassCastException(obj.getClass().getName().concat(" is not Comparable"));
            }
            d10 = new D(d, obj, i11, d19, d19.f1756p);
            dArr[length] = d10;
        }
        int i12 = this.o;
        this.o = i12 + 1;
        if (i12 > this.f1765q) {
            D[] dArr2 = this.f1762m;
            int length2 = dArr2.length;
            int i13 = length2 * 2;
            D[] dArr3 = new D[i13];
            z zVar = new z(0, (byte) 0);
            z zVar2 = new z(0, (byte) 0);
            int i14 = 0;
            while (i14 < length2) {
                D d21 = dArr2[i14];
                if (d21 == null) {
                    d12 = d17;
                } else {
                    D d22 = d17;
                    for (D d23 = d21; d23 != null; d23 = d23.f1754m) {
                        d23.l = d22;
                        d22 = d23;
                    }
                    int i15 = 0;
                    int i16 = 0;
                    while (true) {
                        if (d22 != null) {
                            D d24 = d22.l;
                            d22.l = d17;
                            D d25 = d22.f1755n;
                            while (true) {
                                D d26 = d24;
                                d24 = d25;
                                d11 = d26;
                                if (d24 == null) {
                                    break;
                                }
                                d24.l = d11;
                                d25 = d24.f1754m;
                            }
                        } else {
                            D d27 = d22;
                            d22 = d17;
                            d11 = d27;
                        }
                        if (d22 == null) {
                            break;
                        }
                        if ((d22.f1758r & length2) == 0) {
                            i15++;
                        } else {
                            i16++;
                        }
                        d22 = d11;
                        d17 = null;
                    }
                    zVar.f1841b = ((Integer.highestOneBit(i15) * 2) - 1) - i15;
                    zVar.d = 0;
                    zVar.c = 0;
                    d12 = null;
                    zVar.f1842e = null;
                    zVar2.f1841b = ((Integer.highestOneBit(i16) * 2) - 1) - i16;
                    zVar2.d = 0;
                    zVar2.c = 0;
                    zVar2.f1842e = null;
                    D d28 = null;
                    while (d21 != null) {
                        d21.l = d28;
                        d28 = d21;
                        d21 = d21.f1754m;
                    }
                    while (true) {
                        if (d28 != null) {
                            D d29 = d28.l;
                            d28.l = null;
                            D d30 = d28.f1755n;
                            while (true) {
                                D d31 = d30;
                                d13 = d29;
                                d29 = d31;
                                if (d29 == null) {
                                    break;
                                }
                                d29.l = d13;
                                d30 = d29.f1754m;
                            }
                        } else {
                            d13 = d28;
                            d28 = null;
                        }
                        if (d28 == null) {
                            break;
                        }
                        if ((d28.f1758r & length2) == 0) {
                            zVar.a(d28);
                        } else {
                            zVar2.a(d28);
                        }
                        d28 = d13;
                    }
                    if (i15 > 0) {
                        d14 = (D) zVar.f1842e;
                        if (d14.l != null) {
                            throw new IllegalStateException();
                        }
                    } else {
                        d14 = null;
                    }
                    dArr3[i14] = d14;
                    int i17 = i14 + length2;
                    if (i16 > 0) {
                        d15 = (D) zVar2.f1842e;
                        if (d15.l != null) {
                            throw new IllegalStateException();
                        }
                    } else {
                        d15 = null;
                    }
                    dArr3[i17] = d15;
                }
                i14++;
                d17 = d12;
            }
            this.f1762m = dArr3;
            this.f1765q = (i13 / 4) + (i13 / 2);
        }
        this.f1764p++;
        return d10;
    }

    public final void c(D d, boolean z6) {
        while (d != null) {
            D d10 = d.f1754m;
            D d11 = d.f1755n;
            int i7 = d10 != null ? d10.f1760t : 0;
            int i10 = d11 != null ? d11.f1760t : 0;
            int i11 = i7 - i10;
            if (i11 == -2) {
                D d12 = d11.f1754m;
                D d13 = d11.f1755n;
                int i12 = (d12 != null ? d12.f1760t : 0) - (d13 != null ? d13.f1760t : 0);
                if (i12 != -1 && (i12 != 0 || z6)) {
                    g(d11);
                }
                f(d);
                if (z6) {
                    return;
                }
            } else if (i11 == 2) {
                D d14 = d10.f1754m;
                D d15 = d10.f1755n;
                int i13 = (d14 != null ? d14.f1760t : 0) - (d15 != null ? d15.f1760t : 0);
                if (i13 != 1 && (i13 != 0 || z6)) {
                    f(d10);
                }
                g(d);
                if (z6) {
                    return;
                }
            } else if (i11 == 0) {
                d.f1760t = i7 + 1;
                if (z6) {
                    return;
                }
            } else {
                d.f1760t = Math.max(i7, i10) + 1;
                if (!z6) {
                    return;
                }
            }
            d = d.l;
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final void clear() {
        Arrays.fill(this.f1762m, (Object) null);
        this.o = 0;
        this.f1764p++;
        D d = this.f1763n;
        D d10 = d.o;
        while (d10 != d) {
            D d11 = d10.o;
            d10.f1756p = null;
            d10.o = null;
            d10 = d11;
        }
        d.f1756p = d;
        d.o = d;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsKey(Object obj) {
        D dB = null;
        if (obj != null) {
            try {
                dB = b(obj, false);
            } catch (ClassCastException unused) {
            }
        }
        return dB != null;
    }

    public final void d(D d, boolean z6) {
        D d10;
        D d11;
        int i7;
        if (z6) {
            D d12 = d.f1756p;
            d12.o = d.o;
            d.o.f1756p = d12;
            d.f1756p = null;
            d.o = null;
        }
        D d13 = d.f1754m;
        D d14 = d.f1755n;
        D d15 = d.l;
        int i10 = 0;
        if (d13 == null || d14 == null) {
            if (d13 != null) {
                e(d, d13);
                d.f1754m = null;
            } else if (d14 != null) {
                e(d, d14);
                d.f1755n = null;
            } else {
                e(d, null);
            }
            c(d15, false);
            this.o--;
            this.f1764p++;
            return;
        }
        if (d13.f1760t > d14.f1760t) {
            D d16 = d13.f1755n;
            while (true) {
                D d17 = d16;
                d11 = d13;
                d13 = d17;
                if (d13 == null) {
                    break;
                } else {
                    d16 = d13.f1755n;
                }
            }
        } else {
            D d18 = d14.f1754m;
            while (true) {
                d10 = d14;
                d14 = d18;
                if (d14 == null) {
                    break;
                } else {
                    d18 = d14.f1754m;
                }
            }
            d11 = d10;
        }
        d(d11, false);
        D d19 = d.f1754m;
        if (d19 != null) {
            i7 = d19.f1760t;
            d11.f1754m = d19;
            d19.l = d11;
            d.f1754m = null;
        } else {
            i7 = 0;
        }
        D d20 = d.f1755n;
        if (d20 != null) {
            i10 = d20.f1760t;
            d11.f1755n = d20;
            d20.l = d11;
            d.f1755n = null;
        }
        d11.f1760t = Math.max(i7, i10) + 1;
        e(d, d11);
    }

    public final void e(D d, D d10) {
        D d11 = d.l;
        d.l = null;
        if (d10 != null) {
            d10.l = d11;
        }
        if (d11 == null) {
            this.f1762m[d.f1758r & (r2.length - 1)] = d10;
        } else if (d11.f1754m == d) {
            d11.f1754m = d10;
        } else {
            d11.f1755n = d10;
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set entrySet() {
        B b10 = this.f1766r;
        if (b10 != null) {
            return b10;
        }
        B b11 = new B(this, 0);
        this.f1766r = b11;
        return b11;
    }

    public final void f(D d) {
        D d10 = d.f1754m;
        D d11 = d.f1755n;
        D d12 = d11.f1754m;
        D d13 = d11.f1755n;
        d.f1755n = d12;
        if (d12 != null) {
            d12.l = d;
        }
        e(d, d11);
        d11.f1754m = d;
        d.l = d11;
        int iMax = Math.max(d10 != null ? d10.f1760t : 0, d12 != null ? d12.f1760t : 0) + 1;
        d.f1760t = iMax;
        d11.f1760t = Math.max(iMax, d13 != null ? d13.f1760t : 0) + 1;
    }

    public final void g(D d) {
        D d10 = d.f1754m;
        D d11 = d.f1755n;
        D d12 = d10.f1754m;
        D d13 = d10.f1755n;
        d.f1754m = d13;
        if (d13 != null) {
            d13.l = d;
        }
        e(d, d10);
        d10.f1755n = d;
        d.l = d10;
        int iMax = Math.max(d11 != null ? d11.f1760t : 0, d13 != null ? d13.f1760t : 0) + 1;
        d.f1760t = iMax;
        d10.f1760t = Math.max(iMax, d12 != null ? d12.f1760t : 0) + 1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object get(Object obj) {
        D dB;
        if (obj != null) {
            try {
                dB = b(obj, false);
            } catch (ClassCastException unused) {
                dB = null;
            }
        } else {
            dB = null;
        }
        if (dB != null) {
            return dB.f1759s;
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set keySet() {
        B b10 = this.f1767s;
        if (b10 != null) {
            return b10;
        }
        B b11 = new B(this, 1);
        this.f1767s = b11;
        return b11;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object put(Object obj, Object obj2) {
        if (obj == null) {
            throw new NullPointerException("key == null");
        }
        D dB = b(obj, true);
        Object obj3 = dB.f1759s;
        dB.f1759s = obj2;
        return obj3;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object remove(Object obj) {
        D dB;
        if (obj != null) {
            try {
                dB = b(obj, false);
            } catch (ClassCastException unused) {
                dB = null;
            }
        } else {
            dB = null;
        }
        if (dB != null) {
            d(dB, true);
        }
        if (dB != null) {
            return dB.f1759s;
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int size() {
        return this.o;
    }
}
