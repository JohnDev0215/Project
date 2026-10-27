package defpackage;

import J.N;
import android.content.Context;
import android.util.Pair;
import org.chromium.chrome.R$id;
import org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid;
import org.chromium.components.embedder_support.view.ContentView;
import org.chromium.components.thinwebview.internal.ThinWebViewImpl;
import org.chromium.content.browser.MediaSessionImpl;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.d;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;

/* JADX INFO: compiled from: VideoPlayerCoordinatorImpl.java */
/* JADX INFO: loaded from: classes2.dex */
public final class vl3 implements ul3 {
    public final cm3 a;
    public final am3 b;
    public final em3 c;
    public WebContents d;
    public WebContentsDelegateAndroid e;
    public r12 f;

    public vl3(Context context, em3 em3Var, p11 p11Var, a8 a8Var, c3 c3Var, aw awVar, d dVar) {
        this.c = em3Var;
        PropertyModel propertyModel = new PropertyModel(bm3.q);
        Pair pair = (Pair) p11Var.get();
        this.d = (WebContents) pair.first;
        ContentView contentView = (ContentView) pair.second;
        this.e = new WebContentsDelegateAndroid();
        this.f = new r12((MediaSessionImpl) N.Mtun$qW8(this.d), new yo(17, this));
        t83 t83Var = new t83();
        t83Var.e = -16777216;
        ThinWebViewImpl thinWebViewImpl = new ThinWebViewImpl(context, t83Var, dVar);
        thinWebViewImpl.a(this.d, contentView, this.e);
        cm3 cm3Var = new cm3(context, thinWebViewImpl);
        this.a = cm3Var;
        this.b = new am3(context, propertyModel, em3Var, new k51(cm3Var.a.findViewById(R$id.language_picker), em3Var, a8Var), this.d, this.f, c3Var, awVar);
        j.b(propertyModel, cm3Var, new dm3());
    }
}
