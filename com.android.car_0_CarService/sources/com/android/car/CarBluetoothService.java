package com.android.car;

import android.app.ActivityManager;
import android.bluetooth.BluetoothDevice;
import android.car.ICarBluetooth;
import android.car.ICarBluetoothUserService;
import android.car.IPerUserCarService;
import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.util.SparseArray;
import com.android.car.PerUserCarServiceHelper;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class CarBluetoothService extends ICarBluetooth.Stub implements CarServiceBase {
    private static final boolean DBG = true;
    private static final String TAG = "CarBluetoothService";
    static final String THREAD_NAME = "CarBluetoothService";
    private static final List<Integer> sManagedProfiles = Arrays.asList(16, 17, 11, 18, 5);
    private ICarBluetoothUserService mCarBluetoothUserService;
    private final Context mContext;
    private IPerUserCarService mPerUserCarService;
    private final boolean mUseDefaultPolicy;
    private final PerUserCarServiceHelper mUserServiceHelper;
    private final Object mPerUserLock = new Object();
    private final SparseArray<BluetoothProfileDeviceManager> mProfileDeviceManagers = new SparseArray<>();
    private BluetoothProfileInhibitManager mInhibitManager = null;
    private BluetoothDeviceConnectionPolicy mBluetoothDeviceConnectionPolicy = null;
    private final PerUserCarServiceHelper.ServiceCallback mUserServiceCallback = new PerUserCarServiceHelper.ServiceCallback() { // from class: com.android.car.CarBluetoothService.1
        @Override // com.android.car.PerUserCarServiceHelper.ServiceCallback
        public void onServiceConnected(IPerUserCarService perUserCarService) {
            CarBluetoothService.logd("Connected to PerUserCarService");
            synchronized (CarBluetoothService.this.mPerUserLock) {
                CarBluetoothService.this.destroyUserLocked();
                CarBluetoothService.this.mPerUserCarService = perUserCarService;
                CarBluetoothService.this.initializeUserLocked();
            }
        }

        @Override // com.android.car.PerUserCarServiceHelper.ServiceCallback
        public void onPreUnbind() {
            CarBluetoothService.logd("Before Unbinding from PerUserCarService");
            synchronized (CarBluetoothService.this.mPerUserLock) {
                CarBluetoothService.this.destroyUserLocked();
            }
        }

        @Override // com.android.car.PerUserCarServiceHelper.ServiceCallback
        public void onServiceDisconnected() {
            CarBluetoothService.logd("Disconnected from PerUserCarService");
            synchronized (CarBluetoothService.this.mPerUserLock) {
                CarBluetoothService.this.destroyUserLocked();
            }
        }
    };
    private int mUserId = -10000;

    public CarBluetoothService(Context context, PerUserCarServiceHelper userSwitchService) {
        this.mContext = context;
        this.mUserServiceHelper = userSwitchService;
        this.mUseDefaultPolicy = context.getResources().getBoolean(R.bool.useDefaultBluetoothConnectionPolicy);
    }

    @Override // com.android.car.CarServiceBase
    public void init() {
        logd("init()");
        this.mUserServiceHelper.registerServiceCallback(this.mUserServiceCallback);
    }

    @Override // com.android.car.CarServiceBase
    public void release() {
        logd("release()");
        this.mUserServiceHelper.unregisterServiceCallback(this.mUserServiceCallback);
        synchronized (this.mPerUserLock) {
            destroyUserLocked();
        }
    }

    public static List<Integer> getManagedProfiles() {
        return sManagedProfiles;
    }

    private boolean isPanSupported(Context context) {
        boolean enablePan = true;
        if (context != null) {
            enablePan = context.getResources().getBoolean(R.bool.enableBluetoothPan);
        }
        logd("IsPANSupport: " + enablePan);
        return enablePan;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initializeUserLocked() {
        logd("Initializing new user");
        this.mUserId = ActivityManager.getCurrentUser();
        createBluetoothUserServiceLocked();
        createBluetoothProfileDeviceManagersLocked();
        createBluetoothProfileInhibitManagerLocked();
        this.mBluetoothDeviceConnectionPolicy = null;
        if (this.mUseDefaultPolicy) {
            createBluetoothDeviceConnectionPolicyLocked();
        }
        logd("Switched to user " + this.mUserId);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void destroyUserLocked() {
        logd("Destroying user " + this.mUserId);
        destroyBluetoothDeviceConnectionPolicyLocked();
        destroyBluetoothProfileInhibitManagerLocked();
        destroyBluetoothProfileDeviceManagersLocked();
        destroyBluetoothUserServiceLocked();
        this.mPerUserCarService = null;
        this.mUserId = -10000;
    }

    private void createBluetoothUserServiceLocked() {
        IPerUserCarService iPerUserCarService = this.mPerUserCarService;
        if (iPerUserCarService != null) {
            try {
                ICarBluetoothUserService bluetoothUserService = iPerUserCarService.getBluetoothUserService();
                this.mCarBluetoothUserService = bluetoothUserService;
                bluetoothUserService.setupBluetoothConnectionProxies();
                return;
            } catch (RemoteException e) {
                Log.e("CarBluetoothService", "Remote Service Exception on ServiceConnection Callback: " + e.getMessage());
                return;
            } catch (NullPointerException e2) {
                Log.e("CarBluetoothService", "Initialization Failed: " + e2.getMessage());
                return;
            }
        }
        logd("PerUserCarService not connected. Cannot get bluetooth user proxy objects");
    }

    private void destroyBluetoothUserServiceLocked() {
        ICarBluetoothUserService iCarBluetoothUserService = this.mCarBluetoothUserService;
        if (iCarBluetoothUserService == null) {
            return;
        }
        try {
            iCarBluetoothUserService.closeBluetoothConnectionProxies();
        } catch (RemoteException e) {
            Log.e("CarBluetoothService", "Remote Service Exception on ServiceConnection Callback: " + e.getMessage());
        }
        this.mCarBluetoothUserService = null;
    }

    private void createBluetoothProfileDeviceManagersLocked() {
        boolean enablePan = isPanSupported(this.mContext);
        if (this.mUserId == -10000) {
            logd("No foreground user, cannot create profile device managers");
            return;
        }
        Iterator<Integer> it = sManagedProfiles.iterator();
        while (it.hasNext()) {
            int profileId = it.next().intValue();
            if (profileId == 5 && !enablePan) {
                logd("Bluetooth PAN is not supported");
            } else {
                BluetoothProfileDeviceManager deviceManager = this.mProfileDeviceManagers.get(profileId);
                if (deviceManager != null) {
                    deviceManager.stop();
                    this.mProfileDeviceManagers.remove(profileId);
                    logd("Existing device manager removed for profile " + Utils.getProfileName(profileId));
                }
                BluetoothProfileDeviceManager deviceManager2 = BluetoothProfileDeviceManager.create(this.mContext, this.mUserId, this.mCarBluetoothUserService, profileId);
                if (deviceManager2 == null) {
                    logd("Failed to create profile device manager for " + Utils.getProfileName(profileId));
                } else {
                    this.mProfileDeviceManagers.put(profileId, deviceManager2);
                    logd("Created profile device manager for " + Utils.getProfileName(profileId));
                }
            }
        }
        for (int i = 0; i < this.mProfileDeviceManagers.size(); i++) {
            int key = this.mProfileDeviceManagers.keyAt(i);
            this.mProfileDeviceManagers.get(key).start();
        }
    }

    private void destroyBluetoothProfileDeviceManagersLocked() {
        for (int i = 0; i < this.mProfileDeviceManagers.size(); i++) {
            int key = this.mProfileDeviceManagers.keyAt(i);
            BluetoothProfileDeviceManager deviceManager = this.mProfileDeviceManagers.get(key);
            deviceManager.stop();
        }
        this.mProfileDeviceManagers.clear();
    }

    private void createBluetoothProfileInhibitManagerLocked() {
        logd("Creating inhibit manager");
        if (this.mUserId == -10000) {
            logd("No foreground user, cannot create profile inhibit manager");
            return;
        }
        BluetoothProfileInhibitManager bluetoothProfileInhibitManager = new BluetoothProfileInhibitManager(this.mContext, this.mUserId, this.mCarBluetoothUserService);
        this.mInhibitManager = bluetoothProfileInhibitManager;
        bluetoothProfileInhibitManager.start();
    }

    private void destroyBluetoothProfileInhibitManagerLocked() {
        logd("Destroying inhibit manager");
        BluetoothProfileInhibitManager bluetoothProfileInhibitManager = this.mInhibitManager;
        if (bluetoothProfileInhibitManager == null) {
            return;
        }
        bluetoothProfileInhibitManager.stop();
        this.mInhibitManager = null;
    }

    private void createBluetoothDeviceConnectionPolicyLocked() {
        logd("Creating device connection policy");
        int i = this.mUserId;
        if (i == -10000) {
            logd("No foreground user, cannot create device connection policy");
            return;
        }
        BluetoothDeviceConnectionPolicy bluetoothDeviceConnectionPolicyCreate = BluetoothDeviceConnectionPolicy.create(this.mContext, i, this);
        this.mBluetoothDeviceConnectionPolicy = bluetoothDeviceConnectionPolicyCreate;
        if (bluetoothDeviceConnectionPolicyCreate == null) {
            logd("Failed to create default Bluetooth device connection policy.");
        } else {
            bluetoothDeviceConnectionPolicyCreate.init();
        }
    }

    private void destroyBluetoothDeviceConnectionPolicyLocked() {
        logd("Destroying device connection policy");
        BluetoothDeviceConnectionPolicy bluetoothDeviceConnectionPolicy = this.mBluetoothDeviceConnectionPolicy;
        if (bluetoothDeviceConnectionPolicy != null) {
            bluetoothDeviceConnectionPolicy.release();
            this.mBluetoothDeviceConnectionPolicy = null;
        }
    }

    public boolean isUsingDefaultConnectionPolicy() {
        boolean z;
        synchronized (this.mPerUserLock) {
            z = this.mBluetoothDeviceConnectionPolicy != null;
        }
        return z;
    }

    public void connectDevices() {
        enforceBluetoothAdminPermission();
        logd("Connect devices for each profile");
        synchronized (this.mPerUserLock) {
            for (int i = 0; i < this.mProfileDeviceManagers.size(); i++) {
                int key = this.mProfileDeviceManagers.keyAt(i);
                BluetoothProfileDeviceManager deviceManager = this.mProfileDeviceManagers.get(key);
                deviceManager.beginAutoConnecting();
            }
        }
    }

    public List<BluetoothDevice> getProfileDevicePriorityList(int profile) {
        enforceBluetoothAdminPermission();
        synchronized (this.mPerUserLock) {
            BluetoothProfileDeviceManager deviceManager = this.mProfileDeviceManagers.get(profile);
            if (deviceManager != null) {
                return deviceManager.getDeviceListSnapshot();
            }
            return new ArrayList();
        }
    }

    public int getDeviceConnectionPriority(int profile, BluetoothDevice device) {
        enforceBluetoothAdminPermission();
        synchronized (this.mPerUserLock) {
            BluetoothProfileDeviceManager deviceManager = this.mProfileDeviceManagers.get(profile);
            if (deviceManager != null) {
                return deviceManager.getDeviceConnectionPriority(device);
            }
            return -1;
        }
    }

    public void setDeviceConnectionPriority(int profile, BluetoothDevice device, int priority) {
        enforceBluetoothAdminPermission();
        synchronized (this.mPerUserLock) {
            BluetoothProfileDeviceManager deviceManager = this.mProfileDeviceManagers.get(profile);
            if (deviceManager != null) {
                deviceManager.setDeviceConnectionPriority(device, priority);
            }
        }
    }

    boolean requestProfileInhibit(BluetoothDevice device, int profile, IBinder token) {
        logd("Request profile inhibit: profile " + Utils.getProfileName(profile) + ", device " + device.getAddressOnlyforLog());
        synchronized (this.mPerUserLock) {
            if (this.mInhibitManager == null) {
                return false;
            }
            return this.mInhibitManager.requestProfileInhibit(device, profile, token);
        }
    }

    boolean releaseProfileInhibit(BluetoothDevice device, int profile, IBinder token) {
        logd("Release profile inhibit: profile " + Utils.getProfileName(profile) + ", device " + device.getAddressOnlyforLog());
        synchronized (this.mPerUserLock) {
            if (this.mInhibitManager == null) {
                return false;
            }
            return this.mInhibitManager.releaseProfileInhibit(device, profile, token);
        }
    }

    private void enforceBluetoothAdminPermission() {
        Context context = this.mContext;
        if (context != null && context.checkCallingOrSelfPermission("android.permission.BLUETOOTH_ADMIN") == 0) {
            return;
        }
        if (this.mContext == null) {
            Log.e("CarBluetoothService", "CarBluetoothPrioritySettings does not have a Context");
        }
        throw new SecurityException("requires permission android.permission.BLUETOOTH_ADMIN");
    }

    @Override // com.android.car.CarServiceBase
    public void dump(PrintWriter writer) {
        writer.println("*CarBluetoothService*");
        synchronized (this.mPerUserLock) {
            writer.println("\tUser ID: " + this.mUserId);
            StringBuilder sb = new StringBuilder();
            sb.append("\tUser Proxies: ");
            sb.append(this.mCarBluetoothUserService != null ? "Yes" : "No");
            writer.println(sb.toString());
            for (int i = 0; i < this.mProfileDeviceManagers.size(); i++) {
                int key = this.mProfileDeviceManagers.keyAt(i);
                BluetoothProfileDeviceManager deviceManager = this.mProfileDeviceManagers.get(key);
                deviceManager.dump(writer, "\t");
            }
            if (this.mInhibitManager != null) {
                this.mInhibitManager.dump(writer, "\t");
            } else {
                writer.println("\tBluetoothProfileInhibitManager: null");
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append("\tUsing default policy? ");
            sb2.append(this.mUseDefaultPolicy ? "Yes" : "No");
            writer.println(sb2.toString());
            if (this.mBluetoothDeviceConnectionPolicy == null) {
                writer.println("\tBluetoothDeviceConnectionPolicy: null");
            } else {
                this.mBluetoothDeviceConnectionPolicy.dump(writer, "\t");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void logd(String msg) {
        Log.i("CarBluetoothService", msg);
    }
}
