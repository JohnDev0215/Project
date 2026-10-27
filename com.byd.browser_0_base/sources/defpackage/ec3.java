package defpackage;

import J.N;
import android.content.res.Resources;
import android.graphics.Rect;
import defpackage.wm3;
import org.chromium.base.Callback;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.toolbar.g;
import org.chromium.chrome.browser.toolbar.top.ToggleTabStackButton;
import org.chromium.chrome.browser.toolbar.top.ToolbarControlContainer;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class ec3 implements Callback {
    public final /* synthetic */ int e;
    public final /* synthetic */ g f;

    public /* synthetic */ ec3(g gVar, int i) {
        this.e = i;
        this.f = gVar;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(Object obj) {
        boolean zM6bsIDpc;
        int i = this.e;
        g gVar = this.f;
        switch (i) {
            case 0:
                gVar.s();
                break;
            default:
                xl2 xl2Var = (xl2) obj;
                gVar.getClass();
                if (xl2Var != null && xl2Var.q() != null) {
                    if (a8.P()) {
                        sk skVar = bq.a;
                        zM6bsIDpc = N.M6bsIDpc("CommercePriceTracking", "check_if_price_drop_is_seen", false);
                    } else {
                        zM6bsIDpc = false;
                    }
                    if (!(zM6bsIDpc ? xl2Var.o.f : false)) {
                        int i2 = R$id.tab_switcher_button;
                        ToolbarControlContainer toolbarControlContainer = gVar.l;
                        ToggleTabStackButton toggleTabStackButton = (ToggleTabStackButton) toolbarControlContainer.findViewById(i2);
                        pk3 pk3Var = new pk3(gVar.T, gVar.S);
                        wm3.a aVar = new wm3.a(1);
                        aVar.b = true;
                        Resources resources = toolbarControlContainer.getResources();
                        int i3 = R$string.price_drop_spotted_iph;
                        hw0 hw0Var = new hw0(resources, "IPH_PriceDropNTP", i3, i3);
                        hw0Var.n = new Rect(0, 0, 0, -toolbarControlContainer.getResources().getDimensionPixelOffset(R$dimen.price_drop_spotted_iph_ntp_tabswitcher_y_inset));
                        hw0Var.j = toggleTabStackButton;
                        hw0Var.q = aVar;
                        hw0Var.e = true;
                        pk3Var.a(hw0Var.a());
                    }
                    break;
                }
                break;
        }
    }
}
