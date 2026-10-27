package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import org.chromium.chrome.R$layout;
import org.chromium.components.browser_ui.modaldialog.AppModalPresenter;
import org.chromium.components.offline_items_collection.a;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;

/* JADX INFO: compiled from: DownloadInterstitialCoordinatorImpl.java */
/* JADX INFO: loaded from: classes.dex */
public final class dc0 implements cc0 {
    public final mc0 e;
    public final kc0 f;
    public final j g;

    public dc0(p30 p30Var, String str, a aVar, wp2 wp2Var, z8 z8Var) {
        Context context = (Context) p30Var.get();
        mc0 mc0Var = new mc0(context, LayoutInflater.from(context).inflate(R$layout.download_interstitial, (ViewGroup) null));
        this.e = mc0Var;
        PropertyModel propertyModel = new PropertyModel(PropertyModel.e(lc0.l));
        propertyModel.p(lc0.j, z8Var);
        this.f = new kc0(p30Var, propertyModel, str, aVar, wp2Var, new bi1(new AppModalPresenter((Context) p30Var.get())));
        this.g = j.b(propertyModel, mc0Var, new cg(4));
    }

    @Override // defpackage.w80
    public final void destroy() {
        kc0 kc0Var = this.f;
        kc0Var.d.j(kc0Var.f);
        this.g.c();
    }
}
