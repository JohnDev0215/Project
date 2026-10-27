package defpackage;

import J.N;
import java.util.Locale;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class y00 implements Callback {
    public final /* synthetic */ int e;
    public final /* synthetic */ Tab f;

    public /* synthetic */ y00(int i, Tab tab) {
        this.e = i;
        this.f = tab;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(Object obj) {
        int i = this.e;
        Tab tab = this.f;
        switch (i) {
            case 0:
                Callback callback = (Callback) obj;
                if (!tab.isDestroyed()) {
                    N.M70bBKgp(Profile.d(), "COPTD", String.format(Locale.US, "https://task-management-chrome.sandbox.google.com/promotions?url=%s", tab.getUrl().j()), "GET", "application/json; charset=UTF-8", a10.q, "", 10000L, a10.s.a, new n62(callback, 15, tab));
                } else {
                    callback.onResult(null);
                }
                break;
            default:
                xl2 xl2Var = (xl2) obj;
                int i2 = xl2.t;
                if (xl2Var == null && !tab.isDestroyed()) {
                    xl2.n(tab);
                    break;
                }
                break;
        }
    }
}
