package defpackage;

import android.app.usage.StorageStats;
import android.app.usage.StorageStatsManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.bydauto_test.TestVehicleHealthManagerImplCallback;
import android.os.Process;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Log;
import java.io.IOException;
import java.util.UUID;

/* JADX INFO: compiled from: PackageMetrics.java */
/* JADX INFO: loaded from: classes2.dex */
public final class xt1 {

    /* JADX INFO: compiled from: PackageMetrics.java */
    public static class a {
        public long a;
        public long b;
        public long c;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v4, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r6v8, types: [java.util.UUID] */
    public static void a() {
        UUID uuidFromString;
        Context context = xz.a;
        StorageManager storageManager = (StorageManager) context.getSystemService(StorageManager.class);
        StorageStatsManager storageStatsManager = (StorageStatsManager) context.getSystemService(StorageStatsManager.class);
        a aVar = null;
        if (storageManager == null || storageStatsManager == null) {
            Log.e("cr_PackageMetrics", "StorageManager or StorageStatsManager is not found");
        } else {
            String packageName = context.getPackageName();
            a aVar2 = new a();
            for (StorageVolume storageVolume : storageManager.getStorageVolumes()) {
                if ("mounted".equals(storageVolume.getState())) {
                    String uuid = storageVolume.getUuid();
                    if (uuid == 0) {
                        try {
                            uuid = StorageManager.UUID_DEFAULT;
                            uuidFromString = uuid;
                        } catch (IllegalArgumentException e) {
                            Log.e("cr_PackageMetrics", "Could not parse UUID " + uuid, e);
                            uuidFromString = 0;
                        }
                    } else {
                        uuidFromString = UUID.fromString(uuid);
                    }
                    if (uuidFromString != 0) {
                        try {
                            StorageStats storageStatsQueryStatsForPackage = storageStatsManager.queryStatsForPackage(uuidFromString, packageName, Process.myUserHandle());
                            aVar2.a += storageStatsQueryStatsForPackage.getAppBytes();
                            aVar2.b = (storageStatsQueryStatsForPackage.getDataBytes() - storageStatsQueryStatsForPackage.getCacheBytes()) + aVar2.b;
                            aVar2.c += storageStatsQueryStatsForPackage.getCacheBytes();
                        } catch (PackageManager.NameNotFoundException | IOException | SecurityException e2) {
                            Log.e("cr_PackageMetrics", "Error calling into queryStatsForPackage", e2);
                        }
                    }
                }
            }
            aVar = aVar2;
        }
        if (aVar != null) {
            oj2.S((int) (aVar.b / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED), 1, TestVehicleHealthManagerImplCallback.SET_FRONT_WIPER_CHECK_STATE, 50, "Android.PackageStats.DataSize");
            oj2.S((int) (aVar.c / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED), 1, TestVehicleHealthManagerImplCallback.SET_FRONT_WIPER_CHECK_STATE, 50, "Android.PackageStats.CacheSize");
            oj2.a0((int) (aVar.a / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED), "Android.PackageStats.CodeSize");
        }
    }
}
