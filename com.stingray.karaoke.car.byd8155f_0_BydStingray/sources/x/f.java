package X;

import A7.m;
import T.C0735d;
import java.util.Arrays;
import java.util.ListIterator;
import n7.AbstractC1996k;

/* JADX INFO: loaded from: classes.dex */
public final class f extends c {
    public final Object[] l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Object[] f9953m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f9954n;
    public final int o;

    public f(Object[] objArr, Object[] objArr2, int i7, int i10) {
        this.l = objArr;
        this.f9953m = objArr2;
        this.f9954n = i7;
        this.o = i10;
        if (d() > 32) {
            int length = objArr2.length;
            return;
        }
        C0735d.X("Trie-based persistent vector should have at least 33 elements, got " + d());
        throw null;
    }

    public static Object[] E(Object[] objArr, int i7, int i10, Object obj) {
        int iC0 = io.sentry.config.a.c0(i10, i7);
        Object[] objArrCopyOf = Arrays.copyOf(objArr, 32);
        m.e(objArrCopyOf, "copyOf(this, newSize)");
        if (i7 == 0) {
            objArrCopyOf[iC0] = obj;
        } else {
            Object obj2 = objArrCopyOf[iC0];
            m.d(obj2, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
            objArrCopyOf[iC0] = E((Object[]) obj2, i7 - 5, i10, obj);
        }
        return objArrCopyOf;
    }

    public static Object[] w(Object[] objArr, int i7, int i10, Object obj, e eVar) {
        Object[] objArrCopyOf;
        int iC0 = io.sentry.config.a.c0(i10, i7);
        if (i7 == 0) {
            if (iC0 == 0) {
                objArrCopyOf = new Object[32];
            } else {
                objArrCopyOf = Arrays.copyOf(objArr, 32);
                m.e(objArrCopyOf, "copyOf(this, newSize)");
            }
            AbstractC1996k.D(iC0 + 1, iC0, 31, objArr, objArrCopyOf);
            eVar.f9952a = objArr[31];
            objArrCopyOf[iC0] = obj;
            return objArrCopyOf;
        }
        Object[] objArrCopyOf2 = Arrays.copyOf(objArr, 32);
        m.e(objArrCopyOf2, "copyOf(this, newSize)");
        int i11 = i7 - 5;
        Object obj2 = objArr[iC0];
        m.d(obj2, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
        objArrCopyOf2[iC0] = w((Object[]) obj2, i11, i10, obj, eVar);
        while (true) {
            iC0++;
            if (iC0 >= 32 || objArrCopyOf2[iC0] == null) {
                break;
            }
            Object obj3 = objArr[iC0];
            m.d(obj3, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
            objArrCopyOf2[iC0] = w((Object[]) obj3, i11, 0, eVar.f9952a, eVar);
        }
        return objArrCopyOf2;
    }

    public static Object[] y(Object[] objArr, int i7, int i10, e eVar) {
        Object[] objArrY;
        int iC0 = io.sentry.config.a.c0(i10, i7);
        if (i7 == 5) {
            eVar.f9952a = objArr[iC0];
            objArrY = null;
        } else {
            Object obj = objArr[iC0];
            m.d(obj, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
            objArrY = y((Object[]) obj, i7 - 5, i10, eVar);
        }
        if (objArrY == null && iC0 == 0) {
            return null;
        }
        Object[] objArrCopyOf = Arrays.copyOf(objArr, 32);
        m.e(objArrCopyOf, "copyOf(this, newSize)");
        objArrCopyOf[iC0] = objArrY;
        return objArrCopyOf;
    }

    public final Object[] A(int i7, Object[] objArr, Object[] objArr2) {
        Object[] objArrCopyOf;
        int iC0 = io.sentry.config.a.c0(d() - 1, i7);
        if (objArr != null) {
            objArrCopyOf = Arrays.copyOf(objArr, 32);
            m.e(objArrCopyOf, "copyOf(this, newSize)");
        } else {
            objArrCopyOf = new Object[32];
        }
        if (i7 == 5) {
            objArrCopyOf[iC0] = objArr2;
        } else {
            objArrCopyOf[iC0] = A(i7 - 5, (Object[]) objArrCopyOf[iC0], objArr2);
        }
        return objArrCopyOf;
    }

    public final Object[] B(Object[] objArr, int i7, int i10, e eVar) {
        Object[] objArrCopyOf;
        int iC0 = io.sentry.config.a.c0(i10, i7);
        if (i7 == 0) {
            if (iC0 == 0) {
                objArrCopyOf = new Object[32];
            } else {
                objArrCopyOf = Arrays.copyOf(objArr, 32);
                m.e(objArrCopyOf, "copyOf(this, newSize)");
            }
            AbstractC1996k.D(iC0, iC0 + 1, 32, objArr, objArrCopyOf);
            objArrCopyOf[31] = eVar.f9952a;
            eVar.f9952a = objArr[iC0];
            return objArrCopyOf;
        }
        int iC02 = objArr[31] == null ? io.sentry.config.a.c0(D() - 1, i7) : 31;
        Object[] objArrCopyOf2 = Arrays.copyOf(objArr, 32);
        m.e(objArrCopyOf2, "copyOf(this, newSize)");
        int i11 = i7 - 5;
        int i12 = iC0 + 1;
        if (i12 <= iC02) {
            while (true) {
                Object obj = objArrCopyOf2[iC02];
                m.d(obj, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
                objArrCopyOf2[iC02] = B((Object[]) obj, i11, 0, eVar);
                if (iC02 == i12) {
                    break;
                }
                iC02--;
            }
        }
        Object obj2 = objArrCopyOf2[iC0];
        m.d(obj2, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
        objArrCopyOf2[iC0] = B((Object[]) obj2, i11, i10, eVar);
        return objArrCopyOf2;
    }

    public final c C(Object[] objArr, int i7, int i10, int i11) {
        f fVar;
        int i12 = this.f9954n - i7;
        if (i12 != 1) {
            Object[] objArr2 = this.f9953m;
            Object[] objArrCopyOf = Arrays.copyOf(objArr2, 32);
            m.e(objArrCopyOf, "copyOf(this, newSize)");
            int i13 = i12 - 1;
            if (i11 < i13) {
                AbstractC1996k.D(i11, i11 + 1, i12, objArr2, objArrCopyOf);
            }
            objArrCopyOf[i13] = null;
            return new f(objArr, objArrCopyOf, (i7 + i12) - 1, i10);
        }
        if (i10 == 0) {
            if (objArr.length == 33) {
                objArr = Arrays.copyOf(objArr, 32);
                m.e(objArr, "copyOf(this, newSize)");
            }
            return new j(objArr);
        }
        e eVar = new e(null);
        Object[] objArrY = y(objArr, i10, i7 - 1, eVar);
        m.c(objArrY);
        Object obj = eVar.f9952a;
        m.d(obj, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
        Object[] objArr3 = (Object[]) obj;
        if (objArrY[1] == null) {
            Object obj2 = objArrY[0];
            m.d(obj2, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
            fVar = new f((Object[]) obj2, objArr3, i7, i10 - 5);
        } else {
            fVar = new f(objArrY, objArr3, i7, i10);
        }
        return fVar;
    }

    public final int D() {
        return (this.f9954n - 1) & (-32);
    }

    @Override // n7.AbstractC1986a
    public final int d() {
        return this.f9954n;
    }

    @Override // java.util.List
    public final Object get(int i7) {
        Object[] objArr;
        com.bumptech.glide.d.r(i7, d());
        if (D() <= i7) {
            objArr = this.f9953m;
        } else {
            Object[] objArr2 = this.l;
            for (int i10 = this.o; i10 > 0; i10 -= 5) {
                Object[] objArr3 = objArr2[io.sentry.config.a.c0(i7, i10)];
                m.d(objArr3, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
                objArr2 = objArr3;
            }
            objArr = objArr2;
        }
        return objArr[i7 & 31];
    }

    @Override // X.c
    public final c l(int i7, Object obj) {
        int i10 = this.f9954n;
        com.bumptech.glide.d.s(i7, i10);
        if (i7 == i10) {
            return q(obj);
        }
        int iD = D();
        Object[] objArr = this.l;
        if (i7 >= iD) {
            return x(i7 - iD, obj, objArr);
        }
        e eVar = new e(null);
        return x(0, eVar.f9952a, w(objArr, this.o, i7, obj, eVar));
    }

    @Override // n7.AbstractC1989d, java.util.List
    public final ListIterator listIterator(int i7) {
        com.bumptech.glide.d.s(i7, this.f9954n);
        return new h(i7, this.f9954n, (this.o / 5) + 1, this.l, this.f9953m);
    }

    @Override // X.c
    public final c q(Object obj) {
        int iD = D();
        int i7 = this.f9954n;
        int i10 = i7 - iD;
        Object[] objArr = this.l;
        Object[] objArr2 = this.f9953m;
        if (i10 >= 32) {
            Object[] objArr3 = new Object[32];
            objArr3[0] = obj;
            return z(objArr, objArr2, objArr3);
        }
        Object[] objArrCopyOf = Arrays.copyOf(objArr2, 32);
        m.e(objArrCopyOf, "copyOf(this, newSize)");
        objArrCopyOf[i10] = obj;
        return new f(objArr, objArrCopyOf, i7 + 1, this.o);
    }

    @Override // X.c
    public final g s() {
        return new g(this, this.l, this.f9953m, this.o);
    }

    @Override // X.c
    public final c t(b bVar) {
        g gVar = new g(this, this.l, this.f9953m, this.o);
        gVar.M(bVar);
        return gVar.q();
    }

    @Override // X.c
    public final c u(int i7) {
        com.bumptech.glide.d.r(i7, this.f9954n);
        int iD = D();
        Object[] objArr = this.l;
        int i10 = this.o;
        return i7 >= iD ? C(objArr, iD, i10, i7 - iD) : C(B(objArr, i10, i7, new e(this.f9953m[0])), iD, i10, 0);
    }

    @Override // X.c
    public final c v(int i7, Object obj) {
        int i10 = this.f9954n;
        com.bumptech.glide.d.r(i7, i10);
        int iD = D();
        Object[] objArr = this.l;
        Object[] objArr2 = this.f9953m;
        int i11 = this.o;
        if (iD > i7) {
            return new f(E(objArr, i11, i7, obj), objArr2, i10, i11);
        }
        Object[] objArrCopyOf = Arrays.copyOf(objArr2, 32);
        m.e(objArrCopyOf, "copyOf(this, newSize)");
        objArrCopyOf[i7 & 31] = obj;
        return new f(objArr, objArrCopyOf, i10, i11);
    }

    public final f x(int i7, Object obj, Object[] objArr) {
        int iD = D();
        int i10 = this.f9954n;
        int i11 = i10 - iD;
        Object[] objArr2 = this.f9953m;
        Object[] objArrCopyOf = Arrays.copyOf(objArr2, 32);
        m.e(objArrCopyOf, "copyOf(this, newSize)");
        if (i11 < 32) {
            AbstractC1996k.D(i7 + 1, i7, i11, objArr2, objArrCopyOf);
            objArrCopyOf[i7] = obj;
            return new f(objArr, objArrCopyOf, i10 + 1, this.o);
        }
        Object obj2 = objArr2[31];
        AbstractC1996k.D(i7 + 1, i7, i11 - 1, objArr2, objArrCopyOf);
        objArrCopyOf[i7] = obj;
        Object[] objArr3 = new Object[32];
        objArr3[0] = obj2;
        return z(objArr, objArrCopyOf, objArr3);
    }

    public final f z(Object[] objArr, Object[] objArr2, Object[] objArr3) {
        int i7 = this.f9954n;
        int i10 = i7 >> 5;
        int i11 = this.o;
        if (i10 <= (1 << i11)) {
            return new f(A(i11, objArr, objArr2), objArr3, i7 + 1, i11);
        }
        Object[] objArr4 = new Object[32];
        objArr4[0] = objArr;
        int i12 = i11 + 5;
        return new f(A(i12, objArr4, objArr2), objArr3, i7 + 1, i12);
    }
}
