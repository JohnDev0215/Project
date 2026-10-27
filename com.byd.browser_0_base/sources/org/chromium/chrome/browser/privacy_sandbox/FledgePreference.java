package org.chromium.chrome.browser.privacy_sandbox;

import android.R;
import android.content.Context;
import android.widget.ImageView;
import defpackage.n32;
import defpackage.ok0;
import defpackage.td2;
import defpackage.tk0;
import org.chromium.components.browser_ui.settings.ImageButtonPreference;
import org.chromium.components.favicon.LargeIconBridge;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes2.dex */
public class FledgePreference extends ImageButtonPreference {
    public final String a0;
    public final LargeIconBridge b0;
    public boolean c0;

    public FledgePreference(Context context, String str, LargeIconBridge largeIconBridge) {
        super(context);
        this.a0 = str;
        this.b0 = largeIconBridge;
        T(str);
    }

    @Override // org.chromium.components.browser_ui.settings.ImageButtonPreference, org.chromium.components.browser_ui.settings.ChromeBasePreference, androidx.preference.Preference
    public final void y(n32 n32Var) {
        super.y(n32Var);
        ImageView imageView = (ImageView) n32Var.u(R.id.icon);
        Context context = this.e;
        tk0.a(context.getResources(), imageView);
        if (this.c0) {
            return;
        }
        ok0.a(context, this.b0, new GURL("https://" + this.a0), new td2(14, this));
        this.c0 = true;
    }
}
