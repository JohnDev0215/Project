package defpackage;

import java.util.HashMap;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.profiles.ProfileManager;

/* JADX INFO: compiled from: CommerceSubscriptionsServiceFactory.java */
/* JADX INFO: loaded from: classes2.dex */
public final class lv {
    public static final HashMap a = new HashMap();
    public static ProfileManager.a b;

    public lv() {
        if (b == null) {
            a aVar = new a();
            b = aVar;
            ProfileManager.a(aVar);
        }
    }

    /* JADX INFO: compiled from: CommerceSubscriptionsServiceFactory.java */
    public class a implements ProfileManager.a {
        @Override // org.chromium.chrome.browser.profiles.ProfileManager.a
        public final void e(Profile profile) {
            HashMap map = lv.a;
            kv kvVar = (kv) map.get(profile);
            if (kvVar != null) {
                kvVar.a();
                map.remove(profile);
            }
            if (map.isEmpty()) {
                ProfileManager.b(lv.b);
                lv.b = null;
            }
        }

        @Override // org.chromium.chrome.browser.profiles.ProfileManager.a
        public final void b(Profile profile) {
        }
    }
}
