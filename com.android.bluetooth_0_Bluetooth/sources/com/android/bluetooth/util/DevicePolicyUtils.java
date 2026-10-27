package com.android.bluetooth.util;

import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.content.pm.UserInfo;
import android.net.Uri;
import android.os.UserHandle;
import android.os.UserManager;
import android.provider.ContactsContract;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class DevicePolicyUtils {
    private static boolean isBluetoothWorkContactSharingDisabled(Context context) {
        DevicePolicyManager dpm = (DevicePolicyManager) context.getSystemService("device_policy");
        UserManager userManager = (UserManager) context.getSystemService("user");
        int myUserId = UserHandle.myUserId();
        List<UserInfo> userInfoList = userManager.getProfiles(myUserId);
        for (UserInfo ui : userInfoList) {
            if (userManager.isManagedProfile(ui.id)) {
                return dpm.getBluetoothContactSharingDisabled(new UserHandle(ui.id));
            }
        }
        return true;
    }

    public static Uri getEnterprisePhoneUri(Context context) {
        return isBluetoothWorkContactSharingDisabled(context) ? ContactsContract.CommonDataKinds.Phone.CONTENT_URI : ContactsContract.CommonDataKinds.Phone.ENTERPRISE_CONTENT_URI;
    }
}
