package defpackage;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.browser_ui.bottomsheet.f;

/* JADX INFO: compiled from: PriceTrackingButtonController.java */
/* JADX INFO: loaded from: classes2.dex */
public final class j42 extends ad {
    public final nw2<ny2> k;
    public final ji l;

    /* JADX WARN: Illegal instructions before constructor call */
    public j42(AppCompatActivity appCompatActivity, z2 z2Var, bi1 bi1Var, f fVar, Drawable drawable, zp1 zp1Var) {
        int i = R$string.enable_price_tracking_menu_item;
        super(z2Var, bi1Var, drawable, appCompatActivity.getString(i), i, 6);
        this.k = zp1Var;
        this.l = fVar;
        fVar.g(new i42(this));
    }

    @Override // defpackage.ad
    public final hw0 a(Tab tab) {
        Resources resources = tab.getContext().getResources();
        int i = R$string.iph_price_tracking_menu_item;
        return new hw0(resources, "IPH_ContextualPageActions_QuietVariant", i, i);
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        this.k.get().b(this.f.get());
    }
}
