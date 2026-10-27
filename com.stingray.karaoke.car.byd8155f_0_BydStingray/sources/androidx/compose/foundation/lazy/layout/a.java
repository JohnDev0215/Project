package androidx.compose.foundation.lazy.layout;

import A7.m;
import B5.u;
import E0.AbstractC0156f;
import E0.V;
import G.A;
import G.B;
import G.C;
import G.C0302l;
import G.C0309t;
import G.C0314y;
import G.D;
import G.K;
import G.N;
import G.O;
import H7.H;
import S8.AbstractC0727y;
import a1.h;
import g0.n;
import g0.o;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import n0.InterfaceC1897z;
import n7.AbstractC1998m;
import n7.AbstractC2003r;
import q0.C2164c;
import u.AbstractC2456D;
import u.AbstractC2457E;
import u.C2453A;
import u.C2484x;
import x.InterfaceC2615D;
import x3.AbstractC2673a;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C2484x f11488a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public K f11489b;
    public int c;
    public final C2453A d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final ArrayList f11490e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final ArrayList f11491f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final ArrayList f11492g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final ArrayList f11493h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final ArrayList f11494i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public A f11495j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final o f11496k;

    public a() {
        long[] jArr = AbstractC2456D.f22584a;
        this.f11488a = new C2484x();
        int i7 = AbstractC2457E.f22585a;
        this.d = new C2453A();
        this.f11490e = new ArrayList();
        this.f11491f = new ArrayList();
        this.f11492g = new ArrayList();
        this.f11493h = new ArrayList();
        this.f11494i = new ArrayList();
        this.f11496k = new V(this) { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimator$DisplayingDisappearingItemsElement

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final a f11483a;

            {
                this.f11483a = this;
            }

            public final boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof LazyLayoutItemAnimator$DisplayingDisappearingItemsElement) && m.a(this.f11483a, ((LazyLayoutItemAnimator$DisplayingDisappearingItemsElement) obj).f11483a);
            }

            public final int hashCode() {
                return this.f11483a.hashCode();
            }

            @Override // E0.V
            public final n i() {
                A a10 = new A();
                a10.f2490y = this.f11483a;
                return a10;
            }

            @Override // E0.V
            public final void l(n nVar) {
                A a10 = (A) nVar;
                a aVar = a10.f2490y;
                a aVar2 = this.f11483a;
                if (m.a(aVar, aVar2) || !a10.l.f14695x) {
                    return;
                }
                a10.f2490y.f();
                aVar2.f11495j = a10;
                a10.f2490y = aVar2;
            }

            public final String toString() {
                return "DisplayingDisappearingItemsElement(animator=" + this.f11483a + ')';
            }
        };
    }

    public static void c(N n10, int i7, B b10) {
        int i10 = 0;
        long jG = n10.g(0);
        long jA = n10.d() ? h.a(0, i7, 1, jG) : h.a(i7, 0, 2, jG);
        C0314y[] c0314yArr = b10.f2491a;
        int length = c0314yArr.length;
        int i11 = 0;
        while (i10 < length) {
            C0314y c0314y = c0314yArr[i10];
            int i12 = i11 + 1;
            if (c0314y != null) {
                c0314y.f2626j = h.d(jA, h.c(n10.g(i11), jG));
            }
            i10++;
            i11 = i12;
        }
    }

    public static int h(int[] iArr, N n10) {
        int i7 = n10.i();
        int iB = n10.b() + i7;
        int iMax = 0;
        while (i7 < iB) {
            int iF = n10.f() + iArr[i7];
            iArr[i7] = iF;
            iMax = Math.max(iMax, iF);
            i7++;
        }
        return iMax;
    }

    public final C0314y a(int i7, Object obj) {
        B b10 = (B) this.f11488a.e(obj);
        if (b10 != null) {
            return b10.f2491a[i7];
        }
        return null;
    }

    public final long b() {
        ArrayList arrayList = this.f11494i;
        int size = arrayList.size();
        long jC = 0;
        for (int i7 = 0; i7 < size; i7++) {
            C0314y c0314y = (C0314y) arrayList.get(i7);
            C2164c c2164c = c0314y.l;
            if (c2164c != null) {
                jC = H.c(Math.max((int) (jC >> 32), ((int) (c0314y.f2626j >> 32)) + ((int) (c2164c.f21135t >> 32))), Math.max((int) (jC & 4294967295L), ((int) (c0314y.f2626j & 4294967295L)) + ((int) (c2164c.f21135t & 4294967295L))));
            }
        }
        return jC;
    }

    public final void d(int i7, int i10, int i11, ArrayList arrayList, u uVar, O o, boolean z6, boolean z10, int i12, boolean z11, int i13, int i14, X8.c cVar, InterfaceC1897z interfaceC1897z) {
        C2484x c2484x;
        long j3;
        ArrayList arrayList2;
        ArrayList arrayList3;
        ArrayList arrayList4;
        boolean z12;
        int[] iArr;
        C2453A c2453a;
        ArrayList arrayList5;
        ArrayList arrayList6;
        C2484x c2484x2;
        int i15;
        int i16;
        u uVar2;
        int[] iArr2;
        C2484x c2484x3;
        C2484x c2484x4;
        int iF;
        int i17;
        Object[] objArr;
        long[] jArr;
        ArrayList arrayList7;
        int i18;
        int i19;
        Object[] objArr2;
        long[] jArr2;
        ArrayList arrayList8;
        ArrayList arrayList9;
        C2484x c2484x5;
        C0314y[] c0314yArr;
        C0314y[] c0314yArr2;
        int i20;
        int i21;
        long j10;
        long j11;
        long[] jArr3;
        long[] jArr4;
        ArrayList arrayList10 = arrayList;
        K k10 = this.f11489b;
        this.f11489b = uVar;
        int size = arrayList.size();
        int i22 = 0;
        loop0: while (true) {
            c2484x = this.f11488a;
            if (i22 < size) {
                N n10 = (N) arrayList10.get(i22);
                int iC = n10.c();
                for (int i23 = 0; i23 < iC; i23++) {
                    Object objH = n10.h(i23);
                    if ((objH instanceof C0302l ? (C0302l) objH : null) != null) {
                        break loop0;
                    }
                }
                i22++;
            } else if (c2484x.f22645e == 0) {
                f();
                return;
            }
        }
        int i24 = this.c;
        N n11 = (N) AbstractC1998m.a0(arrayList);
        this.c = n11 != null ? n11.getIndex() : 0;
        long jD = z6 ? AbstractC2673a.d(0, i7) : AbstractC2673a.d(i7, 0);
        boolean z13 = z10 || !z11;
        Object[] objArr3 = c2484x.f22644b;
        long[] jArr5 = c2484x.f22643a;
        int length = jArr5.length - 2;
        C2453A c2453a2 = this.d;
        if (length >= 0) {
            int i25 = 0;
            while (true) {
                long j12 = jArr5[i25];
                j3 = jD;
                if ((((~j12) << 7) & j12 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i26 = 8 - ((~(i25 - length)) >>> 31);
                    int i27 = 0;
                    while (i27 < i26) {
                        if ((j12 & 255) < 128) {
                            jArr4 = jArr5;
                            c2453a2.a(objArr3[(i25 << 3) + i27]);
                        } else {
                            jArr4 = jArr5;
                        }
                        j12 >>= 8;
                        i27++;
                        jArr5 = jArr4;
                    }
                    jArr3 = jArr5;
                    if (i26 != 8) {
                        break;
                    }
                } else {
                    jArr3 = jArr5;
                }
                if (i25 == length) {
                    break;
                }
                i25++;
                jD = j3;
                jArr5 = jArr3;
            }
        } else {
            j3 = jD;
        }
        int size2 = arrayList.size();
        int i28 = 0;
        while (true) {
            arrayList2 = this.f11494i;
            arrayList3 = this.f11491f;
            arrayList4 = this.f11490e;
            if (i28 >= size2) {
                break;
            }
            N n12 = (N) arrayList10.get(i28);
            c2453a2.j(n12.getKey());
            int iC2 = n12.c();
            int i29 = 0;
            while (true) {
                if (i29 >= iC2) {
                    i20 = size2;
                    i21 = i24;
                    j10 = j3;
                    e(n12.getKey());
                    break;
                }
                i20 = size2;
                Object objH2 = n12.h(i29);
                int i30 = iC2;
                if ((objH2 instanceof C0302l ? (C0302l) objH2 : null) != null) {
                    B b10 = (B) c2484x.e(n12.getKey());
                    int iA = k10 != null ? k10.a(n12.getKey()) : -1;
                    boolean z14 = iA == -1 && k10 != null;
                    if (b10 == null) {
                        B b11 = new B(this);
                        B.b(b11, n12, cVar, interfaceC1897z, i13, i14);
                        c2484x.i(n12.getKey(), b11);
                        if (n12.getIndex() == iA || iA == -1) {
                            long jG = n12.g(0);
                            c(n12, (int) (n12.d() ? jG & 4294967295L : jG >> 32), b11);
                            if (z14) {
                                for (C0314y c0314y : b11.f2491a) {
                                    if (c0314y != null) {
                                        c0314y.a();
                                    }
                                }
                            }
                        } else if (iA < i24) {
                            arrayList4.add(n12);
                        } else {
                            arrayList3.add(n12);
                        }
                    } else if (z13) {
                        B.b(b10, n12, cVar, interfaceC1897z, i13, i14);
                        C0314y[] c0314yArr3 = b10.f2491a;
                        int length2 = c0314yArr3.length;
                        int i31 = 0;
                        while (i31 < length2) {
                            int i32 = i24;
                            C0314y c0314y2 = c0314yArr3[i31];
                            C0314y[] c0314yArr4 = c0314yArr3;
                            int i33 = length2;
                            if (c0314y2 == null || h.b(c0314y2.f2626j, C0314y.f2617q)) {
                                j11 = j3;
                            } else {
                                j11 = j3;
                                c0314y2.f2626j = h.d(c0314y2.f2626j, j11);
                            }
                            i31++;
                            j3 = j11;
                            c0314yArr3 = c0314yArr4;
                            length2 = i33;
                            i24 = i32;
                        }
                        i21 = i24;
                        long j13 = j3;
                        if (z14) {
                            for (C0314y c0314y3 : b10.f2491a) {
                                if (c0314y3 != null) {
                                    if (c0314y3.b()) {
                                        arrayList2.remove(c0314y3);
                                        A a10 = this.f11495j;
                                        if (a10 != null) {
                                            AbstractC0156f.m(a10);
                                        }
                                    }
                                    c0314y3.a();
                                }
                            }
                        }
                        g(n12, false);
                        j10 = j13;
                    }
                    i21 = i24;
                    j10 = j3;
                } else {
                    i29++;
                    iC2 = i30;
                    size2 = i20;
                }
            }
            i28++;
            arrayList10 = arrayList;
            j3 = j10;
            i24 = i21;
            size2 = i20;
        }
        int i34 = i12;
        int[] iArr3 = new int[i34];
        for (int i35 = 0; i35 < i34; i35++) {
            iArr3[i35] = 0;
        }
        if (z13 && k10 != null) {
            if (!arrayList4.isEmpty()) {
                if (arrayList4.size() > 1) {
                    AbstractC2003r.K(arrayList4, new C(k10, 1));
                }
                int size3 = arrayList4.size();
                for (int i36 = 0; i36 < size3; i36++) {
                    N n13 = (N) arrayList4.get(i36);
                    int iH = i13 - h(iArr3, n13);
                    Object objE = c2484x.e(n13.getKey());
                    m.c(objE);
                    c(n13, iH, (B) objE);
                    g(n13, false);
                }
                Arrays.fill(iArr3, 0, i34, 0);
            }
            if (!arrayList3.isEmpty()) {
                if (arrayList3.size() > 1) {
                    AbstractC2003r.K(arrayList3, new C(k10, 0));
                }
                int size4 = arrayList3.size();
                for (int i37 = 0; i37 < size4; i37++) {
                    N n14 = (N) arrayList3.get(i37);
                    int iH2 = (h(iArr3, n14) + i14) - n14.f();
                    Object objE2 = c2484x.e(n14.getKey());
                    m.c(objE2);
                    c(n14, iH2, (B) objE2);
                    g(n14, false);
                }
                Arrays.fill(iArr3, 0, i34, 0);
            }
        }
        Object[] objArr4 = c2453a2.f22580b;
        long[] jArr6 = c2453a2.f22579a;
        int length3 = jArr6.length - 2;
        ArrayList arrayList11 = this.f11493h;
        ArrayList arrayList12 = this.f11492g;
        if (length3 >= 0) {
            c2453a = c2453a2;
            arrayList5 = arrayList3;
            int i38 = 0;
            while (true) {
                long j14 = jArr6[i38];
                z12 = z13;
                iArr = iArr3;
                if ((((~j14) << 7) & j14 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i39 = 8 - ((~(i38 - length3)) >>> 31);
                    int i40 = 0;
                    while (i40 < i39) {
                        if ((j14 & 255) < 128) {
                            jArr2 = jArr6;
                            Object obj = objArr4[(i38 << 3) + i40];
                            Object objE3 = c2484x.e(obj);
                            m.c(objE3);
                            objArr2 = objArr4;
                            B b12 = (B) objE3;
                            arrayList9 = arrayList4;
                            c2484x5 = c2484x;
                            int iA2 = uVar.a(obj);
                            int iMin = Math.min(i34, b12.f2493e);
                            b12.f2493e = iMin;
                            b12.d = Math.min(i34 - iMin, b12.d);
                            if (iA2 == -1) {
                                C0314y[] c0314yArr5 = b12.f2491a;
                                int length4 = c0314yArr5.length;
                                int i41 = 0;
                                boolean z15 = false;
                                int i42 = 0;
                                while (i41 < length4) {
                                    int i43 = length4;
                                    C0314y c0314y4 = c0314yArr5[i41];
                                    int i44 = i42 + 1;
                                    if (c0314y4 != null) {
                                        if (c0314y4.b()) {
                                            c0314yArr2 = c0314yArr5;
                                        } else {
                                            c0314yArr2 = c0314yArr5;
                                            if (((Boolean) c0314y4.f2625i.getValue()).booleanValue()) {
                                                c0314y4.c();
                                                b12.f2491a[i42] = null;
                                                arrayList2.remove(c0314y4);
                                                A a11 = this.f11495j;
                                                if (a11 != null) {
                                                    AbstractC0156f.m(a11);
                                                }
                                            } else {
                                                if (c0314y4.l != null) {
                                                    c0314y4.b();
                                                }
                                                if (c0314y4.b()) {
                                                    arrayList2.add(c0314y4);
                                                    A a12 = this.f11495j;
                                                    if (a12 != null) {
                                                        AbstractC0156f.m(a12);
                                                    }
                                                } else {
                                                    c0314y4.c();
                                                    b12.f2491a[i42] = null;
                                                    i41++;
                                                    length4 = i43;
                                                    i42 = i44;
                                                    c0314yArr5 = c0314yArr2;
                                                }
                                            }
                                        }
                                        z15 = true;
                                        i41++;
                                        length4 = i43;
                                        i42 = i44;
                                        c0314yArr5 = c0314yArr2;
                                    } else {
                                        c0314yArr2 = c0314yArr5;
                                    }
                                    i41++;
                                    length4 = i43;
                                    i42 = i44;
                                    c0314yArr5 = c0314yArr2;
                                }
                                if (!z15) {
                                    e(obj);
                                }
                                i18 = i39;
                                i19 = i40;
                                arrayList8 = arrayList2;
                            } else {
                                a1.a aVar = b12.f2492b;
                                m.c(aVar);
                                arrayList8 = arrayList2;
                                i18 = i39;
                                i19 = i40;
                                N nA = o.a(iA2, b12.d, b12.f2493e, aVar.f11027a);
                                nA.e();
                                C0314y[] c0314yArr6 = b12.f2491a;
                                int length5 = c0314yArr6.length;
                                int i45 = 0;
                                while (true) {
                                    if (i45 < length5) {
                                        C0314y c0314y5 = c0314yArr6[i45];
                                        if (c0314y5 != null) {
                                            c0314yArr = c0314yArr6;
                                            if (((Boolean) c0314y5.f2622f.getValue()).booleanValue()) {
                                                break;
                                            }
                                        } else {
                                            c0314yArr = c0314yArr6;
                                        }
                                        i45++;
                                        c0314yArr6 = c0314yArr;
                                    } else if (k10 == null || iA2 != k10.a(obj)) {
                                        break;
                                    } else {
                                        e(obj);
                                    }
                                }
                                b12.a(nA, cVar, interfaceC1897z, i13, i14, b12.c);
                                if (iA2 < this.c) {
                                    arrayList12.add(nA);
                                } else {
                                    arrayList11.add(nA);
                                }
                            }
                        } else {
                            i18 = i39;
                            i19 = i40;
                            objArr2 = objArr4;
                            jArr2 = jArr6;
                            arrayList8 = arrayList2;
                            arrayList9 = arrayList4;
                            c2484x5 = c2484x;
                        }
                        j14 >>= 8;
                        i40 = i19 + 1;
                        i34 = i12;
                        arrayList4 = arrayList9;
                        jArr6 = jArr2;
                        objArr4 = objArr2;
                        c2484x = c2484x5;
                        arrayList2 = arrayList8;
                        i39 = i18;
                    }
                    objArr = objArr4;
                    jArr = jArr6;
                    arrayList7 = arrayList2;
                    arrayList6 = arrayList4;
                    c2484x2 = c2484x;
                    if (i39 != 8) {
                        break;
                    }
                } else {
                    objArr = objArr4;
                    jArr = jArr6;
                    arrayList7 = arrayList2;
                    arrayList6 = arrayList4;
                    c2484x2 = c2484x;
                }
                if (i38 == length3) {
                    break;
                }
                i38++;
                i34 = i12;
                z13 = z12;
                iArr3 = iArr;
                arrayList4 = arrayList6;
                jArr6 = jArr;
                objArr4 = objArr;
                c2484x = c2484x2;
                arrayList2 = arrayList7;
            }
        } else {
            z12 = z13;
            iArr = iArr3;
            c2453a = c2453a2;
            arrayList5 = arrayList3;
            arrayList6 = arrayList4;
            c2484x2 = c2484x;
        }
        if (arrayList12.isEmpty()) {
            i15 = i10;
            i16 = i11;
            uVar2 = uVar;
            iArr2 = iArr;
            c2484x3 = c2484x2;
        } else {
            if (arrayList12.size() > 1) {
                uVar2 = uVar;
                AbstractC2003r.K(arrayList12, new D(uVar2, 1));
            } else {
                uVar2 = uVar;
            }
            int size5 = arrayList12.size();
            int i46 = 0;
            while (i46 < size5) {
                N n15 = (N) arrayList12.get(i46);
                C2484x c2484x6 = c2484x2;
                Object objE4 = c2484x6.e(n15.getKey());
                m.c(objE4);
                B b13 = (B) objE4;
                int[] iArr4 = iArr;
                int iH3 = h(iArr4, n15);
                if (z10) {
                    N n16 = (N) AbstractC1998m.Y(arrayList);
                    long jG2 = n16.g(0);
                    i17 = n16.d() ? (int) (jG2 & 4294967295L) : (int) (jG2 >> 32);
                } else {
                    i17 = b13.f2494f;
                }
                n15.k(i17 - iH3, b13.c, i10, i11);
                if (z12) {
                    g(n15, true);
                }
                i46++;
                c2484x2 = c2484x6;
                iArr = iArr4;
            }
            i15 = i10;
            i16 = i11;
            iArr2 = iArr;
            c2484x3 = c2484x2;
            Arrays.fill(iArr2, 0, i12, 0);
        }
        if (!arrayList11.isEmpty()) {
            if (arrayList11.size() > 1) {
                AbstractC2003r.K(arrayList11, new D(uVar2, 0));
            }
            int size6 = arrayList11.size();
            int i47 = 0;
            while (i47 < size6) {
                N n17 = (N) arrayList11.get(i47);
                Object objE5 = c2484x3.e(n17.getKey());
                m.c(objE5);
                B b14 = (B) objE5;
                int iH4 = h(iArr2, n17);
                if (z10) {
                    N n18 = (N) AbstractC1998m.h0(arrayList);
                    long jG3 = n18.g(0);
                    if (n18.d()) {
                        c2484x4 = c2484x3;
                        iF = (int) (jG3 & 4294967295L);
                    } else {
                        c2484x4 = c2484x3;
                        iF = (int) (jG3 >> 32);
                    }
                } else {
                    c2484x4 = c2484x3;
                    iF = b14.f2495g - n17.f();
                }
                n17.k(iF + iH4, b14.c, i15, i16);
                if (z12) {
                    g(n17, true);
                }
                i47++;
                c2484x3 = c2484x4;
            }
        }
        Collections.reverse(arrayList12);
        arrayList.addAll(0, arrayList12);
        arrayList.addAll(arrayList11);
        arrayList6.clear();
        arrayList5.clear();
        arrayList12.clear();
        arrayList11.clear();
        c2453a.b();
    }

    public final void e(Object obj) {
        C0314y[] c0314yArr;
        B b10 = (B) this.f11488a.g(obj);
        if (b10 == null || (c0314yArr = b10.f2491a) == null) {
            return;
        }
        for (C0314y c0314y : c0314yArr) {
            if (c0314y != null) {
                c0314y.c();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0053  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void f() {
        /*
            r15 = this;
            u.x r0 = r15.f11488a
            int r1 = r0.f22645e
            if (r1 == 0) goto L5b
            java.lang.Object[] r1 = r0.c
            long[] r2 = r0.f22643a
            int r3 = r2.length
            int r3 = r3 + (-2)
            if (r3 < 0) goto L58
            r4 = 0
            r5 = r4
        L11:
            r6 = r2[r5]
            long r8 = ~r6
            r10 = 7
            long r8 = r8 << r10
            long r8 = r8 & r6
            r10 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r8 = r8 & r10
            int r8 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r8 == 0) goto L53
            int r8 = r5 - r3
            int r8 = ~r8
            int r8 = r8 >>> 31
            r9 = 8
            int r8 = 8 - r8
            r10 = r4
        L2b:
            if (r10 >= r8) goto L51
            r11 = 255(0xff, double:1.26E-321)
            long r11 = r11 & r6
            r13 = 128(0x80, double:6.3E-322)
            int r11 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r11 >= 0) goto L4d
            int r11 = r5 << 3
            int r11 = r11 + r10
            r11 = r1[r11]
            G.B r11 = (G.B) r11
            G.y[] r11 = r11.f2491a
            int r12 = r11.length
            r13 = r4
        L41:
            if (r13 >= r12) goto L4d
            r14 = r11[r13]
            if (r14 == 0) goto L4a
            r14.c()
        L4a:
            int r13 = r13 + 1
            goto L41
        L4d:
            long r6 = r6 >> r9
            int r10 = r10 + 1
            goto L2b
        L51:
            if (r8 != r9) goto L58
        L53:
            if (r5 == r3) goto L58
            int r5 = r5 + 1
            goto L11
        L58:
            r0.a()
        L5b:
            G.J r0 = G.J.l
            r15.f11489b = r0
            r0 = -1
            r15.c = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.lazy.layout.a.f():void");
    }

    public final void g(N n10, boolean z6) {
        Object objE = this.f11488a.e(n10.getKey());
        m.c(objE);
        C0314y[] c0314yArr = ((B) objE).f2491a;
        int length = c0314yArr.length;
        int i7 = 0;
        int i10 = 0;
        while (i7 < length) {
            C0314y c0314y = c0314yArr[i7];
            int i11 = i10 + 1;
            if (c0314y != null) {
                long jG = n10.g(i10);
                long j3 = c0314y.f2626j;
                if (!h.b(j3, C0314y.f2617q) && !h.b(j3, jG)) {
                    long jC = h.c(jG, j3);
                    InterfaceC2615D interfaceC2615D = c0314y.d;
                    if (interfaceC2615D != null) {
                        long jC2 = h.c(((h) c0314y.o.getValue()).f11033a, jC);
                        c0314y.e(jC2);
                        c0314y.d(true);
                        c0314y.f2621e = z6;
                        AbstractC0727y.w(c0314y.f2619a, null, null, new C0309t(c0314y, interfaceC2615D, jC2, null), 3);
                    }
                }
                c0314y.f2626j = jG;
            }
            i7++;
            i10 = i11;
        }
    }
}
