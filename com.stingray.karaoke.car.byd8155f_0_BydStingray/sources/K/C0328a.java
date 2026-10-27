package K;

import T.C0735d;
import T.C0761q;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: K.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0328a extends A7.o implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ g0.o f3211m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f3212n;
    public final /* synthetic */ int o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0328a(g0.o oVar, int i7, int i10) {
        super(2);
        this.f3211m = oVar;
        this.f3212n = i7;
        this.o = i10;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        ((Number) obj2).intValue();
        int iZ = C0735d.Z(this.f3212n | 1);
        int i7 = this.o;
        AbstractC0334d.b(this.f3211m, (C0761q) obj, iZ, i7);
        return m7.q.f19267a;
    }
}
