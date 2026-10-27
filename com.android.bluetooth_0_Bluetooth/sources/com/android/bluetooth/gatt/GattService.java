package com.android.bluetooth.gatt;

import android.app.AppOpsManager;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.IBluetoothGatt;
import android.bluetooth.IBluetoothGattCallback;
import android.bluetooth.IBluetoothGattServerCallback;
import android.bluetooth.le.AdvertiseData;
import android.bluetooth.le.AdvertisingSetParameters;
import android.bluetooth.le.IAdvertisingSetCallback;
import android.bluetooth.le.IPeriodicAdvertisingCallback;
import android.bluetooth.le.IScannerCallback;
import android.bluetooth.le.PeriodicAdvertisingParameters;
import android.bluetooth.le.ResultStorageDescriptor;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanRecord;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.companion.ICompanionDeviceManager;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.ParcelUuid;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.SystemClock;
import android.os.UserHandle;
import android.os.WorkSource;
import android.provider.Settings;
import android.util.Log;
import com.android.bluetooth.BluetoothMetricsProto;
import com.android.bluetooth.R;
import com.android.bluetooth.Utils;
import com.android.bluetooth.btservice.AdapterService;
import com.android.bluetooth.btservice.ProfileService;
import com.android.bluetooth.gatt.HandleMap;
import com.android.bluetooth.util.NumberUtils;
import com.google.common.base.Ascii;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class GattService extends ProfileService {
    private static final int ADVT_STATE_ONFOUND = 0;
    private static final int ADVT_STATE_ONLOST = 1;
    private static final boolean DBG = false;
    private static final int ET_LEGACY_MASK = 16;
    private static final int EXPOSURE_NOTIFICATION_FLAGS_LENGTH = 3;
    private static final byte[] EXPOSURE_NOTIFICATION_FLAGS_PREAMBLE;
    private static final int EXPOSURE_NOTIFICATION_PAYLOAD_LENGTH = 28;
    private static final byte[] EXPOSURE_NOTIFICATION_PAYLOAD_PREAMBLE;
    private static final int MAC_ADDRESS_LENGTH = 6;
    private static final int NUM_SCAN_EVENTS_KEPT = 20;
    static final int SCAN_FILTER_ENABLED = 1;
    static final int SCAN_FILTER_MODIFIED = 2;
    private static final String TAG = "BtGatt.GattService";
    private static final int TIME_STAMP_LENGTH = 2;
    private static final int TRUNCATED_RESULT_SIZE = 11;
    private static final boolean VDBG = false;
    private static GattService sGattService;
    private BluetoothAdapter mAdapter;
    private AdvertiseManager mAdvertiseManager;
    private AppOpsManager mAppOps;
    private ICompanionDeviceManager mCompanionManager;
    private String mExposureNotificationPackage;
    private int mMaxScanFilters;
    private PeriodicScanManager mPeriodicScanManager;
    private ScanManager mScanManager;
    private static final UUID HID_SERVICE_UUID = UUID.fromString("00001812-0000-1000-8000-00805F9B34FB");
    private static final UUID[] HID_UUIDS = {UUID.fromString("00002A4A-0000-1000-8000-00805F9B34FB"), UUID.fromString("00002A4B-0000-1000-8000-00805F9B34FB"), UUID.fromString("00002A4C-0000-1000-8000-00805F9B34FB"), UUID.fromString("00002A4D-0000-1000-8000-00805F9B34FB")};
    private static final UUID ANDROID_TV_REMOTE_SERVICE_UUID = UUID.fromString("AB5E0001-5A21-4F05-BC7D-AF01F617B664");
    private static final UUID FIDO_SERVICE_UUID = UUID.fromString("0000FFFD-0000-1000-8000-00805F9B34FB");
    ScannerMap mScannerMap = new ScannerMap();
    ClientMap mClientMap = new ClientMap();
    ServerMap mServerMap = new ServerMap();
    HandleMap mHandleMap = new HandleMap();
    private List<UUID> mAdvertisingServiceUuids = new ArrayList();
    private final ArrayDeque<BluetoothMetricsProto.ScanEvent> mScanEvents = new ArrayDeque<>(20);
    private final Map<Integer, Set<Integer>> mRestrictedHandles = new HashMap();
    private Set<String> mReliableQueue = new HashSet();

    private static native void classInitNative();

    private native void cleanupNative();

    private native void gattClientConfigureMTUNative(int i, int i2);

    private native void gattClientConnectNative(int i, String str, boolean z, int i2, boolean z2, int i3);

    private native void gattClientDisconnectNative(int i, String str, int i2);

    private native void gattClientDiscoverServiceByUuidNative(int i, long j, long j2);

    private native void gattClientExecuteWriteNative(int i, boolean z);

    private native int gattClientGetDeviceTypeNative(String str);

    /* JADX INFO: Access modifiers changed from: private */
    public native void gattClientGetGattDbNative(int i);

    private native void gattClientReadCharacteristicNative(int i, int i2, int i3);

    private native void gattClientReadDescriptorNative(int i, int i2, int i3);

    private native void gattClientReadPhyNative(int i, String str);

    private native void gattClientReadRemoteRssiNative(int i, String str);

    private native void gattClientReadUsingCharacteristicUuidNative(int i, long j, long j2, int i2, int i3, int i4);

    private native void gattClientRefreshNative(int i, String str);

    private native void gattClientRegisterAppNative(long j, long j2);

    private native void gattClientRegisterForNotificationsNative(int i, String str, int i2, boolean z);

    private native void gattClientSearchServiceNative(int i, boolean z, long j, long j2);

    private native void gattClientSetPreferredPhyNative(int i, String str, int i2, int i3, int i4);

    private native void gattClientUnregisterAppNative(int i);

    private native void gattClientWriteCharacteristicNative(int i, int i2, int i3, int i4, byte[] bArr);

    private native void gattClientWriteDescriptorNative(int i, int i2, int i3, byte[] bArr);

    private native void gattConnectionParameterUpdateNative(int i, String str, int i2, int i3, int i4, int i5, int i6, int i7);

    private native void gattServerAddServiceNative(int i, List<GattDbElement> list);

    private native void gattServerConnectNative(int i, String str, boolean z, int i2);

    private native void gattServerDeleteServiceNative(int i, int i2);

    private native void gattServerDisconnectNative(int i, String str, int i2);

    private native void gattServerReadPhyNative(int i, String str);

    private native void gattServerRegisterAppNative(long j, long j2);

    private native void gattServerSendIndicationNative(int i, int i2, int i3, byte[] bArr);

    private native void gattServerSendNotificationNative(int i, int i2, int i3, byte[] bArr);

    private native void gattServerSendResponseNative(int i, int i2, int i3, int i4, int i5, int i6, byte[] bArr, int i7);

    private native void gattServerSetPreferredPhyNative(int i, String str, int i2, int i3, int i4);

    private native void gattServerStopServiceNative(int i, int i2);

    private native void gattServerUnregisterAppNative(int i);

    private native void gattTestNative(int i, long j, long j2, String str, int i2, int i3, int i4, int i5, int i6);

    private native void initializeNative();

    static {
        classInitNative();
        EXPOSURE_NOTIFICATION_FLAGS_PREAMBLE = new byte[]{2, 1};
        EXPOSURE_NOTIFICATION_PAYLOAD_PREAMBLE = new byte[]{3, 3, 111, -3, Ascii.ETB, Ascii.SYN, 111, -3};
    }

    class PendingIntentInfo {
        public String callingPackage;
        public List<ScanFilter> filters;
        public PendingIntent intent;
        public ScanSettings settings;

        PendingIntentInfo() {
        }

        public boolean equals(Object other) {
            if (!(other instanceof PendingIntentInfo)) {
                return false;
            }
            return this.intent.equals(((PendingIntentInfo) other).intent);
        }
    }

    class ScannerMap extends ContextMap<IScannerCallback, PendingIntentInfo> {
        ScannerMap() {
        }
    }

    class ClientMap extends ContextMap<IBluetoothGattCallback, Void> {
        ClientMap() {
        }
    }

    class ServerMap extends ContextMap<IBluetoothGattServerCallback, Void> {
        ServerMap() {
        }
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    protected ProfileService.IProfileServiceBinder initBinder() {
        return new BluetoothGattBinder(this);
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    protected boolean start() {
        this.mExposureNotificationPackage = getString(R.string.exposure_notification_package);
        Settings.Global.putInt(getContentResolver(), "bluetooth_sanitized_exposure_notification_supported", 1);
        initializeNative();
        this.mAdapter = BluetoothAdapter.getDefaultAdapter();
        this.mCompanionManager = ICompanionDeviceManager.Stub.asInterface(ServiceManager.getService("companiondevice"));
        this.mAppOps = (AppOpsManager) getSystemService(AppOpsManager.class);
        AdvertiseManager advertiseManager = new AdvertiseManager(this, AdapterService.getAdapterService());
        this.mAdvertiseManager = advertiseManager;
        advertiseManager.start();
        ScanManager scanManager = new ScanManager(this);
        this.mScanManager = scanManager;
        scanManager.start();
        PeriodicScanManager periodicScanManager = new PeriodicScanManager(AdapterService.getAdapterService());
        this.mPeriodicScanManager = periodicScanManager;
        periodicScanManager.start();
        setGattService(this);
        return true;
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    protected boolean stop() {
        setGattService(null);
        this.mScannerMap.clear();
        this.mClientMap.clear();
        this.mServerMap.clear();
        this.mHandleMap.clear();
        this.mReliableQueue.clear();
        AdvertiseManager advertiseManager = this.mAdvertiseManager;
        if (advertiseManager != null) {
            advertiseManager.cleanup();
        }
        ScanManager scanManager = this.mScanManager;
        if (scanManager != null) {
            scanManager.cleanup();
        }
        PeriodicScanManager periodicScanManager = this.mPeriodicScanManager;
        if (periodicScanManager != null) {
            periodicScanManager.cleanup();
            return true;
        }
        return true;
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    protected void cleanup() {
        cleanupNative();
        AdvertiseManager advertiseManager = this.mAdvertiseManager;
        if (advertiseManager != null) {
            advertiseManager.cleanup();
        }
        ScanManager scanManager = this.mScanManager;
        if (scanManager != null) {
            scanManager.cleanup();
        }
        PeriodicScanManager periodicScanManager = this.mPeriodicScanManager;
        if (periodicScanManager != null) {
            periodicScanManager.cleanup();
        }
    }

    public static synchronized GattService getGattService() {
        if (sGattService == null) {
            Log.w(TAG, "getGattService(): service is null");
            return null;
        }
        if (!sGattService.isAvailable()) {
            Log.w(TAG, "getGattService(): service is not available");
            return null;
        }
        return sGattService;
    }

    private static synchronized void setGattService(GattService instance) {
        sGattService = instance;
    }

    private boolean permissionCheck(UUID characteristicUuid) {
        return !isHidCharUuid(characteristicUuid) || checkCallingOrSelfPermission("android.permission.BLUETOOTH_PRIVILEGED") == 0;
    }

    private boolean permissionCheck(int connId, int handle) {
        Set<Integer> restrictedHandles = this.mRestrictedHandles.get(Integer.valueOf(connId));
        return restrictedHandles == null || !restrictedHandles.contains(Integer.valueOf(handle)) || checkCallingOrSelfPermission("android.permission.BLUETOOTH_PRIVILEGED") == 0;
    }

    private boolean permissionCheck(ContextMap<IBluetoothGattCallback, Void>.App app, int connId, int handle) {
        Set<Integer> restrictedHandles = this.mRestrictedHandles.get(Integer.valueOf(connId));
        if (restrictedHandles == null || !restrictedHandles.contains(Integer.valueOf(handle))) {
            return true;
        }
        if (!app.hasBluetoothPrivilegedPermission && checkCallingOrSelfPermission("android.permission.BLUETOOTH_PRIVILEGED") == 0) {
            app.hasBluetoothPrivilegedPermission = true;
        }
        return app.hasBluetoothPrivilegedPermission;
    }

    @Override // com.android.bluetooth.btservice.ProfileService, android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (GattDebugUtils.handleDebugAction(this, intent)) {
            return 2;
        }
        return super.onStartCommand(intent, flags, startId);
    }

    class ScannerDeathRecipient implements IBinder.DeathRecipient {
        int mScannerId;

        ScannerDeathRecipient(int scannerId) {
            this.mScannerId = scannerId;
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            ScanClient client = getScanClient(this.mScannerId);
            if (client != null) {
                client.appDied = true;
                GattService.this.stopScan(client.scannerId);
            }
        }

        private ScanClient getScanClient(int clientIf) {
            for (ScanClient client : GattService.this.mScanManager.getRegularScanQueue()) {
                if (client.scannerId == clientIf) {
                    return client;
                }
            }
            for (ScanClient client2 : GattService.this.mScanManager.getBatchScanQueue()) {
                if (client2.scannerId == clientIf) {
                    return client2;
                }
            }
            return null;
        }
    }

    class ServerDeathRecipient implements IBinder.DeathRecipient {
        int mAppIf;

        ServerDeathRecipient(int appIf) {
            this.mAppIf = appIf;
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            GattService.this.unregisterServer(this.mAppIf);
        }
    }

    class ClientDeathRecipient implements IBinder.DeathRecipient {
        int mAppIf;

        ClientDeathRecipient(int appIf) {
            this.mAppIf = appIf;
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            GattService.this.unregisterClient(this.mAppIf);
        }
    }

    private static class BluetoothGattBinder extends IBluetoothGatt.Stub implements ProfileService.IProfileServiceBinder {
        private GattService mService;

        BluetoothGattBinder(GattService svc) {
            this.mService = svc;
        }

        @Override // com.android.bluetooth.btservice.ProfileService.IProfileServiceBinder
        public void cleanup() {
            this.mService = null;
        }

        private GattService getService() {
            GattService gattService = this.mService;
            if (gattService != null && gattService.isAvailable()) {
                return this.mService;
            }
            Log.e(GattService.TAG, "getService() - Service requested, but not available!");
            return null;
        }

        public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] states) {
            GattService service = getService();
            if (service == null) {
                return new ArrayList();
            }
            return service.getDevicesMatchingConnectionStates(states);
        }

        public void registerClient(ParcelUuid uuid, IBluetoothGattCallback callback) {
            GattService service = getService();
            if (service == null) {
                return;
            }
            service.registerClient(uuid.getUuid(), callback);
        }

        public void unregisterClient(int clientIf) {
            GattService service = getService();
            if (service == null) {
                return;
            }
            service.unregisterClient(clientIf);
        }

        public void registerScanner(IScannerCallback callback, WorkSource workSource) throws RemoteException {
            GattService service = getService();
            if (service == null) {
                return;
            }
            service.registerScanner(callback, workSource);
        }

        public void unregisterScanner(int scannerId) {
            GattService service = getService();
            if (service == null) {
                return;
            }
            service.unregisterScanner(scannerId);
        }

        public void startScan(int scannerId, ScanSettings settings, List<ScanFilter> filters, List storages, String callingPackage, String callingFeatureId) {
            GattService service = getService();
            if (service == null) {
                return;
            }
            service.startScan(scannerId, settings, filters, storages, callingPackage, callingFeatureId);
        }

        public void startScanForIntent(PendingIntent intent, ScanSettings settings, List<ScanFilter> filters, String callingPackage, String callingFeatureId) throws RemoteException {
            GattService service = getService();
            if (service == null) {
                return;
            }
            service.registerPiAndStartScan(intent, settings, filters, callingPackage, callingFeatureId);
        }

        public void stopScanForIntent(PendingIntent intent, String callingPackage) throws RemoteException {
            GattService service = getService();
            if (service == null) {
                return;
            }
            service.stopScan(intent, callingPackage);
        }

        public void stopScan(int scannerId) {
            GattService service = getService();
            if (service == null) {
                return;
            }
            service.stopScan(scannerId);
        }

        public void flushPendingBatchResults(int scannerId) {
            GattService service = getService();
            if (service == null) {
                return;
            }
            service.flushPendingBatchResults(scannerId);
        }

        public void clientConnect(int clientIf, String address, boolean isDirect, int transport, boolean opportunistic, int phy) {
            GattService service = getService();
            if (service == null) {
                return;
            }
            service.clientConnect(clientIf, address, isDirect, transport, opportunistic, phy);
        }

        public void clientDisconnect(int clientIf, String address) {
            GattService service = getService();
            if (service == null) {
                return;
            }
            service.clientDisconnect(clientIf, address);
        }

        public void clientSetPreferredPhy(int clientIf, String address, int txPhy, int rxPhy, int phyOptions) {
            GattService service = getService();
            if (service == null) {
                return;
            }
            service.clientSetPreferredPhy(clientIf, address, txPhy, rxPhy, phyOptions);
        }

        public void clientReadPhy(int clientIf, String address) {
            GattService service = getService();
            if (service == null) {
                return;
            }
            service.clientReadPhy(clientIf, address);
        }

        public void refreshDevice(int clientIf, String address) {
            GattService service = getService();
            if (service == null) {
                return;
            }
            service.refreshDevice(clientIf, address);
        }

        public void discoverServices(int clientIf, String address) {
            GattService service = getService();
            if (service == null) {
                return;
            }
            service.discoverServices(clientIf, address);
        }

        public void discoverServiceByUuid(int clientIf, String address, ParcelUuid uuid) {
            GattService service = getService();
            if (service == null) {
                return;
            }
            service.discoverServiceByUuid(clientIf, address, uuid.getUuid());
        }

        public void readCharacteristic(int clientIf, String address, int handle, int authReq) {
            GattService service = getService();
            if (service == null) {
                return;
            }
            service.readCharacteristic(clientIf, address, handle, authReq);
        }

        public void readUsingCharacteristicUuid(int clientIf, String address, ParcelUuid uuid, int startHandle, int endHandle, int authReq) {
            GattService service = getService();
            if (service == null) {
                return;
            }
            service.readUsingCharacteristicUuid(clientIf, address, uuid.getUuid(), startHandle, endHandle, authReq);
        }

        public void writeCharacteristic(int clientIf, String address, int handle, int writeType, int authReq, byte[] value) {
            GattService service = getService();
            if (service == null) {
                return;
            }
            service.writeCharacteristic(clientIf, address, handle, writeType, authReq, value);
        }

        public void readDescriptor(int clientIf, String address, int handle, int authReq) {
            GattService service = getService();
            if (service == null) {
                return;
            }
            service.readDescriptor(clientIf, address, handle, authReq);
        }

        public void writeDescriptor(int clientIf, String address, int handle, int authReq, byte[] value) {
            GattService service = getService();
            if (service == null) {
                return;
            }
            service.writeDescriptor(clientIf, address, handle, authReq, value);
        }

        public void beginReliableWrite(int clientIf, String address) {
            GattService service = getService();
            if (service == null) {
                return;
            }
            service.beginReliableWrite(clientIf, address);
        }

        public void endReliableWrite(int clientIf, String address, boolean execute) {
            GattService service = getService();
            if (service == null) {
                return;
            }
            service.endReliableWrite(clientIf, address, execute);
        }

        public void registerForNotification(int clientIf, String address, int handle, boolean enable) {
            GattService service = getService();
            if (service == null) {
                return;
            }
            service.registerForNotification(clientIf, address, handle, enable);
        }

        public void readRemoteRssi(int clientIf, String address) {
            GattService service = getService();
            if (service == null) {
                return;
            }
            service.readRemoteRssi(clientIf, address);
        }

        public void configureMTU(int clientIf, String address, int mtu) {
            GattService service = getService();
            if (service == null) {
                return;
            }
            service.configureMTU(clientIf, address, mtu);
        }

        public void connectionParameterUpdate(int clientIf, String address, int connectionPriority) {
            GattService service = getService();
            if (service == null) {
                return;
            }
            service.connectionParameterUpdate(clientIf, address, connectionPriority);
        }

        public void leConnectionUpdate(int clientIf, String address, int minConnectionInterval, int maxConnectionInterval, int slaveLatency, int supervisionTimeout, int minConnectionEventLen, int maxConnectionEventLen) {
            GattService service = getService();
            if (service == null) {
                return;
            }
            service.leConnectionUpdate(clientIf, address, minConnectionInterval, maxConnectionInterval, slaveLatency, supervisionTimeout, minConnectionEventLen, maxConnectionEventLen);
        }

        public void registerServer(ParcelUuid uuid, IBluetoothGattServerCallback callback) {
            GattService service = getService();
            if (service == null) {
                return;
            }
            service.registerServer(uuid.getUuid(), callback);
        }

        public void unregisterServer(int serverIf) {
            GattService service = getService();
            if (service == null) {
                return;
            }
            service.unregisterServer(serverIf);
        }

        public void serverConnect(int serverIf, String address, boolean isDirect, int transport) {
            GattService service = getService();
            if (service == null) {
                return;
            }
            service.serverConnect(serverIf, address, isDirect, transport);
        }

        public void serverDisconnect(int serverIf, String address) {
            GattService service = getService();
            if (service == null) {
                return;
            }
            service.serverDisconnect(serverIf, address);
        }

        public void serverSetPreferredPhy(int serverIf, String address, int txPhy, int rxPhy, int phyOptions) {
            GattService service = getService();
            if (service == null) {
                return;
            }
            service.serverSetPreferredPhy(serverIf, address, txPhy, rxPhy, phyOptions);
        }

        public void serverReadPhy(int clientIf, String address) {
            GattService service = getService();
            if (service == null) {
                return;
            }
            service.serverReadPhy(clientIf, address);
        }

        public void addService(int serverIf, BluetoothGattService svc) {
            GattService service = getService();
            if (service == null) {
                return;
            }
            service.addService(serverIf, svc);
        }

        public void removeService(int serverIf, int handle) {
            GattService service = getService();
            if (service == null) {
                return;
            }
            service.removeService(serverIf, handle);
        }

        public void clearServices(int serverIf) {
            GattService service = getService();
            if (service == null) {
                return;
            }
            service.clearServices(serverIf);
        }

        public void sendResponse(int serverIf, String address, int requestId, int status, int offset, byte[] value) {
            GattService service = getService();
            if (service == null) {
                return;
            }
            service.sendResponse(serverIf, address, requestId, status, offset, value);
        }

        public void sendNotification(int serverIf, String address, int handle, boolean confirm, byte[] value) {
            GattService service = getService();
            if (service == null) {
                return;
            }
            service.sendNotification(serverIf, address, handle, confirm, value);
        }

        public void startAdvertisingSet(AdvertisingSetParameters parameters, AdvertiseData advertiseData, AdvertiseData scanResponse, PeriodicAdvertisingParameters periodicParameters, AdvertiseData periodicData, int duration, int maxExtAdvEvents, IAdvertisingSetCallback callback) {
            GattService service = getService();
            if (service == null) {
                return;
            }
            service.startAdvertisingSet(parameters, advertiseData, scanResponse, periodicParameters, periodicData, duration, maxExtAdvEvents, callback);
        }

        public void stopAdvertisingSet(IAdvertisingSetCallback callback) {
            GattService service = getService();
            if (service == null) {
                return;
            }
            service.stopAdvertisingSet(callback);
        }

        public void getOwnAddress(int advertiserId) {
            GattService service = getService();
            if (service == null) {
                return;
            }
            service.getOwnAddress(advertiserId);
        }

        public void enableAdvertisingSet(int advertiserId, boolean enable, int duration, int maxExtAdvEvents) {
            GattService service = getService();
            if (service == null) {
                return;
            }
            service.enableAdvertisingSet(advertiserId, enable, duration, maxExtAdvEvents);
        }

        public void setAdvertisingData(int advertiserId, AdvertiseData data) {
            GattService service = getService();
            if (service == null) {
                return;
            }
            service.setAdvertisingData(advertiserId, data);
        }

        public void setScanResponseData(int advertiserId, AdvertiseData data) {
            GattService service = getService();
            if (service == null) {
                return;
            }
            service.setScanResponseData(advertiserId, data);
        }

        public void setAdvertisingParameters(int advertiserId, AdvertisingSetParameters parameters) {
            GattService service = getService();
            if (service == null) {
                return;
            }
            service.setAdvertisingParameters(advertiserId, parameters);
        }

        public void setPeriodicAdvertisingParameters(int advertiserId, PeriodicAdvertisingParameters parameters) {
            GattService service = getService();
            if (service == null) {
                return;
            }
            service.setPeriodicAdvertisingParameters(advertiserId, parameters);
        }

        public void setPeriodicAdvertisingData(int advertiserId, AdvertiseData data) {
            GattService service = getService();
            if (service == null) {
                return;
            }
            service.setPeriodicAdvertisingData(advertiserId, data);
        }

        public void setPeriodicAdvertisingEnable(int advertiserId, boolean enable) {
            GattService service = getService();
            if (service == null) {
                return;
            }
            service.setPeriodicAdvertisingEnable(advertiserId, enable);
        }

        public void registerSync(ScanResult scanResult, int skip, int timeout, IPeriodicAdvertisingCallback callback) {
            GattService service = getService();
            if (service == null) {
                return;
            }
            service.registerSync(scanResult, skip, timeout, callback);
        }

        public void unregisterSync(IPeriodicAdvertisingCallback callback) {
            GattService service = getService();
            if (service == null) {
                return;
            }
            service.unregisterSync(callback);
        }

        public void disconnectAll() {
            GattService service = getService();
            if (service == null) {
                return;
            }
            service.disconnectAll();
        }

        public void unregAll() {
            GattService service = getService();
            if (service == null) {
                return;
            }
            service.unregAll();
        }

        public int numHwTrackFiltersAvailable() {
            GattService service = getService();
            if (service == null) {
                return 0;
            }
            return service.numHwTrackFiltersAvailable();
        }
    }

    private static boolean arrayStartsWith(byte[] array, byte[] prefix) {
        if (array.length < prefix.length) {
            return false;
        }
        for (int i = 0; i < prefix.length; i++) {
            if (prefix[i] != array[i]) {
                return false;
            }
        }
        return true;
    }

    ScanResult getSanitizedExposureNotification(ScanResult result) {
        ScanRecord record = result.getScanRecord();
        if (record.getBytes().length > 3 && arrayStartsWith(record.getBytes(), EXPOSURE_NOTIFICATION_FLAGS_PREAMBLE)) {
            record = ScanRecord.parseFromBytes(Arrays.copyOfRange(record.getBytes(), 3, record.getBytes().length));
        }
        if (record.getBytes().length == 28 && arrayStartsWith(record.getBytes(), EXPOSURE_NOTIFICATION_PAYLOAD_PREAMBLE)) {
            return new ScanResult(null, 0, 0, 0, 0, 0, result.getRssi(), 0, record, 0L);
        }
        return null;
    }

    void onScanResult(int eventType, int addressType, String address, int primaryPhy, int secondaryPhy, int advertisingSid, int txPower, int rssi, int periodicAdvInt, byte[] advData) {
        byte[] scanRecordData;
        boolean hasPermission;
        ScanResult result;
        ScanResult sanitized;
        List<UUID> remoteUuids = parseUuids(advData);
        byte[] legacyAdvData = Arrays.copyOfRange(advData, 0, 62);
        for (ScanClient client : this.mScanManager.getRegularScanQueue()) {
            if (client.uuids.length > 0) {
                int matches = 0;
                for (UUID search : client.uuids) {
                    Iterator<UUID> it = remoteUuids.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            UUID remote = it.next();
                            if (remote.equals(search)) {
                                matches++;
                                break;
                            }
                        }
                    }
                }
                if (matches < client.uuids.length) {
                }
            }
            ContextMap<IScannerCallback, PendingIntentInfo>.App app = this.mScannerMap.getById(client.scannerId);
            if (app != null) {
                BluetoothDevice device = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(address);
                ScanSettings settings = client.settings;
                if (settings.getLegacy()) {
                    if ((eventType & 16) != 0) {
                        scanRecordData = legacyAdvData;
                    }
                } else {
                    scanRecordData = advData;
                }
                ScanResult result2 = new ScanResult(device, eventType, primaryPhy, secondaryPhy, advertisingSid, txPower, rssi, periodicAdvInt, ScanRecord.parseFromBytes(scanRecordData), SystemClock.elapsedRealtimeNanos());
                boolean hasPermission2 = hasScanResultPermission(client);
                if (!hasPermission2) {
                    Iterator<String> it2 = client.associatedDevices.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            break;
                        }
                        String associatedDevice = it2.next();
                        if (associatedDevice.equalsIgnoreCase(address)) {
                            hasPermission2 = true;
                            break;
                        }
                    }
                }
                if (!hasPermission2 && client.eligibleForSanitizedExposureNotification && (sanitized = getSanitizedExposureNotification(result2)) != null) {
                    hasPermission = true;
                    result = sanitized;
                } else {
                    hasPermission = hasPermission2;
                    result = result2;
                }
                if (hasPermission && matchesFilters(client, result) && (settings.getCallbackType() & 1) != 0) {
                    try {
                        app.appScanStats.addResult(client.scannerId);
                        if (app.callback != null) {
                            app.callback.onScanResult(result);
                        } else {
                            ArrayList<ScanResult> results = new ArrayList<>();
                            results.add(result);
                            sendResultsByPendingIntent(app.info, results, 1);
                        }
                    } catch (PendingIntent.CanceledException | RemoteException e) {
                        Log.e(TAG, "Exception: " + e);
                        this.mScannerMap.remove(client.scannerId);
                        this.mScanManager.stopScan(client.scannerId);
                    }
                }
            }
        }
    }

    private void sendResultByPendingIntent(PendingIntentInfo pii, ScanResult result, int callbackType, ScanClient client) {
        ArrayList<ScanResult> results = new ArrayList<>();
        results.add(result);
        try {
            sendResultsByPendingIntent(pii, results, callbackType);
        } catch (PendingIntent.CanceledException e) {
            stopScan(client.scannerId);
            unregisterScanner(client.scannerId);
        }
    }

    private void sendResultsByPendingIntent(PendingIntentInfo pii, ArrayList<ScanResult> results, int callbackType) throws PendingIntent.CanceledException {
        Intent extrasIntent = new Intent();
        extrasIntent.putParcelableArrayListExtra("android.bluetooth.le.extra.LIST_SCAN_RESULT", results);
        extrasIntent.putExtra("android.bluetooth.le.extra.CALLBACK_TYPE", callbackType);
        pii.intent.send(this, 0, extrasIntent);
    }

    private void sendErrorByPendingIntent(PendingIntentInfo pii, int errorCode) throws PendingIntent.CanceledException {
        Intent extrasIntent = new Intent();
        extrasIntent.putExtra("android.bluetooth.le.extra.ERROR_CODE", errorCode);
        pii.intent.send(this, 0, extrasIntent);
    }

    void onScannerRegistered(int status, int scannerId, long uuidLsb, long uuidMsb) throws RemoteException {
        UUID uuid = new UUID(uuidMsb, uuidLsb);
        ContextMap<IScannerCallback, PendingIntentInfo>.App cbApp = this.mScannerMap.getByUuid(uuid);
        if (cbApp != null) {
            if (status == 0) {
                cbApp.id = scannerId;
                if (cbApp.callback != null) {
                    cbApp.linkToDeath(new ScannerDeathRecipient(scannerId));
                } else {
                    continuePiStartScan(scannerId, cbApp);
                }
            } else {
                this.mScannerMap.remove(scannerId);
            }
            if (cbApp.callback != null) {
                cbApp.callback.onScannerRegistered(status, scannerId);
            }
        }
    }

    private boolean hasScanResultPermission(ScanClient client) {
        if (client.hasNetworkSettingsPermission || client.hasNetworkSetupWizardPermission || client.hasScanWithoutLocationPermission) {
            return true;
        }
        return client.hasLocationPermission && !Utils.blockedByLocationOff(this, client.userHandle);
    }

    private boolean matchesFilters(ScanClient client, ScanResult scanResult) {
        if (client.filters == null || client.filters.isEmpty()) {
            return true;
        }
        for (ScanFilter filter : client.filters) {
            if (filter.matches(scanResult)) {
                return true;
            }
        }
        return false;
    }

    void onClientRegistered(int status, int clientIf, long uuidLsb, long uuidMsb) throws RemoteException {
        UUID uuid = new UUID(uuidMsb, uuidLsb);
        ContextMap<IBluetoothGattCallback, Void>.App app = this.mClientMap.getByUuid(uuid);
        if (app != null) {
            if (status == 0) {
                app.id = clientIf;
                app.linkToDeath(new ClientDeathRecipient(clientIf));
            } else {
                this.mClientMap.remove(uuid);
            }
            app.callback.onClientRegistered(status, clientIf);
        }
    }

    void onConnected(int clientIf, int connId, int status, String address) throws RemoteException {
        if (status == 0) {
            this.mClientMap.addConnection(clientIf, connId, address);
        }
        ContextMap<IBluetoothGattCallback, Void>.App app = this.mClientMap.getById(clientIf);
        if (app != null) {
            app.callback.onClientConnectionState(status, clientIf, status == 0, address);
        }
    }

    void onDisconnected(int clientIf, int connId, int status, String address) throws RemoteException {
        this.mClientMap.removeConnection(clientIf, connId);
        ContextMap<IBluetoothGattCallback, Void>.App app = this.mClientMap.getById(clientIf);
        if (app != null) {
            app.callback.onClientConnectionState(status, clientIf, false, address);
        }
    }

    void onClientPhyUpdate(int connId, int txPhy, int rxPhy, int status) throws RemoteException {
        ContextMap<IBluetoothGattCallback, Void>.App app;
        String address = this.mClientMap.addressByConnId(connId);
        if (address == null || (app = this.mClientMap.getByConnId(connId)) == null) {
            return;
        }
        app.callback.onPhyUpdate(address, txPhy, rxPhy, status);
    }

    void onClientPhyRead(int clientIf, String address, int txPhy, int rxPhy, int status) throws RemoteException {
        Integer connId = this.mClientMap.connIdByAddress(clientIf, address);
        if (connId == null) {
            Log.d(TAG, "onClientPhyRead() - no connection to " + address);
            return;
        }
        ContextMap<IBluetoothGattCallback, Void>.App app = this.mClientMap.getByConnId(connId.intValue());
        if (app == null) {
            return;
        }
        app.callback.onPhyRead(address, txPhy, rxPhy, status);
    }

    void onClientConnUpdate(int connId, int interval, int latency, int timeout, int status) throws RemoteException {
        ContextMap<IBluetoothGattCallback, Void>.App app;
        String address = this.mClientMap.addressByConnId(connId);
        if (address == null || (app = this.mClientMap.getByConnId(connId)) == null) {
            return;
        }
        app.callback.onConnectionUpdated(address, interval, latency, timeout, status);
    }

    void onServerPhyUpdate(int connId, int txPhy, int rxPhy, int status) throws RemoteException {
        ContextMap<IBluetoothGattServerCallback, Void>.App app;
        String address = this.mServerMap.addressByConnId(connId);
        if (address == null || (app = this.mServerMap.getByConnId(connId)) == null) {
            return;
        }
        app.callback.onPhyUpdate(address, txPhy, rxPhy, status);
    }

    void onServerPhyRead(int serverIf, String address, int txPhy, int rxPhy, int status) throws RemoteException {
        Integer connId = this.mServerMap.connIdByAddress(serverIf, address);
        if (connId == null) {
            Log.d(TAG, "onServerPhyRead() - no connection to " + address);
            return;
        }
        ContextMap<IBluetoothGattServerCallback, Void>.App app = this.mServerMap.getByConnId(connId.intValue());
        if (app == null) {
            return;
        }
        app.callback.onPhyRead(address, txPhy, rxPhy, status);
    }

    void onServerConnUpdate(int connId, int interval, int latency, int timeout, int status) throws RemoteException {
        ContextMap<IBluetoothGattServerCallback, Void>.App app;
        String address = this.mServerMap.addressByConnId(connId);
        if (address == null || (app = this.mServerMap.getByConnId(connId)) == null) {
            return;
        }
        app.callback.onConnectionUpdated(address, interval, latency, timeout, status);
    }

    void onSearchCompleted(final int connId, int status) throws RemoteException {
        Thread t = new Thread(new Runnable() { // from class: com.android.bluetooth.gatt.GattService.1
            @Override // java.lang.Runnable
            public void run() {
                GattService.this.gattClientGetGattDbNative(connId);
            }
        });
        t.start();
    }

    GattDbElement getSampleGattDbElement() {
        return new GattDbElement();
    }

    void onGetGattDb(int connId, ArrayList<GattDbElement> db) throws RemoteException {
        Iterator<GattDbElement> it;
        BluetoothGattCharacteristic currChar;
        String address = this.mClientMap.addressByConnId(connId);
        ContextMap<IBluetoothGattCallback, Void>.App app = this.mClientMap.getByConnId(connId);
        if (app == null || app.callback == null) {
            Log.e(TAG, "app or callback is null");
            return;
        }
        List<BluetoothGattService> dbOut = new ArrayList<>();
        Set<Integer> restrictedIds = new HashSet<>();
        BluetoothGattService currSrvc = null;
        BluetoothGattCharacteristic currChar2 = null;
        boolean isRestrictedSrvc = false;
        boolean isHidSrvc = false;
        boolean isRestrictedChar = false;
        Iterator<GattDbElement> it2 = db.iterator();
        while (it2.hasNext()) {
            GattDbElement el = it2.next();
            int i = el.type;
            if (i == 0 || i == 1) {
                BluetoothGattCharacteristic currChar3 = currChar2;
                it = it2;
                BluetoothGattService currSrvc2 = new BluetoothGattService(el.uuid, el.id, el.type);
                dbOut.add(currSrvc2);
                boolean isRestrictedSrvc2 = isFidoSrvcUuid(el.uuid) || isAndroidTvRemoteSrvcUuid(el.uuid);
                boolean isHidSrvc2 = isHidSrvcUuid(el.uuid);
                if (isRestrictedSrvc2) {
                    restrictedIds.add(Integer.valueOf(el.id));
                }
                isRestrictedSrvc = isRestrictedSrvc2;
                isHidSrvc = isHidSrvc2;
                currChar2 = currChar3;
                currSrvc = currSrvc2;
            } else {
                if (i == 2) {
                    currChar = currChar2;
                    it = it2;
                    currSrvc.addIncludedService(new BluetoothGattService(el.uuid, el.startHandle, el.type));
                } else if (i == 3) {
                    it = it2;
                    BluetoothGattCharacteristic currChar4 = new BluetoothGattCharacteristic(el.uuid, el.id, el.properties, 0);
                    currSrvc.addCharacteristic(currChar4);
                    boolean isRestrictedChar2 = isRestrictedSrvc || (isHidSrvc && isHidCharUuid(el.uuid));
                    if (isRestrictedChar2) {
                        restrictedIds.add(Integer.valueOf(el.id));
                    }
                    isRestrictedChar = isRestrictedChar2;
                    currChar2 = currChar4;
                } else if (i == 4) {
                    it = it2;
                    currChar2.addDescriptor(new BluetoothGattDescriptor(el.uuid, el.id, 0));
                    if (!isRestrictedChar) {
                        currChar = currChar2;
                    } else {
                        restrictedIds.add(Integer.valueOf(el.id));
                        currChar = currChar2;
                    }
                } else {
                    Log.e(TAG, "got unknown element with type=" + el.type + " and UUID=" + el.uuid + " id: " + el.id);
                    currChar = currChar2;
                    it = it2;
                }
                currChar2 = currChar;
            }
            it2 = it;
        }
        if (!restrictedIds.isEmpty()) {
            this.mRestrictedHandles.put(Integer.valueOf(connId), restrictedIds);
        }
        app.callback.onSearchComplete(address, dbOut, 0);
    }

    void onRegisterForNotifications(int connId, int status, int registered, int handle) {
        this.mClientMap.addressByConnId(connId);
    }

    void onNotify(int connId, String address, int handle, boolean isNotify, byte[] data) throws RemoteException {
        ContextMap<IBluetoothGattCallback, Void>.App app = this.mClientMap.getByConnId(connId);
        if (app != null) {
            if (!permissionCheck(app, connId, handle)) {
                Log.w(TAG, "onNotify() - permission check failed!");
            } else {
                app.callback.onNotify(address, handle, data);
            }
        }
    }

    void onReadCharacteristic(int connId, int status, int handle, byte[] data) throws RemoteException {
        String address = this.mClientMap.addressByConnId(connId);
        ContextMap<IBluetoothGattCallback, Void>.App app = this.mClientMap.getByConnId(connId);
        if (app != null) {
            app.callback.onCharacteristicRead(address, status, handle, data);
        }
    }

    void onWriteCharacteristic(int connId, int status, int handle) throws RemoteException {
        String address = this.mClientMap.addressByConnId(connId);
        ContextMap<IBluetoothGattCallback, Void>.App app = this.mClientMap.getByConnId(connId);
        if (app == null) {
            return;
        }
        if (!app.isCongested.booleanValue()) {
            app.callback.onCharacteristicWrite(address, status, handle);
            return;
        }
        if (status == 143) {
            status = 0;
        }
        CallbackInfo callbackInfo = new CallbackInfo(address, status, handle);
        app.queueCallback(callbackInfo);
    }

    void onExecuteCompleted(int connId, int status) throws RemoteException {
        String address = this.mClientMap.addressByConnId(connId);
        ContextMap<IBluetoothGattCallback, Void>.App app = this.mClientMap.getByConnId(connId);
        if (app != null) {
            app.callback.onExecuteWrite(address, status);
        }
    }

    void onReadDescriptor(int connId, int status, int handle, byte[] data) throws RemoteException {
        String address = this.mClientMap.addressByConnId(connId);
        ContextMap<IBluetoothGattCallback, Void>.App app = this.mClientMap.getByConnId(connId);
        if (app != null) {
            app.callback.onDescriptorRead(address, status, handle, data);
        }
    }

    void onWriteDescriptor(int connId, int status, int handle) throws RemoteException {
        String address = this.mClientMap.addressByConnId(connId);
        ContextMap<IBluetoothGattCallback, Void>.App app = this.mClientMap.getByConnId(connId);
        if (app != null) {
            app.callback.onDescriptorWrite(address, status, handle);
        }
    }

    void onReadRemoteRssi(int clientIf, String address, int rssi, int status) throws RemoteException {
        ContextMap<IBluetoothGattCallback, Void>.App app = this.mClientMap.getById(clientIf);
        if (app != null) {
            app.callback.onReadRemoteRssi(address, rssi, status);
        }
    }

    void onScanFilterEnableDisabled(int action, int status, int clientIf) {
        this.mScanManager.callbackDone(clientIf, status);
    }

    void onScanFilterParamsConfigured(int action, int status, int clientIf, int availableSpace) {
        this.mScanManager.callbackDone(clientIf, status);
    }

    void onScanFilterConfig(int action, int status, int clientIf, int filterType, int availableSpace) {
        this.mScanManager.callbackDone(clientIf, status);
    }

    void onBatchScanStorageConfigured(int status, int clientIf) {
        this.mScanManager.callbackDone(clientIf, status);
    }

    void onBatchScanStartStopped(int startStopAction, int status, int clientIf) {
        this.mScanManager.callbackDone(clientIf, status);
    }

    ScanClient findBatchScanClientById(int scannerId) {
        for (ScanClient client : this.mScanManager.getBatchScanQueue()) {
            if (client.scannerId == scannerId) {
                return client;
            }
        }
        return null;
    }

    void onBatchScanReports(int status, int scannerId, int reportType, int numRecords, byte[] recordData) throws RemoteException {
        ScanClient client;
        ArrayList<ScanResult> permittedResults;
        this.mScanManager.callbackDone(scannerId, status);
        Set<ScanResult> results = parseBatchScanResults(numRecords, reportType, recordData);
        if (reportType == 1) {
            ContextMap<IScannerCallback, PendingIntentInfo>.App app = this.mScannerMap.getById(scannerId);
            if (app == null || (client = findBatchScanClientById(scannerId)) == null) {
                return;
            }
            if (hasScanResultPermission(client)) {
                permittedResults = new ArrayList<>(results);
            } else {
                permittedResults = new ArrayList<>();
                for (ScanResult scanResult : results) {
                    for (String associatedDevice : client.associatedDevices) {
                        if (associatedDevice.equalsIgnoreCase(scanResult.getDevice().getAddress())) {
                            permittedResults.add(scanResult);
                        }
                    }
                }
                if (permittedResults.isEmpty()) {
                    return;
                }
            }
            if (app.callback == null) {
                try {
                    sendResultsByPendingIntent(app.info, permittedResults, 1);
                    return;
                } catch (PendingIntent.CanceledException e) {
                    return;
                }
            } else {
                app.callback.onBatchScanResults(permittedResults);
                return;
            }
        }
        Iterator<ScanClient> it = this.mScanManager.getFullBatchScanQueue().iterator();
        while (it.hasNext()) {
            deliverBatchScan(it.next(), results);
        }
    }

    private void sendBatchScanResults(ContextMap<IScannerCallback, PendingIntentInfo>.App app, ScanClient client, ArrayList<ScanResult> results) {
        try {
            if (app.callback != null) {
                app.callback.onBatchScanResults(results);
            } else {
                sendResultsByPendingIntent(app.info, results, 1);
            }
        } catch (PendingIntent.CanceledException | RemoteException e) {
            Log.e(TAG, "Exception: " + e);
            this.mScannerMap.remove(client.scannerId);
            this.mScanManager.stopScan(client.scannerId);
        }
    }

    private void deliverBatchScan(ScanClient client, Set<ScanResult> allResults) throws RemoteException {
        ArrayList<ScanResult> permittedResults;
        ContextMap<IScannerCallback, PendingIntentInfo>.App app = this.mScannerMap.getById(client.scannerId);
        if (app == null) {
            return;
        }
        if (hasScanResultPermission(client)) {
            permittedResults = new ArrayList<>(allResults);
        } else {
            permittedResults = new ArrayList<>();
            for (ScanResult scanResult : allResults) {
                for (String associatedDevice : client.associatedDevices) {
                    if (associatedDevice.equalsIgnoreCase(scanResult.getDevice().getAddress())) {
                        permittedResults.add(scanResult);
                    }
                }
            }
            if (permittedResults.isEmpty()) {
                return;
            }
        }
        if (client.filters == null || client.filters.isEmpty()) {
            sendBatchScanResults(app, client, permittedResults);
        }
        ArrayList<ScanResult> results = new ArrayList<>();
        for (ScanResult scanResult2 : permittedResults) {
            if (matchesFilters(client, scanResult2)) {
                results.add(scanResult2);
            }
        }
        sendBatchScanResults(app, client, results);
    }

    private Set<ScanResult> parseBatchScanResults(int numRecords, int reportType, byte[] batchRecord) {
        if (numRecords == 0) {
            return Collections.emptySet();
        }
        if (reportType == 1) {
            return parseTruncatedResults(numRecords, batchRecord);
        }
        return parseFullResults(numRecords, batchRecord);
    }

    private Set<ScanResult> parseTruncatedResults(int numRecords, byte[] batchRecord) {
        Set<ScanResult> results = new HashSet<>(numRecords);
        long now = SystemClock.elapsedRealtimeNanos();
        for (int i = 0; i < numRecords; i++) {
            byte[] record = extractBytes(batchRecord, i * 11, 11);
            byte[] address = extractBytes(record, 0, 6);
            reverse(address);
            BluetoothDevice device = this.mAdapter.getRemoteDevice(address);
            int rssi = record[8];
            long timestampNanos = now - parseTimestampNanos(extractBytes(record, 9, 2));
            results.add(new ScanResult(device, ScanRecord.parseFromBytes(new byte[0]), rssi, timestampNanos));
        }
        return results;
    }

    long parseTimestampNanos(byte[] data) {
        long timestampUnit = NumberUtils.littleEndianByteArrayToInt(data);
        return TimeUnit.MILLISECONDS.toNanos(50 * timestampUnit);
    }

    private Set<ScanResult> parseFullResults(int numRecords, byte[] batchRecord) {
        Set<ScanResult> results = new HashSet<>(numRecords);
        int rssi = 0;
        long now = SystemClock.elapsedRealtimeNanos();
        while (rssi < batchRecord.length) {
            byte[] address = extractBytes(batchRecord, rssi, 6);
            reverse(address);
            BluetoothDevice device = this.mAdapter.getRemoteDevice(address);
            int position = rssi + 6 + 1 + 1;
            int position2 = position + 1;
            int rssi2 = batchRecord[position];
            long timestampNanos = now - parseTimestampNanos(extractBytes(batchRecord, position2, 2));
            int position3 = position2 + 2;
            int position4 = position3 + 1;
            int advertisePacketLen = batchRecord[position3];
            byte[] advertiseBytes = extractBytes(batchRecord, position4, advertisePacketLen);
            int position5 = position4 + advertisePacketLen;
            int position6 = position5 + 1;
            int scanResponsePacketLen = batchRecord[position5];
            byte[] scanResponseBytes = extractBytes(batchRecord, position6, scanResponsePacketLen);
            int position7 = position6 + scanResponsePacketLen;
            int position8 = advertisePacketLen + scanResponsePacketLen;
            byte[] scanRecord = new byte[position8];
            System.arraycopy(advertiseBytes, 0, scanRecord, 0, advertisePacketLen);
            System.arraycopy(scanResponseBytes, 0, scanRecord, advertisePacketLen, scanResponsePacketLen);
            results.add(new ScanResult(device, ScanRecord.parseFromBytes(scanRecord), rssi2, timestampNanos));
            rssi = position7;
        }
        return results;
    }

    private void reverse(byte[] address) {
        int len = address.length;
        for (int i = 0; i < len / 2; i++) {
            byte b = address[i];
            address[i] = address[(len - 1) - i];
            address[(len - 1) - i] = b;
        }
    }

    private static byte[] extractBytes(byte[] scanRecord, int start, int length) {
        byte[] bytes = new byte[length];
        System.arraycopy(scanRecord, start, bytes, 0, length);
        return bytes;
    }

    void onBatchScanThresholdCrossed(int clientIf) {
        flushPendingBatchResults(clientIf);
    }

    AdvtFilterOnFoundOnLostInfo createOnTrackAdvFoundLostObject(int clientIf, int advPktLen, byte[] advPkt, int scanRspLen, byte[] scanRsp, int filtIndex, int advState, int advInfoPresent, String address, int addrType, int txPower, int rssiValue, int timeStamp) {
        return new AdvtFilterOnFoundOnLostInfo(clientIf, advPktLen, advPkt, scanRspLen, scanRsp, filtIndex, advState, advInfoPresent, address, addrType, txPower, rssiValue, timeStamp);
    }

    void onTrackAdvFoundLost(AdvtFilterOnFoundOnLostInfo trackingInfo) throws RemoteException {
        ContextMap<IScannerCallback, PendingIntentInfo>.App app = this.mScannerMap.getById(trackingInfo.getClientIf());
        if (app == null || (app.callback == null && app.info == null)) {
            Log.e(TAG, "app or callback is null");
            return;
        }
        BluetoothDevice device = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(trackingInfo.getAddress());
        int advertiserState = trackingInfo.getAdvState();
        ScanResult result = new ScanResult(device, ScanRecord.parseFromBytes(trackingInfo.getResult()), trackingInfo.getRSSIValue(), SystemClock.elapsedRealtimeNanos());
        for (ScanClient client : this.mScanManager.getRegularScanQueue()) {
            if (client.scannerId == trackingInfo.getClientIf()) {
                ScanSettings settings = client.settings;
                if (advertiserState == 0 && (settings.getCallbackType() & 2) != 0) {
                    if (app.callback != null) {
                        app.callback.onFoundOrLost(true, result);
                    } else {
                        sendResultByPendingIntent(app.info, result, 2, client);
                    }
                } else if (advertiserState == 1 && (settings.getCallbackType() & 4) != 0) {
                    if (app.callback != null) {
                        app.callback.onFoundOrLost(false, result);
                    } else {
                        sendResultByPendingIntent(app.info, result, 4, client);
                    }
                }
            }
        }
    }

    void onScanParamSetupCompleted(int status, int scannerId) throws RemoteException {
        ContextMap<IScannerCallback, PendingIntentInfo>.App app = this.mScannerMap.getById(scannerId);
        if (app == null || app.callback == null) {
            Log.e(TAG, "Advertise app or callback is null");
        }
    }

    void onScanManagerErrorCallback(int scannerId, int errorCode) throws RemoteException {
        ContextMap<IScannerCallback, PendingIntentInfo>.App app = this.mScannerMap.getById(scannerId);
        if (app == null || (app.callback == null && app.info == null)) {
            Log.e(TAG, "App or callback is null");
            return;
        }
        if (app.callback != null) {
            app.callback.onScanManagerErrorCallback(errorCode);
            return;
        }
        try {
            sendErrorByPendingIntent(app.info, errorCode);
        } catch (PendingIntent.CanceledException e) {
            Log.e(TAG, "Error sending error code via PendingIntent:" + e);
        }
    }

    void onConfigureMTU(int connId, int status, int mtu) throws RemoteException {
        String address = this.mClientMap.addressByConnId(connId);
        ContextMap<IBluetoothGattCallback, Void>.App app = this.mClientMap.getByConnId(connId);
        if (app != null) {
            app.callback.onConfigureMTU(address, mtu, status);
        }
    }

    void onClientCongestion(int connId, boolean congested) throws RemoteException {
        CallbackInfo callbackInfo;
        ContextMap<IBluetoothGattCallback, Void>.App app = this.mClientMap.getByConnId(connId);
        if (app != null) {
            app.isCongested = Boolean.valueOf(congested);
            while (!app.isCongested.booleanValue() && (callbackInfo = app.popQueuedCallback()) != null) {
                app.callback.onCharacteristicWrite(callbackInfo.address, callbackInfo.status, callbackInfo.handle);
            }
        }
    }

    List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] states) {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        Map<BluetoothDevice, Integer> deviceStates = new HashMap<>();
        Set<BluetoothDevice> bondedDevices = this.mAdapter.getBondedDevices();
        for (BluetoothDevice device : bondedDevices) {
            if (getDeviceType(device) != 1) {
                deviceStates.put(device, 0);
            }
        }
        Set<String> connectedDevices = new HashSet<>();
        connectedDevices.addAll(this.mClientMap.getConnectedDevices());
        connectedDevices.addAll(this.mServerMap.getConnectedDevices());
        for (String address : connectedDevices) {
            BluetoothDevice device2 = this.mAdapter.getRemoteDevice(address);
            if (device2 != null) {
                deviceStates.put(device2, 2);
            }
        }
        List<BluetoothDevice> deviceList = new ArrayList<>();
        for (Map.Entry<BluetoothDevice, Integer> entry : deviceStates.entrySet()) {
            for (int state : states) {
                if (entry.getValue().intValue() == state) {
                    deviceList.add(entry.getKey());
                }
            }
        }
        return deviceList;
    }

    void registerScanner(IScannerCallback callback, WorkSource workSource) throws RemoteException {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        UUID uuid = UUID.randomUUID();
        if (workSource != null) {
            enforceImpersonatationPermission();
        }
        AppScanStats app = this.mScannerMap.getAppScanStatsByUid(Binder.getCallingUid());
        if (app != null && app.isScanningTooFrequently() && checkCallingOrSelfPermission("android.permission.BLUETOOTH_PRIVILEGED") != 0) {
            Log.e(TAG, "App '" + app.appName + "' is scanning too frequently");
            callback.onScannerRegistered(6, -1);
            return;
        }
        this.mScannerMap.add(uuid, workSource, callback, null, this);
        this.mScanManager.registerScanner(uuid);
    }

    void unregisterScanner(int scannerId) {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        this.mScannerMap.remove(scannerId);
        this.mScanManager.unregisterScanner(scannerId);
    }

    private List<String> getAssociatedDevices(String callingPackage, UserHandle userHandle) {
        if (this.mCompanionManager == null) {
            return new ArrayList();
        }
        long identity = Binder.clearCallingIdentity();
        try {
            try {
                return this.mCompanionManager.getAssociations(callingPackage, userHandle.getIdentifier());
            } catch (RemoteException re) {
                Log.e(TAG, "Cannot reach companion device service", re);
                Binder.restoreCallingIdentity(identity);
                return new ArrayList();
            } catch (SecurityException e) {
                Binder.restoreCallingIdentity(identity);
                return new ArrayList();
            } catch (Exception e2) {
                Log.e(TAG, "Cannot check device associations for " + callingPackage, e2);
                Binder.restoreCallingIdentity(identity);
                return new ArrayList();
            }
        } finally {
            Binder.restoreCallingIdentity(identity);
        }
    }

    void startScan(int scannerId, ScanSettings settings, List<ScanFilter> filters, List<List<ResultStorageDescriptor>> storages, String callingPackage, String callingFeatureId) {
        boolean isCallbackScan;
        UserHandle.of(UserHandle.getCallingUserId());
        enforceAdminPermission();
        if (needsPrivilegedPermissionForScan(settings)) {
            enforcePrivilegedPermission();
        }
        ScanClient scanClient = new ScanClient(scannerId, settings, filters, storages);
        scanClient.userHandle = UserHandle.of(UserHandle.getCallingUserId());
        this.mAppOps.checkPackage(Binder.getCallingUid(), callingPackage);
        scanClient.eligibleForSanitizedExposureNotification = callingPackage.equals(this.mExposureNotificationPackage);
        scanClient.isQApp = Utils.isQApp(this, callingPackage);
        if (scanClient.isQApp) {
            scanClient.hasLocationPermission = Utils.checkCallerHasFineLocation(this, this.mAppOps, callingPackage, callingFeatureId, scanClient.userHandle);
        } else {
            scanClient.hasLocationPermission = Utils.checkCallerHasCoarseOrFineLocation(this, this.mAppOps, callingPackage, callingFeatureId, scanClient.userHandle);
        }
        scanClient.hasNetworkSettingsPermission = Utils.checkCallerHasNetworkSettingsPermission(this);
        scanClient.hasNetworkSetupWizardPermission = Utils.checkCallerHasNetworkSetupWizardPermission(this);
        scanClient.hasScanWithoutLocationPermission = Utils.checkCallerHasScanWithoutLocationPermission(this);
        scanClient.associatedDevices = getAssociatedDevices(callingPackage, scanClient.userHandle);
        AppScanStats app = this.mScannerMap.getAppScanStatsById(scannerId);
        ContextMap<IScannerCallback, PendingIntentInfo>.App cbApp = this.mScannerMap.getById(scannerId);
        if (app != null) {
            scanClient.stats = app;
            boolean isFilteredScan = (filters == null || filters.isEmpty()) ? false : true;
            if (cbApp == null) {
                isCallbackScan = false;
            } else {
                boolean isCallbackScan2 = cbApp.callback != null;
                isCallbackScan = isCallbackScan2;
            }
            app.recordScanStart(settings, filters, isFilteredScan, isCallbackScan, scannerId);
        }
        this.mScanManager.startScan(scanClient);
    }

    void registerPiAndStartScan(PendingIntent pendingIntent, ScanSettings settings, List<ScanFilter> filters, String callingPackage, String callingFeatureId) {
        enforceAdminPermission();
        if (needsPrivilegedPermissionForScan(settings)) {
            enforcePrivilegedPermission();
        }
        UUID uuid = UUID.randomUUID();
        PendingIntentInfo piInfo = new PendingIntentInfo();
        piInfo.intent = pendingIntent;
        piInfo.settings = settings;
        piInfo.filters = filters;
        piInfo.callingPackage = callingPackage;
        if (this.mScannerMap.getByContextInfo(piInfo) != null) {
            Log.d(TAG, "Don't startScan(PI) since the same Pi scan already in mScannerMap.");
            return;
        }
        ContextMap<IScannerCallback, PendingIntentInfo>.App app = this.mScannerMap.add(uuid, null, null, piInfo, this);
        app.mUserHandle = UserHandle.of(UserHandle.getCallingUserId());
        this.mAppOps.checkPackage(Binder.getCallingUid(), callingPackage);
        app.mEligibleForSanitizedExposureNotification = callingPackage.equals(this.mExposureNotificationPackage);
        app.mIsQApp = Utils.isQApp(this, callingPackage);
        try {
            if (app.mIsQApp) {
                app.hasLocationPermission = Utils.checkCallerHasFineLocation(this, this.mAppOps, callingPackage, callingFeatureId, app.mUserHandle);
            } else {
                app.hasLocationPermission = Utils.checkCallerHasCoarseOrFineLocation(this, this.mAppOps, callingPackage, callingFeatureId, app.mUserHandle);
            }
        } catch (SecurityException e) {
            app.hasLocationPermission = false;
        }
        app.mHasNetworkSettingsPermission = Utils.checkCallerHasNetworkSettingsPermission(this);
        app.mHasNetworkSetupWizardPermission = Utils.checkCallerHasNetworkSetupWizardPermission(this);
        app.mHasScanWithoutLocationPermission = Utils.checkCallerHasScanWithoutLocationPermission(this);
        app.mAssociatedDevices = getAssociatedDevices(callingPackage, app.mUserHandle);
        this.mScanManager.registerScanner(uuid);
    }

    void continuePiStartScan(int scannerId, ContextMap<IScannerCallback, PendingIntentInfo>.App app) {
        PendingIntentInfo piInfo = app.info;
        ScanClient scanClient = new ScanClient(scannerId, piInfo.settings, piInfo.filters, null);
        scanClient.hasLocationPermission = app.hasLocationPermission;
        scanClient.userHandle = app.mUserHandle;
        scanClient.isQApp = app.mIsQApp;
        scanClient.eligibleForSanitizedExposureNotification = app.mEligibleForSanitizedExposureNotification;
        scanClient.hasNetworkSettingsPermission = app.mHasNetworkSettingsPermission;
        scanClient.hasNetworkSetupWizardPermission = app.mHasNetworkSetupWizardPermission;
        scanClient.hasScanWithoutLocationPermission = app.mHasScanWithoutLocationPermission;
        scanClient.associatedDevices = app.mAssociatedDevices;
        AppScanStats scanStats = this.mScannerMap.getAppScanStatsById(scannerId);
        if (scanStats != null) {
            scanClient.stats = scanStats;
            boolean isFilteredScan = (piInfo.filters == null || piInfo.filters.isEmpty()) ? false : true;
            scanStats.recordScanStart(piInfo.settings, piInfo.filters, isFilteredScan, false, scannerId);
        }
        this.mScanManager.startScan(scanClient);
    }

    void flushPendingBatchResults(int scannerId) {
        this.mScanManager.flushBatchScanResults(new ScanClient(scannerId));
    }

    void stopScan(int scannerId) {
        enforceAdminPermission();
        int size = this.mScanManager.getBatchScanQueue().size() + this.mScanManager.getRegularScanQueue().size();
        AppScanStats app = this.mScannerMap.getAppScanStatsById(scannerId);
        if (app != null) {
            app.recordScanStop(scannerId);
        }
        this.mScanManager.stopScan(scannerId);
    }

    void stopScan(PendingIntent intent, String callingPackage) {
        enforceAdminPermission();
        PendingIntentInfo pii = new PendingIntentInfo();
        pii.intent = intent;
        ContextMap<IScannerCallback, PendingIntentInfo>.App app = this.mScannerMap.getByContextInfo(pii);
        if (app != null) {
            int scannerId = app.id;
            stopScan(scannerId);
            unregisterScanner(scannerId);
        }
    }

    void disconnectAll() {
        Map<Integer, String> connMap = this.mClientMap.getConnectedMap();
        for (Map.Entry<Integer, String> entry : connMap.entrySet()) {
            clientDisconnect(entry.getKey().intValue(), entry.getValue());
        }
    }

    void unregAll() {
        for (Integer appId : this.mClientMap.getAllAppsIds()) {
            unregisterClient(appId.intValue());
        }
    }

    void registerSync(ScanResult scanResult, int skip, int timeout, IPeriodicAdvertisingCallback callback) {
        enforceAdminPermission();
        this.mPeriodicScanManager.startSync(scanResult, skip, timeout, callback);
    }

    void unregisterSync(IPeriodicAdvertisingCallback callback) {
        enforceAdminPermission();
        this.mPeriodicScanManager.stopSync(callback);
    }

    void startAdvertisingSet(AdvertisingSetParameters parameters, AdvertiseData advertiseData, AdvertiseData scanResponse, PeriodicAdvertisingParameters periodicParameters, AdvertiseData periodicData, int duration, int maxExtAdvEvents, IAdvertisingSetCallback callback) {
        enforceAdminPermission();
        this.mAdvertiseManager.startAdvertisingSet(parameters, advertiseData, scanResponse, periodicParameters, periodicData, duration, maxExtAdvEvents, callback);
    }

    void stopAdvertisingSet(IAdvertisingSetCallback callback) {
        enforceAdminPermission();
        this.mAdvertiseManager.stopAdvertisingSet(callback);
    }

    void getOwnAddress(int advertiserId) {
        enforcePrivilegedPermission();
        this.mAdvertiseManager.getOwnAddress(advertiserId);
    }

    void enableAdvertisingSet(int advertiserId, boolean enable, int duration, int maxExtAdvEvents) {
        enforceAdminPermission();
        this.mAdvertiseManager.enableAdvertisingSet(advertiserId, enable, duration, maxExtAdvEvents);
    }

    void setAdvertisingData(int advertiserId, AdvertiseData data) {
        enforceAdminPermission();
        this.mAdvertiseManager.setAdvertisingData(advertiserId, data);
    }

    void setScanResponseData(int advertiserId, AdvertiseData data) {
        enforceAdminPermission();
        this.mAdvertiseManager.setScanResponseData(advertiserId, data);
    }

    void setAdvertisingParameters(int advertiserId, AdvertisingSetParameters parameters) {
        enforceAdminPermission();
        this.mAdvertiseManager.setAdvertisingParameters(advertiserId, parameters);
    }

    void setPeriodicAdvertisingParameters(int advertiserId, PeriodicAdvertisingParameters parameters) {
        enforceAdminPermission();
        this.mAdvertiseManager.setPeriodicAdvertisingParameters(advertiserId, parameters);
    }

    void setPeriodicAdvertisingData(int advertiserId, AdvertiseData data) {
        enforceAdminPermission();
        this.mAdvertiseManager.setPeriodicAdvertisingData(advertiserId, data);
    }

    void setPeriodicAdvertisingEnable(int advertiserId, boolean enable) {
        enforceAdminPermission();
        this.mAdvertiseManager.setPeriodicAdvertisingEnable(advertiserId, enable);
    }

    void registerClient(UUID uuid, IBluetoothGattCallback callback) {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        this.mClientMap.add(uuid, null, callback, null, this);
        gattClientRegisterAppNative(uuid.getLeastSignificantBits(), uuid.getMostSignificantBits());
    }

    void unregisterClient(int clientIf) {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        this.mClientMap.remove(clientIf);
        gattClientUnregisterAppNative(clientIf);
    }

    void clientConnect(int clientIf, String address, boolean isDirect, int transport, boolean opportunistic, int phy) {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        gattClientConnectNative(clientIf, address, isDirect, transport, opportunistic, phy);
    }

    void clientDisconnect(int clientIf, String address) {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        Integer connId = this.mClientMap.connIdByAddress(clientIf, address);
        gattClientDisconnectNative(clientIf, address, connId != null ? connId.intValue() : 0);
    }

    void clientSetPreferredPhy(int clientIf, String address, int txPhy, int rxPhy, int phyOptions) {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        Integer connId = this.mClientMap.connIdByAddress(clientIf, address);
        if (connId == null) {
            return;
        }
        gattClientSetPreferredPhyNative(clientIf, address, txPhy, rxPhy, phyOptions);
    }

    void clientReadPhy(int clientIf, String address) {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        Integer connId = this.mClientMap.connIdByAddress(clientIf, address);
        if (connId == null) {
            return;
        }
        gattClientReadPhyNative(clientIf, address);
    }

    int numHwTrackFiltersAvailable() {
        return AdapterService.getAdapterService().getTotalNumOfTrackableAdvertisements() - this.mScanManager.getCurrentUsedTrackingAdvertisement();
    }

    synchronized List<ParcelUuid> getRegisteredServiceUuids() {
        List<ParcelUuid> serviceUuids;
        Utils.enforceAdminPermission(this);
        serviceUuids = new ArrayList<>();
        for (HandleMap.Entry entry : this.mHandleMap.mEntries) {
            serviceUuids.add(new ParcelUuid(entry.uuid));
        }
        return serviceUuids;
    }

    List<String> getConnectedDevices() {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        Set<String> connectedDevAddress = new HashSet<>();
        connectedDevAddress.addAll(this.mClientMap.getConnectedDevices());
        connectedDevAddress.addAll(this.mServerMap.getConnectedDevices());
        List<String> connectedDeviceList = new ArrayList<>(connectedDevAddress);
        return connectedDeviceList;
    }

    void refreshDevice(int clientIf, String address) {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        gattClientRefreshNative(clientIf, address);
    }

    void discoverServices(int clientIf, String address) {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        Integer connId = this.mClientMap.connIdByAddress(clientIf, address);
        if (connId != null) {
            gattClientSearchServiceNative(connId.intValue(), true, 0L, 0L);
            return;
        }
        Log.e(TAG, "discoverServices() - No connection for " + address + "...");
    }

    void discoverServiceByUuid(int clientIf, String address, UUID uuid) {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        Integer connId = this.mClientMap.connIdByAddress(clientIf, address);
        if (connId != null) {
            gattClientDiscoverServiceByUuidNative(connId.intValue(), uuid.getLeastSignificantBits(), uuid.getMostSignificantBits());
            return;
        }
        Log.e(TAG, "discoverServiceByUuid() - No connection for " + address + "...");
    }

    void readCharacteristic(int clientIf, String address, int handle, int authReq) {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        Integer connId = this.mClientMap.connIdByAddress(clientIf, address);
        if (connId == null) {
            Log.e(TAG, "readCharacteristic() - No connection for " + address + "...");
            return;
        }
        if (!permissionCheck(connId.intValue(), handle)) {
            Log.w(TAG, "readCharacteristic() - permission check failed!");
        } else {
            gattClientReadCharacteristicNative(connId.intValue(), handle, authReq);
        }
    }

    void readUsingCharacteristicUuid(int clientIf, String address, UUID uuid, int startHandle, int endHandle, int authReq) {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        Integer connId = this.mClientMap.connIdByAddress(clientIf, address);
        if (connId == null) {
            Log.e(TAG, "readUsingCharacteristicUuid() - No connection for " + address + "...");
            return;
        }
        if (!permissionCheck(uuid)) {
            Log.w(TAG, "readUsingCharacteristicUuid() - permission check failed!");
        } else {
            gattClientReadUsingCharacteristicUuidNative(connId.intValue(), uuid.getLeastSignificantBits(), uuid.getMostSignificantBits(), startHandle, endHandle, authReq);
        }
    }

    void writeCharacteristic(int clientIf, String address, int handle, int writeType, int authReq, byte[] value) {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        if (this.mReliableQueue.contains(address)) {
            writeType = 3;
        }
        Integer connId = this.mClientMap.connIdByAddress(clientIf, address);
        if (connId == null) {
            Log.e(TAG, "writeCharacteristic() - No connection for " + address + "...");
            return;
        }
        if (!permissionCheck(connId.intValue(), handle)) {
            Log.w(TAG, "writeCharacteristic() - permission check failed!");
        } else {
            gattClientWriteCharacteristicNative(connId.intValue(), handle, writeType, authReq, value);
        }
    }

    void readDescriptor(int clientIf, String address, int handle, int authReq) {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        Integer connId = this.mClientMap.connIdByAddress(clientIf, address);
        if (connId == null) {
            Log.e(TAG, "readDescriptor() - No connection for " + address + "...");
            return;
        }
        if (!permissionCheck(connId.intValue(), handle)) {
            Log.w(TAG, "readDescriptor() - permission check failed!");
        } else {
            gattClientReadDescriptorNative(connId.intValue(), handle, authReq);
        }
    }

    void writeDescriptor(int clientIf, String address, int handle, int authReq, byte[] value) {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        Integer connId = this.mClientMap.connIdByAddress(clientIf, address);
        if (connId == null) {
            Log.e(TAG, "writeDescriptor() - No connection for " + address + "...");
            return;
        }
        if (!permissionCheck(connId.intValue(), handle)) {
            Log.w(TAG, "writeDescriptor() - permission check failed!");
        } else {
            gattClientWriteDescriptorNative(connId.intValue(), handle, authReq, value);
        }
    }

    void beginReliableWrite(int clientIf, String address) {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        this.mReliableQueue.add(address);
    }

    void endReliableWrite(int clientIf, String address, boolean execute) {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        this.mReliableQueue.remove(address);
        Integer connId = this.mClientMap.connIdByAddress(clientIf, address);
        if (connId != null) {
            gattClientExecuteWriteNative(connId.intValue(), execute);
        }
    }

    void registerForNotification(int clientIf, String address, int handle, boolean enable) {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        Integer connId = this.mClientMap.connIdByAddress(clientIf, address);
        if (connId == null) {
            Log.e(TAG, "registerForNotification() - No connection for " + address + "...");
            return;
        }
        if (!permissionCheck(connId.intValue(), handle)) {
            Log.w(TAG, "registerForNotification() - permission check failed!");
        } else {
            gattClientRegisterForNotificationsNative(clientIf, address, handle, enable);
        }
    }

    void readRemoteRssi(int clientIf, String address) {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        gattClientReadRemoteRssiNative(clientIf, address);
    }

    void configureMTU(int clientIf, String address, int mtu) {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        Integer connId = this.mClientMap.connIdByAddress(clientIf, address);
        if (connId != null) {
            gattClientConfigureMTUNative(connId.intValue(), mtu);
            return;
        }
        Log.e(TAG, "configureMTU() - No connection for " + address + "...");
    }

    void connectionParameterUpdate(int clientIf, String address, int connectionPriority) {
        int minInterval;
        int maxInterval;
        int latency;
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        if (connectionPriority != 1) {
            if (connectionPriority == 2) {
                minInterval = getResources().getInteger(R.integer.gatt_low_power_min_interval);
                int maxInterval2 = getResources().getInteger(R.integer.gatt_low_power_max_interval);
                int latency2 = getResources().getInteger(R.integer.gatt_low_power_latency);
                maxInterval = maxInterval2;
                latency = latency2;
            } else {
                minInterval = getResources().getInteger(R.integer.gatt_balanced_priority_min_interval);
                int maxInterval3 = getResources().getInteger(R.integer.gatt_balanced_priority_max_interval);
                maxInterval = maxInterval3;
                latency = getResources().getInteger(R.integer.gatt_balanced_priority_latency);
            }
        } else {
            minInterval = getResources().getInteger(R.integer.gatt_high_priority_min_interval);
            int maxInterval4 = getResources().getInteger(R.integer.gatt_high_priority_max_interval);
            int latency3 = getResources().getInteger(R.integer.gatt_high_priority_latency);
            maxInterval = maxInterval4;
            latency = latency3;
        }
        gattConnectionParameterUpdateNative(clientIf, address, minInterval, maxInterval, latency, 500, 0, 0);
    }

    void leConnectionUpdate(int clientIf, String address, int minInterval, int maxInterval, int slaveLatency, int supervisionTimeout, int minConnectionEventLen, int maxConnectionEventLen) {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        gattConnectionParameterUpdateNative(clientIf, address, minInterval, maxInterval, slaveLatency, supervisionTimeout, minConnectionEventLen, maxConnectionEventLen);
    }

    void onServerRegistered(int status, int serverIf, long uuidLsb, long uuidMsb) throws RemoteException {
        UUID uuid = new UUID(uuidMsb, uuidLsb);
        ContextMap<IBluetoothGattServerCallback, Void>.App app = this.mServerMap.getByUuid(uuid);
        if (app != null) {
            app.id = serverIf;
            app.linkToDeath(new ServerDeathRecipient(serverIf));
            app.callback.onServerRegistered(status, serverIf);
        }
    }

    void onServiceAdded(int status, int serverIf, List<GattDbElement> service) throws RemoteException {
        if (status != 0) {
            return;
        }
        int i = 0;
        GattDbElement svcEl = service.get(0);
        int srvcHandle = svcEl.attributeHandle;
        BluetoothGattService svc = null;
        for (GattDbElement el : service) {
            if (el.type == 0) {
                this.mHandleMap.addService(serverIf, el.attributeHandle, el.uuid, 0, 0, false);
                svc = new BluetoothGattService(svcEl.uuid, svcEl.attributeHandle, i);
            } else if (el.type == 1) {
                this.mHandleMap.addService(serverIf, el.attributeHandle, el.uuid, 1, 0, false);
                svc = new BluetoothGattService(svcEl.uuid, svcEl.attributeHandle, 1);
            } else if (el.type == 3) {
                this.mHandleMap.addCharacteristic(serverIf, el.attributeHandle, el.uuid, srvcHandle);
                svc.addCharacteristic(new BluetoothGattCharacteristic(el.uuid, el.attributeHandle, el.properties, el.permissions));
            } else if (el.type == 4) {
                this.mHandleMap.addDescriptor(serverIf, el.attributeHandle, el.uuid, srvcHandle);
                List<BluetoothGattCharacteristic> chars = svc.getCharacteristics();
                chars.get(chars.size() - 1).addDescriptor(new BluetoothGattDescriptor(el.uuid, el.attributeHandle, el.permissions));
            }
            i = 0;
        }
        this.mHandleMap.setStarted(serverIf, srvcHandle, true);
        ContextMap<IBluetoothGattServerCallback, Void>.App app = this.mServerMap.getById(serverIf);
        if (app != null) {
            app.callback.onServiceAdded(status, svc);
        }
    }

    void onServiceStopped(int status, int serverIf, int srvcHandle) throws RemoteException {
        if (status == 0) {
            this.mHandleMap.setStarted(serverIf, srvcHandle, false);
        }
        stopNextService(serverIf, status);
    }

    void onServiceDeleted(int status, int serverIf, int srvcHandle) {
        this.mHandleMap.deleteService(serverIf, srvcHandle);
    }

    void onClientConnected(String address, boolean connected, int connId, int serverIf) throws RemoteException {
        ContextMap<IBluetoothGattServerCallback, Void>.App app = this.mServerMap.getById(serverIf);
        if (app == null) {
            return;
        }
        if (connected) {
            this.mServerMap.addConnection(serverIf, connId, address);
        } else {
            this.mServerMap.removeConnection(serverIf, connId);
        }
        app.callback.onServerConnectionState(0, serverIf, connected, address);
    }

    void onServerReadCharacteristic(String address, int connId, int transId, int handle, int offset, boolean isLong) throws RemoteException {
        HandleMap.Entry entry = this.mHandleMap.getByHandle(handle);
        if (entry == null) {
            return;
        }
        this.mHandleMap.addRequest(transId, handle);
        ContextMap<IBluetoothGattServerCallback, Void>.App app = this.mServerMap.getById(entry.serverIf);
        if (app == null) {
            return;
        }
        app.callback.onCharacteristicReadRequest(address, transId, offset, isLong, handle);
    }

    void onServerReadDescriptor(String address, int connId, int transId, int handle, int offset, boolean isLong) throws RemoteException {
        HandleMap.Entry entry = this.mHandleMap.getByHandle(handle);
        if (entry == null) {
            return;
        }
        this.mHandleMap.addRequest(transId, handle);
        ContextMap<IBluetoothGattServerCallback, Void>.App app = this.mServerMap.getById(entry.serverIf);
        if (app == null) {
            return;
        }
        app.callback.onDescriptorReadRequest(address, transId, offset, isLong, handle);
    }

    void onServerWriteCharacteristic(String address, int connId, int transId, int handle, int offset, int length, boolean needRsp, boolean isPrep, byte[] data) throws RemoteException {
        HandleMap.Entry entry = this.mHandleMap.getByHandle(handle);
        if (entry == null) {
            return;
        }
        this.mHandleMap.addRequest(transId, handle);
        ContextMap<IBluetoothGattServerCallback, Void>.App app = this.mServerMap.getById(entry.serverIf);
        if (app == null) {
            return;
        }
        app.callback.onCharacteristicWriteRequest(address, transId, offset, length, isPrep, needRsp, handle, data);
    }

    void onServerWriteDescriptor(String address, int connId, int transId, int handle, int offset, int length, boolean needRsp, boolean isPrep, byte[] data) throws RemoteException {
        HandleMap.Entry entry = this.mHandleMap.getByHandle(handle);
        if (entry == null) {
            return;
        }
        this.mHandleMap.addRequest(transId, handle);
        ContextMap<IBluetoothGattServerCallback, Void>.App app = this.mServerMap.getById(entry.serverIf);
        if (app == null) {
            return;
        }
        app.callback.onDescriptorWriteRequest(address, transId, offset, length, isPrep, needRsp, handle, data);
    }

    void onExecuteWrite(String address, int connId, int transId, int execWrite) throws RemoteException {
        ContextMap<IBluetoothGattServerCallback, Void>.App app = this.mServerMap.getByConnId(connId);
        if (app == null) {
            return;
        }
        app.callback.onExecuteWrite(address, transId, execWrite == 1);
    }

    void onResponseSendCompleted(int status, int attrHandle) {
    }

    void onNotificationSent(int connId, int status) throws RemoteException {
        ContextMap<IBluetoothGattServerCallback, Void>.App app;
        String address = this.mServerMap.addressByConnId(connId);
        if (address == null || (app = this.mServerMap.getByConnId(connId)) == null) {
            return;
        }
        if (!app.isCongested.booleanValue()) {
            app.callback.onNotificationSent(address, status);
            return;
        }
        if (status == 143) {
            status = 0;
        }
        app.queueCallback(new CallbackInfo(address, status));
    }

    void onServerCongestion(int connId, boolean congested) throws RemoteException {
        CallbackInfo callbackInfo;
        ContextMap<IBluetoothGattServerCallback, Void>.App app = this.mServerMap.getByConnId(connId);
        if (app == null) {
            return;
        }
        app.isCongested = Boolean.valueOf(congested);
        while (!app.isCongested.booleanValue() && (callbackInfo = app.popQueuedCallback()) != null) {
            app.callback.onNotificationSent(callbackInfo.address, callbackInfo.status);
        }
    }

    void onMtuChanged(int connId, int mtu) throws RemoteException {
        ContextMap<IBluetoothGattServerCallback, Void>.App app;
        String address = this.mServerMap.addressByConnId(connId);
        if (address == null || (app = this.mServerMap.getByConnId(connId)) == null) {
            return;
        }
        app.callback.onMtuChanged(address, mtu);
    }

    void registerServer(UUID uuid, IBluetoothGattServerCallback callback) {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        this.mServerMap.add(uuid, null, callback, null, this);
        gattServerRegisterAppNative(uuid.getLeastSignificantBits(), uuid.getMostSignificantBits());
    }

    void unregisterServer(int serverIf) {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        deleteServices(serverIf);
        this.mServerMap.remove(serverIf);
        gattServerUnregisterAppNative(serverIf);
    }

    void serverConnect(int serverIf, String address, boolean isDirect, int transport) {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        gattServerConnectNative(serverIf, address, isDirect, transport);
    }

    void serverDisconnect(int serverIf, String address) {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        Integer connId = this.mServerMap.connIdByAddress(serverIf, address);
        gattServerDisconnectNative(serverIf, address, connId != null ? connId.intValue() : 0);
    }

    void serverSetPreferredPhy(int serverIf, String address, int txPhy, int rxPhy, int phyOptions) {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        Integer connId = this.mServerMap.connIdByAddress(serverIf, address);
        if (connId == null) {
            return;
        }
        gattServerSetPreferredPhyNative(serverIf, address, txPhy, rxPhy, phyOptions);
    }

    void serverReadPhy(int serverIf, String address) {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        Integer connId = this.mServerMap.connIdByAddress(serverIf, address);
        if (connId == null) {
            return;
        }
        gattServerReadPhyNative(serverIf, address);
    }

    void addService(int serverIf, BluetoothGattService service) {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        List<GattDbElement> db = new ArrayList<>();
        if (service.getType() == 0) {
            db.add(GattDbElement.createPrimaryService(service.getUuid()));
        } else {
            db.add(GattDbElement.createSecondaryService(service.getUuid()));
        }
        for (BluetoothGattService includedService : service.getIncludedServices()) {
            int inclSrvcHandle = includedService.getInstanceId();
            if (this.mHandleMap.checkServiceExists(includedService.getUuid(), inclSrvcHandle)) {
                db.add(GattDbElement.createIncludedService(inclSrvcHandle));
            } else {
                Log.e(TAG, "included service with UUID " + includedService.getUuid() + " not found!");
            }
        }
        for (BluetoothGattCharacteristic characteristic : service.getCharacteristics()) {
            int permission = ((characteristic.getKeySize() - 7) << 12) + characteristic.getPermissions();
            db.add(GattDbElement.createCharacteristic(characteristic.getUuid(), characteristic.getProperties(), permission));
            for (BluetoothGattDescriptor descriptor : characteristic.getDescriptors()) {
                int permission2 = ((characteristic.getKeySize() - 7) << 12) + descriptor.getPermissions();
                db.add(GattDbElement.createDescriptor(descriptor.getUuid(), permission2));
            }
        }
        gattServerAddServiceNative(serverIf, db);
    }

    void removeService(int serverIf, int handle) {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        gattServerDeleteServiceNative(serverIf, handle);
    }

    void clearServices(int serverIf) {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        deleteServices(serverIf);
    }

    void sendResponse(int serverIf, String address, int requestId, int status, int offset, byte[] value) {
        int handle;
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        HandleMap.Entry entry = this.mHandleMap.getByRequestId(requestId);
        if (entry == null) {
            handle = 0;
        } else {
            int handle2 = entry.handle;
            handle = handle2;
        }
        Integer connId = this.mServerMap.connIdByAddress(serverIf, address);
        gattServerSendResponseNative(serverIf, connId != null ? connId.intValue() : 0, requestId, (byte) status, handle, offset, value, 0);
        this.mHandleMap.deleteRequest(requestId);
    }

    void sendNotification(int serverIf, String address, int handle, boolean confirm, byte[] value) {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        Integer connId = this.mServerMap.connIdByAddress(serverIf, address);
        if (connId == null || connId.intValue() == 0) {
            return;
        }
        if (confirm) {
            gattServerSendIndicationNative(serverIf, handle, connId.intValue(), value);
        } else {
            gattServerSendNotificationNative(serverIf, handle, connId.intValue(), value);
        }
    }

    private boolean isHidSrvcUuid(UUID uuid) {
        return HID_SERVICE_UUID.equals(uuid);
    }

    private boolean isHidCharUuid(UUID uuid) {
        for (UUID hidUuid : HID_UUIDS) {
            if (hidUuid.equals(uuid)) {
                return true;
            }
        }
        return false;
    }

    private boolean isAndroidTvRemoteSrvcUuid(UUID uuid) {
        return ANDROID_TV_REMOTE_SERVICE_UUID.equals(uuid);
    }

    private boolean isFidoSrvcUuid(UUID uuid) {
        return FIDO_SERVICE_UUID.equals(uuid);
    }

    private int getDeviceType(BluetoothDevice device) {
        int type = gattClientGetDeviceTypeNative(device.getAddress());
        return type;
    }

    private void enforceAdminPermission() {
        enforceCallingOrSelfPermission("android.permission.BLUETOOTH_ADMIN", "Need BLUETOOTH_ADMIN permission");
    }

    private boolean needsPrivilegedPermissionForScan(ScanSettings settings) {
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        if (adapter.getState() != 12) {
            return true;
        }
        return (settings == null || settings.getReportDelayMillis() == 0 || settings.getScanResultType() != 1) ? false : true;
    }

    private void enforcePrivilegedPermission() {
        enforceCallingOrSelfPermission("android.permission.BLUETOOTH_PRIVILEGED", "Need BLUETOOTH_PRIVILEGED permission");
    }

    private void enforceImpersonatationPermission() {
        enforceCallingOrSelfPermission("android.permission.UPDATE_DEVICE_STATS", "Need UPDATE_DEVICE_STATS permission");
    }

    private void stopNextService(int serverIf, int status) throws RemoteException {
        if (status == 0) {
            List<HandleMap.Entry> entries = this.mHandleMap.getEntries();
            for (HandleMap.Entry entry : entries) {
                if (entry.type == 1 && entry.serverIf == serverIf && entry.started) {
                    gattServerStopServiceNative(serverIf, entry.handle);
                    return;
                }
            }
        }
    }

    private void deleteServices(int serverIf) {
        List<Integer> handleList = new ArrayList<>();
        List<HandleMap.Entry> entries = this.mHandleMap.getEntries();
        for (HandleMap.Entry entry : entries) {
            if (entry.type == 1 && entry.serverIf == serverIf) {
                handleList.add(Integer.valueOf(entry.handle));
            }
        }
        for (Integer handle : handleList) {
            gattServerDeleteServiceNative(serverIf, handle.intValue());
        }
    }

    private List<UUID> parseUuids(byte[] advData) {
        List<UUID> uuids = new ArrayList<>();
        int len = 0;
        while (len < advData.length - 2) {
            int offset = len + 1;
            int len2 = Byte.toUnsignedInt(advData[len]);
            if (len2 == 0) {
                break;
            }
            int uuid16 = offset + 1;
            int type = advData[offset];
            if (type == 2 || type == 3) {
                while (len2 > 1) {
                    int offset2 = uuid16 + 1;
                    len2 -= 2;
                    uuids.add(UUID.fromString(String.format("%08x-0000-1000-8000-00805f9b34fb", Integer.valueOf(advData[uuid16] + (advData[offset2] << 8)))));
                    uuid16 = offset2 + 1;
                }
                len = uuid16;
            } else {
                len = uuid16 + (len2 - 1);
            }
        }
        return uuids;
    }

    void dumpRegisterId(StringBuilder sb) {
        sb.append("  Scanner:\n");
        for (Integer appId : this.mScannerMap.getAllAppsIds()) {
            println(sb, "    app_if: " + appId + ", appName: " + this.mScannerMap.getById(appId.intValue()).name);
        }
        sb.append("  Client:\n");
        for (Integer appId2 : this.mClientMap.getAllAppsIds()) {
            println(sb, "    app_if: " + appId2 + ", appName: " + this.mClientMap.getById(appId2.intValue()).name);
        }
        sb.append("  Server:\n");
        for (Integer appId3 : this.mServerMap.getAllAppsIds()) {
            println(sb, "    app_if: " + appId3 + ", appName: " + this.mServerMap.getById(appId3.intValue()).name);
        }
        sb.append("\n\n");
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    public void dump(StringBuilder sb) {
        super.dump(sb);
        println(sb, "mAdvertisingServiceUuids:");
        for (UUID uuid : this.mAdvertisingServiceUuids) {
            println(sb, "  " + uuid);
        }
        println(sb, "mMaxScanFilters: " + this.mMaxScanFilters);
        sb.append("\nRegistered App\n");
        dumpRegisterId(sb);
        sb.append("GATT Scanner Map\n");
        this.mScannerMap.dump(sb);
        sb.append("GATT Client Map\n");
        this.mClientMap.dump(sb);
        sb.append("GATT Server Map\n");
        this.mServerMap.dump(sb);
        sb.append("GATT Handle Map\n");
        this.mHandleMap.dump(sb);
    }

    void addScanEvent(BluetoothMetricsProto.ScanEvent event) {
        synchronized (this.mScanEvents) {
            if (this.mScanEvents.size() == 20) {
                this.mScanEvents.remove();
            }
            this.mScanEvents.add(event);
        }
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    public void dumpProto(BluetoothMetricsProto.BluetoothLog.Builder builder) {
        synchronized (this.mScanEvents) {
            builder.addAllScanEvent(this.mScanEvents);
        }
    }

    void gattTestCommand(int command, UUID uuid1, String bda1, int p1, int p2, int p3, int p4, int p5) {
        String bda12;
        if (bda1 != null) {
            bda12 = bda1;
        } else {
            bda12 = "00:00:00:00:00:00";
        }
        if (uuid1 != null) {
            gattTestNative(command, uuid1.getLeastSignificantBits(), uuid1.getMostSignificantBits(), bda12, p1, p2, p3, p4, p5);
        } else {
            gattTestNative(command, 0L, 0L, bda12, p1, p2, p3, p4, p5);
        }
    }
}
