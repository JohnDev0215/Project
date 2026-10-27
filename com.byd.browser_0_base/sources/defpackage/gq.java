package defpackage;

import androidx.preference.Preference;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.settings.R$layout;

/* JADX INFO: compiled from: ChromeManagedPreferenceDelegate.java */
/* JADX INFO: loaded from: classes2.dex */
public interface gq extends mb1 {
    @Override // defpackage.mb1
    default boolean b(Preference preference) {
        return false;
    }

    @Override // defpackage.mb1
    default int d() {
        if (oj2.y()) {
            return R$layout.chrome_managed_preference;
        }
        return 0;
    }

    @Override // defpackage.mb1
    default boolean e() {
        return !oj2.k(Profile.d()).c("profile.managed.second_custodian_name").isEmpty();
    }
}
