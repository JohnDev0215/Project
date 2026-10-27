package E;

import G.I;
import T.X;
import java.util.ArrayList;
import java.util.List;
import n3.C1945j;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class n extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f1364m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ X f1365n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ n(X x8, int i7) {
        super(0);
        this.f1364m = i7;
        this.f1365n = x8;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.f1364m) {
            case 0:
                return new k((InterfaceC2782k) this.f1365n.getValue());
            case 1:
                return new F.g((InterfaceC2782k) this.f1365n.getValue());
            case 2:
                return (I) ((InterfaceC2772a) this.f1365n.getValue()).a();
            case 3:
                X x8 = this.f1365n;
                if (x8 != null) {
                    return (List) x8.getValue();
                }
                return null;
            case 4:
                Boolean bool = (Boolean) this.f1365n.getValue();
                bool.booleanValue();
                return bool;
            case 5:
                return (P.h) this.f1365n.getValue();
            default:
                List list = (List) this.f1365n.getValue();
                ArrayList arrayList = new ArrayList();
                for (Object obj : list) {
                    if (A7.m.a(((C1945j) obj).f19865m.l, "composable")) {
                        arrayList.add(obj);
                    }
                }
                return arrayList;
        }
    }
}
