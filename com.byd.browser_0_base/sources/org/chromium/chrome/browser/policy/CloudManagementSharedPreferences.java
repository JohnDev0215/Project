package org.chromium.chrome.browser.policy;

import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;

/* JADX INFO: loaded from: classes2.dex */
public class CloudManagementSharedPreferences {
    @CalledByNative
    public static void deleteDmToken() {
        SharedPreferencesManager.getInstance().removeKey("Chrome.Policy.CloudManagementDMToken");
    }

    @CalledByNative
    public static String readDmToken() {
        return SharedPreferencesManager.getInstance().g("Chrome.Policy.CloudManagementDMToken", "");
    }

    @CalledByNative
    public static void saveDmToken(String str) {
        SharedPreferencesManager.getInstance().o("Chrome.Policy.CloudManagementDMToken", str);
    }
}
