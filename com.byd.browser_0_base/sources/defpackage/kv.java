package defpackage;

import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.price_tracking.PriceDropNotificationManagerImpl;
import org.chromium.components.commerce.core.ShoppingService;

/* JADX INFO: compiled from: CommerceSubscriptionsService.java */
/* JADX INFO: loaded from: classes2.dex */
public final class kv {
    public final SharedPreferencesManager a = SharedPreferencesManager.getInstance();
    public final u32 b;
    public ox0 c;
    public q2 d;
    public jv e;
    public final ShoppingService f;

    public kv(ShoppingService shoppingService, PriceDropNotificationManagerImpl priceDropNotificationManagerImpl) {
        this.f = shoppingService;
        this.b = priceDropNotificationManagerImpl;
    }

    public final void a() {
        q2 q2Var = this.d;
        if (q2Var != null) {
            q2Var.e(this.e);
        }
        ox0 ox0Var = this.c;
        if (ox0Var != null) {
            ox0Var.a.g(false).l(ox0Var.b);
            this.c = null;
        }
    }
}
