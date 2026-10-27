package com.android.internal.content;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager;
import android.content.pm.PackageParser;
import android.content.pm.dex.DexMetadataHelper;
import android.os.Environment;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.storage.IStorageManager;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.os.storage.VolumeInfo;
import android.provider.Settings;
import android.util.ArrayMap;
import android.util.Log;
import com.android.internal.R;
import com.android.internal.content.NativeLibraryHelper;
import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.util.Iterator;
import java.util.Objects;
import java.util.UUID;
import libcore.io.IoUtils;

/* JADX INFO: loaded from: classes4.dex */
public class PackageHelper {
    public static final int APP_INSTALL_AUTO = 0;
    public static final int APP_INSTALL_EXTERNAL = 2;
    public static final int APP_INSTALL_INTERNAL = 1;
    public static final int RECOMMEND_FAILED_ALREADY_EXISTS = -4;
    public static final int RECOMMEND_FAILED_INSUFFICIENT_STORAGE = -1;
    public static final int RECOMMEND_FAILED_INVALID_APK = -2;
    public static final int RECOMMEND_FAILED_INVALID_LOCATION = -3;
    public static final int RECOMMEND_FAILED_INVALID_URI = -6;
    public static final int RECOMMEND_FAILED_VERSION_DOWNGRADE = -7;
    public static final int RECOMMEND_FAILED_WRONG_INSTALLED_VERSION = -8;
    public static final int RECOMMEND_INSTALL_EPHEMERAL = 3;
    public static final int RECOMMEND_INSTALL_EXTERNAL = 2;
    public static final int RECOMMEND_INSTALL_INTERNAL = 1;
    public static final int RECOMMEND_MEDIA_UNAVAILABLE = -5;
    private static final String TAG = "PackageHelper";
    private static TestableInterface sDefaultTestableInterface;

    public static abstract class TestableInterface {
        public abstract boolean getAllow3rdPartyOnInternalConfig(Context context);

        public abstract File getDataDirectory();

        public abstract ApplicationInfo getExistingAppInfo(Context context, String str);

        public abstract boolean getForceAllowOnExternalSetting(Context context);

        public abstract StorageManager getStorageManager(Context context);
    }

    public static int translateAllocateFlags(int i) {
        return (i & 32768) != 0 ? 1 : 0;
    }

    public static IStorageManager getStorageManager() throws RemoteException {
        IBinder service = ServiceManager.getService("mount");
        if (service != null) {
            return IStorageManager.Stub.asInterface(service);
        }
        Log.e(TAG, "Can't get storagemanager service");
        throw new RemoteException("Could not contact storagemanager service");
    }

    private static synchronized TestableInterface getDefaultTestableInterface() {
        if (sDefaultTestableInterface == null) {
            sDefaultTestableInterface = new TestableInterface() { // from class: com.android.internal.content.PackageHelper.1
                @Override // com.android.internal.content.PackageHelper.TestableInterface
                public StorageManager getStorageManager(Context context) {
                    return (StorageManager) context.getSystemService(StorageManager.class);
                }

                @Override // com.android.internal.content.PackageHelper.TestableInterface
                public boolean getForceAllowOnExternalSetting(Context context) {
                    return Settings.Global.getInt(context.getContentResolver(), Settings.Global.FORCE_ALLOW_ON_EXTERNAL, 0) != 0;
                }

                @Override // com.android.internal.content.PackageHelper.TestableInterface
                public boolean getAllow3rdPartyOnInternalConfig(Context context) {
                    return context.getResources().getBoolean(R.bool.config_allow3rdPartyAppOnInternal);
                }

                @Override // com.android.internal.content.PackageHelper.TestableInterface
                public ApplicationInfo getExistingAppInfo(Context context, String str) {
                    try {
                        return context.getPackageManager().getApplicationInfo(str, 4194304);
                    } catch (PackageManager.NameNotFoundException unused) {
                        return null;
                    }
                }

                @Override // com.android.internal.content.PackageHelper.TestableInterface
                public File getDataDirectory() {
                    return Environment.getDataDirectory();
                }
            };
        }
        return sDefaultTestableInterface;
    }

    @Deprecated
    public static String resolveInstallVolume(Context context, String str, int i, long j, TestableInterface testableInterface) throws IOException {
        PackageInstaller.SessionParams sessionParams = new PackageInstaller.SessionParams(-1);
        sessionParams.appPackageName = str;
        sessionParams.installLocation = i;
        sessionParams.sizeBytes = j;
        return resolveInstallVolume(context, sessionParams, testableInterface);
    }

    public static String resolveInstallVolume(Context context, PackageInstaller.SessionParams sessionParams) throws IOException {
        return resolveInstallVolume(context, sessionParams.appPackageName, sessionParams.installLocation, sessionParams.sizeBytes, getDefaultTestableInterface());
    }

    private static boolean checkFitOnVolume(StorageManager storageManager, String str, PackageInstaller.SessionParams sessionParams) throws Throwable {
        if (str == null) {
            return false;
        }
        int iTranslateAllocateFlags = translateAllocateFlags(sessionParams.installFlags);
        UUID uuidForPath = storageManager.getUuidForPath(new File(str));
        long allocatableBytes = storageManager.getAllocatableBytes(uuidForPath, iTranslateAllocateFlags | 8);
        if (sessionParams.sizeBytes <= allocatableBytes) {
            return true;
        }
        return sessionParams.sizeBytes <= allocatableBytes + storageManager.getAllocatableBytes(uuidForPath, iTranslateAllocateFlags | 16);
    }

    public static String resolveInstallVolume(Context context, PackageInstaller.SessionParams sessionParams, TestableInterface testableInterface) throws Throwable {
        StorageManager storageManager = testableInterface.getStorageManager(context);
        boolean forceAllowOnExternalSetting = testableInterface.getForceAllowOnExternalSetting(context);
        boolean allow3rdPartyOnInternalConfig = testableInterface.getAllow3rdPartyOnInternalConfig(context);
        ApplicationInfo existingAppInfo = testableInterface.getExistingAppInfo(context, sessionParams.appPackageName);
        ArrayMap arrayMap = new ArrayMap();
        String str = null;
        String str2 = null;
        for (VolumeInfo volumeInfo : storageManager.getVolumes()) {
            if (volumeInfo.type == 1 && volumeInfo.isMountedWritable()) {
                boolean zEquals = VolumeInfo.ID_PRIVATE_INTERNAL.equals(volumeInfo.id);
                if (zEquals) {
                    str2 = volumeInfo.path;
                }
                if (!zEquals || allow3rdPartyOnInternalConfig) {
                    arrayMap.put(volumeInfo.fsUuid, volumeInfo.path);
                }
            }
        }
        if (existingAppInfo != null && existingAppInfo.isSystemApp()) {
            if (checkFitOnVolume(storageManager, str2, sessionParams)) {
                return StorageManager.UUID_PRIVATE_INTERNAL;
            }
            throw new IOException("Not enough space on existing volume " + existingAppInfo.volumeUuid + " for system app " + sessionParams.appPackageName + " upgrade");
        }
        if (!forceAllowOnExternalSetting && sessionParams.installLocation == 1) {
            if (existingAppInfo != null && !Objects.equals(existingAppInfo.volumeUuid, StorageManager.UUID_PRIVATE_INTERNAL)) {
                throw new IOException("Cannot automatically move " + sessionParams.appPackageName + " from " + existingAppInfo.volumeUuid + " to internal storage");
            }
            if (!allow3rdPartyOnInternalConfig) {
                throw new IOException("Not allowed to install non-system apps on internal storage");
            }
            if (checkFitOnVolume(storageManager, str2, sessionParams)) {
                return StorageManager.UUID_PRIVATE_INTERNAL;
            }
            throw new IOException("Requested internal only, but not enough space");
        }
        if (existingAppInfo != null) {
            if (Objects.equals(existingAppInfo.volumeUuid, StorageManager.UUID_PRIVATE_INTERNAL)) {
                str = str2;
            } else if (arrayMap.containsKey(existingAppInfo.volumeUuid)) {
                str = (String) arrayMap.get(existingAppInfo.volumeUuid);
            }
            if (checkFitOnVolume(storageManager, str, sessionParams)) {
                return existingAppInfo.volumeUuid;
            }
            throw new IOException("Not enough space on existing volume " + existingAppInfo.volumeUuid + " for " + sessionParams.appPackageName + " upgrade");
        }
        if (arrayMap.size() == 1) {
            if (checkFitOnVolume(storageManager, (String) arrayMap.valueAt(0), sessionParams)) {
                return (String) arrayMap.keyAt(0);
            }
        } else {
            long j = Long.MIN_VALUE;
            for (String str3 : arrayMap.keySet()) {
                long allocatableBytes = storageManager.getAllocatableBytes(storageManager.getUuidForPath(new File((String) arrayMap.get(str3))), translateAllocateFlags(sessionParams.installFlags));
                if (allocatableBytes >= j) {
                    str = str3;
                    j = allocatableBytes;
                }
            }
            if (j >= sessionParams.sizeBytes) {
                return str;
            }
        }
        throw new IOException("No special requests, but no room on allowed volumes.  allow3rdPartyOnInternal? " + allow3rdPartyOnInternalConfig);
    }

    public static boolean fitsOnInternal(Context context, PackageInstaller.SessionParams sessionParams) throws Throwable {
        StorageManager storageManager = (StorageManager) context.getSystemService(StorageManager.class);
        UUID uuidForPath = storageManager.getUuidForPath(Environment.getDataDirectory());
        int iTranslateAllocateFlags = translateAllocateFlags(sessionParams.installFlags);
        long allocatableBytes = storageManager.getAllocatableBytes(uuidForPath, iTranslateAllocateFlags | 8);
        if (sessionParams.sizeBytes <= allocatableBytes) {
            return true;
        }
        return sessionParams.sizeBytes <= allocatableBytes + storageManager.getAllocatableBytes(uuidForPath, iTranslateAllocateFlags | 16);
    }

    public static boolean fitsOnExternal(Context context, PackageInstaller.SessionParams sessionParams) {
        StorageManager storageManager = (StorageManager) context.getSystemService(StorageManager.class);
        StorageVolume primaryVolume = storageManager.getPrimaryVolume();
        return sessionParams.sizeBytes > 0 && !primaryVolume.isEmulated() && Environment.MEDIA_MOUNTED.equals(primaryVolume.getState()) && sessionParams.sizeBytes <= storageManager.getStorageBytesUntilLow(primaryVolume.getPathFile());
    }

    @Deprecated
    public static int resolveInstallLocation(Context context, String str, int i, long j, int i2) {
        PackageInstaller.SessionParams sessionParams = new PackageInstaller.SessionParams(-1);
        sessionParams.appPackageName = str;
        sessionParams.installLocation = i;
        sessionParams.sizeBytes = j;
        sessionParams.installFlags = i2;
        try {
            return resolveInstallLocation(context, sessionParams);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x002e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int resolveInstallLocation(android.content.Context r7, android.content.pm.PackageInstaller.SessionParams r8) throws java.io.IOException {
        /*
            android.content.pm.PackageManager r0 = r7.getPackageManager()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> Ld
            java.lang.String r1 = r8.appPackageName     // Catch: android.content.pm.PackageManager.NameNotFoundException -> Ld
            r2 = 4194304(0x400000, float:5.877472E-39)
            android.content.pm.ApplicationInfo r0 = r0.getApplicationInfo(r1, r2)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> Ld
            goto Le
        Ld:
            r0 = 0
        Le:
            int r1 = r8.installFlags
            r1 = r1 & 2048(0x800, float:2.87E-42)
            r2 = 2
            r3 = 0
            r4 = 1
            if (r1 == 0) goto L1b
            r1 = r3
            r0 = r4
            r5 = r0
            goto L42
        L1b:
            int r1 = r8.installFlags
            r1 = r1 & 16
            if (r1 == 0) goto L25
        L21:
            r1 = r3
            r5 = r1
            r0 = r4
            goto L42
        L25:
            int r1 = r8.installLocation
            if (r1 != r4) goto L2a
            goto L21
        L2a:
            int r1 = r8.installLocation
            if (r1 != r2) goto L32
        L2e:
            r0 = r2
        L2f:
            r5 = r3
            r1 = r4
            goto L42
        L32:
            int r1 = r8.installLocation
            if (r1 != 0) goto L21
            if (r0 == 0) goto L40
            int r0 = r0.flags
            r1 = 262144(0x40000, float:3.67342E-40)
            r0 = r0 & r1
            if (r0 == 0) goto L40
            goto L2e
        L40:
            r0 = r4
            goto L2f
        L42:
            if (r1 != 0) goto L49
            if (r0 != r4) goto L47
            goto L49
        L47:
            r6 = r3
            goto L4d
        L49:
            boolean r6 = fitsOnInternal(r7, r8)
        L4d:
            if (r1 != 0) goto L51
            if (r0 != r2) goto L55
        L51:
            boolean r3 = fitsOnExternal(r7, r8)
        L55:
            if (r0 != r4) goto L5d
            if (r6 == 0) goto L62
            if (r5 == 0) goto L5c
            r4 = 3
        L5c:
            return r4
        L5d:
            if (r0 != r2) goto L62
            if (r3 == 0) goto L62
            return r2
        L62:
            if (r1 == 0) goto L6a
            if (r6 == 0) goto L67
            return r4
        L67:
            if (r3 == 0) goto L6a
            return r2
        L6a:
            r7 = -1
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.internal.content.PackageHelper.resolveInstallLocation(android.content.Context, android.content.pm.PackageInstaller$SessionParams):int");
    }

    @Deprecated
    public static long calculateInstalledSize(PackageParser.PackageLite packageLite, boolean z, String str) throws IOException {
        return calculateInstalledSize(packageLite, str);
    }

    public static long calculateInstalledSize(PackageParser.PackageLite packageLite, String str) throws IOException {
        return calculateInstalledSize(packageLite, str, (FileDescriptor) null);
    }

    public static long calculateInstalledSize(PackageParser.PackageLite packageLite, String str, FileDescriptor fileDescriptor) throws IOException {
        NativeLibraryHelper.Handle handleCreate;
        NativeLibraryHelper.Handle handle = null;
        try {
            if (fileDescriptor != null) {
                handleCreate = NativeLibraryHelper.Handle.createFd(packageLite, fileDescriptor);
            } else {
                handleCreate = NativeLibraryHelper.Handle.create(packageLite);
            }
            handle = handleCreate;
            return calculateInstalledSize(packageLite, handle, str);
        } finally {
            IoUtils.closeQuietly(handle);
        }
    }

    @Deprecated
    public static long calculateInstalledSize(PackageParser.PackageLite packageLite, boolean z, NativeLibraryHelper.Handle handle, String str) throws IOException {
        return calculateInstalledSize(packageLite, handle, str);
    }

    public static long calculateInstalledSize(PackageParser.PackageLite packageLite, NativeLibraryHelper.Handle handle, String str) throws IOException {
        Iterator<String> it = packageLite.getAllCodePaths().iterator();
        long length = 0;
        while (it.hasNext()) {
            length += new File(it.next()).length();
        }
        return length + DexMetadataHelper.getPackageDexMetadataSize(packageLite) + NativeLibraryHelper.sumNativeBinariesWithOverride(handle, str);
    }

    public static String replaceEnd(String str, String str2, String str3) {
        if (!str.endsWith(str2)) {
            throw new IllegalArgumentException("Expected " + str + " to end with " + str2);
        }
        return str.substring(0, str.length() - str2.length()) + str3;
    }
}
