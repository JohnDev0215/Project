package io.branch.referral;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;

/* JADX INFO: loaded from: classes7.dex */
public class BranchUtil {
    private static boolean isCustomDebugEnabled_ = false;
    private static Boolean isManifestTestModeEnabled = null;
    private static boolean isTestModeEnabled_ = false;

    static boolean checkTestMode(Context context) {
        if (!isTestModeEnabled_ && isManifestTestModeEnabled == null) {
            try {
                ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
                Bundle bundle = applicationInfo.metaData;
                if (bundle != null && bundle.containsKey("io.branch.sdk.TestMode")) {
                    isTestModeEnabled_ = applicationInfo.metaData.getBoolean("io.branch.sdk.TestMode", false);
                } else {
                    Resources resources = context.getResources();
                    isTestModeEnabled_ = Boolean.parseBoolean(resources.getString(resources.getIdentifier("io.branch.sdk.TestMode", "string", context.getPackageName())));
                }
            } catch (Exception unused) {
            }
            isManifestTestModeEnabled = Boolean.valueOf(isTestModeEnabled_);
        }
        return isTestModeEnabled_;
    }

    public static String readBranchKey(Context context) {
        String str = isTestModeEnabled() ? "io.branch.sdk.BranchKey.test" : "io.branch.sdk.BranchKey";
        String string = null;
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            Bundle bundle = applicationInfo.metaData;
            if (bundle != null && (string = bundle.getString(str)) == null && isTestModeEnabled()) {
                string = applicationInfo.metaData.getString("io.branch.sdk.BranchKey");
            }
        } catch (Exception unused) {
        }
        if (!TextUtils.isEmpty(string)) {
            return string;
        }
        try {
            Resources resources = context.getResources();
            return resources.getString(resources.getIdentifier(str, "string", context.getPackageName()));
        } catch (Exception unused2) {
            return string;
        }
    }

    public static boolean isTestModeEnabled() {
        return isTestModeEnabled_;
    }

    static void setTestMode(boolean z) {
        isTestModeEnabled_ = z;
    }

    public static boolean isDebugEnabled() {
        return isCustomDebugEnabled_ || isTestModeEnabled();
    }
}
