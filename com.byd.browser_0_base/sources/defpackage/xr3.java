package defpackage;

import org.chromium.base.Callback;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.signin.services.WebSigninBridge;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.signin.base.GoogleServiceAuthError;
import org.chromium.content_public.browser.LoadUrlParams;

/* JADX INFO: compiled from: WebSigninAccountPickerDelegate.java */
/* JADX INFO: loaded from: classes2.dex */
public final class xr3 implements WebSigninBridge.Listener {
    public final /* synthetic */ Callback a;
    public final /* synthetic */ Tab b;
    public final /* synthetic */ String c;

    public xr3(String str, Callback callback, Tab tab) {
        this.a = callback;
        this.b = tab;
        this.c = str;
    }

    @Override // org.chromium.chrome.browser.signin.services.WebSigninBridge.Listener
    public final void a(GoogleServiceAuthError googleServiceAuthError) {
        Object obj = ThreadUtils.a;
        this.a.onResult(googleServiceAuthError);
    }

    @Override // org.chromium.chrome.browser.signin.services.WebSigninBridge.Listener
    public final void b() {
        Object obj = ThreadUtils.a;
        Tab tab = this.b;
        if (tab.isDestroyed()) {
            return;
        }
        tab.b(new LoadUrlParams(this.c, 0));
    }
}
