package com.android.bluetooth.gatt;

import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanSettings;
import android.net.INetd;
import android.os.Binder;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.SystemClock;
import android.os.WorkSource;
import com.android.bluetooth.BluetoothMetricsProto;
import com.android.bluetooth.BluetoothStatsLog;
import com.android.bluetooth.gatt.ContextMap;
import com.android.internal.app.IBatteryStats;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
class AppScanStats {
    static final int BALANCED_WEIGHT = 25;
    static final long EXCESSIVE_SCANNING_PERIOD_MS = 30000;
    static final int LOW_LATENCY_WEIGHT = 100;
    static final int LOW_POWER_WEIGHT = 10;
    static final int NUM_SCAN_DURATIONS_KEPT = 5;
    static final int OPPORTUNISTIC_WEIGHT = 0;
    static final int SCAN_TIMEOUT_MS = 1800000;
    public String appName;
    ContextMap mContextMap;
    GattService mGattService;
    public WorkSource mWorkSource;
    private static final String TAG = AppScanStats.class.getSimpleName();
    static final DateFormat DATE_FORMAT = new SimpleDateFormat("MM-dd HH:mm:ss");
    private int mScansStarted = 0;
    private int mScansStopped = 0;
    public boolean isRegistered = false;
    private long mScanStartTime = 0;
    private long mTotalActiveTime = 0;
    private long mTotalSuspendTime = 0;
    private long mTotalScanTime = 0;
    private long mOppScanTime = 0;
    private long mLowPowerScanTime = 0;
    private long mBalancedScanTime = 0;
    private long mLowLantencyScanTime = 0;
    private int mOppScan = 0;
    private int mLowPowerScan = 0;
    private int mBalancedScan = 0;
    private int mLowLantencyScan = 0;
    private List<LastScan> mLastScans = new ArrayList(5);
    private HashMap<Integer, LastScan> mOngoingScans = new HashMap<>();
    public long startTime = 0;
    public long stopTime = 0;
    public int results = 0;
    IBatteryStats mBatteryStats = IBatteryStats.Stub.asInterface(ServiceManager.getService("batterystats"));

    class LastScan {
        public boolean isCallbackScan;
        public boolean isFilterScan;
        public int scanCallbackType;
        public int scanMode;
        public int scannerId;
        public long timestamp;
        public long duration = 0;
        public boolean isOpportunisticScan = false;
        public boolean isTimeout = false;
        public boolean isBackgroundScan = false;
        public boolean isBatchScan = false;
        public int results = 0;
        public long suspendDuration = 0;
        public long suspendStartTime = 0;
        public boolean isSuspended = false;
        public String filterString = INetd.NEXTHOP_NONE;

        LastScan(long timestamp, boolean isFilterScan, boolean isCallbackScan, int scannerId, int scanMode, int scanCallbackType) {
            this.timestamp = timestamp;
            this.isFilterScan = isFilterScan;
            this.isCallbackScan = isCallbackScan;
            this.scanMode = scanMode;
            this.scanCallbackType = scanCallbackType;
            this.scannerId = scannerId;
        }
    }

    AppScanStats(String name, WorkSource source, ContextMap map, GattService service) {
        this.appName = name;
        this.mContextMap = map;
        this.mGattService = service;
        this.mWorkSource = source == null ? new WorkSource(Binder.getCallingUid(), this.appName) : source;
    }

    synchronized void addResult(int scannerId) {
        LastScan scan = getScanFromScannerId(scannerId);
        if (scan != null) {
            scan.results++;
            if (scan.results % 100 == 0) {
                try {
                    this.mBatteryStats.noteBleScanResults(this.mWorkSource, 100);
                } catch (RemoteException e) {
                }
                BluetoothStatsLog.write(4, this.mWorkSource, 100);
            }
        }
        this.results++;
    }

    boolean isScanning() {
        return !this.mOngoingScans.isEmpty();
    }

    LastScan getScanFromScannerId(int scannerId) {
        return this.mOngoingScans.get(Integer.valueOf(scannerId));
    }

    synchronized void recordScanStart(ScanSettings settings, List<ScanFilter> filters, boolean isFilterScan, boolean isCallbackScan, int scannerId) {
        LastScan existingScan = getScanFromScannerId(scannerId);
        if (existingScan != null) {
            return;
        }
        boolean z = true;
        this.mScansStarted++;
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        this.startTime = jElapsedRealtime;
        LastScan scan = new LastScan(jElapsedRealtime, isFilterScan, isCallbackScan, scannerId, settings.getScanMode(), settings.getCallbackType());
        if (settings != null) {
            scan.isOpportunisticScan = scan.scanMode == -1;
            scan.isBackgroundScan = (scan.scanCallbackType & 2) != 0;
            scan.isBatchScan = settings.getCallbackType() == 1 && settings.getReportDelayMillis() != 0;
            int i = scan.scanMode;
            if (i == -1) {
                this.mOppScan++;
            } else if (i == 0) {
                this.mLowPowerScan++;
            } else if (i == 1) {
                this.mBalancedScan++;
            } else if (i == 2) {
                this.mLowLantencyScan++;
            }
        }
        if (isFilterScan) {
            for (ScanFilter filter : filters) {
                scan.filterString += "\n      └ " + filterToStringWithoutNullParam(filter);
            }
        }
        BluetoothMetricsProto.ScanEvent scanEvent = BluetoothMetricsProto.ScanEvent.newBuilder().setScanEventType(BluetoothMetricsProto.ScanEvent.ScanEventType.SCAN_EVENT_START).setScanTechnologyType(BluetoothMetricsProto.ScanEvent.ScanTechnologyType.SCAN_TECH_TYPE_LE).setEventTimeMillis(System.currentTimeMillis()).setInitiator(truncateAppName(this.appName)).build();
        this.mGattService.addScanEvent(scanEvent);
        if (!isScanning()) {
            this.mScanStartTime = this.startTime;
        }
        try {
            if (scan.isFilterScan || scan.isBackgroundScan || scan.isOpportunisticScan) {
                z = false;
            }
            boolean isUnoptimized = z;
            this.mBatteryStats.noteBleScanStarted(this.mWorkSource, isUnoptimized);
        } catch (RemoteException e) {
        }
        BluetoothStatsLog.write(2, this.mWorkSource, 1, scan.isFilterScan, scan.isBackgroundScan, scan.isOpportunisticScan);
        this.mOngoingScans.put(Integer.valueOf(scannerId), scan);
    }

    synchronized void recordScanStop(int scannerId) {
        LastScan scan = getScanFromScannerId(scannerId);
        if (scan == null) {
            return;
        }
        boolean z = true;
        this.mScansStopped++;
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        this.stopTime = jElapsedRealtime;
        long scanDuration = jElapsedRealtime - scan.timestamp;
        scan.duration = scanDuration;
        if (scan.isSuspended) {
            long suspendDuration = this.stopTime - scan.suspendStartTime;
            scan.suspendDuration += suspendDuration;
            this.mTotalSuspendTime += suspendDuration;
        }
        this.mOngoingScans.remove(Integer.valueOf(scannerId));
        if (this.mLastScans.size() >= 5) {
            this.mLastScans.remove(0);
        }
        this.mLastScans.add(scan);
        BluetoothMetricsProto.ScanEvent scanEvent = BluetoothMetricsProto.ScanEvent.newBuilder().setScanEventType(BluetoothMetricsProto.ScanEvent.ScanEventType.SCAN_EVENT_STOP).setScanTechnologyType(BluetoothMetricsProto.ScanEvent.ScanTechnologyType.SCAN_TECH_TYPE_LE).setEventTimeMillis(System.currentTimeMillis()).setInitiator(truncateAppName(this.appName)).setNumberResults(scan.results).build();
        this.mGattService.addScanEvent(scanEvent);
        this.mTotalScanTime += scanDuration;
        long activeDuration = scanDuration - scan.suspendDuration;
        this.mTotalActiveTime += activeDuration;
        int i = scan.scanMode;
        if (i == -1) {
            this.mOppScanTime += activeDuration;
        } else if (i == 0) {
            this.mLowPowerScanTime += activeDuration;
        } else if (i == 1) {
            this.mBalancedScanTime += activeDuration;
        } else if (i == 2) {
            this.mLowLantencyScanTime += activeDuration;
        }
        try {
            if (scan.isFilterScan || scan.isBackgroundScan || scan.isOpportunisticScan) {
                z = false;
            }
            boolean isUnoptimized = z;
            this.mBatteryStats.noteBleScanResults(this.mWorkSource, scan.results % 100);
            this.mBatteryStats.noteBleScanStopped(this.mWorkSource, isUnoptimized);
        } catch (RemoteException e) {
        }
        BluetoothStatsLog.write(4, this.mWorkSource, scan.results % 100);
        BluetoothStatsLog.write(2, this.mWorkSource, 0, scan.isFilterScan, scan.isBackgroundScan, scan.isOpportunisticScan);
    }

    synchronized void recordScanSuspend(int scannerId) {
        LastScan scan = getScanFromScannerId(scannerId);
        if (scan != null && !scan.isSuspended) {
            scan.suspendStartTime = SystemClock.elapsedRealtime();
            scan.isSuspended = true;
        }
    }

    synchronized void recordScanResume(int scannerId) {
        LastScan scan = getScanFromScannerId(scannerId);
        if (scan != null && scan.isSuspended) {
            scan.isSuspended = false;
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            this.stopTime = jElapsedRealtime;
            long suspendDuration = jElapsedRealtime - scan.suspendStartTime;
            scan.suspendDuration += suspendDuration;
            this.mTotalSuspendTime += suspendDuration;
        }
    }

    synchronized void setScanTimeout(int scannerId) {
        if (isScanning()) {
            LastScan scan = getScanFromScannerId(scannerId);
            if (scan != null) {
                scan.isTimeout = true;
            }
        }
    }

    synchronized boolean isScanningTooFrequently() {
        if (this.mLastScans.size() < 5) {
            return false;
        }
        return SystemClock.elapsedRealtime() - this.mLastScans.get(0).timestamp < EXCESSIVE_SCANNING_PERIOD_MS;
    }

    synchronized boolean isScanningTooLong() {
        if (isScanning()) {
            return SystemClock.elapsedRealtime() - this.mScanStartTime > 1800000;
        }
        return false;
    }

    private String truncateAppName(String name) {
        String[] nameSplit = name.split("\\.");
        if (nameSplit.length > 3) {
            String initiator = nameSplit[0] + "." + nameSplit[1] + "." + nameSplit[2];
            return initiator;
        }
        if (nameSplit.length != 3) {
            return name;
        }
        String initiator2 = nameSplit[0] + "." + nameSplit[1];
        return initiator2;
    }

    private static String filterToStringWithoutNullParam(ScanFilter filter) {
        String filterString = "BluetoothLeScanFilter [";
        if (filter.getDeviceName() != null) {
            filterString = "BluetoothLeScanFilter [ DeviceName=" + filter.getDeviceName();
        }
        if (filter.getDeviceAddress() != null) {
            filterString = filterString + " DeviceAddress=" + filter.getDeviceAddress();
        }
        if (filter.getServiceUuid() != null) {
            filterString = filterString + " ServiceUuid=" + filter.getServiceUuid();
        }
        if (filter.getServiceUuidMask() != null) {
            filterString = filterString + " ServiceUuidMask=" + filter.getServiceUuidMask();
        }
        if (filter.getServiceSolicitationUuid() != null) {
            filterString = filterString + " ServiceSolicitationUuid=" + filter.getServiceSolicitationUuid();
        }
        if (filter.getServiceSolicitationUuidMask() != null) {
            filterString = filterString + " ServiceSolicitationUuidMask=" + filter.getServiceSolicitationUuidMask();
        }
        if (filter.getServiceDataUuid() != null) {
            filterString = filterString + " ServiceDataUuid=" + Objects.toString(filter.getServiceDataUuid());
        }
        if (filter.getServiceData() != null) {
            filterString = filterString + " ServiceData=" + Arrays.toString(filter.getServiceData());
        }
        if (filter.getServiceDataMask() != null) {
            filterString = filterString + " ServiceDataMask=" + Arrays.toString(filter.getServiceDataMask());
        }
        if (filter.getManufacturerId() >= 0) {
            filterString = filterString + " ManufacturerId=" + filter.getManufacturerId();
        }
        if (filter.getManufacturerData() != null) {
            filterString = filterString + " ManufacturerData=" + Arrays.toString(filter.getManufacturerData());
        }
        if (filter.getManufacturerDataMask() != null) {
            filterString = filterString + " ManufacturerDataMask=" + Arrays.toString(filter.getManufacturerDataMask());
        }
        return filterString + " ]";
    }

    private static String scanModeToString(int scanMode) {
        if (scanMode == -1) {
            return "OPPORTUNISTIC";
        }
        if (scanMode == 0) {
            return "LOW_POWER";
        }
        if (scanMode == 1) {
            return "BALANCED";
        }
        if (scanMode == 2) {
            return "LOW_LATENCY";
        }
        return "UNKNOWN(" + scanMode + ")";
    }

    private static String callbackTypeToString(int callbackType) {
        if (callbackType == 1) {
            return "ALL_MATCHES";
        }
        if (callbackType == 2) {
            return "FIRST_MATCH";
        }
        if (callbackType == 4) {
            return "LOST";
        }
        if (callbackType == 6) {
            return "[FIRST_MATCH | LOST]";
        }
        return "UNKNOWN: " + callbackType;
    }

    synchronized void dumpToString(StringBuilder sb) {
        long oppScanTime;
        long activeDuration;
        int oppScan;
        int lowPowerScan;
        int balancedScan;
        int lowLatencyScan;
        long totalActiveTime;
        long totalScanTime;
        long totalActiveTime2;
        long totalScanTime2;
        long currTime;
        long totalSuspendTime;
        long currentTime;
        Iterator<Integer> it;
        long totalScanTime3;
        long currentTime2 = System.currentTimeMillis();
        long currTime2 = SystemClock.elapsedRealtime();
        long activeDuration2 = 0;
        long totalActiveTime3 = this.mTotalActiveTime;
        long Score = this.mTotalSuspendTime;
        long totalSuspendTime2 = Score;
        long totalScanTime4 = this.mTotalScanTime;
        long oppScanTime2 = this.mOppScanTime;
        long lowPowerScanTime = this.mLowPowerScanTime;
        long balancedScanTime = this.mBalancedScanTime;
        long lowLatencyScanTime = this.mLowLantencyScanTime;
        int oppScan2 = this.mOppScan;
        int lowPowerScan2 = this.mLowPowerScan;
        int balancedScan2 = this.mBalancedScan;
        int lowLatencyScan2 = this.mLowLantencyScan;
        long suspendDuration = 0;
        if (this.mOngoingScans.isEmpty()) {
            oppScanTime = oppScanTime2;
            activeDuration = lowPowerScanTime;
            oppScan = oppScan2;
            lowPowerScan = lowPowerScan2;
            balancedScan = balancedScan2;
            lowLatencyScan = lowLatencyScan2;
            totalActiveTime = totalActiveTime3;
            totalScanTime = totalScanTime4;
            totalActiveTime2 = balancedScanTime;
            totalScanTime2 = currTime2;
            currTime = totalSuspendTime2;
            totalSuspendTime = currentTime2;
            currentTime = lowLatencyScanTime;
        } else {
            Iterator<Integer> it2 = this.mOngoingScans.keySet().iterator();
            while (it2.hasNext()) {
                Integer key = it2.next();
                Iterator<Integer> it3 = it2;
                LastScan scan = this.mOngoingScans.get(key);
                long scanDuration = currTime2 - scan.timestamp;
                if (scan.isSuspended) {
                    long suspendDuration2 = currTime2 - scan.suspendStartTime;
                    totalSuspendTime2 += suspendDuration2;
                    suspendDuration = suspendDuration2;
                }
                totalScanTime4 += scanDuration;
                totalSuspendTime2 += suspendDuration;
                long activeDuration3 = (scanDuration - scan.suspendDuration) - suspendDuration;
                totalActiveTime3 += activeDuration3;
                int i = scan.scanMode;
                if (i == -1) {
                    oppScanTime2 += activeDuration3;
                } else if (i == 0) {
                    lowPowerScanTime += activeDuration3;
                } else if (i == 1) {
                    balancedScanTime += activeDuration3;
                } else if (i == 2) {
                    lowLatencyScanTime += activeDuration3;
                }
                activeDuration2 = activeDuration3;
                it2 = it3;
            }
            oppScanTime = oppScanTime2;
            activeDuration = lowPowerScanTime;
            oppScan = oppScan2;
            lowPowerScan = lowPowerScan2;
            balancedScan = balancedScan2;
            lowLatencyScan = lowLatencyScan2;
            totalActiveTime = totalActiveTime3;
            totalScanTime = totalScanTime4;
            totalActiveTime2 = balancedScanTime;
            totalScanTime2 = currTime2;
            currTime = totalSuspendTime2;
            totalSuspendTime = currentTime2;
            currentTime = lowLatencyScanTime;
        }
        long Score2 = ((((oppScanTime * 0) + (10 * activeDuration)) + (25 * totalActiveTime2)) + (currentTime * 100)) / 100;
        long Score3 = currentTime;
        sb.append("  " + this.appName);
        if (this.isRegistered) {
            sb.append(" (Registered)");
        }
        sb.append("\n  LE scans (started/stopped)                                  : " + this.mScansStarted + " / " + this.mScansStopped);
        sb.append("\n  Scan time in ms (active/suspend/total)                      : " + totalActiveTime + " / " + currTime + " / " + totalScanTime);
        sb.append("\n  Scan time with mode in ms (Opp/LowPower/Balanced/LowLatency): " + oppScanTime + " / " + activeDuration + " / " + totalActiveTime2 + " / " + Score3);
        StringBuilder sb2 = new StringBuilder();
        sb2.append("\n  Scan mode counter (Opp/LowPower/Balanced/LowLatency)        : ");
        int oppScan3 = oppScan;
        sb2.append(oppScan3);
        sb2.append(" / ");
        sb2.append(lowPowerScan);
        sb2.append(" / ");
        sb2.append(balancedScan);
        sb2.append(" / ");
        sb2.append(lowLatencyScan);
        sb.append(sb2.toString());
        sb.append("\n  Score                                                       : " + Score2);
        sb.append("\n  Total number of results                                     : " + this.results);
        if (!this.mLastScans.isEmpty()) {
            sb.append("\n  Last " + this.mLastScans.size() + " scans                                                :");
            int i2 = 0;
            while (i2 < this.mLastScans.size()) {
                LastScan scan2 = this.mLastScans.get(i2);
                int oppScan4 = oppScan3;
                long totalActiveTime4 = totalActiveTime;
                Date timestamp = new Date((totalSuspendTime - totalScanTime2) + scan2.timestamp);
                sb.append("\n    " + DATE_FORMAT.format(timestamp) + " - ");
                StringBuilder sb3 = new StringBuilder();
                sb3.append(scan2.duration);
                sb3.append("ms ");
                sb.append(sb3.toString());
                if (scan2.isOpportunisticScan) {
                    sb.append("Opp ");
                }
                if (scan2.isBackgroundScan) {
                    sb.append("Back ");
                }
                if (scan2.isTimeout) {
                    sb.append("Forced ");
                }
                if (scan2.isFilterScan) {
                    sb.append("Filter ");
                }
                sb.append(scan2.results + " results");
                sb.append(" (" + scan2.scannerId + ") ");
                if (scan2.isCallbackScan) {
                    sb.append("CB ");
                } else {
                    sb.append("PI ");
                }
                if (scan2.isBatchScan) {
                    sb.append("Batch Scan");
                } else {
                    sb.append("Regular Scan");
                }
                if (scan2.suspendDuration == 0) {
                    totalScanTime3 = totalScanTime;
                } else {
                    totalScanTime3 = totalScanTime;
                    long activeDuration4 = scan2.duration - scan2.suspendDuration;
                    sb.append("\n      └ Suspended Time: " + scan2.suspendDuration + "ms, Active Time: " + activeDuration4);
                }
                sb.append("\n      └ Scan Config: [ ScanMode=" + scanModeToString(scan2.scanMode) + ", callbackType=" + callbackTypeToString(scan2.scanCallbackType) + " ]");
                if (scan2.isFilterScan) {
                    sb.append(scan2.filterString);
                }
                i2++;
                oppScan3 = oppScan4;
                totalScanTime = totalScanTime3;
                totalActiveTime = totalActiveTime4;
            }
        }
        if (!this.mOngoingScans.isEmpty()) {
            sb.append("\n  Ongoing scans                                               :");
            Iterator<Integer> it4 = this.mOngoingScans.keySet().iterator();
            while (it4.hasNext()) {
                Integer key2 = it4.next();
                LastScan scan3 = this.mOngoingScans.get(key2);
                Date timestamp2 = new Date((totalSuspendTime - totalScanTime2) + scan3.timestamp);
                sb.append("\n    " + DATE_FORMAT.format(timestamp2) + " - ");
                StringBuilder sb4 = new StringBuilder();
                sb4.append(totalScanTime2 - scan3.timestamp);
                sb4.append("ms ");
                sb.append(sb4.toString());
                if (scan3.isOpportunisticScan) {
                    sb.append("Opp ");
                }
                if (scan3.isBackgroundScan) {
                    sb.append("Back ");
                }
                if (scan3.isTimeout) {
                    sb.append("Forced ");
                }
                if (scan3.isFilterScan) {
                    sb.append("Filter ");
                }
                if (scan3.isSuspended) {
                    sb.append("Suspended ");
                }
                sb.append(scan3.results + " results");
                sb.append(" (" + scan3.scannerId + ") ");
                if (scan3.isCallbackScan) {
                    sb.append("CB ");
                } else {
                    sb.append("PI ");
                }
                if (scan3.isBatchScan) {
                    sb.append("Batch Scan");
                } else {
                    sb.append("Regular Scan");
                }
                if (scan3.suspendStartTime == 0) {
                    it = it4;
                } else {
                    long j = scan3.suspendDuration + (scan3.isSuspended ? totalScanTime2 - scan3.suspendStartTime : 0L);
                    it = it4;
                    long activeDuration5 = scan3.duration - scan3.suspendDuration;
                    sb.append("\n      └ Suspended Time:" + scan3.suspendDuration + "ms, Active Time:" + activeDuration5);
                }
                sb.append("\n      └ Scan Config: [ ScanMode=" + scanModeToString(scan3.scanMode) + ", callbackType=" + callbackTypeToString(scan3.scanCallbackType) + " ]");
                if (scan3.isFilterScan) {
                    sb.append(scan3.filterString);
                }
                it4 = it;
            }
        }
        ContextMap.App appEntry = this.mContextMap.getByName(this.appName);
        if (appEntry != null && this.isRegistered) {
            sb.append("\n  Application ID                     : " + appEntry.id);
            sb.append("\n  UUID                               : " + appEntry.uuid);
            List<ContextMap.Connection> connections = this.mContextMap.getConnectionByApp(appEntry.id);
            sb.append("\n  Connections: " + connections.size());
            for (ContextMap.Connection connection : connections) {
                long connectionTime = totalScanTime2 - connection.startTime;
                List<ContextMap.Connection> connections2 = connections;
                Date timestamp3 = new Date((totalSuspendTime - totalScanTime2) + connection.startTime);
                sb.append("\n    " + DATE_FORMAT.format(timestamp3) + " - ");
                StringBuilder sb5 = new StringBuilder();
                sb5.append(connectionTime);
                sb5.append("ms ");
                sb.append(sb5.toString());
                sb.append(": " + connection.address + " (" + connection.connId + ")");
                appEntry = appEntry;
                connections = connections2;
            }
        }
        sb.append("\n\n");
    }
}
