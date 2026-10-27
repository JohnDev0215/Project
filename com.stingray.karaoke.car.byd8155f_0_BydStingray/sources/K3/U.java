package k3;

import d5.C1077F;
import e2.BinderC1198f;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class U implements X {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C1628b0 f17861m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ List f17862n;

    public /* synthetic */ U(C1628b0 c1628b0, List list, int i7) {
        this.l = i7;
        this.f17861m = c1628b0;
        this.f17862n = list;
    }

    @Override // k3.X
    public final void h(InterfaceC1655m interfaceC1655m, int i7) {
        switch (this.l) {
            case 0:
                C1628b0 c1628b0 = this.f17861m;
                c1628b0.getClass();
                C1077F c1077fU = d5.I.u();
                int i10 = 0;
                while (true) {
                    List list = this.f17862n;
                    if (i10 >= list.size()) {
                        interfaceC1655m.u0(c1628b0.c, i7, new BinderC1198f(c1077fU.h()), true);
                    } else {
                        c1077fU.a(((e2.F) list.get(i10)).d(true));
                        i10++;
                    }
                    break;
                }
                break;
            default:
                C1628b0 c1628b02 = this.f17861m;
                c1628b02.getClass();
                C1077F c1077fU2 = d5.I.u();
                int i11 = 0;
                while (true) {
                    List list2 = this.f17862n;
                    if (i11 >= list2.size()) {
                        interfaceC1655m.L0(c1628b02.c, i7, new BinderC1198f(c1077fU2.h()));
                    } else {
                        c1077fU2.a(((e2.F) list2.get(i11)).d(true));
                        i11++;
                    }
                    break;
                }
                break;
        }
    }
}
