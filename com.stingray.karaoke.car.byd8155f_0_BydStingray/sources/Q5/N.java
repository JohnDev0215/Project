package Q5;

import T.C0735d;
import T.C0761q;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class N implements InterfaceC2785n {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ g0.o f6443m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ String f6444n;
    public final /* synthetic */ D.L o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ int f6445p;

    public /* synthetic */ N(g0.o oVar, String str, D.L l, int i7, int i10) {
        this.l = i10;
        this.f6443m = oVar;
        this.f6444n = str;
        this.o = l;
        this.f6445p = i7;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        int i7 = this.l;
        C0761q c0761q = (C0761q) obj;
        ((Integer) obj2).getClass();
        switch (i7) {
            case 0:
                int iZ = C0735d.Z(this.f6445p | 1);
                w0.c.g(this.f6443m, this.f6444n, this.o, c0761q, iZ);
                break;
            default:
                int iZ2 = C0735d.Z(this.f6445p | 1);
                P8.n.b(this.f6443m, this.f6444n, this.o, c0761q, iZ2);
                break;
        }
        return m7.q.f19267a;
    }
}
