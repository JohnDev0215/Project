package w2;

import d5.AbstractC1102y;
import d5.a0;
import d5.k0;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class n extends m {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public final int f23247A;
    public final boolean B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public final boolean f23248C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public final int f23249D;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final boolean f23250p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final C2595i f23251q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final boolean f23252r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final boolean f23253s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final boolean f23254t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final int f23255u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final int f23256v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final int f23257w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final int f23258x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final boolean f23259y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final boolean f23260z;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00f3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public n(int r8, e2.g0 r9, int r10, w2.C2595i r11, int r12, int r13, boolean r14) {
        /*
            Method dump skipped, instruction units count: 420
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: w2.n.<init>(int, e2.g0, int, w2.i, int, int, boolean):void");
    }

    public static int c(n nVar, n nVar2) {
        AbstractC1102y abstractC1102yC = AbstractC1102y.f13464a.c(nVar.f23253s, nVar2.f23253s).a(nVar.f23258x, nVar2.f23258x).c(nVar.f23259y, nVar2.f23259y).c(nVar.f23254t, nVar2.f23254t).c(nVar.f23250p, nVar2.f23250p).c(nVar.f23252r, nVar2.f23252r);
        Integer numValueOf = Integer.valueOf(nVar.f23257w);
        Integer numValueOf2 = Integer.valueOf(nVar2.f23257w);
        a0.l.getClass();
        AbstractC1102y abstractC1102yB = abstractC1102yC.b(numValueOf, numValueOf2, k0.l);
        boolean z6 = nVar2.B;
        boolean z10 = nVar.B;
        AbstractC1102y abstractC1102yC2 = abstractC1102yB.c(z10, z6);
        boolean z11 = nVar2.f23248C;
        boolean z12 = nVar.f23248C;
        AbstractC1102y abstractC1102yC3 = abstractC1102yC2.c(z12, z11);
        if (z10 && z12) {
            abstractC1102yC3 = abstractC1102yC3.a(nVar.f23249D, nVar2.f23249D);
        }
        return abstractC1102yC3.e();
    }

    @Override // w2.m
    public final int a() {
        return this.f23247A;
    }

    @Override // w2.m
    public final boolean b(m mVar) {
        n nVar = (n) mVar;
        if (this.f23260z || Objects.equals(this.o.f14316n, nVar.o.f14316n)) {
            this.f23251q.getClass();
            if (this.B == nVar.B && this.f23248C == nVar.f23248C) {
                return true;
            }
        }
        return false;
    }
}
