package defpackage;

import java.util.function.BooleanSupplier;
import org.chromium.chrome.browser.customtabs.CustomTabActivity;
import org.chromium.chrome.browser.firstrun.FirstRunActivity;
import org.chromium.chrome.browser.partnercustomizations.PartnerBrowserCustomizations;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.toolbar.g;
import org.chromium.chrome.browser.toolbar.top.ToolbarControlContainer;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class s20 implements BooleanSupplier {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ s20(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // java.util.function.BooleanSupplier
    public final boolean getAsBoolean() {
        nw2<Tab> nw2Var;
        bk bkVarC;
        int i = this.a;
        Object obj = this.b;
        switch (i) {
            case 0:
                ((CustomTabActivity) obj).q1.getClass();
                return false;
            case 1:
                return ((aw1) obj).v();
            case 2:
                ((dw1) obj).getClass();
                return true;
            case 3:
                return ((FirstRunActivity) obj).p0.getBoolean("ShowSyncConsent");
            case 4:
                return ((bz0) obj).b();
            case 5:
                zs1 zs1Var = ((g) obj).k.d;
                ck ckVar = zs1Var.c;
                return ((ckVar == null || (nw2Var = zs1Var.f) == null || (bkVarC = ckVar.c(nw2Var.get())) == null || !bkVarC.a) ? 1 : bkVarC.c.g) == 4;
            case 6:
                return ((PartnerBrowserCustomizations) obj).d();
            default:
                int i2 = ToolbarControlContainer.j;
                return ((ToolbarControlContainer) obj).getVisibility() == 0;
        }
    }
}
