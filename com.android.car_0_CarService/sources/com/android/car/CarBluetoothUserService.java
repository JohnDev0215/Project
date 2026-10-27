package com.android.car;

import android.bluetooth.BluetoothA2dpSink;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadsetClient;
import android.bluetooth.BluetoothMapClient;
import android.bluetooth.BluetoothPan;
import android.bluetooth.BluetoothPbapClient;
import android.bluetooth.BluetoothProfile;
import android.car.ICarBluetoothUserService;
import android.content.Context;
import android.util.Log;
import android.util.SparseBooleanArray;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes3.dex */
public class CarBluetoothUserService extends ICarBluetoothUserService.Stub {
    private static final boolean DBG = true;
    private static final int PROXY_OPERATION_TIMEOUT_MS = 3500;
    private static final String TAG = "CarBluetoothUserService";
    private static final List<Integer> sProfilesToConnect = Arrays.asList(16, 17, 11, 18, 5);
    private final BluetoothAdapter mBluetoothAdapter;
    private final ReentrantLock mBluetoothProxyLock;
    private final Condition mConditionAllProxiesConnected;
    private final PerUserCarService mService;
    private BluetoothA2dpSink mBluetoothA2dpSink = null;
    private BluetoothHeadsetClient mBluetoothHeadsetClient = null;
    private BluetoothPbapClient mBluetoothPbapClient = null;
    private BluetoothMapClient mBluetoothMapClient = null;
    private BluetoothPan mBluetoothPan = null;
    private BluetoothProfile.ServiceListener mProfileListener = new BluetoothProfile.ServiceListener() { // from class: com.android.car.CarBluetoothUserService.1
        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceConnected(int profile, BluetoothProfile proxy) {
            CarBluetoothUserService.this.logd("onServiceConnected profile: " + Utils.getProfileName(profile));
            CarBluetoothUserService.this.mBluetoothProxyLock.lock();
            try {
                if (profile == 5) {
                    CarBluetoothUserService.this.mBluetoothPan = (BluetoothPan) proxy;
                } else if (profile != 11) {
                    switch (profile) {
                        case 16:
                            CarBluetoothUserService.this.mBluetoothHeadsetClient = (BluetoothHeadsetClient) proxy;
                            break;
                        case 17:
                            CarBluetoothUserService.this.mBluetoothPbapClient = (BluetoothPbapClient) proxy;
                            break;
                        case 18:
                            CarBluetoothUserService.this.mBluetoothMapClient = (BluetoothMapClient) proxy;
                            break;
                        default:
                            CarBluetoothUserService.this.logd("Unhandled profile connected: " + Utils.getProfileName(profile));
                            break;
                    }
                } else {
                    CarBluetoothUserService.this.mBluetoothA2dpSink = (BluetoothA2dpSink) proxy;
                }
                if (!CarBluetoothUserService.this.mBluetoothProfileStatus.get(profile, false)) {
                    CarBluetoothUserService.this.mBluetoothProfileStatus.put(profile, true);
                    CarBluetoothUserService.access$808(CarBluetoothUserService.this);
                    if (!CarBluetoothUserService.this.isPanSupported(CarBluetoothUserService.this.mService.getApplicationContext())) {
                        if (CarBluetoothUserService.this.mConnectedProfiles == CarBluetoothUserService.sProfilesToConnect.size() - 1) {
                            CarBluetoothUserService.this.logd("All profiles have connected");
                            CarBluetoothUserService.this.mConditionAllProxiesConnected.signal();
                        }
                    } else if (CarBluetoothUserService.this.mConnectedProfiles == CarBluetoothUserService.sProfilesToConnect.size()) {
                        CarBluetoothUserService.this.logd("All profiles have connected");
                        CarBluetoothUserService.this.mConditionAllProxiesConnected.signal();
                    }
                } else {
                    Log.w(CarBluetoothUserService.TAG, "Received duplicate service connection event for: " + Utils.getProfileName(profile));
                }
            } finally {
                CarBluetoothUserService.this.mBluetoothProxyLock.unlock();
            }
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceDisconnected(int profile) {
            CarBluetoothUserService.this.logd("onServiceDisconnected profile: " + Utils.getProfileName(profile));
            CarBluetoothUserService.this.mBluetoothProxyLock.lock();
            try {
                if (CarBluetoothUserService.this.mBluetoothProfileStatus.get(profile, false)) {
                    CarBluetoothUserService.this.mBluetoothProfileStatus.put(profile, false);
                    CarBluetoothUserService.access$810(CarBluetoothUserService.this);
                } else {
                    Log.w(CarBluetoothUserService.TAG, "Received duplicate service disconnection event for: " + Utils.getProfileName(profile));
                }
            } finally {
                CarBluetoothUserService.this.mBluetoothProxyLock.unlock();
            }
        }
    };
    private int mConnectedProfiles = 0;
    private SparseBooleanArray mBluetoothProfileStatus = new SparseBooleanArray();

    static /* synthetic */ int access$808(CarBluetoothUserService x0) {
        int i = x0.mConnectedProfiles;
        x0.mConnectedProfiles = i + 1;
        return i;
    }

    static /* synthetic */ int access$810(CarBluetoothUserService x0) {
        int i = x0.mConnectedProfiles;
        x0.mConnectedProfiles = i - 1;
        return i;
    }

    public CarBluetoothUserService(PerUserCarService service) {
        this.mService = service;
        Iterator<Integer> it = sProfilesToConnect.iterator();
        while (it.hasNext()) {
            int profile = it.next().intValue();
            this.mBluetoothProfileStatus.put(profile, false);
        }
        ReentrantLock reentrantLock = new ReentrantLock();
        this.mBluetoothProxyLock = reentrantLock;
        this.mConditionAllProxiesConnected = reentrantLock.newCondition();
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        this.mBluetoothAdapter = defaultAdapter;
        Objects.requireNonNull(defaultAdapter, "Bluetooth adapter cannot be null");
    }

    public void setupBluetoothConnectionProxies() {
        logd("Initiate connections to profile proxies");
        closeBluetoothConnectionProxies();
        Iterator<Integer> it = sProfilesToConnect.iterator();
        while (it.hasNext()) {
            int profile = it.next().intValue();
            logd("Creating proxy for " + Utils.getProfileName(profile));
            this.mBluetoothAdapter.getProfileProxy(this.mService.getApplicationContext(), this.mProfileListener, profile);
        }
    }

    public void closeBluetoothConnectionProxies() {
        logd("Clean up profile proxy objects");
        this.mBluetoothProxyLock.lock();
        try {
            this.mBluetoothAdapter.closeProfileProxy(11, this.mBluetoothA2dpSink);
            this.mBluetoothA2dpSink = null;
            this.mBluetoothProfileStatus.put(11, false);
            this.mBluetoothAdapter.closeProfileProxy(16, this.mBluetoothHeadsetClient);
            this.mBluetoothHeadsetClient = null;
            this.mBluetoothProfileStatus.put(16, false);
            this.mBluetoothAdapter.closeProfileProxy(17, this.mBluetoothPbapClient);
            this.mBluetoothPbapClient = null;
            this.mBluetoothProfileStatus.put(17, false);
            this.mBluetoothAdapter.closeProfileProxy(18, this.mBluetoothMapClient);
            this.mBluetoothMapClient = null;
            this.mBluetoothProfileStatus.put(18, false);
            this.mBluetoothAdapter.closeProfileProxy(5, this.mBluetoothPan);
            this.mBluetoothPan = null;
            this.mBluetoothProfileStatus.put(5, false);
            this.mConnectedProfiles = 0;
        } finally {
            this.mBluetoothProxyLock.unlock();
        }
    }

    public boolean isBluetoothConnectionProxyAvailable(int profile) {
        if (!this.mBluetoothAdapter.isEnabled()) {
            return false;
        }
        this.mBluetoothProxyLock.lock();
        try {
            boolean proxyConnected = this.mBluetoothProfileStatus.get(profile, false);
            return proxyConnected;
        } finally {
            this.mBluetoothProxyLock.unlock();
        }
    }

    private boolean waitForProxies(int timeout) {
        int needConnectedProfiles = sProfilesToConnect.size();
        logd("waitForProxies()");
        if (!this.mBluetoothAdapter.isEnabled()) {
            return false;
        }
        if (!isPanSupported(this.mService.getApplicationContext())) {
            needConnectedProfiles = sProfilesToConnect.size() - 1;
            logd("No need wait PAN profile");
        }
        while (this.mConnectedProfiles != needConnectedProfiles) {
            try {
                if (!this.mConditionAllProxiesConnected.await(timeout, TimeUnit.MILLISECONDS)) {
                    Log.e(TAG, "Timeout while waiting for proxies, Connected: " + this.mConnectedProfiles + "/" + needConnectedProfiles);
                    return false;
                }
                logd("waitForProxies() while finish.");
            } catch (InterruptedException e) {
                Log.w(TAG, "waitForProxies: interrupted", e);
                return false;
            }
        }
        return true;
    }

    public boolean bluetoothConnectToProfile(int profile, BluetoothDevice device) {
        if (device == null) {
            Log.e(TAG, "Cannot connect to profile on null device");
            return false;
        }
        Log.w(TAG, "Trying to connect to " + device.getName() + " (" + device.getAddressOnlyforLog() + ") Profile: " + Utils.getProfileName(profile));
        this.mBluetoothProxyLock.lock();
        try {
            if (!isBluetoothConnectionProxyAvailable(profile) && !waitForProxies(PROXY_OPERATION_TIMEOUT_MS)) {
                Log.e(TAG, "Cannot connect to Profile. Proxy Unavailable");
                return false;
            }
            if (profile == 5) {
                if (this.mBluetoothPan != null) {
                    return this.mBluetoothPan.connect(device);
                }
                Log.e(TAG, "Cannot connect to Profile. mBluetoothPan == null");
                return false;
            }
            if (profile == 11) {
                if (this.mBluetoothA2dpSink != null) {
                    return this.mBluetoothA2dpSink.connect(device);
                }
                Log.e(TAG, "Cannot connect to Profile. A2dpSink == null");
                return false;
            }
            switch (profile) {
                case 16:
                    if (this.mBluetoothHeadsetClient != null) {
                        return this.mBluetoothHeadsetClient.connect(device);
                    }
                    Log.e(TAG, "Cannot connect to Profile. HeadsetClient == null");
                    return false;
                case 17:
                    if (this.mBluetoothPbapClient != null) {
                        return this.mBluetoothPbapClient.connect(device);
                    }
                    Log.e(TAG, "Cannot connect to Profile. PbapClient == null");
                    return false;
                case 18:
                    if (this.mBluetoothMapClient != null) {
                        return this.mBluetoothMapClient.connect(device);
                    }
                    Log.e(TAG, "Cannot connect to Profile. MapClient == null");
                    return false;
                default:
                    Log.w(TAG, "Unknown Profile: " + Utils.getProfileName(profile));
                    return false;
            }
        } finally {
            this.mBluetoothProxyLock.unlock();
        }
    }

    public boolean bluetoothDisconnectFromProfile(int profile, BluetoothDevice device) {
        if (device == null) {
            Log.e(TAG, "Cannot disconnect from profile on null device");
            return false;
        }
        logd("Try to disconnect from " + device.getName() + " (" + device.getAddressOnlyforLog() + ") Profile: " + Utils.getProfileName(profile));
        this.mBluetoothProxyLock.lock();
        try {
            if (!isBluetoothConnectionProxyAvailable(profile) && !waitForProxies(PROXY_OPERATION_TIMEOUT_MS)) {
                Log.e(TAG, "Cannot disconnect from profile. Proxy Unavailable");
                return false;
            }
            if (profile == 5) {
                if (this.mBluetoothPan != null) {
                    return this.mBluetoothPan.disconnect(device);
                }
                Log.e(TAG, "Cannot disconnect from profile. mBluetoothPan == null");
                return false;
            }
            if (profile == 11) {
                if (this.mBluetoothA2dpSink != null) {
                    return this.mBluetoothA2dpSink.disconnect(device);
                }
                Log.e(TAG, "Cannot disconnect from profile. A2dpSink == null");
                return false;
            }
            switch (profile) {
                case 16:
                    if (this.mBluetoothHeadsetClient != null) {
                        return this.mBluetoothHeadsetClient.disconnect(device);
                    }
                    Log.e(TAG, "Cannot disconnect from profile. HeadsetClient == null");
                    return false;
                case 17:
                    if (this.mBluetoothPbapClient != null) {
                        return this.mBluetoothPbapClient.disconnect(device);
                    }
                    Log.e(TAG, "Cannot disconnect from profile. PbapClient == null");
                    return false;
                case 18:
                    if (this.mBluetoothMapClient != null) {
                        return this.mBluetoothMapClient.disconnect(device);
                    }
                    Log.e(TAG, "Cannot disconnect from profile. MapClient == null");
                    return false;
                default:
                    Log.w(TAG, "Unknown Profile: " + Utils.getProfileName(profile));
                    return false;
            }
        } finally {
            this.mBluetoothProxyLock.unlock();
        }
    }

    public int getProfilePriority(int profile, BluetoothDevice device) {
        int priority;
        if (device == null) {
            Log.e(TAG, "Cannot get " + Utils.getProfileName(profile) + " profile priority on null device");
            return -1;
        }
        this.mBluetoothProxyLock.lock();
        try {
            if (!isBluetoothConnectionProxyAvailable(profile) && !waitForProxies(PROXY_OPERATION_TIMEOUT_MS)) {
                Log.e(TAG, "Cannot get " + Utils.getProfileName(profile) + " profile priority. Proxy Unavailable");
                return -1;
            }
            if (profile == 11) {
                if (this.mBluetoothA2dpSink == null) {
                    Log.e(TAG, "getProfilePriority A2dpSink == null");
                    priority = -1;
                } else {
                    priority = this.mBluetoothA2dpSink.getPriority(device);
                }
            } else {
                switch (profile) {
                    case 16:
                        if (this.mBluetoothHeadsetClient == null) {
                            Log.e(TAG, "getProfilePriority HeadsetClient == null");
                            priority = -1;
                        } else {
                            priority = this.mBluetoothHeadsetClient.getPriority(device);
                        }
                        break;
                    case 17:
                        if (this.mBluetoothPbapClient == null) {
                            Log.e(TAG, "getProfilePriority PbapClient == null");
                            priority = -1;
                        } else {
                            priority = this.mBluetoothPbapClient.getPriority(device);
                        }
                        break;
                    case 18:
                        if (this.mBluetoothMapClient == null) {
                            Log.e(TAG, "getProfilePriority MapClient == null");
                            priority = -1;
                        } else {
                            priority = this.mBluetoothMapClient.getPriority(device);
                        }
                        break;
                    default:
                        Log.w(TAG, "Unknown Profile: " + Utils.getProfileName(profile));
                        priority = -1;
                        break;
                }
            }
            this.mBluetoothProxyLock.unlock();
            logd(Utils.getProfileName(profile) + " priority for " + device.getName() + " (" + device.getAddressOnlyforLog() + ") = " + priority);
            return priority;
        } finally {
            this.mBluetoothProxyLock.unlock();
        }
    }

    public void setProfilePriority(int profile, BluetoothDevice device, int priority) {
        if (device == null) {
            Log.e(TAG, "Cannot set " + Utils.getProfileName(profile) + " profile priority on null device");
            return;
        }
        logd("Setting " + Utils.getProfileName(profile) + " priority for " + device.getName() + " (" + device.getAddressOnlyforLog() + ") to " + priority);
        this.mBluetoothProxyLock.lock();
        try {
            if (!isBluetoothConnectionProxyAvailable(profile) && !waitForProxies(PROXY_OPERATION_TIMEOUT_MS)) {
                Log.e(TAG, "Cannot set " + Utils.getProfileName(profile) + " profile priority. Proxy Unavailable");
                return;
            }
            if (profile == 11) {
                if (this.mBluetoothA2dpSink != null) {
                    this.mBluetoothA2dpSink.setPriority(device, priority);
                }
            } else {
                switch (profile) {
                    case 16:
                        if (this.mBluetoothHeadsetClient != null) {
                            this.mBluetoothHeadsetClient.setPriority(device, priority);
                        }
                        break;
                    case 17:
                        if (this.mBluetoothPbapClient != null) {
                            this.mBluetoothPbapClient.setPriority(device, priority);
                        }
                        break;
                    case 18:
                        if (this.mBluetoothMapClient != null) {
                            this.mBluetoothMapClient.setPriority(device, priority);
                        }
                        break;
                    default:
                        Log.w(TAG, "Unknown Profile: " + Utils.getProfileName(profile));
                        break;
                }
            }
        } finally {
            this.mBluetoothProxyLock.unlock();
        }
    }

    public void setProfilePriority2(int profile, BluetoothDevice device, int priority) {
        if (device == null) {
            Log.e(TAG, "setProfilePriority2 Cannot set " + Utils.getProfileName(profile) + " profile priority on null device");
            return;
        }
        logd("setProfilePriority2 Setting " + Utils.getProfileName(profile) + " priority for " + device.getName() + " (" + device.getAddressOnlyforLog() + ") to " + priority);
        this.mBluetoothProxyLock.lock();
        try {
            if (!isBluetoothConnectionProxyAvailable(profile)) {
                Log.e(TAG, "setProfilePriority2 Cannot set " + Utils.getProfileName(profile) + " profile priority. Proxy Unavailable");
                return;
            }
            if (profile != 11) {
                if (profile == 16) {
                    if (this.mBluetoothHeadsetClient != null) {
                        this.mBluetoothHeadsetClient.setPriority2(device, priority);
                    }
                } else {
                    Log.w(TAG, "setProfilePriority2 Unknown Profile: " + Utils.getProfileName(profile));
                }
            } else if (this.mBluetoothA2dpSink != null) {
                this.mBluetoothA2dpSink.setPriority2(device, priority);
            }
        } finally {
            this.mBluetoothProxyLock.unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isPanSupported(Context context) {
        boolean enablePan = true;
        if (context != null) {
            enablePan = context.getResources().getBoolean(R.bool.enableBluetoothPan);
        }
        logd("IsPANSupport: " + enablePan);
        return enablePan;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logd(String msg) {
        Log.i(TAG, msg);
    }
}
