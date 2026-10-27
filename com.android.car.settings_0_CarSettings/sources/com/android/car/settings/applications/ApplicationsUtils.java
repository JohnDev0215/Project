package com.android.car.settings.applications;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.UserInfo;
import android.telecom.DefaultDialerManager;
import android.text.TextUtils;
import android.util.ArraySet;
import com.android.car.settings.users.UserHelper;
import com.android.internal.telephony.SmsApplication;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
public class ApplicationsUtils {
    private ApplicationsUtils() {
    }

    public static boolean isKeepEnabledPackage(Context context, String packageName) {
        Set<String> keepEnabledPackages = new ArraySet<>();
        String defaultDialer = DefaultDialerManager.getDefaultDialerApplication(context);
        if (!TextUtils.isEmpty(defaultDialer)) {
            keepEnabledPackages.add(defaultDialer);
        }
        ComponentName defaultSms = SmsApplication.getDefaultSmsApplication(context, true);
        if (defaultSms != null) {
            keepEnabledPackages.add(defaultSms.getPackageName());
        }
        return keepEnabledPackages.contains(packageName);
    }

    public static boolean isProfileOrDeviceOwner(String packageName, DevicePolicyManager dpm, UserHelper userHelper) {
        if (dpm.isDeviceOwnerAppOnAnyUser(packageName)) {
            return true;
        }
        List<UserInfo> userInfos = userHelper.getAllUsers();
        for (int i = 0; i < userInfos.size(); i++) {
            ComponentName cn = dpm.getProfileOwnerAsUser(userInfos.get(i).id);
            if (cn != null && cn.getPackageName().equals(packageName)) {
                return true;
            }
        }
        return false;
    }
}
