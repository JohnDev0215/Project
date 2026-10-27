package defpackage;

import J.N;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.browser_ui.site_settings.R$string;
import org.chromium.content_public.browser.BrowserContextHandle;

/* JADX INFO: compiled from: LocationCategory.java */
/* JADX INFO: loaded from: classes2.dex */
public final class x91 extends yn2 {
    public x91(Profile profile) {
        super(9, "android.permission.ACCESS_COARSE_LOCATION", profile);
    }

    @Override // defpackage.yn2
    public final boolean e() {
        ba1.a().getClass();
        return ba1.b();
    }

    @Override // defpackage.yn2
    public final Intent h() {
        if (e()) {
            return null;
        }
        ba1.a().getClass();
        Intent intent = new Intent("android.settings.LOCATION_SOURCE_SETTINGS");
        intent.setFlags(268435456);
        return intent;
    }

    @Override // defpackage.yn2
    public final String i(Context context) {
        Resources resources = context.getResources();
        return d(context) ? resources.getString(R$string.android_location_off_globally) : resources.getString(R$string.android_location_also_off_globally);
    }

    @Override // defpackage.yn2
    public final boolean n(Context context) {
        if (d(context) && e()) {
            return false;
        }
        BrowserContextHandle browserContextHandle = this.a;
        return N.MJSt3Ocq(browserContextHandle, 4) || N.MB23OvTV(browserContextHandle, 4);
    }
}
