package com.android.internal.telephony;

import android.Manifest;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.UserHandle;
import android.permission.PermissionManager;
import android.provider.SettingsStringUtil;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public final class TelephonyPermissions {
    private static final boolean DBG = false;
    private static final String LOG_TAG = "TelephonyPermissions";
    private static final String PROPERTY_DEVICE_IDENTIFIER_ACCESS_RESTRICTIONS_DISABLED = "device_identifier_access_restrictions_disabled";
    private static final Map<String, Set<String>> sReportedDeviceIDPackages = new HashMap();

    private TelephonyPermissions() {
    }

    public static boolean checkCallingOrSelfReadPhoneState(Context context, int i, String str, String str2, String str3) {
        return checkReadPhoneState(context, i, Binder.getCallingPid(), Binder.getCallingUid(), str, str2, str3);
    }

    public static boolean checkCallingOrSelfReadPhoneStateNoThrow(Context context, int i, String str, String str2, String str3) {
        try {
            return checkCallingOrSelfReadPhoneState(context, i, str, str2, str3);
        } catch (SecurityException unused) {
            return false;
        }
    }

    public static boolean checkReadPhoneState(Context context, int i, int i2, int i3, String str, String str2, String str3) {
        try {
            try {
                context.enforcePermission(Manifest.permission.READ_PRIVILEGED_PHONE_STATE, i2, i3, str3);
                return true;
            } catch (SecurityException unused) {
                context.enforcePermission(Manifest.permission.READ_PHONE_STATE, i2, i3, str3);
                return ((AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE)).noteOp(AppOpsManager.OPSTR_READ_PHONE_STATE, i3, str, str2, (String) null) == 0;
            }
        } catch (SecurityException e) {
            if (SubscriptionManager.isValidSubscriptionId(i)) {
                enforceCarrierPrivilege(context, i, i3, str3);
                return true;
            }
            throw e;
        }
    }

    public static boolean checkCarrierPrivilegeForSubId(Context context, int i) {
        return SubscriptionManager.isValidSubscriptionId(i) && getCarrierPrivilegeStatus(context, i, Binder.getCallingUid()) == 1;
    }

    public static boolean checkReadPhoneStateOnAnyActiveSub(Context context, int i, int i2, String str, String str2, String str3) {
        try {
            try {
                context.enforcePermission(Manifest.permission.READ_PRIVILEGED_PHONE_STATE, i, i2, str3);
                return true;
            } catch (SecurityException unused) {
                context.enforcePermission(Manifest.permission.READ_PHONE_STATE, i, i2, str3);
                return ((AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE)).noteOp(AppOpsManager.OPSTR_READ_PHONE_STATE, i2, str, str2, (String) null) == 0;
            }
        } catch (SecurityException unused2) {
            return checkCarrierPrivilegeForAnySubId(context, i2);
        }
    }

    public static boolean checkCallingOrSelfReadDeviceIdentifiers(Context context, String str, String str2, String str3) {
        return checkCallingOrSelfReadDeviceIdentifiers(context, -1, str, str2, str3);
    }

    public static boolean checkCallingOrSelfReadDeviceIdentifiers(Context context, int i, String str, String str2, String str3) {
        return checkPrivilegedReadPermissionOrCarrierPrivilegePermission(context, i, str, str2, str3, true);
    }

    public static boolean checkCallingOrSelfReadSubscriberIdentifiers(Context context, int i, String str, String str2, String str3) {
        return checkPrivilegedReadPermissionOrCarrierPrivilegePermission(context, i, str, str2, str3, false);
    }

    private static boolean checkPrivilegedReadPermissionOrCarrierPrivilegePermission(Context context, int i, String str, String str2, String str3, boolean z) {
        int callingUid = Binder.getCallingUid();
        int callingPid = Binder.getCallingPid();
        if (checkCarrierPrivilegeForSubId(context, i)) {
            return true;
        }
        if ((z && checkCarrierPrivilegeForAnySubId(context, callingUid)) || ((PermissionManager) context.getSystemService("permission")).checkDeviceIdentifierAccess(str, str3, str2, callingPid, callingUid) == 0) {
            return true;
        }
        return reportAccessDeniedToReadIdentifiers(context, i, callingPid, callingUid, str, str3);
    }

    private static boolean reportAccessDeniedToReadIdentifiers(Context context, int i, int i2, int i3, String str, String str2) {
        ApplicationInfo applicationInfoAsUser;
        Set<String> hashSet;
        try {
            applicationInfoAsUser = context.getPackageManager().getApplicationInfoAsUser(str, 0, UserHandle.getUserHandleForUid(i3));
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(LOG_TAG, "Exception caught obtaining package info for package " + str, e);
            applicationInfoAsUser = null;
        }
        boolean zContainsKey = sReportedDeviceIDPackages.containsKey(str);
        if (!zContainsKey || !sReportedDeviceIDPackages.get(str).contains(str2)) {
            if (!zContainsKey) {
                hashSet = new HashSet<>();
                sReportedDeviceIDPackages.put(str, hashSet);
            } else {
                hashSet = sReportedDeviceIDPackages.get(str);
            }
            hashSet.add(str2);
            TelephonyCommonStatsLog.write(172, str, str2, false, false);
        }
        Log.w(LOG_TAG, "reportAccessDeniedToReadIdentifiers:" + str + SettingsStringUtil.DELIMITER + str2 + SettingsStringUtil.DELIMITER + i);
        if (applicationInfoAsUser != null && applicationInfoAsUser.targetSdkVersion < 29 && (context.checkPermission(Manifest.permission.READ_PHONE_STATE, i2, i3) == 0 || checkCarrierPrivilegeForSubId(context, i))) {
            return false;
        }
        throw new SecurityException(str2 + ": The user " + i3 + " does not meet the requirements to access device identifiers.");
    }

    public static boolean checkReadCallLog(Context context, int i, int i2, int i3, String str, String str2) {
        if (context.checkPermission(Manifest.permission.READ_CALL_LOG, i2, i3) == 0) {
            return ((AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE)).noteOp(AppOpsManager.OPSTR_READ_CALL_LOG, i3, str, str2, (String) null) == 0;
        }
        if (!SubscriptionManager.isValidSubscriptionId(i)) {
            return false;
        }
        enforceCarrierPrivilege(context, i, i3, "readCallLog");
        return true;
    }

    public static boolean checkCallingOrSelfReadPhoneNumber(Context context, int i, String str, String str2, String str3) {
        return checkReadPhoneNumber(context, i, Binder.getCallingPid(), Binder.getCallingUid(), str, str2, str3);
    }

    public static boolean checkReadPhoneNumber(Context context, int i, int i2, int i3, String str, String str2, String str3) {
        boolean z = false;
        try {
            try {
                if (context.getPackageManager().getApplicationInfoAsUser(str, 0, UserHandle.getUserHandleForUid(Binder.getCallingUid())).targetSdkVersion <= 29) {
                    z = true;
                }
            } catch (PackageManager.NameNotFoundException unused) {
            }
        } catch (PackageManager.NameNotFoundException unused2) {
        }
        try {
            if (z) {
                return checkReadPhoneState(context, i, i2, i3, str, str2, str3);
            }
            try {
                context.enforcePermission(Manifest.permission.READ_PRIVILEGED_PHONE_STATE, i2, i3, str3);
                return true;
            } catch (SecurityException unused3) {
                if (SubscriptionManager.isValidSubscriptionId(i)) {
                    enforceCarrierPrivilege(context, i, i3, str3);
                    return true;
                }
            }
        } catch (SecurityException unused4) {
        }
        AppOpsManager appOpsManager = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
        if (appOpsManager.noteOp(AppOpsManager.OPSTR_WRITE_SMS, i3, str, str2, (String) null) == 0) {
            return true;
        }
        try {
            context.enforcePermission(Manifest.permission.READ_SMS, i2, i3, str3);
            if (appOpsManager.noteOp(AppOpsManager.OPSTR_READ_SMS, i3, str, str2, (String) null) == 0) {
                return true;
            }
        } catch (SecurityException unused5) {
        }
        try {
            context.enforcePermission(Manifest.permission.READ_PHONE_NUMBERS, i2, i3, str3);
            if (appOpsManager.noteOp(AppOpsManager.OPSTR_READ_PHONE_NUMBERS, i3, str, str2, (String) null) == 0) {
                return true;
            }
        } catch (SecurityException unused6) {
        }
        throw new SecurityException(str3 + ": Neither user " + i3 + " nor current process has " + Manifest.permission.READ_PHONE_STATE + ", " + Manifest.permission.READ_SMS + ", or " + Manifest.permission.READ_PHONE_NUMBERS);
    }

    public static void enforceCallingOrSelfModifyPermissionOrCarrierPrivilege(Context context, int i, String str) {
        if (context.checkCallingOrSelfPermission(Manifest.permission.MODIFY_PHONE_STATE) == 0) {
            return;
        }
        enforceCallingOrSelfCarrierPrivilege(context, i, str);
    }

    public static void enforeceCallingOrSelfReadPhoneStatePermissionOrCarrierPrivilege(Context context, int i, String str) {
        if (context.checkCallingOrSelfPermission(Manifest.permission.READ_PHONE_STATE) == 0) {
            return;
        }
        enforceCallingOrSelfCarrierPrivilege(context, i, str);
    }

    public static void enforeceCallingOrSelfReadPrivilegedPhoneStatePermissionOrCarrierPrivilege(Context context, int i, String str) {
        if (context.checkCallingOrSelfPermission(Manifest.permission.READ_PRIVILEGED_PHONE_STATE) == 0) {
            return;
        }
        enforceCallingOrSelfCarrierPrivilege(context, i, str);
    }

    public static void enforeceCallingOrSelfReadPrecisePhoneStatePermissionOrCarrierPrivilege(Context context, int i, String str) {
        if (context.checkCallingOrSelfPermission(Manifest.permission.READ_PRIVILEGED_PHONE_STATE) == 0 || context.checkCallingOrSelfPermission(Manifest.permission.READ_PRECISE_PHONE_STATE) == 0) {
            return;
        }
        enforceCallingOrSelfCarrierPrivilege(context, i, str);
    }

    public static void enforceCallingOrSelfCarrierPrivilege(Context context, int i, String str) {
        enforceCarrierPrivilege(context, i, Binder.getCallingUid(), str);
    }

    private static void enforceCarrierPrivilege(Context context, int i, int i2, String str) {
        if (getCarrierPrivilegeStatus(context, i, i2) != 1) {
            throw new SecurityException(str);
        }
    }

    private static boolean checkCarrierPrivilegeForAnySubId(Context context, int i) {
        for (int i2 : ((SubscriptionManager) context.getSystemService(Context.TELEPHONY_SUBSCRIPTION_SERVICE)).getCompleteActiveSubscriptionIdList()) {
            if (getCarrierPrivilegeStatus(context, i2, i) == 1) {
                return true;
            }
        }
        return false;
    }

    private static int getCarrierPrivilegeStatus(Context context, int i, int i2) {
        if (i2 == 1000 || i2 == 1001) {
            return 1;
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return ((TelephonyManager) context.getSystemService("phone")).createForSubscriptionId(i).getCarrierPrivilegeStatus(i2);
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public static void enforceShellOnly(int i, String str) {
        if (i == 2000 || i == 0) {
            return;
        }
        throw new SecurityException(str + ": Only shell user can call it");
    }
}
