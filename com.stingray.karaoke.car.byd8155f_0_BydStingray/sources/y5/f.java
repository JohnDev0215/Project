package Y5;

import T.C0735d;
import T.C0761q;
import e2.W;
import g0.o;
import g1.AbstractC1274a;
import m7.q;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class f implements InterfaceC2785n {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ o f10792m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ W f10793n;
    public final /* synthetic */ boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ int f10794p;

    public /* synthetic */ f(o oVar, W w10, boolean z6, int i7, int i10) {
        this.l = i10;
        this.f10792m = oVar;
        this.f10793n = w10;
        this.o = z6;
        this.f10794p = i7;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        int i7 = this.l;
        C0761q c0761q = (C0761q) obj;
        ((Integer) obj2).getClass();
        switch (i7) {
            case 0:
                int iZ = C0735d.Z(this.f10794p | 1);
                AbstractC1274a.t(this.f10792m, this.f10793n, this.o, c0761q, iZ);
                break;
            default:
                int iZ2 = C0735d.Z(this.f10794p | 1);
                AbstractC1274a.u(this.f10792m, this.f10793n, this.o, c0761q, iZ2);
                break;
        }
        return q.f19267a;
    }
}
