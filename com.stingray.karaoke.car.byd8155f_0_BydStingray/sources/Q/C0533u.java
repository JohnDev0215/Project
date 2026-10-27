package Q;

import T.C0735d;
import T.C0761q;
import r0.AbstractC2268a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: Q.u, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0533u extends A7.o implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ AbstractC2268a f6048m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ String f6049n;
    public final /* synthetic */ g0.o o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ long f6050p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ int f6051q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ int f6052r;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0533u(AbstractC2268a abstractC2268a, String str, g0.o oVar, long j3, int i7, int i10) {
        super(2);
        this.f6048m = abstractC2268a;
        this.f6049n = str;
        this.o = oVar;
        this.f6050p = j3;
        this.f6051q = i7;
        this.f6052r = i10;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        ((Number) obj2).intValue();
        int iZ = C0735d.Z(this.f6051q | 1);
        String str = this.f6049n;
        AbstractC0535v.a(this.f6048m, str, this.o, this.f6050p, (C0761q) obj, iZ, this.f6052r);
        return m7.q.f19267a;
    }
}
