package android.os;

import android.app.ActivityManager;
import android.app.backup.FullBackup;
import android.app.blob.XmlTags;
import android.app.job.JobParameters;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.hardware.gnss.visibility_control.V1_0.IGnssVisibilityControlCallback;
import android.location.LocationManager;
import android.media.AudioSystem;
import android.net.TrafficStats;
import android.provider.SettingsStringUtil;
import android.security.Credentials;
import android.telephony.CellSignalStrength;
import android.telephony.TelephonyManager;
import android.text.format.DateFormat;
import android.util.ArrayMap;
import android.util.LongSparseArray;
import android.util.MutableBoolean;
import android.util.Pair;
import android.util.Printer;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.util.TimeUtils;
import android.util.proto.ProtoOutputStream;
import android.view.SurfaceControl;
import androidx.exifinterface.media.ExifInterface;
import com.android.internal.content.NativeLibraryHelper;
import com.android.internal.os.BatterySipper;
import com.android.internal.os.BatteryStatsHelper;
import com.bydux.api.common.tools.cmd.BydAdbHelper;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BatteryStats implements Parcelable {
    private static final String AGGREGATED_WAKELOCK_DATA = "awl";
    public static final int AGGREGATED_WAKE_TYPE_PARTIAL = 20;
    private static final String APK_DATA = "apk";
    private static final String AUDIO_DATA = "aud";
    public static final int AUDIO_TURNED_ON = 15;
    private static final String BATTERY_DATA = "bt";
    private static final String BATTERY_DISCHARGE_DATA = "dc";
    private static final String BATTERY_LEVEL_DATA = "lv";
    private static final int BATTERY_STATS_CHECKIN_VERSION = 9;
    private static final String BLUETOOTH_CONTROLLER_DATA = "ble";
    private static final String BLUETOOTH_MISC_DATA = "blem";
    public static final int BLUETOOTH_SCAN_ON = 19;
    public static final int BLUETOOTH_UNOPTIMIZED_SCAN_ON = 21;
    private static final long BYTES_PER_GB = 1073741824;
    private static final long BYTES_PER_KB = 1024;
    private static final long BYTES_PER_MB = 1048576;
    private static final String CAMERA_DATA = "cam";
    public static final int CAMERA_TURNED_ON = 17;
    private static final String CELLULAR_CONTROLLER_NAME = "Cellular";
    private static final String CHARGE_STEP_DATA = "csd";
    private static final String CHARGE_TIME_REMAIN_DATA = "ctr";
    static final int CHECKIN_VERSION = 35;
    private static final String CPU_DATA = "cpu";
    private static final String CPU_TIMES_AT_FREQ_DATA = "ctf";
    private static final String DATA_CONNECTION_COUNT_DATA = "dcc";
    public static final int DATA_CONNECTION_EMERGENCY_SERVICE;
    static final String[] DATA_CONNECTION_NAMES;
    public static final int DATA_CONNECTION_OTHER;
    public static final int DATA_CONNECTION_OUT_OF_SERVICE = 0;
    private static final String DATA_CONNECTION_TIME_DATA = "dct";
    public static final int DEVICE_IDLE_MODE_DEEP = 2;
    public static final int DEVICE_IDLE_MODE_LIGHT = 1;
    public static final int DEVICE_IDLE_MODE_OFF = 0;
    private static final String DISCHARGE_STEP_DATA = "dsd";
    private static final String DISCHARGE_TIME_REMAIN_DATA = "dtr";
    public static final int DUMP_CHARGED_ONLY = 2;
    public static final int DUMP_DAILY_ONLY = 4;
    public static final int DUMP_DEVICE_WIFI_ONLY = 64;
    public static final int DUMP_HISTORY_ONLY = 8;
    public static final int DUMP_INCLUDE_HISTORY = 16;
    public static final int DUMP_VERBOSE = 32;
    private static final String FLASHLIGHT_DATA = "fla";
    public static final int FLASHLIGHT_TURNED_ON = 16;
    public static final int FOREGROUND_ACTIVITY = 10;
    private static final String FOREGROUND_ACTIVITY_DATA = "fg";
    public static final int FOREGROUND_SERVICE = 22;
    private static final String FOREGROUND_SERVICE_DATA = "fgs";
    public static final int FULL_WIFI_LOCK = 5;
    private static final String GLOBAL_BLUETOOTH_CONTROLLER_DATA = "gble";
    private static final String GLOBAL_CPU_FREQ_DATA = "gcf";
    private static final String GLOBAL_MODEM_CONTROLLER_DATA = "gmcd";
    private static final String GLOBAL_NETWORK_DATA = "gn";
    private static final String GLOBAL_WIFI_CONTROLLER_DATA = "gwfcd";
    private static final String GLOBAL_WIFI_DATA = "gwfl";
    private static final String HISTORY_DATA = "h";
    public static final String[] HISTORY_EVENT_CHECKIN_NAMES;
    public static final IntToString[] HISTORY_EVENT_INT_FORMATTERS;
    public static final String[] HISTORY_EVENT_NAMES;
    public static final BitDescription[] HISTORY_STATE2_DESCRIPTIONS;
    public static final BitDescription[] HISTORY_STATE_DESCRIPTIONS;
    private static final String HISTORY_STRING_POOL = "hsp";
    public static final int JOB = 14;
    private static final String JOBS_DEFERRED_DATA = "jbd";
    private static final String JOB_COMPLETION_DATA = "jbc";
    private static final String JOB_DATA = "jb";
    private static final String KERNEL_WAKELOCK_DATA = "kwl";
    private static final boolean LOCAL_LOGV = false;
    public static final int MAX_TRACKED_SCREEN_STATE = 4;
    public static final double MILLISECONDS_IN_HOUR = 3600000.0d;
    private static final String MISC_DATA = "m";
    private static final String MODEM_CONTROLLER_DATA = "mcd";
    public static final int NETWORK_BT_RX_DATA = 4;
    public static final int NETWORK_BT_TX_DATA = 5;
    private static final String NETWORK_DATA = "nt";
    public static final int NETWORK_MOBILE_BG_RX_DATA = 6;
    public static final int NETWORK_MOBILE_BG_TX_DATA = 7;
    public static final int NETWORK_MOBILE_RX_DATA = 0;
    public static final int NETWORK_MOBILE_TX_DATA = 1;
    public static final int NETWORK_WIFI_BG_RX_DATA = 8;
    public static final int NETWORK_WIFI_BG_TX_DATA = 9;
    public static final int NETWORK_WIFI_RX_DATA = 2;
    public static final int NETWORK_WIFI_TX_DATA = 3;
    public static final int NUM_DATA_CONNECTION_TYPES;
    public static final int NUM_NETWORK_ACTIVITY_TYPES = 10;
    public static final int NUM_SCREEN_BRIGHTNESS_BINS = 5;
    public static final int NUM_WIFI_SIGNAL_STRENGTH_BINS = 5;
    private static final String POWER_USE_ITEM_DATA = "pwi";
    private static final String POWER_USE_SUMMARY_DATA = "pws";
    private static final String PROCESS_DATA = "pr";
    public static final int PROCESS_STATE = 12;
    private static final String RESOURCE_POWER_MANAGER_DATA = "rpm";
    public static final String RESULT_RECEIVER_CONTROLLER_KEY = "controller_activity";
    public static final int SCREEN_BRIGHTNESS_BRIGHT = 4;
    public static final int SCREEN_BRIGHTNESS_DARK = 0;
    private static final String SCREEN_BRIGHTNESS_DATA = "br";
    public static final int SCREEN_BRIGHTNESS_DIM = 1;
    public static final int SCREEN_BRIGHTNESS_LIGHT = 3;
    public static final int SCREEN_BRIGHTNESS_MEDIUM = 2;
    protected static final boolean SCREEN_OFF_RPM_STATS_ENABLED = false;
    public static final int SENSOR = 3;
    private static final String SENSOR_DATA = "sr";
    public static final String SERVICE_NAME = "batterystats";
    private static final String SIGNAL_SCANNING_TIME_DATA = "sst";
    private static final String SIGNAL_STRENGTH_COUNT_DATA = "sgc";
    private static final String SIGNAL_STRENGTH_TIME_DATA = "sgt";
    private static final String STATE_TIME_DATA = "st";

    @Deprecated
    public static final int STATS_CURRENT = 1;
    public static final int STATS_SINCE_CHARGED = 0;

    @Deprecated
    public static final int STATS_SINCE_UNPLUGGED = 2;
    public static final long STEP_LEVEL_INITIAL_MODE_MASK = 71776119061217280L;
    public static final int STEP_LEVEL_INITIAL_MODE_SHIFT = 48;
    public static final long STEP_LEVEL_LEVEL_MASK = 280375465082880L;
    public static final int STEP_LEVEL_LEVEL_SHIFT = 40;
    public static final int[] STEP_LEVEL_MODES_OF_INTEREST;
    public static final int STEP_LEVEL_MODE_DEVICE_IDLE = 8;
    public static final String[] STEP_LEVEL_MODE_LABELS;
    public static final int STEP_LEVEL_MODE_POWER_SAVE = 4;
    public static final int STEP_LEVEL_MODE_SCREEN_STATE = 3;
    public static final int[] STEP_LEVEL_MODE_VALUES;
    public static final long STEP_LEVEL_MODIFIED_MODE_MASK = -72057594037927936L;
    public static final int STEP_LEVEL_MODIFIED_MODE_SHIFT = 56;
    public static final long STEP_LEVEL_TIME_MASK = 1099511627775L;
    public static final int SYNC = 13;
    private static final String SYNC_DATA = "sy";
    private static final String TAG = "BatteryStats";
    private static final String UID_DATA = "uid";
    public static final String UID_TIMES_TYPE_ALL = "A";
    private static final String USER_ACTIVITY_DATA = "ua";
    private static final String VERSION_DATA = "vers";
    private static final String VIBRATOR_DATA = "vib";
    public static final int VIBRATOR_ON = 9;
    private static final String VIDEO_DATA = "vid";
    public static final int VIDEO_TURNED_ON = 8;
    private static final String WAKELOCK_DATA = "wl";
    private static final String WAKEUP_ALARM_DATA = "wua";
    private static final String WAKEUP_REASON_DATA = "wr";
    public static final int WAKE_TYPE_DRAW = 18;
    public static final int WAKE_TYPE_FULL = 1;
    public static final int WAKE_TYPE_PARTIAL = 0;
    public static final int WAKE_TYPE_WINDOW = 2;
    public static final int WIFI_AGGREGATE_MULTICAST_ENABLED = 23;
    public static final int WIFI_BATCHED_SCAN = 11;
    private static final String WIFI_CONTROLLER_DATA = "wfcd";
    private static final String WIFI_CONTROLLER_NAME = "WiFi";
    private static final String WIFI_DATA = "wfl";
    private static final String WIFI_MULTICAST_DATA = "wmc";
    public static final int WIFI_MULTICAST_ENABLED = 7;
    private static final String WIFI_MULTICAST_TOTAL_DATA = "wmct";
    public static final int WIFI_RUNNING = 4;
    public static final int WIFI_SCAN = 6;
    private static final String WIFI_SIGNAL_STRENGTH_COUNT_DATA = "wsgc";
    private static final String WIFI_SIGNAL_STRENGTH_TIME_DATA = "wsgt";
    private static final String WIFI_STATE_COUNT_DATA = "wsc";
    static final String[] WIFI_STATE_NAMES;
    private static final String WIFI_STATE_TIME_DATA = "wst";
    private static final String WIFI_SUPPL_STATE_COUNT_DATA = "wssc";
    static final String[] WIFI_SUPPL_STATE_NAMES;
    static final String[] WIFI_SUPPL_STATE_SHORT_NAMES;
    private static final String WIFI_SUPPL_STATE_TIME_DATA = "wsst";
    private static final IntToString sIntToString;
    private static final IntToString sUidToString;
    private final StringBuilder mFormatBuilder = new StringBuilder(32);
    private final Formatter mFormatter = new Formatter(this.mFormatBuilder);
    private static final String[] STAT_NAMES = {XmlTags.TAG_LEASEE, "c", XmlTags.ATTR_UID};
    public static final long[] JOB_FRESHNESS_BUCKETS = {3600000, 7200000, 14400000, 28800000, Long.MAX_VALUE};
    static final String[] SCREEN_BRIGHTNESS_NAMES = {"dark", "dim", "medium", "light", "bright"};
    static final String[] SCREEN_BRIGHTNESS_SHORT_NAMES = {AudioSystem.LEGACY_REMOTE_SUBMIX_ADDRESS, "1", ExifInterface.GPS_MEASUREMENT_2D, ExifInterface.GPS_MEASUREMENT_3D, "4"};

    public static abstract class ControllerActivityCounter {
        public abstract LongCounter getIdleTimeCounter();

        public abstract LongCounter getMonitoredRailChargeConsumedMaMs();

        public abstract LongCounter getPowerCounter();

        public abstract LongCounter getRxTimeCounter();

        public abstract LongCounter getScanTimeCounter();

        public abstract LongCounter getSleepTimeCounter();

        public abstract LongCounter[] getTxTimeCounters();
    }

    public static abstract class Counter {
        public abstract int getCountLocked(int i);

        public abstract void logState(Printer printer, String str);
    }

    public static final class DailyItem {
        public LevelStepTracker mChargeSteps;
        public LevelStepTracker mDischargeSteps;
        public long mEndTime;
        public ArrayList<PackageChange> mPackageChanges;
        public long mStartTime;
    }

    @FunctionalInterface
    public interface IntToString {
        String applyAsString(int i);
    }

    public static abstract class LongCounter {
        public abstract long getCountLocked(int i);

        public abstract void logState(Printer printer, String str);
    }

    public static abstract class LongCounterArray {
        public abstract long[] getCountsLocked(int i);

        public abstract void logState(Printer printer, String str);
    }

    public static final class PackageChange {
        public String mPackageName;
        public boolean mUpdate;
        public long mVersionCode;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface StatName {
    }

    public static abstract class Timer {
        public abstract int getCountLocked(int i);

        public long getCurrentDurationMsLocked(long j) {
            return -1L;
        }

        public long getMaxDurationMsLocked(long j) {
            return -1L;
        }

        public Timer getSubTimer() {
            return null;
        }

        public abstract long getTimeSinceMarkLocked(long j);

        public long getTotalDurationMsLocked(long j) {
            return -1L;
        }

        public abstract long getTotalTimeLocked(long j, int i);

        public boolean isRunningLocked() {
            return false;
        }

        public abstract void logState(Printer printer, String str);
    }

    public abstract void commitCurrentHistoryBatchLocked();

    public abstract long computeBatteryRealtime(long j, int i);

    public abstract long computeBatteryScreenOffRealtime(long j, int i);

    public abstract long computeBatteryScreenOffUptime(long j, int i);

    public abstract long computeBatteryTimeRemaining(long j);

    public abstract long computeBatteryUptime(long j, int i);

    public abstract long computeChargeTimeRemaining(long j);

    public abstract long computeRealtime(long j, int i);

    public abstract long computeUptime(long j, int i);

    public abstract void finishIteratingHistoryLocked();

    public abstract void finishIteratingOldHistoryLocked();

    public abstract long getBatteryRealtime(long j);

    public abstract long getBatteryUptime(long j);

    public abstract ControllerActivityCounter getBluetoothControllerActivity();

    public abstract long getBluetoothScanTime(long j, int i);

    public abstract long getCameraOnTime(long j, int i);

    public abstract LevelStepTracker getChargeLevelStepTracker();

    public abstract long[] getCpuFreqs();

    public abstract long getCurrentDailyStartTime();

    public abstract LevelStepTracker getDailyChargeLevelStepTracker();

    public abstract LevelStepTracker getDailyDischargeLevelStepTracker();

    public abstract DailyItem getDailyItemLocked(int i);

    public abstract ArrayList<PackageChange> getDailyPackageChanges();

    public abstract int getDeviceIdleModeCount(int i, int i2);

    public abstract long getDeviceIdleModeTime(int i, long j, int i2);

    public abstract int getDeviceIdlingCount(int i, int i2);

    public abstract long getDeviceIdlingTime(int i, long j, int i2);

    public abstract int getDischargeAmount(int i);

    public abstract int getDischargeAmountScreenDoze();

    public abstract int getDischargeAmountScreenDozeSinceCharge();

    public abstract int getDischargeAmountScreenOff();

    public abstract int getDischargeAmountScreenOffSinceCharge();

    public abstract int getDischargeAmountScreenOn();

    public abstract int getDischargeAmountScreenOnSinceCharge();

    public abstract int getDischargeCurrentLevel();

    public abstract LevelStepTracker getDischargeLevelStepTracker();

    public abstract int getDischargeStartLevel();

    public abstract String getEndPlatformVersion();

    public abstract int getEstimatedBatteryCapacity();

    public abstract long getFlashlightOnCount(int i);

    public abstract long getFlashlightOnTime(long j, int i);

    public abstract long getGlobalWifiRunningTime(long j, int i);

    public abstract long getGpsBatteryDrainMaMs();

    public abstract long getGpsSignalQualityTime(int i, long j, int i2);

    public abstract int getHighDischargeAmountSinceCharge();

    public abstract long getHistoryBaseTime();

    public abstract int getHistoryStringPoolBytes();

    public abstract int getHistoryStringPoolSize();

    public abstract String getHistoryTagPoolString(int i);

    public abstract int getHistoryTagPoolUid(int i);

    public abstract int getHistoryTotalSize();

    public abstract int getHistoryUsedSize();

    public abstract long getInteractiveTime(long j, int i);

    public abstract boolean getIsOnBattery();

    public abstract LongSparseArray<? extends Timer> getKernelMemoryStats();

    public abstract Map<String, ? extends Timer> getKernelWakelockStats();

    public abstract long getLongestDeviceIdleModeTime(int i);

    public abstract int getLowDischargeAmountSinceCharge();

    public abstract int getMaxLearnedBatteryCapacity();

    public abstract int getMinLearnedBatteryCapacity();

    public abstract long getMobileRadioActiveAdjustedTime(int i);

    public abstract int getMobileRadioActiveCount(int i);

    public abstract long getMobileRadioActiveTime(long j, int i);

    public abstract int getMobileRadioActiveUnknownCount(int i);

    public abstract long getMobileRadioActiveUnknownTime(int i);

    public abstract ControllerActivityCounter getModemControllerActivity();

    public abstract long getNetworkActivityBytes(int i, int i2);

    public abstract long getNetworkActivityPackets(int i, int i2);

    public abstract boolean getNextHistoryLocked(HistoryItem historyItem);

    public abstract long getNextMaxDailyDeadline();

    public abstract long getNextMinDailyDeadline();

    public abstract boolean getNextOldHistoryLocked(HistoryItem historyItem);

    public abstract int getNumConnectivityChange(int i);

    public abstract int getParcelVersion();

    public abstract int getPhoneDataConnectionCount(int i, int i2);

    public abstract long getPhoneDataConnectionTime(int i, long j, int i2);

    public abstract Timer getPhoneDataConnectionTimer(int i);

    public abstract int getPhoneOnCount(int i);

    public abstract long getPhoneOnTime(long j, int i);

    public abstract long getPhoneSignalScanningTime(long j, int i);

    public abstract Timer getPhoneSignalScanningTimer();

    public abstract int getPhoneSignalStrengthCount(int i, int i2);

    public abstract long getPhoneSignalStrengthTime(int i, long j, int i2);

    protected abstract Timer getPhoneSignalStrengthTimer(int i);

    public abstract int getPowerSaveModeEnabledCount(int i);

    public abstract long getPowerSaveModeEnabledTime(long j, int i);

    public abstract Map<String, ? extends Timer> getRpmStats();

    public abstract long getScreenBrightnessTime(int i, long j, int i2);

    public abstract Timer getScreenBrightnessTimer(int i);

    public abstract int getScreenDozeCount(int i);

    public abstract long getScreenDozeTime(long j, int i);

    public abstract Map<String, ? extends Timer> getScreenOffRpmStats();

    public abstract int getScreenOnCount(int i);

    public abstract long getScreenOnTime(long j, int i);

    public abstract long getStartClockTime();

    public abstract int getStartCount();

    public abstract String getStartPlatformVersion();

    public abstract long getUahDischarge(int i);

    public abstract long getUahDischargeDeepDoze(int i);

    public abstract long getUahDischargeLightDoze(int i);

    public abstract long getUahDischargeScreenDoze(int i);

    public abstract long getUahDischargeScreenOff(int i);

    public abstract SparseArray<? extends Uid> getUidStats();

    public abstract Map<String, ? extends Timer> getWakeupReasonStats();

    public abstract long getWifiActiveTime(long j, int i);

    public abstract ControllerActivityCounter getWifiControllerActivity();

    public abstract int getWifiMulticastWakelockCount(int i);

    public abstract long getWifiMulticastWakelockTime(long j, int i);

    public abstract long getWifiOnTime(long j, int i);

    public abstract int getWifiSignalStrengthCount(int i, int i2);

    public abstract long getWifiSignalStrengthTime(int i, long j, int i2);

    public abstract Timer getWifiSignalStrengthTimer(int i);

    public abstract int getWifiStateCount(int i, int i2);

    public abstract long getWifiStateTime(int i, long j, int i2);

    public abstract Timer getWifiStateTimer(int i);

    public abstract int getWifiSupplStateCount(int i, int i2);

    public abstract long getWifiSupplStateTime(int i, long j, int i2);

    public abstract Timer getWifiSupplStateTimer(int i);

    public abstract boolean hasBluetoothActivityReporting();

    public abstract boolean hasModemActivityReporting();

    public abstract boolean hasWifiActivityReporting();

    public void prepareForDumpLocked() {
    }

    public abstract boolean startIteratingHistoryLocked();

    public abstract boolean startIteratingOldHistoryLocked();

    public abstract void writeToParcelWithoutUids(Parcel parcel, int i);

    static {
        int length = TelephonyManager.getAllNetworkTypes().length + 1;
        DATA_CONNECTION_EMERGENCY_SERVICE = length;
        int i = length + 1;
        DATA_CONNECTION_OTHER = i;
        DATA_CONNECTION_NAMES = new String[]{"oos", "gprs", "edge", "umts", "cdma", "evdo_0", "evdo_A", "1xrtt", "hsdpa", "hsupa", "hspa", "iden", "evdo_b", "lte", "ehrpd", "hspap", "gsm", "td_scdma", "iwlan", "lte_ca", "nr", "emngcy", "other"};
        NUM_DATA_CONNECTION_TYPES = i + 1;
        WIFI_SUPPL_STATE_NAMES = new String[]{"invalid", "disconn", "disabled", "inactive", "scanning", "authenticating", "associating", "associated", "4-way-handshake", "group-handshake", "completed", "dormant", "uninit"};
        WIFI_SUPPL_STATE_SHORT_NAMES = new String[]{"inv", "dsc", "dis", "inact", "scan", Context.AUTH_SERVICE, "ascing", "asced", "4-way", "group", "compl", "dorm", "uninit"};
        String[] strArr = DATA_CONNECTION_NAMES;
        HISTORY_STATE_DESCRIPTIONS = new BitDescription[]{new BitDescription(Integer.MIN_VALUE, "running", "r"), new BitDescription(1073741824, "wake_lock", "w"), new BitDescription(8388608, "sensor", XmlTags.TAG_SESSION), new BitDescription(536870912, LocationManager.GPS_PROVIDER, "g"), new BitDescription(268435456, "wifi_full_lock", "Wl"), new BitDescription(134217728, "wifi_scan", "Ws"), new BitDescription(65536, "wifi_multicast", "Wm"), new BitDescription(67108864, "wifi_radio", "Wr"), new BitDescription(33554432, "mobile_radio", "Pr"), new BitDescription(2097152, "phone_scanning", "Psc"), new BitDescription(4194304, "audio", FullBackup.APK_TREE_TOKEN), new BitDescription(1048576, "screen", ExifInterface.LATITUDE_SOUTH), new BitDescription(524288, BatteryManager.EXTRA_PLUGGED, "BP"), new BitDescription(262144, "screen_doze", "Sd"), new BitDescription(HistoryItem.STATE_DATA_CONNECTION_MASK, 9, "data_conn", "Pcn", strArr, strArr), new BitDescription(448, 6, "phone_state", "Pst", new String[]{"in", "out", "emergency", "off"}, new String[]{"in", "out", "em", "off"}), new BitDescription(56, 3, "phone_signal_strength", "Pss", new String[]{"none", "poor", "moderate", "good", "great"}, new String[]{AudioSystem.LEGACY_REMOTE_SUBMIX_ADDRESS, "1", ExifInterface.GPS_MEASUREMENT_2D, ExifInterface.GPS_MEASUREMENT_3D, "4"}), new BitDescription(7, 0, "brightness", "Sb", SCREEN_BRIGHTNESS_NAMES, SCREEN_BRIGHTNESS_SHORT_NAMES)};
        HISTORY_STATE2_DESCRIPTIONS = new BitDescription[]{new BitDescription(Integer.MIN_VALUE, "power_save", "ps"), new BitDescription(1073741824, "video", "v"), new BitDescription(536870912, "wifi_running", "Ww"), new BitDescription(268435456, "wifi", ExifInterface.LONGITUDE_WEST), new BitDescription(134217728, "flashlight", "fl"), new BitDescription(100663296, 25, "device_idle", "di", new String[]{"off", "light", "full", "???"}, new String[]{"off", "light", "full", "???"}), new BitDescription(16777216, "charging", "ch"), new BitDescription(262144, "usb_data", "Ud"), new BitDescription(8388608, "phone_in_call", "Pcl"), new BitDescription(4194304, "bluetooth", XmlTags.TAG_BLOB), new BitDescription(112, 4, "wifi_signal_strength", "Wss", new String[]{AudioSystem.LEGACY_REMOTE_SUBMIX_ADDRESS, "1", ExifInterface.GPS_MEASUREMENT_2D, ExifInterface.GPS_MEASUREMENT_3D, "4"}, new String[]{AudioSystem.LEGACY_REMOTE_SUBMIX_ADDRESS, "1", ExifInterface.GPS_MEASUREMENT_2D, ExifInterface.GPS_MEASUREMENT_3D, "4"}), new BitDescription(15, 0, "wifi_suppl", "Wsp", WIFI_SUPPL_STATE_NAMES, WIFI_SUPPL_STATE_SHORT_NAMES), new BitDescription(2097152, Context.CAMERA_SERVICE, Credentials.CERTIFICATE_USAGE_CA), new BitDescription(1048576, "ble_scan", "bles"), new BitDescription(524288, "cellular_high_tx_power", "Chtp"), new BitDescription(128, 7, "gps_signal_quality", "Gss", new String[]{"poor", "good"}, new String[]{"poor", "good"})};
        HISTORY_EVENT_NAMES = new String[]{"null", "proc", FOREGROUND_ACTIVITY_DATA, "top", "sync", "wake_lock_in", "job", "user", "userfg", "conn", "active", "pkginst", "pkgunin", "alarm", Context.STATS_MANAGER, "pkginactive", "pkgactive", "tmpwhitelist", "screenwake", "wakeupap", "longwake", "est_capacity"};
        HISTORY_EVENT_CHECKIN_NAMES = new String[]{"Enl", "Epr", "Efg", "Etp", "Esy", "Ewl", "Ejb", "Eur", "Euf", "Ecn", "Eac", "Epi", "Epu", "Eal", "Est", "Eai", "Eaa", "Etw", "Esw", "Ewa", "Elw", "Eec"};
        sUidToString = new IntToString() { // from class: android.os.-$$Lambda$IyvVQC-0mKtsfXbnO0kDL64hrk0
            @Override // android.os.BatteryStats.IntToString
            public final String applyAsString(int i2) {
                return UserHandle.formatUid(i2);
            }
        };
        $$Lambda$BatteryStats$q1UvBdLgHRZVzc68BxdksTmbuCw __lambda_batterystats_q1uvbdlghrzvzc68bxdkstmbucw = new IntToString() { // from class: android.os.-$$Lambda$BatteryStats$q1UvBdLgHRZVzc68BxdksTmbuCw
            @Override // android.os.BatteryStats.IntToString
            public final String applyAsString(int i2) {
                return Integer.toString(i2);
            }
        };
        sIntToString = __lambda_batterystats_q1uvbdlghrzvzc68bxdkstmbucw;
        IntToString intToString = sUidToString;
        HISTORY_EVENT_INT_FORMATTERS = new IntToString[]{intToString, intToString, intToString, intToString, intToString, intToString, intToString, intToString, intToString, intToString, intToString, __lambda_batterystats_q1uvbdlghrzvzc68bxdkstmbucw, intToString, intToString, intToString, intToString, intToString, intToString, intToString, intToString, intToString, __lambda_batterystats_q1uvbdlghrzvzc68bxdkstmbucw};
        WIFI_STATE_NAMES = new String[]{"off", "scanning", "no_net", "disconn", "sta", "p2p", "sta_p2p", "soft_ap"};
        STEP_LEVEL_MODES_OF_INTEREST = new int[]{7, 15, 11, 7, 7, 7, 7, 7, 15, 11};
        STEP_LEVEL_MODE_VALUES = new int[]{0, 4, 8, 1, 5, 2, 6, 3, 7, 11};
        STEP_LEVEL_MODE_LABELS = new String[]{"screen off", "screen off power save", "screen off device idle", "screen on", "screen on power save", "screen doze", "screen doze power save", "screen doze-suspend", "screen doze-suspend power save", "screen doze-suspend device idle"};
    }

    public static int mapToInternalProcessState(int i) {
        if (i == 20) {
            return 20;
        }
        if (i == 2) {
            return 0;
        }
        if (ActivityManager.isForegroundService(i)) {
            return 1;
        }
        if (i <= 6) {
            return 2;
        }
        if (i <= 11) {
            return 3;
        }
        if (i <= 12) {
            return 4;
        }
        return i <= 13 ? 5 : 6;
    }

    public static abstract class Uid {
        public static final int NUM_PROCESS_STATE = 7;
        public static final int NUM_USER_ACTIVITY_TYPES;
        public static final int NUM_WIFI_BATCHED_SCAN_BINS = 5;
        public static final int PROCESS_STATE_BACKGROUND = 3;
        public static final int PROCESS_STATE_CACHED = 6;
        public static final int PROCESS_STATE_FOREGROUND = 2;
        public static final int PROCESS_STATE_FOREGROUND_SERVICE = 1;
        public static final int PROCESS_STATE_HEAVY_WEIGHT = 5;
        public static final int PROCESS_STATE_TOP = 0;
        public static final int PROCESS_STATE_TOP_SLEEPING = 4;
        static final String[] USER_ACTIVITY_TYPES;
        static final String[] PROCESS_STATE_NAMES = {"Top", "Fg Service", "Foreground", "Background", "Top Sleeping", "Heavy Weight", "Cached"};
        public static final String[] UID_PROCESS_TYPES = {ExifInterface.GPS_DIRECTION_TRUE, "FS", "F", "B", "TS", "HW", "C"};
        public static final int[] CRITICAL_PROC_STATES = {0, 3, 1, 2};

        public static abstract class Pkg {

            public static abstract class Serv {
                public abstract int getLaunches(int i);

                public abstract long getStartTime(long j, int i);

                public abstract int getStarts(int i);
            }

            public abstract ArrayMap<String, ? extends Serv> getServiceStats();

            public abstract ArrayMap<String, ? extends Counter> getWakeupAlarmStats();
        }

        public static abstract class Proc {

            public static class ExcessivePower {
                public static final int TYPE_CPU = 2;
                public static final int TYPE_WAKE = 1;
                public long overTime;
                public int type;
                public long usedTime;
            }

            public abstract int countExcessivePowers();

            public abstract ExcessivePower getExcessivePower(int i);

            public abstract long getForegroundTime(int i);

            public abstract int getNumAnrs(int i);

            public abstract int getNumCrashes(int i);

            public abstract int getStarts(int i);

            public abstract long getSystemTime(int i);

            public abstract long getUserTime(int i);

            public abstract boolean isActive();
        }

        public static abstract class Sensor {
            public static final int GPS = -10000;

            public abstract int getHandle();

            public abstract Timer getSensorBackgroundTime();

            public abstract Timer getSensorTime();
        }

        public static abstract class Wakelock {
            public abstract Timer getWakeTime(int i);
        }

        public abstract Timer getAggregatedPartialWakelockTimer();

        public abstract Timer getAudioTurnedOnTimer();

        public abstract ControllerActivityCounter getBluetoothControllerActivity();

        public abstract Timer getBluetoothScanBackgroundTimer();

        public abstract Counter getBluetoothScanResultBgCounter();

        public abstract Counter getBluetoothScanResultCounter();

        public abstract Timer getBluetoothScanTimer();

        public abstract Timer getBluetoothUnoptimizedScanBackgroundTimer();

        public abstract Timer getBluetoothUnoptimizedScanTimer();

        public abstract Timer getCameraTurnedOnTimer();

        public abstract long getCpuActiveTime();

        public abstract long[] getCpuClusterTimes();

        public abstract long[] getCpuFreqTimes(int i);

        public abstract long[] getCpuFreqTimes(int i, int i2);

        public abstract void getDeferredJobsCheckinLineLocked(StringBuilder sb, int i);

        public abstract void getDeferredJobsLineLocked(StringBuilder sb, int i);

        public abstract Timer getFlashlightTurnedOnTimer();

        public abstract Timer getForegroundActivityTimer();

        public abstract Timer getForegroundServiceTimer();

        public abstract long getFullWifiLockTime(long j, int i);

        public abstract ArrayMap<String, SparseIntArray> getJobCompletionStats();

        public abstract ArrayMap<String, ? extends Timer> getJobStats();

        public abstract int getMobileRadioActiveCount(int i);

        public abstract long getMobileRadioActiveTime(int i);

        public abstract long getMobileRadioApWakeupCount(int i);

        public abstract ControllerActivityCounter getModemControllerActivity();

        public abstract Timer getMulticastWakelockStats();

        public abstract long getNetworkActivityBytes(int i, int i2);

        public abstract long getNetworkActivityPackets(int i, int i2);

        public abstract ArrayMap<String, ? extends Pkg> getPackageStats();

        public abstract SparseArray<? extends Pid> getPidStats();

        public abstract long getProcessStateTime(int i, long j, int i2);

        public abstract Timer getProcessStateTimer(int i);

        public abstract ArrayMap<String, ? extends Proc> getProcessStats();

        public abstract long[] getScreenOffCpuFreqTimes(int i);

        public abstract long[] getScreenOffCpuFreqTimes(int i, int i2);

        public abstract SparseArray<? extends Sensor> getSensorStats();

        public abstract ArrayMap<String, ? extends Timer> getSyncStats();

        public abstract long getSystemCpuTimeUs(int i);

        public abstract long getTimeAtCpuSpeed(int i, int i2, int i3);

        public abstract int getUid();

        public abstract int getUserActivityCount(int i, int i2);

        public abstract long getUserCpuTimeUs(int i);

        public abstract Timer getVibratorOnTimer();

        public abstract Timer getVideoTurnedOnTimer();

        public abstract ArrayMap<String, ? extends Wakelock> getWakelockStats();

        public abstract int getWifiBatchedScanCount(int i, int i2);

        public abstract long getWifiBatchedScanTime(int i, long j, int i2);

        public abstract ControllerActivityCounter getWifiControllerActivity();

        public abstract long getWifiMulticastTime(long j, int i);

        public abstract long getWifiRadioApWakeupCount(int i);

        public abstract long getWifiRunningTime(long j, int i);

        public abstract long getWifiScanActualTime(long j);

        public abstract int getWifiScanBackgroundCount(int i);

        public abstract long getWifiScanBackgroundTime(long j);

        public abstract Timer getWifiScanBackgroundTimer();

        public abstract int getWifiScanCount(int i);

        public abstract long getWifiScanTime(long j, int i);

        public abstract Timer getWifiScanTimer();

        public abstract boolean hasNetworkActivity();

        public abstract boolean hasUserActivity();

        public abstract void noteActivityPausedLocked(long j);

        public abstract void noteActivityResumedLocked(long j);

        public abstract void noteFullWifiLockAcquiredLocked(long j);

        public abstract void noteFullWifiLockReleasedLocked(long j);

        public abstract void noteUserActivityLocked(int i);

        public abstract void noteWifiBatchedScanStartedLocked(int i, long j);

        public abstract void noteWifiBatchedScanStoppedLocked(long j);

        public abstract void noteWifiMulticastDisabledLocked(long j);

        public abstract void noteWifiMulticastEnabledLocked(long j);

        public abstract void noteWifiRunningLocked(long j);

        public abstract void noteWifiScanStartedLocked(long j);

        public abstract void noteWifiScanStoppedLocked(long j);

        public abstract void noteWifiStoppedLocked(long j);

        static {
            String[] strArr = {"other", "button", "touch", Context.ACCESSIBILITY_SERVICE, Context.ATTENTION_SERVICE};
            USER_ACTIVITY_TYPES = strArr;
            NUM_USER_ACTIVITY_TYPES = strArr.length;
        }

        public class Pid {
            public int mWakeNesting;
            public long mWakeStartMs;
            public long mWakeSumMs;

            public Pid() {
            }
        }
    }

    public static final class LevelStepTracker {
        public long mLastStepTime = -1;
        public int mNumStepDurations;
        public final long[] mStepDurations;

        public LevelStepTracker(int i) {
            this.mStepDurations = new long[i];
        }

        public LevelStepTracker(int i, long[] jArr) {
            this.mNumStepDurations = i;
            long[] jArr2 = new long[i];
            this.mStepDurations = jArr2;
            System.arraycopy(jArr, 0, jArr2, 0, i);
        }

        public long getDurationAt(int i) {
            return this.mStepDurations[i] & BatteryStats.STEP_LEVEL_TIME_MASK;
        }

        public int getLevelAt(int i) {
            return (int) ((this.mStepDurations[i] & BatteryStats.STEP_LEVEL_LEVEL_MASK) >> 40);
        }

        public int getInitModeAt(int i) {
            return (int) ((this.mStepDurations[i] & BatteryStats.STEP_LEVEL_INITIAL_MODE_MASK) >> 48);
        }

        public int getModModeAt(int i) {
            return (int) ((this.mStepDurations[i] & BatteryStats.STEP_LEVEL_MODIFIED_MODE_MASK) >> 56);
        }

        private void appendHex(long j, int i, StringBuilder sb) {
            boolean z = false;
            while (i >= 0) {
                int i2 = (int) ((j >> i) & 15);
                i -= 4;
                if (z || i2 != 0) {
                    z = true;
                    if (i2 >= 0 && i2 <= 9) {
                        sb.append((char) (i2 + 48));
                    } else {
                        sb.append((char) ((i2 + 97) - 10));
                    }
                }
            }
        }

        public void encodeEntryAt(int i, StringBuilder sb) {
            long j = this.mStepDurations[i];
            long j2 = BatteryStats.STEP_LEVEL_TIME_MASK & j;
            int i2 = (int) ((BatteryStats.STEP_LEVEL_LEVEL_MASK & j) >> 40);
            int i3 = (int) ((BatteryStats.STEP_LEVEL_INITIAL_MODE_MASK & j) >> 48);
            int i4 = (int) ((j & BatteryStats.STEP_LEVEL_MODIFIED_MODE_MASK) >> 56);
            int i5 = (i3 & 3) + 1;
            if (i5 == 1) {
                sb.append('f');
            } else if (i5 == 2) {
                sb.append('o');
            } else if (i5 == 3) {
                sb.append(DateFormat.DATE);
            } else if (i5 == 4) {
                sb.append(DateFormat.TIME_ZONE);
            }
            if ((i3 & 4) != 0) {
                sb.append('p');
            }
            if ((i3 & 8) != 0) {
                sb.append('i');
            }
            int i6 = (i4 & 3) + 1;
            if (i6 == 1) {
                sb.append('F');
            } else if (i6 == 2) {
                sb.append('O');
            } else if (i6 == 3) {
                sb.append('D');
            } else if (i6 == 4) {
                sb.append('Z');
            }
            if ((i4 & 4) != 0) {
                sb.append('P');
            }
            if ((i4 & 8) != 0) {
                sb.append('I');
            }
            sb.append('-');
            appendHex(i2, 4, sb);
            sb.append('-');
            appendHex(j2, 36, sb);
        }

        public void decodeEntryAt(int i, String str) {
            int i2;
            int i3;
            char cCharAt;
            int i4;
            int i5;
            char cCharAt2;
            long j;
            int length = str.length();
            int i6 = 0;
            long j2 = 0;
            while (i6 < length && (cCharAt2 = str.charAt(i6)) != '-') {
                i6++;
                if (cCharAt2 != 'D') {
                    if (cCharAt2 != 'F') {
                        if (cCharAt2 == 'I') {
                            j = 576460752303423488L;
                        } else if (cCharAt2 == 'Z') {
                            j = 216172782113783808L;
                        } else if (cCharAt2 == 'd') {
                            j = 562949953421312L;
                        } else if (cCharAt2 != 'f') {
                            if (cCharAt2 == 'i') {
                                j = 2251799813685248L;
                            } else if (cCharAt2 == 'z') {
                                j = 844424930131968L;
                            } else if (cCharAt2 == 'O') {
                                j = 72057594037927936L;
                            } else if (cCharAt2 == 'P') {
                                j = 288230376151711744L;
                            } else if (cCharAt2 == 'o') {
                                j = 281474976710656L;
                            } else if (cCharAt2 == 'p') {
                                j = TrafficStats.PB_IN_BYTES;
                            }
                        }
                    }
                    j2 |= 0;
                } else {
                    j = 144115188075855872L;
                }
                j2 |= j;
            }
            int i7 = i6 + 1;
            long j3 = 0;
            while (i7 < length && (cCharAt = str.charAt(i7)) != '-') {
                i7++;
                j3 <<= 4;
                if (cCharAt < '0' || cCharAt > '9') {
                    if (cCharAt >= 'a' && cCharAt <= 'f') {
                        i4 = cCharAt - 'a';
                    } else if (cCharAt >= 'A' && cCharAt <= 'F') {
                        i4 = cCharAt - 'A';
                    }
                    i5 = i4 + 10;
                } else {
                    i5 = cCharAt - '0';
                }
                j3 += (long) i5;
            }
            int i8 = i7 + 1;
            long j4 = j2 | ((j3 << 40) & BatteryStats.STEP_LEVEL_LEVEL_MASK);
            long j5 = 0;
            while (i8 < length) {
                char cCharAt3 = str.charAt(i8);
                if (cCharAt3 == '-') {
                    break;
                }
                i8++;
                j5 <<= 4;
                if (cCharAt3 < '0' || cCharAt3 > '9') {
                    if (cCharAt3 >= 'a' && cCharAt3 <= 'f') {
                        i2 = cCharAt3 - 'a';
                    } else if (cCharAt3 >= 'A' && cCharAt3 <= 'F') {
                        i2 = cCharAt3 - 'A';
                    }
                    i3 = i2 + 10;
                } else {
                    i3 = cCharAt3 - '0';
                }
                j5 += (long) i3;
            }
            this.mStepDurations[i] = (j5 & BatteryStats.STEP_LEVEL_TIME_MASK) | j4;
        }

        public void init() {
            this.mLastStepTime = -1L;
            this.mNumStepDurations = 0;
        }

        public void clearTime() {
            this.mLastStepTime = -1L;
        }

        public long computeTimePerLevel() {
            long[] jArr = this.mStepDurations;
            int i = this.mNumStepDurations;
            if (i <= 0) {
                return -1L;
            }
            long j = 0;
            for (int i2 = 0; i2 < i; i2++) {
                j += jArr[i2] & BatteryStats.STEP_LEVEL_TIME_MASK;
            }
            return j / ((long) i);
        }

        public long computeTimeEstimate(long j, long j2, int[] iArr) {
            long[] jArr = this.mStepDurations;
            int i = this.mNumStepDurations;
            if (i <= 0) {
                return -1L;
            }
            long j3 = 0;
            int i2 = 0;
            for (int i3 = 0; i3 < i; i3++) {
                long j4 = (jArr[i3] & BatteryStats.STEP_LEVEL_INITIAL_MODE_MASK) >> 48;
                if ((((jArr[i3] & BatteryStats.STEP_LEVEL_MODIFIED_MODE_MASK) >> 56) & j) == 0 && (j4 & j) == j2) {
                    i2++;
                    j3 += jArr[i3] & BatteryStats.STEP_LEVEL_TIME_MASK;
                }
            }
            if (i2 <= 0) {
                return -1L;
            }
            if (iArr != null) {
                iArr[0] = i2;
            }
            return (j3 / ((long) i2)) * 100;
        }

        public void addLevelSteps(int i, long j, long j2) {
            int length = this.mNumStepDurations;
            long j3 = this.mLastStepTime;
            if (j3 >= 0 && i > 0) {
                long[] jArr = this.mStepDurations;
                long j4 = j2 - j3;
                for (int i2 = 0; i2 < i; i2++) {
                    System.arraycopy(jArr, 0, jArr, 1, jArr.length - 1);
                    long j5 = j4 / ((long) (i - i2));
                    j4 -= j5;
                    if (j5 > BatteryStats.STEP_LEVEL_TIME_MASK) {
                        j5 = 1099511627775L;
                    }
                    jArr[0] = j5 | j;
                }
                length += i;
                if (length > jArr.length) {
                    length = jArr.length;
                }
            }
            this.mNumStepDurations = length;
            this.mLastStepTime = j2;
        }

        public void readFromParcel(Parcel parcel) {
            int i = parcel.readInt();
            if (i > this.mStepDurations.length) {
                throw new ParcelFormatException("more step durations than available: " + i);
            }
            this.mNumStepDurations = i;
            for (int i2 = 0; i2 < i; i2++) {
                this.mStepDurations[i2] = parcel.readLong();
            }
        }

        public void writeToParcel(Parcel parcel) {
            int i = this.mNumStepDurations;
            parcel.writeInt(i);
            for (int i2 = 0; i2 < i; i2++) {
                parcel.writeLong(this.mStepDurations[i2]);
            }
        }
    }

    public static final class HistoryTag {
        public int poolIdx;
        public String string;
        public int uid;

        public void setTo(HistoryTag historyTag) {
            this.string = historyTag.string;
            this.uid = historyTag.uid;
            this.poolIdx = historyTag.poolIdx;
        }

        public void setTo(String str, int i) {
            this.string = str;
            this.uid = i;
            this.poolIdx = -1;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.string);
            parcel.writeInt(this.uid);
        }

        public void readFromParcel(Parcel parcel) {
            this.string = parcel.readString();
            this.uid = parcel.readInt();
            this.poolIdx = -1;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            HistoryTag historyTag = (HistoryTag) obj;
            return this.uid == historyTag.uid && this.string.equals(historyTag.string);
        }

        public int hashCode() {
            return (this.string.hashCode() * 31) + this.uid;
        }
    }

    public static final class HistoryStepDetails {
        public int appCpuSTime1;
        public int appCpuSTime2;
        public int appCpuSTime3;
        public int appCpuUTime1;
        public int appCpuUTime2;
        public int appCpuUTime3;
        public int appCpuUid1;
        public int appCpuUid2;
        public int appCpuUid3;
        public int statIOWaitTime;
        public int statIdlTime;
        public int statIrqTime;
        public String statPlatformIdleState;
        public int statSoftIrqTime;
        public String statSubsystemPowerState;
        public int statSystemTime;
        public int statUserTime;
        public int systemTime;
        public int userTime;

        public HistoryStepDetails() {
            clear();
        }

        public void clear() {
            this.systemTime = 0;
            this.userTime = 0;
            this.appCpuUid3 = -1;
            this.appCpuUid2 = -1;
            this.appCpuUid1 = -1;
            this.appCpuSTime3 = 0;
            this.appCpuUTime3 = 0;
            this.appCpuSTime2 = 0;
            this.appCpuUTime2 = 0;
            this.appCpuSTime1 = 0;
            this.appCpuUTime1 = 0;
        }

        public void writeToParcel(Parcel parcel) {
            parcel.writeInt(this.userTime);
            parcel.writeInt(this.systemTime);
            parcel.writeInt(this.appCpuUid1);
            parcel.writeInt(this.appCpuUTime1);
            parcel.writeInt(this.appCpuSTime1);
            parcel.writeInt(this.appCpuUid2);
            parcel.writeInt(this.appCpuUTime2);
            parcel.writeInt(this.appCpuSTime2);
            parcel.writeInt(this.appCpuUid3);
            parcel.writeInt(this.appCpuUTime3);
            parcel.writeInt(this.appCpuSTime3);
            parcel.writeInt(this.statUserTime);
            parcel.writeInt(this.statSystemTime);
            parcel.writeInt(this.statIOWaitTime);
            parcel.writeInt(this.statIrqTime);
            parcel.writeInt(this.statSoftIrqTime);
            parcel.writeInt(this.statIdlTime);
            parcel.writeString(this.statPlatformIdleState);
            parcel.writeString(this.statSubsystemPowerState);
        }

        public void readFromParcel(Parcel parcel) {
            this.userTime = parcel.readInt();
            this.systemTime = parcel.readInt();
            this.appCpuUid1 = parcel.readInt();
            this.appCpuUTime1 = parcel.readInt();
            this.appCpuSTime1 = parcel.readInt();
            this.appCpuUid2 = parcel.readInt();
            this.appCpuUTime2 = parcel.readInt();
            this.appCpuSTime2 = parcel.readInt();
            this.appCpuUid3 = parcel.readInt();
            this.appCpuUTime3 = parcel.readInt();
            this.appCpuSTime3 = parcel.readInt();
            this.statUserTime = parcel.readInt();
            this.statSystemTime = parcel.readInt();
            this.statIOWaitTime = parcel.readInt();
            this.statIrqTime = parcel.readInt();
            this.statSoftIrqTime = parcel.readInt();
            this.statIdlTime = parcel.readInt();
            this.statPlatformIdleState = parcel.readString();
            this.statSubsystemPowerState = parcel.readString();
        }
    }

    public static final class HistoryItem {
        public static final byte CMD_CURRENT_TIME = 5;
        public static final byte CMD_NULL = -1;
        public static final byte CMD_OVERFLOW = 6;
        public static final byte CMD_RESET = 7;
        public static final byte CMD_SHUTDOWN = 8;
        public static final byte CMD_START = 4;
        public static final byte CMD_UPDATE = 0;
        public static final int EVENT_ACTIVE = 10;
        public static final int EVENT_ALARM = 13;
        public static final int EVENT_ALARM_FINISH = 16397;
        public static final int EVENT_ALARM_START = 32781;
        public static final int EVENT_COLLECT_EXTERNAL_STATS = 14;
        public static final int EVENT_CONNECTIVITY_CHANGED = 9;
        public static final int EVENT_COUNT = 22;
        public static final int EVENT_FLAG_FINISH = 16384;
        public static final int EVENT_FLAG_START = 32768;
        public static final int EVENT_FOREGROUND = 2;
        public static final int EVENT_FOREGROUND_FINISH = 16386;
        public static final int EVENT_FOREGROUND_START = 32770;
        public static final int EVENT_JOB = 6;
        public static final int EVENT_JOB_FINISH = 16390;
        public static final int EVENT_JOB_START = 32774;
        public static final int EVENT_LONG_WAKE_LOCK = 20;
        public static final int EVENT_LONG_WAKE_LOCK_FINISH = 16404;
        public static final int EVENT_LONG_WAKE_LOCK_START = 32788;
        public static final int EVENT_NONE = 0;
        public static final int EVENT_PACKAGE_ACTIVE = 16;
        public static final int EVENT_PACKAGE_INACTIVE = 15;
        public static final int EVENT_PACKAGE_INSTALLED = 11;
        public static final int EVENT_PACKAGE_UNINSTALLED = 12;
        public static final int EVENT_PROC = 1;
        public static final int EVENT_PROC_FINISH = 16385;
        public static final int EVENT_PROC_START = 32769;
        public static final int EVENT_SCREEN_WAKE_UP = 18;
        public static final int EVENT_SYNC = 4;
        public static final int EVENT_SYNC_FINISH = 16388;
        public static final int EVENT_SYNC_START = 32772;
        public static final int EVENT_TEMP_WHITELIST = 17;
        public static final int EVENT_TEMP_WHITELIST_FINISH = 16401;
        public static final int EVENT_TEMP_WHITELIST_START = 32785;
        public static final int EVENT_TOP = 3;
        public static final int EVENT_TOP_FINISH = 16387;
        public static final int EVENT_TOP_START = 32771;
        public static final int EVENT_TYPE_MASK = -49153;
        public static final int EVENT_USER_FOREGROUND = 8;
        public static final int EVENT_USER_FOREGROUND_FINISH = 16392;
        public static final int EVENT_USER_FOREGROUND_START = 32776;
        public static final int EVENT_USER_RUNNING = 7;
        public static final int EVENT_USER_RUNNING_FINISH = 16391;
        public static final int EVENT_USER_RUNNING_START = 32775;
        public static final int EVENT_WAKEUP_AP = 19;
        public static final int EVENT_WAKE_LOCK = 5;
        public static final int EVENT_WAKE_LOCK_FINISH = 16389;
        public static final int EVENT_WAKE_LOCK_START = 32773;
        public static final int MOST_INTERESTING_STATES = 1835008;
        public static final int MOST_INTERESTING_STATES2 = -1749024768;
        public static final int SETTLE_TO_ZERO_STATES = -1900544;
        public static final int SETTLE_TO_ZERO_STATES2 = 1748959232;
        public static final int STATE2_BLUETOOTH_ON_FLAG = 4194304;
        public static final int STATE2_BLUETOOTH_SCAN_FLAG = 1048576;
        public static final int STATE2_CAMERA_FLAG = 2097152;
        public static final int STATE2_CELLULAR_HIGH_TX_POWER_FLAG = 524288;
        public static final int STATE2_CHARGING_FLAG = 16777216;
        public static final int STATE2_DEVICE_IDLE_MASK = 100663296;
        public static final int STATE2_DEVICE_IDLE_SHIFT = 25;
        public static final int STATE2_FLASHLIGHT_FLAG = 134217728;
        public static final int STATE2_GPS_SIGNAL_QUALITY_MASK = 128;
        public static final int STATE2_GPS_SIGNAL_QUALITY_SHIFT = 7;
        public static final int STATE2_PHONE_IN_CALL_FLAG = 8388608;
        public static final int STATE2_POWER_SAVE_FLAG = Integer.MIN_VALUE;
        public static final int STATE2_USB_DATA_LINK_FLAG = 262144;
        public static final int STATE2_VIDEO_ON_FLAG = 1073741824;
        public static final int STATE2_WIFI_ON_FLAG = 268435456;
        public static final int STATE2_WIFI_RUNNING_FLAG = 536870912;
        public static final int STATE2_WIFI_SIGNAL_STRENGTH_MASK = 112;
        public static final int STATE2_WIFI_SIGNAL_STRENGTH_SHIFT = 4;
        public static final int STATE2_WIFI_SUPPL_STATE_MASK = 15;
        public static final int STATE2_WIFI_SUPPL_STATE_SHIFT = 0;
        public static final int STATE_AUDIO_ON_FLAG = 4194304;
        public static final int STATE_BATTERY_PLUGGED_FLAG = 524288;
        public static final int STATE_BRIGHTNESS_MASK = 7;
        public static final int STATE_BRIGHTNESS_SHIFT = 0;
        public static final int STATE_CPU_RUNNING_FLAG = Integer.MIN_VALUE;
        public static final int STATE_DATA_CONNECTION_MASK = 15872;
        public static final int STATE_DATA_CONNECTION_SHIFT = 9;
        public static final int STATE_GPS_ON_FLAG = 536870912;
        public static final int STATE_MOBILE_RADIO_ACTIVE_FLAG = 33554432;
        public static final int STATE_PHONE_SCANNING_FLAG = 2097152;
        public static final int STATE_PHONE_SIGNAL_STRENGTH_MASK = 56;
        public static final int STATE_PHONE_SIGNAL_STRENGTH_SHIFT = 3;
        public static final int STATE_PHONE_STATE_MASK = 448;
        public static final int STATE_PHONE_STATE_SHIFT = 6;
        private static final int STATE_RESERVED_0 = 16777216;
        public static final int STATE_SCREEN_DOZE_FLAG = 262144;
        public static final int STATE_SCREEN_ON_FLAG = 1048576;
        public static final int STATE_SENSOR_ON_FLAG = 8388608;
        public static final int STATE_WAKE_LOCK_FLAG = 1073741824;
        public static final int STATE_WIFI_FULL_LOCK_FLAG = 268435456;
        public static final int STATE_WIFI_MULTICAST_ON_FLAG = 65536;
        public static final int STATE_WIFI_RADIO_ACTIVE_FLAG = 67108864;
        public static final int STATE_WIFI_SCAN_FLAG = 134217728;
        public int batteryChargeUAh;
        public byte batteryHealth;
        public byte batteryLevel;
        public byte batteryPlugType;
        public byte batteryStatus;
        public short batteryTemperature;
        public char batteryVoltage;
        public long currentTime;
        public int eventCode;
        public HistoryTag eventTag;
        public double modemRailChargeMah;
        public HistoryItem next;
        public int numReadInts;
        public int states;
        public int states2;
        public HistoryStepDetails stepDetails;
        public long time;
        public HistoryTag wakeReasonTag;
        public HistoryTag wakelockTag;
        public double wifiRailChargeMah;
        public byte cmd = -1;
        public final HistoryTag localWakelockTag = new HistoryTag();
        public final HistoryTag localWakeReasonTag = new HistoryTag();
        public final HistoryTag localEventTag = new HistoryTag();

        public boolean isDeltaData() {
            return this.cmd == 0;
        }

        public HistoryItem() {
        }

        public HistoryItem(Parcel parcel) {
            readFromParcel(parcel);
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeLong(this.time);
            parcel.writeInt((this.cmd & 255) | ((this.batteryLevel << 8) & 65280) | ((this.batteryStatus << 16) & SurfaceControl.FX_SURFACE_MASK) | ((this.batteryHealth << IGnssVisibilityControlCallback.NfwRequestor.AUTOMOBILE_CLIENT) & 15728640) | ((this.batteryPlugType << 24) & 251658240) | (this.wakelockTag != null ? 268435456 : 0) | (this.wakeReasonTag != null ? 536870912 : 0) | (this.eventCode != 0 ? 1073741824 : 0));
            parcel.writeInt((this.batteryTemperature & 65535) | ((this.batteryVoltage << 16) & (-65536)));
            parcel.writeInt(this.batteryChargeUAh);
            parcel.writeDouble(this.modemRailChargeMah);
            parcel.writeDouble(this.wifiRailChargeMah);
            parcel.writeInt(this.states);
            parcel.writeInt(this.states2);
            HistoryTag historyTag = this.wakelockTag;
            if (historyTag != null) {
                historyTag.writeToParcel(parcel, i);
            }
            HistoryTag historyTag2 = this.wakeReasonTag;
            if (historyTag2 != null) {
                historyTag2.writeToParcel(parcel, i);
            }
            int i2 = this.eventCode;
            if (i2 != 0) {
                parcel.writeInt(i2);
                this.eventTag.writeToParcel(parcel, i);
            }
            byte b = this.cmd;
            if (b == 5 || b == 7) {
                parcel.writeLong(this.currentTime);
            }
        }

        public void readFromParcel(Parcel parcel) {
            int iDataPosition = parcel.dataPosition();
            this.time = parcel.readLong();
            int i = parcel.readInt();
            this.cmd = (byte) (i & 255);
            this.batteryLevel = (byte) ((i >> 8) & 255);
            this.batteryStatus = (byte) ((i >> 16) & 15);
            this.batteryHealth = (byte) ((i >> 20) & 15);
            this.batteryPlugType = (byte) ((i >> 24) & 15);
            int i2 = parcel.readInt();
            this.batteryTemperature = (short) (i2 & 65535);
            this.batteryVoltage = (char) ((i2 >> 16) & 65535);
            this.batteryChargeUAh = parcel.readInt();
            this.modemRailChargeMah = parcel.readDouble();
            this.wifiRailChargeMah = parcel.readDouble();
            this.states = parcel.readInt();
            this.states2 = parcel.readInt();
            if ((268435456 & i) != 0) {
                HistoryTag historyTag = this.localWakelockTag;
                this.wakelockTag = historyTag;
                historyTag.readFromParcel(parcel);
            } else {
                this.wakelockTag = null;
            }
            if ((536870912 & i) != 0) {
                HistoryTag historyTag2 = this.localWakeReasonTag;
                this.wakeReasonTag = historyTag2;
                historyTag2.readFromParcel(parcel);
            } else {
                this.wakeReasonTag = null;
            }
            if ((i & 1073741824) != 0) {
                this.eventCode = parcel.readInt();
                HistoryTag historyTag3 = this.localEventTag;
                this.eventTag = historyTag3;
                historyTag3.readFromParcel(parcel);
            } else {
                this.eventCode = 0;
                this.eventTag = null;
            }
            byte b = this.cmd;
            if (b == 5 || b == 7) {
                this.currentTime = parcel.readLong();
            } else {
                this.currentTime = 0L;
            }
            this.numReadInts += (parcel.dataPosition() - iDataPosition) / 4;
        }

        public void clear() {
            this.time = 0L;
            this.cmd = (byte) -1;
            this.batteryLevel = (byte) 0;
            this.batteryStatus = (byte) 0;
            this.batteryHealth = (byte) 0;
            this.batteryPlugType = (byte) 0;
            this.batteryTemperature = (short) 0;
            this.batteryVoltage = (char) 0;
            this.batteryChargeUAh = 0;
            this.modemRailChargeMah = 0.0d;
            this.wifiRailChargeMah = 0.0d;
            this.states = 0;
            this.states2 = 0;
            this.wakelockTag = null;
            this.wakeReasonTag = null;
            this.eventCode = 0;
            this.eventTag = null;
        }

        public void setTo(HistoryItem historyItem) {
            this.time = historyItem.time;
            this.cmd = historyItem.cmd;
            setToCommon(historyItem);
        }

        public void setTo(long j, byte b, HistoryItem historyItem) {
            this.time = j;
            this.cmd = b;
            setToCommon(historyItem);
        }

        private void setToCommon(HistoryItem historyItem) {
            this.batteryLevel = historyItem.batteryLevel;
            this.batteryStatus = historyItem.batteryStatus;
            this.batteryHealth = historyItem.batteryHealth;
            this.batteryPlugType = historyItem.batteryPlugType;
            this.batteryTemperature = historyItem.batteryTemperature;
            this.batteryVoltage = historyItem.batteryVoltage;
            this.batteryChargeUAh = historyItem.batteryChargeUAh;
            this.modemRailChargeMah = historyItem.modemRailChargeMah;
            this.wifiRailChargeMah = historyItem.wifiRailChargeMah;
            this.states = historyItem.states;
            this.states2 = historyItem.states2;
            if (historyItem.wakelockTag != null) {
                HistoryTag historyTag = this.localWakelockTag;
                this.wakelockTag = historyTag;
                historyTag.setTo(historyItem.wakelockTag);
            } else {
                this.wakelockTag = null;
            }
            if (historyItem.wakeReasonTag != null) {
                HistoryTag historyTag2 = this.localWakeReasonTag;
                this.wakeReasonTag = historyTag2;
                historyTag2.setTo(historyItem.wakeReasonTag);
            } else {
                this.wakeReasonTag = null;
            }
            this.eventCode = historyItem.eventCode;
            if (historyItem.eventTag != null) {
                HistoryTag historyTag3 = this.localEventTag;
                this.eventTag = historyTag3;
                historyTag3.setTo(historyItem.eventTag);
            } else {
                this.eventTag = null;
            }
            this.currentTime = historyItem.currentTime;
        }

        public boolean sameNonEvent(HistoryItem historyItem) {
            return this.batteryLevel == historyItem.batteryLevel && this.batteryStatus == historyItem.batteryStatus && this.batteryHealth == historyItem.batteryHealth && this.batteryPlugType == historyItem.batteryPlugType && this.batteryTemperature == historyItem.batteryTemperature && this.batteryVoltage == historyItem.batteryVoltage && this.batteryChargeUAh == historyItem.batteryChargeUAh && this.modemRailChargeMah == historyItem.modemRailChargeMah && this.wifiRailChargeMah == historyItem.wifiRailChargeMah && this.states == historyItem.states && this.states2 == historyItem.states2 && this.currentTime == historyItem.currentTime;
        }

        public boolean same(HistoryItem historyItem) {
            if (!sameNonEvent(historyItem) || this.eventCode != historyItem.eventCode) {
                return false;
            }
            HistoryTag historyTag = this.wakelockTag;
            HistoryTag historyTag2 = historyItem.wakelockTag;
            if (historyTag != historyTag2 && (historyTag == null || historyTag2 == null || !historyTag.equals(historyTag2))) {
                return false;
            }
            HistoryTag historyTag3 = this.wakeReasonTag;
            HistoryTag historyTag4 = historyItem.wakeReasonTag;
            if (historyTag3 != historyTag4 && (historyTag3 == null || historyTag4 == null || !historyTag3.equals(historyTag4))) {
                return false;
            }
            HistoryTag historyTag5 = this.eventTag;
            HistoryTag historyTag6 = historyItem.eventTag;
            if (historyTag5 != historyTag6) {
                return (historyTag5 == null || historyTag6 == null || !historyTag5.equals(historyTag6)) ? false : true;
            }
            return true;
        }
    }

    public static final class HistoryEventTracker {
        private final HashMap<String, SparseIntArray>[] mActiveEvents = new HashMap[22];

        public boolean updateState(int i, String str, int i2, int i3) {
            SparseIntArray sparseIntArray;
            int iIndexOfKey;
            if ((32768 & i) == 0) {
                if ((i & 16384) == 0) {
                    return true;
                }
                HashMap<String, SparseIntArray> map = this.mActiveEvents[i & HistoryItem.EVENT_TYPE_MASK];
                if (map == null || (sparseIntArray = map.get(str)) == null || (iIndexOfKey = sparseIntArray.indexOfKey(i2)) < 0) {
                    return false;
                }
                sparseIntArray.removeAt(iIndexOfKey);
                if (sparseIntArray.size() > 0) {
                    return true;
                }
                map.remove(str);
                return true;
            }
            int i4 = i & HistoryItem.EVENT_TYPE_MASK;
            HashMap<String, SparseIntArray> map2 = this.mActiveEvents[i4];
            if (map2 == null) {
                map2 = new HashMap<>();
                this.mActiveEvents[i4] = map2;
            }
            SparseIntArray sparseIntArray2 = map2.get(str);
            if (sparseIntArray2 == null) {
                sparseIntArray2 = new SparseIntArray();
                map2.put(str, sparseIntArray2);
            }
            if (sparseIntArray2.indexOfKey(i2) >= 0) {
                return false;
            }
            sparseIntArray2.put(i2, i3);
            return true;
        }

        public void removeEvents(int i) {
            this.mActiveEvents[i & HistoryItem.EVENT_TYPE_MASK] = null;
        }

        public HashMap<String, SparseIntArray> getStateForEvent(int i) {
            return this.mActiveEvents[i];
        }
    }

    public static final class BitDescription {
        public final int mask;
        public final String name;
        public final int shift;
        public final String shortName;
        public final String[] shortValues;
        public final String[] values;

        public BitDescription(int i, String str, String str2) {
            this.mask = i;
            this.shift = -1;
            this.name = str;
            this.shortName = str2;
            this.values = null;
            this.shortValues = null;
        }

        public BitDescription(int i, int i2, String str, String str2, String[] strArr, String[] strArr2) {
            this.mask = i;
            this.shift = i2;
            this.name = str;
            this.shortName = str2;
            this.values = strArr;
            this.shortValues = strArr2;
        }
    }

    private static final void formatTimeRaw(StringBuilder sb, long j) {
        long j2 = j / 86400;
        if (j2 != 0) {
            sb.append(j2);
            sb.append("d ");
        }
        long j3 = j2 * 60 * 60 * 24;
        long j4 = (j - j3) / 3600;
        if (j4 != 0 || j3 != 0) {
            sb.append(j4);
            sb.append("h ");
        }
        long j5 = j3 + (j4 * 60 * 60);
        long j6 = (j - j5) / 60;
        if (j6 != 0 || j5 != 0) {
            sb.append(j6);
            sb.append("m ");
        }
        long j7 = j5 + (j6 * 60);
        if (j == 0 && j7 == 0) {
            return;
        }
        sb.append(j - j7);
        sb.append("s ");
    }

    public static final void formatTimeMs(StringBuilder sb, long j) {
        long j2 = j / 1000;
        formatTimeRaw(sb, j2);
        sb.append(j - (j2 * 1000));
        sb.append("ms ");
    }

    public static final void formatTimeMsNoSpace(StringBuilder sb, long j) {
        long j2 = j / 1000;
        formatTimeRaw(sb, j2);
        sb.append(j - (j2 * 1000));
        sb.append("ms");
    }

    public final String formatRatioLocked(long j, long j2) {
        if (j2 == 0) {
            return "--%";
        }
        this.mFormatBuilder.setLength(0);
        this.mFormatter.format("%.1f%%", Float.valueOf((j / j2) * 100.0f));
        return this.mFormatBuilder.toString();
    }

    final String formatBytesLocked(long j) {
        this.mFormatBuilder.setLength(0);
        if (j < 1024) {
            return j + "B";
        }
        if (j < 1048576) {
            this.mFormatter.format("%.2fKB", Double.valueOf(j / 1024.0d));
            return this.mFormatBuilder.toString();
        }
        if (j < 1073741824) {
            this.mFormatter.format("%.2fMB", Double.valueOf(j / 1048576.0d));
            return this.mFormatBuilder.toString();
        }
        this.mFormatter.format("%.2fGB", Double.valueOf(j / 1.073741824E9d));
        return this.mFormatBuilder.toString();
    }

    private static long roundUsToMs(long j) {
        return (j + 500) / 1000;
    }

    private static long computeWakeLock(Timer timer, long j, int i) {
        if (timer != null) {
            return (timer.getTotalTimeLocked(j, i) + 500) / 1000;
        }
        return 0L;
    }

    private static final String printWakeLock(StringBuilder sb, Timer timer, long j, String str, int i, String str2) {
        if (timer != null) {
            long jComputeWakeLock = computeWakeLock(timer, j, i);
            int countLocked = timer.getCountLocked(i);
            if (jComputeWakeLock != 0) {
                sb.append(str2);
                formatTimeMs(sb, jComputeWakeLock);
                if (str != null) {
                    sb.append(str);
                    sb.append(' ');
                }
                sb.append('(');
                sb.append(countLocked);
                sb.append(" times)");
                long j2 = j / 1000;
                long maxDurationMsLocked = timer.getMaxDurationMsLocked(j2);
                if (maxDurationMsLocked >= 0) {
                    sb.append(" max=");
                    sb.append(maxDurationMsLocked);
                }
                long totalDurationMsLocked = timer.getTotalDurationMsLocked(j2);
                if (totalDurationMsLocked > jComputeWakeLock) {
                    sb.append(" actual=");
                    sb.append(totalDurationMsLocked);
                }
                if (!timer.isRunningLocked()) {
                    return ", ";
                }
                long currentDurationMsLocked = timer.getCurrentDurationMsLocked(j2);
                if (currentDurationMsLocked >= 0) {
                    sb.append(" (running for ");
                    sb.append(currentDurationMsLocked);
                    sb.append("ms)");
                    return ", ";
                }
                sb.append(" (running)");
                return ", ";
            }
        }
        return str2;
    }

    private static final boolean printTimer(PrintWriter printWriter, StringBuilder sb, Timer timer, long j, int i, String str, String str2) {
        if (timer != null) {
            long totalTimeLocked = (timer.getTotalTimeLocked(j, i) + 500) / 1000;
            int countLocked = timer.getCountLocked(i);
            if (totalTimeLocked != 0) {
                sb.setLength(0);
                sb.append(str);
                sb.append("    ");
                sb.append(str2);
                sb.append(": ");
                formatTimeMs(sb, totalTimeLocked);
                sb.append("realtime (");
                sb.append(countLocked);
                sb.append(" times)");
                long j2 = j / 1000;
                long maxDurationMsLocked = timer.getMaxDurationMsLocked(j2);
                if (maxDurationMsLocked >= 0) {
                    sb.append(" max=");
                    sb.append(maxDurationMsLocked);
                }
                if (timer.isRunningLocked()) {
                    long currentDurationMsLocked = timer.getCurrentDurationMsLocked(j2);
                    if (currentDurationMsLocked >= 0) {
                        sb.append(" (running for ");
                        sb.append(currentDurationMsLocked);
                        sb.append("ms)");
                    } else {
                        sb.append(" (running)");
                    }
                }
                printWriter.println(sb.toString());
                return true;
            }
        }
        return false;
    }

    private static final String printWakeLockCheckin(StringBuilder sb, Timer timer, long j, String str, int i, String str2) {
        int countLocked;
        String str3;
        long totalDurationMsLocked;
        long currentDurationMsLocked;
        long maxDurationMsLocked;
        String str4;
        long totalTimeLocked = 0;
        if (timer != null) {
            totalTimeLocked = timer.getTotalTimeLocked(j, i);
            countLocked = timer.getCountLocked(i);
            long j2 = j / 1000;
            currentDurationMsLocked = timer.getCurrentDurationMsLocked(j2);
            maxDurationMsLocked = timer.getMaxDurationMsLocked(j2);
            totalDurationMsLocked = timer.getTotalDurationMsLocked(j2);
            str3 = str2;
        } else {
            countLocked = 0;
            str3 = str2;
            totalDurationMsLocked = 0;
            currentDurationMsLocked = 0;
            maxDurationMsLocked = 0;
        }
        sb.append(str3);
        sb.append((totalTimeLocked + 500) / 1000);
        sb.append(',');
        if (str != null) {
            str4 = str + ",";
        } else {
            str4 = "";
        }
        sb.append(str4);
        sb.append(countLocked);
        sb.append(',');
        sb.append(currentDurationMsLocked);
        sb.append(',');
        sb.append(maxDurationMsLocked);
        if (str != null) {
            sb.append(',');
            sb.append(totalDurationMsLocked);
        }
        return ",";
    }

    private static final void dumpLineHeader(PrintWriter printWriter, int i, String str, String str2) {
        printWriter.print(9);
        printWriter.print(',');
        printWriter.print(i);
        printWriter.print(',');
        printWriter.print(str);
        printWriter.print(',');
        printWriter.print(str2);
    }

    private static final void dumpLine(PrintWriter printWriter, int i, String str, String str2, Object... objArr) {
        dumpLineHeader(printWriter, i, str, str2);
        for (Object obj : objArr) {
            printWriter.print(',');
            printWriter.print(obj);
        }
        printWriter.println();
    }

    private static final void dumpTimer(PrintWriter printWriter, int i, String str, String str2, Timer timer, long j, int i2) {
        if (timer != null) {
            long jRoundUsToMs = roundUsToMs(timer.getTotalTimeLocked(j, i2));
            int countLocked = timer.getCountLocked(i2);
            if (jRoundUsToMs == 0 && countLocked == 0) {
                return;
            }
            dumpLine(printWriter, i, str, str2, Long.valueOf(jRoundUsToMs), Integer.valueOf(countLocked));
        }
    }

    private static void dumpTimer(ProtoOutputStream protoOutputStream, long j, Timer timer, long j2, int i) {
        if (timer == null) {
            return;
        }
        long jRoundUsToMs = roundUsToMs(timer.getTotalTimeLocked(j2, i));
        int countLocked = timer.getCountLocked(i);
        long j3 = j2 / 1000;
        long maxDurationMsLocked = timer.getMaxDurationMsLocked(j3);
        long currentDurationMsLocked = timer.getCurrentDurationMsLocked(j3);
        long totalDurationMsLocked = timer.getTotalDurationMsLocked(j3);
        if (jRoundUsToMs == 0 && countLocked == 0 && maxDurationMsLocked == -1 && currentDurationMsLocked == -1 && totalDurationMsLocked == -1) {
            return;
        }
        long jStart = protoOutputStream.start(j);
        protoOutputStream.write(1112396529665L, jRoundUsToMs);
        protoOutputStream.write(1112396529666L, countLocked);
        if (maxDurationMsLocked != -1) {
            protoOutputStream.write(1112396529667L, maxDurationMsLocked);
        }
        if (currentDurationMsLocked != -1) {
            protoOutputStream.write(1112396529668L, currentDurationMsLocked);
        }
        if (totalDurationMsLocked != -1) {
            protoOutputStream.write(1112396529669L, totalDurationMsLocked);
        }
        protoOutputStream.end(jStart);
    }

    private static boolean controllerActivityHasData(ControllerActivityCounter controllerActivityCounter, int i) {
        if (controllerActivityCounter == null) {
            return false;
        }
        if (controllerActivityCounter.getIdleTimeCounter().getCountLocked(i) != 0 || controllerActivityCounter.getRxTimeCounter().getCountLocked(i) != 0 || controllerActivityCounter.getPowerCounter().getCountLocked(i) != 0 || controllerActivityCounter.getMonitoredRailChargeConsumedMaMs().getCountLocked(i) != 0) {
            return true;
        }
        for (LongCounter longCounter : controllerActivityCounter.getTxTimeCounters()) {
            if (longCounter.getCountLocked(i) != 0) {
                return true;
            }
        }
        return false;
    }

    private static final void dumpControllerActivityLine(PrintWriter printWriter, int i, String str, String str2, ControllerActivityCounter controllerActivityCounter, int i2) {
        if (controllerActivityHasData(controllerActivityCounter, i2)) {
            dumpLineHeader(printWriter, i, str, str2);
            printWriter.print(",");
            printWriter.print(controllerActivityCounter.getIdleTimeCounter().getCountLocked(i2));
            printWriter.print(",");
            printWriter.print(controllerActivityCounter.getRxTimeCounter().getCountLocked(i2));
            printWriter.print(",");
            printWriter.print(controllerActivityCounter.getPowerCounter().getCountLocked(i2) / 3600000.0d);
            printWriter.print(",");
            printWriter.print(controllerActivityCounter.getMonitoredRailChargeConsumedMaMs().getCountLocked(i2) / 3600000.0d);
            LongCounter[] txTimeCounters = controllerActivityCounter.getTxTimeCounters();
            for (LongCounter longCounter : txTimeCounters) {
                printWriter.print(",");
                printWriter.print(longCounter.getCountLocked(i2));
            }
            printWriter.println();
        }
    }

    private static void dumpControllerActivityProto(ProtoOutputStream protoOutputStream, long j, ControllerActivityCounter controllerActivityCounter, int i) {
        if (controllerActivityHasData(controllerActivityCounter, i)) {
            long jStart = protoOutputStream.start(j);
            protoOutputStream.write(1112396529665L, controllerActivityCounter.getIdleTimeCounter().getCountLocked(i));
            protoOutputStream.write(1112396529666L, controllerActivityCounter.getRxTimeCounter().getCountLocked(i));
            protoOutputStream.write(1112396529667L, controllerActivityCounter.getPowerCounter().getCountLocked(i) / 3600000.0d);
            protoOutputStream.write(1103806595077L, controllerActivityCounter.getMonitoredRailChargeConsumedMaMs().getCountLocked(i) / 3600000.0d);
            LongCounter[] txTimeCounters = controllerActivityCounter.getTxTimeCounters();
            for (int i2 = 0; i2 < txTimeCounters.length; i2++) {
                LongCounter longCounter = txTimeCounters[i2];
                long jStart2 = protoOutputStream.start(2246267895812L);
                protoOutputStream.write(1120986464257L, i2);
                protoOutputStream.write(1112396529666L, longCounter.getCountLocked(i));
                protoOutputStream.end(jStart2);
            }
            protoOutputStream.end(jStart);
        }
    }

    private final void printControllerActivityIfInteresting(PrintWriter printWriter, StringBuilder sb, String str, String str2, ControllerActivityCounter controllerActivityCounter, int i) {
        if (controllerActivityHasData(controllerActivityCounter, i)) {
            printControllerActivity(printWriter, sb, str, str2, controllerActivityCounter, i);
        }
    }

    private final void printControllerActivity(PrintWriter printWriter, StringBuilder sb, String str, String str2, ControllerActivityCounter controllerActivityCounter, int i) {
        String str3;
        long j;
        Object obj;
        int i2;
        String[] strArr;
        long countLocked = controllerActivityCounter.getIdleTimeCounter().getCountLocked(i);
        long countLocked2 = controllerActivityCounter.getRxTimeCounter().getCountLocked(i);
        long countLocked3 = controllerActivityCounter.getPowerCounter().getCountLocked(i);
        long countLocked4 = controllerActivityCounter.getMonitoredRailChargeConsumedMaMs().getCountLocked(i);
        long jComputeBatteryRealtime = computeBatteryRealtime(SystemClock.elapsedRealtime() * 1000, i) / 1000;
        LongCounter[] txTimeCounters = controllerActivityCounter.getTxTimeCounters();
        long countLocked5 = 0;
        int i3 = 0;
        for (int length = txTimeCounters.length; i3 < length; length = length) {
            countLocked5 += txTimeCounters[i3].getCountLocked(i);
            i3++;
        }
        if (str2.equals(WIFI_CONTROLLER_NAME)) {
            long countLocked6 = controllerActivityCounter.getScanTimeCounter().getCountLocked(i);
            sb.setLength(0);
            sb.append(str);
            sb.append("     ");
            sb.append(str2);
            sb.append(" Scan time:  ");
            formatTimeMs(sb, countLocked6);
            sb.append("(");
            sb.append(formatRatioLocked(countLocked6, jComputeBatteryRealtime));
            sb.append(")");
            printWriter.println(sb.toString());
            long j2 = jComputeBatteryRealtime - ((countLocked + countLocked2) + countLocked5);
            sb.setLength(0);
            sb.append(str);
            sb.append("     ");
            sb.append(str2);
            str3 = " Sleep time:  ";
            sb.append(str3);
            formatTimeMs(sb, j2);
            sb.append("(");
            sb.append(formatRatioLocked(j2, jComputeBatteryRealtime));
            sb.append(")");
            printWriter.println(sb.toString());
        } else {
            str3 = " Sleep time:  ";
        }
        if (str2.equals(CELLULAR_CONTROLLER_NAME)) {
            LongCounter sleepTimeCounter = controllerActivityCounter.getSleepTimeCounter();
            j = countLocked2;
            obj = CELLULAR_CONTROLLER_NAME;
            i2 = i;
            long countLocked7 = sleepTimeCounter.getCountLocked(i2);
            sb.setLength(0);
            sb.append(str);
            sb.append("     ");
            sb.append(str2);
            sb.append(str3);
            formatTimeMs(sb, countLocked7);
            sb.append("(");
            sb.append(formatRatioLocked(countLocked7, jComputeBatteryRealtime));
            sb.append(")");
            printWriter.println(sb.toString());
        } else {
            j = countLocked2;
            obj = CELLULAR_CONTROLLER_NAME;
            i2 = i;
        }
        sb.setLength(0);
        sb.append(str);
        sb.append("     ");
        sb.append(str2);
        sb.append(" Idle time:   ");
        formatTimeMs(sb, countLocked);
        sb.append("(");
        sb.append(formatRatioLocked(countLocked, jComputeBatteryRealtime));
        sb.append(")");
        printWriter.println(sb.toString());
        sb.setLength(0);
        sb.append(str);
        sb.append("     ");
        sb.append(str2);
        sb.append(" Rx time:     ");
        long j3 = j;
        formatTimeMs(sb, j3);
        sb.append("(");
        sb.append(formatRatioLocked(j3, jComputeBatteryRealtime));
        sb.append(")");
        printWriter.println(sb.toString());
        sb.setLength(0);
        sb.append(str);
        sb.append("     ");
        sb.append(str2);
        sb.append(" Tx time:     ");
        byte b = -1;
        if (str2.hashCode() == -851952246 && str2.equals(obj)) {
            b = 0;
        }
        if (b == 0) {
            strArr = new String[]{"   less than 0dBm: ", "   0dBm to 8dBm: ", "   8dBm to 15dBm: ", "   15dBm to 20dBm: ", "   above 20dBm: "};
        } else {
            strArr = new String[]{"[0]", "[1]", "[2]", "[3]", "[4]"};
        }
        int iMin = Math.min(controllerActivityCounter.getTxTimeCounters().length, strArr.length);
        if (iMin > 1) {
            printWriter.println(sb.toString());
            for (int i4 = 0; i4 < iMin; i4++) {
                long countLocked8 = controllerActivityCounter.getTxTimeCounters()[i4].getCountLocked(i2);
                sb.setLength(0);
                sb.append(str);
                sb.append("    ");
                sb.append(strArr[i4]);
                sb.append(" ");
                formatTimeMs(sb, countLocked8);
                sb.append("(");
                sb.append(formatRatioLocked(countLocked8, jComputeBatteryRealtime));
                sb.append(")");
                printWriter.println(sb.toString());
            }
        } else {
            long countLocked9 = controllerActivityCounter.getTxTimeCounters()[0].getCountLocked(i2);
            formatTimeMs(sb, countLocked9);
            sb.append("(");
            sb.append(formatRatioLocked(countLocked9, jComputeBatteryRealtime));
            sb.append(")");
            printWriter.println(sb.toString());
        }
        if (countLocked3 > 0) {
            sb.setLength(0);
            sb.append(str);
            sb.append("     ");
            sb.append(str2);
            sb.append(" Battery drain: ");
            sb.append(BatteryStatsHelper.makemAh(countLocked3 / 3600000.0d));
            sb.append("mAh");
            printWriter.println(sb.toString());
        }
        if (countLocked4 > 0) {
            sb.setLength(0);
            sb.append(str);
            sb.append("     ");
            sb.append(str2);
            sb.append(" Monitored rail energy drain: ");
            sb.append(new DecimalFormat("#.##").format(countLocked4 / 3600000.0d));
            sb.append(" mAh");
            printWriter.println(sb.toString());
        }
    }

    public final void dumpCheckinLocked(Context context, PrintWriter printWriter, int i, int i2) {
        dumpCheckinLocked(context, printWriter, i, i2, BatteryStatsHelper.checkWifiOnly(context));
    }

    /* JADX WARN: Removed duplicated region for block: B:224:0x0b76  */
    /* JADX WARN: Removed duplicated region for block: B:370:0x108c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void dumpCheckinLocked(android.content.Context r94, java.io.PrintWriter r95, int r96, int r97, boolean r98) {
        /*
            Method dump skipped, instruction units count: 4832
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.os.BatteryStats.dumpCheckinLocked(android.content.Context, java.io.PrintWriter, int, int, boolean):void");
    }

    /* JADX INFO: renamed from: android.os.BatteryStats$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$android$internal$os$BatterySipper$DrainType;

        static {
            int[] iArr = new int[BatterySipper.DrainType.values().length];
            $SwitchMap$com$android$internal$os$BatterySipper$DrainType = iArr;
            try {
                iArr[BatterySipper.DrainType.AMBIENT_DISPLAY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$android$internal$os$BatterySipper$DrainType[BatterySipper.DrainType.IDLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$android$internal$os$BatterySipper$DrainType[BatterySipper.DrainType.CELL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$android$internal$os$BatterySipper$DrainType[BatterySipper.DrainType.PHONE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$android$internal$os$BatterySipper$DrainType[BatterySipper.DrainType.WIFI.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$android$internal$os$BatterySipper$DrainType[BatterySipper.DrainType.BLUETOOTH.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$android$internal$os$BatterySipper$DrainType[BatterySipper.DrainType.SCREEN.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$android$internal$os$BatterySipper$DrainType[BatterySipper.DrainType.FLASHLIGHT.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$android$internal$os$BatterySipper$DrainType[BatterySipper.DrainType.APP.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$android$internal$os$BatterySipper$DrainType[BatterySipper.DrainType.USER.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$android$internal$os$BatterySipper$DrainType[BatterySipper.DrainType.UNACCOUNTED.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$android$internal$os$BatterySipper$DrainType[BatterySipper.DrainType.OVERCOUNTED.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$android$internal$os$BatterySipper$DrainType[BatterySipper.DrainType.CAMERA.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$android$internal$os$BatterySipper$DrainType[BatterySipper.DrainType.MEMORY.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
        }
    }

    static final class TimerEntry {
        final int mId;
        final String mName;
        final long mTime;
        final Timer mTimer;

        TimerEntry(String str, int i, Timer timer, long j) {
            this.mName = str;
            this.mId = i;
            this.mTimer = timer;
            this.mTime = j;
        }
    }

    private void printmAh(PrintWriter printWriter, double d) {
        printWriter.print(BatteryStatsHelper.makemAh(d));
    }

    private void printmAh(StringBuilder sb, double d) {
        sb.append(BatteryStatsHelper.makemAh(d));
    }

    public final void dumpLocked(Context context, PrintWriter printWriter, String str, int i, int i2) {
        dumpLocked(context, printWriter, str, i, i2, BatteryStatsHelper.checkWifiOnly(context));
    }

    /* JADX WARN: Removed duplicated region for block: B:458:0x177f  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x05e3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void dumpLocked(android.content.Context r106, java.io.PrintWriter r107, java.lang.String r108, int r109, int r110, boolean r111) {
        /*
            Method dump skipped, instruction units count: 8588
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.os.BatteryStats.dumpLocked(android.content.Context, java.io.PrintWriter, java.lang.String, int, int, boolean):void");
    }

    static void printBitDescriptions(StringBuilder sb, int i, int i2, HistoryTag historyTag, BitDescription[] bitDescriptionArr, boolean z) {
        int i3 = i ^ i2;
        if (i3 == 0) {
            return;
        }
        boolean z2 = false;
        for (BitDescription bitDescription : bitDescriptionArr) {
            if ((bitDescription.mask & i3) != 0) {
                sb.append(z ? " " : ",");
                if (bitDescription.shift < 0) {
                    sb.append((bitDescription.mask & i2) != 0 ? "+" : NativeLibraryHelper.CLEAR_ABI_OVERRIDE);
                    sb.append(z ? bitDescription.name : bitDescription.shortName);
                    if (bitDescription.mask == 1073741824 && historyTag != null) {
                        sb.append("=");
                        if (z) {
                            UserHandle.formatUid(sb, historyTag.uid);
                            sb.append(":\"");
                            sb.append(historyTag.string);
                            sb.append("\"");
                        } else {
                            sb.append(historyTag.poolIdx);
                        }
                        z2 = true;
                    }
                } else {
                    sb.append(z ? bitDescription.name : bitDescription.shortName);
                    sb.append("=");
                    int i4 = (bitDescription.mask & i2) >> bitDescription.shift;
                    if (bitDescription.values != null && i4 >= 0 && i4 < bitDescription.values.length) {
                        sb.append(z ? bitDescription.values[i4] : bitDescription.shortValues[i4]);
                    } else {
                        sb.append(i4);
                    }
                }
            }
        }
        if (z2 || historyTag == null) {
            return;
        }
        sb.append(z ? " wake_lock=" : ",w=");
        if (z) {
            UserHandle.formatUid(sb, historyTag.uid);
            sb.append(":\"");
            sb.append(historyTag.string);
            sb.append("\"");
            return;
        }
        sb.append(historyTag.poolIdx);
    }

    public static class HistoryPrinter {
        int oldState = 0;
        int oldState2 = 0;
        int oldLevel = -1;
        int oldStatus = -1;
        int oldHealth = -1;
        int oldPlug = -1;
        int oldTemp = -1;
        int oldVolt = -1;
        int oldChargeMAh = -1;
        double oldModemRailChargeMah = -1.0d;
        double oldWifiRailChargeMah = -1.0d;
        long lastTime = -1;

        void reset() {
            this.oldState2 = 0;
            this.oldState = 0;
            this.oldLevel = -1;
            this.oldStatus = -1;
            this.oldHealth = -1;
            this.oldPlug = -1;
            this.oldTemp = -1;
            this.oldVolt = -1;
            this.oldChargeMAh = -1;
            this.oldModemRailChargeMah = -1.0d;
            this.oldWifiRailChargeMah = -1.0d;
        }

        public void printNextItem(PrintWriter printWriter, HistoryItem historyItem, long j, boolean z, boolean z2) {
            printWriter.print(printNextItem(historyItem, j, z, z2));
        }

        public void printNextItem(ProtoOutputStream protoOutputStream, HistoryItem historyItem, long j, boolean z) {
            for (String str : printNextItem(historyItem, j, true, z).split(BydAdbHelper.COMMAND_LINE_END)) {
                protoOutputStream.write(2237677961222L, str);
            }
        }

        private String printNextItem(HistoryItem historyItem, long j, boolean z, boolean z2) {
            String[] strArr;
            StringBuilder sb = new StringBuilder();
            if (!z) {
                sb.append("  ");
                TimeUtils.formatDuration(historyItem.time - j, sb, 19);
                sb.append(" (");
                sb.append(historyItem.numReadInts);
                sb.append(") ");
            } else {
                sb.append(9);
                sb.append(',');
                sb.append(BatteryStats.HISTORY_DATA);
                sb.append(',');
                if (this.lastTime < 0) {
                    sb.append(historyItem.time - j);
                } else {
                    sb.append(historyItem.time - this.lastTime);
                }
                this.lastTime = historyItem.time;
            }
            if (historyItem.cmd == 4) {
                if (z) {
                    sb.append(SettingsStringUtil.DELIMITER);
                }
                sb.append("START\n");
                reset();
            } else {
                if (historyItem.cmd == 5 || historyItem.cmd == 7) {
                    if (z) {
                        sb.append(SettingsStringUtil.DELIMITER);
                    }
                    if (historyItem.cmd == 7) {
                        sb.append("RESET:");
                        reset();
                    }
                    sb.append("TIME:");
                    if (z) {
                        sb.append(historyItem.currentTime);
                        sb.append(BydAdbHelper.COMMAND_LINE_END);
                    } else {
                        sb.append(" ");
                        sb.append(DateFormat.format("yyyy-MM-dd-HH-mm-ss", historyItem.currentTime).toString());
                        sb.append(BydAdbHelper.COMMAND_LINE_END);
                    }
                } else if (historyItem.cmd == 8) {
                    if (z) {
                        sb.append(SettingsStringUtil.DELIMITER);
                    }
                    sb.append("SHUTDOWN\n");
                } else if (historyItem.cmd == 6) {
                    if (z) {
                        sb.append(SettingsStringUtil.DELIMITER);
                    }
                    sb.append("*OVERFLOW*\n");
                } else {
                    if (!z) {
                        if (historyItem.batteryLevel < 10) {
                            sb.append("00");
                        } else if (historyItem.batteryLevel < 100) {
                            sb.append(AudioSystem.LEGACY_REMOTE_SUBMIX_ADDRESS);
                        }
                        sb.append(historyItem.batteryLevel);
                        if (z2) {
                            sb.append(" ");
                            if (historyItem.states >= 0) {
                                if (historyItem.states < 16) {
                                    sb.append("0000000");
                                } else if (historyItem.states < 256) {
                                    sb.append("000000");
                                } else if (historyItem.states < 4096) {
                                    sb.append("00000");
                                } else if (historyItem.states < 65536) {
                                    sb.append("0000");
                                } else if (historyItem.states < 1048576) {
                                    sb.append("000");
                                } else if (historyItem.states < 16777216) {
                                    sb.append("00");
                                } else if (historyItem.states < 268435456) {
                                    sb.append(AudioSystem.LEGACY_REMOTE_SUBMIX_ADDRESS);
                                }
                            }
                            sb.append(Integer.toHexString(historyItem.states));
                        }
                    } else if (this.oldLevel != historyItem.batteryLevel) {
                        this.oldLevel = historyItem.batteryLevel;
                        sb.append(",Bl=");
                        sb.append(historyItem.batteryLevel);
                    }
                    int i = this.oldStatus;
                    byte b = historyItem.batteryStatus;
                    String str = FullBackup.FILES_TREE_TOKEN;
                    String str2 = XmlTags.ATTR_DESCRIPTION;
                    if (i != b) {
                        this.oldStatus = historyItem.batteryStatus;
                        sb.append(z ? ",Bs=" : " status=");
                        int i2 = this.oldStatus;
                        if (i2 == 1) {
                            sb.append(z ? "?" : "unknown");
                        } else if (i2 == 2) {
                            sb.append(z ? "c" : "charging");
                        } else if (i2 == 3) {
                            sb.append(z ? XmlTags.ATTR_DESCRIPTION : "discharging");
                        } else if (i2 == 4) {
                            sb.append(z ? "n" : "not-charging");
                        } else if (i2 == 5) {
                            sb.append(z ? FullBackup.FILES_TREE_TOKEN : "full");
                        } else {
                            sb.append(i2);
                        }
                    }
                    if (this.oldHealth != historyItem.batteryHealth) {
                        this.oldHealth = historyItem.batteryHealth;
                        sb.append(z ? ",Bh=" : " health=");
                        int i3 = this.oldHealth;
                        switch (i3) {
                            case 1:
                                sb.append(z ? "?" : "unknown");
                                break;
                            case 2:
                                sb.append(z ? "g" : "good");
                                break;
                            case 3:
                                sb.append(z ? BatteryStats.HISTORY_DATA : "overheat");
                                break;
                            case 4:
                                if (!z) {
                                    str2 = "dead";
                                }
                                sb.append(str2);
                                break;
                            case 5:
                                sb.append(z ? "v" : "over-voltage");
                                break;
                            case 6:
                                if (!z) {
                                    str = "failure";
                                }
                                sb.append(str);
                                break;
                            case 7:
                                sb.append(z ? "c" : "cold");
                                break;
                            default:
                                sb.append(i3);
                                break;
                        }
                    }
                    if (this.oldPlug != historyItem.batteryPlugType) {
                        this.oldPlug = historyItem.batteryPlugType;
                        sb.append(z ? ",Bp=" : " plug=");
                        int i4 = this.oldPlug;
                        if (i4 == 0) {
                            sb.append(z ? "n" : "none");
                        } else if (i4 == 1) {
                            sb.append(z ? FullBackup.APK_TREE_TOKEN : "ac");
                        } else if (i4 == 2) {
                            sb.append(z ? XmlTags.ATTR_UID : Context.USB_SERVICE);
                        } else if (i4 == 4) {
                            sb.append(z ? "w" : "wireless");
                        } else {
                            sb.append(i4);
                        }
                    }
                    if (this.oldTemp != historyItem.batteryTemperature) {
                        this.oldTemp = historyItem.batteryTemperature;
                        sb.append(z ? ",Bt=" : " temp=");
                        sb.append(this.oldTemp);
                    }
                    if (this.oldVolt != historyItem.batteryVoltage) {
                        this.oldVolt = historyItem.batteryVoltage;
                        sb.append(z ? ",Bv=" : " volt=");
                        sb.append(this.oldVolt);
                    }
                    int i5 = historyItem.batteryChargeUAh / 1000;
                    if (this.oldChargeMAh != i5) {
                        this.oldChargeMAh = i5;
                        sb.append(z ? ",Bcc=" : " charge=");
                        sb.append(this.oldChargeMAh);
                    }
                    if (this.oldModemRailChargeMah != historyItem.modemRailChargeMah) {
                        this.oldModemRailChargeMah = historyItem.modemRailChargeMah;
                        sb.append(z ? ",Mrc=" : " modemRailChargemAh=");
                        sb.append(new DecimalFormat("#.##").format(this.oldModemRailChargeMah));
                    }
                    if (this.oldWifiRailChargeMah != historyItem.wifiRailChargeMah) {
                        this.oldWifiRailChargeMah = historyItem.wifiRailChargeMah;
                        sb.append(z ? ",Wrc=" : " wifiRailChargemAh=");
                        sb.append(new DecimalFormat("#.##").format(this.oldWifiRailChargeMah));
                    }
                    BatteryStats.printBitDescriptions(sb, this.oldState, historyItem.states, historyItem.wakelockTag, BatteryStats.HISTORY_STATE_DESCRIPTIONS, !z);
                    BatteryStats.printBitDescriptions(sb, this.oldState2, historyItem.states2, null, BatteryStats.HISTORY_STATE2_DESCRIPTIONS, !z);
                    if (historyItem.wakeReasonTag != null) {
                        if (z) {
                            sb.append(",wr=");
                            sb.append(historyItem.wakeReasonTag.poolIdx);
                        } else {
                            sb.append(" wake_reason=");
                            sb.append(historyItem.wakeReasonTag.uid);
                            sb.append(":\"");
                            sb.append(historyItem.wakeReasonTag.string);
                            sb.append("\"");
                        }
                    }
                    if (historyItem.eventCode != 0) {
                        sb.append(z ? "," : " ");
                        if ((historyItem.eventCode & 32768) != 0) {
                            sb.append("+");
                        } else if ((historyItem.eventCode & 16384) != 0) {
                            sb.append(NativeLibraryHelper.CLEAR_ABI_OVERRIDE);
                        }
                        if (z) {
                            strArr = BatteryStats.HISTORY_EVENT_CHECKIN_NAMES;
                        } else {
                            strArr = BatteryStats.HISTORY_EVENT_NAMES;
                        }
                        int i6 = historyItem.eventCode & HistoryItem.EVENT_TYPE_MASK;
                        if (i6 >= 0 && i6 < strArr.length) {
                            sb.append(strArr[i6]);
                        } else {
                            sb.append(z ? "Ev" : "event");
                            sb.append(i6);
                        }
                        sb.append("=");
                        if (z) {
                            sb.append(historyItem.eventTag.poolIdx);
                        } else {
                            sb.append(BatteryStats.HISTORY_EVENT_INT_FORMATTERS[i6].applyAsString(historyItem.eventTag.uid));
                            sb.append(":\"");
                            sb.append(historyItem.eventTag.string);
                            sb.append("\"");
                        }
                    }
                    sb.append(BydAdbHelper.COMMAND_LINE_END);
                    if (historyItem.stepDetails != null) {
                        if (!z) {
                            sb.append("                 Details: cpu=");
                            sb.append(historyItem.stepDetails.userTime);
                            sb.append("u+");
                            sb.append(historyItem.stepDetails.systemTime);
                            sb.append(XmlTags.TAG_SESSION);
                            if (historyItem.stepDetails.appCpuUid1 >= 0) {
                                sb.append(" (");
                                printStepCpuUidDetails(sb, historyItem.stepDetails.appCpuUid1, historyItem.stepDetails.appCpuUTime1, historyItem.stepDetails.appCpuSTime1);
                                if (historyItem.stepDetails.appCpuUid2 >= 0) {
                                    sb.append(", ");
                                    printStepCpuUidDetails(sb, historyItem.stepDetails.appCpuUid2, historyItem.stepDetails.appCpuUTime2, historyItem.stepDetails.appCpuSTime2);
                                }
                                if (historyItem.stepDetails.appCpuUid3 >= 0) {
                                    sb.append(", ");
                                    printStepCpuUidDetails(sb, historyItem.stepDetails.appCpuUid3, historyItem.stepDetails.appCpuUTime3, historyItem.stepDetails.appCpuSTime3);
                                }
                                sb.append(')');
                            }
                            sb.append(BydAdbHelper.COMMAND_LINE_END);
                            sb.append("                          /proc/stat=");
                            sb.append(historyItem.stepDetails.statUserTime);
                            sb.append(" usr, ");
                            sb.append(historyItem.stepDetails.statSystemTime);
                            sb.append(" sys, ");
                            sb.append(historyItem.stepDetails.statIOWaitTime);
                            sb.append(" io, ");
                            sb.append(historyItem.stepDetails.statIrqTime);
                            sb.append(" irq, ");
                            sb.append(historyItem.stepDetails.statSoftIrqTime);
                            sb.append(" sirq, ");
                            sb.append(historyItem.stepDetails.statIdlTime);
                            sb.append(" idle");
                            int i7 = historyItem.stepDetails.statUserTime + historyItem.stepDetails.statSystemTime + historyItem.stepDetails.statIOWaitTime + historyItem.stepDetails.statIrqTime + historyItem.stepDetails.statSoftIrqTime;
                            int i8 = historyItem.stepDetails.statIdlTime + i7;
                            if (i8 > 0) {
                                sb.append(" (");
                                sb.append(String.format("%.1f%%", Float.valueOf((i7 / i8) * 100.0f)));
                                sb.append(" of ");
                                StringBuilder sb2 = new StringBuilder(64);
                                BatteryStats.formatTimeMsNoSpace(sb2, i8 * 10);
                                sb.append((CharSequence) sb2);
                                sb.append(")");
                            }
                            sb.append(", PlatformIdleStat ");
                            sb.append(historyItem.stepDetails.statPlatformIdleState);
                            sb.append(BydAdbHelper.COMMAND_LINE_END);
                            sb.append(", SubsystemPowerState ");
                            sb.append(historyItem.stepDetails.statSubsystemPowerState);
                            sb.append(BydAdbHelper.COMMAND_LINE_END);
                        } else {
                            sb.append(9);
                            sb.append(',');
                            sb.append(BatteryStats.HISTORY_DATA);
                            sb.append(",0,Dcpu=");
                            sb.append(historyItem.stepDetails.userTime);
                            sb.append(SettingsStringUtil.DELIMITER);
                            sb.append(historyItem.stepDetails.systemTime);
                            if (historyItem.stepDetails.appCpuUid1 >= 0) {
                                printStepCpuUidCheckinDetails(sb, historyItem.stepDetails.appCpuUid1, historyItem.stepDetails.appCpuUTime1, historyItem.stepDetails.appCpuSTime1);
                                if (historyItem.stepDetails.appCpuUid2 >= 0) {
                                    printStepCpuUidCheckinDetails(sb, historyItem.stepDetails.appCpuUid2, historyItem.stepDetails.appCpuUTime2, historyItem.stepDetails.appCpuSTime2);
                                }
                                if (historyItem.stepDetails.appCpuUid3 >= 0) {
                                    printStepCpuUidCheckinDetails(sb, historyItem.stepDetails.appCpuUid3, historyItem.stepDetails.appCpuUTime3, historyItem.stepDetails.appCpuSTime3);
                                }
                            }
                            sb.append(BydAdbHelper.COMMAND_LINE_END);
                            sb.append(9);
                            sb.append(',');
                            sb.append(BatteryStats.HISTORY_DATA);
                            sb.append(",0,Dpst=");
                            sb.append(historyItem.stepDetails.statUserTime);
                            sb.append(',');
                            sb.append(historyItem.stepDetails.statSystemTime);
                            sb.append(',');
                            sb.append(historyItem.stepDetails.statIOWaitTime);
                            sb.append(',');
                            sb.append(historyItem.stepDetails.statIrqTime);
                            sb.append(',');
                            sb.append(historyItem.stepDetails.statSoftIrqTime);
                            sb.append(',');
                            sb.append(historyItem.stepDetails.statIdlTime);
                            sb.append(',');
                            if (historyItem.stepDetails.statPlatformIdleState != null) {
                                sb.append(historyItem.stepDetails.statPlatformIdleState);
                                if (historyItem.stepDetails.statSubsystemPowerState != null) {
                                    sb.append(',');
                                }
                            }
                            if (historyItem.stepDetails.statSubsystemPowerState != null) {
                                sb.append(historyItem.stepDetails.statSubsystemPowerState);
                            }
                            sb.append(BydAdbHelper.COMMAND_LINE_END);
                        }
                    }
                    this.oldState = historyItem.states;
                    this.oldState2 = historyItem.states2;
                    if ((historyItem.states2 & 524288) != 0) {
                        historyItem.states2 &= -524289;
                    }
                }
            }
            return sb.toString();
        }

        private void printStepCpuUidDetails(StringBuilder sb, int i, int i2, int i3) {
            UserHandle.formatUid(sb, i);
            sb.append("=");
            sb.append(i2);
            sb.append("u+");
            sb.append(i3);
            sb.append(XmlTags.TAG_SESSION);
        }

        private void printStepCpuUidCheckinDetails(StringBuilder sb, int i, int i2, int i3) {
            sb.append('/');
            sb.append(i);
            sb.append(SettingsStringUtil.DELIMITER);
            sb.append(i2);
            sb.append(SettingsStringUtil.DELIMITER);
            sb.append(i3);
        }
    }

    private void printSizeValue(PrintWriter printWriter, long j) {
        String str;
        float f = j;
        if (f >= 10240.0f) {
            f /= 1024.0f;
            str = "KB";
        } else {
            str = "";
        }
        if (f >= 10240.0f) {
            f /= 1024.0f;
            str = "MB";
        }
        if (f >= 10240.0f) {
            f /= 1024.0f;
            str = "GB";
        }
        if (f >= 10240.0f) {
            f /= 1024.0f;
            str = "TB";
        }
        if (f >= 10240.0f) {
            f /= 1024.0f;
            str = "PB";
        }
        printWriter.print((int) f);
        printWriter.print(str);
    }

    private static boolean dumpTimeEstimate(PrintWriter printWriter, String str, String str2, String str3, long j) {
        if (j < 0) {
            return false;
        }
        printWriter.print(str);
        printWriter.print(str2);
        printWriter.print(str3);
        StringBuilder sb = new StringBuilder(64);
        formatTimeMs(sb, j);
        printWriter.print(sb);
        printWriter.println();
        return true;
    }

    private static boolean dumpDurationSteps(PrintWriter printWriter, String str, String str2, LevelStepTracker levelStepTracker, boolean z) {
        int i;
        int i2;
        char c;
        char c2 = 0;
        if (levelStepTracker == null || (i = levelStepTracker.mNumStepDurations) <= 0) {
            return false;
        }
        if (!z) {
            printWriter.println(str2);
        }
        String[] strArr = new String[5];
        int i3 = 0;
        while (true) {
            char c3 = 1;
            if (i3 >= i) {
                return true;
            }
            long durationAt = levelStepTracker.getDurationAt(i3);
            int levelAt = levelStepTracker.getLevelAt(i3);
            long initModeAt = levelStepTracker.getInitModeAt(i3);
            long modModeAt = levelStepTracker.getModModeAt(i3);
            if (z) {
                strArr[c2] = Long.toString(durationAt);
                strArr[1] = Integer.toString(levelAt);
                if ((modModeAt & 3) == 0) {
                    i2 = i;
                    int i4 = ((int) (initModeAt & 3)) + 1;
                    if (i4 == 1) {
                        strArr[2] = "s-";
                    } else if (i4 == 2) {
                        strArr[2] = "s+";
                    } else if (i4 == 3) {
                        strArr[2] = "sd";
                    } else if (i4 != 4) {
                        strArr[2] = "?";
                    } else {
                        strArr[2] = "sds";
                    }
                } else {
                    i2 = i;
                    strArr[2] = "";
                }
                if ((modModeAt & 4) == 0) {
                    strArr[3] = (initModeAt & 4) != 0 ? "p+" : "p-";
                } else {
                    strArr[3] = "";
                }
                if ((modModeAt & 8) == 0) {
                    strArr[4] = (8 & initModeAt) != 0 ? "i+" : "i-";
                } else {
                    strArr[4] = "";
                }
                dumpLine(printWriter, 0, "i", str2, strArr);
                c2 = 0;
            } else {
                i2 = i;
                printWriter.print(str);
                printWriter.print("#");
                printWriter.print(i3);
                printWriter.print(": ");
                TimeUtils.formatDuration(durationAt, printWriter);
                printWriter.print(" to ");
                printWriter.print(levelAt);
                if ((modModeAt & 3) == 0) {
                    printWriter.print(" (");
                    int i5 = ((int) (initModeAt & 3)) + 1;
                    if (i5 == 1) {
                        printWriter.print("screen-off");
                    } else if (i5 == 2) {
                        printWriter.print("screen-on");
                    } else if (i5 == 3) {
                        printWriter.print("screen-doze");
                    } else if (i5 == 4) {
                        printWriter.print("screen-doze-suspend");
                    } else {
                        printWriter.print("screen-?");
                    }
                    c = 1;
                } else {
                    c = c2;
                }
                if ((modModeAt & 4) == 0) {
                    printWriter.print(c != 0 ? ", " : " (");
                    printWriter.print((initModeAt & 4) != 0 ? "power-save-on" : "power-save-off");
                    c = 1;
                }
                if ((modModeAt & 8) == 0) {
                    printWriter.print(c != 0 ? ", " : " (");
                    printWriter.print((initModeAt & 8) != 0 ? "device-idle-on" : "device-idle-off");
                } else {
                    c3 = c;
                }
                if (c3 != 0) {
                    printWriter.print(")");
                }
                printWriter.println();
            }
            i3++;
            i = i2;
        }
    }

    private static void dumpDurationSteps(ProtoOutputStream protoOutputStream, long j, LevelStepTracker levelStepTracker) {
        int i;
        if (levelStepTracker == null) {
            return;
        }
        int i2 = levelStepTracker.mNumStepDurations;
        for (int i3 = 0; i3 < i2; i3++) {
            long jStart = protoOutputStream.start(j);
            protoOutputStream.write(1112396529665L, levelStepTracker.getDurationAt(i3));
            protoOutputStream.write(1120986464258L, levelStepTracker.getLevelAt(i3));
            long initModeAt = levelStepTracker.getInitModeAt(i3);
            long modModeAt = levelStepTracker.getModModeAt(i3);
            int i4 = 3;
            if ((modModeAt & 3) == 0) {
                int i5 = ((int) (3 & initModeAt)) + 1;
                if (i5 == 1) {
                    i = 2;
                } else if (i5 == 2) {
                    i = 1;
                } else if (i5 != 3) {
                    i = 4;
                    if (i5 != 4) {
                        i = 5;
                    }
                } else {
                    i = 3;
                }
            } else {
                i = 0;
            }
            protoOutputStream.write(1159641169923L, i);
            protoOutputStream.write(1159641169924L, (modModeAt & 4) == 0 ? (4 & initModeAt) != 0 ? 1 : 2 : 0);
            if ((modModeAt & 8) != 0) {
                i4 = 0;
            } else if ((initModeAt & 8) != 0) {
                i4 = 2;
            }
            protoOutputStream.write(1159641169925L, i4);
            protoOutputStream.end(jStart);
        }
    }

    private void dumpHistoryLocked(PrintWriter printWriter, int i, long j, boolean z) {
        HistoryPrinter historyPrinter = new HistoryPrinter();
        HistoryItem historyItem = new HistoryItem();
        long j2 = -1;
        long j3 = -1;
        boolean z2 = false;
        while (getNextHistoryLocked(historyItem)) {
            long j4 = historyItem.time;
            long j5 = j2 < 0 ? j4 : j2;
            if (historyItem.time >= j) {
                if (j >= 0 && !z2) {
                    if (historyItem.cmd == 5 || historyItem.cmd == 7 || historyItem.cmd == 4 || historyItem.cmd == 8) {
                        historyPrinter.printNextItem(printWriter, historyItem, j5, z, (i & 32) != 0);
                        historyItem.cmd = (byte) 0;
                    } else if (historyItem.currentTime != 0) {
                        byte b = historyItem.cmd;
                        historyItem.cmd = (byte) 5;
                        historyPrinter.printNextItem(printWriter, historyItem, j5, z, (i & 32) != 0);
                        historyItem.cmd = b;
                    }
                    z2 = true;
                }
                boolean z3 = z2;
                historyPrinter.printNextItem(printWriter, historyItem, j5, z, (i & 32) != 0);
                z2 = z3;
            }
            j3 = j4;
            j2 = j5;
        }
        if (j >= 0) {
            commitCurrentHistoryBatchLocked();
            printWriter.print(z ? "NEXT: " : "  NEXT: ");
            printWriter.println(j3 + 1);
        }
    }

    private void dumpDailyLevelStepSummary(PrintWriter printWriter, String str, String str2, LevelStepTracker levelStepTracker, StringBuilder sb, int[] iArr) {
        if (levelStepTracker == null) {
            return;
        }
        long jComputeTimeEstimate = levelStepTracker.computeTimeEstimate(0L, 0L, iArr);
        if (jComputeTimeEstimate >= 0) {
            printWriter.print(str);
            printWriter.print(str2);
            printWriter.print(" total time: ");
            sb.setLength(0);
            formatTimeMs(sb, jComputeTimeEstimate);
            printWriter.print(sb);
            printWriter.print(" (from ");
            printWriter.print(iArr[0]);
            printWriter.println(" steps)");
        }
        int i = 0;
        while (true) {
            if (i >= STEP_LEVEL_MODES_OF_INTEREST.length) {
                return;
            }
            long jComputeTimeEstimate2 = levelStepTracker.computeTimeEstimate(r3[i], STEP_LEVEL_MODE_VALUES[i], iArr);
            if (jComputeTimeEstimate2 > 0) {
                printWriter.print(str);
                printWriter.print(str2);
                printWriter.print(" ");
                printWriter.print(STEP_LEVEL_MODE_LABELS[i]);
                printWriter.print(" time: ");
                sb.setLength(0);
                formatTimeMs(sb, jComputeTimeEstimate2);
                printWriter.print(sb);
                printWriter.print(" (from ");
                printWriter.print(iArr[0]);
                printWriter.println(" steps)");
            }
            i++;
        }
    }

    private void dumpDailyPackageChanges(PrintWriter printWriter, String str, ArrayList<PackageChange> arrayList) {
        if (arrayList == null) {
            return;
        }
        printWriter.print(str);
        printWriter.println("Package changes:");
        for (int i = 0; i < arrayList.size(); i++) {
            PackageChange packageChange = arrayList.get(i);
            if (packageChange.mUpdate) {
                printWriter.print(str);
                printWriter.print("  Update ");
                printWriter.print(packageChange.mPackageName);
                printWriter.print(" vers=");
                printWriter.println(packageChange.mVersionCode);
            } else {
                printWriter.print(str);
                printWriter.print("  Uninstall ");
                printWriter.println(packageChange.mPackageName);
            }
        }
    }

    public void dumpLocked(Context context, PrintWriter printWriter, int i, int i2, long j) {
        boolean z;
        String str;
        ArrayList<PackageChange> arrayList;
        boolean z2;
        String str2;
        boolean z3;
        long j2;
        prepareForDumpLocked();
        boolean z4 = (i & 14) != 0;
        if ((i & 8) != 0 || !z4) {
            long historyTotalSize = getHistoryTotalSize();
            long historyUsedSize = getHistoryUsedSize();
            if (startIteratingHistoryLocked()) {
                try {
                    printWriter.print("Battery History (");
                    printWriter.print((100 * historyUsedSize) / historyTotalSize);
                    printWriter.print("% used, ");
                    printSizeValue(printWriter, historyUsedSize);
                    printWriter.print(" used of ");
                    printSizeValue(printWriter, historyTotalSize);
                    printWriter.print(", ");
                    printWriter.print(getHistoryStringPoolSize());
                    printWriter.print(" strings using ");
                    printSizeValue(printWriter, getHistoryStringPoolBytes());
                    printWriter.println("):");
                    dumpHistoryLocked(printWriter, i, j, false);
                    printWriter.println();
                } finally {
                    finishIteratingHistoryLocked();
                }
            }
            if (startIteratingOldHistoryLocked()) {
                try {
                    HistoryItem historyItem = new HistoryItem();
                    printWriter.println("Old battery History:");
                    HistoryPrinter historyPrinter = new HistoryPrinter();
                    long j3 = -1;
                    while (getNextOldHistoryLocked(historyItem)) {
                        if (j3 < 0) {
                            j3 = historyItem.time;
                        }
                        long j4 = j3;
                        historyPrinter.printNextItem(printWriter, historyItem, j4, false, (i & 32) != 0);
                        j3 = j4;
                    }
                    printWriter.println();
                } finally {
                    finishIteratingOldHistoryLocked();
                }
            }
        }
        if (z4 && (i & 6) == 0) {
            return;
        }
        if (z4) {
            z = z4;
        } else {
            SparseArray<? extends Uid> uidStats = getUidStats();
            int size = uidStats.size();
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            int i3 = 0;
            boolean z5 = false;
            while (i3 < size) {
                SparseArray<? extends Uid.Pid> pidStats = uidStats.valueAt(i3).getPidStats();
                if (pidStats != null) {
                    int i4 = 0;
                    while (i4 < pidStats.size()) {
                        Uid.Pid pidValueAt = pidStats.valueAt(i4);
                        if (!z5) {
                            printWriter.println("Per-PID Stats:");
                            z5 = true;
                        }
                        long j5 = pidValueAt.mWakeSumMs;
                        if (pidValueAt.mWakeNesting > 0) {
                            z3 = z4;
                            j2 = jElapsedRealtime - pidValueAt.mWakeStartMs;
                        } else {
                            z3 = z4;
                            j2 = 0;
                        }
                        printWriter.print("  PID ");
                        printWriter.print(pidStats.keyAt(i4));
                        printWriter.print(" wake time: ");
                        TimeUtils.formatDuration(j5 + j2, printWriter);
                        printWriter.println("");
                        i4++;
                        z4 = z3;
                    }
                }
                i3++;
                z4 = z4;
            }
            z = z4;
            if (z5) {
                printWriter.println();
            }
        }
        if (!z || (i & 2) != 0) {
            if (dumpDurationSteps(printWriter, "  ", "Discharge step durations:", getDischargeLevelStepTracker(), false)) {
                long jComputeBatteryTimeRemaining = computeBatteryTimeRemaining(SystemClock.elapsedRealtime() * 1000);
                if (jComputeBatteryTimeRemaining >= 0) {
                    printWriter.print("  Estimated discharge time remaining: ");
                    TimeUtils.formatDuration(jComputeBatteryTimeRemaining / 1000, printWriter);
                    printWriter.println();
                }
                LevelStepTracker dischargeLevelStepTracker = getDischargeLevelStepTracker();
                int i5 = 0;
                while (true) {
                    if (i5 >= STEP_LEVEL_MODES_OF_INTEREST.length) {
                        break;
                    }
                    dumpTimeEstimate(printWriter, "  Estimated ", STEP_LEVEL_MODE_LABELS[i5], " time: ", dischargeLevelStepTracker.computeTimeEstimate(r1[i5], STEP_LEVEL_MODE_VALUES[i5], null));
                    i5++;
                }
                printWriter.println();
            }
            if (dumpDurationSteps(printWriter, "  ", "Charge step durations:", getChargeLevelStepTracker(), false)) {
                long jComputeChargeTimeRemaining = computeChargeTimeRemaining(SystemClock.elapsedRealtime() * 1000);
                if (jComputeChargeTimeRemaining >= 0) {
                    printWriter.print("  Estimated charge time remaining: ");
                    TimeUtils.formatDuration(jComputeChargeTimeRemaining / 1000, printWriter);
                    printWriter.println();
                }
                printWriter.println();
            }
        }
        if (z && (i & 4) == 0) {
            z2 = false;
        } else {
            printWriter.println("Daily stats:");
            printWriter.print("  Current start time: ");
            printWriter.println(DateFormat.format("yyyy-MM-dd-HH-mm-ss", getCurrentDailyStartTime()).toString());
            printWriter.print("  Next min deadline: ");
            printWriter.println(DateFormat.format("yyyy-MM-dd-HH-mm-ss", getNextMinDailyDeadline()).toString());
            printWriter.print("  Next max deadline: ");
            printWriter.println(DateFormat.format("yyyy-MM-dd-HH-mm-ss", getNextMaxDailyDeadline()).toString());
            StringBuilder sb = new StringBuilder(64);
            int[] iArr = new int[1];
            LevelStepTracker dailyDischargeLevelStepTracker = getDailyDischargeLevelStepTracker();
            LevelStepTracker dailyChargeLevelStepTracker = getDailyChargeLevelStepTracker();
            ArrayList<PackageChange> dailyPackageChanges = getDailyPackageChanges();
            if (dailyDischargeLevelStepTracker.mNumStepDurations <= 0 && dailyChargeLevelStepTracker.mNumStepDurations <= 0 && dailyPackageChanges == null) {
                str = "    ";
            } else if ((i & 4) != 0 || !z) {
                str = "    ";
                if (dumpDurationSteps(printWriter, str, "  Current daily discharge step durations:", dailyDischargeLevelStepTracker, false)) {
                    arrayList = dailyPackageChanges;
                    dumpDailyLevelStepSummary(printWriter, "      ", "Discharge", dailyDischargeLevelStepTracker, sb, iArr);
                } else {
                    arrayList = dailyPackageChanges;
                }
                if (dumpDurationSteps(printWriter, str, "  Current daily charge step durations:", dailyChargeLevelStepTracker, false)) {
                    dumpDailyLevelStepSummary(printWriter, "      ", "Charge", dailyChargeLevelStepTracker, sb, iArr);
                }
                dumpDailyPackageChanges(printWriter, str, arrayList);
            } else {
                printWriter.println("  Current daily steps:");
                str = "    ";
                dumpDailyLevelStepSummary(printWriter, "    ", "Discharge", dailyDischargeLevelStepTracker, sb, iArr);
                dumpDailyLevelStepSummary(printWriter, "    ", "Charge", dailyChargeLevelStepTracker, sb, iArr);
            }
            int i6 = 0;
            while (true) {
                DailyItem dailyItemLocked = getDailyItemLocked(i6);
                if (dailyItemLocked == null) {
                    break;
                }
                int i7 = i6 + 1;
                int i8 = i & 4;
                if (i8 != 0) {
                    printWriter.println();
                }
                printWriter.print("  Daily from ");
                printWriter.print(DateFormat.format("yyyy-MM-dd-HH-mm-ss", dailyItemLocked.mStartTime).toString());
                printWriter.print(" to ");
                printWriter.print(DateFormat.format("yyyy-MM-dd-HH-mm-ss", dailyItemLocked.mEndTime).toString());
                printWriter.println(SettingsStringUtil.DELIMITER);
                if (i8 != 0 || !z) {
                    if (dumpDurationSteps(printWriter, "      ", "    Discharge step durations:", dailyItemLocked.mDischargeSteps, false)) {
                        str2 = "      ";
                        dumpDailyLevelStepSummary(printWriter, "        ", "Discharge", dailyItemLocked.mDischargeSteps, sb, iArr);
                    } else {
                        str2 = "      ";
                    }
                    if (dumpDurationSteps(printWriter, str2, "    Charge step durations:", dailyItemLocked.mChargeSteps, false)) {
                        dumpDailyLevelStepSummary(printWriter, "        ", "Charge", dailyItemLocked.mChargeSteps, sb, iArr);
                    }
                    dumpDailyPackageChanges(printWriter, str, dailyItemLocked.mPackageChanges);
                } else {
                    dumpDailyLevelStepSummary(printWriter, "    ", "Discharge", dailyItemLocked.mDischargeSteps, sb, iArr);
                    dumpDailyLevelStepSummary(printWriter, "    ", "Charge", dailyItemLocked.mChargeSteps, sb, iArr);
                }
                i6 = i7;
            }
            z2 = false;
            printWriter.println();
        }
        if (z && (i & 2) == 0) {
            return;
        }
        printWriter.println("Statistics since last charge:");
        printWriter.println("  System starts: " + getStartCount() + ", currently on battery: " + getIsOnBattery());
        dumpLocked(context, printWriter, "", 0, i2, (i & 64) != 0 ? true : z2);
        printWriter.println();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void dumpCheckinLocked(Context context, PrintWriter printWriter, List<ApplicationInfo> list, int i, long j) {
        prepareForDumpLocked();
        dumpLine(printWriter, 0, "i", VERSION_DATA, 35, Integer.valueOf(getParcelVersion()), getStartPlatformVersion(), getEndPlatformVersion());
        getHistoryBaseTime();
        SystemClock.elapsedRealtime();
        if ((i & 24) != 0 && startIteratingHistoryLocked()) {
            for (int i2 = 0; i2 < getHistoryStringPoolSize(); i2++) {
                try {
                    printWriter.print(9);
                    printWriter.print(',');
                    printWriter.print(HISTORY_STRING_POOL);
                    printWriter.print(',');
                    printWriter.print(i2);
                    printWriter.print(",");
                    printWriter.print(getHistoryTagPoolUid(i2));
                    printWriter.print(",\"");
                    printWriter.print(getHistoryTagPoolString(i2).replace("\\", "\\\\").replace("\"", "\\\""));
                    printWriter.print("\"");
                    printWriter.println();
                } finally {
                    finishIteratingHistoryLocked();
                }
            }
            dumpHistoryLocked(printWriter, i, j, true);
        }
        if ((i & 8) != 0) {
            return;
        }
        if (list != null) {
            SparseArray sparseArray = new SparseArray();
            for (int i3 = 0; i3 < list.size(); i3++) {
                ApplicationInfo applicationInfo = list.get(i3);
                Pair pair = (Pair) sparseArray.get(UserHandle.getAppId(applicationInfo.uid));
                if (pair == null) {
                    pair = new Pair(new ArrayList(), new MutableBoolean(false));
                    sparseArray.put(UserHandle.getAppId(applicationInfo.uid), pair);
                }
                ((ArrayList) pair.first).add(applicationInfo.packageName);
            }
            SparseArray<? extends Uid> uidStats = getUidStats();
            int size = uidStats.size();
            String[] strArr = new String[2];
            for (int i4 = 0; i4 < size; i4++) {
                int appId = UserHandle.getAppId(uidStats.keyAt(i4));
                Pair pair2 = (Pair) sparseArray.get(appId);
                if (pair2 != null && !((MutableBoolean) pair2.second).value) {
                    ((MutableBoolean) pair2.second).value = true;
                    for (int i5 = 0; i5 < ((ArrayList) pair2.first).size(); i5++) {
                        strArr[0] = Integer.toString(appId);
                        strArr[1] = (String) ((ArrayList) pair2.first).get(i5);
                        dumpLine(printWriter, 0, "i", "uid", strArr);
                    }
                }
            }
        }
        if ((i & 4) == 0) {
            dumpDurationSteps(printWriter, "", DISCHARGE_STEP_DATA, getDischargeLevelStepTracker(), true);
            String[] strArr2 = new String[1];
            long jComputeBatteryTimeRemaining = computeBatteryTimeRemaining(SystemClock.elapsedRealtime() * 1000);
            if (jComputeBatteryTimeRemaining >= 0) {
                strArr2[0] = Long.toString(jComputeBatteryTimeRemaining);
                dumpLine(printWriter, 0, "i", DISCHARGE_TIME_REMAIN_DATA, strArr2);
            }
            dumpDurationSteps(printWriter, "", CHARGE_STEP_DATA, getChargeLevelStepTracker(), true);
            long jComputeChargeTimeRemaining = computeChargeTimeRemaining(SystemClock.elapsedRealtime() * 1000);
            if (jComputeChargeTimeRemaining >= 0) {
                strArr2[0] = Long.toString(jComputeChargeTimeRemaining);
                dumpLine(printWriter, 0, "i", CHARGE_TIME_REMAIN_DATA, strArr2);
            }
            dumpCheckinLocked(context, printWriter, 0, -1, (i & 64) != 0);
        }
    }

    public void dumpProtoLocked(Context context, FileDescriptor fileDescriptor, List<ApplicationInfo> list, int i, long j) {
        ProtoOutputStream protoOutputStream = new ProtoOutputStream(fileDescriptor);
        prepareForDumpLocked();
        if ((i & 24) != 0) {
            dumpProtoHistoryLocked(protoOutputStream, i, j);
            protoOutputStream.flush();
            return;
        }
        long jStart = protoOutputStream.start(1146756268033L);
        protoOutputStream.write(1120986464257L, 35);
        protoOutputStream.write(1112396529666L, getParcelVersion());
        protoOutputStream.write(1138166333443L, getStartPlatformVersion());
        protoOutputStream.write(1138166333444L, getEndPlatformVersion());
        if ((i & 4) == 0) {
            BatteryStatsHelper batteryStatsHelper = new BatteryStatsHelper(context, false, (i & 64) != 0);
            batteryStatsHelper.create(this);
            batteryStatsHelper.refreshStats(0, -1);
            dumpProtoAppsLocked(protoOutputStream, batteryStatsHelper, list);
            dumpProtoSystemLocked(protoOutputStream, batteryStatsHelper);
        }
        protoOutputStream.end(jStart);
        protoOutputStream.flush();
    }

    private void dumpProtoAppsLocked(ProtoOutputStream protoOutputStream, BatteryStatsHelper batteryStatsHelper, List<ApplicationInfo> list) {
        ArrayList<String> arrayList;
        long j;
        long j2;
        long j3;
        long[] jArr;
        SparseArray sparseArray;
        long[] cpuFreqTimes;
        long j4;
        long j5;
        ArrayMap<String, ? extends Uid.Pkg> arrayMap;
        ArrayList arrayList2;
        long j6;
        SparseArray sparseArray2;
        ArrayList arrayList3;
        long j7;
        SparseArray sparseArray3;
        ProtoOutputStream protoOutputStream2 = protoOutputStream;
        long jUptimeMillis = SystemClock.uptimeMillis() * 1000;
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        long j8 = jElapsedRealtime * 1000;
        long batteryUptime = getBatteryUptime(jUptimeMillis);
        SparseArray sparseArray4 = new SparseArray();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                ApplicationInfo applicationInfo = list.get(i);
                int appId = UserHandle.getAppId(applicationInfo.uid);
                ArrayList arrayList4 = (ArrayList) sparseArray4.get(appId);
                if (arrayList4 == null) {
                    arrayList4 = new ArrayList();
                    sparseArray4.put(appId, arrayList4);
                }
                arrayList4.add(applicationInfo.packageName);
            }
        }
        SparseArray sparseArray5 = new SparseArray();
        List<BatterySipper> usageList = batteryStatsHelper.getUsageList();
        if (usageList != null) {
            for (int i2 = 0; i2 < usageList.size(); i2++) {
                BatterySipper batterySipper = usageList.get(i2);
                if (batterySipper.drainType == BatterySipper.DrainType.APP) {
                    sparseArray5.put(batterySipper.uidObj.getUid(), batterySipper);
                }
            }
        }
        SparseArray<? extends Uid> uidStats = getUidStats();
        int size = uidStats.size();
        int i3 = 0;
        while (i3 < size) {
            int i4 = size;
            long jStart = protoOutputStream2.start(2246267895813L);
            Uid uidValueAt = uidStats.valueAt(i3);
            int iKeyAt = uidStats.keyAt(i3);
            SparseArray sparseArray6 = sparseArray5;
            SparseArray<? extends Uid> sparseArray7 = uidStats;
            protoOutputStream2.write(1120986464257L, iKeyAt);
            ArrayList arrayList5 = (ArrayList) sparseArray4.get(UserHandle.getAppId(iKeyAt));
            if (arrayList5 == null) {
                arrayList5 = new ArrayList();
            }
            int i5 = i3;
            ArrayMap<String, ? extends Uid.Pkg> packageStats = uidValueAt.getPackageStats();
            int size2 = packageStats.size() - 1;
            while (true) {
                arrayList = arrayList5;
                if (size2 < 0) {
                    break;
                }
                String strKeyAt = packageStats.keyAt(size2);
                ArrayMap<String, ? extends Uid.Pkg.Serv> serviceStats = packageStats.valueAt(size2).getServiceStats();
                if (serviceStats.size() == 0) {
                    arrayMap = packageStats;
                    arrayList2 = arrayList;
                    j4 = jElapsedRealtime;
                    j5 = j8;
                    j6 = batteryUptime;
                    sparseArray2 = sparseArray4;
                } else {
                    j4 = jElapsedRealtime;
                    j5 = j8;
                    long jStart2 = protoOutputStream2.start(2246267895810L);
                    protoOutputStream2.write(1138166333441L, strKeyAt);
                    arrayList.remove(strKeyAt);
                    int size3 = serviceStats.size() - 1;
                    while (size3 >= 0) {
                        Uid.Pkg.Serv servValueAt = serviceStats.valueAt(size3);
                        long jRoundUsToMs = roundUsToMs(servValueAt.getStartTime(batteryUptime, 0));
                        ArrayMap<String, ? extends Uid.Pkg> arrayMap2 = packageStats;
                        int starts = servValueAt.getStarts(0);
                        int launches = servValueAt.getLaunches(0);
                        if (jRoundUsToMs == 0 && starts == 0 && launches == 0) {
                            protoOutputStream2 = protoOutputStream;
                            arrayList3 = arrayList;
                            j7 = batteryUptime;
                            sparseArray3 = sparseArray4;
                        } else {
                            arrayList3 = arrayList;
                            j7 = batteryUptime;
                            protoOutputStream2 = protoOutputStream;
                            long jStart3 = protoOutputStream2.start(2246267895810L);
                            sparseArray3 = sparseArray4;
                            protoOutputStream2.write(1138166333441L, serviceStats.keyAt(size3));
                            protoOutputStream2.write(1112396529666L, jRoundUsToMs);
                            protoOutputStream2.write(1120986464259L, starts);
                            protoOutputStream2.write(1120986464260L, launches);
                            protoOutputStream2.end(jStart3);
                        }
                        size3--;
                        packageStats = arrayMap2;
                        batteryUptime = j7;
                        arrayList = arrayList3;
                        sparseArray4 = sparseArray3;
                    }
                    arrayMap = packageStats;
                    arrayList2 = arrayList;
                    j6 = batteryUptime;
                    sparseArray2 = sparseArray4;
                    protoOutputStream2.end(jStart2);
                }
                size2--;
                packageStats = arrayMap;
                j8 = j5;
                jElapsedRealtime = j4;
                batteryUptime = j6;
                arrayList5 = arrayList2;
                sparseArray4 = sparseArray2;
            }
            ArrayMap<String, ? extends Uid.Pkg> arrayMap3 = packageStats;
            long j9 = jElapsedRealtime;
            long j10 = j8;
            long j11 = batteryUptime;
            SparseArray sparseArray8 = sparseArray4;
            for (String str : arrayList) {
                long jStart4 = protoOutputStream2.start(2246267895810L);
                protoOutputStream2.write(1138166333441L, str);
                protoOutputStream2.end(jStart4);
            }
            if (uidValueAt.getAggregatedPartialWakelockTimer() != null) {
                Timer aggregatedPartialWakelockTimer = uidValueAt.getAggregatedPartialWakelockTimer();
                j = j9;
                long totalDurationMsLocked = aggregatedPartialWakelockTimer.getTotalDurationMsLocked(j);
                Timer subTimer = aggregatedPartialWakelockTimer.getSubTimer();
                long totalDurationMsLocked2 = subTimer != null ? subTimer.getTotalDurationMsLocked(j) : 0L;
                long jStart5 = protoOutputStream2.start(1146756268056L);
                protoOutputStream2.write(1112396529665L, totalDurationMsLocked);
                protoOutputStream2.write(1112396529666L, totalDurationMsLocked2);
                protoOutputStream2.end(jStart5);
            } else {
                j = j9;
            }
            int i6 = i5;
            int i7 = iKeyAt;
            SparseArray sparseArray9 = sparseArray6;
            ArrayMap<String, ? extends Uid.Pkg> arrayMap4 = arrayMap3;
            dumpTimer(protoOutputStream, 1146756268040L, uidValueAt.getAudioTurnedOnTimer(), j10, 0);
            dumpControllerActivityProto(protoOutputStream2, 1146756268035L, uidValueAt.getBluetoothControllerActivity(), 0);
            Timer bluetoothScanTimer = uidValueAt.getBluetoothScanTimer();
            if (bluetoothScanTimer != null) {
                long jStart6 = protoOutputStream2.start(1146756268038L);
                dumpTimer(protoOutputStream, 1146756268033L, bluetoothScanTimer, j10, 0);
                dumpTimer(protoOutputStream, 1146756268034L, uidValueAt.getBluetoothScanBackgroundTimer(), j10, 0);
                dumpTimer(protoOutputStream, 1146756268035L, uidValueAt.getBluetoothUnoptimizedScanTimer(), j10, 0);
                dumpTimer(protoOutputStream, 1146756268036L, uidValueAt.getBluetoothUnoptimizedScanBackgroundTimer(), j10, 0);
                protoOutputStream2.write(1120986464261L, uidValueAt.getBluetoothScanResultCounter() != null ? uidValueAt.getBluetoothScanResultCounter().getCountLocked(0) : 0);
                protoOutputStream2.write(1120986464262L, uidValueAt.getBluetoothScanResultBgCounter() != null ? uidValueAt.getBluetoothScanResultBgCounter().getCountLocked(0) : 0);
                protoOutputStream2.end(jStart6);
            }
            dumpTimer(protoOutputStream, 1146756268041L, uidValueAt.getCameraTurnedOnTimer(), j10, 0);
            long jStart7 = protoOutputStream2.start(1146756268039L);
            protoOutputStream2.write(1112396529665L, roundUsToMs(uidValueAt.getUserCpuTimeUs(0)));
            protoOutputStream2.write(1112396529666L, roundUsToMs(uidValueAt.getSystemCpuTimeUs(0)));
            long[] cpuFreqs = getCpuFreqs();
            if (cpuFreqs != null && (cpuFreqTimes = uidValueAt.getCpuFreqTimes(0)) != null && cpuFreqTimes.length == cpuFreqs.length) {
                long[] screenOffCpuFreqTimes = uidValueAt.getScreenOffCpuFreqTimes(0);
                if (screenOffCpuFreqTimes == null) {
                    screenOffCpuFreqTimes = new long[cpuFreqTimes.length];
                }
                int i8 = 0;
                while (i8 < cpuFreqTimes.length) {
                    long jStart8 = protoOutputStream2.start(2246267895811L);
                    int i9 = i8 + 1;
                    protoOutputStream2.write(1120986464257L, i9);
                    protoOutputStream2.write(1112396529666L, cpuFreqTimes[i8]);
                    protoOutputStream2.write(1112396529667L, screenOffCpuFreqTimes[i8]);
                    protoOutputStream2.end(jStart8);
                    i8 = i9;
                    i6 = i6;
                    i7 = i7;
                    arrayMap4 = arrayMap4;
                    j = j;
                }
            }
            int i10 = i6;
            int i11 = i7;
            ArrayMap<String, ? extends Uid.Pkg> arrayMap5 = arrayMap4;
            long j12 = j;
            int i12 = 0;
            while (true) {
                j2 = 1159641169921L;
                if (i12 >= 7) {
                    break;
                }
                long[] cpuFreqTimes2 = uidValueAt.getCpuFreqTimes(0, i12);
                if (cpuFreqTimes2 == null || cpuFreqTimes2.length != cpuFreqs.length) {
                    j3 = jStart7;
                    jArr = cpuFreqs;
                    sparseArray = sparseArray9;
                } else {
                    long[] screenOffCpuFreqTimes2 = uidValueAt.getScreenOffCpuFreqTimes(0, i12);
                    if (screenOffCpuFreqTimes2 == null) {
                        screenOffCpuFreqTimes2 = new long[cpuFreqTimes2.length];
                    }
                    long jStart9 = protoOutputStream2.start(2246267895812L);
                    protoOutputStream2.write(1159641169921L, i12);
                    int i13 = 0;
                    while (i13 < cpuFreqTimes2.length) {
                        long jStart10 = protoOutputStream2.start(2246267895810L);
                        int i14 = i13 + 1;
                        protoOutputStream2.write(1120986464257L, i14);
                        protoOutputStream2.write(1112396529666L, cpuFreqTimes2[i13]);
                        protoOutputStream2.write(1112396529667L, screenOffCpuFreqTimes2[i13]);
                        protoOutputStream2.end(jStart10);
                        cpuFreqs = cpuFreqs;
                        i13 = i14;
                        sparseArray9 = sparseArray9;
                        jStart7 = jStart7;
                    }
                    j3 = jStart7;
                    jArr = cpuFreqs;
                    sparseArray = sparseArray9;
                    protoOutputStream2.end(jStart9);
                }
                i12++;
                cpuFreqs = jArr;
                sparseArray9 = sparseArray;
                jStart7 = j3;
            }
            SparseArray sparseArray10 = sparseArray9;
            protoOutputStream2.end(jStart7);
            dumpTimer(protoOutputStream, 1146756268042L, uidValueAt.getFlashlightTurnedOnTimer(), j10, 0);
            dumpTimer(protoOutputStream, 1146756268043L, uidValueAt.getForegroundActivityTimer(), j10, 0);
            dumpTimer(protoOutputStream, 1146756268044L, uidValueAt.getForegroundServiceTimer(), j10, 0);
            ArrayMap<String, SparseIntArray> jobCompletionStats = uidValueAt.getJobCompletionStats();
            int i15 = 0;
            while (i15 < jobCompletionStats.size()) {
                SparseIntArray sparseIntArrayValueAt = jobCompletionStats.valueAt(i15);
                if (sparseIntArrayValueAt != null) {
                    long jStart11 = protoOutputStream2.start(2246267895824L);
                    protoOutputStream2.write(1138166333441L, jobCompletionStats.keyAt(i15));
                    int[] jobStopReasonCodes = JobParameters.getJobStopReasonCodes();
                    int length = jobStopReasonCodes.length;
                    int i16 = 0;
                    while (i16 < length) {
                        int i17 = jobStopReasonCodes[i16];
                        long jStart12 = protoOutputStream2.start(2246267895810L);
                        protoOutputStream2.write(j2, i17);
                        protoOutputStream2.write(1120986464258L, sparseIntArrayValueAt.get(i17, 0));
                        protoOutputStream2.end(jStart12);
                        i16++;
                        length = length;
                        j2 = 1159641169921L;
                    }
                    protoOutputStream2.end(jStart11);
                }
                i15++;
                j2 = 1159641169921L;
            }
            ArrayMap<String, ? extends Timer> jobStats = uidValueAt.getJobStats();
            for (int size4 = jobStats.size() - 1; size4 >= 0; size4--) {
                Timer timerValueAt = jobStats.valueAt(size4);
                Timer subTimer2 = timerValueAt.getSubTimer();
                long jStart13 = protoOutputStream2.start(2246267895823L);
                protoOutputStream2.write(1138166333441L, jobStats.keyAt(size4));
                dumpTimer(protoOutputStream, 1146756268034L, timerValueAt, j10, 0);
                dumpTimer(protoOutputStream, 1146756268035L, subTimer2, j10, 0);
                protoOutputStream2.end(jStart13);
            }
            dumpControllerActivityProto(protoOutputStream2, 1146756268036L, uidValueAt.getModemControllerActivity(), 0);
            long jStart14 = protoOutputStream2.start(1146756268049L);
            protoOutputStream2.write(1112396529665L, uidValueAt.getNetworkActivityBytes(0, 0));
            protoOutputStream2.write(1112396529666L, uidValueAt.getNetworkActivityBytes(1, 0));
            protoOutputStream2.write(1112396529667L, uidValueAt.getNetworkActivityBytes(2, 0));
            long j13 = 1112396529668L;
            protoOutputStream2.write(1112396529668L, uidValueAt.getNetworkActivityBytes(3, 0));
            protoOutputStream2.write(1112396529669L, uidValueAt.getNetworkActivityBytes(4, 0));
            protoOutputStream2.write(1112396529670L, uidValueAt.getNetworkActivityBytes(5, 0));
            protoOutputStream2.write(1112396529671L, uidValueAt.getNetworkActivityPackets(0, 0));
            protoOutputStream2.write(1112396529672L, uidValueAt.getNetworkActivityPackets(1, 0));
            protoOutputStream2.write(1112396529673L, uidValueAt.getNetworkActivityPackets(2, 0));
            protoOutputStream2.write(1112396529674L, uidValueAt.getNetworkActivityPackets(3, 0));
            protoOutputStream2.write(1112396529675L, roundUsToMs(uidValueAt.getMobileRadioActiveTime(0)));
            protoOutputStream2.write(1120986464268L, uidValueAt.getMobileRadioActiveCount(0));
            protoOutputStream2.write(1120986464269L, uidValueAt.getMobileRadioApWakeupCount(0));
            protoOutputStream2.write(1120986464270L, uidValueAt.getWifiRadioApWakeupCount(0));
            protoOutputStream2.write(1112396529679L, uidValueAt.getNetworkActivityBytes(6, 0));
            protoOutputStream2.write(1112396529680L, uidValueAt.getNetworkActivityBytes(7, 0));
            protoOutputStream2.write(1112396529681L, uidValueAt.getNetworkActivityBytes(8, 0));
            protoOutputStream2.write(1112396529682L, uidValueAt.getNetworkActivityBytes(9, 0));
            protoOutputStream2.write(1112396529683L, uidValueAt.getNetworkActivityPackets(6, 0));
            protoOutputStream2.write(1112396529684L, uidValueAt.getNetworkActivityPackets(7, 0));
            protoOutputStream2.write(1112396529685L, uidValueAt.getNetworkActivityPackets(8, 0));
            protoOutputStream2.write(1112396529686L, uidValueAt.getNetworkActivityPackets(9, 0));
            protoOutputStream2.end(jStart14);
            SparseArray sparseArray11 = sparseArray10;
            BatterySipper batterySipper2 = (BatterySipper) sparseArray11.get(i11);
            if (batterySipper2 != null) {
                long jStart15 = protoOutputStream2.start(1146756268050L);
                protoOutputStream2.write(1103806595073L, batterySipper2.totalPowerMah);
                protoOutputStream2.write(1133871366146L, batterySipper2.shouldHide);
                protoOutputStream2.write(1103806595075L, batterySipper2.screenPowerMah);
                protoOutputStream2.write(1103806595076L, batterySipper2.proportionalSmearMah);
                protoOutputStream2.end(jStart15);
            }
            ArrayMap<String, ? extends Uid.Proc> processStats = uidValueAt.getProcessStats();
            int size5 = processStats.size() - 1;
            while (size5 >= 0) {
                Uid.Proc procValueAt = processStats.valueAt(size5);
                long jStart16 = protoOutputStream2.start(2246267895827L);
                protoOutputStream2.write(1138166333441L, processStats.keyAt(size5));
                protoOutputStream2.write(1112396529666L, procValueAt.getUserTime(0));
                protoOutputStream2.write(1112396529667L, procValueAt.getSystemTime(0));
                protoOutputStream2.write(j13, procValueAt.getForegroundTime(0));
                protoOutputStream2.write(1120986464261L, procValueAt.getStarts(0));
                protoOutputStream2.write(1120986464262L, procValueAt.getNumAnrs(0));
                protoOutputStream2.write(1120986464263L, procValueAt.getNumCrashes(0));
                protoOutputStream2.end(jStart16);
                size5--;
                sparseArray11 = sparseArray11;
                j13 = 1112396529668L;
            }
            SparseArray sparseArray12 = sparseArray11;
            SparseArray<? extends Uid.Sensor> sensorStats = uidValueAt.getSensorStats();
            for (int i18 = 0; i18 < sensorStats.size(); i18++) {
                Uid.Sensor sensorValueAt = sensorStats.valueAt(i18);
                Timer sensorTime = sensorValueAt.getSensorTime();
                if (sensorTime != null) {
                    Timer sensorBackgroundTime = sensorValueAt.getSensorBackgroundTime();
                    int iKeyAt2 = sensorStats.keyAt(i18);
                    long jStart17 = protoOutputStream2.start(2246267895829L);
                    protoOutputStream2.write(1120986464257L, iKeyAt2);
                    dumpTimer(protoOutputStream, 1146756268034L, sensorTime, j10, 0);
                    dumpTimer(protoOutputStream, 1146756268035L, sensorBackgroundTime, j10, 0);
                    protoOutputStream2.end(jStart17);
                }
            }
            int i19 = 0;
            while (i19 < 7) {
                long j14 = j10;
                long jRoundUsToMs2 = roundUsToMs(uidValueAt.getProcessStateTime(i19, j14, 0));
                if (jRoundUsToMs2 != 0) {
                    long jStart18 = protoOutputStream2.start(2246267895828L);
                    protoOutputStream2.write(1159641169921L, i19);
                    protoOutputStream2.write(1112396529666L, jRoundUsToMs2);
                    protoOutputStream2.end(jStart18);
                }
                i19++;
                j10 = j14;
            }
            long j15 = j10;
            ArrayMap<String, ? extends Timer> syncStats = uidValueAt.getSyncStats();
            for (int size6 = syncStats.size() - 1; size6 >= 0; size6--) {
                Timer timerValueAt2 = syncStats.valueAt(size6);
                Timer subTimer3 = timerValueAt2.getSubTimer();
                long jStart19 = protoOutputStream2.start(2246267895830L);
                protoOutputStream2.write(1138166333441L, syncStats.keyAt(size6));
                dumpTimer(protoOutputStream, 1146756268034L, timerValueAt2, j15, 0);
                dumpTimer(protoOutputStream, 1146756268035L, subTimer3, j15, 0);
                protoOutputStream2.end(jStart19);
            }
            if (uidValueAt.hasUserActivity()) {
                for (int i20 = 0; i20 < Uid.NUM_USER_ACTIVITY_TYPES; i20++) {
                    int userActivityCount = uidValueAt.getUserActivityCount(i20, 0);
                    if (userActivityCount != 0) {
                        long jStart20 = protoOutputStream2.start(2246267895831L);
                        protoOutputStream2.write(1159641169921L, i20);
                        protoOutputStream2.write(1120986464258L, userActivityCount);
                        protoOutputStream2.end(jStart20);
                    }
                }
            }
            dumpTimer(protoOutputStream, 1146756268045L, uidValueAt.getVibratorOnTimer(), j15, 0);
            dumpTimer(protoOutputStream, 1146756268046L, uidValueAt.getVideoTurnedOnTimer(), j15, 0);
            ArrayMap<String, ? extends Uid.Wakelock> wakelockStats = uidValueAt.getWakelockStats();
            int i21 = 1;
            int size7 = wakelockStats.size() - 1;
            while (size7 >= 0) {
                Uid.Wakelock wakelockValueAt = wakelockStats.valueAt(size7);
                long jStart21 = protoOutputStream2.start(2246267895833L);
                protoOutputStream2.write(1138166333441L, wakelockStats.keyAt(size7));
                dumpTimer(protoOutputStream, 1146756268034L, wakelockValueAt.getWakeTime(i21), j15, 0);
                Timer wakeTime = wakelockValueAt.getWakeTime(0);
                if (wakeTime != null) {
                    dumpTimer(protoOutputStream, 1146756268035L, wakeTime, j15, 0);
                    dumpTimer(protoOutputStream, 1146756268036L, wakeTime.getSubTimer(), j15, 0);
                }
                dumpTimer(protoOutputStream, 1146756268037L, wakelockValueAt.getWakeTime(2), j15, 0);
                protoOutputStream2.end(jStart21);
                size7--;
                i21 = 1;
            }
            dumpTimer(protoOutputStream, 1146756268060L, uidValueAt.getMulticastWakelockStats(), j15, 0);
            int i22 = 1;
            int size8 = arrayMap5.size() - 1;
            while (size8 >= 0) {
                ArrayMap<String, ? extends Uid.Pkg> arrayMap6 = arrayMap5;
                ArrayMap<String, ? extends Counter> wakeupAlarmStats = arrayMap6.valueAt(size8).getWakeupAlarmStats();
                int size9 = wakeupAlarmStats.size() - i22;
                while (size9 >= 0) {
                    long jStart22 = protoOutputStream2.start(2246267895834L);
                    protoOutputStream2.write(1138166333441L, wakeupAlarmStats.keyAt(size9));
                    protoOutputStream2.write(1120986464258L, wakeupAlarmStats.valueAt(size9).getCountLocked(0));
                    protoOutputStream2.end(jStart22);
                    size9--;
                    arrayMap6 = arrayMap6;
                }
                arrayMap5 = arrayMap6;
                size8--;
                i22 = 1;
            }
            dumpControllerActivityProto(protoOutputStream2, 1146756268037L, uidValueAt.getWifiControllerActivity(), 0);
            long jStart23 = protoOutputStream2.start(1146756268059L);
            protoOutputStream2.write(1112396529665L, roundUsToMs(uidValueAt.getFullWifiLockTime(j15, 0)));
            dumpTimer(protoOutputStream, 1146756268035L, uidValueAt.getWifiScanTimer(), j15, 0);
            protoOutputStream2.write(1112396529666L, roundUsToMs(uidValueAt.getWifiRunningTime(j15, 0)));
            dumpTimer(protoOutputStream, 1146756268036L, uidValueAt.getWifiScanBackgroundTimer(), j15, 0);
            protoOutputStream2.end(jStart23);
            protoOutputStream2.end(jStart);
            i3 = i10 + 1;
            j8 = j15;
            size = i4;
            uidStats = sparseArray7;
            sparseArray5 = sparseArray12;
            jElapsedRealtime = j12;
            batteryUptime = j11;
            sparseArray4 = sparseArray8;
        }
    }

    private void dumpProtoHistoryLocked(ProtoOutputStream protoOutputStream, int i, long j) {
        if (startIteratingHistoryLocked()) {
            protoOutputStream.write(1120986464257L, 35);
            protoOutputStream.write(1112396529666L, getParcelVersion());
            protoOutputStream.write(1138166333443L, getStartPlatformVersion());
            protoOutputStream.write(1138166333444L, getEndPlatformVersion());
            for (int i2 = 0; i2 < getHistoryStringPoolSize(); i2++) {
                try {
                    long jStart = protoOutputStream.start(2246267895813L);
                    protoOutputStream.write(1120986464257L, i2);
                    protoOutputStream.write(1120986464258L, getHistoryTagPoolUid(i2));
                    protoOutputStream.write(1138166333443L, getHistoryTagPoolString(i2));
                    protoOutputStream.end(jStart);
                } finally {
                    finishIteratingHistoryLocked();
                }
            }
            HistoryPrinter historyPrinter = new HistoryPrinter();
            HistoryItem historyItem = new HistoryItem();
            long j2 = -1;
            long j3 = -1;
            boolean z = false;
            while (getNextHistoryLocked(historyItem)) {
                long j4 = historyItem.time;
                long j5 = j2 < 0 ? j4 : j2;
                if (historyItem.time >= j) {
                    if (j >= 0 && !z) {
                        if (historyItem.cmd == 5 || historyItem.cmd == 7 || historyItem.cmd == 4 || historyItem.cmd == 8) {
                            historyPrinter.printNextItem(protoOutputStream, historyItem, j5, (i & 32) != 0);
                            historyItem.cmd = (byte) 0;
                        } else if (historyItem.currentTime != 0) {
                            byte b = historyItem.cmd;
                            historyItem.cmd = (byte) 5;
                            historyPrinter.printNextItem(protoOutputStream, historyItem, j5, (i & 32) != 0);
                            historyItem.cmd = b;
                        }
                        z = true;
                    }
                    boolean z2 = z;
                    historyPrinter.printNextItem(protoOutputStream, historyItem, j5, (i & 32) != 0);
                    z = z2;
                }
                j3 = j4;
                j2 = j5;
            }
            if (j >= 0) {
                commitCurrentHistoryBatchLocked();
                protoOutputStream.write(2237677961222L, "NEXT: " + (j3 + 1));
            }
        }
    }

    private void dumpProtoSystemLocked(ProtoOutputStream protoOutputStream, BatteryStatsHelper batteryStatsHelper) {
        int i;
        int i2;
        int uid;
        List<BatterySipper> list;
        long jStart = protoOutputStream.start(1146756268038L);
        long jUptimeMillis = SystemClock.uptimeMillis() * 1000;
        long jElapsedRealtime = SystemClock.elapsedRealtime() * 1000;
        long jStart2 = protoOutputStream.start(1146756268033L);
        protoOutputStream.write(1112396529665L, getStartClockTime());
        protoOutputStream.write(1112396529666L, getStartCount());
        int i3 = 0;
        protoOutputStream.write(1112396529667L, computeRealtime(jElapsedRealtime, 0) / 1000);
        protoOutputStream.write(1112396529668L, computeUptime(jUptimeMillis, 0) / 1000);
        protoOutputStream.write(1112396529669L, computeBatteryRealtime(jElapsedRealtime, 0) / 1000);
        protoOutputStream.write(1112396529670L, computeBatteryUptime(jUptimeMillis, 0) / 1000);
        protoOutputStream.write(1112396529671L, computeBatteryScreenOffRealtime(jElapsedRealtime, 0) / 1000);
        protoOutputStream.write(1112396529672L, computeBatteryScreenOffUptime(jUptimeMillis, 0) / 1000);
        protoOutputStream.write(1112396529673L, getScreenDozeTime(jElapsedRealtime, 0) / 1000);
        protoOutputStream.write(1112396529674L, getEstimatedBatteryCapacity());
        protoOutputStream.write(1112396529675L, getMinLearnedBatteryCapacity());
        protoOutputStream.write(1112396529676L, getMaxLearnedBatteryCapacity());
        protoOutputStream.end(jStart2);
        long jStart3 = protoOutputStream.start(1146756268034L);
        protoOutputStream.write(1120986464257L, getLowDischargeAmountSinceCharge());
        protoOutputStream.write(1120986464258L, getHighDischargeAmountSinceCharge());
        protoOutputStream.write(1120986464259L, getDischargeAmountScreenOnSinceCharge());
        protoOutputStream.write(1120986464260L, getDischargeAmountScreenOffSinceCharge());
        protoOutputStream.write(1120986464261L, getDischargeAmountScreenDozeSinceCharge());
        protoOutputStream.write(1112396529670L, getUahDischarge(0) / 1000);
        protoOutputStream.write(1112396529671L, getUahDischargeScreenOff(0) / 1000);
        protoOutputStream.write(1112396529672L, getUahDischargeScreenDoze(0) / 1000);
        protoOutputStream.write(1112396529673L, getUahDischargeLightDoze(0) / 1000);
        protoOutputStream.write(1112396529674L, getUahDischargeDeepDoze(0) / 1000);
        protoOutputStream.end(jStart3);
        long jComputeChargeTimeRemaining = computeChargeTimeRemaining(jElapsedRealtime);
        long totalTimeLocked = 0;
        if (jComputeChargeTimeRemaining >= 0) {
            protoOutputStream.write(1112396529667L, jComputeChargeTimeRemaining / 1000);
        } else {
            long jComputeBatteryTimeRemaining = computeBatteryTimeRemaining(jElapsedRealtime);
            if (jComputeBatteryTimeRemaining >= 0) {
                protoOutputStream.write(1112396529668L, jComputeBatteryTimeRemaining / 1000);
            } else {
                protoOutputStream.write(1112396529668L, -1);
            }
        }
        dumpDurationSteps(protoOutputStream, 2246267895813L, getChargeLevelStepTracker());
        int i4 = 0;
        while (true) {
            if (i4 >= NUM_DATA_CONNECTION_TYPES) {
                break;
            }
            boolean z = i4 != 0 ? i3 : true;
            int i5 = (i4 == DATA_CONNECTION_OTHER || i4 == DATA_CONNECTION_EMERGENCY_SERVICE) ? i3 : i4;
            long jStart4 = protoOutputStream.start(2246267895816L);
            if (z) {
                protoOutputStream.write(1133871366146L, z);
            } else {
                protoOutputStream.write(1159641169921L, i5);
            }
            dumpTimer(protoOutputStream, 1146756268035L, getPhoneDataConnectionTimer(i4), jElapsedRealtime, 0);
            protoOutputStream.end(jStart4);
            i4++;
            i3 = i3;
        }
        int i6 = i3;
        dumpDurationSteps(protoOutputStream, 2246267895814L, getDischargeLevelStepTracker());
        long[] cpuFreqs = getCpuFreqs();
        if (cpuFreqs != null) {
            int length = cpuFreqs.length;
            for (int i7 = i6; i7 < length; i7++) {
                protoOutputStream.write(SystemProto.CPU_FREQUENCY, cpuFreqs[i7]);
            }
        }
        dumpControllerActivityProto(protoOutputStream, 1146756268041L, getBluetoothControllerActivity(), i6);
        dumpControllerActivityProto(protoOutputStream, 1146756268042L, getModemControllerActivity(), i6);
        long jStart5 = protoOutputStream.start(1146756268044L);
        protoOutputStream.write(1112396529665L, getNetworkActivityBytes(i6, i6));
        protoOutputStream.write(1112396529666L, getNetworkActivityBytes(1, i6));
        protoOutputStream.write(1112396529669L, getNetworkActivityPackets(i6, i6));
        protoOutputStream.write(1112396529670L, getNetworkActivityPackets(1, i6));
        protoOutputStream.write(1112396529667L, getNetworkActivityBytes(2, i6));
        protoOutputStream.write(1112396529668L, getNetworkActivityBytes(3, i6));
        protoOutputStream.write(1112396529671L, getNetworkActivityPackets(2, i6));
        protoOutputStream.write(1112396529672L, getNetworkActivityPackets(3, i6));
        protoOutputStream.write(1112396529673L, getNetworkActivityBytes(4, i6));
        char c = 5;
        protoOutputStream.write(1112396529674L, getNetworkActivityBytes(5, i6));
        protoOutputStream.end(jStart5);
        dumpControllerActivityProto(protoOutputStream, 1146756268043L, getWifiControllerActivity(), i6);
        long jStart6 = protoOutputStream.start(1146756268045L);
        protoOutputStream.write(1112396529665L, getWifiOnTime(jElapsedRealtime, i6) / 1000);
        protoOutputStream.write(1112396529666L, getGlobalWifiRunningTime(jElapsedRealtime, i6) / 1000);
        protoOutputStream.end(jStart6);
        for (Map.Entry<String, ? extends Timer> entry : getKernelWakelockStats().entrySet()) {
            long jStart7 = protoOutputStream.start(2246267895822L);
            protoOutputStream.write(1138166333441L, entry.getKey());
            dumpTimer(protoOutputStream, 1146756268034L, entry.getValue(), jElapsedRealtime, 0);
            protoOutputStream.end(jStart7);
            c = 5;
        }
        SparseArray<? extends Uid> uidStats = getUidStats();
        long totalTimeLocked2 = 0;
        for (int i8 = 0; i8 < uidStats.size(); i8++) {
            ArrayMap<String, ? extends Uid.Wakelock> wakelockStats = uidStats.valueAt(i8).getWakelockStats();
            for (int size = wakelockStats.size() - 1; size >= 0; size--) {
                Uid.Wakelock wakelockValueAt = wakelockStats.valueAt(size);
                Timer wakeTime = wakelockValueAt.getWakeTime(1);
                if (wakeTime != null) {
                    totalTimeLocked += wakeTime.getTotalTimeLocked(jElapsedRealtime, 0);
                }
                Timer wakeTime2 = wakelockValueAt.getWakeTime(0);
                if (wakeTime2 != null) {
                    totalTimeLocked2 += wakeTime2.getTotalTimeLocked(jElapsedRealtime, 0);
                }
            }
        }
        long jStart8 = protoOutputStream.start(1146756268047L);
        protoOutputStream.write(1112396529665L, getScreenOnTime(jElapsedRealtime, 0) / 1000);
        protoOutputStream.write(1112396529666L, getPhoneOnTime(jElapsedRealtime, 0) / 1000);
        protoOutputStream.write(1112396529667L, totalTimeLocked / 1000);
        protoOutputStream.write(1112396529668L, totalTimeLocked2 / 1000);
        protoOutputStream.write(1112396529669L, getMobileRadioActiveTime(jElapsedRealtime, 0) / 1000);
        protoOutputStream.write(1112396529670L, getMobileRadioActiveAdjustedTime(0) / 1000);
        protoOutputStream.write(1120986464263L, getMobileRadioActiveCount(0));
        protoOutputStream.write(1120986464264L, getMobileRadioActiveUnknownTime(0) / 1000);
        protoOutputStream.write(1112396529673L, getInteractiveTime(jElapsedRealtime, 0) / 1000);
        protoOutputStream.write(1112396529674L, getPowerSaveModeEnabledTime(jElapsedRealtime, 0) / 1000);
        protoOutputStream.write(1120986464267L, getNumConnectivityChange(0));
        int i9 = 2;
        protoOutputStream.write(1112396529676L, getDeviceIdleModeTime(2, jElapsedRealtime, 0) / 1000);
        protoOutputStream.write(1120986464269L, getDeviceIdleModeCount(2, 0));
        protoOutputStream.write(1112396529678L, getDeviceIdlingTime(2, jElapsedRealtime, 0) / 1000);
        protoOutputStream.write(1120986464271L, getDeviceIdlingCount(2, 0));
        protoOutputStream.write(1112396529680L, getLongestDeviceIdleModeTime(2));
        protoOutputStream.write(1112396529681L, getDeviceIdleModeTime(1, jElapsedRealtime, 0) / 1000);
        protoOutputStream.write(1120986464274L, getDeviceIdleModeCount(1, 0));
        protoOutputStream.write(1112396529683L, getDeviceIdlingTime(1, jElapsedRealtime, 0) / 1000);
        protoOutputStream.write(1120986464276L, getDeviceIdlingCount(1, 0));
        protoOutputStream.write(1112396529685L, getLongestDeviceIdleModeTime(1));
        protoOutputStream.end(jStart8);
        long wifiMulticastWakelockTime = getWifiMulticastWakelockTime(jElapsedRealtime, 0);
        int wifiMulticastWakelockCount = getWifiMulticastWakelockCount(0);
        long jStart9 = protoOutputStream.start(1146756268055L);
        protoOutputStream.write(1112396529665L, wifiMulticastWakelockTime / 1000);
        long j = 1120986464258L;
        protoOutputStream.write(1120986464258L, wifiMulticastWakelockCount);
        protoOutputStream.end(jStart9);
        List<BatterySipper> usageList = batteryStatsHelper.getUsageList();
        if (usageList != null) {
            int i10 = 0;
            while (i10 < usageList.size()) {
                BatterySipper batterySipper = usageList.get(i10);
                switch (AnonymousClass2.$SwitchMap$com$android$internal$os$BatterySipper$DrainType[batterySipper.drainType.ordinal()]) {
                    case 1:
                        i = 0;
                        i2 = 13;
                        uid = i;
                        long jStart10 = protoOutputStream.start(2246267895825L);
                        list = usageList;
                        protoOutputStream.write(1159641169921L, i2);
                        protoOutputStream.write(j, uid);
                        protoOutputStream.write(1103806595075L, batterySipper.totalPowerMah);
                        protoOutputStream.write(1133871366148L, batterySipper.shouldHide);
                        protoOutputStream.write(1103806595077L, batterySipper.screenPowerMah);
                        protoOutputStream.write(1103806595078L, batterySipper.proportionalSmearMah);
                        protoOutputStream.end(jStart10);
                        break;
                    case 2:
                        uid = 0;
                        i2 = 1;
                        long jStart102 = protoOutputStream.start(2246267895825L);
                        list = usageList;
                        protoOutputStream.write(1159641169921L, i2);
                        protoOutputStream.write(j, uid);
                        protoOutputStream.write(1103806595075L, batterySipper.totalPowerMah);
                        protoOutputStream.write(1133871366148L, batterySipper.shouldHide);
                        protoOutputStream.write(1103806595077L, batterySipper.screenPowerMah);
                        protoOutputStream.write(1103806595078L, batterySipper.proportionalSmearMah);
                        protoOutputStream.end(jStart102);
                        break;
                    case 3:
                        i = 0;
                        i2 = i9;
                        uid = i;
                        long jStart1022 = protoOutputStream.start(2246267895825L);
                        list = usageList;
                        protoOutputStream.write(1159641169921L, i2);
                        protoOutputStream.write(j, uid);
                        protoOutputStream.write(1103806595075L, batterySipper.totalPowerMah);
                        protoOutputStream.write(1133871366148L, batterySipper.shouldHide);
                        protoOutputStream.write(1103806595077L, batterySipper.screenPowerMah);
                        protoOutputStream.write(1103806595078L, batterySipper.proportionalSmearMah);
                        protoOutputStream.end(jStart1022);
                        break;
                    case 4:
                        uid = 0;
                        i2 = 3;
                        long jStart10222 = protoOutputStream.start(2246267895825L);
                        list = usageList;
                        protoOutputStream.write(1159641169921L, i2);
                        protoOutputStream.write(j, uid);
                        protoOutputStream.write(1103806595075L, batterySipper.totalPowerMah);
                        protoOutputStream.write(1133871366148L, batterySipper.shouldHide);
                        protoOutputStream.write(1103806595077L, batterySipper.screenPowerMah);
                        protoOutputStream.write(1103806595078L, batterySipper.proportionalSmearMah);
                        protoOutputStream.end(jStart10222);
                        break;
                    case 5:
                        i = 0;
                        i2 = 4;
                        uid = i;
                        long jStart102222 = protoOutputStream.start(2246267895825L);
                        list = usageList;
                        protoOutputStream.write(1159641169921L, i2);
                        protoOutputStream.write(j, uid);
                        protoOutputStream.write(1103806595075L, batterySipper.totalPowerMah);
                        protoOutputStream.write(1133871366148L, batterySipper.shouldHide);
                        protoOutputStream.write(1103806595077L, batterySipper.screenPowerMah);
                        protoOutputStream.write(1103806595078L, batterySipper.proportionalSmearMah);
                        protoOutputStream.end(jStart102222);
                        break;
                    case 6:
                        uid = 0;
                        i2 = 5;
                        long jStart1022222 = protoOutputStream.start(2246267895825L);
                        list = usageList;
                        protoOutputStream.write(1159641169921L, i2);
                        protoOutputStream.write(j, uid);
                        protoOutputStream.write(1103806595075L, batterySipper.totalPowerMah);
                        protoOutputStream.write(1133871366148L, batterySipper.shouldHide);
                        protoOutputStream.write(1103806595077L, batterySipper.screenPowerMah);
                        protoOutputStream.write(1103806595078L, batterySipper.proportionalSmearMah);
                        protoOutputStream.end(jStart1022222);
                        break;
                    case 7:
                        i = 0;
                        i2 = 7;
                        uid = i;
                        long jStart10222222 = protoOutputStream.start(2246267895825L);
                        list = usageList;
                        protoOutputStream.write(1159641169921L, i2);
                        protoOutputStream.write(j, uid);
                        protoOutputStream.write(1103806595075L, batterySipper.totalPowerMah);
                        protoOutputStream.write(1133871366148L, batterySipper.shouldHide);
                        protoOutputStream.write(1103806595077L, batterySipper.screenPowerMah);
                        protoOutputStream.write(1103806595078L, batterySipper.proportionalSmearMah);
                        protoOutputStream.end(jStart10222222);
                        break;
                    case 8:
                        i = 0;
                        i2 = 6;
                        uid = i;
                        long jStart102222222 = protoOutputStream.start(2246267895825L);
                        list = usageList;
                        protoOutputStream.write(1159641169921L, i2);
                        protoOutputStream.write(j, uid);
                        protoOutputStream.write(1103806595075L, batterySipper.totalPowerMah);
                        protoOutputStream.write(1133871366148L, batterySipper.shouldHide);
                        protoOutputStream.write(1103806595077L, batterySipper.screenPowerMah);
                        protoOutputStream.write(1103806595078L, batterySipper.proportionalSmearMah);
                        protoOutputStream.end(jStart102222222);
                        break;
                    case 9:
                        list = usageList;
                        break;
                    case 10:
                        i2 = 8;
                        uid = UserHandle.getUid(batterySipper.userId, 0);
                        long jStart1022222222 = protoOutputStream.start(2246267895825L);
                        list = usageList;
                        protoOutputStream.write(1159641169921L, i2);
                        protoOutputStream.write(j, uid);
                        protoOutputStream.write(1103806595075L, batterySipper.totalPowerMah);
                        protoOutputStream.write(1133871366148L, batterySipper.shouldHide);
                        protoOutputStream.write(1103806595077L, batterySipper.screenPowerMah);
                        protoOutputStream.write(1103806595078L, batterySipper.proportionalSmearMah);
                        protoOutputStream.end(jStart1022222222);
                        break;
                    case 11:
                        i2 = 9;
                        uid = 0;
                        long jStart10222222222 = protoOutputStream.start(2246267895825L);
                        list = usageList;
                        protoOutputStream.write(1159641169921L, i2);
                        protoOutputStream.write(j, uid);
                        protoOutputStream.write(1103806595075L, batterySipper.totalPowerMah);
                        protoOutputStream.write(1133871366148L, batterySipper.shouldHide);
                        protoOutputStream.write(1103806595077L, batterySipper.screenPowerMah);
                        protoOutputStream.write(1103806595078L, batterySipper.proportionalSmearMah);
                        protoOutputStream.end(jStart10222222222);
                        break;
                    case 12:
                        i2 = 10;
                        uid = 0;
                        long jStart102222222222 = protoOutputStream.start(2246267895825L);
                        list = usageList;
                        protoOutputStream.write(1159641169921L, i2);
                        protoOutputStream.write(j, uid);
                        protoOutputStream.write(1103806595075L, batterySipper.totalPowerMah);
                        protoOutputStream.write(1133871366148L, batterySipper.shouldHide);
                        protoOutputStream.write(1103806595077L, batterySipper.screenPowerMah);
                        protoOutputStream.write(1103806595078L, batterySipper.proportionalSmearMah);
                        protoOutputStream.end(jStart102222222222);
                        break;
                    case 13:
                        i2 = 11;
                        uid = 0;
                        long jStart1022222222222 = protoOutputStream.start(2246267895825L);
                        list = usageList;
                        protoOutputStream.write(1159641169921L, i2);
                        protoOutputStream.write(j, uid);
                        protoOutputStream.write(1103806595075L, batterySipper.totalPowerMah);
                        protoOutputStream.write(1133871366148L, batterySipper.shouldHide);
                        protoOutputStream.write(1103806595077L, batterySipper.screenPowerMah);
                        protoOutputStream.write(1103806595078L, batterySipper.proportionalSmearMah);
                        protoOutputStream.end(jStart1022222222222);
                        break;
                    case 14:
                        i2 = 12;
                        uid = 0;
                        long jStart10222222222222 = protoOutputStream.start(2246267895825L);
                        list = usageList;
                        protoOutputStream.write(1159641169921L, i2);
                        protoOutputStream.write(j, uid);
                        protoOutputStream.write(1103806595075L, batterySipper.totalPowerMah);
                        protoOutputStream.write(1133871366148L, batterySipper.shouldHide);
                        protoOutputStream.write(1103806595077L, batterySipper.screenPowerMah);
                        protoOutputStream.write(1103806595078L, batterySipper.proportionalSmearMah);
                        protoOutputStream.end(jStart10222222222222);
                        break;
                    default:
                        i2 = 0;
                        uid = 0;
                        long jStart102222222222222 = protoOutputStream.start(2246267895825L);
                        list = usageList;
                        protoOutputStream.write(1159641169921L, i2);
                        protoOutputStream.write(j, uid);
                        protoOutputStream.write(1103806595075L, batterySipper.totalPowerMah);
                        protoOutputStream.write(1133871366148L, batterySipper.shouldHide);
                        protoOutputStream.write(1103806595077L, batterySipper.screenPowerMah);
                        protoOutputStream.write(1103806595078L, batterySipper.proportionalSmearMah);
                        protoOutputStream.end(jStart102222222222222);
                        break;
                }
                i10++;
                usageList = list;
                j = 1120986464258L;
                i9 = 2;
            }
        }
        long jStart11 = protoOutputStream.start(1146756268050L);
        protoOutputStream.write(1103806595073L, batteryStatsHelper.getPowerProfile().getBatteryCapacity());
        protoOutputStream.write(1103806595074L, batteryStatsHelper.getComputedPower());
        protoOutputStream.write(1103806595075L, batteryStatsHelper.getMinDrainedPower());
        protoOutputStream.write(1103806595076L, batteryStatsHelper.getMaxDrainedPower());
        protoOutputStream.end(jStart11);
        Map<String, ? extends Timer> rpmStats = getRpmStats();
        Map<String, ? extends Timer> screenOffRpmStats = getScreenOffRpmStats();
        for (Iterator<Map.Entry<String, ? extends Timer>> it = rpmStats.entrySet().iterator(); it.hasNext(); it = it) {
            Map.Entry<String, ? extends Timer> next = it.next();
            long jStart12 = protoOutputStream.start(2246267895827L);
            protoOutputStream.write(1138166333441L, next.getKey());
            dumpTimer(protoOutputStream, 1146756268034L, next.getValue(), jElapsedRealtime, 0);
            dumpTimer(protoOutputStream, 1146756268035L, screenOffRpmStats.get(next.getKey()), jElapsedRealtime, 0);
            protoOutputStream.end(jStart12);
        }
        for (int i11 = 0; i11 < 5; i11++) {
            long jStart13 = protoOutputStream.start(2246267895828L);
            protoOutputStream.write(1159641169921L, i11);
            dumpTimer(protoOutputStream, 1146756268034L, getScreenBrightnessTimer(i11), jElapsedRealtime, 0);
            protoOutputStream.end(jStart13);
        }
        dumpTimer(protoOutputStream, 1146756268053L, getPhoneSignalScanningTimer(), jElapsedRealtime, 0);
        for (int i12 = 0; i12 < CellSignalStrength.getNumSignalStrengthLevels(); i12++) {
            long jStart14 = protoOutputStream.start(2246267895824L);
            protoOutputStream.write(1159641169921L, i12);
            dumpTimer(protoOutputStream, 1146756268034L, getPhoneSignalStrengthTimer(i12), jElapsedRealtime, 0);
            protoOutputStream.end(jStart14);
        }
        for (Map.Entry<String, ? extends Timer> entry2 : getWakeupReasonStats().entrySet()) {
            long jStart15 = protoOutputStream.start(2246267895830L);
            protoOutputStream.write(1138166333441L, entry2.getKey());
            dumpTimer(protoOutputStream, 1146756268034L, entry2.getValue(), jElapsedRealtime, 0);
            protoOutputStream.end(jStart15);
        }
        for (int i13 = 0; i13 < 5; i13++) {
            long jStart16 = protoOutputStream.start(2246267895832L);
            protoOutputStream.write(1159641169921L, i13);
            dumpTimer(protoOutputStream, 1146756268034L, getWifiSignalStrengthTimer(i13), jElapsedRealtime, 0);
            protoOutputStream.end(jStart16);
        }
        for (int i14 = 0; i14 < 8; i14++) {
            long jStart17 = protoOutputStream.start(2246267895833L);
            protoOutputStream.write(1159641169921L, i14);
            dumpTimer(protoOutputStream, 1146756268034L, getWifiStateTimer(i14), jElapsedRealtime, 0);
            protoOutputStream.end(jStart17);
        }
        for (int i15 = 0; i15 < 13; i15++) {
            long jStart18 = protoOutputStream.start(2246267895834L);
            protoOutputStream.write(1159641169921L, i15);
            dumpTimer(protoOutputStream, 1146756268034L, getWifiSupplStateTimer(i15), jElapsedRealtime, 0);
            protoOutputStream.end(jStart18);
        }
        protoOutputStream.end(jStart);
    }
}
