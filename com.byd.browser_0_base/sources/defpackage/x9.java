package defpackage;

import J.N;
import android.content.Context;
import java.util.HashMap;
import java.util.Map;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: AutoDarkFeedbackSource.java */
/* JADX INFO: loaded from: classes2.dex */
public final class x9 implements gm0 {
    public final HashMap<String, String> a;

    public x9(Profile profile, Context context, GURL gurl) {
        boolean z = true;
        HashMap<String, String> map = new HashMap<>(1);
        this.a = map;
        if (profile.h()) {
            return;
        }
        sk skVar = bq.a;
        if (!N.M09VlOh_("DarkenWebsitesCheckboxInThemesSetting")) {
            map.put("auto_dark_web_content_enabled", "Disabled");
            return;
        }
        if (!N.MJSt3Ocq(profile, 72) || !z7.I(context) || (!gurl.k() && !mq3.a(profile, gurl))) {
            z = false;
        }
        map.put("auto_dark_web_content_enabled", z ? "Enabled" : "Disabled");
    }

    @Override // defpackage.gm0
    public final Map<String, String> c() {
        return this.a;
    }
}
