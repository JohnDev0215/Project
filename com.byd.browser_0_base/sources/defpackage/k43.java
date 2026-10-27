package defpackage;

import android.graphics.Rect;
import android.os.Bundle;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.webapps.AddToHomescreenCoordinator;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class k43 implements nw2 {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    public /* synthetic */ k43(Object obj, int i, Object obj2) {
        this.e = i;
        this.f = obj;
        this.g = obj2;
    }

    @Override // defpackage.nw2
    public final Object get() {
        int i = this.e;
        Object obj = this.g;
        Object obj2 = this.f;
        switch (i) {
            case 0:
                l43 l43Var = (l43) obj2;
                l43Var.getClass();
                return Float.valueOf(((Rect) ((nw2) obj).get()).width() / (l43Var.e * l43Var.r));
            case 1:
                l43 l43Var2 = (l43) obj2;
                l43Var2.getClass();
                return Float.valueOf(((Rect) ((nw2) obj).get()).top / l43Var2.r);
            default:
                t3 t3Var = (t3) obj2;
                Tab tab = (Tab) obj;
                t3Var.getClass();
                if (!tab.isDestroyed() && t3Var.a != null) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("AppMenuTitleShown", 1);
                    AddToHomescreenCoordinator.b(t3Var.a, t3Var.b, t3Var.c, tab.c(), bundle);
                    t3Var.e.notifyEvent("add_to_homescreen_dialog_shown");
                    n3.X0("Android.AddToHomescreenIPH.Message.Clicked");
                }
                return 1;
        }
    }
}
