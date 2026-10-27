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
    private BluetoothAdapter mAdapter;
    private final BluetoothProfileConnector<IBluetoothHearingAid> mProfileConnector;

    BluetoothHearingAid(Context context, BluetoothProfile.ServiceListener serviceListener) {
        BluetoothProfileConnector<IBluetoothHearingAid> bluetoothProfileConnector = new BluetoothProfileConnector(this, 21, TAG, IBluetoothHearingAid.class.getName()) { // from class: android.bluetooth.BluetoothHearingAid.1
            @Override // android.bluetooth.BluetoothProfileConnector
            public IBluetoothHearingAid getServiceInterface(IBinder iBinder) {
                return IBluetoothHearingAid.Stub.asInterface(Binder.allowBlocking(iBinder));
            }
        };
        this.mProfileConnector = bluetoothProfileConnector;
        this.mAdapter = BluetoothAdapter.getDefaultAdapter();
        bluetoothProfileConnector.connect(context, serviceListener);
    }

    void close() {
        this.mProfileConnector.disconnect();
    }

    private IBluetoothHearingAid getService() {
        return this.mProfileConnector.getService();
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0038 A[Catch: RemoteException -> 0x003e, TRY_LEAVE, TryCatch #0 {RemoteException -> 0x003e, blocks: (B:4:0x0025, B:6:0x002b, B:8:0x0031, B:11:0x0038), top: B:15:0x0025 }] */
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
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.StringBuilder r0 = r0.append(r5)
            java.lang.String r1 = ")"
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            log(r0)
            android.bluetooth.IBluetoothHearingAid r0 = r4.getService()
            java.lang.String r1 = "BluetoothHearingAid"
            r2 = 0
            if (r0 == 0) goto L36
            boolean r3 = r4.isEnabled()     // Catch: android.os.RemoteException -> L3e
            if (r3 == 0) goto L36
            boolean r4 = r4.isValidDevice(r5)     // Catch: android.os.RemoteException -> L3e
            if (r4 == 0) goto L36
            boolean r4 = r0.connect(r5)     // Catch: android.os.RemoteException -> L3e
            return r4
        L36:
            if (r0 != 0) goto L5d
            java.lang.String r4 = "Proxy not attached to service"
            android.util.Log.w(r1, r4)     // Catch: android.os.RemoteException -> L3e
            goto L5d
        L3e:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "Stack:"
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.Throwable r5 = new java.lang.Throwable
            r5.<init>()
            java.lang.String r5 = android.util.Log.getStackTraceString(r5)
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r4 = r4.toString()
            android.util.Log.e(r1, r4)
        L5d:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.bluetooth.BluetoothHearingAid.connect(android.bluetooth.BluetoothDevice):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0038 A[Catch: RemoteException -> 0x003e, TRY_LEAVE, TryCatch #0 {RemoteException -> 0x003e, blocks: (B:4:0x0025, B:6:0x002b, B:8:0x0031, B:11:0x0038), top: B:15:0x0025 }] */
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
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.StringBuilder r0 = r0.append(r5)
            java.lang.String r1 = ")"
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            log(r0)
            android.bluetooth.IBluetoothHearingAid r0 = r4.getService()
            java.lang.String r1 = "BluetoothHearingAid"
            r2 = 0
            if (r0 == 0) goto L36
            boolean r3 = r4.isEnabled()     // Catch: android.os.RemoteException -> L3e
            if (r3 == 0) goto L36
            boolean r4 = r4.isValidDevice(r5)     // Catch: android.os.RemoteException -> L3e
            if (r4 == 0) goto L36
            boolean r4 = r0.disconnect(r5)     // Catch: android.os.RemoteException -> L3e
            return r4
        L36:
            if (r0 != 0) goto L5d
            java.lang.String r4 = "Proxy not attached to service"
            android.util.Log.w(r1, r4)     // Catch: android.os.RemoteException -> L3e
            goto L5d
        L3e:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "Stack:"
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.Throwable r5 = new java.lang.Throwable
            r5.<init>()
            java.lang.String r5 = android.util.Log.getStackTraceString(r5)
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r4 = r4.toString()
            android.util.Log.e(r1, r4)
        L5d:
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
            boolean r4 = r4.isValidDevice(r5)     // Catch: android.os.RemoteException -> L22
            if (r4 == 0) goto L1a
            int r4 = r0.getConnectionState(r5)     // Catch: android.os.RemoteException -> L22
            return r4
        L1a:
            if (r0 != 0) goto L41
            java.lang.String r4 = "Proxy not attached to service"
            android.util.Log.w(r1, r4)     // Catch: android.os.RemoteException -> L22
            goto L41
        L22:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "Stack:"
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.Throwable r5 = new java.lang.Throwable
            r5.<init>()
            java.lang.String r5 = android.util.Log.getStackTraceString(r5)
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r4 = r4.toString()
            android.util.Log.e(r1, r4)
        L41:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.bluetooth.BluetoothHearingAid.getConnectionState(android.bluetooth.BluetoothDevice):int");
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x003b A[Catch: RemoteException -> 0x0041, TRY_LEAVE, TryCatch #0 {RemoteException -> 0x0041, blocks: (B:4:0x0026, B:7:0x002e, B:9:0x0034, B:12:0x003b), top: B:16:0x0026 }] */
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
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.StringBuilder r0 = r0.append(r5)
            java.lang.String r1 = ")"
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            log(r0)
            android.bluetooth.IBluetoothHearingAid r0 = r4.getService()
            java.lang.String r1 = "BluetoothHearingAid"
            r2 = 0
            if (r0 == 0) goto L39
            boolean r3 = r4.isEnabled()     // Catch: android.os.RemoteException -> L41
            if (r3 == 0) goto L39
            if (r5 == 0) goto L34
            boolean r4 = r4.isValidDevice(r5)     // Catch: android.os.RemoteException -> L41
            if (r4 == 0) goto L39
        L34:
            r0.setActiveDevice(r5)     // Catch: android.os.RemoteException -> L41
            r4 = 1
            return r4
        L39:
            if (r0 != 0) goto L60
            java.lang.String r4 = "Proxy not attached to service"
            android.util.Log.w(r1, r4)     // Catch: android.os.RemoteException -> L41
            goto L60
        L41:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "Stack:"
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.Throwable r5 = new java.lang.Throwable
            r5.<init>()
            java.lang.String r5 = android.util.Log.getStackTraceString(r5)
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r4 = r4.toString()
            android.util.Log.e(r1, r4)
        L60:
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

    /* JADX WARN: Removed duplicated region for block: B:15:0x0050 A[Catch: RemoteException -> 0x0056, TRY_LEAVE, TryCatch #0 {RemoteException -> 0x0056, blocks: (B:4:0x0036, B:6:0x003c, B:12:0x0049, B:15:0x0050), top: B:19:0x0036 }] */
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
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.StringBuilder r0 = r0.append(r5)
            java.lang.String r1 = ", "
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.StringBuilder r0 = r0.append(r6)
            java.lang.String r1 = ")"
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            log(r0)
            java.lang.String r0 = "setConnectionPolicy"
            r4.verifyDeviceNotNull(r5, r0)
            android.bluetooth.IBluetoothHearingAid r0 = r4.getService()
            java.lang.String r1 = "BluetoothHearingAid"
            r2 = 0
            if (r0 == 0) goto L4e
            boolean r3 = r4.isEnabled()     // Catch: android.os.RemoteException -> L56
            if (r3 == 0) goto L4e
            boolean r4 = r4.isValidDevice(r5)     // Catch: android.os.RemoteException -> L56
            if (r4 == 0) goto L4e
            if (r6 == 0) goto L49
            r4 = 100
            if (r6 == r4) goto L49
            return r2
        L49:
            boolean r4 = r0.setConnectionPolicy(r5, r6)     // Catch: android.os.RemoteException -> L56
            return r4
        L4e:
            if (r0 != 0) goto L75
            java.lang.String r4 = "Proxy not attached to service"
            android.util.Log.w(r1, r4)     // Catch: android.os.RemoteException -> L56
            goto L75
        L56:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "Stack:"
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.Throwable r5 = new java.lang.Throwable
            r5.<init>()
            java.lang.String r5 = android.util.Log.getStackTraceString(r5)
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r4 = r4.toString()
            android.util.Log.e(r1, r4)
        L75:
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
            boolean r4 = r4.isValidDevice(r5)     // Catch: android.os.RemoteException -> L27
            if (r4 == 0) goto L1f
            int r4 = r0.getConnectionPolicy(r5)     // Catch: android.os.RemoteException -> L27
            return r4
        L1f:
            if (r0 != 0) goto L46
            java.lang.String r4 = "Proxy not attached to service"
            android.util.Log.w(r1, r4)     // Catch: android.os.RemoteException -> L27
            goto L46
        L27:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "Stack:"
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.Throwable r5 = new java.lang.Throwable
            r5.<init>()
            java.lang.String r5 = android.util.Log.getStackTraceString(r5)
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r4 = r4.toString()
            android.util.Log.e(r1, r4)
        L46:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.bluetooth.BluetoothHearingAid.getConnectionPolicy(android.bluetooth.BluetoothDevice):int");
    }

    public static String stateToString(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? "<unknown state " + i + ">" : ImsConferenceState.STATUS_DISCONNECTING : "connected" : "connecting" : "disconnected";
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
            boolean r4 = r4.isValidDevice(r5)     // Catch: android.os.RemoteException -> L22
            if (r4 == 0) goto L1a
            int r4 = r0.getDeviceSide(r5)     // Catch: android.os.RemoteException -> L22
            return r4
        L1a:
            if (r0 != 0) goto L41
            java.lang.String r4 = "Proxy not attached to service"
            android.util.Log.w(r1, r4)     // Catch: android.os.RemoteException -> L22
            goto L41
        L22:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "Stack:"
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.Throwable r5 = new java.lang.Throwable
            r5.<init>()
            java.lang.String r5 = android.util.Log.getStackTraceString(r5)
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r4 = r4.toString()
            android.util.Log.e(r1, r4)
        L41:
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
            boolean r4 = r4.isValidDevice(r5)     // Catch: android.os.RemoteException -> L22
            if (r4 == 0) goto L1a
            int r4 = r0.getDeviceMode(r5)     // Catch: android.os.RemoteException -> L22
            return r4
        L1a:
            if (r0 != 0) goto L41
            java.lang.String r4 = "Proxy not attached to service"
            android.util.Log.w(r1, r4)     // Catch: android.os.RemoteException -> L22
            goto L41
        L22:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "Stack:"
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.Throwable r5 = new java.lang.Throwable
            r5.<init>()
            java.lang.String r5 = android.util.Log.getStackTraceString(r5)
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r4 = r4.toString()
            android.util.Log.e(r1, r4)
        L41:
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
