package com.android.bluetooth.sdp;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.SdpDipRecord;
import android.bluetooth.SdpMasRecord;
import android.bluetooth.SdpMnsRecord;
import android.bluetooth.SdpOppOpsRecord;
import android.bluetooth.SdpPseRecord;
import android.bluetooth.SdpRecord;
import android.bluetooth.SdpSapsRecord;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.ParcelUuid;
import android.os.Parcelable;
import android.util.Log;
import com.android.bluetooth.Utils;
import com.android.bluetooth.btservice.AdapterService;
import java.util.ArrayList;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class SdpManager {
    private static final boolean D = true;
    private static final int MESSAGE_SDP_INTENT = 2;
    public static final byte PBAP_REPO_FAVORITES = 8;
    public static final byte PBAP_REPO_LOCAL = 1;
    public static final byte PBAP_REPO_SIM = 2;
    public static final byte PBAP_REPO_SPEED_DAIL = 4;
    private static final int SDP_INTENT_DELAY = 11000;
    private static final String TAG = "SdpManager";
    private static final boolean V = false;
    private static AdapterService sAdapterService;
    private static boolean sNativeAvailable;
    static SdpSearchTracker sSdpSearchTracker;
    private final Handler mHandler = new Handler() { // from class: com.android.bluetooth.sdp.SdpManager.1
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            if (msg.what == 2) {
                SdpSearchInstance msgObj = (SdpSearchInstance) msg.obj;
                Log.w(SdpManager.TAG, "Search timedout for UUID " + msgObj.getUuid());
                synchronized (SdpManager.TRACKER_LOCK) {
                    SdpManager.this.sendSdpIntent(msgObj, null, false);
                }
            }
        }
    };
    static boolean sSearchInProgress = false;
    static final Object TRACKER_LOCK = new Object();
    private static SdpManager sSdpManager = null;

    private static native void classInitNative();

    private native void cleanupNative();

    private native void initializeNative();

    private native int sdpCreateMapMasRecordNative(String str, int i, int i2, int i3, int i4, int i5, int i6);

    private native int sdpCreateMapMnsRecordNative(String str, int i, int i2, int i3, int i4);

    private native int sdpCreateOppOpsRecordNative(String str, int i, int i2, int i3, byte[] bArr);

    private native int sdpCreatePbapPceRecordNative(String str, int i);

    private native int sdpCreatePbapPseRecordNative(String str, int i, int i2, int i3, int i4, int i5);

    private native int sdpCreateSapsRecordNative(String str, int i, int i2);

    private native boolean sdpRemoveSdpRecordNative(int i);

    private native boolean sdpSearchNative(byte[] bArr, byte[] bArr2);

    static {
        classInitNative();
    }

    private class SdpSearchInstance {
        private final BluetoothDevice mDevice;
        private boolean mSearching = true;
        private int mStatus;
        private final ParcelUuid mUuid;

        SdpSearchInstance(int status, BluetoothDevice device, ParcelUuid uuid) {
            this.mStatus = 0;
            this.mDevice = device;
            this.mUuid = uuid;
            this.mStatus = status;
        }

        public BluetoothDevice getDevice() {
            return this.mDevice;
        }

        public ParcelUuid getUuid() {
            return this.mUuid;
        }

        public int getStatus() {
            return this.mStatus;
        }

        public void setStatus(int status) {
            this.mStatus = status;
        }

        public void startSearch() {
            this.mSearching = true;
            Message message = SdpManager.this.mHandler.obtainMessage(2, this);
            SdpManager.this.mHandler.sendMessageDelayed(message, 11000L);
        }

        public void stopSearch() {
            if (this.mSearching) {
                SdpManager.this.mHandler.removeMessages(2, this);
            }
            this.mSearching = false;
        }

        public boolean isSearching() {
            return this.mSearching;
        }
    }

    class SdpSearchTracker {
        private final ArrayList<SdpSearchInstance> mList = new ArrayList<>();

        SdpSearchTracker() {
        }

        void clear() {
            this.mList.clear();
        }

        boolean add(SdpSearchInstance inst) {
            return this.mList.add(inst);
        }

        boolean remove(SdpSearchInstance inst) {
            return this.mList.remove(inst);
        }

        SdpSearchInstance getNext() {
            if (this.mList.size() > 0) {
                return this.mList.get(0);
            }
            return null;
        }

        SdpSearchInstance getSearchInstance(byte[] address, byte[] uuidBytes) {
            String addressString = Utils.getAddressStringFromByte(address);
            ParcelUuid uuid = Utils.byteArrayToUuid(uuidBytes)[0];
            for (SdpSearchInstance inst : this.mList) {
                if (inst.getDevice().getAddress().equals(addressString) && inst.getUuid().equals(uuid)) {
                    return inst;
                }
            }
            return null;
        }

        boolean isSearching(BluetoothDevice device, ParcelUuid uuid) {
            String addressString = device.getAddress();
            for (SdpSearchInstance inst : this.mList) {
                if (inst.getDevice().getAddress().equals(addressString) && inst.getUuid().equals(uuid)) {
                    return inst.isSearching();
                }
            }
            return false;
        }
    }

    private SdpManager(AdapterService adapterService) {
        sSdpSearchTracker = new SdpSearchTracker();
        sAdapterService = adapterService;
        initializeNative();
        sNativeAvailable = true;
    }

    public static SdpManager init(AdapterService adapterService) {
        SdpManager sdpManager = new SdpManager(adapterService);
        sSdpManager = sdpManager;
        return sdpManager;
    }

    public static SdpManager getDefaultManager() {
        return sSdpManager;
    }

    public void cleanup() {
        if (sSdpSearchTracker != null) {
            synchronized (TRACKER_LOCK) {
                sSdpSearchTracker.clear();
            }
        }
        if (sNativeAvailable) {
            cleanupNative();
            sNativeAvailable = false;
        }
        sSdpManager = null;
    }

    void sdpMasRecordFoundCallback(int status, byte[] address, byte[] uuid, int masInstanceId, int l2capPsm, int rfcommCannelNumber, int profileVersion, int supportedFeatures, int supportedMessageTypes, String serviceName, boolean moreResults) throws Throwable {
        synchronized (TRACKER_LOCK) {
            try {
                try {
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    SdpSearchInstance inst = sSdpSearchTracker.getSearchInstance(address, uuid);
                    Parcelable sdpMasRecord = null;
                    if (inst == null) {
                        Log.e(TAG, "sdpRecordFoundCallback: Search instance is NULL");
                        return;
                    }
                    inst.setStatus(status);
                    if (status == 0) {
                        sdpMasRecord = new SdpMasRecord(masInstanceId, l2capPsm, rfcommCannelNumber, profileVersion, supportedFeatures, supportedMessageTypes, serviceName);
                    }
                    Log.d(TAG, "UUID: " + Arrays.toString(uuid));
                    Log.d(TAG, "UUID in parcel: " + Utils.byteArrayToUuid(uuid)[0].toString());
                    sendSdpIntent(inst, sdpMasRecord, moreResults);
                } catch (Throwable th2) {
                    th = th2;
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                throw th;
            }
        }
    }

    void sdpMnsRecordFoundCallback(int status, byte[] address, byte[] uuid, int l2capPsm, int rfcommCannelNumber, int profileVersion, int supportedFeatures, String serviceName, boolean moreResults) throws Throwable {
        synchronized (TRACKER_LOCK) {
            try {
                try {
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    SdpSearchInstance inst = sSdpSearchTracker.getSearchInstance(address, uuid);
                    Parcelable sdpMnsRecord = null;
                    if (inst == null) {
                        Log.e(TAG, "sdpRecordFoundCallback: Search instance is NULL");
                        return;
                    }
                    inst.setStatus(status);
                    if (status == 0) {
                        sdpMnsRecord = new SdpMnsRecord(l2capPsm, rfcommCannelNumber, profileVersion, supportedFeatures, serviceName);
                    }
                    Log.d(TAG, "UUID: " + Arrays.toString(uuid));
                    Log.d(TAG, "UUID in parcel: " + Utils.byteArrayToUuid(uuid)[0].toString());
                    sendSdpIntent(inst, sdpMnsRecord, moreResults);
                } catch (Throwable th2) {
                    th = th2;
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                throw th;
            }
        }
    }

    void sdpPseRecordFoundCallback(int status, byte[] address, byte[] uuid, int l2capPsm, int rfcommCannelNumber, int profileVersion, int supportedFeatures, int supportedRepositories, String serviceName, boolean moreResults) throws Throwable {
        synchronized (TRACKER_LOCK) {
            try {
                try {
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    SdpSearchInstance inst = sSdpSearchTracker.getSearchInstance(address, uuid);
                    Parcelable sdpPseRecord = null;
                    if (inst == null) {
                        Log.e(TAG, "sdpRecordFoundCallback: Search instance is NULL");
                        return;
                    }
                    inst.setStatus(status);
                    if (status == 0) {
                        sdpPseRecord = new SdpPseRecord(l2capPsm, rfcommCannelNumber, profileVersion, supportedFeatures, supportedRepositories, serviceName);
                    }
                    Log.d(TAG, "UUID: " + Arrays.toString(uuid));
                    Log.d(TAG, "UUID in parcel: " + Utils.byteArrayToUuid(uuid)[0].toString());
                    sendSdpIntent(inst, sdpPseRecord, moreResults);
                } catch (Throwable th2) {
                    th = th2;
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                throw th;
            }
        }
    }

    void sdpOppOpsRecordFoundCallback(int status, byte[] address, byte[] uuid, int l2capPsm, int rfcommCannelNumber, int profileVersion, String serviceName, byte[] formatsList, boolean moreResults) throws Throwable {
        synchronized (TRACKER_LOCK) {
            try {
                try {
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    SdpSearchInstance inst = sSdpSearchTracker.getSearchInstance(address, uuid);
                    Parcelable sdpOppOpsRecord = null;
                    if (inst == null) {
                        Log.e(TAG, "sdpOppOpsRecordFoundCallback: Search instance is NULL");
                        return;
                    }
                    inst.setStatus(status);
                    if (status == 0) {
                        sdpOppOpsRecord = new SdpOppOpsRecord(serviceName, rfcommCannelNumber, l2capPsm, profileVersion, formatsList);
                    }
                    Log.d(TAG, "UUID: " + Arrays.toString(uuid));
                    Log.d(TAG, "UUID in parcel: " + Utils.byteArrayToUuid(uuid)[0].toString());
                    sendSdpIntent(inst, sdpOppOpsRecord, moreResults);
                } catch (Throwable th2) {
                    th = th2;
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                throw th;
            }
        }
    }

    void sdpSapsRecordFoundCallback(int status, byte[] address, byte[] uuid, int rfcommCannelNumber, int profileVersion, String serviceName, boolean moreResults) {
        synchronized (TRACKER_LOCK) {
            SdpSearchInstance inst = sSdpSearchTracker.getSearchInstance(address, uuid);
            Parcelable sdpSapsRecord = null;
            if (inst == null) {
                Log.e(TAG, "sdpSapsRecordFoundCallback: Search instance is NULL");
                return;
            }
            inst.setStatus(status);
            if (status == 0) {
                sdpSapsRecord = new SdpSapsRecord(rfcommCannelNumber, profileVersion, serviceName);
            }
            Log.d(TAG, "UUID: " + Arrays.toString(uuid));
            Log.d(TAG, "UUID in parcel: " + Utils.byteArrayToUuid(uuid)[0].toString());
            sendSdpIntent(inst, sdpSapsRecord, moreResults);
        }
    }

    void sdpDipRecordFoundCallback(int status, byte[] address, byte[] uuid, int specificationId, int vendorId, int vendorIdSource, int productId, int version, boolean primaryRecord, String clientExecutableUrl, String serviceDescription, String documentationUrl, boolean moreResults) throws Throwable {
        synchronized (TRACKER_LOCK) {
            try {
                try {
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    SdpSearchInstance inst = sSdpSearchTracker.getSearchInstance(address, uuid);
                    Parcelable sdpDipRecord = null;
                    if (inst == null) {
                        Log.e(TAG, "sdpDipRecordFoundCallback: Search instance is NULL");
                        return;
                    }
                    inst.setStatus(status);
                    Log.d(TAG, "sdpDipRecordFoundCallback: status " + status);
                    if (status == 0) {
                        sdpDipRecord = new SdpDipRecord(specificationId, vendorId, vendorIdSource, productId, version, primaryRecord, clientExecutableUrl, serviceDescription, documentationUrl);
                    }
                    Log.d(TAG, "UUID: " + Arrays.toString(uuid));
                    Log.d(TAG, "UUID in parcel: " + Utils.byteArrayToUuid(uuid)[0].toString());
                    sendSdpIntent(inst, sdpDipRecord, moreResults);
                } catch (Throwable th2) {
                    th = th2;
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                throw th;
            }
        }
    }

    void sdpRecordFoundCallback(int status, byte[] address, byte[] uuid, int sizeRecord, byte[] record) {
        synchronized (TRACKER_LOCK) {
            SdpSearchInstance inst = sSdpSearchTracker.getSearchInstance(address, uuid);
            Parcelable sdpRecord = null;
            if (inst == null) {
                Log.e(TAG, "sdpRecordFoundCallback: Search instance is NULL");
                return;
            }
            inst.setStatus(status);
            if (status == 0) {
                Log.d(TAG, "sdpRecordFoundCallback: found a sdp record of size " + sizeRecord);
                Log.d(TAG, "Record:" + Arrays.toString(record));
                sdpRecord = new SdpRecord(sizeRecord, record);
            }
            Log.d(TAG, "UUID: " + Arrays.toString(uuid));
            Log.d(TAG, "UUID in parcel: " + Utils.byteArrayToUuid(uuid)[0].toString());
            sendSdpIntent(inst, sdpRecord, false);
        }
    }

    public void sdpSearch(BluetoothDevice device, ParcelUuid uuid) {
        if (!sNativeAvailable) {
            Log.e(TAG, "Native not initialized!");
            return;
        }
        synchronized (TRACKER_LOCK) {
            if (sSdpSearchTracker.isSearching(device, uuid)) {
                return;
            }
            SdpSearchInstance inst = new SdpSearchInstance(0, device, uuid);
            sSdpSearchTracker.add(inst);
            startSearch();
        }
    }

    private void startSearch() {
        SdpSearchInstance inst = sSdpSearchTracker.getNext();
        if (inst != null && !sSearchInProgress) {
            Log.d(TAG, "Starting search for UUID: " + inst.getUuid());
            sSearchInProgress = true;
            inst.startSearch();
            sdpSearchNative(Utils.getBytesFromAddress(inst.getDevice().getAddress()), Utils.uuidToByteArray(inst.getUuid()));
            return;
        }
        Log.d(TAG, "startSearch(): nextInst = " + inst + " mSearchInProgress = " + sSearchInProgress + " - search busy or queue empty.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendSdpIntent(SdpSearchInstance inst, Parcelable record, boolean moreResults) {
        inst.stopSearch();
        Intent intent = new Intent("android.bluetooth.device.action.SDP_RECORD");
        intent.putExtra("android.bluetooth.device.extra.DEVICE", inst.getDevice());
        intent.putExtra("android.bluetooth.device.extra.SDP_SEARCH_STATUS", inst.getStatus());
        if (record != null) {
            intent.putExtra("android.bluetooth.device.extra.SDP_RECORD", record);
        }
        intent.putExtra("android.bluetooth.device.extra.UUID", inst.getUuid());
        sAdapterService.sendBroadcast(intent, "android.permission.BLUETOOTH_ADMIN");
        if (!moreResults) {
            sSdpSearchTracker.remove(inst);
            sSearchInProgress = false;
            startSearch();
        }
    }

    public int createMapMasRecord(String serviceName, int masId, int rfcommChannel, int l2capPsm, int version, int msgTypes, int features) {
        if (!sNativeAvailable) {
            throw new RuntimeException("SdpManager sNativeAvailable == false - native not initialized");
        }
        return sdpCreateMapMasRecordNative(serviceName, masId, rfcommChannel, l2capPsm, version, msgTypes, features);
    }

    public int createMapMnsRecord(String serviceName, int rfcommChannel, int l2capPsm, int version, int features) {
        if (!sNativeAvailable) {
            throw new RuntimeException("SdpManager sNativeAvailable == false - native not initialized");
        }
        return sdpCreateMapMnsRecordNative(serviceName, rfcommChannel, l2capPsm, version, features);
    }

    public int createPbapPceRecord(String serviceName, int version) {
        if (!sNativeAvailable) {
            throw new RuntimeException("SdpManager sNativeAvailable == false - native not initialized");
        }
        return sdpCreatePbapPceRecordNative(serviceName, version);
    }

    public int createPbapPseRecord(String serviceName, int rfcommChannel, int l2capPsm, int version, int repositories, int features) {
        if (!sNativeAvailable) {
            throw new RuntimeException("SdpManager sNativeAvailable == false - native not initialized");
        }
        return sdpCreatePbapPseRecordNative(serviceName, rfcommChannel, l2capPsm, version, repositories, features);
    }

    public int createOppOpsRecord(String serviceName, int rfcommChannel, int l2capPsm, int version, byte[] formatsList) {
        if (!sNativeAvailable) {
            throw new RuntimeException("SdpManager sNativeAvailable == false - native not initialized");
        }
        return sdpCreateOppOpsRecordNative(serviceName, rfcommChannel, l2capPsm, version, formatsList);
    }

    public int createSapsRecord(String serviceName, int rfcommChannel, int version) {
        if (!sNativeAvailable) {
            throw new RuntimeException("SdpManager sNativeAvailable == false - native not initialized");
        }
        return sdpCreateSapsRecordNative(serviceName, rfcommChannel, version);
    }

    public boolean removeSdpRecord(int recordId) {
        if (!sNativeAvailable) {
            throw new RuntimeException("SdpManager sNativeAvailable == false - native not initialized");
        }
        return sdpRemoveSdpRecordNative(recordId);
    }
}
