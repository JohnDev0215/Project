package com.android.internal.location.gnssmetrics;

import android.app.StatsManager;
import android.content.Context;
import android.location.GnssStatus;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.os.connectivity.GpsBatteryStats;
import android.util.Base64;
import android.util.Log;
import android.util.StatsEvent;
import android.util.TimeUtils;
import com.android.internal.app.IBatteryStats;
import com.android.internal.location.nano.GnssLogsProto;
import com.android.internal.util.ConcurrentUtils;
import com.android.internal.util.FrameworkStatsLog;
import com.bydux.api.common.tools.cmd.BydAdbHelper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class GnssMetrics {
    private static final int DEFAULT_TIME_BETWEEN_FIXES_MILLISECS = 1000;
    private static final int GPS_SIGNAL_QUALITY_GOOD = 1;
    private static final int GPS_SIGNAL_QUALITY_POOR = 0;
    private static final int GPS_SIGNAL_QUALITY_UNKNOWN = -1;
    private static final double HZ_PER_MHZ = 1000000.0d;
    private static final double L5_CARRIER_FREQ_RANGE_HIGH_HZ = 1.189E9d;
    private static final double L5_CARRIER_FREQ_RANGE_LOW_HZ = 1.164E9d;
    public static final int NUM_GPS_SIGNAL_QUALITY_LEVELS = 2;
    private static final String TAG = GnssMetrics.class.getSimpleName();
    private boolean[] mConstellationTypes;
    private GnssPowerMetrics mGnssPowerMetrics;
    private long mL5SvStatusReports;
    private long mL5SvStatusReportsUsedInFix;
    private Statistics mL5TopFourAverageCn0DbmHzReportsStatistics;
    private Statistics mLocationFailureReportsStatistics;
    private Statistics mLocationFailureStatistics;
    private String mLogStartInElapsedRealTime;
    private int mNumL5SvStatus;
    private int mNumL5SvStatusUsedInFix;
    private int mNumSvStatus;
    private int mNumSvStatusUsedInFix;
    private Statistics mPositionAccuracyMeterStatistics;
    private Statistics mPositionAccuracyMetersReportsStatistics;
    private StatsManager mStatsManager;
    private long mSvStatusReports;
    private long mSvStatusReportsUsedInFix;
    private Statistics mTimeToFirstFixMilliSReportsStatistics;
    private Statistics mTimeToFirstFixSecStatistics;
    private Statistics mTopFourAverageCn0DbmHzReportsStatistics;
    private Statistics mTopFourAverageCn0Statistics;
    private Statistics mTopFourAverageCn0StatisticsL5;

    private static boolean isL5Sv(float f) {
        double d = f;
        return d >= L5_CARRIER_FREQ_RANGE_LOW_HZ && d <= L5_CARRIER_FREQ_RANGE_HIGH_HZ;
    }

    public GnssMetrics(Context context, IBatteryStats iBatteryStats) {
        this.mGnssPowerMetrics = new GnssPowerMetrics(iBatteryStats);
        this.mLocationFailureStatistics = new Statistics();
        this.mTimeToFirstFixSecStatistics = new Statistics();
        this.mPositionAccuracyMeterStatistics = new Statistics();
        this.mTopFourAverageCn0Statistics = new Statistics();
        this.mTopFourAverageCn0StatisticsL5 = new Statistics();
        reset();
        this.mLocationFailureReportsStatistics = new Statistics();
        this.mTimeToFirstFixMilliSReportsStatistics = new Statistics();
        this.mPositionAccuracyMetersReportsStatistics = new Statistics();
        this.mTopFourAverageCn0DbmHzReportsStatistics = new Statistics();
        this.mL5TopFourAverageCn0DbmHzReportsStatistics = new Statistics();
        this.mStatsManager = (StatsManager) context.getSystemService(Context.STATS_MANAGER);
        registerGnssStats();
    }

    public void logReceivedLocationStatus(boolean z) {
        if (!z) {
            this.mLocationFailureStatistics.addItem(1.0d);
            this.mLocationFailureReportsStatistics.addItem(1.0d);
        } else {
            this.mLocationFailureStatistics.addItem(0.0d);
            this.mLocationFailureReportsStatistics.addItem(0.0d);
        }
    }

    public void logMissedReports(int i, int i2) {
        int iMax = (i2 / Math.max(1000, i)) - 1;
        if (iMax > 0) {
            for (int i3 = 0; i3 < iMax; i3++) {
                this.mLocationFailureStatistics.addItem(1.0d);
                this.mLocationFailureReportsStatistics.addItem(1.0d);
            }
        }
    }

    public void logTimeToFirstFixMilliSecs(int i) {
        this.mTimeToFirstFixSecStatistics.addItem(i / 1000);
        this.mTimeToFirstFixMilliSReportsStatistics.addItem(i);
    }

    public void logPositionAccuracyMeters(float f) {
        double d = f;
        this.mPositionAccuracyMeterStatistics.addItem(d);
        this.mPositionAccuracyMetersReportsStatistics.addItem(d);
    }

    public void logCn0(float[] fArr, int i, float[] fArr2) {
        logCn0L5(i, fArr, fArr2);
        if (i == 0 || fArr == null || fArr.length == 0 || fArr.length < i) {
            if (i == 0) {
                this.mGnssPowerMetrics.reportSignalQuality(null, 0);
                return;
            }
            return;
        }
        float[] fArrCopyOf = Arrays.copyOf(fArr, i);
        Arrays.sort(fArrCopyOf);
        this.mGnssPowerMetrics.reportSignalQuality(fArrCopyOf, i);
        if (i < 4) {
            return;
        }
        int i2 = i - 4;
        double d = 0.0d;
        if (fArrCopyOf[i2] > 0.0d) {
            while (i2 < i) {
                d += (double) fArrCopyOf[i2];
                i2++;
            }
            double d2 = d / 4.0d;
            this.mTopFourAverageCn0Statistics.addItem(d2);
            this.mTopFourAverageCn0DbmHzReportsStatistics.addItem(d2 * 1000.0d);
        }
    }

    public void logSvStatus(GnssStatus gnssStatus) {
        for (int i = 0; i < gnssStatus.getSatelliteCount(); i++) {
            if (gnssStatus.hasCarrierFrequencyHz(i)) {
                this.mNumSvStatus++;
                this.mSvStatusReports++;
                boolean zIsL5Sv = isL5Sv(gnssStatus.getCarrierFrequencyHz(i));
                if (zIsL5Sv) {
                    this.mNumL5SvStatus++;
                    this.mL5SvStatusReports++;
                }
                if (gnssStatus.usedInFix(i)) {
                    this.mNumSvStatusUsedInFix++;
                    this.mSvStatusReportsUsedInFix++;
                    if (zIsL5Sv) {
                        this.mNumL5SvStatusUsedInFix++;
                        this.mL5SvStatusReportsUsedInFix++;
                    }
                }
            }
        }
    }

    private void logCn0L5(int i, float[] fArr, float[] fArr2) {
        if (i == 0 || fArr == null || fArr.length == 0 || fArr.length < i || fArr2 == null || fArr2.length == 0 || fArr2.length < i) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < i; i2++) {
            if (isL5Sv(fArr2[i2])) {
                arrayList.add(Float.valueOf(fArr[i2]));
            }
        }
        if (arrayList.size() == 0 || arrayList.size() < 4) {
            return;
        }
        int size = arrayList.size();
        Collections.sort(arrayList);
        int i3 = size - 4;
        double dFloatValue = 0.0d;
        if (((Float) arrayList.get(i3)).floatValue() > 0.0d) {
            while (i3 < size) {
                dFloatValue += (double) ((Float) arrayList.get(i3)).floatValue();
                i3++;
            }
            double d = dFloatValue / 4.0d;
            this.mTopFourAverageCn0StatisticsL5.addItem(d);
            this.mL5TopFourAverageCn0DbmHzReportsStatistics.addItem(d * 1000.0d);
        }
    }

    public void logConstellationType(int i) {
        boolean[] zArr = this.mConstellationTypes;
        if (i >= zArr.length) {
            Log.e(TAG, "Constellation type " + i + " is not valid.");
            return;
        }
        zArr[i] = true;
    }

    public String dumpGnssMetricsAsProtoString() {
        GnssLogsProto.GnssLog gnssLog = new GnssLogsProto.GnssLog();
        if (this.mLocationFailureStatistics.getCount() > 0) {
            gnssLog.numLocationReportProcessed = this.mLocationFailureStatistics.getCount();
            gnssLog.percentageLocationFailure = (int) (this.mLocationFailureStatistics.getMean() * 100.0d);
        }
        if (this.mTimeToFirstFixSecStatistics.getCount() > 0) {
            gnssLog.numTimeToFirstFixProcessed = this.mTimeToFirstFixSecStatistics.getCount();
            gnssLog.meanTimeToFirstFixSecs = (int) this.mTimeToFirstFixSecStatistics.getMean();
            gnssLog.standardDeviationTimeToFirstFixSecs = (int) this.mTimeToFirstFixSecStatistics.getStandardDeviation();
        }
        if (this.mPositionAccuracyMeterStatistics.getCount() > 0) {
            gnssLog.numPositionAccuracyProcessed = this.mPositionAccuracyMeterStatistics.getCount();
            gnssLog.meanPositionAccuracyMeters = (int) this.mPositionAccuracyMeterStatistics.getMean();
            gnssLog.standardDeviationPositionAccuracyMeters = (int) this.mPositionAccuracyMeterStatistics.getStandardDeviation();
        }
        if (this.mTopFourAverageCn0Statistics.getCount() > 0) {
            gnssLog.numTopFourAverageCn0Processed = this.mTopFourAverageCn0Statistics.getCount();
            gnssLog.meanTopFourAverageCn0DbHz = this.mTopFourAverageCn0Statistics.getMean();
            gnssLog.standardDeviationTopFourAverageCn0DbHz = this.mTopFourAverageCn0Statistics.getStandardDeviation();
        }
        int i = this.mNumSvStatus;
        if (i > 0) {
            gnssLog.numSvStatusProcessed = i;
        }
        int i2 = this.mNumL5SvStatus;
        if (i2 > 0) {
            gnssLog.numL5SvStatusProcessed = i2;
        }
        int i3 = this.mNumSvStatusUsedInFix;
        if (i3 > 0) {
            gnssLog.numSvStatusUsedInFix = i3;
        }
        int i4 = this.mNumL5SvStatusUsedInFix;
        if (i4 > 0) {
            gnssLog.numL5SvStatusUsedInFix = i4;
        }
        if (this.mTopFourAverageCn0StatisticsL5.getCount() > 0) {
            gnssLog.numL5TopFourAverageCn0Processed = this.mTopFourAverageCn0StatisticsL5.getCount();
            gnssLog.meanL5TopFourAverageCn0DbHz = this.mTopFourAverageCn0StatisticsL5.getMean();
            gnssLog.standardDeviationL5TopFourAverageCn0DbHz = this.mTopFourAverageCn0StatisticsL5.getStandardDeviation();
        }
        gnssLog.powerMetrics = this.mGnssPowerMetrics.buildProto();
        gnssLog.hardwareRevision = SystemProperties.get("ro.boot.revision", "");
        String strEncodeToString = Base64.encodeToString(GnssLogsProto.GnssLog.toByteArray(gnssLog), 0);
        reset();
        return strEncodeToString;
    }

    public String dumpGnssMetricsAsText() {
        StringBuilder sb = new StringBuilder();
        sb.append("GNSS_KPI_START");
        sb.append('\n');
        sb.append("  KPI logging start time: ");
        sb.append(this.mLogStartInElapsedRealTime);
        sb.append(BydAdbHelper.COMMAND_LINE_END);
        sb.append("  KPI logging end time: ");
        TimeUtils.formatDuration(SystemClock.elapsedRealtimeNanos() / TimeUtils.NANOS_PER_MS, sb);
        sb.append(BydAdbHelper.COMMAND_LINE_END);
        sb.append("  Number of location reports: ");
        sb.append(this.mLocationFailureStatistics.getCount());
        sb.append(BydAdbHelper.COMMAND_LINE_END);
        if (this.mLocationFailureStatistics.getCount() > 0) {
            sb.append("  Percentage location failure: ");
            sb.append(this.mLocationFailureStatistics.getMean() * 100.0d);
            sb.append(BydAdbHelper.COMMAND_LINE_END);
        }
        sb.append("  Number of TTFF reports: ");
        sb.append(this.mTimeToFirstFixSecStatistics.getCount());
        sb.append(BydAdbHelper.COMMAND_LINE_END);
        if (this.mTimeToFirstFixSecStatistics.getCount() > 0) {
            sb.append("  TTFF mean (sec): ");
            sb.append(this.mTimeToFirstFixSecStatistics.getMean());
            sb.append(BydAdbHelper.COMMAND_LINE_END);
            sb.append("  TTFF standard deviation (sec): ");
            sb.append(this.mTimeToFirstFixSecStatistics.getStandardDeviation());
            sb.append(BydAdbHelper.COMMAND_LINE_END);
        }
        sb.append("  Number of position accuracy reports: ");
        sb.append(this.mPositionAccuracyMeterStatistics.getCount());
        sb.append(BydAdbHelper.COMMAND_LINE_END);
        if (this.mPositionAccuracyMeterStatistics.getCount() > 0) {
            sb.append("  Position accuracy mean (m): ");
            sb.append(this.mPositionAccuracyMeterStatistics.getMean());
            sb.append(BydAdbHelper.COMMAND_LINE_END);
            sb.append("  Position accuracy standard deviation (m): ");
            sb.append(this.mPositionAccuracyMeterStatistics.getStandardDeviation());
            sb.append(BydAdbHelper.COMMAND_LINE_END);
        }
        sb.append("  Number of CN0 reports: ");
        sb.append(this.mTopFourAverageCn0Statistics.getCount());
        sb.append(BydAdbHelper.COMMAND_LINE_END);
        if (this.mTopFourAverageCn0Statistics.getCount() > 0) {
            sb.append("  Top 4 Avg CN0 mean (dB-Hz): ");
            sb.append(this.mTopFourAverageCn0Statistics.getMean());
            sb.append(BydAdbHelper.COMMAND_LINE_END);
            sb.append("  Top 4 Avg CN0 standard deviation (dB-Hz): ");
            sb.append(this.mTopFourAverageCn0Statistics.getStandardDeviation());
            sb.append(BydAdbHelper.COMMAND_LINE_END);
        }
        sb.append("  Total number of sv status messages processed: ");
        sb.append(this.mNumSvStatus);
        sb.append(BydAdbHelper.COMMAND_LINE_END);
        sb.append("  Total number of L5 sv status messages processed: ");
        sb.append(this.mNumL5SvStatus);
        sb.append(BydAdbHelper.COMMAND_LINE_END);
        sb.append("  Total number of sv status messages processed, where sv is used in fix: ");
        sb.append(this.mNumSvStatusUsedInFix);
        sb.append(BydAdbHelper.COMMAND_LINE_END);
        sb.append("  Total number of L5 sv status messages processed, where sv is used in fix: ");
        sb.append(this.mNumL5SvStatusUsedInFix);
        sb.append(BydAdbHelper.COMMAND_LINE_END);
        sb.append("  Number of L5 CN0 reports: ");
        sb.append(this.mTopFourAverageCn0StatisticsL5.getCount());
        sb.append(BydAdbHelper.COMMAND_LINE_END);
        if (this.mTopFourAverageCn0StatisticsL5.getCount() > 0) {
            sb.append("  L5 Top 4 Avg CN0 mean (dB-Hz): ");
            sb.append(this.mTopFourAverageCn0StatisticsL5.getMean());
            sb.append(BydAdbHelper.COMMAND_LINE_END);
            sb.append("  L5 Top 4 Avg CN0 standard deviation (dB-Hz): ");
            sb.append(this.mTopFourAverageCn0StatisticsL5.getStandardDeviation());
            sb.append(BydAdbHelper.COMMAND_LINE_END);
        }
        sb.append("  Used-in-fix constellation types: ");
        int i = 0;
        while (true) {
            boolean[] zArr = this.mConstellationTypes;
            if (i >= zArr.length) {
                break;
            }
            if (zArr[i]) {
                sb.append(GnssStatus.constellationTypeToString(i));
                sb.append(" ");
            }
            i++;
        }
        sb.append(BydAdbHelper.COMMAND_LINE_END);
        sb.append("GNSS_KPI_END");
        sb.append(BydAdbHelper.COMMAND_LINE_END);
        GpsBatteryStats gpsBatteryStats = this.mGnssPowerMetrics.getGpsBatteryStats();
        if (gpsBatteryStats != null) {
            sb.append("Power Metrics");
            sb.append(BydAdbHelper.COMMAND_LINE_END);
            sb.append("  Time on battery (min): ");
            sb.append(gpsBatteryStats.getLoggingDurationMs() / 60000.0d);
            sb.append(BydAdbHelper.COMMAND_LINE_END);
            long[] timeInGpsSignalQualityLevel = gpsBatteryStats.getTimeInGpsSignalQualityLevel();
            if (timeInGpsSignalQualityLevel != null && timeInGpsSignalQualityLevel.length == 2) {
                sb.append("  Amount of time (while on battery) Top 4 Avg CN0 > 20.0 dB-Hz (min): ");
                sb.append(timeInGpsSignalQualityLevel[1] / 60000.0d);
                sb.append(BydAdbHelper.COMMAND_LINE_END);
                sb.append("  Amount of time (while on battery) Top 4 Avg CN0 <= 20.0 dB-Hz (min): ");
                sb.append(timeInGpsSignalQualityLevel[0] / 60000.0d);
                sb.append(BydAdbHelper.COMMAND_LINE_END);
            }
            sb.append("  Energy consumed while on battery (mAh): ");
            sb.append(gpsBatteryStats.getEnergyConsumedMaMs() / 3600000.0d);
            sb.append(BydAdbHelper.COMMAND_LINE_END);
        }
        sb.append("Hardware Version: ");
        sb.append(SystemProperties.get("ro.boot.revision", ""));
        sb.append(BydAdbHelper.COMMAND_LINE_END);
        return sb.toString();
    }

    private void reset() {
        StringBuilder sb = new StringBuilder();
        TimeUtils.formatDuration(SystemClock.elapsedRealtimeNanos() / TimeUtils.NANOS_PER_MS, sb);
        this.mLogStartInElapsedRealTime = sb.toString();
        this.mLocationFailureStatistics.reset();
        this.mTimeToFirstFixSecStatistics.reset();
        this.mPositionAccuracyMeterStatistics.reset();
        this.mTopFourAverageCn0Statistics.reset();
        resetConstellationTypes();
        this.mTopFourAverageCn0StatisticsL5.reset();
        this.mNumSvStatus = 0;
        this.mNumL5SvStatus = 0;
        this.mNumSvStatusUsedInFix = 0;
        this.mNumL5SvStatusUsedInFix = 0;
    }

    public void resetConstellationTypes() {
        this.mConstellationTypes = new boolean[8];
    }

    private static class Statistics {
        private int mCount;
        private long mLongSum;
        private double mSum;
        private double mSumSquare;

        private Statistics() {
        }

        public synchronized void reset() {
            this.mCount = 0;
            this.mSum = 0.0d;
            this.mSumSquare = 0.0d;
            this.mLongSum = 0L;
        }

        public synchronized void addItem(double d) {
            this.mCount++;
            this.mSum += d;
            this.mSumSquare += d * d;
            this.mLongSum = (long) (this.mLongSum + d);
        }

        public synchronized int getCount() {
            return this.mCount;
        }

        public synchronized double getMean() {
            return this.mSum / ((double) this.mCount);
        }

        public synchronized double getStandardDeviation() {
            double d = this.mSum / ((double) this.mCount);
            double d2 = d * d;
            double d3 = this.mSumSquare / ((double) this.mCount);
            if (d3 <= d2) {
                return 0.0d;
            }
            return Math.sqrt(d3 - d2);
        }

        public synchronized long getLongSum() {
            return this.mLongSum;
        }
    }

    private class GnssPowerMetrics {
        public static final double POOR_TOP_FOUR_AVG_CN0_THRESHOLD_DB_HZ = 20.0d;
        private static final double REPORTING_THRESHOLD_DB_HZ = 1.0d;
        private final IBatteryStats mBatteryStats;
        private double mLastAverageCn0;
        private int mLastSignalLevel;

        private int getSignalLevel(double d) {
            return d > 20.0d ? 1 : 0;
        }

        private GnssPowerMetrics(IBatteryStats iBatteryStats) {
            this.mBatteryStats = iBatteryStats;
            this.mLastAverageCn0 = -100.0d;
            this.mLastSignalLevel = -1;
        }

        public GnssLogsProto.PowerMetrics buildProto() {
            GnssLogsProto.PowerMetrics powerMetrics = new GnssLogsProto.PowerMetrics();
            GpsBatteryStats gpsBatteryStats = GnssMetrics.this.mGnssPowerMetrics.getGpsBatteryStats();
            if (gpsBatteryStats != null) {
                powerMetrics.loggingDurationMs = gpsBatteryStats.getLoggingDurationMs();
                powerMetrics.energyConsumedMah = gpsBatteryStats.getEnergyConsumedMaMs() / 3600000.0d;
                long[] timeInGpsSignalQualityLevel = gpsBatteryStats.getTimeInGpsSignalQualityLevel();
                powerMetrics.timeInSignalQualityLevelMs = new long[timeInGpsSignalQualityLevel.length];
                for (int i = 0; i < timeInGpsSignalQualityLevel.length; i++) {
                    powerMetrics.timeInSignalQualityLevelMs[i] = timeInGpsSignalQualityLevel[i];
                }
            }
            return powerMetrics;
        }

        public GpsBatteryStats getGpsBatteryStats() {
            try {
                return this.mBatteryStats.getGpsBatteryStats();
            } catch (Exception e) {
                Log.w(GnssMetrics.TAG, "Exception", e);
                return null;
            }
        }

        public void reportSignalQuality(float[] fArr, int i) {
            double dMin = 0.0d;
            if (i > 0) {
                for (int iMax = Math.max(0, i - 4); iMax < i; iMax++) {
                    dMin += (double) fArr[iMax];
                }
                dMin /= (double) Math.min(i, 4);
            }
            if (Math.abs(dMin - this.mLastAverageCn0) < REPORTING_THRESHOLD_DB_HZ) {
                return;
            }
            int signalLevel = getSignalLevel(dMin);
            if (signalLevel != this.mLastSignalLevel) {
                FrameworkStatsLog.write(69, signalLevel);
                this.mLastSignalLevel = signalLevel;
            }
            try {
                this.mBatteryStats.noteGpsSignalQuality(signalLevel);
                this.mLastAverageCn0 = dMin;
            } catch (Exception e) {
                Log.w(GnssMetrics.TAG, "Exception", e);
            }
        }
    }

    private void registerGnssStats() {
        this.mStatsManager.setPullAtomCallback(FrameworkStatsLog.GNSS_STATS, (StatsManager.PullAtomMetadata) null, ConcurrentUtils.DIRECT_EXECUTOR, new StatsPullAtomCallbackImpl());
    }

    private class StatsPullAtomCallbackImpl implements StatsManager.StatsPullAtomCallback {
        private StatsPullAtomCallbackImpl() {
        }

        public int onPullAtom(int i, List<StatsEvent> list) {
            if (i != 10074) {
                throw new UnsupportedOperationException("Unknown tagId = " + i);
            }
            list.add(StatsEvent.newBuilder().setAtomId(i).writeLong(GnssMetrics.this.mLocationFailureReportsStatistics.getCount()).writeLong(GnssMetrics.this.mLocationFailureReportsStatistics.getLongSum()).writeLong(GnssMetrics.this.mTimeToFirstFixMilliSReportsStatistics.getCount()).writeLong(GnssMetrics.this.mTimeToFirstFixMilliSReportsStatistics.getLongSum()).writeLong(GnssMetrics.this.mPositionAccuracyMetersReportsStatistics.getCount()).writeLong(GnssMetrics.this.mPositionAccuracyMetersReportsStatistics.getLongSum()).writeLong(GnssMetrics.this.mTopFourAverageCn0DbmHzReportsStatistics.getCount()).writeLong(GnssMetrics.this.mTopFourAverageCn0DbmHzReportsStatistics.getLongSum()).writeLong(GnssMetrics.this.mL5TopFourAverageCn0DbmHzReportsStatistics.getCount()).writeLong(GnssMetrics.this.mL5TopFourAverageCn0DbmHzReportsStatistics.getLongSum()).writeLong(GnssMetrics.this.mSvStatusReports).writeLong(GnssMetrics.this.mSvStatusReportsUsedInFix).writeLong(GnssMetrics.this.mL5SvStatusReports).writeLong(GnssMetrics.this.mL5SvStatusReportsUsedInFix).build());
            return 0;
        }
    }
}
