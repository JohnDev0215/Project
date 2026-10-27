package defpackage;

import J.N;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import defpackage.wm3;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: ShareButtonController.java */
/* JADX INFO: loaded from: classes2.dex */
public final class zj2 extends ad {
    public final n3 k;
    public final zp1<ck2> l;
    public final nw2<xe3> m;
    public final Runnable n;

    public zj2(AppCompatActivity appCompatActivity, Drawable drawable, z2 z2Var, zp1 zp1Var, fc2 fc2Var, n3 n3Var, bi1 bi1Var, f52 f52Var) {
        super(z2Var, bi1Var, drawable, appCompatActivity.getString(R$string.share), 0, 3);
        this.k = n3Var;
        this.l = zp1Var;
        this.m = fc2Var;
        this.n = f52Var;
    }

    @Override // defpackage.ad
    public final hw0 a(Tab tab) {
        wm3.a aVar = new wm3.a(1);
        aVar.b = true;
        Resources resources = tab.getContext().getResources();
        int i = R$string.adaptive_toolbar_button_share_iph;
        hw0 hw0Var = new hw0(resources, "IPH_AdaptiveButtonInTopToolbarCustomization_Share", i, i);
        hw0Var.q = aVar;
        return hw0Var;
    }

    @Override // defpackage.ad
    public final boolean g(Tab tab) {
        if (!super.g(tab) || this.l.get() == null) {
            return false;
        }
        this.k.getClass();
        return n3.j1(tab);
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        Tab tab;
        ck2 ck2Var = this.l.get();
        if (ck2Var == null || (tab = this.f.get()) == null) {
            return;
        }
        Runnable runnable = this.n;
        if (runnable != null) {
            runnable.run();
        }
        n3.X0("MobileTopToolbarShareButton");
        if (tab.c() != null) {
            N.M$ejnyHh(tab.c(), "TopToolbar.Share", "HasOccurred");
        }
        ck2Var.b(1, tab, false);
        nw2<xe3> nw2Var = this.m;
        if (nw2Var.f()) {
            nw2Var.get().notifyEvent("adaptive_toolbar_customization_share_opened");
        }
    }
}
