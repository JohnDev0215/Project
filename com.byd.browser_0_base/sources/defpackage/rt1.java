package defpackage;

import J.N;
import android.view.View;
import org.chromium.chrome.browser.layouts.scene_layer.SceneLayer;

/* JADX INFO: compiled from: OverscrollSceneLayer.java */
/* JADX INFO: loaded from: classes.dex */
public final class rt1 extends ke2 {
    public final View f;
    public long g;
    public float h;

    public rt1(g3 g3Var, View view) {
        this.f = view;
        this.g = N.MDUT5Goj(this, g3Var);
    }

    public final void destroy() {
        N.MPFnESYL(this.e, this);
        this.g = 0L;
    }

    @Override // defpackage.ke2
    public final void e(SceneLayer sceneLayer) {
        N.MXXwWQI$(this.g, this, sceneLayer);
    }

    @Override // org.chromium.chrome.browser.layouts.scene_layer.SceneLayer
    public final void d() {
    }
}
