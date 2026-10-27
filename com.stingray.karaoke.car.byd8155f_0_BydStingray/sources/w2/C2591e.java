package w2;

import android.text.TextUtils;
import d5.AbstractC1102y;
import d5.a0;
import d5.b0;
import d5.k0;
import e2.C1208p;
import h2.AbstractC1343A;
import java.util.Objects;

/* JADX INFO: renamed from: w2.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2591e extends m implements Comparable {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public final int f23178A;
    public final boolean B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public final int f23179C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public final int f23180D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public final int f23181E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public final int f23182F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public final boolean f23183G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public final boolean f23184H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public final boolean f23185I;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final int f23186p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final boolean f23187q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final String f23188r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final C2595i f23189s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final boolean f23190t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final int f23191u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final int f23192v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final int f23193w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final boolean f23194x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final boolean f23195y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final int f23196z;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0070  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public C2591e(int r7, e2.g0 r8, int r9, w2.C2595i r10, int r11, boolean r12, w2.C2590d r13, int r14) {
        /*
            Method dump skipped, instruction units count: 382
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: w2.C2591e.<init>(int, e2.g0, int, w2.i, int, boolean, w2.d, int):void");
    }

    @Override // w2.m
    public final int a() {
        return this.f23186p;
    }

    @Override // w2.m
    public final boolean b(m mVar) {
        int i7;
        String str;
        C2591e c2591e = (C2591e) mVar;
        this.f23189s.getClass();
        C1208p c1208p = this.o;
        int i10 = c1208p.f14295C;
        if (i10 != -1) {
            C1208p c1208p2 = c2591e.o;
            if (i10 == c1208p2.f14295C && ((this.f23194x || ((str = c1208p.f14316n) != null && TextUtils.equals(str, c1208p2.f14316n))) && (i7 = c1208p.f14296D) != -1 && i7 == c1208p2.f14296D)) {
                if (this.f23183G == c2591e.f23183G && this.f23184H == c2591e.f23184H) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final int compareTo(C2591e c2591e) {
        boolean z6 = this.f23190t;
        boolean z10 = this.f23187q;
        b0 b0VarA = (z10 && z6) ? o.f23261j : o.f23261j.a();
        AbstractC1102y abstractC1102yC = AbstractC1102y.f13464a.c(z6, c2591e.f23190t);
        Integer numValueOf = Integer.valueOf(this.f23192v);
        Integer numValueOf2 = Integer.valueOf(c2591e.f23192v);
        a0.l.getClass();
        k0 k0Var = k0.l;
        AbstractC1102y abstractC1102yB = abstractC1102yC.b(numValueOf, numValueOf2, k0Var).a(this.f23191u, c2591e.f23191u).a(this.f23193w, c2591e.f23193w).c(this.B, c2591e.B).c(this.f23195y, c2591e.f23195y).b(Integer.valueOf(this.f23196z), Integer.valueOf(c2591e.f23196z), k0Var).a(this.f23178A, c2591e.f23178A).c(z10, c2591e.f23187q).b(Integer.valueOf(this.f23182F), Integer.valueOf(c2591e.f23182F), k0Var);
        boolean z11 = this.f23189s.f14215y;
        int i7 = this.f23181E;
        int i10 = c2591e.f23181E;
        if (z11) {
            abstractC1102yB = abstractC1102yB.b(Integer.valueOf(i7), Integer.valueOf(i10), o.f23261j.a());
        }
        AbstractC1102y abstractC1102yB2 = abstractC1102yB.c(this.f23183G, c2591e.f23183G).c(this.f23184H, c2591e.f23184H).c(this.f23185I, c2591e.f23185I).b(Integer.valueOf(this.f23179C), Integer.valueOf(c2591e.f23179C), b0VarA).b(Integer.valueOf(this.f23180D), Integer.valueOf(c2591e.f23180D), b0VarA);
        int i11 = AbstractC1343A.f15195a;
        if (Objects.equals(this.f23188r, c2591e.f23188r)) {
            abstractC1102yB2 = abstractC1102yB2.b(Integer.valueOf(i7), Integer.valueOf(i10), b0VarA);
        }
        return abstractC1102yB2.e();
    }
}
