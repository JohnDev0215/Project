package d7;

import B5.RunnableC0079c;
import c7.InterfaceC1035a;
import h5.t;
import java.util.logging.Logger;
import k7.C1707a;

/* JADX INFO: loaded from: classes.dex */
public final class d implements Runnable {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ l f13495m;

    public /* synthetic */ d(l lVar, int i7) {
        this.l = i7;
        this.f13495m = lVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = 0;
        int i10 = 2;
        int i11 = 1;
        l lVar = this.f13495m;
        switch (this.l) {
            case 0:
                lVar.getClass();
                lVar.i1(new f7.b(null, "pong"));
                break;
            case 1:
                C1707a.a(new RunnableC0079c(this, 11));
                break;
            case 2:
                Logger logger = l.f13510M;
                lVar.e1("forced close", null);
                l.f13510M.fine("socket closing - telling transport to close");
                o oVar = lVar.f13517F;
                oVar.getClass();
                C1707a.a(new m(oVar, i11));
                break;
            default:
                int i12 = lVar.f13523L;
                if (i12 == 1 || i12 == 2) {
                    lVar.f13523L = 3;
                    d dVar = new d(lVar, i10);
                    InterfaceC1035a[] interfaceC1035aArr = new InterfaceC1035a[1];
                    interfaceC1035aArr[0] = new e(i7, lVar, dVar, interfaceC1035aArr);
                    t tVar = new t(lVar, 18, interfaceC1035aArr);
                    if (lVar.f13516E.size() > 0) {
                        lVar.U0("drain", new e(this, tVar, dVar));
                    } else if (!lVar.f13526q) {
                        dVar.run();
                    } else {
                        tVar.run();
                    }
                }
                break;
        }
    }
}
