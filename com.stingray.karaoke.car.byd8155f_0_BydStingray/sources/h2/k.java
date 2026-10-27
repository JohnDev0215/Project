package h2;

import A.C0050y0;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import e2.C1206n;
import k3.C1628b0;
import k3.C1646h0;
import k3.C1650j0;
import u2.C2510m;
import u2.C2512o;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class k implements Handler.Callback {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f15219m;

    public /* synthetic */ k(Object obj, int i7) {
        this.l = i7;
        this.f15219m = obj;
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        switch (this.l) {
            case 0:
                o oVar = (o) this.f15219m;
                for (n nVar : oVar.d) {
                    if (!nVar.d && nVar.c) {
                        C1206n c1206nL = nVar.f15221b.l();
                        nVar.f15221b = new C0050y0(5);
                        nVar.c = false;
                        oVar.c.c(nVar.f15220a, c1206nL);
                    }
                    if (oVar.f15223b.f15248a.hasMessages(1)) {
                        break;
                    }
                }
                break;
            case 1:
                M.t tVar = (M.t) this.f15219m;
                tVar.getClass();
                if (message.what == 1) {
                    try {
                        C1628b0 c1628b0 = (C1628b0) tVar.f4151m;
                        c1628b0.f17899A.p(c1628b0.c);
                    } catch (RemoteException unused) {
                        AbstractC1346c.z("MCImplBase", "Error in sending flushCommandQueue");
                    }
                }
                break;
            case 2:
                C1646h0 c1646h0 = (C1646h0) this.f15219m;
                c1646h0.getClass();
                if (message.what == 1) {
                    C1650j0 c1650j0 = c1646h0.f17965e;
                    c1650j0.Y0(false, c1650j0.f18008m);
                }
                break;
            default:
                C2512o c2512o = (C2512o) this.f15219m;
                c2512o.getClass();
                if (message.what == 1) {
                    c2512o.f22885n = false;
                    C2510m c2510mC = c2512o.C();
                    if (c2510mC != null) {
                        c2512o.m(c2510mC);
                    }
                }
                break;
        }
        return true;
        return true;
    }
}
