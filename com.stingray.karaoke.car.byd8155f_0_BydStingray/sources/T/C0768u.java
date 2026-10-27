package T;

import N1.C0420a;
import U.C0800a;
import android.os.Trace;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import u.C2453A;
import u.C2476p;
import u.C2484x;
import u.C2486z;

/* JADX INFO: renamed from: T.u, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0768u implements r {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public final B2.P f8368A;
    public final C0761q B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public boolean f8369C;
    public final AbstractC0764s l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final l3.P f8370m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final AtomicReference f8371n = new AtomicReference(null);
    public final Object o = new Object();

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final C2486z f8372p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final z0 f8373q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final C0420a f8374r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final C2453A f8375s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final C2453A f8376t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final C0420a f8377u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final C0800a f8378v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final C0800a f8379w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final C0420a f8380x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public C0420a f8381y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public boolean f8382z;

    public C0768u(AbstractC0764s abstractC0764s, l3.P p10) {
        this.l = abstractC0764s;
        this.f8370m = p10;
        C2486z c2486z = new C2486z(new C2453A());
        this.f8372p = c2486z;
        z0 z0Var = new z0();
        if (abstractC0764s.c()) {
            z0Var.f8407u = new C2476p();
        }
        if (abstractC0764s.e()) {
            z0Var.l();
        }
        this.f8373q = z0Var;
        this.f8374r = new C0420a(12);
        this.f8375s = new C2453A();
        this.f8376t = new C2453A();
        this.f8377u = new C0420a(12);
        C0800a c0800a = new C0800a();
        this.f8378v = c0800a;
        C0800a c0800a2 = new C0800a();
        this.f8379w = c0800a2;
        this.f8380x = new C0420a(12);
        this.f8381y = new C0420a(12);
        B2.P p11 = new B2.P();
        p11.l = false;
        this.f8368A = p11;
        C0761q c0761q = new C0761q(p10, abstractC0764s, z0Var, c2486z, c0800a, c0800a2, this);
        abstractC0764s.k(c0761q);
        this.B = c0761q;
        boolean z6 = abstractC0764s instanceof C0767t0;
        b0.b bVar = AbstractC0747j.f8262a;
    }

    public final void a() {
        this.f8371n.set(null);
        this.f8378v.d.Q();
        this.f8379w.d.Q();
        C2486z c2486z = this.f8372p;
        if (c2486z.l.g()) {
            return;
        }
        new ArrayList();
        new ArrayList();
        new ArrayList();
        new ArrayList();
        if (c2486z.l.g()) {
            return;
        }
        Trace.beginSection("Compose:abandons");
        try {
            Iterator it = c2486z.iterator();
            while (true) {
                P8.i iVar = (P8.i) it;
                if (!iVar.hasNext()) {
                    return;
                }
                InterfaceC0769u0 interfaceC0769u0 = (InterfaceC0769u0) iVar.next();
                iVar.remove();
                interfaceC0769u0.c();
            }
        } finally {
            Trace.endSection();
        }
    }

    public final void b(Object obj, boolean z6) {
        Object objE = ((C2484x) this.f8374r.l).e(obj);
        if (objE == null) {
            return;
        }
        boolean z10 = objE instanceof C2453A;
        C2453A c2453a = this.f8375s;
        C2453A c2453a2 = this.f8376t;
        C0420a c0420a = this.f8380x;
        if (!z10) {
            C0754m0 c0754m0 = (C0754m0) objE;
            if (c0420a.n(obj, c0754m0) || c0754m0.c(obj) == 1) {
                return;
            }
            if (c0754m0.f8275g == null || z6) {
                c2453a.a(c0754m0);
                return;
            } else {
                c2453a2.a(c0754m0);
                return;
            }
        }
        C2453A c2453a3 = (C2453A) objE;
        Object[] objArr = c2453a3.f22580b;
        long[] jArr = c2453a3.f22579a;
        int length = jArr.length - 2;
        if (length < 0) {
            return;
        }
        int i7 = 0;
        while (true) {
            long j3 = jArr[i7];
            if ((((~j3) << 7) & j3 & (-9187201950435737472L)) != -9187201950435737472L) {
                int i10 = 8 - ((~(i7 - length)) >>> 31);
                for (int i11 = 0; i11 < i10; i11++) {
                    if ((255 & j3) < 128) {
                        C0754m0 c0754m02 = (C0754m0) objArr[(i7 << 3) + i11];
                        if (!c0420a.n(obj, c0754m02) && c0754m02.c(obj) != 1) {
                            if (c0754m02.f8275g == null || z6) {
                                c2453a.a(c0754m02);
                            } else {
                                c2453a2.a(c0754m02);
                            }
                        }
                    }
                    j3 >>= 8;
                }
                if (i10 != 8) {
                    return;
                }
            }
            if (i7 == length) {
                return;
            } else {
                i7++;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x018a A[EDGE_INSN: B:69:0x018a->B:216:0x0194 BREAK  A[LOOP:13: B:59:0x0150->B:70:0x018c]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void c(java.util.Set r33, boolean r34) {
        /*
            Method dump skipped, instruction units count: 1117
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: T.C0768u.c(java.util.Set, boolean):void");
    }

    public final void d() {
        synchronized (this.o) {
            try {
                e(this.f8378v);
                n();
            } catch (Throwable th) {
                try {
                    try {
                        if (!this.f8372p.l.g()) {
                            C2486z c2486z = this.f8372p;
                            new ArrayList();
                            new ArrayList();
                            new ArrayList();
                            new ArrayList();
                            if (!c2486z.l.g()) {
                                Trace.beginSection("Compose:abandons");
                                try {
                                    Iterator it = c2486z.iterator();
                                    while (((P8.l) ((P8.i) it).f5471m).hasNext()) {
                                        InterfaceC0769u0 interfaceC0769u0 = (InterfaceC0769u0) ((P8.l) ((P8.i) it).f5471m).next();
                                        ((P8.i) it).remove();
                                        interfaceC0769u0.c();
                                    }
                                    Trace.endSection();
                                } catch (Throwable th2) {
                                    Trace.endSection();
                                    throw th2;
                                }
                            }
                        }
                        throw th;
                    } catch (Exception e10) {
                        a();
                        throw e10;
                    }
                } catch (Throwable th3) {
                    throw th3;
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:110:0x01f1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void e(U.C0800a r32) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 501
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: T.C0768u.e(U.a):void");
    }

    public final void f() {
        synchronized (this.o) {
            try {
                if (this.f8379w.d.T()) {
                    e(this.f8379w);
                }
            } catch (Throwable th) {
                try {
                    try {
                        if (!this.f8372p.l.g()) {
                            C2486z c2486z = this.f8372p;
                            new ArrayList();
                            new ArrayList();
                            new ArrayList();
                            new ArrayList();
                            if (!c2486z.l.g()) {
                                Trace.beginSection("Compose:abandons");
                                try {
                                    Iterator it = c2486z.iterator();
                                    while (true) {
                                        P8.i iVar = (P8.i) it;
                                        if (!iVar.hasNext()) {
                                            break;
                                        }
                                        InterfaceC0769u0 interfaceC0769u0 = (InterfaceC0769u0) iVar.next();
                                        iVar.remove();
                                        interfaceC0769u0.c();
                                    }
                                    Trace.endSection();
                                } catch (Throwable th2) {
                                    Trace.endSection();
                                    throw th2;
                                }
                            }
                        }
                        throw th;
                    } catch (Exception e10) {
                        a();
                        throw e10;
                    }
                } catch (Throwable th3) {
                    throw th3;
                }
            }
        }
    }

    public final void g() {
        synchronized (this.o) {
            try {
                this.B.f8321u = null;
                if (!this.f8372p.l.g()) {
                    C2486z c2486z = this.f8372p;
                    new ArrayList();
                    new ArrayList();
                    new ArrayList();
                    new ArrayList();
                    if (!c2486z.l.g()) {
                        Trace.beginSection("Compose:abandons");
                        try {
                            Iterator it = c2486z.iterator();
                            while (true) {
                                P8.i iVar = (P8.i) it;
                                if (!iVar.hasNext()) {
                                    break;
                                }
                                InterfaceC0769u0 interfaceC0769u0 = (InterfaceC0769u0) iVar.next();
                                iVar.remove();
                                interfaceC0769u0.c();
                            }
                            Trace.endSection();
                        } finally {
                        }
                    }
                }
            } catch (Throwable th) {
                try {
                    try {
                        if (!this.f8372p.l.g()) {
                            C2486z c2486z2 = this.f8372p;
                            new ArrayList();
                            new ArrayList();
                            new ArrayList();
                            new ArrayList();
                            if (!c2486z2.l.g()) {
                                Trace.beginSection("Compose:abandons");
                                try {
                                    Iterator it2 = c2486z2.iterator();
                                    while (true) {
                                        P8.i iVar2 = (P8.i) it2;
                                        if (!iVar2.hasNext()) {
                                            break;
                                        }
                                        InterfaceC0769u0 interfaceC0769u02 = (InterfaceC0769u0) iVar2.next();
                                        iVar2.remove();
                                        interfaceC0769u02.c();
                                    }
                                    Trace.endSection();
                                } finally {
                                }
                            }
                        }
                        throw th;
                    } catch (Exception e10) {
                        a();
                        throw e10;
                    }
                } catch (Throwable th2) {
                    throw th2;
                }
            }
        }
    }

    public final void h() {
        long[] jArr;
        long[] jArr2;
        int i7;
        int i10;
        int i11;
        int i12;
        boolean zG;
        Object[] objArr;
        long[] jArr3;
        Object[] objArr2;
        long[] jArr4;
        C0768u c0768u = this;
        C2484x c2484x = (C2484x) c0768u.f8377u.l;
        long[] jArr5 = c2484x.f22643a;
        int length = jArr5.length - 2;
        long j3 = 255;
        char c = 7;
        long j10 = -9187201950435737472L;
        int i13 = 8;
        if (length >= 0) {
            int i14 = 0;
            while (true) {
                long j11 = jArr5[i14];
                if ((((~j11) << c) & j11 & j10) != j10) {
                    int i15 = 8 - ((~(i14 - length)) >>> 31);
                    int i16 = 0;
                    while (i16 < i15) {
                        if ((j11 & j3) < 128) {
                            int i17 = (i14 << 3) + i16;
                            Object obj = c2484x.f22644b[i17];
                            Object obj2 = c2484x.c[i17];
                            boolean z6 = obj2 instanceof C2453A;
                            C0420a c0420a = c0768u.f8374r;
                            if (z6) {
                                A7.m.d(obj2, "null cannot be cast to non-null type androidx.collection.MutableScatterSet<Scope of androidx.compose.runtime.collection.ScopeMap.removeScopeIf$lambda$2>");
                                C2453A c2453a = (C2453A) obj2;
                                Object[] objArr3 = c2453a.f22580b;
                                long[] jArr6 = c2453a.f22579a;
                                int length2 = jArr6.length - 2;
                                jArr2 = jArr5;
                                i7 = length;
                                if (length2 >= 0) {
                                    int i18 = 0;
                                    while (true) {
                                        long j12 = jArr6[i18];
                                        i10 = i15;
                                        i11 = i16;
                                        if ((((~j12) << 7) & j12 & (-9187201950435737472L)) != -9187201950435737472L) {
                                            int i19 = 8 - ((~(i18 - length2)) >>> 31);
                                            int i20 = 0;
                                            while (i20 < i19) {
                                                if ((j12 & 255) < 128) {
                                                    jArr4 = jArr6;
                                                    int i21 = (i18 << 3) + i20;
                                                    objArr2 = objArr3;
                                                    if (!((C2484x) c0420a.l).b((D) objArr3[i21])) {
                                                        c2453a.k(i21);
                                                    }
                                                } else {
                                                    objArr2 = objArr3;
                                                    jArr4 = jArr6;
                                                }
                                                j12 >>= 8;
                                                i20++;
                                                jArr6 = jArr4;
                                                objArr3 = objArr2;
                                            }
                                            objArr = objArr3;
                                            jArr3 = jArr6;
                                            if (i19 != 8) {
                                                break;
                                            }
                                        } else {
                                            objArr = objArr3;
                                            jArr3 = jArr6;
                                        }
                                        if (i18 == length2) {
                                            break;
                                        }
                                        i18++;
                                        i15 = i10;
                                        i16 = i11;
                                        jArr6 = jArr3;
                                        objArr3 = objArr;
                                    }
                                } else {
                                    i10 = i15;
                                    i11 = i16;
                                }
                                zG = c2453a.g();
                            } else {
                                jArr2 = jArr5;
                                i7 = length;
                                i10 = i15;
                                i11 = i16;
                                A7.m.d(obj2, "null cannot be cast to non-null type Scope of androidx.compose.runtime.collection.ScopeMap.removeScopeIf$lambda$2");
                                zG = !((C2484x) c0420a.l).b((D) obj2);
                            }
                            if (zG) {
                                c2484x.h(i17);
                            }
                            i12 = 8;
                        } else {
                            jArr2 = jArr5;
                            i7 = length;
                            i10 = i15;
                            i11 = i16;
                            i12 = i13;
                        }
                        j11 >>= i12;
                        i16 = i11 + 1;
                        i13 = i12;
                        jArr5 = jArr2;
                        length = i7;
                        i15 = i10;
                        j3 = 255;
                        c0768u = this;
                    }
                    jArr = jArr5;
                    int i22 = length;
                    if (i15 != i13) {
                        break;
                    } else {
                        length = i22;
                    }
                } else {
                    jArr = jArr5;
                }
                if (i14 == length) {
                    break;
                }
                i14++;
                c0768u = this;
                jArr5 = jArr;
                j3 = 255;
                c = 7;
                j10 = -9187201950435737472L;
                i13 = 8;
            }
        }
        C2453A c2453a2 = this.f8376t;
        if (!c2453a2.h()) {
            return;
        }
        Object[] objArr4 = c2453a2.f22580b;
        long[] jArr7 = c2453a2.f22579a;
        int length3 = jArr7.length - 2;
        if (length3 < 0) {
            return;
        }
        int i23 = 0;
        while (true) {
            long j13 = jArr7[i23];
            if ((((~j13) << 7) & j13 & (-9187201950435737472L)) != -9187201950435737472L) {
                int i24 = 8 - ((~(i23 - length3)) >>> 31);
                for (int i25 = 0; i25 < i24; i25++) {
                    if ((j13 & 255) < 128) {
                        int i26 = (i23 << 3) + i25;
                        if (!(((C0754m0) objArr4[i26]).f8275g != null)) {
                            c2453a2.k(i26);
                        }
                    }
                    j13 >>= 8;
                }
                if (i24 != 8) {
                    return;
                }
            }
            if (i23 == length3) {
                return;
            } else {
                i23++;
            }
        }
    }

    public final void i(b0.b bVar) throws Exception {
        try {
            synchronized (this.o) {
                m();
                C0420a c0420a = this.f8381y;
                this.f8381y = new C0420a(12);
                try {
                    s();
                    C0761q c0761q = this.B;
                    if (!c0761q.f8307e.d.S()) {
                        C0735d.y("Expected applyChanges() to have been called");
                        throw null;
                    }
                    c0761q.n(c0420a, bVar);
                } catch (Exception e10) {
                    this.f8381y = c0420a;
                    throw e10;
                }
            }
        } catch (Throwable th) {
            try {
                if (!this.f8372p.l.g()) {
                    C2486z c2486z = this.f8372p;
                    new ArrayList();
                    new ArrayList();
                    new ArrayList();
                    new ArrayList();
                    if (!c2486z.l.g()) {
                        Trace.beginSection("Compose:abandons");
                        try {
                            Iterator it = c2486z.iterator();
                            while (((P8.l) ((P8.i) it).f5471m).hasNext()) {
                                InterfaceC0769u0 interfaceC0769u0 = (InterfaceC0769u0) ((P8.l) ((P8.i) it).f5471m).next();
                                ((P8.i) it).remove();
                                interfaceC0769u0.c();
                            }
                            Trace.endSection();
                        } catch (Throwable th2) {
                            Trace.endSection();
                            throw th2;
                        }
                    }
                }
                throw th;
            } catch (Exception e11) {
                a();
                throw e11;
            }
        }
    }

    public final void j(b0.b bVar) {
        if (this.f8369C) {
            C0735d.Y("The composition is disposed");
            throw null;
        }
        this.l.a(this, bVar);
    }

    public final void k() {
        synchronized (this.o) {
            try {
                boolean z6 = this.f8373q.f8400m > 0;
                if (z6 || !this.f8372p.l.g()) {
                    Trace.beginSection("Compose:deactivate");
                    try {
                        E0.Y y10 = new E0.Y(this.f8372p);
                        if (z6) {
                            B0 b0R = this.f8373q.r();
                            try {
                                C0735d.D(b0R, y10);
                                b0R.e(true);
                                this.f8370m.y();
                                y10.e();
                            } catch (Throwable th) {
                                b0R.e(false);
                                throw th;
                            }
                        }
                        y10.d();
                        Trace.endSection();
                    } catch (Throwable th2) {
                        Trace.endSection();
                        throw th2;
                    }
                }
                ((C2484x) this.f8374r.l).a();
                ((C2484x) this.f8377u.l).a();
                ((C2484x) this.f8381y.l).a();
                this.f8378v.d.Q();
                this.f8379w.d.Q();
                C0761q c0761q = this.B;
                c0761q.f8292D.l.clear();
                c0761q.f8318r.clear();
                c0761q.f8307e.d.Q();
                c0761q.f8321u = null;
            } catch (Throwable th3) {
                throw th3;
            }
        }
    }

    public final void l() {
        synchronized (this.o) {
            try {
                C0761q c0761q = this.B;
                if (c0761q.f8293E) {
                    C0735d.Y("Composition is disposed while composing. If dispose is triggered by a call in @Composable function, consider wrapping it with SideEffect block.");
                    throw null;
                }
                if (!this.f8369C) {
                    this.f8369C = true;
                    b0.b bVar = AbstractC0747j.f8263b;
                    C0800a c0800a = c0761q.f8299K;
                    if (c0800a != null) {
                        e(c0800a);
                    }
                    boolean z6 = this.f8373q.f8400m > 0;
                    if (z6 || !this.f8372p.l.g()) {
                        E0.Y y10 = new E0.Y(this.f8372p);
                        if (z6) {
                            B0 b0R = this.f8373q.r();
                            try {
                                C0735d.S(b0R, y10);
                                b0R.e(true);
                                this.f8370m.j();
                                this.f8370m.y();
                                y10.e();
                            } catch (Throwable th) {
                                b0R.e(false);
                                throw th;
                            }
                        }
                        y10.d();
                    }
                    C0761q c0761q2 = this.B;
                    c0761q2.getClass();
                    Trace.beginSection("Compose:Composer.dispose");
                    try {
                        c0761q2.f8306b.n(c0761q2);
                        c0761q2.f8292D.l.clear();
                        c0761q2.f8318r.clear();
                        c0761q2.f8307e.d.Q();
                        c0761q2.f8321u = null;
                        c0761q2.f8305a.j();
                        Trace.endSection();
                    } catch (Throwable th2) {
                        Trace.endSection();
                        throw th2;
                    }
                }
            } catch (Throwable th3) {
                throw th3;
            }
        }
        this.l.o(this);
    }

    public final void m() {
        AtomicReference atomicReference = this.f8371n;
        Object obj = C0735d.f8241g;
        Object andSet = atomicReference.getAndSet(obj);
        if (andSet != null) {
            if (andSet.equals(obj)) {
                C0735d.z("pending composition has not been applied");
                throw null;
            }
            if (andSet instanceof Set) {
                c((Set) andSet, true);
                return;
            }
            if (!(andSet instanceof Object[])) {
                C0735d.z("corrupt pendingModifications drain: " + atomicReference);
                throw null;
            }
            for (Set set : (Set[]) andSet) {
                c(set, true);
            }
        }
    }

    public final void n() {
        AtomicReference atomicReference = this.f8371n;
        Object andSet = atomicReference.getAndSet(null);
        if (A7.m.a(andSet, C0735d.f8241g)) {
            return;
        }
        if (andSet instanceof Set) {
            c((Set) andSet, false);
            return;
        }
        if (andSet instanceof Object[]) {
            for (Set set : (Set[]) andSet) {
                c(set, false);
            }
            return;
        }
        if (andSet == null) {
            C0735d.z("calling recordModificationsOf and applyChanges concurrently is not supported");
            throw null;
        }
        C0735d.z("corrupt pendingModifications drain: " + atomicReference);
        throw null;
    }

    public final void o(ArrayList arrayList) throws Exception {
        if (arrayList.size() > 0) {
            ((V) ((m7.i) arrayList.get(0)).l).getClass();
            throw null;
        }
        C0735d.T(true);
        try {
            C0761q c0761q = this.B;
            c0761q.getClass();
            try {
                c0761q.y(arrayList);
                c0761q.i();
            } catch (Throwable th) {
                c0761q.a();
                throw th;
            }
        } catch (Throwable th2) {
            C2486z c2486z = this.f8372p;
            try {
                if (!c2486z.l.g()) {
                    new ArrayList();
                    new ArrayList();
                    new ArrayList();
                    new ArrayList();
                    if (!c2486z.l.g()) {
                        Trace.beginSection("Compose:abandons");
                        try {
                            Iterator it = c2486z.iterator();
                            while (true) {
                                P8.i iVar = (P8.i) it;
                                if (!iVar.hasNext()) {
                                    break;
                                }
                                InterfaceC0769u0 interfaceC0769u0 = (InterfaceC0769u0) iVar.next();
                                iVar.remove();
                                interfaceC0769u0.c();
                            }
                            Trace.endSection();
                        } catch (Throwable th3) {
                            Trace.endSection();
                            throw th3;
                        }
                    }
                }
                throw th2;
            } catch (Exception e10) {
                a();
                throw e10;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:63:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00eb A[Catch: all -> 0x007a, EDGE_INSN: B:81:0x00eb->B:68:0x00eb BREAK  A[LOOP:0: B:52:0x00a8->B:64:0x00e2], EDGE_INSN: B:82:0x00eb->B:68:0x00eb BREAK  A[LOOP:0: B:52:0x00a8->B:64:0x00e2], TRY_LEAVE, TryCatch #0 {all -> 0x007a, blocks: (B:28:0x0052, B:30:0x0059, B:37:0x0068, B:39:0x006d, B:42:0x007d, B:44:0x0081, B:45:0x008d, B:47:0x0099, B:49:0x009d, B:52:0x00a8, B:54:0x00b8, B:56:0x00c4, B:58:0x00ce, B:61:0x00d9, B:64:0x00e2, B:65:0x00e6, B:68:0x00eb), top: B:79:0x0052 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int p(T.C0754m0 r21, java.lang.Object r22) {
        /*
            Method dump skipped, instruction units count: 260
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: T.C0768u.p(T.m0, java.lang.Object):int");
    }

    public final void q() {
        C0768u c0768u;
        synchronized (this.o) {
            try {
                for (Object obj : this.f8373q.f8401n) {
                    C0754m0 c0754m0 = obj instanceof C0754m0 ? (C0754m0) obj : null;
                    if (c0754m0 != null && (c0768u = c0754m0.f8272b) != null) {
                        c0768u.p(c0754m0, null);
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void r(Object obj) {
        Object objE = ((C2484x) this.f8374r.l).e(obj);
        if (objE == null) {
            return;
        }
        boolean z6 = objE instanceof C2453A;
        C0420a c0420a = this.f8380x;
        if (!z6) {
            C0754m0 c0754m0 = (C0754m0) objE;
            if (c0754m0.c(obj) == 4) {
                c0420a.e(obj, c0754m0);
                return;
            }
            return;
        }
        C2453A c2453a = (C2453A) objE;
        Object[] objArr = c2453a.f22580b;
        long[] jArr = c2453a.f22579a;
        int length = jArr.length - 2;
        if (length < 0) {
            return;
        }
        int i7 = 0;
        while (true) {
            long j3 = jArr[i7];
            if ((((~j3) << 7) & j3 & (-9187201950435737472L)) != -9187201950435737472L) {
                int i10 = 8 - ((~(i7 - length)) >>> 31);
                for (int i11 = 0; i11 < i10; i11++) {
                    if ((255 & j3) < 128) {
                        C0754m0 c0754m02 = (C0754m0) objArr[(i7 << 3) + i11];
                        if (c0754m02.c(obj) == 4) {
                            c0420a.e(obj, c0754m02);
                        }
                    }
                    j3 >>= 8;
                }
                if (i10 != 8) {
                    return;
                }
            }
            if (i7 == length) {
                return;
            } else {
                i7++;
            }
        }
    }

    public final void s() {
        if (this.f8368A.l) {
            return;
        }
        this.l.getClass();
        A7.m.a(null, null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x005a, code lost:
    
        return true;
     */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0061  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean t(java.util.Set r18) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            boolean r2 = r1 instanceof V.f
            N1.a r3 = r0.f8377u
            N1.a r0 = r0.f8374r
            r4 = 0
            r5 = 1
            if (r2 == 0) goto L66
            V.f r1 = (V.f) r1
            u.A r1 = r1.l
            java.lang.Object[] r2 = r1.f22580b
            long[] r1 = r1.f22579a
            int r6 = r1.length
            int r6 = r6 + (-2)
            if (r6 < 0) goto L8b
            r7 = r4
        L1c:
            r8 = r1[r7]
            long r10 = ~r8
            r12 = 7
            long r10 = r10 << r12
            long r10 = r10 & r8
            r12 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r10 = r10 & r12
            int r10 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r10 == 0) goto L61
            int r10 = r7 - r6
            int r10 = ~r10
            int r10 = r10 >>> 31
            r11 = 8
            int r10 = 8 - r10
            r12 = r4
        L36:
            if (r12 >= r10) goto L5f
            r13 = 255(0xff, double:1.26E-321)
            long r13 = r13 & r8
            r15 = 128(0x80, double:6.3E-322)
            int r13 = (r13 > r15 ? 1 : (r13 == r15 ? 0 : -1))
            if (r13 >= 0) goto L5b
            int r13 = r7 << 3
            int r13 = r13 + r12
            r13 = r2[r13]
            java.lang.Object r14 = r0.l
            u.x r14 = (u.C2484x) r14
            boolean r14 = r14.b(r13)
            if (r14 != 0) goto L5a
            java.lang.Object r14 = r3.l
            u.x r14 = (u.C2484x) r14
            boolean r13 = r14.b(r13)
            if (r13 == 0) goto L5b
        L5a:
            return r5
        L5b:
            long r8 = r8 >> r11
            int r12 = r12 + 1
            goto L36
        L5f:
            if (r10 != r11) goto L8b
        L61:
            if (r7 == r6) goto L8b
            int r7 = r7 + 1
            goto L1c
        L66:
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            java.util.Iterator r1 = r1.iterator()
        L6c:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L8b
            java.lang.Object r2 = r1.next()
            java.lang.Object r6 = r0.l
            u.x r6 = (u.C2484x) r6
            boolean r6 = r6.b(r2)
            if (r6 != 0) goto L8a
            java.lang.Object r6 = r3.l
            u.x r6 = (u.C2484x) r6
            boolean r2 = r6.b(r2)
            if (r2 == 0) goto L6c
        L8a:
            return r5
        L8b:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: T.C0768u.t(java.util.Set):boolean");
    }

    public final boolean u() {
        boolean zB;
        synchronized (this.o) {
            try {
                m();
                try {
                    C0420a c0420a = this.f8381y;
                    this.f8381y = new C0420a(12);
                    try {
                        s();
                        zB = this.B.B(c0420a);
                        if (!zB) {
                            n();
                        }
                    } catch (Exception e10) {
                        this.f8381y = c0420a;
                        throw e10;
                    }
                } catch (Throwable th) {
                    try {
                        if (!this.f8372p.l.g()) {
                            C2486z c2486z = this.f8372p;
                            new ArrayList();
                            new ArrayList();
                            new ArrayList();
                            new ArrayList();
                            if (!c2486z.l.g()) {
                                Trace.beginSection("Compose:abandons");
                                try {
                                    Iterator it = c2486z.iterator();
                                    while (((P8.l) ((P8.i) it).f5471m).hasNext()) {
                                        InterfaceC0769u0 interfaceC0769u0 = (InterfaceC0769u0) ((P8.l) ((P8.i) it).f5471m).next();
                                        ((P8.i) it).remove();
                                        interfaceC0769u0.c();
                                    }
                                    Trace.endSection();
                                } catch (Throwable th2) {
                                    Trace.endSection();
                                    throw th2;
                                }
                            }
                        }
                        throw th;
                    } catch (Exception e11) {
                        a();
                        throw e11;
                    }
                }
            } catch (Throwable th3) {
                throw th3;
            }
        }
        return zB;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void v(V.f fVar) {
        Object obj;
        while (true) {
            Object obj2 = this.f8371n.get();
            if (obj2 == null ? true : obj2.equals(C0735d.f8241g)) {
                obj = fVar;
            } else if (obj2 instanceof Set) {
                obj = new Set[]{obj2, fVar};
            } else {
                if (!(obj2 instanceof Object[])) {
                    throw new IllegalStateException(("corrupt pendingModifications: " + this.f8371n).toString());
                }
                A7.m.d(obj2, "null cannot be cast to non-null type kotlin.Array<kotlin.collections.Set<kotlin.Any>>");
                Set[] setArr = (Set[]) obj2;
                A7.m.f(setArr, "<this>");
                int length = setArr.length;
                Object[] objArrCopyOf = Arrays.copyOf(setArr, length + 1);
                objArrCopyOf[length] = fVar;
                obj = objArrCopyOf;
            }
            AtomicReference atomicReference = this.f8371n;
            while (!atomicReference.compareAndSet(obj2, obj)) {
                if (atomicReference.get() != obj2) {
                    break;
                }
            }
            if (obj2 == null) {
                synchronized (this.o) {
                    n();
                }
                return;
            }
            return;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x00b9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void w(java.lang.Object r21) {
        /*
            Method dump skipped, instruction units count: 207
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: T.C0768u.w(java.lang.Object):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x005b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void x(java.lang.Object r15) {
        /*
            r14 = this;
            java.lang.Object r0 = r14.o
            monitor-enter(r0)
            r14.r(r15)     // Catch: java.lang.Throwable -> L53
            N1.a r1 = r14.f8377u     // Catch: java.lang.Throwable -> L53
            java.lang.Object r1 = r1.l     // Catch: java.lang.Throwable -> L53
            u.x r1 = (u.C2484x) r1     // Catch: java.lang.Throwable -> L53
            java.lang.Object r15 = r1.e(r15)     // Catch: java.lang.Throwable -> L53
            if (r15 == 0) goto L65
            boolean r1 = r15 instanceof u.C2453A     // Catch: java.lang.Throwable -> L53
            if (r1 == 0) goto L60
            u.A r15 = (u.C2453A) r15     // Catch: java.lang.Throwable -> L53
            java.lang.Object[] r1 = r15.f22580b     // Catch: java.lang.Throwable -> L53
            long[] r15 = r15.f22579a     // Catch: java.lang.Throwable -> L53
            int r2 = r15.length     // Catch: java.lang.Throwable -> L53
            int r2 = r2 + (-2)
            if (r2 < 0) goto L65
            r3 = 0
            r4 = r3
        L23:
            r5 = r15[r4]     // Catch: java.lang.Throwable -> L53
            long r7 = ~r5     // Catch: java.lang.Throwable -> L53
            r9 = 7
            long r7 = r7 << r9
            long r7 = r7 & r5
            r9 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r7 = r7 & r9
            int r7 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r7 == 0) goto L5b
            int r7 = r4 - r2
            int r7 = ~r7     // Catch: java.lang.Throwable -> L53
            int r7 = r7 >>> 31
            r8 = 8
            int r7 = 8 - r7
            r9 = r3
        L3d:
            if (r9 >= r7) goto L59
            r10 = 255(0xff, double:1.26E-321)
            long r10 = r10 & r5
            r12 = 128(0x80, double:6.3E-322)
            int r10 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r10 >= 0) goto L55
            int r10 = r4 << 3
            int r10 = r10 + r9
            r10 = r1[r10]     // Catch: java.lang.Throwable -> L53
            T.D r10 = (T.D) r10     // Catch: java.lang.Throwable -> L53
            r14.r(r10)     // Catch: java.lang.Throwable -> L53
            goto L55
        L53:
            r14 = move-exception
            goto L67
        L55:
            long r5 = r5 >> r8
            int r9 = r9 + 1
            goto L3d
        L59:
            if (r7 != r8) goto L65
        L5b:
            if (r4 == r2) goto L65
            int r4 = r4 + 1
            goto L23
        L60:
            T.D r15 = (T.D) r15     // Catch: java.lang.Throwable -> L53
            r14.r(r15)     // Catch: java.lang.Throwable -> L53
        L65:
            monitor-exit(r0)
            return
        L67:
            monitor-exit(r0)
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: T.C0768u.x(java.lang.Object):void");
    }
}
