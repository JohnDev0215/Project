package defpackage;

import java.util.HashSet;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;

/* JADX INFO: compiled from: ChromeVerificationResultStore.java */
/* JADX INFO: loaded from: classes.dex */
public final class ys extends gl3 {
    public static final ys b = new ys();

    @Override // defpackage.gl3
    public final HashSet a() {
        ku2 ku2VarT = ku2.t();
        try {
            HashSet hashSet = new HashSet(SharedPreferencesManager.getInstance().h("verified_digital_asset_links"));
            ku2VarT.close();
            return hashSet;
        } catch (Throwable th) {
            try {
                ku2VarT.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }
}
