package com.android.car.developeroptions.fuelgauge;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.BatteryStats;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.format.Formatter;
import android.util.SparseIntArray;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.overlay.FeatureFactory;
import com.android.car.developeroptions.widget.UsageView;
import com.android.internal.os.BatteryStatsHelper;
import com.android.settingslib.Utils;
import com.android.settingslib.utils.PowerUtil;
import com.android.settingslib.utils.StringUtil;

/* JADX INFO: loaded from: classes.dex */
public class BatteryInfo {
    public int batteryLevel;
    public String batteryPercentString;
    public CharSequence chargeLabel;
    private boolean mCharging;
    private BatteryStats mStats;
    public CharSequence remainingLabel;
    public String statusLabel;
    public String suggestionLabel;
    private long timePeriod;
    public boolean discharging = true;
    public long remainingTimeUs = 0;
    public long averageTimeToDischarge = -1;

    public interface BatteryDataParser {
        void onDataGap();

        void onDataPoint(long j, BatteryStats.HistoryItem historyItem);

        void onParsingDone();

        void onParsingStarted(long j, long j2);
    }

    public interface Callback {
        void onBatteryInfoLoaded(BatteryInfo batteryInfo);
    }

    public void bindHistory(final UsageView usageView, BatteryDataParser... batteryDataParserArr) {
        final Context context = usageView.getContext();
        BatteryDataParser batteryDataParser = new BatteryDataParser() { // from class: com.android.car.developeroptions.fuelgauge.BatteryInfo.1
            byte lastLevel;
            long startTime;
            SparseIntArray points = new SparseIntArray();
            int lastTime = -1;

            @Override // com.android.car.developeroptions.fuelgauge.BatteryInfo.BatteryDataParser
            public void onParsingStarted(long j, long j2) {
                this.startTime = j;
                BatteryInfo.this.timePeriod = j2 - j;
                usageView.clearPaths();
                usageView.configureGraph((int) BatteryInfo.this.timePeriod, 100);
            }

            @Override // com.android.car.developeroptions.fuelgauge.BatteryInfo.BatteryDataParser
            public void onDataPoint(long j, BatteryStats.HistoryItem historyItem) {
                int i = (int) j;
                this.lastTime = i;
                byte b = historyItem.batteryLevel;
                this.lastLevel = b;
                this.points.put(i, b);
            }

            @Override // com.android.car.developeroptions.fuelgauge.BatteryInfo.BatteryDataParser
            public void onDataGap() {
                if (this.points.size() > 1) {
                    usageView.addPath(this.points);
                }
                this.points.clear();
            }

            @Override // com.android.car.developeroptions.fuelgauge.BatteryInfo.BatteryDataParser
            public void onParsingDone() {
                onDataGap();
                if (BatteryInfo.this.remainingTimeUs != 0) {
                    PowerUsageFeatureProvider powerUsageFeatureProvider = FeatureFactory.getFactory(context).getPowerUsageFeatureProvider(context);
                    if (!BatteryInfo.this.mCharging && powerUsageFeatureProvider.isEnhancedBatteryPredictionEnabled(context)) {
                        this.points = powerUsageFeatureProvider.getEnhancedBatteryPredictionCurve(context, this.startTime);
                    } else {
                        int i = this.lastTime;
                        if (i >= 0) {
                            this.points.put(i, this.lastLevel);
                            this.points.put((int) (BatteryInfo.this.timePeriod + PowerUtil.convertUsToMs(BatteryInfo.this.remainingTimeUs)), BatteryInfo.this.mCharging ? 100 : 0);
                        }
                    }
                }
                SparseIntArray sparseIntArray = this.points;
                if (sparseIntArray == null || sparseIntArray.size() <= 0) {
                    return;
                }
                usageView.configureGraph(this.points.keyAt(r0.size() - 1), 100);
                usageView.addProjectedPath(this.points);
            }
        };
        BatteryDataParser[] batteryDataParserArr2 = new BatteryDataParser[batteryDataParserArr.length + 1];
        for (int i = 0; i < batteryDataParserArr.length; i++) {
            batteryDataParserArr2[i] = batteryDataParserArr[i];
        }
        batteryDataParserArr2[batteryDataParserArr.length] = batteryDataParser;
        parse(this.mStats, batteryDataParserArr2);
        String string = context.getString(R.string.charge_length_format, Formatter.formatShortElapsedTime(context, this.timePeriod));
        long j = this.remainingTimeUs;
        usageView.setBottomLabels(new CharSequence[]{string, j != 0 ? context.getString(R.string.remaining_length_format, Formatter.formatShortElapsedTime(context, j / 1000)) : ""});
    }

    public static void getBatteryInfo(Context context, Callback callback, boolean z) {
        getBatteryInfo(context, callback, null, z);
    }

    public static void getBatteryInfo(final Context context, final Callback callback, final BatteryStatsHelper batteryStatsHelper, final boolean z) {
        new AsyncTask<Void, Void, BatteryInfo>() { // from class: com.android.car.developeroptions.fuelgauge.BatteryInfo.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public BatteryInfo doInBackground(Void... voidArr) {
                return BatteryInfo.getBatteryInfo(context, batteryStatsHelper, z);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(BatteryInfo batteryInfo) {
                long jCurrentTimeMillis = System.currentTimeMillis();
                callback.onBatteryInfoLoaded(batteryInfo);
                BatteryUtils.logRuntime("BatteryInfo", "time for callback", jCurrentTimeMillis);
            }
        }.execute(new Void[0]);
    }

    public static BatteryInfo getBatteryInfo(Context context, BatteryStatsHelper batteryStatsHelper, boolean z) {
        BatteryStats stats;
        Estimate enhancedBatteryPrediction;
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (batteryStatsHelper == null) {
            BatteryStatsHelper batteryStatsHelper2 = new BatteryStatsHelper(context, true);
            batteryStatsHelper2.create((Bundle) null);
            stats = batteryStatsHelper2.getStats();
        } else {
            stats = batteryStatsHelper.getStats();
        }
        BatteryUtils.logRuntime("BatteryInfo", "time for getStats", jCurrentTimeMillis);
        long jCurrentTimeMillis2 = System.currentTimeMillis();
        PowerUsageFeatureProvider powerUsageFeatureProvider = FeatureFactory.getFactory(context).getPowerUsageFeatureProvider(context);
        long jConvertMsToUs = PowerUtil.convertMsToUs(SystemClock.elapsedRealtime());
        Intent intentRegisterReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        boolean z2 = intentRegisterReceiver.getIntExtra("plugged", -1) == 0;
        if (z2 && powerUsageFeatureProvider != null && powerUsageFeatureProvider.isEnhancedBatteryPredictionEnabled(context) && (enhancedBatteryPrediction = powerUsageFeatureProvider.getEnhancedBatteryPrediction(context)) != null) {
            BatteryUtils.logRuntime("BatteryInfo", "time for enhanced BatteryInfo", jCurrentTimeMillis2);
            return getBatteryInfo(context, intentRegisterReceiver, stats, enhancedBatteryPrediction, jConvertMsToUs, z);
        }
        Estimate estimate = new Estimate(PowerUtil.convertUsToMs(z2 ? stats.computeBatteryTimeRemaining(jConvertMsToUs) : 0L), false, -1L);
        BatteryUtils.logRuntime("BatteryInfo", "time for regular BatteryInfo", jCurrentTimeMillis2);
        return getBatteryInfo(context, intentRegisterReceiver, stats, estimate, jConvertMsToUs, z);
    }

    public static BatteryInfo getBatteryInfoOld(Context context, Intent intent, BatteryStats batteryStats, long j, boolean z) {
        return getBatteryInfo(context, intent, batteryStats, new Estimate(PowerUtil.convertUsToMs(batteryStats.computeBatteryTimeRemaining(j)), false, -1L), j, z);
    }

    public static BatteryInfo getBatteryInfo(Context context, Intent intent, BatteryStats batteryStats, Estimate estimate, long j, boolean z) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        BatteryInfo batteryInfo = new BatteryInfo();
        batteryInfo.mStats = batteryStats;
        int batteryLevel = Utils.getBatteryLevel(intent);
        batteryInfo.batteryLevel = batteryLevel;
        batteryInfo.batteryPercentString = Utils.formatPercentage(batteryLevel);
        batteryInfo.mCharging = intent.getIntExtra("plugged", 0) != 0;
        batteryInfo.averageTimeToDischarge = estimate.averageDischargeTime;
        batteryInfo.statusLabel = Utils.getBatteryStatus(context, intent);
        if (!batteryInfo.mCharging) {
            updateBatteryInfoDischarging(context, z, estimate, batteryInfo);
        } else {
            updateBatteryInfoCharging(context, intent, batteryStats, j, batteryInfo);
        }
        BatteryUtils.logRuntime("BatteryInfo", "time for getBatteryInfo", jCurrentTimeMillis);
        return batteryInfo;
    }

    private static void updateBatteryInfoCharging(Context context, Intent intent, BatteryStats batteryStats, long j, BatteryInfo batteryInfo) {
        Resources resources = context.getResources();
        long jComputeChargeTimeRemaining = batteryStats.computeChargeTimeRemaining(j);
        int intExtra = intent.getIntExtra("status", 1);
        batteryInfo.discharging = false;
        batteryInfo.suggestionLabel = null;
        if (jComputeChargeTimeRemaining > 0 && intExtra != 5) {
            batteryInfo.remainingTimeUs = jComputeChargeTimeRemaining;
            CharSequence elapsedTime = StringUtil.formatElapsedTime(context, PowerUtil.convertUsToMs(jComputeChargeTimeRemaining), false);
            batteryInfo.remainingLabel = context.getString(R.string.power_remaining_charging_duration_only, elapsedTime);
            batteryInfo.chargeLabel = context.getString(R.string.power_charging_duration, batteryInfo.batteryPercentString, elapsedTime);
            return;
        }
        String batteryStatus = Utils.getBatteryStatus(context, intent);
        batteryInfo.remainingLabel = null;
        batteryInfo.chargeLabel = batteryInfo.batteryLevel == 100 ? batteryInfo.batteryPercentString : resources.getString(R.string.power_charging, batteryInfo.batteryPercentString, batteryStatus);
    }

    private static void updateBatteryInfoDischarging(Context context, boolean z, Estimate estimate, BatteryInfo batteryInfo) {
        long jConvertMsToUs = PowerUtil.convertMsToUs(estimate.estimateMillis);
        if (jConvertMsToUs > 0) {
            batteryInfo.remainingTimeUs = jConvertMsToUs;
            batteryInfo.remainingLabel = PowerUtil.getBatteryRemainingStringFormatted(context, PowerUtil.convertUsToMs(jConvertMsToUs), null, estimate.isBasedOnUsage && !z);
            batteryInfo.chargeLabel = PowerUtil.getBatteryRemainingStringFormatted(context, PowerUtil.convertUsToMs(jConvertMsToUs), batteryInfo.batteryPercentString, estimate.isBasedOnUsage && !z);
            batteryInfo.suggestionLabel = PowerUtil.getBatteryTipStringFormatted(context, PowerUtil.convertUsToMs(jConvertMsToUs));
            return;
        }
        batteryInfo.remainingLabel = null;
        batteryInfo.suggestionLabel = null;
        batteryInfo.chargeLabel = batteryInfo.batteryPercentString;
    }

    public static void parse(BatteryStats batteryStats, BatteryDataParser... batteryDataParserArr) {
        long j;
        long j2;
        long j3;
        long j4;
        long j5;
        int i;
        char c;
        long j6;
        long j7 = 0;
        if (batteryStats.startIteratingHistoryLocked()) {
            BatteryStats.HistoryItem historyItem = new BatteryStats.HistoryItem();
            j = 0;
            j2 = 0;
            j3 = 0;
            j4 = 0;
            j5 = 0;
            boolean z = true;
            i = 0;
            int i2 = 0;
            while (batteryStats.getNextHistoryLocked(historyItem)) {
                i2++;
                int i3 = i;
                if (z) {
                    j5 = historyItem.time;
                    z = false;
                }
                byte b = historyItem.cmd;
                if (b == 5 || b == 7) {
                    if (historyItem.currentTime > j3 + 15552000000L || historyItem.time < j5 + 300000) {
                        j = 0;
                    }
                    j3 = historyItem.currentTime;
                    long j8 = historyItem.time;
                    if (j == 0) {
                        j = j3 - (j8 - j5);
                    }
                    j4 = j8;
                }
                if (historyItem.isDeltaData()) {
                    j2 = historyItem.time;
                    i = i2;
                } else {
                    i = i3;
                }
            }
        } else {
            j = 0;
            j2 = 0;
            j3 = 0;
            j4 = 0;
            j5 = 0;
            i = 0;
        }
        batteryStats.finishIteratingHistoryLocked();
        long j9 = (j3 + j2) - j4;
        for (BatteryDataParser batteryDataParser : batteryDataParserArr) {
            batteryDataParser.onParsingStarted(j, j9);
        }
        if (j9 > j && batteryStats.startIteratingHistoryLocked()) {
            BatteryStats.HistoryItem historyItem2 = new BatteryStats.HistoryItem();
            long j10 = 0;
            int i4 = 0;
            while (batteryStats.getNextHistoryLocked(historyItem2) && i4 < i) {
                if (historyItem2.isDeltaData()) {
                    long j11 = historyItem2.time;
                    j10 += j11 - j4;
                    long j12 = j10 - j;
                    long j13 = j12 < j7 ? j7 : j12;
                    for (BatteryDataParser batteryDataParser2 : batteryDataParserArr) {
                        batteryDataParser2.onDataPoint(j13, historyItem2);
                    }
                    j4 = j11;
                    c = 7;
                } else {
                    byte b2 = historyItem2.cmd;
                    c = 7;
                    if (b2 == 5 || b2 == 7) {
                        j6 = historyItem2.currentTime;
                        if (j6 < j) {
                            j6 = (historyItem2.time - j5) + j;
                        }
                        j4 = historyItem2.time;
                    } else {
                        j6 = j10;
                    }
                    byte b3 = historyItem2.cmd;
                    if (b3 != 6 && (b3 != 5 || Math.abs(j10 - j6) > 3600000)) {
                        for (BatteryDataParser batteryDataParser3 : batteryDataParserArr) {
                            batteryDataParser3.onDataGap();
                        }
                    }
                    j10 = j6;
                }
                i4++;
                j7 = 0;
            }
        }
        batteryStats.finishIteratingHistoryLocked();
        for (BatteryDataParser batteryDataParser4 : batteryDataParserArr) {
            batteryDataParser4.onParsingDone();
        }
    }
}
