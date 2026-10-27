package y;

import C0.InterfaceC0103j;
import T.C0735d;
import T.C0761q;
import n0.C1883l;
import r0.AbstractC2268a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class Y extends A7.o implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ AbstractC2268a f23874m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ String f23875n;
    public final /* synthetic */ g0.o o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ g0.c f23876p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ InterfaceC0103j f23877q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ float f23878r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ C1883l f23879s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ int f23880t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ int f23881u;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Y(AbstractC2268a abstractC2268a, String str, g0.o oVar, g0.c cVar, InterfaceC0103j interfaceC0103j, float f10, C1883l c1883l, int i7, int i10) {
        super(2);
        this.f23874m = abstractC2268a;
        this.f23875n = str;
        this.o = oVar;
        this.f23876p = cVar;
        this.f23877q = interfaceC0103j;
        this.f23878r = f10;
        this.f23879s = c1883l;
        this.f23880t = i7;
        this.f23881u = i10;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        ((Number) obj2).intValue();
        int iZ = C0735d.Z(this.f23880t | 1);
        InterfaceC0103j interfaceC0103j = this.f23877q;
        h6.q.a(this.f23874m, this.f23875n, this.o, this.f23876p, interfaceC0103j, this.f23878r, this.f23879s, (C0761q) obj, iZ, this.f23881u);
        return m7.q.f19267a;
    }
}
