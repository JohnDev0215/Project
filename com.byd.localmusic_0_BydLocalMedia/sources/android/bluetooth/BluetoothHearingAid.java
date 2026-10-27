package android.bluetooth;

import android.annotation.SystemApi;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.IBluetoothHearingAid;
import android.content.Context;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.telephony.ims.ImsConferenceState;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class BluetoothHearingAid implements BluetoothProfile {
    public static final String ACTION_ACTIVE_DEVICE_CHANGED = "android.bluetooth.hearingaid.profile.action.ACTIVE_DEVICE_CHANGED";
    public static final String ACTION_CONNECTION_STATE_CHANGED = "android.bluetooth.hearingaid.profile.action.CONNECTION_STATE_CHANGED";
    private static final boolean DBG = true;
    public static final long HI_SYNC_ID_INVALID = 0;
    public static final int MODE_BINAURAL = 1;
    public static final int MODE_MONAURAL = 0;
    public static final int SIDE_LEFT = 0;
    public static final int SIDE_RIGHT = 1;
    private static final String TAG = "BluetoothHearingAid";
    private static final boolean VDBG = false;
    private final BluetoothProfileConnector<IBluetoothHearingAid> mProfileConnector = new BluetoothProfileConnector(this, 21, TAG, IBluetoothHearingAid.class.getName()) { // from class: android.bluetooth.BluetoothHearingAid.1
        @Override // android.bluetooth.BluetoothProfileConnector
        public IBluetoothHearingAid getServiceInterface(IBinder iBinder) {
            return IBluetoothHearingAid.Stub.asInterface(Binder.allowBlocking(iBinder));
        }
    };
    private BluetoothAdapter mAdapter = BluetoothAdapter.getDefaultAdapter();

    BluetoothHearingAid(Context context, BluetoothProfile.ServiceListener serviceListener) {
        this.mProfileConnector.connect(context, serviceListener);
    }

    void close() {
        this.mProfileConnector.disconnect();
    }

    private IBluetoothHearingAid getService() {
        return this.mProfileConnector.getService();
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0035 A[Catch: RemoteException -> 0x003b, TRY_LEAVE, TryCatch #0 {RemoteException -> 0x003b, blocks: (B:4:0x0022, B:6:0x0028, B:8:0x002e, B:11:0x0035), top: B:15:0x0022 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean connect(android.bluetooth.BluetoothDevice r5) {
        /*
            r4 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "connect("
            r0.append(r1)
            r0.append(r5)
            java.lang.String r1 = ")"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            log(r0)
            android.bluetooth.IBluetoothHearingAid r0 = r4.getService()
            java.lang.String r1 = "BluetoothHearingAid"
            r2 = 0
            if (r0 == 0) goto L33
            boolean r3 = r4.isEnabled()     // Catch: android.os.RemoteException -> L3b
            if (r3 == 0) goto L33
            boolean r3 = r4.isValidDevice(r5)     // Catch: android.os.RemoteException -> L3b
            if (r3 == 0) goto L33
            boolean r5 = r0.connect(r5)     // Catch: android.os.RemoteException -> L3b
            return r5
        L33:
            if (r0 != 0) goto L58
            java.lang.String r5 = "Proxy not attached to service"
            android.util.Log.w(r1, r5)     // Catch: android.os.RemoteException -> L3b
            goto L58
        L3b:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r0 = "Stack:"
            r5.append(r0)
            java.lang.Throwable r0 = new java.lang.Throwable
            r0.<init>()
            java.lang.String r0 = android.util.Log.getStackTraceString(r0)
            r5.append(r0)
            java.lang.String r5 = r5.toString()
            android.util.Log.e(r1, r5)
        L58:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.bluetooth.BluetoothHearingAid.connect(android.bluetooth.BluetoothDevice):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0035 A[Catch: RemoteException -> 0x003b, TRY_LEAVE, TryCatch #0 {RemoteException -> 0x003b, blocks: (B:4:0x0022, B:6:0x0028, B:8:0x002e, B:11:0x0035), top: B:15:0x0022 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean disconnect(android.bluetooth.BluetoothDevice r5) {
        /*
            r4 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "disconnect("
            r0.append(r1)
            r0.append(r5)
            java.lang.String r1 = ")"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            log(r0)
            android.bluetooth.IBluetoothHearingAid r0 = r4.getService()
            java.lang.String r1 = "BluetoothHearingAid"
            r2 = 0
            if (r0 == 0) goto L33
            boolean r3 = r4.isEnabled()     // Catch: android.os.RemoteException -> L3b
            if (r3 == 0) goto L33
            boolean r3 = r4.isValidDevice(r5)     // Catch: android.os.RemoteException -> L3b
            if (r3 == 0) goto L33
            boolean r5 = r0.disconnect(r5)     // Catch: android.os.RemoteException -> L3b
            return r5
        L33:
            if (r0 != 0) goto L58
            java.lang.String r5 = "Proxy not attached to service"
            android.util.Log.w(r1, r5)     // Catch: android.os.RemoteException -> L3b
            goto L58
        L3b:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r0 = "Stack:"
            r5.append(r0)
            java.lang.Throwable r0 = new java.lang.Throwable
            r0.<init>()
            java.lang.String r0 = android.util.Log.getStackTraceString(r0)
            r5.append(r0)
            java.lang.String r5 = r5.toString()
            android.util.Log.e(r1, r5)
        L58:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.bluetooth.BluetoothHearingAid.disconnect(android.bluetooth.BluetoothDevice):boolean");
    }

    @Override // android.bluetooth.BluetoothProfile
    public List<BluetoothDevice> getConnectedDevices() {
        IBluetoothHearingAid service = getService();
        if (service != null) {
            try {
                if (isEnabled()) {
                    return service.getConnectedDevices();
                }
            } catch (RemoteException unused) {
                Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
                return new ArrayList();
            }
        }
        if (service == null) {
            Log.w(TAG, "Proxy not attached to service");
        }
        return new ArrayList();
    }

    @Override // android.bluetooth.BluetoothProfile
    public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] iArr) {
        IBluetoothHearingAid service = getService();
        if (service != null) {
            try {
                if (isEnabled()) {
                    return service.getDevicesMatchingConnectionStates(iArr);
                }
            } catch (RemoteException unused) {
                Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
                return new ArrayList();
            }
        }
        if (service == null) {
            Log.w(TAG, "Proxy not attached to service");
        }
        return new ArrayList();
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x001c A[Catch: RemoteException -> 0x0022, TRY_LEAVE, TryCatch #0 {RemoteException -> 0x0022, blocks: (B:4:0x0009, B:6:0x000f, B:8:0x0015, B:11:0x001c), top: B:15:0x0009 }] */
    @Override // android.bluetooth.BluetoothProfile
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int getConnectionState(android.bluetooth.BluetoothDevice r5) {
        /*
            r4 = this;
            android.bluetooth.IBluetoothHearingAid r0 = r4.getService()
            java.lang.String r1 = "BluetoothHearingAid"
            r2 = 0
            if (r0 == 0) goto L1a
            boolean r3 = r4.isEnabled()     // Catch: android.os.RemoteException -> L22
            if (r3 == 0) goto L1a
            boolean r3 = r4.isValidDevice(r5)     // Catch: android.os.RemoteException -> L22
            if (r3 == 0) goto L1a
            int r5 = r0.getConnectionState(r5)     // Catch: android.os.RemoteException -> L22
            return r5
        L1a:
            if (r0 != 0) goto L3f
            java.lang.String r5 = "Proxy not attached to service"
            android.util.Log.w(r1, r5)     // Catch: android.os.RemoteException -> L22
            goto L3f
        L22:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r0 = "Stack:"
            r5.append(r0)
            java.lang.Throwable r0 = new java.lang.Throwable
            r0.<init>()
            java.lang.String r0 = android.util.Log.getStackTraceString(r0)
            r5.append(r0)
            java.lang.String r5 = r5.toString()
            android.util.Log.e(r1, r5)
        L3f:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.bluetooth.BluetoothHearingAid.getConnectionState(android.bluetooth.BluetoothDevice):int");
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0038 A[Catch: RemoteException -> 0x003e, TRY_LEAVE, TryCatch #0 {RemoteException -> 0x003e, blocks: (B:4:0x0023, B:7:0x002b, B:9:0x0031, B:12:0x0038), top: B:16:0x0023 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean setActiveDevice(android.bluetooth.BluetoothDevice r5) {
        /*
            r4 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "setActiveDevice("
            r0.append(r1)
            r0.append(r5)
            java.lang.String r1 = ")"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            log(r0)
            android.bluetooth.IBluetoothHearingAid r0 = r4.getService()
            java.lang.String r1 = "BluetoothHearingAid"
            r2 = 0
            if (r0 == 0) goto L36
            boolean r3 = r4.isEnabled()     // Catch: android.os.RemoteException -> L3e
            if (r3 == 0) goto L36
            if (r5 == 0) goto L31
            boolean r3 = r4.isValidDevice(r5)     // Catch: android.os.RemoteException -> L3e
            if (r3 == 0) goto L36
        L31:
            r0.setActiveDevice(r5)     // Catch: android.os.RemoteException -> L3e
            r5 = 1
            return r5
        L36:
            if (r0 != 0) goto L5b
            java.lang.String r5 = "Proxy not attached to service"
            android.util.Log.w(r1, r5)     // Catch: android.os.RemoteException -> L3e
            goto L5b
        L3e:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r0 = "Stack:"
            r5.append(r0)
            java.lang.Throwable r0 = new java.lang.Throwable
            r0.<init>()
            java.lang.String r0 = android.util.Log.getStackTraceString(r0)
            r5.append(r0)
            java.lang.String r5 = r5.toString()
            android.util.Log.e(r1, r5)
        L5b:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.bluetooth.BluetoothHearingAid.setActiveDevice(android.bluetooth.BluetoothDevice):boolean");
    }

    public List<BluetoothDevice> getActiveDevices() {
        IBluetoothHearingAid service = getService();
        if (service != null) {
            try {
                if (isEnabled()) {
                    return service.getActiveDevices();
                }
            } catch (RemoteException unused) {
                Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
                return new ArrayList();
            }
        }
        if (service == null) {
            Log.w(TAG, "Proxy not attached to service");
        }
        return new ArrayList();
    }

    public boolean setPriority(BluetoothDevice bluetoothDevice, int i) {
        log("setPriority(" + bluetoothDevice + ", " + i + ")");
        return setConnectionPolicy(bluetoothDevice, BluetoothAdapter.priorityToConnectionPolicy(i));
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x004b A[Catch: RemoteException -> 0x0051, TRY_LEAVE, TryCatch #0 {RemoteException -> 0x0051, blocks: (B:4:0x0031, B:6:0x0037, B:12:0x0044, B:15:0x004b), top: B:19:0x0031 }] */
    @android.annotation.SystemApi
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean setConnectionPolicy(android.bluetooth.BluetoothDevice r5, int r6) {
        /*
            r4 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "setConnectionPolicy("
            r0.append(r1)
            r0.append(r5)
            java.lang.String r1 = ", "
            r0.append(r1)
            r0.append(r6)
            java.lang.String r1 = ")"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            log(r0)
            java.lang.String r0 = "setConnectionPolicy"
            r4.verifyDeviceNotNull(r5, r0)
            android.bluetooth.IBluetoothHearingAid r0 = r4.getService()
            java.lang.String r1 = "BluetoothHearingAid"
            r2 = 0
            if (r0 == 0) goto L49
            boolean r3 = r4.isEnabled()     // Catch: android.os.RemoteException -> L51
            if (r3 == 0) goto L49
            boolean r3 = r4.isValidDevice(r5)     // Catch: android.os.RemoteException -> L51
            if (r3 == 0) goto L49
            if (r6 == 0) goto L44
            r3 = 100
            if (r6 == r3) goto L44
            return r2
        L44:
            boolean r5 = r0.setConnectionPolicy(r5, r6)     // Catch: android.os.RemoteException -> L51
            return r5
        L49:
            if (r0 != 0) goto L6e
            java.lang.String r5 = "Proxy not attached to service"
            android.util.Log.w(r1, r5)     // Catch: android.os.RemoteException -> L51
            goto L6e
        L51:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "Stack:"
            r5.append(r6)
            java.lang.Throwable r6 = new java.lang.Throwable
            r6.<init>()
            java.lang.String r6 = android.util.Log.getStackTraceString(r6)
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            android.util.Log.e(r1, r5)
        L6e:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.bluetooth.BluetoothHearingAid.setConnectionPolicy(android.bluetooth.BluetoothDevice, int):boolean");
    }

    public int getPriority(BluetoothDevice bluetoothDevice) {
        return BluetoothAdapter.connectionPolicyToPriority(getConnectionPolicy(bluetoothDevice));
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0021 A[Catch: RemoteException -> 0x0027, TRY_LEAVE, TryCatch #0 {RemoteException -> 0x0027, blocks: (B:4:0x000e, B:6:0x0014, B:8:0x001a, B:11:0x0021), top: B:15:0x000e }] */
    @android.annotation.SystemApi
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int getConnectionPolicy(android.bluetooth.BluetoothDevice r5) {
        /*
            r4 = this;
            java.lang.String r0 = "getConnectionPolicy"
            r4.verifyDeviceNotNull(r5, r0)
            android.bluetooth.IBluetoothHearingAid r0 = r4.getService()
            java.lang.String r1 = "BluetoothHearingAid"
            r2 = 0
            if (r0 == 0) goto L1f
            boolean r3 = r4.isEnabled()     // Catch: android.os.RemoteException -> L27
            if (r3 == 0) goto L1f
            boolean r3 = r4.isValidDevice(r5)     // Catch: android.os.RemoteException -> L27
            if (r3 == 0) goto L1f
            int r5 = r0.getConnectionPolicy(r5)     // Catch: android.os.RemoteException -> L27
            return r5
        L1f:
            if (r0 != 0) goto L44
            java.lang.String r5 = "Proxy not attached to service"
            android.util.Log.w(r1, r5)     // Catch: android.os.RemoteException -> L27
            goto L44
        L27:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r0 = "Stack:"
            r5.append(r0)
            java.lang.Throwable r0 = new java.lang.Throwable
            r0.<init>()
            java.lang.String r0 = android.util.Log.getStackTraceString(r0)
            r5.append(r0)
            java.lang.String r5 = r5.toString()
            android.util.Log.e(r1, r5)
        L44:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.bluetooth.BluetoothHearingAid.getConnectionPolicy(android.bluetooth.BluetoothDevice):int");
    }

    public static String stateToString(int i) {
        if (i == 0) {
            return "disconnected";
        }
        if (i == 1) {
            return "connecting";
        }
        if (i == 2) {
            return "connected";
        }
        if (i == 3) {
            return ImsConferenceState.STATUS_DISCONNECTING;
        }
        return "<unknown state " + i + ">";
    }

    public void setVolume(int i) {
        Log.d(TAG, "setVolume(" + i + ")");
        IBluetoothHearingAid service = getService();
        try {
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            } else if (isEnabled()) {
                service.setVolume(i);
            }
        } catch (RemoteException unused) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
        }
    }

    @SystemApi
    public long getHiSyncId(BluetoothDevice bluetoothDevice) {
        verifyDeviceNotNull(bluetoothDevice, "getConnectionPolicy");
        IBluetoothHearingAid service = getService();
        try {
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
                return 0L;
            }
            if (isEnabled() && isValidDevice(bluetoothDevice)) {
                return service.getHiSyncId(bluetoothDevice);
            }
            return 0L;
        } catch (RemoteException unused) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
            return 0L;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x001c A[Catch: RemoteException -> 0x0022, TRY_LEAVE, TryCatch #0 {RemoteException -> 0x0022, blocks: (B:4:0x0009, B:6:0x000f, B:8:0x0015, B:11:0x001c), top: B:15:0x0009 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int getDeviceSide(android.bluetooth.BluetoothDevice r5) {
        /*
            r4 = this;
            android.bluetooth.IBluetoothHearingAid r0 = r4.getService()
            java.lang.String r1 = "BluetoothHearingAid"
            r2 = 0
            if (r0 == 0) goto L1a
            boolean r3 = r4.isEnabled()     // Catch: android.os.RemoteException -> L22
            if (r3 == 0) goto L1a
            boolean r3 = r4.isValidDevice(r5)     // Catch: android.os.RemoteException -> L22
            if (r3 == 0) goto L1a
            int r5 = r0.getDeviceSide(r5)     // Catch: android.os.RemoteException -> L22
            return r5
        L1a:
            if (r0 != 0) goto L3f
            java.lang.String r5 = "Proxy not attached to service"
            android.util.Log.w(r1, r5)     // Catch: android.os.RemoteException -> L22
            goto L3f
        L22:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r0 = "Stack:"
            r5.append(r0)
            java.lang.Throwable r0 = new java.lang.Throwable
            r0.<init>()
            java.lang.String r0 = android.util.Log.getStackTraceString(r0)
            r5.append(r0)
            java.lang.String r5 = r5.toString()
            android.util.Log.e(r1, r5)
        L3f:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.bluetooth.BluetoothHearingAid.getDeviceSide(android.bluetooth.BluetoothDevice):int");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x001c A[Catch: RemoteException -> 0x0022, TRY_LEAVE, TryCatch #0 {RemoteException -> 0x0022, blocks: (B:4:0x0009, B:6:0x000f, B:8:0x0015, B:11:0x001c), top: B:15:0x0009 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int getDeviceMode(android.bluetooth.BluetoothDevice r5) {
        /*
            r4 = this;
            android.bluetooth.IBluetoothHearingAid r0 = r4.getService()
            java.lang.String r1 = "BluetoothHearingAid"
            r2 = 0
            if (r0 == 0) goto L1a
            boolean r3 = r4.isEnabled()     // Catch: android.os.RemoteException -> L22
            if (r3 == 0) goto L1a
            boolean r3 = r4.isValidDevice(r5)     // Catch: android.os.RemoteException -> L22
            if (r3 == 0) goto L1a
            int r5 = r0.getDeviceMode(r5)     // Catch: android.os.RemoteException -> L22
            return r5
        L1a:
            if (r0 != 0) goto L3f
            java.lang.String r5 = "Proxy not attached to service"
            android.util.Log.w(r1, r5)     // Catch: android.os.RemoteException -> L22
            goto L3f
        L22:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r0 = "Stack:"
            r5.append(r0)
            java.lang.Throwable r0 = new java.lang.Throwable
            r0.<init>()
            java.lang.String r0 = android.util.Log.getStackTraceString(r0)
            r5.append(r0)
            java.lang.String r5 = r5.toString()
            android.util.Log.e(r1, r5)
        L3f:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.bluetooth.BluetoothHearingAid.getDeviceMode(android.bluetooth.BluetoothDevice):int");
    }

    private boolean isEnabled() {
        return this.mAdapter.getState() == 12;
    }

    private void verifyDeviceNotNull(BluetoothDevice bluetoothDevice, String str) {
        if (bluetoothDevice != null) {
            return;
        }
        Log.e(TAG, str + ": device param is null");
        throw new IllegalArgumentException("Device cannot be null");
    }

    private boolean isValidDevice(BluetoothDevice bluetoothDevice) {
        return bluetoothDevice != null && BluetoothAdapter.checkBluetoothAddress(bluetoothDevice.getAddress());
    }

    private static void log(String str) {
        Log.d(TAG, str);
    }
}
