package defpackage;

import android.view.View;
import java.util.Iterator;
import org.chromium.chrome.browser.tabmodel.TabModel;

/* JADX INFO: compiled from: EmptyBackgroundViewWrapper.java */
/* JADX INFO: loaded from: classes2.dex */
public final class zg0 implements View.OnAttachStateChangeListener {
    public final /* synthetic */ ah0 e;

    public zg0(ah0 ah0Var) {
        this.e = ah0Var;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        ah0 ah0Var = this.e;
        y13 y13Var = ah0Var.b;
        Iterator it = y13Var.y().iterator();
        while (it.hasNext()) {
            ((TabModel) it.next()).l(ah0Var.d);
        }
        y13Var.l(ah0Var.e);
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
    }
}
