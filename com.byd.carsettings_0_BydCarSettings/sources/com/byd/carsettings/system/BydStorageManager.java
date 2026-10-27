package com.byd.carsettings.system;

import android.annotation.SuppressLint;
import android.app.usage.StorageStats;
import android.app.usage.StorageStatsManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.StatFs;
import android.os.UserHandle;
import android.os.storage.DiskInfo;
import android.os.storage.StorageManager;
import android.os.storage.VolumeInfo;
import android.provider.MediaStore;
import android.text.TextUtils;
import androidx.annotation.RequiresApi;
import b.b.a.f.h;
import b.b.g.n.b;
import b.b.m.g.e;
import com.byd.carsettings.system.model.DiskType;
import com.byd.carsettings.system.model.UFlashInfo;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class BydStorageManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String[] f2871a = {Environment.DIRECTORY_DCIM, Environment.DIRECTORY_MOVIES, Environment.DIRECTORY_PICTURES, Environment.DIRECTORY_MUSIC, Environment.DIRECTORY_ALARMS, Environment.DIRECTORY_NOTIFICATIONS, Environment.DIRECTORY_RINGTONES, Environment.DIRECTORY_PODCASTS, Environment.DIRECTORY_DOWNLOADS, "Android"};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static volatile StorageState f2872b = StorageState.NICE;

    public enum StorageState {
        NICE(0),
        WARNING(1),
        EMERGENCY(2),
        FORCED_RELEASE(3);

        public int value;

        StorageState(int i) {
            this.value = i;
        }
    }

    public static class a implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
            double dO = BydStorageManager.o();
            b.b.m.c.a.a("BydStorageManager", "---updateStorageState---usge: " + e.a(2, dO));
            if (dO < 85.0d) {
                StorageState unused = BydStorageManager.f2872b = StorageState.NICE;
            } else if (dO < 95.0d) {
                StorageState unused2 = BydStorageManager.f2872b = StorageState.WARNING;
            } else if (dO < 98.0d) {
                StorageState unused3 = BydStorageManager.f2872b = StorageState.EMERGENCY;
            } else {
                StorageState unused4 = BydStorageManager.f2872b = StorageState.FORCED_RELEASE;
            }
            b.b.m.c.a.a("BydStorageManager", "---updateStorageState---mState: " + BydStorageManager.f2872b);
        }
    }

    public static void c(Context context, b.k kVar) {
        PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> installedPackages = packageManager.getInstalledPackages(0);
        b.b.m.c.a.a("BydStorageManager", "----clearAllAppCache----packages.size = " + installedPackages.size());
        if (kVar != null) {
            kVar.d0(installedPackages.size());
        }
        b.b.m.c.a.a("BydStorageManager", "-----clearAllAppCache-----start clear.");
        Iterator<PackageInfo> it = installedPackages.iterator();
        while (it.hasNext()) {
            packageManager.deleteApplicationCacheFiles(it.next().packageName, kVar);
        }
        b.b.m.c.a.a("BydStorageManager", "------clearAllAppCache-----clear end.");
    }

    public static void d(Context context, String str, b.b.g.p.j.a aVar) {
        if (context == null || TextUtils.isEmpty(str)) {
            return;
        }
        PackageManager packageManager = context.getPackageManager();
        try {
            b.b.m.c.a.a("BydStorageManager", "---clearAppCacheAndData---start clear. pkg:" + str);
            PackageInfo packageInfo = packageManager.getPackageInfo(str, 0);
            packageManager.deleteApplicationCacheFiles(str, aVar);
            packageManager.clearApplicationUserData(packageInfo.packageName, aVar);
        } catch (PackageManager.NameNotFoundException e2) {
            b.b.m.c.a.c("BydStorageManager", "---clearAppCacheAndData---e: " + e2);
        }
        b.b.m.c.a.a("BydStorageManager", "---clearAppCacheAndData---clear end.");
    }

    public static void e(Context context) {
        f(new File("/data/tempLogs"));
        if ("mounted".equals(Environment.getExternalStorageState())) {
            String path = Environment.getExternalStorageDirectory().getPath();
            b.b.m.c.a.a("BydStorageManager", "---clearDataOnLowAvailableSize---sdPath: " + path);
            f(new File(path));
        }
        d(context, "com.byd.logtools", null);
        d(context, "com.byd.vrassistant", null);
        d(context, "com.neusoft.na.navigation", null);
    }

    public static void f(File file) {
        File[] fileArrListFiles;
        if (file == null) {
            return;
        }
        b.b.m.c.a.a("BydStorageManager", "---deleteFolder---file:" + file.getAbsolutePath() + ", isDirectory: " + file.isDirectory());
        if (file.isDirectory() && (fileArrListFiles = file.listFiles()) != null && fileArrListFiles.length > 0) {
            for (File file2 : fileArrListFiles) {
                f(file2);
            }
        }
        file.delete();
    }

    public static long g(Context context) {
        b.b.m.c.a.a("BydStorageManager", ", InstalledApp, context = " + context);
        long j = 0;
        if (context == null) {
            return 0L;
        }
        List<PackageInfo> installedPackages = context.getPackageManager().getInstalledPackages(0);
        b.b.m.c.a.a("BydStorageManager", ", InstalledApp, packages.size = " + installedPackages.size());
        for (PackageInfo packageInfo : installedPackages) {
            b.b.m.c.a.a("BydStorageManager", ", InstalledApp, size = " + packageInfo.packageName);
            long[] jArrH = h(context, packageInfo);
            b.b.m.c.a.a("BydStorageManager", ", InstalledApp, size = " + (jArrH[0] + jArrH[1] + jArrH[2]) + ", pkg = " + packageInfo.packageName);
            j += jArrH[0] + jArrH[2];
        }
        b.b.m.c.a.a("BydStorageManager", ", InstalledApp, size = " + j);
        return j;
    }

    @SuppressLint({"NewApi"})
    public static long[] h(Context context, PackageInfo packageInfo) {
        StorageStatsManager storageStatsManager = (StorageStatsManager) context.getSystemService("storagestats");
        StorageManager storageManager = (StorageManager) context.getSystemService("storage");
        PackageManager packageManager = context.getPackageManager();
        storageManager.getStorageVolumes();
        long[] jArr = new long[3];
        try {
            StorageStats storageStatsQueryStatsForPackage = storageStatsManager.queryStatsForPackage(StorageManager.UUID_DEFAULT, packageInfo.packageName, UserHandle.getUserHandleForUid(packageManager.getApplicationInfo(packageInfo.packageName, 128).uid));
            jArr[0] = storageStatsQueryStatsForPackage.getAppBytes();
            jArr[1] = storageStatsQueryStatsForPackage.getCacheBytes();
            jArr[2] = storageStatsQueryStatsForPackage.getDataBytes();
        } catch (PackageManager.NameNotFoundException | IOException | RuntimeException e2) {
            b.b.m.c.a.c("BydStorageManager", ", e: " + e2.toString());
        }
        return jArr;
    }

    public static int i() {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        long blockSizeLong = statFs.getBlockSizeLong();
        long blockCountLong = (statFs.getBlockCountLong() * blockSizeLong) / 1024;
        long availableBlocksLong = (blockSizeLong * statFs.getAvailableBlocksLong()) / 1024;
        float f2 = (availableBlocksLong * 1.0f) / blockCountLong;
        b.b.m.c.a.d("BydStorageManager", "---getLocalMemoryAvailableSize---totalSize: " + blockCountLong + ", availableSize: " + availableBlocksLong + ", availableRatio: " + f2);
        return (int) (f2 * 100.0f);
    }

    public static long j(Context context, Uri uri, String str) {
        long j = 0;
        if (context == null) {
            return 0L;
        }
        b.b.m.c.a.a("BydStorageManager", ", ###### 0 uri: " + uri);
        Cursor cursorQuery = context.getContentResolver().query(uri, new String[]{str, "_data"}, null, null, null);
        if (cursorQuery != null && cursorQuery.moveToFirst()) {
            do {
                String string = cursorQuery.getString(cursorQuery.getColumnIndex("_data"));
                b.b.m.c.a.a("BydStorageManager", ", ###### 0 data: " + string);
                if (string != null && string.contains("/storage/emulated/0")) {
                    j += cursorQuery.getLong(cursorQuery.getColumnIndex(str));
                }
            } while (cursorQuery.moveToNext());
        }
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        return j;
    }

    @RequiresApi(api = 29)
    public static long k(Context context) {
        long cacheBytes = 0;
        if (context == null) {
            return 0L;
        }
        StorageStatsManager storageStatsManager = (StorageStatsManager) context.getSystemService("storagestats");
        PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> installedPackages = packageManager.getInstalledPackages(0);
        b.b.m.c.a.a("BydStorageManager", ", TotalCache, packages.size = " + installedPackages.size());
        for (PackageInfo packageInfo : installedPackages) {
            try {
                cacheBytes += storageStatsManager.queryStatsForPackage(StorageManager.UUID_DEFAULT, packageInfo.packageName, UserHandle.getUserHandleForUid(packageManager.getApplicationInfo(packageInfo.packageName, 128).uid)).getCacheBytes();
            } catch (PackageManager.NameNotFoundException | IOException | RuntimeException e2) {
                b.b.m.c.a.c("BydStorageManager", ", e: " + e2.toString());
            }
        }
        b.b.m.c.a.a("BydStorageManager", ", TotalCache, size = " + cacheBytes);
        return cacheBytes;
    }

    @RequiresApi(api = 29)
    public static long l(Context context) {
        return j(context, MediaStore.Downloads.INTERNAL_CONTENT_URI, "_size") + j(context, MediaStore.Downloads.EXTERNAL_CONTENT_URI, "_size");
    }

    public static long m(Context context) {
        if (context == null) {
            return 0L;
        }
        long j = j(context, MediaStore.Video.Media.INTERNAL_CONTENT_URI, "_size");
        long j2 = j(context, MediaStore.Video.Media.EXTERNAL_CONTENT_URI, "_size");
        b.b.m.c.a.a("BydStorageManager", ", ###### 0  videoSize: " + j + ", videoSizeExt: " + j2);
        long j3 = j(context, MediaStore.Images.Media.INTERNAL_CONTENT_URI, "_size");
        long j4 = j(context, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "_size");
        b.b.m.c.a.a("BydStorageManager", ", ###### 0  imgSize: " + j3 + ", imgSizeExt: " + j4);
        return j + j2 + j3 + j4;
    }

    public static StatFs n() {
        return new StatFs(Environment.getDataDirectory().getPath());
    }

    public static double o() {
        StatFs statFsN = n();
        long blockCountLong = statFsN.getBlockCountLong();
        b.b.m.c.a.a("BydStorageManager", "---getTotalRomUage---blockTotal: " + blockCountLong + ", blockAvailable: " + statFsN.getAvailableBlocksLong());
        return ((blockCountLong - r3) * 100.0d) / blockCountLong;
    }

    public static List<UFlashInfo> p(Context context) {
        if (context == null) {
            return null;
        }
        List<VolumeInfo> volumes = ((StorageManager) context.getSystemService(StorageManager.class)).getVolumes();
        ArrayList arrayList = new ArrayList();
        for (VolumeInfo volumeInfo : volumes) {
            DiskInfo disk = volumeInfo.getDisk();
            if (disk != null && (disk.isUsb() || disk.isSd())) {
                UFlashInfo uFlashInfo = new UFlashInfo();
                if (volumeInfo.isMountedReadable()) {
                    uFlashInfo.diskState = 0;
                } else {
                    uFlashInfo.diskState = 2;
                }
                uFlashInfo.setVolId(volumeInfo.getId());
                uFlashInfo.setName(disk.label);
                uFlashInfo.setVolumeCount(disk.volumeCount);
                uFlashInfo.setPath(volumeInfo.path);
                uFlashInfo.setType("usbotg".equals(volumeInfo.getDeviceType()) ? DiskType.USB : DiskType.SD);
                uFlashInfo.setDefPrimary(!TextUtils.isEmpty(volumeInfo.path));
                try {
                    StatFs statFs = new StatFs(volumeInfo.path);
                    long blockSizeLong = statFs.getBlockSizeLong();
                    long blockCountLong = statFs.getBlockCountLong() * blockSizeLong;
                    long availableBlocksLong = blockSizeLong * statFs.getAvailableBlocksLong();
                    uFlashInfo.setSize(blockCountLong);
                    uFlashInfo.sizeUsed = blockCountLong - availableBlocksLong;
                } catch (Exception e2) {
                    b.b.m.c.a.a("ExternalStorageAdapter", "getUFlashInfoList：" + e2.getMessage());
                }
                arrayList.add(uFlashInfo);
            }
        }
        b.b.m.c.a.a("BydStorageManager", "---getUFlashInfoList---list: " + arrayList);
        return arrayList;
    }

    public static void q(Context context, String str) {
        if (context == null) {
            return;
        }
        b.b.m.c.a.a("BydStorageManager", "---removeUFlash---volId: " + str);
        ((StorageManager) context.getSystemService(StorageManager.class)).mount(str);
    }

    public static void r(Context context, String str) {
        if (context == null) {
            return;
        }
        b.b.m.c.a.a("BydStorageManager", "---removeUFlash---volId: " + str);
        ((StorageManager) context.getSystemService(StorageManager.class)).unmount(str);
    }

    public static void s() {
        h.a().j(new a());
    }
}
