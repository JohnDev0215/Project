package defpackage;

import android.view.View;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.toolbar.g;

/* JADX INFO: compiled from: ToolbarManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class nc3 implements View.OnAttachStateChangeListener {
    public final /* synthetic */ g e;

    public nc3(g gVar) {
        this.e = gVar;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
        g gVar = this.e;
        gVar.u((Tab) gVar.y.f);
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
    }
}
