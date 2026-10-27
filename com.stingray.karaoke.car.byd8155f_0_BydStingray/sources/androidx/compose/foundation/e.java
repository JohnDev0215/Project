package androidx.compose.foundation;

import A.EnumC0017h0;
import A.r;
import A7.o;
import T.C0761q;
import h8.AbstractC1386c;
import y.y0;
import z7.InterfaceC2786o;

/* JADX INFO: loaded from: classes.dex */
public final class e extends o implements InterfaceC2786o {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ y0 f11434m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ boolean f11435n;
    public final /* synthetic */ r o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ boolean f11436p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ boolean f11437q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(y0 y0Var, boolean z6, r rVar, boolean z10, boolean z11) {
        super(3);
        this.f11434m = y0Var;
        this.f11435n = z6;
        this.o = rVar;
        this.f11436p = z10;
        this.f11437q = z11;
    }

    @Override // z7.InterfaceC2786o
    public final Object g(Object obj, Object obj2, Object obj3) {
        C0761q c0761q = (C0761q) obj2;
        ((Number) obj3).intValue();
        c0761q.Q(1478351300);
        y0 y0Var = this.f11434m;
        boolean z6 = this.f11435n;
        r rVar = this.o;
        boolean z10 = this.f11436p;
        boolean z11 = this.f11437q;
        g0.o oVarK = AbstractC1386c.s(new ScrollSemanticsElement(y0Var, z6, rVar, z10, z11), y0Var, z11 ? EnumC0017h0.l : EnumC0017h0.f181m, z10, z6, rVar, y0Var.c, c0761q).k(new ScrollingLayoutElement(this.f11434m, this.f11435n, this.f11437q));
        c0761q.p(false);
        return oVarK;
    }
}
