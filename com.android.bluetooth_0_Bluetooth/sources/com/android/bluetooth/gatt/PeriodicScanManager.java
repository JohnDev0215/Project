package com.android.bluetooth.gatt;

import android.bluetooth.le.IPeriodicAdvertisingCallback;
import android.bluetooth.le.PeriodicAdvertisingReport;
import android.bluetooth.le.ScanRecord;
import android.bluetooth.le.ScanResult;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.android.bluetooth.btservice.AdapterService;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
class PeriodicScanManager {
    private static final boolean DBG = false;
    private static final String TAG = "BtGatt.SyncManager";
    static int sTempRegistrationId = -1;
    private final AdapterService mAdapterService;
    Map<IBinder, SyncInfo> mSyncs = Collections.synchronizedMap(new HashMap());

    private static native void classInitNative();

    private native void cleanupNative();

    private native void initializeNative();

    private native void startSyncNative(int i, String str, int i2, int i3, int i4);

    private native void stopSyncNative(int i);

    static {
        classInitNative();
    }

    PeriodicScanManager(AdapterService adapterService) {
        this.mAdapterService = adapterService;
    }

    void start() {
        initializeNative();
    }

    void cleanup() {
        cleanupNative();
        this.mSyncs.clear();
        sTempRegistrationId = -1;
    }

    class SyncInfo {
        public IPeriodicAdvertisingCallback callback;
        public SyncDeathRecipient deathRecipient;
        public Integer id;

        SyncInfo(Integer id, SyncDeathRecipient deathRecipient, IPeriodicAdvertisingCallback callback) {
            this.id = id;
            this.deathRecipient = deathRecipient;
            this.callback = callback;
        }
    }

    IBinder toBinder(IPeriodicAdvertisingCallback e) {
        return e.asBinder();
    }

    class SyncDeathRecipient implements IBinder.DeathRecipient {
        public IPeriodicAdvertisingCallback callback;

        SyncDeathRecipient(IPeriodicAdvertisingCallback callback) {
            this.callback = callback;
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            PeriodicScanManager.this.stopSync(this.callback);
        }
    }

    Map.Entry<IBinder, SyncInfo> findSync(int syncHandle) {
        for (Map.Entry<IBinder, SyncInfo> e : this.mSyncs.entrySet()) {
            if (e.getValue().id.intValue() == syncHandle) {
                return e;
            }
        }
        return null;
    }

    void onSyncStarted(int regId, int syncHandle, int sid, int addressType, String address, int phy, int interval, int status) throws Exception {
        Map.Entry<IBinder, SyncInfo> entry = findSync(regId);
        if (entry == null) {
            Log.i(TAG, "onSyncStarted() - no callback found for regId " + regId);
            stopSyncNative(syncHandle);
            return;
        }
        IPeriodicAdvertisingCallback callback = entry.getValue().callback;
        if (status == 0) {
            entry.setValue(new SyncInfo(Integer.valueOf(syncHandle), entry.getValue().deathRecipient, callback));
            return;
        }
        IBinder binder = entry.getKey();
        binder.unlinkToDeath(entry.getValue().deathRecipient, 0);
        this.mSyncs.remove(binder);
    }

    void onSyncReport(int syncHandle, int txPower, int rssi, int dataStatus, byte[] data) throws Exception {
        Map.Entry<IBinder, SyncInfo> entry = findSync(syncHandle);
        if (entry == null) {
            Log.i(TAG, "onSyncReport() - no callback found for syncHandle " + syncHandle);
            return;
        }
        IPeriodicAdvertisingCallback callback = entry.getValue().callback;
        PeriodicAdvertisingReport report = new PeriodicAdvertisingReport(syncHandle, txPower, rssi, dataStatus, ScanRecord.parseFromBytes(data));
        callback.onPeriodicAdvertisingReport(report);
    }

    void onSyncLost(int syncHandle) throws Exception {
        Map.Entry<IBinder, SyncInfo> entry = findSync(syncHandle);
        if (entry == null) {
            Log.i(TAG, "onSyncLost() - no callback found for syncHandle " + syncHandle);
            return;
        }
        IPeriodicAdvertisingCallback callback = entry.getValue().callback;
        this.mSyncs.remove(entry);
        callback.onSyncLost(syncHandle);
    }

    void startSync(ScanResult scanResult, int skip, int timeout, IPeriodicAdvertisingCallback callback) {
        SyncDeathRecipient deathRecipient = new SyncDeathRecipient(callback);
        IBinder binder = toBinder(callback);
        try {
            binder.linkToDeath(deathRecipient, 0);
            String address = scanResult.getDevice().getAddress();
            int sid = scanResult.getAdvertisingSid();
            int cbId = sTempRegistrationId - 1;
            sTempRegistrationId = cbId;
            this.mSyncs.put(binder, new SyncInfo(Integer.valueOf(cbId), deathRecipient, callback));
            startSyncNative(sid, address, skip, timeout, cbId);
        } catch (RemoteException e) {
            throw new IllegalArgumentException("Can't link to periodic scanner death");
        }
    }

    void stopSync(IPeriodicAdvertisingCallback callback) {
        IBinder binder = toBinder(callback);
        SyncInfo sync = this.mSyncs.remove(binder);
        if (sync == null) {
            Log.e(TAG, "stopSync() - no client found for callback");
            return;
        }
        Integer syncHandle = sync.id;
        binder.unlinkToDeath(sync.deathRecipient, 0);
        if (syncHandle.intValue() < 0) {
            Log.i(TAG, "stopSync() - not finished registration yet");
        } else {
            stopSyncNative(syncHandle.intValue());
        }
    }
}
