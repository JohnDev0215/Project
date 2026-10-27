package defpackage;

import J.N;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.commerce.core.ShoppingService;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: MerchantTrustSignalsDataProvider.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ag1 {
    public final void a(Callback callback, Profile profile, GURL gurl) {
        if (profile == null || profile.h()) {
            callback.onResult(null);
            return;
        }
        ShoppingService shoppingServiceY = n3.Y(profile);
        du duVar = new du(this, callback);
        long j = shoppingServiceY.a;
        if (j == 0) {
            return;
        }
        N.M0walDwl(j, shoppingServiceY, gurl, duVar);
    }
}
