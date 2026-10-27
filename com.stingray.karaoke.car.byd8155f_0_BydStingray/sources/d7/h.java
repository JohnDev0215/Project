package d7;

import B5.RunnableC0079c;
import c7.InterfaceC1035a;
import e7.AbstractC1232b;
import h5.t;
import java.util.logging.Level;
import java.util.logging.Logger;
import k7.C1707a;

/* JADX INFO: loaded from: classes.dex */
public final class h implements InterfaceC1035a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f13501a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f13502b;

    public /* synthetic */ h(Object obj, int i7) {
        this.f13501a = i7;
        this.f13502b = obj;
    }

    @Override // c7.InterfaceC1035a
    public final void q(Object... objArr) {
        boolean z6 = false;
        Object obj = this.f13502b;
        switch (this.f13501a) {
            case 0:
                i iVar = (i) obj;
                if (!iVar.f13503a[0]) {
                    f7.b bVar = (f7.b) objArr[0];
                    boolean zEquals = "pong".equals(bVar.f14641a);
                    String str = iVar.f13504b;
                    o[] oVarArr = iVar.c;
                    l lVar = iVar.d;
                    if (!zEquals || !"probe".equals(bVar.f14642b)) {
                        Logger logger = l.f13510M;
                        if (logger.isLoggable(Level.FINE)) {
                            logger.fine("probe transport '" + str + "' failed");
                        }
                        a aVar = new a("probe error");
                        String str2 = oVarArr[0].o;
                        lVar.N0("upgradeError", aVar);
                        break;
                    } else {
                        Logger logger2 = l.f13510M;
                        Level level = Level.FINE;
                        if (logger2.isLoggable(level)) {
                            logger2.fine("probe transport '" + str + "' pong");
                        }
                        lVar.f13526q = true;
                        lVar.N0("upgrading", oVarArr[0]);
                        o oVar = oVarArr[0];
                        if (oVar != null) {
                            "websocket".equals(oVar.o);
                            Logger logger3 = l.f13510M;
                            if (logger2.isLoggable(level)) {
                                logger2.fine("pausing current transport '" + lVar.f13517F.o + "'");
                            }
                            AbstractC1232b abstractC1232b = (AbstractC1232b) lVar.f13517F;
                            RunnableC0079c runnableC0079c = new RunnableC0079c(this, 12);
                            abstractC1232b.getClass();
                            C1707a.a(new t(21, abstractC1232b, runnableC0079c, z6));
                            break;
                        }
                    }
                }
                break;
            case 1:
                AbstractC1232b.f14470A.fine("writing close packet");
                ((AbstractC1232b) obj).c1(new f7.b[]{new f7.b(null, "close")});
                break;
            default:
                C1707a.a(new RunnableC0079c(this, 14));
                break;
        }
    }
}
