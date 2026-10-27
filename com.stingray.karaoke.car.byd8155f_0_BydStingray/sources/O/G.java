package O;

import T.C0735d;
import T.C0761q;
import g1.AbstractC1274a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class G extends A7.o implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f4898m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ g0.o f4899n;
    public final /* synthetic */ b0.b o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ int f4900p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ G(g0.o oVar, b0.b bVar, int i7, int i10) {
        super(2);
        this.f4898m = i10;
        this.f4899n = oVar;
        this.o = bVar;
        this.f4900p = i7;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        int i7 = this.f4898m;
        C0761q c0761q = (C0761q) obj;
        ((Number) obj2).intValue();
        switch (i7) {
            case 0:
                int iZ = C0735d.Z(this.f4900p | 1);
                l9.d.s(this.f4899n, this.o, c0761q, iZ);
                break;
            default:
                int iZ2 = C0735d.Z(this.f4900p | 1);
                AbstractC1274a.v(this.f4899n, this.o, c0761q, iZ2);
                break;
        }
        return m7.q.f19267a;
    }
}
