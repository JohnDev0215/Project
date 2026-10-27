package U5;

import T.C0735d;
import T.C0761q;
import g1.AbstractC1274a;
import z7.InterfaceC2772a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class g implements InterfaceC2785n {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ g0.o f9128m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2772a f9129n;
    public final /* synthetic */ InterfaceC2772a o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ int f9130p;

    public /* synthetic */ g(g0.o oVar, InterfaceC2772a interfaceC2772a, InterfaceC2772a interfaceC2772a2, int i7, int i10) {
        this.l = i10;
        this.f9128m = oVar;
        this.f9129n = interfaceC2772a;
        this.o = interfaceC2772a2;
        this.f9130p = i7;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        C0761q c0761q = (C0761q) obj;
        Integer num = (Integer) obj2;
        switch (this.l) {
            case 0:
                num.getClass();
                int iZ = C0735d.Z(this.f9130p | 1);
                AbstractC1274a.e(this.f9128m, this.f9129n, this.o, c0761q, iZ);
                break;
            default:
                num.intValue();
                h6.q.e(this.f9128m, this.f9129n, this.o, c0761q, C0735d.Z(this.f9130p | 1));
                break;
        }
        return m7.q.f19267a;
    }
}
