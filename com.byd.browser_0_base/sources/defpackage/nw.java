package defpackage;

import org.chromium.chrome.browser.layouts.scene_layer.SceneLayer;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;

/* JADX INFO: compiled from: CompositorModelChangeProcessor.java */
/* JADX INFO: loaded from: classes.dex */
public final class nw<V extends SceneLayer> {
    public final V a;
    public final PropertyModel b;
    public final j.a<PropertyModel, V, v62> c;
    public final a d;
    public final mw e;
    public final td2 f;

    /* JADX INFO: compiled from: CompositorModelChangeProcessor.java */
    public static class a extends aq1<Long> {
        public final Runnable h;

        public a(wv wvVar) {
            this.h = wvVar;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public nw(PropertyModel propertyModel, SceneLayer sceneLayer, j.a aVar, a aVar2) {
        this.b = propertyModel;
        this.a = sceneLayer;
        this.c = aVar;
        this.d = aVar2;
        td2 td2Var = new td2(3, this);
        this.f = td2Var;
        aVar2.p(td2Var);
        aVar2.h.run();
        mw mwVar = new mw(0, this);
        this.e = mwVar;
        propertyModel.a(mwVar);
    }
}
