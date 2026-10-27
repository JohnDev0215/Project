package defpackage;

import android.app.Dialog;
import android.view.View;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.browser_ui.site_settings.SingleCategorySettings;
import org.chromium.components.browser_ui.site_settings.f;
import org.chromium.content_public.browser.BrowserContextHandle;

/* JADX INFO: compiled from: SingleCategorySettings.java */
/* JADX INFO: loaded from: classes2.dex */
public final class fn2 implements View.OnClickListener {
    public final /* synthetic */ f e;
    public final /* synthetic */ BrowserContextHandle f;
    public final /* synthetic */ int g;
    public final /* synthetic */ Dialog h;
    public final /* synthetic */ SingleCategorySettings i;

    public fn2(SingleCategorySettings singleCategorySettings, f fVar, Profile profile, int i, Dialog dialog) {
        this.i = singleCategorySettings;
        this.e = fVar;
        this.f = profile;
        this.g = i;
        this.h = dialog;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        this.e.m(this.f, this.g, 0);
        SingleCategorySettings singleCategorySettings = this.i;
        if (singleCategorySettings.k0.b == 23) {
            n3.Y0(3, false);
        }
        singleCategorySettings.P0();
        this.h.dismiss();
    }
}
