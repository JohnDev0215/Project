package com.android.internal.telephony;

import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.CarrierAssociatedAppEntry;
import android.os.SystemConfigManager;
import android.os.UserHandle;
import android.telephony.TelephonyManager;
import android.util.ArrayMap;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public final class CarrierAppUtils {
    private static final boolean DEBUG = false;
    private static final String TAG = "CarrierAppUtils";

    static /* synthetic */ void lambda$disableCarrierAppsUntilPrivileged$0(Boolean bool) {
    }

    private CarrierAppUtils() {
    }

    public static synchronized void disableCarrierAppsUntilPrivileged(String str, TelephonyManager telephonyManager, int i, Context context) {
        SystemConfigManager systemConfigManager = (SystemConfigManager) context.getSystemService(SystemConfigManager.class);
        disableCarrierAppsUntilPrivileged(str, telephonyManager, getContentResolverForUser(context, i), i, systemConfigManager.getDisabledUntilUsedPreinstalledCarrierApps(), systemConfigManager.getDisabledUntilUsedPreinstalledCarrierAssociatedAppEntries(), context);
    }

    public static synchronized void disableCarrierAppsUntilPrivileged(String str, int i, Context context) {
        SystemConfigManager systemConfigManager = (SystemConfigManager) context.getSystemService(SystemConfigManager.class);
        disableCarrierAppsUntilPrivileged(str, null, getContentResolverForUser(context, i), i, systemConfigManager.getDisabledUntilUsedPreinstalledCarrierApps(), systemConfigManager.getDisabledUntilUsedPreinstalledCarrierAssociatedAppEntries(), context);
    }

    private static ContentResolver getContentResolverForUser(Context context, int i) {
        return context.createContextAsUser(UserHandle.getUserHandleForUid(i), 0).getContentResolver();
    }

    private static boolean isUpdatedSystemApp(ApplicationInfo applicationInfo) {
        return (applicationInfo.flags & 128) != 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0079 A[Catch: NameNotFoundException -> 0x029d, TryCatch #0 {NameNotFoundException -> 0x029d, blocks: (B:15:0x0047, B:16:0x004b, B:18:0x0051, B:20:0x005f, B:25:0x006d, B:27:0x0079, B:28:0x007d, B:30:0x0083, B:31:0x009a, B:34:0x00b7, B:41:0x00ce, B:43:0x0109, B:44:0x010d, B:46:0x0113, B:55:0x0143, B:56:0x018a, B:39:0x00c8, B:57:0x0192, B:61:0x01a2, B:63:0x01a8, B:65:0x01d2, B:66:0x01d6, B:68:0x01dc, B:71:0x01e6, B:73:0x01eb, B:75:0x01f1, B:82:0x01ff, B:89:0x0225, B:97:0x0280, B:99:0x0286, B:96:0x0277), top: B:104:0x0047 }] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00b7 A[Catch: NameNotFoundException -> 0x029d, TRY_ENTER, TRY_LEAVE, TryCatch #0 {NameNotFoundException -> 0x029d, blocks: (B:15:0x0047, B:16:0x004b, B:18:0x0051, B:20:0x005f, B:25:0x006d, B:27:0x0079, B:28:0x007d, B:30:0x0083, B:31:0x009a, B:34:0x00b7, B:41:0x00ce, B:43:0x0109, B:44:0x010d, B:46:0x0113, B:55:0x0143, B:56:0x018a, B:39:0x00c8, B:57:0x0192, B:61:0x01a2, B:63:0x01a8, B:65:0x01d2, B:66:0x01d6, B:68:0x01dc, B:71:0x01e6, B:73:0x01eb, B:75:0x01f1, B:82:0x01ff, B:89:0x0225, B:97:0x0280, B:99:0x0286, B:96:0x0277), top: B:104:0x0047 }] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0192 A[Catch: NameNotFoundException -> 0x029d, TRY_LEAVE, TryCatch #0 {NameNotFoundException -> 0x029d, blocks: (B:15:0x0047, B:16:0x004b, B:18:0x0051, B:20:0x005f, B:25:0x006d, B:27:0x0079, B:28:0x007d, B:30:0x0083, B:31:0x009a, B:34:0x00b7, B:41:0x00ce, B:43:0x0109, B:44:0x010d, B:46:0x0113, B:55:0x0143, B:56:0x018a, B:39:0x00c8, B:57:0x0192, B:61:0x01a2, B:63:0x01a8, B:65:0x01d2, B:66:0x01d6, B:68:0x01dc, B:71:0x01e6, B:73:0x01eb, B:75:0x01f1, B:82:0x01ff, B:89:0x0225, B:97:0x0280, B:99:0x0286, B:96:0x0277), top: B:104:0x0047 }] */
    /* JADX WARN: Removed duplicated region for block: B:84:0x021c  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x021e  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0256  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void disableCarrierAppsUntilPrivileged(java.lang.String r23, android.telephony.TelephonyManager r24, android.content.ContentResolver r25, int r26, java.util.Set<java.lang.String> r27, java.util.Map<java.lang.String, java.util.List<android.os.CarrierAssociatedAppEntry>> r28, android.content.Context r29) {
        /*
            Method dump skipped, instruction units count: 676
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.internal.telephony.CarrierAppUtils.disableCarrierAppsUntilPrivileged(java.lang.String, android.telephony.TelephonyManager, android.content.ContentResolver, int, java.util.Set, java.util.Map, android.content.Context):void");
    }

    public static List<ApplicationInfo> getDefaultCarrierApps(TelephonyManager telephonyManager, int i, Context context) {
        List<ApplicationInfo> defaultCarrierAppCandidates = getDefaultCarrierAppCandidates(i, context);
        if (defaultCarrierAppCandidates == null || defaultCarrierAppCandidates.isEmpty()) {
            return null;
        }
        for (int size = defaultCarrierAppCandidates.size() - 1; size >= 0; size--) {
            if (!(telephonyManager.checkCarrierPrivilegesForPackageAnyPhone(defaultCarrierAppCandidates.get(size).packageName) == 1)) {
                defaultCarrierAppCandidates.remove(size);
            }
        }
        return defaultCarrierAppCandidates;
    }

    public static List<ApplicationInfo> getDefaultCarrierAppCandidates(int i, Context context) {
        return getDefaultCarrierAppCandidatesHelper(i, ((SystemConfigManager) context.getSystemService(SystemConfigManager.class)).getDisabledUntilUsedPreinstalledCarrierApps(), context);
    }

    private static List<ApplicationInfo> getDefaultCarrierAppCandidatesHelper(int i, Set<String> set, Context context) {
        if (set == null || set.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList(set.size());
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            ApplicationInfo applicationInfoIfSystemApp = getApplicationInfoIfSystemApp(i, it.next(), context);
            if (applicationInfoIfSystemApp != null) {
                arrayList.add(applicationInfoIfSystemApp);
            }
        }
        return arrayList;
    }

    private static Map<String, List<AssociatedAppInfo>> getDefaultCarrierAssociatedAppsHelper(int i, Map<String, List<CarrierAssociatedAppEntry>> map, Context context) {
        ArrayMap arrayMap = new ArrayMap(map.size());
        for (Map.Entry<String, List<CarrierAssociatedAppEntry>> entry : map.entrySet()) {
            String key = entry.getKey();
            List<CarrierAssociatedAppEntry> value = entry.getValue();
            for (int i2 = 0; i2 < value.size(); i2++) {
                CarrierAssociatedAppEntry carrierAssociatedAppEntry = value.get(i2);
                ApplicationInfo applicationInfoIfSystemApp = getApplicationInfoIfSystemApp(i, carrierAssociatedAppEntry.packageName, context);
                if (applicationInfoIfSystemApp != null && !isUpdatedSystemApp(applicationInfoIfSystemApp)) {
                    List arrayList = (List) arrayMap.get(key);
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                        arrayMap.put(key, arrayList);
                    }
                    arrayList.add(new AssociatedAppInfo(applicationInfoIfSystemApp, carrierAssociatedAppEntry.addedInSdk));
                }
            }
        }
        return arrayMap;
    }

    private static ApplicationInfo getApplicationInfoIfSystemApp(int i, String str, Context context) {
        try {
            ApplicationInfo applicationInfo = context.createContextAsUser(UserHandle.of(i), 0).getPackageManager().getApplicationInfo(str, 537952256);
            if (applicationInfo != null) {
                return applicationInfo;
            }
            return null;
        } catch (PackageManager.NameNotFoundException e) {
            Log.w(TAG, "Could not reach PackageManager", e);
            return null;
        }
    }

    private static final class AssociatedAppInfo {
        public final int addedInSdk;
        public final ApplicationInfo appInfo;

        AssociatedAppInfo(ApplicationInfo applicationInfo, int i) {
            this.appInfo = applicationInfo;
            this.addedInSdk = i;
        }
    }
}
