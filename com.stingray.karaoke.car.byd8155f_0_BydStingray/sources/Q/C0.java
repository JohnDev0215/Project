package Q;

import T.C0735d;
import T.C0761q;
import n0.InterfaceC1866P;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class C0 extends A7.o implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ g0.o f5503m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ boolean f5504n;
    public final /* synthetic */ boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ C0544z0 f5505p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2785n f5506q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ C.k f5507r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ InterfaceC1866P f5508s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ int f5509t;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0(g0.o oVar, boolean z6, boolean z10, C0544z0 c0544z0, InterfaceC2785n interfaceC2785n, C.k kVar, InterfaceC1866P interfaceC1866P, int i7) {
        super(2);
        this.f5503m = oVar;
        this.f5504n = z6;
        this.o = z10;
        this.f5505p = c0544z0;
        this.f5506q = interfaceC2785n;
        this.f5507r = kVar;
        this.f5508s = interfaceC1866P;
        this.f5509t = i7;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        ((Number) obj2).intValue();
        int iZ = C0735d.Z(this.f5509t | 1);
        C0544z0 c0544z0 = this.f5505p;
        InterfaceC2785n interfaceC2785n = this.f5506q;
        androidx.compose.material3.d.b(this.f5503m, this.f5504n, this.o, c0544z0, interfaceC2785n, this.f5507r, this.f5508s, (C0761q) obj, iZ);
        return m7.q.f19267a;
    }
}
