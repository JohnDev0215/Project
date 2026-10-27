package com.android.providers.media.util;

import android.app.AppOpsManager;
import android.content.Context;
import android.os.Process;

/* JADX INFO: loaded from: classes.dex */
public class PermissionUtils {
    public static final String OPSTR_NO_ISOLATED_STORAGE = "android:no_isolated_storage";
    private static ThreadLocal<String> sOpDescription = new ThreadLocal<>();

    public static boolean checkPermissionShell(Context context, int i, int i2) {
        return i2 == 0 || i2 == 2000;
    }

    public static void setOpDescription(String str) {
        sOpDescription.set(str);
    }

    public static void clearOpDescription() {
        sOpDescription.set(null);
    }

    public static boolean checkPermissionSelf(Context context, int i, int i2) {
        return Process.myUid() == i2;
    }

    public static boolean checkPermissionManager(Context context, int i, int i2, String str, String str2) {
        if (checkPermissionForDataDelivery(context, "android.permission.MANAGE_EXTERNAL_STORAGE", i, i2, str, str2, generateAppOpMessage(str, sOpDescription.get()))) {
            return true;
        }
        return checkNoIsolatedStorageGranted(context, i2, str, str2);
    }

    public static boolean checkPermissionDelegator(Context context, int i, int i2) {
        return context.checkPermission("android.permission.BACKUP", i, i2) == 0 || context.checkPermission("android.permission.UPDATE_DEVICE_STATS", i, i2) == 0;
    }

    public static boolean checkPermissionWriteStorage(Context context, int i, int i2, String str, String str2) {
        return checkPermissionForDataDelivery(context, "android.permission.WRITE_EXTERNAL_STORAGE", i, i2, str, str2, generateAppOpMessage(str, sOpDescription.get()));
    }

    public static boolean checkPermissionReadStorage(Context context, int i, int i2, String str, String str2) {
        return checkPermissionForDataDelivery(context, "android.permission.READ_EXTERNAL_STORAGE", i, i2, str, str2, generateAppOpMessage(str, sOpDescription.get()));
    }

    public static boolean checkIsLegacyStorageGranted(Context context, int i, String str) {
        return ((AppOpsManager) context.getSystemService(AppOpsManager.class)).unsafeCheckOp("android:legacy_storage", i, str) == 0;
    }

    public static boolean checkPermissionReadAudio(Context context, int i, int i2, String str, String str2) {
        if (checkPermissionForPreflight(context, "android.permission.READ_EXTERNAL_STORAGE", i, i2, str)) {
            return checkAppOpAllowingLegacy(context, "android:read_media_audio", i, i2, str, str2, generateAppOpMessage(str, sOpDescription.get()));
        }
        return false;
    }

    public static boolean checkPermissionWriteAudio(Context context, int i, int i2, String str, String str2) {
        if (checkPermissionAllowingNonLegacy(context, "android.permission.WRITE_EXTERNAL_STORAGE", i, i2, str)) {
            return checkAppOpAllowingLegacy(context, "android:write_media_audio", i, i2, str, str2, generateAppOpMessage(str, sOpDescription.get()));
        }
        return false;
    }

    public static boolean checkPermissionReadVideo(Context context, int i, int i2, String str, String str2) {
        if (checkPermissionForPreflight(context, "android.permission.READ_EXTERNAL_STORAGE", i, i2, str)) {
            return checkAppOpAllowingLegacy(context, "android:read_media_video", i, i2, str, str2, generateAppOpMessage(str, sOpDescription.get()));
        }
        return false;
    }

    public static boolean checkPermissionWriteVideo(Context context, int i, int i2, String str, String str2) {
        if (checkPermissionAllowingNonLegacy(context, "android.permission.WRITE_EXTERNAL_STORAGE", i, i2, str)) {
            return checkAppOpAllowingLegacy(context, "android:write_media_video", i, i2, str, str2, generateAppOpMessage(str, sOpDescription.get()));
        }
        return false;
    }

    public static boolean checkPermissionReadImages(Context context, int i, int i2, String str, String str2) {
        if (checkPermissionForPreflight(context, "android.permission.READ_EXTERNAL_STORAGE", i, i2, str)) {
            return checkAppOpAllowingLegacy(context, "android:read_media_images", i, i2, str, str2, generateAppOpMessage(str, sOpDescription.get()));
        }
        return false;
    }

    public static boolean checkPermissionWriteImages(Context context, int i, int i2, String str, String str2) {
        if (checkPermissionAllowingNonLegacy(context, "android.permission.WRITE_EXTERNAL_STORAGE", i, i2, str)) {
            return checkAppOpAllowingLegacy(context, "android:write_media_images", i, i2, str, str2, generateAppOpMessage(str, sOpDescription.get()));
        }
        return false;
    }

    public static boolean checkWriteImagesOrVideoAppOps(Context context, int i, String str, String str2) {
        return checkAppOp(context, "android:write_media_images", i, str, str2, generateAppOpMessage(str, sOpDescription.get())) || checkAppOp(context, "android:write_media_video", i, str, str2, generateAppOpMessage(str, sOpDescription.get()));
    }

    static boolean checkNoIsolatedStorageGranted(Context context, int i, String str, String str2) {
        return ((AppOpsManager) context.getSystemService(AppOpsManager.class)).noteOpNoThrow(OPSTR_NO_ISOLATED_STORAGE, i, str, str2, generateAppOpMessage(str, "am instrument --no-isolated-storage")) == 0;
    }

    private static String generateAppOpMessage(String str, String str2) {
        if (str2 == null) {
            return null;
        }
        return "Package: " + str + ". Description: " + str2 + ".";
    }

    private static boolean checkPermissionAllowingNonLegacy(Context context, String str, int i, int i2, String str2) {
        if (((AppOpsManager) context.getSystemService(AppOpsManager.class)).unsafeCheckOpNoThrow("android:legacy_storage", i2, str2) != 0) {
            return true;
        }
        return checkPermissionForPreflight(context, str, i, i2, str2);
    }

    private static boolean checkAppOp(Context context, String str, int i, String str2, String str3, String str4) {
        int iNoteOpNoThrow = ((AppOpsManager) context.getSystemService(AppOpsManager.class)).noteOpNoThrow(str, i, str2, str3, str4);
        if (iNoteOpNoThrow == 0) {
            return true;
        }
        if (iNoteOpNoThrow == 1 || iNoteOpNoThrow == 2 || iNoteOpNoThrow == 3) {
            return false;
        }
        throw new IllegalStateException(str + " has unknown mode " + iNoteOpNoThrow);
    }

    private static boolean checkAppOpAllowingLegacy(Context context, String str, int i, int i2, String str2, String str3, String str4) {
        AppOpsManager appOpsManager = (AppOpsManager) context.getSystemService(AppOpsManager.class);
        int iNoteOpNoThrow = appOpsManager.noteOpNoThrow(str, i2, str2, str3, str4);
        if (iNoteOpNoThrow == 0) {
            return true;
        }
        if (iNoteOpNoThrow == 1 || iNoteOpNoThrow == 2 || iNoteOpNoThrow == 3) {
            return appOpsManager.unsafeCheckOpNoThrow("android:legacy_storage", i2, str2) == 0;
        }
        throw new IllegalStateException(str + " has unknown mode " + iNoteOpNoThrow);
    }

    private static boolean checkPermissionForPreflight(Context context, String str, int i, int i2, String str2) {
        return checkPermissionCommon(context, str, i, i2, str2, null, null, false);
    }

    private static boolean checkPermissionForDataDelivery(Context context, String str, int i, int i2, String str2, String str3, String str4) {
        return checkPermissionCommon(context, str, i, i2, str2, str3, str4, true);
    }

    private static boolean checkPermissionCommon(Context context, String str, int i, int i2, String str2, String str3, String str4, boolean z) {
        String[] packagesForUid;
        String str5 = (str2 != null || (packagesForUid = context.getPackageManager().getPackagesForUid(i2)) == null || packagesForUid.length <= 0) ? str2 : packagesForUid[0];
        if (isAppOpPermission(str)) {
            return checkAppOpPermission(context, str, i, i2, str5, str3, str4, z);
        }
        if (isRuntimePermission(str)) {
            return checkRuntimePermission(context, str, i, i2, str5, str3, str4, z);
        }
        return context.checkPermission(str, i, i2) == 0;
    }

    private static boolean isAppOpPermission(String str) {
        return ((str.hashCode() == -1813079487 && str.equals("android.permission.MANAGE_EXTERNAL_STORAGE")) ? (byte) 0 : (byte) -1) == 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static boolean isRuntimePermission(java.lang.String r4) {
        /*
            int r0 = r4.hashCode()
            r1 = -406040016(0xffffffffe7cc5230, float:-1.9297577E24)
            r2 = 0
            r3 = 1
            if (r0 == r1) goto L1b
            r1 = 1365911975(0x516a29a7, float:6.2857572E10)
            if (r0 == r1) goto L11
            goto L25
        L11:
            java.lang.String r0 = "android.permission.WRITE_EXTERNAL_STORAGE"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L25
            r4 = r3
            goto L26
        L1b:
            java.lang.String r0 = "android.permission.READ_EXTERNAL_STORAGE"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L25
            r4 = r2
            goto L26
        L25:
            r4 = -1
        L26:
            if (r4 == 0) goto L2b
            if (r4 == r3) goto L2b
            return r2
        L2b:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.media.util.PermissionUtils.isRuntimePermission(java.lang.String):boolean");
    }

    private static boolean checkAppOpPermission(Context context, String str, int i, int i2, String str2, String str3, String str4, boolean z) {
        int iUnsafeCheckOpRawNoThrow;
        String strPermissionToOp = AppOpsManager.permissionToOp(str);
        if (strPermissionToOp == null || str2 == null) {
            return false;
        }
        AppOpsManager appOpsManager = (AppOpsManager) context.getSystemService(AppOpsManager.class);
        if (z) {
            iUnsafeCheckOpRawNoThrow = appOpsManager.noteOpNoThrow(strPermissionToOp, i2, str2, str3, str4);
        } else {
            iUnsafeCheckOpRawNoThrow = appOpsManager.unsafeCheckOpRawNoThrow(strPermissionToOp, i2, str2);
        }
        if (iUnsafeCheckOpRawNoThrow != 0) {
            if (iUnsafeCheckOpRawNoThrow == 3) {
                return context.checkPermission(str, i, i2) == 0;
            }
            if (iUnsafeCheckOpRawNoThrow != 4) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkRuntimePermission(Context context, String str, int i, int i2, String str2, String str3, String str4, boolean z) {
        int iUnsafeCheckOpRawNoThrow;
        if (context.checkPermission(str, i, i2) == -1) {
            return false;
        }
        String strPermissionToOp = AppOpsManager.permissionToOp(str);
        if (strPermissionToOp != null && str2 != null) {
            AppOpsManager appOpsManager = (AppOpsManager) context.getSystemService(AppOpsManager.class);
            if (z) {
                iUnsafeCheckOpRawNoThrow = appOpsManager.noteOpNoThrow(strPermissionToOp, i2, str2, str3, str4);
            } else {
                iUnsafeCheckOpRawNoThrow = appOpsManager.unsafeCheckOpRawNoThrow(strPermissionToOp, i2, str2);
            }
            if (iUnsafeCheckOpRawNoThrow != 0 && iUnsafeCheckOpRawNoThrow != 4) {
                return false;
            }
        }
        return true;
    }
}
