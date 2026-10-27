package X;

import A7.C0054b;
import A7.m;
import T.C0735d;
import a0.C0906b;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;
import n7.AbstractC1991f;
import n7.AbstractC1996k;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class g extends AbstractC1991f implements Collection, B7.b {
    public c l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Object[] f9955m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Object[] f9956n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public C0906b f9957p = new C0906b();

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public Object[] f9958q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public Object[] f9959r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f9960s;

    public g(c cVar, Object[] objArr, Object[] objArr2, int i7) {
        this.l = cVar;
        this.f9955m = objArr;
        this.f9956n = objArr2;
        this.o = i7;
        this.f9958q = objArr;
        this.f9959r = objArr2;
        this.f9960s = cVar.d();
    }

    public static void r(Object[] objArr, int i7, Iterator it) {
        while (i7 < 32 && it.hasNext()) {
            objArr[i7] = it.next();
            i7++;
        }
    }

    public final Object[] A() {
        Object[] objArr = new Object[33];
        objArr[32] = this.f9957p;
        return objArr;
    }

    public final Object[] B(Object obj) {
        Object[] objArr = new Object[33];
        objArr[0] = obj;
        objArr[32] = this.f9957p;
        return objArr;
    }

    public final Object[] C(Object[] objArr, int i7, int i10) {
        if (!(i10 >= 0)) {
            C0735d.X("shift should be positive");
            throw null;
        }
        if (i10 == 0) {
            return objArr;
        }
        int iC0 = io.sentry.config.a.c0(i7, i10);
        Object obj = objArr[iC0];
        m.d(obj, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
        Object objC = C((Object[]) obj, i7, i10 - 5);
        if (iC0 < 31) {
            int i11 = iC0 + 1;
            if (objArr[i11] != null) {
                if (w(objArr)) {
                    Arrays.fill(objArr, i11, 32, (Object) null);
                }
                Object[] objArrA = A();
                AbstractC1996k.D(0, 0, i11, objArr, objArrA);
                objArr = objArrA;
            }
        }
        if (objC == objArr[iC0]) {
            return objArr;
        }
        Object[] objArrY = y(objArr);
        objArrY[iC0] = objC;
        return objArrY;
    }

    public final Object[] D(Object[] objArr, int i7, int i10, e eVar) {
        Object[] objArrD;
        int iC0 = io.sentry.config.a.c0(i10 - 1, i7);
        if (i7 == 5) {
            eVar.f9952a = objArr[iC0];
            objArrD = null;
        } else {
            Object obj = objArr[iC0];
            m.d(obj, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
            objArrD = D((Object[]) obj, i7 - 5, i10, eVar);
        }
        if (objArrD == null && iC0 == 0) {
            return null;
        }
        Object[] objArrY = y(objArr);
        objArrY[iC0] = objArrD;
        return objArrY;
    }

    public final void E(Object[] objArr, int i7, int i10) {
        if (i10 == 0) {
            this.f9958q = null;
            if (objArr == null) {
                objArr = new Object[0];
            }
            this.f9959r = objArr;
            this.f9960s = i7;
            this.o = i10;
            return;
        }
        e eVar = new e(null);
        m.c(objArr);
        Object[] objArrD = D(objArr, i10, i7, eVar);
        m.c(objArrD);
        Object obj = eVar.f9952a;
        m.d(obj, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
        this.f9959r = (Object[]) obj;
        this.f9960s = i7;
        if (objArrD[1] == null) {
            this.f9958q = (Object[]) objArrD[0];
            this.o = i10 - 5;
        } else {
            this.f9958q = objArrD;
            this.o = i10;
        }
    }

    public final Object[] F(Object[] objArr, int i7, int i10, Iterator it) {
        if (!it.hasNext()) {
            C0735d.X("invalid buffersIterator");
            throw null;
        }
        if (!(i10 >= 0)) {
            C0735d.X("negative shift");
            throw null;
        }
        if (i10 == 0) {
            return (Object[]) it.next();
        }
        Object[] objArrY = y(objArr);
        int iC0 = io.sentry.config.a.c0(i7, i10);
        int i11 = i10 - 5;
        objArrY[iC0] = F((Object[]) objArrY[iC0], i7, i11, it);
        while (true) {
            iC0++;
            if (iC0 >= 32 || !it.hasNext()) {
                break;
            }
            objArrY[iC0] = F((Object[]) objArrY[iC0], 0, i11, it);
        }
        return objArrY;
    }

    public final Object[] G(Object[] objArr, int i7, Object[][] objArr2) {
        C0054b c0054bH = m.h(objArr2);
        int i10 = i7 >> 5;
        int i11 = this.o;
        Object[] objArrF = i10 < (1 << i11) ? F(objArr, i7, i11, c0054bH) : y(objArr);
        while (c0054bH.hasNext()) {
            this.o += 5;
            objArrF = B(objArrF);
            int i12 = this.o;
            F(objArrF, 1 << i12, i12, c0054bH);
        }
        return objArrF;
    }

    public final void H(Object[] objArr, Object[] objArr2, Object[] objArr3) {
        int i7 = this.f9960s;
        int i10 = i7 >> 5;
        int i11 = this.o;
        if (i10 > (1 << i11)) {
            this.f9958q = I(this.o + 5, B(objArr), objArr2);
            this.f9959r = objArr3;
            this.o += 5;
            this.f9960s++;
            return;
        }
        if (objArr == null) {
            this.f9958q = objArr2;
            this.f9959r = objArr3;
            this.f9960s = i7 + 1;
        } else {
            this.f9958q = I(i11, objArr, objArr2);
            this.f9959r = objArr3;
            this.f9960s++;
        }
    }

    public final Object[] I(int i7, Object[] objArr, Object[] objArr2) {
        int iC0 = io.sentry.config.a.c0(d() - 1, i7);
        Object[] objArrY = y(objArr);
        if (i7 == 5) {
            objArrY[iC0] = objArr2;
        } else {
            objArrY[iC0] = I(i7 - 5, (Object[]) objArrY[iC0], objArr2);
        }
        return objArrY;
    }

    public final int J(InterfaceC2782k interfaceC2782k, Object[] objArr, int i7, int i10, e eVar, ArrayList arrayList, ArrayList arrayList2) {
        if (w(objArr)) {
            arrayList.add(objArr);
        }
        Object obj = eVar.f9952a;
        m.d(obj, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
        Object[] objArr2 = (Object[]) obj;
        Object[] objArrA = objArr2;
        for (int i11 = 0; i11 < i7; i11++) {
            Object obj2 = objArr[i11];
            if (!((Boolean) interfaceC2782k.b(obj2)).booleanValue()) {
                if (i10 == 32) {
                    objArrA = !arrayList.isEmpty() ? (Object[]) arrayList.remove(arrayList.size() - 1) : A();
                    i10 = 0;
                }
                objArrA[i10] = obj2;
                i10++;
            }
        }
        eVar.f9952a = objArrA;
        if (objArr2 != objArrA) {
            arrayList2.add(objArr2);
        }
        return i10;
    }

    public final int K(InterfaceC2782k interfaceC2782k, Object[] objArr, int i7, e eVar) {
        Object[] objArrY = objArr;
        int i10 = i7;
        boolean z6 = false;
        for (int i11 = 0; i11 < i7; i11++) {
            Object obj = objArr[i11];
            if (((Boolean) interfaceC2782k.b(obj)).booleanValue()) {
                if (!z6) {
                    objArrY = y(objArr);
                    z6 = true;
                    i10 = i11;
                }
            } else if (z6) {
                objArrY[i10] = obj;
                i10++;
            }
        }
        eVar.f9952a = objArrY;
        return i10;
    }

    public final int L(InterfaceC2782k interfaceC2782k, int i7, e eVar) {
        int iK = K(interfaceC2782k, this.f9959r, i7, eVar);
        if (iK == i7) {
            return i7;
        }
        Object obj = eVar.f9952a;
        m.d(obj, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
        Object[] objArr = (Object[]) obj;
        Arrays.fill(objArr, iK, i7, (Object) null);
        this.f9959r = objArr;
        this.f9960s -= i7 - iK;
        return iK;
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x001a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean M(z7.InterfaceC2782k r19) {
        /*
            Method dump skipped, instruction units count: 246
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: X.g.M(z7.k):boolean");
    }

    public final Object[] N(Object[] objArr, int i7, int i10, e eVar) {
        int iC0 = io.sentry.config.a.c0(i10, i7);
        if (i7 == 0) {
            Object obj = objArr[iC0];
            Object[] objArrY = y(objArr);
            AbstractC1996k.D(iC0, iC0 + 1, 32, objArr, objArrY);
            objArrY[31] = eVar.f9952a;
            eVar.f9952a = obj;
            return objArrY;
        }
        int iC02 = objArr[31] == null ? io.sentry.config.a.c0(P() - 1, i7) : 31;
        Object[] objArrY2 = y(objArr);
        int i11 = i7 - 5;
        int i12 = iC0 + 1;
        if (i12 <= iC02) {
            while (true) {
                Object obj2 = objArrY2[iC02];
                m.d(obj2, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
                objArrY2[iC02] = N((Object[]) obj2, i11, 0, eVar);
                if (iC02 == i12) {
                    break;
                }
                iC02--;
            }
        }
        Object obj3 = objArrY2[iC0];
        m.d(obj3, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
        objArrY2[iC0] = N((Object[]) obj3, i11, i10, eVar);
        return objArrY2;
    }

    public final Object O(Object[] objArr, int i7, int i10, int i11) {
        int i12 = this.f9960s - i7;
        if (i12 == 1) {
            Object obj = this.f9959r[0];
            E(objArr, i7, i10);
            return obj;
        }
        Object[] objArr2 = this.f9959r;
        Object obj2 = objArr2[i11];
        Object[] objArrY = y(objArr2);
        AbstractC1996k.D(i11, i11 + 1, i12, objArr2, objArrY);
        objArrY[i12 - 1] = null;
        this.f9958q = objArr;
        this.f9959r = objArrY;
        this.f9960s = (i7 + i12) - 1;
        this.o = i10;
        return obj2;
    }

    public final int P() {
        int i7 = this.f9960s;
        if (i7 <= 32) {
            return 0;
        }
        return (i7 - 1) & (-32);
    }

    public final Object[] Q(Object[] objArr, int i7, int i10, Object obj, e eVar) {
        int iC0 = io.sentry.config.a.c0(i10, i7);
        Object[] objArrY = y(objArr);
        if (i7 != 0) {
            Object obj2 = objArrY[iC0];
            m.d(obj2, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
            objArrY[iC0] = Q((Object[]) obj2, i7 - 5, i10, obj, eVar);
            return objArrY;
        }
        if (objArrY != objArr) {
            ((AbstractList) this).modCount++;
        }
        eVar.f9952a = objArrY[iC0];
        objArrY[iC0] = obj;
        return objArrY;
    }

    public final void R(Collection collection, int i7, Object[] objArr, int i10, Object[][] objArr2, int i11, Object[] objArr3) {
        Object[] objArrA;
        if (i11 < 1) {
            C0735d.X("requires at least one nullBuffer");
            throw null;
        }
        Object[] objArrY = y(objArr);
        objArr2[0] = objArrY;
        int i12 = i7 & 31;
        int size = ((collection.size() + i7) - 1) & 31;
        int i13 = (i10 - i12) + size;
        if (i13 < 32) {
            AbstractC1996k.D(size + 1, i12, i10, objArrY, objArr3);
        } else {
            int i14 = i13 - 31;
            if (i11 == 1) {
                objArrA = objArrY;
            } else {
                objArrA = A();
                i11--;
                objArr2[i11] = objArrA;
            }
            int i15 = i10 - i14;
            AbstractC1996k.D(0, i15, i10, objArrY, objArr3);
            AbstractC1996k.D(size + 1, i12, i15, objArrY, objArrA);
            objArr3 = objArrA;
        }
        Iterator it = collection.iterator();
        r(objArrY, i12, it);
        for (int i16 = 1; i16 < i11; i16++) {
            Object[] objArrA2 = A();
            r(objArrA2, 0, it);
            objArr2[i16] = objArrA2;
        }
        r(objArr3, 0, it);
    }

    public final int S() {
        int i7 = this.f9960s;
        return i7 <= 32 ? i7 : i7 - ((i7 - 1) & (-32));
    }

    @Override // java.util.AbstractList, java.util.List
    public final void add(int i7, Object obj) {
        com.bumptech.glide.d.s(i7, d());
        if (i7 == d()) {
            add(obj);
            return;
        }
        ((AbstractList) this).modCount++;
        int iP = P();
        if (i7 >= iP) {
            v(i7 - iP, obj, this.f9958q);
            return;
        }
        e eVar = new e(null);
        Object[] objArr = this.f9958q;
        m.c(objArr);
        v(0, eVar.f9952a, u(objArr, this.o, i7, obj, eVar));
    }

    @Override // java.util.AbstractList, java.util.List
    public final boolean addAll(int i7, Collection collection) {
        Object[] objArrA;
        com.bumptech.glide.d.s(i7, this.f9960s);
        if (i7 == this.f9960s) {
            return addAll(collection);
        }
        if (collection.isEmpty()) {
            return false;
        }
        ((AbstractList) this).modCount++;
        int i10 = (i7 >> 5) << 5;
        int size = ((collection.size() + (this.f9960s - i10)) - 1) / 32;
        if (size == 0) {
            int i11 = i7 & 31;
            int size2 = ((collection.size() + i7) - 1) & 31;
            Object[] objArr = this.f9959r;
            Object[] objArrY = y(objArr);
            AbstractC1996k.D(size2 + 1, i11, S(), objArr, objArrY);
            r(objArrY, i11, collection.iterator());
            this.f9959r = objArrY;
            this.f9960s = collection.size() + this.f9960s;
            return true;
        }
        Object[][] objArr2 = new Object[size][];
        int iS = S();
        int size3 = collection.size() + this.f9960s;
        if (size3 > 32) {
            size3 -= (size3 - 1) & (-32);
        }
        if (i7 >= P()) {
            objArrA = A();
            R(collection, i7, this.f9959r, iS, objArr2, size, objArrA);
        } else if (size3 > iS) {
            int i12 = size3 - iS;
            objArrA = z(i12, this.f9959r);
            t(collection, i7, i12, objArr2, size, objArrA);
        } else {
            Object[] objArr3 = this.f9959r;
            objArrA = A();
            int i13 = iS - size3;
            AbstractC1996k.D(0, i13, iS, objArr3, objArrA);
            int i14 = 32 - i13;
            Object[] objArrZ = z(i14, this.f9959r);
            int i15 = size - 1;
            objArr2[i15] = objArrZ;
            t(collection, i7, i14, objArr2, i15, objArrZ);
        }
        this.f9958q = G(this.f9958q, i10, objArr2);
        this.f9959r = objArrA;
        this.f9960s = collection.size() + this.f9960s;
        return true;
    }

    @Override // n7.AbstractC1991f
    public final int d() {
        return this.f9960s;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i7) {
        Object[] objArr;
        com.bumptech.glide.d.r(i7, d());
        if (P() <= i7) {
            objArr = this.f9959r;
        } else {
            Object[] objArr2 = this.f9958q;
            m.c(objArr2);
            for (int i10 = this.o; i10 > 0; i10 -= 5) {
                Object[] objArr3 = objArr2[io.sentry.config.a.c0(i7, i10)];
                m.d(objArr3, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
                objArr2 = objArr3;
            }
            objArr = objArr2;
        }
        return objArr[i7 & 31];
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator iterator() {
        return listIterator(0);
    }

    @Override // n7.AbstractC1991f
    public final Object l(int i7) {
        com.bumptech.glide.d.r(i7, d());
        ((AbstractList) this).modCount++;
        int iP = P();
        if (i7 >= iP) {
            return O(this.f9958q, iP, this.o, i7 - iP);
        }
        e eVar = new e(this.f9959r[0]);
        Object[] objArr = this.f9958q;
        m.c(objArr);
        O(N(objArr, this.o, i7, eVar), iP, this.o, 0);
        return eVar.f9952a;
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator listIterator(int i7) {
        com.bumptech.glide.d.s(i7, this.f9960s);
        return new i(this, i7);
    }

    public final c q() {
        c fVar;
        Object[] objArr = this.f9958q;
        if (objArr == this.f9955m && this.f9959r == this.f9956n) {
            fVar = this.l;
        } else {
            this.f9957p = new C0906b();
            this.f9955m = objArr;
            Object[] objArr2 = this.f9959r;
            this.f9956n = objArr2;
            if (objArr != null) {
                fVar = new f(objArr, objArr2, this.f9960s, this.o);
            } else if (objArr2.length == 0) {
                fVar = j.f9965m;
            } else {
                Object[] objArrCopyOf = Arrays.copyOf(this.f9959r, this.f9960s);
                m.e(objArrCopyOf, "copyOf(this, newSize)");
                fVar = new j(objArrCopyOf);
            }
        }
        this.l = fVar;
        return fVar;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean removeAll(Collection collection) {
        return M(new b(1, collection));
    }

    public final int s() {
        return ((AbstractList) this).modCount;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object set(int i7, Object obj) {
        com.bumptech.glide.d.r(i7, d());
        if (P() > i7) {
            e eVar = new e(null);
            Object[] objArr = this.f9958q;
            m.c(objArr);
            this.f9958q = Q(objArr, this.o, i7, obj, eVar);
            return eVar.f9952a;
        }
        Object[] objArrY = y(this.f9959r);
        if (objArrY != this.f9959r) {
            ((AbstractList) this).modCount++;
        }
        int i10 = i7 & 31;
        Object obj2 = objArrY[i10];
        objArrY[i10] = obj;
        this.f9959r = objArrY;
        return obj2;
    }

    public final void t(Collection collection, int i7, int i10, Object[][] objArr, int i11, Object[] objArr2) {
        if (this.f9958q == null) {
            throw new IllegalStateException("root is null");
        }
        int i12 = i7 >> 5;
        a aVarX = x(P() >> 5);
        int i13 = i11;
        Object[] objArrZ = objArr2;
        while (aVarX.l - 1 != i12) {
            Object[] objArr3 = (Object[]) aVarX.previous();
            AbstractC1996k.D(0, 32 - i10, 32, objArr3, objArrZ);
            objArrZ = z(i10, objArr3);
            i13--;
            objArr[i13] = objArrZ;
        }
        Object[] objArr4 = (Object[]) aVarX.previous();
        int iP = i11 - (((P() >> 5) - 1) - i12);
        if (iP < i11) {
            objArr2 = objArr[iP];
            m.c(objArr2);
        }
        R(collection, i7, objArr4, 32, objArr, iP, objArr2);
    }

    public final Object[] u(Object[] objArr, int i7, int i10, Object obj, e eVar) {
        Object obj2;
        int iC0 = io.sentry.config.a.c0(i10, i7);
        if (i7 == 0) {
            eVar.f9952a = objArr[31];
            Object[] objArrY = y(objArr);
            AbstractC1996k.D(iC0 + 1, iC0, 31, objArr, objArrY);
            objArrY[iC0] = obj;
            return objArrY;
        }
        Object[] objArrY2 = y(objArr);
        int i11 = i7 - 5;
        Object obj3 = objArrY2[iC0];
        m.d(obj3, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
        objArrY2[iC0] = u((Object[]) obj3, i11, i10, obj, eVar);
        while (true) {
            iC0++;
            if (iC0 >= 32 || (obj2 = objArrY2[iC0]) == null) {
                break;
            }
            objArrY2[iC0] = u((Object[]) obj2, i11, 0, eVar.f9952a, eVar);
        }
        return objArrY2;
    }

    public final void v(int i7, Object obj, Object[] objArr) {
        int iS = S();
        Object[] objArrY = y(this.f9959r);
        if (iS >= 32) {
            Object[] objArr2 = this.f9959r;
            Object obj2 = objArr2[31];
            AbstractC1996k.D(i7 + 1, i7, 31, objArr2, objArrY);
            objArrY[i7] = obj;
            H(objArr, objArrY, B(obj2));
            return;
        }
        AbstractC1996k.D(i7 + 1, i7, iS, this.f9959r, objArrY);
        objArrY[i7] = obj;
        this.f9958q = objArr;
        this.f9959r = objArrY;
        this.f9960s++;
    }

    public final boolean w(Object[] objArr) {
        return objArr.length == 33 && objArr[32] == this.f9957p;
    }

    public final a x(int i7) {
        Object[] objArr = this.f9958q;
        if (objArr == null) {
            throw new IllegalStateException("Invalid root");
        }
        int iP = P() >> 5;
        com.bumptech.glide.d.s(i7, iP);
        int i10 = this.o;
        return i10 == 0 ? new d(objArr, i7) : new k(objArr, i7, iP, i10 / 5);
    }

    public final Object[] y(Object[] objArr) {
        if (objArr == null) {
            return A();
        }
        if (w(objArr)) {
            return objArr;
        }
        Object[] objArrA = A();
        int length = objArr.length;
        if (length > 32) {
            length = 32;
        }
        AbstractC1996k.G(0, length, 6, objArr, objArrA);
        return objArrA;
    }

    public final Object[] z(int i7, Object[] objArr) {
        if (w(objArr)) {
            AbstractC1996k.D(i7, 0, 32 - i7, objArr, objArr);
            return objArr;
        }
        Object[] objArrA = A();
        AbstractC1996k.D(i7, 0, 32 - i7, objArr, objArrA);
        return objArrA;
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator listIterator() {
        return listIterator(0);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean add(Object obj) {
        ((AbstractList) this).modCount++;
        int iS = S();
        if (iS < 32) {
            Object[] objArrY = y(this.f9959r);
            objArrY[iS] = obj;
            this.f9959r = objArrY;
            this.f9960s = d() + 1;
        } else {
            H(this.f9958q, this.f9959r, B(obj));
        }
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        if (collection.isEmpty()) {
            return false;
        }
        ((AbstractList) this).modCount++;
        int iS = S();
        Iterator it = collection.iterator();
        if (32 - iS >= collection.size()) {
            Object[] objArrY = y(this.f9959r);
            r(objArrY, iS, it);
            this.f9959r = objArrY;
            this.f9960s = collection.size() + this.f9960s;
        } else {
            int size = ((collection.size() + iS) - 1) / 32;
            Object[][] objArr = new Object[size][];
            Object[] objArrY2 = y(this.f9959r);
            r(objArrY2, iS, it);
            objArr[0] = objArrY2;
            for (int i7 = 1; i7 < size; i7++) {
                Object[] objArrA = A();
                r(objArrA, 0, it);
                objArr[i7] = objArrA;
            }
            this.f9958q = G(this.f9958q, P(), objArr);
            Object[] objArrA2 = A();
            r(objArrA2, 0, it);
            this.f9959r = objArrA2;
            this.f9960s = collection.size() + this.f9960s;
        }
        return true;
    }
}
