package d7;

import c7.InterfaceC1035a;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
public final class j implements InterfaceC1035a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ o[] f13506a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ e f13507b;
    public final /* synthetic */ String c;
    public final /* synthetic */ l d;

    public j(o[] oVarArr, e eVar, String str, l lVar) {
        this.f13506a = oVarArr;
        this.f13507b = eVar;
        this.c = str;
        this.d = lVar;
    }

    @Override // c7.InterfaceC1035a
    public final void q(Object... objArr) {
        a aVar;
        Object obj = objArr[0];
        if (obj instanceof Exception) {
            aVar = new a("probe error", (Exception) obj);
        } else if (obj instanceof String) {
            aVar = new a("probe error: " + ((String) obj));
        } else {
            aVar = new a("probe error");
        }
        String str = this.f13506a[0].o;
        this.f13507b.q(new Object[0]);
        Logger logger = l.f13510M;
        if (logger.isLoggable(Level.FINE)) {
            logger.fine(String.format("probe transport \"%s\" failed because of error: %s", this.c, obj));
        }
        this.d.N0("upgradeError", aVar);
    }
}
