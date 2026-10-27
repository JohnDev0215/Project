package defpackage;

import J.N;
import android.graphics.RectF;
import java.util.ArrayList;
import org.chromium.chrome.browser.layouts.EventFilter;
import org.chromium.chrome.browser.layouts.scene_layer.SceneLayer;
import org.chromium.ui.resources.ResourceManager;

/* JADX INFO: compiled from: StatusIndicatorSceneLayer.java */
/* JADX INFO: loaded from: classes2.dex */
public final class st2 extends ke2 implements je2 {
    public long f;
    public int g;
    public final kj h;
    public boolean i;

    public st2(fj fjVar) {
        this.h = fjVar;
    }

    @Override // defpackage.je2
    public final boolean G(long j) {
        return false;
    }

    @Override // defpackage.je2
    public final ke2 U(RectF rectF, ResourceManager resourceManager) {
        N.MsJYu3I7(this.f, this, resourceManager, this.g, this.h.r());
        return this;
    }

    @Override // defpackage.je2
    public final boolean a() {
        return false;
    }

    @Override // org.chromium.chrome.browser.layouts.scene_layer.SceneLayer
    public final void d() {
        if (this.f == 0) {
            this.f = N.MvcFT3Dn(this);
        }
    }

    @Override // defpackage.ke2
    public final void e(SceneLayer sceneLayer) {
        N.MqEZWVhE(this.f, this, sceneLayer);
    }

    @Override // defpackage.je2
    public final boolean o0() {
        return false;
    }

    @Override // defpackage.je2
    public final boolean q1() {
        return this.i;
    }

    @Override // defpackage.je2
    public final EventFilter x() {
        return null;
    }

    @Override // defpackage.je2
    public final void N() {
    }

    @Override // defpackage.je2
    public final void f0(ArrayList arrayList) {
    }

    @Override // defpackage.je2
    public final void M0(float f, float f2, float f3, int i) {
    }
}
