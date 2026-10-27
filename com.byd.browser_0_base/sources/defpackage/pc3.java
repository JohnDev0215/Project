package defpackage;

import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import org.chromium.chrome.browser.toolbar.g;

/* JADX INFO: compiled from: ToolbarManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class pc3 implements ComponentCallbacks {
    public final /* synthetic */ g e;

    public pc3(g gVar) {
        this.e = gVar;
    }

    @Override // android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
        int i = configuration.orientation;
        g gVar = this.e;
        if (i == gVar.A0) {
            return;
        }
        gVar.A0 = i;
        k2 k2Var = gVar.Q;
        if (k2Var != null && k2Var.b && k2Var.a == null) {
            k2Var.c();
        }
    }

    @Override // android.content.ComponentCallbacks
    public final void onLowMemory() {
    }
}
