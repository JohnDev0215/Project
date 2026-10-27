package defpackage;

import defpackage.rq0;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.toolbar.g;

/* JADX INFO: compiled from: ToolbarManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class uc3 implements rq0.a {
    public final /* synthetic */ g e;

    public uc3(g gVar) {
        this.e = gVar;
    }

    @Override // rq0.a
    public final void a(sq0 sq0Var, Tab tab) {
        xm0 xm0Var = this.e.F;
        if (xm0Var != null) {
            xm0Var.a(true);
        }
    }
}
