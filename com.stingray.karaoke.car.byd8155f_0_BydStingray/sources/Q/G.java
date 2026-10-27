package Q;

import T.C0735d;
import T.C0761q;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class G extends A7.o implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ g0.o f5523m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ long f5524n;
    public final /* synthetic */ float o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ long f5525p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ int f5526q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ int f5527r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ int f5528s;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public G(g0.o oVar, long j3, float f10, long j10, int i7, int i10, int i11) {
        super(2);
        this.f5523m = oVar;
        this.f5524n = j3;
        this.o = f10;
        this.f5525p = j10;
        this.f5526q = i7;
        this.f5527r = i10;
        this.f5528s = i11;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        ((Number) obj2).intValue();
        int iZ = C0735d.Z(this.f5527r | 1);
        float f10 = this.o;
        J.a(this.f5523m, this.f5524n, f10, this.f5525p, this.f5526q, (C0761q) obj, iZ, this.f5528s);
        return m7.q.f19267a;
    }
}
