package defpackage;

import J.N;
import org.chromium.chrome.browser.layouts.scene_layer.SceneLayer;

/* JADX INFO: compiled from: StaticTabSceneLayer.java */
/* JADX INFO: loaded from: classes.dex */
public final class zs2 extends SceneLayer {
    public long f;

    @Override // org.chromium.chrome.browser.layouts.scene_layer.SceneLayer
    public final void d() {
        if (this.f == 0) {
            this.f = N.MqXhoEEp(this);
        }
    }

    public final void destroy() {
        N.MPFnESYL(this.e, this);
        this.f = 0L;
    }
}
