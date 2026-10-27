package com.android.car.developeroptions.fuelgauge;

import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.BatteryStats;
import android.os.Bundle;
import android.os.SystemClock;
import android.os.UserManager;
import android.util.Log;
import android.util.SparseLongArray;
import com.android.car.developeroptions.fuelgauge.batterytip.BatteryDatabaseManager;
import com.android.car.developeroptions.overlay.FeatureFactory;
import com.android.internal.os.BatterySipper;
import com.android.internal.os.BatteryStatsHelper;
import com.android.internal.util.ArrayUtils;
import com.android.settingslib.utils.PowerUtil;
import com.android.settingslib.utils.ThreadUtils;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class BatteryUtils {
    private static BatteryUtils sInstance;
    private AppOpsManager mAppOpsManager;
    private Context mContext;
    private PackageManager mPackageManager;
    PowerUsageFeatureProvider mPowerUsageFeatureProvider;

    public double calculateBatteryPercent(double d, double d2, double d3, int i) {
        if (d2 == 0.0d) {
            return 0.0d;
        }
        return (d / (d2 - d3)) * ((double) i);
    }

    public static BatteryUtils getInstance(Context context) {
        BatteryUtils batteryUtils = sInstance;
        if (batteryUtils == null || batteryUtils.isDataCorrupted()) {
            sInstance = new BatteryUtils(context.getApplicationContext());
        }
        return sInstance;
    }

    BatteryUtils(Context context) {
        this.mContext = context;
        this.mPackageManager = context.getPackageManager();
        this.mAppOpsManager = (AppOpsManager) context.getSystemService("appops");
        this.mPowerUsageFeatureProvider = FeatureFactory.getFactory(context).getPowerUsageFeatureProvider(context);
    }

    public long getProcessTimeMs(int i, BatteryStats.Uid uid, int i2) {
        if (uid == null) {
            return 0L;
        }
        if (i == 0) {
            return getScreenUsageTimeMs(uid, i2);
        }
        if (i == 1) {
            return getProcessForegroundTimeMs(uid, i2);
        }
        if (i == 2) {
            return getProcessBackgroundTimeMs(uid, i2);
        }
        if (i != 3) {
            return 0L;
        }
        return getProcessForegroundTimeMs(uid, i2) + getProcessBackgroundTimeMs(uid, i2);
    }

    private long getScreenUsageTimeMs(BatteryStats.Uid uid, int i, long j) {
        int[] iArr = {0};
        Log.v("BatteryUtils", "package: " + this.mPackageManager.getNameForUid(uid.getUid()));
        long j2 = 0;
        for (int i2 = 0; i2 < 1; i2++) {
            int i3 = iArr[i2];
            long processStateTime = uid.getProcessStateTime(i3, j, i);
            Log.v("BatteryUtils", "type: " + i3 + " time(us): " + processStateTime);
            j2 += processStateTime;
        }
        Log.v("BatteryUtils", "foreground time(us): " + j2);
        return PowerUtil.convertUsToMs(Math.min(j2, getForegroundActivityTotalTimeUs(uid, j)));
    }

    private long getScreenUsageTimeMs(BatteryStats.Uid uid, int i) {
        return getScreenUsageTimeMs(uid, i, PowerUtil.convertMsToUs(SystemClock.elapsedRealtime()));
    }

    private long getProcessBackgroundTimeMs(BatteryStats.Uid uid, int i) {
        long processStateTime = uid.getProcessStateTime(3, PowerUtil.convertMsToUs(SystemClock.elapsedRealtime()), i);
        Log.v("BatteryUtils", "package: " + this.mPackageManager.getNameForUid(uid.getUid()));
        Log.v("BatteryUtils", "background time(us): " + processStateTime);
        return PowerUtil.convertUsToMs(processStateTime);
    }

    private long getProcessForegroundTimeMs(BatteryStats.Uid uid, int i) {
        long jConvertMsToUs = PowerUtil.convertMsToUs(SystemClock.elapsedRealtime());
        return getScreenUsageTimeMs(uid, i, jConvertMsToUs) + PowerUtil.convertUsToMs(getForegroundServiceTotalTimeUs(uid, jConvertMsToUs));
    }

    public double removeHiddenBatterySippers(List<BatterySipper> list) {
        double d = 0.0d;
        BatterySipper batterySipper = null;
        for (int size = list.size() - 1; size >= 0; size--) {
            BatterySipper batterySipper2 = list.get(size);
            if (shouldHideSipper(batterySipper2)) {
                list.remove(size);
                BatterySipper.DrainType drainType = batterySipper2.drainType;
                if (drainType != BatterySipper.DrainType.OVERCOUNTED && drainType != BatterySipper.DrainType.SCREEN && drainType != BatterySipper.DrainType.UNACCOUNTED && drainType != BatterySipper.DrainType.BLUETOOTH && drainType != BatterySipper.DrainType.WIFI && drainType != BatterySipper.DrainType.IDLE) {
                    d += batterySipper2.totalPowerMah;
                }
            }
            if (batterySipper2.drainType == BatterySipper.DrainType.SCREEN) {
                batterySipper = batterySipper2;
            }
        }
        smearScreenBatterySipper(list, batterySipper);
        return d;
    }

    void smearScreenBatterySipper(List<BatterySipper> list, BatterySipper batterySipper) {
        SparseLongArray sparseLongArray = new SparseLongArray();
        int size = list.size();
        long j = 0;
        int i = 0;
        long j2 = 0;
        for (int i2 = 0; i2 < size; i2++) {
            BatteryStats.Uid uid = list.get(i2).uidObj;
            if (uid != null) {
                long processTimeMs = getProcessTimeMs(0, uid, 0);
                sparseLongArray.put(uid.getUid(), processTimeMs);
                j2 += processTimeMs;
            }
        }
        if (j2 >= 600000) {
            if (batterySipper == null) {
                Log.e("BatteryUtils", "screen sipper is null even when app screen time is not zero");
                return;
            }
            double d = batterySipper.totalPowerMah;
            int size2 = list.size();
            while (i < size2) {
                list.get(i).totalPowerMah += (sparseLongArray.get(r3.getUid(), j) * d) / j2;
                i++;
                j = 0;
            }
        }
    }

    public boolean shouldHideSipper(BatterySipper batterySipper) {
        BatterySipper.DrainType drainType = batterySipper.drainType;
        return drainType == BatterySipper.DrainType.IDLE || drainType == BatterySipper.DrainType.CELL || drainType == BatterySipper.DrainType.SCREEN || drainType == BatterySipper.DrainType.UNACCOUNTED || drainType == BatterySipper.DrainType.OVERCOUNTED || drainType == BatterySipper.DrainType.BLUETOOTH || drainType == BatterySipper.DrainType.WIFI || batterySipper.totalPowerMah * 3600.0d < 5.0d || this.mPowerUsageFeatureProvider.isTypeService(batterySipper) || this.mPowerUsageFeatureProvider.isTypeSystem(batterySipper);
    }

    public String getPackageName(int i) {
        String[] packagesForUid = this.mPackageManager.getPackagesForUid(i);
        if (ArrayUtils.isEmpty(packagesForUid)) {
            return null;
        }
        return packagesForUid[0];
    }

    public void sortUsageList(List<BatterySipper> list) {
        Collections.sort(list, new Comparator<BatterySipper>(this) { // from class: com.android.car.developeroptions.fuelgauge.BatteryUtils.1
            @Override // java.util.Comparator
            public int compare(BatterySipper batterySipper, BatterySipper batterySipper2) {
                return Double.compare(batterySipper2.totalPowerMah, batterySipper.totalPowerMah);
            }
        });
    }

    public long calculateLastFullChargeTime(BatteryStatsHelper batteryStatsHelper, long j) {
        return j - batteryStatsHelper.getStats().getStartClockTime();
    }

    public long calculateScreenUsageTime(BatteryStatsHelper batteryStatsHelper) {
        BatterySipper batterySipperFindBatterySipperByType = findBatterySipperByType(batteryStatsHelper.getUsageList(), BatterySipper.DrainType.SCREEN);
        if (batterySipperFindBatterySipperByType != null) {
            return batterySipperFindBatterySipperByType.usageTimeMs;
        }
        return 0L;
    }

    public static void logRuntime(String str, String str2, long j) {
        Log.d(str, str2 + ": " + (System.currentTimeMillis() - j) + "ms");
    }

    public int getPackageUid(String str) {
        if (str == null) {
            return -1;
        }
        try {
            return this.mPackageManager.getPackageUid(str, 128);
        } catch (PackageManager.NameNotFoundException unused) {
            return -1;
        }
    }

    public void setForceAppStandby(final int i, final String str, final int i2) {
        if (isPreOApp(str)) {
            this.mAppOpsManager.setMode(63, i, str, i2);
        }
        this.mAppOpsManager.setMode(70, i, str, i2);
        ThreadUtils.postOnBackgroundThread(new Runnable() { // from class: com.android.car.developeroptions.fuelgauge.-$$Lambda$BatteryUtils$ag855ovbg3P-6I9Dy0XO8IzbKD0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$setForceAppStandby$0$BatteryUtils(i2, i, str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$setForceAppStandby$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$setForceAppStandby$0$BatteryUtils(int i, int i2, String str) {
        BatteryDatabaseManager batteryDatabaseManager = BatteryDatabaseManager.getInstance(this.mContext);
        if (i == 1) {
            batteryDatabaseManager.insertAction(0, i2, str, System.currentTimeMillis());
        } else if (i == 0) {
            batteryDatabaseManager.deleteAction(0, i2, str);
        }
    }

    public boolean isForceAppStandbyEnabled(int i, String str) {
        return this.mAppOpsManager.checkOpNoThrow(70, i, str) == 1;
    }

    public boolean clearForceAppStandby(String str) {
        int packageUid = getPackageUid(str);
        if (packageUid == -1 || !isForceAppStandbyEnabled(packageUid, str)) {
            return false;
        }
        setForceAppStandby(packageUid, str, 0);
        return true;
    }

    public void initBatteryStatsHelper(BatteryStatsHelper batteryStatsHelper, Bundle bundle, UserManager userManager) {
        batteryStatsHelper.create(bundle);
        batteryStatsHelper.clearStats();
        batteryStatsHelper.refreshStats(0, userManager.getUserProfiles());
    }

    public BatteryInfo getBatteryInfo(BatteryStatsHelper batteryStatsHelper, String str) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        Estimate enhancedBatteryPrediction = null;
        Intent intentRegisterReceiver = this.mContext.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        long jConvertMsToUs = PowerUtil.convertMsToUs(SystemClock.elapsedRealtime());
        BatteryStats stats = batteryStatsHelper.getStats();
        PowerUsageFeatureProvider powerUsageFeatureProvider = this.mPowerUsageFeatureProvider;
        if (powerUsageFeatureProvider != null && powerUsageFeatureProvider.isEnhancedBatteryPredictionEnabled(this.mContext)) {
            enhancedBatteryPrediction = this.mPowerUsageFeatureProvider.getEnhancedBatteryPrediction(this.mContext);
        }
        Estimate estimate = enhancedBatteryPrediction == null ? new Estimate(PowerUtil.convertUsToMs(stats.computeBatteryTimeRemaining(jConvertMsToUs)), false, -1L) : enhancedBatteryPrediction;
        logRuntime(str, "BatteryInfoLoader post query", jCurrentTimeMillis);
        BatteryInfo batteryInfo = BatteryInfo.getBatteryInfo(this.mContext, intentRegisterReceiver, stats, estimate, jConvertMsToUs, false);
        logRuntime(str, "BatteryInfoLoader.loadInBackground", jCurrentTimeMillis);
        return batteryInfo;
    }

    public BatterySipper findBatterySipperByType(List<BatterySipper> list, BatterySipper.DrainType drainType) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            BatterySipper batterySipper = list.get(i);
            if (batterySipper.drainType == drainType) {
                return batterySipper;
            }
        }
        return null;
    }

    private boolean isDataCorrupted() {
        return this.mPackageManager == null || this.mAppOpsManager == null;
    }

    long getForegroundActivityTotalTimeUs(BatteryStats.Uid uid, long j) {
        BatteryStats.Timer foregroundActivityTimer = uid.getForegroundActivityTimer();
        if (foregroundActivityTimer != null) {
            return foregroundActivityTimer.getTotalTimeLocked(j, 0);
        }
        return 0L;
    }

    long getForegroundServiceTotalTimeUs(BatteryStats.Uid uid, long j) {
        BatteryStats.Timer foregroundServiceTimer = uid.getForegroundServiceTimer();
        if (foregroundServiceTimer != null) {
            return foregroundServiceTimer.getTotalTimeLocked(j, 0);
        }
        return 0L;
    }

    public boolean isPreOApp(String str) {
        try {
            return this.mPackageManager.getApplicationInfo(str, 128).targetSdkVersion < 26;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("BatteryUtils", "Cannot find package: " + str, e);
            return false;
        }
    }
}
