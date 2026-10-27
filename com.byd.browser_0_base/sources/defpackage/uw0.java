package defpackage;

import J.N;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: compiled from: ImageDescriptionsController.java */
/* JADX INFO: loaded from: classes.dex */
public final class uw0 implements ww0 {
    public final /* synthetic */ vw0 a;

    public uw0(vw0 vw0Var) {
        this.a = vw0Var;
    }

    public final void a(WebContents webContents, boolean z) {
        this.a.getClass();
        SharedPreferencesManager.getInstance().c("Chrome.ImageDescriptions.JustOnceCount");
        SharedPreferencesManager.getInstance().l("Chrome.ImageDescriptions.DontAskAgain", z);
        N.MlWjE4_y(webContents);
    }
}
