package defpackage;

import android.content.Context;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import org.chromium.chrome.browser.merchant_viewer.BottomSheetToolbarView;
import org.chromium.chrome.browser.ui.favicon.FaviconHelper;
import org.chromium.components.browser_ui.bottomsheet.f;
import org.chromium.components.thinwebview.internal.ThinWebViewImpl;
import org.chromium.ui.base.c;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;

/* JADX INFO: compiled from: MerchantTrustBottomSheetCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class nf1 implements View.OnLayoutChangeListener {
    public final Context e;
    public final ji f;
    public final View g;
    public final wf1 h;
    public final qf1 i;
    public mf1 j;
    public lf1 k;
    public int l;
    public ThinWebViewImpl m;
    public BottomSheetToolbarView n;
    public PropertyModel o;
    public j p;
    public final c q;

    public nf1(AppCompatActivity appCompatActivity, g3 g3Var, f fVar, View view, wf1 wf1Var, c cVar, zp1 zp1Var) {
        this.e = appCompatActivity;
        this.f = fVar;
        this.g = view;
        this.h = wf1Var;
        this.q = cVar;
        this.i = new qf1(appCompatActivity, g3Var, wf1Var, zp1Var, new FaviconHelper());
    }

    @Override // android.view.View.OnLayoutChangeListener
    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        int iB;
        if (this.k == null || (iB = this.f.b()) == 0 || this.l == iB) {
            return;
        }
        this.m.getView().getLayoutParams().height = ((int) (iB * 0.9f)) - this.n.a;
        ao3.e(this.m.getView(), "MerchantTrustBottomSheetCoordinator.onLayoutChange");
        this.l = iB;
    }
}
