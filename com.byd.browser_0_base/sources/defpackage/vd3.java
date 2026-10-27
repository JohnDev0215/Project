package defpackage;

import J.N;
import org.chromium.chrome.browser.layouts.scene_layer.SceneLayer;
import org.chromium.ui.resources.ResourceManager;

/* JADX INFO: compiled from: TopToolbarSceneLayer.java */
/* JADX INFO: loaded from: classes2.dex */
public final class vd3 extends ke2 {
    public long f;
    public final nw2<ResourceManager> g;

    public vd3(nw2<ResourceManager> nw2Var) {
        this.g = nw2Var;
    }

    @Override // org.chromium.chrome.browser.layouts.scene_layer.SceneLayer
    public final void d() {
        if (this.f == 0) {
            this.f = N.MFC6akug(this);
        }
    }

    public final void destroy() {
        N.MPFnESYL(this.e, this);
        this.f = 0L;
    }

    @Override // defpackage.ke2
    public final void e(SceneLayer sceneLayer) {
        N.M9k8YKLL(this.f, this, sceneLayer);
    }
}
