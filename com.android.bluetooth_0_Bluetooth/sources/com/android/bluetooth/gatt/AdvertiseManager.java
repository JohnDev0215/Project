package com.android.bluetooth.gatt;

import android.bluetooth.le.AdvertiseData;
import android.bluetooth.le.AdvertisingSetParameters;
import android.bluetooth.le.IAdvertisingSetCallback;
import android.bluetooth.le.PeriodicAdvertisingParameters;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.android.bluetooth.btservice.AdapterService;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
class AdvertiseManager {
    private static final boolean DBG = false;
    private static final String TAG = "BtGatt.AdvertiseManager";
    static int sTempRegistrationId = -1;
    private final AdapterService mAdapterService;
    Map<IBinder, AdvertiserInfo> mAdvertisers = Collections.synchronizedMap(new HashMap());
    private Handler mHandler;
    private final GattService mService;

    private static native void classInitNative();

    private native void cleanupNative();

    private native void enableAdvertisingSetNative(int i, boolean z, int i2, int i3);

    private native void getOwnAddressNative(int i);

    private native void initializeNative();

    private native void setAdvertisingDataNative(int i, byte[] bArr);

    private native void setAdvertisingParametersNative(int i, AdvertisingSetParameters advertisingSetParameters);

    private native void setPeriodicAdvertisingDataNative(int i, byte[] bArr);

    private native void setPeriodicAdvertisingEnableNative(int i, boolean z);

    private native void setPeriodicAdvertisingParametersNative(int i, PeriodicAdvertisingParameters periodicAdvertisingParameters);

    private native void setScanResponseDataNative(int i, byte[] bArr);

    private native void startAdvertisingSetNative(AdvertisingSetParameters advertisingSetParameters, byte[] bArr, byte[] bArr2, PeriodicAdvertisingParameters periodicAdvertisingParameters, byte[] bArr3, int i, int i2, int i3);

    private native void stopAdvertisingSetNative(int i);

    static {
        classInitNative();
    }

    AdvertiseManager(GattService service, AdapterService adapterService) {
        this.mService = service;
        this.mAdapterService = adapterService;
    }

    void start() {
        initializeNative();
        HandlerThread thread = new HandlerThread("BluetoothAdvertiseManager");
        thread.start();
        this.mHandler = new Handler(thread.getLooper());
    }

    void cleanup() {
        cleanupNative();
        this.mAdvertisers.clear();
        sTempRegistrationId = -1;
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            Looper looper = this.mHandler.getLooper();
            if (looper != null) {
                looper.quit();
            }
            this.mHandler = null;
        }
    }

    class AdvertiserInfo {
        public IAdvertisingSetCallback callback;
        public AdvertisingSetDeathRecipient deathRecipient;
        public Integer id;

        AdvertiserInfo(Integer id, AdvertisingSetDeathRecipient deathRecipient, IAdvertisingSetCallback callback) {
            this.id = id;
            this.deathRecipient = deathRecipient;
            this.callback = callback;
        }
    }

    IBinder toBinder(IAdvertisingSetCallback e) {
        return e.asBinder();
    }

    class AdvertisingSetDeathRecipient implements IBinder.DeathRecipient {
        public IAdvertisingSetCallback callback;

        AdvertisingSetDeathRecipient(IAdvertisingSetCallback callback) {
            this.callback = callback;
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            AdvertiseManager.this.stopAdvertisingSet(this.callback);
        }
    }

    Map.Entry<IBinder, AdvertiserInfo> findAdvertiser(int advertiserId) {
        for (Map.Entry<IBinder, AdvertiserInfo> e : this.mAdvertisers.entrySet()) {
            if (e.getValue().id.intValue() == advertiserId) {
                return e;
            }
        }
        return null;
    }

    void onAdvertisingSetStarted(int regId, int advertiserId, int txPower, int status) throws Exception {
        Map.Entry<IBinder, AdvertiserInfo> entry = findAdvertiser(regId);
        if (entry == null) {
            Log.i(TAG, "onAdvertisingSetStarted() - no callback found for regId " + regId);
            stopAdvertisingSetNative(advertiserId);
            return;
        }
        IAdvertisingSetCallback callback = entry.getValue().callback;
        if (status == 0) {
            entry.setValue(new AdvertiserInfo(Integer.valueOf(advertiserId), entry.getValue().deathRecipient, callback));
        } else {
            IBinder binder = entry.getKey();
            binder.unlinkToDeath(entry.getValue().deathRecipient, 0);
            this.mAdvertisers.remove(binder);
        }
        callback.onAdvertisingSetStarted(advertiserId, txPower, status);
    }

    void onAdvertisingEnabled(int advertiserId, boolean enable, int status) throws Exception {
        Map.Entry<IBinder, AdvertiserInfo> entry = findAdvertiser(advertiserId);
        if (entry == null) {
            Log.i(TAG, "onAdvertisingSetEnable() - no callback found for advertiserId " + advertiserId);
            return;
        }
        IAdvertisingSetCallback callback = entry.getValue().callback;
        callback.onAdvertisingEnabled(advertiserId, enable, status);
    }

    void startAdvertisingSet(AdvertisingSetParameters parameters, AdvertiseData advertiseData, AdvertiseData scanResponse, PeriodicAdvertisingParameters periodicParameters, AdvertiseData periodicData, int duration, int maxExtAdvEvents, IAdvertisingSetCallback callback) {
        AdvertisingSetDeathRecipient deathRecipient = new AdvertisingSetDeathRecipient(callback);
        IBinder binder = toBinder(callback);
        try {
            binder.linkToDeath(deathRecipient, 0);
            String deviceName = AdapterService.getAdapterService().getName();
            byte[] advDataBytes = AdvertiseHelper.advertiseDataToBytes(advertiseData, deviceName);
            byte[] scanResponseBytes = AdvertiseHelper.advertiseDataToBytes(scanResponse, deviceName);
            byte[] periodicDataBytes = AdvertiseHelper.advertiseDataToBytes(periodicData, deviceName);
            int cbId = sTempRegistrationId - 1;
            sTempRegistrationId = cbId;
            this.mAdvertisers.put(binder, new AdvertiserInfo(Integer.valueOf(cbId), deathRecipient, callback));
            startAdvertisingSetNative(parameters, advDataBytes, scanResponseBytes, periodicParameters, periodicDataBytes, duration, maxExtAdvEvents, cbId);
        } catch (RemoteException e) {
            throw new IllegalArgumentException("Can't link to advertiser's death");
        }
    }

    void onOwnAddressRead(int advertiserId, int addressType, String address) throws RemoteException {
        Map.Entry<IBinder, AdvertiserInfo> entry = findAdvertiser(advertiserId);
        if (entry == null) {
            Log.w(TAG, "onOwnAddressRead() - bad advertiserId " + advertiserId);
            return;
        }
        IAdvertisingSetCallback callback = entry.getValue().callback;
        callback.onOwnAddressRead(advertiserId, addressType, address);
    }

    void getOwnAddress(int advertiserId) {
        Map.Entry<IBinder, AdvertiserInfo> entry = findAdvertiser(advertiserId);
        if (entry == null) {
            Log.w(TAG, "getOwnAddress() - bad advertiserId " + advertiserId);
            return;
        }
        getOwnAddressNative(advertiserId);
    }

    void stopAdvertisingSet(IAdvertisingSetCallback callback) {
        IBinder binder = toBinder(callback);
        AdvertiserInfo adv = this.mAdvertisers.remove(binder);
        if (adv == null) {
            Log.e(TAG, "stopAdvertisingSet() - no client found for callback");
            return;
        }
        Integer advertiserId = adv.id;
        binder.unlinkToDeath(adv.deathRecipient, 0);
        if (advertiserId.intValue() < 0) {
            Log.i(TAG, "stopAdvertisingSet() - advertiser not finished registration yet");
            return;
        }
        stopAdvertisingSetNative(advertiserId.intValue());
        try {
            callback.onAdvertisingSetStopped(advertiserId.intValue());
        } catch (RemoteException e) {
            Log.i(TAG, "error sending onAdvertisingSetStopped callback", e);
        }
    }

    void enableAdvertisingSet(int advertiserId, boolean enable, int duration, int maxExtAdvEvents) {
        Map.Entry<IBinder, AdvertiserInfo> entry = findAdvertiser(advertiserId);
        if (entry == null) {
            Log.w(TAG, "enableAdvertisingSet() - bad advertiserId " + advertiserId);
            return;
        }
        enableAdvertisingSetNative(advertiserId, enable, duration, maxExtAdvEvents);
    }

    void setAdvertisingData(int advertiserId, AdvertiseData data) {
        Map.Entry<IBinder, AdvertiserInfo> entry = findAdvertiser(advertiserId);
        if (entry == null) {
            Log.w(TAG, "setAdvertisingData() - bad advertiserId " + advertiserId);
            return;
        }
        String deviceName = AdapterService.getAdapterService().getName();
        setAdvertisingDataNative(advertiserId, AdvertiseHelper.advertiseDataToBytes(data, deviceName));
    }

    void setScanResponseData(int advertiserId, AdvertiseData data) {
        Map.Entry<IBinder, AdvertiserInfo> entry = findAdvertiser(advertiserId);
        if (entry == null) {
            Log.w(TAG, "setScanResponseData() - bad advertiserId " + advertiserId);
            return;
        }
        String deviceName = AdapterService.getAdapterService().getName();
        setScanResponseDataNative(advertiserId, AdvertiseHelper.advertiseDataToBytes(data, deviceName));
    }

    void setAdvertisingParameters(int advertiserId, AdvertisingSetParameters parameters) {
        Map.Entry<IBinder, AdvertiserInfo> entry = findAdvertiser(advertiserId);
        if (entry == null) {
            Log.w(TAG, "setAdvertisingParameters() - bad advertiserId " + advertiserId);
            return;
        }
        setAdvertisingParametersNative(advertiserId, parameters);
    }

    void setPeriodicAdvertisingParameters(int advertiserId, PeriodicAdvertisingParameters parameters) {
        Map.Entry<IBinder, AdvertiserInfo> entry = findAdvertiser(advertiserId);
        if (entry == null) {
            Log.w(TAG, "setPeriodicAdvertisingParameters() - bad advertiserId " + advertiserId);
            return;
        }
        setPeriodicAdvertisingParametersNative(advertiserId, parameters);
    }

    void setPeriodicAdvertisingData(int advertiserId, AdvertiseData data) {
        Map.Entry<IBinder, AdvertiserInfo> entry = findAdvertiser(advertiserId);
        if (entry == null) {
            Log.w(TAG, "setPeriodicAdvertisingData() - bad advertiserId " + advertiserId);
            return;
        }
        String deviceName = AdapterService.getAdapterService().getName();
        setPeriodicAdvertisingDataNative(advertiserId, AdvertiseHelper.advertiseDataToBytes(data, deviceName));
    }

    void setPeriodicAdvertisingEnable(int advertiserId, boolean enable) {
        Map.Entry<IBinder, AdvertiserInfo> entry = findAdvertiser(advertiserId);
        if (entry == null) {
            Log.w(TAG, "setPeriodicAdvertisingEnable() - bad advertiserId " + advertiserId);
            return;
        }
        setPeriodicAdvertisingEnableNative(advertiserId, enable);
    }

    void onAdvertisingDataSet(int advertiserId, int status) throws Exception {
        Map.Entry<IBinder, AdvertiserInfo> entry = findAdvertiser(advertiserId);
        if (entry == null) {
            Log.i(TAG, "onAdvertisingDataSet() - bad advertiserId " + advertiserId);
            return;
        }
        IAdvertisingSetCallback callback = entry.getValue().callback;
        callback.onAdvertisingDataSet(advertiserId, status);
    }

    void onScanResponseDataSet(int advertiserId, int status) throws Exception {
        Map.Entry<IBinder, AdvertiserInfo> entry = findAdvertiser(advertiserId);
        if (entry == null) {
            Log.i(TAG, "onScanResponseDataSet() - bad advertiserId " + advertiserId);
            return;
        }
        IAdvertisingSetCallback callback = entry.getValue().callback;
        callback.onScanResponseDataSet(advertiserId, status);
    }

    void onAdvertisingParametersUpdated(int advertiserId, int txPower, int status) throws Exception {
        Map.Entry<IBinder, AdvertiserInfo> entry = findAdvertiser(advertiserId);
        if (entry == null) {
            Log.i(TAG, "onAdvertisingParametersUpdated() - bad advertiserId " + advertiserId);
            return;
        }
        IAdvertisingSetCallback callback = entry.getValue().callback;
        callback.onAdvertisingParametersUpdated(advertiserId, txPower, status);
    }

    void onPeriodicAdvertisingParametersUpdated(int advertiserId, int status) throws Exception {
        Map.Entry<IBinder, AdvertiserInfo> entry = findAdvertiser(advertiserId);
        if (entry == null) {
            Log.i(TAG, "onPeriodicAdvertisingParametersUpdated() - bad advertiserId " + advertiserId);
            return;
        }
        IAdvertisingSetCallback callback = entry.getValue().callback;
        callback.onPeriodicAdvertisingParametersUpdated(advertiserId, status);
    }

    void onPeriodicAdvertisingDataSet(int advertiserId, int status) throws Exception {
        Map.Entry<IBinder, AdvertiserInfo> entry = findAdvertiser(advertiserId);
        if (entry == null) {
            Log.i(TAG, "onPeriodicAdvertisingDataSet() - bad advertiserId " + advertiserId);
            return;
        }
        IAdvertisingSetCallback callback = entry.getValue().callback;
        callback.onPeriodicAdvertisingDataSet(advertiserId, status);
    }

    void onPeriodicAdvertisingEnabled(int advertiserId, boolean enable, int status) throws Exception {
        Map.Entry<IBinder, AdvertiserInfo> entry = findAdvertiser(advertiserId);
        if (entry == null) {
            Log.i(TAG, "onAdvertisingSetEnable() - bad advertiserId " + advertiserId);
            return;
        }
        IAdvertisingSetCallback callback = entry.getValue().callback;
        callback.onPeriodicAdvertisingEnabled(advertiserId, enable, status);
    }
}
