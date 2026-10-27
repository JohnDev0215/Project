package E;

import A.EnumC0017h0;
import C0.L;
import G.C0314y;
import H7.H;
import java.util.List;
import java.util.Map;
import n7.AbstractC1998m;
import x3.AbstractC2673a;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class t implements L {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final u f1408a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1409b;
    public boolean c;
    public float d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final float f1410e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f1411f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final X8.c f1412g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final a1.b f1413h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final long f1414i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final Object f1415j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final int f1416k;
    public final int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f1417m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final boolean f1418n;
    public final EnumC0017h0 o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final int f1419p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final int f1420q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ L f1421r;

    public t(u uVar, int i7, boolean z6, float f10, L l, float f11, boolean z10, X8.c cVar, a1.b bVar, long j3, List list, int i10, int i11, int i12, boolean z11, EnumC0017h0 enumC0017h0, int i13, int i14) {
        this.f1408a = uVar;
        this.f1409b = i7;
        this.c = z6;
        this.d = f10;
        this.f1410e = f11;
        this.f1411f = z10;
        this.f1412g = cVar;
        this.f1413h = bVar;
        this.f1414i = j3;
        this.f1415j = list;
        this.f1416k = i10;
        this.l = i11;
        this.f1417m = i12;
        this.f1418n = z11;
        this.o = enumC0017h0;
        this.f1419p = i13;
        this.f1420q = i14;
        this.f1421r = l;
    }

    @Override // C0.L
    public final Map a() {
        return this.f1421r.a();
    }

    @Override // C0.L
    public final void b() {
        this.f1421r.b();
    }

    @Override // C0.L
    public final int c() {
        return this.f1421r.c();
    }

    @Override // C0.L
    public final InterfaceC2782k d() {
        return this.f1421r.d();
    }

    @Override // C0.L
    public final int e() {
        return this.f1421r.e();
    }

    public final long f() {
        L l = this.f1421r;
        return H.c(l.e(), l.c());
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [java.lang.Object, java.util.List] */
    public final boolean g(int i7, boolean z6) {
        u uVar;
        int i10;
        boolean z10;
        if (this.f1411f) {
            return false;
        }
        ?? r22 = this.f1415j;
        if (r22.isEmpty() || (uVar = this.f1408a) == null || (i10 = this.f1409b - i7) < 0 || i10 >= uVar.f1435r) {
            return false;
        }
        u uVar2 = (u) AbstractC1998m.Y(r22);
        u uVar3 = (u) AbstractC1998m.h0(r22);
        if (uVar2.f1437t || uVar3.f1437t) {
            return false;
        }
        int i11 = this.l;
        int i12 = this.f1416k;
        if (i7 < 0) {
            if (Math.min((uVar2.f1433p + uVar2.f1435r) - i12, (uVar3.f1433p + uVar3.f1435r) - i11) <= (-i7)) {
                return false;
            }
        } else if (Math.min(i12 - uVar2.f1433p, i11 - uVar3.f1433p) <= i7) {
            return false;
        }
        this.f1409b -= i7;
        int size = r22.size();
        for (int i13 = 0; i13 < size; i13++) {
            u uVar4 = (u) r22.get(i13);
            if (!uVar4.f1437t) {
                uVar4.f1433p += i7;
                int[] iArr = uVar4.f1441x;
                int length = iArr.length;
                int i14 = 0;
                while (true) {
                    z10 = uVar4.c;
                    if (i14 >= length) {
                        break;
                    }
                    if ((z10 && i14 % 2 == 1) || (!z10 && i14 % 2 == 0)) {
                        iArr[i14] = iArr[i14] + i7;
                    }
                    i14++;
                }
                if (z6) {
                    int size2 = uVar4.f1423b.size();
                    for (int i15 = 0; i15 < size2; i15++) {
                        C0314y c0314yA = uVar4.f1432n.a(i15, uVar4.l);
                        if (c0314yA != null) {
                            long j3 = c0314yA.f2626j;
                            c0314yA.f2626j = AbstractC2673a.d(z10 ? (int) (j3 >> 32) : ((int) (j3 >> 32)) + i7, z10 ? ((int) (j3 & 4294967295L)) + i7 : (int) (j3 & 4294967295L));
                        }
                    }
                }
            }
        }
        this.d = i7;
        if (!this.c && i7 > 0) {
            this.c = true;
        }
        return true;
    }
}
