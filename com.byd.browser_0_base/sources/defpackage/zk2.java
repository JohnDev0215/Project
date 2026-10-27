package defpackage;

import J.N;
import org.chromium.components.browser_ui.share.a;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: ShareSheetLinkToggleCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class zk2 {
    public final s71 a;
    public a b;
    public jr c;
    public GURL d;
    public boolean e;
    public boolean f;

    public zk2(a aVar, jr jrVar, s71 s71Var) {
        this.a = s71Var;
        b(aVar, jrVar);
    }

    public final a a(int i) {
        a aVar;
        if (this.e) {
            s71 s71Var = this.a;
            return (i != 0 || (aVar = s71Var.l) == null) ? s71Var.m : aVar;
        }
        if (this.f) {
            if (i == 0) {
                this.b.e = this.d.j();
            } else {
                this.b.e = null;
            }
        }
        return this.b;
    }

    public final void b(a aVar, jr jrVar) {
        GURL gurl;
        this.b = aVar;
        this.c = jrVar;
        this.d = jrVar.e;
        sk skVar = bq.a;
        this.e = N.M09VlOh_("PreemptiveLinkToTextGeneration") && this.a != null && jrVar.j == 3;
        this.f = (this.c.j == 0 || (gurl = this.d) == null || gurl.k()) ? false : true;
    }
}
