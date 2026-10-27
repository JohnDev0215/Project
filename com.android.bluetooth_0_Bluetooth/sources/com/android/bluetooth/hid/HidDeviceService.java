package com.android.bluetooth.hid;

import android.app.ActivityManager;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHidDeviceAppQosSettings;
import android.bluetooth.BluetoothHidDeviceAppSdpSettings;
import android.bluetooth.IBluetoothHidDevice;
import android.bluetooth.IBluetoothHidDeviceCallback;
import android.content.Intent;
import android.net.INetd;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import com.android.bluetooth.BluetoothMetricsProto;
import com.android.bluetooth.Utils;
import com.android.bluetooth.btservice.AdapterService;
import com.android.bluetooth.btservice.MetricsLogger;
import com.android.bluetooth.btservice.ProfileService;
import com.android.vcard.VCardConfig;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public class HidDeviceService extends ProfileService {
    private static final boolean DBG = false;
    private static final int FOREGROUND_IMPORTANCE_CUTOFF = 200;
    static final int HAL_CONN_STATE_CONNECTED = 0;
    static final int HAL_CONN_STATE_CONNECTING = 1;
    static final int HAL_CONN_STATE_DISCONNECTED = 2;
    static final int HAL_CONN_STATE_DISCONNECTING = 3;
    private static final int MESSAGE_APPLICATION_STATE_CHANGED = 1;
    private static final int MESSAGE_CONNECT_STATE_CHANGED = 2;
    private static final int MESSAGE_GET_REPORT = 3;
    private static final int MESSAGE_IMPORTANCE_CHANGE = 8;
    private static final int MESSAGE_INTR_DATA = 6;
    private static final int MESSAGE_SET_PROTOCOL = 5;
    private static final int MESSAGE_SET_REPORT = 4;
    private static final int MESSAGE_VC_UNPLUG = 7;
    private static final String TAG = HidDeviceService.class.getSimpleName();
    private static HidDeviceService sHidDeviceService;
    private ActivityManager mActivityManager;
    private IBluetoothHidDeviceCallback mCallback;
    private BluetoothHidDeviceDeathRecipient mDeathRcpt;
    private HidDeviceServiceHandler mHandler;
    private BluetoothDevice mHidDevice;
    private HidDeviceNativeInterface mHidDeviceNativeInterface;
    private boolean mNativeAvailable = false;
    private int mHidDeviceState = 0;
    private int mUserUid = 0;
    private ActivityManager.OnUidImportanceListener mUidImportanceListener = new ActivityManager.OnUidImportanceListener() { // from class: com.android.bluetooth.hid.HidDeviceService.1
        public void onUidImportance(int uid, int importance) {
            Message message = HidDeviceService.this.mHandler.obtainMessage(8);
            message.arg1 = importance;
            message.arg2 = uid;
            HidDeviceService.this.mHandler.sendMessage(message);
        }
    };

    private class HidDeviceServiceHandler extends Handler {
        private HidDeviceServiceHandler() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    BluetoothDevice device = msg.obj != null ? (BluetoothDevice) msg.obj : null;
                    boolean success = msg.arg1 != 0;
                    if (success) {
                        Log.d(HidDeviceService.TAG, "App registered, set device to: " + device);
                        HidDeviceService.this.mHidDevice = device;
                    } else {
                        HidDeviceService.this.mHidDevice = null;
                    }
                    try {
                        if (HidDeviceService.this.mCallback != null) {
                            HidDeviceService.this.mCallback.onAppStatusChanged(device, success);
                        }
                    } catch (RemoteException e) {
                        Log.e(HidDeviceService.TAG, "e=" + e.toString());
                        e.printStackTrace();
                    }
                    if (!success) {
                        if (HidDeviceService.this.mDeathRcpt != null && HidDeviceService.this.mCallback != null) {
                            IBinder binder = HidDeviceService.this.mCallback.asBinder();
                            try {
                                binder.unlinkToDeath(HidDeviceService.this.mDeathRcpt, 0);
                                Log.i(HidDeviceService.TAG, "IBinder.unlinkToDeath() ok");
                            } catch (NoSuchElementException e2) {
                                e2.printStackTrace();
                            }
                            HidDeviceService.this.mDeathRcpt.cleanup();
                            HidDeviceService.this.mDeathRcpt = null;
                        }
                    } else {
                        HidDeviceService hidDeviceService = HidDeviceService.this;
                        hidDeviceService.mDeathRcpt = new BluetoothHidDeviceDeathRecipient(hidDeviceService);
                        if (HidDeviceService.this.mCallback != null) {
                            IBinder binder2 = HidDeviceService.this.mCallback.asBinder();
                            try {
                                binder2.linkToDeath(HidDeviceService.this.mDeathRcpt, 0);
                                Log.i(HidDeviceService.TAG, "IBinder.linkToDeath() ok");
                            } catch (RemoteException e3) {
                                e3.printStackTrace();
                            }
                        }
                    }
                    if (!success) {
                        HidDeviceService.this.mCallback = null;
                    }
                    break;
                case 2:
                    BluetoothDevice device2 = (BluetoothDevice) msg.obj;
                    int halState = msg.arg1;
                    int state = HidDeviceService.convertHalState(halState);
                    if (state != 0) {
                        HidDeviceService.this.mHidDevice = device2;
                    }
                    HidDeviceService.this.setAndBroadcastConnectionState(device2, state);
                    try {
                        if (HidDeviceService.this.mCallback != null) {
                            HidDeviceService.this.mCallback.onConnectionStateChanged(device2, state);
                        }
                    } catch (RemoteException e4) {
                        e4.printStackTrace();
                        return;
                    }
                    break;
                case 3:
                    byte type = (byte) msg.arg1;
                    byte id = (byte) msg.arg2;
                    int bufferSize = msg.obj != null ? ((Integer) msg.obj).intValue() : 0;
                    try {
                        if (HidDeviceService.this.mCallback != null) {
                            HidDeviceService.this.mCallback.onGetReport(HidDeviceService.this.mHidDevice, type, id, bufferSize);
                        }
                    } catch (RemoteException e5) {
                        e5.printStackTrace();
                        return;
                    }
                    break;
                case 4:
                    byte reportType = (byte) msg.arg1;
                    byte reportId = (byte) msg.arg2;
                    byte[] data = ((ByteBuffer) msg.obj).array();
                    try {
                        if (HidDeviceService.this.mCallback != null) {
                            HidDeviceService.this.mCallback.onSetReport(HidDeviceService.this.mHidDevice, reportType, reportId, data);
                        }
                    } catch (RemoteException e6) {
                        e6.printStackTrace();
                        return;
                    }
                    break;
                case 5:
                    byte protocol = (byte) msg.arg1;
                    try {
                        if (HidDeviceService.this.mCallback != null) {
                            HidDeviceService.this.mCallback.onSetProtocol(HidDeviceService.this.mHidDevice, protocol);
                        }
                    } catch (RemoteException e7) {
                        e7.printStackTrace();
                        return;
                    }
                    break;
                case 6:
                    byte reportId2 = (byte) msg.arg1;
                    byte[] data2 = ((ByteBuffer) msg.obj).array();
                    try {
                        if (HidDeviceService.this.mCallback != null) {
                            HidDeviceService.this.mCallback.onInterruptData(HidDeviceService.this.mHidDevice, reportId2, data2);
                        }
                    } catch (RemoteException e8) {
                        e8.printStackTrace();
                        return;
                    }
                    break;
                case 7:
                    try {
                        if (HidDeviceService.this.mCallback != null) {
                            HidDeviceService.this.mCallback.onVirtualCableUnplug(HidDeviceService.this.mHidDevice);
                        }
                    } catch (RemoteException e9) {
                        e9.printStackTrace();
                    }
                    HidDeviceService.this.mHidDevice = null;
                    break;
                case 8:
                    int importance = msg.arg1;
                    int uid = msg.arg2;
                    if (importance > 200 && uid >= 10000) {
                        HidDeviceService.this.unregisterAppUid(uid);
                        break;
                    }
                    break;
            }
        }
    }

    private static class BluetoothHidDeviceDeathRecipient implements IBinder.DeathRecipient {
        private HidDeviceService mService;

        BluetoothHidDeviceDeathRecipient(HidDeviceService service) {
            this.mService = service;
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            Log.w(HidDeviceService.TAG, "Binder died, need to unregister app :(");
            this.mService.unregisterApp();
        }

        public void cleanup() {
            this.mService = null;
        }
    }

    static class BluetoothHidDeviceBinder extends IBluetoothHidDevice.Stub implements ProfileService.IProfileServiceBinder {
        private static final String TAG = BluetoothHidDeviceBinder.class.getSimpleName();
        private HidDeviceService mService;

        BluetoothHidDeviceBinder(HidDeviceService service) {
            this.mService = service;
        }

        HidDeviceService getServiceForTesting() {
            HidDeviceService hidDeviceService = this.mService;
            if (hidDeviceService != null && hidDeviceService.isAvailable()) {
                return this.mService;
            }
            return null;
        }

        @Override // com.android.bluetooth.btservice.ProfileService.IProfileServiceBinder
        public void cleanup() {
            this.mService = null;
        }

        private HidDeviceService getService() {
            if (!Utils.checkCaller()) {
                Log.w(TAG, "HidDevice call not allowed for non-active user");
                return null;
            }
            HidDeviceService hidDeviceService = this.mService;
            if (hidDeviceService == null || !hidDeviceService.isAvailable()) {
                return null;
            }
            return this.mService;
        }

        public boolean registerApp(BluetoothHidDeviceAppSdpSettings sdp, BluetoothHidDeviceAppQosSettings inQos, BluetoothHidDeviceAppQosSettings outQos, IBluetoothHidDeviceCallback callback) {
            HidDeviceService service = getService();
            if (service == null) {
                return false;
            }
            return service.registerApp(sdp, inQos, outQos, callback);
        }

        public boolean unregisterApp() {
            HidDeviceService service = getService();
            if (service == null) {
                return false;
            }
            return service.unregisterApp();
        }

        public boolean sendReport(BluetoothDevice device, int id, byte[] data) {
            HidDeviceService service = getService();
            if (service == null) {
                return false;
            }
            return service.sendReport(device, id, data);
        }

        public boolean replyReport(BluetoothDevice device, byte type, byte id, byte[] data) {
            HidDeviceService service = getService();
            if (service == null) {
                return false;
            }
            return service.replyReport(device, type, id, data);
        }

        public boolean unplug(BluetoothDevice device) {
            HidDeviceService service = getService();
            if (service == null) {
                return false;
            }
            return service.unplug(device);
        }

        public boolean connect(BluetoothDevice device) {
            HidDeviceService service = getService();
            if (service == null) {
                return false;
            }
            return service.connect(device);
        }

        public boolean disconnect(BluetoothDevice device) {
            HidDeviceService service = getService();
            if (service == null) {
                return false;
            }
            return service.disconnect(device);
        }

        public boolean setConnectionPolicy(BluetoothDevice device, int connectionPolicy) {
            HidDeviceService service = getService();
            if (service == null) {
                return false;
            }
            return service.setConnectionPolicy(device, connectionPolicy);
        }

        public boolean reportError(BluetoothDevice device, byte error) {
            HidDeviceService service = getService();
            if (service == null) {
                return false;
            }
            return service.reportError(device, error);
        }

        public int getConnectionState(BluetoothDevice device) {
            HidDeviceService service = getService();
            if (service == null) {
                return 0;
            }
            return service.getConnectionState(device);
        }

        public List<BluetoothDevice> getConnectedDevices() {
            return getDevicesMatchingConnectionStates(new int[]{2});
        }

        public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] states) {
            HidDeviceService service = getService();
            if (service == null) {
                return new ArrayList(0);
            }
            return service.getDevicesMatchingConnectionStates(states);
        }

        public String getUserAppName() {
            HidDeviceService service = getService();
            if (service == null) {
                return INetd.NEXTHOP_NONE;
            }
            return service.getUserAppName();
        }
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    protected ProfileService.IProfileServiceBinder initBinder() {
        return new BluetoothHidDeviceBinder(this);
    }

    private boolean checkDevice(BluetoothDevice device) {
        BluetoothDevice bluetoothDevice = this.mHidDevice;
        if (bluetoothDevice == null || !bluetoothDevice.equals(device)) {
            Log.w(TAG, "Unknown device: " + device);
            return false;
        }
        return true;
    }

    private boolean checkCallingUid() {
        int callingUid = Binder.getCallingUid();
        if (callingUid != this.mUserUid) {
            Log.w(TAG, "checkCallingUid(): caller UID doesn't match registered user UID");
            return false;
        }
        return true;
    }

    synchronized boolean registerApp(BluetoothHidDeviceAppSdpSettings sdp, BluetoothHidDeviceAppQosSettings inQos, BluetoothHidDeviceAppQosSettings outQos, IBluetoothHidDeviceCallback callback) {
        int[] iArr;
        int[] iArr2;
        enforceCallingOrSelfPermission("android.permission.BLUETOOTH_ADMIN", "Need BLUETOOTH ADMIN permission");
        if (this.mUserUid != 0) {
            Log.w(TAG, "registerApp(): failed because another app is registered");
            return false;
        }
        int callingUid = Binder.getCallingUid();
        if (callingUid >= 10000 && this.mActivityManager.getUidImportance(callingUid) > 200) {
            Log.w(TAG, "registerApp(): failed because the app is not foreground");
            return false;
        }
        this.mUserUid = callingUid;
        this.mCallback = callback;
        HidDeviceNativeInterface hidDeviceNativeInterface = this.mHidDeviceNativeInterface;
        String name = sdp.getName();
        String description = sdp.getDescription();
        String provider = sdp.getProvider();
        byte subclass = sdp.getSubclass();
        byte[] descriptors = sdp.getDescriptors();
        if (inQos == null) {
            iArr = null;
        } else {
            iArr = new int[]{inQos.getServiceType(), inQos.getTokenRate(), inQos.getTokenBucketSize(), inQos.getPeakBandwidth(), inQos.getLatency(), inQos.getDelayVariation()};
        }
        if (outQos == null) {
            iArr2 = null;
        } else {
            iArr2 = new int[]{outQos.getServiceType(), outQos.getTokenRate(), outQos.getTokenBucketSize(), outQos.getPeakBandwidth(), outQos.getLatency(), outQos.getDelayVariation()};
        }
        return hidDeviceNativeInterface.registerApp(name, description, provider, subclass, descriptors, iArr, iArr2);
    }

    synchronized boolean unregisterApp() {
        int callingUid;
        enforceCallingOrSelfPermission("android.permission.BLUETOOTH_ADMIN", "Need BLUETOOTH ADMIN permission");
        callingUid = Binder.getCallingUid();
        return unregisterAppUid(callingUid);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized boolean unregisterAppUid(int uid) {
        if (this.mUserUid == 0 || (uid != this.mUserUid && uid >= 10000)) {
            return false;
        }
        this.mUserUid = 0;
        return this.mHidDeviceNativeInterface.unregisterApp();
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x001e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    synchronized boolean sendReport(android.bluetooth.BluetoothDevice r3, int r4, byte[] r5) {
        /*
            r2 = this;
            monitor-enter(r2)
            java.lang.String r0 = "android.permission.BLUETOOTH_ADMIN"
            java.lang.String r1 = "Need BLUETOOTH ADMIN permission"
            r2.enforceCallingOrSelfPermission(r0, r1)     // Catch: java.lang.Throwable -> L21
            boolean r0 = r2.checkDevice(r3)     // Catch: java.lang.Throwable -> L21
            if (r0 == 0) goto L1e
            boolean r0 = r2.checkCallingUid()     // Catch: java.lang.Throwable -> L21
            if (r0 == 0) goto L1e
            com.android.bluetooth.hid.HidDeviceNativeInterface r0 = r2.mHidDeviceNativeInterface     // Catch: java.lang.Throwable -> L21
            boolean r0 = r0.sendReport(r4, r5)     // Catch: java.lang.Throwable -> L21
            if (r0 == 0) goto L1e
            r0 = 1
            goto L1f
        L1e:
            r0 = 0
        L1f:
            monitor-exit(r2)
            return r0
        L21:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.bluetooth.hid.HidDeviceService.sendReport(android.bluetooth.BluetoothDevice, int, byte[]):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x001e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    synchronized boolean replyReport(android.bluetooth.BluetoothDevice r3, byte r4, byte r5, byte[] r6) {
        /*
            r2 = this;
            monitor-enter(r2)
            java.lang.String r0 = "android.permission.BLUETOOTH_ADMIN"
            java.lang.String r1 = "Need BLUETOOTH ADMIN permission"
            r2.enforceCallingOrSelfPermission(r0, r1)     // Catch: java.lang.Throwable -> L21
            boolean r0 = r2.checkDevice(r3)     // Catch: java.lang.Throwable -> L21
            if (r0 == 0) goto L1e
            boolean r0 = r2.checkCallingUid()     // Catch: java.lang.Throwable -> L21
            if (r0 == 0) goto L1e
            com.android.bluetooth.hid.HidDeviceNativeInterface r0 = r2.mHidDeviceNativeInterface     // Catch: java.lang.Throwable -> L21
            boolean r0 = r0.replyReport(r4, r5, r6)     // Catch: java.lang.Throwable -> L21
            if (r0 == 0) goto L1e
            r0 = 1
            goto L1f
        L1e:
            r0 = 0
        L1f:
            monitor-exit(r2)
            return r0
        L21:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.bluetooth.hid.HidDeviceService.replyReport(android.bluetooth.BluetoothDevice, byte, byte, byte[]):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x001e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    synchronized boolean unplug(android.bluetooth.BluetoothDevice r3) {
        /*
            r2 = this;
            monitor-enter(r2)
            java.lang.String r0 = "android.permission.BLUETOOTH_ADMIN"
            java.lang.String r1 = "Need BLUETOOTH ADMIN permission"
            r2.enforceCallingOrSelfPermission(r0, r1)     // Catch: java.lang.Throwable -> L21
            boolean r0 = r2.checkDevice(r3)     // Catch: java.lang.Throwable -> L21
            if (r0 == 0) goto L1e
            boolean r0 = r2.checkCallingUid()     // Catch: java.lang.Throwable -> L21
            if (r0 == 0) goto L1e
            com.android.bluetooth.hid.HidDeviceNativeInterface r0 = r2.mHidDeviceNativeInterface     // Catch: java.lang.Throwable -> L21
            boolean r0 = r0.unplug()     // Catch: java.lang.Throwable -> L21
            if (r0 == 0) goto L1e
            r0 = 1
            goto L1f
        L1e:
            r0 = 0
        L1f:
            monitor-exit(r2)
            return r0
        L21:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.bluetooth.hid.HidDeviceService.unplug(android.bluetooth.BluetoothDevice):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0018  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized boolean connect(android.bluetooth.BluetoothDevice r3) {
        /*
            r2 = this;
            monitor-enter(r2)
            java.lang.String r0 = "android.permission.BLUETOOTH_ADMIN"
            java.lang.String r1 = "Need BLUETOOTH ADMIN permission"
            r2.enforceCallingOrSelfPermission(r0, r1)     // Catch: java.lang.Throwable -> L1b
            boolean r0 = r2.checkCallingUid()     // Catch: java.lang.Throwable -> L1b
            if (r0 == 0) goto L18
            com.android.bluetooth.hid.HidDeviceNativeInterface r0 = r2.mHidDeviceNativeInterface     // Catch: java.lang.Throwable -> L1b
            boolean r0 = r0.connect(r3)     // Catch: java.lang.Throwable -> L1b
            if (r0 == 0) goto L18
            r0 = 1
            goto L19
        L18:
            r0 = 0
        L19:
            monitor-exit(r2)
            return r0
        L1b:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.bluetooth.hid.HidDeviceService.connect(android.bluetooth.BluetoothDevice):boolean");
    }

    public synchronized boolean disconnect(BluetoothDevice device) {
        enforceCallingOrSelfPermission("android.permission.BLUETOOTH_ADMIN", "Need BLUETOOTH ADMIN permission");
        int callingUid = Binder.getCallingUid();
        boolean z = false;
        if (callingUid != this.mUserUid && callingUid >= 10000) {
            Log.w(TAG, "disconnect(): caller UID doesn't match user UID");
            return false;
        }
        if (checkDevice(device) && this.mHidDeviceNativeInterface.disconnect()) {
            z = true;
        }
        return z;
    }

    public boolean setConnectionPolicy(BluetoothDevice device, int connectionPolicy) {
        enforceCallingOrSelfPermission("android.permission.BLUETOOTH_PRIVILEGED", "Need BLUETOOTH_PRIVILEGED permission");
        AdapterService.getAdapterService().getDatabase().setProfileConnectionPolicy(device, 19, connectionPolicy);
        if (connectionPolicy == 0) {
            disconnect(device);
            return true;
        }
        return false;
    }

    public int getConnectionPolicy(BluetoothDevice device) {
        if (device == null) {
            throw new IllegalArgumentException("Null device");
        }
        enforceCallingOrSelfPermission("android.permission.BLUETOOTH_PRIVILEGED", "Need BLUETOOTH_PRIVILEGED permission");
        return AdapterService.getAdapterService().getDatabase().getProfileConnectionPolicy(device, 19);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x001e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    synchronized boolean reportError(android.bluetooth.BluetoothDevice r3, byte r4) {
        /*
            r2 = this;
            monitor-enter(r2)
            java.lang.String r0 = "android.permission.BLUETOOTH_ADMIN"
            java.lang.String r1 = "Need BLUETOOTH ADMIN permission"
            r2.enforceCallingOrSelfPermission(r0, r1)     // Catch: java.lang.Throwable -> L21
            boolean r0 = r2.checkDevice(r3)     // Catch: java.lang.Throwable -> L21
            if (r0 == 0) goto L1e
            boolean r0 = r2.checkCallingUid()     // Catch: java.lang.Throwable -> L21
            if (r0 == 0) goto L1e
            com.android.bluetooth.hid.HidDeviceNativeInterface r0 = r2.mHidDeviceNativeInterface     // Catch: java.lang.Throwable -> L21
            boolean r0 = r0.reportError(r4)     // Catch: java.lang.Throwable -> L21
            if (r0 == 0) goto L1e
            r0 = 1
            goto L1f
        L1e:
            r0 = 0
        L1f:
            monitor-exit(r2)
            return r0
        L21:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.bluetooth.hid.HidDeviceService.reportError(android.bluetooth.BluetoothDevice, byte):boolean");
    }

    synchronized String getUserAppName() {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        if (this.mUserUid < 10000) {
            return INetd.NEXTHOP_NONE;
        }
        String appName = getPackageManager().getNameForUid(this.mUserUid);
        return appName != null ? appName : INetd.NEXTHOP_NONE;
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    protected boolean start() {
        this.mHandler = new HidDeviceServiceHandler();
        HidDeviceNativeInterface hidDeviceNativeInterface = HidDeviceNativeInterface.getInstance();
        this.mHidDeviceNativeInterface = hidDeviceNativeInterface;
        hidDeviceNativeInterface.init();
        this.mNativeAvailable = true;
        ActivityManager activityManager = (ActivityManager) getSystemService("activity");
        this.mActivityManager = activityManager;
        activityManager.addOnUidImportanceListener(this.mUidImportanceListener, 200);
        setHidDeviceService(this);
        return true;
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    protected boolean stop() {
        if (sHidDeviceService == null) {
            Log.w(TAG, "stop() called before start()");
            return true;
        }
        setHidDeviceService(null);
        if (this.mNativeAvailable) {
            this.mHidDeviceNativeInterface.cleanup();
            this.mNativeAvailable = false;
        }
        this.mActivityManager.removeOnUidImportanceListener(this.mUidImportanceListener);
        return true;
    }

    @Override // com.android.bluetooth.btservice.ProfileService, android.app.Service
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "Need to unregister app");
        unregisterApp();
        return super.onUnbind(intent);
    }

    public static synchronized HidDeviceService getHidDeviceService() {
        if (sHidDeviceService == null) {
            Log.d(TAG, "getHidDeviceService(): service is NULL");
            return null;
        }
        if (!sHidDeviceService.isAvailable()) {
            Log.d(TAG, "getHidDeviceService(): service is not available");
            return null;
        }
        return sHidDeviceService;
    }

    private static synchronized void setHidDeviceService(HidDeviceService instance) {
        sHidDeviceService = instance;
    }

    public int getConnectionState(BluetoothDevice device) {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        BluetoothDevice bluetoothDevice = this.mHidDevice;
        if (bluetoothDevice != null && bluetoothDevice.equals(device)) {
            return this.mHidDeviceState;
        }
        return 0;
    }

    List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] states) {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        List<BluetoothDevice> inputDevices = new ArrayList<>();
        if (this.mHidDevice != null) {
            int length = states.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                int state = states[i];
                if (state != this.mHidDeviceState) {
                    i++;
                } else {
                    inputDevices.add(this.mHidDevice);
                    break;
                }
            }
        }
        return inputDevices;
    }

    synchronized void onApplicationStateChangedFromNative(BluetoothDevice device, boolean registered) {
        int i = 1;
        Message msg = this.mHandler.obtainMessage(1);
        msg.obj = device;
        if (!registered) {
            i = 0;
        }
        msg.arg1 = i;
        this.mHandler.sendMessage(msg);
    }

    synchronized void onConnectStateChangedFromNative(BluetoothDevice device, int state) {
        Message msg = this.mHandler.obtainMessage(2);
        msg.obj = device;
        msg.arg1 = state;
        this.mHandler.sendMessage(msg);
    }

    synchronized void onGetReportFromNative(byte type, byte id, short bufferSize) {
        Message msg = this.mHandler.obtainMessage(3);
        msg.obj = bufferSize > 0 ? new Integer(bufferSize) : null;
        msg.arg1 = type;
        msg.arg2 = id;
        this.mHandler.sendMessage(msg);
    }

    synchronized void onSetReportFromNative(byte reportType, byte reportId, byte[] data) {
        ByteBuffer bb = ByteBuffer.wrap(data);
        Message msg = this.mHandler.obtainMessage(4);
        msg.arg1 = reportType;
        msg.arg2 = reportId;
        msg.obj = bb;
        this.mHandler.sendMessage(msg);
    }

    synchronized void onSetProtocolFromNative(byte protocol) {
        Message msg = this.mHandler.obtainMessage(5);
        msg.arg1 = protocol;
        this.mHandler.sendMessage(msg);
    }

    synchronized void onInterruptDataFromNative(byte reportId, byte[] data) {
        ByteBuffer bb = ByteBuffer.wrap(data);
        Message msg = this.mHandler.obtainMessage(6);
        msg.arg1 = reportId;
        msg.obj = bb;
        this.mHandler.sendMessage(msg);
    }

    synchronized void onVirtualCableUnplugFromNative() {
        Message msg = this.mHandler.obtainMessage(7);
        this.mHandler.sendMessage(msg);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAndBroadcastConnectionState(BluetoothDevice device, int newState) {
        BluetoothDevice bluetoothDevice = this.mHidDevice;
        if (bluetoothDevice != null && !bluetoothDevice.equals(device)) {
            Log.w(TAG, "Connection state changed for unknown device, ignoring");
            return;
        }
        int prevState = this.mHidDeviceState;
        this.mHidDeviceState = newState;
        if (prevState == newState) {
            Log.w(TAG, "Connection state is unchanged, ignoring");
            return;
        }
        if (newState == 2) {
            MetricsLogger.logProfileConnectionEvent(BluetoothMetricsProto.ProfileId.HID_DEVICE);
        }
        Intent intent = new Intent("android.bluetooth.hiddevice.profile.action.CONNECTION_STATE_CHANGED");
        intent.putExtra("android.bluetooth.profile.extra.PREVIOUS_STATE", prevState);
        intent.putExtra("android.bluetooth.profile.extra.STATE", newState);
        intent.putExtra("android.bluetooth.device.extra.DEVICE", device);
        intent.addFlags(VCardConfig.FLAG_APPEND_TYPE_PARAM);
        sendBroadcast(intent, ProfileService.BLUETOOTH_PERM);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int convertHalState(int halState) {
        if (halState == 0) {
            return 2;
        }
        if (halState != 1) {
            return halState != 3 ? 0 : 3;
        }
        return 1;
    }
}
