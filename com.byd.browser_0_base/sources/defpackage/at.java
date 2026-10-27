package defpackage;

import android.app.Activity;
import android.view.View;
import java.lang.ref.WeakReference;
import org.chromium.chrome.browser.compositor.CompositorViewHolder;
import org.chromium.ui.base.d;

/* JADX INFO: compiled from: ChromeWindow.java */
/* JADX INFO: loaded from: classes.dex */
public final class at extends g3 {
    public final nw2<CompositorViewHolder> D;
    public final nw2<bi1> E;

    static {
        new pd(2);
    }

    public at(Activity activity, aq1 aq1Var, aq1 aq1Var2, vb1 vb1Var, d dVar) {
        super(activity, new eq(new WeakReference(activity), vb1Var), dVar);
        this.D = aq1Var;
        this.E = aq1Var2;
    }

    @Override // org.chromium.ui.base.WindowAndroid
    public final bi1 n() {
        return this.E.get();
    }

    @Override // org.chromium.ui.base.WindowAndroid
    public final View o() {
        nw2<CompositorViewHolder> nw2Var = this.D;
        if (nw2Var.get() == null) {
            return null;
        }
        return nw2Var.get().getActiveSurfaceView();
    }
}
