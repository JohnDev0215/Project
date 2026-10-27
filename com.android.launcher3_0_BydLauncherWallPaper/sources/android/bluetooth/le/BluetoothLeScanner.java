package android.bluetooth.le;

import android.annotation.SystemApi;
import android.app.ActivityThread;
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
import com.idd.bluetooth.IIddBluetooth;
import com.idd.comm.IIddServiceManager;
import com.idd.comm.IddServiceManagerLoader;
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

    /* JADX WARN: Removed duplicated region for block: B:31:0x0067 A[Catch: all -> 0x0060, TRY_ENTER, TryCatch #0 {, blocks: (B:19:0x004b, B:21:0x0053, B:22:0x0057, B:24:0x0059, B:31:0x0067, B:32:0x006b, B:34:0x006d, B:36:0x0074, B:37:0x0078, B:39:0x007a, B:41:0x0080, B:42:0x0085, B:44:0x0087, B:46:0x008e, B:47:0x0092, B:50:0x0096, B:53:0x00b5, B:52:0x00a7, B:56:0x00b8), top: B:62:0x004b, inners: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x006d A[Catch: all -> 0x0060, TryCatch #0 {, blocks: (B:19:0x004b, B:21:0x0053, B:22:0x0057, B:24:0x0059, B:31:0x0067, B:32:0x006b, B:34:0x006d, B:36:0x0074, B:37:0x0078, B:39:0x007a, B:41:0x0080, B:42:0x0085, B:44:0x0087, B:46:0x008e, B:47:0x0092, B:50:0x0096, B:53:0x00b5, B:52:0x00a7, B:56:0x00b8), top: B:62:0x004b, inners: #2 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int startScan(java.util.List<android.bluetooth.le.ScanFilter> r12, android.bluetooth.le.ScanSettings r13, android.os.WorkSource r14, android.bluetooth.le.ScanCallback r15, android.app.PendingIntent r16, java.util.List<java.util.List<android.bluetooth.le.ResultStorageDescriptor>> r17) {
        /*
            r11 = this;
            r0 = r11
            r5 = r13
            r7 = r15
            android.bluetooth.BluetoothAdapter r1 = r0.mBluetoothAdapter
            android.bluetooth.le.BluetoothLeUtils.checkAdapterStateOn(r1)
            if (r7 != 0) goto L15
            if (r16 == 0) goto Ld
            goto L15
        Ld:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "callback is null"
            r0.<init>(r1)
            throw r0
        L15:
            if (r5 == 0) goto Lbc
            android.app.ActivityThread r1 = android.app.ActivityThread.currentActivityThread()
            android.app.ContextImpl r1 = r1.getSystemContext()
            com.idd.comm.IIddServiceManager r1 = com.idd.comm.IddServiceManagerLoader.getInstance(r1)
            r2 = 1
            if (r1 == 0) goto L3f
            com.idd.bluetooth.IIddBluetooth r1 = r1.getBluetooth()
            if (r1 == 0) goto L37
            java.lang.String r3 = android.app.ActivityThread.currentPackageName()
            java.lang.String r4 = "startScan"
            r1.scan(r3, r2, r4)
            goto L46
        L37:
            java.lang.String r1 = "Idd"
            java.lang.String r3 = "bluetooth startScan(), send server failed"
            android.util.Log.w(r1, r3)
            goto L46
        L3f:
            java.lang.String r1 = "Idd"
            java.lang.String r3 = "bluetooth startScan(), bind server failed"
            android.util.Log.w(r1, r3)
        L46:
            java.util.Map<android.bluetooth.le.ScanCallback, android.bluetooth.le.BluetoothLeScanner$BleScanCallbackWrapper> r9 = r0.mLeScanClients
            monitor-enter(r9)
            if (r7 == 0) goto L59
            java.util.Map<android.bluetooth.le.ScanCallback, android.bluetooth.le.BluetoothLeScanner$BleScanCallbackWrapper> r1 = r0.mLeScanClients     // Catch: java.lang.Throwable -> L60
            boolean r1 = r1.containsKey(r15)     // Catch: java.lang.Throwable -> L60
            if (r1 == 0) goto L59
            int r0 = r11.postCallbackErrorOrReturn(r15, r2)     // Catch: java.lang.Throwable -> L60
            monitor-exit(r9)     // Catch: java.lang.Throwable -> L60
            return r0
        L59:
            android.bluetooth.IBluetoothManager r1 = r0.mBluetoothManager     // Catch: java.lang.Throwable -> L60 android.os.RemoteException -> L62
            android.bluetooth.IBluetoothGatt r1 = r1.getBluetoothGatt()     // Catch: java.lang.Throwable -> L60 android.os.RemoteException -> L62
            goto L63
        L60:
            r0 = move-exception
            goto Lba
        L62:
            r1 = 0
        L63:
            r3 = r1
            r6 = 3
            if (r3 != 0) goto L6d
            int r0 = r11.postCallbackErrorOrReturn(r15, r6)     // Catch: java.lang.Throwable -> L60
            monitor-exit(r9)     // Catch: java.lang.Throwable -> L60
            return r0
        L6d:
            boolean r1 = r11.isSettingsConfigAllowedForScan(r13)     // Catch: java.lang.Throwable -> L60
            r2 = 4
            if (r1 != 0) goto L7a
            int r0 = r11.postCallbackErrorOrReturn(r15, r2)     // Catch: java.lang.Throwable -> L60
            monitor-exit(r9)     // Catch: java.lang.Throwable -> L60
            return r0
        L7a:
            boolean r1 = r11.isHardwareResourcesAvailableForScan(r13)     // Catch: java.lang.Throwable -> L60
            if (r1 != 0) goto L87
            r1 = 5
            int r0 = r11.postCallbackErrorOrReturn(r15, r1)     // Catch: java.lang.Throwable -> L60
            monitor-exit(r9)     // Catch: java.lang.Throwable -> L60
            return r0
        L87:
            r4 = r12
            boolean r1 = r11.isSettingsAndFilterComboAllowed(r13, r12)     // Catch: java.lang.Throwable -> L60
            if (r1 != 0) goto L94
            int r0 = r11.postCallbackErrorOrReturn(r15, r2)     // Catch: java.lang.Throwable -> L60
            monitor-exit(r9)     // Catch: java.lang.Throwable -> L60
            return r0
        L94:
            if (r7 == 0) goto La7
            android.bluetooth.le.BluetoothLeScanner$BleScanCallbackWrapper r10 = new android.bluetooth.le.BluetoothLeScanner$BleScanCallbackWrapper     // Catch: java.lang.Throwable -> L60
            r1 = r10
            r2 = r11
            r4 = r12
            r5 = r13
            r6 = r14
            r7 = r15
            r8 = r17
            r1.<init>(r3, r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L60
            r10.startRegistration()     // Catch: java.lang.Throwable -> L60
            goto Lb5
        La7:
            java.lang.String r7 = r0.mOpPackageName     // Catch: java.lang.Throwable -> L60 android.os.RemoteException -> Lb8
            java.lang.String r8 = r0.mFeatureId     // Catch: java.lang.Throwable -> L60 android.os.RemoteException -> Lb8
            r0 = r3
            r1 = r16
            r2 = r13
            r3 = r12
            r4 = r7
            r5 = r8
            r0.startScanForIntent(r1, r2, r3, r4, r5)     // Catch: java.lang.Throwable -> L60 android.os.RemoteException -> Lb8
        Lb5:
            monitor-exit(r9)     // Catch: java.lang.Throwable -> L60
            r0 = 0
            return r0
        Lb8:
            monitor-exit(r9)     // Catch: java.lang.Throwable -> L60
            return r6
        Lba:
            monitor-exit(r9)     // Catch: java.lang.Throwable -> L60
            throw r0
        Lbc:
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
                return;
            }
            bleScanCallbackWrapperRemove.stopLeScan();
            IIddServiceManager iddServiceManagerLoader = IddServiceManagerLoader.getInstance(ActivityThread.currentActivityThread().getSystemContext());
            if (iddServiceManagerLoader != null) {
                IIddBluetooth bluetooth = iddServiceManagerLoader.getBluetooth();
                if (bluetooth != null) {
                    bluetooth.scan(ActivityThread.currentPackageName(), false, "stopScan");
                    return;
                } else {
                    Log.w("Idd", "bluetooth stopScan(), send server failed");
                    return;
                }
            }
            Log.w("Idd", "bluetooth stopScan(), bind server failed");
        }
    }

    public void stopScan(PendingIntent pendingIntent) {
        BluetoothLeUtils.checkAdapterStateOn(this.mBluetoothAdapter);
        try {
            this.mBluetoothManager.getBluetoothGatt().stopScanForIntent(pendingIntent, this.mOpPackageName);
        } catch (RemoteException unused) {
        }
        IIddServiceManager iddServiceManagerLoader = IddServiceManagerLoader.getInstance(ActivityThread.currentActivityThread().getSystemContext());
        if (iddServiceManagerLoader != null) {
            IIddBluetooth bluetooth = iddServiceManagerLoader.getBluetooth();
            if (bluetooth != null) {
                bluetooth.scan(ActivityThread.currentPackageName(), false, "stopScan");
                return;
            } else {
                Log.w("Idd", "bluetooth stopScan(), send server failed");
                return;
            }
        }
        Log.w("Idd", "bluetooth stopScan(), bind server failed");
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
                int i = this.mScannerId;
                if (i == -1 || i == -2) {
                    return;
                }
                try {
                    this.mBluetoothGatt.registerScanner(this, this.mWorkSource);
                    wait(2000L);
                } catch (RemoteException | InterruptedException e) {
                    Log.e(BluetoothLeScanner.TAG, "application registeration exception", e);
                    BluetoothLeScanner.this.postCallbackError(this.mScanCallback, 3);
                }
                int i2 = this.mScannerId;
                if (i2 > 0) {
                    BluetoothLeScanner.this.mLeScanClients.put(this.mScanCallback, this);
                } else {
                    if (i2 == 0) {
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
                int i = this.mScannerId;
                if (i <= 0) {
                    Log.e(BluetoothLeScanner.TAG, "Error state, mLeHandle: " + this.mScannerId);
                    return;
                }
                try {
                    this.mBluetoothGatt.stopScan(i);
                    this.mBluetoothGatt.unregisterScanner(this.mScannerId);
                } catch (RemoteException e) {
                    Log.e(BluetoothLeScanner.TAG, "Failed to stop scan and unregister", e);
                }
                this.mScannerId = -1;
            }
        }

        void flushPendingBatchResults() {
            synchronized (this) {
                int i = this.mScannerId;
                if (i <= 0) {
                    Log.e(BluetoothLeScanner.TAG, "Error state, mLeHandle: " + this.mScannerId);
                    return;
                }
                try {
                    this.mBluetoothGatt.flushPendingBatchResults(i);
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
