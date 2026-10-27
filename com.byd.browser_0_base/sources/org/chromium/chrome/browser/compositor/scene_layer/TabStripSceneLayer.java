package org.chromium.chrome.browser.compositor.scene_layer;

import J.N;
import android.content.Context;
import defpackage.bq;
import defpackage.ke2;
import org.chromium.chrome.browser.layouts.scene_layer.SceneLayer;

/* JADX INFO: loaded from: classes.dex */
public class TabStripSceneLayer extends ke2 {
    public long f;

    public TabStripSceneLayer(Context context) {
        float f = context.getResources().getDisplayMetrics().density;
    }

    @Override // org.chromium.chrome.browser.layouts.scene_layer.SceneLayer
    public final void d() {
        if (this.f == 0) {
            this.f = N.M8m15MW0(this, bq.f0.a());
        }
    }

    public final void destroy() {
        N.MPFnESYL(this.e, this);
        this.f = 0L;
    }

    @Override // defpackage.ke2
    public final void e(SceneLayer sceneLayer) {
        N.Mm9evJzJ(this.f, this, sceneLayer);
    }
}
