package com.android.providers.media;

import android.app.AppOpsManager;
import android.app.compat.CompatChanges;
import android.content.ContentProvider;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.Process;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.os.UserManager;
import android.util.ArrayMap;
import com.android.providers.media.util.LongArray;
import com.android.providers.media.util.PermissionUtils;

/* JADX INFO: loaded from: classes.dex */
public class LocalCallingIdentity {
    private static final long DEFAULT_SCOPED_STORAGE = 149924527;
    private static final long FORCE_ENABLE_SCOPED_STORAGE = 132649864;
    public static final int PERMISSION_IS_DELEGATOR = 8;
    public static final int PERMISSION_IS_LEGACY_GRANTED = 512;
    public static final int PERMISSION_IS_LEGACY_READ = 1024;
    public static final int PERMISSION_IS_LEGACY_WRITE = 2048;
    public static final int PERMISSION_IS_MANAGER = 4;
    public static final int PERMISSION_IS_REDACTION_NEEDED = 256;
    public static final int PERMISSION_IS_SELF = 1;
    public static final int PERMISSION_IS_SHELL = 2;
    public static final int PERMISSION_IS_SYSTEM_GALLERY = 4194304;
    public static final int PERMISSION_READ_AUDIO = 65536;
    public static final int PERMISSION_READ_IMAGES = 262144;
    public static final int PERMISSION_READ_VIDEO = 131072;
    public static final int PERMISSION_WRITE_AUDIO = 524288;
    public static final int PERMISSION_WRITE_IMAGES = 2097152;
    public static final int PERMISSION_WRITE_VIDEO = 1048576;
    private static final long UNKNOWN_ROW_ID = -1;
    public String attributionTag;
    public final Context context;
    private int hasPermission;
    private int hasPermissionResolved;
    private String packageName;
    private boolean packageNameResolved;
    public final String packageNameUnchecked;
    public final int pid;
    private String[] sharedPackageNames;
    private boolean sharedPackageNamesResolved;
    private int targetSdkVersion;
    private boolean targetSdkVersionResolved;
    public final int uid;
    private LongArray ownedIds = new LongArray();
    private ArrayMap<String, Long> rowIdOfDeletedPaths = new ArrayMap<>();

    private boolean isScopedStorageDisabled(boolean z, boolean z2) {
        return (z || z2) ? false : true;
    }

    private boolean isScopedStorageEnforced(boolean z, boolean z2) {
        return z && z2;
    }

    private LocalCallingIdentity(Context context, int i, int i2, String str, String str2) {
        this.context = context;
        this.pid = i;
        this.uid = i2;
        this.packageNameUnchecked = str;
        this.attributionTag = str2;
    }

    public static LocalCallingIdentity fromBinder(Context context, ContentProvider contentProvider) {
        String callingPackageUnchecked = contentProvider.getCallingPackageUnchecked();
        if (callingPackageUnchecked == null) {
            callingPackageUnchecked = context.getOpPackageName();
        }
        String str = callingPackageUnchecked;
        String callingAttributionTag = contentProvider.getCallingAttributionTag();
        if (callingAttributionTag == null) {
            callingAttributionTag = context.getAttributionTag();
        }
        return new LocalCallingIdentity(context, Binder.getCallingPid(), Binder.getCallingUid(), str, callingAttributionTag);
    }

    public static LocalCallingIdentity fromExternal(Context context, int i) {
        String[] packagesForUid = context.getPackageManager().getPackagesForUid(i);
        if (packagesForUid == null || packagesForUid.length == 0) {
            throw new IllegalArgumentException("UID " + i + " has no associated package");
        }
        LocalCallingIdentity localCallingIdentityFromExternal = fromExternal(context, i, packagesForUid[0], null);
        localCallingIdentityFromExternal.sharedPackageNames = packagesForUid;
        localCallingIdentityFromExternal.sharedPackageNamesResolved = true;
        if (i == 2000 && SystemProperties.getBoolean("persist.sys.fuse.shell.redaction-needed", false)) {
            localCallingIdentityFromExternal.hasPermission |= PERMISSION_IS_REDACTION_NEEDED;
            localCallingIdentityFromExternal.hasPermissionResolved = PERMISSION_IS_REDACTION_NEEDED;
        }
        return localCallingIdentityFromExternal;
    }

    public static LocalCallingIdentity fromExternal(Context context, int i, String str, String str2) {
        return new LocalCallingIdentity(context, -1, i, str, str2);
    }

    public static LocalCallingIdentity fromSelf(Context context) {
        LocalCallingIdentity localCallingIdentity = new LocalCallingIdentity(context, Process.myPid(), Process.myUid(), context.getOpPackageName(), context.getAttributionTag());
        localCallingIdentity.packageName = localCallingIdentity.packageNameUnchecked;
        localCallingIdentity.packageNameResolved = true;
        localCallingIdentity.targetSdkVersion = 10000;
        localCallingIdentity.targetSdkVersionResolved = true;
        localCallingIdentity.hasPermission = -3851;
        localCallingIdentity.hasPermissionResolved = -1;
        return localCallingIdentity;
    }

    public String getPackageName() {
        if (!this.packageNameResolved) {
            this.packageName = getPackageNameInternal();
            this.packageNameResolved = true;
        }
        return this.packageName;
    }

    private String getPackageNameInternal() {
        ((AppOpsManager) this.context.getSystemService(AppOpsManager.class)).checkPackage(this.uid, this.packageNameUnchecked);
        return this.packageNameUnchecked;
    }

    public String[] getSharedPackageNames() {
        if (!this.sharedPackageNamesResolved) {
            this.sharedPackageNames = getSharedPackageNamesInternal();
            this.sharedPackageNamesResolved = true;
        }
        return this.sharedPackageNames;
    }

    private String[] getSharedPackageNamesInternal() {
        String[] packagesForUid = this.context.getPackageManager().getPackagesForUid(this.uid);
        return packagesForUid != null ? packagesForUid : new String[0];
    }

    public int getTargetSdkVersion() {
        if (!this.targetSdkVersionResolved) {
            this.targetSdkVersion = getTargetSdkVersionInternal();
            this.targetSdkVersionResolved = true;
        }
        return this.targetSdkVersion;
    }

    private int getTargetSdkVersionInternal() {
        try {
            ApplicationInfo applicationInfo = this.context.getPackageManager().getApplicationInfo(getPackageName(), 0);
            if (applicationInfo != null) {
                return applicationInfo.targetSdkVersion;
            }
            return 10000;
        } catch (PackageManager.NameNotFoundException unused) {
            return 10000;
        }
    }

    public boolean hasPermission(int i) {
        if ((this.hasPermissionResolved & i) == 0) {
            if (hasPermissionInternal(i)) {
                this.hasPermission |= i;
            }
            this.hasPermissionResolved |= i;
        }
        return (this.hasPermission & i) != 0;
    }

    private boolean hasPermissionInternal(int i) {
        if (this.uid == 2000 && ((UserManager) this.context.getSystemService(UserManager.class)).hasUserRestriction("no_usb_file_transfer")) {
            throw new SecurityException("Shell user cannot access files for user " + UserHandle.myUserId());
        }
        if (i == 1) {
            return PermissionUtils.checkPermissionSelf(this.context, this.pid, this.uid);
        }
        if (i == 2) {
            return PermissionUtils.checkPermissionShell(this.context, this.pid, this.uid);
        }
        switch (i) {
            case 4:
                return PermissionUtils.checkPermissionManager(this.context, this.pid, this.uid, getPackageName(), this.attributionTag);
            case 8:
                return PermissionUtils.checkPermissionDelegator(this.context, this.pid, this.uid);
            case PERMISSION_IS_REDACTION_NEEDED /* 256 */:
                return isRedactionNeededInternal();
            case PERMISSION_IS_LEGACY_GRANTED /* 512 */:
                return isLegacyStorageGranted();
            case PERMISSION_IS_LEGACY_READ /* 1024 */:
                return isLegacyReadInternal();
            case PERMISSION_IS_LEGACY_WRITE /* 2048 */:
                return isLegacyWriteInternal();
            case PERMISSION_READ_AUDIO /* 65536 */:
                return PermissionUtils.checkPermissionReadAudio(this.context, this.pid, this.uid, getPackageName(), this.attributionTag);
            case PERMISSION_READ_VIDEO /* 131072 */:
                return PermissionUtils.checkPermissionReadVideo(this.context, this.pid, this.uid, getPackageName(), this.attributionTag);
            case PERMISSION_READ_IMAGES /* 262144 */:
                return PermissionUtils.checkPermissionReadImages(this.context, this.pid, this.uid, getPackageName(), this.attributionTag);
            case PERMISSION_WRITE_AUDIO /* 524288 */:
                return PermissionUtils.checkPermissionWriteAudio(this.context, this.pid, this.uid, getPackageName(), this.attributionTag);
            case PERMISSION_WRITE_VIDEO /* 1048576 */:
                return PermissionUtils.checkPermissionWriteVideo(this.context, this.pid, this.uid, getPackageName(), this.attributionTag);
            case PERMISSION_WRITE_IMAGES /* 2097152 */:
                return PermissionUtils.checkPermissionWriteImages(this.context, this.pid, this.uid, getPackageName(), this.attributionTag);
            case PERMISSION_IS_SYSTEM_GALLERY /* 4194304 */:
                return PermissionUtils.checkWriteImagesOrVideoAppOps(this.context, this.uid, getPackageName(), this.attributionTag);
            default:
                return false;
        }
    }

    private boolean isLegacyStorageGranted() {
        boolean zIsChangeEnabled = CompatChanges.isChangeEnabled(DEFAULT_SCOPED_STORAGE, getPackageName(), UserHandle.getUserHandleForUid(this.uid));
        boolean zIsChangeEnabled2 = CompatChanges.isChangeEnabled(FORCE_ENABLE_SCOPED_STORAGE, getPackageName(), UserHandle.getUserHandleForUid(this.uid));
        if (isScopedStorageEnforced(zIsChangeEnabled, zIsChangeEnabled2)) {
            return false;
        }
        if (isScopedStorageDisabled(zIsChangeEnabled, zIsChangeEnabled2)) {
            return true;
        }
        return PermissionUtils.checkIsLegacyStorageGranted(this.context, this.uid, getPackageName());
    }

    private boolean isLegacyWriteInternal() {
        return hasPermission(PERMISSION_IS_LEGACY_GRANTED) && PermissionUtils.checkPermissionWriteStorage(this.context, this.pid, this.uid, getPackageName(), this.attributionTag);
    }

    private boolean isLegacyReadInternal() {
        return hasPermission(PERMISSION_IS_LEGACY_GRANTED) && PermissionUtils.checkPermissionReadStorage(this.context, this.pid, this.uid, getPackageName(), this.attributionTag);
    }

    private boolean isRedactionNeededInternal() {
        if (hasPermission(1) || hasPermission(2)) {
            return false;
        }
        return this.context.checkPermission("android.permission.ACCESS_MEDIA_LOCATION", this.pid, this.uid) == -1 || ((AppOpsManager) this.context.getSystemService(AppOpsManager.class)).noteProxyOpNoThrow(AppOpsManager.permissionToOp("android.permission.ACCESS_MEDIA_LOCATION"), getPackageName(), this.uid, this.attributionTag, null) != 0;
    }

    public boolean isOwned(long j) {
        return this.ownedIds.indexOf(j) != -1;
    }

    public void setOwned(long j, boolean z) {
        int iIndexOf = this.ownedIds.indexOf(j);
        if (z) {
            if (iIndexOf == -1) {
                this.ownedIds.add(j);
            }
        } else if (iIndexOf != -1) {
            this.ownedIds.remove(iIndexOf);
        }
    }

    public void addDeletedRowId(String str, long j) {
        this.rowIdOfDeletedPaths.put(str, Long.valueOf(j));
    }

    public boolean removeDeletedRowId(long j) {
        int iIndexOfValue = this.rowIdOfDeletedPaths.indexOfValue(Long.valueOf(j));
        boolean z = iIndexOfValue > -1;
        while (iIndexOfValue > -1) {
            this.rowIdOfDeletedPaths.removeAt(iIndexOfValue);
            iIndexOfValue = this.rowIdOfDeletedPaths.indexOfValue(Long.valueOf(j));
        }
        return z;
    }

    public long getDeletedRowId(String str) {
        return this.rowIdOfDeletedPaths.getOrDefault(str, Long.valueOf(UNKNOWN_ROW_ID)).longValue();
    }
}
