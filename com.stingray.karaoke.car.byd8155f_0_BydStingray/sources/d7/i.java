package d7;

import c7.InterfaceC1035a;
import h5.t;
import java.util.logging.Level;
import java.util.logging.Logger;
import k7.C1707a;

/* JADX INFO: loaded from: classes.dex */
public final class i implements InterfaceC1035a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ boolean[] f13503a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ String f13504b;
    public final /* synthetic */ o[] c;
    public final /* synthetic */ l d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Runnable[] f13505e;

    public i(boolean[] zArr, String str, o[] oVarArr, l lVar, Runnable[] runnableArr) {
        this.f13503a = zArr;
        this.f13504b = str;
        this.c = oVarArr;
        this.d = lVar;
        this.f13505e = runnableArr;
    }

    @Override // c7.InterfaceC1035a
    public final void q(Object... objArr) {
        if (this.f13503a[0]) {
            return;
        }
        Logger logger = l.f13510M;
        if (logger.isLoggable(Level.FINE)) {
            logger.fine("probe transport '" + this.f13504b + "' opened");
        }
        f7.b bVar = new f7.b("probe", "ping");
        o[] oVarArr = this.c;
        o oVar = oVarArr[0];
        oVar.getClass();
        C1707a.a(new t(19, oVar, new f7.b[]{bVar}, false));
        oVarArr[0].U0("packet", new h(this, 0));
    }
}
