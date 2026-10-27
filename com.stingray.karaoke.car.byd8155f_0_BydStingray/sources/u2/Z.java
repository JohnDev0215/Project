package u2;

import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.util.SparseArray;
import e2.C1204l;
import e2.C1207o;
import e2.C1208p;
import e2.InterfaceC1201i;
import h2.AbstractC1346c;
import java.io.EOFException;
import java.util.Objects;
import l5.C1778f;
import r2.C2285g;

/* JADX INFO: loaded from: classes.dex */
public final class Z implements B2.L {
    public boolean B;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final X f22769a;
    public final q2.o d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final q2.k f22771e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public T f22772f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public C1208p f22773g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public q2.h f22774h;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f22780p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f22781q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f22782r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f22783s;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f22787w;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public C1208p f22790z;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C2285g f22770b = new C2285g();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f22775i = 1000;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public long[] f22776j = new long[1000];

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public long[] f22777k = new long[1000];

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public long[] f22779n = new long[1000];

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int[] f22778m = new int[1000];
    public int[] l = new int[1000];
    public B2.K[] o = new B2.K[1000];
    public final B5.u c = new B5.u(new I(1));

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public long f22784t = Long.MIN_VALUE;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public long f22785u = Long.MIN_VALUE;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public long f22786v = Long.MIN_VALUE;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public boolean f22789y = true;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public boolean f22788x = true;

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public boolean f22768A = true;

    public Z(x2.e eVar, q2.o oVar, q2.k kVar) {
        this.d = oVar;
        this.f22771e = kVar;
        this.f22769a = new X(eVar);
    }

    @Override // B2.L
    public final void a(h2.t tVar, int i7, int i10) {
        while (true) {
            X x8 = this.f22769a;
            if (i7 <= 0) {
                x8.getClass();
                return;
            }
            int iB = x8.b(i7);
            G.i0 i0Var = x8.f22764f;
            x2.a aVar = (x2.a) i0Var.f2584n;
            tVar.f(((int) (x8.f22765g - i0Var.l)) + aVar.f23720b, iB, aVar.f23719a);
            i7 -= iB;
            long j3 = x8.f22765g + ((long) iB);
            x8.f22765g = j3;
            G.i0 i0Var2 = x8.f22764f;
            if (j3 == i0Var2.f2583m) {
                x8.f22764f = (G.i0) i0Var2.o;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x00c4 A[Catch: all -> 0x0063, TryCatch #0 {all -> 0x0063, blocks: (B:23:0x0045, B:25:0x0049, B:29:0x005f, B:32:0x0066, B:36:0x006e, B:41:0x00a9, B:64:0x0125, B:66:0x012e, B:43:0x00c4, B:45:0x00cd, B:47:0x00d6, B:49:0x00eb, B:53:0x00f4, B:54:0x00f9, B:56:0x00ff, B:60:0x010d, B:62:0x0112, B:63:0x0122, B:46:0x00d4), top: B:71:0x0045 }] */
    @Override // B2.L
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void b(long r10, int r12, int r13, int r14, B2.K r15) {
        /*
            Method dump skipped, instruction units count: 411
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: u2.Z.b(long, int, int, int, B2.K):void");
    }

    @Override // B2.L
    public final int c(InterfaceC1201i interfaceC1201i, int i7, boolean z6) throws EOFException {
        X x8 = this.f22769a;
        int iB = x8.b(i7);
        G.i0 i0Var = x8.f22764f;
        x2.a aVar = (x2.a) i0Var.f2584n;
        int iN = interfaceC1201i.n(aVar.f23719a, ((int) (x8.f22765g - i0Var.l)) + aVar.f23720b, iB);
        if (iN == -1) {
            if (z6) {
                return -1;
            }
            throw new EOFException();
        }
        long j3 = x8.f22765g + ((long) iN);
        x8.f22765g = j3;
        G.i0 i0Var2 = x8.f22764f;
        if (j3 != i0Var2.f2583m) {
            return iN;
        }
        x8.f22764f = (G.i0) i0Var2.o;
        return iN;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0056 A[Catch: all -> 0x0053, TryCatch #0 {all -> 0x0053, blocks: (B:4:0x0003, B:8:0x0013, B:13:0x0024, B:15:0x003d, B:19:0x0058, B:81:0x010e, B:73:0x00fb, B:76:0x0103, B:18:0x0056), top: B:90:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x010d  */
    @Override // B2.L
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void d(e2.C1208p r7) {
        /*
            Method dump skipped, instruction units count: 364
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: u2.Z.d(e2.p):void");
    }

    public final long e(int i7) {
        long j3 = this.f22785u;
        long jMax = Long.MIN_VALUE;
        if (i7 != 0) {
            int iH = h(i7 - 1);
            for (int i10 = 0; i10 < i7; i10++) {
                jMax = Math.max(jMax, this.f22779n[iH]);
                if ((this.f22778m[iH] & 1) != 0) {
                    break;
                }
                iH--;
                if (iH == -1) {
                    iH = this.f22775i - 1;
                }
            }
        }
        this.f22785u = Math.max(j3, jMax);
        this.f22780p -= i7;
        int i11 = this.f22781q + i7;
        this.f22781q = i11;
        int i12 = this.f22782r + i7;
        this.f22782r = i12;
        int i13 = this.f22775i;
        if (i12 >= i13) {
            this.f22782r = i12 - i13;
        }
        int i14 = this.f22783s - i7;
        this.f22783s = i14;
        int i15 = 0;
        if (i14 < 0) {
            this.f22783s = 0;
        }
        while (true) {
            B5.u uVar = this.c;
            SparseArray sparseArray = (SparseArray) uVar.f736n;
            if (i15 >= sparseArray.size() - 1) {
                break;
            }
            int i16 = i15 + 1;
            if (i11 < sparseArray.keyAt(i16)) {
                break;
            }
            ((I) uVar.o).accept(sparseArray.valueAt(i15));
            sparseArray.removeAt(i15);
            int i17 = uVar.f735m;
            if (i17 > 0) {
                uVar.f735m = i17 - 1;
            }
            i15 = i16;
        }
        if (this.f22780p != 0) {
            return this.f22777k[this.f22782r];
        }
        int i18 = this.f22782r;
        if (i18 == 0) {
            i18 = this.f22775i;
        }
        int i19 = i18 - 1;
        return this.f22777k[i19] + ((long) this.l[i19]);
    }

    public final void f() {
        long jE;
        X x8 = this.f22769a;
        synchronized (this) {
            int i7 = this.f22780p;
            jE = i7 == 0 ? -1L : e(i7);
        }
        x8.a(jE);
    }

    public final int g(int i7, int i10, long j3, boolean z6) {
        int i11 = -1;
        for (int i12 = 0; i12 < i10; i12++) {
            long j10 = this.f22779n[i7];
            if (j10 > j3) {
                break;
            }
            if (!z6 || (this.f22778m[i7] & 1) != 0) {
                i11 = i12;
                if (j10 == j3) {
                    break;
                }
            }
            i7++;
            if (i7 == this.f22775i) {
                i7 = 0;
            }
        }
        return i11;
    }

    public final int h(int i7) {
        int i10 = this.f22782r + i7;
        int i11 = this.f22775i;
        return i10 < i11 ? i10 : i10 - i11;
    }

    public final synchronized boolean i(boolean z6) {
        C1208p c1208p;
        int i7 = this.f22783s;
        boolean z10 = false;
        if (i7 != this.f22780p) {
            if (((Y) this.c.n(this.f22781q + i7)).f22766a != this.f22773g) {
                return true;
            }
            return j(h(this.f22783s));
        }
        if (z6 || this.f22787w || ((c1208p = this.f22790z) != null && c1208p != this.f22773g)) {
            z10 = true;
        }
        return z10;
    }

    public final boolean j(int i7) {
        q2.h hVar = this.f22774h;
        return hVar == null || hVar.getState() == 4 || ((this.f22778m[i7] & TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED) == 0 && this.f22774h.a());
    }

    public final void k(C1208p c1208p, C1778f c1778f) {
        C1208p c1208p2;
        C1208p c1208p3 = this.f22773g;
        boolean z6 = c1208p3 == null;
        C1204l c1204l = c1208p3 == null ? null : c1208p3.f14319r;
        this.f22773g = c1208p;
        C1204l c1204l2 = c1208p.f14319r;
        q2.o oVar = this.d;
        if (oVar != null) {
            int iE = oVar.e(c1208p);
            C1207o c1207oA = c1208p.a();
            c1207oA.f14236K = iE;
            c1208p2 = new C1208p(c1207oA);
        } else {
            c1208p2 = c1208p;
        }
        c1778f.f18639n = c1208p2;
        c1778f.f18638m = this.f22774h;
        if (oVar == null) {
            return;
        }
        if (z6 || !Objects.equals(c1204l, c1204l2)) {
            q2.h hVar = this.f22774h;
            q2.k kVar = this.f22771e;
            q2.h hVarB = oVar.b(kVar, c1208p);
            this.f22774h = hVarB;
            c1778f.f18638m = hVarB;
            if (hVar != null) {
                hVar.d(kVar);
            }
        }
    }

    public final void l(boolean z6) {
        SparseArray sparseArray;
        X x8 = this.f22769a;
        G.i0 i0Var = x8.d;
        if (((x2.a) i0Var.f2584n) != null) {
            x2.e eVar = x8.f22761a;
            synchronized (eVar) {
                G.i0 i0Var2 = i0Var;
                while (i0Var2 != null) {
                    try {
                        x2.a[] aVarArr = eVar.f23728f;
                        int i7 = eVar.f23727e;
                        eVar.f23727e = i7 + 1;
                        x2.a aVar = (x2.a) i0Var2.f2584n;
                        aVar.getClass();
                        aVarArr[i7] = aVar;
                        eVar.d--;
                        i0Var2 = (G.i0) i0Var2.o;
                        if (i0Var2 == null || ((x2.a) i0Var2.f2584n) == null) {
                            i0Var2 = null;
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                eVar.notifyAll();
            }
            i0Var.f2584n = null;
            i0Var.o = null;
        }
        G.i0 i0Var3 = x8.d;
        int i10 = x8.f22762b;
        int i11 = 0;
        AbstractC1346c.j(((x2.a) i0Var3.f2584n) == null);
        i0Var3.l = 0L;
        i0Var3.f2583m = i10;
        G.i0 i0Var4 = x8.d;
        x8.f22763e = i0Var4;
        x8.f22764f = i0Var4;
        x8.f22765g = 0L;
        x8.f22761a.b();
        this.f22780p = 0;
        this.f22781q = 0;
        this.f22782r = 0;
        this.f22783s = 0;
        this.f22788x = true;
        this.f22784t = Long.MIN_VALUE;
        this.f22785u = Long.MIN_VALUE;
        this.f22786v = Long.MIN_VALUE;
        this.f22787w = false;
        B5.u uVar = this.c;
        while (true) {
            sparseArray = (SparseArray) uVar.f736n;
            if (i11 >= sparseArray.size()) {
                break;
            }
            ((I) uVar.o).accept(sparseArray.valueAt(i11));
            i11++;
        }
        uVar.f735m = -1;
        sparseArray.clear();
        if (z6) {
            this.f22790z = null;
            this.f22789y = true;
            this.f22768A = true;
        }
    }

    public final synchronized boolean m(long j3, boolean z6) {
        int iG;
        synchronized (this) {
            this.f22783s = 0;
            X x8 = this.f22769a;
            x8.f22763e = x8.d;
        }
        int iH = h(0);
        int i7 = this.f22783s;
        int i10 = this.f22780p;
        if ((i7 != i10) && j3 >= this.f22779n[iH] && (j3 <= this.f22786v || z6)) {
            if (this.f22768A) {
                int i11 = i10 - i7;
                iG = 0;
                while (true) {
                    if (iG >= i11) {
                        if (!z6) {
                            i11 = -1;
                        }
                        iG = i11;
                    } else {
                        if (this.f22779n[iH] >= j3) {
                            break;
                        }
                        iH++;
                        if (iH == this.f22775i) {
                            iH = 0;
                        }
                        iG++;
                    }
                }
            } else {
                iG = g(iH, i10 - i7, j3, true);
            }
            if (iG == -1) {
                return false;
            }
            this.f22784t = j3;
            this.f22783s += iG;
            return true;
        }
        return false;
    }
}
