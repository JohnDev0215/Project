package d7;

import c7.InterfaceC1035a;
import h5.t;
import k7.C1707a;

/* JADX INFO: loaded from: classes.dex */
public final class e implements InterfaceC1035a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f13496a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f13497b;
    public final /* synthetic */ Object c;
    public final /* synthetic */ Object d;

    public /* synthetic */ e(int i7, Object obj, Object obj2, Object[] objArr) {
        this.f13496a = i7;
        this.c = obj;
        this.d = objArr;
        this.f13497b = obj2;
    }

    @Override // c7.InterfaceC1035a
    public final void q(Object... objArr) {
        switch (this.f13496a) {
            case 0:
                InterfaceC1035a[] interfaceC1035aArr = (InterfaceC1035a[]) this.d;
                InterfaceC1035a interfaceC1035a = interfaceC1035aArr[0];
                l lVar = (l) this.c;
                lVar.R0("upgrade", interfaceC1035a);
                lVar.R0("upgradeError", interfaceC1035aArr[0]);
                ((d) this.f13497b).run();
                break;
            case 1:
                if (!((d) this.d).f13495m.f13526q) {
                    ((d) this.f13497b).run();
                } else {
                    ((t) this.c).run();
                }
                break;
            default:
                boolean[] zArr = (boolean[]) this.c;
                if (!zArr[0]) {
                    zArr[0] = true;
                    ((Runnable[]) this.d)[0].run();
                    o[] oVarArr = (o[]) this.f13497b;
                    o oVar = oVarArr[0];
                    oVar.getClass();
                    C1707a.a(new m(oVar, 1));
                    oVarArr[0] = null;
                    break;
                }
                break;
        }
    }

    public e(d dVar, t tVar, d dVar2) {
        this.f13496a = 1;
        this.d = dVar;
        this.c = tVar;
        this.f13497b = dVar2;
    }
}
