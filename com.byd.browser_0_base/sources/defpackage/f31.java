package defpackage;

import android.content.Context;
import org.chromium.chrome.browser.contextmenu.ContextMenuPopulatorFactory;
import org.chromium.components.embedder_support.contextmenu.ContextMenuParams;

/* JADX INFO: compiled from: JankMetrics.java */
/* JADX INFO: loaded from: classes.dex */
public final class f31 implements ContextMenuPopulatorFactory {
    public final int a;
    public final Object b;
    public final Object c;
    public final Object d;

    public /* synthetic */ f31(az2 az2Var, nw2 nw2Var, int i, zi0 zi0Var) {
        this.b = az2Var;
        this.c = nw2Var;
        this.a = i;
        this.d = zi0Var;
    }

    @Override // org.chromium.chrome.browser.contextmenu.ContextMenuPopulatorFactory
    public final vz a(Context context, ContextMenuParams contextMenuParams, uz uzVar) {
        return new aq((pz) this.b, (nw2) this.c, this.a, (zi0) this.d, context, contextMenuParams, uzVar);
    }

    @Override // org.chromium.chrome.browser.contextmenu.ContextMenuPopulatorFactory
    public final void onDestroy() {
        ((pz) this.b).getClass();
    }
}
