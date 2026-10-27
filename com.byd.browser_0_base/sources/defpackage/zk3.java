package defpackage;

import J.N;
import java.util.HashMap;
import java.util.Map;
import org.chromium.chrome.browser.profiles.Profile;

/* JADX INFO: compiled from: VariationsFeedbackSource.java */
/* JADX INFO: loaded from: classes.dex */
public final class zk3 implements gm0 {
    public final boolean a;

    public zk3(Profile profile) {
        this.a = profile.h();
    }

    @Override // defpackage.gm0
    public final Map<String, String> c() {
        if (this.a) {
            return null;
        }
        HashMap map = new HashMap();
        map.put("Chrome Variations", N.Mkq2qJev());
        return map;
    }
}
