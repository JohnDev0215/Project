package org.chromium.chrome.browser.policy;

import defpackage.n3;
import java.util.UUID;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;

/* JADX INFO: loaded from: classes2.dex */
public class CloudManagementAndroidConnection {
    public final n3 a = new n3();

    public static class a {
        public static final CloudManagementAndroidConnection a = new CloudManagementAndroidConnection();
    }

    @CalledByNative
    public static CloudManagementAndroidConnection getInstance() {
        return a.a;
    }

    @CalledByNative
    public String getClientId() {
        String strG = SharedPreferencesManager.getInstance().g("Chrome.Policy.CloudManagementClientId", "");
        if (!strG.isEmpty()) {
            return strG;
        }
        this.a.getClass();
        String string = UUID.randomUUID().toString();
        SharedPreferencesManager.getInstance().o("Chrome.Policy.CloudManagementClientId", string);
        return string;
    }
}
