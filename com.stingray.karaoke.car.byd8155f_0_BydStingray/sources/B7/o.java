package b7;

import h5.t;
import java.util.logging.Level;
import java.util.logging.Logger;
import k7.C1707a;

/* JADX INFO: loaded from: classes.dex */
public final class o implements Runnable {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ r f12307m;

    public /* synthetic */ o(r rVar, int i7) {
        this.l = i7;
        this.f12307m = rVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.l) {
            case 0:
                if (!this.f12307m.f12315n) {
                    r rVar = this.f12307m;
                    if (!rVar.f12317q.f12292p) {
                        if (rVar.f12319s == null) {
                            rVar.f12319s = new n(rVar, rVar.f12317q);
                        }
                        i iVar = this.f12307m.f12317q;
                        iVar.getClass();
                        C1707a.a(new t(14, iVar, null, false));
                        r rVar2 = this.f12307m;
                        if (3 == rVar2.f12317q.f12290C) {
                            r.a1(rVar2);
                        }
                        break;
                    }
                }
                break;
            default:
                if (this.f12307m.f12315n) {
                    Logger logger = r.f12313x;
                    if (logger.isLoggable(Level.FINE)) {
                        logger.fine("performing disconnect (" + this.f12307m.f12316p + ")");
                    }
                    this.f12307m.i1(new j7.d(1));
                }
                this.f12307m.d1();
                if (this.f12307m.f12315n) {
                    this.f12307m.f1("io client disconnect");
                }
                break;
        }
    }
}
