package com.android.bluetooth.gatt;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanSettings;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.display.DisplayManager;
import android.location.LocationManager;
import android.net.Uri;
import android.net.util.NetworkConstants;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.os.SystemClock;
import android.provider.Settings;
import android.util.Log;
import android.view.Display;
import androidx.core.app.NotificationCompat;
import com.android.bluetooth.Utils;
import com.android.bluetooth.btservice.AdapterService;
import com.android.bluetooth.gatt.ScanFilterQueue;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class ScanManager {
    private static final String ACTION_REFRESH_BATCHED_SCAN = "com.android.bluetooth.gatt.REFRESH_BATCHED_SCAN";
    private static final boolean DBG = false;
    private static final int FOREGROUND_IMPORTANCE_CUTOFF = 125;
    private static final int MSG_FLUSH_BATCH_RESULTS = 2;
    private static final int MSG_IMPORTANCE_CHANGE = 6;
    private static final int MSG_RESUME_SCANS = 5;
    private static final int MSG_SCAN_TIMEOUT = 3;
    private static final int MSG_START_BLE_SCAN = 0;
    private static final int MSG_STOP_BLE_SCAN = 1;
    private static final int MSG_SUSPEND_SCANS = 4;
    private static final int OPERATION_TIME_OUT_MILLIS = 500;
    static final int SCAN_RESULT_TYPE_BOTH = 3;
    static final int SCAN_RESULT_TYPE_FULL = 2;
    static final int SCAN_RESULT_TYPE_TRUNCATED = 1;
    private static final String TAG = "BtGatt.ScanManager";
    private ActivityManager mActivityManager;
    private BroadcastReceiver mBatchAlarmReceiver;
    private boolean mBatchAlarmReceiverRegistered;
    private BatchScanParams mBatchScanParms;
    private DisplayManager mDm;
    private volatile ClientHandler mHandler;
    private CountDownLatch mLatch;
    private LocationManager mLocationManager;
    private GattService mService;
    private int mLastConfiguredScanSetting = Integer.MIN_VALUE;
    private final DisplayManager.DisplayListener mDisplayListener = new DisplayManager.DisplayListener() { // from class: com.android.bluetooth.gatt.ScanManager.1
        @Override // android.hardware.display.DisplayManager.DisplayListener
        public void onDisplayAdded(int displayId) {
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public void onDisplayRemoved(int displayId) {
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public void onDisplayChanged(int displayId) {
            if (!ScanManager.this.isScreenOn() || !ScanManager.this.mLocationManager.isLocationEnabled()) {
                ScanManager.this.sendMessage(4, null);
            } else {
                ScanManager.this.sendMessage(5, null);
            }
        }
    };
    private ActivityManager.OnUidImportanceListener mUidImportanceListener = new ActivityManager.OnUidImportanceListener() { // from class: com.android.bluetooth.gatt.ScanManager.2
        public void onUidImportance(int uid, int importance) {
            if (ScanManager.this.mService.mScannerMap.getAppScanStatsByUid(uid) != null) {
                Message message = new Message();
                message.what = 6;
                message.obj = ScanManager.this.new UidImportance(uid, importance);
                ScanManager.this.mHandler.sendMessage(message);
            }
        }
    };
    private BroadcastReceiver mLocationReceiver = new BroadcastReceiver() { // from class: com.android.bluetooth.gatt.ScanManager.3
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("android.location.MODE_CHANGED".equals(action)) {
                boolean locationEnabled = ScanManager.this.mLocationManager.isLocationEnabled();
                if (!locationEnabled || !ScanManager.this.isScreenOn()) {
                    ScanManager.this.sendMessage(4, null);
                } else {
                    ScanManager.this.sendMessage(5, null);
                }
            }
        }
    };
    private Set<ScanClient> mRegularScanClients = Collections.newSetFromMap(new ConcurrentHashMap());
    private Set<ScanClient> mBatchClients = Collections.newSetFromMap(new ConcurrentHashMap());
    private Set<ScanClient> mSuspendedScanClients = Collections.newSetFromMap(new ConcurrentHashMap());
    private ScanNative mScanNative = new ScanNative();
    private Integer mCurUsedTrackableAdvertisements = 0;

    private class UidImportance {
        public int importance;
        public int uid;

        UidImportance(int uid, int importance) {
            this.uid = uid;
            this.importance = importance;
        }
    }

    ScanManager(GattService service) {
        this.mService = service;
        this.mDm = (DisplayManager) this.mService.getSystemService("display");
        this.mActivityManager = (ActivityManager) this.mService.getSystemService("activity");
        this.mLocationManager = (LocationManager) this.mService.getSystemService("location");
    }

    void start() {
        HandlerThread thread = new HandlerThread("BluetoothScanManager");
        thread.start();
        this.mHandler = new ClientHandler(thread.getLooper());
        DisplayManager displayManager = this.mDm;
        if (displayManager != null) {
            displayManager.registerDisplayListener(this.mDisplayListener, null);
        }
        ActivityManager activityManager = this.mActivityManager;
        if (activityManager != null) {
            activityManager.addOnUidImportanceListener(this.mUidImportanceListener, 125);
        }
        IntentFilter locationIntentFilter = new IntentFilter("android.location.MODE_CHANGED");
        this.mService.registerReceiver(this.mLocationReceiver, locationIntentFilter);
    }

    void cleanup() {
        this.mRegularScanClients.clear();
        this.mBatchClients.clear();
        this.mSuspendedScanClients.clear();
        this.mScanNative.cleanup();
        ActivityManager activityManager = this.mActivityManager;
        if (activityManager != null) {
            try {
                activityManager.removeOnUidImportanceListener(this.mUidImportanceListener);
            } catch (IllegalArgumentException e) {
                Log.w(TAG, "exception when invoking removeOnUidImportanceListener", e);
            }
        }
        DisplayManager displayManager = this.mDm;
        if (displayManager != null) {
            displayManager.unregisterDisplayListener(this.mDisplayListener);
        }
        if (this.mHandler != null) {
            this.mHandler.removeCallbacksAndMessages(null);
            Looper looper = this.mHandler.getLooper();
            if (looper != null) {
                looper.quitSafely();
            }
            this.mHandler = null;
        }
        try {
            this.mService.unregisterReceiver(this.mLocationReceiver);
        } catch (IllegalArgumentException e2) {
            Log.w(TAG, "exception when invoking unregisterReceiver(mLocationReceiver)", e2);
        }
    }

    void registerScanner(UUID uuid) {
        this.mScanNative.registerScannerNative(uuid.getLeastSignificantBits(), uuid.getMostSignificantBits());
    }

    void unregisterScanner(int scannerId) {
        this.mScanNative.unregisterScannerNative(scannerId);
    }

    Set<ScanClient> getRegularScanQueue() {
        return this.mRegularScanClients;
    }

    Set<ScanClient> getBatchScanQueue() {
        return this.mBatchClients;
    }

    Set<ScanClient> getFullBatchScanQueue() {
        Set<ScanClient> fullBatchClients = new HashSet<>();
        for (ScanClient client : this.mBatchClients) {
            if (client.settings.getScanResultType() == 0) {
                fullBatchClients.add(client);
            }
        }
        return fullBatchClients;
    }

    void startScan(ScanClient client) {
        sendMessage(0, client);
    }

    void stopScan(int scannerId) {
        ScanClient client = this.mScanNative.getBatchScanClient(scannerId);
        if (client == null) {
            client = this.mScanNative.getRegularScanClient(scannerId);
        }
        sendMessage(1, client);
    }

    void flushBatchScanResults(ScanClient client) {
        sendMessage(2, client);
    }

    void callbackDone(int scannerId, int status) {
        if (status == 0) {
            this.mLatch.countDown();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendMessage(int what, ScanClient client) {
        ClientHandler handler = this.mHandler;
        if (handler == null) {
            Log.d(TAG, "sendMessage: mHandler is null.");
            return;
        }
        Message message = new Message();
        message.what = what;
        message.obj = client;
        handler.sendMessage(message);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isFilteringSupported() {
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        return adapter.isOffloadedFilteringSupported();
    }

    private class ClientHandler extends Handler {
        ClientHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    handleStartScan((ScanClient) msg.obj);
                    break;
                case 1:
                    handleStopScan((ScanClient) msg.obj);
                    break;
                case 2:
                    handleFlushBatchResults((ScanClient) msg.obj);
                    break;
                case 3:
                    ScanManager.this.mScanNative.regularScanTimeout((ScanClient) msg.obj);
                    break;
                case 4:
                    handleSuspendScans();
                    break;
                case 5:
                    handleResumeScans();
                    break;
                case 6:
                    ScanManager.this.handleImportanceChange((UidImportance) msg.obj);
                    break;
                default:
                    Log.e(ScanManager.TAG, "received an unkown message : " + msg.what);
                    break;
            }
        }

        void handleStartScan(ScanClient client) {
            Utils.enforceAdminPermission(ScanManager.this.mService);
            boolean isFiltered = (client.filters == null || client.filters.isEmpty()) ? false : true;
            if (isScanSupported(client)) {
                if (ScanManager.this.mRegularScanClients.contains(client) || ScanManager.this.mBatchClients.contains(client)) {
                    Log.e(ScanManager.TAG, "Scan already started");
                    return;
                }
                if (ScanManager.this.mScanNative.isOpportunisticScanClient(client) || ScanManager.this.isScreenOn() || isFiltered) {
                    boolean locationEnabled = ScanManager.this.mLocationManager.isLocationEnabled();
                    if (!locationEnabled && !isFiltered) {
                        Log.i(ScanManager.TAG, "Cannot start unfiltered scan in location-off. This scan will be resumed when location is on: " + client.scannerId);
                        ScanManager.this.mSuspendedScanClients.add(client);
                        if (client.stats != null) {
                            client.stats.recordScanSuspend(client.scannerId);
                            return;
                        }
                        return;
                    }
                    if (isBatchClient(client)) {
                        ScanManager.this.mBatchClients.add(client);
                        ScanManager.this.mScanNative.startBatchScan(client);
                        return;
                    }
                    ScanManager.this.mRegularScanClients.add(client);
                    ScanManager.this.mScanNative.startRegularScan(client);
                    if (!ScanManager.this.mScanNative.isOpportunisticScanClient(client)) {
                        ScanManager.this.mScanNative.configureRegularScanParams();
                        if (!ScanManager.this.mScanNative.isExemptFromScanDowngrade(client)) {
                            Message msg = obtainMessage(3);
                            msg.obj = client;
                            sendMessageDelayed(msg, 1800000L);
                            return;
                        }
                        return;
                    }
                    return;
                }
                Log.w(ScanManager.TAG, "Cannot start unfiltered scan in screen-off. This scan will be resumed later: " + client.scannerId);
                ScanManager.this.mSuspendedScanClients.add(client);
                if (client.stats != null) {
                    client.stats.recordScanSuspend(client.scannerId);
                    return;
                }
                return;
            }
            Log.e(ScanManager.TAG, "Scan settings not supported");
        }

        void handleStopScan(ScanClient client) {
            Utils.enforceAdminPermission(ScanManager.this.mService);
            if (client != null) {
                if (ScanManager.this.mSuspendedScanClients.contains(client)) {
                    ScanManager.this.mSuspendedScanClients.remove(client);
                }
                if (ScanManager.this.mRegularScanClients.contains(client)) {
                    ScanManager.this.mScanNative.stopRegularScan(client);
                    if (ScanManager.this.mScanNative.numRegularScanClients() == 0) {
                        removeMessages(3);
                    }
                    if (!ScanManager.this.mScanNative.isOpportunisticScanClient(client)) {
                        ScanManager.this.mScanNative.configureRegularScanParams();
                    }
                } else {
                    ScanManager.this.mScanNative.stopBatchScan(client);
                }
                if (client.appDied) {
                    ScanManager.this.mService.unregisterScanner(client.scannerId);
                }
            }
        }

        void handleFlushBatchResults(ScanClient client) {
            Utils.enforceAdminPermission(ScanManager.this.mService);
            if (ScanManager.this.mBatchClients.contains(client)) {
                ScanManager.this.mScanNative.flushBatchResults(client.scannerId);
            }
        }

        private boolean isBatchClient(ScanClient client) {
            if (client == null || client.settings == null) {
                return false;
            }
            ScanSettings settings = client.settings;
            return settings.getCallbackType() == 1 && settings.getReportDelayMillis() != 0;
        }

        private boolean isScanSupported(ScanClient client) {
            if (client == null || client.settings == null) {
                return true;
            }
            ScanSettings settings = client.settings;
            if (ScanManager.this.isFilteringSupported()) {
                return true;
            }
            return settings.getCallbackType() == 1 && settings.getReportDelayMillis() == 0;
        }

        void handleSuspendScans() {
            for (ScanClient client : ScanManager.this.mRegularScanClients) {
                if (!ScanManager.this.mScanNative.isOpportunisticScanClient(client) && (client.filters == null || client.filters.isEmpty())) {
                    if (client.stats != null) {
                        client.stats.recordScanSuspend(client.scannerId);
                    }
                    handleStopScan(client);
                    ScanManager.this.mSuspendedScanClients.add(client);
                }
            }
        }

        void handleResumeScans() {
            for (ScanClient client : ScanManager.this.mSuspendedScanClients) {
                if (client.stats != null) {
                    client.stats.recordScanResume(client.scannerId);
                }
                handleStartScan(client);
            }
            ScanManager.this.mSuspendedScanClients.clear();
        }
    }

    class BatchScanParams {
        public int scanMode = -1;
        public int fullScanscannerId = -1;
        public int truncatedScanscannerId = -1;

        BatchScanParams() {
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            BatchScanParams other = (BatchScanParams) obj;
            if (this.scanMode == other.scanMode && this.fullScanscannerId == other.fullScanscannerId && this.truncatedScanscannerId == other.truncatedScanscannerId) {
                return true;
            }
            return false;
        }
    }

    public int getCurrentUsedTrackingAdvertisement() {
        return this.mCurUsedTrackableAdvertisements.intValue();
    }

    private class ScanNative {
        private static final int ALL_PASS_FILTER_INDEX_BATCH_SCAN = 2;
        private static final int ALL_PASS_FILTER_INDEX_REGULAR_SCAN = 1;
        private static final int ALL_PASS_FILTER_SELECTION = 0;
        private static final int DELIVERY_MODE_BATCH = 2;
        private static final int DELIVERY_MODE_IMMEDIATE = 0;
        private static final int DELIVERY_MODE_ON_FOUND_LOST = 1;
        private static final int DISCARD_OLDEST_WHEN_BUFFER_FULL = 0;
        private static final int FILTER_LOGIC_TYPE = 1;
        private static final int LIST_LOGIC_TYPE = 17895697;
        private static final int MATCH_MODE_AGGRESSIVE_TIMEOUT_FACTOR = 1;
        private static final int MATCH_MODE_STICKY_TIMEOUT_FACTOR = 3;
        private static final int ONFOUND_SIGHTINGS_AGGRESSIVE = 1;
        private static final int ONFOUND_SIGHTINGS_STICKY = 4;
        private static final int ONLOST_FACTOR = 2;
        private static final int ONLOST_ONFOUND_BASE_TIMEOUT_MS = 500;
        private static final int SCAN_MODE_BALANCED_INTERVAL_MS = 4096;
        private static final int SCAN_MODE_BALANCED_WINDOW_MS = 1024;
        private static final int SCAN_MODE_BATCH_BALANCED_INTERVAL_MS = 15000;
        private static final int SCAN_MODE_BATCH_BALANCED_WINDOW_MS = 1500;
        private static final int SCAN_MODE_BATCH_LOW_LATENCY_INTERVAL_MS = 5000;
        private static final int SCAN_MODE_BATCH_LOW_LATENCY_WINDOW_MS = 1500;
        private static final int SCAN_MODE_BATCH_LOW_POWER_INTERVAL_MS = 150000;
        private static final int SCAN_MODE_BATCH_LOW_POWER_WINDOW_MS = 1500;
        private static final int SCAN_MODE_LOW_LATENCY_INTERVAL_MS = 4096;
        private static final int SCAN_MODE_LOW_LATENCY_WINDOW_MS = 4096;
        private static final int SCAN_MODE_LOW_POWER_INTERVAL_MS = 5120;
        private static final int SCAN_MODE_LOW_POWER_WINDOW_MS = 512;
        private AlarmManager mAlarmManager;
        private PendingIntent mBatchScanIntervalIntent;
        private final Set<Integer> mAllPassRegularClients = new HashSet();
        private final Set<Integer> mAllPassBatchClients = new HashSet();
        private final Deque<Integer> mFilterIndexStack = new ArrayDeque();
        private final Map<Integer, Deque<Integer>> mClientFilterIndexMap = new HashMap();

        private native void gattClientConfigBatchScanStorageNative(int i, int i2, int i3, int i4);

        private native void gattClientReadScanReportsNative(int i, int i2);

        private native void gattClientScanFilterAddNative(int i, ScanFilterQueue.Entry[] entryArr, int i2);

        private native void gattClientScanFilterClearNative(int i, int i2);

        private native void gattClientScanFilterEnableNative(int i, boolean z);

        private native void gattClientScanFilterParamAddNative(FilterParams filterParams);

        private native void gattClientScanFilterParamClearAllNative(int i);

        private native void gattClientScanFilterParamDeleteNative(int i, int i2);

        private native void gattClientScanNative(boolean z);

        private native void gattClientStartBatchScanNative(int i, int i2, int i3, int i4, int i5, int i6);

        private native void gattClientStopBatchScanNative(int i);

        private native void gattSetScanParametersNative(int i, int i2, int i3);

        /* JADX INFO: Access modifiers changed from: private */
        public native void registerScannerNative(long j, long j2);

        /* JADX INFO: Access modifiers changed from: private */
        public native void unregisterScannerNative(int i);

        ScanNative() {
            this.mAlarmManager = (AlarmManager) ScanManager.this.mService.getSystemService(NotificationCompat.CATEGORY_ALARM);
            Intent batchIntent = new Intent(ScanManager.ACTION_REFRESH_BATCHED_SCAN, (Uri) null);
            this.mBatchScanIntervalIntent = PendingIntent.getBroadcast(ScanManager.this.mService, 0, batchIntent, 0);
            IntentFilter filter = new IntentFilter();
            filter.addAction(ScanManager.ACTION_REFRESH_BATCHED_SCAN);
            ScanManager.this.mBatchAlarmReceiver = new BroadcastReceiver() { // from class: com.android.bluetooth.gatt.ScanManager.ScanNative.1
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context, Intent intent) {
                    Log.d(ScanManager.TAG, "awakened up at time " + SystemClock.elapsedRealtime());
                    String action = intent.getAction();
                    if (action.equals(ScanManager.ACTION_REFRESH_BATCHED_SCAN) && !ScanManager.this.mBatchClients.isEmpty() && ScanManager.this.mBatchClients.iterator().hasNext()) {
                        ScanManager.this.flushBatchScanResults((ScanClient) ScanManager.this.mBatchClients.iterator().next());
                    }
                }
            };
            ScanManager.this.mService.registerReceiver(ScanManager.this.mBatchAlarmReceiver, filter);
            ScanManager.this.mBatchAlarmReceiverRegistered = true;
        }

        private void resetCountDownLatch() {
            ScanManager.this.mLatch = new CountDownLatch(1);
        }

        private boolean waitForCallback() {
            try {
                return ScanManager.this.mLatch.await(500L, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                return false;
            }
        }

        void configureRegularScanParams() {
            int curScanSetting = Integer.MIN_VALUE;
            ScanClient client = getAggressiveClient(ScanManager.this.mRegularScanClients);
            if (client != null) {
                curScanSetting = client.settings.getScanMode();
            }
            if (curScanSetting == Integer.MIN_VALUE || curScanSetting == -1) {
                ScanManager.this.mLastConfiguredScanSetting = curScanSetting;
                return;
            }
            if (curScanSetting != ScanManager.this.mLastConfiguredScanSetting) {
                int scanWindow = getScanWindowMillis(client.settings);
                int scanInterval = getScanIntervalMillis(client.settings);
                int scanWindow2 = Utils.millsToUnit(scanWindow);
                int scanInterval2 = Utils.millsToUnit(scanInterval);
                gattClientScanNative(false);
                gattSetScanParametersNative(client.scannerId, scanInterval2, scanWindow2);
                gattClientScanNative(true);
                ScanManager.this.mLastConfiguredScanSetting = curScanSetting;
            }
        }

        ScanClient getAggressiveClient(Set<ScanClient> cList) {
            ScanClient result = null;
            int curScanSetting = Integer.MIN_VALUE;
            for (ScanClient client : cList) {
                if (client.settings.getScanMode() > curScanSetting) {
                    result = client;
                    curScanSetting = client.settings.getScanMode();
                }
            }
            return result;
        }

        void startRegularScan(ScanClient client) {
            if (ScanManager.this.isFilteringSupported() && this.mFilterIndexStack.isEmpty() && this.mClientFilterIndexMap.isEmpty()) {
                initFilterIndexStack();
            }
            if (ScanManager.this.isFilteringSupported()) {
                configureScanFilters(client);
            }
            if (numRegularScanClients() == 1) {
                gattClientScanNative(true);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int numRegularScanClients() {
            int num = 0;
            for (ScanClient client : ScanManager.this.mRegularScanClients) {
                if (client.settings.getScanMode() != -1) {
                    num++;
                }
            }
            return num;
        }

        void startBatchScan(ScanClient client) {
            if (this.mFilterIndexStack.isEmpty() && ScanManager.this.isFilteringSupported()) {
                initFilterIndexStack();
            }
            configureScanFilters(client);
            if (!isOpportunisticScanClient(client)) {
                resetBatchScan(client);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isExemptFromScanDowngrade(ScanClient client) {
            return isOpportunisticScanClient(client) || isFirstMatchScanClient(client) || !shouldUseAllPassFilter(client);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isOpportunisticScanClient(ScanClient client) {
            return client.settings.getScanMode() == -1;
        }

        private boolean isFirstMatchScanClient(ScanClient client) {
            return (client.settings.getCallbackType() & 2) != 0;
        }

        private void resetBatchScan(ScanClient client) {
            int scannerId = client.scannerId;
            BatchScanParams batchScanParams = getBatchScanParams();
            if (ScanManager.this.mBatchScanParms != null && !ScanManager.this.mBatchScanParms.equals(batchScanParams)) {
                resetCountDownLatch();
                gattClientStopBatchScanNative(scannerId);
                waitForCallback();
                flushBatchResults(scannerId);
            }
            if (batchScanParams != null && !batchScanParams.equals(ScanManager.this.mBatchScanParms)) {
                int resultType = getResultType(batchScanParams);
                int fullScanPercent = getFullScanStoragePercent(resultType);
                resetCountDownLatch();
                gattClientConfigBatchScanStorageNative(client.scannerId, fullScanPercent, 100 - fullScanPercent, 95);
                waitForCallback();
                resetCountDownLatch();
                int scanInterval = Utils.millsToUnit(getBatchScanIntervalMillis(batchScanParams.scanMode));
                int scanWindow = Utils.millsToUnit(getBatchScanWindowMillis(batchScanParams.scanMode));
                gattClientStartBatchScanNative(scannerId, resultType, scanInterval, scanWindow, 0, 0);
                waitForCallback();
            }
            ScanManager.this.mBatchScanParms = batchScanParams;
            setBatchAlarm();
        }

        private int getFullScanStoragePercent(int resultType) {
            if (resultType == 1) {
                return 0;
            }
            if (resultType == 2) {
                return 100;
            }
            return 50;
        }

        private BatchScanParams getBatchScanParams() {
            if (ScanManager.this.mBatchClients.isEmpty()) {
                return null;
            }
            BatchScanParams params = ScanManager.this.new BatchScanParams();
            for (ScanClient client : ScanManager.this.mBatchClients) {
                params.scanMode = Math.max(params.scanMode, client.settings.getScanMode());
                if (client.settings.getScanResultType() == 0) {
                    params.fullScanscannerId = client.scannerId;
                } else {
                    params.truncatedScanscannerId = client.scannerId;
                }
            }
            return params;
        }

        private int getBatchScanWindowMillis(int scanMode) {
            return NetworkConstants.ETHER_MTU;
        }

        private int getBatchScanIntervalMillis(int scanMode) {
            if (scanMode == 1) {
                return SCAN_MODE_BATCH_BALANCED_INTERVAL_MS;
            }
            if (scanMode == 2) {
                return 5000;
            }
            return SCAN_MODE_BATCH_LOW_POWER_INTERVAL_MS;
        }

        private void setBatchAlarm() {
            this.mAlarmManager.cancel(this.mBatchScanIntervalIntent);
            if (ScanManager.this.mBatchClients.isEmpty()) {
                return;
            }
            long batchTriggerIntervalMillis = getBatchTriggerIntervalMillis();
            long windowLengthMillis = batchTriggerIntervalMillis / 10;
            long windowStartMillis = SystemClock.elapsedRealtime() + batchTriggerIntervalMillis;
            this.mAlarmManager.setWindow(2, windowStartMillis, windowLengthMillis, this.mBatchScanIntervalIntent);
        }

        void stopRegularScan(ScanClient client) {
            if (client == null) {
                return;
            }
            int deliveryMode = getDeliveryMode(client);
            if (deliveryMode == 1) {
                for (ScanFilter scanFilter : client.filters) {
                    int entriesToFree = getNumOfTrackingAdvertisements(client.settings);
                    if (!manageAllocationOfTrackingAdvertisement(entriesToFree, false)) {
                        Log.e(ScanManager.TAG, "Error freeing for onfound/onlost filter resources " + entriesToFree);
                        try {
                            ScanManager.this.mService.onScanManagerErrorCallback(client.scannerId, 3);
                        } catch (RemoteException e) {
                            Log.e(ScanManager.TAG, "failed on onScanManagerCallback at freeing", e);
                        }
                    }
                }
            }
            ScanManager.this.mRegularScanClients.remove(client);
            if (numRegularScanClients() == 0) {
                gattClientScanNative(false);
            }
            removeScanFilters(client.scannerId);
        }

        void regularScanTimeout(ScanClient client) {
            if (!isExemptFromScanDowngrade(client) && client.stats.isScanningTooLong()) {
                Log.w(ScanManager.TAG, "Moving scan client to opportunistic (scannerId " + client.scannerId + ")");
                setOpportunisticScanClient(client);
                removeScanFilters(client.scannerId);
                client.stats.setScanTimeout(client.scannerId);
            }
            configureRegularScanParams();
            if (numRegularScanClients() == 0) {
                gattClientScanNative(false);
            }
        }

        void setOpportunisticScanClient(ScanClient client) {
            ScanSettings.Builder builder = new ScanSettings.Builder();
            ScanSettings settings = client.settings;
            builder.setScanMode(-1);
            builder.setCallbackType(settings.getCallbackType());
            builder.setScanResultType(settings.getScanResultType());
            builder.setReportDelay(settings.getReportDelayMillis());
            builder.setNumOfMatches(settings.getNumOfMatches());
            client.settings = builder.build();
        }

        ScanClient getRegularScanClient(int scannerId) {
            for (ScanClient client : ScanManager.this.mRegularScanClients) {
                if (client.scannerId == scannerId) {
                    return client;
                }
            }
            return null;
        }

        void stopBatchScan(ScanClient client) {
            ScanManager.this.mBatchClients.remove(client);
            removeScanFilters(client.scannerId);
            if (!isOpportunisticScanClient(client)) {
                resetBatchScan(client);
            }
        }

        void flushBatchResults(int scannerId) {
            if (ScanManager.this.mBatchScanParms.fullScanscannerId != -1) {
                resetCountDownLatch();
                gattClientReadScanReportsNative(ScanManager.this.mBatchScanParms.fullScanscannerId, 2);
                waitForCallback();
            }
            if (ScanManager.this.mBatchScanParms.truncatedScanscannerId != -1) {
                resetCountDownLatch();
                gattClientReadScanReportsNative(ScanManager.this.mBatchScanParms.truncatedScanscannerId, 1);
                waitForCallback();
            }
            setBatchAlarm();
        }

        void cleanup() {
            this.mAlarmManager.cancel(this.mBatchScanIntervalIntent);
            if (ScanManager.this.mBatchAlarmReceiverRegistered) {
                ScanManager.this.mService.unregisterReceiver(ScanManager.this.mBatchAlarmReceiver);
            }
            ScanManager.this.mBatchAlarmReceiverRegistered = false;
        }

        private long getBatchTriggerIntervalMillis() {
            long intervalMillis = Long.MAX_VALUE;
            for (ScanClient client : ScanManager.this.mBatchClients) {
                if (client.settings != null && client.settings.getReportDelayMillis() > 0) {
                    intervalMillis = Math.min(intervalMillis, client.settings.getReportDelayMillis());
                }
            }
            return intervalMillis;
        }

        private void configureScanFilters(ScanClient client) {
            int filterIndex;
            int scannerId = client.scannerId;
            int deliveryMode = getDeliveryMode(client);
            int trackEntries = 0;
            if (isOpportunisticScanClient(client) || !shouldAddAllPassFilterToController(client, deliveryMode)) {
                return;
            }
            resetCountDownLatch();
            gattClientScanFilterEnableNative(scannerId, true);
            waitForCallback();
            if (shouldUseAllPassFilter(client)) {
                if (deliveryMode == 2) {
                    filterIndex = 2;
                } else {
                    filterIndex = 1;
                }
                resetCountDownLatch();
                configureFilterParamter(scannerId, client, 0, filterIndex, 0);
                waitForCallback();
                return;
            }
            Deque<Integer> clientFilterIndices = new ArrayDeque<>();
            for (ScanFilter filter : client.filters) {
                ScanFilterQueue queue = new ScanFilterQueue();
                queue.addScanFilter(filter);
                int featureSelection = queue.getFeatureSelection();
                int filterIndex2 = this.mFilterIndexStack.pop().intValue();
                resetCountDownLatch();
                gattClientScanFilterAddNative(scannerId, queue.toArray(), filterIndex2);
                waitForCallback();
                resetCountDownLatch();
                if (deliveryMode == 1) {
                    int trackEntries2 = getNumOfTrackingAdvertisements(client.settings);
                    if (!manageAllocationOfTrackingAdvertisement(trackEntries2, true)) {
                        Log.e(ScanManager.TAG, "No hardware resources for onfound/onlost filter " + trackEntries2);
                        try {
                            ScanManager.this.mService.onScanManagerErrorCallback(scannerId, 3);
                        } catch (RemoteException e) {
                            Log.e(ScanManager.TAG, "failed on onScanManagerCallback", e);
                        }
                    }
                    trackEntries = trackEntries2;
                }
                configureFilterParamter(scannerId, client, featureSelection, filterIndex2, trackEntries);
                waitForCallback();
                clientFilterIndices.add(Integer.valueOf(filterIndex2));
            }
            this.mClientFilterIndexMap.put(Integer.valueOf(scannerId), clientFilterIndices);
        }

        private boolean shouldAddAllPassFilterToController(ScanClient client, int deliveryMode) {
            if (!shouldUseAllPassFilter(client)) {
                return true;
            }
            if (deliveryMode == 2) {
                this.mAllPassBatchClients.add(Integer.valueOf(client.scannerId));
                return this.mAllPassBatchClients.size() == 1;
            }
            this.mAllPassRegularClients.add(Integer.valueOf(client.scannerId));
            return this.mAllPassRegularClients.size() == 1;
        }

        private void removeScanFilters(int scannerId) {
            Deque<Integer> filterIndices = this.mClientFilterIndexMap.remove(Integer.valueOf(scannerId));
            if (filterIndices != null) {
                this.mFilterIndexStack.addAll(filterIndices);
                for (Integer filterIndex : filterIndices) {
                    resetCountDownLatch();
                    gattClientScanFilterParamDeleteNative(scannerId, filterIndex.intValue());
                    waitForCallback();
                }
            }
            removeFilterIfExisits(this.mAllPassRegularClients, scannerId, 1);
            removeFilterIfExisits(this.mAllPassBatchClients, scannerId, 2);
        }

        private void removeFilterIfExisits(Set<Integer> clients, int scannerId, int filterIndex) {
            if (!clients.contains(Integer.valueOf(scannerId))) {
                return;
            }
            clients.remove(Integer.valueOf(scannerId));
            if (clients.isEmpty()) {
                resetCountDownLatch();
                gattClientScanFilterParamDeleteNative(scannerId, filterIndex);
                waitForCallback();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ScanClient getBatchScanClient(int scannerId) {
            for (ScanClient client : ScanManager.this.mBatchClients) {
                if (client.scannerId == scannerId) {
                    return client;
                }
            }
            return null;
        }

        private int getResultType(BatchScanParams params) {
            if (params.fullScanscannerId != -1 && params.truncatedScanscannerId != -1) {
                return 3;
            }
            if (params.truncatedScanscannerId != -1) {
                return 1;
            }
            return params.fullScanscannerId != -1 ? 2 : -1;
        }

        private boolean shouldUseAllPassFilter(ScanClient client) {
            return client == null || client.filters == null || client.filters.isEmpty() || client.filters.size() > this.mFilterIndexStack.size();
        }

        private void initFilterIndexStack() {
            int maxFiltersSupported = AdapterService.getAdapterService().getNumOfOffloadedScanFilterSupported();
            for (int i = 3; i < maxFiltersSupported; i++) {
                this.mFilterIndexStack.add(Integer.valueOf(i));
            }
        }

        private void configureFilterParamter(int scannerId, ScanClient client, int featureSelection, int filterIndex, int numOfTrackingEntries) {
            int deliveryMode = getDeliveryMode(client);
            ScanSettings settings = client.settings;
            int onFoundTimeout = getOnFoundOnLostTimeoutMillis(settings, true);
            getOnFoundOnLostTimeoutMillis(settings, false);
            int onFoundCount = getOnFoundOnLostSightings(settings);
            FilterParams filtValue = new FilterParams(scannerId, filterIndex, featureSelection, LIST_LOGIC_TYPE, 1, -128, -128, deliveryMode, onFoundTimeout, 10000, onFoundCount, numOfTrackingEntries);
            gattClientScanFilterParamAddNative(filtValue);
        }

        private int getDeliveryMode(ScanClient client) {
            ScanSettings settings;
            if (client == null || (settings = client.settings) == null) {
                return 0;
            }
            if ((settings.getCallbackType() & 2) == 0 && (settings.getCallbackType() & 4) == 0) {
                return settings.getReportDelayMillis() == 0 ? 0 : 2;
            }
            return 1;
        }

        private int getScanWindowMillis(ScanSettings settings) {
            ContentResolver resolver = ScanManager.this.mService.getContentResolver();
            if (settings == null) {
                return Settings.Global.getInt(resolver, "ble_scan_low_power_window_ms", 512);
            }
            int scanMode = settings.getScanMode();
            if (scanMode == 0) {
                return Settings.Global.getInt(resolver, "ble_scan_low_power_window_ms", 512);
            }
            if (scanMode == 1) {
                return Settings.Global.getInt(resolver, "ble_scan_balanced_window_ms", 1024);
            }
            if (scanMode == 2) {
                return Settings.Global.getInt(resolver, "ble_scan_low_latency_window_ms", 4096);
            }
            return Settings.Global.getInt(resolver, "ble_scan_low_power_window_ms", 512);
        }

        private int getScanIntervalMillis(ScanSettings settings) {
            ContentResolver resolver = ScanManager.this.mService.getContentResolver();
            if (settings == null) {
                return Settings.Global.getInt(resolver, "ble_scan_low_power_interval_ms", SCAN_MODE_LOW_POWER_INTERVAL_MS);
            }
            int scanMode = settings.getScanMode();
            if (scanMode == 0) {
                return Settings.Global.getInt(resolver, "ble_scan_low_power_interval_ms", SCAN_MODE_LOW_POWER_INTERVAL_MS);
            }
            if (scanMode == 1) {
                return Settings.Global.getInt(resolver, "ble_scan_balanced_interval_ms", 4096);
            }
            if (scanMode == 2) {
                return Settings.Global.getInt(resolver, "ble_scan_low_latency_interval_ms", 4096);
            }
            return Settings.Global.getInt(resolver, "ble_scan_low_power_interval_ms", SCAN_MODE_LOW_POWER_INTERVAL_MS);
        }

        private int getOnFoundOnLostTimeoutMillis(ScanSettings settings, boolean onFound) {
            int factor;
            if (settings.getMatchMode() == 1) {
                factor = 1;
            } else {
                factor = 3;
            }
            if (!onFound) {
                factor *= 2;
            }
            return 500 * factor;
        }

        private int getOnFoundOnLostSightings(ScanSettings settings) {
            if (settings == null || settings.getMatchMode() == 1) {
                return 1;
            }
            return 4;
        }

        private int getNumOfTrackingAdvertisements(ScanSettings settings) {
            if (settings == null) {
                return 0;
            }
            int maxTotalTrackableAdvertisements = AdapterService.getAdapterService().getTotalNumOfTrackableAdvertisements();
            int numOfMatches = settings.getNumOfMatches();
            if (numOfMatches == 1) {
                return 1;
            }
            if (numOfMatches == 2) {
                return 2;
            }
            if (numOfMatches == 3) {
                int val = maxTotalTrackableAdvertisements / 2;
                return val;
            }
            return 1;
        }

        private boolean manageAllocationOfTrackingAdvertisement(int numOfTrackableAdvertisement, boolean allocate) {
            int maxTotalTrackableAdvertisements = AdapterService.getAdapterService().getTotalNumOfTrackableAdvertisements();
            synchronized (ScanManager.this.mCurUsedTrackableAdvertisements) {
                int availableEntries = maxTotalTrackableAdvertisements - ScanManager.this.mCurUsedTrackableAdvertisements.intValue();
                if (!allocate) {
                    if (numOfTrackableAdvertisement > ScanManager.this.mCurUsedTrackableAdvertisements.intValue()) {
                        return false;
                    }
                    ScanManager.this.mCurUsedTrackableAdvertisements = Integer.valueOf(ScanManager.this.mCurUsedTrackableAdvertisements.intValue() - numOfTrackableAdvertisement);
                    return true;
                }
                if (availableEntries < numOfTrackableAdvertisement) {
                    return false;
                }
                ScanManager.this.mCurUsedTrackableAdvertisements = Integer.valueOf(ScanManager.this.mCurUsedTrackableAdvertisements.intValue() + numOfTrackableAdvertisement);
                return true;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isScreenOn() {
        Display[] displays = this.mDm.getDisplays();
        if (displays == null) {
            return false;
        }
        for (Display display : displays) {
            if (display.getState() == 2) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleImportanceChange(UidImportance imp) {
        if (imp == null) {
            return;
        }
        int uid = imp.uid;
        int importance = imp.importance;
        boolean updatedScanParams = false;
        if (importance <= 125) {
            for (ScanClient client : this.mRegularScanClients) {
                if (client.appUid == uid && client.passiveSettings != null) {
                    client.settings = client.passiveSettings;
                    client.passiveSettings = null;
                    updatedScanParams = true;
                }
            }
        } else {
            ContentResolver resolver = this.mService.getContentResolver();
            int backgroundScanMode = Settings.Global.getInt(resolver, "ble_scan_background_mode", 0);
            for (ScanClient client2 : this.mRegularScanClients) {
                if (client2.appUid == uid && !this.mScanNative.isOpportunisticScanClient(client2)) {
                    client2.passiveSettings = client2.settings;
                    ScanSettings.Builder builder = new ScanSettings.Builder();
                    ScanSettings settings = client2.settings;
                    builder.setScanMode(backgroundScanMode);
                    builder.setCallbackType(settings.getCallbackType());
                    builder.setScanResultType(settings.getScanResultType());
                    builder.setReportDelay(settings.getReportDelayMillis());
                    builder.setNumOfMatches(settings.getNumOfMatches());
                    client2.settings = builder.build();
                    updatedScanParams = true;
                }
            }
        }
        if (updatedScanParams) {
            this.mScanNative.configureRegularScanParams();
        }
    }
}
