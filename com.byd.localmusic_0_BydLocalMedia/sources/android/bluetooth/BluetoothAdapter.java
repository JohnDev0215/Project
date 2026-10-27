package android.bluetooth;

import android.annotation.SystemApi;
import android.app.ActivityThread;
import android.app.PropertyInvalidatedCache;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.IBluetoothManager;
import android.bluetooth.IBluetoothManagerCallback;
import android.bluetooth.IBluetoothMetadataListener;
import android.bluetooth.IBluetoothStateChangeCallback;
import android.bluetooth.le.BluetoothLeAdvertiser;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.PeriodicAdvertisingManager;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanRecord;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.os.Binder;
import android.os.IBinder;
import android.os.ParcelUuid;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.os.ServiceManager;
import android.os.SynchronousResultReceiver;
import android.os.SystemProperties;
import android.util.Log;
import android.util.Pair;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

/* JADX INFO: loaded from: classes.dex */
public final class BluetoothAdapter {
    public static final String ACTION_BLE_ACL_CONNECTED = "android.bluetooth.adapter.action.BLE_ACL_CONNECTED";
    public static final String ACTION_BLE_ACL_DISCONNECTED = "android.bluetooth.adapter.action.BLE_ACL_DISCONNECTED";

    @SystemApi
    public static final String ACTION_BLE_STATE_CHANGED = "android.bluetooth.adapter.action.BLE_STATE_CHANGED";
    public static final String ACTION_BLUETOOTH_ADDRESS_CHANGED = "android.bluetooth.adapter.action.BLUETOOTH_ADDRESS_CHANGED";
    public static final String ACTION_CONNECTION_STATE_CHANGED = "android.bluetooth.adapter.action.CONNECTION_STATE_CHANGED";
    public static final String ACTION_DISCOVERY_FINISHED = "android.bluetooth.adapter.action.DISCOVERY_FINISHED";
    public static final String ACTION_DISCOVERY_STARTED = "android.bluetooth.adapter.action.DISCOVERY_STARTED";
    public static final String ACTION_LOCAL_NAME_CHANGED = "android.bluetooth.adapter.action.LOCAL_NAME_CHANGED";
    public static final String ACTION_LOCAL_OOB_DATA = "android.bluetooth.adapter.action.LOCAL_OOB_DATA";

    @SystemApi
    public static final String ACTION_REQUEST_BLE_SCAN_ALWAYS_AVAILABLE = "android.bluetooth.adapter.action.REQUEST_BLE_SCAN_ALWAYS_AVAILABLE";
    public static final String ACTION_REQUEST_DISABLE = "android.bluetooth.adapter.action.REQUEST_DISABLE";
    public static final String ACTION_REQUEST_DISCOVERABLE = "android.bluetooth.adapter.action.REQUEST_DISCOVERABLE";
    public static final String ACTION_REQUEST_ENABLE = "android.bluetooth.adapter.action.REQUEST_ENABLE";
    public static final String ACTION_SCAN_MODE_CHANGED = "android.bluetooth.adapter.action.SCAN_MODE_CHANGED";
    public static final String ACTION_STATE_CHANGED = "android.bluetooth.adapter.action.STATE_CHANGED";

    @SystemApi
    public static final int ACTIVE_DEVICE_ALL = 2;

    @SystemApi
    public static final int ACTIVE_DEVICE_AUDIO = 0;

    @SystemApi
    public static final int ACTIVE_DEVICE_PHONE_CALL = 1;
    private static final int ADDRESS_LENGTH = 17;
    private static final String BLUETOOTH_FILTERING_CACHE_PROPERTY = "cache_key.bluetooth.is_offloaded_filtering_supported";
    private static final String BLUETOOTH_GET_STATE_CACHE_PROPERTY = "cache_key.bluetooth.get_state";
    public static final String BLUETOOTH_MANAGER_SERVICE = "bluetooth_manager";
    private static final String BLUETOOTH_PROFILE_CACHE_PROPERTY = "cache_key.bluetooth.get_profile_connection_state";
    private static final boolean DBG = true;
    public static final String DEFAULT_MAC_ADDRESS = "02:00:00:00:00:00";
    public static final int ERROR = Integer.MIN_VALUE;
    public static final String EXTRA_BLUETOOTH_ADDRESS = "android.bluetooth.adapter.extra.BLUETOOTH_ADDRESS";
    public static final String EXTRA_CONNECTION_STATE = "android.bluetooth.adapter.extra.CONNECTION_STATE";
    public static final String EXTRA_DISCOVERABLE_DURATION = "android.bluetooth.adapter.extra.DISCOVERABLE_DURATION";
    public static final String EXTRA_LOCAL_NAME = "android.bluetooth.adapter.extra.LOCAL_NAME";
    public static final String EXTRA_LOCAL_OOB_DATA = "android.bluetooth.adapter.extra.LOCAL_OOB_DATA";
    public static final String EXTRA_OOB_RESULT = "android.bluetooth.adapter.extra.EXTRA_OOB_RESULT";
    public static final String EXTRA_PREVIOUS_CONNECTION_STATE = "android.bluetooth.adapter.extra.PREVIOUS_CONNECTION_STATE";
    public static final String EXTRA_PREVIOUS_SCAN_MODE = "android.bluetooth.adapter.extra.PREVIOUS_SCAN_MODE";
    public static final String EXTRA_PREVIOUS_STATE = "android.bluetooth.adapter.extra.PREVIOUS_STATE";
    public static final String EXTRA_SCAN_MODE = "android.bluetooth.adapter.extra.SCAN_MODE";
    public static final String EXTRA_STATE = "android.bluetooth.adapter.extra.STATE";
    public static final int IO_CAPABILITY_IN = 2;
    public static final int IO_CAPABILITY_IO = 1;
    public static final int IO_CAPABILITY_KBDISP = 4;
    public static final int IO_CAPABILITY_MAX = 5;
    public static final int IO_CAPABILITY_NONE = 3;
    public static final int IO_CAPABILITY_OUT = 0;
    public static final int IO_CAPABILITY_UNKNOWN = 255;
    public static final int SCAN_MODE_CONNECTABLE = 21;
    public static final int SCAN_MODE_CONNECTABLE_DISCOVERABLE = 23;
    public static final int SCAN_MODE_NONE = 20;
    public static final int SOCKET_CHANNEL_AUTO_STATIC_NO_SDP = -2;
    public static final int STATE_BLE_ON = 15;
    public static final int STATE_BLE_TURNING_OFF = 16;
    public static final int STATE_BLE_TURNING_ON = 14;
    public static final int STATE_CONNECTED = 2;
    public static final int STATE_CONNECTING = 1;
    public static final int STATE_DISCONNECTED = 0;
    public static final int STATE_DISCONNECTING = 3;
    public static final int STATE_OFF = 10;
    public static final int STATE_ON = 12;
    public static final int STATE_TURNING_OFF = 13;
    public static final int STATE_TURNING_ON = 11;
    private static final String TAG = "BluetoothAdapter";
    private static final boolean VDBG = false;
    private static BluetoothAdapter sAdapter;
    private static BluetoothLeAdvertiser sBluetoothLeAdvertiser;
    private static BluetoothLeScanner sBluetoothLeScanner;
    private static PeriodicAdvertisingManager sPeriodicAdvertisingManager;
    private final PropertyInvalidatedCache<Void, Boolean> mBluetoothFilteringCache;
    private final PropertyInvalidatedCache<Void, Integer> mBluetoothGetStateCache;
    private Context mContext;
    private final PropertyInvalidatedCache<Integer, Integer> mGetProfileConnectionStateCache;
    private final Map<LeScanCallback, ScanCallback> mLeScanClients;
    private final IBluetoothManager mManagerService;
    private IBluetooth mService;
    private final IBinder mToken;
    public static final UUID LE_PSM_CHARACTERISTIC_UUID = UUID.fromString("2d410339-82b6-42aa-b34e-e2e01df8cc1a");
    private static final Map<BluetoothDevice, List<Pair<OnMetadataChangedListener, Executor>>> sMetadataListeners = new HashMap();
    private static final IBluetoothMetadataListener sBluetoothMetadataListener = new AnonymousClass1();
    private final ReentrantReadWriteLock mServiceLock = new ReentrantReadWriteLock();
    private final Object mLock = new Object();
    private final IBluetoothManagerCallback mManagerCallback = new AnonymousClass5();
    private final ArrayList<IBluetoothManagerCallback> mProxyServiceStateCallbacks = new ArrayList<>();

    @Retention(RetentionPolicy.SOURCE)
    public @interface ActiveDeviceUse {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface AdapterState {
    }

    public interface BluetoothStateChangeCallback {
        void onBluetoothStateChange(boolean z);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface IoCapability {
    }

    public interface LeScanCallback {
        void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bArr);
    }

    @SystemApi
    public interface OnMetadataChangedListener {
        void onMetadataChanged(BluetoothDevice bluetoothDevice, int i, byte[] bArr);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ScanMode {
    }

    public static int connectionPolicyToPriority(int i) {
        if (i != 0) {
            return i != 100 ? -1 : 100;
        }
        return 0;
    }

    public boolean changeApplicationBluetoothState(boolean z, BluetoothStateChangeCallback bluetoothStateChangeCallback) {
        return false;
    }

    public Pair<byte[], byte[]> readOutOfBandData() {
        return null;
    }

    public static String nameForState(int i) {
        switch (i) {
            case 10:
                return "OFF";
            case 11:
                return "TURNING_ON";
            case 12:
                return "ON";
            case 13:
                return "TURNING_OFF";
            case 14:
                return "BLE_TURNING_ON";
            case 15:
                return "BLE_ON";
            case 16:
                return "BLE_TURNING_OFF";
            default:
                return "?!?!? (" + i + ")";
        }
    }

    /* JADX INFO: renamed from: android.bluetooth.BluetoothAdapter$1, reason: invalid class name */
    class AnonymousClass1 extends IBluetoothMetadataListener.Stub {
        AnonymousClass1() {
        }

        @Override // android.bluetooth.IBluetoothMetadataListener
        public void onMetadataChanged(final BluetoothDevice bluetoothDevice, final int i, final byte[] bArr) {
            synchronized (BluetoothAdapter.sMetadataListeners) {
                if (BluetoothAdapter.sMetadataListeners.containsKey(bluetoothDevice)) {
                    for (Pair pair : (List) BluetoothAdapter.sMetadataListeners.get(bluetoothDevice)) {
                        final OnMetadataChangedListener onMetadataChangedListener = (OnMetadataChangedListener) pair.first;
                        ((Executor) pair.second).execute(new Runnable() { // from class: android.bluetooth.-$$Lambda$BluetoothAdapter$1$I3p3FVKkxuogQU8eug7PAKoZKZc
                            @Override // java.lang.Runnable
                            public final void run() {
                                onMetadataChangedListener.onMetadataChanged(bluetoothDevice, i, bArr);
                            }
                        });
                    }
                }
            }
        }
    }

    public static synchronized BluetoothAdapter getDefaultAdapter() {
        if (sAdapter == null) {
            IBinder service = ServiceManager.getService(BLUETOOTH_MANAGER_SERVICE);
            if (service != null) {
                sAdapter = new BluetoothAdapter(IBluetoothManager.Stub.asInterface(service));
            } else {
                Log.e(TAG, "Bluetooth binder is null");
            }
        }
        return sAdapter;
    }

    BluetoothAdapter(IBluetoothManager iBluetoothManager) {
        int i = 8;
        this.mBluetoothGetStateCache = new PropertyInvalidatedCache<Void, Integer>(i, BLUETOOTH_GET_STATE_CACHE_PROPERTY) { // from class: android.bluetooth.BluetoothAdapter.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.app.PropertyInvalidatedCache
            public Integer recompute(Void r1) {
                try {
                    return Integer.valueOf(BluetoothAdapter.this.mService.getState());
                } catch (RemoteException e) {
                    throw e.rethrowFromSystemServer();
                }
            }
        };
        this.mBluetoothFilteringCache = new PropertyInvalidatedCache<Void, Boolean>(i, BLUETOOTH_FILTERING_CACHE_PROPERTY) { // from class: android.bluetooth.BluetoothAdapter.3
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.app.PropertyInvalidatedCache
            public Boolean recompute(Void r3) {
                try {
                    try {
                        BluetoothAdapter.this.mServiceLock.readLock().lock();
                        if (BluetoothAdapter.this.mService != null) {
                            return Boolean.valueOf(BluetoothAdapter.this.mService.isOffloadedFilteringSupported());
                        }
                    } catch (RemoteException e) {
                        Log.e(BluetoothAdapter.TAG, "failed to get isOffloadedFilteringSupported, error: ", e);
                    }
                    BluetoothAdapter.this.mServiceLock.readLock().unlock();
                    return false;
                } finally {
                    BluetoothAdapter.this.mServiceLock.readLock().unlock();
                }
            }
        };
        this.mGetProfileConnectionStateCache = new PropertyInvalidatedCache<Integer, Integer>(i, BLUETOOTH_PROFILE_CACHE_PROPERTY) { // from class: android.bluetooth.BluetoothAdapter.4
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.app.PropertyInvalidatedCache
            public Integer recompute(Integer num) {
                try {
                    try {
                        BluetoothAdapter.this.mServiceLock.readLock().lock();
                        if (BluetoothAdapter.this.mService != null) {
                            return Integer.valueOf(BluetoothAdapter.this.mService.getProfileConnectionState(num.intValue()));
                        }
                    } catch (RemoteException e) {
                        Log.e(BluetoothAdapter.TAG, "getProfileConnectionState:", e);
                    }
                    BluetoothAdapter.this.mServiceLock.readLock().unlock();
                    return 0;
                } finally {
                    BluetoothAdapter.this.mServiceLock.readLock().unlock();
                }
            }

            @Override // android.app.PropertyInvalidatedCache
            public String queryToString(Integer num) {
                return String.format("getProfileConnectionState(profile=\"%d\")", num);
            }
        };
        try {
            if (iBluetoothManager == null) {
                throw new IllegalArgumentException("bluetooth manager service is null");
            }
            try {
                this.mServiceLock.writeLock().lock();
                this.mService = iBluetoothManager.registerAdapter(this.mManagerCallback);
            } catch (RemoteException e) {
                Log.e(TAG, "", e);
            }
            this.mServiceLock.writeLock().unlock();
            this.mManagerService = iBluetoothManager;
            this.mLeScanClients = new HashMap();
            this.mToken = new Binder();
        } catch (Throwable th) {
            this.mServiceLock.writeLock().unlock();
            throw th;
        }
    }

    public BluetoothDevice getRemoteDevice(String str) {
        return new BluetoothDevice(str);
    }

    public BluetoothDevice getRemoteDevice(byte[] bArr) {
        if (bArr == null || bArr.length != 6) {
            throw new IllegalArgumentException("Bluetooth address must have 6 bytes");
        }
        return new BluetoothDevice(String.format(Locale.US, "%02X:%02X:%02X:%02X:%02X:%02X", Byte.valueOf(bArr[0]), Byte.valueOf(bArr[1]), Byte.valueOf(bArr[2]), Byte.valueOf(bArr[3]), Byte.valueOf(bArr[4]), Byte.valueOf(bArr[5])));
    }

    public BluetoothLeAdvertiser getBluetoothLeAdvertiser() {
        if (!getLeAccess()) {
            return null;
        }
        synchronized (this.mLock) {
            if (sBluetoothLeAdvertiser == null) {
                sBluetoothLeAdvertiser = new BluetoothLeAdvertiser(this.mManagerService);
            }
        }
        return sBluetoothLeAdvertiser;
    }

    public PeriodicAdvertisingManager getPeriodicAdvertisingManager() {
        if (!getLeAccess() || !isLePeriodicAdvertisingSupported()) {
            return null;
        }
        synchronized (this.mLock) {
            if (sPeriodicAdvertisingManager == null) {
                sPeriodicAdvertisingManager = new PeriodicAdvertisingManager(this.mManagerService);
            }
        }
        return sPeriodicAdvertisingManager;
    }

    public BluetoothLeScanner getBluetoothLeScanner() {
        if (!getLeAccess()) {
            return null;
        }
        synchronized (this.mLock) {
            if (sBluetoothLeScanner == null) {
                sBluetoothLeScanner = new BluetoothLeScanner(this.mManagerService, getOpPackageName(), getAttributionTag());
            }
        }
        return sBluetoothLeScanner;
    }

    public boolean isEnabled() {
        return getState() == 12;
    }

    @SystemApi
    public boolean isLeEnabled() {
        int leState = getLeState();
        Log.d(TAG, "isLeEnabled(): " + nameForState(leState));
        return leState == 12 || leState == 15 || leState == 11 || leState == 13;
    }

    @SystemApi
    public boolean disableBLE() {
        if (!isBleScanAlwaysAvailable()) {
            return false;
        }
        try {
            return this.mManagerService.disableBle(ActivityThread.currentPackageName(), this.mToken);
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
            return false;
        }
    }

    @SystemApi
    public boolean enableBLE() {
        if (!isBleScanAlwaysAvailable()) {
            return false;
        }
        try {
            return this.mManagerService.enableBle(ActivityThread.currentPackageName(), this.mToken);
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
            return false;
        }
    }

    public void disableBluetoothGetStateCache() {
        this.mBluetoothGetStateCache.disableLocal();
    }

    public static void invalidateBluetoothGetStateCache() {
        PropertyInvalidatedCache.invalidateCache(BLUETOOTH_GET_STATE_CACHE_PROPERTY);
    }

    private int getStateInternal() {
        int iIntValue = 10;
        try {
            try {
                this.mServiceLock.readLock().lock();
                if (this.mService != null) {
                    iIntValue = this.mBluetoothGetStateCache.query(null).intValue();
                }
            } catch (RuntimeException e) {
                if (e.getCause() instanceof RemoteException) {
                    Log.e(TAG, "", e.getCause());
                } else {
                    throw e;
                }
            }
            return iIntValue;
        } finally {
            this.mServiceLock.readLock().unlock();
        }
    }

    public int getState() {
        int stateInternal = getStateInternal();
        if (stateInternal == 15 || stateInternal == 14 || stateInternal == 16) {
            return 10;
        }
        return stateInternal;
    }

    public int getLeState() {
        return getStateInternal();
    }

    boolean getLeAccess() {
        return getLeState() == 12 || getLeState() == 15;
    }

    public boolean enable() {
        if (isEnabled()) {
            Log.d(TAG, "enable(): BT already enabled!");
            return true;
        }
        try {
            return this.mManagerService.enable(ActivityThread.currentPackageName());
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
            return false;
        }
    }

    public boolean disable() {
        try {
            return this.mManagerService.disable(ActivityThread.currentPackageName(), true);
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
            return false;
        }
    }

    public boolean disable(boolean z) {
        try {
            return this.mManagerService.disable(ActivityThread.currentPackageName(), z);
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
            return false;
        }
    }

    public String getAddress() {
        try {
            return this.mManagerService.getAddress();
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
            return null;
        }
    }

    public String getName() {
        try {
            return this.mManagerService.getName();
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
            return null;
        }
    }

    public boolean factoryReset() {
        try {
            try {
                this.mServiceLock.readLock().lock();
                if (this.mService == null || !this.mService.factoryReset() || this.mManagerService == null || !this.mManagerService.onFactoryReset()) {
                    Log.e(TAG, "factoryReset(): Setting persist.bluetooth.factoryreset to retry later");
                    SystemProperties.set("persist.bluetooth.factoryreset", "true");
                } else {
                    return true;
                }
            } catch (RemoteException e) {
                Log.e(TAG, "", e);
            }
            this.mServiceLock.readLock().unlock();
            return false;
        } finally {
            this.mServiceLock.readLock().unlock();
        }
    }

    public ParcelUuid[] getUuids() {
        try {
            if (getState() != 12) {
                return null;
            }
            try {
                this.mServiceLock.readLock().lock();
                if (this.mService != null) {
                    return this.mService.getUuids();
                }
            } catch (RemoteException e) {
                Log.e(TAG, "", e);
            }
            return null;
        } finally {
            this.mServiceLock.readLock().unlock();
        }
    }

    public boolean setName(String str) {
        try {
            if (getState() != 12) {
                return false;
            }
            try {
                this.mServiceLock.readLock().lock();
                if (this.mService != null) {
                    return this.mService.setName(str);
                }
            } catch (RemoteException e) {
                Log.e(TAG, "", e);
            }
            return false;
        } finally {
            this.mServiceLock.readLock().unlock();
        }
    }

    public BluetoothClass getBluetoothClass() {
        try {
            if (getState() != 12) {
                return null;
            }
            try {
                this.mServiceLock.readLock().lock();
                if (this.mService != null) {
                    return this.mService.getBluetoothClass();
                }
            } catch (RemoteException e) {
                Log.e(TAG, "", e);
            }
            return null;
        } finally {
            this.mServiceLock.readLock().unlock();
        }
    }

    public boolean setBluetoothClass(BluetoothClass bluetoothClass) {
        try {
            if (getState() != 12) {
                return false;
            }
            try {
                this.mServiceLock.readLock().lock();
                if (this.mService != null) {
                    return this.mService.setBluetoothClass(bluetoothClass);
                }
            } catch (RemoteException e) {
                Log.e(TAG, "", e);
            }
            return false;
        } finally {
            this.mServiceLock.readLock().unlock();
        }
    }

    public int getIoCapability() {
        try {
            if (getState() != 12) {
                return 255;
            }
            try {
                this.mServiceLock.readLock().lock();
                if (this.mService != null) {
                    return this.mService.getIoCapability();
                }
            } catch (RemoteException e) {
                Log.e(TAG, e.getMessage(), e);
            }
            return 255;
        } finally {
            this.mServiceLock.readLock().unlock();
        }
    }

    public boolean setIoCapability(int i) {
        try {
            if (getState() != 12) {
                return false;
            }
            try {
                this.mServiceLock.readLock().lock();
                if (this.mService != null) {
                    return this.mService.setIoCapability(i);
                }
            } catch (RemoteException e) {
                Log.e(TAG, e.getMessage(), e);
            }
            return false;
        } finally {
            this.mServiceLock.readLock().unlock();
        }
    }

    public int getLeIoCapability() {
        try {
            if (getState() != 12) {
                return 255;
            }
            try {
                this.mServiceLock.readLock().lock();
                if (this.mService != null) {
                    return this.mService.getLeIoCapability();
                }
            } catch (RemoteException e) {
                Log.e(TAG, e.getMessage(), e);
            }
            return 255;
        } finally {
            this.mServiceLock.readLock().unlock();
        }
    }

    public boolean setLeIoCapability(int i) {
        try {
            if (getState() != 12) {
                return false;
            }
            try {
                this.mServiceLock.readLock().lock();
                if (this.mService != null) {
                    return this.mService.setLeIoCapability(i);
                }
            } catch (RemoteException e) {
                Log.e(TAG, e.getMessage(), e);
            }
            return false;
        } finally {
            this.mServiceLock.readLock().unlock();
        }
    }

    public int getScanMode() {
        try {
            if (getState() != 12) {
                return 20;
            }
            try {
                this.mServiceLock.readLock().lock();
                if (this.mService != null) {
                    return this.mService.getScanMode();
                }
            } catch (RemoteException e) {
                Log.e(TAG, "", e);
            }
            return 20;
        } finally {
            this.mServiceLock.readLock().unlock();
        }
    }

    public boolean setScanMode(int i, long j) {
        try {
            if (getState() != 12) {
                return false;
            }
            try {
                try {
                    this.mServiceLock.readLock().lock();
                    if (this.mService != null) {
                        return this.mService.setScanMode(i, Math.toIntExact(j / 1000));
                    }
                } catch (ArithmeticException unused) {
                    Log.e(TAG, "setScanMode: Duration in seconds outside of the bounds of an int");
                    throw new IllegalArgumentException("Duration not in bounds. In seconds, the durationMillis must be in the range of an int");
                }
            } catch (RemoteException e) {
                Log.e(TAG, "", e);
            }
            return false;
        } finally {
            this.mServiceLock.readLock().unlock();
        }
    }

    public boolean setScanMode(int i) {
        if (getState() != 12) {
            return false;
        }
        try {
            try {
                this.mServiceLock.readLock().lock();
                if (this.mService != null) {
                    return this.mService.setScanMode(i, getDiscoverableTimeout());
                }
            } catch (RemoteException e) {
                Log.e(TAG, "", e);
            }
            return false;
        } finally {
            this.mServiceLock.readLock().unlock();
        }
    }

    public int getDiscoverableTimeout() {
        try {
            if (getState() != 12) {
                return -1;
            }
            try {
                this.mServiceLock.readLock().lock();
                if (this.mService != null) {
                    return this.mService.getDiscoverableTimeout();
                }
            } catch (RemoteException e) {
                Log.e(TAG, "", e);
            }
            return -1;
        } finally {
            this.mServiceLock.readLock().unlock();
        }
    }

    public void setDiscoverableTimeout(int i) {
        if (getState() != 12) {
            return;
        }
        try {
            try {
                this.mServiceLock.readLock().lock();
                if (this.mService != null) {
                    this.mService.setDiscoverableTimeout(i);
                }
            } catch (RemoteException e) {
                Log.e(TAG, "", e);
            }
        } finally {
            this.mServiceLock.readLock().unlock();
        }
    }

    @SystemApi
    public long getDiscoveryEndMillis() {
        try {
            try {
                this.mServiceLock.readLock().lock();
                if (this.mService != null) {
                    return this.mService.getDiscoveryEndMillis();
                }
            } catch (RemoteException e) {
                Log.e(TAG, "", e);
            }
            this.mServiceLock.readLock().unlock();
            return -1L;
        } finally {
            this.mServiceLock.readLock().unlock();
        }
    }

    public void setContext(Context context) {
        this.mContext = context;
    }

    private String getOpPackageName() {
        Context context = this.mContext;
        if (context != null) {
            return context.getOpPackageName();
        }
        return ActivityThread.currentOpPackageName();
    }

    private String getAttributionTag() {
        Context context = this.mContext;
        if (context != null) {
            return context.getAttributionTag();
        }
        return null;
    }

    public boolean startDiscovery() {
        if (getState() != 12) {
            return false;
        }
        try {
            try {
                this.mServiceLock.readLock().lock();
                if (this.mService != null) {
                    return this.mService.startDiscovery(getOpPackageName(), getAttributionTag());
                }
            } catch (RemoteException e) {
                Log.e(TAG, "", e);
            }
            return false;
        } finally {
            this.mServiceLock.readLock().unlock();
        }
    }

    public boolean cancelDiscovery() {
        try {
            if (getState() != 12) {
                return false;
            }
            try {
                this.mServiceLock.readLock().lock();
                if (this.mService != null) {
                    return this.mService.cancelDiscovery();
                }
            } catch (RemoteException e) {
                Log.e(TAG, "", e);
            }
            return false;
        } finally {
            this.mServiceLock.readLock().unlock();
        }
    }

    public boolean isDiscovering() {
        try {
            if (getState() != 12) {
                return false;
            }
            try {
                this.mServiceLock.readLock().lock();
                if (this.mService != null) {
                    return this.mService.isDiscovering();
                }
            } catch (RemoteException e) {
                Log.e(TAG, "", e);
            }
            return false;
        } finally {
            this.mServiceLock.readLock().unlock();
        }
    }

    @SystemApi
    public boolean removeActiveDevice(int i) {
        if (i != 0 && i != 1 && i != 2) {
            Log.e(TAG, "Invalid profiles param value in removeActiveDevice");
            throw new IllegalArgumentException("Profiles must be one of BluetoothAdapter.ACTIVE_DEVICE_AUDIO, BluetoothAdapter.ACTIVE_DEVICE_PHONE_CALL, or BluetoothAdapter.ACTIVE_DEVICE_ALL");
        }
        try {
            try {
                this.mServiceLock.readLock().lock();
                if (this.mService != null) {
                    return this.mService.removeActiveDevice(i);
                }
            } catch (RemoteException e) {
                Log.e(TAG, "", e);
            }
            this.mServiceLock.readLock().unlock();
            return false;
        } finally {
            this.mServiceLock.readLock().unlock();
        }
    }

    @SystemApi
    public boolean setActiveDevice(BluetoothDevice bluetoothDevice, int i) {
        if (bluetoothDevice == null) {
            Log.e(TAG, "setActiveDevice: Null device passed as parameter");
            throw new IllegalArgumentException("device cannot be null");
        }
        if (i != 0 && i != 1 && i != 2) {
            Log.e(TAG, "Invalid profiles param value in setActiveDevice");
            throw new IllegalArgumentException("Profiles must be one of BluetoothAdapter.ACTIVE_DEVICE_AUDIO, BluetoothAdapter.ACTIVE_DEVICE_PHONE_CALL, or BluetoothAdapter.ACTIVE_DEVICE_ALL");
        }
        try {
            try {
                this.mServiceLock.readLock().lock();
                if (this.mService != null) {
                    return this.mService.setActiveDevice(bluetoothDevice, i);
                }
            } catch (RemoteException e) {
                Log.e(TAG, "", e);
            }
            this.mServiceLock.readLock().unlock();
            return false;
        } finally {
            this.mServiceLock.readLock().unlock();
        }
    }

    public boolean connectAllEnabledProfiles(BluetoothDevice bluetoothDevice) {
        try {
            try {
                this.mServiceLock.readLock().lock();
                if (this.mService != null) {
                    return this.mService.connectAllEnabledProfiles(bluetoothDevice);
                }
            } catch (RemoteException e) {
                Log.e(TAG, "", e);
            }
            this.mServiceLock.readLock().unlock();
            return false;
        } finally {
            this.mServiceLock.readLock().unlock();
        }
    }

    public boolean disconnectAllEnabledProfiles(BluetoothDevice bluetoothDevice) {
        try {
            try {
                this.mServiceLock.readLock().lock();
                if (this.mService != null) {
                    return this.mService.disconnectAllEnabledProfiles(bluetoothDevice);
                }
            } catch (RemoteException e) {
                Log.e(TAG, "", e);
            }
            this.mServiceLock.readLock().unlock();
            return false;
        } finally {
            this.mServiceLock.readLock().unlock();
        }
    }

    public boolean isMultipleAdvertisementSupported() {
        try {
            if (getState() != 12) {
                return false;
            }
            try {
                this.mServiceLock.readLock().lock();
                if (this.mService != null) {
                    return this.mService.isMultiAdvertisementSupported();
                }
            } catch (RemoteException e) {
                Log.e(TAG, "failed to get isMultipleAdvertisementSupported, error: ", e);
            }
            return false;
        } finally {
            this.mServiceLock.readLock().unlock();
        }
    }

    @SystemApi
    public boolean isBleScanAlwaysAvailable() {
        try {
            return this.mManagerService.isBleScanAlwaysAvailable();
        } catch (RemoteException e) {
            Log.e(TAG, "remote expection when calling isBleScanAlwaysAvailable", e);
            return false;
        }
    }

    public void disableIsOffloadedFilteringSupportedCache() {
        this.mBluetoothFilteringCache.disableLocal();
    }

    public static void invalidateIsOffloadedFilteringSupportedCache() {
        PropertyInvalidatedCache.invalidateCache(BLUETOOTH_FILTERING_CACHE_PROPERTY);
    }

    public boolean isOffloadedFilteringSupported() {
        if (getLeAccess()) {
            return this.mBluetoothFilteringCache.query(null).booleanValue();
        }
        return false;
    }

    public boolean isOffloadedScanBatchingSupported() {
        try {
            if (!getLeAccess()) {
                return false;
            }
            try {
                this.mServiceLock.readLock().lock();
                if (this.mService != null) {
                    return this.mService.isOffloadedScanBatchingSupported();
                }
            } catch (RemoteException e) {
                Log.e(TAG, "failed to get isOffloadedScanBatchingSupported, error: ", e);
            }
            return false;
        } finally {
            this.mServiceLock.readLock().unlock();
        }
    }

    public boolean isLe2MPhySupported() {
        try {
            if (!getLeAccess()) {
                return false;
            }
            try {
                this.mServiceLock.readLock().lock();
                if (this.mService != null) {
                    return this.mService.isLe2MPhySupported();
                }
            } catch (RemoteException e) {
                Log.e(TAG, "failed to get isExtendedAdvertisingSupported, error: ", e);
            }
            return false;
        } finally {
            this.mServiceLock.readLock().unlock();
        }
    }

    public boolean isLeCodedPhySupported() {
        try {
            if (!getLeAccess()) {
                return false;
            }
            try {
                this.mServiceLock.readLock().lock();
                if (this.mService != null) {
                    return this.mService.isLeCodedPhySupported();
                }
            } catch (RemoteException e) {
                Log.e(TAG, "failed to get isLeCodedPhySupported, error: ", e);
            }
            return false;
        } finally {
            this.mServiceLock.readLock().unlock();
        }
    }

    public boolean isLeExtendedAdvertisingSupported() {
        try {
            if (!getLeAccess()) {
                return false;
            }
            try {
                this.mServiceLock.readLock().lock();
                if (this.mService != null) {
                    return this.mService.isLeExtendedAdvertisingSupported();
                }
            } catch (RemoteException e) {
                Log.e(TAG, "failed to get isLeExtendedAdvertisingSupported, error: ", e);
            }
            return false;
        } finally {
            this.mServiceLock.readLock().unlock();
        }
    }

    public boolean isLePeriodicAdvertisingSupported() {
        try {
            if (!getLeAccess()) {
                return false;
            }
            try {
                this.mServiceLock.readLock().lock();
                if (this.mService != null) {
                    return this.mService.isLePeriodicAdvertisingSupported();
                }
            } catch (RemoteException e) {
                Log.e(TAG, "failed to get isLePeriodicAdvertisingSupported, error: ", e);
            }
            return false;
        } finally {
            this.mServiceLock.readLock().unlock();
        }
    }

    public int getLeMaximumAdvertisingDataLength() {
        try {
            if (!getLeAccess()) {
                return 0;
            }
            try {
                this.mServiceLock.readLock().lock();
                if (this.mService != null) {
                    return this.mService.getLeMaximumAdvertisingDataLength();
                }
            } catch (RemoteException e) {
                Log.e(TAG, "failed to get getLeMaximumAdvertisingDataLength, error: ", e);
            }
            return 0;
        } finally {
            this.mServiceLock.readLock().unlock();
        }
    }

    private boolean isHearingAidProfileSupported() {
        try {
            return this.mManagerService.isHearingAidProfileSupported();
        } catch (RemoteException e) {
            Log.e(TAG, "remote expection when calling isHearingAidProfileSupported", e);
            return false;
        }
    }

    public int getMaxConnectedAudioDevices() {
        try {
            try {
                this.mServiceLock.readLock().lock();
                if (this.mService != null) {
                    return this.mService.getMaxConnectedAudioDevices();
                }
            } catch (RemoteException e) {
                Log.e(TAG, "failed to get getMaxConnectedAudioDevices, error: ", e);
            }
            this.mServiceLock.readLock().unlock();
            return 1;
        } finally {
            this.mServiceLock.readLock().unlock();
        }
    }

    public boolean isHardwareTrackingFiltersAvailable() {
        if (!getLeAccess()) {
            return false;
        }
        try {
            IBluetoothGatt bluetoothGatt = this.mManagerService.getBluetoothGatt();
            if (bluetoothGatt == null) {
                return false;
            }
            return bluetoothGatt.numHwTrackFiltersAvailable() != 0;
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
            return false;
        }
    }

    @Deprecated
    public BluetoothActivityEnergyInfo getControllerActivityEnergyInfo(int i) {
        SynchronousResultReceiver synchronousResultReceiver = new SynchronousResultReceiver();
        requestControllerActivityEnergyInfo(synchronousResultReceiver);
        try {
            SynchronousResultReceiver.Result resultAwaitResult = synchronousResultReceiver.awaitResult(1000L);
            if (resultAwaitResult.bundle != null) {
                return (BluetoothActivityEnergyInfo) resultAwaitResult.bundle.getParcelable("controller_activity");
            }
            return null;
        } catch (TimeoutException unused) {
            Log.e(TAG, "getControllerActivityEnergyInfo timed out");
            return null;
        }
    }

    public void requestControllerActivityEnergyInfo(ResultReceiver resultReceiver) {
        try {
            try {
                this.mServiceLock.readLock().lock();
                if (this.mService != null) {
                    this.mService.requestActivityInfo(resultReceiver);
                    resultReceiver = null;
                }
                this.mServiceLock.readLock().unlock();
                if (resultReceiver == null) {
                    return;
                }
            } catch (RemoteException e) {
                Log.e(TAG, "getControllerActivityEnergyInfoCallback: " + e);
                this.mServiceLock.readLock().unlock();
                if (resultReceiver == null) {
                    return;
                }
            }
            resultReceiver.send(0, null);
        } catch (Throwable th) {
            this.mServiceLock.readLock().unlock();
            if (resultReceiver != null) {
                resultReceiver.send(0, null);
            }
            throw th;
        }
    }

    public List<BluetoothDevice> getMostRecentlyConnectedDevices() {
        if (getState() != 12) {
            return new ArrayList();
        }
        try {
            try {
                this.mServiceLock.readLock().lock();
                if (this.mService != null) {
                    return this.mService.getMostRecentlyConnectedDevices();
                }
            } catch (RemoteException e) {
                Log.e(TAG, "", e);
            }
            this.mServiceLock.readLock().unlock();
            return new ArrayList();
        } finally {
            this.mServiceLock.readLock().unlock();
        }
    }

    public Set<BluetoothDevice> getBondedDevices() {
        try {
            if (getState() != 12) {
                return toDeviceSet(new BluetoothDevice[0]);
            }
            try {
                this.mServiceLock.readLock().lock();
                if (this.mService != null) {
                    return toDeviceSet(this.mService.getBondedDevices());
                }
                return toDeviceSet(new BluetoothDevice[0]);
            } catch (RemoteException e) {
                Log.e(TAG, "", e);
                this.mServiceLock.readLock().unlock();
                return null;
            }
        } finally {
            this.mServiceLock.readLock().unlock();
        }
    }

    public List<Integer> getSupportedProfiles() {
        ArrayList arrayList = new ArrayList();
        try {
            synchronized (this.mManagerCallback) {
                if (this.mService != null) {
                    long supportedProfiles = this.mService.getSupportedProfiles();
                    for (int i = 0; i <= 21; i++) {
                        if ((((long) (1 << i)) & supportedProfiles) != 0) {
                            arrayList.add(Integer.valueOf(i));
                        }
                    }
                } else if (isHearingAidProfileSupported()) {
                    arrayList.add(21);
                }
            }
        } catch (RemoteException e) {
            Log.e(TAG, "getSupportedProfiles:", e);
        }
        return arrayList;
    }

    public int getConnectionState() {
        try {
            if (getState() != 12) {
                return 0;
            }
            try {
                this.mServiceLock.readLock().lock();
                if (this.mService != null) {
                    return this.mService.getAdapterConnectionState();
                }
            } catch (RemoteException e) {
                Log.e(TAG, "getConnectionState:", e);
            }
            return 0;
        } finally {
            this.mServiceLock.readLock().unlock();
        }
    }

    public void disableGetProfileConnectionStateCache() {
        this.mGetProfileConnectionStateCache.disableLocal();
    }

    public static void invalidateGetProfileConnectionStateCache() {
        PropertyInvalidatedCache.invalidateCache(BLUETOOTH_PROFILE_CACHE_PROPERTY);
    }

    public int getProfileConnectionState(int i) {
        if (getState() != 12) {
            return 0;
        }
        return this.mGetProfileConnectionStateCache.query(new Integer(i)).intValue();
    }

    public BluetoothServerSocket listenUsingRfcommOn(int i) throws IOException {
        return listenUsingRfcommOn(i, false, false);
    }

    public BluetoothServerSocket listenUsingRfcommOn(int i, boolean z, boolean z2) throws IOException {
        BluetoothServerSocket bluetoothServerSocket = new BluetoothServerSocket(1, true, true, i, z, z2);
        int iBindListen = bluetoothServerSocket.mSocket.bindListen();
        if (i == -2) {
            bluetoothServerSocket.setChannel(bluetoothServerSocket.mSocket.getPort());
        }
        if (iBindListen == 0) {
            return bluetoothServerSocket;
        }
        throw new IOException("Error: " + iBindListen);
    }

    public BluetoothServerSocket listenUsingRfcommWithServiceRecord(String str, UUID uuid) throws IOException {
        return createNewRfcommSocketAndRecord(str, uuid, true, true);
    }

    public BluetoothServerSocket listenUsingInsecureRfcommWithServiceRecord(String str, UUID uuid) throws IOException {
        return createNewRfcommSocketAndRecord(str, uuid, false, false);
    }

    public BluetoothServerSocket listenUsingEncryptedRfcommWithServiceRecord(String str, UUID uuid) throws IOException {
        return createNewRfcommSocketAndRecord(str, uuid, false, true);
    }

    private BluetoothServerSocket createNewRfcommSocketAndRecord(String str, UUID uuid, boolean z, boolean z2) throws IOException {
        BluetoothServerSocket bluetoothServerSocket = new BluetoothServerSocket(1, z, z2, new ParcelUuid(uuid));
        bluetoothServerSocket.setServiceName(str);
        int iBindListen = bluetoothServerSocket.mSocket.bindListen();
        if (iBindListen == 0) {
            return bluetoothServerSocket;
        }
        throw new IOException("Error: " + iBindListen);
    }

    public BluetoothServerSocket listenUsingInsecureRfcommOn(int i) throws IOException {
        BluetoothServerSocket bluetoothServerSocket = new BluetoothServerSocket(1, false, false, i);
        int iBindListen = bluetoothServerSocket.mSocket.bindListen();
        if (i == -2) {
            bluetoothServerSocket.setChannel(bluetoothServerSocket.mSocket.getPort());
        }
        if (iBindListen == 0) {
            return bluetoothServerSocket;
        }
        throw new IOException("Error: " + iBindListen);
    }

    public BluetoothServerSocket listenUsingEncryptedRfcommOn(int i) throws IOException {
        BluetoothServerSocket bluetoothServerSocket = new BluetoothServerSocket(1, false, true, i);
        int iBindListen = bluetoothServerSocket.mSocket.bindListen();
        if (i == -2) {
            bluetoothServerSocket.setChannel(bluetoothServerSocket.mSocket.getPort());
        }
        if (iBindListen >= 0) {
            return bluetoothServerSocket;
        }
        throw new IOException("Error: " + iBindListen);
    }

    public static BluetoothServerSocket listenUsingScoOn() throws IOException {
        BluetoothServerSocket bluetoothServerSocket = new BluetoothServerSocket(2, false, false, -1);
        bluetoothServerSocket.mSocket.bindListen();
        return bluetoothServerSocket;
    }

    public BluetoothServerSocket listenUsingL2capOn(int i, boolean z, boolean z2) throws IOException {
        BluetoothServerSocket bluetoothServerSocket = new BluetoothServerSocket(3, true, true, i, z, z2);
        int iBindListen = bluetoothServerSocket.mSocket.bindListen();
        if (i == -2) {
            int port = bluetoothServerSocket.mSocket.getPort();
            Log.d(TAG, "listenUsingL2capOn: set assigned channel to " + port);
            bluetoothServerSocket.setChannel(port);
        }
        if (iBindListen == 0) {
            return bluetoothServerSocket;
        }
        throw new IOException("Error: " + iBindListen);
    }

    public BluetoothServerSocket listenUsingL2capOn(int i) throws IOException {
        return listenUsingL2capOn(i, false, false);
    }

    public BluetoothServerSocket listenUsingInsecureL2capOn(int i) throws IOException {
        Log.d(TAG, "listenUsingInsecureL2capOn: port=" + i);
        BluetoothServerSocket bluetoothServerSocket = new BluetoothServerSocket(3, false, false, i, false, false);
        int iBindListen = bluetoothServerSocket.mSocket.bindListen();
        if (i == -2) {
            int port = bluetoothServerSocket.mSocket.getPort();
            Log.d(TAG, "listenUsingInsecureL2capOn: set assigned channel to " + port);
            bluetoothServerSocket.setChannel(port);
        }
        if (iBindListen == 0) {
            return bluetoothServerSocket;
        }
        throw new IOException("Error: " + iBindListen);
    }

    public boolean readLocalOobData() {
        try {
            if (getState() != 12) {
                return false;
            }
            try {
                this.mServiceLock.readLock().lock();
                if (this.mService != null) {
                    return this.mService.readLocalOobData();
                }
            } catch (RemoteException e) {
                Log.e(TAG, e.getMessage(), e);
            }
            this.mServiceLock.readLock().unlock();
            return true;
        } finally {
            this.mServiceLock.readLock().unlock();
        }
    }

    public boolean getProfileProxy(Context context, BluetoothProfile.ServiceListener serviceListener, int i) {
        if (context != null && serviceListener != null) {
            if (i == 1) {
                new BluetoothHeadset(context, serviceListener);
                return true;
            }
            if (i == 2) {
                new BluetoothA2dp(context, serviceListener);
                return true;
            }
            if (i == 11) {
                new BluetoothA2dpSink(context, serviceListener);
                return true;
            }
            if (i == 12) {
                new BluetoothAvrcpController(context, serviceListener);
                return true;
            }
            if (i == 4) {
                new BluetoothHidHost(context, serviceListener);
                return true;
            }
            if (i == 5) {
                new BluetoothPan(context, serviceListener);
                return true;
            }
            if (i == 6) {
                new BluetoothPbap(context, serviceListener);
                return true;
            }
            if (i == 3) {
                Log.e(TAG, "getProfileProxy(): BluetoothHealth is deprecated");
                return false;
            }
            if (i == 9) {
                new BluetoothMap(context, serviceListener);
                return true;
            }
            if (i == 16) {
                new BluetoothHeadsetClient(context, serviceListener);
                return true;
            }
            if (i == 10) {
                new BluetoothSap(context, serviceListener);
                return true;
            }
            if (i == 17) {
                new BluetoothPbapClient(context, serviceListener);
                return true;
            }
            if (i == 18) {
                new BluetoothMapClient(context, serviceListener);
                return true;
            }
            if (i == 19) {
                new BluetoothHidDevice(context, serviceListener);
                return true;
            }
            if (i == 21 && isHearingAidProfileSupported()) {
                new BluetoothHearingAid(context, serviceListener);
                return true;
            }
        }
        return false;
    }

    public void closeProfileProxy(int i, BluetoothProfile bluetoothProfile) {
        if (bluetoothProfile == null) {
        }
        switch (i) {
            case 1:
                ((BluetoothHeadset) bluetoothProfile).close();
                break;
            case 2:
                ((BluetoothA2dp) bluetoothProfile).close();
                break;
            case 4:
                ((BluetoothHidHost) bluetoothProfile).close();
                break;
            case 5:
                ((BluetoothPan) bluetoothProfile).close();
                break;
            case 6:
                ((BluetoothPbap) bluetoothProfile).close();
                break;
            case 7:
                ((BluetoothGatt) bluetoothProfile).close();
                break;
            case 8:
                ((BluetoothGattServer) bluetoothProfile).close();
                break;
            case 9:
                ((BluetoothMap) bluetoothProfile).close();
                break;
            case 10:
                ((BluetoothSap) bluetoothProfile).close();
                break;
            case 11:
                ((BluetoothA2dpSink) bluetoothProfile).close();
                break;
            case 12:
                ((BluetoothAvrcpController) bluetoothProfile).close();
                break;
            case 16:
                ((BluetoothHeadsetClient) bluetoothProfile).close();
                break;
            case 17:
                ((BluetoothPbapClient) bluetoothProfile).close();
                break;
            case 18:
                ((BluetoothMapClient) bluetoothProfile).close();
                break;
            case 19:
                ((BluetoothHidDevice) bluetoothProfile).close();
                break;
            case 21:
                ((BluetoothHearingAid) bluetoothProfile).close();
                break;
        }
    }

    /* JADX INFO: renamed from: android.bluetooth.BluetoothAdapter$5, reason: invalid class name */
    class AnonymousClass5 extends IBluetoothManagerCallback.Stub {
        @Override // android.bluetooth.IBluetoothManagerCallback
        public void onBrEdrDown() {
        }

        AnonymousClass5() {
        }

        @Override // android.bluetooth.IBluetoothManagerCallback
        public void onBluetoothServiceUp(IBluetooth iBluetooth) {
            Log.d(BluetoothAdapter.TAG, "onBluetoothServiceUp: " + iBluetooth);
            BluetoothAdapter.this.mServiceLock.writeLock().lock();
            BluetoothAdapter.this.mService = iBluetooth;
            BluetoothAdapter.this.mServiceLock.writeLock().unlock();
            synchronized (BluetoothAdapter.this.mProxyServiceStateCallbacks) {
                for (IBluetoothManagerCallback iBluetoothManagerCallback : BluetoothAdapter.this.mProxyServiceStateCallbacks) {
                    if (iBluetoothManagerCallback != null) {
                        try {
                            iBluetoothManagerCallback.onBluetoothServiceUp(iBluetooth);
                        } catch (Exception e) {
                            Log.e(BluetoothAdapter.TAG, "", e);
                        }
                    } else {
                        Log.d(BluetoothAdapter.TAG, "onBluetoothServiceUp: cb is null!");
                    }
                }
            }
            synchronized (BluetoothAdapter.sMetadataListeners) {
                BluetoothAdapter.sMetadataListeners.forEach(new BiConsumer() { // from class: android.bluetooth.-$$Lambda$BluetoothAdapter$5$eKI2JS6EbiGZOGfQ8La27pm0gy0
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        this.f$0.lambda$onBluetoothServiceUp$0$BluetoothAdapter$5((BluetoothDevice) obj, (List) obj2);
                    }
                });
            }
        }

        public /* synthetic */ void lambda$onBluetoothServiceUp$0$BluetoothAdapter$5(BluetoothDevice bluetoothDevice, List list) {
            try {
                BluetoothAdapter.this.mService.registerMetadataListener(BluetoothAdapter.sBluetoothMetadataListener, bluetoothDevice);
            } catch (RemoteException e) {
                Log.e(BluetoothAdapter.TAG, "Failed to register metadata listener", e);
            }
        }

        @Override // android.bluetooth.IBluetoothManagerCallback
        public void onBluetoothServiceDown() {
            Log.d(BluetoothAdapter.TAG, "onBluetoothServiceDown: " + BluetoothAdapter.this.mService);
            try {
                BluetoothAdapter.this.mServiceLock.writeLock().lock();
                BluetoothAdapter.this.mService = null;
                if (BluetoothAdapter.this.mLeScanClients != null) {
                    BluetoothAdapter.this.mLeScanClients.clear();
                }
                if (BluetoothAdapter.sBluetoothLeAdvertiser != null) {
                    BluetoothAdapter.sBluetoothLeAdvertiser.cleanup();
                }
                if (BluetoothAdapter.sBluetoothLeScanner != null) {
                    BluetoothAdapter.sBluetoothLeScanner.cleanup();
                }
                BluetoothAdapter.this.mServiceLock.writeLock().unlock();
                synchronized (BluetoothAdapter.this.mProxyServiceStateCallbacks) {
                    for (IBluetoothManagerCallback iBluetoothManagerCallback : BluetoothAdapter.this.mProxyServiceStateCallbacks) {
                        if (iBluetoothManagerCallback != null) {
                            try {
                                iBluetoothManagerCallback.onBluetoothServiceDown();
                            } catch (Exception e) {
                                Log.e(BluetoothAdapter.TAG, "", e);
                            }
                        } else {
                            Log.d(BluetoothAdapter.TAG, "onBluetoothServiceDown: cb is null!");
                        }
                    }
                }
            } catch (Throwable th) {
                BluetoothAdapter.this.mServiceLock.writeLock().unlock();
                throw th;
            }
        }
    }

    @SystemApi
    public boolean enableNoAutoConnect() {
        if (isEnabled()) {
            Log.d(TAG, "enableNoAutoConnect(): BT already enabled!");
            return true;
        }
        try {
            return this.mManagerService.enableNoAutoConnect(ActivityThread.currentPackageName());
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
            return false;
        }
    }

    public class StateChangeCallbackWrapper extends IBluetoothStateChangeCallback.Stub {
        private BluetoothStateChangeCallback mCallback;

        StateChangeCallbackWrapper(BluetoothStateChangeCallback bluetoothStateChangeCallback) {
            this.mCallback = bluetoothStateChangeCallback;
        }

        @Override // android.bluetooth.IBluetoothStateChangeCallback
        public void onBluetoothStateChange(boolean z) {
            this.mCallback.onBluetoothStateChange(z);
        }
    }

    private Set<BluetoothDevice> toDeviceSet(BluetoothDevice[] bluetoothDeviceArr) {
        return Collections.unmodifiableSet(new HashSet(Arrays.asList(bluetoothDeviceArr)));
    }

    protected void finalize() throws Throwable {
        try {
            try {
                this.mManagerService.unregisterAdapter(this.mManagerCallback);
            } catch (RemoteException e) {
                Log.e(TAG, "", e);
            }
        } finally {
            super.finalize();
        }
    }

    public static boolean checkBluetoothAddress(String str) {
        if (str == null || str.length() != 17) {
            return false;
        }
        for (int i = 0; i < 17; i++) {
            char cCharAt = str.charAt(i);
            int i2 = i % 3;
            if (i2 == 0 || i2 == 1) {
                if ((cCharAt < '0' || cCharAt > '9') && (cCharAt < 'A' || cCharAt > 'F')) {
                    return false;
                }
            } else if (i2 == 2 && cCharAt != ':') {
                return false;
            }
        }
        return true;
    }

    IBluetoothManager getBluetoothManager() {
        return this.mManagerService;
    }

    IBluetooth getBluetoothService(IBluetoothManagerCallback iBluetoothManagerCallback) {
        synchronized (this.mProxyServiceStateCallbacks) {
            if (iBluetoothManagerCallback == null) {
                Log.w(TAG, "getBluetoothService() called with no BluetoothManagerCallback");
            } else if (!this.mProxyServiceStateCallbacks.contains(iBluetoothManagerCallback)) {
                this.mProxyServiceStateCallbacks.add(iBluetoothManagerCallback);
            }
        }
        return this.mService;
    }

    void removeServiceStateCallback(IBluetoothManagerCallback iBluetoothManagerCallback) {
        synchronized (this.mProxyServiceStateCallbacks) {
            this.mProxyServiceStateCallbacks.remove(iBluetoothManagerCallback);
        }
    }

    @Deprecated
    public boolean startLeScan(LeScanCallback leScanCallback) {
        return startLeScan(null, leScanCallback);
    }

    @Deprecated
    public boolean startLeScan(final UUID[] uuidArr, final LeScanCallback leScanCallback) {
        Log.d(TAG, "startLeScan(): " + Arrays.toString(uuidArr));
        if (leScanCallback == null) {
            Log.e(TAG, "startLeScan: null callback");
            return false;
        }
        BluetoothLeScanner bluetoothLeScanner = getBluetoothLeScanner();
        if (bluetoothLeScanner == null) {
            Log.e(TAG, "startLeScan: cannot get BluetoothLeScanner");
            return false;
        }
        synchronized (this.mLeScanClients) {
            if (this.mLeScanClients.containsKey(leScanCallback)) {
                Log.e(TAG, "LE Scan has already started");
                return false;
            }
            try {
                if (this.mManagerService.getBluetoothGatt() == null) {
                    return false;
                }
                ScanCallback scanCallback = new ScanCallback() { // from class: android.bluetooth.BluetoothAdapter.6
                    @Override // android.bluetooth.le.ScanCallback
                    public void onScanResult(int i, ScanResult scanResult) {
                        if (i != 1) {
                            Log.e(BluetoothAdapter.TAG, "LE Scan has already started");
                            return;
                        }
                        ScanRecord scanRecord = scanResult.getScanRecord();
                        if (scanRecord == null) {
                            return;
                        }
                        if (uuidArr != null) {
                            ArrayList arrayList = new ArrayList();
                            for (UUID uuid : uuidArr) {
                                arrayList.add(new ParcelUuid(uuid));
                            }
                            List<ParcelUuid> serviceUuids = scanRecord.getServiceUuids();
                            if (serviceUuids == null || !serviceUuids.containsAll(arrayList)) {
                                Log.d(BluetoothAdapter.TAG, "uuids does not match");
                                return;
                            }
                        }
                        leScanCallback.onLeScan(scanResult.getDevice(), scanResult.getRssi(), scanRecord.getBytes());
                    }
                };
                ScanSettings scanSettingsBuild = new ScanSettings.Builder().setCallbackType(1).setScanMode(2).build();
                ArrayList arrayList = new ArrayList();
                if (uuidArr != null && uuidArr.length > 0) {
                    arrayList.add(new ScanFilter.Builder().setServiceUuid(new ParcelUuid(uuidArr[0])).build());
                }
                bluetoothLeScanner.startScan(arrayList, scanSettingsBuild, scanCallback);
                this.mLeScanClients.put(leScanCallback, scanCallback);
                return true;
            } catch (RemoteException e) {
                Log.e(TAG, "", e);
                return false;
            }
        }
    }

    @Deprecated
    public void stopLeScan(LeScanCallback leScanCallback) {
        Log.d(TAG, "stopLeScan()");
        BluetoothLeScanner bluetoothLeScanner = getBluetoothLeScanner();
        if (bluetoothLeScanner == null) {
            return;
        }
        synchronized (this.mLeScanClients) {
            ScanCallback scanCallbackRemove = this.mLeScanClients.remove(leScanCallback);
            if (scanCallbackRemove == null) {
                Log.d(TAG, "scan not started yet");
            } else {
                bluetoothLeScanner.stopScan(scanCallbackRemove);
            }
        }
    }

    public BluetoothServerSocket listenUsingL2capChannel() throws IOException {
        BluetoothServerSocket bluetoothServerSocket = new BluetoothServerSocket(4, true, true, -2, false, false);
        int iBindListen = bluetoothServerSocket.mSocket.bindListen();
        if (iBindListen != 0) {
            throw new IOException("Error: " + iBindListen);
        }
        int port = bluetoothServerSocket.mSocket.getPort();
        if (port == 0) {
            throw new IOException("Error: Unable to assign PSM value");
        }
        Log.d(TAG, "listenUsingL2capChannel: set assigned PSM to " + port);
        bluetoothServerSocket.setChannel(port);
        return bluetoothServerSocket;
    }

    public BluetoothServerSocket listenUsingInsecureL2capChannel() throws IOException {
        BluetoothServerSocket bluetoothServerSocket = new BluetoothServerSocket(4, false, false, -2, false, false);
        int iBindListen = bluetoothServerSocket.mSocket.bindListen();
        if (iBindListen != 0) {
            throw new IOException("Error: " + iBindListen);
        }
        int port = bluetoothServerSocket.mSocket.getPort();
        if (port == 0) {
            throw new IOException("Error: Unable to assign PSM value");
        }
        Log.d(TAG, "listenUsingInsecureL2capChannel: set assigned PSM to " + port);
        bluetoothServerSocket.setChannel(port);
        return bluetoothServerSocket;
    }

    @SystemApi
    public boolean addOnMetadataChangedListener(BluetoothDevice bluetoothDevice, Executor executor, final OnMetadataChangedListener onMetadataChangedListener) {
        Map<BluetoothDevice, List<Pair<OnMetadataChangedListener, Executor>>> map;
        Log.d(TAG, "addOnMetadataChangedListener()");
        IBluetooth iBluetooth = this.mService;
        boolean zRegisterMetadataListener = false;
        if (iBluetooth == null) {
            Log.e(TAG, "Bluetooth is not enabled. Cannot register metadata listener");
            return false;
        }
        if (onMetadataChangedListener == null) {
            throw new NullPointerException("listener is null");
        }
        if (bluetoothDevice == null) {
            throw new NullPointerException("device is null");
        }
        if (executor == null) {
            throw new NullPointerException("executor is null");
        }
        synchronized (sMetadataListeners) {
            List<Pair<OnMetadataChangedListener, Executor>> arrayList = sMetadataListeners.get(bluetoothDevice);
            if (arrayList == null) {
                arrayList = new ArrayList<>();
                sMetadataListeners.put(bluetoothDevice, arrayList);
            } else if (arrayList.stream().anyMatch(new Predicate() { // from class: android.bluetooth.-$$Lambda$BluetoothAdapter$3qDRCAtPJRu3UbUEFsHnCxkioak
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ((BluetoothAdapter.OnMetadataChangedListener) ((Pair) obj).first).equals(onMetadataChangedListener);
                }
            })) {
                throw new IllegalArgumentException("listener was already regestered for the device");
            }
            Pair<OnMetadataChangedListener, Executor> pair = new Pair<>(onMetadataChangedListener, executor);
            arrayList.add(pair);
            try {
                try {
                    zRegisterMetadataListener = iBluetooth.registerMetadataListener(sBluetoothMetadataListener, bluetoothDevice);
                } catch (RemoteException e) {
                    Log.e(TAG, "registerMetadataListener fail", e);
                    arrayList.remove(pair);
                    if (arrayList.isEmpty()) {
                        map = sMetadataListeners;
                    }
                    return zRegisterMetadataListener;
                }
                if (!zRegisterMetadataListener) {
                    arrayList.remove(pair);
                    if (arrayList.isEmpty()) {
                        map = sMetadataListeners;
                        map.remove(bluetoothDevice);
                    }
                }
            } catch (Throwable th) {
                arrayList.remove(pair);
                if (arrayList.isEmpty()) {
                    sMetadataListeners.remove(bluetoothDevice);
                }
                throw th;
            }
        }
        return zRegisterMetadataListener;
    }

    @SystemApi
    public boolean removeOnMetadataChangedListener(BluetoothDevice bluetoothDevice, final OnMetadataChangedListener onMetadataChangedListener) {
        Log.d(TAG, "removeOnMetadataChangedListener()");
        if (bluetoothDevice == null) {
            throw new NullPointerException("device is null");
        }
        if (onMetadataChangedListener == null) {
            throw new NullPointerException("listener is null");
        }
        synchronized (sMetadataListeners) {
            if (!sMetadataListeners.containsKey(bluetoothDevice)) {
                throw new IllegalArgumentException("device was not registered");
            }
            sMetadataListeners.get(bluetoothDevice).removeIf(new Predicate() { // from class: android.bluetooth.-$$Lambda$BluetoothAdapter$dhiyWTssvWZcLytIeq61ARYDHrc
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ((BluetoothAdapter.OnMetadataChangedListener) ((Pair) obj).first).equals(onMetadataChangedListener);
                }
            });
            if (!sMetadataListeners.get(bluetoothDevice).isEmpty()) {
                return true;
            }
            sMetadataListeners.remove(bluetoothDevice);
            IBluetooth iBluetooth = this.mService;
            if (iBluetooth == null) {
                return true;
            }
            try {
                return iBluetooth.unregisterMetadataListener(bluetoothDevice);
            } catch (RemoteException e) {
                Log.e(TAG, "unregisterMetadataListener fail", e);
                return false;
            }
        }
    }

    public static int priorityToConnectionPolicy(int i) {
        if (i == -1) {
            return -1;
        }
        if (i == 0) {
            return 0;
        }
        if (i == 100 || i == 1000) {
            return 100;
        }
        Log.e(TAG, "setPriority: Invalid priority: " + i);
        return -1;
    }

    public boolean removeAcl(BluetoothDevice bluetoothDevice, int i) {
        try {
            return this.mManagerService.removeAcl(bluetoothDevice, i);
        } catch (RemoteException e) {
            Log.e(TAG, "removeAcl fail", e);
            return false;
        }
    }
}
