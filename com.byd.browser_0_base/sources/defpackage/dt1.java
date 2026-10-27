package defpackage;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import defpackage.wm3;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.ui.base.DeviceFormFactor;

/* JADX INFO: compiled from: OptionalNewTabButtonController.java */
/* JADX INFO: loaded from: classes2.dex */
public final class dt1 extends ad implements bx {
    public final Context k;
    public final a l;
    public final nw2<xe3> m;
    public boolean n;

    /* JADX INFO: compiled from: OptionalNewTabButtonController.java */
    public static class a {
        public final nw2<gz2> a;
        public final nw2<Tab> b;

        public a(nw2 nw2Var, z2 z2Var) {
            this.a = nw2Var;
            this.b = z2Var;
        }
    }

    public dt1(AppCompatActivity appCompatActivity, Drawable drawable, q2 q2Var, nw2 nw2Var, z2 z2Var, fc2 fc2Var) {
        super(z2Var, null, drawable, appCompatActivity.getString(R$string.button_new_tab), 0, 2);
        this.j = true;
        this.k = appCompatActivity;
        this.l = new a(nw2Var, z2Var);
        this.m = fc2Var;
        q2Var.f(this);
        this.n = DeviceFormFactor.a(appCompatActivity);
    }

    @Override // defpackage.ad
    public final hw0 a(Tab tab) {
        wm3.a aVar = new wm3.a(1);
        aVar.b = true;
        Resources resources = tab.getContext().getResources();
        int i = R$string.adaptive_toolbar_button_new_tab_iph;
        hw0 hw0Var = new hw0(resources, "IPH_AdaptiveButtonInTopToolbarCustomization_NewTab", i, i);
        hw0Var.q = aVar;
        return hw0Var;
    }

    @Override // defpackage.ad
    public final boolean g(Tab tab) {
        return (!super.g(tab) || this.n || bk3.l(tab.getUrl())) ? false : true;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        gz2 gz2Var;
        a aVar = this.l;
        nw2<Tab> nw2Var = aVar.b;
        if (nw2Var == null || nw2Var.get() == null || (gz2Var = aVar.a.get()) == null) {
            return;
        }
        boolean zIsIncognito = nw2Var.get().isIncognito();
        n3.X0("MobileTopToolbarOptionalButtonNewTab");
        gz2Var.p0(zIsIncognito).f(2);
        nw2<xe3> nw2Var2 = this.m;
        if (nw2Var2.f()) {
            nw2Var2.get().notifyEvent("adaptive_toolbar_customization_new_tab_opened");
        }
    }

    @Override // defpackage.bx
    public final void onConfigurationChanged(Configuration configuration) {
        boolean zA = DeviceFormFactor.a(this.k);
        if (this.n == zA) {
            return;
        }
        this.n = zA;
        this.e.a = g(this.f.get());
    }
}
