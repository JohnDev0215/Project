package defpackage;

import defpackage.z2;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: compiled from: ChromeActionModeHandler.java */
/* JADX INFO: loaded from: classes.dex */
public final class ro extends z2.b {
    public final /* synthetic */ to h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ro(to toVar, z2 z2Var) {
        super(z2Var, false);
        this.h = toVar;
    }

    @Override // z2.b
    public final void y1(Tab tab) {
        to toVar;
        Tab tab2;
        WebContents webContents;
        if (tab == null || tab == (tab2 = (toVar = this.h).b)) {
            return;
        }
        mc mcVar = toVar.a;
        if (tab2 != null && tab2.isInitialized()) {
            l53.d(toVar.b).g.c(mcVar);
        }
        toVar.b = tab;
        l53 l53VarD = l53.d(tab);
        if (!l53VarD.g.a(mcVar) || (webContents = l53VarD.f.g) == null) {
            return;
        }
        mcVar.onResult(webContents);
    }
}
