package com.android.systemui.util;

import android.content.Context;
import android.os.SystemProperties;
import android.provider.Settings;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes.dex */
public class GuideUtils {
    private static String PRIVACY_POLICY_KEY = "sys.byd.privacy.policy";
    private static final String TAG = "GuideUtils";

    public static boolean isPrivacyPolicyFinished(Context context) {
        int i = Settings.System.getInt(context.getContentResolver(), PRIVACY_POLICY_KEY, 0);
        BydLogHelper.logD(TAG, "PrivacyPolicy status:" + i);
        return i == 1;
    }

    public static boolean isProvisionFinished(Context context) {
        int i = Settings.Global.getInt(context.getContentResolver(), "device_provisioned", 0);
        BydLogHelper.logD(TAG, "Provision status:" + i);
        return i == 1;
    }

    public static boolean isSetupFinished(Context context) {
        int i = Settings.Secure.getInt(context.getContentResolver(), "user_setup_complete", 0);
        BydLogHelper.logD(TAG, "Setup status:" + i);
        return i == 1;
    }

    public static boolean isBootCompleted() {
        return "1".equals(SystemProperties.get("sys.boot_completed"));
    }
}
