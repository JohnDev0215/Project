package com.android.car.developeroptions.backup;

import android.app.backup.IBackupManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.UserHandle;
import android.os.UserManager;
import android.util.Log;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/* JADX INFO: loaded from: classes.dex */
public class PrivacySettingsUtils {
    static boolean isAdminUser(Context context) {
        return UserManager.get(context).isAdminUser();
    }

    static boolean isInvisibleKey(Context context, String str) {
        Set<String> invisibleKey = getInvisibleKey(context);
        if (Log.isLoggable("PrivacySettingsUtils", 3)) {
            Log.d("PrivacySettingsUtils", "keysToRemove size=" + invisibleKey.size() + " keysToRemove=" + invisibleKey);
        }
        return invisibleKey.contains(str);
    }

    private static Set<String> getInvisibleKey(Context context) {
        boolean zIsBackupServiceActive;
        try {
            zIsBackupServiceActive = IBackupManager.Stub.asInterface(ServiceManager.getService("backup")).isBackupServiceActive(UserHandle.myUserId());
        } catch (RemoteException unused) {
            Log.w("PrivacySettingsUtils", "Failed querying backup manager service activity status. Assuming it is inactive.");
            zIsBackupServiceActive = false;
        }
        boolean z = context.getPackageManager().resolveContentProvider("com.google.settings", 0) == null;
        TreeSet treeSet = new TreeSet();
        if (z || zIsBackupServiceActive) {
            treeSet.add("backup_inactive");
        }
        if (z || !zIsBackupServiceActive) {
            treeSet.add("backup_data");
            treeSet.add("auto_restore");
            treeSet.add("configure_account");
        }
        return treeSet;
    }

    public static void updatePrivacyBuffer(Context context, PrivacySettingsConfigData privacySettingsConfigData) {
        IBackupManager iBackupManagerAsInterface = IBackupManager.Stub.asInterface(ServiceManager.getService("backup"));
        try {
            privacySettingsConfigData.setBackupEnabled(iBackupManagerAsInterface.isBackupEnabled());
            String currentTransport = iBackupManagerAsInterface.getCurrentTransport();
            privacySettingsConfigData.setConfigIntent(validatedActivityIntent(context, iBackupManagerAsInterface.getConfigurationIntent(currentTransport), "config"));
            privacySettingsConfigData.setConfigSummary(iBackupManagerAsInterface.getDestinationString(currentTransport));
            privacySettingsConfigData.setManageIntent(validatedActivityIntent(context, iBackupManagerAsInterface.getDataManagementIntent(currentTransport), "management"));
            privacySettingsConfigData.setManageLabel(iBackupManagerAsInterface.getDataManagementLabelForUser(UserHandle.myUserId(), currentTransport));
            privacySettingsConfigData.setBackupGray(false);
        } catch (RemoteException unused) {
            privacySettingsConfigData.setBackupGray(true);
        }
    }

    private static Intent validatedActivityIntent(Context context, Intent intent, String str) {
        if (intent == null) {
            return intent;
        }
        List<ResolveInfo> listQueryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 0);
        if (listQueryIntentActivities != null && !listQueryIntentActivities.isEmpty()) {
            return intent;
        }
        Log.e("PrivacySettingsUtils", "Backup " + str + " intent " + ((Object) null) + " fails to resolve; ignoring");
        return null;
    }
}
