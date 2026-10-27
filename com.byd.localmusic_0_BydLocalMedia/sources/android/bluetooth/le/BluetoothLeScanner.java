package android.bluetooth.le;

import android.annotation.SystemApi;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.IBluetoothGatt;
import android.bluetooth.IBluetoothManager;
import android.bluetooth.le.IScannerCallback;
import android.bluetooth.le.ScanSettings;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.os.WorkSource;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class BluetoothLeScanner {
    private static final boolean DBG = true;
    public static final String EXTRA_CALLBACK_TYPE = "android.bluetooth.le.extra.CALLBACK_TYPE";
    public static final String EXTRA_ERROR_CODE = "android.bluetooth.le.extra.ERROR_CODE";
    public static final String EXTRA_LIST_SCAN_RESULT = "android.bluetooth.le.extra.LIST_SCAN_RESULT";
    private static final String TAG = "BluetoothLeScanner";
    private static final boolean VDBG = false;
    private final IBluetoothManager mBluetoothManager;
    private final String mFeatureId;
    private final String mOpPackageName;
    private BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private final Map<ScanCallback, BleScanCallbackWrapper> mLeScanClients = new HashMap();

    public BluetoothLeScanner(IBluetoothManager iBluetoothManager, String str, String str2) {
        this.mBluetoothManager = iBluetoothManager;
        this.mOpPackageName = str;
        this.mFeatureId = str2;
    }

    public void startScan(ScanCallback scanCallback) {
        startScan((List<ScanFilter>) null, new ScanSettings.Builder().build(), scanCallback);
    }

    public void startScan(List<ScanFilter> list, ScanSettings scanSettings, ScanCallback scanCallback) {
        startScan(list, scanSettings, null, scanCallback, null, null);
    }

    public int startScan(List<ScanFilter> list, ScanSettings scanSettings, PendingIntent pendingIntent) {
        if (scanSettings == null) {
            scanSettings = new ScanSettings.Builder().build();
        }
        return startScan(list, scanSettings, null, null, pendingIntent, null);
    }

    @SystemApi
    public void startScanFromSource(WorkSource workSource, ScanCallback scanCallback) {
        startScanFromSource(null, new ScanSettings.Builder().build(), workSource, scanCallback);
    }

    @SystemApi
    public void startScanFromSource(List<ScanFilter> list, ScanSettings scanSettings, WorkSource workSource, ScanCallback scanCallback) {
        startScan(list, scanSettings, workSource, scanCallback, null, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x003a A[Catch: all -> 0x0033, TRY_ENTER, TryCatch #0 {, blocks: (B:12:0x001d, B:14:0x0025, B:15:0x002a, B:17:0x002c, B:24:0x003a, B:25:0x003e, B:27:0x0040, B:29:0x0047, B:30:0x004b, B:32:0x004d, B:34:0x0053, B:35:0x0058, B:37:0x005a, B:39:0x0061, B:40:0x0065, B:43:0x0069, B:46:0x0087, B:45:0x007b, B:49:0x008a), top: B:55:0x001d, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0040 A[Catch: all -> 0x0033, TryCatch #0 {, blocks: (B:12:0x001d, B:14:0x0025, B:15:0x002a, B:17:0x002c, B:24:0x003a, B:25:0x003e, B:27:0x0040, B:29:0x0047, B:30:0x004b, B:32:0x004d, B:34:0x0053, B:35:0x0058, B:37:0x005a, B:39:0x0061, B:40:0x0065, B:43:0x0069, B:46:0x0087, B:45:0x007b, B:49:0x008a), top: B:55:0x001d, inners: #1 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int startScan(java.util.List<android.bluetooth.le.ScanFilter> r13, android.bluetooth.le.ScanSettings r14, android.os.WorkSource r15, android.bluetooth.le.ScanCallback r16, android.app.PendingIntent r17, java.util.List<java.util.List<android.bluetooth.le.ResultStorageDescriptor>> r18) {
        /*
            r12 = this;
            r9 = r12
            r0 = r14
            r7 = r16
            android.bluetooth.BluetoothAdapter r1 = r9.mBluetoothAdapter
            android.bluetooth.le.BluetoothLeUtils.checkAdapterStateOn(r1)
            if (r7 != 0) goto L16
            if (r17 == 0) goto Le
            goto L16
        Le:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "callback is null"
            r0.<init>(r1)
            throw r0
        L16:
            if (r0 == 0) goto L8e
            java.util.Map<android.bluetooth.le.ScanCallback, android.bluetooth.le.BluetoothLeScanner$BleScanCallbackWrapper> r10 = r9.mLeScanClients
            monitor-enter(r10)
            if (r7 == 0) goto L2c
            java.util.Map<android.bluetooth.le.ScanCallback, android.bluetooth.le.BluetoothLeScanner$BleScanCallbackWrapper> r1 = r9.mLeScanClients     // Catch: java.lang.Throwable -> L33
            boolean r1 = r1.containsKey(r7)     // Catch: java.lang.Throwable -> L33
            if (r1 == 0) goto L2c
            r0 = 1
            int r0 = r12.postCallbackErrorOrReturn(r7, r0)     // Catch: java.lang.Throwable -> L33
            monitor-exit(r10)     // Catch: java.lang.Throwable -> L33
            return r0
        L2c:
            android.bluetooth.IBluetoothManager r1 = r9.mBluetoothManager     // Catch: java.lang.Throwable -> L33 android.os.RemoteException -> L35
            android.bluetooth.IBluetoothGatt r1 = r1.getBluetoothGatt()     // Catch: java.lang.Throwable -> L33 android.os.RemoteException -> L35
            goto L36
        L33:
            r0 = move-exception
            goto L8c
        L35:
            r1 = 0
        L36:
            r3 = r1
            r8 = 3
            if (r3 != 0) goto L40
            int r0 = r12.postCallbackErrorOrReturn(r7, r8)     // Catch: java.lang.Throwable -> L33
            monitor-exit(r10)     // Catch: java.lang.Throwable -> L33
            return r0
        L40:
            boolean r1 = r12.isSettingsConfigAllowedForScan(r14)     // Catch: java.lang.Throwable -> L33
            r2 = 4
            if (r1 != 0) goto L4d
            int r0 = r12.postCallbackErrorOrReturn(r7, r2)     // Catch: java.lang.Throwable -> L33
            monitor-exit(r10)     // Catch: java.lang.Throwable -> L33
            return r0
        L4d:
            boolean r1 = r12.isHardwareResourcesAvailableForScan(r14)     // Catch: java.lang.Throwable -> L33
            if (r1 != 0) goto L5a
            r0 = 5
            int r0 = r12.postCallbackErrorOrReturn(r7, r0)     // Catch: java.lang.Throwable -> L33
            monitor-exit(r10)     // Catch: java.lang.Throwable -> L33
            return r0
        L5a:
            r4 = r13
            boolean r1 = r12.isSettingsAndFilterComboAllowed(r14, r13)     // Catch: java.lang.Throwable -> L33
            if (r1 != 0) goto L67
            int r0 = r12.postCallbackErrorOrReturn(r7, r2)     // Catch: java.lang.Throwable -> L33
            monitor-exit(r10)     // Catch: java.lang.Throwable -> L33
            return r0
        L67:
            if (r7 == 0) goto L7b
            android.bluetooth.le.BluetoothLeScanner$BleScanCallbackWrapper r11 = new android.bluetooth.le.BluetoothLeScanner$BleScanCallbackWrapper     // Catch: java.lang.Throwable -> L33
            r1 = r11
            r2 = r12
            r4 = r13
            r5 = r14
            r6 = r15
            r7 = r16
            r8 = r18
            r1.<init>(r3, r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L33
            r11.startRegistration()     // Catch: java.lang.Throwable -> L33
            goto L87
        L7b:
            java.lang.String r5 = r9.mOpPackageName     // Catch: java.lang.Throwable -> L33 android.os.RemoteException -> L8a
            java.lang.String r6 = r9.mFeatureId     // Catch: java.lang.Throwable -> L33 android.os.RemoteException -> L8a
            r1 = r3
            r2 = r17
            r3 = r14
            r4 = r13
            r1.startScanForIntent(r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> L33 android.os.RemoteException -> L8a
        L87:
            monitor-exit(r10)     // Catch: java.lang.Throwable -> L33
            r0 = 0
            return r0
        L8a:
            monitor-exit(r10)     // Catch: java.lang.Throwable -> L33
            return r8
        L8c:
            monitor-exit(r10)     // Catch: java.lang.Throwable -> L33
            throw r0
        L8e:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "settings is null"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.bluetooth.le.BluetoothLeScanner.startScan(java.util.List, android.bluetooth.le.ScanSettings, android.os.WorkSource, android.bluetooth.le.ScanCallback, android.app.PendingIntent, java.util.List):int");
    }

    public void stopScan(ScanCallback scanCallback) {
        BluetoothLeUtils.checkAdapterStateOn(this.mBluetoothAdapter);
        synchronized (this.mLeScanClients) {
            BleScanCallbackWrapper bleScanCallbackWrapperRemove = this.mLeScanClients.remove(scanCallback);
            if (bleScanCallbackWrapperRemove == null) {
                Log.d(TAG, "could not find callback wrapper");
            } else {
                bleScanCallbackWrapperRemove.stopLeScan();
            }
        }
    }

    public void stopScan(PendingIntent pendingIntent) {
        BluetoothLeUtils.checkAdapterStateOn(this.mBluetoothAdapter);
        try {
            this.mBluetoothManager.getBluetoothGatt().stopScanForIntent(pendingIntent, this.mOpPackageName);
        } catch (RemoteException unused) {
        }
    }

    public void flushPendingScanResults(ScanCallback scanCallback) {
        BluetoothLeUtils.checkAdapterStateOn(this.mBluetoothAdapter);
        if (scanCallback == null) {
            throw new IllegalArgumentException("callback cannot be null!");
        }
        synchronized (this.mLeScanClients) {
            BleScanCallbackWrapper bleScanCallbackWrapper = this.mLeScanClients.get(scanCallback);
            if (bleScanCallbackWrapper == null) {
                return;
            }
            bleScanCallbackWrapper.flushPendingBatchResults();
        }
    }

    @SystemApi
    public void startTruncatedScan(List<TruncatedFilter> list, ScanSettings scanSettings, ScanCallback scanCallback) {
        int size = list.size();
        ArrayList arrayList = new ArrayList(size);
        ArrayList arrayList2 = new ArrayList(size);
        for (TruncatedFilter truncatedFilter : list) {
            arrayList.add(truncatedFilter.getFilter());
            arrayList2.add(truncatedFilter.getStorageDescriptors());
        }
        startScan(arrayList, scanSettings, null, scanCallback, null, arrayList2);
    }

    public void cleanup() {
        this.mLeScanClients.clear();
    }

    private class BleScanCallbackWrapper extends IScannerCallback.Stub {
        private static final int REGISTRATION_CALLBACK_TIMEOUT_MILLIS = 2000;
        private IBluetoothGatt mBluetoothGatt;
        private final List<ScanFilter> mFilters;
        private List<List<ResultStorageDescriptor>> mResultStorages;
        private final ScanCallback mScanCallback;
        private int mScannerId = 0;
        private ScanSettings mSettings;
        private final WorkSource mWorkSource;

        public BleScanCallbackWrapper(IBluetoothGatt iBluetoothGatt, List<ScanFilter> list, ScanSettings scanSettings, WorkSource workSource, ScanCallback scanCallback, List<List<ResultStorageDescriptor>> list2) {
            this.mBluetoothGatt = iBluetoothGatt;
            this.mFilters = list;
            this.mSettings = scanSettings;
            this.mWorkSource = workSource;
            this.mScanCallback = scanCallback;
            this.mResultStorages = list2;
        }

        public void startRegistration() {
            synchronized (this) {
                if (this.mScannerId == -1 || this.mScannerId == -2) {
                    return;
                }
                try {
                    this.mBluetoothGatt.registerScanner(this, this.mWorkSource);
                    wait(2000L);
                } catch (RemoteException | InterruptedException e) {
                    Log.e(BluetoothLeScanner.TAG, "application registeration exception", e);
                    BluetoothLeScanner.this.postCallbackError(this.mScanCallback, 3);
                }
                if (this.mScannerId > 0) {
                    BluetoothLeScanner.this.mLeScanClients.put(this.mScanCallback, this);
                } else {
                    if (this.mScannerId == 0) {
                        this.mScannerId = -1;
                    }
                    if (this.mScannerId == -2) {
                    } else {
                        BluetoothLeScanner.this.postCallbackError(this.mScanCallback, 2);
                    }
                }
            }
        }

        public void stopLeScan() {
            synchronized (this) {
                if (this.mScannerId <= 0) {
                    Log.e(BluetoothLeScanner.TAG, "Error state, mLeHandle: " + this.mScannerId);
                    return;
                }
                try {
                    this.mBluetoothGatt.stopScan(this.mScannerId);
                    this.mBluetoothGatt.unregisterScanner(this.mScannerId);
                } catch (RemoteException e) {
                    Log.e(BluetoothLeScanner.TAG, "Failed to stop scan and unregister", e);
                }
                this.mScannerId = -1;
            }
        }

        void flushPendingBatchResults() {
            synchronized (this) {
                if (this.mScannerId <= 0) {
                    Log.e(BluetoothLeScanner.TAG, "Error state, mLeHandle: " + this.mScannerId);
                    return;
                }
                try {
                    this.mBluetoothGatt.flushPendingBatchResults(this.mScannerId);
                } catch (RemoteException e) {
                    Log.e(BluetoothLeScanner.TAG, "Failed to get pending scan results", e);
                }
            }
        }

        @Override // android.bluetooth.le.IScannerCallback
        public void onScannerRegistered(int i, int i2) {
            Log.d(BluetoothLeScanner.TAG, "onScannerRegistered() - status=" + i + " scannerId=" + i2 + " mScannerId=" + this.mScannerId);
            synchronized (this) {
                if (i == 0) {
                    try {
                        if (this.mScannerId == -1) {
                            this.mBluetoothGatt.unregisterScanner(i2);
                        } else {
                            this.mScannerId = i2;
                            this.mBluetoothGatt.startScan(i2, this.mSettings, this.mFilters, this.mResultStorages, BluetoothLeScanner.this.mOpPackageName, BluetoothLeScanner.this.mFeatureId);
                        }
                    } catch (RemoteException e) {
                        Log.e(BluetoothLeScanner.TAG, "fail to start le scan: " + e);
                        this.mScannerId = -1;
                    }
                } else if (i == 6) {
                    this.mScannerId = -2;
                } else {
                    this.mScannerId = -1;
                }
                notifyAll();
            }
        }

        @Override // android.bluetooth.le.IScannerCallback
        public void onScanResult(final ScanResult scanResult) {
            synchronized (this) {
                if (this.mScannerId <= 0) {
                    return;
                }
                new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: android.bluetooth.le.BluetoothLeScanner.BleScanCallbackWrapper.1
                    @Override // java.lang.Runnable
                    public void run() {
                        BleScanCallbackWrapper.this.mScanCallback.onScanResult(1, scanResult);
                    }
                });
            }
        }

        @Override // android.bluetooth.le.IScannerCallback
        public void onBatchScanResults(final List<ScanResult> list) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: android.bluetooth.le.BluetoothLeScanner.BleScanCallbackWrapper.2
                @Override // java.lang.Runnable
                public void run() {
                    BleScanCallbackWrapper.this.mScanCallback.onBatchScanResults(list);
                }
            });
        }

        @Override // android.bluetooth.le.IScannerCallback
        public void onFoundOrLost(final boolean z, final ScanResult scanResult) {
            synchronized (this) {
                if (this.mScannerId <= 0) {
                    return;
                }
                new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: android.bluetooth.le.BluetoothLeScanner.BleScanCallbackWrapper.3
                    @Override // java.lang.Runnable
                    public void run() {
                        if (z) {
                            BleScanCallbackWrapper.this.mScanCallback.onScanResult(2, scanResult);
                        } else {
                            BleScanCallbackWrapper.this.mScanCallback.onScanResult(4, scanResult);
                        }
                    }
                });
            }
        }

        @Override // android.bluetooth.le.IScannerCallback
        public void onScanManagerErrorCallback(int i) {
            synchronized (this) {
                if (this.mScannerId <= 0) {
                    return;
                }
                BluetoothLeScanner.this.postCallbackError(this.mScanCallback, i);
            }
        }
    }

    private int postCallbackErrorOrReturn(ScanCallback scanCallback, int i) {
        if (scanCallback == null) {
            return i;
        }
        postCallbackError(scanCallback, i);
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postCallbackError(final ScanCallback scanCallback, final int i) {
        this.mHandler.post(new Runnable() { // from class: android.bluetooth.le.BluetoothLeScanner.1
            @Override // java.lang.Runnable
            public void run() {
                scanCallback.onScanFailed(i);
            }
        });
    }

    private boolean isSettingsConfigAllowedForScan(ScanSettings scanSettings) {
        if (this.mBluetoothAdapter.isOffloadedFilteringSupported()) {
            return true;
        }
        return scanSettings.getCallbackType() == 1 && scanSettings.getReportDelayMillis() == 0;
    }

    private boolean isSettingsAndFilterComboAllowed(ScanSettings scanSettings, List<ScanFilter> list) {
        if ((scanSettings.getCallbackType() & 6) == 0) {
            return true;
        }
        if (list == null) {
            return false;
        }
        Iterator<ScanFilter> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().isAllFieldsEmpty()) {
                return false;
            }
        }
        return true;
    }

    private boolean isHardwareResourcesAvailableForScan(ScanSettings scanSettings) {
        int callbackType = scanSettings.getCallbackType();
        if ((callbackType & 2) == 0 && (callbackType & 4) == 0) {
            return true;
        }
        return this.mBluetoothAdapter.isOffloadedFilteringSupported() && this.mBluetoothAdapter.isHardwareTrackingFiltersAvailable();
    }
}
