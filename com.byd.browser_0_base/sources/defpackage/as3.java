package defpackage;

import defpackage.h20;
import defpackage.z2;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.chrome.browser.tab.state.CriticalPersistedTabData;
import org.chromium.content_public.browser.NavigationHandle;

/* JADX INFO: compiled from: WebappActiveTabUmaTracker.java */
/* JADX INFO: loaded from: classes2.dex */
public final class as3 extends z2.b {
    public final oj h;
    public final h20 i;

    public as3(z2 z2Var, oj ojVar, h20 h20Var) {
        super(z2Var, false);
        this.h = ojVar;
        this.i = h20Var;
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void o1(TabImpl tabImpl, NavigationHandle navigationHandle) {
        if (!navigationHandle.g || navigationHandle.c) {
            return;
        }
        boolean z = true;
        oj2.O("Webapp.NavigationStatus", !navigationHandle.i);
        if (this.h.T() && CriticalPersistedTabData.m(tabImpl).p == -1) {
            h20.b bVar = this.i.h;
            if (bVar != null && bVar.c == 2) {
                z = false;
            }
            oj2.O("WebApk.Navigation.InScope", z);
        }
    }
}
