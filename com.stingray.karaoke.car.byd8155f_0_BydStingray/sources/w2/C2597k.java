package w2;

import d5.AbstractC1102y;
import d5.I;
import d5.a0;
import d5.d0;
import d5.k0;
import e2.g0;
import java.util.Comparator;
import n2.AbstractC1905e;

/* JADX INFO: renamed from: w2.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2597k extends m implements Comparable {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final int f23236p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final boolean f23237q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final boolean f23238r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final boolean f23239s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final int f23240t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final int f23241u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final int f23242v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final int f23243w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final boolean f23244x;

    public C2597k(int i7, g0 g0Var, int i10, C2595i c2595i, int i11, String str) {
        int iD;
        super(i7, g0Var, i10);
        int i12 = 0;
        this.f23237q = AbstractC1905e.o(i11, false);
        int i13 = this.o.f14308e & (~c2595i.f14212v);
        this.f23238r = (i13 & 1) != 0;
        this.f23239s = (i13 & 2) != 0;
        d0 d0Var = c2595i.f14210t;
        d0 d0VarB = d0Var.isEmpty() ? I.B("") : d0Var;
        int i14 = 0;
        while (true) {
            if (i14 >= d0VarB.size()) {
                i14 = Integer.MAX_VALUE;
                iD = 0;
                break;
            } else {
                iD = o.d(this.o, (String) d0VarB.get(i14), c2595i.f14213w);
                if (iD > 0) {
                    break;
                } else {
                    i14++;
                }
            }
        }
        this.f23240t = i14;
        this.f23241u = iD;
        int iB = o.b(this.o.f14309f, c2595i.f14211u);
        this.f23242v = iB;
        this.f23244x = (this.o.f14309f & 1088) != 0;
        int iD2 = o.d(this.o, str, o.g(str) == null);
        this.f23243w = iD2;
        boolean z6 = iD > 0 || (d0Var.isEmpty() && iB > 0) || this.f23238r || (this.f23239s && iD2 > 0);
        if (AbstractC1905e.o(i11, c2595i.f23231n0) && z6) {
            i12 = 1;
        }
        this.f23236p = i12;
    }

    @Override // w2.m
    public final int a() {
        return this.f23236p;
    }

    @Override // w2.m
    public final /* bridge */ /* synthetic */ boolean b(m mVar) {
        return false;
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final int compareTo(C2597k c2597k) {
        AbstractC1102y abstractC1102yC = AbstractC1102y.f13464a.c(this.f23237q, c2597k.f23237q);
        Integer numValueOf = Integer.valueOf(this.f23240t);
        Integer numValueOf2 = Integer.valueOf(c2597k.f23240t);
        Comparator comparator = a0.l;
        comparator.getClass();
        k0 k0Var = k0.l;
        AbstractC1102y abstractC1102yB = abstractC1102yC.b(numValueOf, numValueOf2, k0Var);
        int i7 = this.f23241u;
        AbstractC1102y abstractC1102yA = abstractC1102yB.a(i7, c2597k.f23241u);
        int i10 = this.f23242v;
        AbstractC1102y abstractC1102yC2 = abstractC1102yA.a(i10, c2597k.f23242v).c(this.f23238r, c2597k.f23238r);
        Boolean boolValueOf = Boolean.valueOf(this.f23239s);
        Boolean boolValueOf2 = Boolean.valueOf(c2597k.f23239s);
        if (i7 != 0) {
            comparator = k0Var;
        }
        AbstractC1102y abstractC1102yA2 = abstractC1102yC2.b(boolValueOf, boolValueOf2, comparator).a(this.f23243w, c2597k.f23243w);
        if (i10 == 0) {
            abstractC1102yA2 = abstractC1102yA2.d(this.f23244x, c2597k.f23244x);
        }
        return abstractC1102yA2.e();
    }
}
