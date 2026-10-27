package defpackage;

import J.N;
import android.content.Context;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;
import org.chromium.chrome.browser.layouts.scene_layer.SceneLayer;

/* JADX INFO: compiled from: ToolbarSwipeSceneLayer.java */
/* JADX INFO: loaded from: classes.dex */
public final class zc3 extends SceneLayer {
    public final Context f;
    public long g;

    public zc3(Context context, TabContentManager tabContentManager) {
        this.f = context;
        N.Mdsem$C6(this.g, this, tabContentManager);
    }

    @Override // org.chromium.chrome.browser.layouts.scene_layer.SceneLayer
    public final void d() {
        if (this.g == 0) {
            this.g = N.MRop2ASy(this);
        }
    }

    public final void e(m61 m61Var, boolean z, int i) {
        float f = this.f.getResources().getDisplayMetrics().density;
        N.M_4pjYxH(this.g, this, m61Var != null ? m61Var.h(m61.e) : -1, z, m61Var != null ? m61Var.k(m61.B) : false, i, m61Var != null ? m61Var.g(m61.h) * f : 0.0f, m61Var != null ? m61Var.g(m61.i) * f : 0.0f);
    }
}
